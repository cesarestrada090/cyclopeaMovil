package biz.belcorp.ssicc.web.spusicc.sto.form;

import org.apache.struts.upload.FormFile;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoSTOLimiteVentaFocalizadoConsejeraForm extends BaseEditForm{
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	
	private String codigoPeriodo;
	
	private String codigoCliente;
	
	private String numUnidadesLimite;
	
	private String codigosErrados;
	
	private FormFile clienteFile; // objeto que se utilizara para el upload
	
	private String codigosErradosFile;

	/**
	 * @return
	 */
	public String getNumUnidadesLimite() {
		return numUnidadesLimite;
	}

	/**
	 * @param numUnidadesLimite
	 */
	public void setNumUnidadesLimite(String numUnidadesLimite) {
		this.numUnidadesLimite = numUnidadesLimite;
	}

	/**
	 * @return
	 */
	public String getCodigosErradosFile() {
		return codigosErradosFile;
	}

	/**
	 * @param codigosErradosFile
	 */
	public void setCodigosErradosFile(String codigosErradosFile) {
		this.codigosErradosFile = codigosErradosFile;
	}

	/**
	 * @return
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return
	 */
	public String getCodigosErrados() {
		return codigosErrados;
	}

	/**
	 * @param codigosErrados
	 */
	public void setCodigosErrados(String codigosErrados) {
		this.codigosErrados = codigosErrados;
	}

	/**
	 * @return
	 */
	public FormFile getClienteFile() {
		return clienteFile;
	}

	/**
	 * @param clienteFile
	 */
	public void setClienteFile(FormFile clienteFile) {
		this.clienteFile = clienteFile;
	}	
}