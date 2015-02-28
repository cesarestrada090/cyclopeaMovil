package biz.belcorp.ssicc.web.spusicc.app.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


public class ProcesoAPPSecuenciarZonaTerritorioForm extends BaseSearchForm{

	private static final long serialVersionUID = -5501775830349894714L;
	
	private String 	 codigoPais;	
	private String[] regionList;
	private String[] secuencia;
	private String 	 indicadorSinSecuencia;

	
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String[] getRegionList() {
		return regionList;
	}

	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	public String[] getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(String[] secuencia) {
		this.secuencia = secuencia;
	}

	public String getIndicadorSinSecuencia() {
		return indicadorSinSecuencia;
	}

	public void setIndicadorSinSecuencia(String indicadorSinSecuencia) {
		this.indicadorSinSecuencia = indicadorSinSecuencia;
	}

}
