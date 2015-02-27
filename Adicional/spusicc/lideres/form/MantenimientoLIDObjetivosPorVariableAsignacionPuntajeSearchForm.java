package biz.belcorp.ssicc.web.spusicc.lideres.form;

import java.util.List;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


/**
 * The Class MantenimientoLIDObjetivosPorVariableAsignacionPuntajeSearchForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 16/01/2015
 */
@SuppressWarnings("rawtypes")
public class MantenimientoLIDObjetivosPorVariableAsignacionPuntajeSearchForm extends BaseSearchForm {
	
	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String codigoTipoAsignacionPuntaje;
	
	private List tipoAsignacionPuntaje;
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the codigoTipoAsignacionPuntaje
	 */
	public String getCodigoTipoAsignacionPuntaje() {
		return codigoTipoAsignacionPuntaje;
	}
	/**
	 * @param codigoTipoAsignacionPuntaje the codigoTipoAsignacionPuntaje to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoTipoAsignacionPuntaje(String codigoTipoAsignacionPuntaje) {
		this.codigoTipoAsignacionPuntaje = codigoTipoAsignacionPuntaje;
	}
	/**
	 * @return the tipoAsignacionPuntaje
	 */
	public List getTipoAsignacionPuntaje() {
		return tipoAsignacionPuntaje;
	}
	/**
	 * @param tipoAsignacionPuntaje the tipoAsignacionPuntaje to set
	 */
	public void setTipoAsignacionPuntaje(List tipoAsignacionPuntaje) {
		this.tipoAsignacionPuntaje = tipoAsignacionPuntaje;
	}

}
