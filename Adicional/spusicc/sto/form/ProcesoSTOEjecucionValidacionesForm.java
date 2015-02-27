package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoSTOEjecucionValidacionesForm extends BaseProcesoForm implements Serializable{

	private String tipoDocumento;
	private String [] codigoDocumentos;
	private String codigoPeriodo;
	private String fechaProceso;
	
	
	/**
	 * @return Returns the tipoDocumento.
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 *  @struts.validator type = "required"
	 * @param tipoDocumento The tipoDocumento to set.
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	/**
	 * @return Returns the documentos.
	 */
	/**
	 * @return Returns the codigoDocumentos.
	 */
	public String[] getCodigoDocumentos() {
		return codigoDocumentos;
	}
	/**
	 * @param codigoDocumentos The codigoDocumentos to set.
	 */
	public void setCodigoDocumentos(String[] codigoDocumentos) {
		
		this.codigoDocumentos = codigoDocumentos;
		System.out.println("----------leo -------- " + this.codigoDocumentos);
	}
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 *  @struts.validator type = "required"
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	public String getFechaProceso() {
		return fechaProceso;
	}
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
	
	
}
