package biz.belcorp.ssicc.web.spusicc.flexipago.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoFLXGruposForm extends BaseEditForm{
	
	private static final long serialVersionUID = 6675400638146348668L;
	
	private String codigoPais;
	private String codigo;
	private String descripcion;
	private String valorConstante;
	private String estado;
	
	protected GrupoVariableForm[] variablesForm;
	
	public String getCodigoPais() {
		return codigoPais;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getValorConstante() {
		return valorConstante;
	}
	
	public GrupoVariableForm[] getVariablesForm() {
		return variablesForm;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setValorConstante(String valorConstante) {
		this.valorConstante = valorConstante;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setVariablesForm(GrupoVariableForm[] variablesForm) {
		this.variablesForm = variablesForm;
	}
	
}
