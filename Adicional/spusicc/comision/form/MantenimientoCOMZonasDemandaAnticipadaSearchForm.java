package biz.belcorp.ssicc.web.spusicc.comision.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


/**
 * The Class MantenimientoCOMZonasDemandaAnticipadaSearchForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 08/01/2015
 */
public class MantenimientoCOMZonasDemandaAnticipadaSearchForm extends BaseSearchForm {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	
	private String codigoZona;
		
	private String descripcionZona;
	
	private String estado;

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
	 * @return the descripcionZona
	 */
	public String getDescripcionZona() {
		return descripcionZona;
	}

	/**
	 * @param descripcionZona the descripcionZona to set
	 */
	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
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
