package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitadoPK;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECDigitacionCDRService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECLiquidacionBoletaRecojoService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.ProcesoRECEnvioReclamosDigitadosForm;

@ManagedBean  
@SessionScoped
public class ProcesoRECEnvioReclamosDigitadosAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = 1L;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoRECEnvioReclamosDigitadosForm form = new ProcesoRECEnvioReclamosDigitadosForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		if(log.isDebugEnabled()){
			log.debug("executeProcess");
		}
		if(params!=null){
			MantenimientoRECDigitacionCDRService service = (MantenimientoRECDigitacionCDRService)getBean("spusicc.mantenimientoRECDigitacionCDRService");
			service.executeEnviarReclamosDigitados(params);									
		}
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		Map criteria = new HashMap();
		criteria.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
        PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(criteria);		
        
        ProcesoRECEnvioReclamosDigitadosForm f = (ProcesoRECEnvioReclamosDigitadosForm)this.formProceso;
        f.setCodigoPais(controlFacturacion.getCodigoPais());
        f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params)throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParamsBeforeExecute");
		}
		params = super.prepareParamsBeforeExecute(params);
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		params.put("codigoUsuario",usuario.getLogin());
		params.put("codigoDocumento",Constants.TIPO_DOCUMENTO_SPVC);
		params.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		
		MantenimientoRECLiquidacionBoletaRecojoService service = (MantenimientoRECLiquidacionBoletaRecojoService)getBean("spusicc.mantenimientoRECLiquidacionBoletaRecojoService");
		
		TipoDocumentoDigitadoPK tipoDocumentoDigitadoPK = new TipoDocumentoDigitadoPK(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),Constants.STO_TIPO_DOCUMENTO_SPVC);
		ProcesoSTOService stoService = (ProcesoSTOService)getBean("spusicc.procesoSTOService");
		String numLoteSTO=stoService.updateLoteSTO(tipoDocumentoDigitadoPK);
		
		params.put("numeroLote", numLoteSTO);
		return params;
	}

	

}