package biz.belcorp.ssicc.web.spusicc.men.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;



/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoMENEscaleraGananciaSearchForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>

 */
public class MantenimientoMENEscaleraGananciaSearchForm extends BaseSearchForm {

	private static final long serialVersionUID = 1L;	

	private String codigoPais;
	private String codigoEscalon;


	/* (non-Javadoc)
	 * @see org.apache.struts.validator.ValidatorForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	/*public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.codigoEscalon = "";
		this.selectedItems=null;
	}*/
	
	/**
	 * @return
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 */  
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoEscalon
	 */
	public String getCodigoEscalon() {
		return codigoEscalon;
	}

	/**
	 * @param codigoEscalon the codigoEscalon to set
	 */
	public void setCodigoEscalon(String codigoEscalon) {
		this.codigoEscalon = codigoEscalon;
	}



	
}
