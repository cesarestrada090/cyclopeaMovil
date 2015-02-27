package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.struts.action.ActionMessages;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDReemplazoProdImpNacService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoPEDReemplazoProdImpNacForm;

@ManagedBean
@SessionScoped
public class ProcesoPEDReemplazoProdImpNacAction extends
		BaseProcesoAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3430646839850023181L;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoPEDReemplazoProdImpNacForm form = new ProcesoPEDReemplazoProdImpNacForm();
		// TODO Auto-generated method stub
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		log.debug("ProcesoPEDReemplazoProdImpNacAction - executeProcess");

		ProcesoPEDReemplazoProdImpNacForm f = (ProcesoPEDReemplazoProdImpNacForm) formProceso;

		ProcesoPEDReemplazoProdImpNacService service = (ProcesoPEDReemplazoProdImpNacService) getBean("spusicc.pedidos.procesoPEDReemplazoProdImpNacService");

		String codigoPeriodo = f.getCodigoPeriodo();
		params.put("codigoPeriodo", codigoPeriodo);

		String indicadorFaturacion = service
				.getIndicadorPedidosFacturadosPeriodo(params);

		ActionMessages messages = new ActionMessages();

		f.setNumeroRegistros("0");
		if (indicadorFaturacion.equals("N")) {
			String numeroRegistros = service
					.getRegistrosProcesadosReemplazoProdImpNac(params);
			service.executeReemplazoProdImpNac(params);
			f.setNumeroRegistros(numeroRegistros);
			
		} else {
			
			String mensaje =this.getReportResourceMessage("procesoPEDReemplazoProdImpNacForm.pedidosFacturadosPeriodo");

			throw new Exception(mensaje);
		}

		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("MantenimientoOCRReemplazosAction - setViewAttributes");

		ProcesoPEDReemplazoProdImpNacForm f = (ProcesoPEDReemplazoProdImpNacForm) formProceso;

		Map criteria = new HashMap();

		criteria.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());

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

		// Setea la campaña con el valor del Archivo de control

		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());

	}
	
}
