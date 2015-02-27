/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.lideres.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.lideres.MantenimientoLIDLideresService;
import biz.belcorp.ssicc.web.form.SistemaForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaProductoSearchAction;
import biz.belcorp.ssicc.web.spusicc.lideres.form.MantenimientoLIDProductosCanastaForm;
import biz.belcorp.ssicc.web.spusicc.lideres.form.MantenimientoLIDProductosCanastaSearchForm;

/**
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 *
 */
@ManagedBean
@SessionScoped
public class MantenimientoLIDProductosCanastaAction extends BaseMantenimientoSearchAbstractAction {

	private String codigoPeriodoActual;
	private String viewListado;
	private String codigoPeriodoBusq;
	private String indicadorPeriodoValido;
	private List tiposOferta;
	
 	/*Agregar estos atributos para el popup Producto*/
	private static final String POPUP_SACPRODUCTO = "SACPRODUCTO";
	@ManagedProperty(value="#{busquedaProductoSearchAction}")
	private BusquedaProductoSearchAction busquedaProductoSearchAction;
	private boolean mostrarPopupProducto;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoLIDProductosCanastaForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoLIDProductosCanastaSearchForm searchForm = new MantenimientoLIDProductosCanastaSearchForm();
		return searchForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setFindAttributes' method");
		}

		MantenimientoLIDProductosCanastaSearchForm f = (MantenimientoLIDProductosCanastaSearchForm)this.formBusqueda;
		MantenimientoLIDLideresService service = (MantenimientoLIDLideresService) getBean("spusicc.mantenimientoLIDLideresService");
				
		viewListado = Constants.NUMERO_UNO;//FLAG para mostar el listado
		
		List lista = service.getListProductosCanasta(f.getCodigoPeriodo());
		
        codigoPeriodoBusq = f.getCodigoPeriodo();
        
        if(f.getCodigoPeriodo().compareTo(codigoPeriodoActual)>=0)
        {
        	indicadorPeriodoValido = Constants.NUMERO_UNO;
        }	
        else
        	indicadorPeriodoValido = Constants.NUMERO_CERO;

		if(StringUtils.equals(indicadorPeriodoValido, Constants.NUMERO_UNO))
		{
			this.mostrarBotonSave = true;
			this.mostrarBotonNuevo = true;
			this.mostrarBotonModificar = true;
		}
		else
		{
			this.mostrarBotonSave = false;
			this.mostrarBotonNuevo = false;
			this.mostrarBotonModificar = false;
		}
		
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		
		MantenimientoLIDProductosCanastaSearchForm searchForm = (MantenimientoLIDProductosCanastaSearchForm) this.formBusqueda;
		searchForm.setCodigoPais(this.mPantallaPrincipalBean.getCountryCode());
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		MantenimientoLIDLideresService mantenimientoLIDLideresService = (MantenimientoLIDLideresService) getBean("spusicc.mantenimientoLIDLideresService");
		
		searchForm.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(this.mPantallaPrincipalBean.getCountryCode(),Constants.CODIGO_CANAL_DEFAULT));
		codigoPeriodoActual = searchForm.getCodigoPeriodo();
		
		this.tiposOferta = mantenimientoLIDLideresService.getListTipoOferta();
		
		this.mostrarBotonSave = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;		
	}

	@Override
	protected String getSalirForward() {
		return "mantenimientoLIDProductosCanastaList";
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method");
		}

		// Extraemos atributos y parmetros a usar
		MantenimientoLIDProductosCanastaForm mantenimientoLIDProductosCanastaForm = (MantenimientoLIDProductosCanastaForm) this.formMantenimiento;

		// Extreamos el usuario de la sesin
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		// Creamos la instancia del servicio y le asignamos
		// el usuario que va a realizar las operaciones
		MantenimientoLIDLideresService service = (MantenimientoLIDLideresService) getBean("spusicc.mantenimientoLIDLideresService");
		
		// Sistema sistema = (Sistema) convert(mantenimientoLIDProductosCanastaForm);

		Map productoCanasta = BeanUtils.describe(mantenimientoLIDProductosCanastaForm);		
		productoCanasta.put("indicadorActivo", mantenimientoLIDProductosCanastaForm.getIndicadorActivoBool() ? Constants.NUMERO_UNO : Constants.NUMERO_CERO);
		
		List list = new ArrayList();
		list.add(productoCanasta);
		
        Map map = new HashMap();
        map.put("codigoPais", usuario.getCodigoPais());
        map.put("listProductosCanasta", list);
        map.put("usuario", usuario.getLogin());
        
        service.insertListProductosCanasta(map);  
        String mensajeError = (String)map.get("mensajeError");
        
        if(StringUtils.isNotEmpty(mensajeError)){
        	this.addError("Error: ", mensajeError);
        	
        	return false;
        }
        else{
        	//actulizamos la lista
        	this.setFindAttributes();
        }
		
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Map canastabusqueda = (Map)this.beanRegistroSeleccionado;
		
		MantenimientoLIDProductosCanastaForm mantenimientoLIDProductosCanastaForm = new MantenimientoLIDProductosCanastaForm();
		mantenimientoLIDProductosCanastaForm.setCodigoPais(this.mPantallaPrincipalBean.getCountryCode());
		mantenimientoLIDProductosCanastaForm.setCodigoPeriodo(codigoPeriodoBusq);
		mantenimientoLIDProductosCanastaForm.setIndicadorActivo(Constants.NUMERO_UNO);
		
        if (!this.accion.equals(this.ACCION_NUEVO) ) {
        	BeanUtils.copyProperties(mantenimientoLIDProductosCanastaForm, canastabusqueda);
        	mantenimientoLIDProductosCanastaForm.setNewRecord(false);
        	mantenimientoLIDProductosCanastaForm.setIndicadorActivoBool(StringUtils.equals(mantenimientoLIDProductosCanastaForm.getIndicadorActivo(), Constants.NUMERO_UNO));
        }
        
        return mantenimientoLIDProductosCanastaForm;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		
		MantenimientoLIDProductosCanastaForm form = (MantenimientoLIDProductosCanastaForm) this.formMantenimiento;
		boolean isNew = form.isNewRecord();
		if(isNew){
			return "mantenimientoLIDProductosCanastaPopupForm.added.producto.canasta";
		}else{
			return "mantenimientoLIDProductosCanastaPopupForm.updated.producto.canasta";
		}	
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setInvocarPopup(java.lang.String)
	 */
	@Override
	protected void setInvocarPopup(String accion) {

		this.mostrarProcesoBatch = false;
		this.mostrarPopupProducto = true;
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopup' method");
		}
		this.mostrarProcesoBatch = true;
		MantenimientoLIDProductosCanastaForm form = (MantenimientoLIDProductosCanastaForm)this.formMantenimiento;
		
		if (accion.equals(POPUP_SACPRODUCTO)) {
			this.mostrarPopupProducto = false;
			
			this.busquedaProductoSearchAction.verificarRegistro(event);
			if (this.busquedaProductoSearchAction.isSeleccionoRegistro()) {
				
				Map prodMap = (Map) this.busquedaProductoSearchAction.getBeanRegistroSeleccionado(); 

				form.setCodigoSap(MapUtils.getString(prodMap, "codigoSap"));
				this.busquedaProductoSearchAction.setBeanRegistroSeleccionado(null);
			}
		}	
		
		this.formMantenimiento =  form;

	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setSalirPopup()
	 */
	@Override
	protected void setSalirPopup() {
		this.mostrarProcesoBatch = true;
		this.mostrarPopupProducto = false;
		this.busquedaProductoSearchAction.setBeanRegistroSeleccionado(null);
	}
	
	
	/**
	 * @return the codigoPeriodoActual
	 */
	public String getCodigoPeriodoActual() {
		return codigoPeriodoActual;
	}

	/**
	 * @param codigoPeriodoActual the codigoPeriodoActual to set
	 */
	public void setCodigoPeriodoActual(String codigoPeriodoActual) {
		this.codigoPeriodoActual = codigoPeriodoActual;
	}

	/**
	 * @return the viewListado
	 */
	public String getViewListado() {
		return viewListado;
	}

	/**
	 * @param viewListado the viewListado to set
	 */
	public void setViewListado(String viewListado) {
		this.viewListado = viewListado;
	}

	/**
	 * @return the codigoPeriodoBusq
	 */
	public String getCodigoPeriodoBusq() {
		return codigoPeriodoBusq;
	}

	/**
	 * @param codigoPeriodoBusq the codigoPeriodoBusq to set
	 */
	public void setCodigoPeriodoBusq(String codigoPeriodoBusq) {
		this.codigoPeriodoBusq = codigoPeriodoBusq;
	}

	/**
	 * @return the indicadorPeriodoValido
	 */
	public String getIndicadorPeriodoValido() {
		return indicadorPeriodoValido;
	}

	/**
	 * @param indicadorPeriodoValido the indicadorPeriodoValido to set
	 */
	public void setIndicadorPeriodoValido(String indicadorPeriodoValido) {
		this.indicadorPeriodoValido = indicadorPeriodoValido;
	}

	/**
	 * @return the busquedaProductoSearchAction
	 */
	public BusquedaProductoSearchAction getBusquedaProductoSearchAction() {
		return busquedaProductoSearchAction;
	}

	/**
	 * @param busquedaProductoSearchAction the busquedaProductoSearchAction to set
	 */
	public void setBusquedaProductoSearchAction(
			BusquedaProductoSearchAction busquedaProductoSearchAction) {
		this.busquedaProductoSearchAction = busquedaProductoSearchAction;
	}

	/**
	 * @return the mostrarPopupProducto
	 */
	public boolean isMostrarPopupProducto() {
		return mostrarPopupProducto;
	}

	/**
	 * @param mostrarPopupProducto the mostrarPopupProducto to set
	 */
	public void setMostrarPopupProducto(boolean mostrarPopupProducto) {
		this.mostrarPopupProducto = mostrarPopupProducto;
	}

	/**
	 * @return the tiposOferta
	 */
	public List getTiposOferta() {
		return tiposOferta;
	}

	/**
	 * @param tiposOferta the tiposOferta to set
	 */
	public void setTiposOferta(List tiposOferta) {
		this.tiposOferta = tiposOferta;
	}

}
