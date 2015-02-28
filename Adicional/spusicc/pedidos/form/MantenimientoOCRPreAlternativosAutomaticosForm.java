package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;



/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoOCRPreAlternativosAutomaticosForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:croman@csigcomt.com">Cristhian Roman</a>
 * 
 * @struts.form name = "mantenimientoOCRPreAlternativosAutomaticosForm"
 *                      
 */
public class MantenimientoOCRPreAlternativosAutomaticosForm extends BaseSearchForm {

	private static final long serialVersionUID = 1L;	

	private String codigoPais;
	private String codigoPeriodo;
	private String codigoSAPPrincipal;
	private String codigoSAPAlternativo;

	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	public String getCodigoSAPPrincipal() {
		return codigoSAPPrincipal;
	}
	public void setCodigoSAPPrincipal(String codigoSAPPrincipal) {
		this.codigoSAPPrincipal = codigoSAPPrincipal;
	}
	public String getCodigoSAPAlternativo() {
		return codigoSAPAlternativo;
	}
	public void setCodigoSAPAlternativo(String codigoSAPAlternativo) {
		this.codigoSAPAlternativo = codigoSAPAlternativo;
	}
	
	
}
