package biz.belcorp.ssicc.web.spusicc.flexipago.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.BaseOID;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.GrupoFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.GrupoVariableFLX;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spusicc.flexipago.MantenimientoFLXModeloOtorgamientoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.flexipago.form.GrupoVariableForm;
import biz.belcorp.ssicc.web.spusicc.flexipago.form.MantenimientoFLXGruposForm;
import biz.belcorp.ssicc.web.spusicc.flexipago.form.MantenimientoFLXGruposRegionesForm;
import biz.belcorp.ssicc.web.spusicc.flexipago.form.MantenimientoFLXGruposSearchForm;


@ManagedBean
@SessionScoped
public class MantenimientoFLXGruposSearchAction extends BaseMantenimientoSearchAbstractAction{

	private static final long serialVersionUID = -8764916882152250277L;
	
	private List flxGrupoList;

	@Override
	protected String getSalirForward() {		
		return "mantenimientoFLXGruposList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {		
		return "mantenimientoFLXGruposForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoFLXGruposSearchForm searchForm = new MantenimientoFLXGruposSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes - MantenimientoFLXGruposSearchAction");
		}
		
		MantenimientoFLXGruposSearchForm f = (MantenimientoFLXGruposSearchForm) this.formBusqueda;
		MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService)getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");
		String descripcion = f.getDescripcion();
		
		List gruposFLXList = (List)service.getGrupos("%" + (StringUtils.isBlank(descripcion)?"":StringUtils.trim(descripcion)));
		this.flxGrupoList=gruposFLXList;		
		return gruposFLXList;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		
		MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService)getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");
		GrupoFLX sistemabusqueda = (GrupoFLX) this.beanRegistroSeleccionado;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		String id =sistemabusqueda.getCodigo();
		service.removeGrupo(id, usuario);		
		
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method - MantenimientoFLXGruposAction");
		}
		
		MantenimientoFLXGruposForm f = (MantenimientoFLXGruposForm) this.formMantenimiento;
		MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService)getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		boolean isNew = f.isNewRecord();
		
		GrupoFLX grupo = new GrupoFLX();
		
		BeanUtils.copyProperties(grupo, f);
		
        GrupoVariableForm[] variablesForm = f.getVariablesForm();
        if (variablesForm != null) {
            List variables = new ArrayList();
            for (int i = 0; i < variablesForm.length; i++) {
                GrupoVariableFLX  variable = new GrupoVariableFLX();
                BeanUtils.copyProperties(variable, variablesForm[i]);
                if(StringUtils.isBlank(variable.getValorPeso())){
                	variable.setValorPeso("0");
                }
                variables.add(variable);
            }
            grupo.setVariables(variables);
        }        
        try {        	
    		if(isNew){
    			service.insertGrupo(grupo, usuario);    			
    		}else{
    			service.updateGrupo(grupo, usuario);    			
    		}            
        }
        catch (InvalidIdentifierException iie) {
            String codigo = iie.getIdentifier().toString();
            throw new Exception(this.getResourceMessage("errors.invalid.id",new Object[]{codigo}));     
        }
        catch (InvalidDescriptionException ide) {
            String descripcion = ide.getDescription();
            throw new Exception(this.getResourceMessage("errors.invalid.description",new Object[]{descripcion}));
        }				
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		GrupoFLX sistemabusqueda = (GrupoFLX) this.beanRegistroSeleccionado;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoFLXGruposForm f = new MantenimientoFLXGruposForm();
		MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService)getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");
		
		List listVariables = service.getVariables(Constants.FLX_TIPO_VARIABLE_CALCULO_PROBABILIDAD_INCUMPLIMIENTO);
				
		GrupoVariableForm[] grupoVariableForm = new GrupoVariableForm[listVariables.size()];
		
        for (int i = 0; i < listVariables.size(); i++) {
        	BaseOID variable = (BaseOID)listVariables.get(i);
        	grupoVariableForm[i] = new GrupoVariableForm();
        	grupoVariableForm[i].setCodigoVariable(variable.getOid().toString());
        	grupoVariableForm[i].setNombreVariable(variable.getDescripcion());
        }
        f.setVariablesForm(grupoVariableForm);
        
        if (!this.accion.equals(this.ACCION_NUEVO)) {
			String id =sistemabusqueda.getCodigo();		
			String codigoPais = pais.getCodigo();
			
			if (id != null && codigoPais != null) {
				if (log.isDebugEnabled()) {
					log.debug("Id seleccionado de la lista: " + id+ " "+ codigoPais);
				}
				
				GrupoFLX grupo = service.getGrupo(id);	
				BeanUtils.copyProperties(f, grupo);
				
				List variables = grupo.getVariables();
				if (variables != null) {            	
	                GrupoVariableForm[] grupoVariableEdit = new GrupoVariableForm[variables.size()];
	                for (int i = 0; i < variables.size(); i++) {
	                	grupoVariableEdit[i] = new GrupoVariableForm();
	                    BeanUtils.copyProperties(grupoVariableEdit[i], variables.get(i));
	                }
	                
	                f.setVariablesForm(grupoVariableEdit);
	            }
				f.setCodigoPais(codigoPais);
				f.setNewRecord(false);				
			}
		}
        return f;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAttributes - MantenimientoFLXGruposSearchAction");
		}
		this.mostrarBotonConsultar=false;	
		
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoFLXGruposForm sistemaForm = (MantenimientoFLXGruposForm) this.formMantenimiento;
		boolean isNew = sistemaForm.isNewRecord();
		if (isNew) {
			return "mantenimientoFLXGruposForm.created";
		} else {
			return "mantenimientoFLXGruposForm.updated";
		}
	}

	public List getFlxGrupoList() {
		return flxGrupoList;
	}

	public void setFlxGrupoList(List flxGrupoList) {
		this.flxGrupoList = flxGrupoList;
	}

}
