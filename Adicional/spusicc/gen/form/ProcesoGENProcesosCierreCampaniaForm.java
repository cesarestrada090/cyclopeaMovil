package biz.belcorp.ssicc.web.spusicc.gen.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

public class ProcesoGENProcesosCierreCampaniaForm extends BaseInterfazForm
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
	
	private String codigoPais;
	private String codigoMarca;
	private String codigoCanal;
	private String codigoPeriodo;
	private String tipoCierre;
	private String codigoPeriodoActual;
	private String habilitadorHidden;
	
	private String fechaFacturacion;
	
	private boolean mostrarBotonProcesar;
	
	private String indicadorModEducacion;
	
	private String indicadorEjecucionSICC;
	private String indicadorSeleccionInterfaces;
	private String indicadorEjecucionInterfaces;
	/* INI JJ  PER-SiCC-2012-0361 */
	private String indTipoValid;
	
	private String frecuenciaSGR;
	
	/**
	 * @return
	 */
	public String getIndTipoValid() {
		return indTipoValid;
	}

	/**
	 * @param indTipoValid
	 */
	public void setIndTipoValid(String indTipoValid) {
		this.indTipoValid = indTipoValid;
	}

	/* FIN JJ  PER-SiCC-2012-0361 */
	/**
	 * @return
	 */
	public String getIndicadorModEducacion() {
		return indicadorModEducacion;
	}

	/**
	 * @param indicadorModEducacion
	 */
	public void setIndicadorModEducacion(String indicadorModEducacion) {
		this.indicadorModEducacion = indicadorModEducacion;
	}

	/**
	 * @return
	 */
	public String getHabilitadorHidden() {
		return habilitadorHidden;
	}

	/**
	 * @param habilitadorHidden
	 */
	public void setHabilitadorHidden(String habilitadorHidden) {
		this.habilitadorHidden = habilitadorHidden;
	}

	/**
	 * @return
	 */
	public String getCodigoPeriodoActual() {
		return codigoPeriodoActual;
	}

	/**
	 * @param codigoPeriodoActual
	 */
	public void setCodigoPeriodoActual(String codigoPeriodoActual) {
		this.codigoPeriodoActual = codigoPeriodoActual;
	}

	/**
	 * @return
	 */
	public String getTipoCierre() {
		return tipoCierre;
	}

	/**
	 * @param tipoCierre
	 */
	public void setTipoCierre(String tipoCierre) {
		this.tipoCierre = tipoCierre;
	}
	
	/**
	 * @return
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	
	/**
	 * @param codigoPais
	 * @struts.validator type="required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	/**
	 * @return
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}
	
	/**
	 * @param codigoMarca
	 * @struts.validator type="required"
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	
	/**
	 * @return
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}
	
	/**
	 * @param codigoCanal
	 * @struts.validator type="required"
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}
	
	/**
	 * @return
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	
	/**
	 * @param codigoPeriodo
	 * @struts.validator type="required"
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}

	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	/**
	 * @return the mostrarBotonProcesar
	 */
	public boolean isMostrarBotonProcesar() {
		return mostrarBotonProcesar;
	}

	/**
	 * @param mostrarBotonProcesar the mostrarBotonProcesar to set
	 */
	public void setMostrarBotonProcesar(boolean mostrarBotonProcesar) {
		this.mostrarBotonProcesar = mostrarBotonProcesar;
	}
	
	/**
	 * @return
	 */
	public String getIndicadorEjecucionSICC() {
		return indicadorEjecucionSICC;
	}

	/**
	 * @param indicadorEjecucionSICC
	 */
	public void setIndicadorEjecucionSICC(String indicadorEjecucionSICC) {
		this.indicadorEjecucionSICC = indicadorEjecucionSICC;
	}

	/**
	 * @return
	 */
	public String getIndicadorSeleccionInterfaces() {
		return indicadorSeleccionInterfaces;
	}

	/**
	 * @param indicadorSeleccionInterfaces
	 */
	public void setIndicadorSeleccionInterfaces(String indicadorSeleccionInterfaces) {
		this.indicadorSeleccionInterfaces = indicadorSeleccionInterfaces;
	}

	/**
	 * @return
	 */
	public String getIndicadorEjecucionInterfaces() {
		return indicadorEjecucionInterfaces;
	}

	/**
	 * @param indicadorEjecucionInterfaces
	 */
	public void setIndicadorEjecucionInterfaces(String indicadorEjecucionInterfaces) {
		this.indicadorEjecucionInterfaces = indicadorEjecucionInterfaces;
	}

	/**
	 * @return the frecuenciaSGR
	 */
	public String getFrecuenciaSGR() {
		return frecuenciaSGR;
	}

	/**
	 * @param frecuenciaSGR the frecuenciaSGR to set
	 */
	public void setFrecuenciaSGR(String frecuenciaSGR) {
		this.frecuenciaSGR = frecuenciaSGR;
	}
}
