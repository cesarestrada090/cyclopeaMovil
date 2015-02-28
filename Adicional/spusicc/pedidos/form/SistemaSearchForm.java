/*
 * Created on 21-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class SistemaSearchForm extends BaseSearchForm implements Serializable {

	private static final long serialVersionUID = 1L;
	protected String codigoPais;
	protected String codigoSistema;
	protected String descripcionPais;
	protected String descripcionSistema;
	
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
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
	 * @return Returns the descripcionPais.
	 */
	public String getDescripcionPais() {
		return descripcionPais;
	}
	/**
	 * @param descripcionPais The descripcionPais to set.
	 */
	public void setDescripcionPais(String descripcionPais) {
		this.descripcionPais = descripcionPais;
	}
	/**
	 * @return Returns the descripcionSistema.
	 */
	public String getDescripcionSistema() {
		return descripcionSistema;
	}
	/**
	 * @param descripcionSistema The descripcionSistema to set.
	 */
	public void setDescripcionSistema(String descripcionSistema) {
		this.descripcionSistema = descripcionSistema;
	}	
}