package biz.belcorp.ssicc.web.spusicc.lec.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteLECResultadosForm extends BaseReporteForm implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;	
	private String codigoPeriodo;	
	private String[] codigoRegion;
	
	
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
	public String[] getCodigoRegion() {
		return codigoRegion;
	}
	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	

}
