package biz.belcorp.ssicc.web.spusicc.cuentacorriente.form;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * The Class ProcesoCCCDepuracionPagosPorRegularizarForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 28/01/2015
 */
public class ProcesoCCCDepuracionPagosPorRegularizarForm extends BaseProcesoForm {
	
	private static final long serialVersionUID = 1L;

	private String codigoPais;
				
	private String diasAntiguedad;
					
	private String codigoUsuario;
								
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	
	/**
	 * @return the diasAntiguedad
	 */
	public String getDiasAntiguedad() {
		return diasAntiguedad;
	}

	/**
	 * @struts.validator type = "required"
	 * @param diasAntiguedad the diasAntiguedad to set
	 */
	public void setDiasAntiguedad(String diasAntiguedad) {
		this.diasAntiguedad = diasAntiguedad;
	}
	
	/**
	 * @return Returns the codigoUsuario.
	 */
	public String getcodigoUsuario() {
		return codigoUsuario;
	}
	
	/**
	 * @param codigoUsuario
	 *            The codigoUsuario to set.
	 * @struts.validator type = "required"
	 */
	public void setcodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}	
					
}
