package biz.belcorp.ssicc.web.spusicc.gen.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;


public class ProcesoGENEliminarBuzonMensajeForm extends BaseInterfazForm
implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoIdiomaISO;
	private String periodoProceso;
	private String fechaProceso;
	private Date fechaProcesoDate;
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
	
	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}
	
	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
	}
	
	public String getPeriodoProceso() {
		return periodoProceso;
	}
	
	public void setPeriodoProceso(String periodoProceso) {
		this.periodoProceso = periodoProceso;
	}
	
	public String getFechaProceso() {
		return fechaProceso;
	}
	
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
	
	public String getIndicadorProceso() {
		return indicadorProceso;
	}
	
	public void setIndicadorProceso(String indicadorProceso) {
		this.indicadorProceso = indicadorProceso;
	}
	
	public String getCodigoPeriodoActual() {
		return codigoPeriodoActual;
	}
	
	public void setCodigoPeriodoActual(String codigoPeriodoActual) {
		this.codigoPeriodoActual = codigoPeriodoActual;
	}
	
	public String getHabilitadorHidden() {
		return habilitadorHidden;
	}
	
	public void setHabilitadorHidden(String habilitadorHidden) {
		this.habilitadorHidden = habilitadorHidden;
	}
	
	public String getIndicadorModEducacion() {
		return indicadorModEducacion;
	}
	
	public void setIndicadorModEducacion(String indicadorModEducacion) {
		this.indicadorModEducacion = indicadorModEducacion;
	}
	
	public String getIndTipoValid() {
		return indTipoValid;
	}
	
	public void setIndTipoValid(String indTipoValid) {
		this.indTipoValid = indTipoValid;
	}
	
	public String getCodigoPrograma() {
		return codigoPrograma;
	}
	
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}
	
	public String getFrecuenciaSGR() {
		return frecuenciaSGR;
	}
	
	public void setFrecuenciaSGR(String frecuenciaSGR) {
		this.frecuenciaSGR = frecuenciaSGR;
	}
	
	public String getCodigoConexionExterna() {
		return codigoConexionExterna;
	}
	
	public void setCodigoConexionExterna(String codigoConexionExterna) {
		this.codigoConexionExterna = codigoConexionExterna;
	}
	
	public String getGrupoRegion() {
		return grupoRegion;
	}
	
	public void setGrupoRegion(String grupoRegion) {
		this.grupoRegion = grupoRegion;
	}

	public Date getFechaProcesoDate() {
		return fechaProcesoDate;
	}

	public void setFechaProcesoDate(Date fechaProcesoDate) {
		this.fechaProcesoDate = fechaProcesoDate;
	}
}
