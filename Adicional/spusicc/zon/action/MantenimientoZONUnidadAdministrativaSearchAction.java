package biz.belcorp.ssicc.web.spusicc.zon.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.zon.MantenimientoZONRegionService;
import biz.belcorp.ssicc.service.spusicc.zon.MantenimientoZONUnidadAdministrativaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.zon.form.MantenimientoZONUnidadAdministrativaForm;
import biz.belcorp.ssicc.web.spusicc.zon.form.MantenimientoZONUnidadAdministrativaSearchForm;


/**
 * The Class MantenimientoZONUnidadAdministrativaSearchAction.
 *
 * @author Belcorp
 * @version 1.0
 * 16/01/2015
 */
@ManagedBean
@SessionScoped
public class MantenimientoZONUnidadAdministrativaSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 1L;
	
	private LabelValue[] siccSubGerenciaList = {};
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private List zonUnidadAdministrativaRegionList = new ArrayList();
	private List zonUnidadAdministrativaZonaList = new ArrayList();
	private boolean listSelected = false;
	private String tipoElegido = "";
	private boolean deshabilitaCodigoZona = true;
	private String id = "";
	private boolean paramAccesoInternet = false;
	private String attachment = "";
	private boolean mostrarUploadArchivo = false;
	private boolean mostrarPanelTipoElegido = true;

	@Override
	protected String getSalirForward() {
		this.setAttachment("");
		this.mostrarUploadArchivo = false;
		return "mantenimientoZONUnidadAdministrativaList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoZONUnidadAdministrativaForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoZONUnidadAdministrativaSearchForm form = new MantenimientoZONUnidadAdministrativaSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes");
		}
		List lista= new ArrayList();
		
		try {
			
			MantenimientoZONUnidadAdministrativaSearchForm f = (MantenimientoZONUnidadAdministrativaSearchForm)formBusqueda;
			MantenimientoZONUnidadAdministrativaService service = (MantenimientoZONUnidadAdministrativaService)getBean("spusicc.mantenimientoZONUnidadAdministrativaService");

			Map criteria = BeanUtils.describe(f);
			
			if(f.getTipo().equals("0")){
				lista = service.getRegionesList(criteria);
				this.setZonUnidadAdministrativaRegionList(lista);
			}else{
				lista = service.getRegionesZonasList(criteria);
				this.setZonUnidadAdministrativaZonaList(lista);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setDeleteAttributes");
		}
		MantenimientoZONUnidadAdministrativaSearchForm f = (MantenimientoZONUnidadAdministrativaSearchForm) this.formBusqueda;
		MantenimientoZONUnidadAdministrativaService service = (MantenimientoZONUnidadAdministrativaService)getBean("spusicc.mantenimientoZONUnidadAdministrativaService");
		
		String errorDelete = "";
		
		Map criteria = (HashMap)this.beanRegistroSeleccionado;
		if(criteria != null){
			f.setCodigoZona(MapUtils.getString(criteria, "codigoZona", ""));
			f.setCodigoRegion(MapUtils.getString(criteria, "codigoRegion", ""));
		}
		
		if(StringUtils.equals(this.getTipoElegido(),Constants.NUMERO_CERO)){
			this.setId(criteria.get("oidRegion").toString());
		}else{
			this.setId(criteria.get("oidZona").toString());
		}
		
		try {			
			
			if(StringUtils.isNotBlank(this.getId())){
				Map params = new HashMap();
				params.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
				params.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
				params.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
				MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
				PedidoControlFacturacion controlFacturacion = mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(params);

				criteria.put("codigoPeriodo", controlFacturacion.getCodigoPeriodo());
				
				if(StringUtils.equals(f.getTipo(), "0"))
				{
					//Region
					criteria.put("oidRegion", this.getId());
					errorDelete = service.removeRegion(criteria);
					if (StringUtils.isBlank(errorDelete)) {
						this.addInfo("Info:", this.getResourceMessage("mantenimientoZONUnidadAdministrativaForm.removeRegion"));						
					}else {
						this.addError("Error:", this.getResourceMessage(errorDelete));
						return false;
					}
				}else{
					//zona
					criteria.put("oidZona", this.getId());
					errorDelete = service.removeZona(criteria);
					if (StringUtils.isBlank(errorDelete)) {
						this.addInfo("Info:", this.getResourceMessage("mantenimientoZONUnidadAdministrativaForm.removeZona"));
					}else {
						this.addError("Error:", this.getResourceMessage(errorDelete));
						return false;
					}
					
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setSaveAttributes");
		}
		MantenimientoZONUnidadAdministrativaForm f = (MantenimientoZONUnidadAdministrativaForm)formMantenimiento;
		
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		MantenimientoZONRegionService service = (MantenimientoZONRegionService)getBean("spusicc.mantenimientoZONRegionService");
		MantenimientoZONUnidadAdministrativaService serviceUnidadAdministrativa = (MantenimientoZONUnidadAdministrativaService) getBean("spusicc.mantenimientoZONUnidadAdministrativaService");
		
		String lineaError = "";
		f.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		Map map = BeanUtils.describe(f);
		map.put("login", usuario.getLogin());
		map.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		map.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);		
		
		MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(map);
		
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		
		map.put("codigoPeriodo", f.getCodigoPeriodo());
		
		if (!f.isNewRecord()) {
			if(f.getTipo().equals(Constants.NUMERO_CERO)){ //TIPO REGION
				map.put("oidRegion", ((HashMap)this.beanRegistroSeleccionado).get("oidRegion").toString());
				service.updateRegion(map);
				this.addInfo("info:", this.getResourceMessage("mantenimientoZONUnidadAdministrativaForm.updated"));
			}else{ //TIPO ZONA
				map.put("oidZona", ((HashMap)this.beanRegistroSeleccionado).get("oidZona").toString());
				map.put("accesoInternet", f.getAccesoInternet());
				serviceUnidadAdministrativa.updateZona(map);				
				this.addInfo("info:", this.getResourceMessage("mantenimientoZONUnidadAdministrativaForm.updated"));
			}
		}else{
			if(f.getTipo().equals(Constants.NUMERO_CERO)){ //TIPO REGION
				map.put("codigoRegion", f.getCodigoRegion());
				if(validaRegionDuplicado(f.getCodigoRegion())){
					service.insertRegion(map);
					this.addInfo("info:", this.getResourceMessage("mantenimientoZONUnidadAdministrativaForm.created"));
				}else{
					this.addError("Error:", this.getResourceMessage("mantenimientoZONUnidadAdministrativaForm.created.duplicate"));
					return false;
				}
			}else{ //TIPO ZONA
				map.put("codigoRegion", f.getCodigoRegion());
				
				//La zona esta desabilitada, se toma la siguiente
				if(StringUtils.equals(f.getManual(), Constants.NUMERO_CERO)){
					f.setCodigoZona(aSvc.getUltimoCodigoZona(f.getCodigoPais(), f.getCodigoRegion()));
					
					int codigoZonaSiguiente = Integer.parseInt(f.getCodigoZona()); 
					
					if(codigoZonaSiguiente > 0 && codigoZonaSiguiente < 10){
						f.setCodigoZona("000" + f.getCodigoZona());
					}else if(codigoZonaSiguiente > 9 && codigoZonaSiguiente < 100){
						f.setCodigoZona("00" + f.getCodigoZona());
					}else if(codigoZonaSiguiente > 99 && codigoZonaSiguiente < 1000){
						f.setCodigoZona("0" + f.getCodigoZona());
					}
				}
				
				map.put("codigoZona", f.getCodigoZona());
				map.put("accesoInternet", f.getAccesoInternet());
				
				serviceUnidadAdministrativa.insertZona(map);
				this.addInfo("Info:", this.getResourceMessage("mantenimientoZONUnidadAdministrativaForm.created"));
			}
		}
		
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("");
		}
		MantenimientoZONUnidadAdministrativaForm form = new MantenimientoZONUnidadAdministrativaForm();
		form.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		Map criteria = (HashMap)this.beanRegistroSeleccionado;
		
		if(!this.accion.equals(this.ACCION_NUEVO)){
			if(StringUtils.equals(this.getTipoElegido(), Constants.NUMERO_CERO)){
				this.setId(criteria.get("oidRegion").toString());
			}else{
				this.setId(criteria.get("oidZona").toString());
			}
			BeanUtils.copyProperties(form, criteria);
		}
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		this.mostrarBotonConsultar = false;
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		
		MantenimientoZONUnidadAdministrativaSearchForm f = (MantenimientoZONUnidadAdministrativaSearchForm)this.formBusqueda;
		f.setCodigoPais(pais.getCodigo());
		f.setTipo(Constants.NUMERO_CERO);
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
        
		MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(criteria);
		
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		
		LabelValue []subgerencias = aSvc.getSubGerenciaxPaisMarcaCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT);
		this.setSiccSubGerenciaList(subgerencias);
		
		this.setSiccRegionList(aSvc.getRegionesByPaisSubGerencia(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), 
						Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT, subgerencias[0]
								.getValue(), ""));		
		this.setTipoElegido(Constants.NUMERO_CERO);
		f.setTipo(this.getTipoElegido());		
	}
	
	@Override
	protected void setAddAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setAddAttributes");
		}
		MantenimientoZONUnidadAdministrativaForm f = (MantenimientoZONUnidadAdministrativaForm)this.formMantenimiento;
		f.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
        
		MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(criteria);
		
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		
		f.setTipo(this.getTipoElegido());
		
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		LabelValue []subgerencias = aSvc.getSubGerenciaxPaisMarcaCanal(
				this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),
				Constants.CODIGO_MARCA_DEFAULT, 
				Constants.CODIGO_CANAL_DEFAULT);
		this.setSiccSubGerenciaList(subgerencias);
		
		this.setSiccRegionList(
				aSvc.getRegionesByPaisSubGerencia(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), 
						Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT, subgerencias[0]
								.getValue(), ""));
		
		f.setEditable(false);		
		f.setCodigoSubGerencia(null);
		f.setCodigoRegion(null);
		f.setOidRegion(null);
		f.setDescripcion(null);
		f.setDescripcionZona(null);
		
		if(StringUtils.equals(this.getTipoElegido(),Constants.NUMERO_UNO)){
			this.getUltimoCodigoZona();
		}
		
	}
	
	@Override
	protected void setEditAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setEditAttributes");
		}
		MantenimientoZONUnidadAdministrativaForm f = (MantenimientoZONUnidadAdministrativaForm)this.formMantenimiento;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		MantenimientoZONRegionService service = (MantenimientoZONRegionService)getBean("spusicc.mantenimientoZONRegionService");
		MantenimientoZONUnidadAdministrativaService serviceUnidadAdministrativa = (MantenimientoZONUnidadAdministrativaService) getBean("spusicc.mantenimientoZONUnidadAdministrativaService");
		
		f.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		f.setNewRecord(false);
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
        
		MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(criteria);
		
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());

		this.setSiccSubGerenciaList(aSvc.getSubGerenciaxPaisMarcaCanal(
				this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),
				Constants.CODIGO_MARCA_DEFAULT, 
				Constants.CODIGO_CANAL_DEFAULT));
		
		Map valores = (HashMap) this.beanRegistroSeleccionado;
		f.setTipo(this.getTipoElegido());		
		String id = null;		
		if(StringUtils.equals(this.getTipoElegido(), "0")){
			id = valores.get("oidRegion").toString();
			Integer oidRegion = Integer.valueOf(id);
			Map regionMap = service.getRegion(oidRegion);
			BeanUtils.copyProperties(f, regionMap);
		}else{
			id = valores.get("oidZona").toString();
			Integer oidZona = Integer.valueOf(id);
			Map zonaMap = serviceUnidadAdministrativa.getZona(oidZona);
			BeanUtils.copyProperties(f, zonaMap);
			this.setParamAccesoInternet((StringUtils.equals(f.getAccesoInternet(),Constants.NUMERO_UNO)?true:false));
		}
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
		MantenimientoZONUnidadAdministrativaSearchForm f = (MantenimientoZONUnidadAdministrativaSearchForm)this.formBusqueda;
		log.debug(val.getNewValue().toString());
		if(StringUtils.isNotEmpty(val.getNewValue().toString()) 
				|| StringUtils.isNotBlank(val.getNewValue().toString())){
			
			String[] regionListado = new String[1];			
			regionListado[0] = (String)val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			if(regionListado.length>0 
					&& StringUtils.equals(f.getTipo(),Constants.NUMERO_UNO)){
				siccZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(
						this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), 
						"T", 
						"VD", 
						regionListado,
						"T");
			}else{
				this.setSiccZonaList(null);
			}
		}
	}
	
	public void zonaXRegion(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("zonaXRegion");
		}
		String valor = val.getNewValue().toString();
		if(StringUtils.isNotBlank(valor)
			&& StringUtils.isNotEmpty(valor)){
			MantenimientoZONUnidadAdministrativaForm f = (MantenimientoZONUnidadAdministrativaForm)this.formMantenimiento;
			AjaxService ajax = (AjaxService) getBean("ajaxService");			
			f.setCodigoZona(StringUtils.leftPad(ajax.getUltimoCodigoZona(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), valor), 4, "0"));
		}
	}

	/**
	 * @param codigoRegionRegistro
	 * @return
	 */
	public boolean validaRegionDuplicado(String codigoRegionRegistro){
		if(log.isDebugEnabled()){
			log.debug("validaRegionDuplicado");
		}
		MantenimientoZONRegionService service = (MantenimientoZONRegionService)getBean("spusicc.mantenimientoZONRegionService");
		
		Integer encuentraRegion = service.getEncuentraRegionByCodigoRegion(codigoRegionRegistro);
		
		if(encuentraRegion.intValue() > 0)
			return false;
		else
			return true;
	}
	
	public void showComponentxItemSelected(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("showComponentxItemSelected");			
		}
		String valor = val.getNewValue().toString();
		if(StringUtils.isNotBlank(valor) 
			|| StringUtils.isNotEmpty(valor)){ 
			this.setTipoElegido(valor);
		}
	}
	
	public void limpiarCodigoZona(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("limpiarCodigoZona");
		}
		MantenimientoZONUnidadAdministrativaForm f = (MantenimientoZONUnidadAdministrativaForm)this.formMantenimiento;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		boolean valor = (Boolean)(val.getNewValue());
		if(valor){
			this.setDeshabilitaCodigoZona(false);
			f.setCodigoZona("");
		}else{
			this.setDeshabilitaCodigoZona(true);
			f.setCodigoZona(ajax.getUltimoCodigoZona(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), f.getCodigoRegion()));
		}
	}
	
	protected void getUltimoCodigoZona(){
		if(log.isDebugEnabled()){
			log.debug("getUltimoCodigoZona");
		}
		MantenimientoZONUnidadAdministrativaForm f = (MantenimientoZONUnidadAdministrativaForm)this.formMantenimiento;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		
		if(StringUtils.isEmpty(f.getCodigoSubGerencia()) 
				|| StringUtils.isBlank(f.getCodigoSubGerencia())){
			this.obtenerCodigoSubGerenciaXDefecto();
		}
				
		if(StringUtils.isEmpty(f.getCodigoRegion())
				|| StringUtils.isBlank(f.getCodigoRegion())){
			this.obtenerCodigoRegionXDefecto();
		}

		if(StringUtils.equals(this.getTipoElegido(),Constants.UNO)){
			f.setCodigoZona(ajax.getUltimoCodigoZona(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), f.getCodigoRegion()));
		}		
	}
	
	protected void obtenerCodigoSubGerenciaXDefecto(){
		if(log.isDebugEnabled()){
			log.debug("obtenerCodigoSubGerenciaXDefecto");
		}
		MantenimientoZONUnidadAdministrativaForm f = (MantenimientoZONUnidadAdministrativaForm)this.formMantenimiento;		
		f.setCodigoSubGerencia(((LabelValue)this.getSiccSubGerenciaList()[0]).getValue());
	}
	
	protected void obtenerCodigoRegionXDefecto(){
		if(log.isDebugEnabled()){
			log.debug("obtenerCodigoRegionXDefecto");
		}
		MantenimientoZONUnidadAdministrativaForm f = (MantenimientoZONUnidadAdministrativaForm)this.formMantenimiento;
		f.setCodigoRegion(((LabelValue)this.getSiccRegionList()[0]).getValue());
	}
	
	public void valorAccesoInternet(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("valorAccesoInternet");
		}
		MantenimientoZONUnidadAdministrativaForm f = (MantenimientoZONUnidadAdministrativaForm)this.formMantenimiento;
		f.setAccesoInternet(((Boolean)val.getNewValue()).booleanValue()?Constants.NUMERO_UNO:Constants.NUMERO_CERO);
	}
	
	/**
	 * Handle file upload.
	 *
	 * @param event the event
	 */
	public void handleFileUpload(FileUploadEvent event) {
		if(log.isDebugEnabled()){
			log.debug("handleFileUpload");
		}
		MantenimientoZONUnidadAdministrativaSearchForm f = (MantenimientoZONUnidadAdministrativaSearchForm)this.formBusqueda;
		MantenimientoZONUnidadAdministrativaService service = (MantenimientoZONUnidadAdministrativaService)getBean("spusicc.mantenimientoZONUnidadAdministrativaService");
		String mensajes = "";
		if(validarCargaArchivo()){
			if(event != null){
				f.setArchivo(event.getFile());
				this.setAttachment(event.getFile().getFileName());			
				Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();			
				Map criteria = new HashMap();
				criteria.put("codigoPais", pais.getCodigo());
				
				Map params;
				try {
					uploadArchivo();
					params = BeanUtils.describe(f);
					mensajes = service.executeProcesarArchivoExcel(params, f.getDirectorioTemporal(), f.getNombreArchivo(), this.getmPantallaPrincipalBean().getCurrentUser());
					
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					e1.printStackTrace();
				} catch (NoSuchMethodException e1) {
					e1.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
								
				//Borramos las tablas Temporales de Cargos y Abonos Masivos
			   //	service.deleteTablasCADMasivos();				
			}
		}else{
			f.setArchivo(null);			
		}
	}
	
	private void uploadArchivo() throws IOException {
		if(log.isDebugEnabled()){
			log.debug("uploadArchivo");			
		}
		MantenimientoZONUnidadAdministrativaSearchForm f = (MantenimientoZONUnidadAdministrativaSearchForm) this.formBusqueda;
		UploadedFile archivo = f.getArchivo();
		f.setNombreArchivo(archivo.getFileName());
		log.debug("Nombre Archivo Upload: " + f.getNombreArchivo());
		// leyemos el stream de entrada
		//Cargamos el archivo de la maquina del cliente al servidor
		if (log.isDebugEnabled()) {
			log.debug("JFA Cargando Archivo");
		}
		
		f.setNombreArchivo(f.getArchivo().getFileName());

		// leyemos el stream de entrada
		InputStream is;
		is = f.getArchivo().getInputstream();
		// abrimos el stream de escritura, ubicacion al cual se grabara el
		// archivo del cliente		
		FileOutputStream os = new FileOutputStream(new File(f.getDirectorioTemporal(), 
								f.getNombreArchivo()));

		// grabamos cada 1024 bytes
		int bytesRead = 0;
		byte[] buffer = new byte[1024];
		while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();
		String extensionArchivo = f.getNombreArchivo().substring(f.getNombreArchivo().length() - 3);
		f.setExtensionArchivo(extensionArchivo);
		f.setArchivo(null);
	}
	
	public boolean validarCargaArchivo(){
		if(log.isDebugEnabled()){
			log.debug("validarCargaArchivo");
		}
		MantenimientoZONUnidadAdministrativaSearchForm f = (MantenimientoZONUnidadAdministrativaSearchForm) this.formBusqueda;
		if(StringUtils.isBlank(f.getCodigoRegion()) || StringUtils.isEmpty(f.getCodigoRegion())){
			this.addError("Error:", "Seleccione region");
			return false;
		}else{
			f.setCodigoSubGerencia(((LabelValue)this.getSiccSubGerenciaList()[0]).getValue());
		}
		return true;
	}
	
	public void showUpload(ActionEvent e){
		if(log.isDebugEnabled()){
			log.debug("showUpload");
		}
		this.mostrarUploadArchivo = true;
		this.mostrarPanelTipoElegido = false;
	}
	
	public void limpiarPanel(ActionEvent e){
		if(log.isDebugEnabled()){
			log.debug("limpiarPanel");
		}
		this.mostrarUploadArchivo = false;
		this.mostrarPanelTipoElegido = true;
	}
	
	public LabelValue[] getSiccSubGerenciaList() {
		return siccSubGerenciaList;
	}

	public void setSiccSubGerenciaList(LabelValue[] siccSubGerenciaList) {
		this.siccSubGerenciaList = siccSubGerenciaList;
	}

	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public List getZonUnidadAdministrativaRegionList() {
		return zonUnidadAdministrativaRegionList;
	}

	public void setZonUnidadAdministrativaRegionList(
			List zonUnidadAdministrativaRegionList) {
		this.zonUnidadAdministrativaRegionList = zonUnidadAdministrativaRegionList;
	}

	public List getZonUnidadAdministrativaZonaList() {
		return zonUnidadAdministrativaZonaList;
	}

	public void setZonUnidadAdministrativaZonaList(
			List zonUnidadAdministrativaZonaList) {
		this.zonUnidadAdministrativaZonaList = zonUnidadAdministrativaZonaList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public boolean isListSelected() {
		return listSelected;
	}

	public void setListSelected(boolean listSelected) {
		this.listSelected = listSelected;
	}

	public String getTipoElegido() {
		return tipoElegido;
	}

	public void setTipoElegido(String tipoElegido) {
		this.tipoElegido = tipoElegido;
	}

	public boolean isDeshabilitaCodigoZona() {
		return deshabilitaCodigoZona;
	}

	public void setDeshabilitaCodigoZona(boolean deshabilitaCodigoZona) {
		this.deshabilitaCodigoZona = deshabilitaCodigoZona;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isParamAccesoInternet() {
		return paramAccesoInternet;
	}

	public void setParamAccesoInternet(boolean paramAccesoInternet) {
		this.paramAccesoInternet = paramAccesoInternet;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public boolean isMostrarUploadArchivo() {
		return mostrarUploadArchivo;
	}

	public void setMostrarUploadArchivo(boolean mostrarUploadArchivo) {
		this.mostrarUploadArchivo = mostrarUploadArchivo;
	}

	public boolean isMostrarPanelTipoElegido() {
		return mostrarPanelTipoElegido;
	}

	public void setMostrarPanelTipoElegido(boolean mostrarPanelTipoElegido) {
		this.mostrarPanelTipoElegido = mostrarPanelTipoElegido;
	}

}
