package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


/**
 * The Class BusquedaRECDocumentoReferenciaSearchForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 06/12/2013
 */
public class BusquedaRECDocumentoReferenciaSearchForm extends BaseSearchForm {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
    private String codigoClienteBuscar;
	private String oidIdioma;

	

	public String getCodigoClienteBuscar() {
		return codigoClienteBuscar;
	}

	public void setCodigoClienteBuscar(String codigoClienteBuscar) {
		this.codigoClienteBuscar = codigoClienteBuscar;
	}

	public String getOidIdioma() {
		return oidIdioma;
	}

	public void setOidIdioma(String oidIdioma) {
		this.oidIdioma = oidIdioma;
	}
}
