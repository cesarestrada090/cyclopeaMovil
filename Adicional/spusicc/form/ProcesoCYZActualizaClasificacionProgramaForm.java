package biz.belcorp.ssicc.web.spusicc.form;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoCYZActualizaClasificacionProgramaForm extends
        BaseProcesoForm {

	private static final long serialVersionUID = 1292564874974984932L;

	protected String codigoPrograma;
    
    protected String codigoProgramaCumpleanyos;
    
    protected String codigoPeriodo;
    
    protected String codigoProgramaWelcomePack;
    
    protected String codigoProgramaCumpleanyosConsultoras;
    
    /**
     * @return the codigoPrograma
     */
    public String getCodigoPrograma() {
        return codigoPrograma;
    }

    /**
     * @param codigoPrograma the codigoPrograma to set
     */
    public void setCodigoPrograma(String codigoPrograma) {
        this.codigoPrograma = codigoPrograma;
    }

    /**
     * @return the codigoPeriodo
     */
    public String getCodigoPeriodo() {
        return codigoPeriodo;
    }

    /**
     * @param codigoPeriodo the codigoPeriodo to set
     */
    public void setCodigoPeriodo(String codigoPeriodo) {
        this.codigoPeriodo = codigoPeriodo;
    }

	/**
	 * @return the codigoProgramaCumpleanyos
	 */
	public String getCodigoProgramaCumpleanyos() {
		return codigoProgramaCumpleanyos;
	}

	/**
	 * @param codigoProgramaCumpleanyos the codigoProgramaCumpleanyos to set
	 */
	public void setCodigoProgramaCumpleanyos(String codigoProgramaCumpleanyos) {
		this.codigoProgramaCumpleanyos = codigoProgramaCumpleanyos;
	}

	/**
	 * @return the codigoProgramaWelcomePack
	 */
	public String getCodigoProgramaWelcomePack() {
		return codigoProgramaWelcomePack;
	}

	/**
	 * @param codigoProgramaWelcomePack the codigoProgramaWelcomePack to set
	 */
	public void setCodigoProgramaWelcomePack(String codigoProgramaWelcomePack) {
		this.codigoProgramaWelcomePack = codigoProgramaWelcomePack;
	}

	/**
	 * @return the codigoProgramaCumpleanyosConsultoras
	 */
	public String getCodigoProgramaCumpleanyosConsultoras() {
		return codigoProgramaCumpleanyosConsultoras;
	}

	/**
	 * @param codigoProgramaCumpleanyosConsultoras the codigoProgramaCumpleanyosConsultoras to set
	 */
	public void setCodigoProgramaCumpleanyosConsultoras(
			String codigoProgramaCumpleanyosConsultoras) {
		this.codigoProgramaCumpleanyosConsultoras = codigoProgramaCumpleanyosConsultoras;
	}
}