package biz.belcorp.ssicc.web.spusicc.cobranzas.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


/**
 * The Class MantenimientoCOBEtapaDeudaSearchForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 16/01/2015
 */
public class MantenimientoCOBEtapaDeudaSearchForm extends BaseSearchForm {
	
	private static final long serialVersionUID = 1L;

	private String codigoPais;
               
	
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


}