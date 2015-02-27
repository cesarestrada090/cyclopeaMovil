package biz.belcorp.ssicc.web.spusicc.cobranzas.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


/**
 * TODO Include class description here.
 * 
 * <p> 
 * <a href="ConsultaCOBTelecobroForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 	
 * @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva Iriarte</a>
 * 
 */

public class ConsultaCOBTelecobroForm extends BaseSearchForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 493864535211160012L;

	private String codigoPais;
	private String codigoPeriodo;
	private String codigoSociedad;	
	private String codigoEtapa;
	private String codigoCobrador;
	private String departamento;
	private String provincia;
	private String distrito;
	private String codigoRegion;
	private String codigoZona;
	private String codigoCartera;
	private String codigoEtapaConsultora;
	private String codigoConsultora;
	private String nombreConsultora;
	private String tiempoMora;
	private String fechaAsignacion;
	private String fechaCierre;
	private String deudaTotal;
	private String deudaCartera;	
	private String tipoAccion;
	private String accionCobranza;
    private String observaciones;
	private String fechaPago;
	private String importePago;
	private String regionTab;
	private String zonaTab;
	private String seccionTab;
	private String territorioTab;
	private String campanaIngresoTab;
	private String direccionTab;
	private String referenciaTab;
	private String fechaNacimientoTab;
	private String fechaIngresoTab;
	private String statusTab;
	private String ultimoPedidoTab;
	private String[] telefonosTab;	
	private String referencias;
	private String nuevaDireccionTab;
	private String nuevoTelefonoFijoTab;
	private String nuevoTelefonoMovilTab;
	
	private String telefonoFijo;
	private String telefonoMovil;
	private String telefonoTrabajo;
	
	private String  tabSeleccionCurso;
	
	private int indiceSearch;
	private int indice;
	private int tamano;
	
	private String criterioFiltro;
	private String gestionFiltro;
	private String criterioOrdenamiento;
	
	private String codigoSociedadSearch;
	private String codigoRegionSearch;
	private String codigoZonaSearch;
	private String codigoEtapaSearch;
	private String codigoCobradorSearch;
	private String codigoConsultoraSearch;
	private String numeroDocumentoSearch;
	
	
	public void reset() {
		this.codigoPais=null;
		this.codigoPeriodo=null;
		this.codigoSociedad=null;
		this.codigoEtapa=null;
		this.codigoCobrador=null;
		this.departamento=null;
		this.provincia=null;
		this.departamento=null;
		this.codigoRegion=null;
		this.codigoZona=null;
		this.codigoCartera=null;
		this.codigoEtapaConsultora=null;
		this.codigoConsultora=null;
		this.codigoConsultoraSearch=null;
		this.nombreConsultora=null;
		this.tiempoMora=null;
		this.fechaAsignacion=null;
		this.fechaCierre=null;
		this.deudaCartera=null;
		this.deudaTotal=null;
		this.tipoAccion=null;
		this.accionCobranza=null;			
		this.observaciones=null;
		this.fechaPago=null;
		this.importePago=null;
		this.regionTab=null;
		this.zonaTab=null;
		this.seccionTab=null;
		this.territorioTab=null;
		this.campanaIngresoTab=null;
		this.statusTab=null;
		this.direccionTab=null;
		this.ultimoPedidoTab=null;
		this.referencias=null;
		this.telefonosTab=null;
		this.telefonoFijo=null;
		this.telefonoMovil=null;
		this.telefonoTrabajo=null;
		this.tabSeleccionCurso=null;
		this.indice=0;
		this.tamano=0;
		this.numeroDocumentoSearch=null;
				
	}
	
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	public String getCodigoSociedad() {
		return codigoSociedad;
	}
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}
	public String getCodigoEtapa() {
		return codigoEtapa;
	}
	public void setCodigoEtapa(String codigoEtapa) {
		this.codigoEtapa = codigoEtapa;
	}
	public String getCodigoCobrador() {
		return codigoCobrador;
	}
	public void setCodigoCobrador(String codigoCobrador) {
		this.codigoCobrador = codigoCobrador;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getDistrito() {
		return distrito;
	}
	public void setDistrito(String distrito) {
		this.distrito = distrito;
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
	
	public String getCodigoCartera() {
		return codigoCartera;
	}
	public void setCodigoCartera(String codigoCartera) {
		this.codigoCartera = codigoCartera;
	}
	
	public String getCodigoEtapaConsultora() {
		return codigoEtapaConsultora;
	}
	public void setCodigoEtapaConsultora(String codigoEtapaConsultora) {
		this.codigoEtapaConsultora = codigoEtapaConsultora;
	}
	
	public String getCodigoConsultora() {
		return codigoConsultora;
	}
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}
	public String getNombreConsultora() {
		return nombreConsultora;
	}
	public void setNombreConsultora(String nombreConsultora) {
		this.nombreConsultora = nombreConsultora;
	}
	public String getTiempoMora() {
		return tiempoMora;
	}
	
	public void setTiempoMora(String tiempoMora) {
		this.tiempoMora = tiempoMora;
	}
	
	public String getFechaAsignacion() {
		return fechaAsignacion;
	}
	
	public void setFechaAsignacion(String fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}
	
	public String getFechaCierre() {
		return fechaCierre;
	}
	
	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	
	public String getDeudaCartera() {
		return deudaCartera;
	}
	public void setDeudaCartera(String deudaCartera) {
		this.deudaCartera = deudaCartera;
	}
	
	public String getDeudaTotal() {
		return deudaTotal;
	}
	public void setDeudaTotal(String deudaTotal) {
		this.deudaTotal = deudaTotal;
	}
	
	
	public String getTipoAccion() {
		return tipoAccion;
	}
	
	public void setTipoAccion(String tipoAccion) {
		this.tipoAccion = tipoAccion;
	}
	
	public String getAccionCobranza() {
		return accionCobranza;
	}
	public void setAccionCobranza(String accionCobranza) {
		this.accionCobranza = accionCobranza;
	}
		
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	
	public String getImportePago() {
		return importePago;
	}
	public void setImportePago(String importePago) {
		this.importePago = importePago;
	}
	
	public String getTabSeleccionCurso() {
		return tabSeleccionCurso;
	}
	public void setTabSeleccionCurso(String tabSeleccionCurso) {
		this.tabSeleccionCurso = tabSeleccionCurso;
	}
	public String getRegionTab() {
		return regionTab;
	}
	public void setRegionTab(String regionTab) {
		this.regionTab = regionTab;
	}
	public String getZonaTab() {
		return zonaTab;
	}
	public void setZonaTab(String zonaTab) {
		this.zonaTab = zonaTab;
	}
	
	public String getSeccionTab() {
		return seccionTab;
	}
	public void setSeccionTab(String seccionTab) {
		this.seccionTab = seccionTab;
	}
	
	public String getTerritorioTab() {
		return territorioTab;
	}
	public void setTerritorioTab(String territorioTab) {
		this.territorioTab = territorioTab;
	}
	
	public String getCampanaIngresoTab() {
		return campanaIngresoTab;
	}
	public void setCampanaIngresoTab(String campanaIngresoTab) {
		this.campanaIngresoTab = campanaIngresoTab;
	}
	
	public String getStatusTab() {
		return statusTab;
	}
	public void setStatusTab(String statusTab) {
		this.statusTab = statusTab;
	}
	
	public String getDireccionTab() {
		return direccionTab;
	}
	public void setDireccionTab(String direccionTab) {
		this.direccionTab = direccionTab;
	}
	public String getUltimoPedidoTab() {
		return ultimoPedidoTab;
	}
	public void setUltimoPedidoTab(String ultimoPedidoTab) {
		this.ultimoPedidoTab = ultimoPedidoTab;
	}
	
	public String getReferencias() {
		return referencias;
	}
	public void setReferencias(String referencias) {
		this.referencias = referencias;
	}
	 
	public String[] getTelefonosTab() {
		return telefonosTab;
	}
	public void setTelefonosTab(String[] telefonosTab) {
		this.telefonosTab = telefonosTab;
	}
	
	public String getNuevaDireccionTab() {
		return nuevaDireccionTab;
	}
	public void setNuevaDireccionTab(String nuevaDireccionTab) {
		this.nuevaDireccionTab = nuevaDireccionTab;
	}
	
	public String getReferenciaTab() {
		return referenciaTab;
	}
	public void setReferenciaTab(String referenciaTab) {
		this.referenciaTab = referenciaTab;
	}
	
	public String getFechaIngresoTab() {
		return fechaIngresoTab;
	}
	public void setFechaIngresoTab(String fechaIngresoTab) {
		this.fechaIngresoTab = fechaIngresoTab;
	}
	
	public String getFechaNacimientoTab() {
		return fechaNacimientoTab;
	}
	public void setFechaNacimientoTab(String fechaNacimientoTab) {
		this.fechaNacimientoTab = fechaNacimientoTab;
	}
	
	public String getNuevoTelefonoFijoTab() {
		return nuevoTelefonoFijoTab;
	}
	public void setNuevoTelefonoFijoTab(String nuevoTelefonoFijoTab) {
		this.nuevoTelefonoFijoTab = nuevoTelefonoFijoTab;
	}
	
	public String getNuevoTelefonoMovilTab() {
		return nuevoTelefonoMovilTab;
	}
	public void setNuevoTelefonoMovilTab(String nuevoTelefonoMovilTab) {
		this.nuevoTelefonoMovilTab = nuevoTelefonoMovilTab;
	}
	
	
	public String getTelefonoFijo() {
		return telefonoFijo;
	}
	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}
	
	public String getTelefonoMovil() {
		return telefonoMovil;
	}
	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}
	public String getTelefonoTrabajo() {
		return telefonoTrabajo;
	}
	public void setTelefonoTrabajo(String telefonoTrabajo) {
		this.telefonoTrabajo = telefonoTrabajo;
	}
	public int getIndice() {
		return indice;
	}
	public void setIndice(int indice) {
		this.indice = indice;
	}
	
	public int getIndiceSearch() {
		return indiceSearch;
	}
	public void setIndiceSearch(int indiceSearch) {
		this.indiceSearch = indiceSearch;
	}
	
	public int getTamano() {
		return tamano;
	}
	public void setTamano(int tamano) {
		this.tamano = tamano;
	}

	public String getCodigoSociedadSearch() {
		return codigoSociedadSearch;
	}

	public void setCodigoSociedadSearch(String codigoSociedadSearch) {
		this.codigoSociedadSearch = codigoSociedadSearch;
	}

	public String getCodigoRegionSearch() {
		return codigoRegionSearch;
	}

	public void setCodigoRegionSearch(String codigoRegionSearch) {
		this.codigoRegionSearch = codigoRegionSearch;
	}

	public String getCodigoZonaSearch() {
		return codigoZonaSearch;
	}

	public void setCodigoZonaSearch(String codigoZonaSearch) {
		this.codigoZonaSearch = codigoZonaSearch;
	}

	public String getCodigoEtapaSearch() {
		return codigoEtapaSearch;
	}

	public void setCodigoEtapaSearch(String codigoEtapaSearch) {
		this.codigoEtapaSearch = codigoEtapaSearch;
	}

	public String getCodigoCobradorSearch() {
		return codigoCobradorSearch;
	}

	public void setCodigoCobradorSearch(String codigoCobradorSearch) {
		this.codigoCobradorSearch = codigoCobradorSearch;
	}
	
	public String getCodigoConsultoraSearch() {
		return codigoConsultoraSearch;
	}

	public void setCodigoConsultoraSearch(String codigoConsultoraSearch) {
		this.codigoConsultoraSearch = codigoConsultoraSearch;
	}
    
	public String getNumeroDocumentoSearch() {
		return numeroDocumentoSearch;
	}

	public void setNumeroDocumentoSearch(String numeroDocumentoSearch) {
		this.numeroDocumentoSearch = numeroDocumentoSearch;
	}
		
	public String getCriterioFiltro() {
		return criterioFiltro;
	}

	public void setCriterioFiltro(String criterioFiltro) {
		this.criterioFiltro = criterioFiltro;
	}
	
	public String getGestionFiltro() {
		return gestionFiltro;
	}

	public void setGestionFiltro(String gestionFiltro) {
		this.gestionFiltro = gestionFiltro;
	}
	
	public String getCriterioOrdenamiento() {
		return criterioOrdenamiento;
	}

	public void setCriterioOrdenamiento(String criterioOrdenamiento) {
		this.criterioOrdenamiento = criterioOrdenamiento;
	}
	
	
}
