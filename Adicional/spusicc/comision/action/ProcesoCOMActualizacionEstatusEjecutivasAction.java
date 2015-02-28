package biz.belcorp.ssicc.web.spusicc.comision.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMCalificacionComisionService;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCalculoCalificacionTramoService;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCalculoComisionRecuperacionEjecutivasService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaClientesPOPUPSearchAction;
import biz.belcorp.ssicc.web.spusicc.comision.form.ProcesoCOMActualizacionEstatusEjecutivasForm;

/**
 * The Class ProcesoCOMActualizacionEstatusEjecutivasAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 29/12/2014
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@ManagedBean
@SessionScoped
public class ProcesoCOMActualizacionEstatusEjecutivasAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = -3656445178095855959L;
	private List siccMarcaList;
	private List siccCanalList;
	private List comTipoComisionistaList;
	private List comTramoEjecutivasList;
	private List comNivelList;
	private boolean mostrarPopupCliente;
	private static final String POPUP_CLIENTE = "POPUP_CLIENTE";
	
	@ManagedProperty(value="#{busquedaClientesPOPUPSearchAction}")
	private BusquedaClientesPOPUPSearchAction busquedaClientesPOPUPSearchAction;
	
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoCOMActualizacionEstatusEjecutivasForm form = new ProcesoCOMActualizacionEstatusEjecutivasForm();
		return form;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executing :  setViewAttributes. ");
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ProcesoCOMCalculoCalificacionTramoService tramoService = (ProcesoCOMCalculoCalificacionTramoService) 
																getBean("spusicc.procesoCOMCalculoCalificacionTramoService");
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		ProcesoCOMActualizacionEstatusEjecutivasForm f = (ProcesoCOMActualizacionEstatusEjecutivasForm) this.formProceso;
		f.setCodigoPais(pais.getCodigo());
		Map criteria = new HashMap();
        criteria.put("codigoPais", f.getCodigoPais());
        criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
        criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
        criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());
        
        //recuperamos el oid Pais
        String oidPais = clienteService.getOidPais(criteria);
        f.setOidPais(oidPais);
        criteria.put("oidPais", oidPais);
        //recuperamos la longitud del codigo de cliente para el pais logueado
        f.setLongitudCodigoCliente(clienteService.getLongitudCodigoCliente(criteria));
        
		this.siccMarcaList = service.getMarcas();		
		this.siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.comTipoComisionistaList = tramoService.getTiposComisionistas(pais.getCodigo());
		this.comTramoEjecutivasList = tramoService.getTramos(pais.getCodigo());
		MantenimientoCOMCalificacionComisionService calificacionComisionService = (MantenimientoCOMCalificacionComisionService) getBean("spusicc.mantenimientoCOMCalificacionComisionService");
		this.comNivelList = calificacionComisionService.getNivelList(pais.getCodigo());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy"); 
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		f.setTipoComisionista(Constants.CODIGO_TIPO_COMISIONISTA_DEFAULT);
		f.setAnioInicTramo(sdf.format(new Date(System.currentTimeMillis())));
	}
	
	public String setValidarProceso() {
		ProcesoCOMActualizacionEstatusEjecutivasForm f = (ProcesoCOMActualizacionEstatusEjecutivasForm) this.formProceso;
		if(!f.getCodigoClienteBuscar().isEmpty()){
	    	AjaxService ajax = (AjaxService) getBean("ajaxService");
	  		String codigoCliente = completarCaracteres(f.getCodigoClienteBuscar(), f.getLongitudCodigoCliente(), "0"); 
	  		String data = ajax.getExisteCodigoCliente(f.getCodigoPais(), codigoCliente);	  		
	  		if(data.isEmpty()) {
	  			String mensaje =  this.getResourceMessage("procesoCOMActualizacionEstatusEjecutivasForm.nivelActual.vacio");
				return mensaje;
			}	  		
	    } else {	    	
	    	f.setNombreCliente("");    	  
	    }		
		return null;	
	}
	
	private String completarCaracteres(String valor, String longitud, String caracter) {		
		String valorAux = new String("");		
		if (valor.length() != 0) {
			int longitudInt = Integer.parseInt(longitud);
			int faltante = longitudInt - valor.length();
			if (faltante >= 0) {
				for (int i = 0; i < faltante; i++) {
					valorAux = valorAux + caracter;
				}
				valorAux = valorAux + valor;
			} else {			
				faltante = valor.length() - longitudInt;
				valorAux = valor.substring(faltante, longitudInt);
			}
		}		
		return valorAux;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) throws Exception {
		log.debug("Los parametros executeProcess son: " + params.toString());
		
		ProcesoCOMCalculoComisionRecuperacionEjecutivasService procesoService = 
			                   (ProcesoCOMCalculoComisionRecuperacionEjecutivasService)
			                   				getBean("spusicc.procesoCOMCalculoComisionRecuperacionEjecutivasService");
		procesoService.executeActualizacionEstatusEjecutivas(params);
		return params;
	}
	
	/**
	 * Metodo que obtiene que limpia las variables del formulario.
	 * 
	 * @param val - Objeto que representa un evento del framework primefaces a través del AJAX
	 */
	public void clearEjecutiva(ValueChangeEvent val){
		log.debug(">>clearEjecutiva ");
		ProcesoCOMActualizacionEstatusEjecutivasForm f = (ProcesoCOMActualizacionEstatusEjecutivasForm) this.formProceso;	
		f.setCodigoClienteBuscar("");
		f.setNombreCliente("");
	}
	
	@SuppressWarnings("static-access")
	@Override
	protected void setInvocarPopup(String accion) {
		if (accion.equals(this.POPUP_CLIENTE)){ 
			this.mostrarPopupCliente = true;
		}
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopupHipCliente' method");
		}
		
		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;
		if (accion.equals(this.POPUP_CLIENTE)) {
			this.busquedaClientesPOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaClientesPOPUPSearchAction.isSeleccionoRegistro()) {
				Map cliente = (Map) this.busquedaClientesPOPUPSearchAction.getBeanRegistroSeleccionado();				
				ProcesoCOMActualizacionEstatusEjecutivasForm f = (ProcesoCOMActualizacionEstatusEjecutivasForm) this.formProceso;				
				f.setCodigoClienteBuscar(cliente.get("codigo").toString());
				String nombre = cliente.get("nombre1").toString() +" " + cliente.get("apellido1").toString()+" "+cliente.get("apellido2").toString();				
				f.setNombreCliente(nombre);
				this.busquedaClientesPOPUPSearchAction.setBeanRegistroSeleccionado(null);
				this.formProceso =  f;				
			}
		}	
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}
	}
	
	@Override
	protected void setSalirPopup() {
		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;
		this.busquedaClientesPOPUPSearchAction.setBeanRegistroSeleccionado(null);
	}
	

	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * @return the comTipoComisionistaList
	 */
	public List getComTipoComisionistaList() {
		return comTipoComisionistaList;
	}

	/**
	 * @param comTipoComisionistaList the comTipoComisionistaList to set
	 */
	public void setComTipoComisionistaList(List comTipoComisionistaList) {
		this.comTipoComisionistaList = comTipoComisionistaList;
	}

	/**
	 * @return the comTramoEjecutivasList
	 */
	public List getComTramoEjecutivasList() {
		return comTramoEjecutivasList;
	}

	/**
	 * @param comTramoEjecutivasList the comTramoEjecutivasList to set
	 */
	public void setComTramoEjecutivasList(List comTramoEjecutivasList) {
		this.comTramoEjecutivasList = comTramoEjecutivasList;
	}

	/**
	 * @return the comNivelList
	 */
	public List getComNivelList() {
		return comNivelList;
	}

	/**
	 * @param comNivelList the comNivelList to set
	 */
	public void setComNivelList(List comNivelList) {
		this.comNivelList = comNivelList;
	}

	/**
	 * @return the mostrarPopupCliente
	 */
	public boolean isMostrarPopupCliente() {
		return mostrarPopupCliente;
	}

	/**
	 * @return the busquedaClientesPOPUPSearchAction
	 */
	public BusquedaClientesPOPUPSearchAction getBusquedaClientesPOPUPSearchAction() {
		return busquedaClientesPOPUPSearchAction;
	}

	/**
	 * @param busquedaClientesPOPUPSearchAction the busquedaClientesPOPUPSearchAction to set
	 */
	public void setBusquedaClientesPOPUPSearchAction(
			BusquedaClientesPOPUPSearchAction busquedaClientesPOPUPSearchAction) {
		this.busquedaClientesPOPUPSearchAction = busquedaClientesPOPUPSearchAction;
	}

	/**
	 * @param mostrarPopupCliente the mostrarPopupCliente to set
	 */
	public void setMostrarPopupCliente(boolean mostrarPopupCliente) {
		this.mostrarPopupCliente = mostrarPopupCliente;
	}

}
