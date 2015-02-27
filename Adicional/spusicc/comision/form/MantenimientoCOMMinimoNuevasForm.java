package biz.belcorp.ssicc.web.spusicc.comision.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;


/**
 * The Class MantenimientoCOMMinimoNuevasForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 09/01/2015
 */
public class MantenimientoCOMMinimoNuevasForm extends BaseEditForm {

	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoRegion;
	private String codigoZona;
	private String cantidadMinima;
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type="required" 
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @param codigoRegion the codigoRegion to set
	 * @struts.validator type="required" 
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	/**
	 * @return the cantidadMinima
	 */
	public String getCantidadMinima() {
		return cantidadMinima;
	}
	/**
	 * @param cantidadMinima the cantidadMinima to set
	 * @struts.validator type="required" 
	 */
	public void setCantidadMinima(String cantidadMinima) {
		this.cantidadMinima = cantidadMinima;
	}
	
	
}
