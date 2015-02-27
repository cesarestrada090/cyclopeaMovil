package biz.belcorp.ssicc.web.spusicc.form;

import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * The Class MantenimientoPERPercepcionesOtrosCanalesSearchForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 13/01/2015
 */
public class MantenimientoPERPercepcionesOtrosCanalesSearchForm extends BaseSearchForm {

	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoSociedad;
	private String codigoCanal;
	private String codigoAcceso;
	private String codigoSubacceso;
	private String tipoCliente;
	private String codigoCliente;
	private String serieComproPercepcion;
	private String numeroComproPercepcion;

	//campo add
	private String codigoClienteBuscar;
	private String fechaEmisionComprobantePercepcion;
	private Date fechaEmisionComprobantePercepcionD;
	
	
	/**
	 * @return the fechaEmisionComprobantePercepcion
	 */
	public String getFechaEmisionComprobantePercepcion() {
		return fechaEmisionComprobantePercepcion;
	}

	/**
	 * @param fechaEmisionComprobantePercepcion the fechaEmisionComprobantePercepcion to set
	 */
	public void setFechaEmisionComprobantePercepcion(
			String fechaEmisionComprobantePercepcion) {
		this.fechaEmisionComprobantePercepcion = fechaEmisionComprobantePercepcion;
	}

	/**
	 * @return the codigoClienteBuscar
	 */
	public String getCodigoClienteBuscar() {
		return codigoClienteBuscar;
	}

	/**
	 * @param codigoClienteBuscar the codigoClienteBuscar to set
	 */
	public void setCodigoClienteBuscar(String codigoClienteBuscar) {
		this.codigoClienteBuscar = codigoClienteBuscar;
	}
	
	public void inicializar() {
		this.codigoSociedad="";
		this.codigoCanal="";
		this.codigoAcceso="";
		this.codigoSubacceso="";
		this.tipoCliente="";
		this.codigoCliente="";
	}
	/**
	 * @return Returns the codigoAcceso.
	 */
	public String getCodigoAcceso() {
		return codigoAcceso;
	}
	/**
	 * @param codigoAcceso The codigoAcceso to set.
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
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}
	/**
	 * @return Returns the codigoCliente.
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}
	/**
	 * @param codigoCliente The codigoCliente to set.
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
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
	 */
	public void setCodigoSubacceso(String codigoSubacceso) {
		this.codigoSubacceso = codigoSubacceso;
	}
	/**
	 * @return Returns the tipoCliente.
	 */
	public String getTipoCliente() {
		return tipoCliente;
	}
	/**
	 * @param tipoCliente The tipoCliente to set.
	 */
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	/**
	 * @return Returns the numeroComproPercepcion.
	 */
	public String getNumeroComproPercepcion() {
		return numeroComproPercepcion;
	}
	/**
	 * @param numeroComproPercepcion The numeroComproPercepcion to set.
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="^\d{1,8}$"
	 */
	public void setNumeroComproPercepcion(String numeroComproPercepcion) {
		this.numeroComproPercepcion = numeroComproPercepcion;
	}
	/**
	 * @return Returns the serieComproPercepcion.
	 */
	public String getSerieComproPercepcion() {
		return serieComproPercepcion;
	}
	/**
	 * @param serieComproPercepcion The serieComproPercepcion to set.
     * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="^\d{1,8}$"
	 */
	public void setSerieComproPercepcion(String serieComproPercepcion) {
		this.serieComproPercepcion = serieComproPercepcion;
	}

	/**
	 * @return the fechaEmisionComprobantePercepcionD
	 */
	public Date getFechaEmisionComprobantePercepcionD() {
		return fechaEmisionComprobantePercepcionD;
	}

	/**
	 * @param fechaEmisionComprobantePercepcionD the fechaEmisionComprobantePercepcionD to set
	 */
	public void setFechaEmisionComprobantePercepcionD(
			Date fechaEmisionComprobantePercepcionD) {
		this.fechaEmisionComprobantePercepcionD = fechaEmisionComprobantePercepcionD;
	}
	
}
