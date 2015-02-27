package biz.belcorp.ssicc.web.spusicc.mae.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.ProcesoMAEClasificacionClientesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.mae.form.ProcesoMAEClasificacionLoveClientesForm;

@ManagedBean
@SessionScoped
public class ProcesoMAEClasificacionLoveClientesAction extends
		BaseProcesoAbstractAction {

	private static final long serialVersionUID = 5011228263228378670L;
	private LabelValue[] siccPeriodoList = {};
	private List siccMarcaList = new ArrayList();

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoMAEClasificacionLoveClientesForm form = new ProcesoMAEClasificacionLoveClientesForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		ProcesoMAEClasificacionClientesService service = (ProcesoMAEClasificacionClientesService) getBean("spusicc.procesoMAEClasificacionClientesService");

		service.executeProcesarClasificacionLove(params);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map params = new HashMap();
		params.clear();
		params.put("codigoISO", usuario.getIdioma().getCodigoISO());
		params.put("codigoPais", pais.getCodigo());

		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		// Carga de los combos Marca
		siccMarcaList = interfazSiCCService.getMarcas();

		AjaxService ajaxService = (AjaxService) getBean("ajaxService");

		// Asignamos al codigo del periodo el valor por defecto
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		List periodos = interfazSiCCService.getPeriodosDefaultByPMC(criteria);

		if (periodos != null && periodos.size() > 0) {
			ProcesoMAEClasificacionLoveClientesForm actionForm = (ProcesoMAEClasificacionLoveClientesForm) this.formProceso;
			Base base = (Base) periodos.get(0);
			actionForm.setCodigoPeriodo(base.getCodigo());
			actionForm.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
			actionForm.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		}

		siccPeriodoList = ajaxService.getPeriodosDefaultByPMC(pais.getCodigo(),
				Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT);
		
	}

	/**
	 * @return the siccPeriodoList
	 */
	public LabelValue[] getSiccPeriodoList() {
		return siccPeriodoList;
	}

	/**
	 * @param siccPeriodoList
	 *            the siccPeriodoList to set
	 */
	public void setSiccPeriodoList(LabelValue[] siccPeriodoList) {
		this.siccPeriodoList = siccPeriodoList;
	}

	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList
	 *            the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}
}