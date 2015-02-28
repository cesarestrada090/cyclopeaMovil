package biz.belcorp.ssicc.web.spusicc.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

public class ProcesoPERCruceSaldoPositivoNegativoForm extends BaseInterfazForm{

	private static final long serialVersionUID = -2635380135155441881L;	
	
	private String codigoTipoOrigenDatos;

	public String getCodigoTipoOrigenDatos() {
		return codigoTipoOrigenDatos;
	}

	public void setCodigoTipoOrigenDatos(String codigoTipoOrigenDatos) {
		this.codigoTipoOrigenDatos = codigoTipoOrigenDatos;
	}
	
}
