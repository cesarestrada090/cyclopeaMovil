package biz.belcorp.ssicc.web.spusicc.app.form;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * The Class ConsultaAPPSecuenciaZonasForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 13/11/2014
 */
public class ConsultaAPPSecuenciaZonasForm extends BaseReporteForm {

	private static final long serialVersionUID = 1L;

	private String 	 codigoPais;
	private String[] regionList;

	public ConsultaAPPSecuenciaZonasForm() {
		super();
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the regionList
	 */
	public String[] getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList the regionList to set
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}
}