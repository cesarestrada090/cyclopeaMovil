package biz.belcorp.ssicc.web.spusicc.comision.retail.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoRETAsignacionVentasRetailForm extends BaseProcesoForm
implements Serializable {
	
	
	private static final long serialVersionUID = 8320104535254692748L;
	
	private String fechaInicial;
	private String fechaFinal;
	private Date fechaInicialDate;
	private Date fechaFinalDate;
	
	
	public String getFechaInicial() {
		return fechaInicial;
	}
	
	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	
	public String getFechaFinal() {
		return fechaFinal;
	}
	
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Date getFechaInicialDate() {
		return fechaInicialDate;
	}

	public void setFechaInicialDate(Date fechaInicialDate) {
		this.fechaInicialDate = fechaInicialDate;
	}

	public Date getFechaFinalDate() {
		return fechaFinalDate;
	}

	public void setFechaFinalDate(Date fechaFinalDate) {
		this.fechaFinalDate = fechaFinalDate;
	}
	

}
