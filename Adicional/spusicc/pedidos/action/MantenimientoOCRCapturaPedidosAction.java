package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.LabelDatosConsultoraValue;
import biz.belcorp.ssicc.dao.model.LabelPedidosValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.DocumentoDigitadoPK;
import biz.belcorp.ssicc.dao.spusicc.sto.model.GestionDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitadoPK;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRCapturaPedidoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRCargaPedidoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOLevantamientoErroresValidacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.CabeceraDetalleTO;
import biz.belcorp.ssicc.web.framework.util.PedidoDataModel;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaHIPClientePOPUPSearchAction;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoOCRCapturaPedidosForm;
import biz.belcorp.ssicc.web.util.NumberUtil;

/**
 * Clase MantenimientoOCRCapturaPedidosAction
 *
 * @autor: Belcorp
 * @version: 1.0
 * 21/11/2013
 */
@ManagedBean
@SessionScoped
public class MantenimientoOCRCapturaPedidosAction extends BaseMantenimientoSearchAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The longitud campo clientes. */
	private Integer longitudCampoClientes;
	
	/** The lista codigos matriz. */
	private List listaCodigosMatriz;
	
	/** The lista campanhas. */
	private List listaCampanhas;
	
	/** The lista detalle pedido. */
	private List<CabeceraDetalleTO> listaDetallePedido;
	
	/** The cabecera detalle. */
	private List<CabeceraDetalleTO> cabeceraDetalle;
	
	/** The cabecera detalle to. */
	private CabeceraDetalleTO cabeceraDetalleTO;
	
	/** The seleccionados. */
	private CabeceraDetalleTO[] seleccionados;
	
	/** The seleccionados dp. */
	private CabeceraDetalleTO[] seleccionadosDP;
	 
	/** The pedido data model. */
	private PedidoDataModel pedidoDataModel;
	
	/** The detalle pedido data model. */
	private PedidoDataModel detallePedidoDataModel;
	
	/** The mostrar popup hip cliente. */
	private boolean mostrarPopupOCRCliente = false;
	
	/** The index. */
	private int index = -1;
	
	/** The valor focus. */
	private String valorFocus;
	
	/** The valor alerta. */
	private boolean valorAlerta;
	
	/** The habdes cod con. */
	private boolean habdesCodCon;
	
	/** The dlg local. */
	private boolean dlgLocal;
	
	/** The mensaje local. */
	private String mensajeLocal;
	
	/** The propagacion. */
	private boolean propagacion;
	
	/** The colspan detalle. */
	private int colspanDetalle;
	
	/** The mostrar panel detalle pedido. */
	private boolean mostrarPanelDetallePedido = false;
	
	/** The Constant POPUP_HIPCLIENTE. */
	private static final String POPUP_OCRCLIENTE = "OCRCLIENTE";
	
	private int prol = 0;
	
	private boolean mostrarBotonEjecutarProl;
	
	private boolean mostrarBotonRetorno;
	
	private String enlaceOrigen;
	
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
				MantenimientoOCRCapturaPedidosForm busquedaForm = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
				busquedaForm.setCodigoConsultora((String)clienteHipMap.get("codigoCliente"));
				busquedaForm.setNumeroDocIdentidadBuscar((String)clienteHipMap.get("numeroDocumento"));				
				
				AjaxService ajax = (AjaxService)getBean("ajaxService");
				String consultora = ajax.getConsultoraExistenteByCriteria(busquedaForm.getCodigoPais(),
																			busquedaForm.getPeriodo(),
																			busquedaForm.getCodigoConsultora(),
																			busquedaForm.getFechaFacturacion());
				LabelDatosConsultoraValue[] lbldata = ajax.getCabeceraConsultoraSimple(busquedaForm.getCodigoPais(), busquedaForm.getCodigoConsultora());
				if(lbldata.length>0){					
					busquedaForm.setZona(((LabelDatosConsultoraValue)lbldata[0]).getZona());
					busquedaForm.setRegion(((LabelDatosConsultoraValue)lbldata[0]).getRegion());
					busquedaForm.setIndicadorActiva(((LabelDatosConsultoraValue)lbldata[0]).getIndicadorActiva());
					busquedaForm.setEstadoConsultora(((LabelDatosConsultoraValue)lbldata[0]).getEstadoConsultora());
					busquedaForm.setIndicadorBloqueo(((LabelDatosConsultoraValue)lbldata[0]).getIndicadorBloqueo());
					busquedaForm.setNombreConsultora(((LabelDatosConsultoraValue)lbldata[0]).getNombreConsultora());					
				}
				
				this.formBusqueda =  busquedaForm;
				this.busquedaHIPClientePOPUPSearchAction.setBeanRegistroSeleccionado(null);
			}
		}		
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}
		
		this.searchConsultoraOnEnter();
		
		
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
	
	public void buscaClientexCodigo(ActionEvent actionEvent) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'buscaClientexCodigo' method");
		}
		
		MantenimientoOCRCapturaPedidosForm searchForm = (MantenimientoOCRCapturaPedidosForm) this.formBusqueda;			
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService)this.getBeanService("scsicc.consultaHIPDatosClienteService");
		ReporteService reporteService = (ReporteService)this.getBeanService("scsicc.reporteService");
		
		//Recuperamos el idioma		
		Idioma idioma = this.mPantallaPrincipalBean.getCurrentIdioma();
		Map parameterMap = new HashMap();
		parameterMap.put("codigoIdiomaIso", idioma.getCodigoSiCC());
		parameterMap.put("codigoIdioma", idioma.getCodigoISO());
		String oidIdiomaIso = reporteService.getOidString("getOidIdiomaByCodigoIdiomaIso", parameterMap);
				
		Map param = new HashMap();
		
		param.put("oidIdioma", oidIdiomaIso);
		param.put("codigoPais", this.mPantallaPrincipalBean.getCountryCode());
		param.put("codigoCliente", searchForm.getCodigoConsultora());
		
		List resultado = service.getClientesByCriteria(param);
		log.debug("Pintando el tamaño de la lista " + resultado.size());
		
		if(resultado.size() > 0){
			for(int i=0; i<resultado.size(); i++) {
				Map mapResul = (Map)resultado.get(i);
				searchForm.setNumeroDocIdentidadBuscar((String)mapResul.get("numeroDocumento"));
			}
		}else{
			searchForm.setNumeroDocIdentidadBuscar("");
		}
		
	}
	
	public void inicializar(){
		if (log.isDebugEnabled()) {
			log.debug("Inicializar");
		}
		this.activarHotkeyEstandar = false;
		if(StringUtils.isNotBlank(this.getEnlaceOrigen())
				&& StringUtils.isNotEmpty(this.getEnlaceOrigen())){
			if(StringUtils.equals(this.getEnlaceOrigen(), "HIP")){
				this.setMostrarBotonRetorno(true);
			}
		}else{
			this.setMostrarBotonRetorno(false);
		}
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setViewAtributes");
		}
		this.activarHotkeyEstandar = false;
		this.mostrarBotonSalir = false;
		this.mostrarProcesoBatch = false;
		this.mostrarPopupOCRCliente = true;
		this.mostrarPanelDetallePedido = false;
		this.mostrarCabeceraFija = true;
		MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
        MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
        PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(criteria);
		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		
		setLongitudCampoClientes(clienteService.getTamanhoNumeroCliente(pais.getCodigo()));
		
		/**
		 * Carga Fecha y Periodo
		 */		
		String fechaFact = this.formBusqueda.getCodigoPeriodo();
		log.debug("__fechaFact = "+fechaFact);
		if(fechaFact != null){
			if(fechaFact.equals("")){
				f.setFechaFacturacion(controlFacturacion.getFechaProceso());
			}
			else{
				f.setFechaFacturacion(fechaFact);
			}
		}
		else{
			f.setFechaFacturacion(controlFacturacion.getFechaProceso());
		}
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
        f.setPeriodo(f.getCodigoPeriodo());
		/**
		 *  Carga Numero de lotes
		 */
        Map map = new HashMap();
        map.put("periodo",controlFacturacion.getCodigoPeriodo()); 
        map.put("pais",pais.getCodigo());
