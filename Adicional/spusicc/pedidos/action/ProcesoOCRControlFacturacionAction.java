package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessages;
import org.primefaces.context.RequestContext;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoOCRPedidoControlFacturacionForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoOCRPedidoControlFacturacionSearchForm;

@ManagedBean
@SessionScoped
public class ProcesoOCRControlFacturacionAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 5952215183465068093L;
	private List pedControlFacturacionList;
	private List siccMarcaList;
	private List siccCanalList;
	private String disableCombos = "false";

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Executing action : setViewAttributes.");

		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
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
		List list = service.getControlFacturacionByCriteria(criteria);
		this.listaBusqueda = list;
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);

		String indVisualizar = pais.getIndicadorVisualizarMontosFacturacion();

		MantenimientoOCRPedidoControlFacturacionSearchForm f = (MantenimientoOCRPedidoControlFacturacionSearchForm) this.formBusqueda;
		f.setIndVisualizar(indVisualizar);

		// Carga de los combos Marca, Canal
		this.siccMarcaList = interfazSiCCService.getMarcas();
		this.siccCanalList = interfazSiCCService.getCanalesByCodigoISO(usuario
				.getIdioma().getCodigoISO());
		
	}

	public void cerrarCampana(ActionEvent evt)
			throws Exception {
		// TODO Auto-generated method stub
		Map params = new HashMap();
		PedidoControlFacturacion pedControlFact = (PedidoControlFacturacion) this.beanRegistroSeleccionado;
		if (!this.verificarRegistroSeleccionado())
			return;
		
		try {
			String codigoPeriodo =  pedControlFact.getCodigoPeriodo();
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			params.put("codigoPeriodo", codigoPeriodo);
			params.put("codigoPais", pedControlFact.getCodigoPais());
			params.put("login",usuario.getLogin());
			params.put("codigoTipoDocumento", Constants.STO_TIPO_DOCUMENTO_OCC);
			MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			service.cerrarPeriodo(params);
			
			this.find();
			this.addInfo("Información", this.getResourceMessage("proceso.concluido"));
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}

	private PedidoControlFacturacion getControlBean(
			MantenimientoOCRPedidoControlFacturacionSearchForm form) {
		MantenimientoOCRPedidoControlFacturacionSearchForm f = (MantenimientoOCRPedidoControlFacturacionSearchForm) this.formBusqueda;

		PedidoControlFacturacion sistemabusqueda = (PedidoControlFacturacion) this.beanRegistroSeleccionado;

		//String id = sistemabusqueda.getId();

		PedidoControlFacturacion controlFacturacion = new PedidoControlFacturacion();
		controlFacturacion.setCodigoPais(controlFacturacion.getCodigoPais());
		controlFacturacion.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		// Se cierra campanha con estado 1
		controlFacturacion.setEstadoCampanha(Constants.ESTADO_ACTIVO);
		return controlFacturacion;
	}

	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		if (!this.verificarRegistroSeleccionado()) 
			return this.getResourceMessage("errors.select.item");
		if (accion.equals("LOTE")) {
			PedidoControlFacturacion sistemabusqueda = (PedidoControlFacturacion) this.beanRegistroSeleccionado;
			RequestContext context = RequestContext.getCurrentInstance();  
			
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			String id= sistemabusqueda.getCodigoPais() + "-" + sistemabusqueda.getCodigoPeriodo();
			LabelValue retorno = ajax.getNumeroPedidosByLote(id);
			String retornoMensaje = this.getResourceMessage("confirm.actualiza.lote.message");
			if (retorno != null) {
				if (retorno.getValue().equals("0")) {
					retornoMensaje = this.getResourceMessage("mensaje.confirm.cantidad.cero") + " " +
							         retorno.getLabel() + " " +
							         this.getResourceMessage("mensaje.confirm.cantidad.cero.continuar");
				}
			}
			context.addCallbackParam("retornoMensaje", retornoMensaje);
		}
		return "";
	}

	/**
	 * Metodo que actualiza el numero de lote
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void actualizaLote(ActionEvent evt)  {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'actualizaLote' method");
		}
		if (!this.verificarRegistroSeleccionado())
			return;
		 
		try {
			MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");


			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			PedidoControlFacturacion controlFacturacion = (PedidoControlFacturacion) this.beanRegistroSeleccionado;
			String codigoPeriodo = controlFacturacion.getCodigoPeriodo();
	        Map map = BeanUtils.describe(controlFacturacion);
	    
	        controlFacturacion.setUsuario(usuario.getLogin());
	        map.put("controlFacturacion", controlFacturacion);        
	        service.executeActualizaNumeroLote(map);
			String mensaje = this.getResourceMessage("actualiza.lote");
			this.addInfo("Información :", mensaje);
			
			this.find();
		}
		catch(Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Metodo que actualiza los indicadores del archivo de control en forma
	 * contraria
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void actualizaIndicadores(ActionEvent evt) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'actualizaIndicadores' method");
		}

		if (!this.verificarRegistroSeleccionado())
			return;
		try {
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			PedidoControlFacturacion controlFacturacion = (PedidoControlFacturacion) this.beanRegistroSeleccionado;
			MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			Map map = BeanUtils.describe(controlFacturacion);
			if (log.isDebugEnabled()) {
				log.debug("Valores 'map' actualizaIndicadores" + map);
			}
	
			controlFacturacion.setUsuario(usuario.getLogin());
			map.put("controlFacturacion", controlFacturacion);
			service.executeActualizarContrarioIndicadores(map);
	
			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoPeriodo", controlFacturacion.getCodigoPeriodo());
			List list = service.getControlFacturacionByCriteria(criteria);
			this.pedControlFacturacionList = list;
			this.listaBusqueda = list;
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
	
			String mensaje = this
					.getResourceMessage("actualiza.contrario.indicadores");
			this.addInfo("Información :", mensaje);
		}
		catch(Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "procesoOCRControlFacturacionList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "procesoOCRControlFacturacionForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoOCRPedidoControlFacturacionSearchForm form = new MantenimientoOCRPedidoControlFacturacionSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoOCRPedidoControlFacturacionSearchForm form = (MantenimientoOCRPedidoControlFacturacionSearchForm) this.formBusqueda;
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		Map criteria = BeanUtils.describe(form);
		List list = service.getControlFacturacionByCriteria(criteria);
		pedControlFacturacionList = list;
		return list;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub

		// ActionMessages messages = new ActionMessages();
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		MantenimientoOCRPedidoControlFacturacionSearchForm form = (MantenimientoOCRPedidoControlFacturacionSearchForm) this.formBusqueda;
		PedidoControlFacturacion controlFacturacion = getControlBean(form);
		Map map = BeanUtils.describe(controlFacturacion);
		if (log.isDebugEnabled()) {
			log.debug("Valores 'map' actualizaIndicadores" + map);
		}
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		controlFacturacion.setUsuario(usuario.getLogin());
		map.put("controlFacturacion", controlFacturacion);
		service.executeActualizarContrarioIndicadores(map);

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPeriodo", controlFacturacion.getCodigoPeriodo());
		List list = service.getControlFacturacionByCriteria(criteria);
		pedControlFacturacionList = list;
		String mensaje = this
				.getResourceMessage("actualiza.contrario.indicadores");
		addInfo("Infomación:", mensaje);
		return true;
	}

	private boolean isUpdate(
			MantenimientoOCRPedidoControlFacturacionForm controlFacturacionForm) {
		boolean updateFlag = true;
		// String id = request.getParameter("id");
		String id = controlFacturacionForm.getId();
		if (StringUtils.isBlank(id)) {
			updateFlag = false;
		}
		return updateFlag;
	}

	private boolean validationSuccessful(
			MantenimientoOCRPedidoControlFacturacionForm form) {
		boolean isOk = true;
		ActionMessages errors = new ActionMessages();
		/*
		 * if (form.getCodigoVentCupIni() == null ||
		 * form.getCodigoVentCupIni().trim().length() == 0) {
		 * errors.add("codigoVentCupIni", new ActionMessage("errors.required",
		 * "CodigoVentCupIni")); } else { try {
		 * Integer.parseInt(form.getCodigoVentCupIni()); } catch
		 * (NumberFormatException e) { errors.add("codigoVentCupIni", new
		 * ActionMessage("errors.number", "CodigoVentCupIni")); } }
		 */
		if (!errors.isEmpty()) {
			// saveErrors(request, errors);
			isOk = false;
		}
		return isOk;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}

		// ActionMessages messages = new ActionMessages();
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		MantenimientoOCRPedidoControlFacturacionForm controlFacturacionForm = (MantenimientoOCRPedidoControlFacturacionForm) this.formMantenimiento;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		if (validationSuccessful(controlFacturacionForm)) {
			PedidoControlFacturacion controlFacturacion = new PedidoControlFacturacion();
			log.debug("____aqui  " + controlFacturacionForm.getNumLote());
			BeanUtils
					.copyProperties(controlFacturacion, controlFacturacionForm);
			controlFacturacion.setUsuario(usuario.getLogin());
			if (isUpdate(controlFacturacionForm)) {
				log.debug("update bean " + controlFacturacion);
				service.updateControlFacturacion(controlFacturacion, usuario);
				// messages.add(ActionMessages.GLOBAL_MESSAGE, new
				// ActionMessage(
				// "campana.updated"));
				// saveMessages(request, messages);

			} else {
				log.debug("insert bean " + controlFacturacion);
				controlFacturacion.setEstadoCampanha(Constants.NUMERO_CERO);
				controlFacturacion.setNumeroLote(controlFacturacionForm
						.getNumLote());
				controlFacturacionForm.setFechaProceso(DateUtil
						.convertDateToString(controlFacturacionForm
								.getFechaProcesoD()));
				log.debug("____numLote = " + controlFacturacion.getNumeroLote());
				service.insertControlFacturacion(controlFacturacion, usuario);
				// messages.add(ActionMessages.GLOBAL_MESSAGE, new
				// ActionMessage(
				// "campana.added"));
				// saveMessages(request, messages);
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'edit' method");
		}
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		MantenimientoOCRPedidoControlFacturacionForm controlFacturacionForm = new MantenimientoOCRPedidoControlFacturacionForm();

		// ControlFacturacion control = (ControlFacturacion)
		// this.beanRegistroSeleccionado;

		if (!this.accion.equals(this.ACCION_NUEVO)) {
			PedidoControlFacturacion pedControlFact = (PedidoControlFacturacion) this.beanRegistroSeleccionado;
			// HashMap<String, Object> sistemabusqueda = (HashMap<String,
			// Object>)this.beanRegistroSeleccionado;
			// MantenimientoOCRPedidoControlFacturacionForm manOCRPPedidoConFact
			// = (MantenimientoOCRPedidoControlFacturacionForm)
			// this.formMantenimiento;
			this.disableCombos = "true";
			// String id = control.getId();
			String id = pedControlFact.getId();
			PedidoControlFacturacion controlFacturacion = service
					.getControlFacturacionById(getCriteria(id));
			BeanUtils
					.copyProperties(controlFacturacionForm, controlFacturacion);
			controlFacturacionForm.setNumLote(controlFacturacion
					.getNumeroLote());
			String fecha = controlFacturacionForm.getFechaProceso();
			controlFacturacionForm.setFechaProcesoD(DateUtil
					.convertStringToDate(fecha));

		} else {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			this.disableCombos = "false";
			// //Carga Numero de lotes
			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			String periodoSigiente = null;
			periodoSigiente = service.getPedidosSiguienteCampanha(criteria);
			controlFacturacionForm.setCodigoPeriodo(periodoSigiente);
			controlFacturacionForm.setNumLote(service.getNumeroLote(criteria));
			controlFacturacionForm.setFechaProcesoD(new Date());

			controlFacturacionForm
					.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
			controlFacturacionForm
					.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
			controlFacturacionForm.setMontoMinimoFact(new BigDecimal(0));
			controlFacturacionForm.setMontoMaximo(new BigDecimal(0));
			controlFacturacionForm.setMontoMinimoDeuda(new BigDecimal(0));
			controlFacturacionForm.setMontoMinimoAcept(new BigDecimal(0));
			controlFacturacionForm.setUnidadesMaximo(0);
			// return controlFacturacionForm;
		}
		return controlFacturacionForm;

	}

	private Map getCriteria(String id) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", StringUtils.split(id, "-")[0]);
		criteria.put("codigoPeriodo", StringUtils.split(id, "-")[1]);
		return criteria;
	}

	/**
	 * @return the pedControlFacturacionList
	 */
	public List getPedControlFacturacionList() {
		return pedControlFacturacionList;
	}

	/**
	 * @param pedControlFacturacionList
	 *            the pedControlFacturacionList to set
	 */
	public void setPedControlFacturacionList(List pedControlFacturacionList) {
		this.pedControlFacturacionList = pedControlFacturacionList;
	}

	/**
	 * @return the disableCombos
	 */
	public String getDisableCombos() {
		return disableCombos;
	}

	/**
	 * @param disableCombos
	 *            the disableCombos to set
	 */
	public void setDisableCombos(String disableCombos) {
		this.disableCombos = disableCombos;
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
}