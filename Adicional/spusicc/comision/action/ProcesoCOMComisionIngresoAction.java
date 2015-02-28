package biz.belcorp.ssicc.web.spusicc.comision.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCalculoCalificacionTramoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.comision.form.ProcesoCOMComisionIngresoForm;

@SessionScoped
@ManagedBean
public class ProcesoCOMComisionIngresoAction extends BaseProcesoAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3705679360710904799L;
	private List siccMarcaList;
	private List siccCanalList;
	private List siccComisionList;
	private List comTramoEjecutivasList;
	private List comTipoCalculoList;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoCOMComisionIngresoForm form = new ProcesoCOMComisionIngresoForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		log.debug("Los parametros del Reporte en el executeProcess son: "
				+ params.toString());
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		reporteService.executeComisionIngreso(params);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ProcesoCOMCalculoCalificacionTramoService tramoService = (ProcesoCOMCalculoCalificacionTramoService) getBean("spusicc.procesoCOMCalculoCalificacionTramoService");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		this.siccMarcaList = service.getMarcas();

		this.siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma()
				.getCodigoISO());
		this.siccComisionList = service.getComision();
		this.comTramoEjecutivasList = tramoService.getTramos(pais.getCodigo());
		this.comTipoCalculoList = tramoService.getTipoCalculoList(pais
				.getCodigo());

		ProcesoCOMComisionIngresoForm f = (ProcesoCOMComisionIngresoForm) this.formProceso;
		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(
				pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);

		f.setCodigoPeriodo(codigoPeriodo);
		log.debug("Todo Ok: Redireccionando");

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

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList
	 *            the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * @return the siccComisionList
	 */
	public List getSiccComisionList() {
		return siccComisionList;
	}

	/**
	 * @param siccComisionList
	 *            the siccComisionList to set
	 */
	public void setSiccComisionList(List siccComisionList) {
		this.siccComisionList = siccComisionList;
	}

	/**
	 * @return the comTramoEjecutivasList
	 */
	public List getComTramoEjecutivasList() {
		return comTramoEjecutivasList;
	}

	/**
	 * @param comTramoEjecutivasList
	 *            the comTramoEjecutivasList to set
	 */
	public void setComTramoEjecutivasList(List comTramoEjecutivasList) {
		this.comTramoEjecutivasList = comTramoEjecutivasList;
	}

	/**
	 * @return the comTipoCalculoList
	 */
	public List getComTipoCalculoList() {
		return comTipoCalculoList;
	}

	/**
	 * @param comTipoCalculoList
	 *            the comTipoCalculoList to set
	 */
	public void setComTipoCalculoList(List comTipoCalculoList) {
		this.comTipoCalculoList = comTipoCalculoList;
	}

}
