package biz.belcorp.ssicc.web.spusicc.ape.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoAPETiposCajaEmbalajeSearchForm extends BaseSearchForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoTipoCaja;
	private String descripcionTipoCaja;
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the codigoTipoCaja
	 */
	public String getCodigoTipoCaja() {
		return codigoTipoCaja;
	}
	/**
	 * @param codigoTipoCaja the codigoTipoCaja to set
	 */
	public void setCodigoTipoCaja(String codigoTipoCaja) {
		this.codigoTipoCaja = codigoTipoCaja;
	}
	/**
	 * @return the descripcionTipoCaja
	 */
	public String getDescripcionTipoCaja() {
		return descripcionTipoCaja;
	}
	/**
	 * @param descripcionTipoCaja the descripcionTipoCaja to set
	 */
	public void setDescripcionTipoCaja(String descripcionTipoCaja) {
		this.descripcionTipoCaja = descripcionTipoCaja;
	}
	
	
}
