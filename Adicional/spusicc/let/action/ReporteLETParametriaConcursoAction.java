package biz.belcorp.ssicc.web.spusicc.let.action;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.core.io.ClassPathResource;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.spusicc.let.MantenimientoLETPremioCampaniaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.let.form.ReporteLETParametriaConcursoForm;


/**
 * The Class ReporteLETParametriaConcursoAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 07/11/2014
 */
@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class ReporteLETParametriaConcursoAction extends BaseReporteAbstractAction {
	
	private static final long serialVersionUID = -6167773274025905241L;
	private String formatoReporte;
	private List siccConcursoList;
	private String tipoReporte;
	
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteLETParametriaConcursoForm form = new ReporteLETParametriaConcursoForm();
		return form;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteLETParametriaConcursoAction.setViewAtributes' method");
		}		
		this.mostrarReporteOCSV = false;
		this.mostrarReporteXLSX = false;
		this.mostrarReporteCSV = false;
		this.mostrarReportePDF = true;
		this.mostrarReporteXLS = false;
		this.mostrarReporteOJXLSX = false;
		this.mostrarReporteOOXLSX = false;			
		MantenimientoLETPremioCampaniaService mantenimientoLETPremioCampaniaService = (MantenimientoLETPremioCampaniaService)getBean("spusicc.mantenimientoLETPremioCampaniaService");
		ReporteLETParametriaConcursoForm f = new ReporteLETParametriaConcursoForm();
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		criteriaOperacion.put("codigoConcurso", f.getCodigoConcurso());
		siccConcursoList = mantenimientoLETPremioCampaniaService.getNumeroConcursoList(criteriaOperacion);
	}

	@Override
	protected String devuelveNombreReporte() throws Exception { 		
		return "reporteMaestroVertical"; 
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteLETParametriaConcursoPDF";
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteLETParametriaConcursoForm f = (ReporteLETParametriaConcursoForm) this.formReporte;		
		formatoReporte = f.getFormatoExportacion();		
		params.put("codigoPais" ,f.getCodigoPais());
		params.put("codigoConcurso", f.getCodigoConcurso());
		params.put("formatoExportacion", "VPDF");
		ClassPathResource resource = new ClassPathResource("subReporteLETParametriaConcursoPC" + JASPER_EXTENSION);
		ClassPathResource resource1 = new ClassPathResource("subReporteLETParametriaConcursoNC" + JASPER_EXTENSION);
		ClassPathResource resource2 = new ClassPathResource("subReporteLETParametriaConcursoRP" + JASPER_EXTENSION);		
		
		
		params.put("SUBREPORT_DIR1", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() )));
		params.put("SUBREPORT_DIR2", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource1.getPath() )));
		params.put("SUBREPORT_DIR3", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource2.getPath() )));
			
		params.put("NroReporte", "");
		params.put("titulo", this.getReportResourceMessage("reporteLETParametriaConcursoForm.title"));
		log.debug("Imprimiendo parámetros");
		log.debug(params);
		log.debug("Fin parámetros");
		return params;
	}

	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/**
	 * @return the siccConcursoList
	 */
	public List getSiccConcursoList() {
		return siccConcursoList;
	}

	/**
	 * @param siccConcursoList the siccConcursoList to set
	 */
	public void setSiccConcursoList(List siccConcursoList) {
		this.siccConcursoList = siccConcursoList;
	}

	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}
	
}