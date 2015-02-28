package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoPEDConfiguracionOfertasPorConcursosSearchForm extends
		BaseSearchForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6298996406519611010L;
	private String codigoPais;
	private String codigoPeriodo;
	private String codigoCatalogo;
	private String numeroPagina;
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
	 * @return the codigoCatalogo
	 */
	public String getCodigoCatalogo() {
		return codigoCatalogo;
	}
	/**
	 * @param codigoCatalogo the codigoCatalogo to set
	 */
	public void setCodigoCatalogo(String codigoCatalogo) {
		this.codigoCatalogo = codigoCatalogo;
	}
	/**
	 * @return the numeroPagina
	 */
	public String getNumeroPagina() {
		return numeroPagina;
	}
	/**
	 * @param numeroPagina the numeroPagina to set
	 */
	public void setNumeroPagina(String numeroPagina) {
		this.numeroPagina = numeroPagina;
	}
	
	
}
