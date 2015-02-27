package biz.belcorp.ssicc.web.spusicc.flexipago.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoFLXMotivosRecomendacionForm extends BaseEditForm{

	private static final long serialVersionUID = 4195627150544514424L;
	
	private String codigoPais;
	private String codigo;
	private String codigoMotivo;
	private String descripcion;
	private String estado;
	
	private String codigoEstatus;
	
	
	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getCodigoMotivo() {
		return codigoMotivo;
	}
	
	public void setCodigoMotivo(String codigoMotivo) {
		this.codigoMotivo = codigoMotivo;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCodigoEstatus() {
		return codigoEstatus;
	}

	public void setCodigoEstatus(String codigoEstatus) {
		this.codigoEstatus = codigoEstatus;
	}	

}
