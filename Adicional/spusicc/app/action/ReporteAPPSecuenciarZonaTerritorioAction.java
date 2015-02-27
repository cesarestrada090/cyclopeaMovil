package biz.belcorp.ssicc.web.spusicc.app.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.app.form.ReporteAPPSecuenciarZonaTerritorioForm;


@ManagedBean
@SessionScoped
public class ReporteAPPSecuenciarZonaTerritorioAction extends BaseReporteAbstractAction{

	private static final long serialVersionUID = 7867511685821610886L;
	
	private String nameSubReporte;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteAPPSecuenciarZonaTerritorioForm reporteForm = new ReporteAPPSecuenciarZonaTerritorioForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {		
		return nameSubReporte;
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {		
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();	
		ReporteAPPSecuenciarZonaTerritorioForm f = (ReporteAPPSecuenciarZonaTerritorioForm) this.formReporte;	
				
		params.put("NroReporte", "");
		params.put("formatoExportacion","XLS");			
		f.setFormatoExportacion("XLS");		

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
         
        //String condicionRegion = request.getAttribute("condicionRegion").toString();
        
        //getNroReportesAGenerar(request,form);
        
		if (!this.isVisualizarReporte()) {			
			//Map map = new HashMap();
			//map = (Map)listaParametros.get(this.getNroReporteProcesando() - 1);	
			
			//params.put("email", map.get("email").toString());
			//params.put("condicionRegion", condicionRegion);
		}		
		return params;
	}
	
	@Override
	protected void beforeGrabarReporte() {
		super.beforeGrabarReporte();
		nameSubReporte="reporteAPPSecuenciar";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#getMailService()
	 */
	public String getMailService() {
		return "app.mailReporteAPPSecuenciarZonaTerritorioService";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#getNroReportesAGenerar()
	 */
	protected int getNroReportesAGenerar() {		
		return 1;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#getNombreArchivoReporte(biz.belcorp.ssicc.scsicc.service.framework.beans.ReporteParams)
	 */
	/*
	protected String getNombreArchivoReporte() {
		String nombreArchivoReporte;		

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
		//nombreArchivoReporte = this.getPrefijoArchivo() +  "_" + sdf.format(new Date(System.currentTimeMillis()));
		return nombreArchivoReporte;
	}
*/
	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
