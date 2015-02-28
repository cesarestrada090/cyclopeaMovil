package biz.belcorp.ssicc.web.spusicc.cobranzas.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.ExcepcionAsignacionCartera;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ConsultaCOBGenericoService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.MantenimientoCOBGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.MantenimientoCOBExcepcionAsignacionCarteraForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.MantenimientoCOBExcepcionAsignacionCarteraSearchForm;

/**
 * The Class MantenimientoCOBExcepcionAsignacionCarteraSearchAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 20/01/2015
 */
@SuppressWarnings({ "rawtypes" })
@ManagedBean
@SessionScoped
public class MantenimientoCOBExcepcionAsignacionCarteraSearchAction extends BaseMantenimientoSearchAbstractAction {
	
	private static final long serialVersionUID = 6822664457502359528L;
	private List siccEtapaDeudaList;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoCOBExcepcionAsignacionCarteraForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCOBExcepcionAsignacionCarteraSearchForm searchForm = new MantenimientoCOBExcepcionAsignacionCarteraSearchForm();
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
		
		MantenimientoCOBExcepcionAsignacionCarteraSearchForm f = (MantenimientoCOBExcepcionAsignacionCarteraSearchForm) this.formBusqueda;
		String codigoPais = this.mPantallaPrincipalBean.getCountryCode();
		f.setCodigoPais(codigoPais);
						
		/* Obteniedo la Lista de Etapas de Deuda */
		ConsultaCOBGenericoService consultaCOBService = (ConsultaCOBGenericoService) this.getBean("spusicc.consultaCOBGenericoService");
		this.siccEtapaDeudaList = consultaCOBService.getEtapasDeuda(); 
				
		
		/* Obteniendo valores */
		ExcepcionAsignacionCartera bean = new ExcepcionAsignacionCartera();
		bean.setCodigoPais(codigoPais);
		
		/* Obteniendo valores */
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
		MantenimientoCOBExcepcionAsignacionCarteraSearchForm f = (MantenimientoCOBExcepcionAsignacionCarteraSearchForm) this.formBusqueda;
		
		ExcepcionAsignacionCartera bean = new ExcepcionAsignacionCartera();
		bean.setCodigoPais(f.getCodigoPais());
		bean.setCodigoEtapaDeuda(f.getCodigoEtapaDeuda());

		MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) 
		this.getBean("spusicc.mantenimientoCOBGenericoService");
		List resultado = service.getListaExcepcionAsignacionCartera(bean);
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
			
			ExcepcionAsignacionCartera excepcionAsignacionCartera = new ExcepcionAsignacionCartera();
			
			BeanUtils.copyProperties(excepcionAsignacionCartera, mapa);

			service.deleteExcepcionAsignacionCartera(excepcionAsignacionCartera, usuario);
			
			this.getResourceMessage("mantenimientoCOBExcepcionAsignacionCarteraForm.deleted");
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
		return "mantenimientoCOBExcepcionAsignacionCarteraList";
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'setSaveAttributes' method");
		}
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		MantenimientoCOBExcepcionAsignacionCarteraForm f = (MantenimientoCOBExcepcionAsignacionCarteraForm) this.formMantenimiento;
		
		MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) getBean("spusicc.mantenimientoCOBGenericoService");
		
		ExcepcionAsignacionCartera cobrador = new ExcepcionAsignacionCartera();
		
		BeanUtils.copyProperties(cobrador, f);	
		
		cobrador.setIndicadorActivo(obtenerValor(f.isIndicadorActivoB()));
		
		if (!f.isNewRecord()) {
			if (log.isDebugEnabled()) {
				log.debug("EN EL CASO QUE SEA MODIFICACION");
			}
			service.updateExcepcionAsignacionCartera(cobrador, usuario);
		} else {
			if (log.isDebugEnabled()) {
				log.debug("EN EL CASO QUE SEA CREACION");
			}
			service.insertExcepcionAsignacionCartera(cobrador, usuario);
		}
		return true;
	}
	
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		log.debug("JFA Action: Entering setConsultarAttributes");

		MantenimientoCOBExcepcionAsignacionCarteraForm mantenimientoForm = new MantenimientoCOBExcepcionAsignacionCarteraForm();
		
		if (!this.accion.equals(this.ACCION_NUEVO)) {  		
			Map mapa = (Map) this.beanRegistroSeleccionado;			
			
			ExcepcionAsignacionCartera excepcionAsignacionCartera = new ExcepcionAsignacionCartera();
			
			BeanUtils.copyProperties(excepcionAsignacionCartera, mapa);
			
			MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) 
													getBean("spusicc.mantenimientoCOBGenericoService");	
			log.debug("JFA - obtener Bean");
			excepcionAsignacionCartera = service.getExcepcionAsignacionCartera(excepcionAsignacionCartera);
							
			BeanUtils.copyProperties(mantenimientoForm, excepcionAsignacionCartera);			
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
		MantenimientoCOBExcepcionAsignacionCarteraForm mantenimientoForm = (MantenimientoCOBExcepcionAsignacionCarteraForm) this.formMantenimiento;
		boolean isNew = mantenimientoForm.isNewRecord();
		if(isNew) {
			return "mantenimientoCOBExcepcionAsignacionCarteraForm.add";
		} else {
			return "mantenimientoCOBExcepcionAsignacionCarteraForm.updated";
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
