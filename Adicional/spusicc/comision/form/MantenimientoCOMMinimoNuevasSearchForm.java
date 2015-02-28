package biz.belcorp.ssicc.web.spusicc.comision.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


/**
 * The Class MantenimientoCOMMinimoNuevasSearchForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 09/01/2015
 */
public class MantenimientoCOMMinimoNuevasSearchForm extends BaseSearchForm {

	private static final long serialVersionUID = 1L;	
	public String codigoPais;
	public String codigoRegionSearch;
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	
	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoRegionSearch
	 */
	public String getCodigoRegionSearch() {
		return codigoRegionSearch;
	}

	/**
	 * @param codigoRegionSearch the codigoRegionSearch to set
	 */
	public void setCodigoRegionSearch(String codigoRegionSearch) {
		this.codigoRegionSearch = codigoRegionSearch;
	}
	
	
}
