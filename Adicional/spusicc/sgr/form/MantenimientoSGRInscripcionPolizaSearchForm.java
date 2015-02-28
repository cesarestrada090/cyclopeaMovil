package biz.belcorp.ssicc.web.spusicc.sgr.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * The Class MantenimientoSGRInscripcionPolizaSearchForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 23/01/2015
 */
public class MantenimientoSGRInscripcionPolizaSearchForm extends BaseSearchForm {
	
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoPoliza;
	private String descripcionPoliza;
	private String codigoCliente;
	private String numeroCertificado;
	private String estado;
	private String origen;
	
	
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 * @struts.validator type = "required" 
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return the numeroCertificado
	 */
	public String getNumeroCertificado() {
		return numeroCertificado;
	}

	/**
	 * @param numeroCertificado the numeroCertificado to set
	 */
	public void setNumeroCertificado(String numeroCertificado) {
		this.numeroCertificado = numeroCertificado;
	}

	/**
	 * @return the codigoPoliza
	 */
	public String getCodigoPoliza() {
		return codigoPoliza;
	}

	/**
	 * @param codigoPoliza the codigoPoliza to set
	 */
	public void setCodigoPoliza(String codigoPoliza) {
		this.codigoPoliza = codigoPoliza;
	}

	/**
	 * @return the descripcionPoliza
	 */
	public String getDescripcionPoliza() {
		return descripcionPoliza;
	}

	/**
	 * @param descripcionPoliza the descripcionPoliza to set
	 */
	public void setDescripcionPoliza(String descripcionPoliza) {
		this.descripcionPoliza = descripcionPoliza;
	}

	
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the origen
	 */
	public String getOrigen() {
		return origen;
	}

	/**
	 * @param origen the origen to set
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}

}