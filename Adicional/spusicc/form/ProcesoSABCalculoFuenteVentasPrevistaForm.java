package biz.belcorp.ssicc.web.spusicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoSABCalculoFuenteVentasPrevistaForm extends BaseProcesoForm
		implements Serializable {

	private static final long serialVersionUID = 8758096623617401519L;

	private String codigoPais;

	private String nombrePais;

	private String codigoSociedad;

	private String codigoAlmacen;

	private String codigoAnio;

	private String codigoMarca;

	private String codigoCanal;

	private String codigoRangoPeriodo;

	private String codigoPeriodoInicial;

	// public void reset(ActionMapping mapping, HttpServletRequest request) {
	// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	// this.codigoSociedad = Constants.CODIGO_SOCIEDAD_DEFAULT;
	// this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
	// this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
	// this.codigoAnio = sdf.format(new Date(System.currentTimeMillis()))
	// .substring(6, 10);
	// this.codigoAlmacen = Constants.CODIGO_ALMACEN_DEFAULT;
	// }

	// //Ini efernandezo
	// public void inicializar() {
	// this.codigoAlmacen = Constants.CODIGO_ALMACEN_DEFAULT;
	// log.debug("En el form hallando el codigoAlmacen:" + this.codigoAlmacen);
	// }
	// Fin efernandezo

	/**
	 * @return
	 */
	public String getCodigoAlmacen() {
		return codigoAlmacen;
	}

	/**
	 * @param codigoAlmacen
	 */
	public void setCodigoAlmacen(String codigoAlmacen) {
		this.codigoAlmacen = codigoAlmacen;
	}

	/**
	 * @return
	 */
	public String getCodigoAnio() {
		return codigoAnio;
	}

	/**
	 * @param codigoAnio
	 */
	public void setCodigoAnio(String codigoAnio) {
		this.codigoAnio = codigoAnio;
	}

	/**
	 * @return
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm#getCodigoPais()
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm#setCodigoPais(java.lang.String)
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return
	 */
	public String getCodigoPeriodoInicial() {
		return codigoPeriodoInicial;
	}

	/**
	 * @param codigoPeriodoInicial
	 */
	public void setCodigoPeriodoInicial(String codigoPeriodoInicial) {
		this.codigoPeriodoInicial = codigoPeriodoInicial;
	}

	/**
	 * @return
	 */
	public String getCodigoRangoPeriodo() {
		return codigoRangoPeriodo;
	}

	/**
	 * @param codigoRangoPeriodo
	 */
	public void setCodigoRangoPeriodo(String codigoRangoPeriodo) {
		this.codigoRangoPeriodo = codigoRangoPeriodo;
	}

	/**
	 * @return
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	/**
	 * @param codigoSociedad
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}

	/**
	 * @return
	 */
	public String getNombrePais() {
		return nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
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
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
}