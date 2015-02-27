package biz.belcorp.ssicc.web.spusicc.inc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoINCHabilitacionConcursoCargaPuntajeForm extends BaseSearchForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3269022620138494516L;
	private String codigoPais;
	protected String[] selectedItems = {};
	protected String[] selectedItemsRemove = {};
	protected String selectedItem = null;
	
	private String[] listaConcurso;
	private String[] listaConcursoSeleccion;
	//
 
	
	
	/**
	 * @return the listaConcurso
	 */
	public String[] getListaConcurso() {
		return listaConcurso;
	}

	public String[] getSelectedItemsRemove() {
		return selectedItemsRemove;
	}

	public void setSelectedItemsRemove(String[] selectedItemsRemove) {
		this.selectedItemsRemove = selectedItemsRemove;
	}

	/**
	 * @param listaConcurso the listaConcurso to set
	 */
	public void setListaConcurso(String[] listaConcurso) {
		this.listaConcurso = listaConcurso;
	}

	/**
	 * @return the listaConcursoSeleccion
	 */
	public String[] getListaConcursoSeleccion() {
		return listaConcursoSeleccion;
	}

	/**
	 * @param listaConcursoSeleccion the listaConcursoSeleccion to set
	 */
	public void setListaConcursoSeleccion(String[] listaConcursoSeleccion) {
		this.listaConcursoSeleccion = listaConcursoSeleccion;
	}

	/**
	 * @return the selectedItems
	 */
	public String[] getSelectedItems() {
		return selectedItems;
	}

	/**
	 * @param selectedItems the selectedItems to set
	 */
	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}

	/**
	 * @return the selectedItem
	 */
	public String getSelectedItem() {
		return selectedItem;
	}

	/**
	 * @param selectedItem the selectedItem to set
	 */
	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}

	public void reset() {
		this.selectedItems = new String[0];
		this.listaConcurso=new String[0];
		this.listaConcursoSeleccion=new String[0];
	}
	
	public String getCodigoPais() {
		return codigoPais;
	}

  
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	

}
