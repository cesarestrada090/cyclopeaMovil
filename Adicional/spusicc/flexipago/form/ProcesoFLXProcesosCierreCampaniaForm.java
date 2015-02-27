package biz.belcorp.ssicc.web.spusicc.flexipago.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

public class ProcesoFLXProcesosCierreCampaniaForm extends BaseInterfazForm
implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7063752251679799238L;
	private String codigoPais;
	private String codigoPeriodo;
	private String indicadorEjecucionSICC;
	private String indicadorSeleccionInterfaces;
	private String indicadorEjecucionInterfaces;
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
	 * @return the indicadorEjecucionSICC
	 */
	public String getIndicadorEjecucionSICC() {
		return indicadorEjecucionSICC;
	}
	/**
	 * @param indicadorEjecucionSICC the indicadorEjecucionSICC to set
	 */
	public void setIndicadorEjecucionSICC(String indicadorEjecucionSICC) {
		this.indicadorEjecucionSICC = indicadorEjecucionSICC;
	}
	/**
	 * @return the indicadorSeleccionInterfaces
	 */
	public String getIndicadorSeleccionInterfaces() {
		return indicadorSeleccionInterfaces;
	}
	/**
	 * @param indicadorSeleccionInterfaces the indicadorSeleccionInterfaces to set
	 */
	public void setIndicadorSeleccionInterfaces(String indicadorSeleccionInterfaces) {
		this.indicadorSeleccionInterfaces = indicadorSeleccionInterfaces;
	}
	/**
	 * @return the indicadorEjecucionInterfaces
	 */
	public String getIndicadorEjecucionInterfaces() {
		return indicadorEjecucionInterfaces;
	}
	/**
	 * @param indicadorEjecucionInterfaces the indicadorEjecucionInterfaces to set
	 */
	public void setIndicadorEjecucionInterfaces(String indicadorEjecucionInterfaces) {
		this.indicadorEjecucionInterfaces = indicadorEjecucionInterfaces;
	}
	
	

	
}

