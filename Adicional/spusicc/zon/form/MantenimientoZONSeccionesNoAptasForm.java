/*
 * 
 */
package biz.belcorp.ssicc.web.spusicc.zon.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * The Class MantenimientoZONSeccionesNoAptasForm.
 *
 * @author Belcorp
 * @version 1.0
 * 15/01/2015
 */
public class MantenimientoZONSeccionesNoAptasForm extends BaseEditForm {
	
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoRegion;
    private String codigoZona;
    private String codigoSeccion;
    private String indicadorApta;
    
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 * 
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona the codigoZona to set
	 * 
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
	 * 
	 */
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}

	/**
	 * @return the indicadorApta
	 */
	public String getIndicadorApta() {
		return indicadorApta;
	}

	/**
	 * @param indicadorApta the indicadorApta to set
	 * 
	 */
	public void setIndicadorApta(String indicadorApta) {
		this.indicadorApta = indicadorApta;
	}

	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion the codigoRegion to set
	 * 
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

}