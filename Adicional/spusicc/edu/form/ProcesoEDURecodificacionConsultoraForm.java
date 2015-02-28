package biz.belcorp.ssicc.web.spusicc.edu.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoEDURecodificacionConsultoraForm extends BaseProcesoForm
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7988917188988185240L;

	private String codigoPais;

	private String codigoEmpresa;

	private String codigoConsultora;

	private String codigoConsultoraRecod;

	private String descripcionConsultora;

	public void reset() {
		codigoConsultora = codigoConsultoraRecod = "";
		codigoEmpresa = "";
	}

	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}



	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	public String getCodigoPais() {
		return codigoPais;
	}



	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getDescripcionConsultora() {
		return descripcionConsultora;
	}

	public void setDescripcionConsultora(String descripcionConsultora) {
		this.descripcionConsultora = descripcionConsultora;
	}


	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}

	public String getCodigoConsultoraRecod() {
		return codigoConsultoraRecod;
	}


	public void setCodigoConsultoraRecod(String codigoConsultoraRecod) {
		this.codigoConsultoraRecod = codigoConsultoraRecod;
	}

}
