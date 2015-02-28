package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIOutput;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionDetalle;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.ProductoAgregacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRReemplazosService;
import biz.belcorp.ssicc.web.form.SistemaForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMPorcentajeComisionForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoOCRReemplazosForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoOCRReemplazosSearchForm;

@SessionScoped
@ManagedBean
public class MantenimientoOCRReemplazosSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8454161656873648560L;
	private List ocrReemplazosList;
	private List ocrTipoReemplazoList;
	private List ocrTipoClienteList;
	private LabelValue[] ocrSubtipoiClienteList = {};
	private LabelValue[] ocrTipoClasificacionList = {};
	private LabelValue[] ocrClasificacionList = {};
	private String mostrarTipoReemplazo;
	private List ocrRegionList;
	private LabelValue[] ocrZonaList = {};
	private String codigoPais;
	private String codigoUser;
	private String OCR_BLOQUEO_CONTROL_SI = Constants.OCR_BLOQUEO_CONTROL_SI;
	private String OCR_BLOQUEO_CONTROL_NO = Constants.OCR_BLOQUEO_CONTROL_NO;
	private boolean indicadorMensajeBoolean;

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoOCRReemplazosList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoOCRReemplazosForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoOCRReemplazosSearchForm form = new MantenimientoOCRReemplazosSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("MantenimientoOCRReemplazosSearchAction - setFindAttributes");
		MantenimientoOCRReemplazosSearchForm f = (MantenimientoOCRReemplazosSearchForm) this.formBusqueda;
		List reemplazosList = new ArrayList();
		reemplazosList = buscarLista(f);
		this.ocrReemplazosList = reemplazosList;
		return reemplazosList;
	}

	public List buscarLista(MantenimientoOCRReemplazosSearchForm f) {
		MantenimientoOCRReemplazosService service = (MantenimientoOCRReemplazosService) getBean("spusicc.pedidos.mantenimientoOCRReemplazosService");
		Map criteria = new HashMap();
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("codigoVentaPrincipal", f.getCodigoVentaPrincipal());
		criteria.put("codigoVentaReemplazo", f.getCodigoVentaReemplazo());
		criteria.put("indicadorActivo", f.getIndicadorActivo());
		criteria.put("oidTipoReemplazo", f.getOidTipoReemplazo());
		List list = new ArrayList();
		list = service.getReemplazosByPeriodo(criteria);
		return list;
	}

	public String setValidarConfirmar(String accion) {
		Map data = (Map) this.beanRegistroSeleccionado;

		if (accion.equals("ACTIVAR")) {

			if (beanRegistroSeleccionado == null || listaBusqueda == null)
				return this
						.getResourceMessage("mantenimientoOCRReemplazosSearchForm.msg.errorSinRegistros");
			String indicadorActivo = (String) data.get("indicadorActivo");
			if (indicadorActivo.equals(Constants.NUMERO_UNO))
				return this
						.getResourceMessage("mantenimientoOCRReemplazosSearchForm.estado.activo.existe");
		} else if (accion.equals("DESACTIVAR")) {

			if (beanRegistroSeleccionado == null || listaBusqueda == null)
				return this
						.getResourceMessage("mantenimientoOCRReemplazosSearchForm.msg.errorSinRegistros");
			String indicadorActivo = (String) data.get("indicadorActivo");
			if (indicadorActivo.equals(Constants.NUMERO_CERO))
				return this
						.getResourceMessage("mantenimientoOCRReemplazosSearchForm.estado.desactivo.existe");
		}
		return null;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		Map data = (Map) this.beanRegistroSeleccionado;
		String id = (String) data.get("oidMatrizReemp");
		Map criteria = new HashMap();
		criteria.put("oidMatrizReemp", id);
		MantenimientoOCRReemplazosService service = (MantenimientoOCRReemplazosService) getBean("spusicc.pedidos.mantenimientoOCRReemplazosService");
		service.deleteReemplazos(criteria);
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		boolean bGrabar = false;
		log.debug("MantenimientoOCRReemplazosAction - setAddAttributes");
		MantenimientoOCRReemplazosForm f = (MantenimientoOCRReemplazosForm) this.formMantenimiento;
		Map criteria = new HashMap();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		criteria.put("oidProductoPrincipal", f.getOidProductoPrincipal());
		criteria.put("oidProductoReemplazo", f.getOidProductoReemplazo());
		if (indicadorMensajeBoolean == true)
			criteria.put("indicadorMensaje", Constants.NUMERO_UNO);
		else
			criteria.put("indicadorMensaje", Constants.NUMERO_CERO);
		criteria.put("tipoReemplazo", f.getTipoReemplazo());
		criteria.put("oidTipoReemplazo", f.getOidTipoReemplazo());
		criteria.put("codigoPais", codigoPais);
		criteria.put("region", f.getRegion());
		criteria.put("zona", f.getZona());
		criteria.put("tipoCliente", f.getTipoCliente());
		criteria.put("subtipoCliente", f.getSubtipoCliente());
		criteria.put("tipoClasificacion", f.getTipoClasificacion());
		criteria.put("clasificacion", f.getClasificacion());
		criteria.put("usuario", usuario.getLogin());

		// fechaActivacion
		if (f.getFechaActivacionD() != null) {
			f.setFechaActivacion(DateUtil.convertDateToString(f
					.getFechaActivacionD()));
		}
		criteria.put("fechaActivacion", f.getFechaActivacion());

		MantenimientoOCRReemplazosService service = (MantenimientoOCRReemplazosService) getBean("spusicc.pedidos.mantenimientoOCRReemplazosService");
		try {
			bGrabar = true;
			service.insertOCRReemplazos(criteria);
		} catch (Exception e) {
			bGrabar = false;
			throw new Exception(
					this.getResourceMessage("mensaje.reemplazo.no.satisfactorio"));
		}
		return bGrabar;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoOCRReemplazosForm f = new MantenimientoOCRReemplazosForm();

		if (this.accion.equals(this.ACCION_NUEVO)) {

			log.debug("MantenimientoOCRReemplazosAction - setViewAttributes");
			Map criteria = new HashMap();
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			criteria.put("codigoPais", codigoPais);
			criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																	// Campanha
																	// Activa
			criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																		// Campanha
																		// activa
																		// q se
																		// procesa
																		// actualmente
			MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			PedidoControlFacturacion controlFacturacion = serviceFact
					.getControlFacturacionById(criteria);
			// Setea la campaña con el valor del Archivo de control
			f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
			// Carga el combo Tipo cliente
			InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
			this.ocrTipoReemplazoList = interfazSiCCService.getTipoReemplazo();
			this.ocrTipoClienteList = interfazSiCCService
					.getTiposClientesByCodigoISOOID(usuario.getIdioma()
							.getCodigoISO());

			// Carga el combo de Regiones
			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
			this.ocrRegionList = reporteService.getListaGenerico(
					"getRegionesByPais", criteria);

			// Obtenes valor del parametro para el tipo de reemplazo
			GenericoService genericoService1 = (GenericoService) getBean("genericoService");

			ParametroPais parametroPais1 = new ParametroPais();
			parametroPais1.setCodigoPais(codigoPais);
			parametroPais1.setCodigoSistema(Constants.OCR_CODIGO_SISTEMA);
			parametroPais1
					.setCodigoParametro(Constants.OCR_CODIGO_PARAMETRO_TIPO_REEM);
			parametroPais1
					.setNombreParametro(Constants.OCR_NOMBRE_PARAMETRO_MOSTRAR_TIPO_REEM);

			List lstParametros1 = genericoService1
					.getParametrosPais(parametroPais1);

			String mostrarTipoReem = Constants.NO;

			if (lstParametros1 != null && lstParametros1.size() > 0) {
				ParametroPais ps = (ParametroPais) lstParametros1.get(0);
				mostrarTipoReem = ps.getValorParametro();
			}
			this.mostrarTipoReemplazo = mostrarTipoReem;
			this.indicadorMensajeBoolean = false;
		}
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		codigoUser = this.mPantallaPrincipalBean.getCurrentUser().getCodigo();
		codigoPais = this.mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo();

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																// Campanha
																// Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																	// Campanha
																	// activa q
																	// se
																	// procesa
																	// actualmente

		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceFact
				.getControlFacturacionById(criteria);
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.ocrTipoReemplazoList = interfazSiCCService.getTipoReemplazo();

		MantenimientoOCRReemplazosSearchForm f = (MantenimientoOCRReemplazosSearchForm) this.formBusqueda;
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setCodigoVentaPrincipal("");
		f.setCodigoVentaReemplazo("");
		this.mostrarBotonModificar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarMantenimientoEnPopup = false;
	}

	public void activar(ActionEvent actionEvent) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'activar' method");
		}

		Map data = (Map) this.beanRegistroSeleccionado;
		String oidMatrizReemp = (String) data.get("oidMatrizReemp");
		Map criteria = new HashMap();
		criteria.put("oidMatrizReemp", oidMatrizReemp);
		criteria.put("indicadorActivo", Constants.NRO_UNO);
		criteria.put("accion", Constants.OCR_REEMPLAZOS_ACCION_ACTIVAR);
		criteria.put("usuario", codigoUser);
		MantenimientoOCRReemplazosService service = (MantenimientoOCRReemplazosService) getBean("spusicc.pedidos.mantenimientoOCRReemplazosService");
		service.updateReemplazos(criteria);
		//
		MantenimientoOCRReemplazosSearchForm f = (MantenimientoOCRReemplazosSearchForm) this.formBusqueda;
		f.setIndicadorActivo(Constants.NRO_UNO);
		List reemplazosList = new ArrayList();
		reemplazosList = buscarLista(f);
		this.listaBusqueda = reemplazosList;
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);

	}

	public void desactivar(ActionEvent actionEvent) {

		Map data = (Map) this.beanRegistroSeleccionado;
		String oidMatrizReemp = (String) data.get("oidMatrizReemp");
		Map criteria = new HashMap();
		criteria.put("oidMatrizReemp", oidMatrizReemp);
		criteria.put("indicadorActivo", Constants.NRO_CERO);
		criteria.put("accion", Constants.OCR_REEMPLAZOS_ACCION_DESACTIVAR);
		criteria.put("usuario", codigoUser);
		MantenimientoOCRReemplazosService service = (MantenimientoOCRReemplazosService) getBean("spusicc.pedidos.mantenimientoOCRReemplazosService");
		service.updateReemplazos(criteria);
		//
		MantenimientoOCRReemplazosSearchForm f = (MantenimientoOCRReemplazosSearchForm) this.formBusqueda;
		f.setIndicadorActivo(Constants.NRO_CERO);
		List reemplazosList = new ArrayList();
		reemplazosList = buscarLista(f);
		this.listaBusqueda = reemplazosList;
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
	}

	public void loadcodigoVentaPrincipal(AjaxBehaviorEvent e) {
		String valor = (String) ((UIOutput) e.getSource()).getValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		MantenimientoOCRReemplazosForm f = (MantenimientoOCRReemplazosForm) this.formMantenimiento;
		String codPro = "";
		String descri = "";
		if (valor.length() == 5) {
			ProductoAgregacion pro = new ProductoAgregacion();
			pro = ajax.getInformacionCUV(f.getCodigoPeriodo(), valor, "P");
			if (pro == null) {
				f.setCodigoVentaPrincipal("");
				this.addError("Error", this.getResourceMessage("mensaje.CUV.noExiste"));
			} else {
				if (!StringUtils.isBlank(pro.getCodigoProducto()))
					codPro = pro.getCodigoProducto();
				if (!StringUtils.isBlank(pro.getDescripcionProducto()))
					descri = pro.getDescripcionProducto();
				f.setCodigoSAPPrincipal(codPro);
				f.setDescripcionPrincipal(descri);
				f.setOidProductoPrincipal(pro.getId());
				if (pro.getIndicadorExisteReemplazo() != "0") {
					String ventana = "PF('confirmDialogValidar_confirmationDialogConfirmarSalir').show()";
					this.getRequestContext().execute(ventana);
				}

			}

		}

	}

	public void cancelarCodigoVentaPrincipal(ActionEvent actionEvent) {
		MantenimientoOCRReemplazosForm f = (MantenimientoOCRReemplazosForm) this.formMantenimiento;
		f.setCodigoSAPPrincipal("");
		f.setDescripcionPrincipal("");
		f.setCodigoVentaPrincipal("");
	}

	public void aceptarCodigoVentaPrincipal(ActionEvent actionEvent) {

	}

	public void loadcodigoVentaReemplazo(AjaxBehaviorEvent e) {
		String valor = (String) ((UIOutput) e.getSource()).getValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		MantenimientoOCRReemplazosForm f = (MantenimientoOCRReemplazosForm) this.formMantenimiento;
		String codPro = "";
		String descri = "";
		if (valor.length() == 5) {
			ProductoAgregacion pro = new ProductoAgregacion();
			pro = ajax.getInformacionCUV(f.getCodigoPeriodo(), valor, "R");
			if (!StringUtils.isBlank(pro.getCodigoProducto()))
				codPro = pro.getCodigoProducto();
			if (!StringUtils.isBlank(pro.getDescripcionProducto()))
				descri = pro.getDescripcionProducto();
			f.setOidProductoReemplazo(pro.getId());
		}
		f.setCodigoSAPReemplazo(codPro);
		f.setDescripcionReemplazo(descri);
	}

	/**
	 * Metodo para obtener Lista de SubClientes
	 * 
	 * @param val
	 */
	public void loadSubTiposClientes(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadSubTiposClientes");
		}
		MantenimientoOCRReemplazosForm f = (MantenimientoOCRReemplazosForm) this.formMantenimiento;

		String valor = (String) val.getNewValue();
		if (valor != null) {
			ArrayList<String> myList = new ArrayList<String>(
					Arrays.asList(valor));
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			this.ocrSubtipoiClienteList = ajaxService
					.getSubTiposClientesPorPaisTipoClientesOID(codigoPais,
							myList);
		} else {
			ocrSubtipoiClienteList = null;
			ocrTipoClasificacionList = null;
			ocrClasificacionList = null;
			f.setSubtipoCliente(null);
			f.setTipoClasificacion(null);
			f.setClasificacion(null);
		}

	}

	public void loadTiposClasificaciones(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadTiposClasificaciones");
		}
		MantenimientoOCRReemplazosForm f = (MantenimientoOCRReemplazosForm) this.formMantenimiento;

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String valor = (String) val.getNewValue();
		if (valor != null) {
			ArrayList<String> myList = new ArrayList<String>(
					Arrays.asList(valor));
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			this.ocrTipoClasificacionList = ajaxService
					.getTiposClasificacionesByCriteriaMultipleOID(usuario
							.getIdioma().getCodigoISO(), f.getTipoCliente(),
							myList);
		} else {
			ocrTipoClasificacionList = null;
			ocrClasificacionList = null;
			f.setTipoClasificacion(null);
			f.setClasificacion(null);
		}

	}

	public void loadClasificaciones(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadTiposClasificaciones");
		}
		MantenimientoOCRReemplazosForm f = (MantenimientoOCRReemplazosForm) this.formMantenimiento;

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String valor = (String) val.getNewValue();
		String subTipoCliente = f.getSubtipoCliente();
		ArrayList<String> subTipoClienteArray = new ArrayList<String>(
				Arrays.asList(subTipoCliente));
		ArrayList<String> codigoTipoClasificacion = new ArrayList<String>(
				Arrays.asList(valor));
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		this.ocrClasificacionList = ajaxService
				.getClasificacionesByCriteriaMultipleOID(usuario.getIdioma()
						.getCodigoISO(), f.getTipoCliente(),
						subTipoClienteArray, codigoTipoClasificacion);
	}

	public void loadZona(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadZona");
		}
		String valor = (String) val.getNewValue();
		if (StringUtils.isBlank(valor)) {

		}
		String[] dato = { valor };
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		this.ocrZonaList = ajaxService.getZonasMultipleByPaisMarcaCanalRegion(
				codigoPais, "T", "VD", dato, "");
	}

	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoOCRReemplazosForm sistemaForm = (MantenimientoOCRReemplazosForm) this.formMantenimiento;
		boolean isNew = sistemaForm.isNewRecord();
		if (isNew) {
			return "mensaje.reemplazo.satisfactorio";
		} else {
			return "sistema.updated";
		}
	}

	/**
	 * @return the ocrReemplazosList
	 */
	public List getOcrReemplazosList() {
		return ocrReemplazosList;
	}

	/**
	 * @param ocrReemplazosList
	 *            the ocrReemplazosList to set
	 */
	public void setOcrReemplazosList(List ocrReemplazosList) {
		this.ocrReemplazosList = ocrReemplazosList;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoUser
	 */
	public String getCodigoUser() {
		return codigoUser;
	}

	/**
	 * @param codigoUser
	 *            the codigoUser to set
	 */
	public void setCodigoUser(String codigoUser) {
		this.codigoUser = codigoUser;
	}

	/**
	 * @return the ocrTipoReemplazoList
	 */
	public List getOcrTipoReemplazoList() {
		return ocrTipoReemplazoList;
	}

	/**
	 * @param ocrTipoReemplazoList
	 *            the ocrTipoReemplazoList to set
	 */
	public void setOcrTipoReemplazoList(List ocrTipoReemplazoList) {
		this.ocrTipoReemplazoList = ocrTipoReemplazoList;
	}

	/**
	 * @return the oCR_BLOQUEO_CONTROL_SI
	 */
	public String getOCR_BLOQUEO_CONTROL_SI() {
		return OCR_BLOQUEO_CONTROL_SI;
	}

	/**
	 * @param oCR_BLOQUEO_CONTROL_SI
	 *            the oCR_BLOQUEO_CONTROL_SI to set
	 */
	public void setOCR_BLOQUEO_CONTROL_SI(String oCR_BLOQUEO_CONTROL_SI) {
		OCR_BLOQUEO_CONTROL_SI = oCR_BLOQUEO_CONTROL_SI;
	}

	/**
	 * @return the oCR_BLOQUEO_CONTROL_NO
	 */
	public String getOCR_BLOQUEO_CONTROL_NO() {
		return OCR_BLOQUEO_CONTROL_NO;
	}

	/**
	 * @param oCR_BLOQUEO_CONTROL_NO
	 *            the oCR_BLOQUEO_CONTROL_NO to set
	 */
	public void setOCR_BLOQUEO_CONTROL_NO(String oCR_BLOQUEO_CONTROL_NO) {
		OCR_BLOQUEO_CONTROL_NO = oCR_BLOQUEO_CONTROL_NO;
	}

	/**
	 * @return the ocrTipoClienteList
	 */
	public List getOcrTipoClienteList() {
		return ocrTipoClienteList;
	}

	/**
	 * @param ocrTipoClienteList
	 *            the ocrTipoClienteList to set
	 */
	public void setOcrTipoClienteList(List ocrTipoClienteList) {
		this.ocrTipoClienteList = ocrTipoClienteList;
	}

	/**
	 * @return the mostrarTipoReemplazo
	 */
	public String getMostrarTipoReemplazo() {
		return mostrarTipoReemplazo;
	}

	/**
	 * @param mostrarTipoReemplazo
	 *            the mostrarTipoReemplazo to set
	 */
	public void setMostrarTipoReemplazo(String mostrarTipoReemplazo) {
		this.mostrarTipoReemplazo = mostrarTipoReemplazo;
	}

	/**
	 * @return the ocrRegionList
	 */
	public List getOcrRegionList() {
		return ocrRegionList;
	}

	/**
	 * @param ocrRegionList
	 *            the ocrRegionList to set
	 */
	public void setOcrRegionList(List ocrRegionList) {
		this.ocrRegionList = ocrRegionList;
	}

	/**
	 * @return the indicadorMensajeBoolean
	 */
	public boolean isIndicadorMensajeBoolean() {
		return indicadorMensajeBoolean;
	}

	/**
	 * @return the ocrSubtipoiClienteList
	 */
	public LabelValue[] getOcrSubtipoiClienteList() {
		return ocrSubtipoiClienteList;
	}

	/**
	 * @param ocrSubtipoiClienteList
	 *            the ocrSubtipoiClienteList to set
	 */
	public void setOcrSubtipoiClienteList(LabelValue[] ocrSubtipoiClienteList) {
		this.ocrSubtipoiClienteList = ocrSubtipoiClienteList;
	}

	/**
	 * @return the ocrTipoClasificacionList
	 */
	public LabelValue[] getOcrTipoClasificacionList() {
		return ocrTipoClasificacionList;
	}

	/**
	 * @param ocrTipoClasificacionList
	 *            the ocrTipoClasificacionList to set
	 */
	public void setOcrTipoClasificacionList(
			LabelValue[] ocrTipoClasificacionList) {
		this.ocrTipoClasificacionList = ocrTipoClasificacionList;
	}

	/**
	 * @return the ocrClasificacionList
	 */
	public LabelValue[] getOcrClasificacionList() {
		return ocrClasificacionList;
	}

	/**
	 * @param ocrClasificacionList
	 *            the ocrClasificacionList to set
	 */
	public void setOcrClasificacionList(LabelValue[] ocrClasificacionList) {
		this.ocrClasificacionList = ocrClasificacionList;
	}

	/**
	 * @return the ocrZonaList
	 */
	public LabelValue[] getOcrZonaList() {
		return ocrZonaList;
	}

	/**
	 * @param ocrZonaList
	 *            the ocrZonaList to set
	 */
	public void setOcrZonaList(LabelValue[] ocrZonaList) {
		this.ocrZonaList = ocrZonaList;
	}

	/**
	 * @param indicadorMensajeBoolean
	 *            the indicadorMensajeBoolean to set
	 */
	public void setIndicadorMensajeBoolean(boolean indicadorMensajeBoolean) {
		this.indicadorMensajeBoolean = indicadorMensajeBoolean;
	}

}
