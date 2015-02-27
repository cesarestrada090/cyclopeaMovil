package biz.belcorp.ssicc.web.spusicc.comision.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoCOMDesmarcarEnvioSAPSearchForm extends BaseSearchForm
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String codigoLider;

	/**
	 * @return Returns the codigoLider.
	 */
	public String getCodigoLider() {
		return codigoLider;
	}

	/**
	 * @param codigoLider
	 *            The codigoLider to set.
	 */
	public void setCodigoLider(String codigoLider) {
		this.codigoLider = codigoLider;
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
}
