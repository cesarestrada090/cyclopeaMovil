package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.aop.framework.adapter.ThrowsAdviceInterceptor;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.Flete;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMMinimoNuevasService;
import biz.belcorp.ssicc.service.spusicc.men.MantenimientoMENIngresoGerenteZonalesService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDClasificacionesChequeoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDFleteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMMinimoNuevasForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMMinimoNuevasSearchForm;
import biz.belcorp.ssicc.web.spusicc.men.form.MantenimientoMENEscaleraGananciaForm;
import biz.belcorp.ssicc.web.spusicc.men.form.MantenimientoMENEscaleraGananciaSearchForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDFleteDetalleForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDFleteForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDFleteSearchForm;

/**
 * @author Giovanni Ascarza
 */
@ManagedBean
@SessionScoped
public class MantenimientoPEDFleteSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -2039671691149644047L;
	
	private List pedTipoDespachoList;
	private List stoRegionList;
	private List pedTipoCliente;
	private LabelValue[] stoSubtipoClienteList;
	private LabelValue[] stoTipoClasificacionList;
	private LabelValue[] stoClasificacionList;
	private LabelValue[] stoZonaList;
	
	@ManagedProperty(value="#{mantenimientoPEDFleteDetalleAction}")
	private MantenimientoPEDFleteDetalleAction mantenimientoPEDFleteDetalleAction;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoPEDFleteForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		return new MantenimientoPEDFleteSearchForm();
	}
	
	public void loadSubTipoCliente(ValueChangeEvent val){
		String oidTipoCliente = (String) val.getNewValue();

		if (log.isDebugEnabled()) {
			log.debug("loadSubTipoCliente");
		}
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		
		this.stoSubtipoClienteList = ajax.getSubTipoClienteByOidTipoCliente(oidTipoCliente);
		this.stoTipoClasificacionList = null;
		this.stoClasificacionList = null;
	}
	
	public void loadTipoClasificacion(ValueChangeEvent val){
		
		String oidSubTipoCliente = (String) val.getNewValue();

		if (log.isDebugEnabled()) {
			log.debug("loadTipoClasificacion");
		}
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		
		this.stoTipoClasificacionList = ajax.getTipoClasificacionByOidSubTipoCliente(oidSubTipoCliente);
		this.stoClasificacionList = null;
	}
	
     public void loadClasificacion(ValueChangeEvent val){
		
		String oidTipoClasi = (String) val.getNewValue();

		if (log.isDebugEnabled()) {
			log.debug("loadClasificacion");
		}
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		
		this.stoClasificacionList = ajax.getClasificacionByOidTipoClasificacion(oidTipoClasi);
	}
	
     public void loadZonas(ValueChangeEvent val){
    	 String codigoRegion = (String) val.getNewValue();

 		if (log.isDebugEnabled()) {
 			log.debug("loadZonas");
 		}
 		AjaxService ajax = (AjaxService) getBean("ajaxService");
 		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
 	
    	this.stoZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion( pais.getCodigo(), "T", "VD", new String[]{codigoRegion}, "");
     }
     
     
     public void loadDataInput(){
    	
    	 MantenimientoPEDFleteForm f = (MantenimientoPEDFleteForm) this.formMantenimiento;
    	 String montoFijo = f.getMontoFijo();
    	 BigDecimal bd = new BigDecimal(montoFijo);
    	 int valor =  bd.intValueExact();
    	 f.setValidMontoFijo(String.valueOf(valor));
     }
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("'setViewAttributes'");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoPEDFleteSearchForm f = (MantenimientoPEDFleteSearchForm) this.formBusqueda; 		
		
		f.setCodigoPais(pais.getCodigo());

		MantenimientoPEDFleteService servicePed = 
			(MantenimientoPEDFleteService) getBean("spusicc.pedidos.mantenimientoPEDFleteService");
		MantenimientoPEDClasificacionesChequeoService mantenimientoPEDClasificacionesChequeoService = 
			(MantenimientoPEDClasificacionesChequeoService)getBean("spusicc.pedidos.mantenimientoPEDClasificacionesChequeoService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		Map criteria = new HashMap();
		criteria.put("codigoPais",f.getCodigoPais());
		
		pedTipoDespachoList = servicePed.getTipoDespacho();
		stoRegionList = reporteService.getListaGenerico("getRegionesByPais",criteria);
		pedTipoCliente = mantenimientoPEDClasificacionesChequeoService.getTipoCliente();
	
	}
	
	@Override
	protected void setAddAttributes() throws Exception {
		log.debug("'setAddAttributes'");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();	
		MantenimientoPEDFleteForm f = (MantenimientoPEDFleteForm) this.formMantenimiento;
        MantenimientoPEDFleteForm fn = new MantenimientoPEDFleteForm();
		
		BeanUtils.copyProperties(f, fn);
		
		f.setTasa(Constants.NUMERO_CERO);
		f.setFleteMaximo(Constants.NUMERO_CERO);
		f.setFleteMinimo(Constants.NUMERO_CERO);
		f.setRecargo(Constants.NUMERO_CERO);
		f.setMontoFijo(Constants.NUMERO_CERO);

		f.setCodigoPais(pais.getCodigo());
		f.setValidMontoFijo(Constants.NUMERO_CERO);

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		log.debug("'setFindAttributes'");
	
		List list = null;
		try {
			
			MantenimientoPEDFleteSearchForm f = (MantenimientoPEDFleteSearchForm)this.formBusqueda;
			MantenimientoPEDFleteService servicePed = (MantenimientoPEDFleteService) getBean("spusicc.pedidos.mantenimientoPEDFleteService");

			Map map = BeanUtils.describe(f);
	
			list = servicePed.getFleteList(map);

		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) {
			   error = e.getLocalizedMessage();
			}
		   throw new Exception(this.getResourceMessage("errors.detail", new Object[]{ error }));	
		}
		
		return list;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {	
		log.debug("'setDeleteAttributes'");
		 Map map = (Map) this.beanRegistroSeleccionado;
		 MantenimientoPEDFleteSearchForm f = (MantenimientoPEDFleteSearchForm) this.formBusqueda;
		 MantenimientoPEDFleteService servicePed = (MantenimientoPEDFleteService) getBean("spusicc.pedidos.mantenimientoPEDFleteService");

		 Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

			try {
				
				Flete flete = servicePed.getFlete(MapUtils.getString(map, "oidFlete"));
				flete.setCodigoPais(f.getCodigoPais());
					
				servicePed.deleteFlete(flete, usuario);
		
			}catch (Exception e) {
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
		return "mantenimientoPEDFleteSearchForm";
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		log.debug("entrando a: 'setSaveAttributes'");
        Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoPEDFleteForm f = (MantenimientoPEDFleteForm)this.formMantenimiento;

		MantenimientoPEDFleteService servicePed = 
			(MantenimientoPEDFleteService) getBean("spusicc.pedidos.mantenimientoPEDFleteService");

		List detalle = mantenimientoPEDFleteDetalleAction.getListaFleteDetalle();
		
		if(CollectionUtils.isEmpty(detalle)){
			detalle = new ArrayList();
		}
		
		Flete flete = new Flete(); 
		BeanUtils.copyProperties(flete, f);
		
		flete.setOidSubTipoCliente(StringUtils.isNotBlank(f.getOidSubTipoCliente())?f.getOidSubTipoCliente():null);
		flete.setOidTipoClasificacion(StringUtils.isNotBlank(f.getOidTipoClasificacion())?f.getOidTipoClasificacion():null);
		flete.setOidClasificacion(StringUtils.isNotBlank(f.getOidClasificacion())?f.getOidClasificacion():null);
		flete.setCodigoZona(StringUtils.isNotBlank(f.getCodigoZona())?f.getCodigoZona():null);
		flete.setCodigoPais(pais.getCodigo());
		
		
		try{
			
			if(f.isNewRecord()){			
				int oidFlete = servicePed.getNextOidFlete();
				flete.setOidFlete(String.valueOf(oidFlete));
				servicePed.insertFlete(flete, detalle, usuario);
			}
			else{
				servicePed.updateFlete(flete, detalle, usuario);
			}
			
		}catch(Exception e){
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) {
				error = e.getLocalizedMessage();
			}
			throw new Exception(this.getResourceMessage("errors.detail", new Object[]{ error }));
							
		}

		return true;
	}
	
	@Override
	protected void setEditAttributes() throws Exception {
		log.debug("entrando a: 'setEditAttributes'");
		try {
			 cargarData();
		} catch (Exception e) {

			String error = e.getMessage();
			if (StringUtils.isBlank(error)) {
				error = e.getLocalizedMessage();
			}
			throw new Exception(this.getResourceMessage("errors.detail", new Object[]{ error }));
		}  
	}
	
	public void cargarData() throws Exception{
       
		MantenimientoPEDFleteForm mForm = (MantenimientoPEDFleteForm) this.formMantenimiento;

		try{
			
		MantenimientoPEDFleteService servicePed = (MantenimientoPEDFleteService) getBean("spusicc.pedidos.mantenimientoPEDFleteService");
		AjaxService ajax = (AjaxService) getBean("ajaxService");

		Flete flete = servicePed.getFlete(mForm.getOidFlete());
		
		if(!StringUtils.isBlank(flete.getOidSubTipoCliente()))
		{
			this.stoSubtipoClienteList = ajax.getSubTipoClienteByOidTipoCliente(flete.getOidTipoCliente());
		}
		if(!StringUtils.isBlank(flete.getOidTipoClasificacion()))
		{
			this.stoSubtipoClienteList = ajax.getTipoClasificacionByOidSubTipoCliente(flete.getOidSubTipoCliente());
		}		
		if(!StringUtils.isBlank(flete.getOidClasificacion()))
		{
			this.stoSubtipoClienteList = ajax.getClasificacionByOidTipoClasificacion(flete.getOidTipoClasificacion());
		}
		if(!StringUtils.isBlank(flete.getCodigoZona()))
		{
			String []regiones = new String[]{flete.getCodigoRegion()};
			this.stoZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(mForm.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, regiones, "" );
		}

		 
		 BeanUtils.copyProperties(mForm, flete);

    	 int valor =  new BigDecimal(mForm.getMontoFijo()).intValueExact();
    	 mForm.setValidMontoFijo(String.valueOf(valor));
    	 
		}catch(Exception e){
			throw e;			
		}
	}
	
	@Override
	protected void setConsultarAttributes() throws Exception{
		
		log.debug("entrando a: 'setConsultarAttributes'");
		try {
			 cargarData();
		} catch (Exception e) {

			String error = e.getMessage();
			if (StringUtils.isBlank(error)) {
				error = e.getLocalizedMessage();
			}
			throw new Exception(this.getResourceMessage("errors.detail", new Object[]{ error }));
		}  
	}
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Map mapa = (Map) this.beanRegistroSeleccionado;
		MantenimientoPEDFleteSearchForm form = (MantenimientoPEDFleteSearchForm) this.formBusqueda;
		MantenimientoPEDFleteForm mForm = new MantenimientoPEDFleteForm();
		
		BeanUtils.copyProperties(mForm, form);
		
		BeanUtils.populate(mForm, mapa);

		/*if (this.accion.equals(this.ACCION_CONSULTAR) || this.accion.equals(this.ACCION_MODIFICAR)) {
			this.setOidMensajeActual(mForm.getOidMensaje());
			this.setRangoInicialActual(mForm.getRangoInicial());
			this.setRangoFinalActual(mForm.getRangoFinal());
			this.setCodigoEscalonActual(mForm.getCodigoEscalon());
		}*/
		
		if(this.accion.equals(this.ACCION_CONSULTAR)){
			this.setMostrarBotonSave(false);
		}else{
			this.setMostrarBotonSave(true);
		}
		
		if(!this.accion.equals(this.ACCION_NUEVO)){
			mForm.setNewRecord(false);
		}
		
		return mForm;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoPEDFleteForm mantenimientoForm = (MantenimientoPEDFleteForm) this.formMantenimiento;
		boolean isNew = mantenimientoForm.isNewRecord();
		if(isNew) {
			return "mantenimientoPEDFleteForm.cabecera.insert";
		}else{
			return "mantenimientoPEDFleteForm.update";
		}	

	}
	
	@Override
	public String setValidarMantenimiento() {
		/*MantenimientoMENEscaleraGananciaForm f = (MantenimientoMENEscaleraGananciaForm) this.formMantenimiento;
		int rangoInicial = Integer.valueOf(f.getRangoInicial());
		int rangoFinal = Integer.valueOf(f.getRangoFinal());
		
		if (rangoInicial >= rangoFinal) {
				String mensaje = this.getResourceMessage("mantenimientoMENEscaleraGananciaForm.rango.mayor");
				return mensaje;
		
		}*/

		return null;
	}

	public List getPedTipoDespachoList() {
		return pedTipoDespachoList;
	}

	public void setPedTipoDespachoList(List pedTipoDespachoList) {
		this.pedTipoDespachoList = pedTipoDespachoList;
	}

	public List getStoRegionList() {
		return stoRegionList;
	}

	public void setStoRegionList(List stoRegionList) {
		this.stoRegionList = stoRegionList;
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

	public MantenimientoPEDFleteDetalleAction getMantenimientoPEDFleteDetalleAction() {
		return mantenimientoPEDFleteDetalleAction;
	}

	public void setMantenimientoPEDFleteDetalleAction(
			MantenimientoPEDFleteDetalleAction mantenimientoPEDFleteDetalleAction) {
		this.mantenimientoPEDFleteDetalleAction = mantenimientoPEDFleteDetalleAction;
	}

	

	

	
	

	
}
