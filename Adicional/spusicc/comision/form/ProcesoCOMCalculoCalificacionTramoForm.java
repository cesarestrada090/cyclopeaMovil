package biz.belcorp.ssicc.web.spusicc.comision.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoCOMCalculoCalificacionTramoForm extends BaseProcesoForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7734952745911084230L;
	
	private String codigoMarca;
	
	private String codigoCanal;
	
	private String anioInicial;
	
	private String codigoTramo;
	
	private String tipoComisionista;

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

	public String getAnioInicial() {
		return anioInicial;
	}

	public void setAnioInicial(String anioInicial) {
		this.anioInicial = anioInicial;
	}

	public String getCodigoTramo() {
		return codigoTramo;
	}

	public void setCodigoTramo(String codigoTramo) {
		this.codigoTramo = codigoTramo;
	}

	public String getTipoComisionista() {
		return tipoComisionista;
	}

	public void setTipoComisionista(String tipoComisionista) {
		this.tipoComisionista = tipoComisionista;
	}

	
}
