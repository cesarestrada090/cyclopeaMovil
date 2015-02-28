package biz.belcorp.ssicc.web.spusicc.dto.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoDTODescuentoAdicionalForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:kgomez@sigcomt.com">Karina Gomez</a>
 */

public class MantenimientoDTODescuentoAdicionalForm extends BaseEditForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	
	private String codigoAdicional;
	private String descripcionAdicional;
	private String porcentaje;
	private String periodoInicial;
	private String periodoFinal;
	private String estadoRegistro;
	
	private String periodoProceso;
	private String tipo;
	
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	 * @return the codigoAdicional
	 */
	public String getCodigoAdicional() {
		return codigoAdicional;
	}

	/**
	 * @param codigoAdicional the codigoAdicional to set
	 */
	public void setCodigoAdicional(String codigoAdicional) {
		this.codigoAdicional = codigoAdicional;
	}

	/**
	 * @return the descripcionAdicional
	 */
	public String getDescripcionAdicional() {
		return descripcionAdicional;
	}

	/**
	 * @param descripcionAdicional the descripcionAdicional to set
	 */
	public void setDescripcionAdicional(String descripcionAdicional) {
		this.descripcionAdicional = descripcionAdicional;
	}

	/**
	 * @return the porcentaje
	 */
	public String getPorcentaje() {
		return porcentaje;
	}

	/**
	 * @param porcentaje the porcentaje to set
	 */
	public void setPorcentaje(String porcentaje) {
		this.porcentaje = porcentaje;
	}

	/**
	 * @return the periodoInicial
	 */
	public String getPeriodoInicial() {
		return periodoInicial;
	}

	/**
	 * @param periodoInicial the periodoInicial to set
	 */
	public void setPeriodoInicial(String periodoInicial) {
		this.periodoInicial = periodoInicial;
	}

	/**
	 * @return the periodoFinal
	 */
	public String getPeriodoFinal() {
		return periodoFinal;
	}

	/**
	 * @param periodoFinal the periodoFinal to set
	 */
	public void setPeriodoFinal(String periodoFinal) {
		this.periodoFinal = periodoFinal;
	}

	/**
	 * @return the estadoRegistro
	 */
	public String getEstadoRegistro() {
		return estadoRegistro;
	}

	/**
	 * @param estadoRegistro the estadoRegistro to set
	 */
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
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
	
	
	
}
