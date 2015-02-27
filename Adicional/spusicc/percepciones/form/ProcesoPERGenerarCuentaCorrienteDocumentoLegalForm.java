package biz.belcorp.ssicc.web.spusicc.percepciones.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoPERGenerarCuentaCorrienteDocumentoLegalForm extends BaseProcesoForm
implements Serializable{

	private static final long serialVersionUID = -3100021931875162172L;

	private String codigoModulo;
	
	private String codigoProceso;			
	
	
	public String getCodigoModulo() {
		return codigoModulo;
	}

	public void setCodigoModulo(String codigoModulo) {
		this.codigoModulo = codigoModulo;
	}

	public String getCodigoProceso() {
		return codigoProceso;
	}

	public void setCodigoProceso(String codigoProceso) {
		this.codigoProceso = codigoProceso;
	}

}
