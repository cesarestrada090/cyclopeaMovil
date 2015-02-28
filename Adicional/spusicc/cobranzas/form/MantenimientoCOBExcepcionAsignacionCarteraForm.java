package biz.belcorp.ssicc.web.spusicc.cobranzas.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * The Class MantenimientoCOBExcepcionAsignacionCarteraForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 20/01/2015
 */
public class MantenimientoCOBExcepcionAsignacionCarteraForm extends BaseEditForm {
	
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoEtapaDeuda;	
	private String codigoTipoExcepcion;
	private String numeroOrdenExcepcion;	
	private String valorNombrePrograma;
	private String valorMensajeExcepcion;
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
	 * @return the codigoTipoExcepcion
	 */
	public String getCodigoTipoExcepcion() {
		return codigoTipoExcepcion;
	}



	/**
	 * @param codigoTipoExcepcion the codigoTipoExcepcion to set
	 */
	public void setCodigoTipoExcepcion(String codigoTipoExcepcion) {
		this.codigoTipoExcepcion = codigoTipoExcepcion;
	}



	/**
	 * @return the numeroOrdenExcepcion
	 */
	public String getNumeroOrdenExcepcion() {
		return numeroOrdenExcepcion;
	}



	/**
	 * @param numeroOrdenExcepcion the numeroOrdenExcepcion to set
	 */
	public void setNumeroOrdenExcepcion(String numeroOrdenExcepcion) {
		this.numeroOrdenExcepcion = numeroOrdenExcepcion;
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
	 * @return the valorMensajeExcepcion
	 */
	public String getValorMensajeExcepcion() {
		return valorMensajeExcepcion;
	}



	/**
	 * @param valorMensajeExcepcion the valorMensajeExcepcion to set
	 */
	public void setValorMensajeExcepcion(String valorMensajeExcepcion) {
		this.valorMensajeExcepcion = valorMensajeExcepcion;
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
