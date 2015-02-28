package biz.belcorp.ssicc.web.spusicc.men.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;


public class MantenimientoMENEscaleraGananciaForm extends BaseEditForm {

	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoEscalon;
	private String rangoInicial;
	private String rangoFinal;
	private String codigoMensaje;
	private String oidMensaje;
	private String indicadorActivo;
	
	
	/* (non-Javadoc)
	 * @see org.apache.struts.validator.ValidatorForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */

	
	/**
	 * @return
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 * @struts.validator type="required"
	 */  
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoEscalon
	 */
	public String getCodigoEscalon() {
		return codigoEscalon;
	}

	/**
	 * @param codigoEscalon the codigoEscalon to set
	 */
	public void setCodigoEscalon(String codigoEscalon) {
		this.codigoEscalon = codigoEscalon;
	}

	/**
	 * @return the rangoInicial
	 */
	public String getRangoInicial() {
		return rangoInicial;
	}

	/**
	 * @param rangoInicial the rangoInicial to set
	 *  @struts.validator type="required"
	 */
	public void setRangoInicial(String rangoInicial) {
		this.rangoInicial = rangoInicial;
	}

	/**
	 * @return the rangoFinal
	 */
	public String getRangoFinal() {
		return rangoFinal;
	}

	/**
	 * @param rangoFinal the rangoFinal to set
	 *  @struts.validator type="required"
	 */
	public void setRangoFinal(String rangoFinal) {
		this.rangoFinal = rangoFinal;
	}

	/**
	 * @return the codigoMensaje
	 */
	public String getCodigoMensaje() {
		return codigoMensaje;
	}

	/**
	 * @param codigoMensaje the codigoMensaje to set
	 * 
	 */
	public void setCodigoMensaje(String codigoMensaje) {
		this.codigoMensaje = codigoMensaje;
	}
	
	/**
	 * @return the oidMensaje
	 */
	public String getOidMensaje() {
		return oidMensaje;
	}

	/**
	 * @param oidMensaje the oidMensaje to set
	 *  @struts.validator type="required"
	 */
	public void setOidMensaje(String oidMensaje) {
		this.oidMensaje = oidMensaje;
	}

	/**
	 * @return the indicadorActivo
	 */
	public String getIndicadorActivo() {
		return indicadorActivo;
	}

	/**
	 * @param indicadorActivo the indicadorActivo to set
	 *  @struts.validator type="required"
	 */
	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}
	

	
}
