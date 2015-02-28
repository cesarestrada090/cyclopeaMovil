package biz.belcorp.ssicc.web.spusicc.zon.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoZONEnviarEntidadesDirectorioForm extends BaseProcesoForm
implements Serializable{

	private static final long serialVersionUID = -3352840876062259507L;

	private String codigoPais;
	
	private String codigoSistema;
	
   
	protected String[] selectedItems = {};


	public String[] getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}
	
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public String getCodigoSistema() {
		return codigoSistema;
	}

	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}  
	
}
