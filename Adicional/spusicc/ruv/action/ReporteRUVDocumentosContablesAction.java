package biz.belcorp.ssicc.web.spusicc.ruv.action;

import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.ruv.MantenimientoRUVDocumentosContablesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.ruv.form.ReporteRUVDocumentosContablesForm;

@ManagedBean
@SessionScoped
public class ReporteRUVDocumentosContablesAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = -8597144041761585850L;

	private String formatoReporte;
	private List ruvTipoDocumentoContableList;
	private List siccCanalList;
	private List siccAccesoList;
	private LabelValue[] ruvSubaccesoList;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRUVDocumentosContablesForm reporteForm = new ReporteRUVDocumentosContablesForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		log.debug("ReporteRUVDocumentosContablesAction - setViewAttributes");

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ReporteRUVDocumentosContablesForm f = (ReporteRUVDocumentosContablesForm) this.formReporte;
		f.setCodigoPais(pais.getCodigo());
		MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService) getBean("spusicc.mantenimientoRUVDocumentosContablesService");
		InterfazSiCCService siccService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		this.ruvTipoDocumentoContableList = service.getTipoDocumentoContable();
		this.siccCanalList = siccService.getCanalesByCodigoISO(usuario
				.getIdioma().getCodigoISO());
		this.siccAccesoList=getAccesoList(Constants.CODIGO_CANAL_DEFAULT);
		this.ruvSubaccesoList = ajax
				.getSubaccesoByAcceso(Constants.CODIGO_ACCESO_DEFAULT);

		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoAcceso(Constants.CODIGO_ACCESO_DEFAULT);
		f.setCodigoSubacceso(Constants.CODIGO_SUBACCESO_000);

	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteRUVDocumentosContables2XLS";
		else
			return "reporteMaestroHorizontal";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		log.debug("ReporteRUVDocumentosContablesAction - prepareParameterMap");

		ReporteRUVDocumentosContablesForm f = (ReporteRUVDocumentosContablesForm) this.formReporte;

		formatoReporte = f.getFormatoExportacion();
		f.setFechaDesde(DateUtil.convertDateToString(f.getFechaDesdeD()));
		f.setFechaHasta(DateUtil.convertDateToString(f.getFechaHastaD()));

		params.put("codigoSubacceso", "888");
		params.put("fechaDesde", f.getFechaDesde());
		params.put("fechaHasta", f.getFechaHasta());
		params.put("serie", f.getSerie());
		params.put("codigoPais", f.getCodigoPais());
		params.put("oidTipoDocumento", f.getCodigoTipoDocumentoContable());

		return params;

	}



//	public String setValidarReporte() {
//		ReporteRUVDocumentosContablesForm f = (ReporteRUVDocumentosContablesForm) this.formReporte;
//		if (f.getFechaHastaD().compareTo(f.getFechaDesdeD()) < 0) {
//			String mensaje = this.getResourceMessage("errors.compare.dates");
//			return mensaje;
//		}
//		return null;
//	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public List getRuvTipoDocumentoContableList() {
		return ruvTipoDocumentoContableList;
	}

	public void setRuvTipoDocumentoContableList(
			List ruvTipoDocumentoContableList) {
		this.ruvTipoDocumentoContableList = ruvTipoDocumentoContableList;
	}

	public LabelValue[] getRuvSubaccesoList() {
		return ruvSubaccesoList;
	}

	public void setRuvSubaccesoList(LabelValue[] ruvSubaccesoList) {
		this.ruvSubaccesoList = ruvSubaccesoList;
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
	 * @return the siccAccesoList
	 */
	public List getSiccAccesoList() {
		return siccAccesoList;
	}

	/**
	 * @param siccAccesoList the siccAccesoList to set
	 */
	public void setSiccAccesoList(List siccAccesoList) {
		this.siccAccesoList = siccAccesoList;
	}
	
	

	

}
