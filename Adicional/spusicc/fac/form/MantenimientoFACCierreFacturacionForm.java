package biz.belcorp.ssicc.web.spusicc.fac.form;

import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoFACCierreFacturacionForm extends BaseEditForm{
	
	private static final long serialVersionUID = 8944337997623199466L;	
	
	private String codigoPais;
	private String campanhaProceso;
	private String fechaCierre;
	private String flagCierreCampanha;
	private String [] zonas;
	private String [] regions;
	private String [] soloRegions;
	private String indicadorZona;
	private String indicadorRegion;
	private String tabSeleccion;
	
	private String[] selectedItemsZona;
	private String[] selectedItemsRegion;
	private String indicadorEdit;
	private String fechaProcesoActual;
	private Date fechaCierreDate;
	private Date fechaProcesoActualDate;
	

	/* (non-Javadoc)
	 * @see org.apache.struts.validator.ValidatorForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	/*
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.selectedItems=null;
		campanhaProceso=fechaCierre="";
		flagCierreCampanha=Constants.NUMERO_CERO;
		zonas=regions=soloRegions=null;
		selectedItemsZona=selectedItemsRegion=null;
		indicadorEdit=Constants.NUMERO_CERO;
	}
	*/
	
	
	public String getCodigoPais() {
		return codigoPais;
	}
  
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCampanhaProceso() {
		return campanhaProceso;
	}

	public void setCampanhaProceso(String campanhaProceso) {
		this.campanhaProceso = campanhaProceso;
	}

	public String getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public String getFlagCierreCampanha() {
		return flagCierreCampanha;
	}

	public void setFlagCierreCampanha(String flagCierreCampanha) {
		this.flagCierreCampanha = flagCierreCampanha;
	}

	public String[] getZonas() {
		return zonas;
	}

	public void setZonas(String[] zonas) {
		this.zonas = zonas;
	}

	public String[] getRegions() {
		return regions;
	}

	public void setRegions(String[] regions) {
		this.regions = regions;
	}

	public String getIndicadorZona() {
		return indicadorZona;
	}

	public void setIndicadorZona(String indicadorZona) {
		this.indicadorZona = indicadorZona;
	}

	public String getIndicadorRegion() {
		return indicadorRegion;
	}

	public void setIndicadorRegion(String indicadorRegion) {
		this.indicadorRegion = indicadorRegion;
	}

	public String getTabSeleccion() {
		return tabSeleccion;
	}

	public void setTabSeleccion(String tabSeleccion) {
		this.tabSeleccion = tabSeleccion;
	}

	public String[] getSelectedItemsZona() {
		return selectedItemsZona;
	}

	public void setSelectedItemsZona(String[] selectedItemsZona) {
		this.selectedItemsZona = selectedItemsZona;
	}

	public String[] getSelectedItemsRegion() {
		return selectedItemsRegion;
	}

	public void setSelectedItemsRegion(String[] selectedItemsRegion) {
		this.selectedItemsRegion = selectedItemsRegion;
	}

	public String[] getSoloRegions() {
		return soloRegions;
	}

	public void setSoloRegions(String[] soloRegions) {
		this.soloRegions = soloRegions;
	}

	public String getIndicadorEdit() {
		return indicadorEdit;
	}

	public void setIndicadorEdit(String indicadorEdit) {
		this.indicadorEdit = indicadorEdit;
	}

	public String getFechaProcesoActual() {
		return fechaProcesoActual;
	}

	public void setFechaProcesoActual(String fechaProcesoActual) {
		this.fechaProcesoActual = fechaProcesoActual;
	}

	public Date getFechaCierreDate() {
		return fechaCierreDate;
	}

	public void setFechaCierreDate(Date fechaCierreDate) {
		this.fechaCierreDate = fechaCierreDate;
	}

	public Date getFechaProcesoActualDate() {
		return fechaProcesoActualDate;
	}

	public void setFechaProcesoActualDate(Date fechaProcesoActualDate) {
		this.fechaProcesoActualDate = fechaProcesoActualDate;
	}
	

}
