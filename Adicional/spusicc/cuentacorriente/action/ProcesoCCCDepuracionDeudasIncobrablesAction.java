package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCDepuracionDeudasIncobrablesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.ProcesoCCCDepuracionDeudasIncobrablesForm;

@ManagedBean
@SessionScoped
public class ProcesoCCCDepuracionDeudasIncobrablesAction extends
		BaseProcesoAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 916587038703146111L;


	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {

		ProcesoCCCDepuracionDeudasIncobrablesForm form = new ProcesoCCCDepuracionDeudasIncobrablesForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		log.debug("JFA Entrando executeProcess");

		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		log.debug("usuario " + usuario.getLogin());

		params.put("codigoUsuario", usuario.getLogin());

		super.executeProceso();

		log.debug("Los parametros del Reporte en el executeProcess son: "
				+ params.toString());
		ProcesoCCCDepuracionDeudasIncobrablesService service = (ProcesoCCCDepuracionDeudasIncobrablesService) getBean("spusicc.procesoCCCDepuracionDeudasIncobrablesService");
		service.executeDepuracionDeudasIncobrables(params);

		log.debug("JFA Finalizando executeProcess");

		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("JFA Entrando setViewAttributes");

		// Obtenemos los beans básicos de la sesión
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();

		String codigoProcesoBatch = mPantallaPrincipalBean
				.getCodigoProcesoBatch();
		String codigoSistema = mPantallaPrincipalBean.getCodigoSistema();
		ProcesoCCCDepuracionDeudasIncobrablesForm f =(ProcesoCCCDepuracionDeudasIncobrablesForm) this.formProceso;
		f.setCodigoProcesoBatch(codigoProcesoBatch);
		f.setCodigoSistema(codigoSistema);
//		session.setAttribute("codigoProcesoBatch", codigoProcesoBatch);
//		session.setAttribute("codigoSistema", codigoSistema);

		log.debug("JFA Finalizando setViewAttributes");
	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("JFA Executing action : prepareParamsBeforeExecute.");
		}
				
		 params = super.prepareParamsBeforeExecute(params, form);
						
		log.debug("Los parametros son : "
				+ params.toString());
		
				
		return params;

	}


}
