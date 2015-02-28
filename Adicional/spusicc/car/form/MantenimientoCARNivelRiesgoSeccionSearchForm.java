package biz.belcorp.ssicc.web.spusicc.car.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * The Class MantenimientoCARNivelRiesgoSeccionSearchForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 15/01/2015
 */
public class MantenimientoCARNivelRiesgoSeccionSearchForm extends BaseSearchForm {
			 
	private static final long serialVersionUID = 1L;
	private String codigoPais; 
	private String codigoRegion;
	private String codigoZona;
	private String codigoSeccion;
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	
	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type = "required"  
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
	 * @struts.validator type = "required"  
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
	 * @return the codigoSeccion
	 */
	public String getCodigoSeccion() {
		return codigoSeccion;
	}
	
	/**
	 * @param codigoSeccion the codigoSeccion to set
	 */
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}
	

}
