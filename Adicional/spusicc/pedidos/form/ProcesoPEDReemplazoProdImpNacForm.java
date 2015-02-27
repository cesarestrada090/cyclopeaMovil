package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;



/**
 * <p>
 * <a href="ProcesoPEDResecuenciacionPedidosForm.java.html"> <i>View Source</i>
 * </a>
 * </p>
 *
 * @author <a href="mailto:jcairampoma@belcorp.biz">José A. Cairampoma</a>
 *
 * @struts.form name = "procesoPEDResecuenciacionPedidosForm" extends = "baseProcesoForm"
 */
public class ProcesoPEDReemplazoProdImpNacForm extends BaseProcesoForm{

private static final long serialVersionUID = 1L;
	
	public String codigoPeriodo;
	
	public String numeroRegistros;
	
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @
	 * return the numeroRegistros
	 */
	public String getNumeroRegistros() {
		return numeroRegistros;
	}

	/**
	 * @param numeroRegistros the numeroRegistros to set
	 */
	public void setNumeroRegistros(String numeroRegistros) {
		this.numeroRegistros = numeroRegistros;
	}
	
	
}