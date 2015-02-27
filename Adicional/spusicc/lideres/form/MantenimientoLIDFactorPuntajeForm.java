package biz.belcorp.ssicc.web.spusicc.lideres.form;

import java.util.List;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoLIDFactorPuntajeForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:kgomez@sigcomt.com">Karina Gomez</a>
 */
public class MantenimientoLIDFactorPuntajeForm extends BaseSearchForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String codigoMarca;
	private String periodoProceso;
	private String codigoIdiomaISO;
	private String codigoNumeroConcurso;
	private String codigoTipoAsignacionPuntaje;
	private String fechaProceso;
	
	private List marcaList;
	private List factorPuntajeList;
	private List tipoAsignacionPuntajeList;
	
	private String[] valorFactorMultiplicador;
	private String[] codConcurso;
	private String[] tipAsignacionPuntaje;
	private String[] codPais;
	private String[] codPeriodo;
	private String[] flag;
	
	
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
	 * @return the periodoProceso
	 */
	public String getPeriodoProceso() {
		return periodoProceso;
	}
	/**
	 * @param periodoProceso the periodoProceso to set
	 */
	public void setPeriodoProceso(String periodoProceso) {
		this.periodoProceso = periodoProceso;
	}
	/**
	 * @return the codigoIdiomaISO
	 */
	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}
	/**
	 * @param codigoIdiomaISO the codigoIdiomaISO to set
	 */
	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
	}
	/**
	 * @return the codigoNumeroConcurso
	 */
	public String getCodigoNumeroConcurso() {
		return codigoNumeroConcurso;
	}
	/**
	 * @param codigoNumeroConcurso the codigoNumeroConcurso to set
	 */
	public void setCodigoNumeroConcurso(String codigoNumeroConcurso) {
		this.codigoNumeroConcurso = codigoNumeroConcurso;
	}
	/**
	 * @return the codigoTipoAsignacionPuntaje
	 */
	public String getCodigoTipoAsignacionPuntaje() {
		return codigoTipoAsignacionPuntaje;
	}
	/**
	 * @param codigoTipoAsignacionPuntaje the codigoTipoAsignacionPuntaje to set
	 */
	public void setCodigoTipoAsignacionPuntaje(String codigoTipoAsignacionPuntaje) {
		this.codigoTipoAsignacionPuntaje = codigoTipoAsignacionPuntaje;
	}
	/**
	 * @return the fechaProceso
	 */
	public String getFechaProceso() {
		return fechaProceso;
	}
	/**
	 * @param fechaProceso the fechaProceso to set
	 */
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
	/**
	 * @return the marcaList
	 */
	public List getMarcaList() {
		return marcaList;
	}
	/**
	 * @param marcaList the marcaList to set
	 */
	public void setMarcaList(List marcaList) {
		this.marcaList = marcaList;
	}
	/**
	 * @return the factorPuntajeList
	 */
	public List getFactorPuntajeList() {
		return factorPuntajeList;
	}
	/**
	 * @param factorPuntajeList the factorPuntajeList to set
	 */
	public void setFactorPuntajeList(List factorPuntajeList) {
		this.factorPuntajeList = factorPuntajeList;
	}
	/**
	 * @return the tipoAsignacionPuntajeList
	 */
	public List getTipoAsignacionPuntajeList() {
		return tipoAsignacionPuntajeList;
	}
	/**
	 * @param tipoAsignacionPuntajeList the tipoAsignacionPuntajeList to set
	 */
	public void setTipoAsignacionPuntajeList(List tipoAsignacionPuntajeList) {
		this.tipoAsignacionPuntajeList = tipoAsignacionPuntajeList;
	}
	/**
	 * @return the valorFactorMultiplicador
	 */
	public String[] getValorFactorMultiplicador() {
		return valorFactorMultiplicador;
	}
	/**
	 * @param valorFactorMultiplicador the valorFactorMultiplicador to set
	 */
	public void setValorFactorMultiplicador(String[] valorFactorMultiplicador) {
		this.valorFactorMultiplicador = valorFactorMultiplicador;
	}
	/**
	 * @return the codConcurso
	 */
	public String[] getCodConcurso() {
		return codConcurso;
	}
	/**
	 * @param codConcurso the codConcurso to set
	 */
	public void setCodConcurso(String[] codConcurso) {
		this.codConcurso = codConcurso;
	}
	/**
	 * @return the tipAsignacionPuntaje
	 */
	public String[] getTipAsignacionPuntaje() {
		return tipAsignacionPuntaje;
	}
	/**
	 * @param tipAsignacionPuntaje the tipAsignacionPuntaje to set
	 */
	public void setTipAsignacionPuntaje(String[] tipAsignacionPuntaje) {
		this.tipAsignacionPuntaje = tipAsignacionPuntaje;
	}
	/**
	 * @return the codPais
	 */
	public String[] getCodPais() {
		return codPais;
	}
	/**
	 * @param codPais the codPais to set
	 */
	public void setCodPais(String[] codPais) {
		this.codPais = codPais;
	}
	/**
	 * @return the codPeriodo
	 */
	public String[] getCodPeriodo() {
		return codPeriodo;
	}
	/**
	 * @param codPeriodo the codPeriodo to set
	 */
	public void setCodPeriodo(String[] codPeriodo) {
		this.codPeriodo = codPeriodo;
	}
	/**
	 * @return the flag
	 */
	public String[] getFlag() {
		return flag;
	}
	/**
	 * @param flag the flag to set
	 */
	public void setFlag(String[] flag) {
		this.flag = flag;
	}
	
	
	
}
