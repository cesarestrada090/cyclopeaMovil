package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCDepuracionSaldosMenoresAcreedoresService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.ProcesoCCCDepuracionSaldosMenoresAcreedoresForm;

/**
 * The Class ProcesoCCCDepuracionSaldosMenoresAcreedoresAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 22/01/2015
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@ManagedBean
@SessionScoped
public class ProcesoCCCDepuracionSaldosMenoresAcreedoresAction extends BaseProcesoAbstractAction {
	
	private static final long serialVersionUID = 6415276559617896297L;
	

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoCCCDepuracionSaldosMenoresAcreedoresForm form = new ProcesoCCCDepuracionSaldosMenoresAcreedoresForm();
		return form;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		
		log.debug("JFA Entrando setViewAttributes");
		
		// Obtenemos los beans básicos de la sesión
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoProcesoBatch = this.mPantallaPrincipalBean.getCodigoProcesoBatch();
		String codigoSistema = this.mPantallaPrincipalBean.getCodigoSistema();
		ProcesoCCCDepuracionSaldosMenoresAcreedoresForm f =(ProcesoCCCDepuracionSaldosMenoresAcreedoresForm) this.formProceso;
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
						
		log.debug("Los parametros son : "+ params.toString());		
				
		return params;		
	}
	
	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) throws Exception {
    					
		log.debug("JFA Entrando executeProcess");
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		log.debug("usuario "+ usuario.getLogin());
		
		params.put("codigoUsuario", usuario.getLogin());
									
		super.executeProceso();
		
		log.debug("Los parametros del Reporte en el executeProcess son: "
				+ params.toString());
		
		ProcesoCCCDepuracionSaldosMenoresAcreedoresService service = (ProcesoCCCDepuracionSaldosMenoresAcreedoresService)getBean("spusicc.procesoCCCDepuracionSaldosMenoresAcreedoresService");
		service.executeDepuracionSaldosMenoresAcreedores(params);
		
		log.debug("JFA Finalizando executeProcess");
		
		return params;
	}
		
}