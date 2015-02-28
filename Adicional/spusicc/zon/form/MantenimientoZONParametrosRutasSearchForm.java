package biz.belcorp.ssicc.web.spusicc.zon.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoZONParametrosRutasSearchForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:kgomez@sigcomt.com">Karina Gomez</a>
 * 
 *  @struts.form name = "mantenimientoZONParametrosRutasSearchForm"
 */
public class MantenimientoZONParametrosRutasSearchForm extends BaseSearchForm{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoNovedad;
	private String[] listaNovedades;
	
	
	/**
	 * @return the listaNovedades
	 */
	public String[] getListaNovedades() {
		return listaNovedades;
	}
	/**
	 * @param listaNovedades the listaNovedades to set
	 */
	public void setListaNovedades(String[] listaNovedades) {
		this.listaNovedades = listaNovedades;
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
	 * @return the codigoNovedad
	 */
	public String getCodigoNovedad() {
		return codigoNovedad;
	}
	/**
	 * @param codigoNovedad the codigoNovedad to set
	 */
	public void setCodigoNovedad(String codigoNovedad) {
		this.codigoNovedad = codigoNovedad;
	}
	
	
}
