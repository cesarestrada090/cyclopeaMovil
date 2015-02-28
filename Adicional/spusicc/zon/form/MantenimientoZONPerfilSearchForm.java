package biz.belcorp.ssicc.web.spusicc.zon.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoZONPerfilSearchForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:kgomez@sigcomt.com">Karina Gomez</a>
 * 
 */

public class MantenimientoZONPerfilSearchForm extends BaseSearchForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8019473719276740660L;
	
	private String codigoPais;
	private String descripcion;
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
