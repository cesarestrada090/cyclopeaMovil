package biz.belcorp.ssicc.web.spusicc.ape.form;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoAPEGenerarOlasForm extends BaseProcesoForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -282759583675824115L;
	private String codigoPais;

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	
}
