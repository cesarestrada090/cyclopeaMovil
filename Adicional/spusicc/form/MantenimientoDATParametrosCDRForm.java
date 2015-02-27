package biz.belcorp.ssicc.web.spusicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoDATParametrosCDRForm extends BaseEditForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;

	private String idTipoSolicitud;
	private String indicadorAnulado;
	private String indicadorDevolucion;
	private String indicadorTrueque;
	private String indicadorCanje;
	private String indicadorFaltante;
	private String indicadorPremio;	
	private String indicadorNMP;
	private String indicadorPedidoServicio;
	private String indicadorVenta;

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
	 * @return Returns the indicadorAnulado.
	 */
	public String getIndicadorAnulado() {
		return indicadorAnulado;
	}

	/**
	 * @param indicadorAnulado
	 *            The indicadorAnulado to set.
	 * 
	 */
	public void setIndicadorAnulado(String indicadorAnulado) {
		this.indicadorAnulado = indicadorAnulado;
	}

	/**
	 * @return Returns the indicadorCanje.
	 */
	public String getIndicadorCanje() {
		return indicadorCanje;
	}

	/**
	 * @param indicadorCanje
	 *            The indicadorCanje to set.
	 * 
	 */
	public void setIndicadorCanje(String indicadorCanje) {
		this.indicadorCanje = indicadorCanje;
	}

	/**
	 * @return Returns the indicadorDevolucion.
	 */
	public String getIndicadorDevolucion() {
		return indicadorDevolucion;
	}

	/**
	 * @param indicadorDevolucion
	 *            The indicadorDevolucion to set.
	 * 
	 */
	public void setIndicadorDevolucion(String indicadorDevolucion) {
		this.indicadorDevolucion = indicadorDevolucion;
	}

	/**
	 * @return Returns the indicadorFaltante.
	 */
	public String getIndicadorFaltante() {
		return indicadorFaltante;
	}

	/**
	 * @param indicadorFaltante
	 *            The indicadorFaltante to set.
	 * 
	 */
	public void setIndicadorFaltante(String indicadorFaltante) {
		this.indicadorFaltante = indicadorFaltante;
	}

	/**
	 * @return Returns the indicadorPedidoServicio.
	 */

	public String getIndicadorPedidoServicio() {
		return indicadorPedidoServicio;
	}

	/**
	 * @param indicadorPedidoServicio
	 *            The indicadorPedidoServicio to set.
	 * 
	 */
	public void setIndicadorPedidoServicio(String indicadorPedidoServicio) {
		this.indicadorPedidoServicio = indicadorPedidoServicio;
	}

	/**
	 * @return Returns the indicadorPremio.
	 */

	public String getIndicadorPremio() {
		return indicadorPremio;
	}

	/**
	 * @param indicadorPremio
	 *            The indicadorPremio to set.
	 * 
	 */
	public void setIndicadorPremio(String indicadorPremio) {
		this.indicadorPremio = indicadorPremio;
	}

	/**
	 * @return Returns the indicadorTrueque.
	 */

	public String getIndicadorTrueque() {
		return indicadorTrueque;
	}

	/**
	 * @param indicadorTrueque
	 *            The indicadorTrueque to set.
	 * 
	 */
	public void setIndicadorTrueque(String indicadorTrueque) {
		this.indicadorTrueque = indicadorTrueque;
	}

	/**
	 * @return Returns the indicadorVenta.
	 */

	public String getIndicadorVenta() {
		return indicadorVenta;
	}

	/**
	 * @param indicadorVenta
	 *            The indicadorVenta to set.
	 * 
	 */

	public void setIndicadorVenta(String indicadorVenta) {
		this.indicadorVenta = indicadorVenta;
	}

	/**
	 * @return Returns the idTipoSolicitud.
	 */
	public String getIdTipoSolicitud() {
		return idTipoSolicitud;
	}
	/**
	 * @param idTipoSolicitud
	 *            The idTipoSolicitud to set.
	 * @struts.validator type = "required"
	 */
	public void setIdTipoSolicitud(String idTipoSolicitud) {
		this.idTipoSolicitud = idTipoSolicitud;
	}
	/**
	 * @return Returns the indicadorNMP.
	 */
	public String getIndicadorNMP() {
		return indicadorNMP;
	}
	/**
	 * @param indicadorNMP
	 *            The indicadorNMP to set.
	 * 
	 */
	public void setIndicadorNMP(String indicadorNMP) {
		this.indicadorNMP = indicadorNMP;
	}
}
