package biz.belcorp.ssicc.web.spusicc.zon.form;


import javax.validation.constraints.Size;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoZONRolSearchForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:kgomez@sigcomt.com">Karina Gomez</a>
 * 
 */

public class MantenimientoZONRolSearchForm extends BaseSearchForm{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1221791128218814121L;
	private String codigoPais;
	private String descripcion;
	private String codigo;
	
	
	
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
	 * @return the descripcion
	 */
	@Size(max=100)
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
