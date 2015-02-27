package biz.belcorp.ssicc.web.spusicc.ventas.form;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Size;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoBASFeriadoZonaSearchForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 */
public class MantenimientoBASFeriadoZonaSearchForm extends BaseSearchForm{

	private static final long serialVersionUID = -535306596623225116L;

	private String codigoPais;
	
	private String codigoAnio;
	
	private String codigoZona;
	
	private Integer oidZona;
	
	private String fechaCalendario;
	
	private Date calendar;
	
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	
	/**
	 * @return Returns the fechaCalendario.
	 */
	public String getFechaCalendario() {
		return fechaCalendario;
	}

	/**
	 * @param fechaCalendario
	 *            The fechaCalendario to set.
	 */
	public void setFechaCalendario(String fechaCalendario) {
		this.fechaCalendario = fechaCalendario;
	}
	
	
	/**
	 * @return Returns the codigoAnio.
	 */
	@Size(max = 4)
	public String getCodigoAnio() {
		return codigoAnio;
	}

	/**
	 * @param codigoAnio The codigoAnio to set.
	 * 
	 */
	public void setCodigoAnio(String codigoAnio) {
		this.codigoAnio = codigoAnio;
	}
	
	/**
	 * @return Returns the codigoZona.
	 */
	@Size(max = 4)
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
	 * @return Returns the oidZona.
	 */
	public Integer getOidZona() {
		return oidZona;
	}

	/**
	 * @param oidZona The oidZona to set.
	 */
	public void setOidZona(Integer oidZona) {
		this.oidZona = oidZona;
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
		if(calendar!=null && calendar.toString().length()>0){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			this.fechaCalendario = sdf.format(calendar);
		}
		this.calendar = calendar;
	}
	
	/*
	 *  (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	/*
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.fechaCalendario="";
		this.selectedItem="";
		this.selectedItems=null;
		
	}*/
	
}
