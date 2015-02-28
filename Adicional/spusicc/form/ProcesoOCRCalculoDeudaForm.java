package biz.belcorp.ssicc.web.spusicc.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * The Class ProcesoOCRCalculoDeudaForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 06/01/2015
 */
public class ProcesoOCRCalculoDeudaForm extends BaseInterfazForm {

	private static final long serialVersionUID = 1L;
	private String  codigoProcesoBatch;	

	/**
	 * @return Returns the codigoProcesoBatch.
	 */
	public String getCodigoProcesoBatch() {
		return codigoProcesoBatch;
	}

	/**
	 * @param codigoProcesoBatch The codigoProcesoBatch to set.
	 */
	public void setCodigoProcesoBatch(String codigoProcesoBatch) {
		this.codigoProcesoBatch = codigoProcesoBatch;
	}

}
