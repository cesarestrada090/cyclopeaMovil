package biz.belcorp.ssicc.web.spusicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoINCConfiguracionConcursoCuponElectronicoSearchForm
		extends BaseSearchForm implements Serializable 
{
	private static final long serialVersionUID = 1L;

	protected String codigoConcurso;

	protected String codigoPeriodoProceso;

	protected String descripcionConcurso;

	protected String periodoProceso;

	protected String ultimoCupon;

	protected String estado;

	private String codigoPais;

	/**
	 * @return the codigoConcurso
	 */
	public String getCodigoConcurso() {
		return codigoConcurso;
	}

	/**
	 * @param codigoConcurso
	 *            the codigoConcurso to set
	 */
	public void setCodigoConcurso(String codigoConcurso) {
		this.codigoConcurso = codigoConcurso;
	}

	/**
	 * @return the codigoPeriodoProceso
	 */
	public String getCodigoPeriodoProceso() {
		return codigoPeriodoProceso;
	}

	/**
	 * @param codigoPeriodoProceso
	 *            the codigoPeriodoProceso to set
	 */
	public void setCodigoPeriodoProceso(String codigoPeriodoProceso) {
		this.codigoPeriodoProceso = codigoPeriodoProceso;
	}

	/**
	 * @return the descripcionConcurso
	 */
	public String getDescripcionConcurso() {
		return descripcionConcurso;
	}

	/**
	 * @param descripcionConcurso
	 *            the descripcionConcurso to set
	 */
	public void setDescripcionConcurso(String descripcionConcurso) {
		this.descripcionConcurso = descripcionConcurso;
	}

	/**
	 * @return the periodoProceso
	 */
	public String getPeriodoProceso() {
		return periodoProceso;
	}

	/**
	 * @param periodoProceso
	 *            the periodoProceso to set
	 */
	public void setPeriodoProceso(String periodoProceso) {
		this.periodoProceso = periodoProceso;
	}

	/**
	 * @return the ultimoCupon
	 */
	public String getUltimoCupon() {
		return ultimoCupon;
	}

	/**
	 * @param ultimoCupon
	 *            the ultimoCupon to set
	 */
	public void setUltimoCupon(String ultimoCupon) {
		this.ultimoCupon = ultimoCupon;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

}
