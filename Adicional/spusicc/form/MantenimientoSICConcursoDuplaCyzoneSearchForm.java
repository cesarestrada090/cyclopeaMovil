package biz.belcorp.ssicc.web.spusicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoSICConcursoDuplaCyzoneSearchForm extends BaseSearchForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoMarcaSearch;
	private String codigoCanalSearch;
	private String codigoPaisSearch;	
	private String codigoConcursoSearch;	
	private String nombreConcursoSearch;	
	private String nombrePlantillaSearch;	
	private String codigoPlantillaSearch;
	
	private String versionSearch;
	
	public String getCodigoCanalSearch() {
		return codigoCanalSearch;
	}

	public void setCodigoCanalSearch(String codigoCanalSearch) {
		this.codigoCanalSearch = codigoCanalSearch;
	}

	public String getCodigoMarcaSearch() {
		return codigoMarcaSearch;
	}

	public void setCodigoMarcaSearch(String codigoMarcaSearch) {
		this.codigoMarcaSearch = codigoMarcaSearch;
	}


	/**
	 * @return Returns the codigoPaisSearch.
	 */
	public String getCodigoPaisSearch() {
		return codigoPaisSearch;
	}

	/**
	 * @param codigoPaisSearch The codigoPaisSearch to set.
	 * @struts.validator type = "required" 
	 */
	public void setCodigoPaisSearch(String codigoPaisSearch) {
		this.codigoPaisSearch = codigoPaisSearch;
	}


	/**
	 * @return Returns the codigoConcursoSearch.
	 */
	public String getCodigoConcursoSearch() {
		return codigoConcursoSearch;
	}


	public void setCodigoConcursoSearch(String codigoConcursoSearch) {
		this.codigoConcursoSearch = codigoConcursoSearch;
	}


	/**
	 * @return Returns the codigoPlantillaSearch.
	 */
	public String getCodigoPlantillaSearch() {
		return codigoPlantillaSearch;
	}


	/**
	 * @param codigoPlantillaSearch The codigoPlantillaSearch to set.
	 */
	public void setCodigoPlantillaSearch(String codigoPlantillaSearch) {
		this.codigoPlantillaSearch = codigoPlantillaSearch;
	}


	/**
	 * @return Returns the nombreConcursoSearch.
	 */
	public String getNombreConcursoSearch() {
		return nombreConcursoSearch;
	}


	/**
	 * @param nombreConcursoSearch The nombreConcursoSearch to set.
	 */
	public void setNombreConcursoSearch(String nombreConcursoSearch) {
		this.nombreConcursoSearch = nombreConcursoSearch;
	}


	/**
	 * @return Returns the nombrePlantillaSearch.
	 */
	public String getNombrePlantillaSearch() {
		return nombrePlantillaSearch;
	}


	/**
	 * @param nombrePlantillaSearch The nombrePlantillaSearch to set.
	 */
	public void setNombrePlantillaSearch(String nombrePlantillaSearch) {
		this.nombrePlantillaSearch = nombrePlantillaSearch;
	}


	/**
	 * @return Returns the versionSearch.
	 */
	public String getVersionSearch() {
		return versionSearch;
	}


	/**
	 * @param versionSearch The versionSearch to set.
	 */
	public void setVersionSearch(String versionSearch) {
		this.versionSearch = versionSearch;
	}

}
