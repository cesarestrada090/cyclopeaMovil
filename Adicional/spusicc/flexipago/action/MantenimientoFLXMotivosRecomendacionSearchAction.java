package biz.belcorp.ssicc.web.spusicc.flexipago.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.EstatusRecomendacionFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.MotivoRecomendacionFLX;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spusicc.flexipago.MantenimientoFLXModeloOtorgamientoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.flexipago.form.MantenimientoFLXMotivosRecomendacionForm;
import biz.belcorp.ssicc.web.spusicc.flexipago.form.MantenimientoFLXMotivosRecomendacionSearchForm;


@ManagedBean
@SessionScoped
public class MantenimientoFLXMotivosRecomendacionSearchAction extends BaseMantenimientoSearchAbstractAction{

	private static final long serialVersionUID = -4385398503844574260L;
	
	private List flxMotivoRecomendacionList;
	private List flxEstatusRecomendacionList;

	@Override
	protected String getSalirForward() {		
		return "mantenimientoFLXMotivosRecomendacionList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {		
		return "mantenimientoFLXMotivosRecomendacionForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoFLXMotivosRecomendacionSearchForm searchForm = new MantenimientoFLXMotivosRecomendacionSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes - MantenimientoFLXMotivosRecomendacionSearchAction");
		}
		
		MantenimientoFLXMotivosRecomendacionSearchForm f = (MantenimientoFLXMotivosRecomendacionSearchForm) this.formBusqueda;
		MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService)getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");
		String descripcion = f.getDescripcion();
		
		Map params = BeanUtils.describe(f);
		params.put("descripcion", "%" + (StringUtils.isBlank(descripcion)?"":StringUtils.trim(descripcion)));
		
		List motivos = (List)service.getMotivosRecomendacionByCriteria(params);
		this.flxMotivoRecomendacionList=motivos;	
		return motivos;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setDeleteAttributes' ");
		}
		
		MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService)getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");
		MotivoRecomendacionFLX sistemabusqueda = (MotivoRecomendacionFLX) this.beanRegistroSeleccionado;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		String id = sistemabusqueda.getCodigo();
		service.removeMotivoRecomendacion(id, usuario);
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method - MantenimientoFLXMotivosRecomendacionAction");
		}		
		
		MantenimientoFLXMotivosRecomendacionForm f = (MantenimientoFLXMotivosRecomendacionForm) this.formMantenimiento;
		MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService)getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		boolean isNew = f.isNewRecord();
		
		MotivoRecomendacionFLX motivo = new MotivoRecomendacionFLX();		
		BeanUtils.copyProperties(motivo, f);
		
		EstatusRecomendacionFLX estatusRecomendacion = new EstatusRecomendacionFLX();
		estatusRecomendacion.setCodigo(f.getCodigoEstatus());
		motivo.setEstatusRecomendacion(estatusRecomendacion);
		
        try {           
            if (isNew) {
    			service.insertMotivoRecomendacion(motivo, usuario);    			
            }
            else {
    			service.updateMotivoRecomendacion(motivo, usuario);    			
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
		MotivoRecomendacionFLX sistemabusqueda = (MotivoRecomendacionFLX) this.beanRegistroSeleccionado;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoFLXMotivosRecomendacionForm f = new MantenimientoFLXMotivosRecomendacionForm();
		MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService)getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");
		this.flxEstatusRecomendacionList=service.getEstatusRecomendacionByCriteria(null);
		
		if (!this.accion.equals(this.ACCION_NUEVO)) {
			String id = sistemabusqueda.getCodigo();
			String codigoPais = pais.getCodigo();
			
			if (id != null && codigoPais != null) {
				if (log.isDebugEnabled()) {
					log.debug("Id seleccionado de la lista: " + id + " "+ codigoPais);
				}
				
				MotivoRecomendacionFLX motivo = service.getMotivoRecomendacion(id);
				BeanUtils.copyProperties(f, motivo);
				f.setCodigoEstatus(motivo.getEstatusRecomendacion().getCodigo());
				f.setCodigoPais(codigoPais);
				f.setNewRecord(false);				
			}				
		}
		return f;		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAttributes - MantenimientoFLXMotivosRecomendacionSearchAction");
		}
		this.mostrarBotonConsultar=false;
		
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoFLXMotivosRecomendacionForm sistemaForm = (MantenimientoFLXMotivosRecomendacionForm) this.formMantenimiento;
		boolean isNew = sistemaForm.isNewRecord();
		if (isNew) {
			return "mantenimientoFLXMotivosRecomendacionForm.created";
		} else {
			return "mantenimientoFLXMotivosRecomendacionForm.updated";
		}
	}

	public List getFlxMotivoRecomendacionList() {
		return flxMotivoRecomendacionList;
	}

	public void setFlxMotivoRecomendacionList(List flxMotivoRecomendacionList) {
		this.flxMotivoRecomendacionList = flxMotivoRecomendacionList;
	}

	public List getFlxEstatusRecomendacionList() {
		return flxEstatusRecomendacionList;
	}

	public void setFlxEstatusRecomendacionList(List flxEstatusRecomendacionList) {
		this.flxEstatusRecomendacionList = flxEstatusRecomendacionList;
	}
	

}
