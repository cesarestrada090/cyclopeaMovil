package biz.belcorp.ssicc.web.spusicc.lideres.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoForm extends BaseProcesoForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoMarca;
	private String periodoProceso;
	private String codigoIdiomaISO;
	private String fechaProceso;
	private String tipoEvaluacion;
	
	public String getCodigoMarca() {
		return codigoMarca;
	}
	
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	
	public String getPeriodoProceso() {
		return periodoProceso;
	}
	
	public void setPeriodoProceso(String periodoProceso) {
		this.periodoProceso = periodoProceso;
	}
	
	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}
	
	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
	}
	
	public String getFechaProceso() {
		return fechaProceso;
	}
	
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
	
	public String getTipoEvaluacion() {
		return tipoEvaluacion;
	}
	
	public void setTipoEvaluacion(String tipoEvaluacion) {
		this.tipoEvaluacion = tipoEvaluacion;
	}
}
