package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class BusquedaRECCodigoVentaMatrizForm extends BaseSearchForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String codigoPeriodo;
    private String codigoVenta;
    private String codigoSAP;
    private String descripcion;
    private String matriz;
    private String numeroCruce;
    private String indice;
    private String cajaTexto;
    
    private String precioInicial;
    private String precioFinal;
    
	public String getCodigoVenta() {
		return codigoVenta;
	}
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}
	public String getCodigoSAP() {
		return codigoSAP;
	}
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	public String getMatriz() {
		return matriz;
	}
	public void setMatriz(String matriz) {
		this.matriz = matriz;
	}
	public String getNumeroCruce() {
		return numeroCruce;
	}
	public void setNumeroCruce(String numeroCruce) {
		this.numeroCruce = numeroCruce;
	}
	/**
	 * @return the indice
	 */
	public String getIndice() {
		return indice;
	}
	/**
	 * @param indice the indice to set
	 */
	public void setIndice(String indice) {
		this.indice = indice;
	}
	/**
	 * @return the cajaTexto
	 */
	public String getCajaTexto() {
		return cajaTexto;
	}
	/**
	 * @param cajaTexto the cajaTexto to set
	 */
	public void setCajaTexto(String cajaTexto) {
		this.cajaTexto = cajaTexto;
	}
	/**
	 * @return the precioInicial
	 */
	public String getPrecioInicial() {
		return precioInicial;
	}
	/**
	 * @param precioInicial the precioInicial to set
	 */
	public void setPrecioInicial(String precioInicial) {
		this.precioInicial = precioInicial;
	}
	/**
	 * @return the precioFinal
	 */
	public String getPrecioFinal() {
		return precioFinal;
	}
	/**
	 * @param precioFinal the precioFinal to set
	 */
	public void setPrecioFinal(String precioFinal) {
		this.precioFinal = precioFinal;
	}
	
}
