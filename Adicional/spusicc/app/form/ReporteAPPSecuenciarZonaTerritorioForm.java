package biz.belcorp.ssicc.web.spusicc.app.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteAPPSecuenciarZonaTerritorioForm extends BaseReporteForm	implements Serializable{
	
	
	private static final long serialVersionUID = -1950858535485519889L;
	
	private String codigoPais;	
	
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
}
