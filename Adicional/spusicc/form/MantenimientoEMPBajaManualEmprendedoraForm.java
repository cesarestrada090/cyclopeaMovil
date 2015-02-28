package biz.belcorp.ssicc.web.spusicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoEMPBajaManualEmprendedoraForm extends BaseSearchForm
		implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String codigoClienteBuscar;

	private String codigoCliente;
	private String nombreCliente;
	private String oidCliente;

	private String region;
	private String zona;
	private String indicadorEmprendedora;

	private String motivo;

	private String oidNivelRiesgoActual;
	private String codigoNivelRiesgoActual;
	private String descripcionNivelRiesgoActual;

	private String fechaUltimaActualizacion;
	private String nuevoNivelRiesgo;
	private String oidNuevoNivelRiesgo;
	private String codigoNuevoNivelRiesgo;

	private String oidPeriodoNivelRiezgo;

	private boolean clienteEncontrado;
	
	private boolean newRecord;
	
	

	public boolean isNewRecord() {
		return newRecord;
	}

	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}

	/**
	 * @return Returns the codigoCliente.
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente
	 *            The codigoCliente to set.
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return Returns the codigoNivelRiesgoActual.
	 */
	public String getCodigoNivelRiesgoActual() {
		return codigoNivelRiesgoActual;
	}

	/**
	 * @param codigoNivelRiesgoActual
	 *            The codigoNivelRiesgoActual to set.
	 */
	public void setCodigoNivelRiesgoActual(String codigoNivelRiesgoActual) {
		this.codigoNivelRiesgoActual = codigoNivelRiesgoActual;
	}

	/**
	 * @return Returns the codigoNuevoNivelRiesgo.
	 */
	public String getCodigoNuevoNivelRiesgo() {
		return codigoNuevoNivelRiesgo;
	}

	/**
	 * @param codigoNuevoNivelRiesgo
	 *            The codigoNuevoNivelRiesgo to set.
	 */
	public void setCodigoNuevoNivelRiesgo(String codigoNuevoNivelRiesgo) {
		this.codigoNuevoNivelRiesgo = codigoNuevoNivelRiesgo;
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the descripcionNivelRiesgoActual.
	 */
	public String getDescripcionNivelRiesgoActual() {
		return descripcionNivelRiesgoActual;
	}

	/**
	 * @param descripcionNivelRiesgoActual
	 *            The descripcionNivelRiesgoActual to set.
	 */
	public void setDescripcionNivelRiesgoActual(
			String descripcionNivelRiesgoActual) {
		this.descripcionNivelRiesgoActual = descripcionNivelRiesgoActual;
	}

	/**
	 * @return Returns the fechaUltimaActualizacion.
	 */
	public String getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}

	/**
	 * @param fechaUltimaActualizacion
	 *            The fechaUltimaActualizacion to set.
	 */
	public void setFechaUltimaActualizacion(String fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}

	/**
	 * @return Returns the nuevoNivelRiesgo.
	 */
	public String getNuevoNivelRiesgo() {
		return nuevoNivelRiesgo;
	}

	public void setNuevoNivelRiesgo(String nuevoNivelRiesgo) {
		this.nuevoNivelRiesgo = nuevoNivelRiesgo;
	}

	/**
	 * @return Returns the oidCliente.
	 */
	public String getOidCliente() {
		return oidCliente;
	}

	/**
	 * @param oidCliente
	 *            The oidCliente to set.
	 */
	public void setOidCliente(String oidCliente) {
		this.oidCliente = oidCliente;
	}

	/**
	 * @return Returns the oidNivelRiesgoActual.
	 */
	public String getOidNivelRiesgoActual() {
		return oidNivelRiesgoActual;
	}

	/**
	 * @param oidNivelRiesgoActual
	 *            The oidNivelRiesgoActual to set.
	 */
	public void setOidNivelRiesgoActual(String oidNivelRiesgoActual) {
		this.oidNivelRiesgoActual = oidNivelRiesgoActual;
	}

	/**
	 * @return Returns the oidNuevoNivelRiesgo.
	 */
	public String getOidNuevoNivelRiesgo() {
		return oidNuevoNivelRiesgo;
	}

	/**
	 * @param oidNuevoNivelRiesgo
	 *            The oidNuevoNivelRiesgo to set.
	 */
	public void setOidNuevoNivelRiesgo(String oidNuevoNivelRiesgo) {
		this.oidNuevoNivelRiesgo = oidNuevoNivelRiesgo;
	}

	/**
	 * @return Returns the clienteEncontrado.
	 */
	public boolean isClienteEncontrado() {
		return clienteEncontrado;
	}

	/**
	 * @param clienteEncontrado
	 *            The clienteEncontrado to set.
	 */
	public void setClienteEncontrado(boolean clienteEncontrado) {
		this.clienteEncontrado = clienteEncontrado;
	}

	/**
	 * @return Returns the codigoClienteBuscar.
	 */
	public String getCodigoClienteBuscar() {
		return codigoClienteBuscar;
	}

	public void setCodigoClienteBuscar(String codigoClienteBuscar) {
		this.codigoClienteBuscar = codigoClienteBuscar;
	}

	/**
	 * @return Returns the nombreCliente.
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * @param nombreCliente
	 *            The nombreCliente to set.
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	/**
	 * @return the oidPeriodoNivelRiezgo
	 */
	public String getOidPeriodoNivelRiezgo() {
		return oidPeriodoNivelRiezgo;
	}

	/**
	 * @param oidPeriodoNivelRiezgo
	 *            the oidPeriodoNivelRiezgo to set
	 */
	public void setOidPeriodoNivelRiezgo(String oidPeriodoNivelRiezgo) {
		this.oidPeriodoNivelRiezgo = oidPeriodoNivelRiezgo;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region
	 *            the region to set
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
	 * @param zona
	 *            the zona to set
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}

	/**
	 * @return the indicadorEmprendedora
	 */
	public String getIndicadorEmprendedora() {
		return indicadorEmprendedora;
	}

	/**
	 * @param indicadorEmprendedora
	 *            the indicadorEmprendedora to set
	 */
	public void setIndicadorEmprendedora(String indicadorEmprendedora) {
		this.indicadorEmprendedora = indicadorEmprendedora;
	}

	/**
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}

	/**
	 * @param motivo
	 *            the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
}
