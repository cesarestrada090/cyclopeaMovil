package biz.belcorp.ssicc.web.spusicc.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * The Class MantenimientoPERNumeracionComprobantesSunatSearchForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 14/01/2015
 */
public class MantenimientoPERNumeracionComprobantesSunatSearchForm extends BaseSearchForm {

	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoAutorizacion;
	private String numeroAutorizacion;
	private String serieDocumento;
	private String numeroComprobanteInicial;
	private String numeroComprobanteFinal;
	
	
	/**
	 * @return the codigoAutorizacion
	 */
	public String getCodigoAutorizacion() {
		return codigoAutorizacion;
	}
	/**
	 * @param codigoAutorizacion the codigoAutorizacion to set
	 */
	public void setCodigoAutorizacion(String codigoAutorizacion) {
		this.codigoAutorizacion = codigoAutorizacion;
	}
	/**
	 * @return the numeroAutorizacion
	 */
	public String getNumeroAutorizacion() {
		return numeroAutorizacion;
	}
	/**
	 * @param numeroAutorizacion the numeroAutorizacion to set
	 */
	public void setNumeroAutorizacion(String numeroAutorizacion) {
		this.numeroAutorizacion = numeroAutorizacion;
	}
	/**
	 * @return the serieDocumento
	 */
	public String getSerieDocumento() {
		return serieDocumento;
	}
	/**
	 * @param serieDocumento the serieDocumento to set
	 */
	public void setSerieDocumento(String serieDocumento) {
		this.serieDocumento = serieDocumento;
	}
	/**
	 * @return the numeroComprobanteInicial
	 */
	public String getNumeroComprobanteInicial() {
		return numeroComprobanteInicial;
	}
	/**
	 * @param numeroComprobanteInicial the numeroComprobanteInicial to set
	 */
	public void setNumeroComprobanteInicial(String numeroComprobanteInicial) {
		this.numeroComprobanteInicial = numeroComprobanteInicial;
	}
	/**
	 * @return the numeroComprobanteFinal
	 */
	public String getNumeroComprobanteFinal() {
		return numeroComprobanteFinal;
	}
	/**
	 * @param numeroComprobanteFinal the numeroComprobanteFinal to set
	 */
	public void setNumeroComprobanteFinal(String numeroComprobanteFinal) {
		this.numeroComprobanteFinal = numeroComprobanteFinal;
	}
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais The codigoPais to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	

}
