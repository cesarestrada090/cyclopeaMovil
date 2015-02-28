package biz.belcorp.ssicc.web.spusicc.cuentacorriente.form;



import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;





public class ProcesoCCCDepuracionDeudasIncobrablesForm extends BaseProcesoForm
		implements Serializable {
		
	private static final long serialVersionUID = 1L;

	private String codigoPais;
				
	private String codigoPeriodoHasta;
	
	private String importeDesde;
					
	private String codigoUsuario;
					


	public String getCodigoPais() {
		return codigoPais;
	}


	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
		
	/**
	 * @return the importeDesde
	 */
	public String getImporteDesde() {
		return importeDesde;
	}


	public void setImporteDesde(String importeDesde) {
		this.importeDesde = importeDesde;
	}

	/**
	 * @return the codigoPeriodoHasta
	 */
	public String getCodigoPeriodoHasta() {
		return codigoPeriodoHasta;
	}


	public void setCodigoPeriodoHasta(String codigoPeriodoHasta) {
		this.codigoPeriodoHasta = codigoPeriodoHasta;
	}
	
	/**
	 * @return Returns the codigoUsuario.
	 */
	public String getcodigoUsuario() {
		return codigoUsuario;
	}
	

	public void setcodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
						
}
