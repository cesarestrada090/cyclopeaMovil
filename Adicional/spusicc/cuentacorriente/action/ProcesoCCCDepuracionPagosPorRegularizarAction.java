package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCDepuracionPagosPorRegularizarService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.ProcesoCCCDepuracionPagosPorRegularizarForm;

/**
 * The Class ProcesoCCCDepuracionPagosPorRegularizarAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 28/01/2015
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@ManagedBean
@SessionScoped
public class ProcesoCCCDepuracionPagosPorRegularizarAction extends BaseProcesoAbstractAction {
	
	private static final long serialVersionUID = 1288327163917300295L;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoCCCDepuracionPagosPorRegularizarForm form = new ProcesoCCCDepuracionPagosPorRegularizarForm();
		return form;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		
		log.debug("JFA Entrando setViewAttributes");
		
		//Obtenemos los beans básicos de la sesi�n
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();		
		
		String codigoProcesoBatch = this.mPantallaPrincipalBean.getCodigoProcesoBatch();
		String codigoSistema = this.mPantallaPrincipalBean.getCodigoSistema();
		ProcesoCCCDepuracionPagosPorRegularizarForm f = (ProcesoCCCDepuracionPagosPorRegularizarForm) this.formProceso;
		f.setCodigoProcesoBatch(codigoProcesoBatch);
		f.setCodigoSistema(codigoSistema);
		f.setCodigoPais(pais.getCodigo());
														
		log.debug("JFA Finalizando setViewAttributes");
	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("JFA Executing action : prepareParamsBeforeExecute.");
		}
				
		params = super.prepareParamsBeforeExecute(params, form);
						
		log.debug("Los parametros son : " + params.toString());		
				
		return params;
		
	}
	
	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) throws Exception {
		log.debug("JFA Entrando executeProcess");
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		log.debug("usuario "+ usuario.getLogin());
		
		params.put("codigoUsuario", usuario.getLogin());
									
		super.executeProceso();
		
		log.debug("Los parametros del Reporte en el executeProcess son: " + params.toString());	
		
		ProcesoCCCDepuracionPagosPorRegularizarService service = (ProcesoCCCDepuracionPagosPorRegularizarService)getBean("spusicc.procesoCCCDepuracionPagosPorRegularizarService");
		service.executeDepuracionPagosPorRegularizar(params);
		
		log.debug("JFA Finalizando executeProcess");
		
		return params;
	}
		
}