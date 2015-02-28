package biz.belcorp.ssicc.web.spusicc.comision.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoCOMBonosSearchForm extends BaseSearchForm  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5076581005624311657L;
	private String codigoPais;
	private String codigoConcepto;
	private String descripcionConcepto;
	private String campanhaInicial;
	private String campanhaFinal;
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
	 * @return the codigoConcepto
	 */
	public String getCodigoConcepto() {
		return codigoConcepto;
	}
	/**
	 * @param codigoConcepto the codigoConcepto to set
	 */
	public void setCodigoConcepto(String codigoConcepto) {
		this.codigoConcepto = codigoConcepto;
	}
	/**
	 * @return the descripcionConcepto
	 */
	public String getDescripcionConcepto() {
		return descripcionConcepto;
	}
	/**
	 * @param descripcionConcepto the descripcionConcepto to set
	 */
	public void setDescripcionConcepto(String descripcionConcepto) {
		this.descripcionConcepto = descripcionConcepto;
	}
	/**
	 * @return the campanhaInicial
	 */
	public String getCampanhaInicial() {
		return campanhaInicial;
	}
	/**
	 * @param campanhaInicial the campanhaInicial to set
	 */
	public void setCampanhaInicial(String campanhaInicial) {
		this.campanhaInicial = campanhaInicial;
	}
	/**
	 * @return the campanhaFinal
	 */
	public String getCampanhaFinal() {
		return campanhaFinal;
	}
	/**
	 * @param campanhaFinal the campanhaFinal to set
	 */
	public void setCampanhaFinal(String campanhaFinal) {
		this.campanhaFinal = campanhaFinal;
	}
	
	

}
