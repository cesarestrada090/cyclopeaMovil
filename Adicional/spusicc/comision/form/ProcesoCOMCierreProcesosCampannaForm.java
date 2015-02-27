package biz.belcorp.ssicc.web.spusicc.comision.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * 
 * 
 * @struts.form name = "procesoCOMComisionGenerarArchivoNominaForm"
 */
public class ProcesoCOMCierreProcesosCampannaForm extends BaseProcesoForm
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7289479864856482287L;
	private String codigoPais;
	private String codigoPeriodo;
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
	
	
}
