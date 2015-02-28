/*
 * Created on 21-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.spusicc.lideres.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoLIDProductosCanastaForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 */

public class MantenimientoLIDProductosCanastaForm extends BaseEditForm {

	private static final long serialVersionUID = 1L;
	private String codigoPeriodo;
	private String codigoPais;
	private String codigoSap;
	private String codigoOferta;
	private String codigoVenta;
	private String numUnidades;
	private String precioContable;
	private String descripcionCorta;
	private String indicadorActivo;	
	
	private Boolean indicadorActivoBool;
	
	/**
	 * @return the indicadorActivo
	 */
	public String getIndicadorActivo() {
		return indicadorActivo;
	}
	/**
	 * @param indicadorActivo the indicadorActivo to set
	 */
	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @return the codigoVenta
	 */
	public String getCodigoVenta() {
		return codigoVenta;
	}
	/**
	 * @param codigoVenta the codigoVenta to set
	 * 
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return the descripcionCorta
	 */
	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	/**
	 * @param descripcionCorta the descripcionCorta to set
	 */
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}
	/**
	 * @return the codigoSap
	 */
	public String getCodigoSap() {
		return codigoSap;
	}
	/**
	 * @param codigoSap the codigoSap to set
	 */
	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}
	/**
	 * @return the codigoOferta
	 */
	public String getCodigoOferta() {
		return codigoOferta;
	}
	/**
	 * @param codigoTipoOferta the codigoOferta to set
	 */
	public void setCodigoOferta(String codigoOferta) {
		this.codigoOferta = codigoOferta;
	}
	/**
	 * @return the numUnidades
	 */
	public String getNumUnidades() {
		return numUnidades;
	}
	/**
	 * @param numUnidades the numUnidades to set
	 */
	public void setNumUnidades(String numUnidades) {
		this.numUnidades = numUnidades;
	}
	/**
	 * @return the precioContable
	 */
	public String getPrecioContable() {
		return precioContable;
	}
	/**
	 * @param precioContable the precioContable to set
	 */
	public void setPrecioContable(String precioContable) {
		this.precioContable = precioContable;
	}
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
	 * @return the indicadorActivoBool
	 */
	public Boolean getIndicadorActivoBool() {
		return indicadorActivoBool;
	}
	/**
	 * @param indicadorActivoBool the indicadorActivoBool to set
	 */
	public void setIndicadorActivoBool(Boolean indicadorActivoBool) {
		this.indicadorActivoBool = indicadorActivoBool;
	}

}
