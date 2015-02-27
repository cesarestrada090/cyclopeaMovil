package biz.belcorp.ssicc.web.spusicc.form;

import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * The Class MantenimientoPERPercepcionesOtrosCanalesForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 13/01/2015
 */
public class MantenimientoPERPercepcionesOtrosCanalesForm extends BaseEditForm {

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String codigoSociedad;

	private String codigoCanal;

	private String codigoAcceso;

	private String codigoSubacceso;

	private String tipoCliente;

	private String codigoConsultora;
	
	private String descripcionConsultora;

	private String tipoDocumentoIdentidad;

	private String numeroDocumentoIdentidad;

	private String serieComprobantePercepcion;

	private String numeroComprobantePercepcion;

	private String secuenciaComprobantePercepcion;
	
	private String correlativoComprobantePercepcion;

	private String montoTotalDocumentoLegal;

	private String montoPago;

	private String montoPercepcion;

	private String porcentajePercepcion;

	private String tipoDocumentoLegal;

	private String serieDocumentoLegal;

	private String numeroDocumentoLegal;
	
	private Date fechaEmisionComprobantePercepcion;
	
	private Date fechaEmisionDocumentoLegal;
	
	private String apellidoPaterno;
	
	private String apellidoMaterno;
	
	private String nombres;
		
	protected String[] selectedItems = {};
	
	protected String selectedItem = null;


	/**
	 * @return Returns the codigoAcceso.
	 */
	public String getCodigoAcceso() {
		return codigoAcceso;
	}

	/**
	 * @param codigoAcceso The codigoAcceso to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoAcceso(String codigoAcceso) {
		this.codigoAcceso = codigoAcceso;
	}

	/**
	 * @return Returns the codigoCanal.
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal The codigoCanal to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return Returns the codigoConsultora.
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	/**
	 * @param codigoConsultora The codigoConsultora to set.
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the codigoSociedad.
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	/**
	 * @param codigoSociedad The codigoSociedad to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}

	/**
	 * @return Returns the codigoSubacceso.
	 */
	public String getCodigoSubacceso() {
		return codigoSubacceso;
	}

	/**
	 * @param codigoSubacceso The codigoSubacceso to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoSubacceso(String codigoSubacceso) {
		this.codigoSubacceso = codigoSubacceso;
	}

	/**
	 * @return Returns the correlativoComprobantePercepcion.
	 */
	public String getCorrelativoComprobantePercepcion() {
		return correlativoComprobantePercepcion;
	}

	/**
	 * @param correlativoComprobantePercepcion The correlativoComprobantePercepcion to set.
	 */
	public void setCorrelativoComprobantePercepcion(
			String correlativoComprobantePercepcion) {
		this.correlativoComprobantePercepcion = correlativoComprobantePercepcion;
	}

	/**
	 * @return Returns the montoPago.
	 */
	public String getMontoPago() {
		return montoPago;
	}

	/**
	 * @param montoPago The montoPago to set.
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="^\d{1,9}(.(\d{1,2}))?$"
	 */
	public void setMontoPago(String montoPago) {
		this.montoPago = montoPago;
	}

	/**
	 * @return Returns the montoPercepcion.
	 */
	public String getMontoPercepcion() {
		return montoPercepcion;
	}

	/**
	 * @param montoPercepcion The montoPercepcion to set.
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="^\d{1,9}(.(\d{1,2}))?$" 
	 */
	public void setMontoPercepcion(String montoPercepcion) {
		this.montoPercepcion = montoPercepcion;
	}

	/**
	 * @return Returns the montoTotalDocumentoLegal.
	 */
	public String getMontoTotalDocumentoLegal() {
		return montoTotalDocumentoLegal;
	}

	/**
	 * @param montoTotalDocumentoLegal The montoTotalDocumentoLegal to set.
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="^\d{1,9}(.(\d{1,2}))?$"
	 */
	public void setMontoTotalDocumentoLegal(String montoTotalDocumentoLegal) {
		this.montoTotalDocumentoLegal = montoTotalDocumentoLegal;
	}

	/**
	 * @return Returns the numeroComprobantePercepcion.
	 */
	public String getNumeroComprobantePercepcion() {
		return numeroComprobantePercepcion;
	}

	/**
	 * @param numeroComprobantePercepcion The numeroComprobantePercepcion to set.
	 * @struts.validator type = "required"
	 */
	public void setNumeroComprobantePercepcion(String numeroComprobantePercepcion) {
		this.numeroComprobantePercepcion = numeroComprobantePercepcion;
	}

	/**
	 * @return Returns the numeroDocumentoIdentidad.
	 */
	public String getNumeroDocumentoIdentidad() {
		return numeroDocumentoIdentidad;
	}

	/**
	 * @param numeroDocumentoIdentidad The numeroDocumentoIdentidad to set.
	 */
	public void setNumeroDocumentoIdentidad(String numeroDocumentoIdentidad) {
		this.numeroDocumentoIdentidad = numeroDocumentoIdentidad;
	}

	/**
	 * @return Returns the numeroDocumentoLegal.
	 */
	public String getNumeroDocumentoLegal() {
		return numeroDocumentoLegal;
	}

