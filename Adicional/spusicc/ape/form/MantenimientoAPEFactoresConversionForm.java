package biz.belcorp.ssicc.web.spusicc.ape.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoAPEFactoresConversionForm extends BaseEditForm{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoMagn;
	private String codigoUnidMediOrig;
	private String codigoUnidMediDest;
	private String factor;
	private String oidFactorConv;
	private String flagEdicion;
	private boolean activo;
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
	 * @return the codigoMagn
	 */
	public String getCodigoMagn() {
		return codigoMagn;
	}
	/**
	 * @param codigoMagn the codigoMagn to set
	 */
	public void setCodigoMagn(String codigoMagn) {
		this.codigoMagn = codigoMagn;
	}
	/**
	 * @return the codigoUnidMediOrig
	 */
	public String getCodigoUnidMediOrig() {
		return codigoUnidMediOrig;
	}
	/**
	 * @param codigoUnidMediOrig the codigoUnidMediOrig to set
	 */
	public void setCodigoUnidMediOrig(String codigoUnidMediOrig) {
		this.codigoUnidMediOrig = codigoUnidMediOrig;
	}
	/**
	 * @return the codigoUnidMediDest
	 */
	public String getCodigoUnidMediDest() {
		return codigoUnidMediDest;
	}
	/**
	 * @param codigoUnidMediDest the codigoUnidMediDest to set
	 */
	public void setCodigoUnidMediDest(String codigoUnidMediDest) {
		this.codigoUnidMediDest = codigoUnidMediDest;
	}
	/**
	 * @return the factor
	 */
	public String getFactor() {
		return factor;
	}
	/**
	 * @param factor the factor to set
	 */
	public void setFactor(String factor) {
		this.factor = factor;
	}
	/**
	 * @return the oidFactorConv
	 */
	public String getOidFactorConv() {
		return oidFactorConv;
	}
	/**
	 * @param oidFactorConv the oidFactorConv to set
	 */
	public void setOidFactorConv(String oidFactorConv) {
		this.oidFactorConv = oidFactorConv;
	}
	/**
	 * @return the flagEdicion
	 */
	public String getFlagEdicion() {
		return flagEdicion;
	}
	/**
	 * @param flagEdicion the flagEdicion to set
	 */
	public void setFlagEdicion(String flagEdicion) {
		this.flagEdicion = flagEdicion;
	}
	/**
	 * @return the activo
	 */
	public boolean isActivo() {
		return activo;
	}
	/**
	 * @param activo the activo to set
	 */
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	
}
