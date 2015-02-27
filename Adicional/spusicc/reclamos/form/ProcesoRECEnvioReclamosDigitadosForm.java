package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoRECEnvioReclamosDigitadosForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 	
 * @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva Iriarte</a>
 * 
 */

public class ProcesoRECEnvioReclamosDigitadosForm  extends BaseProcesoForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String codigoPeriodo;

	public void reset(ActionMapping mapping, HttpServletRequest request) {
	
	}

	public String getCodigoPais() {
		return codigoPais;
	}
	
	/**
     * @param codigoPais
     *            The codigoPais to set.
     */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
     * @param codigoPeriodo
     *            The codigoPeriodo to set.
     */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	}
