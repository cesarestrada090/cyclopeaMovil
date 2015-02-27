package biz.belcorp.ssicc.web.spusicc.ventas.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


public class MantenimientoVENPorcentajeIgvSearchForm extends BaseSearchForm  {
	
	
	private static final long serialVersionUID = -2573015866273986246L;
	
	private String codigoPais; /**Codigo del Pais*/
	private String codigoPeriodo; /**Periodo*/
	
	
	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
	public void reset() {
		this.codigoPeriodo  = "";
	}
	

}
