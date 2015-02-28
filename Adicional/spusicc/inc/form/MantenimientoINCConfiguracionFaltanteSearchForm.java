package biz.belcorp.ssicc.web.spusicc.inc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoINCConfiguracionFaltanteSearchForm extends BaseSearchForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;

	private String oidPais;
	private String[] oidConcurso;
	private String[] oidPremio;
	private String[] oidRegion;
	private String[] oidZona;
	private String codigoCliente;
	private String nombreCliente;
	private String oidPeriodo;
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @struts.validator type="required"
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
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
	 * @return the oidConcurso
	 */
	public String[] getOidConcurso() {
		return oidConcurso;
	}
	/**
	 * @param oidConcurso the oidConcurso to set
	 */
	public void setOidConcurso(String[] oidConcurso) {
		this.oidConcurso = oidConcurso;
	}
	/**
	 * @return the oidPremio
	 */
	public String[] getOidPremio() {
		return oidPremio;
	}
	/**
	 * @param oidPremio the oidPremio to set
	 */
	public void setOidPremio(String[] oidPremio) {
		this.oidPremio = oidPremio;
	}
	/**
	 * @return the oidRegion
	 */
	public String[] getOidRegion() {
		return oidRegion;
	}
	/**
	 * @param oidRegion the oidRegion to set
	 */
	public void setOidRegion(String[] oidRegion) {
		this.oidRegion = oidRegion;
	}
	/**
	 * @return the oidZona
	 */
	public String[] getOidZona() {
		return oidZona;
	}
	/**
	 * @param oidZona the oidZona to set
	 */
	public void setOidZona(String[] oidZona) {
		this.oidZona = oidZona;
	}
	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}
	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
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
	 * @return the oidPeriodo
	 */
	public String getOidPeriodo() {
		return oidPeriodo;
	}

	/**
	 * @param oidPeriodo the oidPeriodo to set
	 */
	public void setOidPeriodo(String oidPeriodo) {
		this.oidPeriodo = oidPeriodo;
	}
}
