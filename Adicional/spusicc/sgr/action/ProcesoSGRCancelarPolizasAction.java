package biz.belcorp.ssicc.web.spusicc.sgr.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.sgr.ProcesoSGRGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.sgr.form.ProcesoSGRCancelarPolizasForm;

@SessionScoped
@ManagedBean
public class ProcesoSGRCancelarPolizasAction extends BaseProcesoAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2937372656297657821L;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoSGRCancelarPolizasForm form = new ProcesoSGRCancelarPolizasForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		params.put("login", usuario.getLogin());
		Map map = new HashMap();
		map.put("codigoPais", params.get("codigoPais"));
		map.put("login", params.get("login"));
		log.debug("Los parametros del Generar en el executeProcess son: "
				+ map.toString());

		ProcesoSGRGenericoService procesoService = (ProcesoSGRGenericoService)
						getBean("spusicc.procesoSGRGenericoService");
		
		procesoService.executeCancelarPolizas(map);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
