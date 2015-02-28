package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDEjecutarGPService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.sto.form.ProcesoSTOEjecutarGP2OCForm;

@ManagedBean  
@SessionScoped
public class ProcesoSTOEjecutarGP2OCAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = 1L;
	private static final String CODIGO_PROCESO_BATCH = "11";
	private static final String CODIGO_SISTEMA = "STO";
    
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoSTOEjecutarGP2OCForm form = new ProcesoSTOEjecutarGP2OCForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		if(log.isDebugEnabled()){
			log.debug("executeProcess");
		}
		if(params!=null){
			ProcesoPEDEjecutarGPService procesoPEDEjecutarGPService= (ProcesoPEDEjecutarGPService)getBean("spusicc.procesoPEDEjecutarGPService");
			procesoPEDEjecutarGPService.executeEjecutarGP2(params);
		}
        return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		
		ProcesoSTOEjecutarGP2OCForm form = (ProcesoSTOEjecutarGP2OCForm)this.formProceso;
		Map criteria = new HashMap();
		criteria.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);

		// Carga el periodo actual
		form.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		form.setFechaProceso(controlFacturacion.getFechaProceso());
		
		form.setCodigoProcesoBatch(CODIGO_PROCESO_BATCH);
		form.setCodigoSistema(CODIGO_SISTEMA);
	}
}