package biz.belcorp.ssicc.web.spusicc.cobranzas.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoCOBActualizarCarteraForm extends BaseProcesoForm
implements Serializable{

	private static final long serialVersionUID = -1285416904321837158L;
	
	private String codigoPais;
	
	private String codigoSociedad;
					
	private String codigoUsuario;
	
	private String codigoModulo;
	
	private String codigoProceso;
					
	
	
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}
	
	public String getcodigoUsuario() {
		return codigoUsuario;
	}

	public void setcodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

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
