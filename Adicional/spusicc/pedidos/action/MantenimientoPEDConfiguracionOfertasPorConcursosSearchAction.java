package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resources;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDConfiguracionOfertasPorConcursosService;
import biz.belcorp.ssicc.web.form.SistemaForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.form.ConsultaBASProcesoBatchHistoSearchForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDConfiguracionOfertasPorConcursosForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDConfiguracionOfertasPorConcursosRegalosForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDConfiguracionOfertasPorConcursosSearchForm;

@SessionScoped
@ManagedBean
public class MantenimientoPEDConfiguracionOfertasPorConcursosSearchAction
		extends BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4141286895794280982L;
	private List pedOfertaConcursosCatalogoList;
	private List pedOfertaConcursosList;
	private List siccCatalogoList;
	private List pedOfertasPorConcursosRangosList;
	private List pedOfertasPorConcursosCriteriosList;
	private String codigoPais;
	private String codigoLogin;
	private String PED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_PRECIOS = Constants.PED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_PRECIOS;
	private String PED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_CONCURSOS = Constants.PED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_CONCURSOS;
	private String PED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_UNIDADES = Constants.PED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_UNIDADES;
	private String PED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_MONTO = Constants.PED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_MONTO;
	private String PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO = Constants.PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO;
	private String PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO = Constants.PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO;
	private DataTableModel comDetalleTableModel;
	private Object beanRegistroDetalle;
	private DataTableModel comDetalleTableModelCriterio;
	private Object beanRegistroDetalleCriterio;
	private boolean rangoboolean;
	private boolean indicadorExclusionRangoBoolean;
	private boolean esRango;
	private List pedOfertasPorConcursosCriteriosProductosList;

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoPEDConfiguracionOfertasPorConcursosList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoPEDConfiguracionOfertasPorConcursosForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoPEDConfiguracionOfertasPorConcursosSearchForm form = new MantenimientoPEDConfiguracionOfertasPorConcursosSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoPEDConfiguracionOfertasPorConcursosSearchForm searchForm = (MantenimientoPEDConfiguracionOfertasPorConcursosSearchForm) this.formBusqueda;
		searchForm.setCodigoPais(codigoPais);
		List lista = buscar(searchForm);
		this.pedOfertaConcursosList = lista;
		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoPEDConfiguracionOfertasPorConcursosService service = (MantenimientoPEDConfiguracionOfertasPorConcursosService) getBean("spusicc.pedidos.mantenimientoPEDConfiguracionOfertasPorConcursosService");
		Map data = (Map) this.beanRegistroSeleccionado;
		String oidOferta = (String) data.get("oidOferta");
		service.removeOfertaConcursos(oidOferta);
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoPEDConfiguracionOfertasPorConcursosService service = (MantenimientoPEDConfiguracionOfertasPorConcursosService) getBean("spusicc.pedidos.mantenimientoPEDConfiguracionOfertasPorConcursosService");
		MantenimientoPEDConfiguracionOfertasPorConcursosForm editForm = (MantenimientoPEDConfiguracionOfertasPorConcursosForm) this.formMantenimiento;
		Map params = BeanUtils.describe(editForm);
		params.put("codigoUsuario", codigoLogin);

		if (editForm.isNewRecord()) {
			service.insertOfertaConcursos(params);
			editForm.setOidOferta(MapUtils.getString(params, "oidOferta"));
			editForm.setNewRecord(false);
		} else {
			// service.updateOfertaConcursos(params);
			// messages.add(
			// ActionMessages.GLOBAL_MESSAGE,
			// new ActionMessage(
			// "mantenimientoPEDConfiguracionOfertasPorConcursosForm.oferta.actualizada"));
		}

		editForm.setMostrarDetalles(true);
		indicadorExclusionRangoBoolean = true;

		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoPEDConfiguracionOfertasPorConcursosForm form = new MantenimientoPEDConfiguracionOfertasPorConcursosForm();
		form.setCodigoPais(codigoPais);

		if (this.accion.equals(this.ACCION_NUEVO)) {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'setAddAttributes' method");
			}

			MantenimientoPEDConfiguracionOfertasPorConcursosService service = (MantenimientoPEDConfiguracionOfertasPorConcursosService) getBean("spusicc.pedidos.mantenimientoPEDConfiguracionOfertasPorConcursosService");

			Map params = BeanUtils.describe(form);

			// Moneda
			Map moneda = service.getMoneda(params);
			form.setNumeroDecimales(MapUtils.getString(moneda,
					"numeroDecimales"));

			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
			this.siccCatalogoList = reporteService.getListaGenerico(
					"getOidCatalogoProductos", null);

			Map criteriaPeriodo = new HashMap();
			criteriaPeriodo.put("codigoPais", codigoPais);
			criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																			// Campanha
																			// Activa
			criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																				// Campanha
																				// activa
																				// q
																				// se
																				// procesa
																				// actualmente

			MantenimientoOCRPedidoControlFacturacionService controlFactService = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			PedidoControlFacturacion controlFacturacion = controlFactService
					.getControlFacturacionById(criteriaPeriodo);
			form.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
			form.setNewRecord(true);
		}

		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoPEDConfiguracionOfertasPorConcursosSearchForm searchForm = (MantenimientoPEDConfiguracionOfertasPorConcursosSearchForm) this.formBusqueda;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		this.pedOfertaConcursosCatalogoList = reporteService.getListaGenerico(
				"getCatalogoProductos", null);
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		codigoPais = pais.getCodigo();
		codigoLogin = usuario.getLogin();
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", codigoPais);

		// Indica Campanha Activa
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
		// Indica Campanha activa q se procesa actualmente
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO);

		MantenimientoOCRPedidoControlFacturacionService controlFactService = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = controlFactService
				.getControlFacturacionById(criteriaPeriodo);

		searchForm.setCodigoPais(codigoPais);
		searchForm.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		searchForm.setNumeroPagina(null);
		searchForm.setCodigoCatalogo(null);

		List lista = buscar(searchForm);
		this.pedOfertaConcursosList = lista;
		this.listaBusqueda = lista;
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
		this.mostrarBotonConsultar = false;

	}

	private List buscar(
			MantenimientoPEDConfiguracionOfertasPorConcursosSearchForm form)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		MantenimientoPEDConfiguracionOfertasPorConcursosService service = (MantenimientoPEDConfiguracionOfertasPorConcursosService) getBean("spusicc.pedidos.mantenimientoPEDConfiguracionOfertasPorConcursosService");

		Map params = BeanUtils.describe(form);

		List lista = (List) service.getOfertaConcursosList(params);

		return lista;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction
	 * #devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoPEDConfiguracionOfertasPorConcursosForm form = (MantenimientoPEDConfiguracionOfertasPorConcursosForm) this.formMantenimiento;
		boolean isNew = form.isNewRecord();
		if (isNew) {
			return "mantenimientoPEDConfiguracionOfertasPorConcursosForm.oferta.grabada";
		} else {
			return "mantenimientoPEDConfiguracionOfertasPorConcursosForm.oferta.actualizada";
		}
	}

	public String setValidarConfirmar(String accion) {
		MantenimientoPEDConfiguracionOfertasPorConcursosForm form = (MantenimientoPEDConfiguracionOfertasPorConcursosForm) this.formMantenimiento;

		if (accion.equals("INSERTAR_DETALLE")) {
			int rangoInferior = Integer.parseInt(form.getRangoInferior());
			int rangoSuperior = Integer.parseInt(form.getRangoSuperior());
			if (rangoInferior > rangoSuperior)
				return "Rango Superior debe ser mayor o igual que el Rango Inferior";
		}
		return null;
	}

	/**
	 * @param actionEvent
	 */
	public void agregarrango(ActionEvent actionEvent) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'agregarrango' method");
		}

		MantenimientoPEDConfiguracionOfertasPorConcursosForm editForm = (MantenimientoPEDConfiguracionOfertasPorConcursosForm) this.formMantenimiento;
		MantenimientoPEDConfiguracionOfertasPorConcursosService service = (MantenimientoPEDConfiguracionOfertasPorConcursosService) getBean("spusicc.pedidos.mantenimientoPEDConfiguracionOfertasPorConcursosService");

		List rangoList = this.pedOfertasPorConcursosRangosList;

		if (rangoList == null)
			rangoList = new ArrayList();

		Map rango = new HashMap();

		rango.put("oidOferta", editForm.getOidOferta());
		rango.put("rangoInferior", editForm.getRangoInferior());
		rango.put("rangoSuperior", editForm.getRangoSuperior());
		rango.put("precioUnitario", editForm.getPrecioUnitario());
		rango.put("codigoUsuario", codigoLogin);

		// Insertamos en BD
		service.insertRangoOfertaConcursos(rango);
		//

		rangoList.add(rango);

		this.pedOfertasPorConcursosRangosList = rangoList;
		this.comDetalleTableModel = new DataTableModel(
				this.pedOfertasPorConcursosRangosList);
		editForm.setRangoInferior("");
		editForm.setRangoSuperior("");
		editForm.setPrecioUnitario("");
		editForm.setMostrarDetalles(true);

	}

	public void eliminarrango(ActionEvent actionEvent) {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'eliminarrango' method");
		}

		MantenimientoPEDConfiguracionOfertasPorConcursosService service = (MantenimientoPEDConfiguracionOfertasPorConcursosService) getBean("spusicc.pedidos.mantenimientoPEDConfiguracionOfertasPorConcursosService");
		Map data = (Map) this.beanRegistroDetalle;

		String oidRango = (String) data.get("oidRango");

		if (oidRango != null) {

			// Eliminados de BD
			service.removeRangoOfertaConcursos(oidRango);

		}
		List rangoList = this.pedOfertasPorConcursosRangosList;
		rangoList.remove(data);

	}

	public void habilitarPrecio(ValueChangeEvent val) {

		MantenimientoPEDConfiguracionOfertasPorConcursosForm editForm = (MantenimientoPEDConfiguracionOfertasPorConcursosForm) this.formMantenimiento;
		if (pedOfertasPorConcursosRangosList != null) {
			this.addError(
					"Error",
					this.getResourceMessage("mantenimientoPEDConfiguracionOfertasPorConcursosForm.codigoTipoPrograma.error"));
			String valor = (String) val.getOldValue();
			editForm.setCodigoTipoPrograma(valor);
		} else {
			String valor2 = (String) val.getNewValue();
			if (valor2.equals("1")) {
				rangoboolean = false;
			} else {
				rangoboolean = true;
			}
		}

	}

	/**
	 * 
	 * @param actionEvent
	 */
	public void openPopupGratis(ActionEvent actionEvent) {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String urlPopup = externalContext.getRequestParameterMap().get(
				"urlPopup");
		String oidOferta = externalContext.getRequestParameterMap().get(
				"oidOferta");
		String oidRango = externalContext.getRequestParameterMap().get(
				"oidRango");

		this.setUrlPopupConsulta(urlPopup);

		MantenimientoPEDConfiguracionOfertasPorConcursosRegalosForm form = (MantenimientoPEDConfiguracionOfertasPorConcursosRegalosForm) this.formMantenimiento;

		if (StringUtils.isNotBlank(oidOferta)) {
			form.setOidOferta(oidOferta);
		}

		String popup = "PF('popupConsultaForm').show()";
		this.getRequestContext().execute(popup);

	}

	public void loadTipoRango(ValueChangeEvent val) {
		String valor = (String) val.getNewValue();
		if (valor.equals("R")) {
			setEsRango(true);
		} else
			setEsRango(false);
	}

	public void agregarcriterio(ActionEvent val) {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'agregarcriterio' method");
		}

		MantenimientoPEDConfiguracionOfertasPorConcursosForm editForm = (MantenimientoPEDConfiguracionOfertasPorConcursosForm) this.formMantenimiento;
		MantenimientoPEDConfiguracionOfertasPorConcursosService service = (MantenimientoPEDConfiguracionOfertasPorConcursosService) getBean("spusicc.pedidos.mantenimientoPEDConfiguracionOfertasPorConcursosService");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		List criterioList = this.pedOfertasPorConcursosCriteriosList;

		if (criterioList == null)
			criterioList = new ArrayList();

		if (indicadorExclusionRangoBoolean == true)
			editForm.setIndicadorExclusionRango(Constants.NUMERO_UNO);
		else
			editForm.setIndicadorExclusionRango(Constants.NUMERO_CERO);
		Map criterio = new HashMap();
		criterio.put("oidOferta", editForm.getOidOferta());
		criterio.put("oidCatalogoCriterio", editForm.getOidCatalogoCriterio());
		criterio.put("codigoTipoRango", editForm.getCodigoTipoRango());
		criterio.put("numeroPaginaInicial", editForm.getNumeroPaginaInicial());
		criterio.put("numeroPaginaFinal", editForm.getNumeroPaginaFinal());
		criterio.put("indicadorExclusionRango", StringUtils.isBlank(editForm
				.getIndicadorExclusionRango()) ? Constants.NUMERO_CERO
				: editForm.getIndicadorExclusionRango());
		criterio.put("codigoUsuario", codigoLogin);

		String oidProducto = null;
		if (StringUtils.isNotBlank(editForm.getCodigoProducto())) {

			// validamos si el producto ingresado existe
			oidProducto = ajaxService.validarCodigoSAP(codigoPais,
					editForm.getCodigoProducto());

			if (StringUtils.isBlank(oidProducto)) {
				this.addError("Error", "El producto no existe");
			}
		}

		criterio.put("oidProducto", oidProducto);

		// Insertamos en BD
		service.insertCriterioOfertaConcursos(criterio);
		//

		criterioList = service.getCriterioOfertaConcursosList(editForm
				.getOidOferta());
		
		this.pedOfertasPorConcursosCriteriosList = criterioList;
		this.comDetalleTableModelCriterio = new DataTableModel(
				this.pedOfertasPorConcursosCriteriosList);

		// Invocamos al proceso de calculo de productos
		executeCalcularProductos(editForm, service);
		//

		editForm.setOidCatalogoCriterio("");
		editForm.setCodigoTipoRango("");
		editForm.setNumeroPaginaInicial("");
		editForm.setNumeroPaginaFinal("");
		editForm.setCodigoProducto("");
		indicadorExclusionRangoBoolean = false;
		// editForm.setIndicadorExclusionRango(Constants.NUMERO_CERO);

	}

	public void eliminarcriterio(ActionEvent val) {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'eliminarcriterio' method");
		}

		MantenimientoPEDConfiguracionOfertasPorConcursosForm editForm = (MantenimientoPEDConfiguracionOfertasPorConcursosForm) this.formMantenimiento;
		MantenimientoPEDConfiguracionOfertasPorConcursosService service = (MantenimientoPEDConfiguracionOfertasPorConcursosService) getBean("spusicc.pedidos.mantenimientoPEDConfiguracionOfertasPorConcursosService");
		Map data = (Map) this.beanRegistroDetalleCriterio;
		String oidCriterio = (String) data.get("oidCriterio");
		if (oidCriterio != null) {

			// Eliminados de BD
			service.removeCriterioOfertaConcursos(oidCriterio);
			//

		}

		List criterioList = this.pedOfertasPorConcursosCriteriosList;
		criterioList.remove(data);

		// Invocamos al proceso de calculo de productos
		executeCalcularProductos(editForm, service);
		//

	}

	private void executeCalcularProductos(
			MantenimientoPEDConfiguracionOfertasPorConcursosForm editForm,
			MantenimientoPEDConfiguracionOfertasPorConcursosService service) {
		Map params = new HashMap();
		params.put("oidOferta", editForm.getOidOferta());
		params.put("codigoPeriodo", editForm.getCodigoPeriodo());
		params.put("codigoUsuario", codigoLogin);

		List productoList = service.executeCalcularProductos(params);
		this.pedOfertasPorConcursosCriteriosList = productoList;
	}

	/**
	 * @return the pedOfertaConcursosCatalogoList
	 */
	public List getPedOfertaConcursosCatalogoList() {
		return pedOfertaConcursosCatalogoList;
	}

	/**
	 * @param pedOfertaConcursosCatalogoList
	 *            the pedOfertaConcursosCatalogoList to set
	 */
	public void setPedOfertaConcursosCatalogoList(
			List pedOfertaConcursosCatalogoList) {
		this.pedOfertaConcursosCatalogoList = pedOfertaConcursosCatalogoList;
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
	 * @return the pedOfertaConcursosList
	 */
	public List getPedOfertaConcursosList() {
		return pedOfertaConcursosList;
	}

	/**
	 * @param pedOfertaConcursosList
	 *            the pedOfertaConcursosList to set
	 */
	public void setPedOfertaConcursosList(List pedOfertaConcursosList) {
		this.pedOfertaConcursosList = pedOfertaConcursosList;
	}

	/**
	 * @return the siccCatalogoList
	 */
	public List getSiccCatalogoList() {
		return siccCatalogoList;
	}

	/**
	 * @param siccCatalogoList
	 *            the siccCatalogoList to set
	 */
	public void setSiccCatalogoList(List siccCatalogoList) {
		this.siccCatalogoList = siccCatalogoList;
	}

	/**
	 * @return the pED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_PRECIOS
	 */
	public String getPED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_PRECIOS() {
		return PED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_PRECIOS;
	}

	/**
	 * @param pED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_PRECIOS
	 *            the pED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_PRECIOS to
	 *            set
	 */
	public void setPED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_PRECIOS(
			String pED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_PRECIOS) {
		PED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_PRECIOS = pED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_PRECIOS;
	}

	/**
	 * @return the pED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_CONCURSOS
	 */
	public String getPED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_CONCURSOS() {
		return PED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_CONCURSOS;
	}

	/**
	 * @param pED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_CONCURSOS
	 *            the pED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_CONCURSOS
	 *            to set
	 */
	public void setPED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_CONCURSOS(
			String pED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_CONCURSOS) {
		PED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_CONCURSOS = pED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_CONCURSOS;
	}

	/**
	 * @return the pED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_UNIDADES
	 */
	public String getPED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_UNIDADES() {
		return PED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_UNIDADES;
	}

	/**
	 * @param pED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_UNIDADES
	 *            the pED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_UNIDADES to set
	 */
	public void setPED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_UNIDADES(
			String pED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_UNIDADES) {
		PED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_UNIDADES = pED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_UNIDADES;
	}

	/**
	 * @return the pED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_MONTO
	 */
	public String getPED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_MONTO() {
		return PED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_MONTO;
	}

	/**
	 * @param pED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_MONTO
	 *            the pED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_MONTO to set
	 */
	public void setPED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_MONTO(
			String pED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_MONTO) {
		PED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_MONTO = pED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_MONTO;
	}

	/**
	 * @return the comDetalleTableModel
	 */
	public DataTableModel getComDetalleTableModel() {
		return comDetalleTableModel;
	}

	/**
	 * @param comDetalleTableModel
	 *            the comDetalleTableModel to set
	 */
	public void setComDetalleTableModel(DataTableModel comDetalleTableModel) {
		this.comDetalleTableModel = comDetalleTableModel;
	}

	/**
	 * @return the beanRegistroDetalle
	 */
	public Object getBeanRegistroDetalle() {
		return beanRegistroDetalle;
	}

	/**
	 * @param beanRegistroDetalle
	 *            the beanRegistroDetalle to set
	 */
	public void setBeanRegistroDetalle(Object beanRegistroDetalle) {
		this.beanRegistroDetalle = beanRegistroDetalle;
	}

	/**
	 * @return the pedOfertasPorConcursosRangosList
	 */
	public List getPedOfertasPorConcursosRangosList() {
		return pedOfertasPorConcursosRangosList;
	}

	/**
	 * @param pedOfertasPorConcursosRangosList
	 *            the pedOfertasPorConcursosRangosList to set
	 */
	public void setPedOfertasPorConcursosRangosList(
			List pedOfertasPorConcursosRangosList) {
		this.pedOfertasPorConcursosRangosList = pedOfertasPorConcursosRangosList;
	}

	/**
	 * @return the rangoboolean
	 */
	public boolean isRangoboolean() {
		return rangoboolean;
	}

	/**
	 * @param rangoboolean
	 *            the rangoboolean to set
	 */
	public void setRangoboolean(boolean rangoboolean) {
		this.rangoboolean = rangoboolean;
	}

	/**
	 * @return the pED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO
	 */
	public String getPED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO() {
		return PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO;
	}

	/**
	 * @param pED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO
	 *            the pED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO to set
	 */
	public void setPED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO(
			String pED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO) {
		PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO = pED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO;
	}

	/**
	 * @return the pED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO
	 */
	public String getPED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO() {
		return PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO;
	}

	/**
	 * @param pED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO
	 *            the pED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO to set
	 */
	public void setPED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO(
			String pED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO) {
		PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO = pED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO;
	}

	/**
	 * @return the indicadorExclusionRangoBoolean
	 */
	public boolean isIndicadorExclusionRangoBoolean() {
		return indicadorExclusionRangoBoolean;
	}

	/**
	 * @param indicadorExclusionRangoBoolean
	 *            the indicadorExclusionRangoBoolean to set
	 */
	public void setIndicadorExclusionRangoBoolean(
			boolean indicadorExclusionRangoBoolean) {
		this.indicadorExclusionRangoBoolean = indicadorExclusionRangoBoolean;
	}

	/**
	 * @return the esRango
	 */
	public boolean isEsRango() {
		return esRango;
	}

	/**
	 * @param esRango
	 *            the esRango to set
	 */
	public void setEsRango(boolean esRango) {
		this.esRango = esRango;
	}

	/**
	 * @return the pedOfertasPorConcursosCriteriosList
	 */
	public List getPedOfertasPorConcursosCriteriosList() {
		return pedOfertasPorConcursosCriteriosList;
	}

	/**
	 * @param pedOfertasPorConcursosCriteriosList
	 *            the pedOfertasPorConcursosCriteriosList to set
	 */
	public void setPedOfertasPorConcursosCriteriosList(
			List pedOfertasPorConcursosCriteriosList) {
		this.pedOfertasPorConcursosCriteriosList = pedOfertasPorConcursosCriteriosList;
	}

	/**
	 * @return the codigoLogin
	 */
	public String getCodigoLogin() {
		return codigoLogin;
	}

	/**
	 * @param codigoLogin
	 *            the codigoLogin to set
	 */
	public void setCodigoLogin(String codigoLogin) {
		this.codigoLogin = codigoLogin;
	}

	/**
	 * @return the comDetalleTableModelCriterio
	 */
	public DataTableModel getComDetalleTableModelCriterio() {
		return comDetalleTableModelCriterio;
	}

	/**
	 * @param comDetalleTableModelCriterio
	 *            the comDetalleTableModelCriterio to set
	 */
	public void setComDetalleTableModelCriterio(
			DataTableModel comDetalleTableModelCriterio) {
		this.comDetalleTableModelCriterio = comDetalleTableModelCriterio;
	}

	/**
	 * @return the beanRegistroDetalleCriterio
	 */
	public Object getBeanRegistroDetalleCriterio() {
		return beanRegistroDetalleCriterio;
	}

	/**
	 * @param beanRegistroDetalleCriterio
	 *            the beanRegistroDetalleCriterio to set
	 */
	public void setBeanRegistroDetalleCriterio(
			Object beanRegistroDetalleCriterio) {
		this.beanRegistroDetalleCriterio = beanRegistroDetalleCriterio;
	}

}
