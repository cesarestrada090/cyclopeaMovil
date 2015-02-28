/*
 * 
 */
package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECLiquidacionBoletaRecojoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaHIPClientePOPUPSearchAction;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECLiquidacionBoletaRecojoForm;


// TODO: Auto-generated Javadoc
/**
 * The Class MantenimientoRECDigitacionCDRAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 05/12/2013
 */
@ManagedBean
@SessionScoped
public class MantenimientoRECLiquidacionBoletaRecojoAction extends BaseMantenimientoSearchAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The mantenimiento rec liquidacion boleta recojo cabecera list. */
	private List mantenimientoRECLiquidacionBoletaRecojoCabeceraList = new ArrayList();
	
	/** The sicc marca list. */
	private List siccMarcaList = new ArrayList();
	
	/** The sicc canal list. */
	private List siccCanalList = new ArrayList();
	
	/** The sicc region list. */
	private List siccRegionList = new ArrayList();
	
	/** The sicc zona list. */
	private List siccZonaList = new ArrayList();
	
	/** The estados list. */
	private List estadosList = new ArrayList();
	
	/** The longitud campo clientes. */
	private Integer longitudCampoClientes = 0;
	
	/** The mostrar popup ocr cliente. */
	private boolean mostrarPopupOCRCliente = false;
	
	/** The Constant POPUP_OCRCLIENTE. */
	private static final String POPUP_OCRCLIENTE = "OCRCLIENTE";
	
	/** The busqueda hip cliente popup search action. */
	@ManagedProperty(value="#{busquedaHIPClientePOPUPSearchAction}")
	private BusquedaHIPClientePOPUPSearchAction busquedaHIPClientePOPUPSearchAction;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopupHipCliente' method");
		}		
		
		if (accion.equals(this.POPUP_OCRCLIENTE)) {
			this.busquedaHIPClientePOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaHIPClientePOPUPSearchAction.isSeleccionoRegistro()) {								
				Map clienteHipMap = (Map)this.busquedaHIPClientePOPUPSearchAction.getBeanRegistroSeleccionado();				
				
				
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
		this.mostrarPopupOCRCliente = false;
		this.busquedaHIPClientePOPUPSearchAction.setBeanRegistroSeleccionado(null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setInvocarPopup(java.lang.String)
	 */
	@Override
	protected void setInvocarPopup(String accion) {
		this.mostrarProcesoBatch = false;
		if (accion.equals(this.POPUP_OCRCLIENTE)){ 
			this.mostrarPopupOCRCliente = true;
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoRECLiquidacionBoletaRecojoForm form = new MantenimientoRECLiquidacionBoletaRecojoForm(); 
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes");
		}
		MantenimientoRECLiquidacionBoletaRecojoForm f = (MantenimientoRECLiquidacionBoletaRecojoForm)this.formBusqueda;
		Map criteria = getCriteria(f);
		
		List regionList = (List)MapUtils.getObject(criteria, "regionList");
		List zonaList = (List)MapUtils.getObject(criteria, "zonaList");
		
		if(regionList == null || regionList.size() == 0 || (regionList.size() == 1 && StringUtils.isBlank(((String)regionList.get(0)))))
			criteria.put("regionList", null);

		if(zonaList == null || zonaList.size() == 0 || (zonaList.size() == 1 && StringUtils.isBlank(((String)zonaList.get(0)))))
			criteria.put("zonaList", null);
		
		List l = new ArrayList();
		MantenimientoRECLiquidacionBoletaRecojoService service = (MantenimientoRECLiquidacionBoletaRecojoService)getBean("spusicc.mantenimientoRECLiquidacionBoletaRecojoService");
		l = service.getCabecerasBoletasReclamo(criteria);
		this.mantenimientoRECLiquidacionBoletaRecojoCabeceraList = l;	
		setEstadisticas(f, criteria, service);
		
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		
		LabelValue[] lista = ajax.getZonasMultipleByPaisMarcaCanalRegion(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), 
																		Constants.CODIGO_MARCA_DEFAULT, 
																		Constants.CODIGO_CANAL_DEFAULT, 
																		f.getCodigoRegion(), "");
		
		if(regionList==null){
			lista = new LabelValue[]{};
		}
		
		this.setSiccRegionList(Arrays.asList(lista));
		
		return l;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		MantenimientoRECLiquidacionBoletaRecojoForm form = (MantenimientoRECLiquidacionBoletaRecojoForm)this.formBusqueda;
		form.setCodigoEstado("");		
		this.mostrarBotonConsultar = false;
		this.mostrarListaBusqueda = true;		
		this.loadCombos();
	}
	
	
	/**
	 * Load combos.
	 */
	private void loadCombos() {
		if(log.isDebugEnabled()){
			log.debug("loadCombos");
		}
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		MantenimientoRECLiquidacionBoletaRecojoService serviceRec = (MantenimientoRECLiquidacionBoletaRecojoService)getBean("spusicc.mantenimientoRECLiquidacionBoletaRecojoService");		
		this.setSiccMarcaList(service.getMarcas());
		this.setSiccCanalList(service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO()));
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		
		this.setSiccRegionList(reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion));
		
		this.setLongitudCampoClientes(clienteService.getTamanhoNumeroCliente(pais.getCodigo()));		
		this.setEstadosList(serviceRec.getEstados());
	}
	
	/**
	 * Gets the criteria.
	 *
	 * @param f the f
	 * @return the criteria
	 */
	private Map getCriteria(MantenimientoRECLiquidacionBoletaRecojoForm f) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		criteria.put("codigoPeriodo", f.getPeriodo());
		criteria.put("codigoPeriodoFinal", f.getPeriodoFinal());
		criteria.put("numeroBoleta", f.getNumeroBoleta());
		criteria.put("codigoConsultora", f.getCodigoConsultora());
		if(StringUtils.isNotBlank(f.getCodigoEstado())&&StringUtils.isNotEmpty(f.getCodigoEstado())){
			criteria.put("codigoEstado", f.getCodigoEstado());	
		}
		if(f.getCodigoRegion().length > 0){
			if(!f.getCodigoRegion()[0].equals("")){
				criteria.put("regionList",Arrays.asList(f.getCodigoRegion()));
				if(f.getCodigoZona() != null){
					criteria.put("zonaList",Arrays.asList(f.getCodigoZona()));
				}
			}
		}
		return criteria;
	}
	
	/**
	 * Sets the estadisticas.
	 *
	 * @param f the f
	 * @param criteria the criteria
	 * @param service the service
	 */
	private void setEstadisticas(MantenimientoRECLiquidacionBoletaRecojoForm f,
			Map criteria, MantenimientoRECLiquidacionBoletaRecojoService service) {
		f.setTotalBoletasRecojo(service.getTotalBR(criteria));
		f.setBoletasRecojoPendientes(service.getPendientesBR(criteria));
		f.setBoletasRecojoAprobadas(service.getAprobadasBR(criteria));
		f.setBoletasRecojoRechazadas(service.getRechazadasBR(criteria));
		f.setTotalCargos(service.getTotalCargosBR(criteria));
		f.setTotalAbonos(service.getTotalAbonosBR(criteria));
	}
	
	/**
	 * Show zonasx region.
	 *
	 * @param val the val
	 */
	public void showZonasxRegion(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if(StringUtils.isNotEmpty(val.getNewValue().toString()) 
				|| StringUtils.isNotBlank(val.getNewValue().toString())){
			String[] regionListado = (String[])val.getNewValue();
			log.debug(regionListado.length);
			
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			if(regionListado.length>0){
				siccZonaList = Arrays.asList(ajax.getZonasMultipleByPaisMarcaCanalRegion(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), "T", "VD", regionListado,"T"));			
			}else{
				siccZonaList = new ArrayList();
			}
		}
	}

	/**
	 * Gets the mantenimiento rec liquidacion boleta recojo cabecera list.
	 *
	 * @return the mantenimiento rec liquidacion boleta recojo cabecera list
	 */
	public List getMantenimientoRECLiquidacionBoletaRecojoCabeceraList() {
		return mantenimientoRECLiquidacionBoletaRecojoCabeceraList;
	}

	/**
	 * Sets the mantenimiento rec liquidacion boleta recojo cabecera list.
	 *
	 * @param mantenimientoRECLiquidacionBoletaRecojoCabeceraList the new mantenimiento rec liquidacion boleta recojo cabecera list
	 */
	public void setMantenimientoRECLiquidacionBoletaRecojoCabeceraList(
			List mantenimientoRECLiquidacionBoletaRecojoCabeceraList) {
		this.mantenimientoRECLiquidacionBoletaRecojoCabeceraList = mantenimientoRECLiquidacionBoletaRecojoCabeceraList;
	}

	/**
	 * Gets the sicc marca list.
	 *
	 * @return the sicc marca list
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * Sets the sicc marca list.
	 *
	 * @param siccMarcaList the new sicc marca list
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * Gets the sicc canal list.
	 *
	 * @return the sicc canal list
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * Sets the sicc canal list.
	 *
	 * @param siccCanalList the new sicc canal list
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * Gets the sicc region list.
	 *
	 * @return the sicc region list
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * Sets the sicc region list.
	 *
	 * @param siccRegionList the new sicc region list
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * Gets the longitud campo clientes.
	 *
	 * @return the longitud campo clientes
	 */
	public Integer getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	/**
	 * Sets the longitud campo clientes.
	 *
	 * @param longitudCampoClientes the new longitud campo clientes
	 */
	public void setLongitudCampoClientes(Integer longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}

	/**
	 * Gets the estados list.
	 *
	 * @return the estados list
	 */
	public List getEstadosList() {
		return estadosList;
	}

	/**
	 * Sets the estados list.
	 *
	 * @param estadosList the new estados list
	 */
	public void setEstadosList(List estadosList) {
		this.estadosList = estadosList;
	}

	/**
	 * Gets the sicc zona list.
	 *
	 * @return the sicc zona list
	 */
	public List getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * Sets the sicc zona list.
	 *
	 * @param siccZonaList the new sicc zona list
	 */
	public void setSiccZonaList(List siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * Gets the busqueda hip cliente popup search action.
	 *
	 * @return the busqueda hip cliente popup search action
	 */
	public BusquedaHIPClientePOPUPSearchAction getBusquedaHIPClientePOPUPSearchAction() {
		return busquedaHIPClientePOPUPSearchAction;
	}

	/**
	 * Sets the busqueda hip cliente popup search action.
	 *
	 * @param busquedaHIPClientePOPUPSearchAction the new busqueda hip cliente popup search action
	 */
	public void setBusquedaHIPClientePOPUPSearchAction(
			BusquedaHIPClientePOPUPSearchAction busquedaHIPClientePOPUPSearchAction) {
		this.busquedaHIPClientePOPUPSearchAction = busquedaHIPClientePOPUPSearchAction;
	}

	/**
	 * Checks if is mostrar popup ocr cliente.
	 *
	 * @return true, if is mostrar popup ocr cliente
	 */
	public boolean isMostrarPopupOCRCliente() {
		return mostrarPopupOCRCliente;
	}

	/**
	 * Sets the mostrar popup ocr cliente.
	 *
	 * @param mostrarPopupOCRCliente the new mostrar popup ocr cliente
	 */
	public void setMostrarPopupOCRCliente(boolean mostrarPopupOCRCliente) {
		this.mostrarPopupOCRCliente = mostrarPopupOCRCliente;
	}
	
}


