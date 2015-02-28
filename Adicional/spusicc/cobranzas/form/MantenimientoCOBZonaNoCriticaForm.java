package biz.belcorp.ssicc.web.spusicc.cobranzas.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoCOBZonaNoCriticaForm extends BaseEditForm implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String codigoPais;
	
	private String codigoEtapaDeuda;
	
	private String codigoRegion;
	
	private String codigoZona;
	
	private String indicadorActivo;
	
	private boolean indicadorActivoBool;
	
	private String observaciones;
	
	private String codigoZonaSeleccionado;
	
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
	 * @return the codigoEtapaDeuda
	 */
	public String getCodigoEtapaDeuda() {
		return codigoEtapaDeuda;
	}



	/**
	 * @param codigoEtapaDeuda the codigoEtapaDeuda to set
	 */
	public void setCodigoEtapaDeuda(String codigoEtapaDeuda) {
		this.codigoEtapaDeuda = codigoEtapaDeuda;
	}



	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}



	/**
	 * @param codigoRegion the codigoRegion to set
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
	 * @return the indicadorActivo
	 */
	public String getIndicadorActivo() {
		return indicadorActivo;
	}



	/**
	 * @param indicadorActivo the indicadorActivo to set
	 */
	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}



	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}



	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}



	/**
	 * @return the codigoZonaSeleccionado
	 */
	public String getCodigoZonaSeleccionado() {
		return codigoZonaSeleccionado;
	}



	/**
	 * @param codigoZonaSeleccionado the codigoZonaSeleccionado to set
	 */
	public void setCodigoZonaSeleccionado(String codigoZonaSeleccionado) {
		this.codigoZonaSeleccionado = codigoZonaSeleccionado;
	}



	public boolean isIndicadorActivoBool() {
		return indicadorActivoBool;
	}



	public void setIndicadorActivoBool(boolean indicadorActivoBool) {
		this.indicadorActivoBool = indicadorActivoBool;
	}

	

}
