package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.spusicc.cuentacorriente.form.ReporteCCCDeudorasConMasUnaCampanhaForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;



@ManagedBean
@SessionScoped
public class ReporteCCCDeudorasConMasUnaCampanhaAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = 4877199747943152031L;

	private String formatoReporte;
	private List siccsociedadList;
	

	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCDeudorasConMasUnaCampanhaForm reporteForm = new ReporteCCCDeudorasConMasUnaCampanhaForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		ReporteCCCDeudorasConMasUnaCampanhaForm f = (ReporteCCCDeudorasConMasUnaCampanhaForm) this.formReporte;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		
		
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codpais = pais.getCodigo();
		siccsociedadList = service.getSociedadesByCodigoPais(codpais);		
		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(codpais, Constants.CODIGO_CANAL_DEFAULT);
		
		f.setCodigoPeriodo(codigoPeriodo);
		
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formReporte.getFormatoExportacion()))
			return "reporteCCCSaldosCampaY5AnterDXLS";
		else
			return " ";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return " ";
	}

	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteCCCDeudorasConMasUnaCampanhaService";
	}

	@Override
	protected Map prepareParameterMap(Map params) {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParameterMap' method");
		}
		
		
		ReporteCCCDeudorasConMasUnaCampanhaForm f = (ReporteCCCDeudorasConMasUnaCampanhaForm) this.formReporte;
		formatoReporte = f.getFormatoExportacion();

	
		params.put("titulo",getResourceMessage("reportePERVentaForm.title"));		
		return params;
	}
	

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public List getSiccsociedadList() {
		return siccsociedadList;
	}

	public void setSiccsociedadList(List siccsociedadList) {
		this.siccsociedadList = siccsociedadList;
	}


}
