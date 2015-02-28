package biz.belcorp.ssicc.web.spusicc.sgr.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * The Class MantenimientoSGRInscripcionPolizaForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 23/01/2015
 */
public class MantenimientoSGRInscripcionPolizaForm extends BaseEditForm {
	
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoPoliza;
	private String numeroCertificado;
	private String tipoDocumentoIdentidad;
	private String numeroDocumentoIdent;
	private String codigoCliente;	
	private String oidPais;
	private String longitudCodigoCliente;	
	private String nombreCliente;
	private String indicadorInsertar;
	
	private String nombreBeneficiario;
	private String apellidoBeneficiario;
	private String tipoDocumentoIdentidadBenef;
	private String numeroDocumentoIdentBenef;
	
	private String longitudTipoDocumento;
	
	private String numeroInicialPoliza;
	private String indicadorActivo;
		
	public String getIndicadorActivo() {
		return indicadorActivo;
	}

	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}

	/**
	 * @return the indicadorInsertar
	 */
	public String getIndicadorInsertar() {
		return indicadorInsertar;
	}

	/**
	 * @param indicadorInsertar the indicadorInsertar to set
	 */
	public void setIndicadorInsertar(String indicadorInsertar) {
		this.indicadorInsertar = indicadorInsertar;
	}

	/**
	 * @return the nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * @param nombreCliente the nombreCliente to set
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	/**
	 * @return the oidPais
	 */
	public String getOidPais() {
		return oidPais;
	}

	/**
	 * @param oidPais the oidPais to set
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}

	/**
	 * @return the longitudCodigoCliente
	 */
	public String getLongitudCodigoCliente() {
		return longitudCodigoCliente;
	}

	/**
	 * @param longitudCodigoCliente the longitudCodigoCliente to set
	 */
	public void setLongitudCodigoCliente(String longitudCodigoCliente) {
		this.longitudCodigoCliente = longitudCodigoCliente;
	}
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 * @struts.validator type="required"
	 */  
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoPoliza
	 */
	public String getCodigoPoliza() {
		return codigoPoliza;
	}

	/**
	 * @param codigoPoliza the codigoPoliza to set
	 * @struts.validator type="required"
	 */
	public void setCodigoPoliza(String codigoPoliza) {
		this.codigoPoliza = codigoPoliza;
	}

	/**
	 * @return the numeroCertificado
	 */
	public String getNumeroCertificado() {
		return numeroCertificado;
	}

	/**
	 * @param numeroCertificado the numeroCertificado to set
	 * @struts.validator type="required"
	 */
	public void setNumeroCertificado(String numeroCertificado) {
		this.numeroCertificado = numeroCertificado;
	}

	/**
	 * @return the tipoDocumentoIdentidad
	 */
	public String getTipoDocumentoIdentidad() {
		return tipoDocumentoIdentidad;
	}

	/**
	 * @param tipoDocumentoIdentidad the tipoDocumentoIdentidad to set
	 * @struts.validator type="required"
	 */
	public void setTipoDocumentoIdentidad(String tipoDocumentoIdentidad) {
		this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
	}

	/**
	 * @return the numeroDocumentoIdent
	 */
	public String getNumeroDocumentoIdent() {
		return numeroDocumentoIdent;
	}

	/**
	 * @param numeroDocumentoIdent the numeroDocumentoIdent to set
	 * @struts.validator type="required"
	 */
	public void setNumeroDocumentoIdent(String numeroDocumentoIdent) {
		this.numeroDocumentoIdent = numeroDocumentoIdent;
	}

	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente the codigoCliente to set
	 * @struts.validator type="required"
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return the nombreBeneficiario
	 */
	public String getNombreBeneficiario() {
		return nombreBeneficiario;
	}

	/**
	 * @param nombreBeneficiario the nombreBeneficiario to set
	 */
	public void setNombreBeneficiario(String nombreBeneficiario) {
		this.nombreBeneficiario = nombreBeneficiario;
	}

	/**
	 * @return the apellidoBeneficiario
	 */
	public String getApellidoBeneficiario() {
		return apellidoBeneficiario;
	}

	/**
	 * @param apellidoBeneficiario the apellidoBeneficiario to set
	 */
	public void setApellidoBeneficiario(String apellidoBeneficiario) {
		this.apellidoBeneficiario = apellidoBeneficiario;
	}

	/**
	 * @return the tipoDocumentoIdentidadBenef
	 */
	public String getTipoDocumentoIdentidadBenef() {
		return tipoDocumentoIdentidadBenef;
	}

	/**
	 * @param tipoDocumentoIdentidadBenef the tipoDocumentoIdentidadBenef to set
	 */
	public void setTipoDocumentoIdentidadBenef(String tipoDocumentoIdentidadBenef) {
		this.tipoDocumentoIdentidadBenef = tipoDocumentoIdentidadBenef;
	}

	/**
	 * @return the numeroDocumentoIdentBenef
	 */
	public String getNumeroDocumentoIdentBenef() {
		return numeroDocumentoIdentBenef;
	}

	/**
	 * @param numeroDocumentoIdentBenef the numeroDocumentoIdentBenef to set
	 */
	public void setNumeroDocumentoIdentBenef(String numeroDocumentoIdentBenef) {
		this.numeroDocumentoIdentBenef = numeroDocumentoIdentBenef;
	}

	/**
	 * @return the longitudTipoDocumento
	 */
	public String getLongitudTipoDocumento() {
		return longitudTipoDocumento;
	}

	/**
	 * @param longitudTipoDocumento the longitudTipoDocumento to set
	 */
	public void setLongitudTipoDocumento(String longitudTipoDocumento) {
		this.longitudTipoDocumento = longitudTipoDocumento;
	}

	public String getNumeroInicialPoliza() {
		return numeroInicialPoliza;
	}

	public void setNumeroInicialPoliza(String numeroInicialPoliza) {
		this.numeroInicialPoliza = numeroInicialPoliza;
	}

		
}