	/**
	 * @param numeroDocumentoLegal The numeroDocumentoLegal to set.
	 */
	public void setNumeroDocumentoLegal(String numeroDocumentoLegal) {
		this.numeroDocumentoLegal = numeroDocumentoLegal;
	}

	/**
	 * @return Returns the porcentajePercepcion.
	 */
	public String getPorcentajePercepcion() {
		return porcentajePercepcion;
	}

	/**
	 * @param porcentajePercepcion The porcentajePercepcion to set.
	 */
	public void setPorcentajePercepcion(String porcentajePercepcion) {
		this.porcentajePercepcion = porcentajePercepcion;
	}

	/**
	 * @return Returns the secuenciaComprobantePercepcion.
	 */
	public String getSecuenciaComprobantePercepcion() {
		return secuenciaComprobantePercepcion;
	}

	/**
	 * @param secuenciaComprobantePercepcion The secuenciaComprobantePercepcion to set.
	 * @struts.validator type = "required"
	 */
	public void setSecuenciaComprobantePercepcion(
			String secuenciaComprobantePercepcion) {
		this.secuenciaComprobantePercepcion = secuenciaComprobantePercepcion;
	}

	/**
	 * @return Returns the selectedItem.
	 */
	public String getSelectedItem() {
		return selectedItem;
	}

	/**
	 * @param selectedItem The selectedItem to set.
	 */
	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}

	/**
	 * @return Returns the selectedItems.
	 */
	public String[] getSelectedItems() {
		return selectedItems;
	}

	/**
	 * @param selectedItems The selectedItems to set.
	 */
	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}

	/**
	 * @return Returns the serieComprobantePercepcion.
	 */
	public String getSerieComprobantePercepcion() {
		return serieComprobantePercepcion;
	}

	/**
	 * @param serieComprobantePercepcion The serieComprobantePercepcion to set.
	 * @struts.validator type = "required"
	 */
	public void setSerieComprobantePercepcion(String serieComprobantePercepcion) {
		this.serieComprobantePercepcion = serieComprobantePercepcion;
	}

	/**
	 * @return Returns the serieDocumentoLegal.
	 */
	public String getSerieDocumentoLegal() {
		return serieDocumentoLegal;
	}

	/**
	 * @param serieDocumentoLegal The serieDocumentoLegal to set.
	 */
	public void setSerieDocumentoLegal(String serieDocumentoLegal) {
		this.serieDocumentoLegal = serieDocumentoLegal;
	}

	/**
	 * @return Returns the tipoCliente.
	 */
	public String getTipoCliente() {
		return tipoCliente;
	}

	/**
	 * @param tipoCliente The tipoCliente to set.
	 * @struts.validator type = "required"
	 */
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	/**
	 * @return Returns the tipoDocumentoIdentidad.
	 */
	public String getTipoDocumentoIdentidad() {
		return tipoDocumentoIdentidad;
	}

	/**
	 * @param tipoDocumentoIdentidad The tipoDocumentoIdentidad to set.
	 */
	public void setTipoDocumentoIdentidad(String tipoDocumentoIdentidad) {
		this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
	}

	/**
	 * @return Returns the tipoDocumentoLegal.
	 */
	public String getTipoDocumentoLegal() {
		return tipoDocumentoLegal;
	}

	/**
	 * @param tipoDocumentoLegal The tipoDocumentoLegal to set.
	 */
	public void setTipoDocumentoLegal(String tipoDocumentoLegal) {
		this.tipoDocumentoLegal = tipoDocumentoLegal;
	}

	/**
	 * @return Returns the descripcionConsultora.
	 */
	public String getDescripcionConsultora() {
		return descripcionConsultora;
	}

	/**
	 * @param descripcionConsultora The descripcionConsultora to set.
	 */
	public void setDescripcionConsultora(String descripcionConsultora) {
		this.descripcionConsultora = descripcionConsultora;
	}

	/**
	 * @return Returns the apellidoMaterno.
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	/**
	 * @param apellidoMaterno The apellidoMaterno to set.
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	/**
	 * @return Returns the apellidoPaterno.
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	/**
	 * @param apellidoPaterno The apellidoPaterno to set.
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	/**
	 * @return Returns the nombres.
	 */
	public String getNombres() {
		return nombres;
	}

	/**
	 * @param nombres The nombres to set.
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	/**
	 * @return the fechaEmisionComprobantePercepcion
	 */
	public Date getFechaEmisionComprobantePercepcion() {
		return fechaEmisionComprobantePercepcion;
	}

	/**
	 * @param fechaEmisionComprobantePercepcion the fechaEmisionComprobantePercepcion to set
	 */
	public void setFechaEmisionComprobantePercepcion(
			Date fechaEmisionComprobantePercepcion) {
		this.fechaEmisionComprobantePercepcion = fechaEmisionComprobantePercepcion;
	}

	/**
	 * @return the fechaEmisionDocumentoLegal
	 */
	public Date getFechaEmisionDocumentoLegal() {
		return fechaEmisionDocumentoLegal;
	}

	/**
	 * @param fechaEmisionDocumentoLegal the fechaEmisionDocumentoLegal to set
	 */
	public void setFechaEmisionDocumentoLegal(Date fechaEmisionDocumentoLegal) {
		this.fechaEmisionDocumentoLegal = fechaEmisionDocumentoLegal;
	}

}
