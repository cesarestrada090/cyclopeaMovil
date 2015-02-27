package biz.belcorp.ssicc.web.spusicc.gen.action;

import java.io.Serializable;
import java.util.Date;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

public class ProcesoGENProcesarCierreZonaForm extends BaseInterfazForm implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoMarca;
	private String codigoCanal;

	private String codigoPeriodo;
	private String fechaFacturacion;
	private Date fechaFacturacionDate;
	
	private String[] regiones;
	private String[] zonas;
	
	private boolean mostrarBotonProcesar;
	/* INI JJ  PER-SiCC-2012-0388 */
	private String indicadorProceso;
	private String indicadorEjecucionSICC;
	private String indicadorSeleccionInterfaces;
	private String indicadorEjecucionInterfaces;
	
	/**
	 * @return
	 */
	public String getIndicadorProceso() {
		return indicadorProceso;
	}

	/**
	 * @param indicadorProceso
	 */
	public void setIndicadorProceso(String indicadorProceso) {
		this.indicadorProceso = indicadorProceso;
	}

	/**
	 * @return
	 */
	public String getIndicadorEjecucionSICC() {
		return indicadorEjecucionSICC;
	}

	/**
	 * @param indicadorEjecucionSICC
	 */
	public void setIndicadorEjecucionSICC(String indicadorEjecucionSICC) {
		this.indicadorEjecucionSICC = indicadorEjecucionSICC;
	}

	/**
	 * @return
	 */
	public String getIndicadorSeleccionInterfaces() {
		return indicadorSeleccionInterfaces;
	}

	/**
	 * @param indicadorSeleccionInterfaces
	 */
	public void setIndicadorSeleccionInterfaces(String indicadorSeleccionInterfaces) {
		this.indicadorSeleccionInterfaces = indicadorSeleccionInterfaces;
	}

	/**
	 * @return
	 */
	public String getIndicadorEjecucionInterfaces() {
		return indicadorEjecucionInterfaces;
	}

	/**
	 * @param indicadorEjecucionInterfaces
	 */
	public void setIndicadorEjecucionInterfaces(String indicadorEjecucionInterfaces) {
		this.indicadorEjecucionInterfaces = indicadorEjecucionInterfaces;
	}

	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo The codigoPeriodo to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
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
	 * @return the codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca the codigoMarca to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal the codigoCanal to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return the mostrarBotonProcesar
	 */
	public boolean isMostrarBotonProcesar() {
		return mostrarBotonProcesar;
	}

	/**
	 * @param mostrarBotonProcesar the mostrarBotonProcesar to set
	 */
	public void setMostrarBotonProcesar(boolean mostrarBotonProcesar) {
		this.mostrarBotonProcesar = mostrarBotonProcesar;
	}

	/**
	 * @return the regiones
	 */
	public String[] getRegiones() {
		return regiones;
	}

	/**
	 * @param regiones the regiones to set
	 */
	public void setRegiones(String[] regiones) {
		this.regiones = regiones;
	}

	/**
	 * @return the zonas
	 */
	public String[] getZonas() {
		return zonas;
	}

	/**
	 * @param zonas the zonas to set
	 */
	public void setZonas(String[] zonas) {
		this.zonas = zonas;
	}

	public Date getFechaFacturacionDate() {
		return fechaFacturacionDate;
	}

	public void setFechaFacturacionDate(Date fechaFacturacionDate) {
		this.fechaFacturacionDate = fechaFacturacionDate;
	}
}
