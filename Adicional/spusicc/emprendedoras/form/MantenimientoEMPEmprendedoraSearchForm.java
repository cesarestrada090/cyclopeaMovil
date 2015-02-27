package biz.belcorp.ssicc.web.spusicc.emprendedoras.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoEMPEmprendedoraSearchForm extends BaseSearchForm{
	
	private static final long serialVersionUID = 8328046988510396560L;
	

	public String codigoPais;

	public String tipo;
	
	public String nombre;	
	
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	

}
