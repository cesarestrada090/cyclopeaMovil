package biz.belcorp.ssicc.web.spusicc.ventas.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoVENGenerarHistoricoVentaCatalogoForm extends BaseProcesoForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoMarca;
	private String descripcionMarca;
	private String codigoCanal;
	private String descripcionCanal;
	private String codigoSociedad;
	private String descripcionSociedad;	
	private String codigoCampanha;	
	private String codigoAlmacen;
	private String descripcionAlmacen;
	
	/**
	 * @return Returns the codigoCanal.
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal
	 *            The codigoCanal to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return Returns the codigoMarca.
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca
	 *            The codigoMarca to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

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
	 * @return Returns the descripcionCanal.
	 */
	public String getDescripcionCanal() {
		return descripcionCanal;
	}

	/**
	 * @param descripcionCanal
	 *            The descripcionCanal to set.
	 */
	public void setDescripcionCanal(String descripcionCanal) {
		this.descripcionCanal = descripcionCanal;
	}

	/**
	 * @return Returns the descripcionMarca.
	 */
	public String getDescripcionMarca() {
		return descripcionMarca;
	}

	/**
	 * @param descripcionMarca
	 *            The descripcionMarca to set.
	 */
	public void setDescripcionMarca(String descripcionMarca) {
		this.descripcionMarca = descripcionMarca;
	}

	
	/**
	 * @return Returns the codigoSociedad.
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	/**
	 * @param codigoSociedad
	 *             The codigoSociedad to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}

	/**
	 * @return Returns the descripcionSociedad.
	 */
	public String getDescripcionSociedad() {
		return descripcionSociedad;
	}

	/**
	 * @param descripcionSociedad
	 *            The descripcionSociedad to set.
	 */
	public void setDescripcionSociedad(String descripcionSociedad) {
		this.descripcionSociedad = descripcionSociedad;
	}
	
	/**
	 * @return Returns the codigoCampanha.
	 */
	public String getCodigoCampanha() {
		return codigoCampanha;
	}

	/**
	 * @param codigoCampanha
	 *            The codigoCampanha to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoCampanha(String codigoCampanha) {
		this.codigoCampanha = codigoCampanha;
	}
	
	/**
	 * @param codigoAlmacen
	 *            The codigoAlmacen to set.
	 */
	public String getCodigoAlmacen() {
		return codigoAlmacen;
	}

	/**
	 * @param codigoAlmacen
	 *            The codigoAlmacen to set.
	 * @struts.validator type = "required"
	 */	
	public void setCodigoAlmacen(String codigoAlmacen) {
		this.codigoAlmacen = codigoAlmacen;
	}

	/**
	 * @param descripcionAlmacen
	 *            The descripcionAlmacen to set.
	 */
	public String getDescripcionAlmacen() {
		return descripcionAlmacen;
	}

	public void setDescripcionAlmacen(String descripcionAlmacen) {
		this.descripcionAlmacen = descripcionAlmacen;
	}
}
