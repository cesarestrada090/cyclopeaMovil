package biz.belcorp.ssicc.web.spusicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoCOMGenerarLideresNuevasForm extends BaseProcesoForm implements
		Serializable {

	private static final long serialVersionUID = 366453083115752194L;

	private String codigoPais;

	private String codigoMarca;

	private String codigoCanal;

	private String codigoPeriodo;
	
	private String codigoTipoDocumento;

	/**
	 * @return Returns the codigoTipoDocumento.
	 */
	public String getCodigoTipoDocumento() {
		return codigoTipoDocumento;
	}

	/**
	 * @param codigoTipoDocumento The codigoTipoDocumento to set.
	 */
	public void setCodigoTipoDocumento(String codigoTipoDocumento) {
		this.codigoTipoDocumento = codigoTipoDocumento;
	}

	/**
	 * @return Returns the codigoCanal.
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal The codigoCanal to set.
	 * 
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
	 * @param codigoMarca The codigoMarca to set.
	 * 
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
	 * @param codigoPais The codigoPais to set.
	 * 
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo The codigoPeriodo to set.
	 * 
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
//	public void reset(ActionMapping mapping, HttpServletRequest request) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
//		
//    	this.codigoMarca = Constants.CODIGO_MARCA_EBEL;
//    	this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
//    	this.codigoPeriodo = sdf.format(new Date(System.currentTimeMillis()));
//    	
//    }
}