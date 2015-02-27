package biz.belcorp.ssicc.web.spusicc.comision.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoCOMOrdenEstadisticoForm extends BaseEditForm
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5412694674406180504L;
	private String codigoPais;
	private String codigoRegion;
	private String codigoZona;
	private String ordenEstadistico;
	private String estatusRegistro;
	private boolean viewEdit;
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
	 * @return the ordenEstadistico
	 */
	public String getOrdenEstadistico() {
		return ordenEstadistico;
	}
	/**
	 * @param ordenEstadistico the ordenEstadistico to set
	 */
	public void setOrdenEstadistico(String ordenEstadistico) {
		this.ordenEstadistico = ordenEstadistico;
	}
	/**
	 * @return the estatusRegistro
	 */
	public String getEstatusRegistro() {
		return estatusRegistro;
	}
	/**
	 * @param estatusRegistro the estatusRegistro to set
	 */
	public void setEstatusRegistro(String estatusRegistro) {
		this.estatusRegistro = estatusRegistro;
	}
	/**
	 * @return the viewEdit
	 */
	public boolean isViewEdit() {
		return viewEdit;
	}
	/**
	 * @param viewEdit the viewEdit to set
	 */
	public void setViewEdit(boolean viewEdit) {
		this.viewEdit = viewEdit;
	}

	
}
