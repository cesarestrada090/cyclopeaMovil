package biz.belcorp.ssicc.web.spusicc.edu.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.edu.ProcesoEDURecodificacionConsultoraService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaHIPClientePOPUPSearchAction;
import biz.belcorp.ssicc.web.spusicc.edu.form.ProcesoEDURecodificacionConsultoraForm;

@SessionScoped
@ManagedBean
public class ProcesoEDURecodificacionConsultoraAction extends
		BaseProcesoAbstractAction {
	private List eduEmpresaComercializadoraList;
	private boolean mostrarPopUpCliente = false;
	private static final String POPUP_CLIENTES = "SCLIENTES";
	@ManagedProperty(value = "#{busquedaHIPClientePOPUPSearchAction}")
	private BusquedaHIPClientePOPUPSearchAction busquedaHIPClientePOPUPSearchAction;

	@Override
	protected void setSalirPopup() {
		this.mostrarPopUpCliente = false;
		this.busquedaHIPClientePOPUPSearchAction
				.setBeanRegistroSeleccionado(null);
	}

	
	@Override
	protected java.util.Map<String,Object> prepareParamsBeforeExecute(Map<String,Object> params, BaseForm form) throws Exception {
		ProcesoEDURecodificacionConsultoraForm f = (ProcesoEDURecodificacionConsultoraForm) formProceso;
		params = super.prepareParamsBeforeExecute(params);
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();
		String codigoPais = pais.getCodigo();
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		params.put("codigoPais", codigoPais);
		params.put("codigoEmpresa", f.getCodigoEmpresa());
		params.put("usuarioLogin", usuario.getLogin());
		params.put("codigoConsultora", f.getCodigoConsultora());
		params.put("codigoConsultoraRecod", f.getCodigoConsultoraRecod());
		return params;
	};

	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}
		if (accion.equals(this.POPUP_CLIENTES)) {
			this.busquedaHIPClientePOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaHIPClientePOPUPSearchAction.isSeleccionoRegistro()) {
				Map clienteHipMap = (Map) this.busquedaHIPClientePOPUPSearchAction
						.getBeanRegistroSeleccionado();
				ProcesoEDURecodificacionConsultoraForm f = (ProcesoEDURecodificacionConsultoraForm) this.formProceso;
				f.setCodigoConsultora(((String) clienteHipMap
						.get("codigoCliente")));
				String apellido2 = (String) clienteHipMap.get("apellido2");
				String nombre1 = (String) clienteHipMap.get("nombre1");
				f.setDescripcionConsultora(nombre1 + " " + apellido2);

				this.busquedaHIPClientePOPUPSearchAction
						.setBeanRegistroSeleccionado(null);
			}
		}
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

	public BusquedaHIPClientePOPUPSearchAction getBusquedaHIPClientePOPUPSearchAction() {
		return busquedaHIPClientePOPUPSearchAction;
	}

	public void setBusquedaHIPClientePOPUPSearchAction(
			BusquedaHIPClientePOPUPSearchAction busquedaHIPClientePOPUPSearchAction) {
		this.busquedaHIPClientePOPUPSearchAction = busquedaHIPClientePOPUPSearchAction;
	}

	public static String getPopupClientes() {
		return POPUP_CLIENTES;
	}

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoEDURecodificacionConsultoraForm form = new ProcesoEDURecodificacionConsultoraForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		log.debug("Los parametros en el executeProcess Recodificacion  son: "
				+ params.toString());

		ProcesoEDURecodificacionConsultoraService service = (ProcesoEDURecodificacionConsultoraService) getBean("edu.procesoEDURecodificacionConsultoraService");
		service.executeRecodificacionConsultora(params);
        setMensajeError(params);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		try {
			log.debug("inicio setViewAttributes");
			ProcesoEDURecodificacionConsultoraForm f = (ProcesoEDURecodificacionConsultoraForm) formProceso;
			f.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry().getCodigo());
			inicializarForm(f);
			log.debug("codigo Pais " + f.getCodigoPais());
			loadCombos(f);
		} catch (Exception e) {
			ActionMessages messages = new ActionMessages();
			String error = e.getMessage();
			if (StringUtils.isBlank(error))
				error = e.getLocalizedMessage();
			messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
					"errors.detail", error));

		}

	}

	private void inicializarForm(ProcesoEDURecodificacionConsultoraForm f) {
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		f.setDescripcionConsultora("");
		f.setCodigoConsultora("");
		f.setCodigoConsultoraRecod("");
	}

	private void setMensajeError(Map params) {
		String keyMensaje = (String) params.get("mensajeError");
		log.debug("KeyMessage " + keyMensaje);

		if (StringUtils.isNotEmpty(keyMensaje)) {
			
			String mensaje =this.getResourceMessage(keyMensaje);
			params.put("mensajeError", mensaje);
		}

	}

	public String setValidarProceso(){
		ProcesoEDURecodificacionConsultoraForm f = (ProcesoEDURecodificacionConsultoraForm) formProceso;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		String longitudCodPaid=aSvc.getLongitudCodCliente(mPantallaPrincipalBean.getCurrentCountry().getCodigo());

		return "";
	}
	protected String setErrorLogicaNegocio(Map params) {
		String mensaje = (String) params.get("mensajeError");
		if (StringUtils.isBlank(mensaje))
			return null;
		return mensaje;
	}

	private void loadCombos(ProcesoEDURecodificacionConsultoraForm f)
			throws Exception {

		Pais pais = mPantallaPrincipalBean.getCurrentCountry();
		EmpresaComercializadora parametroEmpresa = new EmpresaComercializadora();
		parametroEmpresa.setCodigoPais(pais.getCodigo());
		parametroEmpresa.setEstadoRegistro(Constants.ESTADO_ACTIVO);

		MantenimientoEDUGenericoService siccService = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
		List listaEmpresa = siccService
				.getEmpresasComercializadorasByPais(parametroEmpresa);
		eduEmpresaComercializadoraList = listaEmpresa;

	}

	public List getEduEmpresaComercializadoraList() {
		return eduEmpresaComercializadoraList;
	}

	public void setEduEmpresaComercializadoraList(
			List eduEmpresaComercializadoraList) {
		
		this.eduEmpresaComercializadoraList = eduEmpresaComercializadoraList;
	}

}
