package biz.belcorp.ssicc.web.spusicc.comision.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar La Rosa</a><br>
 * 
 */
public class ConsultaCOMComisionGerenteRegionObjetivoForm extends BaseReporteForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String[] codigoComision;
	
	private String codigoPeriodoInicial;

	private String codigoPeriodoFinal;
	
	private String[] codigoRegion;



	/**
	 * @return the codigoPeriodoInicial
	 */
	public String getCodigoPeriodoInicial() {
		return codigoPeriodoInicial;
	}

	/**
	 * @param codigoPeriodoInicial the codigoPeriodoInicial to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPeriodoInicial(String codigoPeriodoInicial) {
		this.codigoPeriodoInicial = codigoPeriodoInicial;
	}

	/**
	 * @return the codigoPeriodoFinal
	 */
	public String getCodigoPeriodoFinal() {
		return codigoPeriodoFinal;
	}

	/**
	 * @param codigoPeriodoFinal the codigoPeriodoFinal to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPeriodoFinal(String codigoPeriodoFinal) {
		this.codigoPeriodoFinal = codigoPeriodoFinal;
	}

	/**
	 * @return the codigoComision
	 */
	public String[] getCodigoComision() {
		return codigoComision;
	}

	/**
	 * @param codigoComision the codigoComision to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoComision(String[] codigoComision) {
		this.codigoComision = codigoComision;
	}

	/**
	 * @return the codigoRegion
	 */
	public String[] getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}


		
}
