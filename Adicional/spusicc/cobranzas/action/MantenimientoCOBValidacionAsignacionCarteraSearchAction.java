package biz.belcorp.ssicc.web.spusicc.cobranzas.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.ValidacionAsignacionCartera;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ConsultaCOBGenericoService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.MantenimientoCOBGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.MantenimientoCOBValidacionAsignacionCarteraForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.MantenimientoCOBValidacionAsignacionCarteraSearchForm;

/**
 * The Class MantenimientoCOBValidacionAsignacionCarteraSearchAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 20/01/2015
 */
@SuppressWarnings({ "rawtypes" })
@ManagedBean
@SessionScoped
public class MantenimientoCOBValidacionAsignacionCarteraSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 3803975727560961593L;
	private List siccEtapaDeudaList;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoCOBValidacionAsignacionCarteraForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCOBValidacionAsignacionCarteraSearchForm searchForm = new MantenimientoCOBValidacionAsignacionCarteraSearchForm();
		return searchForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {		
		if (log.isDebugEnabled()) {
		   this.log.debug("JFA Entering: setViewAttributes");
		}
		
		MantenimientoCOBValidacionAsignacionCarteraSearchForm f = (MantenimientoCOBValidacionAsignacionCarteraSearchForm) this.formBusqueda;
		String codigoPais = this.mPantallaPrincipalBean.getCountryCode();
		f.setCodigoPais(codigoPais);
						
		/* Obteniedo la Lista de Etapas de Deuda */
		ConsultaCOBGenericoService consultaCOBService = (ConsultaCOBGenericoService) this.getBean("spusicc.consultaCOBGenericoService");
		this.siccEtapaDeudaList = consultaCOBService.getEtapasDeuda(); 				
		
		/* Obteniendo valores */
		ValidacionAsignacionCartera bean = new ValidacionAsignacionCartera();
		bean.setCodigoPais(codigoPais);
		
		/* Obteniendo Lista */				
		find(); 
		
		this.mostrarBotonNuevo = false;
		this.mostrarBotonEliminar = false;
		
		if (log.isDebugEnabled()) {
			this.log.debug("JFA Todo Ok: Redireccionando");
		}		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {	
		if (log.isDebugEnabled()) {
			   this.log.debug("JFA Entering: setFindAttributes");
		}
		
		/* obteniendo valores */
		MantenimientoCOBValidacionAsignacionCarteraSearchForm f = (MantenimientoCOBValidacionAsignacionCarteraSearchForm) this.formBusqueda;
		
		ValidacionAsignacionCartera bean = new ValidacionAsignacionCartera();
		bean.setCodigoPais(f.getCodigoPais());
		bean.setCodigoEtapaDeuda(f.getCodigoEtapaDeuda());

		MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) 
		this.getBean("spusicc.mantenimientoCOBGenericoService");
		List resultado = service.getListaValidacionAsignacionCartera(bean);
		if (log.isDebugEnabled()) {
			this.log.debug("JFA Todo Ok: Redireccionando");
		}		
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		try {			
			MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) 
			getBean("spusicc.mantenimientoCOBGenericoService");	

			Map mapa = (Map) this.beanRegistroSeleccionado;
			
			ValidacionAsignacionCartera validacionAsignacionCartera = new ValidacionAsignacionCartera();
			
			BeanUtils.copyProperties(validacionAsignacionCartera, mapa);

			service.deleteValidacionAsignacionCartera(validacionAsignacionCartera, usuario);
			
			this.getResourceMessage("mantenimientoCOBValidacionAsignacionCarteraForm.deleted");
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) {
				error = e.getLocalizedMessage();
			}
			throw new Exception(this.getResourceMessage("errors.detail", new Object[]{ error }));
		}			
		return true;
	}
	
	@Override
	protected String getSalirForward() {
		return "mantenimientoCOBValidacionAsignacionCarteraList";
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'setSaveAttributes' method");
		}
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		MantenimientoCOBValidacionAsignacionCarteraForm f = (MantenimientoCOBValidacionAsignacionCarteraForm) this.formMantenimiento;
		
		MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) getBean("spusicc.mantenimientoCOBGenericoService");
			
		ValidacionAsignacionCartera cobrador = new ValidacionAsignacionCartera();

		BeanUtils.copyProperties(cobrador, f);	
		
		cobrador.setIndicadorActivo(obtenerValor(f.isIndicadorActivoB()));
		
		if (!f.isNewRecord()) {
			if (log.isDebugEnabled()) {
				log.debug("EN EL CASO QUE SEA MODIFICACION");
			}
			service.updateValidacionAsignacionCartera(cobrador, usuario);
		} else {
			if (log.isDebugEnabled()) {
				log.debug("EN EL CASO QUE SEA CREACION");
			}
			service.insertValidacionAsignacionCartera(cobrador, usuario);
		}

		return true;		
	}
	
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		log.debug("JFA Action: Entering setConsultarAttributes");
		
		MantenimientoCOBValidacionAsignacionCarteraForm mantenimientoForm = new MantenimientoCOBValidacionAsignacionCarteraForm();
		
		if (!this.accion.equals(this.ACCION_NUEVO)) { 			
			
			Map mapa = (Map) this.beanRegistroSeleccionado;
			
			ValidacionAsignacionCartera validacionAsignacionCartera = new ValidacionAsignacionCartera();
			
			BeanUtils.copyProperties(validacionAsignacionCartera, mapa);
			
			MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) 
													getBean("spusicc.mantenimientoCOBGenericoService");	
			log.debug("JFA - obtener Bean");
			validacionAsignacionCartera = service.getValidacionAsignacionCartera(validacionAsignacionCartera);
							
			BeanUtils.copyProperties(mantenimientoForm, validacionAsignacionCartera);	
			
			mantenimientoForm.setIndicadorActivoB(obtenerValorBoolean(mantenimientoForm.getIndicadorActivo()));
			mantenimientoForm.setNewRecord(false);
			
			log.debug("JFA - Todo OK");	
		}
		
		return mantenimientoForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoCOBValidacionAsignacionCarteraForm mantenimientoForm = (MantenimientoCOBValidacionAsignacionCarteraForm) this.formMantenimiento;
		boolean isNew = mantenimientoForm.isNewRecord();
		if(isNew) {
			return "mantenimientoCOBValidacionAsignacionCarteraForm.add";
		} else {
			return "mantenimientoCOBValidacionAsignacionCarteraForm.updated";
		}	
	}	
	
	private boolean obtenerValorBoolean(String indicador) {
		if(indicador != null && indicador.endsWith("1")) {
			return true;
		} else {
			return false;
		}
	}
	
	private String obtenerValor(boolean estado) {
		if(estado) {
			return "1";
		} else {
			return "0";
		}
	}
	

	/**
	 * @return the siccEtapaDeudaList
	 */
	public List getSiccEtapaDeudaList() {
		return siccEtapaDeudaList;
	}


	/**
	 * @param siccEtapaDeudaList the siccEtapaDeudaList to set
	 */
	public void setSiccEtapaDeudaList(List siccEtapaDeudaList) {
		this.siccEtapaDeudaList = siccEtapaDeudaList;
	}	
				
}
