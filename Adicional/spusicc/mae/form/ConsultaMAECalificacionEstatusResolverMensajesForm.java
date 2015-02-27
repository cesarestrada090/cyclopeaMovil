package biz.belcorp.ssicc.web.spusicc.mae.form;

import java.io.Serializable;

import org.apache.xmlbeans.impl.jam.mutable.MPackage;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.web.MPantallaPrincipalBean;

public class ConsultaMAECalificacionEstatusResolverMensajesForm extends
BaseProcesoForm implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5704257600337596899L;

	private String codigoPais;
	
	private String tipoProceso;

	private String codigoPeriodo;
	
	private String codigoMarca;
	
	private String codigoCanal;

	/**
	 * @return the tipoProceso
	 */
	public String getTipoProceso() {
		return tipoProceso;
	}

	/**
	 * @param tipoProceso
	 *            The tipoProceso to set.
	 * @struts.validator type = "required"
	 */
	public void setTipoProceso(String tipoProceso) {
		this.tipoProceso = tipoProceso;
	}

	/*
    *  (non-Javadoc)
    * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
    */
	public void reset() {
		this.codigoPeriodo = Constants.CODIGO_PERIODO_DEFAULT;
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
        this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
        this.tipoProceso = Constants.CODIGO_TIPO_PROCESO;



	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}


	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	/**
	 * @return the codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}


	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}



	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}


	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}


		
}