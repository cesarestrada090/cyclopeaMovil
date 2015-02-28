package biz.belcorp.ssicc.web.spusicc.cuentacorriente.form;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * The Class ProcesoCCCDepuracionSaldosMenoresDeudoresForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 28/01/2015
 */
public class ProcesoCCCDepuracionSaldosMenoresDeudoresForm extends BaseProcesoForm {		
	
	private static final long serialVersionUID = 1L;

	private String codigoPais;
			
	private String importeHasta;
	
	private String codigoPeriodoHasta;
					
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
	 * @return the importeHasta
	 */
	public String getImporteHasta() {
		return importeHasta;
	}

	/**
	 * @struts.validator type = "required"
	 * @param importeHasta the importeHasta to set
	 */
	public void setImporteHasta(String importeHasta) {
		this.importeHasta = importeHasta;
	}

	/**
	 * @return the codigoPeriodoHasta
	 */
	public String getCodigoPeriodoHasta() {
		return codigoPeriodoHasta;
	}

	/**
	 * @struts.validator type = "required"
	 * @param codigoPeriodoHasta the codigoPeriodoHasta to set
	 */
	public void setCodigoPeriodoHasta(String codigoPeriodoHasta) {
		this.codigoPeriodoHasta = codigoPeriodoHasta;
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
