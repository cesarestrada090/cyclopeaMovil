package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.PredicateUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelPedidosValue;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.LabelValueCDR;
import biz.belcorp.ssicc.dao.model.ParametrosOperacionesReclamos;
import biz.belcorp.ssicc.dao.sisicc.model.OperacionesResultado;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.sto.model.CodigoVentaPedido;
import biz.belcorp.ssicc.dao.spusicc.sto.model.DocumentoReferencia;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECDigitacionCDRService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DatosCabeceraCDRTO;
import biz.belcorp.ssicc.web.framework.util.DigitacionCDRDataModel;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.BusquedaRECCodigoVentaPedidoForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECDigitacionCDRForm;


/**
 * The Class MantenimientoRECDigitacionCDRAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 05/12/2013
 */
@ManagedBean
@SessionScoped
public class MantenimientoRECDigitacionCDRAction extends BaseMantenimientoSearchAbstractAction {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private String validacionCambia = "";
	private String cargo = "";
	private String varOidPeriCDR = "";
	private String varCodOperSICC = "";
	private String opcion = "";
	private String valorFoco = "";
	private String visualizaRechazo = "";
	
	List listaOperaciones = new ArrayList();
	List listaMotivo = new ArrayList();	
	Map<String, OperacionesResultado> operaciones = new HashMap<String, OperacionesResultado>();
	private boolean mostrarBotonBuscarDOCREF;
	private boolean mostrarBotonBuscarCUVCambia;
	private boolean mostrarBotonBuscarCUVDesea;
	private boolean mostrarPopupRECCodigoVentaPedido;
	private boolean mostrarPopupRECDocRef;
	private boolean mostrarPopupCUV;
	private boolean mostrarPopupCVM;
	private static final String POPUP_RECDOCREF = "RECDOCREF";
	private static final String POPUP_BUSCUV = "BUSCUV";
	private static final String POPUP_BUSCVM = "BUSCVM";
	private static String mensajeValidaPedido;
	private List mantenimientoRECDigitacionCDRDetallesDigitadosList = new ArrayList();
	private List recOperacionParametrosList = new ArrayList();
	private List lstCodMotRechazo = new ArrayList();
	private double montoDevolucion = 0.0;
	private double montoPedido = 0.0;
	private double porcentajeDevolucion = 0.0;
	private List arrCuvs = new ArrayList();
	private List arrDesc = new ArrayList();
	private List arrPrec = new ArrayList();
	private List arrPrecCata = new ArrayList();
	private List arrPosic = new ArrayList();
	
	
	private List arrCodigoOperacion = new ArrayList();
	private List arrIndicadorCUVCambiaObligatorio = new ArrayList();
	private List arrIndicadorCUVDeseaObligatorio = new ArrayList();
	private List arrIndicadorValidacionCUVCambia = new ArrayList();
	private List arrIndicadorValidacionCUVDesea = new ArrayList();
	private List arrPopupCambia = new ArrayList();
	private List arrPopupDesea = new ArrayList();
	private List arrIndicadorValidarCentroServicio = new ArrayList();
	
	private DatosCabeceraCDRTO datosCabeceraCDRTO = new DatosCabeceraCDRTO();
	private List<DatosCabeceraCDRTO> listaDatosCabeceraCDRTO = new ArrayList<DatosCabeceraCDRTO>();
	private DigitacionCDRDataModel digitacionCDRDataModel = new DigitacionCDRDataModel();
	private DatosCabeceraCDRTO[] seleccionados = {};
	
	private String[] valoresOferta = {};
	private String[] valoresOfertaPOSBUSC = {};
	private String[] valoresOfertaPOSOFER = {};				
	private String[] valoresOfertaCUV = {};  
	private String[] valoresOfertaUNI = {};
	private String[] valoresOfertaDESPRO = {};
	private String[] valoresOfertaPREPRO = {};
	
	
	private boolean mantenerFocoIgualEnvio;
	private boolean flagOperacionTrueque;
	private boolean onFocusOperacion;
	private boolean onFocusCUV;
	private boolean onFocusCantidad;
	private boolean verex;	
	private boolean esEnterEnCantidadProductoCambiaOnChange;
	private boolean esEnterEnCantidadProductoCambia;
	
	private boolean mostrarPanelDigitacionCdr;
	private boolean agregarFilasOferta;
	
	private String mensajeLocal;
	private String validacionDesea;
	
	private boolean visualizarPanelGrilla;
	
	/**
	 * Variables que deshabilitan componentes.
	 */
	private boolean deshabilitarEnvio;
	private boolean desabilitaMotivo;

	/** The busqueda rec documento referencia search action. */
	@ManagedProperty(value="#{busquedaRECDocumentoReferenciaSearchAction}")
	private BusquedaRECDocumentoReferenciaSearchAction busquedaRECDocumentoReferenciaSearchAction;
	
	@ManagedProperty(value="#{busquedaRECCodigoVentaPedidoAction}")
	private BusquedaRECCodigoVentaPedidoAction busquedaRECCodigoVentaPedidoAction;
	
	@ManagedProperty(value="#{busquedaRECCodigoVentaMatrizAction}")
	private BusquedaRECCodigoVentaMatrizAction busquedaRECCodigoVentaMatrizAction;
	
	public void buscaCodigoVentaPedido(ActionEvent e){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'MantenimientoRECDigitacionCDRAction - buscaCodigoVentaPedido' method");
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setSalirPopup()
	 */
	@Override
	protected void setSalirPopup() {		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSalirPopup' method");
		}	
		if(StringUtils.equals(accion,this.POPUP_RECDOCREF)){
			this.busquedaRECDocumentoReferenciaSearchAction.setBeanRegistroSeleccionado(null);
		}
		
		if(StringUtils.equals(accion, this.POPUP_BUSCUV)){
			this.busquedaRECCodigoVentaPedidoAction.setBeanRegistroSeleccionado(null);
		}
		
		if(StringUtils.equals(accion, this.POPUP_BUSCVM)){
 		  this.busquedaRECCodigoVentaPedidoAction.setBeanRegistroSeleccionado(null);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopup' method");
		}
		
		if(StringUtils.equals(accion,this.POPUP_RECDOCREF)){
			this.busquedaRECDocumentoReferenciaSearchAction.verificarRegistro(event);
			if(this.busquedaRECDocumentoReferenciaSearchAction.isSeleccionoRegistro()){
				DocumentoReferencia documentoReferencia = (DocumentoReferencia)this.busquedaRECDocumentoReferenciaSearchAction.getBeanRegistroSeleccionado();
				((MantenimientoRECDigitacionCDRForm)this.formBusqueda).setNumeroBoletaDespacho(documentoReferencia.getNumeroSolicitud());
				this.seteaFocoNumeroBoletaDespacho();
				this.busquedaRECDocumentoReferenciaSearchAction.setBeanRegistroSeleccionado(null);
			}
		}
		
		if(StringUtils.equals(accion, this.POPUP_BUSCUV)){
			this.busquedaRECCodigoVentaPedidoAction.verificarRegistro(event);
			if(this.busquedaRECCodigoVentaPedidoAction.isSeleccionoRegistro()){
				CodigoVentaPedido codigoVentaPedido = (CodigoVentaPedido)this.busquedaRECCodigoVentaPedidoAction.getBeanRegistroSeleccionado(); 
				this.getDatosCabeceraCDRTO().setCodigoVentaCambia(codigoVentaPedido.getCodigoVenta());
				this.getDatosCabeceraCDRTO().setPuFactura(codigoVentaPedido.getPrecioFactura());
				this.getDatosCabeceraCDRTO().setProductoCambia(codigoVentaPedido.getDescripcion());
				this.busquedaRECCodigoVentaPedidoAction.setBeanRegistroSeleccionado(null);
			}
		}
		
		if(StringUtils.equals(accion, this.POPUP_BUSCVM)){
			this.busquedaRECCodigoVentaPedidoAction.verificarRegistro(event);
			if(this.busquedaRECCodigoVentaPedidoAction.isSeleccionoRegistro()){
				
				
				this.busquedaRECCodigoVentaPedidoAction.setBeanRegistroSeleccionado(null);
			}
		}
	}
	
	public void seteaFocoNumeroBoletaDespacho(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'seteaFocoNumeroBoletaDespacho' method");
		}
				
		MantenimientoRECDigitacionCDRForm form = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
				
