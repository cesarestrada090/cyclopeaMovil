package biz.belcorp.ssicc.web.spusicc.comision.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMComisionGerenteZonaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.comision.form.ConsultaCOMComisionGerenteRegionObjetivoForm;


/**
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar La Rosa</a><br>
 * 
 */
@ManagedBean
@SessionScoped
public class ConsultaCOMComisionGerenteRegionObjetivoAction extends BaseReporteAbstractAction {

	private List listaComision;
	private List listaRegion;
	private List listaGerencia;
	private String formatoReporte;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ConsultaCOMComisionGerenteRegionObjetivoForm reporteForm = new ConsultaCOMComisionGerenteRegionObjetivoForm();
		return reporteForm;
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ConsultaCOMComisionGerenteRegionObjetivoForm form = (ConsultaCOMComisionGerenteRegionObjetivoForm) this.formReporte;
		if ("XLS".equals(form.getFormatoExportacion())) {
			return "reporteCOMComisionGRegionObjeXLS";
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

		MantenimientoCOMComisionGerenteZonaService service = (MantenimientoCOMComisionGerenteZonaService) getBean("spusicc.mantenimientoCOMComisionGerenteZonaService");

		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
		criteria.put("tipoComision", "01");
		criteria.put("codigoBase", "04");
		setListaComision(service.getComisionVal(criteria));
		log.debug(" tamanio de lista comision " + getListaComision().size());

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		setListaRegion(reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion));
		
		log.debug("Todo Ok: Redireccionando");

	}

	@Override
	protected List setFindAttributes()throws Exception {
		 ConsultaCOMComisionGerenteRegionObjetivoForm f = (ConsultaCOMComisionGerenteRegionObjetivoForm)this.formReporte;
		 MantenimientoCOMComisionGerenteZonaService service = (MantenimientoCOMComisionGerenteZonaService) getBean("spusicc.mantenimientoCOMComisionGerenteZonaService");
		
		 Map criteria = new HashMap();
		 criteria.put("codigoComision", f.getCodigoComision());
		 criteria.put("codigoPeriodoInicial", f.getCodigoPeriodoInicial());
		 criteria.put("codigoPeriodoFinal", f.getCodigoPeriodoFinal());
		 String codigoRegion[] = f.getCodigoRegion();
		 if (codigoRegion != null) {
			 if (codigoRegion.length > 0) {
				 if (StringUtils.isNotBlank(codigoRegion[0]))
					 criteria.put("codigoRegion", f.getCodigoRegion());
			 }
		 }
		 
		 return service.getComisionGerenteRegionObjetivo(criteria);
	}	
	
	@Override
	protected Map prepareParameterMap(Map params) throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaCOMComisionGerenteRegionObjetivoAction.prepareParameterMap' method");
		}
		ConsultaCOMComisionGerenteRegionObjetivoForm reporteForm = (ConsultaCOMComisionGerenteRegionObjetivoForm)this.formReporte;
		formatoReporte = reporteForm.getFormatoExportacion();
		
		reporteForm.setTitulo(this.getReportResourceMessage("consultaCOMComisionGerenteRegionObjetivoForm.title"));
		reporteForm.setBeforeExecuteReporte(true);
		params.put("titulo", reporteForm.getTitulo());
		
		String[] codigoRegion = reporteForm.getCodigoRegion();
		params.put("codigoRegion", obtieneCondicion(reporteForm.getCodigoRegion(), "Z.COD_REGI", "'"));
		params.put("codigoComision", obtieneCondicion(reporteForm.getCodigoComision(), "Z.COD_COMI", "'"));
		params.put("codigoPeriodoIni", reporteForm.getCodigoPeriodoInicial());
		params.put("codigoPeriodoFin", reporteForm.getCodigoPeriodoFinal());
		
		return params;
	}

	public List getListaComision() {
		return listaComision;
	}

	public void setListaComision(List listaComision) {
		this.listaComision = listaComision;
	}

	public List getListaRegion() {
		return listaRegion;
	}

	public void setListaRegion(List listaRegion) {
		this.listaRegion = listaRegion;
	}

	public List getListaGerencia() {
		return listaGerencia;
	}

	public void setListaGerencia(List listaGerencia) {
		this.listaGerencia = listaGerencia;
	}
	
}