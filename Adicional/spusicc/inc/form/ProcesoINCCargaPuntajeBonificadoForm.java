package biz.belcorp.ssicc.web.spusicc.inc.form;

import org.apache.struts.upload.FormFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * The Class ProcesoINCCargaPuntajeBonificadoForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 30/01/2015
 */
public class ProcesoINCCargaPuntajeBonificadoForm extends BaseProcesoForm {
	
	private static final long serialVersionUID = -5822547280747131770L;
	private String codigoPais;
	private String oidConcurso;
	private String codigoMotivo;
	
	private FormFile archivo;	//objeto que se utilizara para el upload del Archivo
	private String directorioTemporal;  //directorio temporal del servidor donde se guardara el archivo
	private String nombreArchivo;	//nombre del archivo a subirse al servidor
	private String extensionArchivo;	//extension del archivo
	
	private String numRegistros;
	private String numRegistrosError;
	
	private String indicadorValido;
	private String numeroLote;
	private String codigoPeriodo;
	private String numRegistrosValido;
	
	private String indicadorConsultoras;
	private boolean indicadorConsultorasB;
	private String codigoCliente;
	private String numeroPuntos;
	
	public String getNumRegistrosValido() {
		return numRegistrosValido;
	}

	public void setNumRegistrosValido(String numRegistrosValido) {
		this.numRegistrosValido = numRegistrosValido;
	}

	/**
	 * @return the numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote the numeroLote to set
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
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
	 * @return the indicadorValido
	 */
	public String getIndicadorValido() {
		return indicadorValido;
	}

	/**
	 * @param indicadorValido the indicadorValido to set
	 */
	public void setIndicadorValido(String indicadorValido) {
		this.indicadorValido = indicadorValido;
	}

	/**
	 * @return the numRegistros
	 */
	public String getNumRegistros() {
		return numRegistros;
	}

	/**
	 * @param numRegistros the numRegistros to set
	 */
	public void setNumRegistros(String numRegistros) {
		this.numRegistros = numRegistros;
	}

	/**
	 * @return the numRegistrosError
	 */
	public String getNumRegistrosError() {
		return numRegistrosError;
	}

	/**
	 * @param numRegistrosError the numRegistrosError to set
	 */
	public void setNumRegistrosError(String numRegistrosError) {
		this.numRegistrosError = numRegistrosError;
	}	
	/**
	 * @return the archivo
	 */
	public FormFile getArchivo() {
		return archivo;
	}

	/**
	 * @param archivo the archivo to set
	 */
	public void setArchivo(FormFile archivo) {
		this.archivo = archivo;
	}

	/**
	 * @return the directorioTemporal
	 */
	public String getDirectorioTemporal() {
		return directorioTemporal;
	}

	/**
	 * @param directorioTemporal the directorioTemporal to set
	 */
	public void setDirectorioTemporal(String directorioTemporal) {
		this.directorioTemporal = directorioTemporal;
	}

	/**
	 * @return the nombreArchivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 * @param nombreArchivo the nombreArchivo to set
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	/**
	 * @return the extensionArchivo
	 */
	public String getExtensionArchivo() {
		return extensionArchivo;
	}

	/**
	 * @param extensionArchivo the extensionArchivo to set
	 */
	public void setExtensionArchivo(String extensionArchivo) {
		this.extensionArchivo = extensionArchivo;
	}

	/**
	 * @return the oidConcurso
	 */
	public String getOidConcurso() {
		return oidConcurso;
	}

	/**
	 * @param oidConcurso the oidConcurso to set
	 * @struts.validator type="required"
	 */
	public void setOidConcurso(String oidConcurso) {
		this.oidConcurso = oidConcurso;
	}

	/**
	 * @return the codigoMotivo
	 */
	public String getCodigoMotivo() {
		return codigoMotivo;
	}

	/**
	 * @param codigoMotivo the codigoMotivo to set
	 * @struts.validator type="required"
	 */
	public void setCodigoMotivo(String codigoMotivo) {
		this.codigoMotivo = codigoMotivo;
	}

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
	 * @return the indicadorConsultoras
	 */
	public String getIndicadorConsultoras() {
		return indicadorConsultoras;
	}

	/**
	 * @param indicadorConsultoras the indicadorConsultoras to set
	 */
	public void setIndicadorConsultoras(String indicadorConsultoras) {
		this.indicadorConsultoras = indicadorConsultoras;
	}

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
	 * @return the numeroPuntos
	 */
	public String getNumeroPuntos() {
		return numeroPuntos;
	}

	/**
	 * @param numeroPuntos the numeroPuntos to set
	 */
	public void setNumeroPuntos(String numeroPuntos) {
		this.numeroPuntos = numeroPuntos;
	}

	/**
	 * @return the indicadorConsultorasB
	 */
	public boolean isIndicadorConsultorasB() {
		return indicadorConsultorasB;
	}

	/**
	 * @param indicadorConsultorasB the indicadorConsultorasB to set
	 */
	public void setIndicadorConsultorasB(boolean indicadorConsultorasB) {
		this.indicadorConsultorasB = indicadorConsultorasB;
	}

}
