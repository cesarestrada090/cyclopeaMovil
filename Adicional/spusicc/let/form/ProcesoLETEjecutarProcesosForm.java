package biz.belcorp.ssicc.web.spusicc.let.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazPaqueteForm;

public class ProcesoLETEjecutarProcesosForm extends BaseInterfazPaqueteForm
		implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoMarca;
	private String codigoCanal;
	private String codigoPeriodo;
	private String codigoRegion;
	private String tipoCierre;
	/* INI JJ PER-SiCC-2012-0297 */
	private String tipoTransaccion;
	private String tipoOperacion;

	/**
	 * @return
	 */
	public String getTipoTransaccion() {
		return tipoTransaccion;
	}

	/**
	 * @param tipoTransaccion
	 */
	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	/**
	 * @return
	 */
	public String getTipoOperacion() {
		return tipoOperacion;
	}

	/**
	 * @param tipoOperacion
	 */
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	/* FIN JJ PER-SiCC-2012-0297 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.web.framework.form.BaseInterfazForm#reset(org
	 * .apache.struts.action.ActionMapping,
	 * javax.servlet.http.HttpServletRequest)
	 */
	// public void reset(ActionMapping mapping, HttpServletRequest request) {
	// this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
	// this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
	// }

	/**
	 * @return
	 */
	public String getTipoCierre() {
		return tipoCierre;
	}

	/**
	 * @param tipoCierre
	 */
	public void setTipoCierre(String tipoCierre) {
		this.tipoCierre = tipoCierre;
	}

	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 *            The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca
	 *            the codigoMarca to set
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return the codigoCanal
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

	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion
	 *            the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
}