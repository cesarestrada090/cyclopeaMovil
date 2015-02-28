package biz.belcorp.ssicc.web.spusicc.fac.form;

import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoFACCierreFacturacionSearchForm extends BaseSearchForm{
	
	private static final long serialVersionUID = -4900257294643313734L;
	
	
	private String codigoPais;
	private String campanhaProceso;
	private String fechaCierre;
	private Date fechaCierreDate;
	
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public String getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public String getCampanhaProceso() {
		return campanhaProceso;
	}

	public void setCampanhaProceso(String campanhaProceso) {
		this.campanhaProceso = campanhaProceso;
	}

	public Date getFechaCierreDate() {
		return fechaCierreDate;
	}

	public void setFechaCierreDate(Date fechaCierreDate) {
		this.fechaCierreDate = fechaCierreDate;
	}
	

}
