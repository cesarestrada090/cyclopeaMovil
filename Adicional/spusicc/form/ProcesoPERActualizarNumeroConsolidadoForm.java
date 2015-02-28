package biz.belcorp.ssicc.web.spusicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoPERActualizarNumeroConsolidadoForm extends BaseProcesoForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7947487274917835882L;

	private String codigoPais;
	private String codigoCanal;
	private String codigoAcceso;
	private String codigoSubacceso;
	private String codigoGrupoProceso;

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoCanal() {
		return codigoCanal;
	}

	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	public String getCodigoAcceso() {
		return codigoAcceso;
	}

	public void setCodigoAcceso(String codigoAcceso) {
		this.codigoAcceso = codigoAcceso;
	}

	public String getCodigoSubacceso() {
		return codigoSubacceso;
	}

	public void setCodigoSubacceso(String codigoSubacceso) {
		this.codigoSubacceso = codigoSubacceso;
	}

	public String getCodigoGrupoProceso() {
		return codigoGrupoProceso;
	}

	public void setCodigoGrupoProceso(String codigoGrupoProceso) {
		this.codigoGrupoProceso = codigoGrupoProceso;
	}
	
	
}
