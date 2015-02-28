package biz.belcorp.ssicc.web.spusicc.fac.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoFACEliminarTablasTemporalesForm extends BaseProcesoForm implements Serializable{

	private static final long serialVersionUID = -2071493872453065062L;
	
	private String codigoPais;

	
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

}
