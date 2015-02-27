package biz.belcorp.ssicc.web.spusicc.zon.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;




public class ConsultaZONNovedadesHistoricoForm extends BaseSearchForm implements Serializable{
	

	private static final long serialVersionUID = -6730191155285005397L;
	
	private String codigoPais;
    private String codigoOperacion;
    private String codigoRegion;
    private String codigoZona;
    private String estado;
    private String codigoCargo;
    private String codigoClienteBuscar;
    private String oidIdioma;
    private String codigoRol;
	private String codigoPerfil;
    
    //Popup Buscar Consultora
    private String nombreCliente;
	private String docCliente;
	private String numeroDocIdentidadBuscar;
    
		
	public ConsultaZONNovedadesHistoricoForm() {	

		this.codigoPais = "";
	    this.codigoOperacion="";
	    this.codigoRegion=this.codigoZona="";	    
	    this.estado = "";	    
	    this.codigoClienteBuscar = "";	    
	    this.nombreCliente = "";	    
	    this.docCliente = "";	    
	    this.numeroDocIdentidadBuscar = "";	    
	    this.codigoCargo = "";	    
	    this.codigoRol = "";		
		this.codigoPerfil = "";
	}
	
	
	public String getCodigoOperacion() {
		return codigoOperacion;
	}
	
	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
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

	
	public String getCodigoClienteBuscar() {
		return codigoClienteBuscar;
	}

	
	public void setCodigoClienteBuscar(String codigoClienteBuscar) {
		this.codigoClienteBuscar = codigoClienteBuscar;
	}

	
	public String getOidIdioma() {
		return oidIdioma;
	}

	
	public void setOidIdioma(String oidIdioma) {
		this.oidIdioma = oidIdioma;
	}

	
	public String getNombreCliente() {
		return nombreCliente;
	}

	
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	
	public String getDocCliente() {
		return docCliente;
	}

	
	public void setDocCliente(String docCliente) {
		this.docCliente = docCliente;
	}
	
	public String getNumeroDocIdentidadBuscar() {
		return numeroDocIdentidadBuscar;
	}
	
	
	public void setNumeroDocIdentidadBuscar(String numeroDocIdentidadBuscar) {
		this.numeroDocIdentidadBuscar = numeroDocIdentidadBuscar;
	}
	
	
	public String getEstado() {
		return estado;
	}
	
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	public String getCodigoCargo() {
		return codigoCargo;
	}
	
	
	public void setCodigoCargo(String codigoCargo) {
		this.codigoCargo = codigoCargo;
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

}
