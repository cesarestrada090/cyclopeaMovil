package biz.belcorp.ssicc.web.spusicc.dto.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoDTOMatrizDescuentoSearchForm extends BaseSearchForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9210975985262806855L;
	private String codigoPais;

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
}
