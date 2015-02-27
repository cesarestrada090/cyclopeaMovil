package biz.belcorp.ssicc.web.spusicc.sac.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSACAsistenciaCompartamosEsikaForm;


/**
 * The Class ReporteSACFacturacionDetalleAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 28/08/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSACAsistenciaCompartamosEsikaAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;

	private String[] listaTotal;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSACAsistenciaCompartamosEsikaForm form = new ReporteSACAsistenciaCompartamosEsikaForm();
		return form;
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteSACAsistenciaEsikaFormXLS";
		
	}
	
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		ReporteSACAsistenciaCompartamosEsikaForm form = (ReporteSACAsistenciaCompartamosEsikaForm) this.formReporte;
		this.mostrarReporteMailXLS = true;
		this.mostrarReportePDF = false;
		this.mostrarListaReporteLog = true;		
		form.setEnvioEmail(true);
		this.setVisualizarReporte(false);
		form.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
	}


	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap");
		}
		ReporteSACAsistenciaCompartamosEsikaForm form = (ReporteSACAsistenciaCompartamosEsikaForm) this.formReporte;
		int contNroReporteProcesando = 0;
		params.put("NroReporte", "");
		params.put("formatoExportacion","XLS");
		form.setFormatoExportacion("XLS");		
		if (!this.isVisualizarReporte()) {
			contNroReporteProcesando = this.getNroReporteProcesando();
			if(listaTotal != null){
				params.put("codigoZona", this.listaTotal[contNroReporteProcesando - 1 ]);
			}
		}
		//if(contNroReporteProcesando>1){
			form.setEnvioEmail(true);		
		//}
		return params;
	}

	@Override
	protected int getNroReportesAGenerar() {
		if(log.isDebugEnabled()){
			log.debug("getNroReportesAGenerar");
		}
		ReporteSACAsistenciaCompartamosEsikaForm form = (ReporteSACAsistenciaCompartamosEsikaForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");				
		List l = new ArrayList();
	    l = reporteService.getListaZonasReporteSACAsistencia();
		int tamanno = l.size();
		this.listaTotal = new String[tamanno];
		for (int i=0; i < tamanno; i++) {
			this.listaTotal[i] = (String)l.get(i); 
		}		
		return this.listaTotal.length;
	}
	
	
	
	@Override
	protected String getValorFiltroGrabarReporte(ReporteParams reporteParams) {
		if(log.isDebugEnabled()){
			log.debug("getValorFiltroGrabarReporte");
		}			
		String filtro = new String();
		filtro = "Zona: ";		
		return filtro + this.listaTotal[this.getNroReporteProcesando() - 1 ];
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteSACAsistenciaCompartamosEsikaServiceImpl";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanMailService()
	 */
	protected String devuelveBeanMailService(){
		return "sac.mailReporteSACAsistenciaCompartamosEsikaService";
	}
		
//	protected void beforeGrabarReporte(){
//		if(log.isDebugEnabled()){
//			log.debug("beforeGrabarReporte");
//		}
//		ReporteSACAsistenciaCompartamosEsikaForm form = (ReporteSACAsistenciaCompartamosEsikaForm) this.formReporte;
//		form.setNameSubReporte("reporteSACAsistenciaEsikaFormXLS");
//	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#continueExecuteReporte(biz.belcorp.ssicc.scsicc.service.framework.beans.ReporteParams)
	 */
	protected boolean continueExecuteReporte(ReporteParams reporteParams) {
		if(log.isDebugEnabled()){
			log.debug("continueExecuteReporte");
		}
		return true;
	}

	public String[] getListaTotal() {
		return listaTotal;
	}

	public void setListaTotal(String[] listaTotal) {
		this.listaTotal = listaTotal;
	}

	

}
