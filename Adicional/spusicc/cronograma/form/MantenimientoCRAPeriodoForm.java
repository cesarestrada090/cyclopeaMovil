package biz.belcorp.ssicc.web.spusicc.cronograma.form;

import java.io.Serializable;
import java.util.Arrays;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoCRAPeriodoForm extends BaseSearchForm
		implements Serializable {

	private static final long serialVersionUID = 1L;
	

	private String codigoPais;
	
	private String anhio;

	private String codigoPeriodo;
	
	private String duracion;
	
	private String fechaInicio;
	
	private String fechaFin;	
	
	private String indicadorPeriodoCorto;
	
	private String indicadorPeriodoCruce;
	
	private String[] listaCodigoPeriodo;
	
	private String[] listaDuracion;
	
	private String[] listaFechaInicio;
	
	private String[] listaFechaFin;
	
	private String[] listaIndicadorPeriodoCorto;
	
	private String[] listaIndicadorPeriodoCruce;
	
	private String[] listaOidPeriodo;
		
	protected boolean newRecord = true;
	
	
	
	public boolean isNewRecord() {
		return newRecord;
	}

	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}

	@Override
	public String toString() {
		return "MantenimientoCRAPeriodoForm [codigoPais=" + codigoPais
				+ ", anhio=" + anhio + ", codigoPeriodo=" + codigoPeriodo
				+ ", duracion=" + duracion + ", fechaInicio=" + fechaInicio
				+ ", fechaFin=" + fechaFin + ", indicadorPeriodoCorto="
				+ indicadorPeriodoCorto + ", indicadorPeriodoCruce="
				+ indicadorPeriodoCruce + ", listaCodigoPeriodo="
				+ Arrays.toString(listaCodigoPeriodo) + ", listaDuracion="
				+ Arrays.toString(listaDuracion) + ", listaFechaInicio="
				+ Arrays.toString(listaFechaInicio) + ", listaFechaFin="
				+ Arrays.toString(listaFechaFin)
				+ ", listaIndicadorPeriodoCorto="
				+ Arrays.toString(listaIndicadorPeriodoCorto)
				+ ", listaIndicadorPeriodoCruce="
				+ Arrays.toString(listaIndicadorPeriodoCruce)
				+ ", listaOidPeriodo=" + Arrays.toString(listaOidPeriodo) + "]";
	}

	public void reset(){
		this.anhio = "";
		this.codigoPeriodo ="";
		this.duracion="";
		this.fechaInicio ="";
		this.fechaFin="";
		this.indicadorPeriodoCorto="";
		this.indicadorPeriodoCruce="";
		this.listaOidPeriodo = new String[0];
		this.listaCodigoPeriodo = new String[0];
		this.listaDuracion = new String[0];
		this.listaFechaInicio = new String[0];
		this.listaFechaFin = new String[0];
		this.listaIndicadorPeriodoCorto = new String[0];
		this.listaIndicadorPeriodoCruce = new String[0];
	}
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type="required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the anhio
	 */
	public String getAnhio() {
		return anhio;
	}

	/**
	 * @param anhio the anhio to set
	 * @struts.validator type="required"
	 */
	public void setAnhio(String anhio) {
		this.anhio = anhio;
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
	 * @return the duracion
	 */
	public String getDuracion() {
		return duracion;
	}

	/**
	 * @param duracion the duracion to set
	 */
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the indicadorPeriodoCorto
	 */
	public String getIndicadorPeriodoCorto() {
		return indicadorPeriodoCorto;
	}

	/**
	 * @param indicadorPeriodoCorto the indicadorPeriodoCorto to set
	 */
	public void setIndicadorPeriodoCorto(String indicadorPeriodoCorto) {
		this.indicadorPeriodoCorto = indicadorPeriodoCorto;
	}

	/**
	 * @return the indicadorPeriodoCruce
	 */
	public String getIndicadorPeriodoCruce() {
		return indicadorPeriodoCruce;
	}

	/**
	 * @param indicadorPeriodoCruce the indicadorPeriodoCruce to set
	 */
	public void setIndicadorPeriodoCruce(String indicadorPeriodoCruce) {
		this.indicadorPeriodoCruce = indicadorPeriodoCruce;
	}

	/**
	 * @return the listaDuracion
	 */
	public String[] getListaDuracion() {
		return listaDuracion;
	}

	/**
	 * @param listaDuracion the listaDuracion to set
	 */
	public void setListaDuracion(String[] listaDuracion) {
		this.listaDuracion = listaDuracion;
	}

	/**
	 * @return the listaFechaInicio
	 */
	public String[] getListaFechaInicio() {
		return listaFechaInicio;
	}

	/**
	 * @param listaFechaInicio the listaFechaInicio to set
	 */
	public void setListaFechaInicio(String[] listaFechaInicio) {
		this.listaFechaInicio = listaFechaInicio;
	}

	/**
	 * @return the listaFechaFin
	 */
	public String[] getListaFechaFin() {
		return listaFechaFin;
	}

	/**
	 * @param listaFechaFin the listaFechaFin to set
	 */
	public void setListaFechaFin(String[] listaFechaFin) {
		this.listaFechaFin = listaFechaFin;
	}

	/**
	 * @return the listaIndicadorPeriodoCorto
	 */
	public String[] getListaIndicadorPeriodoCorto() {
		return listaIndicadorPeriodoCorto;
	}

	/**
	 * @param listaIndicadorPeriodoCorto the listaIndicadorPeriodoCorto to set
	 */
	public void setListaIndicadorPeriodoCorto(String[] listaIndicadorPeriodoCorto) {
		this.listaIndicadorPeriodoCorto = listaIndicadorPeriodoCorto;
	}

	/**
	 * @return the listaIndicadorPeriodoCruce
	 */
	public String[] getListaIndicadorPeriodoCruce() {
		return listaIndicadorPeriodoCruce;
	}

	/**
	 * @param listaIndicadorPeriodoCruce the listaIndicadorPeriodoCruce to set
	 */
	public void setListaIndicadorPeriodoCruce(String[] listaIndicadorPeriodoCruce) {
		this.listaIndicadorPeriodoCruce = listaIndicadorPeriodoCruce;
	}

	/**
	 * @return the listaCodigoPeriodo
	 */
	public String[] getListaCodigoPeriodo() {
		return listaCodigoPeriodo;
	}

	/**
	 * @param listaCodigoPeriodo the listaCodigoPeriodo to set
	 */
	public void setListaCodigoPeriodo(String[] listaCodigoPeriodo) {
		this.listaCodigoPeriodo = listaCodigoPeriodo;
	}

	/**
	 * @return the listaOidPeriodo
	 */
	public String[] getListaOidPeriodo() {
		return listaOidPeriodo;
	}

	/**
	 * @param listaOidPeriodo the listaOidPeriodo to set
	 */
	public void setListaOidPeriodo(String[] listaOidPeriodo) {
		this.listaOidPeriodo = listaOidPeriodo;
	}	
	
	
}
