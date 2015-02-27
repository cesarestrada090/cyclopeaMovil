package biz.belcorp.ssicc.web.spusicc.zon.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * <p><a href="MantenimientoZONUnidadGeograficaSearchForm.java.html"><i>View Source</i></a></p>
 * 	
 * @author Aurelio Oviedo
 * 
 */
public class MantenimientoZONUnidadGeograficaSearchForm extends BaseSearchForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	
	private String nivel1;
	private String nivel2;
	private String nivel3;
	private String nivel4;
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * 
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the nivel1
	 */
	public String getNivel1() {
		return nivel1;
	}
	/**
	 * @param nivel1 the nivel1 to set
	 */
	public void setNivel1(String nivel1) {
		this.nivel1 = nivel1;
	}
	/**
	 * @return the nivel2
	 */
	public String getNivel2() {
		return nivel2;
	}
	/**
	 * @param nivel2 the nivel2 to set
	 */
	public void setNivel2(String nivel2) {
		this.nivel2 = nivel2;
	}
	/**
	 * @return the nivel3
	 */
	public String getNivel3() {
		return nivel3;
	}
	/**
	 * @param nivel3 the nivel3 to set
	 */
	public void setNivel3(String nivel3) {
		this.nivel3 = nivel3;
	}
	/**
	 * @return the nivel4
	 */
	public String getNivel4() {
		return nivel4;
	}
	/**
	 * @param nivel4 the nivel4 to set
	 */
	public void setNivel4(String nivel4) {
		this.nivel4 = nivel4;
	}
}