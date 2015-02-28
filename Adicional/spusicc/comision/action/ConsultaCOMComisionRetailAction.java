/*
 * 
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
import biz.belcorp.ssicc.web.spusicc.comision.form.ConsultaCOMComisionRetailForm;


// TODO: Auto-generated Javadoc
/**
 * The Class ConsultaCOMComisionRetailAction.
 *
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar La Rosa</a><br>
 */
@ManagedBean
@SessionScoped
public class ConsultaCOMComisionRetailAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The lista comision. */
	private List listaComision;
	
	/** The lista region. */
	private List listaRegion;
	
	/** The lista gerencia. */
	private List listaGerencia;
	
	/** The formato reporte. */
	private String formatoReporte;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ConsultaCOMComisionRetailForm reporteForm = new ConsultaCOMComisionRetailForm();
		return reporteForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteCOMComisionRetailXLS";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
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
		criteria.put("tipoComision", "02");
		criteria.put("codigoBase", "06");
		List list = service.getComisionVal(criteria);
		log.debug(" tamanio de lista comision " + list.size());
		this.setListaComision(list);
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		this.setListaRegion(reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion));
		
		log.debug("Todo Ok: Redireccionando");

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes()throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes");
		}
		 ConsultaCOMComisionRetailForm f = (ConsultaCOMComisionRetailForm)this.formReporte;
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
		 List list = service.getComisionRetail(criteria);		 
		 return list;
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaCOMComisionGerenteRegionObjetivoAction.prepareParameterMap' method");
		}
		ConsultaCOMComisionRetailForm form = (ConsultaCOMComisionRetailForm)this.formReporte;
		
		String[] codigoRegion = form.getCodigoRegion();
		params.put("codigoRegion", obtieneCondicion(form.getCodigoRegion(), "Z.COD_REGI", "'"));
		params.put("codigoComision", obtieneCondicion(form.getCodigoComision(), "Z.COD_COMI", "'"));
		params.put("codigoPeriodoIni", form.getCodigoPeriodoInicial());
		params.put("codigoPeriodoFin", form.getCodigoPeriodoFinal());
		
		return params;
	}

	/**
	 * Gets the lista comision.
	 *
	 * @return the lista comision
	 */
	public List getListaComision() {
		return listaComision;
	}

	/**
	 * Sets the lista comision.
	 *
	 * @param listaComision the new lista comision
	 */
	public void setListaComision(List listaComision) {
		this.listaComision = listaComision;
	}

	/**
	 * Gets the lista region.
	 *
	 * @return the lista region
	 */
	public List getListaRegion() {
		return listaRegion;
	}

	/**
	 * Sets the lista region.
	 *
	 * @param listaRegion the new lista region
	 */
	public void setListaRegion(List listaRegion) {
		this.listaRegion = listaRegion;
	}

	/**
	 * Gets the lista gerencia.
	 *
	 * @return the lista gerencia
	 */
	public List getListaGerencia() {
		return listaGerencia;
	}

	/**
	 * Sets the lista gerencia.
	 *
	 * @param listaGerencia the new lista gerencia
	 */
	public void setListaGerencia(List listaGerencia) {
		this.listaGerencia = listaGerencia;
	}
	
}