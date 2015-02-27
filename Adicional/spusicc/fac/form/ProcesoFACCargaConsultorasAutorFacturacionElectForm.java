package biz.belcorp.ssicc.web.spusicc.fac.form;

import java.io.Serializable;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * @author Danny Amaro
 * 
 * @struts.form name = "procesoFACCargaConsultorasAutorFacturacionElectForm" extends = "baseEditForm"
 */
public class ProcesoFACCargaConsultorasAutorFacturacionElectForm extends BaseProcesoForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 179392229636610512L;
	
	private String codigoPais;
	
	private UploadedFile clienteFile;
	private String[] clienteList;
	
	private String codigosErradosFile;
	private int errados;
	
	private int nroConsultorasConRUC;
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @struts.validator type = "required"
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the clienteList
	 */
	public String[] getClienteList() {
		return clienteList;
	}
	/**
	 * @param clienteList the clienteList to set
	 */
	public void setClienteList(String[] clienteList) {
		this.clienteList = clienteList;
	}
	/**
	 * @return the codigosErradosFile
	 */
	public String getCodigosErradosFile() {
		return codigosErradosFile;
	}
	/**
	 * @param codigosErradosFile the codigosErradosFile to set
	 */
	public void setCodigosErradosFile(String codigosErradosFile) {
		this.codigosErradosFile = codigosErradosFile;
	}
	/**
	 * @return the errados
	 */
	public int getErrados() {
		return errados;
	}
	/**
	 * @param errados the errados to set
	 */
	public void setErrados(int errados) {
		this.errados = errados;
	}
	/**
	 * @return the nroConsultorasConRUC
	 */
	public int getNroConsultorasConRUC() {
		return nroConsultorasConRUC;
	}
	/**
	 * @param nroConsultorasConRUC the nroConsultorasConRUC to set
	 */
	public void setNroConsultorasConRUC(int nroConsultorasConRUC) {
		this.nroConsultorasConRUC = nroConsultorasConRUC;
	}
	public UploadedFile getClienteFile() {
		return clienteFile;
	}
	public void setClienteFile(UploadedFile clienteFile) {
		this.clienteFile = clienteFile;
	}
	
}
