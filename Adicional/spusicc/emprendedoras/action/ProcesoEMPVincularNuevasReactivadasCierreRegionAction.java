package biz.belcorp.ssicc.web.spusicc.emprendedoras.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.emprendedoras.ProcesoEMPVincularNuevasReactivadasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.emprendedoras.form.ProcesoEMPVincularNuevasReactivadasCierreRegionForm;

@ManagedBean
@SessionScoped
public class ProcesoEMPVincularNuevasReactivadasCierreRegionAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = 4719206813800027889L;


	@Override
	protected void setViewAtributes() throws Exception {

		log.info("Entro a ProcesoLETAsignarDesvincularLiderAction - setViewAttributes");
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		ProcesoEMPVincularNuevasReactivadasService service = (ProcesoEMPVincularNuevasReactivadasService)getBean("spusicc.procesoEMPVincularNuevasReactivadasService");
		params.put("codigoPrograma", Constants.LET_CODIGO_PARAM_001);
		service.executeStoreProcedure(params);
		return params;
	}

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoEMPVincularNuevasReactivadasCierreRegionForm form = new ProcesoEMPVincularNuevasReactivadasCierreRegionForm();
		return form;
	}


}
