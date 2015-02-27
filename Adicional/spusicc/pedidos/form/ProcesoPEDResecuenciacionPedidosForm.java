package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.util.Date;

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
public class ProcesoPEDResecuenciacionPedidosForm extends BaseProcesoForm{

	private static final long serialVersionUID = 1L;
	
	private String codigoPeriodo;
	
	private String fechaFacturacion;
	
	private Date fechaFacturacionD;
	
	




	public Date getFechaFacturacionD() {
		return fechaFacturacionD;
	}


	public void setFechaFacturacionD(Date fechaFacturacionD) {
		this.fechaFacturacionD = fechaFacturacionD;
	}


	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}


	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}


	public String getFechaFacturacion() {
		return fechaFacturacion;
	}


	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}
	
	
}