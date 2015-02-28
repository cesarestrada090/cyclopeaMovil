package biz.belcorp.ssicc.web.spusicc.zon.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.zon.MantenimientoZONDirectorioService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaClientesPOPUPSearchAction;
import biz.belcorp.ssicc.web.spusicc.zon.form.MantenimientoZONIngresoDirectorioForm;

/**
 * The Class MantenimientoZONIngresoDirectorioAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 27/01/2015
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@ManagedBean
@SessionScoped
public class MantenimientoZONIngresoDirectorioAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 7322183438961300901L;
	private List zonTipoCargoList;
	private List siccRegionList;
	private List siccZonaList;
	private String titular;
	private String tipoUnidadAdministrativa;
	private String cantidadUnidad;
	private boolean bregion;
	private boolean bzona;
	private boolean bregionArray;
	private boolean bzonaArray;
	
	private boolean mostrarPopupCliente;
	private static final String POPUP_CLIENTE = "POPUP_CLIENTE";
	
	@ManagedProperty(value="#{busquedaClientesPOPUPSearchAction}")
	private BusquedaClientesPOPUPSearchAction busquedaClientesPOPUPSearchAction;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoZONIngresoDirectorioForm searchForm = new MantenimientoZONIngresoDirectorioForm();
		return searchForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		MantenimientoZONIngresoDirectorioForm f = (MantenimientoZONIngresoDirectorioForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		f.setCodigoPais(pais.getCodigo());							
		/* obteniendo valores */
		MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService) this.getBean("spusicc.mantenimientoZONDirectorioService");								
		this.zonTipoCargoList = service.getTipoCargo(new HashMap());
		
		//recuperamos el oid Pais
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		Map criteria = new HashMap();
        criteria.put("codigoPais", f.getCodigoPais());
        criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
        criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
        criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());
        String oidPais = clienteService.getOidPais(criteria);
        f.setOidPais(oidPais);
        criteria.put("oidPais", oidPais);
        //recuperamos la longitud del codigo de cliente para el pais logueado
        f.setLongitudCodigoCliente(clienteService.getLongitudCodigoCliente(criteria));
        
        Date fecha = new Date(System.currentTimeMillis());
        f.setFechaIngresoD(fecha);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        f.setFechaIngreso(sdf.format(fecha));
		
		this.siccRegionList = new ArrayList();
		this.siccZonaList = new ArrayList();
		
		this.mostrarBotonSave = true;
		this.mostrarBotonBuscar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonEliminar = false;
		
		////////////
		Map mapa = (Map) this.zonTipoCargoList.get(this.zonTipoCargoList.size()-1);
    	titular = mapa.get("titular").toString();
		
		
		this.log.debug("Todo Oki");	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return false;
	}
	
	@Override
	protected String getSalirForward() {
		return null;
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method");
		}
		MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService) 
														this.getBean("spusicc.mantenimientoZONDirectorioService");	
		MantenimientoZONIngresoDirectorioForm f = (MantenimientoZONIngresoDirectorioForm) this.formBusqueda;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        f.setFechaIngreso(sdf.format(f.getFechaIngresoD()));
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map map = BeanUtils.describe(f);
		map.put("usuario", usuario.getLogin());
		map.put("mensajeError", null);
		
		map.put("codigoRegion",f.getCodigoRegionList() !=null ? Arrays.asList(f.getCodigoRegionList()) : f.getCodigoRegion());
		map.put("codigoZona", f.getCodigoZonaList() !=null ? Arrays.asList(f.getCodigoZonaList()) : f.getCodigoZona());
		
		map.put("tipoUnidad", this.tipoUnidadAdministrativa);
	    map.put("cantidadUnidad", this.cantidadUnidad);
		
		log.debug("map validacion xx " + map);
		
		try {
		   service.executeValidacionDirectorio(map);
		} catch(Exception e) {
			   log.debug("hay error en valdacion "+e.getMessage());
			    String mensajeError= e.getMessage();
				String [] error =  mensajeError.split("_");
				int codigo = Integer.parseInt(error[0]);			
				switch (codigo) {
				case 0:
					 this.getResourceMessage("mantenimientoZonIngresoDirectorio.no.gerente");
					 break;
				case 1:
					this.getResourceMessage("mantenimientoZonIngresoDirectorio.inactivo.temporal");
					break;
				case 2:				
					this.getResourceMessage("mantenimientoZonIngresoDirectorio.region.titular");
					break;				
				case 3:				
					this.getResourceMessage("mantenimientoZonIngresoDirectorio.zona.titular");
					break;					
				}	
				log.debug("codigo cargo xy " + f.getCodigoCargo());
				return false;
		}		
		this.getResourceMessage("mantenimientoZonIngresoDirectorio.ingreso.ok");
		return true;
	}
	
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		return null;
	}
	
	/**
	 * Metodo para Ocultar
	 * 
	 * @param val
	 */
	public void jsCargarUA(ValueChangeEvent val) {
        String codigoCargo = val.getNewValue().toString();
        if(!codigoCargo.isEmpty()) {
        	for(Object arrObjectCargo : this.zonTipoCargoList) {
        		Map mapa = (Map) arrObjectCargo;
        		if(mapa.get("codigoCargo").toString().equals(codigoCargo)) {
        			String titulartemp = "";
        			this.tipoUnidadAdministrativa = mapa.get("tipoUnidadAdministrativa").toString();
        			if(this.tipoUnidadAdministrativa.equals("Z")) {
	               	 	titulartemp = "0";  
	                } else {    
	               	 	titulartemp = mapa.get("titular").toString();
	                } 
	                AjaxService ajaxService = (AjaxService) getBean("ajaxService");
	                this.siccRegionList = Arrays.asList(ajaxService.getRegionesDirectorio(titulartemp));	                
	                
	                this.bregion = false;
	                this.bzona = false;
	                this.bregionArray = false;
	                this.bzonaArray = false;
	                if(this.siccRegionList != null && !this.siccRegionList.isEmpty()) {
		                MantenimientoZONIngresoDirectorioForm f = (MantenimientoZONIngresoDirectorioForm) this.formBusqueda;
		                this.cantidadUnidad = mapa.get("cantidadUnidadAdministrativa").toString();
		                if(this.cantidadUnidad.equals("U")) {	       
		                	this.bregion = true;
		                	this.bregionArray = false;
		                	if(this.tipoUnidadAdministrativa.equals("Z")) {
		                		LabelValue value = (LabelValue) this.siccRegionList.get(0);
		                		loadZonasRegion(value.getValue());
		                		this.bzona = true;
		                	} else {
		                		this.bzona = false;
		                	}
		                	
		                } else {
		                	this.bregion = false;
		                	this.bregionArray = true;
		                	if(this.tipoUnidadAdministrativa.equals("Z")) {
		                		loadZonas2Region(f.getCodigoRegionList());
		                		this.bzonaArray = true;
		                	} else {
		                		this.bzonaArray = false;
		                	}
		                }
	                }
	                break;
        		}
        	}
        }              
    }
	
	/**
	 * loadZonas
	 * 
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val) { 		
    	String region = val.getNewValue().toString(); 	
    	String values[] = new String[0];    	    	
    	if (region.isEmpty()) {
    		this.siccZonaList = new ArrayList();
		} else {				
			values[0] = region;
	    	AjaxService ajaxService = (AjaxService) getBean("ajaxService");
	    	this.siccZonaList = Arrays.asList(ajaxService.getZonasDirectorio(this.titular, values, "0"));
		}	
	}
	
	public void loadZonasRegion(String region) { 		 	
    	String values[] = new String[1];    	    	
    	if (region.isEmpty()) {
    		this.siccZonaList = new ArrayList();
		} else {				
			values[0] = region;
	    	AjaxService ajaxService = (AjaxService) getBean("ajaxService");
	    	this.siccZonaList = Arrays.asList(ajaxService.getZonasDirectorio(this.titular, values, "0"));
		}	
	}
	
	/**
	 * loadZonas2
	 * 
	 * @param val
	 */
	public void loadZonas2(ValueChangeEvent val) {    	
		String regiones[] = (String[]) val.getNewValue();
    	if(regiones != null && regiones.length > 0) {
    		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
    		this.siccZonaList = Arrays.asList(ajaxService.getZonasDirectorio(this.titular, regiones, "0"));  		
    	} else {
    		this.siccZonaList = new ArrayList();
    	}
    }
	
	/**
	 * loadZonas2
	 * 
	 * @param regiones  
	 */
	public void loadZonas2Region(String[] regiones) {    	
		if(regiones != null && regiones.length > 0) {
    		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
    		this.siccZonaList = Arrays.asList(ajaxService.getZonasDirectorio(this.titular, regiones, "0"));  		
    	} else {
    		this.siccZonaList = new ArrayList();
    	}
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
				MantenimientoZONIngresoDirectorioForm f = (MantenimientoZONIngresoDirectorioForm) this.formBusqueda;
				f.setCodigoClienteBuscar(cliente.get("codigo").toString());
				StringBuilder nombre = new StringBuilder();
				nombre.append(cliente.get("apellido1").toString()).append(" ");
				nombre.append(cliente.get("apellido2").toString()).append(" ");
				nombre.append(cliente.get("nombre1").toString()).append(" ");
				nombre.append(cliente.get("nombre2").toString());
				f.setNombreCliente(nombre.toString());
				f.setNumeroDocumentoIdentidad(cliente.get("documentoIdentidad").toString());
				this.busquedaClientesPOPUPSearchAction.setBeanRegistroSeleccionado(null);
				this.formBusqueda =  f;	
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
	 * @return the zonTipoCargoList
	 */
	public List getZonTipoCargoList() {
		return zonTipoCargoList;
	}

	/**
	 * @param zonTipoCargoList the zonTipoCargoList to set
	 */
	public void setZonTipoCargoList(List zonTipoCargoList) {
		this.zonTipoCargoList = zonTipoCargoList;
	}

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public List getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(List siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the titular
	 */
	public String getTitular() {
		return titular;
	}

	/**
	 * @param titular the titular to set
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}

	/**
	 * @return the bregion
	 */
	public boolean isBregion() {
		return bregion;
	}

	/**
	 * @param bregion the bregion to set
	 */
	public void setBregion(boolean bregion) {
		this.bregion = bregion;
	}

	/**
	 * @return the bzona
	 */
	public boolean isBzona() {
		return bzona;
	}

	/**
	 * @param bzona the bzona to set
	 */
	public void setBzona(boolean bzona) {
		this.bzona = bzona;
	}

	/**
	 * @return the bregionArray
	 */
	public boolean isBregionArray() {
		return bregionArray;
	}

	/**
	 * @param bregionArray the bregionArray to set
	 */
	public void setBregionArray(boolean bregionArray) {
		this.bregionArray = bregionArray;
	}

	/**
	 * @return the bzonaArray
	 */
	public boolean isBzonaArray() {
		return bzonaArray;
	}

	/**
	 * @param bzonaArray the bzonaArray to set
	 */
	public void setBzonaArray(boolean bzonaArray) {
		this.bzonaArray = bzonaArray;
	}

	public boolean isMostrarPopupCliente() {
		return mostrarPopupCliente;
	}

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

	/**
	 * @return the tipoUnidadAdministrativa
	 */
	public String getTipoUnidadAdministrativa() {
		return tipoUnidadAdministrativa;
	}

	/**
	 * @param tipoUnidadAdministrativa the tipoUnidadAdministrativa to set
	 */
	public void setTipoUnidadAdministrativa(String tipoUnidadAdministrativa) {
		this.tipoUnidadAdministrativa = tipoUnidadAdministrativa;
	}

	/**
	 * @return the cantidadUnidad
	 */
	public String getCantidadUnidad() {
		return cantidadUnidad;
	}

	/**
	 * @param cantidadUnidad the cantidadUnidad to set
	 */
	public void setCantidadUnidad(String cantidadUnidad) {
		this.cantidadUnidad = cantidadUnidad;
	}

}