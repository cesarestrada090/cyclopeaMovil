package biz.belcorp.ssicc.web.spusicc.sto.action;


import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.sto.form.ProcesoSTOGrabarPedRecForm;

@ManagedBean
@SessionScoped
public class ProcesoSTOGrabarPedRecAction extends BaseProcesoAbstractAction {
	
	private static final long serialVersionUID = -4978407059022581447L;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.action.BaseAbstractAction#setViewAttributes(javax.servlet.http.HttpServletRequest, org.apache.struts.action.ActionForm)
	 */
	protected void setViewAttributes(HttpServletRequest request, ActionForm form) {		
		log.debug("Executing action : setViewAttributes.");
		HttpSession session = request.getSession(true);
		String codigoProceso = request.getParameter("codigoProcesoBatch");
		String codigoSistema = request.getParameter("codigoSistema");
		session.setAttribute("codigoProcesoBatch", codigoProceso);	
		session.setAttribute("codigoSistema", codigoSistema);
		//ProcesoSTOGrabarPedRecForm f = (ProcesoSTOGrabarPedRecForm)form;
		//f.setCodigoPais(getCodigoPais(request));
	}

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoSTOGrabarPedRecForm form = new ProcesoSTOGrabarPedRecForm();
		return form;
	}


	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		log.debug("-------- Execute process --------");
    	ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
    	procesoSTOEjecucionValidacionesService.STOGrabarPedRec(params);
    
    	return params;
	}


	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Executing action : setViewAttributes.");
//		String codigoProceso = request.getParameter("codigoProcesoBatch");
//		String codigoSistema = request.getParameter("codigoSistema");
//		session.setAttribute("codigoProcesoBatch", codigoProceso);	
//		session.setAttribute("codigoSistema", codigoSistema);
		//ProcesoSTOGrabarPedRecForm f = (ProcesoSTOGrabarPedRecForm)form;
		//f.setCodigoPais(getCodigoPais(request));
		
	} 
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute (Map params, BaseForm form)
	throws Exception{		
		if (log.isDebugEnabled()) {
			log.debug("Exectuting action : prepareParamsBeforeExecute.");
		}

		params=super.prepareParamsBeforeExecute(params, form);
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario= this.mPantallaPrincipalBean.getCurrentUser();
		//ProcesoSTOGrabarPedRecForm f = (ProcesoSTOGrabarPedRecForm)form;
		//f.setCodigoPais(getCodigoPais(request));
		params.put("codigoPais", pais.getCodigo());		
		params.put("codigoUsuario",usuario.getLogin());		
		return params;
	}
	
}
