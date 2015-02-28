package biz.belcorp.ssicc.web.spusicc.ventas.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;


/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoBASFeriadoForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 *
 **/

public class MantenimientoBASFeriadoForm extends BaseEditForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 37124319860199066L;

	private String codigoAnio;
	
	private String fechaCalendario;
	
	private String posicionSemana;
	
	private String descripcion;
	
	private String indicadorFeriado;

	private String descIndicadorFeriado;
	
	
		
	/**
	 * @return Returns the fechaCalendario
	 */
	public String getFechaCalendario() {
		return fechaCalendario;
	}

	/**
	 * @param fechaCalendario 
	 *       The fechaCalendario to set.
	 */
	public void setFechaCalendario(String fechaCalendario) {
		this.fechaCalendario = fechaCalendario;
	}

	
	/**
	 * @return Returns the posicionSemana.
	 */
	public String getPosicionSemana() {
		return posicionSemana;
	}

	/**
	 * @param posicionSemana The posicionSemana to set.
	 * 
	 */
	public void setPosicionSemana(String posicionSemana) {
		this.posicionSemana = posicionSemana;
	}

	
	/**
	 * @return Returns the descIndicadorFeriado.
	 */
	public String getDescIndicadorFeriado() {
		return descIndicadorFeriado;
	}

	/**
	 * @param descIndicadorFeriado The descIndicadorFeriado to set.
	 */
	public void setDescIndicadorFeriado(String descIndicadorFeriado) {
		this.descIndicadorFeriado = descIndicadorFeriado;
	}

	/**
	 * @return Returns the descripcion.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion The descripcion to set.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return Returns the indicadorFeriado.
	 */
	public String getIndicadorFeriado() {
		return indicadorFeriado;
	}

	/**
	 * @param indicadorFeriado The indicadorFeriado to set.
	 */
	public void setIndicadorFeriado(String indicadorFeriado) {
		this.indicadorFeriado = indicadorFeriado;
	}

	/**
	 * @return Returns the codigoAnio.
	 */
	public String getCodigoAnio() {
		return codigoAnio;
	}

	/**
	 * @param codigoAnio The codigoAnio to set.
	 */
	public void setCodigoAnio(String codigoAnio) {
		this.codigoAnio = codigoAnio;
	}

}
