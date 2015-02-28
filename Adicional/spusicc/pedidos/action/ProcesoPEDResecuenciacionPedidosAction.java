package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDResecuenciacionPedidosService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoPEDResecuenciacionPedidosForm;

@ManagedBean
@SessionScoped
public class ProcesoPEDResecuenciacionPedidosAction extends
		BaseProcesoAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3430646839850023181L;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoPEDResecuenciacionPedidosForm form = new ProcesoPEDResecuenciacionPedidosForm();
		// TODO Auto-generated method stub
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		log.debug("executeProcess - ProcesoPEDResecuenciacionPedidosAction");
		ProcesoPEDResecuenciacionPedidosService service = (ProcesoPEDResecuenciacionPedidosService)getBean("spusicc.procesoPEDResecuenciacionPedidosService");		
		ProcesoPEDResecuenciacionPedidosForm f = (ProcesoPEDResecuenciacionPedidosForm)formProceso;	
		f.setFechaFacturacion(DateUtil.getDate(f.getFechaFacturacionD()));
		params.put("codigoPais", f.getCodigoPais());
		params.put("codigoPeriodo", f.getCodigoPeriodo());
		params.put("fechaFacturacion", f.getFechaFacturacion());
		service.executeResecuenciacionPedidos(params);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isErrorEnabled())
			log.debug("Executing action : setViewAttributes.");
		ProcesoPEDResecuenciacionPedidosForm f = (ProcesoPEDResecuenciacionPedidosForm) formProceso;
		String codigoProceso = mPantallaPrincipalBean.getCodigoProcesoBatch();;
		String codigoSistema =mPantallaPrincipalBean.getCodigoSistema();
		
		f.setCodigoProcesoBatch(codigoProceso);
		f.setCodigoSistema(codigoSistema);
//		session.setAttribute("codigoProcesoBatch", codigoProceso);
//		session.setAttribute("codigoSistema", codigoSistema);

		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); 

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteriaPeriodo);

		
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		f.setFechaFacturacionD( formato.parse(controlFacturacion.getFechaProceso()));

	}
}
