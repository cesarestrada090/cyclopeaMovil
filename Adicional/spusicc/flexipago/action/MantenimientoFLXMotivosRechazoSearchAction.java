package biz.belcorp.ssicc.web.spusicc.flexipago.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.EstatusRechazoFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.MotivoRechazoFLX;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spusicc.flexipago.MantenimientoFLXModeloOtorgamientoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.cronograma.form.MantenimientoCRAActividadForm;
import biz.belcorp.ssicc.web.spusicc.cronograma.form.MantenimientoCRAActividadSearchForm;
import biz.belcorp.ssicc.web.spusicc.flexipago.form.MantenimientoFLXMotivosRechazoForm;
import biz.belcorp.ssicc.web.spusicc.flexipago.form.MantenimientoFLXMotivosRechazoSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoFLXMotivosRechazoSearchAction extends BaseMantenimientoSearchAbstractAction{

	
	private static final long serialVersionUID = 288081128257432713L;
	
	private List flxMotivoRechazoList;
	private List flxEstatusRechazoList;	

	@Override
	protected String getSalirForward() {		
		return "mantenimientoFLXMotivosRechazoList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {		
		return "mantenimientoFLXMotivosRechazoForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoFLXMotivosRechazoSearchForm searchForm = new MantenimientoFLXMotivosRechazoSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes - MantenimientoFLXMotivosRechazoSearchAction");
		}
		
		MantenimientoFLXMotivosRechazoSearchForm f = (MantenimientoFLXMotivosRechazoSearchForm) this.formBusqueda;
		MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService)getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");
		String descripcion = f.getDescripcion();
		
		Map params = BeanUtils.describe(f);
		params.put("descripcion", "%" + (StringUtils.isBlank(descripcion)?"":StringUtils.trim(descripcion)));
		
		List motivos = (List)service.getMotivosRechazoByCriteria(params);
		this.flxMotivoRechazoList=motivos;		
		return motivos;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setDeleteAttributes' method - MantenimientoFLXMotivosRechazoAction");
		}
		
		MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService)getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");
		MotivoRechazoFLX sistemabusqueda = (MotivoRechazoFLX) this.beanRegistroSeleccionado;
		Usuario usuario =this.mPantallaPrincipalBean.getCurrentUser();
		
		String id = sistemabusqueda.getCodigo();
		service.removeMotivoRechazo(id, usuario);		
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method - MantenimientoFLXMotivosRechazoAction");
		}		
		
		MantenimientoFLXMotivosRechazoForm f = (MantenimientoFLXMotivosRechazoForm) this.formMantenimiento;
		MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService)getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();		
		
		boolean isNew = f.isNewRecord();
		
		MotivoRechazoFLX motivo = new MotivoRechazoFLX();		
		BeanUtils.copyProperties(motivo, f);		

		EstatusRechazoFLX estatusRechazo = new EstatusRechazoFLX();
		estatusRechazo.setCodigo(f.getCodigoEstatus());
		motivo.setEstatusRechazo(estatusRechazo);
		
        try {           
            if (isNew) {
    			service.insertMotivoRechazo(motivo, usuario);    			
            }
            else {
    			service.updateMotivoRechazo(motivo, usuario);    			
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
		MotivoRechazoFLX sistemabusqueda = (MotivoRechazoFLX) this.beanRegistroSeleccionado;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoFLXMotivosRechazoForm f = new MantenimientoFLXMotivosRechazoForm();
		MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService)getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");
		this.flxEstatusRechazoList=service.getEstatusRechazoByCriteria(null);		
		
		if (!this.accion.equals(this.ACCION_NUEVO)) {
			String id = sistemabusqueda.getCodigo();
			String codigoPais = pais.getCodigo();			
			
			if (id != null && codigoPais != null) {
				if (log.isDebugEnabled()) {
					log.debug("Id seleccionado de la lista: " + id + " "+ codigoPais);
				}	
				
				MotivoRechazoFLX motivo = service.getMotivoRechazo(id);
				BeanUtils.copyProperties(f, motivo);
				
				f.setCodigoEstatus(motivo.getEstatusRechazo().getCodigo());
				f.setNewRecord(false);
			}	
		}		
		return f;
		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAttributes - MantenimientoFLXMotivosRechazoSearchAction");
		}
		this.mostrarBotonConsultar=false;
		
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoFLXMotivosRechazoForm sistemaForm = (MantenimientoFLXMotivosRechazoForm) this.formMantenimiento;
		boolean isNew = sistemaForm.isNewRecord();
		if (isNew) {
			return "mantenimientoFLXMotivosRechazoForm.created";
		} else {
			return "mantenimientoFLXMotivosRechazoForm.updated";
		}
	}

	public List getFlxMotivoRechazoList() {
		return flxMotivoRechazoList;
	}

	public void setFlxMotivoRechazoList(List flxMotivoRechazoList) {
		this.flxMotivoRechazoList = flxMotivoRechazoList;
	}

	public List getFlxEstatusRechazoList() {
		return flxEstatusRechazoList;
	}

	public void setFlxEstatusRechazoList(List flxEstatusRechazoList) {
		this.flxEstatusRechazoList = flxEstatusRechazoList;
	}	
}
