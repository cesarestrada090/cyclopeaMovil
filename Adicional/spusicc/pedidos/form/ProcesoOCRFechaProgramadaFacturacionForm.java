package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoOCRFechaProgramadaFacturacionForm extends BaseProcesoForm
		implements Serializable {

	private static final long serialVersionUID = 1L;

	private String periodo;

	private String tipoSolicitud;

	private String fecha;
	private Date fechaD;

	private String nuevaFecha;
	private Date nuevaFechaD;

	private String grupoProceso;

	private String oidTipoSolicitud;

	public String getPeriodo() {
		return periodo;
	}

	/**
	 * @param periodo
	 * 
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	/**
	 * @return tipoSolicitud
	 */
	public String getTipoSolicitud() {
		return tipoSolicitud;
	}

	/**
	 * @param tipoSolicitud
	 * 
	 */
	public void setTipoSolicitud(String tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	/**
	 * @return fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return nuevaFecha
	 */
	public String getNuevaFecha() {
		return nuevaFecha;
	}

	/**
	 * @param nuevaFecha
	 */
	public void setNuevaFecha(String nuevaFecha) {
		this.nuevaFecha = nuevaFecha;
	}

	/**
	 * @return the grupoProceso
	 */
	public String getGrupoProceso() {
		return grupoProceso;
	}

	/**
	 * @param grupoProceso
	 *            the grupoProceso to set
	 */
	public void setGrupoProceso(String grupoProceso) {
		this.grupoProceso = grupoProceso;
	}

	/**
	 * @return the oidTipoSolicitud
	 */
	public String getOidTipoSolicitud() {
		return oidTipoSolicitud;
	}

	/**
	 * @param oidTipoSolicitud
	 *            the oidTipoSolicitud to set
	 */
	public void setOidTipoSolicitud(String oidTipoSolicitud) {
		this.oidTipoSolicitud = oidTipoSolicitud;
	}

	/**
	 * @return the fechaD
	 */
	public Date getFechaD() {
		return fechaD;
	}

	/**
	 * @param fechaD the fechaD to set
	 */
	public void setFechaD(Date fechaD) {
		this.fechaD = fechaD;
	}

	/**
	 * @return the nuevaFechaD
	 */
	public Date getNuevaFechaD() {
		return nuevaFechaD;
	}

	/**
	 * @param nuevaFechaD the nuevaFechaD to set
	 */
	public void setNuevaFechaD(Date nuevaFechaD) {
		this.nuevaFechaD = nuevaFechaD;
	}
}