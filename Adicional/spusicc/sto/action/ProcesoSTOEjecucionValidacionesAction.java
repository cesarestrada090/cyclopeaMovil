package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitado;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.sto.form.ProcesoSTOEjecucionValidacionesForm;

@ManagedBean
@SessionScoped
public class ProcesoSTOEjecucionValidacionesAction extends
		BaseProcesoAbstractAction {
	private List stoTipoDocumentoList;
	private LabelValue[] stoDetalleDocumentoList;
	private boolean primerPaso=true;
	
	

	public boolean isPrimerPaso() {
		return primerPaso;
	}

	public void setPrimerPaso(boolean primerPaso) {
		this.primerPaso = primerPaso;
	}

	public List getStoTipoDocumentoList() {
		return stoTipoDocumentoList;
	}

	public void setStoTipoDocumentoList(List stoTipoDocumentoList) {
		this.stoTipoDocumentoList = stoTipoDocumentoList;
	}

	public LabelValue[] getStoDetalleDocumentoList() {
		return stoDetalleDocumentoList;
	}

	public void setStoDetalleDocumentoList(LabelValue[] stoDetalleDocumentoList) {
		this.stoDetalleDocumentoList = stoDetalleDocumentoList;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3430646839850023181L;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoSTOEjecucionValidacionesForm form = new ProcesoSTOEjecucionValidacionesForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		this.mostrarBotonExecute=false;
		AccionTipoDocumento accionTipoDocumento = (AccionTipoDocumento) params
				.get("accionTipoDocumento");
		ProcesoSTOExecutionService service = (ProcesoSTOExecutionService) getBean("spusicc.procesoSTOExecutionService");
		this.primerPaso=false;
		service.execute(accionTipoDocumento, params, null);
		return params;
	}
	

	@Override
	public void afterExecuteProcess(BaseProcesoForm form) throws Exception {
		
		super.afterExecuteProcess(form);
	}
	@Override
	protected void setViewAtributes() throws Exception {
		ProcesoSTOEjecucionValidacionesForm f = (ProcesoSTOEjecucionValidacionesForm) formProceso;
		log.debug("Executing action : setViewAttributes.");
		Map criteria = new HashMap();
		criteria.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());
		criteria.put("codigoUsuario", mPantallaPrincipalBean.getCurrentUser()
				.getLogin());
		String codigoProceso =f.getCodigoProcesoBatch();
		String codigoSistema =f.getCodigoSistema();
		
		f.setCodigoProcesoBatch(codigoProceso);
		f.setCodigoSistema(codigoSistema);

		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");

		stoTipoDocumentoList = procesoSTOEjecucionValidacionesService
				.getTiposDocumentosSTO(criteria);

		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", mPantallaPrincipalBean
				.getCurrentCountry().getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO);

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteriaPeriodo);

		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setFechaProceso(controlFacturacion.getFechaProceso());

	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Exectuting action : prepareParamsBeforeExecute.");
		}

		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		ProcesoSTOEjecucionValidacionesForm f = (ProcesoSTOEjecucionValidacionesForm) form;

		String codigoTipoDocumento = f.getTipoDocumento();
		TipoDocumentoDigitado tipoDocumentoDigitado = procesoSTOEjecucionValidacionesService
				.getTipoDocumentoDigitado(codigoTipoDocumento);

		// Setea el proceso Batch Actual a ejecutarse
		String codigoProcesoBatch = tipoDocumentoDigitado
				.getCodigoProcesoBatch();

		// Setea el proceso en session para la consulta

		f.setCodigoProcesoBatch(mPantallaPrincipalBean.getCodigoProcesoBatch());

		params = super.prepareParamsBeforeExecute(params);

		params.put("usuario", mPantallaPrincipalBean.getCurrentUser());

		if (codigoTipoDocumento.equalsIgnoreCase("OCC")) {
			params.put("codigoPeriodo", f.getCodigoPeriodo());
		} else {
			params.put("codigoPeriodo", "");
		}

		AccionTipoDocumento accionTipoDocumento = new AccionTipoDocumento(
				mPantallaPrincipalBean.getCurrentCountry().getCodigo(),
				codigoTipoDocumento, Constants.STO_ACCI_VALI_MASI);
		params.put("accionTipoDocumento", accionTipoDocumento);

		params.put("codigoProcesoBatch", codigoProcesoBatch);

		return params;

	}

	public void cargarDocumentos(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("Load Docs");
		}

		String tipoDoc = (String) val.getNewValue();

		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		this.stoDetalleDocumentoList = aSvc.getDocumentos(mPantallaPrincipalBean.getCurrentCountry().getCodigo(),
				tipoDoc);

	}
}
