package biz.belcorp.ssicc.web.spusicc.zon.form;

import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * The Class MantenimientoZONIngresoDirectorioForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 27/01/2015
 */
public class MantenimientoZONIngresoDirectorioForm extends BaseSearchForm {

	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoCargo;
	private String codigoClienteBuscar;
	private String codigoRegion;
	private String codigoZona;
	private String [] codigoRegionList;
	private String [] codigoZonaList;
	private String nombreCliente;
	private String numeroDocumentoIdentidad;
    private String longitudCodigoCliente;
    private String oidPais;
    
    private String tipoUnidad;
    private String cantidadUnidad;
    
    private String fechaIngreso;
    private Date fechaIngresoD;

        	
	/**
	 * @return the tipoUnidad
	 */
	public String getTipoUnidad() {
		return tipoUnidad;
	}

	/**
	 * @param tipoUnidad the tipoUnidad to set
	 */
	public void setTipoUnidad(String tipoUnidad) {
		this.tipoUnidad = tipoUnidad;
	}

	/**
	 * @return the cantidadUnidad
	 */
	public String getCantidadUnidad() {
		return cantidadUnidad;
	}

	/**
	 * @param cantidadUnidad the cantidadUnidad to set
	 */
	public void setCantidadUnidad(String cantidadUnidad) {
		this.cantidadUnidad = cantidadUnidad;
	}

	/**
	 * @return the longitudCodigoCliente
	 */
	public String getLongitudCodigoCliente() {
		return longitudCodigoCliente;
	}

	/**
	 * @param longitudCodigoCliente the longitudCodigoCliente to set
	 */
	public void setLongitudCodigoCliente(String longitudCodigoCliente) {
		this.longitudCodigoCliente = longitudCodigoCliente;
	}

	/**
	 * @return the oidPais
	 */
	public String getOidPais() {
		return oidPais;
	}

	/**
	 * @param oidPais the oidPais to set
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}

	/**
	 * @return the nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * @param nombreCliente the nombreCliente to set
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	/**
	 * @return the numeroDocumentoIdentidad
	 */
	public String getNumeroDocumentoIdentidad() {
		return numeroDocumentoIdentidad;
	}

	/**
	 * @param numeroDocumentoIdentidad the numeroDocumentoIdentidad to set
	 */
	public void setNumeroDocumentoIdentidad(String numeroDocumentoIdentidad) {
		this.numeroDocumentoIdentidad = numeroDocumentoIdentidad;
	}

	/**
	 * @return the codigoRegionList
	 */
	public String[] getCodigoRegionList() {
		return codigoRegionList;
	}

	/**
	 * @param codigoRegionList the codigoRegionList to set
	 */
	public void setCodigoRegionList(String[] codigoRegionList) {
		this.codigoRegionList = codigoRegionList;
	}

	/**
	 * @return the codigoZonaList
	 */
	public String[] getCodigoZonaList() {
		return codigoZonaList;
	}

	/**
	 * @param codigoZonaList the codigoZonaList to set
	 */
	public void setCodigoZonaList(String[] codigoZonaList) {
		this.codigoZonaList = codigoZonaList;
	}

	/**
	 * @return the codigoClienteBuscar
	 */
	public String getCodigoClienteBuscar() {
		return codigoClienteBuscar;
	}

	/**
	 * @param codigoClienteBuscar the codigoClienteBuscar to set
	 * @struts.validator type = "required" 
	 */
	public void setCodigoClienteBuscar(String codigoClienteBuscar) {
		this.codigoClienteBuscar = codigoClienteBuscar;
	}

	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 * @struts.validator type = "required" 
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoCargo
	 */
	public String getCodigoCargo() {
		return codigoCargo;
	}

	/**
	 * @param codigoCargo the codigoCargo to set
	 * @struts.validator type = "required" 
	 */
	public void setCodigoCargo(String codigoCargo) {
		this.codigoCargo = codigoCargo;
	}
	
	/**
	 * @return the fechaIngreso
	 */
	public String getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * @param fechaIngreso the fechaIngreso to set
	 * @struts.validator type = "required"
	 */
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	/**
	 * @return the fechaIngresoD
	 */
	public Date getFechaIngresoD() {
		return fechaIngresoD;
	}

	/**
	 * @param fechaIngresoD the fechaIngresoD to set
	 */
	public void setFechaIngresoD(Date fechaIngresoD) {
		this.fechaIngresoD = fechaIngresoD;
	}

}