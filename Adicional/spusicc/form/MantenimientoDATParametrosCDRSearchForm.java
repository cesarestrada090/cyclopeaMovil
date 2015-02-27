package biz.belcorp.ssicc.web.spusicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoDATParametrosCDRSearchForm extends BaseSearchForm implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private Integer idTipoSolicitud;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.
	 * ActionMapping, javax.servlet.http.HttpServletRequest)
	 */

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
	 * @return Returns the idTipoSolicitud.
	 */
	public Integer getIdTipoSolicitud() {
		return idTipoSolicitud;
	}

	/**
	 * @param idTipoSolicitud
	 *            The idTipoSolicitud to set.
	 */
	public void setIdTipoSolicitud(Integer idTipoSolicitud) {
		this.idTipoSolicitud = idTipoSolicitud;
	}
}
