package biz.belcorp.ssicc.web.spusicc.lec.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.lec.MantenimientoLECProgramaCorporativoService;
import biz.belcorp.ssicc.service.spusicc.pej.MantenimientoPEJProgramaEjecutivasService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBloqueoControlService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.lec.form.ReporteLECIngresosForm;

@ManagedBean
@SessionScoped
public class ReporteLECIngresosAction extends BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8283811688652696544L;

	private String formatoReporte;
	private List siccRegionList;
	private List lecGrupoRegionList;
	private LabelValue[] lecRegionList;
	private String indTipoGrupoRegion1;

	public String getIndTipoGrupoRegion1() {
		return indTipoGrupoRegion1;
	}

	public void setIndTipoGrupoRegion1(String indTipoGrupoRegion1) {
		this.indTipoGrupoRegion1 = indTipoGrupoRegion1;
	}

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteLECIngresosForm reporteForm = new ReporteLECIngresosForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		String reporte = "";

		if ("XLS".equals(formatoReporte)) { // XLS
			reporte = "reporteLECIngresosXLS";
		} else {
			reporte = "reporteMaestroHorizontalLECIngresos"; // PDF
		}
		return reporte;
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		String reporte = "reporteLECIngresosPDF";

		if ("XLS".equals(formatoReporte)) {
			reporte = "";
		}
		return reporte;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteLECIngresosForm reporteForm = (ReporteLECIngresosForm) this.formReporte;
		
		formatoReporte = reporteForm.getFormatoExportacion();
		
		ClassPathResource resource = new ClassPathResource("subReporteLECIngresos" + JASPER_EXTENSION);	
		params.put("SUBREPORT_DIR1", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() )));
			
		String regionList[] = null;
		String condicionRegion = "";
		String condicionRegion1 = "";
		if (StringUtils.isNotBlank(reporteForm.getGrupoRegion())) {
			regionList = reporteForm.getRegion();
			if (ArrayUtils.isEmpty(regionList)) {
				regionList = (String[]) ArrayUtils.add(regionList,reporteForm.getGrupoRegion());
			}
			
		}else{
			regionList = (String[]) ArrayUtils.add(regionList,reporteForm.getCodigoRegion());
		}

 		if (!regionList[0].equals(Constants.OPCION_TODOS)) {
 			condicionRegion = this.obtieneCondicion(regionList, "dr.cod_regi", "'");
 			condicionRegion1 = this.obtieneCondicion(regionList, "dtr.cod_regi", "'");
		}

		params.put("condicionRegion", condicionRegion);
		params.put("condicionRegion1", condicionRegion1);
		params.put("condicionTramos", reporteForm.getTramo());
		
		params.put("NroReporte", "");
		params.put("campanyaProceso", reporteForm.getCampanyaProceso());
		params.put("titulo",this.getResourceMessage("reporteLECIngresosForm.titulo"));
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReporteXLS = true;

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoPEJProgramaEjecutivasService service = (MantenimientoPEJProgramaEjecutivasService) getBean("spusicc.mantenimientoPEJProgramaEjecutivasService");
		MantenimientoLECProgramaCorporativoService lecService = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");
		ReporteLECIngresosForm f = (ReporteLECIngresosForm) this.formReporte;
		Map result = service.getPeriodoDefault();
		String codigoPeriodo = (String) result.get("codigoPeriodo");
		f.setCampanyaProceso(codigoPeriodo);

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoPais = pais.getCodigo();

		Map map = new HashMap();
		map.put("codigoPais", codigoPais);

		List list = lecService.getTipoGrupoRegion(map);

		map.put("codigoTipoGroup", ((Base) list.get(0)).getCodigo());
		String indTipoGrupoRegion = getIndicadorGrupoRegion();

		this.siccRegionList = reporteService.getListaGenerico("getRegionesPEJ",
				map);
		this.lecGrupoRegionList = list;
		indTipoGrupoRegion1 = indTipoGrupoRegion != null ? indTipoGrupoRegion
				: Constants.NRO_CERO;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String getIndicadorGrupoRegion() {
		Map criteriaParam = new HashMap();
		criteriaParam.put("codigoPais", this.mPantallaPrincipalBean
				.getCurrentCountry().getCodigo());
		criteriaParam.put("codigoSistema", "LET");
		criteriaParam.put("nombreParametro", "indTipoGrupoRegion");
		return ((MantenimientoSTOBloqueoControlService) getBean("spusicc.mantenimientoSTOBloqueoControlService"))
				.getParametroGenericoSistema(criteriaParam);

	}

	public void loadRegiones(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadRegiones");
		}

		String valor = (String) val.getNewValue();
		if (valor.trim().length() > 0) {
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			setLecRegionList(ajaxService.getRegionByTipoGrupoRegion(this
					.getmPantallaPrincipalBean().getCurrentCountry()
					.getCodigo(), valor));
		} else {
			setLecRegionList(null);
		}
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public List getLecGrupoRegionList() {
		return lecGrupoRegionList;
	}

	public void setLecGrupoRegionList(List lecGrupoRegionList) {
		this.lecGrupoRegionList = lecGrupoRegionList;
	}

	public LabelValue[] getLecRegionList() {
		return lecRegionList;
	}

	public void setLecRegionList(LabelValue[] lecRegionList) {
		this.lecRegionList = lecRegionList;
	}

}
