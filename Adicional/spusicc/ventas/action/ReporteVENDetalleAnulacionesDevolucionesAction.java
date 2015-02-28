package biz.belcorp.ssicc.web.spusicc.ventas.action;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.ventas.form.ReporteVENDetalleAnulacionesDevolucionesForm;


/**
 * The Class ReporteCCCBoletaDepositoAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 10/11/2014
 */
@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class ReporteVENDetalleAnulacionesDevolucionesAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = -6427088480220818789L;
	private String formatoReporte;
	private List siccAccesoList;
	private List siccSubAccesoList;
	
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteVENDetalleAnulacionesDevolucionesForm form = new ReporteVENDetalleAnulacionesDevolucionesForm();
		return form;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteVENDetalleAnulacionesDevolucionesAction.setViewAtributes' method");
		}
		this.mostrarReporteOCSV = false;
		this.mostrarReporteXLSX = false;
		this.mostrarReporteCSV = false;
		this.mostrarReportePDF = true;
		this.mostrarReporteXLS = true;
		this.mostrarReporteOJXLSX = false;
		this.mostrarReporteOOXLSX = false;
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		ReporteVENDetalleAnulacionesDevolucionesForm f = (ReporteVENDetalleAnulacionesDevolucionesForm) this.formReporte;
		f.setCodigoPais(pais.getCodigo());
		f.setFechaDesdeD(DateUtil.getToday().getTime());
		f.setFechaHastaD(DateUtil.getToday().getTime());
		InterfazSiCCService siccService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		
		// Setea el combo de Acceso
		Map params = new HashMap();
		params.put("canal", Constants.CODIGO_CANAL_DEFAULT);
		this.siccAccesoList = siccService.getAccesoByCanal(params);
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		this.siccSubAccesoList = Arrays.asList(ajax.getSubaccesoByAcceso(Constants.CODIGO_ACCESO_DEFAULT));
		f.setCodigoAcceso(Constants.CODIGO_ACCESO_DEFAULT);
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		this.formatoReporte = ((ReporteVENDetalleAnulacionesDevolucionesForm)this.formReporte).getFormatoExportacion(); 
		if ("XLS".equals(formatoReporte)) {
			return "reporteVENDetalleAnulacionesDevolucionesXLS";
		} else {
			return "reporteMaestroHorizontal";
		}
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteVENDetalleAnulacionesDevolucionesPDF";
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteVENDetalleAnulacionesDevolucionesForm form = (ReporteVENDetalleAnulacionesDevolucionesForm) this.formReporte;
		Date fecha1D = form.getFechaDesdeD();
		Date fecha2D = form.getFechaHastaD();
		if (fecha2D.compareTo(fecha1D) < 0) {
			String mensaje = this.getResourceMessage("reporteVENDetalleAnulacionesDevolucionesForm.errors.compare.fecha");
			return mensaje;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		log.debug("ReporteVENDetalleAnulacionesDevolucionesAction ::::: prepareParameterMap");
		
		ReporteVENDetalleAnulacionesDevolucionesForm reporteVENForm = (ReporteVENDetalleAnulacionesDevolucionesForm) this.formReporte;
		formatoReporte = reporteVENForm.getFormatoExportacion();
				
		params.put("titulo", this.getReportResourceMessage("reporteVENDetalleAnulacionesDevolucionesForm.titulo"));			
		params.put("codigoSubacceso", reporteVENForm.getCodigoSubacceso());			
				
		String fecha1 = DateUtil.getDate(reporteVENForm.getFechaDesdeD());
		String fecha2 = DateUtil.getDate(reporteVENForm.getFechaHastaD());
		reporteVENForm.setFechaDesde(fecha1);
		reporteVENForm.setFechaHasta(fecha2);		
		
		params.put("fechaDesde", DateUtil.convertDateToString(Constants.PATRON_FECHA_AAAAMMDD, 
															  DateUtil.convertStringToDate(reporteVENForm.getFechaDesde())));
		params.put("fechaHasta", DateUtil.convertDateToString(Constants.PATRON_FECHA_AAAAMMDD, 
															  DateUtil.convertStringToDate(reporteVENForm.getFechaHasta())));
		log.debug("Imprimiendo parámetros");
		log.debug(params);
		log.debug("Fin parámetros");
		return params;
	}
	
	/**
	 * Carga los subaccesos.
	 *
	 * @param val the val
	 */
	public void cargarSubaccesos(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("cargarSubaccesos:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		this.siccSubAccesoList = Arrays.asList(ajax.getSubaccesoByAcceso(val.getNewValue().toString()));			
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

	/**
	 * @return the siccSubAccesoList
	 */
	public List getSiccSubAccesoList() {
		return siccSubAccesoList;
	}

	/**
	 * @param siccSubAccesoList the siccSubAccesoList to set
	 */
	public void setSiccSubAccesoList(List siccSubAccesoList) {
		this.siccSubAccesoList = siccSubAccesoList;
	}
	
}