package biz.belcorp.ssicc.web.spusicc.comision.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * 
 * @struts.form name = "procesoCOMComisionGenerarArchivoNominaForm"
 */
public class ProcesoCOMComisionGenerarArchivoNominaForm extends BaseReporteForm
		implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String[] codigoComision;
	
	private String codigoPeriodoInicial;

	private String codigoPeriodoFinal;
	
	private String tipoComision;

	private String[] codigoComisionAnterior;
	
	private String cargaCombo;


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
	 * @return the tipoComision
	 */
	public String getTipoComision() {
		return tipoComision;
	}

	/**
	 * @param tipoComision the tipoComision to set
	 * @struts.validator type = "required"
	 */
	public void setTipoComision(String tipoComision) {
		this.tipoComision = tipoComision;
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
	 * @return the codigoComisionAnterior
	 */
	public String[] getCodigoComisionAnterior() {
		return codigoComisionAnterior;
	}

	/**
	 * @param codigoComisionAnterior the codigoComisionAnterior to set
	 */
	public void setCodigoComisionAnterior(String[] codigoComisionAnterior) {
		this.codigoComisionAnterior = codigoComisionAnterior;
	}

	/**
	 * @return the cargaCombo
	 */
	public String getCargaCombo() {
		return cargaCombo;
	}

	/**
	 * @param cargaCombo the cargaCombo to set
	 */
	public void setCargaCombo(String cargaCombo) {
		this.cargaCombo = cargaCombo;
	}
	

		
}
