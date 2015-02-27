package biz.belcorp.ssicc.web.spusicc.let.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.let.MantenimientoLETPremioCampaniaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.let.form.ReporteLETResultadosLideresConcursoForm;

/**
 * The Class ReporteLETParametriaConcursoAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 10/11/2014
 */
@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class ReporteLETResultadosLideresConcursoAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 2796062053364311956L;
	private String formatoReporte;
	private List siccMarcaList;
	private List siccCanalList;
	private LabelValue[] siccRegionList;
	private LabelValue[] siccZonaList;
	private List siccConcursoList;
	
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteLETResultadosLideresConcursoForm form = new ReporteLETResultadosLideresConcursoForm();
		return form;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteLETResultadosLideresConcursoAction.setViewAtributes' method");
		}		
		this.mostrarReporteOCSV = false;
		this.mostrarReporteXLSX = false;
		this.mostrarReporteCSV = false;
		this.mostrarReportePDF = true;
		this.mostrarReporteXLS = true;
		this.mostrarReporteOJXLSX = false;
		this.mostrarReporteOOXLSX = false;	
		
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		MantenimientoLETPremioCampaniaService mantenimientoLETPremioCampaniaService = (MantenimientoLETPremioCampaniaService)getBean("spusicc.mantenimientoLETPremioCampaniaService");

		ReporteLETResultadosLideresConcursoForm f = (ReporteLETResultadosLideresConcursoForm) this.formReporte;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		
		this.siccMarcaList = interfazSiCCService.getMarcas();		
		this.siccCanalList = interfazSiCCService.getCanalesByCodigoISO(this.mPantallaPrincipalBean.getCurrentIdioma().getCodigoISO());
		this.siccRegionList = ajaxService.getRegionesByPaisLet(pais.getCodigo()); 
		this.siccConcursoList = mantenimientoLETPremioCampaniaService.getNumeroConcursoList(criteria);
		
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);		
		f.setCodigoPais(pais.getCodigo());
	}
	
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteLETResultadosLideresConcursoPDF";
	}

	@Override
	protected String devuelveNombreReporte() throws Exception { 
		this.formatoReporte = ((ReporteLETResultadosLideresConcursoForm)this.formReporte).getFormatoExportacion();  
		if ("VXLS".equals(formatoReporte) ||("XLS".equals(formatoReporte))) {
			return "reporteLETResultadosLideresConcursoXLS";
		} else {
			return "reporteMaestroHorizontalLETResultadoLideres";
		}
	}
	
	@Override
	protected String devuelveBeanReporteService(){
		return "spusicc.reporteLETResultadosLideresConcursoService";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		log.debug("ReporteLETResultadosLideresConcursoAction ::::: prepareParameterMap");
		
		ReporteLETResultadosLideresConcursoForm f = (ReporteLETResultadosLideresConcursoForm) this.formReporte;
		formatoReporte = f.getFormatoExportacion();
		
		params.put("codigoPais", f.getCodigoPais());
		String codigoZona = (String) params.get("codigoZona");
		String codigoRegion = (String) params.get("codigoRegion");
		
		String condicionRegion = "";		
		if(StringUtils.isNotBlank(codigoRegion)) {
			condicionRegion = "AND a.COD_REGI = '"+codigoRegion+"' ";
		} else {
			params.put("codigoRegion", null);
		}
		
		String condicionZonas = "";				
		if ("Todos".equals(codigoZona) || StringUtils.isEmpty(codigoZona)) {
			codigoZona = null;
			params.put("codigoZona", codigoZona);
		} else {
			condicionZonas = obtieneCondicion(f.getCodigoZona(),"a.COD_ZONA", "'");
		}
		
		String condicion = condicionRegion + condicionZonas;		
		params.put("condicion", condicion);
		params.put("titulo", this.getReportResourceMessage("reporteLETResultadosLideresConcursoForm.titulo"));
		log.debug("Imprimiendo parámetros");
		log.debug(params);
		log.debug("Fin parámetros");
		return params;
	}
	
	/**
	 * Metodo para obtener Lista de Zonas
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		
		if (!val.equals(null)) {
			String valor = (String) val.getNewValue();
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			this.setSiccZonaList(ajaxService
					.getZonasByPaisRegionLet(this
							.getmPantallaPrincipalBean().getCurrentCountry()
							.getCodigo(), valor));
		}else
			setSiccZonaList(null);
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
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList the siccMarcaList to set
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
	 * @param siccCanalList the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}
	
	/**
	 * @return the siccRegionList
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
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
	
}