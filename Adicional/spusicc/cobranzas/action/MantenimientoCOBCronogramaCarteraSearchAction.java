package biz.belcorp.ssicc.web.spusicc.cobranzas.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.CronogramaCartera;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.MantenimientoCOBCronogramaCarteraService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.MantenimientoCOBCronogramaCarteraForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.MantenimientoCOBCronogramaCarteraSearchForm;

/**
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@ManagedBean
@SessionScoped
public class MantenimientoCOBCronogramaCarteraSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4340883988374367410L;
	private List cobCronogramaCarteraList;
	private List siccSociedadList;
	private LabelValue[] siccEtapaDeudaList = {};
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private boolean fechaGeneracionCartera;
	private boolean fechaCierreCartera;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction
	 * #getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoCOBCronogramaCarteraForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction
	 * #devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCOBCronogramaCarteraSearchForm searchForm = new MantenimientoCOBCronogramaCarteraSearchForm();
		return searchForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction
	 * #setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'find' method");
		}

		MantenimientoCOBCronogramaCarteraSearchForm f = (MantenimientoCOBCronogramaCarteraSearchForm) this.formBusqueda;
		String fecha = "";
		if (f.getFechaGeneracionD() != null) {
			fecha = DateUtil.convertDateToString(f.getFechaGeneracionD());
		}

		MantenimientoCOBCronogramaCarteraService service = (MantenimientoCOBCronogramaCarteraService) getBean("spusicc.mantenimientoCOBCronogramaCarteraService");

		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoSociedad", f.getCodigoSociedad());
		criteria.put("codigoEtapaDeuda", f.getCodigoEtapaDeuda());
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("codigoRegion", f.getCodigoRegion());
		criteria.put("codigoZona", f.getCodigoZona());
		criteria.put("fechaGeneracion", fecha);

		List resultado = service.getCronogramaCarteraList(criteria);
		this.cobCronogramaCarteraList = resultado;

		if (log.isDebugEnabled()) {
			log.debug("JFA Return resultado");
		}

		return resultado;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction
	 * #setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes
	 * ()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonEliminar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonConsultar = false;

		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'view' method");
		}
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoCOBCronogramaCarteraSearchForm f = (MantenimientoCOBCronogramaCarteraSearchForm) this.formBusqueda;

		f.setCodigoPais(pais.getCodigo());
		this.siccSociedadList = service.getSociedadesByCodigoPais(pais
				.getCodigo());
		Base base = (Base) siccSociedadList.get(0);
		String codSociedad = base.getCodigo();
		this.siccEtapaDeudaList = ajax.getEtapasDeudaByPaisSociedad(
				pais.getCodigo(), codSociedad);
		String codEtapa = "";
		if (siccEtapaDeudaList != null)
			codEtapa = getSiccEtapaDeudaList()[0].getValue();
		this.siccRegionList = ajax.getRegionesByPaisSociedadEtapaDeuda(
				pais.getCodigo(), codSociedad, codEtapa);
		String codregion = "";
		if (siccRegionList != null)
			codregion = getSiccRegionList()[0].getValue();
		this.siccZonaList = ajax.getZonasByPaisSociedadEtapaDeudaRegion(
				pais.getCodigo(), codSociedad, codEtapa, codregion);

		if (log.isDebugEnabled()) {
			log.debug("JFA Todo Ok: Redireccionando");
		}

	}

	@Override
	protected String getSalirForward() {
		return "mantenimientoCOBCronogramaCarteraList";
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {

		MantenimientoCOBCronogramaCarteraForm f = (MantenimientoCOBCronogramaCarteraForm) this.formMantenimiento;

		MantenimientoCOBCronogramaCarteraService service = (MantenimientoCOBCronogramaCarteraService) getBean("spusicc.mantenimientoCOBCronogramaCarteraService");
		f.setFechaCierreCartera(DateUtil.convertDateToString(f
				.getFechaCierreCarteraD()));
		f.setFechaCompromisoPago(DateUtil.convertDateToString(f
				.getFechaCompromisoPagoD()));
		f.setFechaGeneracionCartera(DateUtil.convertDateToString(f
				.getFechaGeneracionCarteraD()));
		f.setFechaInicioGestion(DateUtil.convertDateToString(f
				.getFechaInicioGestionD()));
		try {
			CronogramaCartera cronogramaCartera = new CronogramaCartera();

			BeanUtils.copyProperties(cronogramaCartera, f);
			if (!f.isNewRecord()) {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA MODIFICACION");
				}

				Map params = BeanUtils.describe(cronogramaCartera);
				Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
				params.put("codigoUsuario", usuario.getLogin());
				params.put("tipoExcepcion", f.getTipoExcepcion());

				String tipoExcepcion = f.getTipoExcepcion();

				if (tipoExcepcion.equals("CIE")) {
					params.put("fechaExcepcion", f.getFechaCierreCartera());
				} else {
					params.put("fechaExcepcion", f.getFechaGeneracionCartera());

				}

				service.updateCronogramaCartera(params);
			}

		} catch (Exception e) {
			throw new Exception(this.getResourceMessage(
					"errors.invalid.description"));
		}
		return true;

		// SistemaForm sistemaForm = (SistemaForm) this.formMantenimiento;
		// boolean isNew = sistemaForm.isNewRecord();
		//
		// Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		//
		// SistemaService service = (SistemaService) this
		// .getBeanService("sisicc.sistemaService");
		//
		// Sistema sistema = new Sistema();
		// BeanUtils.copyProperties(sistema, sistemaForm);
		//
		// try {
		// if (this.accion.equals(this.ACCION_NUEVO)) {
		// service.insertSistema(sistema, usuario);
		// } else {
		// service.updateSistema(sistema, usuario);
		// }
		// } catch (InvalidIdentifierException iie) {
		// String codigo = iie.getIdentifier().toString();
		// throw new Exception(this.getResourceMessage("errors.invalid.id",
		// new Object[] { codigo }));
		//
		// } catch (InvalidDescriptionException ide) {
		// String descripcion = ide.getDescription();
		// throw new Exception(this.getResourceMessage(
		// "errors.invalid.description", new Object[] { descripcion }));
		// }

		// PRUEBAS FRAMEWORK INTERFACES
		/*
		 * BaseInterfazService pruebaService = (BaseInterfazService)
		 * this.getBeanService("sisicc.interfazXXXEnviarConcursoPremioService");
		 * pruebaService.executePruebaTransaccion();
		 */
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Map sistemabusqueda = (Map) this.beanRegistroSeleccionado;

		MantenimientoCOBCronogramaCarteraForm f = new MantenimientoCOBCronogramaCarteraForm();

		MantenimientoCOBCronogramaCarteraService service = (MantenimientoCOBCronogramaCarteraService) getBean("spusicc.mantenimientoCOBCronogramaCarteraService");

		CronogramaCartera cronogramaCartera = new CronogramaCartera();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoSociedad = (String) sistemabusqueda.get("codigoSociedad");
		String codigoEtapaDeuda = (String) sistemabusqueda
				.get("codigoEtapaDeuda");
		String codigoPeriodo = (String) sistemabusqueda.get("codigoPeriodo");
		String codigoRegion = (String) sistemabusqueda.get("codigoRegion");
		String codigoZona = (String) sistemabusqueda.get("codigoZona");
		Date fechaGeneracionCartera = (Date) sistemabusqueda
				.get("fechaGeneracionCartera");
		Date fechaInicioGestion = (Date) sistemabusqueda
				.get("fechaInicioGestion");
		Date fechaCompromisoPago = (Date) sistemabusqueda
				.get("fechaCompromisoPago");
		Date fechaCierreCartera = (Date) sistemabusqueda
				.get("fechaCierreCartera");

		cronogramaCartera.setCodigoPais(pais.getCodigo());
		cronogramaCartera.setCodigoSociedad(codigoSociedad);
		cronogramaCartera.setCodigoEtapaDeuda(codigoEtapaDeuda);
		cronogramaCartera.setCodigoPeriodo(codigoPeriodo);
		cronogramaCartera.setCodigoRegion(codigoRegion);
		cronogramaCartera.setCodigoZona(codigoZona);
		cronogramaCartera = (CronogramaCartera) service
				.getCronogramaCarteraById(cronogramaCartera);

		BeanUtils.copyProperties(f, cronogramaCartera);
		f.setFechaGeneracionCarteraD(fechaGeneracionCartera);
		f.setFechaInicioGestionD(fechaInicioGestion);
		f.setFechaCompromisoPagoD(fechaCompromisoPago);
		f.setFechaCierreCarteraD(fechaCierreCartera);
		this.fechaCierreCartera = true;
		this.fechaGeneracionCartera = false;
		return f;
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
		MantenimientoCOBCronogramaCarteraForm sistemaForm = (MantenimientoCOBCronogramaCarteraForm) this.formMantenimiento;
		boolean isNew = sistemaForm.isNewRecord();
		if (isNew) {
			return "mantenimientoCOBCronogramaCarteraList.updated";
		} else {
			return "mantenimientoCOBCronogramaCarteraList.updated";
		}
	}

	/**
	 * Metodo para obtener Lista de Etapa
	 * 
	 * @param val
	 */
	public void loadEtapas(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadEtapas");
		}
		String codSociedad = (String) val.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.siccEtapaDeudaList = ajax.getEtapasDeudaByPaisSociedad(
				pais.getCodigo(), codSociedad);
		String codEtapa = "";
		if (siccEtapaDeudaList != null)
			codEtapa = getSiccEtapaDeudaList()[0].getValue();
		this.siccRegionList = ajax.getRegionesByPaisSociedadEtapaDeuda(
				pais.getCodigo(), codSociedad, codEtapa);
		String codregion = "";
		if (siccRegionList != null)
			codregion = getSiccRegionList()[0].getValue();
		this.siccZonaList = ajax.getZonasByPaisSociedadEtapaDeudaRegion(
				pais.getCodigo(), codSociedad, codEtapa, codregion);

	}

	/**
	 * Metodo para obtener Lista de Regiones
	 * 
	 * @param val
	 */
	public void loadRegion(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadRegion");
		}
		MantenimientoCOBCronogramaCarteraSearchForm f = (MantenimientoCOBCronogramaCarteraSearchForm) this.formBusqueda;
		String valor = (String) val.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.siccRegionList = ajax.getRegionesByPaisSociedadEtapaDeuda(
				pais.getCodigo(), f.getCodigoSociedad(), valor);
		this.siccZonaList = ajax.getZonasByPaisSociedadEtapaDeudaRegion(
				pais.getCodigo(), f.getCodigoSociedad(), valor,
				getSiccRegionList()[0].getValue());

	}

	/**
	 * Metodo para obtener Lista de Zonas
	 * 
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		MantenimientoCOBCronogramaCarteraSearchForm f = (MantenimientoCOBCronogramaCarteraSearchForm) this.formBusqueda;
		String valor = (String) val.getNewValue();
		if (!StringUtils.isBlank(valor)) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			this.siccZonaList = ajax.getZonasByPaisSociedadEtapaDeudaRegion(
					pais.getCodigo(), f.getCodigoSociedad(),
					f.getCodigoEtapaDeuda(), valor);
		}

	}

	/**
	 * Metodo para Cambiar
	 * 
	 * @param val
	 */
	public void cambiar(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("cambiar");
		}
		String valor = (String) val.getNewValue();
		if (valor.equals("GEN")) {
			setFechaCierreCartera(true);
			setFechaGeneracionCartera(false);
		}

		else {
			setFechaGeneracionCartera(true);
			setFechaCierreCartera(false);
		}

	}

	/**
	 * @return the cobCronogramaCarteraList
	 */
	public List getCobCronogramaCarteraList() {
		return cobCronogramaCarteraList;
	}

	/**
	 * @param cobCronogramaCarteraList
	 *            the cobCronogramaCarteraList to set
	 */
	public void setCobCronogramaCarteraList(List cobCronogramaCarteraList) {
		this.cobCronogramaCarteraList = cobCronogramaCarteraList;
	}

	/**
	 * @return the siccSociedadList
	 */
	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	/**
	 * @param siccSociedadList
	 *            the siccSociedadList to set
	 */
	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}

	/**
	 * @return the siccEtapaDeudaList
	 */
	public LabelValue[] getSiccEtapaDeudaList() {
		return siccEtapaDeudaList;
	}

	/**
	 * @param siccEtapaDeudaList
	 *            the siccEtapaDeudaList to set
	 */
	public void setSiccEtapaDeudaList(LabelValue[] siccEtapaDeudaList) {
		this.siccEtapaDeudaList = siccEtapaDeudaList;
	}

	/**
	 * @return the siccRegionList
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 *            the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the fechaGeneracionCartera
	 */
	public boolean isFechaGeneracionCartera() {
		return fechaGeneracionCartera;
	}

	/**
	 * @param fechaGeneracionCartera
	 *            the fechaGeneracionCartera to set
	 */
	public void setFechaGeneracionCartera(boolean fechaGeneracionCartera) {
		this.fechaGeneracionCartera = fechaGeneracionCartera;
	}

	/**
	 * @return the fechaCierreCartera
	 */
	public boolean isFechaCierreCartera() {
		return fechaCierreCartera;
	}

	/**
	 * @param fechaCierreCartera
	 *            the fechaCierreCartera to set
	 */
	public void setFechaCierreCartera(boolean fechaCierreCartera) {
		this.fechaCierreCartera = fechaCierreCartera;
	}

}