//        f.setNumLote(serviceFact.getNumLotes(map));
        f.setNumLote(Constants.PED_LOTE_FIJO_DIGIT_PEDIDOS);
        /**
         * Lista de campanhas activas
         */
        criteria.put("indicadorActiva","");
        
        /**
         * Lista de campanias
         */
        setListaCampanhas(serviceFact.getCampanhasActivasById(criteria));
        
        f.setUnidadesMaximas(serviceFact.getMaximoUnidades(map));
        f.setLongitudUnidadesMaximas(serviceFact.getLongitudMaximoUnidades(map));
        //se muestra la consultora, si es enviado
        String codigoConsultora = f.getCodigoConsultora();
        if(codigoConsultora != null) {
        	f.setMostrarConsultora(true);
        	f.setCodigoConsultora(codigoConsultora);
        } else {
        	f.setMostrarConsultora(false);
        }
        String indicadorHiperConsulta = f.getIndicadorHiperConsulta();
        
        f.setIndicadorHiperConsulta(Constants.NO);
        f.setIndicadorCerrarPopup(Constants.NO);
        if (StringUtils.isNotBlank(indicadorHiperConsulta)){
        	f.setIndicadorHiperConsulta(Constants.SI);
        }
                
        /**
         * Cargo la matriz en memoria
         */
        MantenimientoOCRCapturaPedidoService service = (MantenimientoOCRCapturaPedidoService)getBean("spusicc.pedidos.mantenimientoOCRCapturaPedidoService");
        setListaCodigosMatriz(service.getListaCodigosVentaMatriz(map));
        
       /**
        * Setea el indicador de parametria (Mostrar detalle) BAS_PAIS -> IND_DIGI_PEDI [S]/[N]
        */
        f.setIndicadorMostrarDetalle(pais.getIndicadorMostrarDetalleDigitacionPedidos());
        //f.setIndicadorMostrarDetalle("N");
        if(f.getIndicadorMostrarDetalle().equals("S")){
        	this.setColspanDetalle(5);
        }
        
        if(f.getIndicadorMostrarDetalle().equals("N")){
        	this.setColspanDetalle(3);
        }

        //session.removeAttribute(Constants.LISTA_DETALLE_PEDIDO);
        f.setIndicadorDatos("N");
        
        f.setIndicadorActiva("");
        f.setEstadoConsultora("");
        f.setIndicadorBloqueo("");
        
        this.cabeceraDetalleTO = new CabeceraDetalleTO();
        
        this.cabeceraDetalle = new ArrayList();
        this.listaDetallePedido = new ArrayList<CabeceraDetalleTO>();
        
        this.pedidoDataModel = new PedidoDataModel(this.cabeceraDetalle);
        this.detallePedidoDataModel = new PedidoDataModel(this.listaDetallePedido);
        this.valorFocus = "1";
        this.mostrarCabeceraFija = true;
