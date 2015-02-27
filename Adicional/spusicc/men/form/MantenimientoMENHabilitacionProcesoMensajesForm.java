package biz.belcorp.ssicc.web.spusicc.men.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoMENHabilitacionProcesoMensajesForm extends BaseSearchForm{

	private static final long serialVersionUID = 1423542164355252717L;
	
	private String codigoPais;
	protected String[] selectedItems = {};
	protected String selectedItem = null;
	
	private String[] listaProceso;
	private String[] listaProcesoSeleccion;
	private String codigoMarca;
	private String codigoCanal;
	private String indicadorProceso;


	public String[] getlistaProceso() {
		return listaProceso;
	}

	public String getIndicadorProceso() {
		return indicadorProceso;
	}

	public void setIndicadorProceso(String indicadorProceso) {
		this.indicadorProceso = indicadorProceso;
	}

	public String getCodigoMarca() {
		return codigoMarca;
	}

	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	public String getCodigoCanal() {
		return codigoCanal;
	}

	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	public void setlistaProceso(String[] listaProceso) {
		this.listaProceso = listaProceso;
	}

	public String[] getlistaProcesoSeleccion() {
		return listaProcesoSeleccion;
	}

	public void setlistaProcesoSeleccion(String[] listaProcesoSeleccion) {
		this.listaProcesoSeleccion = listaProcesoSeleccion;
	}

	public String[] getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}

	public String getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}	
	
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
}
