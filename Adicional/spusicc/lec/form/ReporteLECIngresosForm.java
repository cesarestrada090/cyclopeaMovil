package biz.belcorp.ssicc.web.spusicc.lec.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


public class ReporteLECIngresosForm extends BaseReporteForm implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String campanyaProceso;
	private String grupoRegion;
	private String codigoRegion;
	private String[] region;
	private String tramo;
	
	
	public String getCodigoPais() {
		return codigoPais;
	}
	
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public String getCampanyaProceso() {
		return campanyaProceso;
	}
	
	public void setCampanyaProceso(String campanyaProceso) {
		this.campanyaProceso = campanyaProceso;
	}
	
	public String getGrupoRegion() {
		return grupoRegion;
	}
	
	public void setGrupoRegion(String grupoRegion) {
		this.grupoRegion = grupoRegion;
	}
	
	public String getCodigoRegion() {
		return codigoRegion;
	}
	
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	
	public String[] getRegion() {
		return region;
	}
	
	public void setRegion(String[] region) {
		this.region = region;
	}
	
	public String getTramo() {
		return tramo;
	}
	
	public void setTramo(String tramo) {
		this.tramo = tramo;
	}
}
