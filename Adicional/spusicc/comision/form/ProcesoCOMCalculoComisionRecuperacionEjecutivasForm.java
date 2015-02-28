package biz.belcorp.ssicc.web.spusicc.comision.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoCOMCalculoComisionRecuperacionEjecutivasForm extends BaseProcesoForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6164203708906011058L;
	
	private String codigoMarca;
	
	private String codigoCanal;
	
	private String codigoPeriodo;
	
	private String tipoComisionista;
	
	private String codComision;
	
	private String anioInicTramo;
	
	private String codTramo;

	public String getCodigoMarca() {
		return codigoMarca;
	}

	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	public String getCodigoCanal() {
		return codigoCanal;
	}

	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public String getTipoComisionista() {
		return tipoComisionista;
	}

	public void setTipoComisionista(String tipoComisionista) {
		this.tipoComisionista = tipoComisionista;
	}

	public String getCodComision() {
		return codComision;
	}

	public void setCodComision(String codComision) {
		this.codComision = codComision;
	}

	public String getAnioInicTramo() {
		return anioInicTramo;
	}

	public void setAnioInicTramo(String anioInicTramo) {
		this.anioInicTramo = anioInicTramo;
	}

	public String getCodTramo() {
		return codTramo;
	}

	public void setCodTramo(String codTramo) {
		this.codTramo = codTramo;
	}
	
}
