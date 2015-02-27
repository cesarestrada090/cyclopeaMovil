package biz.belcorp.ssicc.web.spusicc.comision.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMComisionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.comision.form.ProcesoCOMComisionGenerarArchivoNominaForm;

/** 
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ProcesoCOMComisionGenerarArchivoNominaAction extends BaseReporteAbstractAction {
	
	private static final long serialVersionUID = -8167675097964549780L;
	
	private List listTipoComision;
	private LabelValue[] listComision;
	private Map datosAdam;
	private String formatoReporte;
		
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ProcesoCOMComisionGenerarArchivoNominaForm reporteForm = new ProcesoCOMComisionGenerarArchivoNominaForm();
		return reporteForm;
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ProcesoCOMComisionGenerarArchivoNominaForm form = (ProcesoCOMComisionGenerarArchivoNominaForm) this.formReporte; 
		if ("XLS".equals(form.getFormatoExportacion())) {
			return "reporteCOMComisionNominaRegionXLS";
		} 
		else {
			return "reporteMaestroHorizontal";
		} 
	}
	
	@Override
	protected String devuelveNombreSubReporte() throws Exception {		
		return "";
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ProcesoCOMComisionGenerarArchivoNominaAction.setViewAtributes' method");
		}
		
		this.mostrarBotonBuscar = true;
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
		this.mostrarListaBusqueda = true;
		
		MantenimientoCOMComisionService service = (MantenimientoCOMComisionService) getBean("spusicc.mantenimientoCOMComisionService");
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		setListTipoComision(service.getTipoComiList(criteria));
		log.debug(" tamanio de lista comision " + getListTipoComision().size());
		
		setDatosAdam(((Map)service.getComDatosAdam(criteria).get(0)));		
		
		log.debug("Todo Ok: Redireccionando");

	}

	@Override
	protected List setFindAttributes()throws Exception {
		ProcesoCOMComisionGenerarArchivoNominaForm f = (ProcesoCOMComisionGenerarArchivoNominaForm)this.formReporte;
		MantenimientoCOMComisionService service = (MantenimientoCOMComisionService) getBean("spusicc.mantenimientoCOMComisionService");		
		f.setCodigoComisionAnterior(f.getCodigoComision());
		
		Map criteria = getDatosAdam();
		criteria.put("codigoComision", f.getCodigoComision());
		criteria.put("codigoPeriodoInicial", f.getCodigoPeriodoInicial());
		criteria.put("codigoPeriodoFinal", f.getCodigoPeriodoFinal());
		criteria.put("tipoComision", f.getTipoComision());
		 
		List lista = service.getComArchivoNominaList(criteria);
		return lista;
	}
		
	@Override
	protected Map prepareParameterMap(Map params) throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ProcesoCOMComisionGenerarArchivoNominaAction.prepareParameterMap' method");
		}
		ProcesoCOMComisionGenerarArchivoNominaForm reporteForm = (ProcesoCOMComisionGenerarArchivoNominaForm)this.formReporte;
		formatoReporte = reporteForm.getFormatoExportacion();
		
		reporteForm.setTitulo(this.getReportResourceMessage("procesoCOMComisionGenerarArchivoNominaForm.title"));
		reporteForm.setBeforeExecuteReporte(true);
		params.put("titulo", reporteForm.getTitulo());
		
		Map criteria = getDatosAdam();
		String codigoCompania = (String)criteria.get("codigoCompania");
		String codigoIncRecup = (String)criteria.get("codigoIncRecup");
		
		params.put("codigoIncRecup", codigoIncRecup);
		params.put("codigoCompania", codigoCompania);
		
		return params;
	}
	
	/**
	 * Metodo que obtiene el listado de comisiones de acuerdo a valor seleccionado del usuario usando 
	 * el componente "ssicc"; procede a invocar el metodo getComisionesByTipo que existe en la capa de servicio.  
	 * 
	 * @param val, objeto que representa un evento del framework primefaces a través del AJAX
	 */
	public void loadComisionByTipo(ValueChangeEvent val){
		String tipoComision = val.getNewValue().toString();
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		this.listComision = aSvc.getComisionesByTipo(tipoComision);		
	}

	public List getListTipoComision() {
		return listTipoComision;
	}

	public void setListTipoComision(List listTipoComision) {
		this.listTipoComision = listTipoComision;
	}

	public Map getDatosAdam() {
		return datosAdam;
	}

	public void setDatosAdam(Map datosAdam) {
		this.datosAdam = datosAdam;
	}

	public LabelValue[] getListComision() {
		return listComision;
	}

	public void setListComision(LabelValue[] listComision) {
		this.listComision = listComision;
	}	
}