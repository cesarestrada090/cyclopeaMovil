package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoOCRReemplazosForm extends BaseEditForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7057756237476653079L;
	private String codigoPeriodo;
	private String codigoVentaPrincipal;
	private String codigoSAPPrincipal;
	private String descripcionPrincipal;
	private String codigoVentaReemplazo;
	private String codigoSAPReemplazo;
	private String descripcionReemplazo;
	private String indicadorMensaje;
	private String tipoReemplazo;
	private String oidTipoReemplazo;
	private String tipoCliente;
	private String subtipoCliente;
	private String tipoClasificacion;
	private String clasificacion;
	private String region;
	private String zona;	
	private String oidProductoPrincipal;
	private String oidProductoReemplazo;
	private String fechaActivacion;
	private Date fechaActivacionD;
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
	 * @return the codigoVentaPrincipal
	 */
	public String getCodigoVentaPrincipal() {
		return codigoVentaPrincipal;
	}
	/**
	 * @param codigoVentaPrincipal the codigoVentaPrincipal to set
	 */
	public void setCodigoVentaPrincipal(String codigoVentaPrincipal) {
		this.codigoVentaPrincipal = codigoVentaPrincipal;
	}
	/**
	 * @return the codigoSAPPrincipal
	 */
	public String getCodigoSAPPrincipal() {
		return codigoSAPPrincipal;
	}
	/**
	 * @param codigoSAPPrincipal the codigoSAPPrincipal to set
	 */
	public void setCodigoSAPPrincipal(String codigoSAPPrincipal) {
		this.codigoSAPPrincipal = codigoSAPPrincipal;
	}
	/**
	 * @return the descripcionPrincipal
	 */
	public String getDescripcionPrincipal() {
		return descripcionPrincipal;
	}
	/**
	 * @param descripcionPrincipal the descripcionPrincipal to set
	 */
	public void setDescripcionPrincipal(String descripcionPrincipal) {
		this.descripcionPrincipal = descripcionPrincipal;
	}
	/**
	 * @return the codigoVentaReemplazo
	 */
	public String getCodigoVentaReemplazo() {
		return codigoVentaReemplazo;
	}
	/**
	 * @param codigoVentaReemplazo the codigoVentaReemplazo to set
	 */
	public void setCodigoVentaReemplazo(String codigoVentaReemplazo) {
		this.codigoVentaReemplazo = codigoVentaReemplazo;
	}
	/**
	 * @return the codigoSAPReemplazo
	 */
	public String getCodigoSAPReemplazo() {
		return codigoSAPReemplazo;
	}
	/**
	 * @param codigoSAPReemplazo the codigoSAPReemplazo to set
	 */
	public void setCodigoSAPReemplazo(String codigoSAPReemplazo) {
		this.codigoSAPReemplazo = codigoSAPReemplazo;
	}
	/**
	 * @return the descripcionReemplazo
	 */
	public String getDescripcionReemplazo() {
		return descripcionReemplazo;
	}
	/**
	 * @param descripcionReemplazo the descripcionReemplazo to set
	 */
	public void setDescripcionReemplazo(String descripcionReemplazo) {
		this.descripcionReemplazo = descripcionReemplazo;
	}
	/**
	 * @return the indicadorMensaje
	 */
	public String getIndicadorMensaje() {
		return indicadorMensaje;
	}
	/**
	 * @param indicadorMensaje the indicadorMensaje to set
	 */
	public void setIndicadorMensaje(String indicadorMensaje) {
		this.indicadorMensaje = indicadorMensaje;
	}
	/**
	 * @return the tipoReemplazo
	 */
	public String getTipoReemplazo() {
		return tipoReemplazo;
	}
	/**
	 * @param tipoReemplazo the tipoReemplazo to set
	 */
	public void setTipoReemplazo(String tipoReemplazo) {
		this.tipoReemplazo = tipoReemplazo;
	}
	/**
	 * @return the oidTipoReemplazo
	 */
	public String getOidTipoReemplazo() {
		return oidTipoReemplazo;
	}
	/**
	 * @param oidTipoReemplazo the oidTipoReemplazo to set
	 */
	public void setOidTipoReemplazo(String oidTipoReemplazo) {
		this.oidTipoReemplazo = oidTipoReemplazo;
	}
	/**
	 * @return the tipoCliente
	 */
	public String getTipoCliente() {
		return tipoCliente;
	}
	/**
	 * @param tipoCliente the tipoCliente to set
	 */
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	/**
	 * @return the subtipoCliente
	 */
	public String getSubtipoCliente() {
		return subtipoCliente;
	}
	/**
	 * @param subtipoCliente the subtipoCliente to set
	 */
	public void setSubtipoCliente(String subtipoCliente) {
		this.subtipoCliente = subtipoCliente;
	}
	/**
	 * @return the tipoClasificacion
	 */
	public String getTipoClasificacion() {
		return tipoClasificacion;
	}
	/**
	 * @param tipoClasificacion the tipoClasificacion to set
	 */
	public void setTipoClasificacion(String tipoClasificacion) {
		this.tipoClasificacion = tipoClasificacion;
	}
	/**
	 * @return the clasificacion
	 */
	public String getClasificacion() {
		return clasificacion;
	}
	/**
	 * @param clasificacion the clasificacion to set
	 */
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * @return the zona
	 */
	public String getZona() {
		return zona;
	}
	/**
	 * @param zona the zona to set
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}
	/**
	 * @return the oidProductoPrincipal
	 */
	public String getOidProductoPrincipal() {
		return oidProductoPrincipal;
	}
	/**
	 * @param oidProductoPrincipal the oidProductoPrincipal to set
	 */
	public void setOidProductoPrincipal(String oidProductoPrincipal) {
		this.oidProductoPrincipal = oidProductoPrincipal;
	}
	/**
	 * @return the oidProductoReemplazo
	 */
	public String getOidProductoReemplazo() {
		return oidProductoReemplazo;
	}
	/**
	 * @param oidProductoReemplazo the oidProductoReemplazo to set
	 */
	public void setOidProductoReemplazo(String oidProductoReemplazo) {
		this.oidProductoReemplazo = oidProductoReemplazo;
	}
	/**
	 * @return the fechaActivacion
	 */
	public String getFechaActivacion() {
		return fechaActivacion;
	}
	/**
	 * @param fechaActivacion the fechaActivacion to set
	 */
	public void setFechaActivacion(String fechaActivacion) {
		this.fechaActivacion = fechaActivacion;
	}
	/**
	 * @return the fechaActivacionD
	 */
	public Date getFechaActivacionD() {
		return fechaActivacionD;
	}
	/**
	 * @param fechaActivacionD the fechaActivacionD to set
	 */
	public void setFechaActivacionD(Date fechaActivacionD) {
		this.fechaActivacionD = fechaActivacionD;
	}
	
	

}
