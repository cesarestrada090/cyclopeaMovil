package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.collections.MapUtils;

import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.sto.form.ReporteSTOSolucionesCentroAcopioManualForm;


/**
 * The Class ReporteSACFacturacionDetalleAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 28/08/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSTOSolucionesCentroAcopioManualAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;

	private List listaParametros;
	private List stoCentroAcopioList; 
	
	
	public List getStoCentroAcopioList() {
		return stoCentroAcopioList;
	}

	public void setStoCentroAcopioList(List stoCentroAcopioList) {
		this.stoCentroAcopioList = stoCentroAcopioList;
	}

	public List getListaParametros() {
		return listaParametros;
	}
	
	public void setListaParametros(List listaParametros) {
		this.listaParametros = listaParametros;
	}

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSTOSolucionesCentroAcopioManualForm form = new ReporteSTOSolucionesCentroAcopioManualForm();
		return form;
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteMaestroHorizontal";
		
	}
	
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteSTOCentroAcopioManualPDF";
		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		ReporteSTOSolucionesCentroAcopioManualForm form = (ReporteSTOSolucionesCentroAcopioManualForm) this.formReporte;
		this.mostrarReporteMailXLS = true;
		this.mostrarReportePDF = false;
		this.mostrarListaReporteLog = true;
		form.setEnvioEmail(true);
		form.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		
		ProcesoSTOEjecucionValidacionesService service = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		List l = service.getCentrosDeAcopio(null);
		List acopiosList = new ArrayList();
		Map map = new HashMap();
		for (int i = 0; i < l.size(); i++) {
			map = (Map)l.get(i);
			Base b = new Base();
			b.setCodigo(map.get("codCentroAcopio").toString());
			b.setDescripcion(map.get("nomCentroAcopio").toString());
			acopiosList.add(b);
		}
		stoCentroAcopioList=acopiosList;
		
	}


	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteSTOSolucionesCentroAcopioManualForm f = (ReporteSTOSolucionesCentroAcopioManualForm) this.formReporte;		
	
		params.put("NroReporte", "");
		params.put("formatoExportacion","VPDF");
		f.setFormatoExportacion("VPDF");		
        
		params.put("titulo", getResourceMessage("reporteSTOCentroAcopioManual.titulo"));
		
		if (!this.isVisualizarReporte()) {			
			Map map = new HashMap();
			map = (Map)listaParametros.get(this.getNroReporteProcesando() - 1);
			params.put("codigoCentroAcopio" , f.getCentroAcopio());
			params.put("emailCentroAcopio"  , map.get("emailCentroAcopio").toString());			
		}
		ProcesoSTOEjecucionValidacionesService service = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		List lista = service.getCentrosDeAcopio(params);
		
		if(lista != null && lista.size() > 0)
		{
			Map ca = (Map)lista.get(0);			
			params.put("codigoCiaTransporte"  , MapUtils.getString(ca, "codCiaTransporte", ""));
			params.put("codigoCentroAcopio"  , MapUtils.getString(ca, "codCentroAcopio", ""));
			params.put("nomCentroAcopio"  , MapUtils.getString(ca, "nomCentroAcopio", ""));
			params.put("ciaTransporte"  , MapUtils.getString(ca, "ciaTransporte", ""));
		}	
		return params;
	}

	@Override
	protected int getNroReportesAGenerar() {
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		ReporteSTOSolucionesCentroAcopioManualForm f = (ReporteSTOSolucionesCentroAcopioManualForm) formReporte;
		Map criteria = new HashMap();
		criteria.put("codigoCentroAcopio", f.getCentroAcopio());
		listaParametros = procesoSTOEjecucionValidacionesService.getCentrosDeAcopio(criteria);		
		return listaParametros.size();
	}
	

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteSTOCentroAcopioFacturadoService";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanMailService()
	 */
	protected String devuelveBeanMailService(){
		return "sto.mailReporteSTOBoletasEscanearService";
	}	

		

	protected boolean continueExecuteReporte(ReporteParams reporteParams) {
		if(log.isDebugEnabled()){
			log.debug("continueExecuteReporte");
		}
		return true;
	}




	
	
	

}
