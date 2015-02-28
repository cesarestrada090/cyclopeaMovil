package biz.belcorp.ssicc.web.spusicc.cobranzas.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * The Class MantenimientoCOBValidacionAsignacionCarteraForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 20/01/2015
 */
public class MantenimientoCOBValidacionAsignacionCarteraForm extends BaseEditForm {
	
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoEtapaDeuda;	
	private String codigoTipoValidacion;
	private String numeroOrdenValidacion;	
	private String valorNombrePrograma;
	private String valorMensajeValidacion;
	private String indicadorActivo;	
	private boolean indicadorActivoB;	
	

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
	 * @return the codigoTipoValidacion
	 */
	public String getCodigoTipoValidacion() {
		return codigoTipoValidacion;
	}



	/**
	 * @param codigoTipoValidacion the codigoTipoValidacion to set
	 */
	public void setCodigoTipoValidacion(String codigoTipoValidacion) {
		this.codigoTipoValidacion = codigoTipoValidacion;
	}



	/**
	 * @return the numeroOrdenValidacion
	 */
	public String getNumeroOrdenValidacion() {
		return numeroOrdenValidacion;
	}



	/**
	 * @param numeroOrdenValidacion the numeroOrdenValidacion to set
	 */
	public void setNumeroOrdenValidacion(String numeroOrdenValidacion) {
		this.numeroOrdenValidacion = numeroOrdenValidacion;
	}



	/**
	 * @return the valorNombrePrograma
	 */
	public String getValorNombrePrograma() {
		return valorNombrePrograma;
	}



	/**
	 * @param valorNombrePrograma the valorNombrePrograma to set
	 */
	public void setValorNombrePrograma(String valorNombrePrograma) {
		this.valorNombrePrograma = valorNombrePrograma;
	}



	/**
	 * @return the valorMensajeValidacion
	 */
	public String getValorMensajeValidacion() {
		return valorMensajeValidacion;
	}



	/**
	 * @param valorMensajeValidacion the valorMensajeValidacion to set
	 */
	public void setValorMensajeValidacion(String valorMensajeValidacion) {
		this.valorMensajeValidacion = valorMensajeValidacion;
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
	 * @return the indicadorActivoB
	 */
	public boolean isIndicadorActivoB() {
		return indicadorActivoB;
	}



	/**
	 * @param indicadorActivoB the indicadorActivoB to set
	 */
	public void setIndicadorActivoB(boolean indicadorActivoB) {
		this.indicadorActivoB = indicadorActivoB;
	}
	
}
