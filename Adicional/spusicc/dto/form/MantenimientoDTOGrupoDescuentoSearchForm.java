package biz.belcorp.ssicc.web.spusicc.dto.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoDTOGrupoDescuentoSearchForm extends BaseSearchForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1444809730978453170L;
	
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
