/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Cliente;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoAtencionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultoraPOPUPSearchAction;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.ConsultaRECIngresoAtencionAnulacionForm;

/**
 * @author sbuchelli - Sergio Buchelli
 */
@ManagedBean
@SessionScoped
public class ConsultaRECIngresoAtencionAnulacionAction extends BaseConsultaAbstractAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5647449951895809425L;

	private String flagVistaProcesoBatch; 
	private String numeroLote;
	
	private boolean mostrarPopupCliente;
	private static final String POPUP_CLIENTE = "POPUP_CLIENTE";
	
	@ManagedProperty(value="#{busquedaConsultoraPOPUPSearchAction}")
	private BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction;
	
	@ManagedProperty(value="#{consultaRECIngresoAtencionAnulacionVerDetalleAction}")
	private ConsultaRECIngresoAtencionAnulacionVerDetalleAction consultaRECIngresoAtencionAnulacionVerDetalleAction;
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaRECIngresoAtencionAnulacionForm form = new ConsultaRECIngresoAtencionAnulacionForm();
		return form;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ConsultaRECIngresoAtencionAnulacionForm f = (ConsultaRECIngresoAtencionAnulacionForm) this.formBusqueda;
		f.setCodigoPais(pais.getCodigo());
		f.setNumeroLote("");
		f.setFlagRolDelete(Constants.NO);
		f.setTipoConsulta("");
		this.flagVistaProcesoBatch = (String)this.parametrosPantalla.get("flagVistaProcesoBatch");	
		this.numeroLote = (String)this.parametrosPantalla.get("numeroLote");	
	}
	
	
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}
		MantenimientoRECIngresoAtencionesService service = 
				(MantenimientoRECIngresoAtencionesService)getBean("spusicc.mantenimientoRECIngresoAtencionesService");
		ConsultaRECIngresoAtencionAnulacionForm f = (ConsultaRECIngresoAtencionAnulacionForm) this.formBusqueda;
        String fecha = DateUtil.getDate(f.getFechaD());
		f.setFecha(fecha);
		
		
		Map params = new HashMap();
		params.put("numeroLote", f.getNumeroLote());
		params.put("fecha", f.getFecha());
		params.put("usuario", f.getUsuario());
		params.put("codigoCliente", f.getCodigoCliente());
		List resultado = new ArrayList();
		if(Constants.NUMERO_CERO.equals(f.getTipoConsulta())
				)//ATENCIONES
			resultado = service.getAtencion(params);
		else
			resultado = service.getAnulacion(params);//ANULACIONES
		
		
		this.consultaRECIngresoAtencionAnulacionVerDetalleAction.setListaPadre(resultado);
		return resultado;
		
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setInvocarPopup(java.lang.String)
	 */
	@Override
	protected void setInvocarPopup(String accion) {
		if (accion.equals(this.POPUP_CLIENTE)){ 
			this.mostrarPopupCliente = true;
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopupHipCliente' method");
		}
		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;
		if (accion.equals(this.POPUP_CLIENTE)) {
			this.busquedaConsultoraPOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaConsultoraPOPUPSearchAction.isSeleccionoRegistro()) {	
				
				Cliente cliente= (Cliente)this.busquedaConsultoraPOPUPSearchAction.getBeanRegistroSeleccionado(); 
				ConsultaRECIngresoAtencionAnulacionForm f = (ConsultaRECIngresoAtencionAnulacionForm) this.formBusqueda;
				f.setCodigoCliente(cliente.getCodigo());
				this.busquedaConsultoraPOPUPSearchAction.setBeanRegistroSeleccionado(null);
				this.formBusqueda =  f;
				
				
			}
		}	
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setSalirPopup()
	 */
	@Override
	protected void setSalirPopup() {
		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;
		this.busquedaConsultoraPOPUPSearchAction.setBeanRegistroSeleccionado(null);
	}
	
	
	
	
	/**
	 * @return the flagVistaProcesoBatch
	 */
	public String getFlagVistaProcesoBatch() {
		return flagVistaProcesoBatch;
	}

	/**
	 * @param flagVistaProcesoBatch the flagVistaProcesoBatch to set
	 */
	public void setFlagVistaProcesoBatch(String flagVistaProcesoBatch) {
		this.flagVistaProcesoBatch = flagVistaProcesoBatch;
	}

	/**
	 * @return the numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote the numeroLote to set
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	/**
	 * @return the mostrarPopupCliente
	 */
	public boolean isMostrarPopupCliente() {
		return mostrarPopupCliente;
	}

	/**
	 * @param mostrarPopupCliente the mostrarPopupCliente to set
	 */
	public void setMostrarPopupCliente(boolean mostrarPopupCliente) {
		this.mostrarPopupCliente = mostrarPopupCliente;
	}

	/**
	 * @return the busquedaConsultoraPOPUPSearchAction
	 */
	public BusquedaConsultoraPOPUPSearchAction getBusquedaConsultoraPOPUPSearchAction() {
		return busquedaConsultoraPOPUPSearchAction;
	}

	/**
	 * @param busquedaConsultoraPOPUPSearchAction the busquedaConsultoraPOPUPSearchAction to set
	 */
	public void setBusquedaConsultoraPOPUPSearchAction(
			BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction) {
		this.busquedaConsultoraPOPUPSearchAction = busquedaConsultoraPOPUPSearchAction;
	}

	/**
	 * @return the consultaRECIngresoAtencionAnulacionVerDetalleAction
	 */
	public ConsultaRECIngresoAtencionAnulacionVerDetalleAction getConsultaRECIngresoAtencionAnulacionVerDetalleAction() {
		return consultaRECIngresoAtencionAnulacionVerDetalleAction;
	}

	/**
	 * @param consultaRECIngresoAtencionAnulacionVerDetalleAction the consultaRECIngresoAtencionAnulacionVerDetalleAction to set
	 */
	public void setConsultaRECIngresoAtencionAnulacionVerDetalleAction(
			ConsultaRECIngresoAtencionAnulacionVerDetalleAction consultaRECIngresoAtencionAnulacionVerDetalleAction) {
		this.consultaRECIngresoAtencionAnulacionVerDetalleAction = consultaRECIngresoAtencionAnulacionVerDetalleAction;
	}
	
	
	
	
}
