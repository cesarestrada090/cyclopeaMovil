/*
 * 
 */
package biz.belcorp.ssicc.web.spusicc.zon.form;

import java.io.Serializable;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;


/**
 * The Class MantenimientoZONUnidadAdministrativaForm.
 *
 * @author Belcorp
 * @version 1.0
 * 16/01/2015
 */
public class MantenimientoZONUnidadAdministrativaForm extends BaseEditForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	 
	private String codigoPais;
	private String codigoSubGerencia;
	private Integer oidRegion;
	private String codigoRegion;
	private String descripcion;
	private String codigoPeriodo;
	
	private String codigoZona;
	private String descripcionZona;
	private String manual;
	private String accesoInternet;
	private String[] regionList;
	private String tipo;
	
	/**
	 * @return
	 */
	public Integer getOidRegion() {
		return oidRegion;
	}

	/**
	 * @param oidRegion
	 */
	public void setOidRegion(Integer oidRegion) {
		this.oidRegion = oidRegion;
	}

	/**
	 * @return
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return
	 */
	public String getCodigoSubGerencia() {
		return codigoSubGerencia;
	}

	/**
	 * @param codigoSubGerencia
	 */
	public void setCodigoSubGerencia(String codigoSubGerencia) {
		this.codigoSubGerencia = codigoSubGerencia;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descRegion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
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
	 * @return the descripcionZona
	 */
	public String getDescripcionZona() {
		return descripcionZona;
	}

	/**
	 * @param descripcionZona the descripcionZona to set
	 */
	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}

	/**
	 * @return the manual
	 */
	public String getManual() {
		return manual;
	}

	/**
	 * @param manual the manual to set
	 */
	public void setManual(String manual) {
		this.manual = manual;
	}

	/**
	 * @return the regionList
	 */
	public String[] getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList the regionList to set
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the accesoInternet
	 */
	public String getAccesoInternet() {
		return accesoInternet;
	}

	/**
	 * @param accesoInternet the accesoInternet to set
	 */
	public void setAccesoInternet(String accesoInternet) {
		this.accesoInternet = accesoInternet;
	}
}