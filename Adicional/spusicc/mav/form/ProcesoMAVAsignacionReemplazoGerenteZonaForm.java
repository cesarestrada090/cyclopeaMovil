package biz.belcorp.ssicc.web.spusicc.mav.form;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * The Class ProcesoMAVAsignacionReemplazoGerenteZonaForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 04/02/2015
 */
public class ProcesoMAVAsignacionReemplazoGerenteZonaForm extends BaseProcesoForm {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoMarca;
	private String codigoCanal;
	private String codigoPeriodo;
	
	private String codigoClienteBuscar;
	private String oidRegion;
	
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
	 * @return the codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}
	
	/**
	 * @param codigoMarca the codigoMarca to set
     * @struts.validator type = "required" 
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	
	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}
	/**
	 * @param codigoCanal the codigoCanal to set
     * @struts.validator type = "required" 
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}
	
	/**
	 * @return the codigoClienteBuscar
	 */
	public String getCodigoClienteBuscar() {
		return codigoClienteBuscar;
	}
	/**
	 * @param codigoClienteBuscar the codigoClienteBuscar to set
	 */
	public void setCodigoClienteBuscar(String codigoClienteBuscar) {
		this.codigoClienteBuscar = codigoClienteBuscar;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 * @struts.validator type = "required" 
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the oidRegion
	 */
	public String getOidRegion() {
		return oidRegion;
	}

	/**
	 * @param oidRegion the oidRegion to set
	 */
	public void setOidRegion(String oidRegion) {
		this.oidRegion = oidRegion;
	}

}

