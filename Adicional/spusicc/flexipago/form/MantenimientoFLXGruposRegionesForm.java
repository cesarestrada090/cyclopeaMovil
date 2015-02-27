package biz.belcorp.ssicc.web.spusicc.flexipago.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoFLXGruposRegionesForm extends BaseEditForm{
	
	private static final long serialVersionUID = -8391307257499488402L;

	private String codigoPais;
	
	private String codigo;
	private String descripcion;
	
	private String codigoRegion;
	private String codigoRegionSeleccionada;
	
	
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
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getCodigoRegion() {
		return codigoRegion;
	}
	
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	
	public String getCodigoRegionSeleccionada() {
		return codigoRegionSeleccionada;
	}
	
	public void setCodigoRegionSeleccionada(String codigoRegionSeleccionada) {
		this.codigoRegionSeleccionada = codigoRegionSeleccionada;
	}
}
