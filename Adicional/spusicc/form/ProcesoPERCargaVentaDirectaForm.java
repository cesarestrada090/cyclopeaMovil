package biz.belcorp.ssicc.web.spusicc.form;

import java.util.Date;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * The Class ProcesoPERCargaVentaDirectaForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 05/01/2015
 */
public class ProcesoPERCargaVentaDirectaForm extends BaseProcesoForm {
	
	private static final long serialVersionUID = 1L;
	private String codigoSistema;
	private String codigoInterfaz;	
	private String fechaInicial;
	private Date fechaInicialD;
	private String fechaFinal;
	private Date fechaFinalD;
	private String fechaCorte;
	private Date fechaCorteD;

	/**
	 * @return Returns the fechaCorte.
	 */
	public String getFechaCorte() {
		return fechaCorte;
	}

	/**
	 * @param fechaCorte The fechaCorte to set.
	 * @struts.validator type="required"
	 * @struts.validator type="date"
	 * @struts.validator-var name="datePatternStrict"
	 *                       value="${defaultDatePattern}"
	 */
	public void setFechaCorte(String fechaCorte) {
		this.fechaCorte = fechaCorte;
	}

	/**
	 * @return Returns the fechaFinal.
	 */
	public String getFechaFinal() {
		return fechaFinal;
	}

	/**
	 * @param fechaFinal The fechaFinal to set.
	 * @struts.validator type="required"
	 * @struts.validator type="date"
	 * @struts.validator-var name="datePatternStrict"
	 *                       value="${defaultDatePattern}"
	 */
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	/**
	 * @return Returns the fechaInicial.
	 */
	public String getFechaInicial() {
		return fechaInicial;
	}
	
	/**
	 * @return Returns the codigoInterfaz.
	 */
	public String getCodigoInterfaz() {
		return codigoInterfaz;
	}

	/**
	 * @param codigoInterfaz The codigoInterfaz to set.
	 */
	public void setCodigoInterfaz(String codigoInterfaz) {
		this.codigoInterfaz = codigoInterfaz;
	}

	/**
	 * @return Returns the codigoSistema.
	 */
	public String getCodigoSistema() {
		return codigoSistema;
	}

	/**
	 * @param codigoSistema The codigoSistema to set.
	 */
	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}

	/**
	 * @param fechaInicial The fechaInicial to set.
	 * @struts.validator type="required"
	 * @struts.validator type="date"
	 * @struts.validator-var name="datePatternStrict"
	 *                       value="${defaultDatePattern}"
	 */
	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	/**
	 * @return the fechaInicialD
	 */
	public Date getFechaInicialD() {
		return fechaInicialD;
	}

	/**
	 * @param fechaInicialD the fechaInicialD to set
	 */
	public void setFechaInicialD(Date fechaInicialD) {
		this.fechaInicialD = fechaInicialD;
	}

	/**
	 * @return the fechaFinalD
	 */
	public Date getFechaFinalD() {
		return fechaFinalD;
	}

	/**
	 * @param fechaFinalD the fechaFinalD to set
	 */
	public void setFechaFinalD(Date fechaFinalD) {
		this.fechaFinalD = fechaFinalD;
	}

	/**
	 * @return the fechaCorteD
	 */
	public Date getFechaCorteD() {
		return fechaCorteD;
	}

	/**
	 * @param fechaCorteD the fechaCorteD to set
	 */
	public void setFechaCorteD(Date fechaCorteD) {
		this.fechaCorteD = fechaCorteD;
	}

}
