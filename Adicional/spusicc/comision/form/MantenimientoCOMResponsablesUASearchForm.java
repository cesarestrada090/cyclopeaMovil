package biz.belcorp.ssicc.web.spusicc.comision.form;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoCOMResponsablesUASearchForm extends BaseSearchForm
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2288998013496566257L;
	private String codigoPais;
	private String codigoMarca;
	private String descripcionMarca;
	private String codigoCanal;
	private String descripcionCanal;
	private String codigoZona;
	private String codigoSeccion;
	private String tipoUnidad;
	private String descripcionTipoUnidad;
	private Integer contador;
	private String[] listaFechaDesde = {};
	private String[] listaFechaHasta = {};
	private String[] listaCodResponsable = {};
	private String[] listaOidHistorico = {};
	private String[] listaCodPeriDesde = {};
	private String[] listaCodPeriHasta = {};

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.form.BaseSearchForm#reset(org.apache.struts.action
	 * .ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public MantenimientoCOMResponsablesUASearchForm() {
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
		this.codigoZona = null;
		this.codigoSeccion = null;
		this.descripcionTipoUnidad = null;
		this.tipoUnidad = Constants.TIPO_UA_ZONA;
		this.contador = new Integer(0);
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca the codigoMarca to set
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return the descripcionMarca
	 */
	public String getDescripcionMarca() {
		return descripcionMarca;
	}

	/**
	 * @param descripcionMarca the descripcionMarca to set
	 */
	public void setDescripcionMarca(String descripcionMarca) {
		this.descripcionMarca = descripcionMarca;
	}

	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal the codigoCanal to set
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return the descripcionCanal
	 */
	public String getDescripcionCanal() {
		return descripcionCanal;
	}

	/**
	 * @param descripcionCanal the descripcionCanal to set
	 */
	public void setDescripcionCanal(String descripcionCanal) {
		this.descripcionCanal = descripcionCanal;
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
	 * @return the codigoSeccion
	 */
	public String getCodigoSeccion() {
		return codigoSeccion;
	}

	/**
	 * @param codigoSeccion the codigoSeccion to set
	 */
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}

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
	 * @return the descripcionTipoUnidad
	 */
	public String getDescripcionTipoUnidad() {
		return descripcionTipoUnidad;
	}

	/**
	 * @param descripcionTipoUnidad the descripcionTipoUnidad to set
	 */
	public void setDescripcionTipoUnidad(String descripcionTipoUnidad) {
		this.descripcionTipoUnidad = descripcionTipoUnidad;
	}

	/**
	 * @return the contador
	 */
	public Integer getContador() {
		return contador;
	}

	/**
	 * @param contador the contador to set
	 */
	public void setContador(Integer contador) {
		this.contador = contador;
	}

	/**
	 * @return the listaFechaDesde
	 */
	public String[] getListaFechaDesde() {
		return listaFechaDesde;
	}

	/**
	 * @param listaFechaDesde the listaFechaDesde to set
	 */
	public void setListaFechaDesde(String[] listaFechaDesde) {
		this.listaFechaDesde = listaFechaDesde;
	}

	/**
	 * @return the listaFechaHasta
	 */
	public String[] getListaFechaHasta() {
		return listaFechaHasta;
	}

	/**
	 * @param listaFechaHasta the listaFechaHasta to set
	 */
	public void setListaFechaHasta(String[] listaFechaHasta) {
		this.listaFechaHasta = listaFechaHasta;
	}

	/**
	 * @return the listaCodResponsable
	 */
	public String[] getListaCodResponsable() {
		return listaCodResponsable;
	}

	/**
	 * @param listaCodResponsable the listaCodResponsable to set
	 */
	public void setListaCodResponsable(String[] listaCodResponsable) {
		this.listaCodResponsable = listaCodResponsable;
	}

	/**
	 * @return the listaOidHistorico
	 */
	public String[] getListaOidHistorico() {
		return listaOidHistorico;
	}

	/**
	 * @param listaOidHistorico the listaOidHistorico to set
	 */
	public void setListaOidHistorico(String[] listaOidHistorico) {
		this.listaOidHistorico = listaOidHistorico;
	}

	/**
	 * @return the listaCodPeriDesde
	 */
	public String[] getListaCodPeriDesde() {
		return listaCodPeriDesde;
	}

	/**
	 * @param listaCodPeriDesde the listaCodPeriDesde to set
	 */
	public void setListaCodPeriDesde(String[] listaCodPeriDesde) {
		this.listaCodPeriDesde = listaCodPeriDesde;
	}

	/**
	 * @return the listaCodPeriHasta
	 */
	public String[] getListaCodPeriHasta() {
		return listaCodPeriHasta;
	}

	/**
	 * @param listaCodPeriHasta the listaCodPeriHasta to set
	 */
	public void setListaCodPeriHasta(String[] listaCodPeriHasta) {
		this.listaCodPeriHasta = listaCodPeriHasta;
	}
	
	

}
