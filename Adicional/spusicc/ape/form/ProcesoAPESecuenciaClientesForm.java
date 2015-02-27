package biz.belcorp.ssicc.web.spusicc.ape.form;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * The Class ProcesoAPESecuenciaClientesForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 22/12/2014
 */
public class ProcesoAPESecuenciaClientesForm extends BaseProcesoForm {

	private static final long serialVersionUID = 1L;
	private String codigoPais;	

	public String getCodigoPais() {
		return codigoPais;
	}
	
	/**
     * @param codigoPais
     *            The codigoPais to set.
     * @struts.validator type="required"
     */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
		
}