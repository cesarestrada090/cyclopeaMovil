package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * <p>
 * <a href="MantenimientoOCRNuevoAlternativoAutomaticoForm.java.html"> <i>View Source</i>
 * </a>
 * </p>
 *
 * @author <a href="mailto:croman@belcorp.biz">Christian Roman</a>
 *
 */
public class MantenimientoOCRNuevoAlternativoAutomaticoForm extends BaseEditForm {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String codigoSAPPrincipal;
	private String codigoSAPAlternativo;
	/**
	
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais The codigoPais to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getCodigoSAPPrincipal() {
		return codigoSAPPrincipal;
	}
	/**
	 * @param codigoSAPPrincipal The codigoSAPPrincipal to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoSAPPrincipal(String codigoSAPPrincipal) {
		this.codigoSAPPrincipal = codigoSAPPrincipal;
	}
	public String getCodigoSAPAlternativo() {
		return codigoSAPAlternativo;
	}
	/**
	 * @param codigoSAPAlternativo The codigoSAPAlternativo to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoSAPAlternativo(String codigoSAPAlternativo) {
		this.codigoSAPAlternativo = codigoSAPAlternativo;
	}
	
	

	
}
