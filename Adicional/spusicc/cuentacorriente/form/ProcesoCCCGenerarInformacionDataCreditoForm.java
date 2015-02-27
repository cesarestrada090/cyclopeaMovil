package biz.belcorp.ssicc.web.spusicc.cuentacorriente.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoCCCGenerarInformacionDataCreditoForm extends BaseProcesoForm
implements Serializable{

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	
	private String codigoSociedad;
	
	private String codigoModulo;
	
	private String codigoInterface;
	
	private String codigoPeriodoDesde;
				
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
	 * @return the codigoModulo
	 */
	public String getCodigoModulo() {
		return codigoModulo;
	}

	/**
	 * @param codigoModulo the codigoModulo to set
	 */
	public void setCodigoModulo(String codigoModulo) {
		this.codigoModulo = codigoModulo;
	}

	/**
	 * @return the codigoInterface
	 */
	public String getCodigoInterface() {
		return codigoInterface;
	}

	/**
	 * @param codigoInterface the codigoInterface to set
	 */
	public void setCodigoInterface(String codigoInterface) {
		this.codigoInterface = codigoInterface;
	}

	/**
	 * @return the codigoPeriodoDesde
	 */
	public String getCodigoPeriodoDesde() {
		return codigoPeriodoDesde;
	}

	/**
	 * @param codigoPeriodoDesde the codigoPeriodoDesde to set
	 */
	public void setCodigoPeriodoDesde(String codigoPeriodoDesde) {
		this.codigoPeriodoDesde = codigoPeriodoDesde;
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
