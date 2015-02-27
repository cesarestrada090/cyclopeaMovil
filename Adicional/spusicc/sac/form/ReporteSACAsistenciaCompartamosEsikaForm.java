/*
 * 
 */
package biz.belcorp.ssicc.web.spusicc.sac.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

// TODO: Auto-generated Javadoc
/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ReporteSACAsistenciaCompartamosEsikaForm.java.html"> <i>View Source</i> </a>
 * </p>
 *
 * @author Belcorp
 * @version 1.0
 * 13/11/2014
 */
public class ReporteSACAsistenciaCompartamosEsikaForm extends BaseReporteForm  implements Serializable {

	/** The codigo pais. */
	private String codigoPais;	
	
	/**
	 * Gets the codigo pais.
	 *
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * Sets the codigo pais.
	 *
	 * @param codigoPais The codigoPais to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
			
}
