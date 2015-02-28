package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;

import org.apache.struts.upload.FormFile;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoSTOControlFnePorcentajeForm extends BaseEditForm implements Serializable
{

private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	
	private String codigoCliente;
	
	private String tipoCliente;
	
	private String subTipoCliente;
	
	private String tipoClasificacion;
	
	private String clasificacion;
	
	private String codigoPeriodo;
	
	private String codigoRegion;
	
	private String codigoZona;
	
	private String observaciones;
	
	private String codigosErrados;
	
	private UploadedFile clienteFile; // objeto que se utilizara para el upload

	private String tipoBloqueo;
	
	private String codigosErradosFile;
	
	private String codigoTipoDocumento;
	
	private Double porcentaje;
	
	/**
	 * @return
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.validator.ValidatorForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset() {
		this.tipoCliente = null;
		this.subTipoCliente = null;
		this.tipoClasificacion = null;
		this.clasificacion = null;
		this.codigoRegion = null;
		this.codigoZona = null;
		this.observaciones = null;
		this.codigoPeriodo = null;
		this.codigoCliente = null;
		this.codigosErrados = null;
	}

	/**
	 * @return
	 */
	public String getCodigoPais() {
		return codigoPais;
	}


	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return
	 */
	public String getTipoCliente() {
		return tipoCliente;
	}

	/**
	 * @param tipoCliente
	 */
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	/**
	 * @return
	 */
	public String getSubTipoCliente() {
		return subTipoCliente;
	}

	/**
	 * @param subTipoCliente
	 */
	public void setSubTipoCliente(String subTipoCliente) {
		this.subTipoCliente = subTipoCliente;
	}

	/**
	 * @return
	 */
	public String getTipoClasificacion() {
		return tipoClasificacion;
	}

	/**
	 * @param tipoClasificacion
	 */
	public void setTipoClasificacion(String tipoClasificacion) {
		this.tipoClasificacion = tipoClasificacion;
	}

	/**
	 * @return
	 */
	public String getClasificacion() {
		return clasificacion;
	}

	/**
	 * @param clasificacion
	 */
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	/**
	 * @return
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return the codigosErrados
	 */
	public String getCodigosErrados() {
		return codigosErrados;
	}

	/**
	 * @param codigosErrados the codigosErrados to set
	 */
	public void setCodigosErrados(String codigosErrados) {
		this.codigosErrados = codigosErrados;
	}

	/**
	 * @return the clienteFile
	 */
	

	/**
	 * @return the tipoBloqueo
	 */
	public String getTipoBloqueo() {
		return tipoBloqueo;
	}

	public UploadedFile getClienteFile() {
		return clienteFile;
	}

	public void setClienteFile(UploadedFile clienteFile) {
		this.clienteFile = clienteFile;
	}

	/**
	 * @param tipoBloqueo the tipoBloqueo to set
	 */
	public void setTipoBloqueo(String tipoBloqueo) {
		this.tipoBloqueo = tipoBloqueo;
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

	/**
	 * @return the codigoTipoDocumento
	 */
	public String getCodigoTipoDocumento() {
		return codigoTipoDocumento;
	}

	/**
	 * @param codigoTipoDocumento the codigoTipoDocumento to set
	 */
	public void setCodigoTipoDocumento(String codigoTipoDocumento) {
		this.codigoTipoDocumento = codigoTipoDocumento;
	}

	/**
	 * @return the porcentaje
	 */
	public Double getPorcentaje() {
		return porcentaje;
	}

	/**
	 * @param porcentaje the porcentaje to set
	 */
	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}
}
