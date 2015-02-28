package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCRecepcionarPagosWebService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.ProcesoCCCRecepcionarPagosWebForm;

@SessionScoped
@ManagedBean
public class ProcesoCCCRecepcionarPagosWebAction extends BaseProcesoAbstractAction{



	/**
	 * 
	 */
	private static final long serialVersionUID = 7541047219213118330L;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoCCCRecepcionarPagosWebForm form = new ProcesoCCCRecepcionarPagosWebForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Executing action : setViewAttributes.");
		ProcesoCCCRecepcionarPagosWebForm f =(ProcesoCCCRecepcionarPagosWebForm) this.formProceso;
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		f.setCodigoModulo(getRequest().getParameter("codigoSistema"));
		f.setCodigoPrograma(getRequest().getParameter("codigoPrograma"));
		
		log.debug(f.getCodigoModulo());
		log.debug(f.getCodigoPrograma());
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.action.BaseAbstractAction#prepareParamsBeforeExecute(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
	 */
	protected Map<String, Object> prepareParamsBeforeExecute (Map params,BaseForm form) throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Exectuting action : prepareParamsBeforeExecute.");
		}
		
		ProcesoCCCRecepcionarPagosWebForm f = (ProcesoCCCRecepcionarPagosWebForm) this.formProceso;
		params = super.prepareParamsBeforeExecute(params, form);
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
    	params.put("codigoPais",pais.getCodigo());		
    	params.put("codigoUsuario", usuario.getLogin());
    	params.put("codigoModulo",f.getCodigoModulo());
		params.put("codigoPrograma",f.getCodigoPrograma());
        
		log.debug("Los parametros del prepareParamsBeforeExecute son: "
				+ params.toString());
		
		return params;
		
	}
	
	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
	  	
    	  	
    	ProcesoCCCRecepcionarPagosWebService service = (ProcesoCCCRecepcionarPagosWebService) getBean("spusicc.procesoCCCRecepcionarPagosWebService");    	
    	service.executeRecepcionarPagosWeb(params);
    	return params;
	}


}

