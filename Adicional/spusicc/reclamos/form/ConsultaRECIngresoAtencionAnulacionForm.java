package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * 
 * <p>
 * <a href="ConsultaRECIngresoAtencionAnulacionForm.java.html"> <i>View
 * Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * 
 */
public class ConsultaRECIngresoAtencionAnulacionForm extends
		BaseSearchForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8407697347427429308L;

	private String codigoPais;

	private String tipoConsulta;

	private String lineaDefecto;

	private String numeroLote;

	private String fecha;

	private String usuario;

	private String flagDelete;

	private String flagRolDelete;
	
	private String codigoCliente;
	
	private Date fechaD;


	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return
	 */
	public String getTipoConsulta() {
		return tipoConsulta;
	}

	/**
	 * @param tipoConsulta
	 * @struts.validator type = "required"
	 */
	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	/**
	 * @return the lineaDefecto
	 */
	public String getLineaDefecto() {
		return lineaDefecto;
	}

	/**
	 * @param lineaDefecto
	 *            the lineaDefecto to set
	 */
	public void setLineaDefecto(String lineaDefecto) {
		this.lineaDefecto = lineaDefecto;
	}

	/**
	 * @return the numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote
	 *            the numeroLote to set
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 * @struts.validator type="date"
	 * @struts.validator-var name="datePatternStrict"
	 *                       value="${defaultDatePattern}"
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return
	 */
	public String getFlagDelete() {
		return flagDelete;
	}

	/**
	 * @param flagDelete
	 */
	public void setFlagDelete(String flagDelete) {
		this.flagDelete = flagDelete;
	}

	/**
	 * @return
	 */
	public String getFlagRolDelete() {
		return flagRolDelete;
	}

	/**
	 * @param flagRolDelete
	 */
	public void setFlagRolDelete(String flagRolDelete) {
		this.flagRolDelete = flagRolDelete;
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
	 * @return the fechaD
	 */
	public Date getFechaD() {
		return fechaD;
	}

	/**
	 * @param fechaD the fechaD to set
	 */
	public void setFechaD(Date fechaD) {
		this.fechaD = fechaD;
	}
	
	
	
}
