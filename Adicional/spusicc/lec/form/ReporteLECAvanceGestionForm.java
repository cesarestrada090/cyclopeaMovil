package biz.belcorp.ssicc.web.spusicc.lec.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;



/**
 * @author <a href="mailto:yrivas@sigcomt.com">Yahir Rivas L.</a>
 * 
 * @struts.form name="reporteLECPagoSociaEmpresariaForm" extends="baseReporteForm"
 */
public class ReporteLECAvanceGestionForm extends BaseReporteForm {
	
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
	
	
	
	public Date getFechaFacturacionD() {
		return fechaFacturacionD;
	}
	public void setFechaFacturacionD(Date fechaFacturacionD) {
		this.fechaFacturacionD = fechaFacturacionD;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the campanyaProceso
	 */
	public String getCampanyaProceso() {
		return campanyaProceso;
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


	public void setCampanyaProceso(String campanyaProceso) {
		this.campanyaProceso = campanyaProceso;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}

	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}


	public String getFormatoCampanyaProceso() {
		return formatoCampanyaProceso;
	}

	public void setFormatoCampanyaProceso(String formatoCampanyaProceso) {
		this.formatoCampanyaProceso = formatoCampanyaProceso;
	}

	public String getCodigoPrograma() {
		return codigoPrograma;
	}

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
	
	
	
	
}