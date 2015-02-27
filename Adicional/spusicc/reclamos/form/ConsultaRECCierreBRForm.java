package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class ConsultaRECCierreBRForm extends BaseSearchForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String numeroLote;
	private String codigoResultadoBR;
	private String fecha;
	private Date fechaDate;
	
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
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type="required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the codigoResultadoBR
	 */
	public String getCodigoResultadoBR() {
		return codigoResultadoBR;
	}
	/**
	 * @param codigoResultadoBR the codigoResultadoBR to set
	 */
	public void setCodigoResultadoBR(String codigoResultadoBR) {
		this.codigoResultadoBR = codigoResultadoBR;
	}
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Date getFechaDate() {
		return fechaDate;
	}
	public void setFechaDate(Date fechaDate) {
		this.fechaDate = fechaDate;
	}
}
