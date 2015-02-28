package biz.belcorp.ssicc.web.spusicc.ventas.form;

import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoVENPorcentajeIgvForm extends BaseEditForm {
	
	private static final long serialVersionUID = -5598467587573964080L;
	private String codigoPais;/**Codigo del Pais*/
	private String codigoPeriodo;/**Periodo*/
	private Date codigoPeriodoDate;
	private Integer valIgv;/**Valor de Igv*/
	
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


	public Integer getValIgv() {
		return valIgv;
	}


	public void setValIgv(Integer valIgv) {
		this.valIgv = valIgv;
	}
	
	public void reset() {
		this.codigoPais = "";
		this.codigoPeriodo  = "";
		this.valIgv=null;
	}


	public Date getCodigoPeriodoDate() {
		return codigoPeriodoDate;
	}


	public void setCodigoPeriodoDate(Date codigoPeriodoDate) {
		this.codigoPeriodoDate = codigoPeriodoDate;
	}

}
