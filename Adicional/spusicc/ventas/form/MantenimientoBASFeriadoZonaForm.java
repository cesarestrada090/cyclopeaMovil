package biz.belcorp.ssicc.web.spusicc.ventas.form;

import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoBASFeriadoZonaForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 * 
 */

public class MantenimientoBASFeriadoZonaForm extends BaseEditForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4980038510740990595L;

	private String codigoAnio;
	
	private String fechaCalendario;
	
	private String codigoZona;
	
	private String codigoRegion;
	
	private String  descripcion;
		
	private Date calendar;

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
	 * @return Returns the fechaCalendario
	 */
	public String getFechaCalendario() {
		return fechaCalendario;
	}

	/**
	 * @param fechaCalendario 
	 *      The fechaCalendario to set.
	 */
	public void setFechaCalendario(String fechaCalendario) {
		this.fechaCalendario = fechaCalendario;
	}

	/**
	 * @return Returns the descripcion.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion The descripcion to set.
	 * 
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	/**
	 * @return Returns the codigoZona.
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona The codigoZona to set.
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	
	/**
	 * @return the calendar
	 */
	public Date getCalendar() {
		return calendar;
	}

	/**
	 * @param calendar the calendar to set
	 */
	public void setCalendar(Date calendar) {
		this.calendar = calendar;
	}
	
}
