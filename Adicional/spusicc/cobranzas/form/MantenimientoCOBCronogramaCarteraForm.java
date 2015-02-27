package biz.belcorp.ssicc.web.spusicc.cobranzas.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoCOBCronogramaCarteraForm extends BaseEditForm
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1049065256373110000L;
	private String codigoPais;
	private String codigoSociedad;
	private String codigoEtapaDeuda;
	private String descriEtapaDeuda;
	private String codigoPeriodo;
	private String codigoRegion;
	private String descriRegion;
	private String codigoZona;
	private String tipoExcepcion;
	private String fechaGeneracion;
	private String fechaGeneracionCartera;
	private String fechaInicioGestion;
	private String fechaCompromisoPago;
	private String fechaCierreCartera;
	private Date fechaGeneracionD;
	private Date fechaGeneracionCarteraD;
	private Date fechaInicioGestionD;
	private Date fechaCompromisoPagoD;
	private Date fechaCierreCarteraD;
	
	
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
	 * @return the codigoSociedad
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}
	/**
	 * @param codigoSociedad the codigoSociedad to set
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}
	/**
	 * @return the codigoEtapaDeuda
	 */
	public String getCodigoEtapaDeuda() {
		return codigoEtapaDeuda;
	}
	/**
	 * @param codigoEtapaDeuda the codigoEtapaDeuda to set
	 */
	public void setCodigoEtapaDeuda(String codigoEtapaDeuda) {
		this.codigoEtapaDeuda = codigoEtapaDeuda;
	}
	/**
	 * @return the descriEtapaDeuda
	 */
	public String getDescriEtapaDeuda() {
		return descriEtapaDeuda;
	}
	/**
	 * @param descriEtapaDeuda the descriEtapaDeuda to set
	 */
	public void setDescriEtapaDeuda(String descriEtapaDeuda) {
		this.descriEtapaDeuda = descriEtapaDeuda;
	}
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	/**
	 * @return the descriRegion
	 */
	public String getDescriRegion() {
		return descriRegion;
	}
	/**
	 * @param descriRegion the descriRegion to set
	 */
	public void setDescriRegion(String descriRegion) {
		this.descriRegion = descriRegion;
	}
	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	/**
	 * @return the tipoExcepcion
	 */
	public String getTipoExcepcion() {
		return tipoExcepcion;
	}
	/**
	 * @param tipoExcepcion the tipoExcepcion to set
	 */
	public void setTipoExcepcion(String tipoExcepcion) {
		this.tipoExcepcion = tipoExcepcion;
	}
	/**
	 * @return the fechaGeneracion
	 */
	public String getFechaGeneracion() {
		return fechaGeneracion;
	}
	/**
	 * @param fechaGeneracion the fechaGeneracion to set
	 */
	public void setFechaGeneracion(String fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}
	/**
	 * @return the fechaGeneracionCartera
	 */
	public String getFechaGeneracionCartera() {
		return fechaGeneracionCartera;
	}
	/**
	 * @param fechaGeneracionCartera the fechaGeneracionCartera to set
	 */
	public void setFechaGeneracionCartera(String fechaGeneracionCartera) {
		this.fechaGeneracionCartera = fechaGeneracionCartera;
	}
	/**
	 * @return the fechaInicioGestion
	 */
	public String getFechaInicioGestion() {
		return fechaInicioGestion;
	}
	/**
	 * @param fechaInicioGestion the fechaInicioGestion to set
	 */
	public void setFechaInicioGestion(String fechaInicioGestion) {
		this.fechaInicioGestion = fechaInicioGestion;
	}
	/**
	 * @return the fechaCompromisoPago
	 */
	public String getFechaCompromisoPago() {
		return fechaCompromisoPago;
	}
	/**
	 * @param fechaCompromisoPago the fechaCompromisoPago to set
	 */
	public void setFechaCompromisoPago(String fechaCompromisoPago) {
		this.fechaCompromisoPago = fechaCompromisoPago;
	}
	/**
	 * @return the fechaCierreCartera
	 */
	public String getFechaCierreCartera() {
		return fechaCierreCartera;
	}
	/**
	 * @param fechaCierreCartera the fechaCierreCartera to set
	 */
	public void setFechaCierreCartera(String fechaCierreCartera) {
		this.fechaCierreCartera = fechaCierreCartera;
	}
	/**
	 * @return the fechaGeneracionD
	 */
	public Date getFechaGeneracionD() {
		return fechaGeneracionD;
	}
	/**
	 * @param fechaGeneracionD the fechaGeneracionD to set
	 */
	public void setFechaGeneracionD(Date fechaGeneracionD) {
		this.fechaGeneracionD = fechaGeneracionD;
	}
	/**
	 * @return the fechaGeneracionCarteraD
	 */
	public Date getFechaGeneracionCarteraD() {
		return fechaGeneracionCarteraD;
	}
	/**
	 * @param fechaGeneracionCarteraD the fechaGeneracionCarteraD to set
	 */
	public void setFechaGeneracionCarteraD(Date fechaGeneracionCarteraD) {
		this.fechaGeneracionCarteraD = fechaGeneracionCarteraD;
	}
	/**
	 * @return the fechaInicioGestionD
	 */
	public Date getFechaInicioGestionD() {
		return fechaInicioGestionD;
	}
	/**
	 * @param fechaInicioGestionD the fechaInicioGestionD to set
	 */
	public void setFechaInicioGestionD(Date fechaInicioGestionD) {
		this.fechaInicioGestionD = fechaInicioGestionD;
	}
	/**
	 * @return the fechaCompromisoPagoD
	 */
	public Date getFechaCompromisoPagoD() {
		return fechaCompromisoPagoD;
	}
	/**
	 * @param fechaCompromisoPagoD the fechaCompromisoPagoD to set
	 */
	public void setFechaCompromisoPagoD(Date fechaCompromisoPagoD) {
		this.fechaCompromisoPagoD = fechaCompromisoPagoD;
	}
	/**
	 * @return the fechaCierreCarteraD
	 */
	public Date getFechaCierreCarteraD() {
		return fechaCierreCarteraD;
	}
	/**
	 * @param fechaCierreCarteraD the fechaCierreCarteraD to set
	 */
	public void setFechaCierreCarteraD(Date fechaCierreCarteraD) {
		this.fechaCierreCarteraD = fechaCierreCarteraD;
	}
	
	
	
	

}
