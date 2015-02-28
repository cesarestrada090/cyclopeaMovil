package biz.belcorp.ssicc.web.spusicc.zon.form;

import javax.validation.constraints.Size;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoZONRolForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:kgomez@sigcomt.com">Karina Gomez</a>
 */
public class MantenimientoZONRolForm extends BaseEditForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1589562255849822888L;

	private String codigoPais;
	private String codigo;
	private String descripcion;
	private String codigoTipoUniAdmi;
	private String indicadorActivo;
	private String estado;
	private boolean activo; 
	
	
	
	/**
	 * @return the activo
	 */
	public boolean isActivo() {
		return activo;
	}
	/**
	 * @param activo the activo to set
	 */
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the descripcion
	 */
	@Size(max = 300)
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the codigoTipoUniAdmi
	 */
	public String getCodigoTipoUniAdmi() {
		return codigoTipoUniAdmi;
	}
	/**
	 * @param codigoTipoUniAdmi the codigoTipoUniAdmi to set
	 */
	public void setCodigoTipoUniAdmi(String codigoTipoUniAdmi) {
		this.codigoTipoUniAdmi = codigoTipoUniAdmi;
	}
	/**
	 * @return the indicadorActivo
	 */
	public String getIndicadorActivo() {
		return indicadorActivo;
	}
	/**
	 * @param indicadorActivo the indicadorActivo to set
	 */
	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	
}
