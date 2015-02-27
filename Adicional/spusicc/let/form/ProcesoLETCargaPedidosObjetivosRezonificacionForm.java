package biz.belcorp.ssicc.web.spusicc.let.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoLETCargaPedidosObjetivosRezonificacionForm extends BaseProcesoForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPeriodo;

	private String[] codigoRegion;

	private String[] codigoZona;
	
	private boolean mostrarRegionesCerradasList;

	private String indicadorValidaCargaObjetivos;

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public String[] getCodigoRegion() {
		return codigoRegion;
	}

	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	public String[] getCodigoZona() {
		return codigoZona;
	}

	public void setCodigoZona(String[] codigoZona) {
		this.codigoZona = codigoZona;
	}

	public boolean isMostrarRegionesCerradasList() {
		return mostrarRegionesCerradasList;
	}

	public void setMostrarRegionesCerradasList(boolean mostrarRegionesCerradasList) {
		this.mostrarRegionesCerradasList = mostrarRegionesCerradasList;
	}

	public String getIndicadorValidaCargaObjetivos() {
		return indicadorValidaCargaObjetivos;
	}

	public void setIndicadorValidaCargaObjetivos(String indicadorValidaCargaObjetivos) {
		this.indicadorValidaCargaObjetivos = indicadorValidaCargaObjetivos;
	}
}