//        this.mostrarBotonEjecutarProl = true;
        return;
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		if (log.isDebugEnabled()) {
			log.debug("getSalirForward");
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("getSalirForward");
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoOCRCapturaPedidosForm form = new MantenimientoOCRCapturaPedidosForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setFindAttributes");
		}
		return null;
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setFindAttributes");
		}
		return null;
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setDeleteAttributes");
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setSaveAttributes");
		}
		
		//this.antesGuardar(new ActionEvent(new UICommand()));
		
		return false;
	}
	
	/**
	 * Metodo que realiza el seguimiento del valor del CUV ingresado por pantalla de digitacion de pedidos.
	 */
	public void ingresoCodigoVenta(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ingresoCodigoVenta' method");
		}
		/**
		 * Verifica la existencia de codigo de consulta ingresado.
		 */
		boolean existeCodCons = existeConsultora();
		if(existeCodCons){
			this.habdesCodCon = true;
			if(StringUtils.isNotBlank(this.getCabeceraDetalleTO().getCodigoVenta()) 
					&& StringUtils.isNotEmpty(this.getCabeceraDetalleTO().getCodigoVenta())){
				if(existeCodigoInListaDetPed(this.getCabeceraDetalleTO().getCodigoVenta())>0){
					List lista = cargarDetallePedido();
					List<CabeceraDetalleTO> listado = conversionLista(lista);
					obtenerObjeto("codigoVenta",this.getCabeceraDetalleTO().getCodigoVenta(),listado);
					this.setMensajeLocal(this.getResourceMessage("mensaje.codigoVtaExiste"));
					this.posicionarUniDem();
					this.index = -1;
				}else{			
					this.index = validaExisteCodigoVenta();
					if(this.getIndex()!=-1){
						int existeCodVenta = validaCodigoVentaInListaCabecera();
						if(existeCodVenta>0){
							log.debug(this.getResourceMessage("procesoUsuario.gestionPedidos.ingresoPedidos.productoRegistrado"));
							this.setMensajeLocal(this.getResourceMessage("procesoUsuario.gestionPedidos.ingresoPedidos.productoRegistrado"));
							posicionarCUV();
							this.cabeceraDetalleTO.setCodigoVenta("");
						}else{
							LabelPedidosValue labelPedidosValue = (LabelPedidosValue)listaCodigosMatriz.get(this.getIndex());		
							this.cabeceraDetalleTO.setDesProducto(labelPedidosValue.getDescripcion());
							this.cabeceraDetalleTO.setValorPrecCatalogo(labelPedidosValue.getPrecioCat());
							this.valorFocus = "3";
						}
					}else{
						log.debug("No existe codigo de venta: " + this.getCabeceraDetalleTO().getCodigoVenta());
						this.setMensajeLocal(this.getResourceMessage("mensaje.codVtaNoExiste"));
						posicionarCUV();
						this.cabeceraDetalleTO.setCodigoVenta("");
					}
				}
			}else{
				log.debug("Ingrese codigo de venta: ");
			}
		}else{			
			posicionarCodConsultora();
		}
			
	}	
	
	/**
	 * Posicionar uni dem.
	 */
	public void posicionarUniDem(){
		this.valorFocus = "3";
		this.mostrarDialogoComun();
	}
	
	/**
	 * Posicionar cuv.
	 */
	public void posicionarCUV(){		
		this.valorFocus = "2";
		this.mostrarDialogoComun();		
	}
	
	/**
	 * Posicionar cod consultora.
	 */
	public void posicionarCodConsultora(){
		if(StringUtils.isBlank(((MantenimientoOCRCapturaPedidosForm)this.formBusqueda).getCodigoConsultora())
				|| StringUtils.isEmpty(((MantenimientoOCRCapturaPedidosForm)this.formBusqueda).getCodigoConsultora())){			
			log.debug("mensaje: " + this.getResourceMessage("procesoUsuario.gestionPedidos.ingresoPedidos.seleccioneConsultora"));
			this.setMensajeLocal(this.getResourceMessage("procesoUsuario.gestionPedidos.ingresoPedidos.seleccioneConsultora"));
			this.valorFocus = "1";
			this.mostrarDialogoComun();
			this.cabeceraDetalleTO.setCodigoVenta("");
		}
	}
	
	/**
	 * Agregar pedido detalle.
	 */
	public void agregarPedidoDetalle(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ingresoPedidoDetalleS' method");
		}
		MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
		if(StringUtils.isNotBlank(this.getCabeceraDetalleTO().getUnidDemanda()) && StringUtils.isNotEmpty(this.getCabeceraDetalleTO().getUnidDemanda())){
			if(Integer.parseInt(this.getCabeceraDetalleTO().getUnidDemanda()) > Integer.parseInt(f.getUnidadesMaximas())){
				StringBuilder cadena = new StringBuilder();
				cadena.append(this.getResourceMessage("mensaje.confirm.mayor99"))
						.append(f.getUnidadesMaximas())
						.append(this.getResourceMessage("mensaje.alert.modificarRegistro"));
				this.mensajeLocal =  cadena.toString();
				this.mostrarDialogoComun();
				this.getCabeceraDetalleTO().setUnidDemanda("");
				this.valorFocus = "3";
			}else{
				this.actualizaPedidoS();
			}
		}else{
			log.debug("Ingrese unidades de demanda.");
			//TODO mostrar popup de advertencia de ingreso de unidades.
		}
	}
	
	/**
	 * Valida existe codigo venta.
	 *
	 * @return the int
	 */
	public int validaExisteCodigoVenta(){
		int index = -1;
		for (int i = 0; i < listaCodigosMatriz.size(); i++) {
			if(((LabelPedidosValue)listaCodigosMatriz.get(i)).getCodigoVta().equals(this.getCabeceraDetalleTO().getCodigoVenta())){
				index = i;
				break;
			}			
		}
		return index;
	}
	
	
	/**
	 * Verifica la existencia de CUV desde la lista de cabecera de pedidos hacia la lista de detalle de pedidos.
	 *
	 * @return true, if successful
	 */
	public boolean existenCUVsInListaDetPed(){
		List lista = cargarDetallePedido();
		List<CabeceraDetalleTO> listaDetallePedido = conversionLista(lista);
		List<CabeceraDetalleTO> listaCabecera = this.getCabeceraDetalle();
		for (CabeceraDetalleTO elemento : listaCabecera) {
			if(CollectionUtils.exists(
					listaDetallePedido, new BeanPredicate(
							"codigoVenta", new EqualPredicate(elemento.getCodigoVenta())))){
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Establece el valor del objeto de acuerdo a los parametros ingresados.
	 *
	 * @param propertyName the property name
	 * @param value the value
	 * @param listado the listado
	 */
	
	public void obtenerObjeto(String propertyName, String value, List listado){		
		EqualPredicate nameEqlPredicate = new EqualPredicate(value);		
		BeanPredicate beanPredicate = new BeanPredicate(propertyName, nameEqlPredicate);
		CabeceraDetalleTO cabeceraDetalleTO = (CabeceraDetalleTO)CollectionUtils.find(listado, beanPredicate);
		this.setCabeceraDetalleTO(cabeceraDetalleTO);	
	}
	
	/**
	 * Verifica la existencia del CUV antes de ingresar a la lista de cabecera depedidos.
	 *
	 * @param codigoVenta the codigo venta
	 * @return the int
	 */
	public int existeCodigoInListaDetPed(String codigoVenta){
		int i = 0;
		List lista = cargarDetallePedido();
		List<CabeceraDetalleTO> listadoCabecera = conversionLista(lista);
		List<String> codigosVentas = new ArrayList<String>();
		for (CabeceraDetalleTO elemento : listadoCabecera) {
			codigosVentas.add(new String(elemento.getCodigoVenta()));
		}
		i = CollectionUtils.cardinality(codigoVenta, codigosVentas);
		
		lista.clear();
		listadoCabecera.clear();
		codigosVentas.clear();
		return i;
	}
	
	
	
	/**
	 * Actualiza pedido s.
	 */
	public void actualizaPedidoS(){
		MantenimientoOCRCapturaPedidosForm form = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
		int totalUnid = 0;
		double totalPedido = 0.0d;
		if(this.getIndex()!=-1){
			LabelPedidosValue labelPedidosValue = (LabelPedidosValue)listaCodigosMatriz.get(this.getIndex());
			this.cabeceraDetalleTO.setCodigoCliente(form.getCodigoConsultora());
			this.cabeceraDetalleTO.setCodigoPeriodo(form.getPeriodo());
			this.cabeceraDetalleTO.setDesProducto(labelPedidosValue.getDescripcion());
			this.cabeceraDetalleTO.setValorPrecCatalogo(labelPedidosValue.getPrecioCat());
			if(StringUtils.isNotBlank(this.cabeceraDetalleTO.getUnidDemanda()) && StringUtils.isNotEmpty(this.cabeceraDetalleTO.getUnidDemanda())){
				this.cabeceraDetalleTO.setPreTotal(
						"" + Double.parseDouble(labelPedidosValue.getPrecioCat()) 
							* Integer.parseInt(this.cabeceraDetalleTO.getUnidDemanda()));
			}		
		}else{
			EqualPredicate nameEqlPredicate = new EqualPredicate(this.getCabeceraDetalleTO().getCodigoVenta());
			BeanPredicate beanPredicate = new BeanPredicate("codigoVta", nameEqlPredicate);
			LabelPedidosValue labelPedidosValue = (LabelPedidosValue)CollectionUtils.find(this.getListaCodigosMatriz(), beanPredicate);
			if(StringUtils.isNotBlank(this.cabeceraDetalleTO.getUnidDemanda()) && StringUtils.isNotEmpty(this.cabeceraDetalleTO.getUnidDemanda())){
				this.cabeceraDetalleTO.setPreTotal(
						"" + Double.parseDouble(labelPedidosValue.getPrecioCat()) 
							* Integer.parseInt(this.cabeceraDetalleTO.getUnidDemanda()));
			}
		}
		if(validaDatos()){
			
			AjaxService ajax = (AjaxService)getBean("ajaxService");
			
			String validarCUVLimiteVenta = ajax.validarCUVLimiteVenta(
					cabeceraDetalleTO.getCodigoVenta(), 
					cabeceraDetalleTO.getCodigoPeriodo(), 
					cabeceraDetalleTO.getCodigoCliente(),
					cabeceraDetalleTO.getUnidDemanda());
			
			if(validarCUVLimiteVenta.equals("False")){
				
				if(this.accion.equals(this.ACCION_MODIFICAR)){
					this.accion = this.ACCION_BUSCAR;
					((CabeceraDetalleTO[])this.getSeleccionados())[0] = this.getCabeceraDetalleTO();
					this.seleccionados = null;
				}else{
					this.cabeceraDetalle.add(this.getCabeceraDetalleTO());					
				}
				
				this.pedidoDataModel = new PedidoDataModel(this.cabeceraDetalle);
				this.setCabeceraDetalleTO(new CabeceraDetalleTO());
				form.setTxtnumPedidos(String.valueOf(this.cabeceraDetalle.size()));
				
				for (CabeceraDetalleTO elemento : cabeceraDetalle) {			
					totalUnid = totalUnid + NumberUtils.toInt(elemento.getUnidDemanda());					
					totalPedido = totalPedido + NumberUtils.toDouble(elemento.getPreTotal(),0.00d);					
				}
				List lista = this.cargarDetallePedido();
				if(lista!=null){
					if(lista.size()>0){
						totalPedido = Double.parseDouble(form.getTotalPedido()) + totalPedido; 
					}
				}
				
				form.setTotalUnid(String.valueOf(totalUnid));
				form.setTotalPedido(NumberUtil.NumberToString(totalPedido));
				this.valorFocus = "2";
				
			}else{
				this.setMensajeAlertaDefault(validarCUVLimiteVenta);
				this.mostrarDialogoGeneral();
				return;
			}
		}
	}
	
	/**
	 * Valida datos.
	 *
	 * @return true, if successful
	 */
	public boolean validaDatos(){
		boolean opc = true;
		if(StringUtils.isBlank(this.cabeceraDetalleTO.getCodigoVenta())
				|| StringUtils.isEmpty(this.cabeceraDetalleTO.getCodigoVenta())){
			opc = false; 
		}
		if(StringUtils.isBlank(this.cabeceraDetalleTO.getDesProducto())
				|| StringUtils.isEmpty(this.cabeceraDetalleTO.getDesProducto())){
			opc = false;
		}
		if(StringUtils.isBlank(this.cabeceraDetalleTO.getValorPrecCatalogo())
				|| StringUtils.isEmpty(this.cabeceraDetalleTO.getValorPrecCatalogo())){
			opc = false;
		}
		if(StringUtils.isBlank(this.cabeceraDetalleTO.getUnidDemanda())
				|| StringUtils.isEmpty(this.cabeceraDetalleTO.getUnidDemanda())){
			opc = false;
		}
		if(StringUtils.isBlank(this.cabeceraDetalleTO.getPreTotal())
				|| StringUtils.isEmpty(this.cabeceraDetalleTO.getPreTotal())){
			opc = false;
		}
		return opc;
	}
	
	/**
	 * Valida codigo venta in lista cabecera.
	 *
	 * @return the int
	 */
	public int validaCodigoVentaInListaCabecera(){
		int i=0;
		if(!cabeceraDetalle.isEmpty() && cabeceraDetalle.size()>0){
			for (CabeceraDetalleTO object : cabeceraDetalle) {
				if(object.getCodigoVenta().equals(this.cabeceraDetalleTO.getCodigoVenta())){
					i++;
					return i;
				}
			}
		}
		return i;
	}
	
	/**
	 * Limpieza.
	 */
	public void limpieza(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'limpieza' method");
		}
		
		MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
		
		f.setCodigoConsultora("");
		f.setRegion("");
		f.setZona("");
		f.setEstadoConsultora("");
		f.setIndicadorActiva("");
		f.setIndicadorBloqueo("");
		f.setNombreConsultora("");
		f.setTxtnumPedidos("");
		f.setNumPedidosRegistrados("");
		f.setTotalPedido("");
		f.setTotalTot("");
		f.setTotalUnid("");
		f.setMontoMinimo("");
		this.cabeceraDetalleTO = new CabeceraDetalleTO();
		this.cabeceraDetalle = new ArrayList<CabeceraDetalleTO>();
		this.pedidoDataModel = new PedidoDataModel(new ArrayList<CabeceraDetalleTO>(this.getCabeceraDetalle()));
	}	
	
	/**
	 * Limpiar panel consulta.
	 */
	public void limpiarPanelConsulta(){
		MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
		f.setCodigoConsultora("");
		f.setNombreConsultora("");
		f.setRegion("");
		f.setZona("");
		f.setEstadoConsultora("");
		f.setEstatus("");
		f.setIndicadorBloqueo("");
		f.setIndicadorActiva("");
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
		MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
		List<String> listado = new ArrayList<String>();
		listado.add(StringUtils.leftPad(query, 9,"0"));
		f.setCodigoConsultora(StringUtils.leftPad(query, 9,"0"));
		return listado;
	}
	
	
	/**
	 * Search consultora on enter.
	 */
	public void searchConsultoraOnEnter(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'searchConsultoraOnEnter' method");
		}		
		MantenimientoOCRCapturaPedidosForm busquedaForm = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
		
		if(StringUtils.isNotBlank(busquedaForm.getCodigoConsultora())
				&& StringUtils.isNotEmpty(busquedaForm.getCodigoConsultora())){
			if(existeConsultora()){
				
				 if(existeBloqueoOnline()>0){
		        	this.mostrarBotonEjecutarProl = true;		        	
		        }else{
		        	this.buscarConsultora();
					this.cargarMontoMinimo(busquedaForm.getCodigoConsultora());
					this.valorFocus = "2";
					String existePedido = existePedidoConsultora();				
					List lista = this.cargarDetallePedido();
					if(!lista.isEmpty()){
			    		if(lista.size()>0){		    			
			    			this.listaDetallePedido = conversionLista(lista);
			    			this.detallePedidoDataModel = new PedidoDataModel(this.getListaDetallePedido());
			    			Object[] objs = {this.getmPantallaPrincipalBean().getCodigoUsuario(),
			    							DateUtil.getDateTimeNow("dd/MM/yyyy hh:mm:ss", new Date())};
			    			this.mensajeLocal =  this.getResourceMessage("procesoUsuario.gestionPedidos.ingresoPedidos.mensajeAvisoDetalleListaPedido",objs);		    			
			    			this.mostrarDialogoDetallePedido();
			    			this.mostrarPanelDetallePedido = true;		    			
			    		}    	
			    	}else{
			    		this.setListaDetallePedido(new ArrayList<CabeceraDetalleTO>());
			    		this.detallePedidoDataModel = new PedidoDataModel(this.getListaDetallePedido());
			    		this.mostrarPanelDetallePedido = false;
			    	}
		        }
			}else{
				this.limpiarPanelConsulta();
				this.setMensajeLocal(this.getResourceMessage("mensaje.noExisteConsultora"));				
				this.mostrarDialogoComun();
				this.valorFocus = "1";
				this.setListaDetallePedido(new ArrayList<CabeceraDetalleTO>());
				this.detallePedidoDataModel = new PedidoDataModel(this.getListaDetallePedido());
				this.mostrarPanelDetallePedido = false;				
			}			
		}		
	}
	
	/**
	 * Conversion lista.
	 *
	 * @param lista the lista
	 * @return the list
	 */
	public List<CabeceraDetalleTO> conversionLista(List lista){
		List<CabeceraDetalleTO> resultado = new ArrayList<CabeceraDetalleTO>();
		for (Object object : lista) {
			CabeceraDetalleTO objeto = new CabeceraDetalleTO();
			HashMap  map = (HashMap)object;
			objeto.setCodigoVenta((String)map.get("codigoVenta"));
			objeto.setDesProducto((String)map.get("producto"));
			objeto.setCodigoPais((String)map.get("codPais"));
			objeto.setCodigoCliente((String)map.get("codCliente"));
			BigDecimal uniDemanda = (BigDecimal)map.get("uniDemanda");
			objeto.setUnidDemanda(uniDemanda.toString());
			objeto.setNumLote((String)map.get("numLote"));
			BigDecimal preTotal = (BigDecimal)map.get("preTotal");
			objeto.setPreTotal(preTotal.toString());
			BigDecimal preCatalogo = (BigDecimal)map.get("preCatalogo");			
			objeto.setValorPrecCatalogo(preCatalogo.toString());
			objeto.setCodigoPeriodo((String)map.get("codPeriodo"));
			resultado.add(objeto);
		}
		return resultado; 
	}
	
	/**
	 * Cargar detalle pedido.
	 *
	 * @return the list
	 */
	public List cargarDetallePedido(){
		MantenimientoOCRCapturaPedidoService mantenimientoOCRCapturaPedidoService = (MantenimientoOCRCapturaPedidoService)getBean("spusicc.pedidos.mantenimientoOCRCapturaPedidoService");
		MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
    	Map criteria = new HashMap();
    	
    	criteria.put("codigoPais",  f.getCodigoPais());
    	criteria.put("codigoPeriodo", f.getPeriodo());
    	criteria.put("codigoConsultora", f.getCodigoConsultora());

    	List lista = mantenimientoOCRCapturaPedidoService.getListaDetallePedido(criteria);
    	
    	String totalPedido = mantenimientoOCRCapturaPedidoService.getSumaTotalPedidoListaDetallePedido(criteria);
    	f.setTotalPedido(totalPedido);
    	
    	return lista;
    }
	
	/**
	 * Cargar monto minimo.
	 *
	 * @param codigoConsultora the codigo consultora
	 */
	protected void cargarMontoMinimo(String codigoConsultora){
		//carga Monto minimo		
		MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		String cargaMontoMinimo = ajax.getMontoMinimoConsultora(f.getCodigoConsultora());
		f.setMontoMinimo(cargaMontoMinimo);		
	}
	
	/**
	 * Metodo que permite verificar el valor de codigo de consultora.s
	 *
	 * @return true, if successful
	 */
	protected boolean existeConsultora(){
		boolean rpta= false;
		AjaxService ajax = (AjaxService)getBean("ajaxService");		
		MantenimientoOCRCapturaPedidosForm busquedaForm = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;		
		LabelDatosConsultoraValue[] lbldata = ajax.getCabeceraConsultoraSimple(busquedaForm.getCodigoPais(), busquedaForm.getCodigoConsultora());
		if(lbldata!=null && lbldata.length>0){
			rpta = true;
		}
		return rpta;
	}
	
	/**
	 * Existe pedido consultora.
	 *
	 * @return the string
	 */
	protected String existePedidoConsultora(){
		MantenimientoOCRCapturaPedidosForm busquedaForm = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");			
		return ajax.getExistePedidoConsultora(busquedaForm.getCodigoPais(), 
				busquedaForm.getCodigoConsultora(), 
				busquedaForm.getCodigoPeriodo(), 
				busquedaForm.getNumLote());
	}
	
	/**
	 * Buscar consultora.
	 */
	protected void buscarConsultora(){
		
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		
		MantenimientoOCRCapturaPedidosForm busquedaForm = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
		
		String consultora = ajax.getConsultoraExistenteByCriteria(busquedaForm.getCodigoPais(),
																	busquedaForm.getPeriodo(),
																	busquedaForm.getCodigoConsultora(),
																	busquedaForm.getFechaFacturacion());
				
		
		LabelDatosConsultoraValue[] lbldata = ajax.getCabeceraConsultoraSimple(busquedaForm.getCodigoPais(), busquedaForm.getCodigoConsultora());
		
		if(lbldata!=null && lbldata.length>0){
			busquedaForm.setZona(((LabelDatosConsultoraValue)lbldata[0]).getZona());
			busquedaForm.setRegion(((LabelDatosConsultoraValue)lbldata[0]).getRegion());
			busquedaForm.setIndicadorActiva(((LabelDatosConsultoraValue)lbldata[0]).getIndicadorActiva());
			busquedaForm.setEstadoConsultora(((LabelDatosConsultoraValue)lbldata[0]).getEstadoConsultora());
			busquedaForm.setIndicadorBloqueo(((LabelDatosConsultoraValue)lbldata[0]).getIndicadorBloqueo());
			busquedaForm.setNombreConsultora(((LabelDatosConsultoraValue)lbldata[0]).getNombreConsultora());
			busquedaForm.setNumLote(((LabelDatosConsultoraValue)lbldata[0]).getNumLote()==null?Constants.PED_LOTE_FIJO_DIGIT_PEDIDOS:((LabelDatosConsultoraValue)lbldata[0]).getNumLote());
			busquedaForm.setNumPedidosRegistrados(((LabelDatosConsultoraValue)lbldata[0]).getNumeroPedidosRegistrado());
			this.cabeceraDetalle = new ArrayList();
			
		}
			
	}
	
	/**
	 * Metodo que se encarga de verificar si la consultora se encuentra bloqueada.
	 *
	 * @return the int
	 */
	public int existeBloqueoOnline(){
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		MantenimientoOCRCapturaPedidosForm busquedaForm = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;		
		Integer bloqueoOnlIne = 0;
		bloqueoOnlIne = ajax.getBloqueoOnline(busquedaForm.getCodigoPais(), 
				busquedaForm.getCodigoConsultora(), 
				busquedaForm.getPeriodo());
		return bloqueoOnlIne.intValue();
	}
	
	/**
	 * Listar detalle pedido.
	 */
	public void listarDetallePedido(){
		this.cerrarDialogoDetallePedido();		
	}	
	
	/**
	 * Mostrar dialogo detalle pedido.
	 */
	public void mostrarDialogoDetallePedido(){
		this.getRequestContext().execute("PF('dlgDetallePedido').show()");
	}
	
	/**
	 * Cerrar dialogo detalle pedido.
	 */
	public void cerrarDialogoDetallePedido(){
		this.getRequestContext().execute("PF('dlgDetallePedido').hide()");
	}
	
	/**
	 * Mostrar dialogo comun.
	 */
	public void mostrarDialogoComun(){
		this.getRequestContext().execute("PF('dlgComun').show()");
	}
	
	/**
	 * Abrir dialogo local.
	 */
	public void abrirDialogoLocal(){
		this.getRequestContext().execute("PF('dlgMonto').show()");
	}
		
	/**
	 * Cerrar dialogo local.
	 */
	public void cerrarDialogoLocal(){
		this.getRequestContext().execute("PF('dlgMonto').hide()");
	}
	
	/**
	 * Abrir dialogo local1.
	 */
	public void abrirDialogoControl(){
		this.getRequestContext().execute("PF('confirmacionControl').show()");
	}
		
	/**
	 * Cerrar dialogo local1.
	 */
	public void cerrarDialogoControl(){
		this.getRequestContext().execute("PF('confirmacionControl').hide()");
	}
	
	
	/**
	 * Mostrar dialogo delete.
	 *
	 * @param e the e
	 */
	public void mostrarDialogoDelete(ActionEvent e){
		this.mensajeLocal = this.getResourceMessage("mensaje.confirm.eliminarPedidos");
		this.getRequestContext().execute("PF('dlgDelete').show()");
	}
	
	/**
	 * Cerrar dialogo delete.
	 */
	public void cerrarDialogoDelete(){	
		this.getRequestContext().execute("PF('dlgDelete').hide()");
	}
	
	public void abrirDialogoControlProl(){
		this.getRequestContext().execute("PF('dlgProl').show()");
	}
	
	public void cerrarDialogoControlProl(){
		this.getRequestContext().execute("PF('dlgProl').hide()");
	}
	
	/**
	 * Guardar items.
	 */
	public void guardarItems(){	
		if (log.isDebugEnabled()) {
			log.debug("Entering 'guardarItem' method");
		}
		
		MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)formBusqueda;
		
		String[] arrCodigosEliminar = {""};
		
		LabelDatosConsultoraValue objDatosConsultora= new LabelDatosConsultoraValue();
		//seteo los datos de la cabecera de la consultora
		objDatosConsultora.setPeriodoFacturacion(f.getPeriodo());
	    objDatosConsultora.setNumeroPedidosRegistrado(f.getTxtnumPedidos());
	    objDatosConsultora.setCodigoConsultora(f.getCodigoConsultora());
	    objDatosConsultora.setNombreConsultora(f.getNombreConsultora());
	    objDatosConsultora.setTelefono(f.getTelefono());
	    objDatosConsultora.setEstatus(f.getEstatus());
	    objDatosConsultora.setBloqueado(f.getChkBloqueado());
	    objDatosConsultora.setPrimerPedido(f.getPrimerPedido());
	    objDatosConsultora.setUltimoPedido(f.getUltimoPedido());
	    objDatosConsultora.setRegion(f.getRegion());
	    objDatosConsultora.setZona(f.getCodZona());
	    objDatosConsultora.setFechaFacturacion(f.getFechaFacturacion());
	    objDatosConsultora.setTotalUnidades(f.getTotalUnid());
	    objDatosConsultora.setCodPais(f.getCodigoPais());
	    objDatosConsultora.setNumLote(f.getNumLote());
	    objDatosConsultora.setCodRegion(f.getCodRegion());
	    objDatosConsultora.setCodZona(f.getCodZona());
	    ArrayList objListaPedidos = new ArrayList();
	    if(!cabeceraDetalle.isEmpty()){
		    if(cabeceraDetalle.size()>0){
		    	for (CabeceraDetalleTO elemento : this.getCabeceraDetalle()) {
		    		LabelPedidosValue objPedido= new LabelPedidosValue();
	    	        objPedido.setCodigoVta(elemento.getCodigoVenta());
	    	        objPedido.setUnidades(elemento.getUnidDemanda());
	    	        objPedido.setCodPais(f.getCodigoPais());
	    	        objPedido.setCodPeriodo(f.getPeriodo());
	    	        objPedido.setCodCliente(f.getCodigoConsultora());
	    	        objPedido.setFechaSolicitud(f.getFechaFacturacion());
	    	        objPedido.setNumPosicion(String.valueOf(cabeceraDetalle.indexOf(elemento) + 1));
	    	        objPedido.setNumLote(f.getNumLote());
	    	        objListaPedidos.add(objPedido);
				}
		    	
		    	MantenimientoOCRCapturaPedidoService service = (MantenimientoOCRCapturaPedidoService)getBean("spusicc.pedidos.mantenimientoOCRCapturaPedidoService");
			    Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
	    	    Map filter = new HashMap();
	    	    filter.put("codPais", f.getCodigoPais());
	    	    filter.put("codCliente", f.getCodigoConsultora());
	    	    filter.put("periodo", f.getPeriodo());
	    	    filter.put("numeroLote", f.getNumLote());
	    	    
	    	    service.insertarPedido(objDatosConsultora, objListaPedidos,	usuario, filter, arrCodigosEliminar);
	    	    despuesGuardar();
		    }
	    }
	}
	
	/**
	 * Despues guardar.
	 */
	public void despuesGuardar(){
		this.addInfo("Info: ", this.getResourceMessage("mantenimientoOCRCargaPedidosForm.msj.registrar"));
		this.limpieza();
		this.valorFocus = "1";
		this.habdesCodCon = false;
		this.listaDetallePedido = new ArrayList<CabeceraDetalleTO>();
		this.detallePedidoDataModel = new PedidoDataModel(this.getListaDetallePedido());
	}
	
	/**
	 * Antes guardar.
	 *
	 * @param e the e
	 */
	public void verificaMontoMinimo(ActionEvent e){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'antesGuardar' method");
		}
		if(!this.cabeceraDetalle.isEmpty()){
			if(this.cabeceraDetalle.size()>0){
				if(validarMontoMinimo()){
					this.mensajeLocal = this.getResourceMessage("procesoUsuario.gestionPedidos.ingresoPedidos.confirmacionItem");
					this.abrirDialogoLocal();
				}else{
					this.verificaBloqueoConsultora();
				}
			}
		}
	}
	
	public void verificaBloqueoConsultora(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'verificaBloqueConsultora' method");
		}
		this.cerrarDialogoLocal();
		if(validacionBloqueo()){
			this.verificaIndicadorHiperConsulta();
		}else{
			this.setKeyMensajeAlertaDefault("mensaje.consultora.bloqueada");
			this.mostrarDialogoGeneral();
		}
	}
	
	public void verificaIndicadorHiperConsulta(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'verificaIndicadorHiperConsulta' method");
		}
				
		if(validaIndicadorHiperConsulta()){
			this.mensajeLocal = this.getResourceMessage("mantenimientoOCRCapturaPedidosForm.mensaje.popup.guardar");
			this.abrirDialogoControlProl();
		}else{
			this.mensajeLocal = this.getResourceMessage("confirm.execute.process");
			this.abrirDialogoControl();
		}
	}
		
	/**
	 * Valida control ejcucion.
	 */
	public void validaControlEjcucion(){
		this.cerrarDialogoControl();		
		this.guardarItems();
	}
	
	/**
	 * Valida indicador hiper consulta.
	 */
	public boolean validaIndicadorHiperConsulta(){
		this.cerrarDialogoLocal();
		MantenimientoOCRCapturaPedidosForm busquedaForm = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
		return busquedaForm.getIndicadorHiperConsulta().equals("S")?true:false;
	}
	
	
	public void ejecutarPorIndicadorHiperConsulta(){
		MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
		f.setCodigoConsultora2(f.getCodigoConsultora());
		if(this.getProl()==1){
			ejecutaPROL(null);
		}else{
			guardarItems();
		}
	}
	
	/**
	 * Validacion bloqueo.
	 *
	 * @return true, if successful
	 */
	public boolean validacionBloqueo(){
		MantenimientoOCRCapturaPedidosForm busquedaForm = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
		boolean valor = false;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		if(StringUtils.isNotBlank(busquedaForm.getCodigoConsultora()) 
				&& StringUtils.isNotEmpty(busquedaForm.getCodigoConsultora())){
			String validacionBloqueo = ajax.validarBloqueoDigitacionPedidos(busquedaForm.getCodigoConsultora());
			valor = validacionBloqueo.equals("S") ? true : false;			
		}
		return valor;
	}
	
	/**
	 * Validar monto minimo.
	 *
	 * @return true, if successful
	 */
	public boolean validarMontoMinimo(){
		MantenimientoOCRCapturaPedidosForm busquedaForm = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
		double vMontoMin = 0.0d;
		double vTotalPedido = 0.0d;
		if(StringUtils.isNotBlank(busquedaForm.getMontoMinimo())
				&& StringUtils.isNotEmpty(busquedaForm.getMontoMinimo())
				&& StringUtils.isNotBlank(busquedaForm.getTotalPedido())
				&& StringUtils.isNotEmpty(busquedaForm.getTotalPedido())){
			vMontoMin = Double.parseDouble(busquedaForm.getMontoMinimo());
			vTotalPedido = Double.parseDouble(busquedaForm.getTotalPedido());
		}
		return  vMontoMin > vTotalPedido ? true : false;
	}
	
	/**
	 * Metodo que se encarga de elimnar los registros seleccionados.
	 */
	public void deleteItem(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deleteItem' method");
		}
		
		MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
		
		if(this.seleccionados == null){
			this.cerrarDialogoDelete();
		}else{		
			List<CabeceraDetalleTO> listaConvertida = new ArrayList<CabeceraDetalleTO>(Arrays.asList(this.getSeleccionados()));
			this.cabeceraDetalle.removeAll(listaConvertida);
			listaConvertida.clear();
			this.cerrarDialogoDelete();
			f.setTxtnumPedidos("");
			f.setTotalPedido("");
			f.setTotalUnid("");
		}
	}
	
	/**
	 * Eliminar item ld pedido.
	 *
	 * @param e the e
	 */
	public void eliminarItemLDPedido(ActionEvent e){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'eliminarItemListaDetallePedido' method");
		}
		if(this.seleccionadosDP == null){
			this.cerrarDialogoDelete();
		}else{
			MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)formBusqueda;
	    	MantenimientoOCRCapturaPedidoService service = (MantenimientoOCRCapturaPedidoService)getBean("spusicc.pedidos.mantenimientoOCRCapturaPedidoService");
	    	
	    	String[] items = new String[this.seleccionadosDP.length + 1];
	    	items[0] = "";
	    	int i = 0;
	    	for (Object elemento : this.seleccionadosDP) {
				CabeceraDetalleTO cabeceraDetalleTO = (CabeceraDetalleTO)elemento;
				StringBuilder cadena = new StringBuilder();
				cadena.append(cabeceraDetalleTO.getCodigoPais())
						.append("|")
						.append(cabeceraDetalleTO.getNumLote())
						.append("|")
						.append(cabeceraDetalleTO.getCodigoCliente())
						.append("|")
						.append(cabeceraDetalleTO.getCodigoPeriodo())
						.append("|")
						.append(cabeceraDetalleTO.getCodigoVenta());
				++i;
				items[i] = new String(cadena.toString());
			}
	    	
	    	service.deleteDetallePedidoOnLine(items);

	    	//se verifica si el pedido tiene detalles sino los tiene se elimina la cabecera
	    	Map filter = new HashMap();
	 	    filter.put("codigoPais", f.getCodigoPais());
	 	    filter.put("codigoConsultora", f.getCodigoConsultora());
	 	    filter.put("codigoPeriodo", f.getPeriodo());
	 	    filter.put("numLote", f.getNumLote());
	 	    String detalles = service.verificarDetallePedido(filter);
	 	    if (detalles.equals(Constants.NUMERO_CERO)){
	 	    	service.deleteCabeceraPedidoOnLine(filter);
	 	    }
	 	    
	 	    List lista = this.cargarDetallePedido();
	 	    this.listaDetallePedido = conversionLista(lista);
			this.detallePedidoDataModel = new PedidoDataModel(this.getListaDetallePedido());			
		}
	}
	
	
	/**
	 * On row select.
	 *
	 * @param e the e
	 */
	public void onRowSelect(ActionEvent e){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'onRowSelect' method");
		}
		this.accion = this.ACCION_MODIFICAR;
		if(this.getSeleccionados() != null){
			if(this.getSeleccionados().length != 1){
				this.mostrarDialogoComun();
				this.mensajeLocal = this.getResourceMessage("errors.select.unique.item");
			}else{
				CabeceraDetalleTO cabeceraDetalleTO = ((CabeceraDetalleTO[])this.getSeleccionados())[0];
				this.setCabeceraDetalleTO(cabeceraDetalleTO);
				this.valorFocus = "3";
//				this.ingresoCodigoVenta();
//				this.agregarPedidoDetalle();
			}
		}
	}
	
	public void ejecutaPROL(ActionEvent e){
		if(!this.getCabeceraDetalle().isEmpty()){
			if(this.getCabeceraDetalle().size()>0){
				if(existeConsultora()){
					this.prol = 1;
				}
				
			}else{
				this.mensajeLocal = this.getResourceMessage("mensaje.error.ingresoDetallePedido");
				this.mostrarDialogoComun();
				this.valorFocus = "2";
			}
		}
	}
	
	
	public void ejecutar()  throws Exception {
		log.debug("Entro AL ejecutarPROL");
    	MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)formBusqueda;
    	MantenimientoOCRCapturaPedidoService service = (MantenimientoOCRCapturaPedidoService)getBean("spusicc.pedidos.mantenimientoOCRCapturaPedidoService");
    	MantenimientoOCRCargaPedidoService service2 = (MantenimientoOCRCargaPedidoService) getBean("spusicc.pedidos.mantenimientoOCRCargaPedidoService");
    	ProcesoSTOExecutionService procesoSTOExecutionService =	(ProcesoSTOExecutionService) getBean("spusicc.procesoSTOExecutionService");
    	ProcesoSTOService procesoSTOService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");
    	ProcesoSTOLevantamientoErroresValidacionService serviceGestion = (ProcesoSTOLevantamientoErroresValidacionService) getBean("spusicc.procesoSTOLevantamientoErroresValidacionService");
    	ProcesoPEDService servicePed = (ProcesoPEDService) getBean("spusicc.procesoPEDService");
    	Map criteria = new HashMap();
    	Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
    	String codigoDocumento = Constants.STO_TIPO_DOCUMENTO_OCC;

    	//////////////////////////////////////////////////////////////////////
    	//Se inserta la cabecera y detalle del pedido
    	f.setCodigoConsultora(f.getCodigoConsultora2());
    	String[] arrCodigosEliminar = null;

    	if(f.getListaEliminar().length != 0){
    		arrCodigosEliminar = f.getListaEliminar()[0].split(",");
    	}

    	LabelDatosConsultoraValue objDatosConsultora= new LabelDatosConsultoraValue();
		//seteo los datos de la cabecera de la consultora
		objDatosConsultora.setPeriodoFacturacion(f.getPeriodo());
	    objDatosConsultora.setNumeroPedidosRegistrado(f.getTxtnumPedidos());
	    objDatosConsultora.setCodigoConsultora(f.getCodigoConsultora());
	    objDatosConsultora.setNombreConsultora(f.getNombreConsultora());
	    objDatosConsultora.setTelefono(f.getTelefono());
	    objDatosConsultora.setEstatus(f.getEstatus());
	    objDatosConsultora.setBloqueado(f.getChkBloqueado());
	    objDatosConsultora.setPrimerPedido(f.getPrimerPedido());
	    objDatosConsultora.setUltimoPedido(f.getUltimoPedido());
	    objDatosConsultora.setRegion(f.getRegion());
	    objDatosConsultora.setZona(f.getCodZona());
	    objDatosConsultora.setFechaFacturacion(f.getFechaFacturacion());
	    objDatosConsultora.setTotalUnidades(f.getTotalUnid());
	    objDatosConsultora.setCodPais(f.getCodigoPais());
	    objDatosConsultora.setNumLote(f.getNumLote());
	    objDatosConsultora.setCodRegion(f.getCodRegion());
	    objDatosConsultora.setCodZona(f.getCodZona());

		// Setea los datos de los detalles
		String[]objlistaCodigos   = f.getLabel();
		String[]objlistaUnidades  = f.getLabel4();
		int tamcodigos            = objlistaCodigos.length;
		ArrayList objListaPedidos = new ArrayList();
		int j = 0;
		String auxPosi = "";

		for (int i=0; i<tamcodigos-1; i++){
	    	String codigo      = objlistaCodigos[i+1];
	        String unidades    = objlistaUnidades[i+1];

	        if (!codigo.equals("")){
	        	auxPosi = String.valueOf(j+1);
	        	LabelPedidosValue objPedido = new LabelPedidosValue();
    	        objPedido.setCodigoVta(codigo);
    	        objPedido.setUnidades(unidades);
    	        objPedido.setCodPais(f.getCodigoPais());
    	        objPedido.setCodPeriodo(f.getPeriodo());
    	        objPedido.setCodCliente(f.getCodigoConsultora());
    	        objPedido.setFechaSolicitud(f.getFechaFacturacion());
    	        objPedido.setNumPosicion(auxPosi);
    	        objPedido.setNumLote(f.getNumLote());
    	        objListaPedidos.add(objPedido);
    	        j++;
	        }
	    }

		//********************************************************************
		// Se verifica si existe pedido para la consultora, Si existe se elimina el pedido
    	Map params = new HashMap();
    	String secuencia =  procesoSTOService.getSecuenciaConsultaDocumento();
    	params.put("codigoPais", f.getCodigoPais());
    	params.put("tipoDocumento", codigoDocumento);
    	params.put("secuencia", secuencia);
    	params.put("codigoOrigen", "OL");
    	params.put("codigoCliente", f.getCodigoConsultora());
    	params.put("codPeriodo",f.getPeriodo());
    	params.put("codigoPeriodo",f.getPeriodo());
    	/*params.put("fechaInicio","");
    	params.put("fechaFin","");
    	params.put("fechaInicioProceso","");
    	params.put("fechaFinProceso","");
    	params.put("fechaInicioProgramadaFacturacion","");
    	params.put("fechaFinProgramadaFacturacion","");
    	params.put("numLote","");
    	params.put("estadoGP1","");
    	params.put("estadoGP2","");
    	params.put("estadoGP3","");
    	params.put("estadoGP4","");
    	params.put("estadoGP5","");
    	params.put("estadoFacturadas","");
    	params.put("estadoError","");
    	params.put("estadoRechazadas","");
    	params.put("estadoSinValidar","");
    	params.put("oidClienteCarga","");
    	params.put("clienteList",new ArrayList());
    	params.put("regionList",new ArrayList());
		params.put("zonaList",new ArrayList());*/
		params.put("usuario",usuario);

		int existePedido = 0;
		existePedido = service.existePedido(params);
		
		if (existePedido > 0){
			List consultaValidacionList =  procesoSTOService.getConsultaPedidosOnlineList(params);
	    	
	    	if (consultaValidacionList.size() > 0){
	    		eliminarPedido(consultaValidacionList, params, f, procesoSTOExecutionService);
	    	}	
		}

    	//********************************************************************

	    service.insertarPedido(objDatosConsultora, objListaPedidos,	usuario, criteria, arrCodigosEliminar);
	    
	    //TODO  Buscar de donde esta jalando la fecha de facturacion en sesion en el ssicc, de momento por defecto =""	    	
	    //request.getSession().setAttribute("fecha_facturacion_cte", f.getFechaFacturacion());

	    //////////////////////////////////////////////////////////////////////
	    criteria.put("codigoPais", f.getCodigoPais());
    	criteria.put("codPais", f.getCodigoPais());
    	criteria.put("codCliente", f.getCodigoConsultora());
    	criteria.put("codigoPeriodo", f.getPeriodo());
    	criteria.put("numLote", f.getNumLote());
    	criteria.put("codigoDocumento", codigoDocumento);
    	criteria.put("codTipoDocu", codigoDocumento);
    	criteria.put("tipoDocumento",codigoDocumento);
    	criteria.put("usuario", usuario);
    	criteria.put("codigoUsuario", usuario.getLogin());
    	criteria.put("periodo", f.getPeriodo());
    	criteria.put("numeroLote", f.getNumLote());

    	//Se actualiza el indicador de envio OCS
    	service.actualizaIndicadorOCS(criteria);

    	//obteniendo el numero de lote actual de la campana
    	String numeroLote = service.getNumeroLoteByPk(criteria);
    	criteria.put("numLote", numeroLote);

    	//Obteniendo el numero de lote sto
    	String numLoteSTO = procesoSTOService.updateLoteSTO(new TipoDocumentoDigitadoPK(f.getCodigoPais(),Constants.STO_TIPO_DOCUMENTO_OCC));
    	criteria.put("numLoteSTO", numLoteSTO);
    	criteria.put("indOrigen","L");

    	//Consolidando los pedidos
    	//service2.executeEnviarDetallesDigitados(criteria);
    	service2.executeConsolidaPedidoOnline(criteria);
    	servicePed.executeConsolidadoPedidos(criteria);

    	//Se llama a STO
    	List lista = procesoSTOService.getDocumentoDigitadoPKByLote(criteria);
		List listaSTO = new ArrayList();

		for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
			DocumentoDigitadoPK documentoDigitadoPK = (DocumentoDigitadoPK) iterator.next();
   		 	GestionDocumento gestionDocumento = new GestionDocumento();
   		 	gestionDocumento.setCodigoPais(documentoDigitadoPK.getCodPais());
   		 	gestionDocumento.setNumeroDocumento(documentoDigitadoPK.getSecNumeDocu());
   		 	gestionDocumento.setLote(documentoDigitadoPK.getNumLote());
   		 	gestionDocumento.setDocumento(documentoDigitadoPK.getCodTipoDocu());
   		 	listaSTO.add(gestionDocumento);
 		}

    	AccionTipoDocumento accionTipoDocumento = new AccionTipoDocumento(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),codigoDocumento,Constants.STO_ACCI_VALI_ON_LINE);
    	criteria.put("accionTipoDocumento", accionTipoDocumento);

    	procesoSTOExecutionService.execute(accionTipoDocumento,criteria, listaSTO);

    	//Verificamos si  Existen Errores STO En caso existan los mostramos
    	criteria.put("tipoDocumento",codigoDocumento);
    	List listaErroresValidacion =  serviceGestion.getGestionDocumentoList(criteria);
		if (listaErroresValidacion.size()>0){
			//TODO
//			ActionErrors errors = new ActionErrors();
//			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("mantenimientoOCRCargaPedidosForm.errors.gestion"));
//			for (Iterator iterator = listaErroresValidacion.iterator(); iterator.hasNext();) {
//	    		 GestionDocumento gestionDocumento = (GestionDocumento) iterator.next();
//	    		 errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.detail",gestionDocumento.getDesValidacionLarga()));
//	  		}
//			saveErrors(session, errors);
			//return mapping.findForward("view");
		}

		
		//Se obtiene la fecha de facturacion
		String fecha = service.getFechaFacturacion(criteria);
		criteria.put("fechaProceso", fecha);

		//Se obtiene la secuencia del documento
		String secuenciaDoc = service.getNumeroSecuenciaDocumento(criteria);
		criteria.put("secNumDoc", secuenciaDoc);

		//Se obtiene el oid de la solicitud
		String oidSolicitud = service.getOidSolicitud(criteria);
		if (oidSolicitud != null){
			criteria.put("oidSolicitud", oidSolicitud);
		}
		else{
			criteria.put("oidSolicitud", "0");
		}

		//Se llama a Sicc
		service.executeEjecutarGP2(criteria);

		this.listaDetallePedido.clear();
		this.detallePedidoDataModel = new PedidoDataModel(this.listaDetallePedido);
		f.setIndicadorDatos("N");
    }

	
	private void eliminarPedido(List lista, Map params, MantenimientoOCRCapturaPedidosForm f, ProcesoSTOExecutionService service) throws Exception {
    	log.debug("Entro al Eliminar Pedido PROL");

    	String accion = "ELS";
    	String codPais = f.getCodigoPais();
    	String codTipoDocumento = Constants.STO_TIPO_DOCUMENTO_OCC;
    	params.put("codigoAccionEjecutada", Constants.STO_INDICADOR_ELIMINACION + accion);
    	params.put("codTipoDocu", codTipoDocumento);

    	AccionTipoDocumento accionTipoDocumento = new AccionTipoDocumento(codPais, codTipoDocumento, accion);
    	service.execute(accionTipoDocumento, params, lista);
	}
	
	public void retorna(ActionEvent event) throws IOException{
		if(log.isDebugEnabled()){
			log.debug("retorna");
		}
		
		FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/pages/scsicc/hip/consultaHIPDatosClienteForm.jsf");
	}
	
	/**
	 * ***************************************************************************
	 * 
	 * Inicio de metodos GET y SET
	 * 
	 * ***************************************************************************.
	 *
	 * @return the cabecera detalle to
	 */
	
	public CabeceraDetalleTO getCabeceraDetalleTO() {
		return cabeceraDetalleTO;
	}

	/**
	 * Sets the cabecera detalle to.
	 *
	 * @param cabeceraDetalleTO the new cabecera detalle to
	 */
	public void setCabeceraDetalleTO(CabeceraDetalleTO cabeceraDetalleTO) {
		this.cabeceraDetalleTO = cabeceraDetalleTO;
	}

	/**
	 * Gets the index.
	 *
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Sets the index.
	 *
	 * @param index the new index
	 */
	public void setIndex(int index) {
		this.index = index;
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
	 * Gets the lista campanhas.
	 *
	 * @return the lista campanhas
	 */
	public List getListaCampanhas() {
		return listaCampanhas;
	}

	/**
	 * Sets the lista campanhas.
	 *
	 * @param listaCampanhas the new lista campanhas
	 */
	public void setListaCampanhas(List listaCampanhas) {
		this.listaCampanhas = listaCampanhas;
	}

	/**
	 * Gets the popup hipcliente.
	 *
	 * @return the popup hipcliente
	 */
	public static String getPopupHipcliente() {
		return POPUP_OCRCLIENTE;
	}

	/**
	 * Gets the lista codigos matriz.
	 *
	 * @return the lista codigos matriz
	 */
	public List getListaCodigosMatriz() {
		return listaCodigosMatriz;
	}

	/**
	 * Sets the lista codigos matriz.
	 *
	 * @param listaCodigosMatriz the new lista codigos matriz
	 */
	public void setListaCodigosMatriz(List listaCodigosMatriz) {
		this.listaCodigosMatriz = listaCodigosMatriz;
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
	 * Gets the cabecera detalle.
	 *
	 * @return the cabecera detalle
	 */
	public List<CabeceraDetalleTO> getCabeceraDetalle() {
		return cabeceraDetalle;
	}

	/**
	 * Sets the cabecera detalle.
	 *
	 * @param cabeceraDetalle the new cabecera detalle
	 */
	public void setCabeceraDetalle(List<CabeceraDetalleTO> cabeceraDetalle) {
		this.cabeceraDetalle = cabeceraDetalle;
	}

	/**
	 * Gets the lista detalle pedido.
	 *
	 * @return the lista detalle pedido
	 */
	public List<CabeceraDetalleTO> getListaDetallePedido() {
		return listaDetallePedido;
	}

	/**
	 * Sets the lista detalle pedido.
	 *
	 * @param listaDetallePedido the new lista detalle pedido
	 */
	public void setListaDetallePedido(List<CabeceraDetalleTO> listaDetallePedido) {
		this.listaDetallePedido = listaDetallePedido;
	}

	/**
	 * Gets the pedido data model.
	 *
	 * @return the pedido data model
	 */
	public PedidoDataModel getPedidoDataModel() {
		return pedidoDataModel;
	}

	/**
	 * Sets the pedido data model.
	 *
	 * @param pedidoDataModel the new pedido data model
	 */
	public void setPedidoDataModel(PedidoDataModel pedidoDataModel) {
		this.pedidoDataModel = pedidoDataModel;
	}
	
	/**
	 * Gets the valor focus.
	 *
	 * @return the valor focus
	 */
	public String getValorFocus() {
		return valorFocus;
	}

	/**
	 * Sets the valor focus.
	 *
	 * @param valorFocus the new valor focus
	 */
	public void setValorFocus(String valorFocus) {
		this.valorFocus = valorFocus;
	}

	/**
	 * Checks if is valor alerta.
	 *
	 * @return true, if is valor alerta
	 */
	public boolean isValorAlerta() {
		return valorAlerta;
	}

	/**
	 * Sets the valor alerta.
	 *
	 * @param valorAlerta the new valor alerta
	 */
	public void setValorAlerta(boolean valorAlerta) {
		this.valorAlerta = valorAlerta;
	}	

	/**
	 * Gets the mensaje local.
	 *
	 * @return the mensaje local
	 */
	public String getMensajeLocal() {
		return mensajeLocal;
	}

	/**
	 * Sets the mensaje local.
	 *
	 * @param mensajeLocal the new mensaje local
	 */
	public void setMensajeLocal(String mensajeLocal) {
		this.mensajeLocal = mensajeLocal;
	}

	/**
	 * Checks if is dlg local.
	 *
	 * @return true, if is dlg local
	 */
	public boolean isDlgLocal() {
		return dlgLocal;
	}

	/**
	 * Sets the dlg local.
	 *
	 * @param dlgLocal the new dlg local
	 */
	public void setDlgLocal(boolean dlgLocal) {
		this.dlgLocal = dlgLocal;
	}

	/**
	 * Checks if is propagacion.
	 *
	 * @return true, if is propagacion
	 */
	public boolean isPropagacion() {
		return propagacion;
	}

	/**
	 * Sets the propagacion.
	 *
	 * @param propagacion the new propagacion
	 */
	public void setPropagacion(boolean propagacion) {
		this.propagacion = propagacion;
	}

	/**
	 * Gets the colspan detalle.
	 *
	 * @return the colspan detalle
	 */
	public int getColspanDetalle() {
		return colspanDetalle;
	}

	/**
	 * Sets the colspan detalle.
	 *
	 * @param colspanDetalle the new colspan detalle
	 */
	public void setColspanDetalle(int colspanDetalle) {
		this.colspanDetalle = colspanDetalle;
	}

	/**
	 * Gets the seleccionados dp.
	 *
	 * @return the seleccionados dp
	 */
	public CabeceraDetalleTO[] getSeleccionadosDP() {
		return seleccionadosDP;
	}

	/**
	 * Sets the seleccionados dp.
	 *
	 * @param seleccionadosDP the new seleccionados dp
	 */
	public void setSeleccionadosDP(CabeceraDetalleTO[] seleccionadosDP) {
		this.seleccionadosDP = seleccionadosDP;
	}

	/**
	 * Gets the detalle pedido data model.
	 *
	 * @return the detalle pedido data model
	 */
	public PedidoDataModel getDetallePedidoDataModel() {
		return detallePedidoDataModel;
	}

	/**
	 * Sets the detalle pedido data model.
	 *
	 * @param detallePedidoDataModel the new detalle pedido data model
	 */
	public void setDetallePedidoDataModel(PedidoDataModel detallePedidoDataModel) {
		this.detallePedidoDataModel = detallePedidoDataModel;
	}

	/**
	 * Checks if is mostrar panel detalle pedido.
	 *
	 * @return true, if is mostrar panel detalle pedido
	 */
	public boolean isMostrarPanelDetallePedido() {
		return mostrarPanelDetallePedido;
	}

	/**
	 * Sets the mostrar panel detalle pedido.
	 *
	 * @param mostrarPanelDetallePedido the new mostrar panel detalle pedido
	 */
	public void setMostrarPanelDetallePedido(boolean mostrarPanelDetallePedido) {
		this.mostrarPanelDetallePedido = mostrarPanelDetallePedido;
	}

	/**
	 * Gets the seleccionados.
	 *
	 * @return the seleccionados
	 */
	public CabeceraDetalleTO[] getSeleccionados() {
		return seleccionados;
	}

	/**
	 * Sets the seleccionados.
	 *
	 * @param seleccionados the new seleccionados
	 */
	public void setSeleccionados(CabeceraDetalleTO[] seleccionados) {
		this.seleccionados = seleccionados;
	}

	/**
	 * Checks if is habdes cod con.
	 *
	 * @return true, if is habdes cod con
	 */
	public boolean isHabdesCodCon() {
		return habdesCodCon;
	}

	/**
	 * Sets the habdes cod con.
	 *
	 * @param habdesCodCon the new habdes cod con
	 */
	public void setHabdesCodCon(boolean habdesCodCon) {
		this.habdesCodCon = habdesCodCon;
	}

	public int getProl() {
		return prol;
	}

	public void setProl(int prol) {
		this.prol = prol;
	}

	public boolean isMostrarBotonEjecutarProl() {
		return mostrarBotonEjecutarProl;
	}

	public void setMostrarBotonEjecutarProl(boolean mostrarBotonEjecutarProl) {
		this.mostrarBotonEjecutarProl = mostrarBotonEjecutarProl;
	}

	public boolean isMostrarPopupOCRCliente() {
		return mostrarPopupOCRCliente;
	}

	public void setMostrarPopupOCRCliente(boolean mostrarPopupOCRCliente) {
		this.mostrarPopupOCRCliente = mostrarPopupOCRCliente;
	}

	public String getEnlaceOrigen() {
		return enlaceOrigen;
	}

	public void setEnlaceOrigen(String enlaceOrigen) {
		this.enlaceOrigen = enlaceOrigen;
	}

	public boolean isMostrarBotonRetorno() {
		return mostrarBotonRetorno;
	}

	public void setMostrarBotonRetorno(boolean mostrarBotonRetorno) {
		this.mostrarBotonRetorno = mostrarBotonRetorno;
	}
}