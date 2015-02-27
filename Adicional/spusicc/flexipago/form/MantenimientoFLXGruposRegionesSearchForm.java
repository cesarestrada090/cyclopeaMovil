package biz.belcorp.ssicc.web.spusicc.flexipago.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoFLXGruposRegionesSearchForm extends BaseSearchForm{

	private static final long serialVersionUID = 8476041865450052767L;
	
	private String codigoPais;
	private String codigoGrupo;
	
	
	public String getCodigoPais() {
		return codigoPais;
	}
	
	public String getCodigoGrupo() {
		return codigoGrupo;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public void setCodigoGrupo(String codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}


}
