package biz.belcorp.ssicc.web.spusicc.mav.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.mav.ProcesoMAVAsignacionReemplazoGerenteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaClientesPOPUPSearchAction;
import biz.belcorp.ssicc.web.spusicc.mav.form.ProcesoMAVAsignacionReemplazoGerenteZonaForm;

/**
 * The Class ProcesoMAVAsignacionReemplazoGerenteZonaAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 02/02/2015
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@ManagedBean
@SessionScoped
public class ProcesoMAVAsignacionReemplazoGerenteZonaAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = 322829314892848608L;
	private List siccMarcaList;
	private List siccCanalList;
	private List mavGerentesList;
	private Map selectGerente;
	private boolean bmavGerentes;
	private boolean mostrarPopupCliente;
	private static final String POPUP_CLIENTE = "POPUP_CLIENTE";
	
	@ManagedProperty(value="#{busquedaClientesPOPUPSearchAction}")
	private BusquedaClientesPOPUPSearchAction busquedaClientesPOPUPSearchAction;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoMAVAsignacionReemplazoGerenteZonaForm form = new ProcesoMAVAsignacionReemplazoGerenteZonaForm();
		return form;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		
		ProcesoMAVAsignacionReemplazoGerenteZonaForm f = (ProcesoMAVAsignacionReemplazoGerenteZonaForm) this.formProceso;

		// Obtenemos los beans b�sicos de la sesi�n
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map params = new HashMap();
		params.put("codigoISO", usuario.getIdioma().getCodigoISO());
		params.put("codigoPais", pais.getCodigo());

		// Cargamos los combos a utilizar
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.siccMarcaList = svc.getMarcas();
		this.siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());

		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoPeriodo("");
		f.setCodigoClienteBuscar("");

		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		LabelValue[] periodos = clienteService.getPeriodosVigentesByPaisMarcaCanal(params);
		f.setCodigoPeriodo(periodos[0].getLabel());
		
		this.mostrarBotonExecute = false;
	}

	/**
	 * @param event 
	 * @throws Exception
	 */
	public void findAttributes(ActionEvent event) throws Exception {
		log.debug("Seting find Attributes.");
		ProcesoMAVAsignacionReemplazoGerenteZonaForm f = (ProcesoMAVAsignacionReemplazoGerenteZonaForm) this.formProceso;
	
        // Obtenemos las propiedades del bean como un 'Map'
        Map criteria = BeanUtils.describe(f);

        ProcesoMAVAsignacionReemplazoGerenteService service = 
						(ProcesoMAVAsignacionReemplazoGerenteService) getBean("spusicc.procesoMAVAsignacionReemplazoGerenteService");	

        this.mavGerentesList = service.getGerentesZonas(criteria);
		log.debug("List size :  " +this.mavGerentesList.size());
		
		if(this.mavGerentesList != null && !this.mavGerentesList.isEmpty()) {
			this.bmavGerentes = true;
		} else {
			this.bmavGerentes = false;
		}
	}
	
	public void grabar(ActionEvent event) {
		ProcesoMAVAsignacionReemplazoGerenteZonaForm f = (ProcesoMAVAsignacionReemplazoGerenteZonaForm) this.formProceso;
		if(this.selectGerente == null) {
			this.addInfo("Info: ", this.getResourceMessage("errors.select.item"));						
		} else {
			if(f.getCodigoClienteBuscar() != null && !f.getCodigoClienteBuscar().isEmpty()) {
				validarExecuteProceso(null);				
			} else {
				this.addInfo("Info: ", this.getResourceMessage("procesoMAVAsignacionReemplazoGerenteZonaForm.msg.clienteNoIngresado"));
			}
		}		
	}
	
	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'executeProceso' method");
		}
		ProcesoMAVAsignacionReemplazoGerenteZonaForm f = (ProcesoMAVAsignacionReemplazoGerenteZonaForm) this.formProceso;
		ProcesoMAVAsignacionReemplazoGerenteService service = (ProcesoMAVAsignacionReemplazoGerenteService) getBean("spusicc.procesoMAVAsignacionReemplazoGerenteService");	
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		try {

	        //recuperamos la seccion seleccionada
			
			String codigoZona = (String) this.selectGerente.get("codigoZona");
			String oidRegion = (String) this.selectGerente.get("oidRegion");
			String oidZona = (String) this.selectGerente.get("oidZona");

	        // Obtenemos las propiedades del bean como un 'Map'
	        
	        params.put("codigoPais", f.getCodigoPais());
	        params.put("codigoMarca", f.getCodigoMarca());
	        params.put("codigoCanal", f.getCodigoCanal());
	        params.put("codigoPeriodo", f.getCodigoPeriodo());
	        params.put("oidRegion", oidRegion);
	        params.put("oidZona", oidZona);
	        params.put("codigoCliente", f.getCodigoClienteBuscar());
	        params.put("codigoUsuario", usuario.getLogin());
	        
	        //recuperamos el oid Pais
	        String oidPais = clienteService.getOidPais(params);
	        params.put("oidPais", oidPais);
	        
	        boolean existeCliente = service.existeCodigoCliente(params);
			
			if(!existeCliente) {
				this.addInfo("Info: ", this.getResourceMessage("procesoMAVAsignacionReemplazoGerenteZonaForm.msg.clienteNoExiste",
						     new Object[]{f.getCodigoClienteBuscar()}));
			}
			else {
		        
				//ejecutamos la asignacion del gerente a la zona
				service.executeAsignacionGerenteZona(params);
				
				if(f.getCodigoClienteBuscar().equals("")) {
					this.addInfo("Info: ", this.getResourceMessage("procesoMAVAsignacionReemplazoGerenteZonaForm.msg.deasignacionCorrecta", 
							  new Object[]{codigoZona}));
				} else {
					String[] datos = new String[] {f.getCodigoClienteBuscar(), codigoZona};
					this.addInfo("Info: ", this.getResourceMessage("procesoMAVAsignacionReemplazoGerenteZonaForm.msg.asignacionCorrecta", datos));
				}
				
				//realizamos nuevamente la busqueda
				f.setCodigoClienteBuscar("");
				findAttributes(null);
			}
				
		}  catch (Exception e) {
			throw new Exception(this.getResourceMessage("procesoEDULocal.cabecera.error", new Object[]{e.getMessage()}));
		}
		return params;
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
				ProcesoMAVAsignacionReemplazoGerenteZonaForm f = (ProcesoMAVAsignacionReemplazoGerenteZonaForm) this.formProceso;				
				f.setCodigoClienteBuscar(cliente.get("codigo").toString());
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
	 * @return the mavGerentesList
	 */
	public List getMavGerentesList() {
		return mavGerentesList;
	}

	/**
	 * @param mavGerentesList the mavGerentesList to set
	 */
	public void setMavGerentesList(List mavGerentesList) {
		this.mavGerentesList = mavGerentesList;
	}

	/**
	 * @return the selectGerente
	 */
	public Map getSelectGerente() {
		return selectGerente;
	}

	/**
	 * @param selectGerente the selectGerente to set
	 */
	public void setSelectGerente(Map selectGerente) {
		this.selectGerente = selectGerente;
	}

	/**
	 * @return the bmavGerentes
	 */
	public boolean isBmavGerentes() {
		return bmavGerentes;
	}

	/**
	 * @param bmavGerentes the bmavGerentes to set
	 */
	public void setBmavGerentes(boolean bmavGerentes) {
		this.bmavGerentes = bmavGerentes;
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

}
