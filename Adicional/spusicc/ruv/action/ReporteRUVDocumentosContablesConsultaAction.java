package biz.belcorp.ssicc.web.spusicc.ruv.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import org.apache.commons.lang.StringUtils;
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
import biz.belcorp.ssicc.web.spusicc.ruv.form.ReporteRUVDocumentosContablesConsultaForm;

@ManagedBean
@SessionScoped
public class ReporteRUVDocumentosContablesConsultaAction extends BaseReporteAbstractAction{
	
	private static final long serialVersionUID = -8597144041761585850L;
	
	private String formatoReporte;
	private List ruvTipoDocumentoContableList;	
	private LabelValue[] ruvAccesoList;	
	private LabelValue[] ruvSubaccesoList;
	public List siccCanalList;
	
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRUVDocumentosContablesConsultaForm reporteForm = new ReporteRUVDocumentosContablesConsultaForm();
		return reporteForm;
	}
	
	
	@Override
	protected void setViewAtributes() throws Exception {
		
		log.debug("ReporteRUVDocumentosContablesAction - setViewAttributes");
		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;		
		
		ReporteRUVDocumentosContablesConsultaForm f = (ReporteRUVDocumentosContablesConsultaForm) this.formReporte;
		MantenimientoRUVDocumentosContablesService service = (MantenimientoRUVDocumentosContablesService)getBean("spusicc.mantenimientoRUVDocumentosContablesService");
		InterfazSiCCService siccService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codpais = pais.getCodigo();
		f.setCodigoPais(codpais);
		
		this.ruvTipoDocumentoContableList=service.getTipoDocumentoContable();
		this.siccCanalList=siccService.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.ruvAccesoList=ajax.getAccesoByCanal(Constants.CODIGO_CANAL_DEFAULT);
		this.ruvSubaccesoList= ajax.getSubaccesoByAcceso(Constants.CODIGO_ACCESO_DEFAULT);
		
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
			return "reporteRUVDocumentosContablesConsultaXLS";
		return "";
	}
	
		
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		
		log.debug("ReporteRUVDocumentosContablesAction - prepareParameterMap");
		
		ReporteRUVDocumentosContablesConsultaForm f = (ReporteRUVDocumentosContablesConsultaForm) this.formReporte;		
		formatoReporte = f.getFormatoExportacion();
		log.debug("mi fecha: "+DateUtil.convertDateToString("yyyyMMdd", DateUtil.convertStringToDate("dd/MM/yyyy", f.getFechaDesde())));
			
		params.put("codigoPais", f.getCodigoPais());
		params.put("codigoCanal", f.getCodigoCanal());	
		params.put("codigoAcceso", f.getCodigoAcceso());
		params.put("fechaDesde", DateUtil.convertDateToString("yyyyMMdd", f.getFechaDesdeD()));
		params.put("fechaHasta", DateUtil.convertDateToString("yyyyMMdd",f.getFechaHastaD()));
		params.put("serie", f.getSerie());
		params.put("codigoSubacceso", f.getCodigoSubacceso());
		params.put("oidTipoDocumento",f.getCodigoTipoDocumentoContable());
			
		String condicion = "";			
		if(StringUtils.isNotBlank(f.getNumDocInterno()))
		{
			condicion = condicion + " and a.num_docu_cont_inte = '" + f.getNumDocInterno().trim() + "' "; 
		}
		if(StringUtils.isNotBlank(f.getNumDocLegal()))
		{
			condicion = condicion + " and a.val_nume_docu_lega = '" + f.getNumDocLegal().trim() + "' "; 
		}
			   
		params.put("condicion", condicion);
		return params;			
		
	}
	
	public void cargarAccesos(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("cargar Accesos:ValueChangeEvent");
		}
		ReporteRUVDocumentosContablesConsultaForm f = (ReporteRUVDocumentosContablesConsultaForm) this.formReporte;	
		String valor=val.getNewValue().toString();
		String acceso="";
		log.debug(valor);
		AjaxService ajax = (AjaxService) getBean("ajaxService");		
		this.ruvAccesoList=ajax.getAccesoByCanal(valor);
		acceso=getRuvAccesoList()[0].getValue();
		if(valor.equals("VD")){
			acceso=getRuvAccesoList()[2].getValue();
		}
		
		this.ruvSubaccesoList= ajax.getSubaccesoByAcceso(acceso);
	}
	
	public void cargarSubaccesos(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("cargarSubaccesos:ValueChangeEvent");
		}
		String valor=val.getNewValue().toString();
		log.debug(valor);
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		this.ruvSubaccesoList= ajax.getSubaccesoByAcceso(valor);
		
	}
	
	public String setValidarReporte() {
		ReporteRUVDocumentosContablesConsultaForm f = (ReporteRUVDocumentosContablesConsultaForm) this.formReporte;	
	    if (f.getFechaHastaD().compareTo(f.getFechaDesdeD()) < 0 ){
	    	String mensaje =  this.getResourceMessage("errors.compare.dates");
			return mensaje;
	    }	    					
	    return null;
	}


	public String getFormatoReporte() {
		return formatoReporte;
	}


	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}


	public List getRuvTipoDocumentoContableList() {
		return ruvTipoDocumentoContableList;
	}


	public void setRuvTipoDocumentoContableList(List ruvTipoDocumentoContableList) {
		this.ruvTipoDocumentoContableList = ruvTipoDocumentoContableList;
	}


	public LabelValue[] getRuvAccesoList() {
		return ruvAccesoList;
	}


	public void setRuvAccesoList(LabelValue[] ruvAccesoList) {
		this.ruvAccesoList = ruvAccesoList;
	}


	public LabelValue[] getRuvSubaccesoList() {
		return ruvSubaccesoList;
	}


	public List getSiccCanalList() {
		return siccCanalList;
	}


	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}


	public void setRuvSubaccesoList(LabelValue[] ruvSubaccesoList) {
		this.ruvSubaccesoList = ruvSubaccesoList;
	}
	
	
}
