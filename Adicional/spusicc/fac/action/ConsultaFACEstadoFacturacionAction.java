package biz.belcorp.ssicc.web.spusicc.fac.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.fac.MantenimientoFACGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.form.ConsultaMAVEstadoAtencionesCampanaForm;
import biz.belcorp.ssicc.web.spusicc.fac.form.ConsultaFACEstadoFacturacionForm;
import biz.belcorp.ssicc.web.spusicc.flexipago.form.ProcesoFLXProcesosCierreCampaniaForm;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano
 *         Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ConsultaFACEstadoFacturacionAction extends
BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -809635998880486173L;
	private String tipoReporte;
	private String tipoDocumento;
	private List facTipoDocumentoList=new ArrayList();
	private List facListaEstadoFacturacionDetallado=new ArrayList();
	private List facListaEstadoFacturacionConsolidado=new ArrayList();
	
	

	public List getFacTipoDocumentoList() {
		return facTipoDocumentoList;
	}

	public void setFacTipoDocumentoList(List facTipoDocumentoList) {
		this.facTipoDocumentoList = facTipoDocumentoList;
	}

	public List getFacListaEstadoFacturacionDetallado() {
		return facListaEstadoFacturacionDetallado;
	}

	public void setFacListaEstadoFacturacionDetallado(
			List facListaEstadoFacturacionDetallado) {
		this.facListaEstadoFacturacionDetallado = facListaEstadoFacturacionDetallado;
	}

	public List getFacListaEstadoFacturacionConsolidado() {
		return facListaEstadoFacturacionConsolidado;
	}

	public void setFacListaEstadoFacturacionConsolidado(
			List facListaEstadoFacturacionConsolidado) {
		this.facListaEstadoFacturacionConsolidado = facListaEstadoFacturacionConsolidado;
	}

	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaFACEstadoFacturacionAction.setFindAttributes' method");
		}
		
		ActionMessages messages = new ActionMessages();
		String strMessage = null;
		
		ConsultaFACEstadoFacturacionForm reporteForm = (ConsultaFACEstadoFacturacionForm) formReporte;
		MantenimientoFACGenericoService serviceGenerico = (MantenimientoFACGenericoService) getBean("spusicc.mantenimientoFACGenericoService");
		
		Map criteria = new HashMap();
		List list = new ArrayList();
		
		try {
			//Removemos de sesion las listas buscadas anteriormente
			facListaEstadoFacturacionConsolidado.clear();
			facListaEstadoFacturacionDetallado.clear();

			//Cargamos en el Map los datos recogidos en pantalla
			criteria.put("tipoReporte", reporteForm.getTipoReporte());
			criteria.put("tipoDocumento", reporteForm.getTipoDocumento());
			criteria.put("serieDocumento", reporteForm.getSerieDocumento());
			criteria.put("fechaDesde", reporteForm.getFechaDesde());
			criteria.put("fechaHasta", reporteForm.getFechaHasta());
			criteria.put("fechaInicio", reporteForm.getFechaInicio());
			criteria.put("fechaFin", reporteForm.getFechaFin());
			criteria.put("estadoDocumento", reporteForm.getEstadoDocumento());
			
			//Actualizamos las variables del tipoReporte y tipoDocumento
			tipoReporte = reporteForm.getTipoReporte();
			tipoDocumento = reporteForm.getTipoDocumento();
			
			/**
			 * Tipo Reporte:		Detallado (D), Consolidado (C)
			 * 
			 * Tipo Documento: 
			 * 							Factura (001), Boleta Venta (011), Boleta Venta Premio (012)
			 * 							Nota de Credito Contra Boleta de Venta (020), Nota de Credito Contra Factura (021), Nota de Debito (023)
			 * 							Nota de Credito Retail Contra Boleta de Venta (BR), Nota de Credito Retail Contra Factura (FR) 
			 */
			if(StringUtils.equalsIgnoreCase(reporteForm.getTipoReporte(), "D")){
				if(StringUtils.equalsIgnoreCase(reporteForm.getTipoDocumento(), "001") || StringUtils.equalsIgnoreCase(reporteForm.getTipoDocumento(), "011") || StringUtils.equalsIgnoreCase(reporteForm.getTipoDocumento(), "012"))
					list = serviceGenerico.getListaEstadoFacturacionDetalladoBF(criteria);
				else if(StringUtils.equalsIgnoreCase(reporteForm.getTipoDocumento(), "020") || StringUtils.equalsIgnoreCase(reporteForm.getTipoDocumento(), "021") || StringUtils.equalsIgnoreCase(reporteForm.getTipoDocumento(), "023"))
					list = serviceGenerico.getListaEstadoFacturacionDetalladoNCND(criteria);
				else if(StringUtils.equalsIgnoreCase(reporteForm.getTipoDocumento(), "BR") || StringUtils.equalsIgnoreCase(reporteForm.getTipoDocumento(), "FR"))
					list = serviceGenerico.getListaEstadoFacturacionDetalladoNCRetailBF(criteria);
				else if(StringUtils.equalsIgnoreCase(reporteForm.getTipoDocumento(), "T"))
					list = serviceGenerico.getListaEstadoFacturacionDetalladoTodos(criteria);
				facListaEstadoFacturacionDetallado=list;
				
			}else{
				if(StringUtils.equalsIgnoreCase(reporteForm.getTipoDocumento(), "001") || StringUtils.equalsIgnoreCase(reporteForm.getTipoDocumento(), "011") || StringUtils.equalsIgnoreCase(reporteForm.getTipoDocumento(), "012"))
					list = serviceGenerico.getListaEstadoFacturacionConsolidadoBF(criteria);
				else if(StringUtils.equalsIgnoreCase(reporteForm.getTipoDocumento(), "020") || StringUtils.equalsIgnoreCase(reporteForm.getTipoDocumento(), "021") || StringUtils.equalsIgnoreCase(reporteForm.getTipoDocumento(), "023"))
					list = serviceGenerico.getListaEstadoFacturacionConsolidadoNCND(criteria);
				else if(StringUtils.equalsIgnoreCase(reporteForm.getTipoDocumento(), "BR") || StringUtils.equalsIgnoreCase(reporteForm.getTipoDocumento(), "FR"))
					list = serviceGenerico.getListaEstadoFacturacionConsolidadoNCRetailBF(criteria);
				else if(StringUtils.equalsIgnoreCase(reporteForm.getTipoDocumento(), "T"))
					list = serviceGenerico.getListaEstadoFacturacionConsolidadoTodos(criteria);
				facListaEstadoFacturacionConsolidado=list;
				
			}
			
		} catch (Exception ex) {
			strMessage = ex.getMessage();
			if (StringUtils.isBlank(strMessage))
				strMessage = ex.getLocalizedMessage();
			messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.detail", strMessage));
	
		}
		
		return list;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaFACEstadoFacturacionAction.setViewAttributes' method");
		}
		this.mostrarBotonBuscar=true;
		this.mostrarReportePDF=false;
		this.mostrarReporteXLS=true;
		
		ActionMessages messages = new ActionMessages();
		String strMessage = null;

		ConsultaFACEstadoFacturacionForm reporteForm = (ConsultaFACEstadoFacturacionForm) formReporte;
		InterfazSiCCService siccService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
	
		
		try {
			//Cargamos la fecha actual en pantalla 
			
			
			//Cargamos la lista de documentos de pago 
			facTipoDocumentoList=siccService.getTipoDocumentosPago();
			
			
			//Removemos de sesion las listas buscadas anteriormente facListaEstadoFacturacionDetallado
			log.debug("Todo Ok: Redireccionando");
			reporteForm.setFechaInicioD( new Date(System.currentTimeMillis()));
			reporteForm.setFechaFinD( new Date(System.currentTimeMillis()) );
			reporteForm.setFechaDesdeD( new Date(System.currentTimeMillis()) );
			reporteForm.setFechaHastaD( new Date(System.currentTimeMillis()) );
		} catch (Exception ex) {
			strMessage = ex.getMessage();
			if (StringUtils.isBlank(strMessage))
				strMessage = ex.getLocalizedMessage();
			messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.detail", strMessage));
	
		}
	}

	



	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ConsultaFACEstadoFacturacionForm form = new ConsultaFACEstadoFacturacionForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaFACEstadoFacturacionAction.getReporteFileName' method");
		}
		
		String lsReporte = null;
		
		try {
			/**
			 * Tipo Reporte:		Detallado (D), Consolidado (C)
			 * Tipo Documento: Factura (001), Boleta Venta (011), Boleta Venta Premio (012)
			 * 							Nota de Credito Contra Boleta de Venta (020), Nota de Credito Contra Factura (021), Nota de Debito (023)
			 * 							Nota de Credito Retail Contra Boleta de Venta (BR), Nota de Credito Retail Contra Factura (FR) 
			 */
			if(StringUtils.equalsIgnoreCase(tipoReporte, "D")){
				if(StringUtils.equalsIgnoreCase(tipoDocumento, "001") || StringUtils.equalsIgnoreCase(tipoDocumento, "011") || StringUtils.equalsIgnoreCase(tipoDocumento, "012"))
					lsReporte = "reporteFACEstadoFacturaDetalladoBFXLS";
				else if(StringUtils.equalsIgnoreCase(tipoDocumento, "020") || StringUtils.equalsIgnoreCase(tipoDocumento, "021") || StringUtils.equalsIgnoreCase(tipoDocumento, "023"))
					lsReporte = "reporteFACEstadoFacturaDetalladoNCNDXLS";
				else if(StringUtils.equalsIgnoreCase(tipoDocumento, "BR") || StringUtils.equalsIgnoreCase(tipoDocumento, "FR"))
					lsReporte = "reporteFACEstadoFacturaDetalladoNCRBFXLS";
				else if(StringUtils.equalsIgnoreCase(tipoDocumento, "T"))
					lsReporte = "reporteFACEstadoFacturaDetalladoTodosXLS";
			}else{
				if(StringUtils.equalsIgnoreCase(tipoDocumento, "001") || StringUtils.equalsIgnoreCase(tipoDocumento, "011") || StringUtils.equalsIgnoreCase(tipoDocumento, "012"))
					lsReporte = "reporteFACEstadoFacturaConsolidadoBFXLS";
				else if(StringUtils.equalsIgnoreCase(tipoDocumento, "020") || StringUtils.equalsIgnoreCase(tipoDocumento, "021") || StringUtils.equalsIgnoreCase(tipoDocumento, "023"))
					lsReporte = "reporteFACEstadoFacturaConsolidadoNCNDXLS";
				else if(StringUtils.equalsIgnoreCase(tipoDocumento, "BR") || StringUtils.equalsIgnoreCase(tipoDocumento, "FR"))
					lsReporte = "reporteFACEstadoFacturaConsolidadoNCRBFXLS";
				else if(StringUtils.equalsIgnoreCase(tipoDocumento, "T"))
					lsReporte = "reporteFACEstadoFacturaConsolidadoTodosXLS";
			}
			
		} catch (Exception ex) {
			log.debug("Error: " + ex.getMessage());
		} finally {
			log.debug("Reporte de estado facturacion ubicado con exito");
		}
		
		return lsReporte;
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaFACEstadoFacturacionAction.prepareParameterMap' method");
		}
		
		ActionMessages messages = new ActionMessages();
		String strMessage = null;
		
		ConsultaFACEstadoFacturacionForm reporteForm = (ConsultaFACEstadoFacturacionForm) formReporte;
		reporteForm.setFechaDesde(DateUtil.getDate(reporteForm.getFechaDesdeD()));
		reporteForm.setFechaHasta(DateUtil.getDate(reporteForm.getFechaHastaD()));
		try {
			//Cargamos a los parametros del reporte, los datos recogidos en pantalla
			params.put("tipoReporte", reporteForm.getTipoReporte());
			params.put("tipoDocumento", reporteForm.getTipoDocumento());
			params.put("serieDocumento", reporteForm.getSerieDocumento());
			params.put("fechaInicio", reporteForm.getFechaInicio());
			params.put("fechaFin", reporteForm.getFechaFin());
			params.put("fechaDesde", reporteForm.getFechaDesde());
			params.put("fechaHasta", reporteForm.getFechaHasta());
			params.put("estadoDocumento", reporteForm.getEstadoDocumento());
			
			//Actualizamos las variables del tipoReporte y tipoDocumento
			tipoReporte = reporteForm.getTipoReporte();
			tipoDocumento = reporteForm.getTipoDocumento();
			
			//Validar unidades de atencion para Nota de Credito Contra Boleta de Venta (020), Nota de Credito Contra Factura (021), Nota de Debito (023)
			if(StringUtils.equalsIgnoreCase(tipoDocumento, "020") || StringUtils.equalsIgnoreCase(tipoDocumento, "021"))
				params.put("numUnidadAtencion", " AND det.num_unid_aten <> 0 ");
			else if(StringUtils.equalsIgnoreCase(tipoDocumento, "023"))
				params.put("numUnidadAtencion", " AND det.num_unid_aten > 0 ");
			else
				params.put("numUnidadAtencion", " ");
			
			return params;
		} catch (Exception ex) {
			strMessage = ex.getMessage();
			if (StringUtils.isBlank(strMessage))
				strMessage = ex.getLocalizedMessage();
			messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.detail", strMessage));
			return params;
		}
	}
	

}