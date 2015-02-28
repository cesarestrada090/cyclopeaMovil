package biz.belcorp.ssicc.web.spusicc.zon.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


/**
 * The Class MantenimientoZONSeccionesNoAptasSearchForm.
 *
 * @author Belcorp
 * @version 1.0
 * 15/01/2015
 */
public class MantenimientoZONSeccionesNoAptasSearchForm extends BaseSearchForm {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The codigo pais. */
	private String codigoPais;
    
    /** The codigo region. */
    private String codigoRegion;
    
    /** The codigo zona. */
    private String codigoZona;
    
	/**
	 * Gets the codigo pais.
	 *
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * Sets the codigo pais.
	 *
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	/**
	 * Gets the codigo region.
	 *
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * Sets the codigo region.
	 *
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * Gets the codigo zona.
	 *
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * Sets the codigo zona.
	 *
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
}