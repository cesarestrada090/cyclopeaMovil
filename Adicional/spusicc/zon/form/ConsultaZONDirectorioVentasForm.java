package biz.belcorp.ssicc.web.spusicc.zon.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class ConsultaZONDirectorioVentasForm extends BaseSearchForm implements Serializable{
	
	
	private static final long serialVersionUID = -5516800622685082946L;
	
	private String codigoPais;
	private String codigoCargo;
	private String codigoRol;
	private String codigoPerfil;
	private String codigoRegion;
	private String codigoZona;
	private String estado;
	private String[] estadoList;
	private String email;
	private String barrio;
	private String cub;
	private String resumenDetalle;
	private String tipoCargo;
	private String oidIdioma;
	private String codigoClienteBuscar;
	private String nombreCliente;
	private String numeroDocIdentidadBuscar;

	public ConsultaZONDirectorioVentasForm() {

		this.codigoPais = "";
		this.codigoCargo = "";
		this.codigoRol = "";
		this.codigoPerfil = "";
		this.codigoRegion = "";
		this.codigoZona = "";
		this.estado = "";
		this.estadoList = new String[4];
		this.email = "";
		this.barrio = "";
		this.cub = "";
		this.resumenDetalle = "";		
		this.tipoCargo = "";
		this.codigoClienteBuscar = "";
		this.nombreCliente = "";
		this.numeroDocIdentidadBuscar = "";

	}
	
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

	
	public String getCodigoZona() {
		return codigoZona;
	}

	
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	
	public String getCodigoCargo() {
		return codigoCargo;
	}

	
	public void setCodigoCargo(String codigoCargo) {
		this.codigoCargo = codigoCargo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public String getCub() {
		return cub;
	}

	public void setCub(String cub) {
		this.cub = cub;
	}

	public String[] getEstadoList() {
		return estadoList;
	}

	public void setEstadoList(String[] estadoList) {
		this.estadoList = estadoList;
	}

	public String getCodigoRol() {
		return codigoRol;
	}

	public void setCodigoRol(String codigoRol) {
		this.codigoRol = codigoRol;
	}

	public String getCodigoPerfil() {
		return codigoPerfil;
	}

	public void setCodigoPerfil(String codigoPerfil) {
		this.codigoPerfil = codigoPerfil;
	}

	public String getResumenDetalle() {
		return resumenDetalle;
	}

	public void setResumenDetalle(String resumenDetalle) {
		this.resumenDetalle = resumenDetalle;
	}

	public String getTipoCargo() {
		return tipoCargo;
	}

	public void setTipoCargo(String tipoCargo) {
		this.tipoCargo = tipoCargo;
	}

	public String getCodigoClienteBuscar() {
		return codigoClienteBuscar;
	}

	public void setCodigoClienteBuscar(String codigoClienteBuscar) {
		this.codigoClienteBuscar = codigoClienteBuscar;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getNumeroDocIdentidadBuscar() {
		return numeroDocIdentidadBuscar;
	}

	public void setNumeroDocIdentidadBuscar(String numeroDocIdentidadBuscar) {
		this.numeroDocIdentidadBuscar = numeroDocIdentidadBuscar;
	}

	public String getOidIdioma() {
		return oidIdioma;
	}

	public void setOidIdioma(String oidIdioma) {
		this.oidIdioma = oidIdioma;
	}
}
