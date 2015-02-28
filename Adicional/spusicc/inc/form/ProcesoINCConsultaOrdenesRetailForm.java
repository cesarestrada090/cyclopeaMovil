package biz.belcorp.ssicc.web.spusicc.inc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoINCConsultaOrdenesRetailForm extends BaseProcesoForm
implements Serializable{
	
	private static final long serialVersionUID = -8835213647280127470L;
	private String oidControlCarga;	
	
	public String getOidControlCarga() {
		return oidControlCarga;
	}

	public void setOidControlCarga(String oidControlCarga) {
		this.oidControlCarga = oidControlCarga;
	}

}
