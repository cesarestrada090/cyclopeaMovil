package biz.belcorp.ssicc.web.spusicc.lec.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

public class ProcesoLECCalcularRecuperacionForm extends BaseInterfazForm implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String codigoIdiomaISO;
	private String periodoProceso;
	private String indicadorProceso;
	
	private String codigoPeriodoActual;
	private String habilitadorHidden;
	private String indicadorModEducacion;
	/* INI JJ  PER-SiCC-2012-0361 */
	private String indTipoValid;
	
	private String codigoPrograma;
	private String frecuenciaSGR;	
	
	private String codigoConexionExterna;
	private String grupoRegion;
	
	private String descPrograma;
	private String codigoRegion;
	
	private String codigoPeriodoRecaudo;
	private String habilitadorRecaudoHidden;
	private String habilitadorPeriodo;
		
	/**
	 * @return
	 */
	public String getIndTipoValid() {
		return indTipoValid;
	}

	/**
	 * @param indTipoValid
	 */
	public void setIndTipoValid(String indTipoValid) {
		this.indTipoValid = indTipoValid;
	}
	/* FIN JJ  PER-SiCC-2012-0361 */
	/**
	 * @return
	 */
	public String getIndicadorModEducacion() {
		return indicadorModEducacion;
	}

	/**
	 * @param indicadorModEducacion
	 */
	public void setIndicadorModEducacion(String indicadorModEducacion) {
		this.indicadorModEducacion = indicadorModEducacion;
	}

	/**
	 * @return
	 */
	public String getCodigoPeriodoActual() {
		return codigoPeriodoActual;
	}

	/**
	 * @param codigoPeriodoActual
	 */
	public void setCodigoPeriodoActual(String codigoPeriodoActual) {
		this.codigoPeriodoActual = codigoPeriodoActual;
	}

	/**
	 * @return
	 */
	public String getHabilitadorHidden() {
		return habilitadorHidden;
	}

	/**
	 * @param habilitadorHidden
	 */
	public void setHabilitadorHidden(String habilitadorHidden) {
		this.habilitadorHidden = habilitadorHidden;
	}

	/**
	 * @return Returns the codigoIdiomaISO.
	 */
	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}

	/**
	 * @param codigoIdiomaISO
	 *            The codigoIdiomaISO to set.
	 */
	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the periodoProceso
	 */
	public String getPeriodoProceso() {
		return periodoProceso;
	}

	/**
	 * @param periodoProceso
	 * @struts.validator type="required"
	 */
	public void setPeriodoProceso(String periodoProceso) {
		this.periodoProceso = periodoProceso;
	}

	/**
	 * @return Returns the indicadorProceso
	 */
	public String getIndicadorProceso() {
		return indicadorProceso;
	}

	/**
	 * @param indicadorProceso
	 */
	public void setIndicadorProceso(String indicadorProceso) {
		this.indicadorProceso = indicadorProceso;
	}

	/**
	 * @return the codigoPrograma
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}

	/**
	 * @param codigoPrograma the codigoPrograma to set
	 * @struts.validator type="required"
	 */
	
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}

	/**
	 * @return the frecuenciaSGR
	 */
	public String getFrecuenciaSGR() {
		return frecuenciaSGR;
	}

	/**
	 * @param frecuenciaSGR the frecuenciaSGR to set
	 */
	public void setFrecuenciaSGR(String frecuenciaSGR) {
		this.frecuenciaSGR = frecuenciaSGR;
	}

	/**
	 * @return the codigoConexionExterna
	 */
	public String getCodigoConexionExterna() {
		return codigoConexionExterna;
	}

	/**
	 * @param codigoConexionExterna the codigoConexionExterna to set
	 */
	public void setCodigoConexionExterna(String codigoConexionExterna) {
		this.codigoConexionExterna = codigoConexionExterna;
	}
	
	/**
	 * @return the grupoRegion
	 */
	public String getGrupoRegion() {
		return grupoRegion;
	}
	
	/**
	 * @param grupoRegion the grupoRegion to set
	 */
	public void setGrupoRegion(String grupoRegion) {
		this.grupoRegion = grupoRegion;
	}

	/**
	 * @return the descPrograma
	 */
	public String getDescPrograma() {
		return descPrograma;
	}

	/**
	 * @param descPrograma the descPrograma to set
	 */
	public void setDescPrograma(String descPrograma) {
		this.descPrograma = descPrograma;
	}

	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return the codigoPeriodoRecaudo
	 */
	public String getCodigoPeriodoRecaudo() {
		return codigoPeriodoRecaudo;
	}

	/**
	 * @param codigoPeriodoRecaudo the codigoPeriodoRecaudo to set
	 * @struts.validator type="required"
	 */
	public void setCodigoPeriodoRecaudo(String codigoPeriodoRecaudo) {
		this.codigoPeriodoRecaudo = codigoPeriodoRecaudo;
	}

	/**
	 * @return the habilitadorRecaudoHidden
	 */
	public String getHabilitadorRecaudoHidden() {
		return habilitadorRecaudoHidden;
	}

	/**
	 * @param habilitadorRecaudoHidden the habilitadorRecaudoHidden to set
	 */
	public void setHabilitadorRecaudoHidden(String habilitadorRecaudoHidden) {
		this.habilitadorRecaudoHidden = habilitadorRecaudoHidden;
	}

	/**
	 * @return the habilitadorPeriodo
	 */
	public String getHabilitadorPeriodo() {
		return habilitadorPeriodo;
	}

	/**
	 * @param habilitadorPeriodo the habilitadorPeriodo to set
	 */
	public void setHabilitadorPeriodo(String habilitadorPeriodo) {
		this.habilitadorPeriodo = habilitadorPeriodo;
	}	
}