		if(StringUtils.isNotBlank(form.getNumeroBoletaDespacho())
			&& StringUtils.isNotEmpty(form.getNumeroBoletaDespacho())){
			String mensaje = ajax.getMensajeValidaPedido(form.getNumeroBoletaDespacho());
			if(StringUtils.isNotBlank(mensaje) && StringUtils.isNotEmpty(mensaje)){
				this.setMensajeAlertaDefault(mensaje);
				this.mostrarDialogoGeneral();
			}else{
				this.setDatos();
			}
			
		}else{
			//TODO
			//openSearchDocumentosReferenciaPopup
		}
	}
	
	public void setDatos(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setDatos' method");
		}
		this.setearConsultora();
		this.valorFoco = "3";
		this.ejecutarFunciones("1");
	}
	
	public void setearConsultora(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setearConsultora' method");
		}
		
		MantenimientoRECDigitacionCDRForm form = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		LabelValueCDR labelValueCDR = ajax.getConsultoraReclamoByCodigo(form.getNumeroBoletaDespacho(), form.getCodigoPais(), form.getPeriodoActivo());
		if(labelValueCDR != null){
			this.setVisualizarPanelGrilla(true);
			form.setCampana(labelValueCDR.getCampana());
			form.setCodigoConsejera(labelValueCDR.getLabel());
			form.setNombreConsejera(labelValueCDR.getValue());
			form.setZona(labelValueCDR.getZona());
			form.setDireccionDomicilio(labelValueCDR.getDireccionDomicilio());
			form.setFechaPedido(labelValueCDR.getFechaFactura());
			if (StringUtils.equals(form.getIndicadorOnline(),"S") && StringUtils.equals(form.getIndicadorValCDRLinea(),"1")){
				this.setearPeriodoCDR();
				if(StringUtils.equals(form.getIndicadorPedidoChequeado(),"1")){
					obtenerCodigoResultadoChequeo();
				}
			}
			this.setearPeriodo();
			form.setDireccionDomicilio(labelValueCDR.getDireccionDomicilio());
			form.setSaldoUnico(labelValueCDR.getSaldoUnico());
			form.setUbicacionGeografica(labelValueCDR.getUbicacionGeografica());
			List lista = labelValueCDR.getLista();
			this.arrCuvs = new ArrayList();
			this.arrDesc = new ArrayList();
			this.arrPrec = new ArrayList();
			this.arrPrecCata = new ArrayList();
			this.arrPosic = new ArrayList();
			for (int i = 0; i < lista.size(); i++) {
				this.arrCuvs.add(((LabelPedidosValue) lista.get(i)).getCodigoVta());
				this.arrDesc.add(((LabelPedidosValue) lista.get(i)).getDescripcion());
				this.arrPrec.add(((LabelPedidosValue) lista.get(i)).getPrecioCat());
				this.arrPrecCata.add(((LabelPedidosValue) lista.get(i)).getPrecioCatalogo());
				this.arrPosic.add(((LabelPedidosValue)lista.get(i)).getPosicion());
			}
			this.bloquear();	//TODO revisar el porque del bloqueo despues de realizar la consulta; cual es su comportamiento
		}else{
			//TODO Mostrar dialogo mensaje de alerta con key propertie alert('<fmt:message key="mensaje.noExisteBoleta"/>');
			form.setNumeroCDR("");
			form.setNumeroBoletaDespacho("");
			this.valorFoco = "1";
		}
	}
	
	protected void setearPeriodo(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setearPeriodo' method");
		}
		MantenimientoRECDigitacionCDRForm form = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		String valorPeriodoReclamo = ajax.getPeriodoReclamo(form.getCodigoConsejera(),form.getPeriodoActivo());
		if(StringUtils.isNotBlank(valorPeriodoReclamo)
				&& StringUtils.isNotEmpty(valorPeriodoReclamo)){
			form.setPeriodo(valorPeriodoReclamo);
		}
	}
	
	protected void setearPeriodoCDR() {
		MantenimientoRECDigitacionCDRForm form = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		if(StringUtils.isNotBlank(form.getCodigoConsejera()) &&
				StringUtils.isNotEmpty(form.getCodigoConsejera())){
			AjaxService ajax = (AjaxService)getBean("ajaxService");
			LabelValue labelValue = ajax.getPeriodoCDR(form.getCodigoConsejera(), form.isIndicadorExpress()==true?"1":"0");
			if(labelValue != null){
				if(labelValue.getValue() == "0"){
					//TODO Mostrar dialogo de alert("Error en periodo del Pedido");
				}else{
					form.setPeriodoCDR(labelValue.getValue());
					this.setVarOidPeriCDR(labelValue.getLabel());
				}
			}
		}
		
	}
	
	protected void existeReclamo(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'existeReclamo' method");
		}
		MantenimientoRECDigitacionCDRForm form = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		if(!StringUtils.equals(this.getCargo(), "1")){
			String valorExisteReclamo = ajax.getExisteReclamo(form.getCodigoPais(),form.getNumeroCDR(),form.getPeriodo(),form.getCodigoConsejera());
			if(!StringUtils.equals(valorExisteReclamo, "N")){
				form.setNumeroCDR("");
				form.setNumeroBoletaDespacho("");
				form.setPeriodo("");
				form.setCodigoConsejera("");
				form.setNombreConsejera("");
				this.valorFoco = "2";
			}else{
				form.setNumeroBoletaDespacho(valorExisteReclamo);
			}
		}
	}
	
	protected void obtenerCodigoResultadoChequeo(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'obtenerCodigoResultadoChequeo' method");
		}
		MantenimientoRECDigitacionCDRForm form = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		String valor = ajax.getCodigoResultadoChequeo(form.getNumeroBoletaDespacho());
		if(StringUtils.isNotBlank(valor) 
				&& StringUtils.isNotEmpty(valor)){
			form.setCodigoResultadoChequeo(valor);
		}else{
			form.setCodigoResultadoChequeo("");
		}
	}
	
	public void bloquear(){
		this.mostrarBotonBuscarDOCREF = false;
	}
	
	
	
	@Override
	protected void setInvocarPopup(String accion) {		
		this.mostrarProcesoBatch = false;
		if (accion.equals(this.POPUP_RECDOCREF)){
			this.mostrarPopupRECDocRef = true;
			this.mostrarPopupCUV = false;
			this.mostrarPopupCVM = false;
		}
		if(StringUtils.equals(accion, this.POPUP_BUSCUV)){
			this.mostrarPopupCUV = true;
			this.mostrarPopupRECDocRef = false;
			this.mostrarPopupCVM = false;
			BusquedaRECCodigoVentaPedidoForm busquedaRECCodigoVentaPedidoForm = (BusquedaRECCodigoVentaPedidoForm)this.getBusquedaRECCodigoVentaPedidoAction().getFormBusqueda();
			busquedaRECCodigoVentaPedidoForm.setNumeroCruce(((MantenimientoRECDigitacionCDRForm)this.formBusqueda).getNumeroBoletaDespacho());
		}
		if(StringUtils.equals(accion, this.POPUP_BUSCVM)){			
			this.mostrarPopupRECDocRef = false;
			this.mostrarPopupCUV = false;
			this.mostrarPopupCVM = true;
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
		MantenimientoRECDigitacionCDRForm form = new MantenimientoRECDigitacionCDRForm(); 
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
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
	
	/**
	 * Inits the onload.
	 */
	public void initOnload(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'init' method");
		}
		//this.initESC();		
		this.inicializa();
		this.existeFlagRechazo();
		this.inicializaIndicadorRechazo();
	}
	
	/**
	 * Inicializa.
	 */
	public void inicializa(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'inicializa' method");
		}
		this.activarHotkeyEstandar = false;
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		if(StringUtils.isNotBlank(f.getNumeroBoletaDespacho())
				&& StringUtils.isNotEmpty(f.getNumeroBoletaDespacho())){
			this.setCargo("1");
			this.setearConsultora();
		}else{
			this.setCargo("0");
		}
		
		
	}
	
	public void existeFlagRechazo(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'existeFlagRechazo' method");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		if(StringUtils.equals(f.getMuestraIndicador(), "1")){
			if(f.isIndicadorCDRRechazo()){
				this.visualizaRechazo = "1";
			}else{
				this.visualizaRechazo = "2";
			}
		}else{
			this.visualizaRechazo = "0";
		}
	}
	
	public void inicializaIndicadorRechazo(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'inicializaIndicadorRechazo' method");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		if(StringUtils.equals(f.getMuestraIndicador(), "1")){
			if(f.isIndicadorCDRRechazo()){
				//TODO
				//$('codigoMotivoRechazoDef').disabled=false;
		 		//$('observacionCDR').disabled=false;
				//$('codigoMotivoRechazoDef').selectedIndex = 1;
				//borrarTodo();
				this.existeFlagRechazo();
			}else{
				//TODO
//				$('codigoMotivoRechazoDef').disabled=true;
//				$('observacionCDR').disabled=true;
//				$('codigoMotivoRechazoDef').value = '';
//				$('observacionCDR').value = '';
//				borrarTodo();
				this.existeFlagRechazo();
			}
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAtributes' method");
		}
		this.activarHotkeyEstandar = false;
		this.mostrarCabeceraFija = true;
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		f.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		
		f.setIndicadorOnline("S");

		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  

        MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
        PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(criteria);
        f.setPeriodoActivo(controlFacturacion.getCodigoPeriodo());

        this.mantenimientoRECDigitacionCDRDetallesDigitadosList = new ArrayList();

        f.setIndicadorModifica("0");


		MantenimientoRECDigitacionCDRService service = (MantenimientoRECDigitacionCDRService)getBean("spusicc.mantenimientoRECDigitacionCDRService");
		this.recOperacionParametrosList = new ArrayList();
		this.recOperacionParametrosList = service.getParametrosOperacionesReclamos();
		
		if(!this.getRecOperacionParametrosList().isEmpty()){
			if(this.getRecOperacionParametrosList().size()>0){
				for (Object item : this.getRecOperacionParametrosList()) {
					this.getArrCodigoOperacion().add(((ParametrosOperacionesReclamos)item).getCodigoOperacion());
					this.getArrIndicadorCUVCambiaObligatorio().add(((ParametrosOperacionesReclamos)item).getIndicadorCUVCambiaObligatorio());
					this.getArrIndicadorCUVDeseaObligatorio().add(((ParametrosOperacionesReclamos)item).getIndicadorCUVDeseaObligatorio());
					this.getArrIndicadorValidacionCUVCambia().add(((ParametrosOperacionesReclamos)item).getIndicadorValidacionCUVCambia());
					this.getArrIndicadorValidacionCUVDesea().add(((ParametrosOperacionesReclamos)item).getIndicadorValidacionCUVDesea());
					this.getArrPopupCambia().add(((ParametrosOperacionesReclamos)item).getPopupCambia());
					this.getArrPopupDesea().add(((ParametrosOperacionesReclamos)item).getPopupDesea());
					this.getArrIndicadorValidarCentroServicio().add(((ParametrosOperacionesReclamos)item).getIndicadorValidarCentroServicio());
				}
			}
		}
		

		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		Map criteriaMinimo = new HashMap();
    	criteriaMinimo.put("codigoPais",f.getCodigoPais());
    	criteriaMinimo.put("codigoParametro",Constants.REC_INDICADOR_VALIDA_DEVOLUCION);
    	f.setIndicadorValidaDevolucion(procesoSTOEjecucionValidacionesService.getParametroSTO(criteriaMinimo));

    	//Se obtiene el parametro de indicador de pedido chequeado, solo para la opcion en linea
    	if (StringUtils.isNotBlank(f.getIndicadorOnline())
    			&& StringUtils.isNotEmpty(f.getIndicadorOnline())
    			&& StringUtils.equals(f.getIndicadorOnline(), "S")){
    		String sIndPedido = service.getIndicadorPedidoChequeado();
        	f.setIndicadorPedidoChequeado(sIndPedido);	
    	}
    	f.setNumeroCDR("");
    	f.setNumeroBoletaDespacho("");
    	f.setCodigoConsejera("");
    	f.setNombreConsejera("");
    	f.setPeriodo("");
    	f.setZona("");

    	/* INI JR PER-SiCC-2012-0304 */
    	//Obteniendo el parametro para mostra el Rechazo CDR
    	f.setMuestraIndicador(service.getIndicadorCDRRechazo(criteria));
    	f.setObservacionCDR(null);
    	//f.setIndicadorCDRRechazo(Constants.NUMERO_CERO);
    	this.visualizaRechazo = Constants.NUMERO_CERO;    	
    	/* FIN JR PER-SiCC-2012-0304 */
    	
    	f.setPeriodoCDR("");
    	criteria.put("codigoParametro", Constants.STO_IND_CDR_LINEA);
    	
    	String valorParametro = procesoSTOEjecucionValidacionesService.getParametroSTO(criteria);
    	f.setIndicadorValCDRLinea(valorParametro);
    	
    	
    	// PER-SiCC-2012-0642 
    	this.lstCodMotRechazo = new ArrayList();
    	this.lstCodMotRechazo = service.getCodigoMotivoRechazo();
    	f.setCodigoMotivoRechazoDef(null);
    	
    	f.setUsuario(this.getmPantallaPrincipalBean().getCodigoUsuario());
    	
    	//Obtenemos el parametro de validacion de oferta
    	criteria.put("codigoParametro", Constants.STO_IND_DEV_OFERTA);
    	String indicadorDevolucionOferta = procesoSTOEjecucionValidacionesService.getParametroSTO(criteria);
    	
    	f.setIndicadorDevolucionOferta(StringUtils.isBlank(indicadorDevolucionOferta)? Constants.NUMERO_CERO:indicadorDevolucionOferta);
    	//
    	
    	//Obtenemos los parametros Adicionales para validar TRUEQUES
    	criteria.put("codigoParametro", Constants.STO_IND_CDR_LINEA_18);
    	String indicadorLinea18 = procesoSTOEjecucionValidacionesService.getParametroSTO(criteria);    	
    	f.setIndicadorLinea18(StringUtils.isBlank(indicadorLinea18)?"":indicadorLinea18);
    	
    	criteria.put("codigoParametro", Constants.STO_DESV_TRQ);
    	String valorDesviacionPrecioUnitarioTrueque = procesoSTOEjecucionValidacionesService.getParametroSTO(criteria);
    	f.setValorDesviacionPrecioUnitarioTrueque(StringUtils.isBlank(valorDesviacionPrecioUnitarioTrueque)?"":valorDesviacionPrecioUnitarioTrueque);
    	//
    	
    	//Obtenemos el parametro de validacion PRODUCTO QUE CAMBIA IGUAL DE PORDUCTO QUE DESEA
    	criteria.put("codigoParametro", Constants.STO_IND_CAMB_IGUA);
    	String indicadorProductoCambiaIgualDesea = procesoSTOEjecucionValidacionesService.getParametroSTO(criteria);
    	f.setIndicadorProductoCambiaIgualDesea(StringUtils.isBlank(indicadorProductoCambiaIgualDesea)?"":indicadorProductoCambiaIgualDesea);
    	//
    	
    	//Obtenemos el parametro codigo de cliente que luego sera enviado en la busqueda de documentos de referencia (Nro perido)
    	String codigoCliente = f.getCodigoClienteDocumentoReferencia();
    	f.setCodigoClienteDocumentoReferencia(codigoCliente);
    	//
    	
    	//Obtenemos el parametro de operacion de TRUEQUE con valor absoluto
    	criteria.put("codigoParametro", Constants.STO_DESV_TRQ_OPER);
    	String indicadorDesviacionPrecioUnitarioTruequeValorAbsoluto = procesoSTOEjecucionValidacionesService.getParametroSTO(criteria);
    	f.setIndicadorDesviacionPrecioUnitarioTruequeValorAbsoluto(StringUtils.isBlank(indicadorDesviacionPrecioUnitarioTruequeValorAbsoluto)?"":indicadorDesviacionPrecioUnitarioTruequeValorAbsoluto);
    	//		
		this.loadCombos();
		this.mostrarBotonBuscarDOCREF = true;
		this.mostrarBotonBuscarCUVCambia = true;
		this.mostrarBotonBuscarCUVDesea = true;
		this.valorFoco = "1";
		this.incializaComponentes();
		
		this.mostrarPanelDigitacionCdr = true;
	}
	
	/**
	 * Load combos.
	 */
	private void loadCombos() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'loadCombos' method");
		}
		MantenimientoRECDigitacionCDRForm form = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		Map criteria = new HashMap();
		Map resultado = new HashMap();
		MantenimientoRECDigitacionCDRService service = (MantenimientoRECDigitacionCDRService)getBean("spusicc.mantenimientoRECDigitacionCDRService");
		this.listaOperaciones = service.getOperacionesReclamos(criteria);
		this.listaMotivo = service.getMotivosReclamos(criteria);
		
		StringBuilder selectOperacionDeuda = new StringBuilder();
		StringBuilder selectOperacionDeudaPrimero  = new StringBuilder();
		
		Collections.sort(listaOperaciones, new Comparator<OperacionesResultado>() {
			@Override
			public int compare(OperacionesResultado o1, OperacionesResultado o2) {
				int val = o1.getDescripcion().compareTo(o2.getDescripcion());
				return val;
			}
		});
		
		for (int i = 0; i < listaOperaciones.size(); i++) {					
			//Base base = new Base(); Se cambio por OperacionesREsultado donde se encuentra mas data
			OperacionesResultado base=(OperacionesResultado)listaOperaciones.get(i);
//			StringBuilder selectOperacion = new StringBuilder();
//			selectOperacion.append("valor:" + base.getCodigo()+"|"+base.getTipoOperSicc() +"|"+base.getCodOperSicc()  +"|"+base.getCambVali()+ "|" + base.getCambObli() )	//se enlaza para obtener el tipo de operacion
//							.append(" descripcion :" +base.getDescripcion());
//			log.debug(selectOperacion.toString());
			if(StringUtils.equals(base.getCodigo(), "D")){
				
			}
			resultado.put(String.valueOf(i),(OperacionesResultado)listaOperaciones.get(i));		
		}
		
		List<Entry<String, OperacionesResultado>> entries = new ArrayList<Entry<String, OperacionesResultado>>(resultado.entrySet());
		Collections.sort(entries, new Comparator<Entry<String, OperacionesResultado>>() {
		    public int compare(Entry<String, OperacionesResultado> e1, Entry<String, OperacionesResultado> e2) {
		        return ((OperacionesResultado)e1.getValue()).getDescripcion().compareTo(((OperacionesResultado)e2.getValue()).getDescripcion());
		    }
		});
		
		Map<String, OperacionesResultado> orderedMap = new LinkedHashMap<String, OperacionesResultado>();
		for (Entry<String, OperacionesResultado> entry : entries) {
		    orderedMap.put(entry.getKey(), entry.getValue());
		}
		this.operaciones = orderedMap;
	}
	
	/**
	 * Ceros izquierda.
	 *
	 * @param query the query
	 * @return the list
	 */
	public List<String> cerosIzquierda(String query){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cerosIzquierda' method");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		List<String> listado = new ArrayList<String>();
		listado.add(StringUtils.leftPad(query, 10,"0"));
		f.setNumeroBoletaDespacho(StringUtils.leftPad(query, 10,"0"));
		return listado;
	}
	
	/**
	 * Busca documento referencia.
	 *
	 * @param e the e
	 */
	public void buscaDocumentoReferencia(ActionEvent e){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'buscaDocumentoReferencia' method");
		}
	}
	
	/**
	 * Changed operacion.
	 */
	public void changedOperacion(){
		if (log.isDebugEnabled()) {
			log.debug("changedOperacion");
		}
	
		this.incializaComponentes();
		String codigo = ((OperacionesResultado)this.getOperaciones().get(this.getDatosCabeceraCDRTO().getOperacion())).getCodigo();
		log.debug(codigo);
		if(StringUtils.equals(codigo, "D")){
			this.deshabilitarEnvio = true;
			this.mostrarBotonBuscarCUVDesea = false;
		}		
		if(StringUtils.equals(codigo, "G") 
				|| StringUtils.equals(codigo, "F") 
				|| StringUtils.equals(codigo, "H") 
				|| StringUtils.equals(codigo, "F3") 
				|| StringUtils.equals(codigo, "F4") 
				|| StringUtils.equals(codigo, "G3") 
				|| StringUtils.equals(codigo, "G4") 
				|| StringUtils.equals(codigo, "XA") 
				|| StringUtils.equals(codigo, "XI")
				|| StringUtils.equals(codigo, "XM") 
				|| StringUtils.equals(codigo, "XP")){
			this.deshabilitarEnvio = true;
			this.mostrarBotonBuscarCUVDesea = false;
		}
		
		if(StringUtils.equals(codigo, "E") || StringUtils.equals(codigo, "A")){
			this.deshabilitarEnvio = true;
		}
		
		/**
		 * Obtener el index del arreglo
		 */
		ParametrosOperacionesReclamos objeto = this.obtieneIndexRecOperacionParametrosList(codigo);
		
		if(StringUtils.equals(objeto.getIndicadorCUVCambiaObligatorio(),"NO") 
				&& !StringUtils.equals(codigo, "T")){
			this.mostrarBotonBuscarCUVCambia = false;
		}
		
		if(StringUtils.equals(objeto.getIndicadorCUVDeseaObligatorio(), "NO")
				&& !StringUtils.equals(codigo, "T")){
			this.mostrarBotonBuscarCUVDesea = false;
		}
		
		this.valorFoco = "4";
	}
	
	/**
	 * Obtiene index rec operacion parametros list.
	 *
	 * @return the parametros operaciones reclamos
	 */
	public ParametrosOperacionesReclamos obtieneIndexRecOperacionParametrosList(String codigo){
		if (log.isDebugEnabled()) {
			log.debug("obtieneIndexRecOperacionParametrosList");
		}
		ParametrosOperacionesReclamos resultado = new ParametrosOperacionesReclamos();
		List local = new ArrayList();
		if(!this.getRecOperacionParametrosList().isEmpty()
				&& this.getRecOperacionParametrosList().size()>0){
			local = this.getRecOperacionParametrosList();
			resultado = (ParametrosOperacionesReclamos)CollectionUtils.find(local, new BeanPredicate("codigoOperacion", new EqualPredicate(codigo)));			
		}		
		return resultado;
	}
		
	
	/**
	 * Obtener codigo mapa operaciones.
	 *
	 * @param codigo the codigo
	 * @return the operaciones resultado
	 */
	public OperacionesResultado obtenerCodigoMapaOperaciones(String codigo){
		if (log.isDebugEnabled()) {
			log.debug("obtenerCodigoOperaciones");
		}
		OperacionesResultado operacionesResultado = new OperacionesResultado();
		if(StringUtils.isNotBlank(codigo) 
			&& StringUtils.isNotEmpty(codigo)){
			operacionesResultado = this.getOperaciones().get(codigo);
		}
		return operacionesResultado;
	}
	
	/**
	 * Incializa componentes.
	 */
	public void incializaComponentes(){
		if (log.isDebugEnabled()) {
			log.debug("incializaComponentes");
		}
		this.deshabilitarEnvio = false;
		this.mostrarBotonBuscarCUVCambia =true;
		this.mostrarBotonBuscarCUVDesea = true;
	}
	
	public void seteaFocoCUVDesea(){
		if(log.isDebugEnabled()){
			log.debug("seteaFocoCUVDesea");
		}
		this.setFocusCUVDesea();
	}
	
	public boolean setFocusCUVDesea(){
		if(log.isDebugEnabled()){
			log.debug("setFocusCUVDesea");
		}
		MantenimientoRECDigitacionCDRForm form = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		OperacionesResultado operacionesResultado = this.getOperaciones().get(this.getDatosCabeceraCDRTO().getOperacion());
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		int index = this.getListaOperaciones().indexOf(operacionesResultado);
		
		//Evalua parametro que indica si el CUV es obligatorio o no
		if(StringUtils.equals((String)this.getArrCodigoOperacion().get(index),"NO")){
			this.valorFoco = "5";
		}else{
			if(StringUtils.isBlank(this.getDatosCabeceraCDRTO().getCodigoVentaDesea())){
				return false;
			}
		}
		
		//Evalua el tipo de validacion parametrizada para el tipo de operacion seleccionado
		int index2 = this.getArrCuvs().indexOf(
				CollectionUtils.find(
						this.getArrCuvs(), 
						new PredicateUtils().equalPredicate(
								this.getDatosCabeceraCDRTO().getCodigoVentaDesea())));
		this.setValidacionDesea((String)this.getArrIndicadorValidacionCUVDesea().get(index));
		if(StringUtils.isBlank((String)this.getArrIndicadorValidacionCUVDesea().get(index))){
			this.valorFoco = "8";
		}else{
			if(StringUtils.equals((String)this.getArrIndicadorValidacionCUVDesea().get(index),"PE")){
				if(index2 == -1){
					this.setMensajeAlertaDefault(this.getResourceMessage("mensaje.codVtaNoExisteCDR"));
					this.mostrarDialogoGeneral();
					this.valorFoco = "7";
				}else{
					this.getDatosCabeceraCDRTO().setProductoDesea((String)this.getArrDesc().get(index2));
					this.valorFoco = "8";
				}
			}else{
				if(StringUtils.equals((String)this.getArrIndicadorValidacionCUVDesea().get(index),"MF")){
					LabelValue labelValue = ajax.validarCUVMatrizPreciosPremios(this.getDatosCabeceraCDRTO().getCodigoVentaDesea(), 
														"1", 
														form.getNumeroBoletaDespacho());
					this.loadProductoDeseaCallback(labelValue);
				}else{
					if(StringUtils.equals((String)this.getArrIndicadorValidacionCUVDesea().get(index),"MP")){
						LabelValue labelValue = ajax.validarCUVMatrizPreciosPremios(this.getDatosCabeceraCDRTO().getCodigoVentaDesea(), 
								"2", 
								form.getNumeroBoletaDespacho());
						this.loadProductoDeseaCallback(labelValue);
					}else{
						if(StringUtils.equals((String)this.getArrIndicadorValidacionCUVDesea().get(index),"-")){
							if(index2 != -1){
								this.getDatosCabeceraCDRTO().setProductoDesea((String)this.getArrDesc().get(index2));
								this.getDatosCabeceraCDRTO().setPrecioDesea((String)this.getArrPrec().get(index2));
							}
							this.valorFoco = "8";
						}
					}
				}
			}
		}
		return true;
	}
	
	
	public void loadProductoDeseaCallback(LabelValue labelValue){
		if(log.isDebugEnabled()){
			log.debug("loadProductoDeseaCallback");
		}
		if(labelValue != null){
			//Ponemos la descripcion
			this.getDatosCabeceraCDRTO().setProductoDesea(labelValue.getLabel());
			//Ponemos el precio
			this.getDatosCabeceraCDRTO().setPrecioDesea(labelValue.getValue());
			this.valorFoco = "8";
		}else{
			this.getDatosCabeceraCDRTO().setProductoDesea("");
			this.getDatosCabeceraCDRTO().setPrecioDesea("");
			this.valorFoco = "7";
			if(StringUtils.equals(this.getValidacionDesea(), "PE")){
				this.mostrarDialogoGeneral();
				this.setMensajeAlertaDefault(this.getResourceMessage("mensaje.codVtaNoExiste.pedido"));
			}
			if(StringUtils.equals(this.getValidacionDesea(), "MF")){
				this.mostrarDialogoGeneral();
				this.setMensajeAlertaDefault(this.getResourceMessage("mensaje.codVtaNoExiste.precios"));
			}
			if(StringUtils.equals(this.getValidacionDesea(), "MP")){
				this.mostrarDialogoGeneral();
				this.setMensajeAlertaDefault(this.getResourceMessage("mensaje.codVtaNoExiste.premios"));
			}
		}
	}
	
	public void agregarDetalle(){
		if(log.isDebugEnabled()){
			log.debug("agregarDetalle");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		OperacionesResultado operacionesResultado = this.getOperaciones().get(this.getDatosCabeceraCDRTO().getOperacion());
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		int index = this.getArrCodigoOperacion().indexOf(CollectionUtils.find(this.getArrCodigoOperacion(), new EqualPredicate(operacionesResultado.getCodigo())));
		if(StringUtils.equals(operacionesResultado.getCodigo(),"T")){//TRUEQUE
			//Verifica que almenos uno de los cuvs y cantidades se haya ingresado
			if(StringUtils.isBlank(this.getDatosCabeceraCDRTO().getCodigoVentaCambia())
				&& StringUtils.isBlank(this.getDatosCabeceraCDRTO().getCodigoVentaDesea())){
				this.valorFoco = "4";
				return;
			}
			if(StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getCodigoVentaCambia())
				&& StringUtils.isBlank(this.getDatosCabeceraCDRTO().getCantidadCambia())
				|| Integer.parseInt(this.getDatosCabeceraCDRTO().getCantidadCambia()) == 0){
				this.valorFoco = "5";
				return;
			}
			if(StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getCodigoVentaDesea())
				&& StringUtils.isBlank(this.getDatosCabeceraCDRTO().getCantidadDesea())
				|| Integer.parseInt(this.getDatosCabeceraCDRTO().getCantidadDesea()) == 0){
				this.valorFoco = "7";
				return;
			}
		}
		//Evalua parametro que indica si el CUV es obligatorio o no		
		if(StringUtils.equals((String)this.getArrIndicadorCUVDeseaObligatorio().get(index), "SI")
			&& StringUtils.isBlank(this.getDatosCabeceraCDRTO().getCantidadDesea())
			|| Integer.parseInt(this.getDatosCabeceraCDRTO().getCantidadDesea()) == 0){
			this.setMensajeAlertaDefault("Cantidad debe ser mayor a cero");
			this.mostrarDialogoGeneral();
			return;
		}else{
			if(StringUtils.isBlank(this.getDatosCabeceraCDRTO().getCantidadCambia())){
				this.getDatosCabeceraCDRTO().setCantidadDesea("0");
			}
			this.agregarValorArr();

			Integer dato = ajax.getSaldoProducto(f.getNumeroBoletaDespacho(),
									this.getDatosCabeceraCDRTO().getCodigoVentaCambia(), 
									this.getmPantallaPrincipalBean().getCountryCode());
			this.validaSaldoProductoCallBack(dato);
		}
	}
	
	
	
	/**
	 * Setea foco cuv cambia.
	 */
	public void seteaFocoCUVCambia(){
		if (log.isDebugEnabled()) {
			log.debug("seteaFocoCUVCambia");
		}
		int cont = 0;
		FacesContext context = FacesContext.getCurrentInstance();
	    Map<String,String> params = context.getExternalContext().getRequestParameterMap();
	    String valor = params.get("pflagOperacionTrueque");
	    log.debug(valor);
	    if(StringUtils.isNotBlank(valor)
				&& StringUtils.isNotEmpty(valor)){
			if(StringUtils.equals(valor, "true")){
				this.flagOperacionTrueque = true;
			}
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		if(StringUtils.isNotEmpty(this.getDatosCabeceraCDRTO().getCodigoVentaCambia())
			&& StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getCodigoVentaCambia())){
			log.debug(this.getDatosCabeceraCDRTO().getCodigoVentaCambia());
			if(StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getOperacion())
				&& StringUtils.isNotEmpty(this.getDatosCabeceraCDRTO().getOperacion())){
				log.debug(this.getDatosCabeceraCDRTO().getOperacion());
				OperacionesResultado operacionesResultado= obtenerCodigoMapaOperaciones(this.getDatosCabeceraCDRTO().getOperacion()); 
				log.debug(operacionesResultado.getCodigo());
				if(!StringUtils.equals(operacionesResultado.getCodigo(), "C")
						&& !StringUtils.equals(operacionesResultado.getCodigo(), "P")){
					
					//TODO Verificar si existen duplicados de registros					
					if(this.getListaDatosCabeceraCDRTO().size()>0){
						for (DatosCabeceraCDRTO elemento : this.getListaDatosCabeceraCDRTO()) {
							if(CollectionUtils.exists(
									this.getListaDatosCabeceraCDRTO(), new BeanPredicate(
											"codigoVentaCambia", new EqualPredicate(elemento.getCodigoVentaCambia())))
								&& 
							CollectionUtils.exists(
									this.getListaDatosCabeceraCDRTO(), new BeanPredicate(
											"operacion", new EqualPredicate(elemento.getOperacion())))){
								cont++;
							}
						}
					}	
				}
				
				if(cont != 0){
					this.setMensajeAlertaDefault(this.getResourceMessage("mantenimientoRECDigitacionCDRAction.cuvingresadoCDR"));
		    		this.mostrarDialogoGeneral();
				}else{
					this.setFocusCUVCambia();
				}
			}
		}else{
			if(StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getOperacion())
					&& StringUtils.isNotEmpty(this.getDatosCabeceraCDRTO().getOperacion())){
				OperacionesResultado operacionesResultado = this.getOperaciones().get(this.getDatosCabeceraCDRTO().getOperacion());
				int index = this.getListaOperaciones().indexOf(operacionesResultado);
				if(StringUtils.equals(((ParametrosOperacionesReclamos)this.getRecOperacionParametrosList().get(index)).getIndicadorCUVCambiaObligatorio(),"NO")){
					this.valorFoco = "6";
				}else{
					if(StringUtils.equals(this.getDatosCabeceraCDRTO().getOperacion(),"T")){
						if(this.isFlagOperacionTrueque()){
							this.setFlagOperacionTrueque(false);
						}else{
							this.valorFoco = "6";
						}
							
					}
				}
			}
		}
	}
	
	/**
	 * Sets the focus cuv cambia.
	 */
	public void setFocusCUVCambia(){
		if (log.isDebugEnabled()) {
			log.debug("setFocusCUVCambia");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		OperacionesResultado operacionesResultado = this.getOperaciones().get(this.getDatosCabeceraCDRTO().getOperacion());
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		if(this.isOnFocusOperacion()){
			this.getDatosCabeceraCDRTO().setCodigoVentaCambia("");
			this.getDatosCabeceraCDRTO().setProductoCambia("");
			this.getDatosCabeceraCDRTO().setPuFactura("");
			this.getDatosCabeceraCDRTO().setCantidadCambia("");
			this.ejecutarFunciones("1");
		}else{
			
			int index = this.getListaOperaciones().indexOf(operacionesResultado);
			if(((String)this.getArrIndicadorCUVCambiaObligatorio().get(index)).equals("NO") && operacionesResultado.getCodigo()!="T"){
				this.valorFoco = "5";
				int index2 = this.getArrCuvs().indexOf(this.getDatosCabeceraCDRTO().getCodigoVentaCambia());
				if(index2!=-1){
					if(StringUtils.isNotBlank((String)this.getArrDesc().get(index)) 
						&& StringUtils.isNotEmpty((String)this.getArrDesc().get(index))
						&& StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getCodigoVentaCambia())
						&& StringUtils.isNotEmpty(this.getDatosCabeceraCDRTO().getCodigoVentaCambia())){
						this.getDatosCabeceraCDRTO().setProductoCambia((String)this.getArrDesc().get(index));
						if(operacionesResultado.getCodigo().equals("D")){
							String valor = (String)this.getArrPrecCata().get(index);
							if(Integer.parseInt(valor) == 0){
								this.getDatosCabeceraCDRTO().setPuFactura((String)this.getArrPrecCata().get(index));
							}else{
								this.getDatosCabeceraCDRTO().setPuFactura((String)this.getArrPrec().get(index));
							}
						}else{
							this.getDatosCabeceraCDRTO().setPuFactura((String)this.getArrPrec().get(index));
						}
						this.getDatosCabeceraCDRTO().setListaIdentSolicPos((String)this.getArrPosic().get(index));
					}
				}
			}else{
				int index2 = this.getArrCuvs().indexOf(this.getDatosCabeceraCDRTO().getCodigoVentaCambia());
				if(index2 != -1){
					if(StringUtils.isNotBlank((String)this.getArrDesc().get(index2))
						&& StringUtils.isNotEmpty((String)this.getArrDesc().get(index2))
						&& StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getCodigoVentaCambia())
						&& StringUtils.isNotEmpty(this.getDatosCabeceraCDRTO().getCodigoVentaCambia())){
						this.getDatosCabeceraCDRTO().setProductoCambia((String)this.getArrDesc().get(index2));
						if(StringUtils.equals(operacionesResultado.getCodigo(), "T")){
							this.getDatosCabeceraCDRTO().setPuFactura((String)this.getArrPrecCata().get(index2));
						}else{							
							if(StringUtils.equals(operacionesResultado.getCodigo(), "D")){
								if(StringUtils.equals((String)this.getArrPrecCata().get(index2),"0")){
									this.getDatosCabeceraCDRTO().setPuFactura((String)this.getArrPrecCata().get(index2));
								}else{
									this.getDatosCabeceraCDRTO().setPuFactura((String)this.getArrPrec().get(index2));
								}
							}
						}
						this.getDatosCabeceraCDRTO().setListaIdentSolicPos(this.getDatosCabeceraCDRTO().getListaIdentSolicPos());
						this.verificaExcepcion("4");
						this.setVerex(true);
					}
				}
			}
			
			this.validacionCambia = (String)this.getArrIndicadorValidacionCUVCambia().get(index);
			if(((String)this.getArrIndicadorValidacionCUVCambia().get(index)).equals("")){
				this.valorFoco = "5";
			}else{
				if(((String)this.getArrIndicadorValidacionCUVCambia().get(index)).equals("PE")){
					int index2 = this.getArrCuvs().indexOf(this.getDatosCabeceraCDRTO().getCodigoVentaCambia());
					if(index2 == -1 ){
						this.setMensajeAlertaDefault(this.getResourceMessage("mensaje.codVtaNoExisteCDR"));
			    		this.mostrarDialogoGeneral();
			    		this.getDatosCabeceraCDRTO().setCodigoVentaCambia("");
			    		this.getDatosCabeceraCDRTO().setProductoCambia("");
			    		this.getDatosCabeceraCDRTO().setPuFactura("");
			    		this.getDatosCabeceraCDRTO().setListaIdentSolicPos("");
					}else{
						this.valorFoco = "5";
						this.getDatosCabeceraCDRTO().setProductoCambia((String) this.getArrDesc().get(index2));
						if(StringUtils.equals(operacionesResultado.getCodigo(), "T")){
							this.getDatosCabeceraCDRTO().setPuFactura((String) this.getArrPrecCata().get(index2));
						}else{
							if(StringUtils.equals(operacionesResultado.getCodigo(), "D")){
								if(Integer.parseInt((String)this.getArrPrecCata().get(index2)) == 0){
									this.getDatosCabeceraCDRTO().setPuFactura((String) this.getArrPrecCata().get(index2));
								}else{
									this.getDatosCabeceraCDRTO().setPuFactura((String)this.getArrPrec().get(index2));
								}
								
							}else{
								this.getDatosCabeceraCDRTO().setPuFactura((String)this.getArrPrec().get(index2));
							}
						}
					}
					this.getDatosCabeceraCDRTO().setListaIdentSolicPos((String)this.getArrPosic().get(index2));
					if(!this.isVerex()){
						this.verificaExcepcion("4");
					}
				}else{
					if(((String)this.getArrIndicadorValidacionCUVCambia().get(index)).equals("MF")){
						LabelValue labelValue = ajax.validarCUVMatrizPreciosPremios(this.getDatosCabeceraCDRTO().getCodigoVentaCambia(), 
																					"1", 
																					f.getNumeroBoletaDespacho());
						this.loadProductoCallback(labelValue);
					}else{
						if(((String)this.getArrIndicadorValidacionCUVCambia().get(index)).equals("MP")){
							LabelValue labelValue = ajax.validarCUVMatrizPreciosPremios(this.getDatosCabeceraCDRTO().getCodigoVentaCambia(), 
																						"2", 
																						f.getNumeroBoletaDespacho());
							this.loadProductoCallback(labelValue);
						}else{
							if(((String)this.getArrIndicadorValidacionCUVCambia().get(index)).equals("-")){
								this.valorFoco = "5";
								int index2 = this.getArrCuvs().indexOf(this.getDatosCabeceraCDRTO().getCodigoVentaCambia());
								if(index2 != -1){
									if(StringUtils.isNotBlank((String)this.getArrDesc().get(index2))
										&& StringUtils.isNotEmpty((String)this.getArrDesc().get(index2))
										&& StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getCodigoVentaCambia())
										&& StringUtils.isNotEmpty(this.getDatosCabeceraCDRTO().getCodigoVentaCambia())){
										this.getDatosCabeceraCDRTO().setProductoCambia((String)this.getArrDesc().get(index2));
										if(StringUtils.equals(operacionesResultado.getCodigo(), "T")){
											this.getDatosCabeceraCDRTO().setPuFactura((String)this.getArrPrecCata().get(index2));
										}else{
											if(operacionesResultado.getCodigo().equals("D")){
												if(Integer.parseInt((String)this.getArrPrecCata().get(index2)) == 0){
													this.getDatosCabeceraCDRTO().setPuFactura((String)this.getArrPrecCata().get(index2));
												}else{
													this.getDatosCabeceraCDRTO().setPuFactura((String)this.getArrPrec().get(index2));
												}
											}else{
												this.getDatosCabeceraCDRTO().setPuFactura((String)this.getArrPrec().get(index2));
											}
											this.getDatosCabeceraCDRTO().setListaIdentSolicPos((String)this.getArrPosic().get(index2));
											if(this.isVerex()){
												this.verificaExcepcion("4");
											}
										}
									}
								}
							}
						}
					}
				}
			}
			
			if(((String)this.getArrIndicadorValidarCentroServicio().get(index)).equals("SI")){
				this.validaCentroServicio(this.getDatosCabeceraCDRTO().getCodigoVentaCambia());
			}
		}
	}
	
	public void loadProductoCallback(LabelValue labelValue){
		if (log.isDebugEnabled()) {
			log.debug("loadProductoCallback");
		}
		if(labelValue != null){
			this.valorFoco = "5";
			this.getDatosCabeceraCDRTO().setProductoCambia(labelValue.getLabel());
			this.getDatosCabeceraCDRTO().setPuFactura(labelValue.getValue());
		}else{
			this.getDatosCabeceraCDRTO().setProductoCambia("");
			this.getDatosCabeceraCDRTO().setPuFactura("");
			this.getDatosCabeceraCDRTO().setCodigoVentaCambia("");
			this.valorFoco = "4";
			this.setOnFocusOperacion(false);
			this.setOnFocusCUV(true);
			this.setOnFocusCantidad(false);
			if(this.getValidacionCambia().equals("PE")){
				this.setMensajeAlertaDefault(this.getResourceMessage("mensaje.codVtaNoExiste.pedido"));
	    		this.mostrarDialogoGeneral();
			}
			if(this.getValidacionCambia().equals("MF")){
				this.setMensajeAlertaDefault(this.getResourceMessage("mensaje.codVtaNoExiste.precios"));
	    		this.mostrarDialogoGeneral();
			}
			if(this.getValidacionCambia().equals("MP")){
				this.setMensajeAlertaDefault(this.getResourceMessage("mensaje.codVtaNoExiste.premios"));
	    		this.mostrarDialogoGeneral();
			}
		}
	}
	
	public void verificaExcepcion(String variable){
		if (log.isDebugEnabled()) {
			log.debug("verificaExcepcion");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		
		OperacionesResultado operacionesResultado = this.getOperaciones().get(this.getDatosCabeceraCDRTO().getOperacion());
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		if(StringUtils.equals(f.getIndicadorOnline(), "S")
			&& StringUtils.equals(f.getIndicadorValCDRLinea(), "1")
			&& (this.getVisualizaRechazo().equals("0"))
			|| (this.getVisualizaRechazo().equals("2"))){
			StringBuilder datoCompuesto = new StringBuilder();
			datoCompuesto.append(operacionesResultado.getCodigo())
						.append("|")
						.append(operacionesResultado.getTipoOperSicc())
						.append("|")
						.append(operacionesResultado.getCodOperSicc());
			log.debug(datoCompuesto.toString());
			LabelValue labelValue = ajax.getCodigoOperacionCorrecto(datoCompuesto.toString(), 
					this.getDatosCabeceraCDRTO().getMotivo(), 
					f.isIndicadorExpress()?"1":"0", 
					this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(), 
					f.getNumeroBoletaDespacho(), 
					this.getVarOidPeriCDR(), 
					this.getmPantallaPrincipalBean().getCodigoUsuario());
			if(labelValue != null){
				if(StringUtils.equals(variable,"4")){
					this.valorFoco = "4";
					this.setOnFocusCUV(true);
				}else{
					if(StringUtils.equals(variable,"5")){
						this.valorFoco = "5";
						this.setOnFocusOperacion(true);
					}
				}
				
			}
			
			
		}
	}
	
	
	public void ejecutarFunciones(String variable){
		if (log.isDebugEnabled()) {
			log.debug("ejecutarFunciones");
		}
		this.setOpcion(variable);
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		OperacionesResultado operacionesResultado = new OperacionesResultado();
		if(StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getOperacion())
			&& StringUtils.isNotEmpty(this.getDatosCabeceraCDRTO().getOperacion())){
			operacionesResultado = this.getOperaciones().get(this.getDatosCabeceraCDRTO().getOperacion());
			AjaxService ajax = (AjaxService)getBean("ajaxService");
			if(StringUtils.equals(f.getIndicadorOnline(),"S")
					&& StringUtils.equals(f.getIndicadorValCDRLinea(), "1")
					&& StringUtils.isNotBlank(this.getVarOidPeriCDR())
					&& StringUtils.isNotEmpty(this.getVarOidPeriCDR())
					&& (StringUtils.equals(this.getVisualizaRechazo(),"0")
							|| StringUtils.equals(this.getVisualizaRechazo(), "2"))){
				if(StringUtils.equals(this.getValorFoco(), "3")
						&& !StringUtils.equals(this.getOpcion(), "1")){
					this.valorFoco = "3";
				}else{
					if(StringUtils.equals(this.getValorFoco(), "6")
							&& !StringUtils.equals(this.getOpcion(),"3")){
						this.valorFoco = "6";
					}else{
						if(operacionesResultado != null){
							StringBuilder datoCompuesto = new StringBuilder();
							datoCompuesto.append(operacionesResultado.getCodigo())
										.append("|")
										.append(operacionesResultado.getTipoOperSicc())
										.append("|")
										.append(operacionesResultado.getCodOperSicc());
							log.debug(datoCompuesto.toString());
							LabelValue labelValue = ajax.getCodigoOperacionCorrecto(datoCompuesto.toString() ,
															this.getDatosCabeceraCDRTO().getMotivo(),
															f.isIndicadorExpress()==true?"1":"0", 
															this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(),
															f.getNumeroBoletaDespacho(),
															this.getVarOidPeriCDR(),
															this.mPantallaPrincipalBean.getCodigoUsuario());
							if(labelValue != null){
								if(labelValue.getLabel() != null){
									f.setCodigoOperacionCorrecto(labelValue.getLabel());
									this.setVarCodOperSICC(labelValue.getLabel());
								}
								if(labelValue.getValue() != null){
									if(StringUtils.equals(this.getOpcion(),"1")){
										this.valorFoco = "3";
										this.setOnFocusOperacion(true);
									}
								}
							}else{
								if(StringUtils.equals(this.getOpcion(),"1")){
									this.setOnFocusOperacion(false);
								}
							}
						}
					}
				}
			}
			//TODO
			//bloquearComponentes(form.listaOperacion[form.listaOperacion.length-1]);
		}
		
	}
	
	/**
	 * Valida centro servicio.
	 *
	 * @param codigoVenta the codigo venta
	 */
	public void validaCentroServicio(String codigoVenta){
		if (log.isDebugEnabled()) {
			log.debug("validaCentroServicio");
		}
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		LabelValue labelValue = ajax.getIndicadorCentroServicio(codigoVenta);
		
		
		if(labelValue != null){
	    	if(StringUtils.equals(labelValue.getValue(),"1")){
	    		this.setMensajeAlertaDefault(this.getResourceMessage("mensaje.garantiaCentroServicio"));
	    		this.mostrarDialogoGeneral();
		    	this.getDatosCabeceraCDRTO().setCodigoVentaCambia("");
		    	this.getDatosCabeceraCDRTO().setProductoCambia("");
		    	this.getDatosCabeceraCDRTO().setPuFactura("");
		    	this.getDatosCabeceraCDRTO().setListaIdentSolicPos("");
		 		this.valorFoco = "4";
	    	}else{
//	    		int index = this.getListaCodigoVenta().indexOf((String)this.getDatosCabeceraCDRTO().getCodigoVentaCambia());
//	    		if(index == -1){
//					this.setMensajeAlertaDefault(this.getResourceMessage("mensaje.codVtaNoExisteCDR"));
//		    		this.mostrarDialogoGeneral();
//		    		this.getDatosCabeceraCDRTO().setCodigoVentaCambia("");
//		    		this.valorFoco = "4";
//	    		}else{
//	    			this.getDatosCabeceraCDRTO().setProductoCambia((String)this.getListaDescripcion().get(index));
//	    			this.valorFoco = "5";
//	    			if(StringUtils.equals(((OperacionesResultado)this.operaciones.get(this.getDatosCabeceraCDRTO().getOperacion())).getCodigo(),"D")){
//	    				if(((Integer)this.getListaPrecioCat().get(index)).intValue() == 0){
//	    					this.getDatosCabeceraCDRTO().setPuFactura((String)this.getListaPrecioCatalogo().get(index));
//	    				}else{
//	    					this.getDatosCabeceraCDRTO().setPuFactura((String)this.getListaPrecioCat().get(index));
//	    				}
//	    			}else{
//	    				this.getDatosCabeceraCDRTO().setPuFactura((String)this.getListaPrecioCat().get(index));
//	    			}
//	    		}
//	    		this.getDatosCabeceraCDRTO().setListaIdentSolicPos((String)this.getListaPosicion().get(index));
	    	}			
		}
	}
	
	
	
	/**
	 * Setea foco unidades cambia.
	 */
	public void seteaFocoUnidadesCambia(){
		if (log.isDebugEnabled()) {
			log.debug("seteaFocoUnidadesCambia");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		this.setEsEnterEnCantidadProductoCambiaOnChange(true);
		int i = this.getValUnidadReclamo("5");
		//Si tiene errores
	 	if(this.isOnFocusCantidad()){
      		return;
      	}else{
      		if ( i == 0){
      			this.setDesabilitaMotivo(false);
	      		OperacionesResultado operacionesResultado = this.getOperaciones().get(this.getDatosCabeceraCDRTO().getOperacion());
	      		if(operacionesResultado.getCambObli().equals("SI")
	      			&& (!StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getCantidadCambia())		
	      				|| Integer.parseInt(this.getDatosCabeceraCDRTO().getCantidadCambia()) == 0)
	      			){
	      			this.setMensajeAlertaDefault(this.getResourceMessage("mantenimientoRECDigitacionCDRAction.mensaje.cantidadMayoracero"));
		    		this.mostrarDialogoGeneral();
	      		}
	      		if(operacionesResultado.getCambObli().equals("NO")
	      			&& !(StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getCantidadCambia())
	      				|| Integer.parseInt(this.getDatosCabeceraCDRTO().getCantidadCambia()) == 0)
	      			&& operacionesResultado.getCodigo().equals("T")
	      			&& StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getCodigoVentaCambia())
	      			){
	      			this.setMensajeAlertaDefault(this.getResourceMessage("mantenimientoRECDigitacionCDRAction.mensaje.cantidadMayoracero"));
		    		this.mostrarDialogoGeneral();
	      		}
	      			
	  			this.setEsEnterEnCantidadProductoCambiaOnChange(true);
	  			
	  			if(!StringUtils.equals(operacionesResultado.getCodigo(), "G") 
	  				&& !StringUtils.equals(operacionesResultado.getCodigo(), "F")
	  				&& !StringUtils.equals(operacionesResultado.getCodigo(), "H") 
	  				&& !StringUtils.equals(operacionesResultado.getCodigo(), "F3")
	  				&& !StringUtils.equals(operacionesResultado.getCodigo(), "F4")
	  				&& !StringUtils.equals(operacionesResultado.getCodigo(), "G3")
	  				&& !StringUtils.equals(operacionesResultado.getCodigo(), "G4")
	  				&& !StringUtils.equals(operacionesResultado.getCodigo(), "XA")
	  				&& !StringUtils.equals(operacionesResultado.getCodigo(), "XI")
	  				&& !StringUtils.equals(operacionesResultado.getCodigo(), "XM")
	  				&& !StringUtils.equals(operacionesResultado.getCodigo(), "XP")
	  				){
	  				if(StringUtils.equals(operacionesResultado.getCodigo(), "D")
	  					&& StringUtils.equals(f.getIndicadorDevolucionOferta(), "1")){
	  					this.validarOferta();
	  				}else{
	  					this.valorFoco = "6";
	  				}
	  			}else{
	  				this.valorFoco = "6";
	  			}
      		}else{
      			this.setDesabilitaMotivo(true);
      		}
      	}	 	
    }
	
	
	public void validarOferta(){
		if (log.isDebugEnabled()) {
			log.debug("validarOferta");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		OperacionesResultado operacionesResultado = this.getOperaciones().get(this.getDatosCabeceraCDRTO().getOperacion());
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		if(StringUtils.equals(operacionesResultado.getCodigo(), "D")
			&& StringUtils.equals(f.getIndicadorOnline(), "S")
			&& StringUtils.equals(f.getIndicadorValCDRLinea(), "1")){
			String valor =  ajax.ofertaDevolucion(this.getDatosCabeceraCDRTO().getListaIdentSolicPos(),
												this.getDatosCabeceraCDRTO().getCantidadCambia());
			if(StringUtils.isNotBlank(valor) && StringUtils.isNotEmpty(valor)){
				String arrRespuesta[] = valor.split("|");
				StringBuilder muestraOferta = new StringBuilder(arrRespuesta[0]);
				StringBuilder mensajeError = new StringBuilder(arrRespuesta[1]);
				if(StringUtils.isNotBlank(mensajeError.toString())){
					if(Integer.parseInt(muestraOferta.toString()) > 0){
						//error de cantidad
						this.setOnFocusCantidad(true);
						this.openSearchOfertaPopup();
					}
				}else{
					if(Integer.parseInt(muestraOferta.toString()) == 0){
						//poner foco en listamotivo
						this.valorFoco = "6";
					}else{
						//Validar cada registro de la tabla GTT
						double montoDevolucionActual = this.calcularMontoDevolucionActual();
						int cantidades = this.calcularCantidadesPorCUV();
						String valProdOfe = ajax.validarProductosOferta(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),//UR
													f.getCodigoConsejera(),			//UR
													String.valueOf(cantidades),		//UR
													String.valueOf(this.getPorcentajeDevolucion()),	//PD
													String.valueOf(montoDevolucionActual),	//PD
													String.valueOf(this.getMontoDevolucion()),	//PD
													String.valueOf(this.getMontoPedido()),		//PD
													this.getVarOidPeriCDR(),	//PROD_GANA
													this.getVarCodOperSICC(),	//PROD_GANA
													operacionesResultado.getTipoOperSicc(), //PROD_GANA
													operacionesResultado.getCambVali(),	//PROD_GANA
													this.getDatosCabeceraCDRTO().getMotivo() //PROD_GANA
													);
						if(StringUtils.equals(valProdOfe,"1")){	//ERROR
							this.setMensajeAlertaDefault(this.getResourceMessage("mantenimientoRECDigitacionCDRAction.mensaje.ofertanoingresada"));
							this.mostrarDialogoGeneral();
							this.setOnFocusCantidad(true);
						}else{
							//Salvamos la data en variables globales
							valoresOferta = valProdOfe.split("|");
							if(valoresOferta.length > 0){
								valoresOfertaPOSBUSC = valoresOferta[0].split(";");
								valoresOfertaPOSOFER = valoresOferta[1].split(";");				
								valoresOfertaCUV = valoresOferta[2].split(";");  
								valoresOfertaUNI = valoresOferta[3].split(";");
								valoresOfertaDESPRO = valoresOferta[4].split(";");
								valoresOfertaPREPRO = valoresOferta[5].split(";");
							}
							
							this.valorFoco = "6";
							this.setAgregarFilasOferta(true);
						}
					}
				}
			}
			
		}else{
			this.valorFoco = "6";
		}
	}
	
	public double calcularMontoDevolucionActual(){
		if (log.isDebugEnabled()) {
			log.debug("calcularMontoDevolucionActual");
		}
		OperacionesResultado operacionesResultado = this.getOperaciones().get(this.getDatosCabeceraCDRTO().getOperacion());
		double monto = 0.0;
		if(operacionesResultado.getCodigo().equals("D")){
			monto = monto + Integer.parseInt(this.getDatosCabeceraCDRTO().getCantidadCambia()) 
					* Double.parseDouble(this.getDatosCabeceraCDRTO().getPuFactura());
		}
		return monto;
	}
	
	public int calcularCantidadesPorCUV(){
		if (log.isDebugEnabled()) {
			log.debug("calcularCantidadesPorCUV");
		}
		int cantidad = 0;
		if(this.getListaDatosCabeceraCDRTO().size()>0){
			for (DatosCabeceraCDRTO elemento : this.getListaDatosCabeceraCDRTO()) {
				if(StringUtils.isNotBlank(elemento.getCodigoVentaCambia())
					&& StringUtils.isNotEmpty(elemento.getCodigoVentaCambia())){
					cantidad = cantidad + Integer.parseInt(elemento.getCantidadCambia());
				}
			} 
		}
		return cantidad;
	}
	
	public void esEnterMotivo(){
		if(log.isDebugEnabled()){
			log.debug("esEnterMotivo");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		boolean valor = false;
		OperacionesResultado operacionesResultado = this.getOperaciones().get(this.getDatosCabeceraCDRTO().getOperacion());
		if(!StringUtils.equals(operacionesResultado.getCodigo(),"E")
			&& !StringUtils.equals(operacionesResultado.getCodigo(), "A")){
			if(!StringUtils.equals(operacionesResultado.getCodigo(), "D")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "F")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "G")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "H")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "F3")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "F4")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "G3")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "G4")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "XA")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "XI")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "XM")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "XP")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "J")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "K")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "H3")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "N")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "G5")){
				if(StringUtils.equals(operacionesResultado.getCodigo(), "C")
					|| StringUtils.equals(operacionesResultado.getCodigo(), "P")){
					if(StringUtils.isNotBlank(f.getIndicadorProductoCambiaIgualDesea()) 
						&& StringUtils.equals(f.getIndicadorProductoCambiaIgualDesea(),"1")){
						this.agregarValorArr();
						if(StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getCodigoVentaDesea())){
							Integer data = ajax.getSaldoProducto(f.getNumeroBoletaDespacho(),
													this.getDatosCabeceraCDRTO().getCodigoVentaDesea(),
													f.getCodigoPais());
							valor = this.validaSaldoProductoCallBack(data);
							if(valor){
								
							}else{
								return;
							}
							this.setDesabilitaMotivo(true);
						}
					}else{
						this.setMantenerFocoIgualEnvio(true);
					}
				}else{
					this.valorFoco = "7";
				}
			}else{
				
			}
		}else{
			this.valorFoco = "7";
		}
	}
	
	public void agregarValorArr(){
		if(log.isDebugEnabled()){
			log.debug("agregarValorArr");
		}
		this.getListaDatosCabeceraCDRTO().add(new DatosCabeceraCDRTO());
		this.getListaDatosCabeceraCDRTO().add(this.getDatosCabeceraCDRTO());
		this.getDigitacionCDRDataModel().setWrappedData(this.getListaDatosCabeceraCDRTO());
	}
	
	public void openSearchOfertaPopup(){
		if (log.isDebugEnabled()) {
			log.debug("openSearchOfertaPopup");
		}
	}
	
	public int getValUnidadReclamo(String variable){
		if (log.isDebugEnabled()) {
			log.debug("getValUnidadReclamo");
		}
		int i=0;
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		OperacionesResultado operacionesResultado = this.getOperaciones().get(this.getDatosCabeceraCDRTO().getOperacion());
		String codOperacion = operacionesResultado.getCodigo();
		String camObi = operacionesResultado.getCambObli();
		int sumCantidad = 0;
		if(this.getListaDatosCabeceraCDRTO().size()>0){
			DatosCabeceraCDRTO elemento = (DatosCabeceraCDRTO)CollectionUtils.find(this.getListaDatosCabeceraCDRTO(), 
					new BeanPredicate("codigoVentaCambia", 
							new EqualPredicate(this.getDatosCabeceraCDRTO().getCodigoVentaCambia())));
			if(elemento != null){
				if(StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getCantidadCambia())){
					if(elemento.getCantidadCambia().equals(this.getDatosCabeceraCDRTO().getCantidadCambia())){
						sumCantidad += Integer.parseInt(elemento.getCantidadCambia());
					}
				}
			}	
		}
		
		if(sumCantidad == 0){
			sumCantidad = Integer.parseInt(this.getDatosCabeceraCDRTO().getCantidadCambia());
		}else{
			sumCantidad += Integer.parseInt(this.getDatosCabeceraCDRTO().getCantidadCambia());
		}
		
		if(StringUtils.equals(f.getIndicadorOnline(), "S")
			&& StringUtils.equals(f.getIndicadorValCDRLinea(), "1")
			&& (StringUtils.equals(this.getVisualizaRechazo(),"0")
				|| StringUtils.equals(this.getVisualizaRechazo(),"2"))){
			if(StringUtils.equals(operacionesResultado.getCambVali(), "PE")){
				if(StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getCantidadCambia())
					&& StringUtils.isNotEmpty(this.getDatosCabeceraCDRTO().getCantidadCambia())
					&& StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getListaIdentSolicPos())
					&& StringUtils.isNotEmpty(this.getDatosCabeceraCDRTO().getListaIdentSolicPos())){
					String valor = ajax.getValUnidadReclamo(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), 
															f.getCodigoConsejera(), 
															this.getDatosCabeceraCDRTO().getListaIdentSolicPos(), 
															String.valueOf(sumCantidad),
															operacionesResultado.getCodOperSicc(),
															operacionesResultado.getTipoOperSicc());
					this.loadMensajeCallBack(valor);					
					i++;
				}
			}else{
				this.setOnFocusCantidad(false);
			}
		}
		return i;
	}
	
	public void loadMensajeCallBack(String data){
		if(StringUtils.isNotBlank(data)
			&& StringUtils.isNotEmpty(data)){
			this.mostrarDialogo();
			this.setMensajeLocal(data);
			if(this.getOpcion().equals("4")){
				this.valorFoco = "4";
				this.setOnFocusCUV(true);
			}else{
				if(this.getOpcion().equals("5")){
					this.valorFoco = "5";
					this.setOnFocusCantidad(true);
				}
			}
		}else{
			if(this.getOpcion().equals("4")){
				this.setOnFocusCUV(false);
			}else{
				if(this.getOpcion().equals("5")){
					this.setOnFocusCantidad(false);
				}
			}
		}
	}
	
	public void cambiarPeriodo(){
		if (log.isDebugEnabled()) {
			log.debug("cambiarPeriodo");
		}
	}
	
	public void openPopup(){
		if (log.isDebugEnabled()) {
			log.debug("openPopup");
		}
		this.getRequestContext().execute("PF('dlgOpenCodigoVentaMatrizPopup').show()");
	}
	
	public boolean validaSaldoProductoCallBack(Integer data){
		if (log.isDebugEnabled()) {
			log.debug("validaSaldoProductoCallBack");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		OperacionesResultado operacionesResultado = this.getOperaciones().get(this.getDatosCabeceraCDRTO().getOperacion());
		if(StringUtils.equals(f.getIndicadorOnline(),"S")){
			if(data <= 0){
				this.setMensajeLocal(this.getResourceMessage("mantenimientoRECDigitacionCDRForm.message.listaSTO"));
				this.mostrarDialogo();
				return false;
			}
		}
		if(StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getCodigoVentaCambia())){
			/**
			 * TODO
			 */
//	 		form.listaCUVCambia[form.listaCUVCambia.length-1].readOnly=true;
//	 		form.listaCantidadCambia[form.listaCantidadCambia.length-1].readOnly=true;
//	 		form.listaIgualEnvio[form.listaIgualEnvio.length-1].disabled=true;
//	 		form.listaCUVDesea[form.listaCUVDesea.length-1].readOnly=true;
//	 		form.listaCantidadDesea[form.listaCantidadDesea.length-1].readOnly=true;
			if(this.validarMontoDevolucion()){
				//TODO addRow('prodList',listaCampos);   //Verificar si aqui se inserta a 
				//la lista final los datos ingresados procesador por cvada componente
				//desabilitarFilas();	//TODO Verificar si se deshabilitan componentes luego de la inserción forma parte del comportamiento de la plantilla (vista).
				this.setEsEnterEnCantidadProductoCambia(false);		//TODO verificar si es necesario utilizar flag
				this.setEsEnterEnCantidadProductoCambiaOnChange(false); //TODO verificar si es necesario utilizar flag
				this.ejecutarFunciones("1");
			}else{
				//TODO 
				
				/*form.listaCUVCambia[form.listaCUVCambia.length-1].readOnly=false;
	 			form.listaCUVCambia[form.listaCUVCambia.length-1].value='';
	 			
		 		form.listaCantidadCambia[form.listaCantidadCambia.length-1].readOnly=false;
		 		form.listaCantidadCambia[form.listaCantidadCambia.length-1].value='';
		 		
		 		form.listaIgualEnvio[form.listaIgualEnvio.length-1].disabled=false;
		 		form.listaIgualEnvio[form.listaIgualEnvio.length-1].checked=false;
		 					 		 		
		 		form.listaCUVDesea[form.listaCUVDesea.length-1].readOnly=false;
		 		form.listaCUVDesea[form.listaCUVDesea.length-1].value='';
		 		
		 		form.listaCantidadDesea[form.listaCantidadDesea.length-1].readOnly=false;
		 		form.listaCantidadDesea[form.listaCantidadDesea.length-1].value='';

		 		form.listaDescripcionCambia[form.listaCantidadDesea.length-1].value='';
		 		form.listaPrecioCambia[form.listaCantidadDesea.length-1].value='';

		 		form.listaIdentSolicPos[form.listaIdentSolicPos.length-1].value='';*/
		 		
				this.mostrarDialogoGeneral();
				this.setMensajeAlertaDefault(this.getResourceMessage("message.supera.porcentaje") + " " + this.getPorcentajeDevolucion());
			}
		}else{
			if(CollectionUtils.exists(
					this.getArrCodigoOperacion(), 
					new PredicateUtils().equalPredicate(
							this.getDatosCabeceraCDRTO().getCodigoVentaCambia()))){
				int index = this.getArrCodigoOperacion().indexOf(
					CollectionUtils.find(this.getArrCodigoOperacion(), 
						new PredicateUtils().equalPredicate(
								this.getDatosCabeceraCDRTO().getCodigoVentaCambia())));
				if(StringUtils.equals((String)this.getArrIndicadorCUVCambiaObligatorio().get(index), "NO")
					|| StringUtils.equals((String)this.getArrIndicadorCUVCambiaObligatorio().get(index), "SI")
					&& StringUtils.equals(operacionesResultado.getCodigo(), "T")){
					
					if(validarMontoDevolucion()){
//					addRow('prodList',listaCampos);
//					desabilitarFilas();
						this.setEsEnterEnCantidadProductoCambia(false);
						this.setEsEnterEnCantidadProductoCambiaOnChange(false);
						this.ejecutarFunciones("1");
					}else{
						this.mostrarDialogoGeneral();
						this.setMensajeAlertaDefault(this.getResourceMessage("message.supera.porcentaje") + " " + this.getPorcentajeDevolucion());
					}
				}
			}
		}
		this.valorFoco = "idFoco3";
		return true;
	}
	
	public boolean validarMontoDevolucion(){
		if (log.isDebugEnabled()) {
			log.debug("validarMontoDevolucion");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		OperacionesResultado operacionesResultado = this.getOperaciones().get(this.getDatosCabeceraCDRTO().getOperacion());
		//Solo aplica la logica para la pantalla de callcenter
		if(StringUtils.equals(f.getIndicadorOnline(), "S")){
			return true;
		}
		//Solo aplica la logica si esta encendido el indicador
		if(StringUtils.equals(f.getIndicadorValidaDevolucion(),"0")){
			return true;
		}
		//Solo se realiza la validacion si la opercacione es de devolucion
		if(!StringUtils.equals(operacionesResultado.getCodigo(), "D")){
			return true;
		}
		
		double monto = this.calcularMontoDevolucionActual();
		double valor = ((monto + this.getMontoDevolucion())/this.getMontoPedido())*100;
		
		if(valor > this.getPorcentajeDevolucion()){
			return false;
		}else{
			return true;
		}
	}
	
	public void mostrarDialogo(){
		if (log.isDebugEnabled()) {
			log.debug("mostrarDialogo");
		}
		this.getRequestContext().execute("PF('dlgLocal').show()");
	}
	
	public void mostrarDialogoConfirmacion(){
		if (log.isDebugEnabled()) {
			log.debug("mostrarDialogoConfirmacion");
		}
		this.getRequestContext().execute("PF('dlgConfirmacion').show()");
	}
	
	public String indicadorExpressEstado(){
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		if(f.isIndicadorExpress()){
			return "1";
		}else{
			return "0";
		}
	}
	
	/**
	 * Metodos Get y Set
	 * @return
	 */

	public List getListaOperaciones() {
		return listaOperaciones;
	}

	public void setListaOperaciones(List listaOperaciones) {
		this.listaOperaciones = listaOperaciones;
	}

	public List getListaMotivo() {
		return listaMotivo;
	}

	public void setListaMotivo(List listaMotivo) {
		this.listaMotivo = listaMotivo;
	}

	public boolean isMostrarPopupRECCodigoVentaPedido() {
		return mostrarPopupRECCodigoVentaPedido;
	}

	public void setMostrarPopupRECCodigoVentaPedido(
			boolean mostrarPopupRECCodigoVentaPedido) {
		this.mostrarPopupRECCodigoVentaPedido = mostrarPopupRECCodigoVentaPedido;
	}

	public BusquedaRECDocumentoReferenciaSearchAction getBusquedaRECDocumentoReferenciaSearchAction() {
		return busquedaRECDocumentoReferenciaSearchAction;
	}

	public void setBusquedaRECDocumentoReferenciaSearchAction(
			BusquedaRECDocumentoReferenciaSearchAction busquedaRECDocumentoReferenciaSearchAction) {
		this.busquedaRECDocumentoReferenciaSearchAction = busquedaRECDocumentoReferenciaSearchAction;
	}

	public boolean isMostrarPopupRECDocRef() {
		return mostrarPopupRECDocRef;
	}

	public void setMostrarPopupRECDocRef(boolean mostrarPopupRECDocRef) {
		this.mostrarPopupRECDocRef = mostrarPopupRECDocRef;
	}

	public static String getMensajeValidaPedido() {
		return mensajeValidaPedido;
	}

	public static void setMensajeValidaPedido(String mensajeValidaPedido) {
		MantenimientoRECDigitacionCDRAction.mensajeValidaPedido = mensajeValidaPedido;
	}

	public List getMantenimientoRECDigitacionCDRDetallesDigitadosList() {
		return mantenimientoRECDigitacionCDRDetallesDigitadosList;
	}

	public void setMantenimientoRECDigitacionCDRDetallesDigitadosList(
			List mantenimientoRECDigitacionCDRDetallesDigitadosList) {
		this.mantenimientoRECDigitacionCDRDetallesDigitadosList = mantenimientoRECDigitacionCDRDetallesDigitadosList;
	}

	public List getRecOperacionParametrosList() {
		return recOperacionParametrosList;
	}

	public void setRecOperacionParametrosList(List recOperacionParametrosList) {
		this.recOperacionParametrosList = recOperacionParametrosList;
	}

	public List getLstCodMotRechazo() {
		return lstCodMotRechazo;
	}

	public void setLstCodMotRechazo(List lstCodMotRechazo) {
		this.lstCodMotRechazo = lstCodMotRechazo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getValorFoco() {
		return valorFoco;
	}

	public void setValorFoco(String valorFoco) {
		this.valorFoco = valorFoco;
	}

	public double getMontoDevolucion() {
		return montoDevolucion;
	}

	public void setMontoDevolucion(double montoDevolucion) {
		this.montoDevolucion = montoDevolucion;
	}

	public double getMontoPedido() {
		return montoPedido;
	}

	public void setMontoPedido(double montoPedido) {
		this.montoPedido = montoPedido;
	}

	public double getPorcentajeDevolucion() {
		return porcentajeDevolucion;
	}

	public void setPorcentajeDevolucion(double porcentajeDevolucion) {
		this.porcentajeDevolucion = porcentajeDevolucion;
	}	

	public boolean isMostrarBotonBuscarDOCREF() {
		return mostrarBotonBuscarDOCREF;
	}

	public void setMostrarBotonBuscarDOCREF(boolean mostrarBotonBuscarDOCREF) {
		this.mostrarBotonBuscarDOCREF = mostrarBotonBuscarDOCREF;
	}

	public boolean isMostrarPopupCUV() {
		return mostrarPopupCUV;
	}

	public void setMostrarPopupCUV(boolean mostrarPopupCUV) {
		this.mostrarPopupCUV = mostrarPopupCUV;
	}

	public BusquedaRECCodigoVentaPedidoAction getBusquedaRECCodigoVentaPedidoAction() {
		return busquedaRECCodigoVentaPedidoAction;
	}

	public void setBusquedaRECCodigoVentaPedidoAction(
			BusquedaRECCodigoVentaPedidoAction busquedaRECCodigoVentaPedidoAction) {
		this.busquedaRECCodigoVentaPedidoAction = busquedaRECCodigoVentaPedidoAction;
	}

	public DatosCabeceraCDRTO getDatosCabeceraCDRTO() {
		return datosCabeceraCDRTO;
	}

	public void setDatosCabeceraCDRTO(DatosCabeceraCDRTO datosCabeceraCDRTO) {
		this.datosCabeceraCDRTO = datosCabeceraCDRTO;
	}

	public BusquedaRECCodigoVentaMatrizAction getBusquedaRECCodigoVentaMatrizAction() {
		return busquedaRECCodigoVentaMatrizAction;
	}

	public void setBusquedaRECCodigoVentaMatrizAction(
			BusquedaRECCodigoVentaMatrizAction busquedaRECCodigoVentaMatrizAction) {
		this.busquedaRECCodigoVentaMatrizAction = busquedaRECCodigoVentaMatrizAction;
	}
	
	public boolean isMostrarPopupCVM() {
		return mostrarPopupCVM;
	}

	public void setMostrarPopupCVM(boolean mostrarPopupCVM) {
		this.mostrarPopupCVM = mostrarPopupCVM;
	}

	public Map<String, OperacionesResultado> getOperaciones() {
		return operaciones;
	}

	public void setOperaciones(Map<String, OperacionesResultado> operaciones) {
		this.operaciones = operaciones;
	}

	public boolean isDeshabilitarEnvio() {
		return deshabilitarEnvio;
	}

	public void setDeshabilitarEnvio(boolean deshabilitarEnvio) {
		this.deshabilitarEnvio = deshabilitarEnvio;
	}

	public boolean isMostrarBotonBuscarCUVCambia() {
		return mostrarBotonBuscarCUVCambia;
	}

	public void setMostrarBotonBuscarCUVCambia(boolean mostrarBotonBuscarCUVCambia) {
		this.mostrarBotonBuscarCUVCambia = mostrarBotonBuscarCUVCambia;
	}

	public boolean isMostrarBotonBuscarCUVDesea() {
		return mostrarBotonBuscarCUVDesea;
	}

	public void setMostrarBotonBuscarCUVDesea(boolean mostrarBotonBuscarCUVDesea) {
		this.mostrarBotonBuscarCUVDesea = mostrarBotonBuscarCUVDesea;
	}

	public boolean isFlagOperacionTrueque() {
		return flagOperacionTrueque;
	}

	public void setFlagOperacionTrueque(boolean flagOperacionTrueque) {
		this.flagOperacionTrueque = flagOperacionTrueque;
	}

	public boolean isOnFocusOperacion() {
		return onFocusOperacion;
	}

	public void setOnFocusOperacion(boolean onFocusOperacion) {
		this.onFocusOperacion = onFocusOperacion;
	}

	public String getVarOidPeriCDR() {
		return varOidPeriCDR;
	}

	public void setVarOidPeriCDR(String varOidPeriCDR) {
		this.varOidPeriCDR = varOidPeriCDR;
	}

	public String getVisualizaRechazo() {
		return visualizaRechazo;
	}

	public void setVisualizaRechazo(String visualizaRechazo) {
		this.visualizaRechazo = visualizaRechazo;
	}

	public String getVarCodOperSICC() {
		return varCodOperSICC;
	}

	public void setVarCodOperSICC(String varCodOperSICC) {
		this.varCodOperSICC = varCodOperSICC;
	}

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public boolean isMostrarPanelDigitacionCdr() {
		return mostrarPanelDigitacionCdr;
	}

	public void setMostrarPanelDigitacionCdr(boolean mostrarPanelDigitacionCdr) {
		this.mostrarPanelDigitacionCdr = mostrarPanelDigitacionCdr;
	}

	public boolean isEsEnterEnCantidadProductoCambiaOnChange() {
		return esEnterEnCantidadProductoCambiaOnChange;
	}

	public void setEsEnterEnCantidadProductoCambiaOnChange(
			boolean esEnterEnCantidadProductoCambiaOnChange) {
		this.esEnterEnCantidadProductoCambiaOnChange = esEnterEnCantidadProductoCambiaOnChange;
	}

	public List getArrCuvs() {
		return arrCuvs;
	}

	public void setArrCuvs(List arrCuvs) {
		this.arrCuvs = arrCuvs;
	}

	public List getArrDesc() {
		return arrDesc;
	}

	public void setArrDesc(List arrDesc) {
		this.arrDesc = arrDesc;
	}

	public List getArrPrec() {
		return arrPrec;
	}

	public void setArrPrec(List arrPrec) {
		this.arrPrec = arrPrec;
	}

	public List getArrPrecCata() {
		return arrPrecCata;
	}

	public void setArrPrecCata(List arrPrecCata) {
		this.arrPrecCata = arrPrecCata;
	}

	public List getArrPosic() {
		return arrPosic;
	}

	public void setArrPosic(List arrPosic) {
		this.arrPosic = arrPosic;
	}

	public DigitacionCDRDataModel getDigitacionCDRDataModel() {
		return digitacionCDRDataModel;
	}

	public void setDigitacionCDRDataModel(
			DigitacionCDRDataModel digitacionCDRDataModel) {
		this.digitacionCDRDataModel = digitacionCDRDataModel;
	}

	public List<DatosCabeceraCDRTO> getListaDatosCabeceraCDRTO() {
		return listaDatosCabeceraCDRTO;
	}

	public void setListaDatosCabeceraCDRTO(
			List<DatosCabeceraCDRTO> listaDatosCabeceraCDRTO) {
		this.listaDatosCabeceraCDRTO = listaDatosCabeceraCDRTO;
	}

	public DatosCabeceraCDRTO[] getSeleccionados() {
		return seleccionados;
	}

	public void setSeleccionados(DatosCabeceraCDRTO[] seleccionados) {
		this.seleccionados = seleccionados;
	}	

	public List getArrIndicadorCUVCambiaObligatorio() {
		return arrIndicadorCUVCambiaObligatorio;
	}

	public void setArrIndicadorCUVCambiaObligatorio(
			List arrIndicadorCUVCambiaObligatorio) {
		this.arrIndicadorCUVCambiaObligatorio = arrIndicadorCUVCambiaObligatorio;
	}

	public List getArrIndicadorCUVDeseaObligatorio() {
		return arrIndicadorCUVDeseaObligatorio;
	}

	public void setArrIndicadorCUVDeseaObligatorio(
			List arrIndicadorCUVDeseaObligatorio) {
		this.arrIndicadorCUVDeseaObligatorio = arrIndicadorCUVDeseaObligatorio;
	}

	public List getArrIndicadorValidacionCUVCambia() {
		return arrIndicadorValidacionCUVCambia;
	}

	public void setArrIndicadorValidacionCUVCambia(
			List arrIndicadorValidacionCUVCambia) {
		this.arrIndicadorValidacionCUVCambia = arrIndicadorValidacionCUVCambia;
	}

	public List getArrIndicadorValidacionCUVDesea() {
		return arrIndicadorValidacionCUVDesea;
	}

	public void setArrIndicadorValidacionCUVDesea(
			List arrIndicadorValidacionCUVDesea) {
		this.arrIndicadorValidacionCUVDesea = arrIndicadorValidacionCUVDesea;
	}

	public List getArrPopupCambia() {
		return arrPopupCambia;
	}

	public void setArrPopupCambia(List arrPopupCambia) {
		this.arrPopupCambia = arrPopupCambia;
	}

	public List getArrPopupDesea() {
		return arrPopupDesea;
	}

	public void setArrPopupDesea(List arrPopupDesea) {
		this.arrPopupDesea = arrPopupDesea;
	}

	public List getArrIndicadorValidarCentroServicio() {
		return arrIndicadorValidarCentroServicio;
	}

	public void setArrIndicadorValidarCentroServicio(
			List arrIndicadorValidarCentroServicio) {
		this.arrIndicadorValidarCentroServicio = arrIndicadorValidarCentroServicio;
	}

	public List<String> getArrCodigoOperacion() {
		return arrCodigoOperacion;
	}

	public void setArrCodigoOperacion(List arrCodigoOperacion) {
		this.arrCodigoOperacion = arrCodigoOperacion;
	}

	public boolean isOnFocusCUV() {
		return onFocusCUV;
	}

	public void setOnFocusCUV(boolean onFocusCUV) {
		this.onFocusCUV = onFocusCUV;
	}

	public boolean isVerex() {
		return verex;
	}

	public void setVerex(boolean verex) {
		this.verex = verex;
	}

	public boolean isOnFocusCantidad() {
		return onFocusCantidad;
	}

	public void setOnFocusCantidad(boolean onFocusCantidad) {
		this.onFocusCantidad = onFocusCantidad;
	}

	public String getValidacionCambia() {
		return validacionCambia;
	}

	public void setValidacionCambia(String validacionCambia) {
		this.validacionCambia = validacionCambia;
	}

	public String[] getValoresOferta() {
		return valoresOferta;
	}

	public void setValoresOferta(String[] valoresOferta) {
		this.valoresOferta = valoresOferta;
	}

	public String[] getValoresOfertaPOSBUSC() {
		return valoresOfertaPOSBUSC;
	}

	public void setValoresOfertaPOSBUSC(String[] valoresOfertaPOSBUSC) {
		this.valoresOfertaPOSBUSC = valoresOfertaPOSBUSC;
	}

	public String[] getValoresOfertaPOSOFER() {
		return valoresOfertaPOSOFER;
	}

	public void setValoresOfertaPOSOFER(String[] valoresOfertaPOSOFER) {
		this.valoresOfertaPOSOFER = valoresOfertaPOSOFER;
	}

	public String[] getValoresOfertaCUV() {
		return valoresOfertaCUV;
	}

	public void setValoresOfertaCUV(String[] valoresOfertaCUV) {
		this.valoresOfertaCUV = valoresOfertaCUV;
	}

	public String[] getValoresOfertaUNI() {
		return valoresOfertaUNI;
	}

	public void setValoresOfertaUNI(String[] valoresOfertaUNI) {
		this.valoresOfertaUNI = valoresOfertaUNI;
	}

	public String[] getValoresOfertaDESPRO() {
		return valoresOfertaDESPRO;
	}

	public void setValoresOfertaDESPRO(String[] valoresOfertaDESPRO) {
		this.valoresOfertaDESPRO = valoresOfertaDESPRO;
	}

	public String[] getValoresOfertaPREPRO() {
		return valoresOfertaPREPRO;
	}

	public void setValoresOfertaPREPRO(String[] valoresOfertaPREPRO) {
		this.valoresOfertaPREPRO = valoresOfertaPREPRO;
	}

	public boolean isAgregarFilasOferta() {
		return agregarFilasOferta;
	}

	public void setAgregarFilasOferta(boolean agregarFilasOferta) {
		this.agregarFilasOferta = agregarFilasOferta;
	}

	public String getMensajeLocal() {
		return mensajeLocal;
	}

	public void setMensajeLocal(String mensajeLocal) {
		this.mensajeLocal = mensajeLocal;
	}

	public boolean isVisualizarPanelGrilla() {
		return visualizarPanelGrilla;
	}

	public void setVisualizarPanelGrilla(boolean visualizarPanelGrilla) {
		this.visualizarPanelGrilla = visualizarPanelGrilla;
	}

	public boolean isDesabilitaMotivo() {
		return desabilitaMotivo;
	}

	public void setDesabilitaMotivo(boolean desabilitaMotivo) {
		this.desabilitaMotivo = desabilitaMotivo;
	}

	public boolean isEsEnterEnCantidadProductoCambia() {
		return esEnterEnCantidadProductoCambia;
	}

	public void setEsEnterEnCantidadProductoCambia(
			boolean esEnterEnCantidadProductoCambia) {
		this.esEnterEnCantidadProductoCambia = esEnterEnCantidadProductoCambia;
	}

	public boolean isMantenerFocoIgualEnvio() {
		return mantenerFocoIgualEnvio;
	}

	public void setMantenerFocoIgualEnvio(boolean mantenerFocoIgualEnvio) {
		this.mantenerFocoIgualEnvio = mantenerFocoIgualEnvio;
	}

	public String getValidacionDesea() {
		return validacionDesea;
	}

	public void setValidacionDesea(String validacionDesea) {
		this.validacionDesea = validacionDesea;
	}
}


