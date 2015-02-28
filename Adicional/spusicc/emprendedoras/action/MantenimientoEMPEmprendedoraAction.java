package biz.belcorp.ssicc.web.spusicc.emprendedoras.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.spusicc.emprendedoras.MantenimientoEMPEmprendedoraService;
import biz.belcorp.ssicc.service.spusicc.emprendedoras.ProcesoEMPCargarPreEmprendedorasService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultorasAction;
import biz.belcorp.ssicc.web.spusicc.emprendedoras.form.MantenimientoEMPEmprendedoraForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoEMPEmprendedoraAction extends
BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	 private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final long serialVersionUID = 9046087508667111219L;
	private List empTipoEmprendedoraList;
	private List empRegimenList;
	private List empProgramasList;
	private int longitudCampoClientes;
	private List empRequisitosNoCumpleList;
	private List empRecomendadas;
	private boolean mostrarPopUpCliente = false;
	private static final String POPUP_CLIENTES = "SCLIENTES";

	@ManagedProperty(value = "#{busquedaConsultorasAction}")
	private BusquedaConsultorasAction busquedaConsultorasAction;
	
	
	
	
	
	
	@Override
	public String setValidarMantenimiento() {
		MantenimientoEMPEmprendedoraForm f = (MantenimientoEMPEmprendedoraForm) formBusqueda;
		if(!validateEmail(f.getMail())){
			return "Ingrese un email Válido";
		}
		if(StringUtils.isBlank(f.getCodigoCliente())){
			return "Ingrese Consultora";
		}
		return "";
	}
	
	
	
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}
		if (accion.equals(this.POPUP_CLIENTES)) {
			this.busquedaConsultorasAction.verificarRegistro(event);
			if (this.busquedaConsultorasAction.isSeleccionoRegistro()) {
				Map clienteHipMap = (Map) this.busquedaConsultorasAction
						.getBeanRegistroSeleccionado();
				MantenimientoEMPEmprendedoraForm f = (MantenimientoEMPEmprendedoraForm) this.formBusqueda;			
	//[apellido2=VILCA, indActi=NO, codigoCliente=010079403, null, nombre2=OLGA, nombre1=SILVIA, null, numeroDocumento=0020104009, null, tipoDocumento=Doc. Nacional Identidad (DNI) - Bolt., null, null, null, null, null, codigoZona=6031]
				f.setCodigoCliente((String)clienteHipMap.get("codigoCliente") );
				this.busquedaConsultorasAction
						.setBeanRegistroSeleccionado(null);
			}
		}
	}
	@Override
	protected void setSalirPopup() {
		this.mostrarPopUpCliente = false;
		this.busquedaConsultorasAction
				.setBeanRegistroSeleccionado(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setInvocarPopup(java.lang.String)
	 */

	@Override
	protected void setInvocarPopup(String accion) {
		this.mostrarProcesoBatch = false;

		if (accion.equals(this.POPUP_CLIENTES)) {
			this.mostrarPopUpCliente = true;
		}
	}

	public boolean isMostrarPopUpCliente() {
		return mostrarPopUpCliente;
	}

	public void setMostrarPopUpCliente(boolean mostrarPopUpCliente) {
		this.mostrarPopUpCliente = mostrarPopUpCliente;
	}


	public BusquedaConsultorasAction getbusquedaConsultorasAction() {
		return busquedaConsultorasAction;
	}
	public void setbusquedaConsultorasAction(
			BusquedaConsultorasAction busquedaConsultorasAction) {
		this.busquedaConsultorasAction = busquedaConsultorasAction;
	}
	public static String getPopupClientes() {
		return POPUP_CLIENTES;
	}

	
	
	public List getEmpRecomendadas() {
		return empRecomendadas;
	}

	public void setEmpRecomendadas(List empRecomendadas) {
		this.empRecomendadas = empRecomendadas;
	}

	public List getEmpRequisitosNoCumpleList() {
		return empRequisitosNoCumpleList;
	}

	public void setEmpRequisitosNoCumpleList(List empRequisitosNoCumpleList) {
		this.empRequisitosNoCumpleList = empRequisitosNoCumpleList;
	}

	public int getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	public void setLongitudCampoClientes(int longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}

	public List getEmpTipoEmprendedoraList() {
		return empTipoEmprendedoraList;
	}

	public void setEmpTipoEmprendedoraList(List empTipoEmprendedoraList) {
		this.empTipoEmprendedoraList = empTipoEmprendedoraList;
	}

	public List getEmpRegimenList() {
		return empRegimenList;
	}

	public void setEmpRegimenList(List empRegimenList) {
		this.empRegimenList = empRegimenList;
	}

	public List getEmpProgramasList() {
		return empProgramasList;
	}

	public void setEmpProgramasList(List empProgramasList) {
		this.empProgramasList = empProgramasList;
	}

	@Override
	protected String getSalirForward() {
		return "mantenimientoCRAGrupoZonaList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoCRAGrupoZonaForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoEMPEmprendedoraForm searchForm = new MantenimientoEMPEmprendedoraForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		log.debug("setFindAttributes");
		log.debug("find");
		String strMessage = null;
		MantenimientoEMPEmprendedoraForm f = (MantenimientoEMPEmprendedoraForm) formBusqueda;

		MantenimientoEMPEmprendedoraService serviceEmp = (MantenimientoEMPEmprendedoraService) getBean("spusicc.mantenimientoEMPEmprendedoraService");
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		
		// Obtiene la información de la consultora
		Map criteria = new HashMap();
		criteria.put("codigoCliente", f.getCodigoClienteBuscar());

		List lista = serviceEmp.getDatosConsultora(criteria);

		inicializar(f);

		if ((lista != null) && (lista.size() > 0)) {

			Map map = new HashMap();
			Map mapDatosCliente = (Map) lista.get(0);
			if (mapDatosCliente != null) {

				map.put("codigoPeriodo", f.getCodigoPeriodo());
				map.put("codigoZona",
						(String) mapDatosCliente.get("codigoZona"));

				if (clienteService.getZonaPeriodoCerrada(map).equals(
						Constants.SI)) {
					f.setFlagMostrarErrores(true);

					String error= this.getResourceMessage("mantenimientoEMPEmprendedoraForm.msg.regionCerrada");
					addInfo("Atención", error);
				} else {

					f.setNombreCliente((String) mapDatosCliente
							.get("nombreCliente"));
					f.setRegion((String) mapDatosCliente.get("region"));
					f.setZona((String) mapDatosCliente.get("zona"));
					f.setCodigoCliente((String) mapDatosCliente
							.get("codigoCliente"));
					f.setCodigoClienteBuscar((String) mapDatosCliente
							.get("codigoCliente"));
					f.setDireccion((String) mapDatosCliente.get("direccion"));
					f.setFechaNacimiento((String) mapDatosCliente
							.get("fechaNacimiento"));
					f.setMail((String) mapDatosCliente.get("email"));
					f.setTipoDocumento((String) mapDatosCliente
							.get("tipoDocumento"));
					f.setNumeroDocumento((String) mapDatosCliente
							.get("numeroDocumento"));
					f.setTelefonoCasa((String) mapDatosCliente
							.get("telefonoCasa"));
					f.setTelefonoCelular((String) mapDatosCliente
							.get("telefonoCelular"));
					f.setOidCliente(((BigDecimal) mapDatosCliente
							.get("oidCliente")).toString());

					if (mapDatosCliente.get("existeEmpresaria") != null) { // Existe
																			// como
																			// empresaria
																			// debe
																			// actualizar

						f.setNewRecord(false);

						String error = this
								.getResourceMessage("mantenimientoEMPEmprendedoraForm.msg.existeEmprendedora");
						addInfo("Existe", error);

					}

				}
			}
		} else {
			inicializar(f);
			String e = this
					.getResourceMessage("mantenimientoEMPEmprendedoraForm.msg.clienteNoEncontrado");
			addError("Error: ", e); // mensaje infomrativo addinfo
		}
		
		return lista;

		
	}

	


	public void llenarAttributes() throws Exception {
		log.debug("setFindAttributes");
		log.debug("finds");
		String strMessage = null;
		MantenimientoEMPEmprendedoraForm f = (MantenimientoEMPEmprendedoraForm) formBusqueda;

		MantenimientoEMPEmprendedoraService serviceEmp = (MantenimientoEMPEmprendedoraService) getBean("spusicc.mantenimientoEMPEmprendedoraService");
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		
		// Obtiene la información de la consultora
		Map criteria = new HashMap();
		criteria.put("codigoCliente", f.getCodigoCliente());

		List lista = serviceEmp.getDatosConsultora(criteria);


		if ((lista != null) && (lista.size() > 0)) {

			Map map = new HashMap();
			Map mapDatosCliente = (Map) lista.get(0);
			if (mapDatosCliente != null) {

				map.put("codigoPeriodo", f.getCodigoPeriodo());
				map.put("codigoZona",
						(String) mapDatosCliente.get("codigoZona"));

				if (clienteService.getZonaPeriodoCerrada(map).equals(
						Constants.SI)) {
					f.setFlagMostrarErrores(true);

					String error= this.getResourceMessage("mantenimientoEMPEmprendedoraForm.msg.regionCerrada");
					addInfo("Atención", error);
				} else {

					f.setNombreCliente((String) mapDatosCliente
							.get("nombreCliente"));
					f.setRegion((String) mapDatosCliente.get("region"));
					f.setZona((String) mapDatosCliente.get("zona"));
					f.setCodigoCliente((String) mapDatosCliente
							.get("codigoCliente"));
					f.setCodigoClienteBuscar((String) mapDatosCliente
							.get("codigoCliente"));
					f.setDireccion((String) mapDatosCliente.get("direccion"));
					f.setFechaNacimiento((String) mapDatosCliente
							.get("fechaNacimiento"));
					f.setMail((String) mapDatosCliente.get("email"));
					f.setTipoDocumento((String) mapDatosCliente
							.get("tipoDocumento"));
					f.setNumeroDocumento((String) mapDatosCliente
							.get("numeroDocumento"));
					f.setTelefonoCasa((String) mapDatosCliente
							.get("telefonoCasa"));
					f.setTelefonoCelular((String) mapDatosCliente
							.get("telefonoCelular"));
					f.setOidCliente(((BigDecimal) mapDatosCliente
							.get("oidCliente")).toString());

					if (mapDatosCliente.get("existeEmpresaria") != null) { // Existe
																			// como
																			// empresaria
																			// debe
																			// actualizar

						f.setNewRecord(false);

						String error = this
								.getResourceMessage("mantenimientoEMPEmprendedoraForm.msg.existeEmprendedora");
						addInfo("Existe", error);

					}

				}
			}
		} else {
			inicializar(f);
			String e = this
					.getResourceMessage("mantenimientoEMPEmprendedoraForm.msg.clienteNoEncontrado");
			addError("Error: ", e); // mensaje infomrativo addinfo
		}
		
	

		
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {

		return true;

	}

	@Override
	protected boolean setSaveAttributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}
		String error = null;
		String nivel = "1";
		MantenimientoEMPEmprendedoraForm f = (MantenimientoEMPEmprendedoraForm) formBusqueda;
		MantenimientoEMPEmprendedoraService serviceEmp = (MantenimientoEMPEmprendedoraService) getBean("spusicc.mantenimientoEMPEmprendedoraService");

		Map datos = new HashMap();
		Map criteria = new HashMap();

		// Obtenemos los datos del usuario Logueado
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();

		datos.put("codigoUsuario", usuario.getLogin());
		datos.put("codigoPrograma", f.getCodigoPrograma());
		datos.put("codigoCliente", f.getCodigoClienteBuscar());
		datos.put("codigoPeriodo", f.getCodigoPeriodo());
		datos.put("telefonoCasa", f.getTelefonoCasa());
		datos.put("telefonoCelular", f.getTelefonoCelular());
		datos.put("mail", f.getMail());
		datos.put("documentoLegal", f.getDocumentoLegal());
		datos.put("cuentaBancaria", f.getCuentaBancaria());
		datos.put("codigoCci", f.getCodigoCci());
		datos.put("regimen", f.getRegimen());
		datos.put("tipoEmprendedora", f.getTipoEmprendedora());
		datos.put("oidCliente", f.getOidCliente());
		datos.put("indicadorEmprendedora", Constants.EMP_EMPRENDEDORA);

		try {

			if (f.isNewRecord()) {
				// Validar los requisitos para la creacion de emprendedora
				List list = serviceEmp.executeValidarEmprendedora(datos);
				criteria.put("oidCliente", f.getOidCliente());
				criteria.put("codigoUsuario", usuario.getLogin());

				error = (String) datos.get("error");

				if (f.getTipoEmprendedora().equals(
						Constants.EMP_TIPO_EMPRENDEDORA_FASTTRACK)) {
					nivel = (String) datos.get("nivel");
				}

				datos.put("nivel", nivel);

				if (error.equals("0")) {
					// Inserta datos emprendedora
					serviceEmp.insertDatosEmprendedora(datos);

					// Actualiza datos cliente mail, telefono casa, telefono
					// celular
					criteria.put("dato", f.getMail());
					criteria.put("campo", "ML");
					serviceEmp.updateDatosCliente(criteria);
					criteria.put("dato", f.getTelefonoCasa());
					criteria.put("campo", "TF");
					serviceEmp.updateDatosCliente(criteria);
					criteria.put("dato", f.getTelefonoCelular());
					criteria.put("campo", "TM");
					serviceEmp.updateDatosCliente(criteria);

					empRecomendadas = list;

					f.setNivelAsignado(nivel);
					return true;
					//strMessage = "mantenimientoEMPEmprendedoraForm.msg.validarEmprendedora.ok";
					// messages.add(ActionMessages.GLOBAL_MESSAGE, new
					// ActionMessage(strMessage));
					// saveMessages(request, messages);
				} else {
					f.setFlagMostrarErrores(true);
					empRequisitosNoCumpleList = list;
					addError("Error", this.getResourceMessage("mantenimientoEMPEmprendedoraForm.msg.validarEmprendedora.error"));
					return false;
				}
			} else { // Actualizar

				criteria.put("oidCliente", f.getOidCliente());
				criteria.put("codigoUsuario", usuario.getLogin());

				// Actualiza datos cliente mail, telefono casa, telefono celular
				criteria.put("dato", f.getMail());
				criteria.put("campo", "ML");
				serviceEmp.updateDatosCliente(criteria);
				criteria.put("dato", f.getTelefonoCasa());
				criteria.put("campo", "TF");
				serviceEmp.updateDatosCliente(criteria);
				criteria.put("dato", f.getTelefonoCelular());
				criteria.put("campo", "TM");
				serviceEmp.updateDatosCliente(criteria);
				return true;

			}
			

		} catch (Exception ex) {
			String error2 = ex.getMessage();
			if (StringUtils.isBlank(error2))
				error2 = ex.getLocalizedMessage();
			throw new Exception(this.getResourceMessage(
					"mantenimientoCRAGrupoZonaForm.insert.error",
					new Object[] { error2 }));

		}

	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {

		
		return null;

	}

	private void inicializar(MantenimientoEMPEmprendedoraForm f) {

		f.setNombreCliente("");
		f.setRegion("");
		f.setZona("");
		f.setFechaNacimiento("");
		f.setTipoDocumento("");
		f.setDireccion("");
		f.setTelefonoCasa("");
		f.setTelefonoCelular("");
		f.setMail("");
		f.setDocumentoLegal("");
		f.setCuentaBancaria("");
		f.setCodigoCci("");
		f.setRegimen("");
		f.setTipoEmprendedora("");
		f.setCodigoCliente("");
		f.setCodigoClienteBuscar("");
		f.setNivelAsignado("");
		f.setFlagMostrarErrores(false);
		f.setNewRecord(true);

		empRequisitosNoCumpleList = null;
		empRecomendadas = null;

	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonConsultar=false;
		this.mostrarBotonEliminar=false;
		this.mostrarBotonModificar=false;
		this.mostrarBotonNuevo=false;
		
		/* *********** METODO ADD ******** */

		log.debug("add new");
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoEMPEmprendedoraForm f = (MantenimientoEMPEmprendedoraForm) formBusqueda;

		f.setCodigoPais(pais.getCodigo());
		this.inicializar(f);
		MantenimientoEMPEmprendedoraService serviceEmp = (MantenimientoEMPEmprendedoraService) getBean("spusicc.mantenimientoEMPEmprendedoraService");
		ProcesoEMPCargarPreEmprendedorasService serviceEmpProceso = (ProcesoEMPCargarPreEmprendedorasService) getBean("spusicc.procesoEMPCargarPreEmprendedorasService");

		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());

		empTipoEmprendedoraList = serviceEmp.getTipoEmprededoras();
		empRegimenList = serviceEmp.getRegimenes(criteria);
		empProgramasList = serviceEmpProceso.getProgramas();

		// Obtiene la campaña de proceso actual
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
		String periodoActual = controlFacturacion.getCodigoPeriodo();

		inicializar(f);
		f.setCodigoPeriodo(periodoActual);

		// Seteo la longitud del codigo de consultora de acuerdo al pais
		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		longitudCampoClientes = clienteService.getTamanhoNumeroCliente(pais
				.getCodigo());

		f.setLongitudCodigoConsultora(String.valueOf(clienteService
				.getTamanhoNumeroCliente(pais.getCodigo())));

	}

	/**
	 * @param
	 * @throws Exception
	 */

	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoEMPEmprendedoraForm f = (MantenimientoEMPEmprendedoraForm) this.formBusqueda;
		boolean isNew = f.isNewRecord();
		if (isNew) {
			return "mantenimientoEMPEmprendedoraForm.msg.validarEmprendedora.ok";
		} else {
			return "mantenimientoEMPEmprendedoraForm.msg.actualizaEmprendedora.ok";
		}

	}

}
