package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCRegistroIncobrablesService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.ProcesoCCCRegistroIncobrablesForm;

@ManagedBean
@SessionScoped
public class ProcesoCCCRegistroIncobrablesAction extends
		BaseProcesoAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 916587038703146111L;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {

		ProcesoCCCRegistroIncobrablesForm form = new ProcesoCCCRegistroIncobrablesForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		if(log.isDebugEnabled()){
			log.debug("Entro a ProcesoCCCRegistroIncobrablesAction - executeProcess");
		}
		
		log.debug("Los parametros del Reporte en el executeProcess son: " + params.toString());
		ProcesoCCCRegistroIncobrablesService service = (ProcesoCCCRegistroIncobrablesService) getBean("spusicc.procesoCCCRegistroIncobrablesService");
		service.executeRegistroIncobrables(params);

		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("Entro a ProcesoCCCRegistroIncobrablesAction - setViewAttributes");
		}
		
		ProcesoCCCRegistroIncobrablesForm f = (ProcesoCCCRegistroIncobrablesForm) formProceso;
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		
		String codigoProcesoBatch = mPantallaPrincipalBean
				.getCodigoProcesoBatch();
		String codigoSistema = mPantallaPrincipalBean.getCodigoSistema();
		f.setCodigoProcesoBatch(codigoProcesoBatch);
		f.setCodigoSistema(codigoSistema);

		
		//Recuperamos el pais logueado
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		String indicadorCompletarCerosNumDocumento  = clienteService.getValorModuloxPaisTipoValidacion(f.getCodigoPais(), Constants.HIP_VALID_COMPLETA_CEROS_DOCUMENTO_IDENTIDAD);		
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		if(StringUtils.isEmpty(indicadorCompletarCerosNumDocumento)) {
			f.setIndicadorCompletarCerosNumDocumento(true);
			//longitud de número de documento de identidad para el país
			f.setLongitudNumeroDocIdentidad(clienteService.getLongitudNumeroDocIdentidad(criteria));
		}else
			f.setIndicadorCompletarCerosNumDocumento(false);
	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entro a ProcesoCCCRegistroIncobrablesAction - prepareParamsBeforeExecute");
		}

		params = super.prepareParamsBeforeExecute(params, form);
		log.debug("Los parametros son : " + params.toString());

		return params;

	}

}
