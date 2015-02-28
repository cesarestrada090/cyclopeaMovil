package biz.belcorp.ssicc.web.spusicc.cronograma.form;



import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoCRACalendarioCopiaPopupForm extends BaseSearchForm {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String anhio;
	private String oidActividad;
	private String nombreActividad;
	private String[] actividadRegenerar;

	private String salirPantalla = "N";

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the oidActividad
	 */
	public String getOidActividad() {
		return oidActividad;
	}

	/**
	 * @param oidActividad
	 *            the oidActividad to set
	 */
	public void setOidActividad(String oidActividad) {
		this.oidActividad = oidActividad;
	}

	/**
	 * @return the nombreActividad
	 */
	public String getNombreActividad() {
		return nombreActividad;
	}

	/**
	 * @param nombreActividad
	 *            the nombreActividad to set
	 */
	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}

	/**
	 * @return the actividadRegenerar
	 */
	public String[] getActividadRegenerar() {
		return actividadRegenerar;
	}

	/**
	 * @param actividadRegenerar
	 *            the actividadRegenerar to set
	 * @struts.validator type = "required"
	 */
	public void setActividadRegenerar(String[] actividadRegenerar) {
		this.actividadRegenerar = actividadRegenerar;
	}

	/**
	 * @return the salirPantalla
	 */
	public String getSalirPantalla() {
		return salirPantalla;
	}

	/**
	 * @param salirPantalla
	 *            the salirPantalla to set
	 */
	public void setSalirPantalla(String salirPantalla) {
		this.salirPantalla = salirPantalla;
	}

	/**
	 * @return the anhio
	 */
	public String getAnhio() {
		return anhio;
	}

	/**
	 * @param anhio
	 *            the anhio to set
	 */
	public void setAnhio(String anhio) {
		this.anhio = anhio;
	}

}
