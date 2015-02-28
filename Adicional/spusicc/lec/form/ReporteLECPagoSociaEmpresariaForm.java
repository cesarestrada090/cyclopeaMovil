package biz.belcorp.ssicc.web.spusicc.lec.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;



/**
 * @author <a href="mailto:yrivas@sigcomt.com">Yahir Rivas L.</a>
 * 
 * @struts.form name="reporteLECPagoSociaEmpresariaForm" extends="baseReporteForm"
 */
public class ReporteLECPagoSociaEmpresariaForm extends BaseReporteForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoRegion;
	private String codigoZona;
	private String codigoSeccion;
	private String campanyaProceso;
	private String fechaFacturacion;
	private Date fechaFacturacionD;
	private String formatoCampanyaProceso;
	private String codigoPrograma;
	private String descPrograma;
	private String codigoTramo;
	
	private String codigoPeriodoRecaudo;
	
	/**
	 * @return the campanyaProceso
	 */
	
	
	public String getCampanyaProceso() {
		return campanyaProceso;
	}
	public Date getFechaFacturacionD() {
		return fechaFacturacionD;
	}
	public void setFechaFacturacionD(Date fechaFacturacionD) {
		this.fechaFacturacionD = fechaFacturacionD;
	}
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @return the codigoSeccion
	 */
	public String getCodigoSeccion() {
		return codigoSeccion;
	}
	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}

	/**
	 * @param campanyaProceso the campanyaProceso to set
	 * @struts.validator type="required"
	 */
	public void setCampanyaProceso(String campanyaProceso) {
		this.campanyaProceso = campanyaProceso;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type="required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	/**
	 * @param codigoSeccion the codigoSeccion to set
	 */
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}
	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	/**
	 * @return the formatoCampanyaProceso
	 */
	public String getFormatoCampanyaProceso() {
		return formatoCampanyaProceso;
	}
	/**
	 * @param formatoCampanyaProceso the formatoCampanyaProceso to set
	 */
	public void setFormatoCampanyaProceso(String formatoCampanyaProceso) {
		this.formatoCampanyaProceso = formatoCampanyaProceso;
	}
	/**
	 * @return the codigoPrograma
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}
	/**
	 * @param codigoPrograma the codigoPrograma to set
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}
	/**
	 * @return the descPrograma
	 */
	public String getDescPrograma() {
		return descPrograma;
	}
	/**
	 * @param descPrograma the descPrograma to set
	 */
	public void setDescPrograma(String descPrograma) {
		this.descPrograma = descPrograma;
	}
	/**
	 * @return the codigoPeriodoRecaudo
	 */
	public String getCodigoPeriodoRecaudo() {
		return codigoPeriodoRecaudo;
	}
	/**
	 * @param codigoPeriodoRecaudo the codigoPeriodoRecaudo to set
	 * @struts.validator type="required"
	 */
	public void setCodigoPeriodoRecaudo(String codigoPeriodoRecaudo) {
		this.codigoPeriodoRecaudo = codigoPeriodoRecaudo;
	}
	/**
	 * @return the codigoTramo
	 */
	public String getCodigoTramo() {
		return codigoTramo;
	}
	/**
	 * @param codigoTramo the codigoTramo to set
	 * @struts.validator type="required"
	 */
	public void setCodigoTramo(String codigoTramo) {
		this.codigoTramo = codigoTramo;
	}
	
	
	
	
}