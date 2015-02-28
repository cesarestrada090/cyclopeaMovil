package biz.belcorp.ssicc.web.spusicc.inc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.inc.ProcesoINCCargaOrdenesRetailService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.inc.form.ProcesoINCCargaOrdenesRetailForm;

@ManagedBean
@SessionScoped
public class ProcesoINCCargaOrdenesRetailAction extends
		BaseProcesoAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3641167432778591411L;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoINCCargaOrdenesRetailForm form = new ProcesoINCCargaOrdenesRetailForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		ProcesoINCCargaOrdenesRetailForm f = (ProcesoINCCargaOrdenesRetailForm) this.formProceso;

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

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
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteria);

		// Carga de PeriodoProceso y Fecha Facturacion
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());

	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		ProcesoINCCargaOrdenesRetailForm f = (ProcesoINCCargaOrdenesRetailForm) this.formProceso;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		// validamos si el codigo de periodo existe
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		String[] fechas = ajaxService.getIntervalosFechaFacturasVentaDirecta(
				f.getCodigoPeriodo(), pais.getCodigo(),
				Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT,
				null);

		if (fechas == null) {
			String mensaje = this
					.getResourceMessage("procesoINCCargaOrdenesRetailForm.msg.validarPeriodo "
							+ f.getCodigoPeriodo());
			throw new Exception(mensaje);
		}

		params = super.prepareParamsBeforeExecute(params, form);

		// validamos si el codigo de periodo ya fue procesado
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());

		ProcesoINCCargaOrdenesRetailService service = (ProcesoINCCargaOrdenesRetailService) getBean("spusicc.procesoINCCargaOrdenesRetailService");

		if (service.existeCargaOrdenesRetailProcesado(criteria)) {
			String mensaje = this
					.getResourceMessage("procesoINCCargaOrdenesRetailForm.msg.validarPeriodo "
							+ f.getCodigoPeriodo());
			throw new Exception(mensaje);
		}

		return params;

	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		log.debug("Los parametros del Proceso en el executeProcess son: "
				+ params.toString());

		ProcesoINCCargaOrdenesRetailService service = (ProcesoINCCargaOrdenesRetailService) getBean("spusicc.procesoINCCargaOrdenesRetailService");

		service.executeCargaOrdenesRetail(params);

		return params;
	}

}
