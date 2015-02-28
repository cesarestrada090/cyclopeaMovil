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
public class ProcesoOCRCrearOfertasAlternativasForm extends BaseProcesoForm{

	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoPeriodo;
	
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
	
}