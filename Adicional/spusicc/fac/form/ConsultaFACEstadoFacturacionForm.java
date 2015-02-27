package biz.belcorp.ssicc.web.spusicc.fac.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;




public class ConsultaFACEstadoFacturacionForm extends BaseReporteForm implements Serializable {
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 8302927402970628951L;

	private String codigoPais;
	
	private String tipoReporte;
	
	private String tipoDocumento;
	
	private String serieDocumento;
	
	private String fechaInicio;
	
	private Date fechaInicioD;
	
	private String fechaFin;
	
	private Date fechaFinD;
	
	private String fechaDesde;
	
	private Date fechaDesdeD;
	
	private String fechaHasta;
	
	private Date fechaHastaD;
	
	private String estadoDocumento;

	/**
	 * @return the codigoPais
	 */
	
	
	
	public String getCodigoPais() {
		return codigoPais;
	}

	public Date getFechaInicioD() {
		return fechaInicioD;
	}

	public void setFechaInicioD(Date fechaInicioD) {
		
		this.fechaInicioD = fechaInicioD;
	}

	public Date getFechaFinD() {
		float x=3.3f;
		return fechaFinD;
		
	}

	public void setFechaFinD(Date fechaFinD) {
		this.fechaFinD = fechaFinD;
	}

	public Date getFechaDesdeD() {
		return fechaDesdeD;
	}

	public void setFechaDesdeD(Date fechaDesdeD) {
		this.fechaDesdeD = fechaDesdeD;
	}



	public Date getFechaHastaD() {
		return fechaHastaD;
	}

	public void setFechaHastaD(Date fechaHastaD) {
		this.fechaHastaD = fechaHastaD;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @return the serieDocumento
	 */
	public String getSerieDocumento() {
		return serieDocumento;
	}

	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * @return the fechaDesde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * @return the fechaHasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * @return the estadoDocumento
	 */
	public String getEstadoDocumento() {
		return estadoDocumento;
	}


	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}


	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @param serieDocumento the serieDocumento to set
	 */
	public void setSerieDocumento(String serieDocumento) {
		this.serieDocumento = serieDocumento;
	}


	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}


	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	/**
	 * @param estadoDocumento the estadoDocumento to set
	 */
	public void setEstadoDocumento(String estadoDocumento) {
		this.estadoDocumento = estadoDocumento;
	}
}
