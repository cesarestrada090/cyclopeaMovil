package biz.belcorp.ssicc.web.spusicc.inc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoINCBloqueoPremiosForm extends BaseEditForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;	
	private String codigoPeriodo;
	private String codigoConcurso;
	private String codigoPremio;
	private String[] codigoRegion;
	private String indicadorReemplazo;
	
	private String identificador;
	
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 * @struts.validator type="required"
	 */  
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @struts.validator type="required"
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the codigoConcurso
	 */
	public String getCodigoConcurso() {
		return codigoConcurso;
	}

	/**
	 * @struts.validator type="required"
	 * @param codigoConcurso the codigoConcurso to set
	 */
	public void setCodigoConcurso(String codigoConcurso) {
		this.codigoConcurso = codigoConcurso;
	}

	/**
	 * @return the codigoPremio
	 */
	public String getCodigoPremio() {
		return codigoPremio;
	}

	/**
	 * @struts.validator type="required"
	 * @param codigoPremio the codigoPremio to set
	 */
	public void setCodigoPremio(String codigoPremio) {
		this.codigoPremio = codigoPremio;
	}

	/**
	 * @return the indicadorReemplazo
	 */
	public String getIndicadorReemplazo() {
		return indicadorReemplazo;
	}

	/**
	 * @param indicadorReemplazo the indicadorReemplazo to set
	 */
	public void setIndicadorReemplazo(String indicadorReemplazo) {
		this.indicadorReemplazo = indicadorReemplazo;
	}

	/**
	 * @return the codigoRegion
	 */
	public String[] getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion the codigoRegion to set
	 * @struts.validator type="required"
	 */
	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return the identificador
	 */
	public String getIdentificador() {
		return identificador;
	}

	/**
	 * @param identificador the identificador to set
	 */
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
}
