package biz.belcorp.ssicc.web.spusicc.ape.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoAPETiposCajaEmbalajeForm extends BaseEditForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoUnidMedidaCapa;
	private String codigoUnidMedidaExte;
	private String oidTipoCaja;
	private String descripcionTipoCaja;
	private String flagEdicion;
	private String codigoTipoCajaEmbalaje;
	private String capacidad;
	private String capacidadMinima;
	private String indicadorCubicaje;
	private String porcentajeSeguridad;
	private String nivelAplicacion;
	private String numEtiquetasCaja;
	private String codigoTipoCajaProducto;
	private String alto;
	private String ancho;
	private String largo;
	private String indicadorCajaMaestra;
	private boolean activo;
	
	
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
	 * @return the codigoUnidMedidaCapa
	 */
	public String getCodigoUnidMedidaCapa() {
		return codigoUnidMedidaCapa;
	}
	/**
	 * @param codigoUnidMedidaCapa the codigoUnidMedidaCapa to set
	 */
	public void setCodigoUnidMedidaCapa(String codigoUnidMedidaCapa) {
		this.codigoUnidMedidaCapa = codigoUnidMedidaCapa;
	}
	/**
	 * @return the codigoUnidMedidaExte
	 */
	public String getCodigoUnidMedidaExte() {
		return codigoUnidMedidaExte;
	}
	/**
	 * @param codigoUnidMedidaExte the codigoUnidMedidaExte to set
	 */
	public void setCodigoUnidMedidaExte(String codigoUnidMedidaExte) {
		this.codigoUnidMedidaExte = codigoUnidMedidaExte;
	}
	/**
	 * @return the oidTipoCaja
	 */
	public String getOidTipoCaja() {
		return oidTipoCaja;
	}
	/**
	 * @param oidTipoCaja the oidTipoCaja to set
	 */
	public void setOidTipoCaja(String oidTipoCaja) {
		this.oidTipoCaja = oidTipoCaja;
	}
	/**
	 * @return the descripcionTipoCaja
	 */
	public String getDescripcionTipoCaja() {
		return descripcionTipoCaja;
	}
	/**
	 * @param descripcionTipoCaja the descripcionTipoCaja to set
	 */
	public void setDescripcionTipoCaja(String descripcionTipoCaja) {
		this.descripcionTipoCaja = descripcionTipoCaja;
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
	 * @return the codigoTipoCajaEmbalaje
	 */
	public String getCodigoTipoCajaEmbalaje() {
		return codigoTipoCajaEmbalaje;
	}
	/**
	 * @param codigoTipoCajaEmbalaje the codigoTipoCajaEmbalaje to set
	 */
	public void setCodigoTipoCajaEmbalaje(String codigoTipoCajaEmbalaje) {
		this.codigoTipoCajaEmbalaje = codigoTipoCajaEmbalaje;
	}
	/**
	 * @return the capacidad
	 */
	public String getCapacidad() {
		return capacidad;
	}
	/**
	 * @param capacidad the capacidad to set
	 */
	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}
	/**
	 * @return the capacidadMinima
	 */
	public String getCapacidadMinima() {
		return capacidadMinima;
	}
	/**
	 * @param capacidadMinima the capacidadMinima to set
	 */
	public void setCapacidadMinima(String capacidadMinima) {
		this.capacidadMinima = capacidadMinima;
	}
	/**
	 * @return the indicadorCubicaje
	 */
	public String getIndicadorCubicaje() {
		return indicadorCubicaje;
	}
	/**
	 * @param indicadorCubicaje the indicadorCubicaje to set
	 */
	public void setIndicadorCubicaje(String indicadorCubicaje) {
		this.indicadorCubicaje = indicadorCubicaje;
	}
	/**
	 * @return the porcentajeSeguridad
	 */
	public String getPorcentajeSeguridad() {
		return porcentajeSeguridad;
	}
	/**
	 * @param porcentajeSeguridad the porcentajeSeguridad to set
	 */
	public void setPorcentajeSeguridad(String porcentajeSeguridad) {
		this.porcentajeSeguridad = porcentajeSeguridad;
	}
	/**
	 * @return the nivelAplicacion
	 */
	public String getNivelAplicacion() {
		return nivelAplicacion;
	}
	/**
	 * @param nivelAplicacion the nivelAplicacion to set
	 */
	public void setNivelAplicacion(String nivelAplicacion) {
		this.nivelAplicacion = nivelAplicacion;
	}
	/**
	 * @return the numEtiquetasCaja
	 */
	public String getNumEtiquetasCaja() {
		return numEtiquetasCaja;
	}
	/**
	 * @param numEtiquetasCaja the numEtiquetasCaja to set
	 */
	public void setNumEtiquetasCaja(String numEtiquetasCaja) {
		this.numEtiquetasCaja = numEtiquetasCaja;
	}
	/**
	 * @return the codigoTipoCajaProducto
	 */
	public String getCodigoTipoCajaProducto() {
		return codigoTipoCajaProducto;
	}
	/**
	 * @param codigoTipoCajaProducto the codigoTipoCajaProducto to set
	 */
	public void setCodigoTipoCajaProducto(String codigoTipoCajaProducto) {
		this.codigoTipoCajaProducto = codigoTipoCajaProducto;
	}
	/**
	 * @return the alto
	 */
	public String getAlto() {
		return alto;
	}
	/**
	 * @param alto the alto to set
	 */
	public void setAlto(String alto) {
		this.alto = alto;
	}
	/**
	 * @return the ancho
	 */
	public String getAncho() {
		return ancho;
	}
	/**
	 * @param ancho the ancho to set
	 */
	public void setAncho(String ancho) {
		this.ancho = ancho;
	}
	/**
	 * @return the largo
	 */
	public String getLargo() {
		return largo;
	}
	/**
	 * @param largo the largo to set
	 */
	public void setLargo(String largo) {
		this.largo = largo;
	}
	/**
	 * @return the indicadorCajaMaestra
	 */
	public String getIndicadorCajaMaestra() {
		return indicadorCajaMaestra;
	}
	/**
	 * @param indicadorCajaMaestra the indicadorCajaMaestra to set
	 */
	public void setIndicadorCajaMaestra(String indicadorCajaMaestra) {
		this.indicadorCajaMaestra = indicadorCajaMaestra;
	}
	
	
	
}
