package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.sto.form.ProcesoSTORecepcionarCuponForm;

@ManagedBean  
@SessionScoped
public class ProcesoSTORecepcionarCuponAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = 1L;
	private static final String CODIGO_PROCESO_BATCH = "12";
	private static final String CODIGO_SISTEMA = "STO";
    
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoSTORecepcionarCuponForm form = new ProcesoSTORecepcionarCuponForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		if(log.isDebugEnabled()){
			log.debug("executeProcess");
		}
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
    	procesoSTOEjecucionValidacionesService.executeRecepcionCuponPago(params);
        return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		
		ProcesoSTORecepcionarCuponForm form = (ProcesoSTORecepcionarCuponForm)this.formProceso;
		form.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		String oidPais = reporteService.getOidString("getOidPaisByCodigoPais",criteriaOperacion);
		
		form.setOidPais(String.valueOf(oidPais));
		
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaPeriodo);
		form.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		form.setFechaProceso(controlFacturacion.getFechaProceso());
		
		form.setCodigoProcesoBatch(CODIGO_PROCESO_BATCH);
		form.setCodigoSistema(CODIGO_SISTEMA);
	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params)throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParamsBeforeExecute");
		}
		ProcesoSTORecepcionarCuponForm form = (ProcesoSTORecepcionarCuponForm)this.formProceso;
		params = super.prepareParamsBeforeExecute(params);
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
		
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaPeriodo);
		
    	params.put("codigoPais",this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		 //criteria.put("codCliente",f.getCodCliente());
		 //criteria.put("valorPagado",f.getValorPagado());
		 //criteria.put("fechaRegistro",f.getFechaRegistro());
    	params.put("codigoUsuario", this.getmPantallaPrincipalBean().getCurrentUser().getCodigo());
    	params.put("codigoDocumento",Constants.STO_TIPO_DOCUMENTO_CUP);
    	params.put("caracterDocumento",Constants.STO_CODIGO_NUMERO_DOCUMENTO_CUPON);
    	params.put("codigoPeriodo",controlFacturacion.getCodigoPeriodo());
    	//dd/mm/yyyy to ddmmyyyy
    	params.put("fechaProceso",controlFacturacion.getFechaProceso().substring(0, 2)+controlFacturacion.getFechaProceso().substring(3, 5)+controlFacturacion.getFechaProceso().substring(6));//ddmmyyyy
		String codigoCompania=procesoSTOEjecucionValidacionesService.getCodigoCompania(params);
		String numLoteSTO = interfazSiCCService.getNumLoteSTO(params);
//		String secuenciaCupon= procesoSTOEjecucionValidacionesService.getSecuenciaCuponPagoSTONextValue();
//		params.put("secuenciaCupon",secuenciaCupon);
//		String secuenciaSTO = procesoSTOEjecucionValidacionesService.getSecuenciaSTONextValue();
//		params.put("secuenciaSTO",secuenciaSTO);
		params.put("numLoteSTO",numLoteSTO);
		params.put("codigoCompania",codigoCompania);
		params.put("indicadorRechazo","0");
		return params;
	}
}