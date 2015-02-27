package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoSTOConsultaClientesForm extends BaseProcesoForm
implements Serializable{

	private static final long serialVersionUID = 173917224441129742L;
	
	private String tipoDocumento;
	
	private String numeroDocumento;
	private String selectedItems; 
	
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	public String getSelectedItems() {
		return selectedItems;
	}
	
	public void setSelectedItems(String selectedItems) {
		this.selectedItems = selectedItems;
	}
	
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	

}
