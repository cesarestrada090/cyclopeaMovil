package biz.belcorp.ssicc.web.spusicc.comision.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCierreProcesosCampannaService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.comision.form.ProcesoCOMCierreProcesosCampannaForm;

@ManagedBean
@SessionScoped
public class ProcesoCOMCierreProcesosCampannaAction extends
		BaseProcesoAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7427934238003866056L;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoCOMCierreProcesosCampannaForm form = new ProcesoCOMCierreProcesosCampannaForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ProcesoCOMCierreProcesosCampannaForm f = (ProcesoCOMCierreProcesosCampannaForm) this.formProceso;
		f.setCodigoPais(pais.getCodigo());

		/* Colocando procesos a ejecutar */
		ArrayList resultado = new ArrayList();
		this.adicionarProceso(resultado, "1",
				"ProcesoCOMCierreProcesosCampannaForm.proceso01");

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

	/**
	 * Obtiene descripcion del proceso del archivo de recursos
	 * 
	 * @param resultado
	 * @param messageResources
	 * @param codigo
	 * @param keyMensaje
	 */
	private void adicionarProceso(ArrayList resultado, String codigo,
			String keyMensaje) {
		Base bean = new Base();
		String proceso = this.getResourceMessage(keyMensaje);
		bean.setCodigo(codigo);
		bean.setDescripcion(proceso);
		resultado.add(bean);
	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {

		params = super.prepareParamsBeforeExecute(params, form);

		return params;

	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		log.debug("Los parametros del Reporte en el executeProcess son: "
				+ params.toString());

		ProcesoCOMCierreProcesosCampannaService service = (ProcesoCOMCierreProcesosCampannaService) getBean("spusicc.procesoCOMCierreProcesosCampannaService");

		// Invocando al Proceso de Cerrar Cursos Vigentes / Actualizar
		// Parametros Cursos
		service.executeCierreProcesosCampanna(params);
		return params;
	}

}
