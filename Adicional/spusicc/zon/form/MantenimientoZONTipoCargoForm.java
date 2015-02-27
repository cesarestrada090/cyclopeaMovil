package biz.belcorp.ssicc.web.spusicc.zon.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;


/**
 * The Class MantenimientoZONTipoCargoForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 26/01/2015
 */
public class MantenimientoZONTipoCargoForm extends BaseEditForm {

	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoCargo;
	private String descripcion;
	private String tipoUnidadAdministrativa;
	private String cantidadUnidadAdministrativa;
	private String titular;
	
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
	 * @return the codigoCargo
	 */
	public String getCodigoCargo() {
		return codigoCargo;
	}

	/**
	 * @param codigoCargo the codigoCargo to set
	 * @struts.validator type = "required" 
	 */
	public void setCodigoCargo(String codigoCargo) {
		this.codigoCargo = codigoCargo;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 * @struts.validator type = "required" 
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the tipoUnidadAdministrativa
	 */
	public String getTipoUnidadAdministrativa() {
		return tipoUnidadAdministrativa;
	}

	/**
	 * @param tipoUnidadAdministrativa the tipoUnidadAdministrativa to set
	 * @struts.validator type = "required" 
	 */
	public void setTipoUnidadAdministrativa(String tipoUnidadAdministrativa) {
		this.tipoUnidadAdministrativa = tipoUnidadAdministrativa;
	}

	/**
	 * @return the cantidadUnidadAdministrativa
	 */
	public String getCantidadUnidadAdministrativa() {
		return cantidadUnidadAdministrativa;
	}

	/**
	 * @param cantidadUnidadAdministrativa the cantidadUnidadAdministrativa to set
	 * @struts.validator type = "required" 
	 */
	public void setCantidadUnidadAdministrativa(String cantidadUnidadAdministrativa) {
		this.cantidadUnidadAdministrativa = cantidadUnidadAdministrativa;
	}

	/**
	 * @return the titular
	 */
	public String getTitular() {
		return titular;
	}

	/**
	 * @param titular the titular to set
	 * @struts.validator type = "required" 
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}
		
}