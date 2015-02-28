package biz.belcorp.ssicc.web.spusicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoPRYProyeccionFaltanteDiaForm extends BaseProcesoForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7801413500533014832L;
	private String  codigoProcesoBatch;
	private String  codigoPeriodo;
	private String  descripcionPeriodo;
	private String  fechaFacturacion;
	private Date  fechaFacturacionD;
	private Integer totalSolicitud;
	private String  numeroVersion;
	/**
	 * @return the codigoProcesoBatch
	 */
	public String getCodigoProcesoBatch() {
		return codigoProcesoBatch;
	}
	/**
	 * @param codigoProcesoBatch the codigoProcesoBatch to set
	 */
	public void setCodigoProcesoBatch(String codigoProcesoBatch) {
		this.codigoProcesoBatch = codigoProcesoBatch;
	}
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return the descripcionPeriodo
	 */
	public String getDescripcionPeriodo() {
		return descripcionPeriodo;
	}
	/**
	 * @param descripcionPeriodo the descripcionPeriodo to set
	 */
	public void setDescripcionPeriodo(String descripcionPeriodo) {
		this.descripcionPeriodo = descripcionPeriodo;
	}
	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}
	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}
	/**
	 * @return the fechaFacturacionD
	 */
	public Date getFechaFacturacionD() {
		return fechaFacturacionD;
	}
	/**
	 * @param fechaFacturacionD the fechaFacturacionD to set
	 */
	public void setFechaFacturacionD(Date fechaFacturacionD) {
		this.fechaFacturacionD = fechaFacturacionD;
	}
	/**
	 * @return the totalSolicitud
	 */
	public Integer getTotalSolicitud() {
		return totalSolicitud;
	}
	/**
	 * @param totalSolicitud the totalSolicitud to set
	 */
	public void setTotalSolicitud(Integer totalSolicitud) {
		this.totalSolicitud = totalSolicitud;
	}
	/**
	 * @return the numeroVersion
	 */
	public String getNumeroVersion() {
		return numeroVersion;
	}
	/**
	 * @param numeroVersion the numeroVersion to set
	 */
	public void setNumeroVersion(String numeroVersion) {
		this.numeroVersion = numeroVersion;
	}
	
	

}
