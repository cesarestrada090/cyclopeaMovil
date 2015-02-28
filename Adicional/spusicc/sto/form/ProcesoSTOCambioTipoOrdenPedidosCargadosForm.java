package biz.belcorp.ssicc.web.spusicc.sto.form;

import org.apache.struts.upload.FormFile;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * <p>
 * <a href="ProcesoSTOCambioTipoOrdenPedidosCargadosForm.java.html"> <i>View Source</i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:jjrios@belcorp.biz">Jesse James Rios Franco</a>
 * 
 */

public class ProcesoSTOCambioTipoOrdenPedidosCargadosForm extends BaseProcesoForm{
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPeriodo;
	
	private String numeroLote;
	
	private UploadedFile clienteFile;
	
	private String tipoOrden;
	
	
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
	public String getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	/**
	 * @return
	 */
	public String getTipoOrden() {
		return tipoOrden;
	}

	/**
	 * @param tipoOrden
	 */
	public void setTipoOrden(String tipoOrden) {
		this.tipoOrden = tipoOrden;
	}

	public UploadedFile getClienteFile() {
		return clienteFile;
	}

	public void setClienteFile(UploadedFile clienteFile) {
		this.clienteFile = clienteFile;
	}	
}