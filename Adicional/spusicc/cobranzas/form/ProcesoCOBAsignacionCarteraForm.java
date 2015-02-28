package biz.belcorp.ssicc.web.spusicc.cobranzas.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoCOBAsignacionCarteraForm extends BaseProcesoForm
implements Serializable{

	private static final long serialVersionUID = -6094022380262992783L;

	private String codigoPais;
	
	private String codigoSociedad;
		
	private String codigoEtapaDeuda;
	
	private String codigoPeriodo;
	
	private String codigoRegion;
	
	private String codigoZona;
	    	
	private String fechaGeneracionCartera;
	private Date fechaGeneracionCarteraDate;
	
	private String codigoModulo;
	
	private String codigoProceso;
	
	private String codigoUsuario;
	
	
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

	public String getcodigoEtapaDeuda() {
		return codigoEtapaDeuda;
	}

	public void setCodigoEtapaDeuda(String codigoEtapaDeuda) {
		this.codigoEtapaDeuda = codigoEtapaDeuda;
	}
	
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
	public String getCodigoRegion() {
		return codigoRegion;
	}

	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	public String getCodigoZona() {
		return codigoZona;
	}

	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	
	public String getFechaGeneracionCartera() {
		return fechaGeneracionCartera;
	}

	public void setFechaGeneracionCartera(String fechaGeneracionCartera) {
		this.fechaGeneracionCartera = fechaGeneracionCartera;
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

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public Date getFechaGeneracionCarteraDate() {
		return fechaGeneracionCarteraDate;
	}

	public void setFechaGeneracionCarteraDate(Date fechaGeneracionCarteraDate) {
		this.fechaGeneracionCarteraDate = fechaGeneracionCarteraDate;
	}
	
	

}
