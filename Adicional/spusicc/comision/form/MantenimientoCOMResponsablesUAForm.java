package biz.belcorp.ssicc.web.spusicc.comision.form;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoCOMResponsablesUAForm extends BaseEditForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3160502340954638803L;
	private String codigoPais;
	private String codigoMarca;
	private String codigoCanal;
	private Integer oidHistGere;
	private String ua;
	private String codigoPeriodoDesde;
	private String codigoPeriodoHasta;
	private String codigoResponsable;
	private String codigoResponsableNoEditado;
	private String longitudCodigoResponsable;
	private String oidPais;

	public MantenimientoCOMResponsablesUAForm() {
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
		this.oidHistGere = null;
		this.ua = null;
		this.codigoPeriodoDesde = null;
		this.codigoPeriodoHasta = null;
		this.codigoResponsable = null;
		this.codigoResponsableNoEditado = null;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
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
	 * @param codigoMarca
	 *            the codigoMarca to set
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
	 * @param codigoCanal
	 *            the codigoCanal to set
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return the oidHistGere
	 */
	public Integer getOidHistGere() {
		return oidHistGere;
	}

	/**
	 * @param oidHistGere
	 *            the oidHistGere to set
	 */
	public void setOidHistGere(Integer oidHistGere) {
		this.oidHistGere = oidHistGere;
	}

	/**
	 * @return the ua
	 */
	public String getUa() {
		return ua;
	}

	/**
	 * @param ua
	 *            the ua to set
	 */
	public void setUa(String ua) {
		this.ua = ua;
	}

	/**
	 * @return the codigoPeriodoDesde
	 */
	public String getCodigoPeriodoDesde() {
		return codigoPeriodoDesde;
	}

	/**
	 * @param codigoPeriodoDesde
	 *            the codigoPeriodoDesde to set
	 */
	public void setCodigoPeriodoDesde(String codigoPeriodoDesde) {
		this.codigoPeriodoDesde = codigoPeriodoDesde;
	}

	/**
	 * @return the codigoPeriodoHasta
	 */
	public String getCodigoPeriodoHasta() {
		return codigoPeriodoHasta;
	}

	/**
	 * @param codigoPeriodoHasta
	 *            the codigoPeriodoHasta to set
	 */
	public void setCodigoPeriodoHasta(String codigoPeriodoHasta) {
		this.codigoPeriodoHasta = codigoPeriodoHasta;
	}

	/**
	 * @return the codigoResponsable
	 */
	public String getCodigoResponsable() {
		return codigoResponsable;
	}

	/**
	 * @param codigoResponsable
	 *            the codigoResponsable to set
	 */
	public void setCodigoResponsable(String codigoResponsable) {
		this.codigoResponsable = codigoResponsable;
	}

	/**
	 * @return the codigoResponsableNoEditado
	 */
	public String getCodigoResponsableNoEditado() {
		return codigoResponsableNoEditado;
	}

	/**
	 * @param codigoResponsableNoEditado
	 *            the codigoResponsableNoEditado to set
	 */
	public void setCodigoResponsableNoEditado(String codigoResponsableNoEditado) {
		this.codigoResponsableNoEditado = codigoResponsableNoEditado;
	}

	/**
	 * @return the longitudCodigoResponsable
	 */
	public String getLongitudCodigoResponsable() {
		return longitudCodigoResponsable;
	}

	/**
	 * @param longitudCodigoResponsable
	 *            the longitudCodigoResponsable to set
	 */
	public void setLongitudCodigoResponsable(String longitudCodigoResponsable) {
		this.longitudCodigoResponsable = longitudCodigoResponsable;
	}

	/**
	 * @return the oidPais
	 */
	public String getOidPais() {
		return oidPais;
	}

	/**
	 * @param oidPais
	 *            the oidPais to set
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}

}
