package biz.belcorp.ssicc.web.spusicc.form;

import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * The Class MantenimientoPERNumeracionComprobantesSunatForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 14/01/2015
 */
public class MantenimientoPERNumeracionComprobantesSunatForm extends BaseEditForm {

	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String correlativoAutorizacion;
	private String codigoAutorizacion;
	private String fechaAutorizacion;
	private Date fechaAutorizacionD;
	private String numeroAutorizacion;
	private String serieDocumento;
	private String numeroComprobanteInicial;
	private String codigoVigencia;
	private String indicadorVigencia;
	private boolean indicadorVigenciaB;
	
	/**
	 * @return the correlativoAutorizacion
	 */
	public String getCorrelativoAutorizacion() {
		return correlativoAutorizacion;
	}
	/**
	 * @param correlativoAutorizacion the correlativoAutorizacion to set
	 */
	public void setCorrelativoAutorizacion(String correlativoAutorizacion) {
		this.correlativoAutorizacion = correlativoAutorizacion;
	}
	
	/**
	 * @return the codigoVigencia
	 */
	public String getCodigoVigencia() {
		return codigoVigencia;
	}
	/**
	 * @param codigoVigencia the codigoVigencia to set
	 *
	 */
	public void setCodigoVigencia(String codigoVigencia) {
		this.codigoVigencia = codigoVigencia;
	}
	/**
	 * @return the indicadorVigencia
	 */
	public String getIndicadorVigencia() {
		return indicadorVigencia;
	}
	/**
	 * @param indicadorVigencia the indicadorVigencia to set
	 */
	public void setIndicadorVigencia(String indicadorVigencia) {
		this.indicadorVigencia = indicadorVigencia;
	}
	/**
	 * @return the codigoAutorizacion
	 */
	public String getCodigoAutorizacion() {
		return codigoAutorizacion;
	}
	/**
	 * @param codigoAutorizacion the codigoAutorizacion to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoAutorizacion(String codigoAutorizacion) {
		this.codigoAutorizacion = codigoAutorizacion;
	}
	/**
	 * @return the fechaAutorizacion
	 */
	public String getFechaAutorizacion() {
		return fechaAutorizacion;
	}
	/**
	 * @param fechaAutorizacion the fechaAutorizacion to set
	 * @struts.validator type = "required"
	 */
	public void setFechaAutorizacion(String fechaAutorizacion) {
		this.fechaAutorizacion = fechaAutorizacion;
	}	

	/**
	 * @return the serieDocumento
	 */
	public String getSerieDocumento() {
		return serieDocumento;
	}
	/**
	 * @param serieDocumento the serieDocumento to set
	 * @struts.validator type = "required"
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
	 * @struts.validator type = "required"
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
	 * @struts.validator type = "required"
	 */
	public void setNumeroComprobanteFinal(String numeroComprobanteFinal) {
		this.numeroComprobanteFinal = numeroComprobanteFinal;
	}

	private String numeroComprobanteFinal;
	
	
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
	
	/**
	 * @return the numeroAutorizacion
	 */
	public String getNumeroAutorizacion() {
		return numeroAutorizacion;
	}
	/**
	 * @param numeroAutorizacion the numeroAutorizacion to set
	 * @struts.validator type = "required"
	 */
	public void setNumeroAutorizacion(String numeroAutorizacion) {
		this.numeroAutorizacion = numeroAutorizacion;
	}
	/**
	 * @return the fechaAutorizacionD
	 */
	public Date getFechaAutorizacionD() {
		return fechaAutorizacionD;
	}
	/**
	 * @param fechaAutorizacionD the fechaAutorizacionD to set
	 */
	public void setFechaAutorizacionD(Date fechaAutorizacionD) {
		this.fechaAutorizacionD = fechaAutorizacionD;
	}
	/**
	 * @return the indicadorVigenciaB
	 */
	public boolean isIndicadorVigenciaB() {
		return indicadorVigenciaB;
	}
	/**
	 * @param indicadorVigenciaB the indicadorVigenciaB to set
	 */
	public void setIndicadorVigenciaB(boolean indicadorVigenciaB) {
		this.indicadorVigenciaB = indicadorVigenciaB;
	}
	
}
