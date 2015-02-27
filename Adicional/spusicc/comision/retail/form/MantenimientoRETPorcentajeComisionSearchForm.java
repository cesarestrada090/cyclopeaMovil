package biz.belcorp.ssicc.web.spusicc.comision.retail.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * The Class MantenimientoRETPorcentajeComisionSearchForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 12/01/2015
 */
public class MantenimientoRETPorcentajeComisionSearchForm extends BaseSearchForm {

	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String descripcionPais;
	
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