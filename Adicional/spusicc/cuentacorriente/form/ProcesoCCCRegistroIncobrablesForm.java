package biz.belcorp.ssicc.web.spusicc.cuentacorriente.form;



import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;



public class ProcesoCCCRegistroIncobrablesForm extends BaseProcesoForm
		implements Serializable {
		
	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String documentoIdentidad;
	private boolean indicadorCompletarCerosNumDocumento;
	private String longitudNumeroDocIdentidad;
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the documentoIdentidad
	 */
	public String getDocumentoIdentidad() {
		return documentoIdentidad;
	}

	public void setDocumentoIdentidad(String documentoIdentidad) {
		this.documentoIdentidad = documentoIdentidad;
	}
	/**
	 * @return the indicadorCompletarCerosNumDocumento
	 */
	public boolean isIndicadorCompletarCerosNumDocumento() {
		return indicadorCompletarCerosNumDocumento;
	}
	/**
	 * @return the longitudNumeroDocIdentidad
	 */
	public String getLongitudNumeroDocIdentidad() {
		return longitudNumeroDocIdentidad;
	}
	/**
	 * @param indicadorCompletarCerosNumDocumento the indicadorCompletarCerosNumDocumento to set
	 */
	public void setIndicadorCompletarCerosNumDocumento(
			boolean indicadorCompletarCerosNumDocumento) {
		this.indicadorCompletarCerosNumDocumento = indicadorCompletarCerosNumDocumento;
	}
	/**
	 * @param longitudNumeroDocIdentidad the longitudNumeroDocIdentidad to set
	 */
	public void setLongitudNumeroDocIdentidad(String longitudNumeroDocIdentidad) {
		this.longitudNumeroDocIdentidad = longitudNumeroDocIdentidad;
	}
						
}
