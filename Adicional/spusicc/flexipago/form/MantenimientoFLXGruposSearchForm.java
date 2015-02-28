package biz.belcorp.ssicc.web.spusicc.flexipago.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoFLXGruposSearchForm extends BaseSearchForm{
	
	private static final long serialVersionUID = -7897096584585730383L;
	
	private String codigoPais;
	private String descripcion;	


	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
