package biz.belcorp.ssicc.web.spusicc.flexipago.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;

public class ParametroForm extends BaseForm implements Serializable{
	
	private static final long serialVersionUID = 7263679869970250714L;
	
	private String codigo;
	private String codigoParametro;
	private String descripcionParametro;
	private String valorParametro;
	private String valorParametro2;
	private String codigoGrupo;
	private String descripcionGrupo;
	


	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoParametro() {
		return codigoParametro;
	}

	public void setCodigoParametro(String codigoParametro) {
		this.codigoParametro = codigoParametro;
	}
	
	public String getDescripcionParametro() {
		return descripcionParametro;
	}
	
	public void setDescripcionParametro(String descripcionParametro) {
		this.descripcionParametro = descripcionParametro;
	}
	
	public String getValorParametro() {
		return valorParametro;
	}
	
	public void setValorParametro(String valorParametro) {
		this.valorParametro = valorParametro;
	}
	
	public String getCodigoGrupo() {
		return codigoGrupo;
	}
	
	public void setCodigoGrupo(String codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}
	
	public String getDescripcionGrupo() {
		return descripcionGrupo;
	}
	
	public void setDescripcionGrupo(String descripcionGrupo) {
		this.descripcionGrupo = descripcionGrupo;
	}
	
	public String getValorParametro2() {
		return valorParametro2;
	}
	
	public void setValorParametro2(String valorParametro2) {
		this.valorParametro2 = valorParametro2;
	}
}
