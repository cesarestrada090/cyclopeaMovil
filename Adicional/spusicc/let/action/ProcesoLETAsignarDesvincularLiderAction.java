package biz.belcorp.ssicc.web.spusicc.let.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.let.ProcesoLETAsignarDesvincularLiderService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.let.form.ProcesoLETAsignarDesvincularLiderForm;

@ManagedBean
@SessionScoped
public class ProcesoLETAsignarDesvincularLiderAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = 4719206813800027888L;
	private List cupProgramasList;

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub

		log.info("Entro a ProcesoLETAsignarDesvincularLiderAction - setViewAttributes");

		// -- Variables
		ProcesoLETAsignarDesvincularLiderForm f = (ProcesoLETAsignarDesvincularLiderForm) formProceso;
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();

		// -- Capturar Periodo Actual --------------------------------
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		String periodoActual = ((HashMap) interfazSiCCService
				.getPeriodoFechaProcesoActual(criteria).get(0)).get("cod_peri")
				.toString();

		// -- Inicializar Formulario ---------------------------------
		f.setCodigoPais(pais.getCodigo());
		f.setCodigoPeriodo(periodoActual);

		log.info("Entro a ProcesoLETAsignarDesvincularLiderAction - setViewAttributes");
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		log.info("Entro a ProcesoLETAsignarDesvincularLiderAction - executeProcess");

		// -- Variables
		ProcesoLETAsignarDesvincularLiderForm f = (ProcesoLETAsignarDesvincularLiderForm) formProceso;
		ProcesoLETAsignarDesvincularLiderService service = (ProcesoLETAsignarDesvincularLiderService) getBean("spusicc.procesoLETAsignarDesvincularLiderService");

		// -- Parametros proceso
		params.put("codigoPais", f.getCodigoPais());
		params.put("codigoPeriodo", f.getCodigoPeriodo());

		// -- Logica
		service.executeAsignarDesvincularLider(params);

		log.info("Salio a ProcesoLETAsignarDesvincularLiderAction - executeProcess");
		return params;
	}

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoLETAsignarDesvincularLiderForm form = new ProcesoLETAsignarDesvincularLiderForm();
		return form;
	}

	/**
	 * @return the cupProgramasList
	 */
	public List getCupProgramasList() {
		return cupProgramasList;
	}

	/**
	 * @param cupProgramasList
	 *            the cupProgramasList to set
	 */
	public void setCupProgramasList(List cupProgramasList) {
		this.cupProgramasList = cupProgramasList;
	}
}
