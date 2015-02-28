package biz.belcorp.ssicc.web.spusicc.proyeccion.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoPRYPorcentajeFaltanteSearchForm extends BaseSearchForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoUnidadNegocioAux; 
	private String descripcionUnidadNegocio; 
	
	
	/*
	 *  (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
//	public void reset(ActionMapping mapping, HttpServletRequest request) {
//		this.descripcionUnidadNegocio = "";
//		this.selectedItem="";
//		this.selectedItems=null;
//	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
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
	 * @return the codigoUnidadNegocioAux
	 */
	public String getCodigoUnidadNegocioAux() {
		return codigoUnidadNegocioAux;
	}

	/**
	 * @param codigoUnidadNegocioAux the codigoUnidadNegocioAux to set
	 */
	public void setCodigoUnidadNegocioAux(String codigoUnidadNegocioAux) {
		this.codigoUnidadNegocioAux = codigoUnidadNegocioAux;
	}
}
