package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoOCRProcesosEspecialesOCSForm extends BaseProcesoForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2261495986146194121L;
	private String codigoPais;
	private String periodo;
	private String fechaFact;
	private Date fechaFactD;
	/**
	 * @return the codigoPai
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPai the codigoPai to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}
	/**
	 * @param periodo the periodo to set
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	/**
	 * @return the fechaFact
	 */
	public String getFechaFact() {
		return fechaFact;
	}
	/**
	 * @param fechaFact the fechaFact to set
	 */
	public void setFechaFact(String fechaFact) {
		this.fechaFact = fechaFact;
	}
	/**
	 * @return the fechaFactD
	 */
	public Date getFechaFactD() {
		return fechaFactD;
	}
	/**
	 * @param fechaFactD the fechaFactD to set
	 */
	public void setFechaFactD(Date fechaFactD) {
		this.fechaFactD = fechaFactD;
	}
	
	

}
