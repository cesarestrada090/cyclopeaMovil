package biz.belcorp.ssicc.web.spusicc.cuentacorriente.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoCCCCierreFacturacionForm extends BaseProcesoForm
implements Serializable{

	private static final long serialVersionUID = -7826707111165570291L;
	
	private String codigoPais;
	
	private String codigoSistema;
	
	
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
