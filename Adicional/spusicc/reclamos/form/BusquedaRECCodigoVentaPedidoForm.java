package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


/**
 * The Class BusquedaRECCodigoVentaPedidoForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 06/12/2013
 */
public class BusquedaRECCodigoVentaPedidoForm extends BaseSearchForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String codigoPais;
    
	private String codigoVenta;
    private String codigoSAP;
    private String descripcion;
    private String numeroCruce;
    private String indice;
    private String cajaTexto;
    private String numPedido;
    private String codConsejera;
    
	/**
	 * @return codigoVenta
	 */
	public String getCodigoVenta() {
		return codigoVenta;
	}
	
	/**
	 * @param codigoVenta
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}
	
	/**
	 * @return codigoSAP
	 */
	public String getCodigoSAP() {
		return codigoSAP;
	}
	
	/**
	 * @param codigoSAP
	 */
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}
	
	/**
	 * @return descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * @return numeroCruce
	 */
	public String getNumeroCruce() {
		return numeroCruce;
	}
	
	/**
	 * @param numeroCruce
	 */
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
	 * @return numPedido
	 */
	public String getNumPedido() {
		return numPedido;
	}

	/**
	 * @param numPedido
	 */
	public void setNumPedido(String numPedido) {
		this.numPedido = numPedido;
	}

	/**
	 * @return codConsejera
	 */ 
	public String getCodConsejera() {
		return codConsejera;
	}

	/**
	 * @param codConsejera
	 */
	public void setCodConsejera(String codConsejera) {
		this.codConsejera = codConsejera;
	}
	
}
