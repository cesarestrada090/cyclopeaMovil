package biz.belcorp.ssicc.web.spusicc.lec.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.spusicc.lec.MantenimientoLECProgramaCorporativoService;
import biz.belcorp.ssicc.service.spusicc.pej.MantenimientoPEJProgramaEjecutivasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.lec.form.ReporteLECAvanceGestionForm;

@ManagedBean
@SessionScoped
public class ReporteLECAvanceGestionAction extends BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8283811688652696544L;

	private String formatoReporte;
	private List siccRegionList;
	private List siccZonaList;
	private List siccSeccionList;

	private String[] listaTotal;
	private String[] listaTotalSeccion;
	private String nameSubReporte;

	public String[] getListaTotalSeccion() {
		return listaTotalSeccion;
	}

	public void setListaTotalSeccion(String[] listaTotalSeccion) {
		this.listaTotalSeccion = listaTotalSeccion;
	}

	public String getNameSubReporte() {
		return nameSubReporte;
	}

	public void setNameSubReporte(String nameSubReporte) {
		this.nameSubReporte = nameSubReporte;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String[] getListaTotal() {
		return listaTotal;
	}

	public void setListaTotal(String[] listaTotal) {
		this.listaTotal = listaTotal;
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public List getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(List siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public List getSiccSeccionList() {
		return siccSeccionList;
	}

	public void setSiccSeccionList(List siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
	}

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteLECAvanceGestionForm reporteForm = new ReporteLECAvanceGestionForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		String reporte = "";

		if ("XLS".equals(formatoReporte)) {
			// if (tipoReporte.equals("0001")){
			reporte = "";
			// }
		} else
			reporte = "reporteMaestroHorizontalLECAvanceGestion";

		return reporte;
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		String reporte = "";

		if ("VPDF".equals(formatoReporte)) {
			// if (tipoReporte.equals("0001")){
			reporte = "reporteLECAvanceGestionPDF";
			// }
		}

		return reporte;

	}

	protected int getNroReportesAGenerar() {
		ReporteLECAvanceGestionForm f = (ReporteLECAvanceGestionForm) formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		AjaxService ajaxService = (AjaxService) this.getBean("ajaxService");
		List lista = new ArrayList();

		String codigoRegion = f.getCodigoRegion();
		String codigoZona = f.getCodigoZona();
		String codigoSeccion = f.getCodigoSeccion();

		// if ((tipoReporte.equals("0001")) || (tipoReporte.equals("0002"))) {

		if (StringUtils.equals(codigoZona, "Todos")
				|| StringUtils.isBlank(codigoZona)) {
			String codigoRegionAux = codigoRegion;
			if (StringUtils.equals(codigoRegion, "Todos")
					|| StringUtils.isBlank(codigoRegion)) {
				codigoRegionAux = "";
			}

			LabelValue[] result = ajaxService.getZonasRegionPEJTodos(
					codigoRegionAux, f.getCampanyaProceso());
			if (result != null) {
				for (int i = 0; i < result.length; i++) {
					LabelValue zonas = result[i];
					lista.add(zonas.getValue());
				}
			}
		} else {
			lista.add(codigoZona);
		}

		if (StringUtils.equals(codigoRegion, "Todos")
				|| StringUtils.isBlank(codigoRegion)) {
			List listaRegiones = reporteService.getListaGenerico(
					"getRegionesPEJ", null);

			for (int i = 0; i < listaRegiones.size(); i++) {
				Base base = (Base) listaRegiones.get(i);
				lista.add("codigoRegion__" + base.getCodigo());
			}
		} else
			lista.add("codigoRegion__" + codigoRegion);

		int tamanno = lista.size();
		this.listaTotal = new String[tamanno];
		for (int i = 0; i < tamanno; i++) {
			this.listaTotal[i] = (String) lista.get(i);
		}
		return this.listaTotal.length;
		// }

	}

	protected String getValorFiltroGrabarReporte(ReporteParams reporteParams) {
		String filtro = new String();
		// if ((tipoReporte.equals("0001")) || (tipoReporte.equals("0002"))) {
		if (this.listaTotal[this.getNroReporteProcesando() - 1]
				.indexOf("codigoRegion__") < 0) {
			filtro = "Zona: ";
			return filtro + this.listaTotal[this.getNroReporteProcesando() - 1];
		} else {
			filtro = "Region: ";
			return filtro
					+ this.listaTotal[this.getNroReporteProcesando() - 1]
							.substring(14);
		}
		// }
	}

	protected boolean continueExecuteReporte(ReporteParams reporteParams) {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteLECAvanceGestionForm reporteForm = (ReporteLECAvanceGestionForm) formReporte;
		String codigoPais = mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo();

		params.put("codigoPrograma", reporteForm.getCodigoPrograma());

		formatoReporte = reporteForm.getFormatoExportacion();

		String regionList[] = { reporteForm.getCodigoRegion() };
		String zonaList[] = { reporteForm.getCodigoZona() };
		String seccionList[] = { reporteForm.getCodigoSeccion() };

		String condicionRegion = this.obtieneCondicion(regionList,
				"zorg.cod_regi", "'");
		String condicionZonas = this.obtieneCondicion(zonaList,
				"zzon.cod_zona", "'");
		String condicionSeccion = this.obtieneCondicion(seccionList,
				"zscc.cod_secc", "'");
		String condicion = condicionRegion + condicionZonas + condicionSeccion;
		params.put("condicion", condicion);

		// if (tipoReporte.equals("0001")){
		params.put("titulo",
				this.getResourceMessage("reporteLECAvanceGestionForm.titulo"));

		params.put("condicionZonaCorreo", " ");
		params.put("condicionSeccionCorreo", " ");

		if (!this.isVisualizarReporte()) {

			if (this.listaTotal[this.getNroReporteProcesando() - 1]
					.indexOf("codigoRegion__") < 0) {
				params.put("codigoRegion", null);
				params.put("codigoZona",
						this.listaTotal[this.getNroReporteProcesando() - 1]);
				params.put("condicionZonaCorreo", " AND zzon.cod_zona='"
						+ this.listaTotal[this.getNroReporteProcesando() - 1]
						+ "' ");
			} else {
				params.put("codigoRegion", this.listaTotal[this
						.getNroReporteProcesando() - 1].substring(14));
				params.put(
						"condicionZonaCorreo",
						" AND zorg.cod_regi='"
								+ this.listaTotal[this
										.getNroReporteProcesando() - 1]
										.substring(14) + "' ");
			}
		}

		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReporteXLS = true;
		this.mostrarReporteMailXLS = true;
		this.mostrarListaReporteLog = true;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoPEJProgramaEjecutivasService service = (MantenimientoPEJProgramaEjecutivasService) getBean("spusicc.mantenimientoPEJProgramaEjecutivasService");
		ReporteLECAvanceGestionForm f = (ReporteLECAvanceGestionForm) formReporte;
		MantenimientoLECProgramaCorporativoService lecService = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");
		Map result = service.getPeriodoDefault();

		String codigoPeriodo = (String) result.get("codigoPeriodo");
		String fechaProceso = (String) result.get("fechaProceso");
		f.setCampanyaProceso(codigoPeriodo);
		f.setFechaFacturacion(fechaProceso);

		Map map1 = lecService.getEncontrarProgramaLecCorporativo(f
				.getCampanyaProceso());

		f.setCodigoPrograma(map1.get("codigoPrograma") == null ? "" : map1.get(
				"codigoPrograma").toString());
		f.setDescPrograma(map1.get("descPrograma") == null ? "" : map1.get(
				"descPrograma").toString());

		Map criteria = new HashMap();
		criteria.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());
		siccRegionList = reporteService.getListaGenerico("getRegionesPEJ",
				criteria);
		siccZonaList = new ArrayList();
		siccSeccionList = new ArrayList();

	}

	protected String devuelveBeanMailService() {
		return "lec.mailReporteLECAvanceGestion";
	}

	protected void beforeGrabarReporte() {

		ReporteLECAvanceGestionForm reporteForm = (ReporteLECAvanceGestionForm) formReporte;

		this.formatoReporte = "VPDF";

		this.nameSubReporte = "reporteLECAvanceGestionPDF";

	}

}
