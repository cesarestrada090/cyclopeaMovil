package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.MontoMinimo;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDClasificacionesChequeoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDMontoMinimoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDMontoMinimoForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDMontoMinimoSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class MantenimientoPEDMontoMinimoSearchAction extends BaseMantenimientoSearchAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3033003088233159817L;
	
	private List stoRegionList;
	private List pedTipoSolicitudList;
	private List pedTipoCliente;
	private LabelValue[] stoSubtipoClienteList = {};
	private LabelValue[] stoTipoClasificacionList = {};
	private LabelValue[] stoClasificacionList = {};
	private LabelValue[] stoZonaList = {};
	
	private List pedMontoMinimoList;
	
	private boolean mostrarPopUpMontoMinimo = false;
	private static final String POPUP_MONTO_MINIMO = "INGRESO";
	
	@ManagedProperty(value = "#{mantenimientoPEDMontoMinimoAction}")
	private MantenimientoPEDMontoMinimoAction mantenimientoPEDMontoMinimoAction;

	@Override
	protected String getSalirForward() 
	{
		return "mantenimientoPEDMontoMinimoSearchForm";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception 
	{
		return "mantenimientoPEDMontoMinimoForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception 
	{
		MantenimientoPEDMontoMinimoSearchForm searchForm = new MantenimientoPEDMontoMinimoSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception 
	{
		MantenimientoPEDMontoMinimoSearchForm f = (MantenimientoPEDMontoMinimoSearchForm)this.formBusqueda;
		MantenimientoPEDMontoMinimoService servicePed = 
			(MantenimientoPEDMontoMinimoService) getBean("spusicc.mantenimientoPEDMontoMinimoService");
		Map map = new HashMap();

		map.put("codigoPais", f.getCodigoPais());
		map.put("oidTipoSolicitud", f.getOidTipoSolicitud());
		map.put("oidTipoCliente", f.getOidTipoCliente());
		map.put("oidSubTipoCliente", f.getOidSubTipoCliente());
		map.put("oidTipoClasificacion", f.getOidTipoClasificacion());
		map.put("oidClasificacion", f.getOidClasificacion());
		map.put("codigoRegion", f.getCodigoRegion());
		map.put("codigoZona", f.getCodigoZona());

		List lista = servicePed.getMontominimoList(map);

		f.setCodigoZona("");

		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception 
	{
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map registroSeleccionado = (Map)this.beanRegistroSeleccionado;
		
		MantenimientoPEDMontoMinimoSearchForm f = (MantenimientoPEDMontoMinimoSearchForm)this.formBusqueda;
		MantenimientoPEDMontoMinimoService servicePed = 
			(MantenimientoPEDMontoMinimoService) getBean("spusicc.mantenimientoPEDMontoMinimoService");
		
		Map criteria = new HashMap();
		
		String[] items = new String[]{registroSeleccionado.get("oidMontoMinimo").toString()};
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("usuario", usuario.getLogin());
		
		String indicador = servicePed.getIndicadorActualizaMontominimo(criteria);
		
		servicePed.deleteMontoMinimo(criteria,items);

		if (Constants.SI.equals(indicador)){
			servicePed.updateErrorMontoMinimo(criteria);
		}

		this.getResourceMessage("mantenimientoPEDMontoMinimoSearchForm.deleted");
		
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception 
	{
		MantenimientoPEDMontoMinimoForm f = (MantenimientoPEDMontoMinimoForm)this.formMantenimiento;
		MantenimientoPEDMontoMinimoService servicePed = (MantenimientoPEDMontoMinimoService) getBean("spusicc.mantenimientoPEDMontoMinimoService");
		ReporteService reporteService = (ReporteService)getBean("scsicc.reporteService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		Map params = new HashMap();
		Map map = new HashMap();

		if(f.isNewRecord()){

			params.put("codigoPais", pais.getCodigo());
			params.put("oidTipoSolicitud", f.getOidTipoSolicitud());
			params.put("oidTipoCliente", f.getOidTipoCliente());
			params.put("oidSubTipoCliente", f.getOidSubTipoCliente());
			params.put("oidTipoClasificacion", f.getOidTipoClasificacion());
			params.put("oidClasificacion", f.getOidClasificacion());
			params.put("nivel1", f.getNivel1());
			params.put("nivel2", f.getNivel2());
			params.put("nivel3", f.getNivel3());
			
			if(Constants.STO_TIPO_GESTIONABLE_TODOS.equals(f.getRecargo())){
				params.put("recargo", Constants.NUMERO_CERO);
			}else{
			params.put("recargo", f.getRecargo());
			}
			
			
			//params.put("nominal", f.getNominal());
			//if(f.getNominal() == ""){
			if(Constants.STO_TIPO_GESTIONABLE_TODOS.equals(f.getNominal())){
				params.put("nominal", Constants.NUMERO_CERO);
			}else{
				params.put("nominal", f.getNominal());
			}
			
			params.put("usuario", usuario.getLogin());
			params.put("codigoRegion", f.getCodigoRegion());
			params.put("codigoZona", f.getCodigoZona());

			int oidMontoMinimo = servicePed.getNextOidMontoMinimo(params);
			params.put("oidMontoMinimo", oidMontoMinimo);

			servicePed.insertMontoMinimo(params);

			//insertando en la tabla de auditoria
			params.put("codigoAccion", Constants.PED_MOTO_MINIMO_ACCION_REGISTRO);
			params.put("codigoStatusRegistro", Constants.PED_MOTO_MINIMO_STATUS_DESPUES);
			
			servicePed.insertAuditoriaMontoMinimo(params);
		
		}else{

			params.put("codigoPais", pais.getCodigo());
			params.put("oidMontoMinimo", f.getOidMontoMinimo());
			params.put("oidTipoSolicitud", f.getOidTipoSolicitud());
			params.put("oidTipoCliente", f.getOidTipoCliente());
			params.put("oidSubTipoCliente", f.getOidSubTipoCliente());
			params.put("oidTipoClasificacion", f.getOidTipoClasificacion());
			params.put("oidClasificacion", f.getOidClasificacion());
			params.put("nivel1", f.getNivel1());
			params.put("nivel2", f.getNivel2());
			params.put("nivel3", f.getNivel3());
			if(Constants.STO_TIPO_GESTIONABLE_TODOS.equals(f.getRecargo())){
				params.put("recargo", Constants.NUMERO_CERO);
			}else{
			params.put("recargo", f.getRecargo());
			}
			//params.put("nominal", f.getNominal());
			if(Constants.STO_TIPO_GESTIONABLE_TODOS.equals(f.getNominal())){
				params.put("nominal", Constants.NUMERO_CERO);
			}else{
				params.put("nominal", f.getNominal());
			}
			params.put("usuario", usuario.getLogin());
			params.put("codigoRegion", f.getCodigoRegion());
			params.put("codigoZona", f.getCodigoZona());

			//los ids temporales antes de ser actualizados
			map.put("oidMontoMinimo", f.getOidMontoMinimo());
			MontoMinimo montoMinimo = servicePed.getMontoMinimoObject(map);
			
			map.put("oidTipoSolicitud", montoMinimo.getOidTipoSolicitud());
			map.put("oidTipoCliente", montoMinimo.getOidTipoCliente());
			map.put("oidSubTipoCliente", montoMinimo.getOidSubTipoCliente());
			map.put("oidTipoClasificacion", montoMinimo.getOidTipoClasificacion());
			map.put("oidClasificacion", montoMinimo.getOidClasificacion());
			map.put("nivel1", montoMinimo.getNivel1());
			map.put("nivel2", montoMinimo.getNivel2());
			map.put("nivel3", montoMinimo.getNivel3());
			map.put("recargo", montoMinimo.getRecargo());
			map.put("nominal", montoMinimo.getNominal());
			map.put("usuario", usuario.getLogin());
			map.put("codigoRegion", montoMinimo.getCodigoRegion());
			map.put("codigoZona", montoMinimo.getCodigoZona());
			map.put("codigoPais", pais.getCodigo());

			//insertando el valor antiguo en la tabla de auditoria
			map.put("codigoAccion", Constants.PED_MOTO_MINIMO_ACCION_MODIFICACION);
			map.put("codigoStatusRegistro", Constants.PED_MOTO_MINIMO_STATUS_ANTES);			
			servicePed.insertAuditoriaMontoMinimo(map);

			servicePed.updateMontoMinimo(params);

			//insertando el valor actual
			params.put("codigoAccion", Constants.PED_MOTO_MINIMO_ACCION_MODIFICACION);
			params.put("codigoStatusRegistro", Constants.PED_MOTO_MINIMO_STATUS_DESPUES);			
			servicePed.insertAuditoriaMontoMinimo(params);
		}

		String indicador = servicePed.getIndicadorActualizaMontominimo(params);

		if (Constants.SI.equals(indicador)){
			servicePed.updateErrorMontoMinimo(params);
		}

		Map map2 = new HashMap();
		List list = servicePed.getMontominimoList(map2);
		this.pedMontoMinimoList = list;
		
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception 
	{
		MantenimientoPEDMontoMinimoForm f = new MantenimientoPEDMontoMinimoForm();
		inicializa(f);
		
		Map registroSeleccionado = (Map)this.beanRegistroSeleccionado;
		
		if (this.accion.equals(this.ACCION_MODIFICAR) ) 
		{
			String oidMontoMinimo = registroSeleccionado.get("oidMontoMinimo").toString();
			cargaCombos(f, oidMontoMinimo);
			f.setNewRecord(false);
		}
		
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		this.mostrarBotonConsultar = false;
		
		MantenimientoPEDMontoMinimoSearchForm f = (MantenimientoPEDMontoMinimoSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());

		MantenimientoPEDMontoMinimoService servicePed = (MantenimientoPEDMontoMinimoService) getBean("spusicc.mantenimientoPEDMontoMinimoService");
		MantenimientoPEDClasificacionesChequeoService mantenimientoPEDClasificacionesChequeoService = (MantenimientoPEDClasificacionesChequeoService) getBean("spusicc.pedidos.mantenimientoPEDClasificacionesChequeoService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		
		f.setOidTipoSolicitud("");
		f.setOidTipoCliente("");
		f.setOidSubTipoCliente("");
		f.setOidTipoClasificacion("");
		f.setOidClasificacion("");
		f.setCodigoRegion("");
		f.setCodigoZona("");

		this.stoRegionList = reporteService.getListaGenerico("getRegionesByPais", criteria);

		this.pedTipoSolicitudList = servicePed.getTipoSolicitud();
		this.pedTipoCliente = mantenimientoPEDClasificacionesChequeoService.getTipoCliente();
	}
	
	private void inicializa(MantenimientoPEDMontoMinimoForm f)
	{
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		f.setOidTipoCliente(null);
		f.setOidSubTipoCliente(null);
		f.setOidTipoClasificacion(null);
		f.setOidClasificacion(null);
		f.setOidTipoSolicitud(null);
		f.setCodigoRegion(null);
		f.setCodigoZona(null);
		f.setNivel1(null);
		f.setNivel2(null);
		f.setNivel3(null);
		f.setRecargo(null);
		f.setNominal(null);		
	}
	

	/**
	 * @param f
	 * @param oidMontoMinimo
	 */
	private void cargaCombos(MantenimientoPEDMontoMinimoForm f,String oidMontoMinimo) {

		MantenimientoPEDMontoMinimoService servicePed = (MantenimientoPEDMontoMinimoService) getBean("spusicc.mantenimientoPEDMontoMinimoService");
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map map = new HashMap();

		if (log.isDebugEnabled()) {
			log.debug("Id seleccionado de la lista: " + oidMontoMinimo);
		}

		
		map.put("codigoPais", pais.getCodigo());
		map.put("oidMontoMinimo", oidMontoMinimo);

		MontoMinimo montoMinimo = servicePed.getMontoMinimoObject(map);

		f.setCodigoPais(montoMinimo.getCodigoPais());
		f.setOidMontoMinimo(oidMontoMinimo);
		f.setOidTipoSolicitud(montoMinimo.getOidTipoSolicitud());
		f.setOidTipoCliente(montoMinimo.getOidTipoCliente());
		f.setOidSubTipoCliente(montoMinimo.getOidSubTipoCliente());
		f.setOidTipoClasificacion(montoMinimo.getOidTipoClasificacion());
		f.setOidClasificacion(montoMinimo.getOidClasificacion());
		f.setCodigoRegion(montoMinimo.getCodigoRegion());
		f.setCodigoZona(montoMinimo.getCodigoZona());
		f.setNivel1(montoMinimo.getNivel1());
		f.setNivel2(montoMinimo.getNivel2());
		f.setNivel3(montoMinimo.getNivel3());
		f.setRecargo(montoMinimo.getRecargo());
		f.setNominal(montoMinimo.getNominal());
		
		if(f.getOidTipoCliente() != null)
			this.stoSubtipoClienteList = ajax.getSubTipoClienteByOidTipoCliente(f.getOidTipoCliente());
		
		if(f.getOidSubTipoCliente() != null)
			this.stoTipoClasificacionList = ajax.getTipoClasificacionByOidSubTipoCliente(f.getOidSubTipoCliente());
		
		if(f.getOidTipoClasificacion() != null)
			this.stoClasificacionList = ajax.getClasificacionByOidTipoClasificacion(f.getOidTipoClasificacion());
		
		if(f.getCodigoRegion() != null)
		{
			String[] codigoRegiones = new String[]{f.getCodigoRegion()};
			this.stoZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, 
					Constants.CODIGO_CANAL_DEFAULT, codigoRegiones, "");
		}
	}

	@Override
	protected String devuelveMensajeKeySaveOK() 
	{
		MantenimientoPEDMontoMinimoForm f = (MantenimientoPEDMontoMinimoForm)this.formMantenimiento;
		boolean newRecord = f.isNewRecord();
		if(newRecord)
			return "mantenimientoPEDMontoMinimoForm.cabecera.insert";
		else
			return "mantenimientoPEDMontoMinimoForm.cabecera.update";
	}
	
	@Override
	protected void setInvocarPopup(String accion) 
	{
		this.mostrarProcesoBatch = false;

		if (accion.equals(this.POPUP_MONTO_MINIMO)) {
			this.mostrarPopUpMontoMinimo = true;
		}
	}
	
	@Override
	public void aceptarPopup(ActionEvent event) 
	{
		// TODO Auto-generated method stub
		super.aceptarPopup(event);
	}
	
	@Override
	public void salirPopup(ActionEvent event) 
	{
		this.mostrarPopUpMontoMinimo = false;
	}
		
	public void loadSubTipoCliente(ValueChangeEvent event)
	{
		String valor = (String) event.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		
		if(valor != null)
			this.stoSubtipoClienteList = ajax.getSubTipoClienteByOidTipoCliente(valor);
		else
			this.stoSubtipoClienteList = null;		
	}
	
	public void loadTipoClasificacion(ValueChangeEvent event)
	{
		String valor = (String) event.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		if(valor != null)
			this.stoTipoClasificacionList = ajax.getTipoClasificacionByOidSubTipoCliente(valor);
		else
			this.stoTipoClasificacionList = null;
		
	}
	
	public void loadClasificacion(ValueChangeEvent event)
	{
		String valor = (String) event.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		if(valor != null)
			this.stoClasificacionList = ajax.getClasificacionByOidTipoClasificacion(valor);
		else
			this.stoClasificacionList = null;		
	}
	
	public void loadZonas(ValueChangeEvent event)
	{
		String valor = (String) event.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		if(valor != null)
		{
			String[] codigoRegiones = new String[]{valor};
			this.stoZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, 
					Constants.CODIGO_CANAL_DEFAULT, codigoRegiones, "");			
		}else
			this.stoZonaList = null;	
	}
	
	public List getStoRegionList() {
		return stoRegionList;
	}

	public void setStoRegionList(List stoRegionList) {
		this.stoRegionList = stoRegionList;
	}

	public List getPedTipoSolicitudList() {
		return pedTipoSolicitudList;
	}

	public void setPedTipoSolicitudList(List pedTipoSolicitudList) {
		this.pedTipoSolicitudList = pedTipoSolicitudList;
	}

	public List getPedTipoCliente() {
		return pedTipoCliente;
	}

	public void setPedTipoCliente(List pedTipoCliente) {
		this.pedTipoCliente = pedTipoCliente;
	}

	public LabelValue[] getStoSubtipoClienteList() {
		return stoSubtipoClienteList;
	}

	public void setStoSubtipoClienteList(LabelValue[] stoSubtipoClienteList) {
		this.stoSubtipoClienteList = stoSubtipoClienteList;
	}

	public LabelValue[] getStoTipoClasificacionList() {
		return stoTipoClasificacionList;
	}

	public void setStoTipoClasificacionList(LabelValue[] stoTipoClasificacionList) {
		this.stoTipoClasificacionList = stoTipoClasificacionList;
	}

	public LabelValue[] getStoClasificacionList() {
		return stoClasificacionList;
	}

	public void setStoClasificacionList(LabelValue[] stoClasificacionList) {
		this.stoClasificacionList = stoClasificacionList;
	}

	public LabelValue[] getStoZonaList() {
		return stoZonaList;
	}

	public void setStoZonaList(LabelValue[] stoZonaList) {
		this.stoZonaList = stoZonaList;
	}

	public List getPedMontoMinimoList() {
		return pedMontoMinimoList;
	}

	public void setPedMontoMinimoList(List pedMontoMinimoList) {
		this.pedMontoMinimoList = pedMontoMinimoList;
	}

	public boolean isMostrarPopUpMontoMinimo() {
		return mostrarPopUpMontoMinimo;
	}

	public void setMostrarPopUpMontoMinimo(boolean mostrarPopUpMontoMinimo) {
		this.mostrarPopUpMontoMinimo = mostrarPopUpMontoMinimo;
	}

	public static String getPopupMontoMinimo() {
		return POPUP_MONTO_MINIMO;
	}

	public MantenimientoPEDMontoMinimoAction getMantenimientoPEDMontoMinimoAction() {
		return mantenimientoPEDMontoMinimoAction;
	}

	public void setMantenimientoPEDMontoMinimoAction(
			MantenimientoPEDMontoMinimoAction mantenimientoPEDMontoMinimoAction) {
		this.mantenimientoPEDMontoMinimoAction = mantenimientoPEDMontoMinimoAction;
	}
}
