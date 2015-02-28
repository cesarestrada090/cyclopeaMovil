package biz.belcorp.ssicc.web.spusicc.let.form;



import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoINCEjecutarProcesosInicioCampanaForm.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:jjrios@csigcomt.com">Jesse James Rios Franco</a>
 * 
 * @struts.form name = "procesoINCEjecutarProcesosInicioCampanaForm" extends = "BaseInterfazPaqueteForm"
 */

public class ProcesoINCEjecutarProcesosInicioCampanaForm extends BaseInterfazForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String codigoMarca;
	private String codigoCanal;
	private String codigoPeriodo;
	private String indicadorProceso;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.form.BaseInterfazForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
//	public void reset(ActionMapping mapping, HttpServletRequest request) {
//		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
//		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
//	}

	/**
	 * @return
	 */
	public String getIndicadorProceso() {
		return indicadorProceso;
	}

	/**
	 * @param indicadorProceso
	 */
	public void setIndicadorProceso(String indicadorProceso) {
		this.indicadorProceso = indicadorProceso;
	}

	/**
	 * @return
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}


	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}


	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}


	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
}