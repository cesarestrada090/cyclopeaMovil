package biz.belcorp.ssicc.web.spusicc.emprendedoras.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoEMPEmprendedoraForm extends BaseSearchForm implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String codigoPrograma;
	private String codigoPeriodo;
	private String codigoCliente;
	private String codigoClienteBuscar;
	private String oidCliente;
	private String nombreCliente;
	private String region;
	private String zona;
	private String fechaNacimiento;
	private String tipoDocumento;
	private String numeroDocumento;
	private String direccion;
	private String telefonoCasa;
	private String telefonoCelular;
	private String mail;
	private String documentoLegal;
	private String cuentaBancaria;
	private String codigoCci;
	private String regimen;
	private String tipoEmprendedora;
	private String regionCerrada;
	private String nivelAsignado;
	private boolean newRecord;
	
	

	public boolean isNewRecord() {
		return newRecord;
	}

	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}

	private boolean flagMostrarErrores;
	private String longitudCodigoConsultora;

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoPrograma
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}

	/**
	 * @param codigoPrograma
	 *            the codigoPrograma to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 *            the codigoPeriodo to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente
	 *            the codigoCliente to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return the codigoClienteBuscar
	 */
	public String getCodigoClienteBuscar() {
		return codigoClienteBuscar;
	}

	/**
	 * @param codigoClienteBuscar
	 *            the codigoClienteBuscar to set
	 */
	public void setCodigoClienteBuscar(String codigoClienteBuscar) {
		this.codigoClienteBuscar = codigoClienteBuscar;
	}

	/**
	 * @return the oidCliente
	 */
	public String getOidCliente() {
		return oidCliente;
	}

	/**
	 * @param oidCliente
	 *            the oidCliente to set
	 */
	public void setOidCliente(String oidCliente) {
		this.oidCliente = oidCliente;
	}

	/**
	 * @return the nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * @param nombreCliente
	 *            the nombreCliente to set
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
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
	 * @return the fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento
	 *            the fechaNacimiento to set
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento
	 *            the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento
	 *            the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion
	 *            the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the telefonoCasa
	 */
	public String getTelefonoCasa() {
		return telefonoCasa;
	}

	/**
	 * @param telefonoCasa
	 *            the telefonoCasa to set
	 */
	public void setTelefonoCasa(String telefonoCasa) {
		this.telefonoCasa = telefonoCasa;
	}

	/**
	 * @return the telefonoCelular
	 */
	public String getTelefonoCelular() {
		return telefonoCelular;
	}

	/**
	 * @param telefonoCelular
	 *            the telefonoCelular to set
	 */
	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail
	 *            the mail to set
	 * @struts.validator type = "required"
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the documentoLegal
	 */
	public String getDocumentoLegal() {
		return documentoLegal;
	}

	/**
	 * @param documentoLegal
	 *            the documentoLegal to set
	 */
	public void setDocumentoLegal(String documentoLegal) {
		this.documentoLegal = documentoLegal;
	}

	/**
	 * @return the cuentaBancaria
	 */
	public String getCuentaBancaria() {
		return cuentaBancaria;
	}

	/**
	 * @param cuentaBancaria
	 *            the cuentaBancaria to set
	 */
	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	/**
	 * @return the codigoCci
	 */
	public String getCodigoCci() {
		return codigoCci;
	}

	/**
	 * @param codigoCci
	 *            the codigoCci to set
	 */
	public void setCodigoCci(String codigoCci) {
		this.codigoCci = codigoCci;
	}

	/**
	 * @return the regimen
	 */
	public String getRegimen() {
		return regimen;
	}

	/**
	 * @param regimen
	 *            the regimen to set
	 */
	public void setRegimen(String regimen) {
		this.regimen = regimen;
	}

	/**
	 * @return the tipoEmprendedora
	 */
	public String getTipoEmprendedora() {
		return tipoEmprendedora;
	}

	/**
	 * @param tipoEmprendedora
	 *            the tipoEmprendedora to set
	 */
	public void setTipoEmprendedora(String tipoEmprendedora) {
		this.tipoEmprendedora = tipoEmprendedora;
	}

	/**
	 * @return the regionCerrada
	 */
	public String getRegionCerrada() {
		return regionCerrada;
	}

	/**
	 * @param regionCerrada
	 *            the regionCerrada to set
	 */
	public void setRegionCerrada(String regionCerrada) {
		this.regionCerrada = regionCerrada;
	}

	/**
	 * @return the flagMostrarErrores
	 */
	public boolean isFlagMostrarErrores() {
		return flagMostrarErrores;
	}

	/**
	 * @param flagMostrarErrores
	 *            the flagMostrarErrores to set
	 */
	public void setFlagMostrarErrores(boolean flagMostrarErrores) {
		this.flagMostrarErrores = flagMostrarErrores;
	}

	/**
	 * @return the longitudCodigoConsultora
	 */
	public String getLongitudCodigoConsultora() {
		return longitudCodigoConsultora;
	}

	/**
	 * @param longitudCodigoConsultora
	 *            the longitudCodigoConsultora to set
	 */
	public void setLongitudCodigoConsultora(String longitudCodigoConsultora) {
		this.longitudCodigoConsultora = longitudCodigoConsultora;
	}

	/**
	 * @return the nivelAsignado
	 */
	public String getNivelAsignado() {
		return nivelAsignado;
	}

	/**
	 * @param nivelAsignado
	 *            the nivelAsignado to set
	 */
	public void setNivelAsignado(String nivelAsignado) {
		this.nivelAsignado = nivelAsignado;
	}

}
