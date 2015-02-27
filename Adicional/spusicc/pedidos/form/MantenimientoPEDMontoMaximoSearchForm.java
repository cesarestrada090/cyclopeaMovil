package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.spusicc.pedidos.model.CarParamCarte;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoPEDMontoMaximoSearchForm extends BaseSearchForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoNivelRiesgo;
	private String[] codigoRegion;
	private String[] codigoZona;
	private String[] codigoSeccion;
	private String montoMaximo;
	
	private String subgerenciaVenta;
	private String oidParaCart;
	private String niriOidNiveRies;
	private String valI18n;
	private String valMontMaxiPerm;
	private String indMontoMaximo;
	
	protected CarParamCarte[] carParamCarteForm;
	
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoNivelRiesgo() {
		return codigoNivelRiesgo;
	}

	/**
	 * @param codigoNivelRiesgo
	 * @struts.validator type="required"
	 */
	public void setCodigoNivelRiesgo(String codigoNivelRiesgo) {
		this.codigoNivelRiesgo = codigoNivelRiesgo;
	}

	public String[] getCodigoRegion() {
		return codigoRegion;
	}

	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	public String[] getCodigoZona() {
		return codigoZona;
	}

	public void setCodigoZona(String[] codigoZona) {
		this.codigoZona = codigoZona;
	}

	public String[] getCodigoSeccion() {
		return codigoSeccion;
	}

	public void setCodigoSeccion(String[] codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}

	public String getMontoMaximo() {
		return montoMaximo;
	}

	/**
	 * @param montoMaximo
	 * @struts.validator type="required"
	 */
	public void setMontoMaximo(String montoMaximo) {
		this.montoMaximo = montoMaximo;
	}

	/**
	 * @return the subgerenciaVenta
	 */
	public String getSubgerenciaVenta() {
		return subgerenciaVenta;
	}

	/**
	 * @param subgerenciaVenta the subgerenciaVenta to set
	 */
	public void setSubgerenciaVenta(String subgerenciaVenta) {
		this.subgerenciaVenta = subgerenciaVenta;
	}

	/**
	 * @return the oidParaCart
	 */
	public String getOidParaCart() {
		return oidParaCart;
	}

	/**
	 * @param oidParaCart the oidParaCart to set
	 */
	public void setOidParaCart(String oidParaCart) {
		this.oidParaCart = oidParaCart;
	}

	/**
	 * @return the niriOidNiveRies
	 */
	public String getNiriOidNiveRies() {
		return niriOidNiveRies;
	}

	/**
	 * @param niriOidNiveRies the niriOidNiveRies to set
	 */
	public void setNiriOidNiveRies(String niriOidNiveRies) {
		this.niriOidNiveRies = niriOidNiveRies;
	}

	/**
	 * @return the valI18n
	 */
	public String getValI18n() {
		return valI18n;
	}

	/**
	 * @param valI18n the valI18n to set
	 */
	public void setValI18n(String valI18n) {
		this.valI18n = valI18n;
	}

	/**
	 * @return the valMontMaxiPerm
	 */
	public String getValMontMaxiPerm() {
		return valMontMaxiPerm;
	}

	/**
	 * @param valMontMaxiPerm the valMontMaxiPerm to set
	 */
	public void setValMontMaxiPerm(String valMontMaxiPerm) {
		this.valMontMaxiPerm = valMontMaxiPerm;
	}

	/**
	 * @return the indMontoMaximo
	 */
	public String getIndMontoMaximo() {
		return indMontoMaximo;
	}

	/**
	 * @param indMontoMaximo the indMontoMaximo to set
	 */
	public void setIndMontoMaximo(String indMontoMaximo) {
		this.indMontoMaximo = indMontoMaximo;
	}

	public CarParamCarte[] getCarParamCarteForm() {
		return carParamCarteForm;
	}

	public void setCarParamCarteForm(CarParamCarte[] carParamCarteForm) {
		this.carParamCarteForm = carParamCarteForm;
	}
}
