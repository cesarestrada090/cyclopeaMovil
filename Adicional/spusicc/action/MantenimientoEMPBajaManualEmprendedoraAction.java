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

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultorasAction;
import biz.belcorp.ssicc.web.spusicc.emprendedoras.form.MantenimientoEMPEmprendedoraForm;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoEMPBajaManualEmprendedoraForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoEMPBajaManualEmprendedoraAction extends
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
				MantenimientoEMPBajaManualEmprendedoraForm f = (MantenimientoEMPBajaManualEmprendedoraForm) this.formBusqueda;			
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
		return "mantenimientoCRAGrupoZonaList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoCRAGrupoZonaForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoEMPBajaManualEmprendedoraForm searchForm = new MantenimientoEMPBajaManualEmprendedoraForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		MantenimientoEMPBajaManualEmprendedoraForm f = (MantenimientoEMPBajaManualEmprendedoraForm) formBusqueda;

		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

		
		//Obtenemos los datos del usuario Logueado
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		
        // Obtiene la información de la consultora
        Map criteria = new HashMap();
        criteria.put("codigoPais", f.getCodigoPais());
        criteria.put("codigoCliente",  f.getCodigoCliente());

        //limpiamos los datos del formulario
        inicializar(f);
        
        Map mapDatosCliente = clienteService.getDatosCliente(criteria);
        if(mapDatosCliente != null) {
        	
        	if(StringUtils.isEmpty((String)mapDatosCliente.get("indicadorEmprendedora"))){
        		
            	
    			 addError("Atención", this.getResourceMessage("mantenimientoEMPBajaManualEmprendedoraForm.msg.clienteNoEmpresaria"));
    			f.setCodigoClienteBuscar("");
        	}
        	
        	else{     
        		
        		if(!StringUtils.isEmpty((String)mapDatosCliente.get("indicadorBaja"))){
        			if(mapDatosCliente.get("indicadorBaja").toString().equals("1")){
        				
        	        	addError("Atención", this.getResourceMessage("mantenimientoEMPBajaManualEmprendedoraForm.msg.clienteDeBaja"));
        				f.setCodigoClienteBuscar("");
        			}
        		}
        		
	        	f.setNombreCliente((String)mapDatosCliente.get("nombreCliente"));
	        	f.setRegion((String)mapDatosCliente.get("region"));
	        	f.setZona((String)mapDatosCliente.get("zona"));
	        	f.setIndicadorEmprendedora((String)mapDatosCliente.get("indicadorEmprendedora"));
	        	f.setCodigoCliente((String)mapDatosCliente.get("codigoCliente"));
	        	
	        	criteria.put("indicadorEmprendedora", (String)mapDatosCliente.get("indicadorEmprendedora"));
	        	
	        	// Obtiene los motivos de baja para emprendedoras y pre emprendedoras
	        	empMotivoBajaList= clienteService.getMotivosBaja(criteria);
	        	
	        	
	           	f.setClienteEncontrado(true);           	
        	}
           	
        } else {
			addError("Atención", this.getResourceMessage("mantenimientoEMPBajaManualEmprendedoraForm.msg.clienteNoEncontrado"));
			f.setCodigoClienteBuscar("");
        }
        
        return null;
		
	}

	public void buscarAttributes() throws Exception {
		MantenimientoEMPBajaManualEmprendedoraForm f = (MantenimientoEMPBajaManualEmprendedoraForm) formBusqueda;

		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

		
		//Obtenemos los datos del usuario Logueado
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		
        // Obtiene la información de la consultora
        Map criteria = new HashMap();
        criteria.put("codigoPais", f.getCodigoPais());
        criteria.put("codigoCliente",  f.getCodigoCliente());

        //limpiamos los datos del formulario
        //inicializar(f);
        
        Map mapDatosCliente = clienteService.getDatosCliente(criteria);
        if(mapDatosCliente != null) {
        	
        	if(StringUtils.isEmpty((String)mapDatosCliente.get("indicadorEmprendedora"))){
        		
            	
    			 addError("Atención", this.getResourceMessage("mantenimientoEMPBajaManualEmprendedoraForm.msg.clienteNoEmpresaria"));
    			f.setCodigoClienteBuscar("");
        	}
        	
        	else{     
        		
        		if(!StringUtils.isEmpty((String)mapDatosCliente.get("indicadorBaja"))){
        			if(mapDatosCliente.get("indicadorBaja").toString().equals("1")){
        				
        	        	addError("Atención", this.getResourceMessage("mantenimientoEMPBajaManualEmprendedoraForm.msg.clienteDeBaja"));
        				f.setCodigoClienteBuscar("");
        			}
        		}
        		
	        	f.setNombreCliente((String)mapDatosCliente.get("nombreCliente"));
	        	f.setRegion((String)mapDatosCliente.get("region"));
	        	f.setZona((String)mapDatosCliente.get("zona"));
	        	f.setIndicadorEmprendedora((String)mapDatosCliente.get("indicadorEmprendedora"));
	        	f.setCodigoCliente((String)mapDatosCliente.get("codigoCliente"));
	        	
	        	criteria.put("indicadorEmprendedora", (String)mapDatosCliente.get("indicadorEmprendedora"));
	        	
	        	// Obtiene los motivos de baja para emprendedoras y pre emprendedoras
	        	empMotivoBajaList= clienteService.getMotivosBaja(criteria);
	        	
	        	
	           	f.setClienteEncontrado(true);           	
        	}
           	
        } else {
			addError("Atención", this.getResourceMessage("mantenimientoEMPBajaManualEmprendedoraForm.msg.clienteNoEncontrado"));
			f.setCodigoClienteBuscar("");
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
		
		MantenimientoEMPBajaManualEmprendedoraForm f = (MantenimientoEMPBajaManualEmprendedoraForm) formBusqueda;		
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
						
		Map criteria = new HashMap();
				
		//Obtenemos los datos del usuario Logueado
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		
		criteria.put("codigoUsuario", usuario.getLogin());
		criteria.put("codigoCliente", f.getCodigoCliente());
		criteria.put("motivo", f.getMotivo());
		
		boolean bGrabarCab = false;
		try {
			 									
			clienteService.executeBajaManualEmpresarias(criteria);
			
			//addInfo("Mensaje", this.getResourceMessage("mantenimientoEMPBajaManualEmprendedoraForm.success"));
			//limpiamos los datos del formulario
	        inicializar(f);
	        f.setCodigoClienteBuscar("");
			bGrabarCab = true;
				
		} catch (Exception e) {
			e.printStackTrace();
			bGrabarCab = false;
			
			addError("Mensaje", this.getResourceMessage("mantenimientoEDULocal.cabecera.error")+e.getMessage());
			return false;
		}
		


		return true;

	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {

		
		return null;

	}


	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("add new");
		this.mostrarBotonEliminar=false;
		this.mostrarBotonModificar=false;
		this.mostrarBotonConsultar=false;
		this.mostrarBotonNuevo=false;
		MantenimientoEMPBajaManualEmprendedoraForm f = (MantenimientoEMPBajaManualEmprendedoraForm) formBusqueda;

		f.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry().getCodigo());		

		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		     
        //inicializa la lista de motivos de baja
		
		empMotivoBajaList=new ArrayList();
     //   session.setAttribute(Constants.EMP_MOTIVO_BAJA_LIST, new ArrayList());
        
        inicializar(f);
        f.setCodigoClienteBuscar("");

	}
	
	private void inicializar(MantenimientoEMPBajaManualEmprendedoraForm f) {
		f.setCodigoCliente("");
		f.setNombreCliente("");
		f.setRegion("");
		f.setZona("");
		f.setIndicadorEmprendedora("");
		f.setClienteEncontrado(false);
		this.empMotivoBajaList.clear();
	}


	/**
	 * @param
	 * @throws Exception
	 */

	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoEMPBajaManualEmprendedoraForm f = (MantenimientoEMPBajaManualEmprendedoraForm) this.formBusqueda;
		boolean isNew = f.isNewRecord();
		if (isNew) {
			return "mantenimientoEMPBajaManualEmprendedoraForm.success";
		} else {
			return "mantenimientoEMPBajaManualEmprendedoraForm.success";
		}
		

	}

}
