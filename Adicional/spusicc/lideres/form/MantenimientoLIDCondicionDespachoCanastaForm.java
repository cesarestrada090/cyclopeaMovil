package biz.belcorp.ssicc.web.spusicc.lideres.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoLIDCondicionDespachoCanastaForm extends BaseSearchForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -53316662910437443L;
	private String codigoPais;
	private String codigoPeriodo;
	private String codigoCondicionDespacho;
	private String metaCondicion;
	private String indicadorLider;
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
	 * @return the codigoCondicionDespacho
	 */
	public String getCodigoCondicionDespacho() {
		return codigoCondicionDespacho;
	}
	/**
	 * @param codigoCondicionDespacho the codigoCondicionDespacho to set
	 */
	public void setCodigoCondicionDespacho(String codigoCondicionDespacho) {
		this.codigoCondicionDespacho = codigoCondicionDespacho;
	}
	/**
	 * @return the metaCondicion
	 */
	public String getMetaCondicion() {
		return metaCondicion;
	}
	/**
	 * @param metaCondicion the metaCondicion to set
	 */
	public void setMetaCondicion(String metaCondicion) {
		this.metaCondicion = metaCondicion;
	}
	/**
	 * @return the indicadorLider
	 */
	public String getIndicadorLider() {
		return indicadorLider;
	}
	/**
	 * @param indicadorLider the indicadorLider to set
	 */
	public void setIndicadorLider(String indicadorLider) {
		this.indicadorLider = indicadorLider;
	}
	
	

}
