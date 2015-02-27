package biz.belcorp.ssicc.web.spusicc.flexipago.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;

public class GrupoVariableForm  extends BaseForm implements Serializable{

	private static final long serialVersionUID = 7225224981752903656L;
	
	private String codigo;
	private String codigoVariable;
	private String nombreVariable;
	private String valorPeso;
	
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getCodigoVariable() {
		return codigoVariable;
	}
	
	public String getNombreVariable() {
		return nombreVariable;
	}
	
	public String getValorPeso() {
		return valorPeso;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public void setCodigoVariable(String codigoVariable) {
		this.codigoVariable = codigoVariable;
	}
	
	public void setNombreVariable(String nombreVariable) {
		this.nombreVariable = nombreVariable;
	}
	
	public void setValorPeso(String valorPeso) {
		this.valorPeso = valorPeso;
	}
}
