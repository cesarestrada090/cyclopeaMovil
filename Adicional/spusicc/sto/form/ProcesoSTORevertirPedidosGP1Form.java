package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;
import java.util.Date;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoSTORevertirPedidosGP1Form extends BaseProcesoForm
implements Serializable{

	private static final long serialVersionUID = 1L;

	private String []selectedItems;
	private String tipoDocumento;
	private String descripcionDocumento;
	private String codigoPeriodo;
	private String fechaInicioProgramadaFacturacion;
	private Date fechaInicioProgramadaFacturacionD;
	private String fechaFinProgramadaFacturacion;
	private Date fechaFinProgramadaFacturacionD;
	private String codigoCliente;
	private String []regionList;
	private String []zonaList;
	private UploadedFile clienteFile;
	private String []clienteList;
	private String lineaDefecto;
	private String lineaMaxima;
	private String cargaCombo;
	private String accion;
	private String []selectedItemsSearch;
	private String tipoDocumentoSearch;
	private String codigoPeriodoSearch;
	private String fechaInicioProgramadaFacturacionSearch;
	private String fechaFinProgramadaFacturacionSearch;
	private String codigoClienteSearch;
	private String []regionListSearch;
	private String []zonaListSearch;
	private String accionSearch;
	private String grupoProceso;
	private String codigosErradosFile;

	
	
	public Date getFechaInicioProgramadaFacturacionD() {
		return fechaInicioProgramadaFacturacionD;
	}
	public void setFechaInicioProgramadaFacturacionD(
			Date fechaInicioProgramadaFacturacionD) {
		this.fechaInicioProgramadaFacturacionD = fechaInicioProgramadaFacturacionD;
	}
	public Date getFechaFinProgramadaFacturacionD() {
		return fechaFinProgramadaFacturacionD;
	}
	public void setFechaFinProgramadaFacturacionD(
			Date fechaFinProgramadaFacturacionD) {
		this.fechaFinProgramadaFacturacionD = fechaFinProgramadaFacturacionD;
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
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	/**
	 * @return the descripcionDocumento
	 */
	public String getDescripcionDocumento() {
		return descripcionDocumento;
	}
	/**
	 * @param descripcionDocumento the descripcionDocumento to set
	 */
	public void setDescripcionDocumento(String descripcionDocumento) {
		this.descripcionDocumento = descripcionDocumento;
	}
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return the fechaInicioProgramadaFacturacion
	 */
	public String getFechaInicioProgramadaFacturacion() {
		return fechaInicioProgramadaFacturacion;
	}
	/**
	 * @param fechaInicioProgramadaFacturacion the fechaInicioProgramadaFacturacion to set
	 */
	public void setFechaInicioProgramadaFacturacion(
			String fechaInicioProgramadaFacturacion) {
		this.fechaInicioProgramadaFacturacion = fechaInicioProgramadaFacturacion;
	}
	/**
	 * @return the fechaFinProgramadaFacturacion
	 */
	public String getFechaFinProgramadaFacturacion() {
		return fechaFinProgramadaFacturacion;
	}
	/**
	 * @param fechaFinProgramadaFacturacion the fechaFinProgramadaFacturacion to set
	 */
	public void setFechaFinProgramadaFacturacion(
			String fechaFinProgramadaFacturacion) {
		this.fechaFinProgramadaFacturacion = fechaFinProgramadaFacturacion;
	}
	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}
	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	/**
	 * @return the regionList
	 */
	public String[] getRegionList() {
		return regionList;
	}
	/**
	 * @param regionList the regionList to set
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}
	/**
	 * @return the zonaList
	 */
	public String[] getZonaList() {
		return zonaList;
	}
	/**
	 * @param zonaList the zonaList to set
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}
	/**
	 * @return the clienteFile
	 */
	
	/**
	 * @return the clienteList
	 */
	public String[] getClienteList() {
		return clienteList;
	}
	
	public UploadedFile getClienteFile() {
		return clienteFile;
	}
	public void setClienteFile(UploadedFile clienteFile) {
		this.clienteFile = clienteFile;
	}
	/**
	 * @param clienteList the clienteList to set
	 */
	public void setClienteList(String[] clienteList) {
		this.clienteList = clienteList;
	}
	/**
	 * @return the lineaDefecto
	 */
	public String getLineaDefecto() {
		return lineaDefecto;
	}
	/**
	 * @param lineaDefecto the lineaDefecto to set
	 */
	public void setLineaDefecto(String lineaDefecto) {
		this.lineaDefecto = lineaDefecto;
	}
	/**
	 * @return the lineaMaxima
	 */
	public String getLineaMaxima() {
		return lineaMaxima;
	}
	/**
	 * @param lineaMaxima the lineaMaxima to set
	 */
	public void setLineaMaxima(String lineaMaxima) {
		this.lineaMaxima = lineaMaxima;
	}
	/**
	 * @return the cargaCombo
	 */
	public String getCargaCombo() {
		return cargaCombo;
	}
	/**
	 * @param cargaCombo the cargaCombo to set
	 */
	public void setCargaCombo(String cargaCombo) {
		this.cargaCombo = cargaCombo;
	}
	/**
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}
	/**
	 * @param accion the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}
	/**
	 * @return the selectedItemsSearch
	 */
	public String[] getSelectedItemsSearch() {
		return selectedItemsSearch;
	}
	/**
	 * @param selectedItemsSearch the selectedItemsSearch to set
	 */
	public void setSelectedItemsSearch(String[] selectedItemsSearch) {
		this.selectedItemsSearch = selectedItemsSearch;
	}
	/**
	 * @return the tipoDocumentoSearch
	 */
	public String getTipoDocumentoSearch() {
		return tipoDocumentoSearch;
	}
	/**
	 * @param tipoDocumentoSearch the tipoDocumentoSearch to set
	 */
	public void setTipoDocumentoSearch(String tipoDocumentoSearch) {
		this.tipoDocumentoSearch = tipoDocumentoSearch;
	}
	/**
	 * @return the codigoPeriodoSearch
	 */
	public String getCodigoPeriodoSearch() {
		return codigoPeriodoSearch;
	}
	/**
	 * @param codigoPeriodoSearch the codigoPeriodoSearch to set
	 */
	public void setCodigoPeriodoSearch(String codigoPeriodoSearch) {
		this.codigoPeriodoSearch = codigoPeriodoSearch;
	}
	/**
	 * @return the fechaInicioProgramadaFacturacionSearch
	 */
	public String getFechaInicioProgramadaFacturacionSearch() {
		return fechaInicioProgramadaFacturacionSearch;
	}
	/**
	 * @param fechaInicioProgramadaFacturacionSearch the fechaInicioProgramadaFacturacionSearch to set
	 */
	public void setFechaInicioProgramadaFacturacionSearch(
			String fechaInicioProgramadaFacturacionSearch) {
		this.fechaInicioProgramadaFacturacionSearch = fechaInicioProgramadaFacturacionSearch;
	}
	/**
	 * @return the fechaFinProgramadaFacturacionSearch
	 */
	public String getFechaFinProgramadaFacturacionSearch() {
		return fechaFinProgramadaFacturacionSearch;
	}
	/**
	 * @param fechaFinProgramadaFacturacionSearch the fechaFinProgramadaFacturacionSearch to set
	 */
	public void setFechaFinProgramadaFacturacionSearch(
			String fechaFinProgramadaFacturacionSearch) {
		this.fechaFinProgramadaFacturacionSearch = fechaFinProgramadaFacturacionSearch;
	}
	/**
	 * @return the codigoClienteSearch
	 */
	public String getCodigoClienteSearch() {
		return codigoClienteSearch;
	}
	/**
	 * @param codigoClienteSearch the codigoClienteSearch to set
	 */
	public void setCodigoClienteSearch(String codigoClienteSearch) {
		this.codigoClienteSearch = codigoClienteSearch;
	}
	/**
	 * @return the regionListSearch
	 */
	public String[] getRegionListSearch() {
		return regionListSearch;
	}
	/**
	 * @param regionListSearch the regionListSearch to set
	 */
	public void setRegionListSearch(String[] regionListSearch) {
		this.regionListSearch = regionListSearch;
	}
	/**
	 * @return the zonaListSearch
	 */
	public String[] getZonaListSearch() {
		return zonaListSearch;
	}
	/**
	 * @param zonaListSearch the zonaListSearch to set
	 */
	public void setZonaListSearch(String[] zonaListSearch) {
		this.zonaListSearch = zonaListSearch;
	}
	/**
	 * @return the accionSearch
	 */
	public String getAccionSearch() {
		return accionSearch;
	}
	/**
	 * @param accionSearch the accionSearch to set
	 */
	public void setAccionSearch(String accionSearch) {
		this.accionSearch = accionSearch;
	}
	/**
	 * @return the grupoProceso
	 */
	public String getGrupoProceso() {
		return grupoProceso;
	}
	/**
	 * @param grupoProceso the grupoProceso to set
	 */
	public void setGrupoProceso(String grupoProceso) {
		this.grupoProceso = grupoProceso;
	}
	/**
	 * @return the codigosErradosFile
	 */
	public String getCodigosErradosFile() {
		return codigosErradosFile;
	}
	/**
	 * @param codigosErradosFile the codigosErradosFile to set
	 */
	public void setCodigosErradosFile(String codigosErradosFile) {
		this.codigosErradosFile = codigosErradosFile;
	}

}
