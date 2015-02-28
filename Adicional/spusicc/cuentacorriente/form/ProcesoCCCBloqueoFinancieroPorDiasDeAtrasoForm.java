package biz.belcorp.ssicc.web.spusicc.cuentacorriente.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoCCCBloqueoFinancieroPorDiasDeAtrasoForm extends BaseProcesoForm
implements Serializable{

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	
	private String codigoSociedad;
				
	private String numeroDiasAtraso;
	
	private String importeDesde;
							


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
	 * @return Returns the codigoSociedad.
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	/**
	 * @param codigoSociedad
	 *            The codigoSociedad to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}

	/**
	 * @return the numeroDiasAtraso
	 */
	public String getNumeroDiasAtraso() {
		return numeroDiasAtraso;
	}

	/**
	 * @param numeroDiasAtraso the numeroDiasAtraso to set
	 */
	public void setNumeroDiasAtraso(String numeroDiasAtraso) {
		this.numeroDiasAtraso = numeroDiasAtraso;
	}

	/**
	 * @return the importeDesde
	 */
	public String getImporteDesde() {
		return importeDesde;
	}

	/**
	 * @param importeDesde the importeDesde to set
	 */
	public void setImporteDesde(String importeDesde) {
		this.importeDesde = importeDesde;
	}				
		
}
