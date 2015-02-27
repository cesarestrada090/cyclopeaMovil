package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.sto.form.ReporteSTOSolucionesCentroAcopioAutomaticoForm;


/**
 * The Class ReporteSACFacturacionDetalleAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 28/08/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSTOSolucionesCentroAcopioAutomaticoAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;

	private List listaParametros;
	
	
	public List getListaParametros() {
		return listaParametros;
	}
	
	public void setListaParametros(List listaParametros) {
		this.listaParametros = listaParametros;
	}

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSTOSolucionesCentroAcopioAutomaticoForm form = new ReporteSTOSolucionesCentroAcopioAutomaticoForm();
		return form;
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteMaestroHorizontal";
		
	}
	
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteSTOCentroAcopioAutoPDF";
		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		ReporteSTOSolucionesCentroAcopioAutomaticoForm form = (ReporteSTOSolucionesCentroAcopioAutomaticoForm) this.formReporte;
		this.mostrarReporteMailXLS = true;
		this.mostrarReportePDF = false;
		this.mostrarListaReporteLog = true;
		form.setEnvioEmail(true);
		form.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
	}


	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteSTOSolucionesCentroAcopioAutomaticoForm f = (ReporteSTOSolucionesCentroAcopioAutomaticoForm) this.formReporte;		
	
		params.put("NroReporte", "");
		params.put("formatoExportacion","VPDF");			
		f.setFormatoExportacion("VPDF");		
		params.put("titulo", getResourceMessage("reporteSTOCentroAcopioAuto.titulo"));
		Map criteria = new HashMap();
		criteria.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry().getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
        
		
		if (!this.isVisualizarReporte()) {			
			Map map = new HashMap();
			map = (Map)listaParametros.get(this.getNroReporteProcesando() - 1);
			log.debug("map "+map);
			params.put("codigoCiaTransporte" , map.get("codCiaTransporte"));			
			params.put("codigoCentroAcopio"  , map.get("codCentroAcopio"));
			params.put("ciaTransporte"    	 , map.get("ciaTransporte"));
			params.put("nomCentroAcopio"  	 , map.get("nomCentroAcopio"));
			params.put("emailCentroAcopio"	 , map.get("emailCentroAcopio"));
		}
		f.setEnvioEmail(true);	
		return params;
	}

	@Override
	protected int getNroReportesAGenerar() {
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		listaParametros = procesoSTOEjecucionValidacionesService.getCentrosDeAcopioSolucionesCentroAcopioAutomatico();		
		return listaParametros.size();
	}
	

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteSTOSolucionesCentroAcopioAutomaticoService";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanMailService()
	 */
	protected String devuelveBeanMailService(){
		return "sto.mailReporteSTOSolucionesCentroAcopioAutomaticoService";
	}	
		

	protected boolean continueExecuteReporte(ReporteParams reporteParams) {
		if(log.isDebugEnabled()){
			log.debug("continueExecuteReporte");
		}
		return true;
	}




	
	
	

}
