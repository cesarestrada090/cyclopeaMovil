package biz.belcorp.ssicc.web.spusicc.ape.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.ape.ProcesoAPESecuenciaClientesService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.ape.form.ProcesoAPESecuenciaClientesForm;

/**
 * The Class ProcesoAPESecuenciaClientesAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 22/12/2014
 */
@ManagedBean
@SessionScoped
public class ProcesoAPESecuenciaClientesAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = 2614459806796827496L;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoAPESecuenciaClientesForm form = new ProcesoAPESecuenciaClientesForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executing action : setViewAttributes.");		
        ProcesoAPESecuenciaClientesForm f = (ProcesoAPESecuenciaClientesForm) this.formProceso;
        Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
        f.setCodigoPais(pais.getCodigo());   	
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Exectuting action : prepareParamsBeforeExecute.");
		}		
		params = super.prepareParamsBeforeExecute(params, form);
		Map criteria = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  		
        MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService) 
        															  getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
        PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(criteria);        
        //La fecha sera la obtenida del archivo de control
        params.put("fechaFacturacion",controlFacturacion.getFechaProceso());        
        MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
        //recuperamos el id Pais
        String oidPais = clienteService.getOidPais(criteria);
        params.put("oidPais", oidPais);
		params.put("codigoPais", pais.getCodigo());			
		return params;		
	}
	
	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) throws Exception {
		log.debug("Executing action : executeProcess");
		if(params!=null){
			ProcesoAPESecuenciaClientesService service = (ProcesoAPESecuenciaClientesService) 
														  getBean("spusicc.procesoAPESecuenciaClientesService");
			service.executeProcesoAPECargaRutasCliente(params);
		}
		return params;
	}
    
}
