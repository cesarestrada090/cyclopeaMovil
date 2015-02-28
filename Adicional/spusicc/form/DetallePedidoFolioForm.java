/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * @author tokkto
 *
 */

public class DetallePedidoFolioForm extends BaseSearchForm{
	
	private String codigoCliente;
	private String codigoPeri;
	private String valorNumericoSolicitud;
	private String fechaProc;
	private String valorCodigoVenta;
	private String valorDescripcionProducto;
	private String numeroUnidad;
	private String valorPrecioCatalogo;
	
	private boolean ckhVerDatos; 
	
	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}
	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	/**
	 * @return the codigoPeri
	 */
	public String getCodigoPeri() {
		return codigoPeri;
	}
	/**
	 * @param codigoPeri the codigoPeri to set
	 */
	public void setCodigoPeri(String codigoPeri) {
		this.codigoPeri = codigoPeri;
	}
	/**
	 * @return the valorNumericoSolicitud
	 */
	public String getValorNumericoSolicitud() {
		return valorNumericoSolicitud;
	}
	/**
	 * @param valorNumericoSolicitud the valorNumericoSolicitud to set
	 */
	public void setValorNumericoSolicitud(String valorNumericoSolicitud) {
		this.valorNumericoSolicitud = valorNumericoSolicitud;
	}
	/**
	 * @return the fechaProc
	 */
	public String getFechaProc() {
		return fechaProc;
	}
	/**
	 * @param fechaProc the fechaProc to set
	 */
	public void setFechaProc(String fechaProc) {
		this.fechaProc = fechaProc;
	}
	/**
	 * @return the valorCodigoVenta
	 */
	public String getValorCodigoVenta() {
		return valorCodigoVenta;
	}
	/**
	 * @param valorCodigoVenta the valorCodigoVenta to set
	 */
	public void setValorCodigoVenta(String valorCodigoVenta) {
		this.valorCodigoVenta = valorCodigoVenta;
	}
	/**
	 * @return the valorDescripcionProducto
	 */
	public String getValorDescripcionProducto() {
		return valorDescripcionProducto;
	}
	/**
	 * @param valorDescripcionProducto the valorDescripcionProducto to set
	 */
	public void setValorDescripcionProducto(String valorDescripcionProducto) {
		this.valorDescripcionProducto = valorDescripcionProducto;
	}
	/**
	 * @return the numeroUnidad
	 */
	public String getNumeroUnidad() {
		return numeroUnidad;
	}
	/**
	 * @param numeroUnidad the numeroUnidad to set
	 */
	public void setNumeroUnidad(String numeroUnidad) {
		this.numeroUnidad = numeroUnidad;
	}
	/**
	 * @return the valorPrecioCatalogo
	 */
	public String getValorPrecioCatalogo() {
		return valorPrecioCatalogo;
	}
	/**
	 * @param valorPrecioCatalogo the valorPrecioCatalogo to set
	 */
	public void setValorPrecioCatalogo(String valorPrecioCatalogo) {
		this.valorPrecioCatalogo = valorPrecioCatalogo;
	}
	/**
	 * @return the ckhVerDatos
	 */
	public boolean isCkhVerDatos() {
		return ckhVerDatos;
	}
	/**
	 * @param ckhVerDatos the ckhVerDatos to set
	 */
	public void setCkhVerDatos(boolean ckhVerDatos) {
		this.ckhVerDatos = ckhVerDatos;
	}
		
}
