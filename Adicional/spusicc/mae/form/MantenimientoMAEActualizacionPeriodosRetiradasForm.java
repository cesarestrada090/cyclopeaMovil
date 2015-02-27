package biz.belcorp.ssicc.web.spusicc.mae.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoMAEActualizacionPeriodosRetiradasForm extends BaseSearchForm	{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1076010529379890145L;
	private String codigoPais;
	private String codigoPeriodo;
	private String numeroPeriodos;
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
	 * @return the numeroPeriodos
	 */
	public String getNumeroPeriodos() {
		return numeroPeriodos;
	}
	/**
	 * @param numeroPeriodos the numeroPeriodos to set
	 */
	public void setNumeroPeriodos(String numeroPeriodos) {
		this.numeroPeriodos = numeroPeriodos;
	}
	
	
	
}
