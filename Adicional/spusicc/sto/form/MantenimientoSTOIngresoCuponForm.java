package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoSTOIngresoCuponForm extends BaseEditForm implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8421936216752560777L;
	private String codigoPais;
	private String codCliente;
	private String valorPagado;
	private String fechaRegistro;
	private Date fechaRegistroD;
	private String indicadorRechazo;
	private String oidPais;
	private String numeroDocumentoIdentidad;
	private String codigoPeriodo;

	public MantenimientoSTOIngresoCuponForm() {
		this.fechaRegistroD = new Date();
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codCliente
	 */
	public String getCodCliente() {
		return codCliente;
	}

	/**
	 * @param codCliente
	 *            the codCliente to set
	 */
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}

	/**
	 * @return the valorPagado
	 */
	public String getValorPagado() {
		return valorPagado;
	}

	/**
	 * @param valorPagado
	 *            the valorPagado to set
	 */
	public void setValorPagado(String valorPagado) {
		this.valorPagado = valorPagado;
	}

	/**
	 * @return the fechaRegistro
	 */
	public String getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * @param fechaRegistro
	 *            the fechaRegistro to set
	 */
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	/**
	 * @return the fechaRegistroD
	 */
	public Date getFechaRegistroD() {
		return fechaRegistroD;
	}

	/**
	 * @param fechaRegistroD
	 *            the fechaRegistroD to set
	 */
	public void setFechaRegistroD(Date fechaRegistroD) {
		this.fechaRegistroD = fechaRegistroD;
	}

	/**
	 * @return the indicadorRechazo
	 */
	public String getIndicadorRechazo() {
		return indicadorRechazo;
	}

	/**
	 * @param indicadorRechazo
	 *            the indicadorRechazo to set
	 */
	public void setIndicadorRechazo(String indicadorRechazo) {
		this.indicadorRechazo = indicadorRechazo;
	}

	/**
	 * @return the oidPais
	 */
	public String getOidPais() {
		return oidPais;
	}

	/**
	 * @param oidPais
	 *            the oidPais to set
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}

	/**
	 * @return the numeroDocumentoIdentidad
	 */
	public String getNumeroDocumentoIdentidad() {
		return numeroDocumentoIdentidad;
	}

	/**
	 * @param numeroDocumentoIdentidad
	 *            the numeroDocumentoIdentidad to set
	 */
	public void setNumeroDocumentoIdentidad(String numeroDocumentoIdentidad) {
		this.numeroDocumentoIdentidad = numeroDocumentoIdentidad;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 *            the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

}
