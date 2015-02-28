package biz.belcorp.ssicc.web.spusicc.app.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class ProcesoAPPSecuenciarZonaTerritorioPopupForm extends BaseSearchForm{
	
	private static final long serialVersionUID = 7374941538213443397L;
	
	private String codigoPais;
	private String codigoRegion;
	private String descripcionZona;
	private String codigoZona;
	private String[] secuencia;
	private String indicadorSinSecuencia;
	private String msgProceso;
	private String flgProceso;
	private String rolGuardar;
	private String rolEjecutar;

	
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoRegion() {
		return codigoRegion;
	}

	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	public String getDescripcionZona() {
		return descripcionZona;
	}

	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}

	public String getCodigoZona() {
		return codigoZona;
	}
	
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	public String[] getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(String[] secuencia) {
		this.secuencia = secuencia;
	}

	public String getIndicadorSinSecuencia() {
		return indicadorSinSecuencia;
	}

	public void setIndicadorSinSecuencia(String indicadorSinSecuencia) {
		this.indicadorSinSecuencia = indicadorSinSecuencia;
	}

	public String getMsgProceso() {
		return msgProceso;
	}

	public void setMsgProceso(String msgProceso) {
		this.msgProceso = msgProceso;
	}

	public String getFlgProceso() {
		return flgProceso;
	}

	public void setFlgProceso(String flgProceso) {
		this.flgProceso = flgProceso;
	}

	public String getRolGuardar() {
		return rolGuardar;
	}

	public void setRolGuardar(String rolGuardar) {
		this.rolGuardar = rolGuardar;
	}

	public String getRolEjecutar() {
		return rolEjecutar;
	}

	public void setRolEjecutar(String rolEjecutar) {
		this.rolEjecutar = rolEjecutar;
	}
}
