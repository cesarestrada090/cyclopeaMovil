package biz.belcorp.ssicc.web.spusicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoINCConfiguracionConcursoCuponElectronicoForm extends BaseEditForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoConcurso;
	private String codigoPeriodoInicial;
	private String codigoPeriodoFinal;
	private String cuponInicial;
	private String ultimoCupon;
	private String estado;
	private String descripcionConcurso;
	private String codigoPeriodoProcesoAux;
	
	/**
	 * @return the codigoConcurso
	 */
	public String getCodigoConcurso() {
		return codigoConcurso;
	}

	/**
	 * @param codigoConcurso the codigoConcurso to set
	 * 
	 * @struts.validator type = "required"
	 */
	public void setCodigoConcurso(String codigoConcurso) {
		this.codigoConcurso = codigoConcurso;
	}

	/**
	 * @return the codigoPeriodoInicial
	 */
	public String getCodigoPeriodoInicial() {
		return codigoPeriodoInicial;
	}

	/**
	 * @param codigoPeriodoInicial the codigoPeriodoInicial to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPeriodoInicial(String codigoPeriodoInicial) {
		this.codigoPeriodoInicial = codigoPeriodoInicial;
	}

	/**
	 * @return the codigoPeriodoFinal
	 */
	public String getCodigoPeriodoFinal() {
		return codigoPeriodoFinal;
	}

	/**
	 * @param codigoPeriodoFinal the codigoPeriodoFinal to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPeriodoFinal(String codigoPeriodoFinal) {
		this.codigoPeriodoFinal = codigoPeriodoFinal;
	}

	/**
	 * @return the cuponInicial
	 */
	public String getCuponInicial() {
		return cuponInicial;
	}

	/**
	 * @param cuponInicial the cuponInicial to set
	 * 
	 * @struts.validator type = "required"
	 */
	public void setCuponInicial(String cuponInicial) {
		this.cuponInicial = cuponInicial;
	}

	/**
	 * @return the ultimoCupon
	 */
	public String getUltimoCupon() {
		return ultimoCupon;
	}

	/**
	 * @param ultimoCupon the ultimoCupon to set
	 * 
	 * @struts.validator type = "required"
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
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the descripcionConcurso
	 */
	public String getDescripcionConcurso() {
		return descripcionConcurso;
	}

	/**
	 * @param descripcionConcurso the descripcionConcurso to set
	 */
	public void setDescripcionConcurso(String descripcionConcurso) {
		this.descripcionConcurso = descripcionConcurso;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoPeriodoProcesoAux
	 */
	public String getCodigoPeriodoProcesoAux() {
		return codigoPeriodoProcesoAux;
	}

	/**
	 * @param codigoPeriodoProcesoAux the codigoPeriodoProcesoAux to set
	 */
	public void setCodigoPeriodoProcesoAux(String codigoPeriodoProcesoAux) {
		this.codigoPeriodoProcesoAux = codigoPeriodoProcesoAux;
	}
}
