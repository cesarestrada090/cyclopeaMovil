package biz.belcorp.ssicc.web.spusicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

public class ProcesoPERGeneracionCtaCteDocumentoLegalForm extends BaseInterfazForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8079610886120707484L;
	
	private String codigoTipoOrigenDatos;

	public String getCodigoTipoOrigenDatos() {
		return codigoTipoOrigenDatos;
	}

	public void setCodigoTipoOrigenDatos(String codigoTipoOrigenDatos) {
		this.codigoTipoOrigenDatos = codigoTipoOrigenDatos;
	}
	
	

}
