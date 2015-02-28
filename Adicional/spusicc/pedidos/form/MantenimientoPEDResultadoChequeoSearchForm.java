package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoPEDResultadoChequeoSearchForm extends BaseSearchForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoResultadoChequeo;
	private String descripcionResultadoChequeo;
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
	 * @return the codigoResultadoChequeo
	 */
	public String getCodigoResultadoChequeo() {
		return codigoResultadoChequeo;
	}
	/**
	 * @param codigoResultadoChequeo the codigoResultadoChequeo to set
	 */
	public void setCodigoResultadoChequeo(String codigoResultadoChequeo) {
		this.codigoResultadoChequeo = codigoResultadoChequeo;
	}
	/**
	 * @return the descripcionResultadoChequeo
	 */
	public String getDescripcionResultadoChequeo() {
		return descripcionResultadoChequeo;
	}
	/**
	 * @param descripcionResultadoChequeo the descripcionResultadoChequeo to set
	 */
	public void setDescripcionResultadoChequeo(String descripcionResultadoChequeo) {
		this.descripcionResultadoChequeo = descripcionResultadoChequeo;
	}
	
	
}
