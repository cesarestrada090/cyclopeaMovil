package biz.belcorp.ssicc.web.spusicc.sms.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoSMSSearchForm extends BaseSearchForm{

	private static final long serialVersionUID = -9164288763106369797L;	
	
	private String codigoPais;
	private String oidTipoCliente;
	private String oidSubTipoCliente;
	private String oidTipoClasificacion;
	private String oidClasificacion;	
	private String codigoRegion;
	private String codigoZona;
	private String codigoPeriodo;
	private String codigoSeccion;
	private String activacion;
	
	private boolean indBusqueda;
		
	public MantenimientoSMSSearchForm(){
		this.indBusqueda = false;
	}
	
	
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getOidTipoCliente() {
		return oidTipoCliente;
	}

	public void setOidTipoCliente(String oidTipoCliente) {
		this.oidTipoCliente = oidTipoCliente;
	}

	public String getOidSubTipoCliente() {
		return oidSubTipoCliente;
	}

	public void setOidSubTipoCliente(String oidSubTipoCliente) {
		this.oidSubTipoCliente = oidSubTipoCliente;
	}

	public String getOidTipoClasificacion() {
		return oidTipoClasificacion;
	}

	public void setOidTipoClasificacion(String oidTipoClasificacion) {
		this.oidTipoClasificacion = oidTipoClasificacion;
	}

	public String getOidClasificacion() {
		return oidClasificacion;
	}

	public void setOidClasificacion(String oidClasificacion) {
		this.oidClasificacion = oidClasificacion;
	}

	public String getCodigoZona() {
		return codigoZona;
	}

	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	public String getCodigoRegion() {
		return codigoRegion;
	}

	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	public String getCodigoSeccion() {
		return codigoSeccion;
	}

	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}

	public boolean isIndBusqueda() {
		return indBusqueda;
	}

	public void setIndBusqueda(boolean indBusqueda) {
		this.indBusqueda = indBusqueda;
	}

	public String getActivacion() {
		return activacion;
	}

	public void setActivacion(String activacion) {
		this.activacion = activacion;
	}
}
