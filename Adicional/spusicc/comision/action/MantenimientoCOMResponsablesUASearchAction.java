package biz.belcorp.ssicc.web.spusicc.comision.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.Sistema;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMResponsablesUAService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMResponsablesUAForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMResponsablesUASearchForm;

/**
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@ManagedBean
@SessionScoped
public class MantenimientoCOMResponsablesUASearchAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4144315734023488896L;
	private List siccMarcaList;
	private List siccCanalList;
	private List mantenimientoComResponsablesUa;
	public String TIPO_UA_ZONA = "3";
	public String TIPO_UA_SECCION = "4";
	public boolean habilitar;
	public boolean disabled;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction
	 * #getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoCOMResponsablesUAForm";
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
		MantenimientoCOMResponsablesUASearchForm form = new MantenimientoCOMResponsablesUASearchForm();
		return form;
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
		MantenimientoCOMResponsablesUASearchForm f = (MantenimientoCOMResponsablesUASearchForm) this.formBusqueda;

		MantenimientoCOMResponsablesUAService service = (MantenimientoCOMResponsablesUAService) getBean("spusicc.mantenimientoCOMResponsablesUAService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		/* obteniendo valores */
		Map criterios = new HashMap();
		criterios.put("codigoPais", pais.getCodigo());
		criterios.put("codigoMarca", f.getCodigoMarca());
		criterios.put("codigoCanal", f.getCodigoCanal());
		criterios.put("tipoUnidad", f.getTipoUnidad());
		criterios.put("codigoZona", f.getCodigoZona());
		criterios.put("codigoSeccion", f.getCodigoSeccion());

		/* Obteniendo Lista */
		List resultado = service.getListaResponsablesUA(criterios);
		f.setContador(new Integer(resultado.size()));
		this.mantenimientoComResponsablesUa = resultado;

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

		Map sistemabusqueda = (Map) this.beanRegistroSeleccionado;
		String codigo = sistemabusqueda.get("idHistGere").toString();

		if (codigo != null) {
			Integer cod = Integer.parseInt(codigo);
			String codPeriHasta = (String) sistemabusqueda.get("codPeriHasta");
			MantenimientoCOMResponsablesUAService service = (MantenimientoCOMResponsablesUAService) getBean("spusicc.mantenimientoCOMResponsablesUAService");
			if (codPeriHasta != null) {
				service.deleteResponsableUA(cod);

			} else {
				throw new Exception(
						this.getResourceMessage("mantenimientoCOMResponsablesUAForm.msg.validacionEliminacionRegActivo"));
			}
		}
		return true;
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

		MantenimientoCOMResponsablesUASearchForm f = (MantenimientoCOMResponsablesUASearchForm) this.formBusqueda;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		this.siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma()
				.getCodigoISO());
		this.siccMarcaList = service.getMarcas();
		this.habilitar = true;
		this.mostrarBotonConsultar = false;

	}

	@Override
	protected String getSalirForward() {
		return "mantenimientoCOMResponsablesUAList";
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {

		MantenimientoCOMResponsablesUAForm f = (MantenimientoCOMResponsablesUAForm) this.formMantenimiento;
		f.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());
		MantenimientoCOMResponsablesUAService service = (MantenimientoCOMResponsablesUAService) getBean("spusicc.mantenimientoCOMResponsablesUAService");

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		Map criteria = BeanUtils.describe(f);

		criteria.put("usuario", usuario.getLogin());

		if (!f.isNewRecord()) {

			// if(StringUtils.isNotBlank(f.getCodigoPeriodoHasta()) &&
			// !f.getCodigoResponsableNoEditado().equals(f.getCodigoResponsable())){
			//
			// String resultado = service.getValidaResponsable(criteria);
			//
			// String codigoError = resultado.split("-")[0];
			//
			// if(codigoError.equals("ER")){
			// String uaCruze = resultado.split("-")[1];
			// String codigoPeriodoCruze = resultado.split("-")[2];
			// String mensaje[] =
			// {f.getCodigoResponsable(),uaCruze,codigoPeriodoCruze};
			// messages.add(ActionMessages.GLOBAL_MESSAGE, new
			// ActionMessage("mantenimientoCOMResponsablesUAForm.periodoCruze",mensaje));
			// saveErrors(request, messages);
			// }else{
			// service.updateResponsableUA(criteria);
			// messages.add(ActionMessages.GLOBAL_MESSAGE, new
			// ActionMessage("mantenimientoCOMResponsablesUAForm.updated"));
			// saveMessages(request, messages);
			// }
			//
			// }else{
			// criteria.put("codigoResponsable",
			// f.getCodigoResponsableNoEditado());
			// service.updateResponsableUA(criteria);
			// messages.add(ActionMessages.GLOBAL_MESSAGE, new
			// ActionMessage("mantenimientoCOMResponsablesUAForm.updated"));
			// saveMessages(request, messages);
			// }

		} else {
			String uaArmado;
			MantenimientoCOMResponsablesUASearchForm form = (MantenimientoCOMResponsablesUASearchForm) this.formBusqueda;
			String codigoZona = form.getCodigoZona();
			String codigoSeccion = form.getCodigoSeccion();

			Map resultado = service
					.getCodigosSubGerenciaRegionByCodigoZona(codigoZona);

			String codigoSubGerenciaVenta = (String) resultado
					.get("codigoSubGerenciaVenta");
			String codigoRegion = (String) resultado.get("codigoRegion");

			uaArmado = codigoSubGerenciaVenta + codigoRegion + codigoZona;

			if (StringUtils.isNotBlank(codigoSeccion)) {
				uaArmado = uaArmado + codigoSeccion;
			}

			criteria.put("ua", uaArmado);

			List responsablesUAList = mantenimientoComResponsablesUa;

			boolean isRegistroValidoCruze = false;
			boolean validacionCruze = false;
			boolean isRegistroValidoActivo = false;
			boolean validacionRegistroActivo = false;
			if (StringUtils.isNotBlank(f.getCodigoPeriodoHasta())) {
				validacionCruze = true;
				for (int i = 0; i < responsablesUAList.size(); i++) {

					Map responsableUA = (HashMap) responsablesUAList.get(i);
					Integer codigoPeriodoDesde = Integer
							.valueOf((String) responsableUA.get("codPeriDesde"));
					// Integer codigoPeriodoHasta =
					// responsableUA.get("codPeriHasta")!= null ?
					// Integer.valueOf((String)responsableUA.get("codPeriHasta"))
					// : null;

					if (Integer.valueOf(f.getCodigoPeriodoHasta()) < codigoPeriodoDesde) {
						isRegistroValidoCruze = true;
						break;
					}
				}
			} else {
				validacionRegistroActivo = true;
				Map responsableUA = (HashMap) responsablesUAList
						.get(responsablesUAList.size() - 1);

				Integer codigoPeriodoHasta = responsableUA.get("codPeriHasta") != null ? Integer
						.valueOf((String) responsableUA.get("codPeriHasta"))
						: null;

				if (StringUtils.isBlank(f.getCodigoPeriodoHasta())
						&& codigoPeriodoHasta != null) {
					isRegistroValidoActivo = true;
				}
			}

			if ((validacionCruze && isRegistroValidoCruze)
					|| (validacionRegistroActivo && isRegistroValidoActivo)) {
				String resultadoValidacion = service
						.getValidaResponsable(criteria);

				String codigoError = resultadoValidacion.split("-")[0];

				if (codigoError.equals("ER")) {
					String uaCruze = resultadoValidacion.split("-")[1];
					String codigoPeriodoCruze = resultadoValidacion.split("-")[2];
					String mensaje[] = { f.getCodigoResponsable(), uaCruze,
							codigoPeriodoCruze };
					throw new Exception(
							this.getResourceMessage("mantenimientoCOMResponsablesUAForm.periodoCruze"));

				} else {
					service.insertResponsableUA(criteria);
					throw new Exception(
							this.getResourceMessage("mantenimientoCOMResponsablesUAForm.insert"));
				}
			} else if (validacionCruze && !isRegistroValidoCruze) {
				throw new Exception(
						this.getResourceMessage("mantenimientoCOMResponsablesUAForm.periodosTralape"));
			} else if (validacionRegistroActivo && !isRegistroValidoActivo) {
				throw new Exception(
						this.getResourceMessage("mantenimientoCOMResponsablesUAForm.registroYaActivoUA"));
			}
		}

		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Map sistemabusqueda = (Map) this.beanRegistroSeleccionado;
		MantenimientoCOMResponsablesUAForm f = new MantenimientoCOMResponsablesUAForm();
		if (this.accion.equals(this.ACCION_NUEVO)) {
			MantenimientoCOMResponsablesUASearchForm form = (MantenimientoCOMResponsablesUASearchForm) this.formBusqueda;
			String codZona = form.getCodigoZona();
			String codSeccion = form.getCodigoSeccion();
			String tipoUnidad = form.getTipoUnidad();
			if (codZona == null && tipoUnidad.equals("3")) {
				throw new Exception("Debe Ingresar Zona");
			}
			if (codZona == null && tipoUnidad.equals("4") && codSeccion == null) {
				throw new Exception(
						"Primero Debe de Ingresar la Zona o Sección en la que se desea agregar el nuevo registro");
			}
			setDisabled(false);
		} else {
			String idHistGere = (String) sistemabusqueda.get("idHistGere");
			Integer codigo = Integer.parseInt(idHistGere);
			f.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry()
					.getCodigo());
			f.setOidHistGere(codigo);
			f.setCodigoCanal((String) sistemabusqueda.get("codigoCanal"));
			f.setCodigoMarca((String) sistemabusqueda.get("codigoMarca"));
			f.setCodigoPeriodoDesde((String) sistemabusqueda
					.get("codPeriDesde"));
			f.setCodigoPeriodoHasta((String) sistemabusqueda
					.get("codPeriHasta"));
			f.setCodigoResponsable((String) sistemabusqueda.get("codGerencia"));
			setDisabled(true);

			// String codigoPeriodoHastaAnterior;
			// List responsablesUAList = mantenimientoComResponsablesUa;
			// if (codigo > 1) {
			// codigoPeriodoHastaAnterior = (String) ((HashMap)
			// responsablesUAList
			// .get(codigo - 1)).get("codPeriHasta");
			// } else {
			// codigoPeriodoHastaAnterior = "-1";
			// }
			//
			// if (StringUtils.isBlank(codigoPeriodoHastaAnterior)) {
			// codigoPeriodoHastaAnterior = "-1";
			// }
			//
			// String codigoPeriodoDesdeSiguiente = "-1";
			//
			// if (responsablesUAList.size() != codigo)
			// codigoPeriodoDesdeSiguiente = (String) ((HashMap)
			// responsablesUAList
			// .get(codigo)).get("codPeriDesde");

			// session.setAttribute("codigoPeriodoHastaAnterior",
			// codigoPeriodoHastaAnterior);
			// session.setAttribute("codigoPeriodoDesdeSiguiente",
			// codigoPeriodoDesdeSiguiente);

			Map criteria = new HashMap();
			criteria.put("codigoPais", this.mPantallaPrincipalBean
					.getCurrentCountry().getCodigo());

			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
			String oidPais = reporteService.getOidString(
					"getOidPaisByCodigoPais", criteria);
			f.setOidPais(String.valueOf(oidPais));

			// Seteo la longitud del codigo de consultora de acuerdo al pais
			ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
			f.setLongitudCodigoResponsable(String.valueOf(clienteService
					.getTamanhoNumeroCliente(this.mPantallaPrincipalBean
							.getCurrentCountry().getCodigo())));

			// this.siccCanalList =
			// service.getCanalesByCodigoISO(usuario.getIdioma()
			// .getCodigoISO());
			// this.siccMarcaList = service.getMarcas();
			// List canal = new ArrayList();
			//
			// canal.add(sistemabusqueda.get("codigoCanal"));
			// this.siccCanalList=getSiccCanalList()
		}

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
		MantenimientoCOMResponsablesUAForm sistemaForm = (MantenimientoCOMResponsablesUAForm) this.formMantenimiento;
		boolean isNew = sistemaForm.isNewRecord();
		if (isNew) {
			return "mantenimientoCOMResponsablesUAForm.insert";
		} else {
			return "sistema.updated";
		}
	}

	/**
	 * Metodo para Habilitar
	 * 
	 * @param val
	 */
	public void habilitar(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("habilitar");
		}
		String valor = (String) val.getNewValue();
		if (valor.equals("4")) {
			setHabilitar(false);
		} else {
			setHabilitar(true);
		}
	}

	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList
	 *            the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList
	 *            the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * @return the mantenimientoComResponsablesUa
	 */
	public List getMantenimientoComResponsablesUa() {
		return mantenimientoComResponsablesUa;
	}

	/**
	 * @param mantenimientoComResponsablesUa
	 *            the mantenimientoComResponsablesUa to set
	 */
	public void setMantenimientoComResponsablesUa(
			List mantenimientoComResponsablesUa) {
		this.mantenimientoComResponsablesUa = mantenimientoComResponsablesUa;
	}

	/**
	 * @return the tIPO_UA_ZONA
	 */
	public String getTIPO_UA_ZONA() {
		return TIPO_UA_ZONA;
	}

	/**
	 * @param tIPO_UA_ZONA
	 *            the tIPO_UA_ZONA to set
	 */
	public void setTIPO_UA_ZONA(String tIPO_UA_ZONA) {
		TIPO_UA_ZONA = tIPO_UA_ZONA;
	}

	/**
	 * @return the tIPO_UA_SECCION
	 */
	public String getTIPO_UA_SECCION() {
		return TIPO_UA_SECCION;
	}

	/**
	 * @param tIPO_UA_SECCION
	 *            the tIPO_UA_SECCION to set
	 */
	public void setTIPO_UA_SECCION(String tIPO_UA_SECCION) {
		TIPO_UA_SECCION = tIPO_UA_SECCION;
	}

	/**
	 * @return the habilitar
	 */
	public boolean isHabilitar() {
		return habilitar;
	}

	/**
	 * @param habilitar
	 *            the habilitar to set
	 */
	public void setHabilitar(boolean habilitar) {
		this.habilitar = habilitar;
	}

	/**
	 * @return the disabled
	 */
	public boolean isDisabled() {
		return disabled;
	}

	/**
	 * @param disabled the disabled to set
	 */
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	
	

}
