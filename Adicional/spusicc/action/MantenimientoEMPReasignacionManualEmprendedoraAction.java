package biz.belcorp.ssicc.web.spusicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultorasAction;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoEMPReasignacionManualEmprendedoraForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoEMPReasignacionManualEmprendedoraAction extends
BaseMantenimientoSearchAbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4831008411765334067L;
	private List empMotivoBajaList=new ArrayList();
	private boolean mostrarPopUpCliente = false;
	private static final String POPUP_CLIENTES = "SCLIENTES";

	@ManagedProperty(value = "#{busquedaConsultorasAction}")
	private BusquedaConsultorasAction busquedaConsultorasAction;
	
	
	
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}
		if (accion.equals(this.POPUP_CLIENTES)) {
			this.busquedaConsultorasAction.verificarRegistro(event);
			if (this.busquedaConsultorasAction.isSeleccionoRegistro()) {
				Map clienteHipMap = (Map) this.busquedaConsultorasAction
						.getBeanRegistroSeleccionado();
				MantenimientoEMPReasignacionManualEmprendedoraForm f = (MantenimientoEMPReasignacionManualEmprendedoraForm) this.formBusqueda;			
	//[apellido2=VILCA, indActi=NO, codigoCliente=010079403, null, nombre2=OLGA, nombre1=SILVIA, null, numeroDocumento=0020104009, null, tipoDocumento=Doc. Nacional Identidad (DNI) - Bolt., null, null, null, null, null, codigoZona=6031]
				f.setCodigoCliente((String)clienteHipMap.get("codigoCliente") );
				this.busquedaConsultorasAction
						.setBeanRegistroSeleccionado(null);
			}
		}
	}
	@Override
	protected void setSalirPopup() {
		this.mostrarPopUpCliente = false;
		this.busquedaConsultorasAction
				.setBeanRegistroSeleccionado(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setInvocarPopup(java.lang.String)
	 */

	@Override
	protected void setInvocarPopup(String accion) {
		this.mostrarProcesoBatch = false;

		if (accion.equals(this.POPUP_CLIENTES)) {
			this.mostrarPopUpCliente = true;
		}
	}

	public boolean isMostrarPopUpCliente() {
		return mostrarPopUpCliente;
	}

	public void setMostrarPopUpCliente(boolean mostrarPopUpCliente) {
		this.mostrarPopUpCliente = mostrarPopUpCliente;
	}


	public BusquedaConsultorasAction getbusquedaConsultorasAction() {
		return busquedaConsultorasAction;
	}
	public void setbusquedaConsultorasAction(
			BusquedaConsultorasAction busquedaConsultorasAction) {
		this.busquedaConsultorasAction = busquedaConsultorasAction;
	}
	public static String getPopupClientes() {
		return POPUP_CLIENTES;
	}
	

	
	public List getEmpMotivoBajaList() {
		return empMotivoBajaList;
	}

	public void setEmpMotivoBajaList(List empMotivoBajaList) {
		this.empMotivoBajaList = empMotivoBajaList;
	}

	@Override
	protected String getSalirForward() {
		return "mantenimientoEMPReasignacionManualEmprendedoraForm";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoEMPReasignacionManualEmprendedoraForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoEMPReasignacionManualEmprendedoraForm searchForm = new MantenimientoEMPReasignacionManualEmprendedoraForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		log.debug("find Cliente");
		MantenimientoEMPReasignacionManualEmprendedoraForm f = (MantenimientoEMPReasignacionManualEmprendedoraForm) formBusqueda;

		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

		
		//Obtenemos los datos del usuario Logueado
		Usuario usuario =mPantallaPrincipalBean.getCurrentUser();
		
        // Obtiene la información de la consultora
        Map criteria = new HashMap();
        criteria.put("codigoPais", f.getCodigoPais());
        criteria.put("codigoCliente",  f.getCodigoClienteBuscar());

        Map map = new HashMap();
        map.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry().getCodigo());
        map.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa
        map.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
        MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
        // Obtiene la informacion de la campaña actual
        PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(map);
        
        log.debug("Campaña actual --> " + controlFacturacion.getCodigoPeriodo());
                        
        //limpiamos los datos del formulario
        inicializar(f);
        
        Map mapDatosCliente = clienteService.getDatosEmprendedoraCliente(criteria);
        //log.debug(mapDatosCliente.toString());
        Map mapDatosCliente2 = clienteService.getDatosCliente(criteria);
        //log.debug(mapDatosCliente2.toString());
        
        if(mapDatosCliente2 != null) {
        	
        	map.put("codigoPeriodo", controlFacturacion.getCodigoPeriodo());
        	map.put("codigoZona", (String)mapDatosCliente2.get("codigozona"));
        	
        	// Valida que la region de la consultora no haya cerrado en la campaña actual        	                	
        	if(clienteService.getZonaPeriodoCerrada(map).equals(Constants.SI)){
        		addInfo("Mensaje", this.getResourceMessage("mantenimientoEMPReasignacionManualEmprendedoraForm.msg.regionCerrada"));
        	}
        	else{
        	
	        	f.setNombreConsultora((String)mapDatosCliente2.get("nombreCliente"));        	
	        	f.setCodigoZonaConsultora((String)mapDatosCliente2.get("codigozona"));
	        	try {
									
		        	f.setNombreCliente((String)mapDatosCliente.get("nombreCliente"));
		        	f.setRegion((String)mapDatosCliente.get("region"));
		        	f.setZona((String)mapDatosCliente.get("zona"));
		        	f.setIndicadorEmprendedora((String)mapDatosCliente.get("indicadorEmprendedora"));
		        	f.setCodigoCliente((String)mapDatosCliente.get("codigoCliente"));
	        		        	
		        	criteria.put("indicadorEmprendedora", (String)mapDatosCliente.get("indicadorEmprendedora"));
	        	
	        	} catch (Exception e) {
					// TODO: handle exception
				}
	        	
	        	// Obtiene los motivos de baja para emprendedoras y pre emprendedoras
	        	
	        	empMotivoBajaList=clienteService.getMotivosBaja(criteria);
	           	f.setClienteEncontrado(true);
        	}
           	
        } else {

			addInfo("Mensaje", this.getResourceMessage("mantenimientoEMPReasignacionManualEmprendedoraForm.msg.clienteNoEncontrado"));
        }
        
        return null;
		
	}
	public void buscarConsultora(){
		log.debug("find Cliente");
		MantenimientoEMPReasignacionManualEmprendedoraForm f = (MantenimientoEMPReasignacionManualEmprendedoraForm) formBusqueda;

		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

		
		//Obtenemos los datos del usuario Logueado
		Usuario usuario =mPantallaPrincipalBean.getCurrentUser();
		
        // Obtiene la información de la consultora
        Map criteria = new HashMap();
        criteria.put("codigoPais", f.getCodigoPais());

        criteria.put("codigoCliente",  f.getCodigoCliente());

        Map map = new HashMap();
        map.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry().getCodigo());
        map.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa
        map.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
        MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
        // Obtiene la informacion de la campaña actual
        PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(map);
        
        log.debug("Campaña actual --> " + controlFacturacion.getCodigoPeriodo());
                        
        //limpiamos los datos del formulario
        inicializar(f);
        
        Map mapDatosCliente = clienteService.getDatosEmprendedoraCliente(criteria);
        //log.debug(mapDatosCliente.toString());
        Map mapDatosCliente2 = clienteService.getDatosCliente(criteria);
        //log.debug(mapDatosCliente2.toString());
        
        if(mapDatosCliente2 != null) {
        	
        	map.put("codigoPeriodo", controlFacturacion.getCodigoPeriodo());
        	map.put("codigoZona", (String)mapDatosCliente2.get("codigozona"));
        	
        	// Valida que la region de la consultora no haya cerrado en la campaña actual        	                	
        	if(clienteService.getZonaPeriodoCerrada(map).equals(Constants.SI)){
        		addInfo("Mensaje", this.getResourceMessage("mantenimientoEMPReasignacionManualEmprendedoraForm.msg.regionCerrada"));
        	}
        	else{
        	
	        	f.setNombreConsultora((String)mapDatosCliente2.get("nombreCliente"));
	        	f.setCodigoCliente((String)mapDatosCliente2.get("codigoCliente"));
	        	//f.setCodigoCliente((String)mapDatosCliente2.get("codigoCliente")+" "+(String)mapDatosCliente2.get("nombreCliente"));
	        	f.setCodigoZonaConsultora((String)mapDatosCliente2.get("codigozona"));
	        	try {
									
		        	f.setNombreCliente((String)mapDatosCliente.get("nombreCliente"));
		        	f.setRegion((String)mapDatosCliente.get("region"));
		        	f.setZona((String)mapDatosCliente.get("zona"));
		        	f.setIndicadorEmprendedora((String)mapDatosCliente.get("indicadorEmprendedora"));
		        	//f.setCodigoCliente((String)mapDatosCliente.get("codigoCliente"));
	        		        	
		        	criteria.put("indicadorEmprendedora", (String)mapDatosCliente.get("indicadorEmprendedora"));
	        	
	        	} catch (Exception e) {
					// TODO: handle exception
				}
	        	
	        	// Obtiene los motivos de baja para emprendedoras y pre emprendedoras
	        	
	        	empMotivoBajaList=clienteService.getMotivosBaja(criteria);
	           	f.setClienteEncontrado(true);
        	}
           	
        } else {

			addInfo("Mensaje", this.getResourceMessage("mantenimientoEMPReasignacionManualEmprendedoraForm.msg.clienteNoEncontrado"));
        }
        
	}

	public void buscarEmprendedora() throws Exception {
		log.debug("find Cliente");
		MantenimientoEMPReasignacionManualEmprendedoraForm f = (MantenimientoEMPReasignacionManualEmprendedoraForm) formBusqueda;

		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");


		
		//Obtenemos los datos del usuario Logueado
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		
        // Obtiene la información de la consultora
        Map criteria = new HashMap();
        criteria.put("codigoPais", f.getCodigoPais());
        criteria.put("codigoCliente",  f.getClienteBuscarEmp());

        //limpiamos los datos del formulario
        //inicializar(f);
        
        Map mapDatosCliente = clienteService.getDatosCliente(criteria);
        
        //log.debug(mapDatosCliente.toString());
        
        if(mapDatosCliente != null) {
        	        	        	
        	//Obtiene la zona de la nueva empresaria
        	        	
        	// --- Se realizo el cambio porque las regulares y fastrack no graban el codigozonaPrograma ---
        	// --- se trabajara con la zona de la consultora que se obtiene con la funcion gen_fn_clien_datos ---
        	
        	f.setCodigoZonaNuevaSocia((String)mapDatosCliente.get("codigozonaPrograma"));
        	//f.setCodigoZonaNuevaSocia((String)mapDatosCliente.get("zona"));
        	
        	//log.debug("---> CodigoZonaNuevaSocia ---> "+f.getCodigoZonaNuevaSocia());
        	
        	//Valida que sea empresaria
        	if(mapDatosCliente.get("indicadorEmprendedora")==null){
        		
    			addError("Mensaje", getResourceMessage("mantenimientoEMPReasignacionManualEmprendedoraForm.msg.noEsEmpresaria"));
            	
        	}
        	
        	//Valida que no haya sido dada de baja
        	if(!(mapDatosCliente.get("indicadorBaja")==null)){
    			addError("Mensaje", getResourceMessage("mantenimientoEMPReasignacionManualEmprendedoraForm.msg.empresariaBaja"));

        	}
        	
        	//log.debug("---> Compara  ---> ");
        	//log.debug("---> CodigoZonaNuevaSocia ---> "+f.getCodigoZonaNuevaSocia());
        	//log.debug("---> CodigoZonaConsultora ---> "+f.getCodigoZonaConsultora());
        	
        	// La compara a ver si la zona conicide con la consultora a reasignarse
        	if(f.getCodigoZonaConsultora().trim().equals(f.getCodigoZonaNuevaSocia().trim())){
        		
        		log.debug("---> Son iguales  ---> ");
        		        		
        		f.setNombreClienteEmp((String)mapDatosCliente.get("nombreCliente"));
            	f.setRegionEmp((String)mapDatosCliente.get("region"));
            	f.setZonaEmp((String)mapDatosCliente.get("zona"));
            	f.setIndicadorEmprendedoraEmp((String)mapDatosCliente.get("indicadorEmprendedora"));        	
            	
            	criteria.put("indicadorEmprendedora", (String)mapDatosCliente.get("indicadorEmprendedora"));
        		
        		// Obtiene los motivos de baja para emprendedoras y pre emprendedoras
            	empMotivoBajaList=clienteService.getMotivosBaja(criteria);
            	            	
               	f.setClienteEncontrado(true);
               	f.setEmprendedoraEncontrada(true);
        	}
        	else{
        		log.debug("---> No son iguales  ---> ");
    			addError("Mensaje", getResourceMessage("mantenimientoEMPReasignacionManualEmprendedoraForm.msg.zonaNoCoincide"));
        	}
        	        	           	
        } else {
			addError("Mensaje", getResourceMessage("mantenimientoEMPReasignacionManualEmprendedoraForm.msg.emprendedoraNoEncontrado"));
        }
        
	}



	
	@Override
	protected boolean setDeleteAttributes() throws Exception {

		return true;

	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}
		boolean bGrabarCab = false;
				
		
		MantenimientoEMPReasignacionManualEmprendedoraForm f = (MantenimientoEMPReasignacionManualEmprendedoraForm) formBusqueda;		
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
						
		Map criteria = new HashMap();
				
		//Obtenemos los datos del usuario Logueado
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		
		criteria.put("codigoCliente", f.getCodigoCliente());
		//log.debug("---- codigoCliente ------>   "+f.getCodigoClienteBuscar());
		
		criteria.put("codigoUsuario", usuario.getLogin());
		//log.debug("---- codigoUsuario ------>   "+usuario.getLogin());
		
		criteria.put("motivo", f.getMotivo());
		//log.debug("---- motivo ------>   "+f.getMotivo());
		
		criteria.put("codigoNuevaEmprendedora", f.getClienteBuscarEmp());
		//log.debug("---- codigoNuevaEmprendedora ------>   "+f.getClienteBuscarEmp());
		
		try {
			 									
			clienteService.executeReasignacionManualEmpresarias(criteria);
			//messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("mantenimientoEMPReasignacionManualEmprendedoraForm.success"));
			
			//limpiamos los datos del formulario
	        inicializar(f);
	        f.setCodigoCliente("");
			bGrabarCab = true;
			f.setEmprendedoraEncontrada(false);
			f.setClienteEncontrado(false);
			return true;
				
		} catch (Exception e) {
			e.printStackTrace();
			bGrabarCab = false;
			addError("Error", getResourceMessage("errors.invalid.transaction.parametro")+e.getMessage());
			return false;
		}


	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {

		
		return null;

	}


	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonConsultar=false;
		this.mostrarBotonNuevo=false;
		this.mostrarBotonModificar=false;
		this.mostrarBotonEliminar=false;
		log.debug("add new");
		MantenimientoEMPReasignacionManualEmprendedoraForm f = (MantenimientoEMPReasignacionManualEmprendedoraForm) formBusqueda;

		f.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry().getCodigo());		

		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		     
        //inicializa la lista de motivos de baja
        
        
        inicializar(f);
        f.setCodigoClienteBuscar("");     
        f.setNombreConsultora("");

	}
	
	private void inicializar(MantenimientoEMPReasignacionManualEmprendedoraForm f) {
		f.setCodigoCliente("");
		f.setNombreCliente("");
		f.setRegion("");
		f.setZona("");
		f.setIndicadorEmprendedora("");
		f.setClienteEncontrado(false);
		f.setEmprendedoraEncontrada(false);
		
		f.setNombreConsultora("");
		//f.setCodigoClienteBuscar("");
		f.setClienteBuscarEmp("");
		f.setNombreClienteEmp("");
		f.setRegionEmp("");
		f.setZonaEmp("");
		f.setTipoEmp("");
		f.setIndicadorEmprendedoraEmp("");
		this.empMotivoBajaList=null;
	}


	/**
	 * @param
	 * @throws Exception
	 */

	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoEMPReasignacionManualEmprendedoraForm f = (MantenimientoEMPReasignacionManualEmprendedoraForm) this.formBusqueda;
		boolean isNew = f.isNewRecord();
		if (isNew) {
			return "mantenimientoEMPReasignacionManualEmprendedoraForm.success";
		} else {
			return "mantenimientoEMPReasignacionManualEmprendedoraForm.success";
		}
		

	}

}
