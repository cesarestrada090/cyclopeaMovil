package biz.belcorp.ssicc.web.spusicc.comision.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class ConsultaCOMComisionPerdidasSearchForm extends BaseSearchForm implements Serializable{
	
	private static final long serialVersionUID = -4064395390501600900L;
	
	private String codigoMarca;
	private String descripcionMarca;
	private String codigoCanal;
	private String descripcionCanal;
	private String codigoComision;
	private String descripcionComision;
	private String anhoProceso;
	private String codigoRangoPeriodo;
	private String tipoGerente;
	
	public String getTipoGerente() {
		return tipoGerente;
	}

	public void setTipoGerente(String tipoGerente) {
		this.tipoGerente = tipoGerente;
	}

	public String getAnhoProceso() {
		return anhoProceso;
	}

	public void setAnhoProceso(String anhoProceso) {
		this.anhoProceso = anhoProceso;
	}

	public String getCodigoRangoPeriodo() {
		return codigoRangoPeriodo;
	}

	public void setCodigoRangoPeriodo(String codigoRangoPeriodo) {
		this.codigoRangoPeriodo = codigoRangoPeriodo;
	}				
	
	public String getCodigoCanal() {
		return codigoCanal;
	}

	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	public String getCodigoMarca() {
		return codigoMarca;
	}

	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	
	public String getDescripcionCanal() {
		return descripcionCanal;
	}

	public void setDescripcionCanal(String descripcionCanal) {
		this.descripcionCanal = descripcionCanal;
	}

	
	public String getDescripcionMarca() {
		return descripcionMarca;
	}

	public void setDescripcionMarca(String descripcionMarca) {
		this.descripcionMarca = descripcionMarca;
	}

	public String getCodigoComision() {
		return codigoComision;
	}

	public void setCodigoComision(String codigoComision) {
		this.codigoComision = codigoComision;
	}

	public String getDescripcionComision() {
		return descripcionComision;
	}

	public void setDescripcionComision(String descripcionComision) {
		this.descripcionComision = descripcionComision;
	}

}
