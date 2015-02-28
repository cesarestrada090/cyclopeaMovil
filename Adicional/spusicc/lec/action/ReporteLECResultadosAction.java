package biz.belcorp.ssicc.web.spusicc.lec.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.lec.form.ReporteLECResultadosForm;


@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class ReporteLECResultadosAction extends BaseReporteAbstractAction 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4698880017395058272L;

	private List siccRegionList;
	
	
	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void setViewAtributes() throws Exception 
	{
//		ReporteLECResultadosForm f = (ReporteLECResultadosForm) this.formReporte;
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais", criteria); 
		
		log.debug("Todo Ok: Redireccionando");		
	}

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteLECResultadosForm reporteForm = new ReporteLECResultadosForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteLECResultadosXLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap");
		}
		
		ReporteLECResultadosForm reporteForm = (ReporteLECResultadosForm) this.formReporte;
		String codigoRegion = obtieneCondicion(reporteForm.getCodigoRegion(), "r.cod_regi", "'");
		params.put("region", codigoRegion);
		params.put("campana", reporteForm.getCodigoPeriodo());
		params.put("NroReporte", "");
		params.put("titulo", "");
		return params;
	}
}
