/*
 * Created on 04-ene-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
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
import biz.belcorp.ssicc.web.spusicc.comision.form.ProcesoCOMComisionGerenteZonaForm;

/** 
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 *         
 */

@ManagedBean
@SessionScoped
public class ProcesoCOMComisionGerenteZonaAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;

	private List siccRegionList;
	private List comTipoComisionistaList;
	private List listadoZona;
	private String formatoReporte;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ProcesoCOMComisionGerenteZonaForm reporteForm = new ProcesoCOMComisionGerenteZonaForm();
		return reporteForm;
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ProcesoCOMComisionGerenteZonaForm form = (ProcesoCOMComisionGerenteZonaForm)this.formReporte;
		if ("XLS".equals(form.getFormatoExportacion())){
			return "reporteCOMComisionGZonaObjeXLS";
		}else{
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
			log.debug("Entering 'ProcesoCOMComisionGerenteZonaAction.setViewAtributes' method");
		}
		
		this.mostrarBotonBuscar = true;
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
		this.mostrarListaBusqueda = true;
		
		MantenimientoCOMComisionGerenteZonaService service = (MantenimientoCOMComisionGerenteZonaService) getBean("spusicc.mantenimientoCOMComisionGerenteZonaService");
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		Map criteria = new HashMap();
		criteria.put("tipoComision", "02");
		criteria.put("codigoBase", "04");
		setComTipoComisionistaList(service.getComisionVal(criteria));
		log.debug(" tamanio de lista comision " + getComTipoComisionistaList().size());

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		setSiccRegionList(reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion));
	
		log.debug("Todo Ok: Redireccionando");

	}

	@Override
	protected List setFindAttributes()throws Exception {
		ProcesoCOMComisionGerenteZonaForm f = (ProcesoCOMComisionGerenteZonaForm)this.formReporte;
		
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
		 
		 setListadoZona( service.getComGerenteZonaList(criteria));
		 
		return getListadoZona();
	}

	

	@Override
	protected Map prepareParameterMap(Map params) throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ProcesoCOMComisionGerenteZonaAction.prepareParameterMap' method");
		}
		ProcesoCOMComisionGerenteZonaForm reporteForm = (ProcesoCOMComisionGerenteZonaForm) this.formReporte;
		formatoReporte = reporteForm.getFormatoExportacion();
		
		reporteForm.setTitulo(this.getReportResourceMessage("procesoCOMComisionGerenteZonaForm.title"));
		reporteForm.setBeforeExecuteReporte(true);
		params.put("titulo", reporteForm.getTitulo());
		
		String[] codigoRegion = reporteForm.getCodigoRegion();
		params.put("codigoRegion", obtieneCondicion(reporteForm.getCodigoRegion(), "Z.COD_REGI", "'"));
		params.put("codigoComision", obtieneCondicion(reporteForm.getCodigoComision(), "Z.COD_COMI", "'"));
		params.put("codigoPeriodoIni", reporteForm.getCodigoPeriodoInicial());
		params.put("codigoPeriodoFin", reporteForm.getCodigoPeriodoFinal());
	
		return params;
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public List getComTipoComisionistaList() {
		return comTipoComisionistaList;
	}

	public void setComTipoComisionistaList(List comTipoComisionistaList) {
		this.comTipoComisionistaList = comTipoComisionistaList;
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public List getListadoZona() {
		return listadoZona;
	}

	public void setListadoZona(List listadoZona) {
		this.listadoZona = listadoZona;
	}	
	
}