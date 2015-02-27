package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class ProcesoRUVEliminacionRUVOtrosCanalesForm extends BaseSearchForm{

	private static final long serialVersionUID = -1497431445529622887L;
	
	private String codigoPais;
	private String codigoSubAcceso;
	private String fechaInicio;
	private String fechaFin;
	
	private Date fechaInicioDate;
	private Date fechaFinDate;
	
	
	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public String getCodigoSubAcceso() {
		return codigoSubAcceso;
	}
	
	public void setCodigoSubAcceso(String codigoSubAcceso) {
		this.codigoSubAcceso = codigoSubAcceso;
	}
	
	public String getFechaInicio() {
		return fechaInicio;
	}
	
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public String getFechaFin() {
		return fechaFin;
	}
	
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicioDate() {
		return fechaInicioDate;
	}

	public void setFechaInicioDate(Date fechaInicioDate) {
		this.fechaInicioDate = fechaInicioDate;
	}

	public Date getFechaFinDate() {
		return fechaFinDate;
	}

	public void setFechaFinDate(Date fechaFinDate) {
		this.fechaFinDate = fechaFinDate;
	}
	
}
