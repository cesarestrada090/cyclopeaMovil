package biz.belcorp.ssicc.web.spusicc.proyeccion.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoPRYPorcentajeFaltanteForm extends BaseEditForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoUnidadNegocio; 
	private String descripcionUnidadNegocio;
	private String porcentajeMaximo;
	
	
	/**
	 * @return Returns the codigoUnidadNegocio.
	 */
	public String getCodigoUnidadNegocio() {
		return codigoUnidadNegocio;
	}
	/**
	 * @param codigoUnidadNegocio The codigoUnidadNegocio to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoUnidadNegocio(String codigoUnidadNegocio) {
		this.codigoUnidadNegocio = codigoUnidadNegocio;
	}
	/**
	 * @return Returns the descripcionUnidadNegocio.
	 */
	public String getDescripcionUnidadNegocio() {
		return descripcionUnidadNegocio;
	}
	/**
	 * @param descripcionUnidadNegocio The descripcionUnidadNegocio to set.
	 */
	public void setDescripcionUnidadNegocio(String descripcionUnidadNegocio) {
		this.descripcionUnidadNegocio = descripcionUnidadNegocio;
	}
	
	/**
	 * @return Returns the porcentajeMaximo.
	 */
	public String getPorcentajeMaximo() {
		return porcentajeMaximo;
	}
	
	/**
	 * @param porcentajeMaximo The porcentajeMaximo to set.
	 * @struts.validator type="required"
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="^\d{1,13}(.(\d{1,2}))?$"
	 */
	public void setPorcentajeMaximo(String porcentajeMaximo) {
		this.porcentajeMaximo = porcentajeMaximo;
	} 
}
