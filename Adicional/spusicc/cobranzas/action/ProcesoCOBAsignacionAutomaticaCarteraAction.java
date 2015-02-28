package biz.belcorp.ssicc.web.spusicc.cobranzas.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ProcesoCOBAsignacionAutomaticaCarteraService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.ProcesoCOBAsignacionAutomaticaCarteraForm;


@ManagedBean
@SessionScoped
public class ProcesoCOBAsignacionAutomaticaCarteraAction extends BaseProcesoAbstractAction{

	
	private static final long serialVersionUID = -3736172084188737079L;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoCOBAsignacionAutomaticaCarteraForm procesoForm =new ProcesoCOBAsignacionAutomaticaCarteraForm();		
		return procesoForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Entrando setViewAttributes");		
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		log.debug("Entrando executeProcess");
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();			
		params.put("codigoUsuario", usuario.getLogin());
		
		ProcesoCOBAsignacionAutomaticaCarteraService service = (ProcesoCOBAsignacionAutomaticaCarteraService)getBean("spusicc.procesoCOBAsignacionAutomaticaCarteraService");		
		service.executeAsignacionAutomaticaCartera(params);		
		return params;	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseProcesoHiloAbstractAction#prepareParamsBeforeExecute(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
	 */
	protected Map<String, Object> prepareParamsBeforeExecute(Map params) throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Executing action : prepareParamsBeforeExecute.");
		}				
		params = super.prepareParamsBeforeExecute(params);				
		return params;		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseProcesoAbstractAction#afterExecuteProcessSuccess(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
	 */
	protected void afterExecuteProcessSuccess() {
        
		log.debug("Entrando afterExecuteProcessSuccess");		
		ProcesoCOBAsignacionAutomaticaCarteraForm f = (ProcesoCOBAsignacionAutomaticaCarteraForm) this.formProceso;
		
		Map params = new HashMap();		
		params.put("codigoPais", f.getCodigoPais());		
		params.put("codigoModulo",f.getCodigoModulo());
		params.put("codigoProceso",f.getCodigoProceso());
		
		ProcesoCOBAsignacionAutomaticaCarteraService service = (ProcesoCOBAsignacionAutomaticaCarteraService)getBean("spusicc.procesoCOBAsignacionAutomaticaCarteraService");
		service.executeEnvioMail(params);	
		
	}


	

}
