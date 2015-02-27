package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoPEDMontoMaximoForm extends BaseEditForm implements Serializable
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
}
