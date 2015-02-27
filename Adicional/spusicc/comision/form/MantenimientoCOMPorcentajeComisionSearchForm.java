package biz.belcorp.ssicc.web.spusicc.comision.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoCOMPorcentajeComisionSearchForm extends BaseSearchForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 24983920277491791L;
	private String codigoPais;
	private String codigoMarca;
	private String codigoCanal;
	private String codigoTipoComisionista;
	private String descripcion;
	private Integer codigoPorcentaje;
	private String codigoCampaniaVigenteDesde;
	private String codigoCampaniaVigenteHasta;
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
	 * @return the codigoTipoComisionista
	 */
	public String getCodigoTipoComisionista() {
		return codigoTipoComisionista;
	}
	/**
	 * @param codigoTipoComisionista the codigoTipoComisionista to set
	 */
	public void setCodigoTipoComisionista(String codigoTipoComisionista) {
		this.codigoTipoComisionista = codigoTipoComisionista;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the codigoPorcentaje
	 */
	public Integer getCodigoPorcentaje() {
		return codigoPorcentaje;
	}
	/**
	 * @param codigoPorcentaje the codigoPorcentaje to set
	 */
	public void setCodigoPorcentaje(Integer codigoPorcentaje) {
		this.codigoPorcentaje = codigoPorcentaje;
	}
	/**
	 * @return the codigoCampaniaVigenteDesde
	 */
	public String getCodigoCampaniaVigenteDesde() {
		return codigoCampaniaVigenteDesde;
	}
	/**
	 * @param codigoCampaniaVigenteDesde the codigoCampaniaVigenteDesde to set
	 */
	public void setCodigoCampaniaVigenteDesde(String codigoCampaniaVigenteDesde) {
		this.codigoCampaniaVigenteDesde = codigoCampaniaVigenteDesde;
	}
	/**
	 * @return the codigoCampaniaVigenteHasta
	 */
	public String getCodigoCampaniaVigenteHasta() {
		return codigoCampaniaVigenteHasta;
	}
	/**
	 * @param codigoCampaniaVigenteHasta the codigoCampaniaVigenteHasta to set
	 */
	public void setCodigoCampaniaVigenteHasta(String codigoCampaniaVigenteHasta) {
		this.codigoCampaniaVigenteHasta = codigoCampaniaVigenteHasta;
	}
	
	

}
