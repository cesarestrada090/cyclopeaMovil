package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.time.DateFormatUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.bean.ReporteEnviadoMail;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRCapturaPedidoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ReporteOCRCargaDocumentoForm;


@ManagedBean
@SessionScoped
public class ReporteOCRCargaDocumentoAction extends BaseReporteAbstractAction{

	private static final long serialVersionUID = -8435977825548097269L;
	
	private String formatoReporte;
	private String tipoReporte;


	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteOCRCargaDocumentoForm reporteForm = new ReporteOCRCargaDocumentoForm();
		return reporteForm;
	}	

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setViewAtributes");
		}

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		MantenimientoOCRCapturaPedidoService service1 = (MantenimientoOCRCapturaPedidoService) getBean("spusicc.pedidos.mantenimientoOCRCapturaPedidoService");
		ReporteOCRCargaDocumentoForm f = (ReporteOCRCargaDocumentoForm) this.formReporte;
		f.setCodigoPais(pais.getCodigo());
		Map criteria;
		criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", "0");
		criteria.put("indicadorActiva", "1");
		List lista = service.getCampanhasActivasByCriteria(criteria);
		if (lista.size() == 1) {
			criteria = null;
			criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoPeriodo", String.valueOf(lista.get(0)));
			String fecha = service1.getFechaFacturacion(criteria);
			f.setFechaInicialDate(DateUtil.convertStringToDate(fecha));
			f.setFechaFinDate(DateUtil.convertStringToDate(fecha));
			f.setFechaInicial(fecha);
			f.setFechaFin(fecha);
		}
		log.debug("Todo Ok: Redireccionando");
		//this.mostrarEnviarEmail=true;
		//this.visualizarReporte=false;
		//this.mostrarLogPantalla=true;
		//this.mostrarListaReporteLog=true;
		//this.formReporte.setEnvioEmail(true);
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteOCRCargaDocumentoXLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {		
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteOCRCargaDocumentoForm f = (ReporteOCRCargaDocumentoForm) this.formReporte;
		f.setFechaInicial(DateUtil.convertDateToString(f.getFechaInicialDate()));
		f.setFechaFin(DateUtil.convertDateToString(f.getFechaFinDate()));

		this.formatoReporte = f.getFormatoExportacion();		
				
		params.put("titulo", this.getResourceMessage("reporteOCRCargaDocumentoForm.titulo"));
		params.put("tipoReporte", this.tipoReporte);
		params.put("fecha", f.getFechaInicial());
	
		params.put("fechaInicio", DateFormatUtils.format(f.getFechaInicialDate(), "yyyyMMdd"));
		params.put("fechaFin", DateFormatUtils.format(f.getFechaFinDate(), "yyyyMMdd"));
		return params;
	}
	
	public String setValidarReporte() {
		ReporteOCRCargaDocumentoForm f = (ReporteOCRCargaDocumentoForm) this.formReporte;
	    if (f.getFechaFinDate().compareTo(f.getFechaInicialDate()) < 0 ){
	    	String mensaje =  this.getResourceMessage("errors.compare.dates");
			return mensaje;
	    }	    					
	    return null;
	}
	
	
	protected String getNombreArchivoReporte(ReporteParams reporteParams) {
		String nombreArchivoReporte;		
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
		Map repor=reporteParams.getQueryParams();
		String prefijo=(String)repor.get("prefijoArchivo");
		nombreArchivoReporte = prefijo +  "_" +
						           sdf.format(new Date(System.currentTimeMillis()));
		
		return nombreArchivoReporte;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#getMailService()
	 */
	public String getMailService() {
		return "ocr.mailReporteOCRCargaDocumento";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#beforeGrabarReporte(javax.servlet.http.HttpServletRequest, org.apache.struts.action.ActionForm)
	 */
	protected void beforeGrabarReporte() {		
		super.beforeGrabarReporte();	
		//this.formatoReporte ="VPDF";		
		//this.nameSubReporte = "reporteMAENovedadesZonaPDF";		
	}
	
	public void cargarLista(ActionEvent actionEvent){
        if (log.isDebugEnabled()) {
            log.debug("Entering 'cargarLista' method");
        }
       this.mostrarEnviarEmail=true;
       this.mostrarListaReporteLog=true;
       this.formReporte.setEnvioEmail(true);
       this.grabarReporte(actionEvent);
        
        return;
	}	

}
