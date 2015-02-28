package biz.belcorp.ssicc.web.spusicc.comision.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;


/**
 * The Class MantenimientoCOMZonasDemandaAnticipadaForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 08/01/2015
 */
public class MantenimientoCOMZonasDemandaAnticipadaForm extends BaseEditForm {

	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoZona;

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
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona the codigoZona to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	
}
