package biz.belcorp.ssicc.web.spusicc.comision.retail.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * The Class MantenimientoRETPorcentajeComisionForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 12/01/2015
 */
public class MantenimientoRETPorcentajeComisionForm extends BaseEditForm {
	
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String descripcionPais;
	private String porcentajeComision;

	
	/**
	 * @return the porcentajeComision
	 */
	public String getPorcentajeComision() {
		return porcentajeComision;
	}

	/**
	 * @param porcentajeComision the porcentajeComision to set
	 * @struts.validator type="required" 
	 */
	public void setPorcentajeComision(String porcentajeComision) {
		this.porcentajeComision = porcentajeComision;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @return the descripcionPais
	 */
	public String getDescripcionPais() {
		return descripcionPais;
	}

	/**
	 * @param descripcionPais the descripcionPais to set
	 */
	public void setDescripcionPais(String descripcionPais) {
		this.descripcionPais = descripcionPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type="required" 
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}


}