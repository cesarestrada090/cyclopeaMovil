package biz.belcorp.ssicc.web.spusicc.form;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

@ManagedBean  
@SessionScoped
public class ProcesoMAEClasificacionClientesForm extends
BaseProcesoForm implements Serializable {
	
	private static final long serialVersionUID = 4945605920141128166L;

	private String codigoPais;
	
	private String codigoMarca;

	private String codigoCanal;

	private String codigoPeriodo;

	private String codigoTipoCliente;
	
	private String nroCampaniasEvaluar;

//	public void reset(ActionMapping mapping, HttpServletRequest request) {
//		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
//		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
//		this.codigoTipoCliente = Constants.CODIGO_TIPO_CLIENTE_DEFAULT;
//		this.nroCampaniasEvaluar = "4";
//	}	
//	
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal
	 * @struts.validator type="required"
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public String getCodigoTipoCliente() {
		return codigoTipoCliente;
	}

	/**
	 * @param codigoTipoCliente
	 */
	public void setCodigoTipoCliente(String codigoTipoCliente) {
		this.codigoTipoCliente = codigoTipoCliente;
	}

	public String getNroCampaniasEvaluar() {
		return nroCampaniasEvaluar;
	}

	/**
	 * @param nroCampaniasEvaluar
	 */
	public void setNroCampaniasEvaluar(String nroCampaniasEvaluar) {
		this.nroCampaniasEvaluar = nroCampaniasEvaluar;
	}
}