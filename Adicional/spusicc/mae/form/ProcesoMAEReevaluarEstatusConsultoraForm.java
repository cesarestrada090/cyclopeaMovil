package biz.belcorp.ssicc.web.spusicc.mae.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoMAEReevaluarEstatusConsultoraForm extends BaseProcesoForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8327800768995255471L;
	
	private String codigoPeriodo;
	private boolean mostrarBotonProcesar;
	
	
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	public boolean isMostrarBotonProcesar() {
		return mostrarBotonProcesar;
	}
	public void setMostrarBotonProcesar(boolean mostrarBotonProcesar) {
		this.mostrarBotonProcesar = mostrarBotonProcesar;
	}
	
}
