package biz.belcorp.ssicc.web.spusicc.action;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.mae.model.Cliente;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteAdicional;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteClasificacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteComunicacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteConcursoPremio;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteDireccion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteHistoricoEstatus;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteIdentificacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteMarca;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClientePrimerContacto;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteReferencias;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteSubTipo;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteUnidadAdministrativa;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteVinculo;
import biz.belcorp.ssicc.dao.spusicc.mae.model.Concurso;
import biz.belcorp.ssicc.dao.spusicc.mae.model.Premio;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.Estimado;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.MantenimientoDATEstimadosService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMPorcentajeComisionService;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCalculoCalificacionTramoService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.ocr.ws.axis.IConsultaBelcorp;
import biz.belcorp.ssicc.service.spusicc.ocr.ws.axis.IConsultaBelcorpservice;
import biz.belcorp.ssicc.service.spusicc.ocr.ws.axis.IConsultaBelcorpserviceLocator;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultorasAction;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMCalificacionComisionForm;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoDATEstimadosSearchForm;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoMAEClienteForm;
import biz.belcorp.ssicc.web.spusicc.mav.form.ProcesoMAVAsignacionReemplazoGerenteRegionForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOControlFnePorcentajeSearchForm;

@SessionScoped
@ManagedBean
public class MantenimientoMAEClienteSearchAction extends
		BaseMantenimientoSearchAbstractAction {
	private List maeClienteSubtipoList = new ArrayList();
	private List maeClienteClasificacionList = new ArrayList();
	private List maeClienteNivelEstudioList = new ArrayList();
	private List maeClienteNacionalidadList = new ArrayList();
	private Object beanRegistroTipoClienteSeleccionado;
	private List maeClienteEstadoCivilList = new ArrayList();
	private List maeClienteSexoList = new ArrayList();
	private List maeClienteTipoViaList = new ArrayList();
	private LabelValue[] maeClienteNivel1List = {};
	private LabelValue[] maeClienteNivel2List = {};
	private LabelValue[] maeClienteNivel3List = {};
	private LabelValue[] maeClienteNivel4List = {};
	private List maeClienteTipoDireccionList = new ArrayList();
	private List maeClienteTipoVinculoList = new ArrayList();
	private List maeTipoCutisList = new ArrayList();
	private List maeOtrasMarcas = new ArrayList();
	private LabelValue[] siccPeriodoList = {};
	private List siccTipoDocumentoList = new ArrayList();
	private LabelValue[] maeClienteNivel2AvalList = {};
	private LabelValue[] maeClienteNivel3AvalList = {};
	private LabelValue[] maeClienteNivel2CTList = {};
	private LabelValue[] maeCiudadList = {};
	private LabelValue[] maeCiudadCTList = {};
	private LabelValue[] maeClienteNivel3CTList = {};
	private LabelValue[] maeClienteNivel4CTList = {};
	private LabelValue[] maeClienteNivel5CTList = {};
	private LabelValue[] maeClienteNivel6CTList = {};
	private LabelValue[] maeClienteNivel2VACList = {};
	private LabelValue[] maeCiudadVacList = {};
	private LabelValue[] maeClienteNivel3VACList = {};
	private LabelValue[] maeClienteNivel4VACList = {};
	private LabelValue[] maeClienteNivel5VACList = {};
	private List maeClienteTratamientoList = new ArrayList();
	private LabelValue[] maeClienteNivel6VACList = {};
	private List siccSubTipoClienteList = new ArrayList();
	private List maeDeudorasList = new ArrayList();
	private String maeFlagDeudoras = "";
	private List siccTipoClasificacion = new ArrayList();
	private String validacionExiDoc = "";

	/**
	 * 
	 */

	private boolean mostrarReferenciasChk;
	private boolean mostrarClasificacionesChk;
	public static final String MAE_TIPO_DIRECCION_DOMICILIO = "01";
	public static final String MAE_TIPO_DIRECCION_TRABAJO = "03";

	public static final String MAE_TIPO_COMUNICACION_TELEFONO_CASA = "TF";
	public static final String MAE_TIPO_COMUNICACION_TELEFONO_MOVIL = "TM";
	public static final String MAE_TIPO_COMUNICACION_TELEFONO_TRABAJO = "TT";
	public static final String MAE_TIPO_COMUNICACION_MAIL = "ML";
	public static final String MAE_TIPO_COMUNICACION_TELEFONO_REFERENCIA = "TR";
	public static final String MAE_TIPO_COMUNICACION_TELEFONO_ADICIONAL = "TA";

	public static final String MAE_OID_TIPO_VIA_DEFAULT = "2001";
	public static final String MAE_OID_ESTADO_CIVIL_DEFAULT = "2001";
	public static final String MAE_COD_SEXO_DEFAULT = "F";
	public static final String MAE_OID_TRATAMIENTO_DEFAULT = "2";
	public static final String MAE_OID_TIPO_DIRECCION_DISTRIBUCION = "2007";

	public static final String MAE_CONTROL_SUBTIPOCLIENTE = "subTipoCliente";
	public static final String MAE_CONTROL_ZONA = "codigoZona";
	public static final String MAE_CONTROL_PERIODO = "oidPeriodo";

	public static final String MAE_LONGITUD_CODIGO_ZONA = "4";
	public static final Long MAE_CLIENTE_CONTACTO_DUMMY = new Long(461343);
	private static final String MAE_DEUDORAS_LIST = "maeClienteDeudorasAval";
	private static final String MAE_FLAG_DEUDORAS = "maeFlagDeudoras";

	private static final long serialVersionUID = 1077191583359523430L;
	private LabelValue[] siccClasificacion;
	private boolean mostrarDireccionEnvioChk = false;
	private boolean mostrarDireccionVacacionesChk = false;

	private boolean mostrarPopupCliente;
	private boolean mostrarDivIndicadorFiscal;
	private static final String POPUP_CLIENTE = "POPUP_CLIENTE";

	@ManagedProperty(value = "#{busquedaConsultorasAction}")
	private BusquedaConsultorasAction busquedaConsultorasAction;

	public BusquedaConsultorasAction getBusquedaConsultorasAction() {
		return busquedaConsultorasAction;
	}

	public void setBusquedaConsultorasAction(
			BusquedaConsultorasAction busquedaConsultorasAction) {
		this.busquedaConsultorasAction = busquedaConsultorasAction;
	}
	

	@SuppressWarnings("static-access")
	@Override
	protected void setInvocarPopup(String accion) {
		if (accion.equals(this.POPUP_CLIENTE)) {
			this.mostrarPopupCliente = true;
		}
	}

	public boolean isMostrarPopupCliente() {
		return mostrarPopupCliente;
	}

	
	
	/**
	 * @param mostrarPopupCliente
	 *            the mostrarPopupCliente to set
	 */
	public void setMostrarPopupCliente(boolean mostrarPopupCliente) {
		this.mostrarPopupCliente = mostrarPopupCliente;
	}

	@SuppressWarnings("static-access")
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopupHipCliente' method");
		}

		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;
		if (accion.equals("SCLIENTES")) {
			this.busquedaConsultorasAction.verificarRegistro(event);
			if (this.busquedaConsultorasAction.isSeleccionoRegistro()) {
				Map cliente = (Map) this.busquedaConsultorasAction
						.getBeanRegistroSeleccionado();
				MantenimientoMAEClienteForm f = (MantenimientoMAEClienteForm) this.formBusqueda;
				f.setNombreConsultoraRecomendante(cliente.get("nombre1")
						.toString() + " " + cliente.get("apellido1").toString());
				f.setCodigoConsultoraRecomendante(cliente.get("codigoCliente")
						.toString());
				this.busquedaConsultorasAction
						.setBeanRegistroSeleccionado(null);
				this.formBusqueda = f;
			}
		}
		if (accion.equals("POPUP_CLIENTELIDER")) {
			this.busquedaConsultorasAction.verificarRegistro(event);
			if (this.busquedaConsultorasAction.isSeleccionoRegistro()) {
				Map cliente = (Map) this.busquedaConsultorasAction
						.getBeanRegistroSeleccionado();
				MantenimientoMAEClienteForm f = (MantenimientoMAEClienteForm) this.formBusqueda;
				f.setNombreConsultoraLiderRecomendante(cliente.get("nombre1")
						.toString() + " " + cliente.get("apellido1").toString());
				f.setCodigoConsultoraLiderRecomendante(cliente.get("codigoCliente")
						.toString());
				this.busquedaConsultorasAction
						.setBeanRegistroSeleccionado(null);
				this.formBusqueda = f;
			}
		}
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}
	}

	@Override
	protected void setSalirPopup() {
		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;
		this.busquedaConsultorasAction.setBeanRegistroSeleccionado(null);
	}

	public static String getPopupCliente() {
		return POPUP_CLIENTE;
	}

	public Object getBeanRegistroTipoClienteSeleccionado() {
		return beanRegistroTipoClienteSeleccionado;
	}

	public void setBeanRegistroTipoClienteSeleccionado(
			Object beanRegistroTipoClienteSeleccionado) {
		this.beanRegistroTipoClienteSeleccionado = beanRegistroTipoClienteSeleccionado;
	}

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	public void insertListCliente() {

		MantenimientoMAEClienteForm e = (MantenimientoMAEClienteForm) this.formBusqueda;
		String valor = e.getSubTipoCliente();
		// maeClienteSubtipoList.add()

	}

	public void deleteListCliente() {

	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoMAEClienteForm f = new MantenimientoMAEClienteForm();
		return f;
	}

	private Date obtenerFechaAñoSiguiente(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		calendar.add(Calendar.YEAR, 1);
		Date fechaAñoSiguiente = calendar.getTime();

		return fechaAñoSiguiente;
	}

	private boolean existeSubTipoClienteAux(String codigoTipoCliente,
			String codigoSubTipoCliente, List detalList) {
		Iterator it = detalList.iterator();

		while (it.hasNext()) {
			ClienteSubTipo ccd = (ClienteSubTipo) it.next();

			if ((ccd.getCodigoTipoCliente().equals(codigoTipoCliente))
					&& (ccd.getCodigoSubTipoCliente()
							.equals(codigoSubTipoCliente))) {
				return true;
			}
		}
		return false;
	}

	private String validarBoletinComercial(String codigoPais, String numeroRUT,
			String host, String codigoUsuario) {
		GenericoService serviceGen = (GenericoService) getBean("genericoService");
		log.debug("validarBoletinComercial,  numeroRUT >>> " + numeroRUT);
		String urlWSOCR = serviceGen.getParametroPais(codigoPais,
				Constants.SISTEMA_OCR, Constants.OCR_URL_WS_SOCCRED);
		Integer port = new Integer(serviceGen.getParametroPais(codigoPais,
				Constants.SISTEMA_OCR, Constants.OCR_URL_PORT_SOCCRED));
		String hostParametro = serviceGen.getParametroPais(codigoPais,
				Constants.SISTEMA_OCR, Constants.OCR_URL_HOST_SOCCRED);

		if (StringUtils.isEmpty(hostParametro))
			hostParametro = host;

		log.debug("host " + hostParametro);
		log.debug("port " + port);

		String serie = "";
		String zona = "";

		IConsultaBelcorpservice locator = new IConsultaBelcorpserviceLocator();
		log.debug("URL ws Boletin electronico >>> " + urlWSOCR);
		String res = null;

		String resultadoServicio = Constants.MAE_WEBSERVICE_RESULTADO_ERROR;

		try {
			IConsultaBelcorp service = locator
					.getIConsultaBelcorpPort(new java.net.URL(urlWSOCR));
			log.debug("service ws conectado " + service);
			res = service.getEvaluacionBelcorp(hostParametro, port, numeroRUT,
					serie, zona, codigoUsuario);

			log.debug("res " + res);
			if (StringUtils.isNotEmpty(res)) {
				String[] split = StringUtils.split(res, "|");// reporte|montoBic|MontoMic|MontoInfoCom|Estado|Mensaje|Error
				if (split.length == 7)
					resultadoServicio = split[4]; // estado
				if (split.length == 3)
					resultadoServicio = Constants.MAE_WEBSERVICE_RESULTADO_ERROR2;
			}

		} catch (Exception e) {
			log.debug("error conexion web service " + e.getMessage());
			res = null;
		}

		return resultadoServicio;
	}

	private String completarCaracteres(String valor, int longitud,
			String caracter) {
		String valorAux = new String("");

		int faltante = longitud - valor.length();

		if (faltante >= 0) {
			for (int i = 0; i < faltante; i++) {
				valorAux = valorAux + caracter;
			}
			valorAux = valorAux + valor;
		} else {

			faltante = valor.length() - longitud;
			valorAux = valor.substring(faltante);
		}

		return valorAux;
	}

	private void obtenerPeriodoActual(
			MantenimientoMAEClienteService clienteService,
			MantenimientoMAEClienteForm f, LabelValue[] periodos) {
		String oidPeriodo = "";
		Map criteria = new HashMap();

		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoMarca", f.getCodigoMarca());
		criteria.put("codigoCanal", f.getCodigoCanal());
		criteria.put("codigoZona", f.getCodigoZona());

		for (int i = 0; i < periodos.length; i++) {
			LabelValue periodo = periodos[i];

			criteria.put("codigoPeriodo", periodo.getLabel());
			boolean esRegionCerrada = clienteService
					.esRegionCerradaxZona(criteria);

			if (!esRegionCerrada) {
				boolean esZonaCerrada = clienteService.esZonaCerrada(criteria);

				if (!esZonaCerrada) {
					oidPeriodo = periodo.getValue();
					break;
				}
			}
		}

		if (oidPeriodo.length() == 0) {
			Base basePeriodo = clienteService.getSiguientePeriodo(criteria);
			oidPeriodo = basePeriodo.getCodigo();

			LabelValue[] periodosAux = new LabelValue[periodos.length + 1];

			for (int j = 0; j < periodos.length; j++) {
				periodosAux[j] = periodos[j];
			}

			LabelValue lblSiguientePeriodo = new LabelValue(
					basePeriodo.getDescripcion(), basePeriodo.getCodigo());
			periodosAux[periodos.length] = lblSiguientePeriodo;

			siccPeriodoList = periodosAux;
		} else {
			siccPeriodoList = periodos;
		}

		f.setOidPeriodo(oidPeriodo);
		f.setOidPeriodoConcurso(oidPeriodo);
	}

	private void initCabecera(MantenimientoMAEClienteForm f,
			boolean borrarSubTipoCliente) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date hoy = new Date(System.currentTimeMillis());
		f.setFechaIngreso(sdf.format(hoy));
		f.setFechaNacimiento("");
		f.setFechaDesde(sdf.format(hoy));
		f.setFechaActual(sdf.format(hoy));
		f.setFechaHasta(sdf.format(obtenerFechaAñoSiguiente(hoy)));

		if (borrarSubTipoCliente) {
			f.setMostrarDireccion(true);
			f.setMostrarUnidadAdministrativa(true);
			f.setMostrarNumeroIdentidad(true);
			f.setMostrarConsultoraRecomendante(true);
			f.setMostrarClasificaciones(true);
			f.setMostrarConsultoraVinculo(false);
			f.setMostrarCodigoEmpleado(true);
			/* INI JJ PER-SiCC-2012-0329 */
			f.setMostrarCodigoCUB(true);
			/* FIN JJ PER-SiCC-2012-0329 */
			f.setEsDuplaCyzone(false);
			f.setEsTipoConsultora(false);

			f.setIndicadoObligatorioNacionalidad(false);
			f.setIndicadoObligatorioGradoInstruccion(false);
			f.setIndicadoObligatorioTratamiento(false);

			// borramos los datos de las grillas de SubTipo Cliente y
			// Clasificaciones
			maeClienteSubtipoList.clear();

		}

		maeClienteClasificacionList.clear();

		f.setOidConsultoraRecomendante("");
		f.setCodigoConsultoraRecomendante("");
		f.setNombreConsultoraRecomendante("");
		f.setApellidoPaterno("");
		f.setApellidoMaterno("");
		f.setApellidoCasada("");
		f.setNombre1("");
		f.setNombre2("");
		f.setNumeroDocumentoIdentidad("");
		f.setEdad("0");
		f.setCodigoEmpleado("");
		f.setGradoInstruccion("");
		f.setNacionalidad("");
		f.setCodigoAnterior("");
		f.setTipoCutis("");
		f.setCodigoZona("");
		f.setCodigoTerritorio("");
		f.setOtrasMarcas("");

		f.setNivel1("");
		f.setNivel2("");
		f.setNivel3("");
		f.setNivel4("");
		f.setNivel5("");
		f.setNivel6("");
		f.setTipoVia("");
		f.setNumeroPrincipal("");
		f.setNombreVia("");
		f.setCorreVia("");
		f.setObservacionDireccion("");
		f.setBarrio("");
		f.setTelefonoCasa("");
		f.setTelefonoCelular("");
		f.setTelefonoReferencia("");
		f.setMail("");
		f.setCodigoPostal("");

		f.setCodNivel2("");
		f.setCodNivel3("");
		f.setCodNivel4("");
		f.setCodNivel5("");
		f.setCodNivel6("");

		f.setCodNivel2CT("");
		f.setCodNivel3CT("");
		f.setCodNivel4CT("");
		f.setCodNivel5CT("");
		f.setCodNivel6CT("");
		f.setIndicadorDocFiscal(true);
		f.setEmpresa("");
		f.setCargo("");
		f.setNivel1CT("");
		f.setNivel2CT("");
		f.setNivel3CT("");
		f.setNivel4CT("");
		f.setNivel5CT("");
		f.setNivel6CT("");
		f.setTipoViaCT("");
		f.setNumeroPrincipalCT("");
		f.setNombreViaCT("");
		f.setCorreViaCT("");
		f.setCodigoPostalCT("");
		f.setObservacionDireccionCT("");
		f.setBarrioCT("");
		f.setTelefono("");
		f.setSueldoMensual("");
		f.setTelefonoCasaDireccionEntrega("");
		f.setTelefonoCelularDireccionEntrega("");

		f.setOidConsultoraVinculo("");
		f.setCodigoConsultoraVinculo("");
		f.setNombreConsultoraVinculo("");

		f.setObservaciones1("");
		f.setObservaciones2("");
		f.setObservaciones3("");

		f.setEstadoCivil(MAE_OID_ESTADO_CIVIL_DEFAULT);
		f.setTipoVia(MAE_OID_TIPO_VIA_DEFAULT);
		f.setTratamiento(MAE_OID_TRATAMIENTO_DEFAULT);
		f.setSexo(MAE_COD_SEXO_DEFAULT);
		f.setGraboOK(false);
		f.setOidNuevoCliente("");

		f.setAgregarClasificacionDefault(false);
		f.setControlFoco(MAE_CONTROL_SUBTIPOCLIENTE);

		f.setConfirmacionTerritorio("");
		f.setMensajeConfirmacion("");

		f.setIndicadorClasificaciones("0");
		f.setIndicadorDireccionTrabajo("0");
		f.setIndicadorObservaciones("0");
		f.setLongitudTipoDocumento("15");
		f.setTipoDireccion(MAE_OID_TIPO_DIRECCION_DISTRIBUCION);

		f.setPrimeraVezDespliegueDireccionEntrega(true);
		f.setMostrarPantallaPremios(false);
		f.setPrimeraVezDespliegueReferencias(true);

		f.setLongitudCodigoZona(MAE_LONGITUD_CODIGO_ZONA);
		f.setPermitirModificarUbigeo(true);
		f.setMostrarTipoVia(true);
		f.setMostrarNumeroPrincipal(true);
		f.setMostrarUbigeoEntrega(true);

		f.setAprobarAvaladas(false);

		f.setPermitirCompletarCerosIdentidad(true);
		f.setMostrarSegundoDocumento(false);
		f.setTipoDocumentoIdentidad2("");
		f.setNumeroDocumentoIdentidad2("");
		f.setLongitudTipoDocumento2("15");

		f.setValidarCaracteres1("");
		f.setValidarCaracteres2("");
		f.setValidarCaracteres3("");
		f.setValidarCaracteresIdentidad("");
		f.setPermitirComenzarCerosIdentidad(true);

		f.setMostrarBarrio(false);

		/* INI SA PER-SiCC-2012-0459 */
		f.setMostrarCiudad(false);
		f.setMostrarVillaPoblacion(false);
		f.setCodigoCiudad("");
		f.setVillaPoblacion("");
		f.setCodigoCiudadCT("");
		f.setVillaPoblacionCT("");
		/* FIN SA PER-SiCC-2012-0459 */

		/* INI JJ PER-SiCC-2012-0329 */
		f.setCodigoCUB("");
		/* FIN JJ PER-SiCC-2012-0329 */

		/* INI SA PER-SiCC-2012-0365 */
		f.setTipoViaVacaciones("");
		f.setNumeroPrincipalVacaciones("");
		f.setNombreViaVacaciones("");
		f.setCorreViaVacaciones("");
		f.setObservacionDireccionVacaciones("");
		f.setBarrioVacaciones("");

		f.setCodigoPeriodoInicio("");
		f.setCodigoPeriodoFin("");
		f.setNivel1Vacaciones("");
		f.setNivel2Vacaciones("");
		f.setNivel3Vacaciones("");
		f.setNivel4Vacaciones("");
		f.setNivel5Vacaciones("");
		f.setNivel6Vacaciones("");
		f.setCodNivel2Vacaciones("");
		f.setCodNivel3Vacaciones("");
		f.setCodNivel4Vacaciones("");
		f.setCodNivel5Vacaciones("");
		f.setCodNivel6Vacaciones("");

		f.setCodigoCiudadVacaciones("");
		f.setVillaPoblacionVacaciones("");

		f.setTelefonoCasaDireccionVacaciones("");
		f.setTelefonoCelularDireccionVacaciones("");

		f.setMostrarDireccionVacaciones(false);
		f.setIndicadorDireccionVacaciones("0");
		f.setPrimeraVezDespliegueDireccionVacaciones(true);
		f.setActualizaUbigeoDirecciones(false);
		/* FIN SA PER-SiCC-2012-0365 */

		/* INI SA PER-SiCC-2012-0367 */
		f.setValidarEstatusComercial(false);
		f.setMensajeConsultoraRoja("");
		/* FIN SA PER-SiCC-2012-0367 */

		/* INI SA COS-SiCC-2013-0031 */
		f.setMostrarIndicadorImpresionPaqDoc(false);
		f.setIndicadorImpresionPaqDoc(Constants.SI);
		/* FIN SA COS-SiCC-2013-0031 */

		f.setTelefonoAdicional("");

		f.setValorIndicadorFactElect(Constants.UNO);

		limpiarReferencia(f);
	}

	private void limpiarReferencia(MantenimientoMAEClienteForm f) {
		ClienteReferencias clienteReferencias = new ClienteReferencias();
		try {
			String codigoCliente = f.getCodigoCliente();
			BeanUtils.copyProperties(f, clienteReferencias);
			f.setCodigoCliente(codigoCliente);
			f.setChkReferencias(Constants.NUMERO_CERO);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	private void actualizarListaTiposDocumento(MantenimientoMAEClienteForm f,
			MantenimientoMAEClienteService clienteService, boolean esDuplaCyzone) {
		Map criteriaDoc = new HashMap();
		criteriaDoc.put("oidPais", f.getOidPais());

		if (f.isEsDuplaCyzone())
			criteriaDoc.put("siglaDocumento", "DCZ");
		else
			criteriaDoc.put("indicadorDocFiscal", "1");

		siccTipoDocumentoList = clienteService
				.getTiposDocumentoIdentidad(criteriaDoc);

	}

	private void actualizarListarUbigeoAval(MantenimientoMAEClienteForm f) {
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");

		// String codDpto=
		// (f.getCodigoDepartamentoAval()==null?"":f.getCodigoDepartamentoAval());
		
		f.setCodigoDepartamentoAval(f.getNivel1());
		f.setCodigoProvinciaAval(f.getNivel2());
		f.setCodigoDistritoAval(f.getNivel3());
		
		
		
		String codPvr = (f.getCodigoProvinciaAval() == null ? "" : f
				.getCodigoProvinciaAval());
		String codDist = (f.getCodigoDistritoAval() == null ? "" : f
				.getCodigoDistritoAval());
		if (codPvr.length() > 0) {
			maeClienteNivel2AvalList = ajaxService.getUnidadesGeograficas(
					f.getOidPais(), f.getCodigoDepartamentoAval());
			// session.setAttribute(Constants.MAE_CLIENTE_NIVEL2_AVAL_LIST,
			// ajaxService.getUnidadesGeograficas(f.getOidPais(),
			// f.getCodigoDepartamentoAval()));
		}
		// log.debug("f.getCodigoDistritoAval()" + f.getCodigoDistritoAval());
		if (codDist.length() > 0) {
			maeClienteNivel3AvalList = ajaxService.getUnidadesGeograficas(
					f.getOidPais(), f.getCodigoProvinciaAval());
		}

		log.debug("codigo distrito Aval  " + f.getCodigoProvinciaAval());
		log.debug("codigo distrito Aval  " + f.getCodigoDistritoAval());

	}

	private void actualizarListarUbigeo(MantenimientoMAEClienteForm f) {
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");

		// actualizamos las listas de ubigeo de Direccion de Entrega
		if (f.getNivel1CT().length() > 0) {
			maeClienteNivel2CTList = ajaxService.getUnidadesGeograficas(
					f.getOidPais(), f.getNivel1CT());

		}

		/* INI SA PER-SiCC-2012-0459 */
		if (f.isMostrarCiudad()) {
			if (f.getNivel1().length() > 0) {
				maeCiudadList = ajaxService.getCiudadesByRegion(f.getNivel1());

			}
			if (f.getNivel1CT().length() > 0) {
				maeCiudadCTList = ajaxService.getCiudadesByRegion(f
						.getNivel1CT());
			}
		}
		/* FIN SA PER-SiCC-2012-0459 */

		if (f.getNivel2CT().length() > 0) {
			maeClienteNivel3CTList = ajaxService.getUnidadesGeograficas(
					f.getOidPais(), f.getNivel2CT());
		}
		if (f.getNivel3CT().length() > 0) {
			maeClienteNivel4CTList = ajaxService.getUnidadesGeograficas(
					f.getOidPais(), f.getNivel3CT());
		}
		if (f.getNivel4CT().length() > 0) {
			maeClienteNivel5CTList = ajaxService.getUnidadesGeograficas(
					f.getOidPais(), f.getNivel4CT());
		}
		if (f.getNivel5CT().length() > 0) {
			maeClienteNivel6CTList = ajaxService.getUnidadesGeograficas(
					f.getOidPais(), f.getNivel5CT());
		}

		f.setNivel1Vacaciones(f.getNivel1());
		f.setNivel2Vacaciones(f.getNivel2());
		f.setNivel3Vacaciones(f.getNivel3());
		f.setNivel4Vacaciones(f.getNivel4());
		f.setNivel5Vacaciones(f.getNivel5());
		f.setNivel6Vacaciones(f.getNivel6());
		
		
		/* INI SA PER-SiCC-2012-0365 */
		if (f.getNivel1Vacaciones().length() > 0) {
			maeClienteNivel2VACList = ajaxService.getUnidadesGeograficas(
					f.getOidPais(), f.getNivel1Vacaciones());
		}

		if (f.isMostrarCiudad()) {
			if (f.getNivel1Vacaciones().length() > 0) {
				maeCiudadVacList = ajaxService.getCiudadesByRegion(f
						.getNivel1Vacaciones());
			}
		}

		if (f.getNivel2Vacaciones().length() > 0) {
			maeClienteNivel3VACList = ajaxService.getUnidadesGeograficas(
					f.getOidPais(), f.getNivel2Vacaciones());
		}
		if (f.getNivel3Vacaciones().length() > 0) {
			maeClienteNivel4VACList = ajaxService.getUnidadesGeograficas(
					f.getOidPais(), f.getNivel3Vacaciones());
		}
		if (f.getNivel4Vacaciones().length() > 0) {
			maeClienteNivel5VACList = ajaxService.getUnidadesGeograficas(
					f.getOidPais(), f.getNivel4Vacaciones());
		}
		if (f.getNivel5Vacaciones().length() > 0) {
			maeClienteNivel6VACList = ajaxService.getUnidadesGeograficas(
					f.getOidPais(), f.getNivel5Vacaciones());
		}
		/* FIN SA PER-SiCC-2012-0365 */

		actualizarListarUbigeoAval(f);
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	private void setClienteReferencia(Cliente cliente,
			MantenimientoMAEClienteForm f) throws IllegalAccessException,
			InvocationTargetException {
		ClienteReferencias clienteReferencias = new ClienteReferencias();
		BeanUtils.copyProperties(clienteReferencias, f);
		cliente.setClienteReferencias(clienteReferencias);
	}

	private boolean existeSubTipoCliente(String codigoTipoCliente,
			String codigoSubTipoCliente, List detalList) {
		Iterator it = detalList.iterator();

		while (it.hasNext()) {
			ClienteSubTipo ccd = (ClienteSubTipo) it.next();

			if ((ccd.getCodigoTipoCliente().equals(codigoTipoCliente))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Se recupera un subTipoCliente de la lista de SubTipoClientes en sesion
	 * 
	 * @param session
	 * @return
	 */
	private List getDetalSubTipoClienteList() {
		List list = maeClienteSubtipoList;

		if (list == null) {
			list = new ArrayList();
		}
		maeClienteSubtipoList = list;
		// session.setAttribute(Constants.MAE_CLIENTE_SUBTIPO_LIST,list);
		return list;

	}

	/******************************* Combos Domicilio ***************/
	// /// 3

	public void buscarClasificacionesPorTipo(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			MantenimientoMAEClienteForm f = (MantenimientoMAEClienteForm) this.formBusqueda;
			String tipoClasificacion = (String) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			String valor = tipoClasificacion.split("-")[1];
			siccClasificacion = ajax.getClasificacionesByTipoClasificacion(
					valor, mPantallaPrincipalBean.getCurrentIdioma()
							.getCodigoISO());

		} else {
			siccClasificacion = null;
		}
	}

	public void buscarNivel2Domicilio(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			MantenimientoMAEClienteForm f = (MantenimientoMAEClienteForm) this.formBusqueda;
			String valor = (String) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			// ajax.getUnidadesGeograficas( oidPais.value,
			// unidadGeografica.value, loadNivel2Callback);
			maeClienteNivel2List = ajax.getUnidadesGeograficas(f.getOidPais(),
					valor);
			maeClienteNivel3List = null;
			maeClienteNivel4List = null;

		} else {
			maeClienteNivel2List = null;
			maeClienteNivel3List = null;
			maeClienteNivel4List = null;
		}
	}

	public void buscarNivel3Domicilio(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			MantenimientoMAEClienteForm f = (MantenimientoMAEClienteForm) this.formBusqueda;
			String valor = (String) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			// ajax.getUnidadesGeograficas( oidPais.value,
			// unidadGeografica.value, loadNivel2Callback);
			maeClienteNivel3List = ajax.getUnidadesGeograficas(f.getOidPais(),
					valor);
			maeClienteNivel4List = null;

		} else {
			maeClienteNivel3List = null;
			maeClienteNivel4List = null;
		}
	}

	public void buscarNivel4Domicilio(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			MantenimientoMAEClienteForm f = (MantenimientoMAEClienteForm) this.formBusqueda;
			String valor = (String) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			// ajax.getUnidadesGeograficas( oidPais.value,
			// unidadGeografica.value, loadNivel2Callback);
			maeClienteNivel4List = ajax.getUnidadesGeograficas(f.getOidPais(),
					valor);

		} else {
			maeClienteNivel4List = null;
		}
	}

	/******************************* Combos CT ***************/
	// /// 3

	public void buscarNivel2CTDomicilio(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			MantenimientoMAEClienteForm f = (MantenimientoMAEClienteForm) this.formBusqueda;
			String valor = (String) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			// ajax.getUnidadesGeograficas( oidPais.value,
			// unidadGeografica.value, loadNivel2Callback);
			maeClienteNivel2CTList = ajax.getUnidadesGeograficas(
					f.getOidPais(), valor);
			maeClienteNivel3CTList = null;
			maeClienteNivel4CTList = null;
			maeClienteNivel5CTList = null;
			maeClienteNivel6CTList = null;

		} else {
			maeClienteNivel2CTList = null;
			maeClienteNivel3CTList = null;
			maeClienteNivel4CTList = null;
			maeClienteNivel5CTList = null;
			maeClienteNivel6CTList = null;
		}
	}

	public void buscarNivel3CTDomicilio(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			MantenimientoMAEClienteForm f = (MantenimientoMAEClienteForm) this.formBusqueda;
			String valor = (String) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			// ajax.getUnidadesGeograficas( oidPais.value,
			// unidadGeografica.value, loadNivel2Callback);
			maeClienteNivel3CTList = ajax.getUnidadesGeograficas(
					f.getOidPais(), valor);
			maeClienteNivel4CTList = null;
			maeClienteNivel5CTList = null;
			maeClienteNivel6CTList = null;

		} else {
			maeClienteNivel3CTList = null;
			maeClienteNivel4CTList = null;
			maeClienteNivel5CTList = null;
			maeClienteNivel6CTList = null;
		}
	}

	public void buscarNivel4CTDomicilio(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			MantenimientoMAEClienteForm f = (MantenimientoMAEClienteForm) this.formBusqueda;
			String valor = (String) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			// ajax.getUnidadesGeograficas( oidPais.value,
			// unidadGeografica.value, loadNivel2Callback);
			maeClienteNivel4CTList = ajax.getUnidadesGeograficas(
					f.getOidPais(), valor);
			maeClienteNivel5CTList = null;
			maeClienteNivel6CTList = null;

		} else {
			maeClienteNivel4CTList = null;
			maeClienteNivel5CTList = null;
			maeClienteNivel6CTList = null;
		}
	}

	public void buscarNivel5CTDomicilio(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			MantenimientoMAEClienteForm f = (MantenimientoMAEClienteForm) this.formBusqueda;
			String valor = (String) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			// ajax.getUnidadesGeograficas( oidPais.value,
			// unidadGeografica.value, loadNivel2Callback);
			maeClienteNivel5CTList = ajax.getUnidadesGeograficas(
					f.getOidPais(), valor);
			maeClienteNivel6CTList = null;

		} else {
			maeClienteNivel5CTList = null;
			maeClienteNivel6CTList = null;
		}
	}

	public void buscarNivel6CTDomicilio(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			MantenimientoMAEClienteForm f = (MantenimientoMAEClienteForm) this.formBusqueda;
			String valor = (String) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			// ajax.getUnidadesGeograficas( oidPais.value,
			// unidadGeografica.value, loadNivel2Callback);
			maeClienteNivel6CTList = ajax.getUnidadesGeograficas(
					f.getOidPais(), valor);

		} else {
			maeClienteNivel6CTList = null;
		}
	}

	/******************************* Combos AVAL ***************/
	// /// 3

	public void buscarNivel2AvalDomicilio(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			MantenimientoMAEClienteForm f = (MantenimientoMAEClienteForm) this.formBusqueda;
			String valor = (String) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			// ajax.getUnidadesGeograficas( oidPais.value,
			// unidadGeografica.value, loadNivel2Callback);
			maeClienteNivel2VACList = ajax.getUnidadesGeograficas(
					f.getOidPais(), valor);
			maeClienteNivel3VACList = null;
			maeClienteNivel4VACList = null;

		} else {
			maeClienteNivel2VACList = null;
			maeClienteNivel3VACList = null;
			maeClienteNivel4VACList = null;
		}
	}

	public void buscarNivel3AvalDomicilio(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			MantenimientoMAEClienteForm f = (MantenimientoMAEClienteForm) this.formBusqueda;
			String valor = (String) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			// ajax.getUnidadesGeograficas( oidPais.value,
			// unidadGeografica.value, loadNivel2Callback);
			maeClienteNivel3VACList = ajax.getUnidadesGeograficas(
					f.getOidPais(), valor);
			maeClienteNivel4VACList = null;

		} else {
			maeClienteNivel3VACList = null;
			maeClienteNivel4VACList = null;

		}
	}

	public void buscarNivel4AvalDomicilio(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			MantenimientoMAEClienteForm f = (MantenimientoMAEClienteForm) this.formBusqueda;
			String valor = (String) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			// ajax.getUnidadesGeograficas( oidPais.value,
			// unidadGeografica.value, loadNivel2Callback);
			maeClienteNivel4VACList = ajax.getUnidadesGeograficas(
					f.getOidPais(), valor);

		} else {
			maeClienteNivel4VACList = null;
		}
	}

	public void addDetalSubTipoCliente() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'addDetalSubTipoCliente' method");
		}
		MantenimientoMAEClienteForm f = (MantenimientoMAEClienteForm) formBusqueda;

		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");

		List detalList = getDetalSubTipoClienteList();
		ClienteSubTipo clienteSubTipo = getSubTipoCliente(f);

		if (!existeSubTipoCliente(clienteSubTipo.getCodigoTipoCliente(),
				clienteSubTipo.getCodigoSubTipoCliente(), detalList)) {
			// SI ES CONSULTORA, SE PONDRA EL TIPO DE DOCUMENTO OBLIGATORIO PARA
			// EL PAIS
			if ((clienteSubTipo.getCodigoTipoCliente()
					.equals(Constants.MAE_TIPO_CLIENTE_CONSULTORA))
					&& ((clienteSubTipo.getCodigoSubTipoCliente()
							.equals(Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_NEGOCIO)) || (clienteSubTipo
							.getCodigoSubTipoCliente()
							.equals(Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_OFICINA)))) {
				f.setTipoDocumentoIdentidad(clienteService
						.getTipoDocumentoObligatorio(f.getOidPais()));
				f.setMostrarConsultoraRecomendante(true);
				f.setMostrarUnidadAdministrativa(true);
				f.setMostrarConsultoraVinculo(false);
				f.setMostrarDireccion(true);
				f.setMostrarNumeroIdentidad(true);
				f.setControlFoco(MAE_CONTROL_ZONA);

				if (clienteSubTipo.getCodigoSubTipoCliente().equals(
						Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_OFICINA)) {
					f.setMostrarCodigoEmpleado(true);
				} else {
					if (!(existeSubTipoCliente(
							Constants.MAE_TIPO_CLIENTE_CONSULTORA,
							Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_OFICINA,
							detalList))
							|| (existeSubTipoCliente(
									Constants.MAE_TIPO_CLIENTE_GERENTE,
									Constants.MAE_SUBTIPO_GERENTE_REGION,
									detalList))
							|| (existeSubTipoCliente(
									Constants.MAE_TIPO_CLIENTE_GERENTE,
									Constants.MAE_SUBTIPO_GERENTE_ZONA,
									detalList))) {
						f.setMostrarCodigoEmpleado(false);
						/* INI JJ PER-SiCC-2012-0329 */
						f.setMostrarCodigoCUB(false);
						/* FIN JJ PER-SiCC-2012-0329 */
					}
				}

				if ("-".equals(f.getCodigoZona())) {
					f.setCodigoZona("");
					f.setCodigoTerritorio("");
				}

				if ("-".equals(f.getNumeroDocumentoIdentidad())) {
					f.setNumeroDocumentoIdentidad("");
				}

				Map criteria = new HashMap();
				criteria.put("oidPais", f.getOidPais());
				criteria.put("oidSubTipoCliente",
						clienteSubTipo.getOidSubTipoCliente());

				// seteamos la edad minima y maxima
				f.setEdadMinima(clienteService.getEdadMinima(criteria));
				f.setEdadMaxima(clienteService.getEdadMaxima(criteria));

				f.setEsDuplaCyzone(false);
				f.setEsTipoConsultora(true);
			}

			// SI ES GERENTE, NO SE MUESTRA CONSULTORA RECOMENDANTE
			if ((clienteSubTipo.getCodigoTipoCliente()
					.equals(Constants.MAE_TIPO_CLIENTE_GERENTE))
					&& ((clienteSubTipo.getCodigoSubTipoCliente()
							.equals(Constants.MAE_SUBTIPO_GERENTE_REGION)) || (clienteSubTipo
							.getCodigoSubTipoCliente()
							.equals(Constants.MAE_SUBTIPO_GERENTE_ZONA)))
					&& (!existeSubTipoCliente(
							Constants.MAE_TIPO_CLIENTE_CONSULTORA,
							Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_NEGOCIO,
							detalList))
					&& (!existeSubTipoCliente(
							Constants.MAE_TIPO_CLIENTE_CONSULTORA,
							Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_OFICINA,
							detalList))) {
				f.setMostrarConsultoraRecomendante(false);
				f.setMostrarConsultoraVinculo(false);

				f.setMostrarDireccion(true);
				f.setMostrarNumeroIdentidad(true);
				f.setMostrarUnidadAdministrativa(true);
				f.setMostrarCodigoEmpleado(true);
				/* INI JJ PER-SiCC-2012-0329 */
				f.setMostrarCodigoCUB(true);
				/* FIN JJ PER-SiCC-2012-0329 */
				f.setControlFoco(MAE_CONTROL_ZONA);

				if ("-".equals(f.getCodigoZona())) {
					f.setCodigoZona("");
					f.setCodigoTerritorio("");
				}

				if ("-".equals(f.getNumeroDocumentoIdentidad())) {
					f.setNumeroDocumentoIdentidad("");
				}
			}

			// SI ES DUPLACYZONE, SE PONDRA EL TIPO DE DOCUMENTO DE DUPLA CYZONE
			if ((clienteSubTipo.getCodigoTipoCliente()
					.equals(Constants.MAE_TIPO_CLIENTE_HIJADUPLA))
					&& (clienteSubTipo.getCodigoSubTipoCliente()
							.equals(Constants.MAE_SUBTIPO_HIJADUPLA_HIJADUPLA))) {

				f.setMostrarConsultoraRecomendante(false);
				f.setTipoDocumentoIdentidad(clienteService
						.getTipoDocumentoDuplaCyzone(f.getOidPais()));
				f.setMostrarNumeroIdentidad(false);
				f.setMostrarDireccion(false);
				f.setMostrarUnidadAdministrativa(false);
				f.setMostrarConsultoraVinculo(true);
				f.setMostrarCodigoEmpleado(false);
				f.setEsDuplaCyzone(true);
				f.setEsTipoConsultora(false);
				f.setControlFoco(MAE_CONTROL_PERIODO);

				f.setCodigoZona("-");
				f.setCodigoTerritorio("-");
				f.setNumeroDocumentoIdentidad("-");

				f.setNivel1("-");
				f.setNivel2("-");
				f.setNivel3("-");

				Map criteria = new HashMap();
				criteria.put("oidPais", f.getOidPais());
				criteria.put("oidSubTipoCliente",
						clienteSubTipo.getOidSubTipoCliente());

				// seteamos la edad minima y maxima
				f.setEdadMinima(clienteService.getEdadMinima(criteria));
				f.setEdadMaxima(clienteService.getEdadMaxima(criteria));

			}

			// SI ES AVAL, SE PONDRA EL TIPO DE DOCUMENTO OBLIGATORIO PARA EL
			// PAIS
			if ((clienteSubTipo.getCodigoTipoCliente()
					.equals(Constants.MAE_TIPO_CLIENTE_POTENCIAL))
					&& (clienteSubTipo.getCodigoSubTipoCliente()
							.equals(Constants.MAE_SUBTIPO_POTENCIAL_AVAL))
					&& (!existeSubTipoCliente(
							Constants.MAE_TIPO_CLIENTE_CONSULTORA,
							Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_NEGOCIO,
							detalList))
					&& (!existeSubTipoCliente(
							Constants.MAE_TIPO_CLIENTE_CONSULTORA,
							Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_OFICINA,
							detalList))) {
				f.setTipoDocumentoIdentidad(clienteService
						.getTipoDocumentoObligatorio(f.getOidPais()));
				f.setMostrarConsultoraRecomendante(false);
				f.setMostrarUnidadAdministrativa(false);
				f.setMostrarConsultoraVinculo(false);
				f.setMostrarCodigoEmpleado(false);

				f.setMostrarDireccion(true);
				f.setMostrarNumeroIdentidad(true);
				f.setControlFoco(MAE_CONTROL_PERIODO);

				if (detalList.size() == 0) {
					f.setCodigoZona("-");
					f.setCodigoTerritorio("-");
				}
			}

			if (detalList.size() == 0) {
				clienteSubTipo.setIndicadorPrincipal(new Integer(1));

				// si es consultora/gerente/aval, entonces no se mostrara el
				// SubTipoCliente DuplaCyzone
				if (!clienteSubTipo.getCodigoTipoCliente().equals(
						Constants.MAE_TIPO_CLIENTE_HIJADUPLA)) {
					List subTiposCliente = siccSubTipoClienteList;

					for (int i = 0; i < subTiposCliente.size(); i++) {
						Base base = (Base) subTiposCliente.get(i);

						StringTokenizer st = new StringTokenizer(
								base.getCodigo(), "-");
						String codigoTipoCliente = st.nextToken();

						if (codigoTipoCliente
								.equals(Constants.MAE_TIPO_CLIENTE_HIJADUPLA)) {
							subTiposCliente.remove(i);
							break;
						}
					}

					// Recargaremos la lista de Tipos de documento
					actualizarListaTiposDocumento(f, clienteService, false);

				} else {
					List subTipoClienteDupla = new ArrayList();
					List subTiposCliente = siccSubTipoClienteList;

					for (int i = 0; i < subTiposCliente.size(); i++) {
						Base base = (Base) subTiposCliente.get(i);

						StringTokenizer st = new StringTokenizer(
								base.getCodigo(), "-");
						String codigoTipoCliente = st.nextToken();

						if (codigoTipoCliente
								.equals(Constants.MAE_TIPO_CLIENTE_HIJADUPLA)) {
							subTipoClienteDupla.add(base);
							break;
						}
					}

					siccSubTipoClienteList = subTipoClienteDupla;
					// session.setAttribute(Constants.SICC_SUB_TIPO_CLIENTE_LIST,
					// subTipoClienteDupla);

					// Recargaremos la lista de Tipos de documento
					actualizarListaTiposDocumento(f, clienteService, true);
				}

				// Colocamos valores por Defecto
				setearValorPorDefectoPorSubTipoCliente(clienteService, f,
						clienteSubTipo.getOidSubTipoCliente().toString());
			} else
				clienteSubTipo.setIndicadorPrincipal(new Integer(0));

			log.debug("- seteando lista : "
					+ clienteSubTipo.getDescripcionTipoCliente() + "/"
					+ clienteSubTipo.getDescripcionSubTipoCliente());
			detalList.add(clienteSubTipo);
			log.debug("lista size : " + detalList.size());

			f.setMostrarCodigoCUB(false);
			if ((existeSubTipoClienteAux(Constants.MAE_TIPO_CLIENTE_CONSULTORA,
					Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_OFICINA, detalList))
					|| (existeSubTipoClienteAux(
							Constants.MAE_TIPO_CLIENTE_GERENTE,
							Constants.MAE_SUBTIPO_GERENTE_REGION, detalList))
					|| (existeSubTipoClienteAux(
							Constants.MAE_TIPO_CLIENTE_GERENTE,
							Constants.MAE_SUBTIPO_GERENTE_ZONA, detalList))) {
				f.setMostrarCodigoCUB(true);
			}

			// Agregamos la clasificacion por Defecto relacionado al
			// SubTipoCliente
			String tipoClasificacionDefault = clienteService
					.getTipoClasificacionDefault(
							clienteSubTipo.getCodigoTipoCliente(),
							clienteSubTipo.getCodigoSubTipoCliente());

			String clasificacionDefault = clienteService
					.getClasificacionDefault(
							clienteSubTipo.getCodigoTipoCliente(),
							clienteSubTipo.getCodigoSubTipoCliente());

			f.setTipoClasificacion(tipoClasificacionDefault);
			f.setClasificacion(clasificacionDefault);

			addDetalClasificacion();

			// Actualizamos la longitud del tipo de documento de identidad
			f.setLongitudTipoDocumento(ajaxService.getLongitudTipoDocumento(
					f.getOidPais(), f.getTipoDocumentoIdentidad()));

		} else {
			String cadError = clienteSubTipo.getDescripcionTipoCliente();
			addError(
					"Error",
					getResourceMessage("mantenimientoMAEClienteForm.msg.SubTipoExiste")
							+ " " + cadError);

		}

		updateTiposClasificaciones(detalList);

		// Actualizamos las listas de ubigeo
		actualizarListarUbigeo(f);

		validarMostrarLiderRecomendante(detalList, clienteService, f);
		String valor = f.getTipoClasificacion().split("-")[1];
		String x = f.getTipoClasificacion();
		siccClasificacion = ajaxService
				.getClasificacionesByTipoClasificacion(valor,
						mPantallaPrincipalBean.getCurrentIdioma()
								.getCodigoISO());

	}

	private Cliente obtenerCliente(MantenimientoMAEClienteForm f,
			List listSubTipo, List listClasificacion) throws Exception {
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		Long oidMarca = clienteService
				.getOidMarca(Constants.CODIGO_MARCA_DEFAULT);
		Long oidCanal = clienteService
				.getOidCanal(Constants.CODIGO_CANAL_DEFAULT);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaActual = sdf.format(new Date(System.currentTimeMillis()));

		// Obtenemos los datos del usuario Logueado
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();

		boolean tieneTipoClienteConsultora = false;
		String oidSubTipoClientePrincipal = ((ClienteSubTipo) listSubTipo
				.get(0)).getOidSubTipoCliente().toString();

		// Verifico si el cliente tiene un tipo de cliente CONSULTORA
		if ((existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_CONSULTORA,
				Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_NEGOCIO, listSubTipo))
				|| (existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_CONSULTORA,
						Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_OFICINA,
						listSubTipo))) {
			tieneTipoClienteConsultora = true;
		}

		// Recuperamos el codigo de cliente y digito de control
		// obtenemos un nuevo codigo de cliente y su digito de control
		Cliente cliente = new Cliente();
		if (f.isEsCodigoClienteAutomatico()) {
			Map criteriaCodigo = new HashMap();
			criteriaCodigo.put("codigoPais", f.getCodigoPais());
			criteriaCodigo.put("longitudCodigoCliente",
					f.getLongitudCodigoCliente());

			Base baseCodigoCliente = clienteService
					.getNuevoCodigoCliente(criteriaCodigo);

			cliente.setCodigo(baseCodigoCliente.getCodigo());
			cliente.setDigitoControl(baseCodigoCliente.getDescripcion());
		} else {
			log.info("Numero Documento : " + f.getNumeroDocumentoIdentidad()
					+ ", LongitudCodigoCliente: "
					+ f.getLongitudCodigoCliente());

			String codigoClienteAux = completarCaracteres(
					f.getNumeroDocumentoIdentidad(),
					Integer.parseInt(f.getLongitudCodigoCliente()), "0");
			log.info("Codigo Cliente Generado: " + codigoClienteAux);

			cliente.setCodigo(codigoClienteAux);
			cliente.setDigitoControl(f.getDigitoControl());
		}

		// validamos que la fecha actual se encuentre dentro del rango de la
		// campaña de ingreso
		// de lo contrario se toma la fecha de inicio de la campaña
		Map criteriaFechas = new HashMap();
		criteriaFechas.put("oidPeriodo", f.getOidPeriodo());
		criteriaFechas.put("fecha", fechaActual);
		String fechaInicio = clienteService
				.getFechaInicioPeriodo(criteriaFechas);
		if (fechaInicio == null) {
			criteriaFechas.remove("fecha");
			fechaActual = clienteService.getFechaInicioPeriodo(criteriaFechas);
		}

		// datos del cliente
		cliente.setCodigoPais(f.getCodigoPais());
		cliente.setOidPais(new Long(f.getOidPais()));
		cliente.setApellido1(f.getApellidoPaterno());
		cliente.setApellido2(f.getApellidoMaterno());
		cliente.setApellidoCasada(f.getApellidoCasada());
		cliente.setNombre1(f.getNombre1());
		cliente.setNombre2(f.getNombre2());
		cliente.setTratamiento(f.getTratamiento());
		cliente.setSexo(f.getSexo());
		cliente.setIndicadorFichaInscripcion(new Integer(0));
		cliente.setFechaIngreso(sdf.parse(f.getFechaIngreso()));
		cliente.setOidFormaPago(clienteService.getOidFormaPagoSubTipoCliente(
				f.getOidPais(), oidSubTipoClientePrincipal));
		cliente.setOidPeriodoIngreso(new Long(f.getOidPeriodo()));
		cliente.setCodigoUsuario(usuario.getLogin());
		cliente.setUsuarioModifica(usuario.getLogin());
		cliente.setCodigoAnterior(f.getCodigoAnterior());
		cliente.setIndicadorOrigen("C");
		obtenerCriteriosBusqueda(cliente, f);

		// subtipos del cliente
		cliente.setListClienteSubTipo(listSubTipo);
		for (int i = 0; i < listSubTipo.size(); i++) {
			ClienteSubTipo clienteSubTipo = (ClienteSubTipo) listSubTipo.get(i);
			clienteSubTipo.setListClienteClasificacion(null);
		}

		// clasificaciones del cliente
		if (listClasificacion != null && listClasificacion.size() > 0) {
			Iterator it = listClasificacion.iterator();
			while (it.hasNext()) {
				ClienteClasificacion clienteClasificacion = (ClienteClasificacion) it
						.next();
				clienteClasificacion.setOidPeriodo(new Long(f.getOidPeriodo()));
				clienteClasificacion.setFechaClasificacion(sdf.parse(f
						.getFechaIngreso()));

				Long oidSubTipo = clienteClasificacion.getOidClienteSubTipo();

				for (int i = 0; i < listSubTipo.size(); i++) {
					ClienteSubTipo clienteSubTipo = (ClienteSubTipo) listSubTipo
							.get(i);

					if (oidSubTipo.longValue() == clienteSubTipo
							.getOidSubTipoCliente().longValue()) {
						if (clienteSubTipo.getListClienteClasificacion() == null)
							clienteSubTipo
									.setListClienteClasificacion(new ArrayList());

						clienteSubTipo.getListClienteClasificacion().add(
								clienteClasificacion);
						break;
					}
				}
			}
		}

		// datos adicionales del cliente

		ClienteAdicional clienteAdicional = new ClienteAdicional();
		if (tieneTipoClienteConsultora)
			clienteAdicional.setOidEstatusCliente(new Long(1));
		clienteAdicional.setIndicadorActivo(new Integer(1));
		clienteAdicional.setNumeroCampanasSinPedido(null);
		clienteAdicional.setFechaNacimiento(sdf.parse(f.getFechaNacimiento()));
		clienteAdicional.setCodigoEmpleado(f.getCodigoEmpleado());
		clienteAdicional.setEdad(new Integer(f.getEdad()));
		clienteAdicional.setOidEstadoCivil(new Long(f.getEstadoCivil()));
		clienteAdicional.setOidPeriodoNivelRiesgo(null);
		clienteAdicional.setOidPeriodoLineaCredito(null);
		/* INI JJ PER-SiCC-2012-0329 */
		clienteAdicional.setCodigoCUB(f.getCodigoCUB());
		/* FIN JJ PER-SiCC-2012-0329 */
		if (f.isMostrarUnidadAdministrativa()) { // NO SE GRABA PARA AVAL NI
													// PARA DUPLACYZONE
			clienteAdicional.setOidNivelRiesgo(new Long(4));
			clienteAdicional.setMontoLineaCredito(new Double(0));
		}
		if (f.getGradoInstruccion() != null
				&& !("".equals(f.getGradoInstruccion())))
			clienteAdicional.setOidNivelEstudios(new Long(f
					.getGradoInstruccion()));
		if (f.getSueldoMensual() != null && !("".equals(f.getSueldoMensual())))
			clienteAdicional
					.setIngresoFamiliar(new Double(f.getSueldoMensual()));
		if (f.getNacionalidad() != null && !("".equals(f.getNacionalidad())))
			clienteAdicional.setOidNacionalidad(new Long(f.getNacionalidad()));
		clienteAdicional.setCentroTrabajo(f.getEmpresa());
		clienteAdicional.setCargo(f.getCargo());
		if (tieneTipoClienteConsultora) {
			clienteAdicional.setOidPeriodoLineaCredito(new Long(f
					.getOidPeriodo()));
			clienteAdicional.setOidPeriodoNivelRiesgo(new Long(f
					.getOidPeriodo()));
		}
		clienteAdicional.setIndicadorCorrespondencia(new Integer(1));
		if (f.getTipoCutis() != null && !("".equals(f.getTipoCutis())))
			clienteAdicional.setOidTipoCutis(new Long(f.getTipoCutis()));
		if (f.getOtrasMarcas() != null && !("".equals(f.getOtrasMarcas())))
			clienteAdicional.setOidOtrasMarcas(new Long(f.getOtrasMarcas()));
		clienteAdicional
				.setIndicadorCompromiso(f.getValorIndicadorCompromiso());
		clienteAdicional.setMotivo(f.getMotivo());

		/* INI SA COS-SiCC-2013-0031 */
		if (f.isMostrarIndicadorImpresionPaqDoc()) {
			if (Constants.SI.equals(f.getIndicadorImpresionPaqDoc()))
				clienteAdicional.setIndicadorImpresionPaqDoc(null);
			else
				clienteAdicional.setIndicadorImpresionPaqDoc(Constants.NO);
		}
		/* FIN SA COS-SiCC-2013-0031 */

		/* INI JP PER-SiCC-2013-0480 */
		String indicadorDocumentosLegales = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_DOCUM_LEGAL);
		if (Constants.MAE_IMPRI_DOCUM_LEGAL.equals(indicadorDocumentosLegales)) {
			f.setIndicadorDocumentosLegales(true);
		} else {
			f.setIndicadorDocumentosLegales(false);
		}

		if (f.isIndicadorDocumentosLegales()) {

			String codTipoDocLegal = clienteService.getCodigoTipoDocLegal(f
					.getTipoDocumentoIdentidad());
			if (StringUtils.isNotEmpty(codTipoDocLegal)) {
				if (codTipoDocLegal.equals(f.getTipoDocumentoIdentidad()))
					clienteAdicional.setIndicadorImpresionDocumentos("0");
				else
					clienteAdicional.setIndicadorImpresionDocumentos("1");
			} else
				clienteAdicional.setIndicadorImpresionDocumentos("1");
		} else
			clienteAdicional.setIndicadorImpresionDocumentos("1");
		/* FIN JP PER-SiCC-2013-0480 */
		/* INI PER-SiCC-2014-0162 */
		if (f.getTipoDocumentoIdentidad().compareTo(Constants.MAE_CODIGO_RUC_2) == 0
				|| f.getTipoDocumentoIdentidad().compareTo(
						Constants.MAE_CODIGO_REGISTRO_UNICO_CONTRIBUYENTE_FACT) == 0) {
			clienteAdicional.setUsuCarg(null);
			clienteAdicional.setFecCarg(null);
			clienteAdicional.setTipCarg(null);
			clienteAdicional.setIndImprDocu("0");

		}
		if (f.getTipoDocumentoIdentidad().compareTo(Constants.MAE_CODIGO_RUC_2) != 0
				&& f.getTipoDocumentoIdentidad().compareTo(
						Constants.MAE_CODIGO_REGISTRO_UNICO_CONTRIBUYENTE_FACT) != 0) {
			clienteAdicional.setUsuCarg(usuario.getLogin());
			clienteAdicional.setFecCarg(new Date(System.currentTimeMillis()));
			clienteAdicional.setTipCarg("3");
			clienteAdicional.setIndImprDocu(f.getValorIndicadorFactElect());
		}
		/* FIN PER-SiCC-2014-0162 */

		cliente.setClienteAdicional(clienteAdicional);

		// estatus inicial del cliente
		if (tieneTipoClienteConsultora) {
			ClienteHistoricoEstatus clienteHistoricoEstatus = new ClienteHistoricoEstatus();
			clienteHistoricoEstatus.setOidEstatus(new Long(1));
			clienteHistoricoEstatus.setOidPeriodo(new Long(f.getOidPeriodo()));
			clienteHistoricoEstatus.setOidPeriodoFin(null);
			cliente.setClienteHistoricoEstatus(clienteHistoricoEstatus);
		}

		// datos de identidad del cliente
		List listClienteIdentificacion = new ArrayList();
		ClienteIdentificacion clienteIdentificacion = new ClienteIdentificacion();
		clienteIdentificacion.setOidTipoDocumento(new Long(f
				.getTipoDocumentoIdentidad()));
		clienteIdentificacion.setNumeroDocumento(f
				.getNumeroDocumentoIdentidad());
		if (f.isEsDuplaCyzone()) {
			// lo actualizamos con el codigo de cliente
			clienteIdentificacion.setNumeroDocumento(cliente.getCodigo());
		}
		clienteIdentificacion.setIndicadorPrincipal(new Integer(1));
		clienteIdentificacion.setIdentificadorPersonal("P");
		listClienteIdentificacion.add(clienteIdentificacion);

		if (f.isMostrarSegundoDocumento() && (!f.isEsDuplaCyzone())) {
			if ((f.getTipoDocumentoIdentidad2().length() > 0)
					&& (f.getNumeroDocumentoIdentidad2().length() > 0)) {
				ClienteIdentificacion clienteIdentificacionAux = new ClienteIdentificacion();
				clienteIdentificacionAux.setOidTipoDocumento(new Long(f
						.getTipoDocumentoIdentidad2()));
				clienteIdentificacionAux.setNumeroDocumento(f
						.getNumeroDocumentoIdentidad2());
				clienteIdentificacionAux.setIndicadorPrincipal(new Integer(0));
				clienteIdentificacionAux.setIdentificadorPersonal("P");

				listClienteIdentificacion.add(clienteIdentificacionAux);
			}
		}
		cliente.setListClienteIdentificacion(listClienteIdentificacion);

		// direcciones del cliente
		if (f.isMostrarDireccion()) {
			List listDireccionCliente = new ArrayList();
			ClienteDireccion clienteDireccion = new ClienteDireccion();
			clienteDireccion.setOidTipoDireccion(clienteService
					.getOidTipoDireccion(MAE_TIPO_DIRECCION_DOMICILIO));

			if (f.isMostrarUnidadAdministrativa()) {
				clienteDireccion
						.setOidTerritorio(new Long(f.getOidTerritorio()));
				clienteDireccion.setIndicadorEstandarizacionGIS("S");
			}

			if (f.getTipoVia() != null && !("".equals(f.getTipoVia())))
				clienteDireccion.setOidTipoVia(new Long(f.getTipoVia()));
			clienteDireccion.setNombreVia(f.getNombreVia());
			clienteDireccion.setCorreVia(f.getCorreVia());
			clienteDireccion.setNumeroPrincipal(f.getNumeroPrincipal());
			clienteDireccion.setObservaciones(f.getObservacionDireccion());
			clienteDireccion.setBarrio(f.getBarrio());
			clienteDireccion.setIndicadorDireccionPrincipal(new Integer(1));
			clienteDireccion.setIndicadorEliminacion(new Integer(0));
			clienteDireccion.setCodigoPostal(f.getCodigoPostal());

			/* INI SA PER-SiCC-2012-0459 */
			if (f.getCodigoCiudad() != null
					&& !("".equals(f.getCodigoCiudad()))) {
				StringTokenizer stCodigoCiudad = new StringTokenizer(
						f.getCodigoCiudad(), "__");
				clienteDireccion.setCodigoUbigeo1(stCodigoCiudad.nextToken());
				clienteDireccion.setCodigoCiudad(stCodigoCiudad.nextToken());
			}
			if (f.getVillaPoblacion() != null
					&& !("".equals(f.getVillaPoblacion()))) {
				clienteDireccion.setVillaPoblacion(f.getVillaPoblacion());
			}
			/* FIN SA PER-SiCC-2012-0459 */

			if (f.getNivel6() != null && !("".equals(f.getNivel6())))
				clienteDireccion.setCodigoUnidadGeografica(f.getNivel6());
			else if (f.getNivel5() != null && !("".equals(f.getNivel5())))
				clienteDireccion.setCodigoUnidadGeografica(f.getNivel5());
			else if (f.getNivel4() != null && !("".equals(f.getNivel4())))
				clienteDireccion.setCodigoUnidadGeografica(f.getNivel4());
			else if (f.getNivel3() != null && !("".equals(f.getNivel3())))
				clienteDireccion.setCodigoUnidadGeografica(f.getNivel3());
			listDireccionCliente.add(clienteDireccion);

			if ((f.getNombreViaCT().length() > 0)
					|| (f.getObservacionDireccionCT().length() > 0)) {
				ClienteDireccion clienteDireccionCT = new ClienteDireccion();
				clienteDireccionCT.setOidTipoDireccion(new Long(f
						.getTipoDireccion()));
				clienteDireccionCT.setOidTipoVia(new Long(f.getTipoViaCT()));
				clienteDireccionCT.setNombreVia(f.getNombreViaCT());
				clienteDireccionCT.setCorreVia(f.getCorreViaCT());
				clienteDireccionCT.setNumeroPrincipal(f.getNumeroPrincipalCT());
				clienteDireccionCT.setObservaciones(f
						.getObservacionDireccionCT());
				clienteDireccionCT.setBarrio(f.getBarrioCT());
				clienteDireccionCT
						.setIndicadorDireccionPrincipal(new Integer(0));
				clienteDireccionCT.setIndicadorEliminacion(new Integer(0));
				clienteDireccionCT.setIndicadorEstandarizacionGIS(null);
				clienteDireccionCT.setCodigoPostal(f.getCodigoPostalCT());

				/* INI SA PER-SiCC-2012-0459 */
				if (f.getCodigoCiudadCT() != null
						&& !("".equals(f.getCodigoCiudadCT()))) {
					StringTokenizer stCodigoCiudad = new StringTokenizer(
							f.getCodigoCiudadCT(), "__");
					clienteDireccionCT.setCodigoUbigeo1(stCodigoCiudad
							.nextToken());
					clienteDireccionCT.setCodigoCiudad(stCodigoCiudad
							.nextToken());
				}
				if (f.getVillaPoblacionCT() != null
						&& !("".equals(f.getVillaPoblacionCT()))) {
					clienteDireccionCT.setVillaPoblacion(f
							.getVillaPoblacionCT());
				}
				/* FIN SA PER-SiCC-2012-0459 */

				if (f.getNivel6CT() != null && !("".equals(f.getNivel6CT())))
					clienteDireccionCT.setCodigoUnidadGeografica(f
							.getNivel6CT());
				else if (f.getNivel5CT() != null
						&& !("".equals(f.getNivel5CT())))
					clienteDireccionCT.setCodigoUnidadGeografica(f
							.getNivel5CT());
				else if (f.getNivel4CT() != null
						&& !("".equals(f.getNivel4CT())))
					clienteDireccionCT.setCodigoUnidadGeografica(f
							.getNivel4CT());
				else if (f.getNivel3CT() != null
						&& !("".equals(f.getNivel3CT())))
					clienteDireccionCT.setCodigoUnidadGeografica(f
							.getNivel3CT());
				listDireccionCliente.add(clienteDireccionCT);

			}

			/* INI SA PER-SiCC-2012-0365 */
			if ((f.getNombreViaVacaciones().length() > 0)
					|| (f.getObservacionDireccionVacaciones().length() > 0)) {
				ClienteDireccion clienteDireccionVac = new ClienteDireccion();
				clienteDireccionVac
						.setOidTipoDireccion(clienteService
								.getOidTipoDireccion(Constants.MAE_TIPO_DIRECCION_VACACIONES));
				clienteDireccionVac.setCodigoPeriodoInicio(f
						.getCodigoPeriodoInicio());
				clienteDireccionVac
						.setCodigoPeriodoFin(f.getCodigoPeriodoFin());
				clienteDireccionVac.setOidTipoVia(new Long(f
						.getTipoViaVacaciones()));
				clienteDireccionVac.setNombreVia(f.getNombreViaVacaciones());
				clienteDireccionVac.setCorreVia(f.getCorreViaVacaciones());
				clienteDireccionVac.setNumeroPrincipal(f
						.getNumeroPrincipalVacaciones());
				clienteDireccionVac.setObservaciones(f
						.getObservacionDireccionVacaciones());
				clienteDireccionVac.setBarrio(f.getBarrioVacaciones());
				clienteDireccionVac.setIndicadorDireccionPrincipal(new Integer(
						0));
				clienteDireccionVac.setIndicadorEliminacion(new Integer(0));
				clienteDireccionVac.setIndicadorEstandarizacionGIS(null);

				if (f.getCodigoCiudadVacaciones() != null
						&& !("".equals(f.getCodigoCiudadVacaciones()))) {
					StringTokenizer stCodigoCiudad = new StringTokenizer(
							f.getCodigoCiudadVacaciones(), "__");
					clienteDireccionVac.setCodigoUbigeo1(stCodigoCiudad
							.nextToken());
					clienteDireccionVac.setCodigoCiudad(stCodigoCiudad
							.nextToken());
				}
				if (f.getVillaPoblacionVacaciones() != null
						&& !("".equals(f.getVillaPoblacionVacaciones()))) {
					clienteDireccionVac.setVillaPoblacion(f
							.getVillaPoblacionVacaciones());
				}

				if (f.getNivel6Vacaciones() != null
						&& !("".equals(f.getNivel6Vacaciones())))
					clienteDireccionVac.setCodigoUnidadGeografica(f
							.getNivel6Vacaciones());
				else if (f.getNivel5Vacaciones() != null
						&& !("".equals(f.getNivel5Vacaciones())))
					clienteDireccionVac.setCodigoUnidadGeografica(f
							.getNivel5Vacaciones());
				else if (f.getNivel4Vacaciones() != null
						&& !("".equals(f.getNivel4Vacaciones())))
					clienteDireccionVac.setCodigoUnidadGeografica(f
							.getNivel4Vacaciones());
				else if (f.getNivel3Vacaciones() != null
						&& !("".equals(f.getNivel3Vacaciones())))
					clienteDireccionVac.setCodigoUnidadGeografica(f
							.getNivel3Vacaciones());
				listDireccionCliente.add(clienteDireccionVac);

			}
			/* FIN SA PER-SiCC-2012-0365 */

			cliente.setListClienteDireccion(listDireccionCliente);
		}

		// unidad administrativa del cliente
		if (f.isMostrarUnidadAdministrativa()) {
			ClienteUnidadAdministrativa clienteUnidadAdministrativa = new ClienteUnidadAdministrativa();
			clienteUnidadAdministrativa
					.setOidTerritorioAdministrativo(new Long(f
							.getOidTerritorioAdministrativo()));
			clienteUnidadAdministrativa.setIndicadorActivo(new Integer(1));
			clienteUnidadAdministrativa.setPeriodoInicio(new Long(f
					.getOidPeriodo()));
			clienteUnidadAdministrativa.setPeriodoFin(null);
			cliente.setClienteUnidadAdministrativa(clienteUnidadAdministrativa);
		}

		// primer contacto del cliente
		ClientePrimerContacto clientePrimerContacto = new ClientePrimerContacto();
		clientePrimerContacto.setOidPeriodo(new Long(f.getOidPeriodo()));
		clientePrimerContacto.setOidMarca(oidMarca);
		clientePrimerContacto.setOidCanal(oidCanal);
		clientePrimerContacto.setCodigoTipoContacto("I");
		clientePrimerContacto.setFechaContacto(sdf.parse(fechaActual));

		String fechaSiguienteContacto = sdf
				.format(obtenerFechaAñoSiguiente(clientePrimerContacto
						.getFechaContacto()));
		clientePrimerContacto.setFechaSiguienteContacto(sdf
				.parse(fechaSiguienteContacto));

		cliente.setClientePrimerContacto(clientePrimerContacto);

		// marca del cliente
		ClienteMarca clienteMarca = new ClienteMarca();
		clienteMarca.setIndicadorPrincipal(new Integer(1));
		clienteMarca.setOidMarca(oidMarca);
		cliente.setClienteMarca(clienteMarca);

		// grabamos los tipos de comunicacion del cliente
		List listClienteComunicacion = new ArrayList();
		Integer indPrincipalComunicacion = new Integer(1);
		if (f.getTelefonoCasa() != null && !("".equals(f.getTelefonoCasa()))) {
			ClienteComunicacion clienteComunicacion = new ClienteComunicacion();
			Long oidTipoComunicacion = clienteService
					.getOidTipoComunicacion(MAE_TIPO_COMUNICACION_TELEFONO_CASA);

			clienteComunicacion.setOidTipoComunicacion(oidTipoComunicacion);
			clienteComunicacion.setTextoComunicacion(f.getTelefonoCasa());
			clienteComunicacion.setIndicadorPrincipal(indPrincipalComunicacion);
			listClienteComunicacion.add(clienteComunicacion);

			if (indPrincipalComunicacion.intValue() == 1)
				indPrincipalComunicacion = new Integer(0);
		}
		if (f.getTelefonoCelular() != null
				&& !("".equals(f.getTelefonoCelular()))) {
			ClienteComunicacion clienteComunicacion = new ClienteComunicacion();
			Long oidTipoComunicacion = clienteService
					.getOidTipoComunicacion(MAE_TIPO_COMUNICACION_TELEFONO_MOVIL);

			clienteComunicacion.setOidTipoComunicacion(oidTipoComunicacion);
			clienteComunicacion.setTextoComunicacion(f.getTelefonoCelular());
			clienteComunicacion.setIndicadorPrincipal(indPrincipalComunicacion);
			listClienteComunicacion.add(clienteComunicacion);

			if (indPrincipalComunicacion.intValue() == 1)
				indPrincipalComunicacion = new Integer(0);
		}
		if (f.getMail() != null && !("".equals(f.getMail()))) {
			ClienteComunicacion clienteComunicacion = new ClienteComunicacion();
			Long oidTipoComunicacion = clienteService
					.getOidTipoComunicacion(MAE_TIPO_COMUNICACION_MAIL);

			clienteComunicacion.setOidTipoComunicacion(oidTipoComunicacion);
			clienteComunicacion.setTextoComunicacion(f.getMail());
			clienteComunicacion.setIndicadorPrincipal(indPrincipalComunicacion);
			listClienteComunicacion.add(clienteComunicacion);

			if (indPrincipalComunicacion.intValue() == 1)
				indPrincipalComunicacion = new Integer(0);
		}
		if (f.getTelefono() != null && !("".equals(f.getTelefono()))) {
			ClienteComunicacion clienteComunicacion = new ClienteComunicacion();
			Long oidTipoComunicacion = clienteService
					.getOidTipoComunicacion(MAE_TIPO_COMUNICACION_TELEFONO_TRABAJO);

			clienteComunicacion.setOidTipoComunicacion(oidTipoComunicacion);
			clienteComunicacion.setTextoComunicacion(f.getTelefono());
			clienteComunicacion.setIndicadorPrincipal(indPrincipalComunicacion);
			listClienteComunicacion.add(clienteComunicacion);

			if (indPrincipalComunicacion.intValue() == 1)
				indPrincipalComunicacion = new Integer(0);
		}
		cliente.setListClienteComunicacion(listClienteComunicacion);

		if (f.getTelefonoReferencia() != null
				&& !("".equals(f.getTelefonoReferencia()))) {
			ClienteComunicacion clienteComunicacion = new ClienteComunicacion();
			Long oidTipoComunicacion = clienteService
					.getOidTipoComunicacion(MAE_TIPO_COMUNICACION_TELEFONO_REFERENCIA);

			clienteComunicacion.setOidTipoComunicacion(oidTipoComunicacion);
			clienteComunicacion.setTextoComunicacion(f.getTelefonoReferencia());
			clienteComunicacion.setIndicadorPrincipal(indPrincipalComunicacion);
			listClienteComunicacion.add(clienteComunicacion);

			if (indPrincipalComunicacion.intValue() == 1)
				indPrincipalComunicacion = new Integer(0);
		}
		cliente.setListClienteComunicacion(listClienteComunicacion);

		if (f.getTelefonoAdicional() != null
				&& !("".equals(f.getTelefonoAdicional()))) {
			ClienteComunicacion clienteComunicacion = new ClienteComunicacion();
			Long oidTipoComunicacion = clienteService
					.getOidTipoComunicacion(MAE_TIPO_COMUNICACION_TELEFONO_ADICIONAL);

			clienteComunicacion.setOidTipoComunicacion(oidTipoComunicacion);
			clienteComunicacion.setTextoComunicacion(f.getTelefonoAdicional());
			clienteComunicacion.setIndicadorPrincipal(indPrincipalComunicacion);
			listClienteComunicacion.add(clienteComunicacion);

			if (indPrincipalComunicacion.intValue() == 1)
				indPrincipalComunicacion = new Integer(0);
		}
		cliente.setListClienteComunicacion(listClienteComunicacion);

		if (f.getTelefonoCasaDireccionEntrega() != null
				&& !("".equals(f.getTelefonoCasaDireccionEntrega()))) {
			ClienteComunicacion clienteComunicacion = new ClienteComunicacion();
			Long oidTipoComunicacion = clienteService
					.getOidTipoComunicacion(Constants.MAE_TIPO_COMUNICACION_TELEFONO_CASA_DIRECCION_ENTREGA);

			clienteComunicacion.setOidTipoComunicacion(oidTipoComunicacion);
			clienteComunicacion.setTextoComunicacion(f
					.getTelefonoCasaDireccionEntrega());
			clienteComunicacion.setIndicadorPrincipal(new Integer(0));
			listClienteComunicacion.add(clienteComunicacion);
		}
		cliente.setListClienteComunicacion(listClienteComunicacion);

		if (f.getTelefonoCelularDireccionEntrega() != null
				&& !("".equals(f.getTelefonoCelularDireccionEntrega()))) {
			ClienteComunicacion clienteComunicacion = new ClienteComunicacion();
			Long oidTipoComunicacion = clienteService
					.getOidTipoComunicacion(Constants.MAE_TIPO_COMUNICACION_TELEFONO_CELULAR_DIRECCION_ENTREGA);

			clienteComunicacion.setOidTipoComunicacion(oidTipoComunicacion);
			clienteComunicacion.setTextoComunicacion(f
					.getTelefonoCelularDireccionEntrega());
			clienteComunicacion.setIndicadorPrincipal(new Integer(0));
			listClienteComunicacion.add(clienteComunicacion);
		}
		cliente.setListClienteComunicacion(listClienteComunicacion);

		/* INI SA PER-SiCC-2012-0365 */
		if (f.getTelefonoCasaDireccionVacaciones() != null
				&& !("".equals(f.getTelefonoCasaDireccionVacaciones()))) {
			ClienteComunicacion clienteComunicacion = new ClienteComunicacion();
			Long oidTipoComunicacion = clienteService
					.getOidTipoComunicacion(Constants.MAE_TIPO_COMUNICACION_TELEFONO_CASA_DIRECCION_VACACIONES);

			clienteComunicacion.setOidTipoComunicacion(oidTipoComunicacion);
			clienteComunicacion.setTextoComunicacion(f
					.getTelefonoCasaDireccionVacaciones());
			clienteComunicacion.setIndicadorPrincipal(new Integer(0));
			listClienteComunicacion.add(clienteComunicacion);
		}
		cliente.setListClienteComunicacion(listClienteComunicacion);

		if (f.getTelefonoCelularDireccionVacaciones() != null
				&& !("".equals(f.getTelefonoCelularDireccionVacaciones()))) {
			ClienteComunicacion clienteComunicacion = new ClienteComunicacion();
			Long oidTipoComunicacion = clienteService
					.getOidTipoComunicacion(Constants.MAE_TIPO_COMUNICACION_TELEFONO_CELULAR_DIRECCION_VACACIONES);

			clienteComunicacion.setOidTipoComunicacion(oidTipoComunicacion);
			clienteComunicacion.setTextoComunicacion(f
					.getTelefonoCelularDireccionVacaciones());
			clienteComunicacion.setIndicadorPrincipal(new Integer(0));
			listClienteComunicacion.add(clienteComunicacion);
		}
		cliente.setListClienteComunicacion(listClienteComunicacion);
		/* FIN SA PER-SiCC-2012-0365 */

		// Grabamos los vinculos del Cliente
		List listClienteVinculo = new ArrayList();
		if (f.getCodigoConsultoraRecomendante() != null
				&& !("".equals(f.getCodigoConsultoraRecomendante()))) {
			Long oidTipoVinculo = clienteService.getOidTipoVinculo(
					f.getOidPais(), Constants.MAE_TIPO_VINCULO_RECOMENDANTE);
			ClienteVinculo clienteVinculo = new ClienteVinculo();

			clienteVinculo
					.setCodigoTipoVinculo(Constants.MAE_TIPO_VINCULO_RECOMENDANTE);
			clienteVinculo.setOidClienteVinculante(new Long(f
					.getOidConsultoraRecomendante()));
			clienteVinculo.setOidTipoVinculo(oidTipoVinculo);
			clienteVinculo.setFechaDesde(sdf.parse(fechaActual));

			String fechaHasta = sdf
					.format(obtenerFechaAñoSiguiente(clienteVinculo
							.getFechaDesde()));

			clienteVinculo.setFechaHasta(sdf.parse(fechaHasta));
			clienteVinculo.setIndicadorPrincipal(new Integer(1));

			listClienteVinculo.add(clienteVinculo);
		}
		if (f.getCodigoConsultoraVinculo() != null
				&& !("".equals(f.getCodigoConsultoraVinculo()))
				&& f.isEsDuplaCyzone()) {
			Long oidTipoVinculo = clienteService.getOidTipoVinculo(
					f.getOidPais(), Constants.MAE_TIPO_VINCULO_DUPLACYZONE);
			ClienteVinculo clienteVinculo = new ClienteVinculo();

			clienteVinculo
					.setCodigoTipoVinculo(Constants.MAE_TIPO_VINCULO_DUPLACYZONE);
			clienteVinculo.setOidClienteVinculante(new Long(f
					.getOidConsultoraVinculo()));
			clienteVinculo.setOidTipoVinculo(oidTipoVinculo);
			clienteVinculo.setFechaDesde(sdf.parse(f.getFechaDesde()));
			clienteVinculo.setFechaHasta(null);
			clienteVinculo.setIndicadorPrincipal(new Integer(0));

			listClienteVinculo.add(clienteVinculo);
		}

		if (StringUtils.isNotBlank(f.getCodigoConsultoraLiderRecomendante())) {
			Long oidTipoVinculo = clienteService.getOidTipoVinculo(
					f.getOidPais(),
					Constants.MAE_TIPO_VINCULO_LIDER_RECOMENDADA);
			ClienteVinculo clienteVinculo = new ClienteVinculo();

			clienteVinculo
					.setCodigoTipoVinculo(Constants.MAE_TIPO_VINCULO_LIDER_RECOMENDADA);
			clienteVinculo.setOidClienteVinculante(new Long(f
					.getOidConsultoraLiderRecomendante()));
			clienteVinculo.setOidTipoVinculo(oidTipoVinculo);

			// Lineas arriba la fecha ya tiene la fecha actual o la fecha de
			// inicio del periodo.
			clienteVinculo.setFechaDesde(sdf.parse(fechaActual));

			String fechaHasta = sdf
					.format(obtenerFechaAñoSiguiente(clienteVinculo
							.getFechaDesde()));

			clienteVinculo.setFechaHasta(sdf.parse(fechaHasta));
			clienteVinculo.setIndicadorPrincipal(new Integer(0));

			listClienteVinculo.add(clienteVinculo);
		}

		cliente.setListClienteVinculo(listClienteVinculo);

		// grabamos los concursos y premios para el cliente nuevo
		if (f.getCodigoConsultoraRecomendante() != null
				&& !("".equals(f.getCodigoConsultoraRecomendante()))) {
			Map criteriaConcurso = new HashMap();
			criteriaConcurso.put("oidPeriodo", f.getOidPeriodo());
			criteriaConcurso.put("oidPais", f.getOidPais());
			criteriaConcurso.put("oidDirigConsul",
					Constants.MAE_OID_DIRIG_CONSUL);
			criteriaConcurso.put("oidBaseCalcu",
					Constants.MAE_OID_BASE_CALCU_RECOMEN);

			String codigoZona = clienteService.getZonaCliente(
					f.getCodigoPais(), f.getCodigoConsultoraRecomendante());
			criteriaConcurso.put("codigoZona", codigoZona);
			criteriaConcurso.put("codigoCliente",
					f.getCodigoConsultoraRecomendante());
			// request.getSession().setAttribute("codigoZonaConcurso",
			// codigoZona);

			List listConcursos = clienteService.getConcursos(criteriaConcurso);
			Iterator itConcursos = listConcursos.iterator();
			List listClienteConcursoPremio = new ArrayList();

			while (itConcursos.hasNext()) {
				Concurso concurso = (Concurso) itConcursos.next();

				// si no pertenece a la zona geografica del concurso, no se le
				// asigna dicho concurso a la consultora
				if (concurso.getTotalGeografico().intValue() > 0
						&& concurso.getCodigoZona() == null)
					continue;

				criteriaConcurso.put("oidConcurso", concurso.getOidConcurso());
				List listPremios = clienteService.getPremios(criteriaConcurso);

				// si el concurso es de mas de un Nivel, no se muestra
				// Si el Concurso de Rec. No tiene Niveles Electivos, no se
				// muestra, //Anterior: if(concurso.getNumeroNivel().intValue()
				// > 1)
				if (concurso.getNumeroNivelSelectivo().intValue() == 0) {
					ClienteConcursoPremio concursoPremio = new ClienteConcursoPremio();
					concursoPremio.setOidModulo(new Long(
							Constants.MAE_CLIENTE_MODULO_MAE));

					concursoPremio.setCodigoClienteRecomendante(f
							.getCodigoConsultoraRecomendante());
					concursoPremio.setOidConcurso(concurso.getOidConcurso());

					concursoPremio.setCodigoClienteRecomendado(cliente
							.getCodigo());
					concursoPremio.setOidPeriodo(new Long(f.getOidPeriodo()));

					listClienteConcursoPremio.add(concursoPremio);
				} else {

					// si el premio es de un nivel se graba
					if (listPremios.size() > 1)
						f.setMostrarPantallaPremios(true);

					ClienteConcursoPremio concursoPremio = new ClienteConcursoPremio();
					concursoPremio.setOidModulo(new Long(
							Constants.MAE_CLIENTE_MODULO_MAE));

					concursoPremio.setCodigoClienteRecomendante(f
							.getCodigoConsultoraRecomendante());
					concursoPremio.setOidConcurso(concurso.getOidConcurso());

					concursoPremio.setCodigoClienteRecomendado(cliente
							.getCodigo());
					concursoPremio.setOidPeriodo(new Long(f.getOidPeriodo()));

					// Seteamos a null si el indicador esta en uno
					if (StringUtils.equals(concurso.getTipoPremio(),
							Constants.ESTADO_ACTIVO)) {
						concursoPremio.setOidNivelPremio(null);
						concursoPremio.setNumeroPremio(null);
					} else {
						Premio premio = (Premio) listPremios.get(0);
						concursoPremio.setOidNivelPremio(premio.getOidPremio());
						concursoPremio
								.setNumeroPremio(premio.getNumeroPremio());
					}

					listClienteConcursoPremio.add(concursoPremio);
				}
			}

			// OCULTAR LA PANTALLA DE PREMIOS
			if (listConcursos != null && listConcursos.size() > 0) {
				// verificamos si el indicador TPRM_OID_TIPO_PION tipopremio
				// esta en 1
				// TODOS los registros de la lista van a tenr el mismo valor
				Concurso concurso = (Concurso) listConcursos.get(0);

				// No se muestra la pantalla de premios
				if (StringUtils.equals(concurso.getTipoPremio(),
						Constants.ESTADO_ACTIVO)) {
					f.setMostrarPantallaPremios(false);
				}
			}
			//

			cliente.setListClienteConcursoPremio(listClienteConcursoPremio);

		}

		// seteamos la referencia del cliente
		// seteamos campos de ubigeo dela refrencia

		/**** DEMASIADO IMPORTANTE FALTA REVISAR */
		// f.setCodigoProvinciaAval(request.getParameter("codigoUbigeo2"));
		// f.setCodigoDistritoAval(request.getParameter("codigoUbigeo3"));
		setClienteReferencia(cliente, f);

		// recuperamos la lista de Consultoras Avaladas
		if (f.isAprobarAvaladas()) {
			List listConsultorasDeudoras = maeDeudorasList;

			cliente.setListClienteAval(listConsultorasDeudoras);
		}

		return cliente;
	}

	private void updateTiposClasificaciones(List detalList) {
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		List listSubTipoClientes = new ArrayList();

		Iterator it = detalList.iterator();
		while (it.hasNext()) {
			ClienteSubTipo clienteSubTipo = (ClienteSubTipo) it.next();
			listSubTipoClientes.add(clienteSubTipo.getOidSubTipoCliente());
		}

		Map criteria = new HashMap();
		criteria.put("listSubTipoClientes", listSubTipoClientes);
		siccTipoDocumentoList = clienteService
				.getTiposClasificaciones(criteria);
		siccTipoClasificacion = clienteService
				.getTiposClasificaciones(criteria);
		// request.getSession().setAttribute(Constants.SICC_TIPO_CLASIFICACION_LIST,
		// clienteService.getTiposClasificaciones(criteria));
	}

	private void setearValorPorDefectoPorSubTipoCliente(
			MantenimientoMAEClienteService clienteService,
			MantenimientoMAEClienteForm f, String oidSubTipoCliente) {

		Map mapRespuesta = null;
		String indObligatorio = null;
		String oidCampo;

		// Colocamos valores por Defecto para Nacionalidad
		List listNacionalidades = maeClienteClasificacionList;
		mapRespuesta = setearValorDefaultCampo(clienteService, f.getOidPais(),
				oidSubTipoCliente, Constants.MAE_OID_CONFI_CAMPO_NACIONALIDAD,
				listNacionalidades);
		indObligatorio = (String) mapRespuesta.get("indicadorObligatorio");
		oidCampo = (String) mapRespuesta.get("oidCampo");

		if ((indObligatorio != null)) {
			if ("1".equals(indObligatorio))
				f.setIndicadoObligatorioNacionalidad(true);
			else
				f.setIndicadoObligatorioNacionalidad(false);

			f.setNacionalidad(oidCampo);
		}

		// Colocamos valores por Defecto para Nacionalidad
		List listNivelEstudios = maeClienteNivelEstudioList;
		mapRespuesta = setearValorDefaultCampo(clienteService, f.getOidPais(),
				oidSubTipoCliente,
				Constants.MAE_OID_CONFI_CAMPO_GRADO_INSTRUCCION,
				listNivelEstudios);
		indObligatorio = (String) mapRespuesta.get("indicadorObligatorio");
		oidCampo = (String) mapRespuesta.get("oidCampo");

		if ((indObligatorio != null)) {
			if ("1".equals(indObligatorio))
				f.setIndicadoObligatorioGradoInstruccion(true);
			else
				f.setIndicadoObligatorioGradoInstruccion(false);

			f.setGradoInstruccion(oidCampo);
		}

		// Colocamos valores por Defecto para Tratamiento
		List listTratamientos = maeClienteTratamientoList;
		mapRespuesta = setearValorDefaultCampo(clienteService, f.getOidPais(),
				oidSubTipoCliente, Constants.MAE_OID_CONFI_CAMPO_TRATAMIENTO,
				listTratamientos);
		indObligatorio = (String) mapRespuesta.get("indicadorObligatorio");
		oidCampo = (String) mapRespuesta.get("oidCampo");

		if ((indObligatorio != null)) {
			if ("1".equals(indObligatorio))
				f.setIndicadoObligatorioTratamiento(true);
			else
				f.setIndicadoObligatorioTratamiento(false);

			if (!oidCampo.equals(""))
				f.setTratamiento(oidCampo);
		}
	}

	private Map setearValorDefaultCampo(
			MantenimientoMAEClienteService clienteService, String oidPais,
			String oidSubTipoCliente, String oidCampoConfiguracion,
			List listCampos) {
		String campoDefault = clienteService.getValorConfiCampoSubTipoCliente(
				oidPais, oidSubTipoCliente, oidCampoConfiguracion);
		Map mapRespuesta = new HashMap();

		log.debug(" campoDefault(" + oidCampoConfiguracion + ": "
				+ campoDefault);

		if (campoDefault != null) {
			StringTokenizer st = new StringTokenizer(campoDefault, "-");
			String indicadorObligatorio = st.nextToken();
			String oidCampo = "";
			String valorDefault = st.nextToken().toUpperCase();

			for (int i = 0; i < listCampos.size(); i++) {
				Base base = (Base) listCampos.get(i);

				if (base.getDescripcion().toUpperCase().equals(valorDefault)) {
					oidCampo = base.getCodigo();
					break;
				}
			}

			mapRespuesta.put("indicadorObligatorio", indicadorObligatorio);
			mapRespuesta.put("oidCampo", oidCampo);
		}

		return mapRespuesta;
	}

	private void validarMostrarLiderRecomendante(List detalList,
			MantenimientoMAEClienteService clienteService,
			MantenimientoMAEClienteForm f) {

		String moduloValidacion = clienteService
				.getValorModuloxPaisTipoValidacion(mPantallaPrincipalBean
						.getCurrentCountry().getCodigo(),
						Constants.MAE_VALID_LIDER_RECOM);

		f.setMostrarConsultoraLiderRecomendante(false);

		if (detalList != null && detalList.size() > 0) {
			for (int i = 0; i < detalList.size(); i++) {
				ClienteSubTipo clienteSubTipo = (ClienteSubTipo) detalList
						.get(i);
				if (StringUtils.equals(moduloValidacion,
						Constants.MAE_LIDER_RECOM)
						&& (StringUtils
								.equals(clienteSubTipo
										.getCodigoSubTipoCliente(),
										Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_NEGOCIO) || StringUtils
								.equals(clienteSubTipo
										.getCodigoSubTipoCliente(),
										Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_OFICINA))) {
					f.setMostrarConsultoraLiderRecomendante(true);
					break;
				}
			}
		}

	}

	/**
	 * metodo auxiliar que permite recuperar el tipo de cliente agregado por el
	 * usuario
	 * 
	 * @param f
	 * @param session
	 * @return
	 */
	private ClienteSubTipo getSubTipoCliente(MantenimientoMAEClienteForm f) {
		String subTipoCliente = f.getSubTipoCliente();

		StringTokenizer st = new StringTokenizer(subTipoCliente, "-");

		ClienteSubTipo clienteSubTipo = new ClienteSubTipo();
		clienteSubTipo.setCodigoTipoCliente(st.nextToken());
		clienteSubTipo.setCodigoSubTipoCliente(st.nextToken());
		clienteSubTipo.setOidTipoCliente(new Long(st.nextToken()));
		clienteSubTipo.setOidSubTipoCliente(new Long(st.nextToken()));

		StringTokenizer st2 = new StringTokenizer(st.nextToken(), "/");
		clienteSubTipo.setDescripcionTipoCliente(st2.nextToken());

		if (st2.hasMoreTokens())
			clienteSubTipo.setDescripcionSubTipoCliente(st2.nextToken());

		return clienteSubTipo;
	}

	private void obtenerCriteriosBusqueda(Cliente cliente,
			MantenimientoMAEClienteForm f) {
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

		String criterioBusqueda1 = clienteService.getCriterioBusqueda1(f
				.getOidPais());
		String criterioBusqueda2 = clienteService.getCriterioBusqueda2(f
				.getOidPais());
		String resultado1 = null;
		String resultado2 = null;

		log.debug("criterioBusqueda1 : " + criterioBusqueda1);
		log.debug("criterioBusqueda2 : " + criterioBusqueda2);

		if (criterioBusqueda1 != null) {
			if (criterioBusqueda1.equals("MAECLIEAPELL1"))
				resultado1 = cliente.getApellido1();
			if (criterioBusqueda1.equals("MAECLIEAPELL2"))
				resultado1 = cliente.getApellido2();
			if (criterioBusqueda1.equals("MAECLIEAPELLCA"))
				resultado1 = cliente.getApellidoCasada();
			if (criterioBusqueda1.equals("MAECLIENOM1"))
				resultado1 = cliente.getNombre1();
			if (criterioBusqueda1.equals("MAECLIENOM2"))
				resultado1 = cliente.getNombre2();
			if (criterioBusqueda1.equals("MAECLIETRAT"))
				resultado1 = cliente.getTratamiento();
			if (criterioBusqueda1.equals("MAECLIESEXO"))
				resultado1 = cliente.getSexo();
			if (criterioBusqueda1.equals("MAECLIEFECHING"))
				resultado1 = String
						.valueOf(cliente.getFechaIngreso().getTime());
			if (criterioBusqueda1.equals("MAECLIEFORMPA"))
				resultado1 = cliente.getOidFormaPago().toString();
		}

		if (criterioBusqueda2 != null) {
			if (criterioBusqueda2.equals("MAECLIEAPELL1"))
				resultado2 = cliente.getApellido1();
			if (criterioBusqueda2.equals("MAECLIEAPELL2"))
				resultado2 = cliente.getApellido2();
			if (criterioBusqueda2.equals("MAECLIEAPELLCA"))
				resultado2 = cliente.getApellidoCasada();
			if (criterioBusqueda2.equals("MAECLIENOM1"))
				resultado2 = cliente.getNombre1();
			if (criterioBusqueda2.equals("MAECLIENOM2"))
				resultado2 = cliente.getNombre2();
			if (criterioBusqueda2.equals("MAECLIETRAT"))
				resultado2 = cliente.getTratamiento();
			if (criterioBusqueda2.equals("MAECLIESEXO"))
				resultado2 = cliente.getSexo();
			if (criterioBusqueda2.equals("MAECLIEFECHING"))
				resultado2 = String
						.valueOf(cliente.getFechaIngreso().getTime());
			if (criterioBusqueda2.equals("MAECLIEFORMPA"))
				resultado2 = cliente.getOidFormaPago().toString();
		}

		log.debug("resultado1 : " + resultado1);
		log.debug("resultado2 : " + resultado2);

		cliente.setCriterioBusqueda1(resultado1);
		cliente.setCriterioBusqueda2(resultado2);
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}
		MantenimientoMAEClienteForm f = (MantenimientoMAEClienteForm) formBusqueda;

		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

		Map criteria = BeanUtils.describe(formBusqueda);
		List detalListSubTipo = maeClienteSubtipoList;
		for (int i = 0; i < detalListSubTipo.size(); i++) {
			ClienteSubTipo clienteSubTipo = (ClienteSubTipo) detalListSubTipo
					.get(i);
			String tipo = clienteSubTipo.getCodigoTipoCliente();
			String subtipo = clienteSubTipo.getCodigoSubTipoCliente();
			if ((tipo.equals(Constants.MAE_CODIGO_TIPO_CONSULTORA) && subtipo
					.equals(Constants.MAE_CODIGO_SUBTIPO_OFICINA))
					|| (tipo.equals(Constants.MAE_TIPO_CLIENTE_GERENTE) && subtipo
							.equals(Constants.MAE_SUBTIPO_GERENTE_REGION))
					|| (tipo.equals(Constants.MAE_TIPO_CLIENTE_GERENTE) && subtipo
							.equals(Constants.MAE_SUBTIPO_GERENTE_ZONA))) {
				String codigoCUB = f.getCodigoCUB();
				if (StringUtils.isBlank(codigoCUB)) {
					addInfo("Mensaje",
							getResourceMessage("mantenimientoMAEClienteForm.msg.codigoCUBRequerido"));

					return false;
				}
			}
		}

		if (f.isEsDuplaCyzone()) {
			// lo actualizamos con el codigo de cliente
			criteria.put("numeroDocumentoIdentidad", f.getCodigoCliente());
		}

		maeFlagDeudoras = Constants.NUMERO_CERO;
		boolean flagValidarDocumento = validarDocumentoIdentidad(
				clienteService, criteria, f);
		if (!flagValidarDocumento) {
			String mensajeError = (String) criteria.get("mensajeError");
			log.debug("mensajeError " + mensajeError);
			if (StringUtils.isNotEmpty(mensajeError)) {
				List listConsultorasDeudoras = (List) criteria
						.get("listDeudoras");// clienteService.getListConsultorasDeudorasAval(criteria);
				maeDeudorasList = listConsultorasDeudoras;
				maeDeudorasList = listConsultorasDeudoras;
				maeFlagDeudoras = Constants.NUMERO_UNO;
			}
			// Actualizamos las listas de ubigeo
			actualizarListarUbigeo(f);
			return false;
		}

		/* INI SA PER-SiCC-2012-0367 */
		// Obtenemos los datos del usuario Logueado
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();

		if (f.isValidarEstatusComercial()) {

			String host = this.getRequest().getRemoteHost();
			String resultado = validarBoletinComercial(f.getCodigoPais(),
					f.getNumeroDocumentoIdentidad(), host, usuario.getLogin());
			if (resultado.equals(Constants.MAE_WEBSERVICE_RESULTADO_ERROR)) {
				addInfo("Mensaje",
						getResourceMessage("mantenimientoMAEClienteForm.msg.errorWebServiceBoletinComercial"));
				return false;
			} else if (resultado
					.equals(Constants.MAE_WEBSERVICE_RESULTADO_ERROR2)) {
				// messages.add(ActionMessages.GLOBAL_MESSAGE, new
				// ActionMessage("mantenimientoMAEClienteForm.msg.errorWebServiceBoletinComercial2"));
				addInfo("Mensaje",
						getResourceMessage("mantenimientoMAEClienteForm.msg.errorWebServiceBoletinComercial2"));
				return false;
			} else { // Si la Consultora es Marcado como Roja
				if (resultado.equals("07") || resultado.equals("08")
						|| resultado.equals("09") || resultado.equals("10")
						|| resultado.equals("11") || resultado.equals("12")) {

					addInfo("Mensaje",
							getResourceMessage("mantenimientoMAEClienteForm.msg.consultoraRojaBoletinComercial"));
					String texto = getResourceMessage("mantenimientoMAEClienteForm.msg.consultoraRojaBoletinComercial");
					f.setMensajeConsultoraRoja(texto);

					actualizarListarUbigeo(f);
					return true;
				}
			}
		}
		/* FIN SA PER-SiCC-2012-0367 */
		String codigoUbigeo2 = "";
		String codigoUbigeo3 = "";
		codigoUbigeo2 = this.getParametrosPantalla().get("codigoUbigeo2");
		codigoUbigeo3 = this.getParametrosPantalla().get("codigoUbigeo3");
		// request.setAttribute("codigoUbigeo2",
		// request.getParameter("codigoUbigeo2"));
		// request.setAttribute("codigoUbigeo3",
		// request.getParameter("codigoUbigeo3"));

		if (!f.isPermitirModificarUbigeo()) {
			f.setNivel1(f.getCodNivel1());
			f.setNivel2(f.getCodNivel2());
			f.setNivel3(f.getCodNivel3());
			f.setNivel4(f.getCodNivel4());
			f.setNivel5(f.getCodNivel5());
			f.setNivel6(f.getCodNivel6());
		}

		// Obtenemos la unidad geografica de la direccion principal
		String codigoUbigeo = "";
		if (f.getNivel6() != null && !("".equals(f.getNivel6())))
			codigoUbigeo = f.getNivel6();
		else if (f.getNivel5() != null && !("".equals(f.getNivel5())))
			codigoUbigeo = f.getNivel5();
		else if (f.getNivel4() != null && !("".equals(f.getNivel4())))
			codigoUbigeo = f.getNivel4();
		else if (f.getNivel3() != null && !("".equals(f.getNivel3())))
			codigoUbigeo = f.getNivel3();
		criteria.put("codigoUbigeo", codigoUbigeo);
		criteria.put("listSubTipo", detalListSubTipo);

		List erroresEncontrados = clienteService.validarDatosCliente(criteria);
		if (erroresEncontrados.size() > 0) {
			Iterator it = erroresEncontrados.iterator();
			while (it.hasNext()) {
				String error = (String) it.next();

				if (error
						.equals("mantenimientoMAEClienteForm.msg.TerritorioNoCorrespondeDistrito")) {

					f.setMensajeConfirmacion(getResourceMessage(error));

					// Actualizamos las listas de ubigeo
					actualizarListarUbigeo(f);

					return true;
				}

				addInfo("Mensaje", getResourceMessage(error));

			}

			// Actualizamos las listas de ubigeo
			actualizarListarUbigeo(f);

			return false;
		} else {
			f.setOidTerritorioAdministrativo((String) criteria
					.get("oidTerritorioAdministrativo"));
			f.setOidTerritorio((String) criteria.get("oidTerritorio"));
		}

		List detalListClasificacion = maeClienteClasificacionList;

		/* INI SA PER-SiCC-2013-0265 */
		if (existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_HIJADUPLA,
				Constants.MAE_SUBTIPO_HIJADUPLA_HIJADUPLA, detalListSubTipo)) {
			String oidTipoDocDuplaCyzone = clienteService
					.getTipoDocumentoDuplaCyzone(f.getOidPais());
			if (!f.getTipoDocumentoIdentidad().equals(oidTipoDocDuplaCyzone)) {
				addError(
						"Mensaje",
						getResourceMessage("mantenimientoMAEClienteForm.msg.validarTipoDocDuplaCyzone"));
				return false;
			}

			if (detalListSubTipo.size() > 1) {
				addError(
						"Mensaje",
						getResourceMessage("mantenimientoMAEClienteForm.msg.validarSubTipo.hijaDupla"));
				return false;
			}

			if (f.isMostrarUnidadAdministrativa()) {
				addError(
						"Mensaje",
						getResourceMessage("mantenimientoMAEClienteForm.msg.validarUnidadAdministrativa.hijaDupla"));

				return false;
			}
		}
		if (existeSubTipoClienteAux(Constants.MAE_TIPO_CLIENTE_CONSULTORA,
				Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_NEGOCIO,
				detalListSubTipo)) {
			if (detalListSubTipo.size() > 1) {

				addError(
						"Mensaje",
						getResourceMessage("mantenimientoMAEClienteForm.msg.validarSubTipo.consultoraNegocio"));
				return false;
			}
		}

		if (existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_GERENTE,
				Constants.MAE_SUBTIPO_GERENTE_REGION, detalListSubTipo)
				|| existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_GERENTE,
						Constants.MAE_SUBTIPO_GERENTE_ZONA, detalListSubTipo)) {
			boolean valido = false;

			if (existeSubTipoClienteAux(Constants.MAE_TIPO_CLIENTE_CONSULTORA,
					Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_OFICINA,
					detalListSubTipo)) {
				if (detalListSubTipo.size() == 2) {
					valido = true;
				}
			}
			if (!valido) {
				addError(
						"Mensaje",
						getResourceMessage("mantenimientoMAEClienteForm.msg.validarSubTipo.Gerente"));
				return false;
			}
		}

		if ((existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_CONSULTORA,
				Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_NEGOCIO,
				detalListSubTipo))
				|| (existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_CONSULTORA,
						Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_OFICINA,
						detalListSubTipo))
				|| (existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_GERENTE,
						Constants.MAE_SUBTIPO_GERENTE_REGION, detalListSubTipo))
				|| (existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_GERENTE,
						Constants.MAE_SUBTIPO_GERENTE_ZONA, detalListSubTipo))) {
			if (!f.isMostrarUnidadAdministrativa()) {
				addError(
						"Mensaje",
						getResourceMessage("mantenimientoMAEClienteForm.msg.validarNivelRiesgo"));
				actualizarListarUbigeo(f);

				return false;
			}

			String oidTipoDocDuplaCyzone = clienteService
					.getTipoDocumentoDuplaCyzone(f.getOidPais());
			if (f.getTipoDocumentoIdentidad().equals(oidTipoDocDuplaCyzone)) {
				addError(
						"Mensaje",
						getResourceMessage("mantenimientoMAEClienteForm.msg.validarTipoDocConsultora"));
				return false;
			}
		} else {
			String oidTipoDocDuplaCyzone = clienteService
					.getTipoDocumentoDuplaCyzone(f.getOidPais());
			if (!f.getTipoDocumentoIdentidad().equals(oidTipoDocDuplaCyzone)) {
				addError(
						"Mensaje",
						getResourceMessage("mantenimientoMAEClienteForm.msg.validarTipoDocDuplaCyzone"));
				actualizarListarUbigeo(f);
				return false;
			}
		}
		/* FIN SA PER-SiCC-2013-0265 */

		boolean bGrabarCab = false;
		try {

			Cliente cliente = obtenerCliente(f, detalListSubTipo,
					detalListClasificacion);

			log.debug("Inserting...");
			clienteService.insertCliente(cliente);
			f.setCodigoCliente(cliente.getCodigo());
			f.setDigitoControl(cliente.getDigitoControl());

			// Verificamos si para el pais se tiene que mostrar el digito de
			// control
			String mostrarDigitoControl = clienteService
					.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
							Constants.MAE_MOSTRAR_DIGITO_CONTROL);
			if (mostrarDigitoControl != null) {
				addInfo("Mensaje",
						getResourceMessage("mantenimientoMAEClienteForm.added"
								+ cliente.getCodigo() + "-"
								+ cliente.getDigitoControl()));
			} else {
				addInfo("Mensaje",
						getResourceMessage("mantenimientoMAEClienteForm.added")
								+ cliente.getCodigo());
			}

			if (mostrarDigitoControl != null) {
				f.setMensajeGrabarAlert(getResourceMessage("mantenimientoMAEClienteForm.added")
						+ cliente.getCodigo()
						+ "-"
						+ cliente.getDigitoControl());
			} else {
				f.setMensajeGrabarAlert(getResourceMessage("mantenimientoMAEClienteForm.added")
						+ cliente.getCodigo());
			}

			f.setGraboOK(true);
			bGrabarCab = true;
			f.setOidNuevoCliente(cliente.getOid().toString());

		} catch (Exception e) {
			e.printStackTrace();
			bGrabarCab = false;
			addInfo(this
					.getResourceMessage("mantenimientoEDULocal.cabecera.error"),
					e.getMessage());

			// Actualizamos las listas de ubigeo
			actualizarListarUbigeo(f);

		}

		return bGrabarCab;
	}

	private void setearCamposModuloPais(MantenimientoMAEClienteForm f,
			MantenimientoMAEClienteService clienteService,
			AjaxService ajaxService) {
		String permitirModificarUbigeo = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_UBIGEO);
		String mostrarTipoVia = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_TIPO_VIA);
		String mostrarNumeroPrincipal = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_NUMERO_PRINCIPAL);
		String mostrarUbigeoEntrega = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_DIRECCION_ENTREGA);

		String noPermitirCompletarCerosIdentidad = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_COMPLETA_CEROS_IDENTIDAD);
		String mostrarSegundoDocumento = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_SEGUNDO_DOCUMENTO_IDENTIDAD);

		String validarCaractererNV1 = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_CARACTER_NOVALIDO1);
		String validarCaractererNV2 = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_CARACTER_NOVALIDO2);
		String validarCaractererNV3 = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_CARACTER_NOVALIDO3);
		String validarCaracteresDocIdent = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_CARACTER_DOCUMENTO_IDENTIDAD);

		String validarBarrio = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_BARRIO);
		String noPermitirComenzarCerosIdentidad = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.VAL_IDENT_CERO);

		/* INI SA PER-SiCC-2012-0459 */
		String mostrarCiudad = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_CIUDAD);
		String mostrarVillaPoblacion = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_VILLA);
		/* FIN SA PER-SiCC-2012-0459 */

		/* INI SA PER-SiCC-2012-0365 */
		String mostrarDireccionVacaciones = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_DIRECCION_VACACIONES);
		/* INI SA PER-SiCC-2012-0365 */

		/* INI SA PER-SiCC-2012-0367 */
		String validarEstatusComercial = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_ESTATUS_COMERCIAL);
		/* FIN SA PER-SiCC-2012-0367 */

		/* INI SA COS-SiCC-2013-0031 */
		String indicadorPaqueteDocumentario = clienteService
				.getValorModuloxPaisTipoValidacion(f.getCodigoPais(),
						Constants.MAE_VALID_PAQUE_DOCUM);
		/* FIN SA COS-SiCC-2013-0031 */

		if (permitirModificarUbigeo != null)
			f.setPermitirModificarUbigeo(false);

		if (mostrarTipoVia != null) {
			f.setMostrarTipoVia(false);
			f.setTipoVia(clienteService.getOidTipoVia(mostrarTipoVia)
					.toString());
			f.setTipoViaCT(clienteService.getOidTipoVia(mostrarTipoVia)
					.toString());
		}

		if (mostrarNumeroPrincipal != null)
			f.setMostrarNumeroPrincipal(false);

		if (mostrarUbigeoEntrega != null)
			f.setMostrarUbigeoEntrega(false);

		if (noPermitirCompletarCerosIdentidad != null)
			f.setPermitirCompletarCerosIdentidad(false);

		if (mostrarSegundoDocumento != null) {
			f.setMostrarSegundoDocumento(true);
			f.setTipoDocumentoIdentidad2(clienteService
					.getSegundoTipoDocumento(f.getOidPais()));

			// Actualizamos la longitud del tipo de documento de identidad
			f.setLongitudTipoDocumento2(ajaxService.getLongitudTipoDocumento(
					f.getOidPais(), f.getTipoDocumentoIdentidad2()));
		}

		if (validarCaractererNV1 != null) {
			f.setValidarCaracteres1(validarCaractererNV1);
			f.setCadenaCaracteresV1(clienteService
					.getCaracteresxModuloValidacion(
							Constants.MAE_VALID_CARACTER_NOVALIDO1, "S"));
			f.setCadenaCaracteresNV1(clienteService
					.getCaracteresxModuloValidacion(
							Constants.MAE_VALID_CARACTER_NOVALIDO1, "N"));
		}
		if (validarCaractererNV2 != null) {
			f.setValidarCaracteres2(validarCaractererNV2);
			f.setCadenaCaracteresV2(clienteService
					.getCaracteresxModuloValidacion(
							Constants.MAE_VALID_CARACTER_NOVALIDO2, "S"));
			f.setCadenaCaracteresNV2(clienteService
					.getCaracteresxModuloValidacion(
							Constants.MAE_VALID_CARACTER_NOVALIDO2, "N"));
		}
		if (validarCaractererNV3 != null) {
			f.setValidarCaracteres3(validarCaractererNV3);
			f.setCadenaCaracteresV3(clienteService
					.getCaracteresxModuloValidacion(
							Constants.MAE_VALID_CARACTER_NOVALIDO3, "S"));
			f.setCadenaCaracteresNV3(clienteService
					.getCaracteresxModuloValidacion(
							Constants.MAE_VALID_CARACTER_NOVALIDO3, "N"));
		}
		if (validarCaracteresDocIdent != null) {
			f.setValidarCaracteresIdentidad(validarCaracteresDocIdent);
			f.setCadenaCaracteresVIdentidad(clienteService
					.getCaracteresxModuloValidacion(
							Constants.MAE_VALID_CARACTER_DOCUMENTO_IDENTIDAD,
							"S"));
			f.setCadenaCaracteresNVIdentidad(clienteService
					.getCaracteresxModuloValidacion(
							Constants.MAE_VALID_CARACTER_DOCUMENTO_IDENTIDAD,
							"N"));
		}

		if (validarBarrio != null)
			f.setMostrarBarrio(true);

		if (noPermitirComenzarCerosIdentidad != null)
			f.setPermitirComenzarCerosIdentidad(false);

		/* INI SA PER-SiCC-2012-0459 */
		if (mostrarCiudad != null)
			f.setMostrarCiudad(true);

		if (mostrarVillaPoblacion != null)
			f.setMostrarVillaPoblacion(true);
		/* FIN SA PER-SiCC-2012-0459 */

		/* INI SA PER-SiCC-2012-0365 */
		if (mostrarDireccionVacaciones != null)
			f.setMostrarDireccionVacaciones(true);
		/* INI SA PER-SiCC-2012-0365 */

		/* INI SA PER-SiCC-2012-0367 */
		if (validarEstatusComercial != null)
			f.setValidarEstatusComercial(true);
		/* FIN SA PER-SiCC-2012-0367 */

		/* INI SA COS-SiCC-2013-0031 */
		if (Constants.MAE_IMPRI_PAQUE_DOCUM
				.equals(indicadorPaqueteDocumentario))
			f.setMostrarIndicadorImpresionPaqDoc(true);
		/* FIN SA COS-SiCC-2013-0031 */

	}

	private boolean existeClasificacionCliente(Long oidClasificacion,
			List detalList) {
		Iterator it = detalList.iterator();

		while (it.hasNext()) {
			ClienteClasificacion ccd = (ClienteClasificacion) it.next();

			if (ccd.getOidClasificacion().equals(oidClasificacion)) {
				return true;
			}
		}
		return false;
	}

	private ClienteClasificacion getClasificacionCliente(
			MantenimientoMAEClienteForm f) {
		String tipoClasificacion = f.getTipoClasificacion();
		String clasificacion = f.getClasificacion();

		StringTokenizer st = new StringTokenizer(tipoClasificacion, "-");

		ClienteClasificacion clienteClasificacion = new ClienteClasificacion();
		clienteClasificacion.setOidClienteSubTipo(new Long(st.nextToken()));
		clienteClasificacion.setOidTipoClasificacion(new Long(st.nextToken()));
		clienteClasificacion.setDescripcionTipoClasificacion(st.nextToken());

		StringTokenizer st2 = new StringTokenizer(clasificacion, "-");
		clienteClasificacion.setOidClasificacion(new Long(st2.nextToken()));
		clienteClasificacion.setDescripcionClasificacion(st2.nextToken());

		return clienteClasificacion;
	}

	private List getDetalClasificacionList() {
		List list = maeClienteClasificacionList;

		if (list == null) {
			list = new ArrayList();
		}
		maeClienteClasificacionList = list;
		// session.setAttribute(Constants.MAE_CLIENTE_CLASIFICACION_LIST,list);
		return list;
	}

	public void addDetalClasificacion() {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'addDetalClasificacion' method");
		}

		MantenimientoMAEClienteForm f = (MantenimientoMAEClienteForm) formBusqueda;

		List detalList = getDetalClasificacionList();
		log.debug("formulario  :  " + f);
		ClienteClasificacion clienteClasificacion = getClasificacionCliente(f);

		if (!existeClasificacionCliente(
				clienteClasificacion.getOidClasificacion(), detalList)) {
			if (detalList.size() == 0)
				clienteClasificacion.setIndicadorPrincipal(new Integer(1));
			else
				clienteClasificacion.setIndicadorPrincipal(new Integer(0));

			log.debug("- seteando lista : "
					+ clienteClasificacion.getDescripcionTipoClasificacion()
					+ "/" + clienteClasificacion.getDescripcionClasificacion());
			detalList.add(clienteClasificacion);
			log.debug("lista size : " + detalList.size());
		} else {
			String cadError = clienteClasificacion
					.getDescripcionTipoClasificacion()
					+ "/"
					+ clienteClasificacion.getDescripcionClasificacion();
			addInfo("Info",
					getResourceMessage("mantenimientoMAEClienteForm.msg.ClasificacionExiste"));

		}

		// Actualizamos las listas de ubigeo
		actualizarListarUbigeo(f);

	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {

		return null;
	}

	public void validarZonaTerritorio() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validarZonaTerritorio' method");
		}
		MantenimientoMAEClienteForm f = (MantenimientoMAEClienteForm) formBusqueda;
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		Map criteria = BeanUtils.describe(f);
		List resultados = clienteService.validarZonaTerritorio(criteria);
		String result = (String) resultados.get(0);

		f.setCodNivel1("");
		f.setCodNivel2("");
		f.setCodNivel3("");
		f.setCodNivel4("");
		f.setCodNivel5("");
		f.setCodNivel6("");

		LabelValue[] periodos = clienteService
				.getPeriodosVigentesByPaisMarcaCanal(criteria);

		if (result.equals("ok")) {
			Map mapUbigeo = (Map) resultados.get(1);
			f.setNivel1((String) mapUbigeo.get("ubigeo1"));
			f.setCodNivel1(f.getNivel1());
			maeClienteNivel2List = ajaxService.getUnidadesGeograficas(
					f.getOidPais(), f.getNivel1());

			if ((String) mapUbigeo.get("ubigeo2") != null) {
				f.setCodNivel2(f.getNivel1()
						+ (String) mapUbigeo.get("ubigeo2"));
				f.setNivel2(f.getCodNivel2());

			}

			maeClienteNivel3List = ajaxService.getUnidadesGeograficas(
					f.getOidPais(), f.getNivel2());

			if ((String) mapUbigeo.get("ubigeo3") != null) {
				f.setCodNivel3(f.getCodNivel2()
						+ (String) mapUbigeo.get("ubigeo3"));
				f.setNivel3(f.getCodNivel3());
			}
			maeClienteNivel4List = ajaxService.getUnidadesGeograficas(
					f.getOidPais(), f.getNivel3());

			if ((String) mapUbigeo.get("ubigeo4") != null) {
				f.setCodNivel4(f.getCodNivel3()
						+ (String) mapUbigeo.get("ubigeo4"));
				f.setNivel4(f.getCodNivel4());
			}

			if ((String) mapUbigeo.get("ubigeo5") != null) {
				f.setCodNivel5(f.getCodNivel4()
						+ (String) mapUbigeo.get("ubigeo5"));
				f.setNivel5(f.getCodNivel5());
			}
			if ((String) mapUbigeo.get("ubigeo6") != null) {
				f.setCodNivel6(f.getCodNivel5()
						+ (String) mapUbigeo.get("ubigeo6"));
				f.setNivel6(f.getCodNivel6());
			}
			// Si se aplica calculo de periodo de ingreso en base al cierre de
			// Region,
			boolean esPaisCalculoPeriodoIngreso = clienteService
					.esPaisCalculaPeriodoIngreso(f.getCodigoPais());
			if (esPaisCalculoPeriodoIngreso)
				obtenerPeriodoActual(clienteService, f, periodos);

			/* INI SA PER-SiCC-2012-0365 */
			
			
			f.setNivel1CT(f.getNivel1());
			f.setNivel2CT(f.getNivel2());
			f.setNivel3CT(f.getNivel3());
			f.setNivel4CT(f.getNivel4());
			f.setNivel5CT(f.getNivel5());
			f.setNivel5CT(f.getNivel6());
			
			
			
			f.setActualizaUbigeoDirecciones(true);
			/* FIN SA PER-SiCC-2012-0365 */

			f.setControlFoco(MAE_CONTROL_PERIODO);
		} else {
			Iterator it = resultados.iterator();
			while (it.hasNext()) {
				String error = (String) it.next();
				addError("Mensaje", getResourceMessage(error));
			}
			f.setControlFoco(MAE_CONTROL_ZONA);
			siccPeriodoList = periodos;

		}
		// Actualizamos las listas de ubigeo
		actualizarListarUbigeo(f);

	}

	public void deleteDetalClasificacionCliente() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deleteDetalClasificacionCliente' method");
		}

		Base b = (Base) beanRegistroTipoClienteSeleccionado;
		int index = maeClienteClasificacionList
				.indexOf((Base) beanRegistroTipoClienteSeleccionado);
		MantenimientoMAEClienteForm f = (MantenimientoMAEClienteForm) formBusqueda;

		String id = ""; // importantee request.getParameter("id");
		List detalList = maeClienteClasificacionList;

		detalList.remove(Integer.parseInt(id) - 1);

		// Actualizamos las listas de ubigeo
		actualizarListarUbigeo(f);
	}

	public void rechazoSolicitudCredito() {
		if (log.isDebugEnabled())
			log.debug("Entering 'procesar' method");
		MantenimientoMAEClienteForm f = (MantenimientoMAEClienteForm) formBusqueda;

		// Obtenemos los datos del usuario Logueado
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();

		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

		Map params = new HashMap();
		params.put("codigoPais", f.getCodigoPais());
		params.put("codigoTipoDocumento", Constants.STO_TIPO_DOCUMENTO_SCC);

		// obtenemos el numero de lote, y lo actualizamos
		Map criteriaInterfaz = new HashMap();
		criteriaInterfaz.put("codigoPais", f.getCodigoPais());
		criteriaInterfaz.put("codigoDocumento",
				Constants.STO_TIPO_DOCUMENTO_SCC);
		String numeroLote = interfazSiCCService.getNumLoteSTO(criteriaInterfaz);
		params.put("numeroLote", numeroLote);
		params.put("codigoUsuario", usuario.getLogin());

		String numeroSecuencia = procesoSTOEjecucionValidacionesService
				.getSecuenciaSTONextValue();
		params.put("numeroSecuencia", numeroSecuencia);
		params.put("indicadorEnvio", "0");
		params.put("indicadorRechazo", "1");

		params.put("codigoPais", f.getCodigoPais());
		params.put("apellidoPaterno", f.getApellidoPaterno());
		params.put("apellidoMaterno", f.getApellidoMaterno());
		params.put("nombre1", f.getNombre1());
		params.put("nombre2", f.getNombre2());
		params.put("tipoDocumentoIdentidad", f.getTipoDocumentoIdentidad());
		params.put("numeroDocumentoIdentidad", f.getNumeroDocumentoIdentidad());
		params.put("codigoZona", f.getCodigoZona());
		params.put("codigoTerritorio", f.getCodigoTerritorio());
		params.put("oidPeriodo", f.getOidPeriodo());

		params.put("codigoValidacion", "SCC-44");
		params.put("indicadorAprobado", "0");
		params.put("indicadorGestion", "0");
		params.put("indicadorLevaError", "0");
		params.put("codigoMensaje", "4400");

		try {
			clienteService.insertSolicitudCreditoRechazado(params);
			addInfo("Mensaje",
					getResourceMessage("mantenimientoMAEClienteForm.msg.graboSolicitudCreditoOk"));

		} catch (Exception ex) {
			ex.printStackTrace();
			addInfo("Mensaje",
					getResourceMessage("mantenimientoEDULocal.cabecera.error")
							+ ex.getMessage());

			// Actualizamos las listas de ubigeo
			actualizarListarUbigeo(f);

		}
	}

	public void deleteDetalSubTipoCliente() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deleteDetalSubTipoCliente' method");
		}

		MantenimientoMAEClienteForm f = (MantenimientoMAEClienteForm) formBusqueda;
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

		String id = "";// Demasiado importante = request.getParameter("id");
		List detalList = maeClienteSubtipoList;

		/* INI SA PER-SiCC-2013-0265 */
		if (detalList.size() > 0 && (Integer.parseInt(id) <= detalList.size())) {
			ClienteSubTipo clienteSubTipo = (ClienteSubTipo) detalList
					.get(Integer.parseInt(id) - 1);

			detalList.remove(Integer.parseInt(id) - 1);

			// BORRAMOS LOS TIPOS DE CLASIFICACION RELACIONADOS
			List listClasificaciones = getDetalClasificacionList();
			f.setMostrarConsultoraVinculo(false);
			for (int i = 0; i < listClasificaciones.size(); i++) {
				ClienteClasificacion clienteClasificacion = (ClienteClasificacion) listClasificaciones
						.get(i);

				if (clienteClasificacion.getOidClienteSubTipo().equals(
						clienteSubTipo.getOidSubTipoCliente())) {
					listClasificaciones.remove(i);
				}
			}
		}
		/* FIN SA PER-SiCC-2013-0265 */

		// SI ES CONSULTORA
		if ((existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_CONSULTORA,
				Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_NEGOCIO, detalList))
				|| (existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_CONSULTORA,
						Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_OFICINA,
						detalList))) {

			if (existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_CONSULTORA,
					Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_NEGOCIO, detalList)) {
				if (!((existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_GERENTE,
						Constants.MAE_SUBTIPO_GERENTE_REGION, detalList)) || (existeSubTipoCliente(
						Constants.MAE_TIPO_CLIENTE_GERENTE,
						Constants.MAE_SUBTIPO_GERENTE_ZONA, detalList)))) {
					f.setMostrarCodigoEmpleado(false);
					/* INI JJ PER-SiCC-2012-0329 */
					f.setMostrarCodigoCUB(false);
					/* FIN JJ PER-SiCC-2012-0329 */
				}
			}

			f.setEsDuplaCyzone(false);
			f.setEsTipoConsultora(true);
		} else {
			// SI ES GERENTE
			if ((existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_GERENTE,
					Constants.MAE_SUBTIPO_GERENTE_REGION, detalList))
					|| (existeSubTipoCliente(
							Constants.MAE_TIPO_CLIENTE_GERENTE,
							Constants.MAE_SUBTIPO_GERENTE_ZONA, detalList))) {
				f.setMostrarConsultoraRecomendante(false);
				f.setMostrarConsultoraVinculo(false);
				/* INI JJ PER-SiCC-2012-0329 */
				f.setMostrarCodigoCUB(true);
				/* FIN JJ PER-SiCC-2012-0329 */
			} else {
				// SI ES AVAL
				if ((existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_POTENCIAL,
						Constants.MAE_SUBTIPO_POTENCIAL_AVAL, detalList))) {
					f.setMostrarConsultoraRecomendante(false);
					f.setMostrarUnidadAdministrativa(false);
					f.setMostrarConsultoraVinculo(false);
					f.setMostrarCodigoEmpleado(false);
					/* INI JJ PER-SiCC-2012-0329 */
					f.setMostrarCodigoCUB(false);
					/* FIN JJ PER-SiCC-2012-0329 */
					f.setCodigoZona("-");
					f.setCodigoTerritorio("-");
				}
			}
		}

		/* INI SA PER-SiCC-2013-0265 */
		if ((existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_HIJADUPLA,
				Constants.MAE_SUBTIPO_HIJADUPLA_HIJADUPLA, detalList))
				&& detalList.size() == 1) {
			f.setMostrarConsultoraRecomendante(false);
			f.setTipoDocumentoIdentidad(clienteService
					.getTipoDocumentoDuplaCyzone(f.getOidPais()));
			f.setMostrarNumeroIdentidad(false);
			f.setMostrarDireccion(false);
			f.setMostrarUnidadAdministrativa(false);
			f.setMostrarConsultoraVinculo(true);
			f.setMostrarCodigoEmpleado(false);
			f.setEsDuplaCyzone(true);
			f.setEsTipoConsultora(false);
			f.setControlFoco(MAE_CONTROL_PERIODO);

			f.setCodigoZona("-");
			f.setCodigoTerritorio("-");
			f.setNumeroDocumentoIdentidad("-");

			f.setNivel1("-");
			f.setNivel2("-");
			f.setNivel3("-");
		}
		/* FIN SA PER-SiCC-2013-0265 */
		f.setMostrarCodigoCUB(false);
		if ((existeSubTipoClienteAux(Constants.MAE_TIPO_CLIENTE_CONSULTORA,
				Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_OFICINA, detalList))
				|| (existeSubTipoClienteAux(Constants.MAE_TIPO_CLIENTE_GERENTE,
						Constants.MAE_SUBTIPO_GERENTE_REGION, detalList))
				|| (existeSubTipoClienteAux(Constants.MAE_TIPO_CLIENTE_GERENTE,
						Constants.MAE_SUBTIPO_GERENTE_ZONA, detalList))) {
			f.setMostrarCodigoCUB(true);
		}

		// SE MUESTRA TODOS LOS SUBTIPOS DE CLIENTES
		if (detalList.size() == 0) {
			List subTiposCliente = clienteService
					.getSubTiposClienteInsertar(null);

			// seteamos al primer tipo/subtipo cliente encontrado
			if (subTiposCliente != null && subTiposCliente.size() > 0) {
				Base base = (Base) subTiposCliente.get(0);
				f.setSubTipoCliente(base.getCodigo());
			}

			f.setEsDuplaCyzone(false);
			f.setEsTipoConsultora(false);
			siccSubTipoClienteList = subTiposCliente;

		}

		updateTiposClasificaciones(detalList);

		// Actualizamos las listas de ubigeo
		actualizarListarUbigeo(f);

		validarMostrarLiderRecomendante(detalList, clienteService, f);

	}

	public void add() {
		log.debug("add new");
		MantenimientoMAEClienteForm f = (MantenimientoMAEClienteForm) formBusqueda;

		f.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);

		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");

		// Obtenemos los datos del usuario Logueado
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();

		// Ya registro un cliente
		String flagInserto = mPantallaPrincipalBean.getRequest().getParameter(
				"inserto");

		// Asignamos al codigo del periodo el valor por defecto
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());

		// setear el valor para la validacion
		if (clienteService.getObtieneTipoValidacion(criteria)) {
			validacionExiDoc = "SI";
			// session.setAttribute("validacionExiDoc", "SI");
		} else {
			validacionExiDoc = "NO";
			// session.setAttribute("validacionExiDoc", "NO");
		}

		// recuperamos el oid Pais
		String oidPais = clienteService.getOidPais(criteria);
		f.setOidPais(oidPais);
		criteria.put("oidPais", oidPais);

		/* INI SA PER-SiCC-2012-0365 */
		// RECUPERAMOS EL PERIODO DE PROCESO
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																// Campanha
																// Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																	// Campanha
																	// activa q
																	// se
																	// procesa
																	// actualmente

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteria);

		// Carga Periodo Proceso
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		/* FIN SA PER-SiCC-2012-0365 */

		// recuperamos la longitud del codigo de cliente para el pais logueado
		f.setLongitudCodigoCliente(clienteService
				.getLongitudCodigoCliente(criteria));
		criteria.put("longitudCodigoCliente",
				clienteService.getLongitudCodigoCliente(criteria));

		LabelValue[] periodos = clienteService
				.getPeriodosVigentesByPaisMarcaCanal(criteria);
		List subTiposCliente = clienteService
				.getSubTiposClienteInsertar(criteria);
		List nivelesGeograficos = clienteService
				.getNivelesGeograficos(criteria);

		// seteamos los niveles geograficos del pais
		if (nivelesGeograficos != null && nivelesGeograficos.size() > 0) {
			f.setTotalNiveles(String.valueOf(nivelesGeograficos.size()));

			for (int i = 0; i < nivelesGeograficos.size(); i++) {
				Base nivel = (Base) nivelesGeograficos.get(i);

				if (i == 0)
					f.setDescripcionNivel1(nivel.getDescripcion());
				if (i == 1)
					f.setDescripcionNivel2(nivel.getDescripcion());
				if (i == 2)
					f.setDescripcionNivel3(nivel.getDescripcion());
				if (i == 3)
					f.setDescripcionNivel4(nivel.getDescripcion());
				if (i == 4)
					f.setDescripcionNivel5(nivel.getDescripcion());
				if (i == 5)
					f.setDescripcionNivel6(nivel.getDescripcion());
			}
		}

		// seteamos el periodo actual
		if (periodos != null && periodos.length > 0) {
			LabelValue base = periodos[0];
			f.setOidPeriodo(base.getValue());
		}
		// seteamos al primer tipo/subtipo cliente encontrado
		if (subTiposCliente != null && subTiposCliente.size() > 0) {
			Base base = (Base) subTiposCliente.get(0);
			f.setSubTipoCliente(base.getCodigo());
		}

		// seteamos la edad minima y maxima
		f.setEdadMinima(clienteService.getEdadMinima(criteria));
		f.setEdadMaxima(clienteService.getEdadMaxima(criteria));

		// VERIFICAMOS SI LA GENERACION DEL CODIGO DE CLIENTE ES AUTOMATICA O
		// MANUAL
		boolean esCodigoClienteAutomatico = clienteService
				.isCodigoClienteAutomatico(criteria);
		f.setEsCodigoClienteAutomatico(esCodigoClienteAutomatico);

		f.setCodigoCliente("");
		f.setDigitoControl("");

		// request.getSession().setAttribute("codigoIdiomaISO",
		// getUsuario(session).getIdioma().getCodigoISO() );

		// configuramos la lista de periodos, subtipo de clientes
		// session.setAttribute(Constants.SICC_PERIODO_LIST, periodos);
		siccPeriodoList = periodos;
		if (flagInserto == null)
			siccSubTipoClienteList = subTiposCliente;
		// session.setAttribute(Constants.SICC_SUB_TIPO_CLIENTE_LIST,
		// subTiposCliente);

		// configuramos la lista de tipo documento de identidad, estado civil,
		// tratamiento, sexo, tipo de via, nivel geografico
		maeClienteEstadoCivilList = clienteService.getEstadosCiviles(criteria);
		maeClienteTratamientoList = clienteService.getTratamientos(criteria);
		maeClienteSexoList = clienteService.getSexos(criteria);
		maeClienteTipoViaList = clienteService.getTiposVias(criteria);
		maeClienteNivel1List = ajaxService.getUnidadesGeograficas(oidPais, "");
		maeClienteNivelEstudioList = clienteService.getNivelEstudios(criteria);
		maeClienteNacionalidadList = clienteService.getNacionalidades(criteria);
		maeClienteTipoDireccionList = clienteService
				.getTiposDireccion(criteria);
		maeClienteTipoVinculoList = clienteService.getTipoVinculo();
		maeTipoCutisList = clienteService.getTipoCutis(criteria);
		maeOtrasMarcas = clienteService.getOtrasMarcas(criteria);

		// session.setAttribute(Constants.MAE_CLIENTE_ESTADO_CIVIL_LIST,
		// clienteService.getEstadosCiviles(criteria));
		// session.setAttribute(Constants.MAE_CLIENTE_TRATAMIENTO_LIST,
		// clienteService.getTratamientos(criteria));
		// session.setAttribute(Constants.MAE_CLIENTE_SEXO_LIST,
		// clienteService.getSexos(criteria));
		// session.setAttribute(Constants.MAE_CLIENTE_TIPO_VIA_LIST,
		// clienteService.getTiposVias(criteria));
		// session.setAttribute(Constants.MAE_CLIENTE_NIVEL1_LIST,
		// ajaxService.getUnidadesGeograficas(oidPais, ""));
		// session.setAttribute(Constants.MAE_CLIENTE_NIVEL_ESTUDIO_LIST,
		// clienteService.getNivelEstudios(criteria));
		// session.setAttribute(Constants.MAE_CLIENTE_NACIONALIDAD_LIST,
		// clienteService.getNacionalidades(criteria));
		// session.setAttribute(Constants.MAE_CLIENTE_TIPO_DIRECCION_LIST,
		// clienteService.getTiposDireccion(criteria));
		// session.setAttribute(Constants.MAE_CLIENTE_TIPO_VINCULO_LIST,
		// clienteService.getTipoVinculo());
		// session.setAttribute(Constants.MAE_TIPO_CUTIS_LIST,
		// clienteService.getTipoCutis(criteria));
		// session.setAttribute(Constants.MAE_OTRAS_MARCAS_LIST,
		// clienteService.getOtrasMarcas(criteria));

		/* INI SA PER-SiCC-2012-0459 */
		// session.setAttribute(Constants.MAE_CIUDAD_LIST, new ArrayList());
		// session.setAttribute(Constants.MAE_CIUDAD_CT_LIST, new ArrayList());
		// /* FIN SA PER-SiCC-2012-0459 */
		//
		// session.setAttribute(Constants.MAE_CLIENTE_NIVEL2CT_LIST, new
		// ArrayList());
		// session.setAttribute(Constants.MAE_CLIENTE_NIVEL3CT_LIST, new
		// ArrayList());
		// session.setAttribute(Constants.MAE_CLIENTE_NIVEL4CT_LIST, new
		// ArrayList());
		// session.setAttribute(Constants.MAE_CLIENTE_NIVEL5CT_LIST, new
		// ArrayList());
		// session.setAttribute(Constants.MAE_CLIENTE_NIVEL6CT_LIST, new
		// ArrayList());
		//
		// /* INI SA PER-SiCC-2012-0365 */
		// session.setAttribute(Constants.MAE_CIUDAD_VAC_LIST, new ArrayList());
		// session.setAttribute(Constants.MAE_CLIENTE_NIVEL2VAC_LIST, new
		// ArrayList());
		// session.setAttribute(Constants.MAE_CLIENTE_NIVEL3VAC_LIST, new
		// ArrayList());
		// session.setAttribute(Constants.MAE_CLIENTE_NIVEL4VAC_LIST, new
		// ArrayList());
		// session.setAttribute(Constants.MAE_CLIENTE_NIVEL5VAC_LIST, new
		// ArrayList());
		// session.setAttribute(Constants.MAE_CLIENTE_NIVEL6VAC_LIST, new
		// ArrayList());

		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		f.setCodigoPeriodoInicioVacaciones(clienteService
				.getPeriodoInicioVacaciones(criteria));
		f.setCodigoPeriodoFinVacaciones(clienteService
				.getPeriodoFinVacaciones(criteria));
		/* FIN SA PER-SiCC-2012-0365 */

		// Dejamos la lista de subtipoClientes intacto, y las clasificaciones
		// por defecto
		if (flagInserto != null) {
			List detalListSubTipo = maeClienteSubtipoList;

			initCabecera(f, false);

			Iterator itSubTipoCliente = detalListSubTipo.iterator();
			while (itSubTipoCliente.hasNext()) {
				ClienteSubTipo clienteSubTipo = (ClienteSubTipo) itSubTipoCliente
						.next();

				// Agregamos la clasificacion por Defecto relacionado al
				// SubTipoCliente
				String tipoClasificacionDefault = clienteService
						.getTipoClasificacionDefault(
								clienteSubTipo.getCodigoTipoCliente(),
								clienteSubTipo.getCodigoSubTipoCliente());

				String clasificacionDefault = clienteService
						.getClasificacionDefault(
								clienteSubTipo.getCodigoTipoCliente(),
								clienteSubTipo.getCodigoSubTipoCliente());

				f.setTipoClasificacion(tipoClasificacionDefault);
				f.setClasificacion(clasificacionDefault);
				addDetalClasificacion();

				// Colocamos valores por Defecto
				setearValorPorDefectoPorSubTipoCliente(clienteService, f,
						clienteSubTipo.getOidSubTipoCliente().toString());

				// obtenemos el tipo de documento de identidad obligatorio para
				// el subtipocliente
				if (f.isEsDuplaCyzone())
					f.setTipoDocumentoIdentidad(clienteService
							.getTipoDocumentoDuplaCyzone(f.getOidPais()));
				else
					f.setTipoDocumentoIdentidad(clienteService
							.getTipoDocumentoObligatorio(f.getOidPais()));

				// Actualizamos la longitud del tipo de documento de identidad
				f.setLongitudTipoDocumento(ajaxService
						.getLongitudTipoDocumento(f.getOidPais(),
								f.getTipoDocumentoIdentidad()));

				// seteamos la edad minima y maxima
				if (clienteSubTipo.getCodigoTipoCliente().equals(
						Constants.MAE_TIPO_CLIENTE_HIJADUPLA)
						|| clienteSubTipo.getCodigoTipoCliente().equals(
								Constants.MAE_TIPO_CLIENTE_CONSULTORA)) {
					criteria.put("oidSubTipoCliente",
							clienteSubTipo.getOidSubTipoCliente());

					f.setEdadMinima(clienteService.getEdadMinima(criteria));
					f.setEdadMaxima(clienteService.getEdadMaxima(criteria));
				}
			}

			if (f.isEsDuplaCyzone()) {
				f.setControlFoco(MAE_CONTROL_PERIODO);
				f.setCodigoZona("-");
				f.setCodigoTerritorio("-");
				f.setNumeroDocumentoIdentidad("-");
				f.setNivel1("-");
				f.setNivel2("-");
				f.setNivel3("-");
			} else {
				f.setControlFoco(MAE_CONTROL_ZONA);
			}

		} else {
			initCabecera(f, true);

			// session.setAttribute(Constants.SICC_TIPO_CLASIFICACION_LIST, new
			// ArrayList());
			siccTipoDocumentoList = clienteService
					.getTiposDocumentoIdentidad(criteria);
			// session.setAttribute(Constants.SICC_TIPO_DOCUMENTO_LIST,
			// clienteService.getTiposDocumentoIdentidad(criteria));
		}
		// Verificar Parametria BAS_PARAM_PAIS
		GenericoService genericoService = (GenericoService) getBean("genericoService");
		ParametroPais paramPais = new ParametroPais();

		paramPais.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());
		paramPais.setCodigoSistema(Constants.MAE_CODIGO_SISTEMA);
		paramPais.setNombreParametro(Constants.MAE_NOMB_PARAM);
		paramPais.setValorParametro(Constants.NUMERO_UNO);

		List lstParametros = genericoService.getParametrosPais(paramPais);

		if (lstParametros != null && lstParametros.size() > 0) {
			f.setIndicadorSeccionOtros(true);
		} else {
			f.setIndicadorSeccionOtros(false);
		}

		f.setIndicadorCompromiso(Constants.NUMERO_CERO);
		setearCamposModuloPais(f, clienteService, ajaxService);
		// limpiamos la lista de deudoras
		maeDeudorasList.clear();
		maeFlagDeudoras = "";
		// session.removeAttribute(MAE_DEUDORAS_LIST);
		// session.removeAttribute(MAE_FLAG_DEUDORAS);

		f.setMotivo("");

		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());
		parametroPais.setCodigoSistema(Constants.MAE_CODIGO_SISTEMA);
		parametroPais.setNombreParametro(Constants.MAE_IND_BUSCAR_DIRECCION);
		parametroPais.setIndicadorActivo(Constants.NUMERO_UNO);
		List parametros = genericoService.getParametrosPais(parametroPais);

		f.setMostrarBuscarDireccion(false);
		ParametroPais parametro = null;
		if (parametros.size() == 1) {
			parametro = (ParametroPais) parametros.get(0);
			if (parametro.getValorParametro().equals(Constants.NUMERO_UNO)) {
				f.setMostrarBuscarDireccion(true);
			}
		}
		/* INI PER-SiCC-2014-0162 */
		ParametroPais paramPaisFactElect = new ParametroPais();

		paramPaisFactElect.setCodigoPais(mPantallaPrincipalBean
				.getCurrentCountry().getCodigo());
		paramPaisFactElect.setCodigoSistema(Constants.MAE_CODIGO_SISTEMA);
		paramPaisFactElect
				.setNombreParametro(Constants.MAE_NOMB_PARAM_FACT_ELECT);
		paramPaisFactElect.setValorParametro(Constants.NUMERO_UNO);

		List lstParametrosFactElec = genericoService
				.getParametrosPais(paramPaisFactElect);

		if (lstParametrosFactElec != null && lstParametrosFactElec.size() > 0) {
			f.setIndicadorFactElect(true);
		} else {
			f.setIndicadorFactElect(false);
		}
		/* FIN PER-SiCC-2014-0162 */

		f.setMostrarConsultoraLiderRecomendante(false);
		f.setOidConsultoraLiderRecomendante("");
		f.setCodigoConsultoraLiderRecomendante("");
		f.setNombreConsultoraLiderRecomendante("");
	}

	@Override
	protected void setViewAtributes() throws Exception {
		add();

	}

	private boolean validarDocumentoIdentidad(
			MantenimientoMAEClienteService clienteService, Map criteria,
			MantenimientoMAEClienteForm f) {
		boolean flagOK = true;
		String documento = clienteService.validarDocumentoIdentidad(criteria);

		if (documento != null) {
			flagOK = false;

			if (documento.equals("Modulo10")) {
				addInfo("Mensaje",
						getResourceMessage("mantenimientoMAEClienteForm.msg.DocIdentidadNoValido"));
				/* INI SA PER-SiCC-2012-0265 */
			} else if (documento.equals("Modulo11V")) {
				addInfo("Mensaje",
						getResourceMessage("mantenimientoMAEClienteForm.msg.DocIdentidadNoValidoRUT"));
				/* FIN SA PER-SiCC-2012-0265 */
			} else {
				StringTokenizer st = new StringTokenizer(documento, "-");
				String saldo = st.nextToken();

				if (saldo.equals(" "))
					addInfo("Mensaje",
							getResourceMessage("mantenimientoMAEClienteForm.msg.ClienteExiste"));

				else
					addInfo("Mensaje",
							getResourceMessage("mantenimientoMAEClienteForm.msg.ClienteTieneCuentasCastigadas"));
			}
		} else {

			if (f.isMostrarSegundoDocumento() && (!f.isEsDuplaCyzone())) {
				if ((f.getTipoDocumentoIdentidad2().length() > 0)
						&& (f.getNumeroDocumentoIdentidad2().length() > 0)) {
					Map criteriaAux = new HashMap();
					criteriaAux.put("oidPais", f.getOidPais());
					criteriaAux.put("tipoDocumentoIdentidad",
							f.getTipoDocumentoIdentidad2());
					criteriaAux.put("numeroDocumentoIdentidad",
							f.getNumeroDocumentoIdentidad2());

					if (clienteService.verificarDocumentoIdentidad(criteriaAux)) {
						flagOK = false;
						addInfo("Mensaje",
								getResourceMessage("mantenimientoMAEClienteForm.msg.SegundoDocumentoExiste"));

					}
				}
			}

			if (flagOK) {
				if (!f.isAprobarAvaladas()) {
					// Modifcacion 16/10/2009 S.B se verificara si es aval de
					// consultoras que tienen deuda si es asi
					// no se le permitira registar
					criteria.put("mensajeError", "");
					criteria.put("tipo", Constants.NUMERO_CERO);// tipo de
																// registro
																// referencia
					log.debug("criteria " + criteria);
					clienteService
							.executeValidacionDeudoraConsultoraAval(criteria);
					String mensajeError = (String) criteria.get("mensajeError");
					if (StringUtils.isNotEmpty(mensajeError)) {
						addInfo("Mensaje", mensajeError);

						flagOK = false;
					}
				}
			}
		}

		return flagOK;
	}

	public List getMaeClienteSubtipoList() {
		return maeClienteSubtipoList;
	}

	public void setMaeClienteSubtipoList(List maeClienteSubtipoList) {
		this.maeClienteSubtipoList = maeClienteSubtipoList;
	}

	public List getMaeClienteClasificacionList() {
		return maeClienteClasificacionList;
	}

	public void setMaeClienteClasificacionList(List maeClienteClasificacionList) {
		this.maeClienteClasificacionList = maeClienteClasificacionList;
	}

	public List getMaeClienteNivelEstudioList() {
		return maeClienteNivelEstudioList;
	}

	public void setMaeClienteNivelEstudioList(List maeClienteNivelEstudioList) {
		this.maeClienteNivelEstudioList = maeClienteNivelEstudioList;
	}

	public List getMaeClienteNacionalidadList() {
		return maeClienteNacionalidadList;
	}

	public void setMaeClienteNacionalidadList(List maeClienteNacionalidadList) {
		this.maeClienteNacionalidadList = maeClienteNacionalidadList;
	}

	public List getMaeClienteEstadoCivilList() {
		return maeClienteEstadoCivilList;
	}

	public void setMaeClienteEstadoCivilList(List maeClienteEstadoCivilList) {
		this.maeClienteEstadoCivilList = maeClienteEstadoCivilList;
	}

	public List getMaeClienteSexoList() {
		return maeClienteSexoList;
	}

	public void setMaeClienteSexoList(List maeClienteSexoList) {
		this.maeClienteSexoList = maeClienteSexoList;
	}

	public List getMaeClienteTipoViaList() {
		return maeClienteTipoViaList;
	}

	public void setMaeClienteTipoViaList(List maeClienteTipoViaList) {
		this.maeClienteTipoViaList = maeClienteTipoViaList;
	}

	public LabelValue[] getMaeClienteNivel1List() {
		return maeClienteNivel1List;
	}

	public void setMaeClienteNivel1List(LabelValue[] maeClienteNivel1List) {
		this.maeClienteNivel1List = maeClienteNivel1List;
	}

	public List getMaeClienteTipoDireccionList() {
		return maeClienteTipoDireccionList;
	}

	public void setMaeClienteTipoDireccionList(List maeClienteTipoDireccionList) {
		this.maeClienteTipoDireccionList = maeClienteTipoDireccionList;
	}

	public boolean isMostrarReferenciasChk() {
		return mostrarReferenciasChk;
	}

	public void setMostrarReferenciasChk(boolean mostrarReferenciasChk) {
		this.mostrarReferenciasChk = mostrarReferenciasChk;
	}

	public List getMaeClienteTipoVinculoList() {
		return maeClienteTipoVinculoList;
	}

	public void setMaeClienteTipoVinculoList(List maeClienteTipoVinculoList) {
		this.maeClienteTipoVinculoList = maeClienteTipoVinculoList;
	}

	public List getMaeTipoCutisList() {
		return maeTipoCutisList;
	}

	public void setMaeTipoCutisList(List maeTipoCutisList) {
		this.maeTipoCutisList = maeTipoCutisList;
	}

	public List getMaeOtrasMarcas() {
		return maeOtrasMarcas;
	}

	public void setMaeOtrasMarcas(List maeOtrasMarcas) {
		this.maeOtrasMarcas = maeOtrasMarcas;
	}

	public LabelValue[] getSiccPeriodoList() {
		return siccPeriodoList;
	}

	public void setSiccPeriodoList(LabelValue[] siccPeriodoList) {
		this.siccPeriodoList = siccPeriodoList;
	}

	public List getSiccTipoDocumentoList() {
		return siccTipoDocumentoList;
	}

	public void setSiccTipoDocumentoList(List siccTipoDocumentoList) {
		this.siccTipoDocumentoList = siccTipoDocumentoList;
	}

	public LabelValue[] getMaeClienteNivel2AvalList() {
		return maeClienteNivel2AvalList;
	}

	public void setMaeClienteNivel2AvalList(
			LabelValue[] maeClienteNivel2AvalList) {
		this.maeClienteNivel2AvalList = maeClienteNivel2AvalList;
	}

	public LabelValue[] getMaeClienteNivel3AvalList() {
		return maeClienteNivel3AvalList;
	}

	public void setMaeClienteNivel3AvalList(
			LabelValue[] maeClienteNivel3AvalList) {
		this.maeClienteNivel3AvalList = maeClienteNivel3AvalList;
	}

	public LabelValue[] getMaeClienteNivel2CTList() {
		return maeClienteNivel2CTList;
	}

	public void setMaeClienteNivel2CTList(LabelValue[] maeClienteNivel2CTList) {
		this.maeClienteNivel2CTList = maeClienteNivel2CTList;
	}

	public LabelValue[] getMaeCiudadList() {
		return maeCiudadList;
	}

	public void setMaeCiudadList(LabelValue[] maeCiudadList) {
		this.maeCiudadList = maeCiudadList;
	}

	public LabelValue[] getMaeCiudadCTList() {
		return maeCiudadCTList;
	}

	public void setMaeCiudadCTList(LabelValue[] maeCiudadCTList) {
		this.maeCiudadCTList = maeCiudadCTList;
	}

	public LabelValue[] getMaeClienteNivel3CTList() {
		return maeClienteNivel3CTList;
	}

	public void setMaeClienteNivel3CTList(LabelValue[] maeClienteNivel3CTList) {
		this.maeClienteNivel3CTList = maeClienteNivel3CTList;
	}

	public LabelValue[] getMaeClienteNivel4CTList() {
		return maeClienteNivel4CTList;
	}

	public void setMaeClienteNivel4CTList(LabelValue[] maeClienteNivel4CTList) {
		this.maeClienteNivel4CTList = maeClienteNivel4CTList;
	}

	public LabelValue[] getMaeClienteNivel5CTList() {
		return maeClienteNivel5CTList;
	}

	public void setMaeClienteNivel5CTList(LabelValue[] maeClienteNivel5CTList) {
		this.maeClienteNivel5CTList = maeClienteNivel5CTList;
	}

	public LabelValue[] getMaeClienteNivel6CTList() {
		return maeClienteNivel6CTList;
	}

	public void setMaeClienteNivel6CTList(LabelValue[] maeClienteNivel6CTList) {
		this.maeClienteNivel6CTList = maeClienteNivel6CTList;
	}

	public LabelValue[] getMaeClienteNivel2VACList() {
		return maeClienteNivel2VACList;
	}

	public void setMaeClienteNivel2VACList(LabelValue[] maeClienteNivel2VACList) {
		this.maeClienteNivel2VACList = maeClienteNivel2VACList;
	}

	public LabelValue[] getMaeCiudadVacList() {
		return maeCiudadVacList;
	}

	public void setMaeCiudadVacList(LabelValue[] maeCiudadVacList) {
		this.maeCiudadVacList = maeCiudadVacList;
	}

	public LabelValue[] getMaeClienteNivel3VACList() {
		return maeClienteNivel3VACList;
	}

	public void setMaeClienteNivel3VACList(LabelValue[] maeClienteNivel3VACList) {
		this.maeClienteNivel3VACList = maeClienteNivel3VACList;
	}

	public LabelValue[] getMaeClienteNivel4VACList() {
		return maeClienteNivel4VACList;
	}

	public void setMaeClienteNivel4VACList(LabelValue[] maeClienteNivel4VACList) {
		this.maeClienteNivel4VACList = maeClienteNivel4VACList;
	}

	public LabelValue[] getMaeClienteNivel5VACList() {
		return maeClienteNivel5VACList;
	}

	public void setMaeClienteNivel5VACList(LabelValue[] maeClienteNivel5VACList) {
		this.maeClienteNivel5VACList = maeClienteNivel5VACList;
	}

	public List getMaeClienteTratamientoList() {
		return maeClienteTratamientoList;
	}

	public void setMaeClienteTratamientoList(List maeClienteTratamientoList) {
		this.maeClienteTratamientoList = maeClienteTratamientoList;
	}

	public LabelValue[] getMaeClienteNivel6VACList() {
		return maeClienteNivel6VACList;
	}

	public void setMaeClienteNivel6VACList(LabelValue[] maeClienteNivel6VACList) {
		this.maeClienteNivel6VACList = maeClienteNivel6VACList;
	}

	public List getSiccSubTipoClienteList() {
		return siccSubTipoClienteList;
	}

	public void setSiccSubTipoClienteList(List siccSubTipoClienteList) {
		this.siccSubTipoClienteList = siccSubTipoClienteList;
	}

	public List getMaeDeudorasList() {
		return maeDeudorasList;
	}

	public void setMaeDeudorasList(List maeDeudorasList) {
		this.maeDeudorasList = maeDeudorasList;
	}

	public String getMaeFlagDeudoras() {
		return maeFlagDeudoras;
	}

	public void setMaeFlagDeudoras(String maeFlagDeudoras) {
		this.maeFlagDeudoras = maeFlagDeudoras;
	}

	public String getValidacionExiDoc() {
		return validacionExiDoc;
	}

	public void setValidacionExiDoc(String validacionExiDoc) {
		this.validacionExiDoc = validacionExiDoc;
	}

	public static String getMaeTipoDireccionDomicilio() {
		return MAE_TIPO_DIRECCION_DOMICILIO;
	}

	public static String getMaeTipoDireccionTrabajo() {
		return MAE_TIPO_DIRECCION_TRABAJO;
	}

	public static String getMaeTipoComunicacionTelefonoCasa() {
		return MAE_TIPO_COMUNICACION_TELEFONO_CASA;
	}

	public static String getMaeTipoComunicacionTelefonoMovil() {
		return MAE_TIPO_COMUNICACION_TELEFONO_MOVIL;
	}

	public static String getMaeTipoComunicacionTelefonoTrabajo() {
		return MAE_TIPO_COMUNICACION_TELEFONO_TRABAJO;
	}

	public static String getMaeTipoComunicacionMail() {
		return MAE_TIPO_COMUNICACION_MAIL;
	}

	public static String getMaeTipoComunicacionTelefonoReferencia() {
		return MAE_TIPO_COMUNICACION_TELEFONO_REFERENCIA;
	}

	public static String getMaeTipoComunicacionTelefonoAdicional() {
		return MAE_TIPO_COMUNICACION_TELEFONO_ADICIONAL;
	}

	public static String getMaeOidTipoViaDefault() {
		return MAE_OID_TIPO_VIA_DEFAULT;
	}

	public static String getMaeOidEstadoCivilDefault() {
		return MAE_OID_ESTADO_CIVIL_DEFAULT;
	}

	public static String getMaeCodSexoDefault() {
		return MAE_COD_SEXO_DEFAULT;
	}

	public static String getMaeOidTratamientoDefault() {
		return MAE_OID_TRATAMIENTO_DEFAULT;
	}

	public static String getMaeOidTipoDireccionDistribucion() {
		return MAE_OID_TIPO_DIRECCION_DISTRIBUCION;
	}

	public static String getMaeControlSubtipocliente() {
		return MAE_CONTROL_SUBTIPOCLIENTE;
	}

	public static String getMaeControlZona() {
		return MAE_CONTROL_ZONA;
	}

	public static String getMaeControlPeriodo() {
		return MAE_CONTROL_PERIODO;
	}

	public static String getMaeLongitudCodigoZona() {
		return MAE_LONGITUD_CODIGO_ZONA;
	}

	public static Long getMaeClienteContactoDummy() {
		return MAE_CLIENTE_CONTACTO_DUMMY;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public LabelValue[] getMaeClienteNivel2List() {
		return maeClienteNivel2List;
	}

	public void setMaeClienteNivel2List(LabelValue[] maeClienteNivel2List) {
		this.maeClienteNivel2List = maeClienteNivel2List;
	}

	public LabelValue[] getMaeClienteNivel3List() {
		return maeClienteNivel3List;
	}

	public void setMaeClienteNivel3List(LabelValue[] maeClienteNivel3List) {
		this.maeClienteNivel3List = maeClienteNivel3List;
	}

	public LabelValue[] getMaeClienteNivel4List() {
		return maeClienteNivel4List;
	}

	public void setMaeClienteNivel4List(LabelValue[] maeClienteNivel4List) {
		this.maeClienteNivel4List = maeClienteNivel4List;
	}

	public boolean isMostrarDireccionEnvioChk() {
		return mostrarDireccionEnvioChk;
	}

	public void setMostrarDireccionEnvioChk(boolean mostrarDireccionEnvioChk) {
		this.mostrarDireccionEnvioChk = mostrarDireccionEnvioChk;
	}

	public void activarModuloDireccion() {

	
		

	}
	public void activarModuloDireccionVacaciones() {


	}

	public boolean isMostrarClasificacionesChk() {
		return mostrarClasificacionesChk;
	}

	public void setMostrarClasificacionesChk(boolean mostrarClasificacionesChk) {
		this.mostrarClasificacionesChk = mostrarClasificacionesChk;
	}

	public List getSiccTipoClasificacion() {
		return siccTipoClasificacion;
	}

	public void setSiccTipoClasificacion(List siccTipoClasificacion) {
		this.siccTipoClasificacion = siccTipoClasificacion;
	}

	public LabelValue[] getSiccClasificacion() {
		return siccClasificacion;
	}

	public void setSiccClasificacion(LabelValue[] siccClasificacion) {
		this.siccClasificacion = siccClasificacion;
	}

	public boolean isMostrarDivIndicadorFiscal() {
		return mostrarDivIndicadorFiscal;
	}

	public void setMostrarDivIndicadorFiscal(boolean mostrarDivIndicadorFiscal) {
		this.mostrarDivIndicadorFiscal = mostrarDivIndicadorFiscal;
	}

	public boolean isMostrarDireccionVacacionesChk() {
		return mostrarDireccionVacacionesChk;
	}

	public void setMostrarDireccionVacacionesChk(
			boolean mostrarDireccionVacacionesChk) {
		this.mostrarDireccionVacacionesChk = mostrarDireccionVacacionesChk;
	}
	

}
