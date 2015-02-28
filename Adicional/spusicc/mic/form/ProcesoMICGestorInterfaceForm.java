package biz.belcorp.ssicc.web.spusicc.mic.form;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * The Class ProcesoMICGestorInterfaceForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 23/12/2014
 */
public class ProcesoMICGestorInterfaceForm extends BaseProcesoForm {

	private static final long serialVersionUID = 1L;

	private String codigoMarca; 
	
    private String codigoCanal;

	private String codigoPeriodo;
	
	private String fechaFacturacion;
	
	private String tipoProceso;
	
	private String numeroEjecucionEnvios;
	
	
    /**
	 * @return the numeroEjecucionEnvios
	 */
	public String getNumeroEjecucionEnvios() {
		return numeroEjecucionEnvios;
	}

	/**
	 * @param numeroEjecucionEnvios the numeroEjecucionEnvios to set
	 */
	public void setNumeroEjecucionEnvios(String numeroEjecucionEnvios) {
		this.numeroEjecucionEnvios = numeroEjecucionEnvios;
	}

	/**
	 * @return the tipoProceso
	 */
	public String getTipoProceso() {
		return tipoProceso;
	}

	/**
	 * @param tipoProceso the tipoProceso to set
	 * @struts.validator type = "required"
	 */
	public void setTipoProceso(String tipoProceso) {
		this.tipoProceso = tipoProceso;
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
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}

	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 * @struts.validator type = "required"
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

}
