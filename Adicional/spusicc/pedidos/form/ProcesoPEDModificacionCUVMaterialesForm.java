package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoPEDModificacionCUVMaterialesForm extends BaseProcesoForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String oidProducto;
	
	private String codigoSap;
	
	private String descripcionSap;
	
	private String cuvAnterior;
	
	private String cuvNuevo;

	/**
	 * @return
	 */
	public String getCodigoSap() {
		return codigoSap;
	}

	/**
	 * @param codigoSap
	 */
	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}

	/**
	 * @return
	 */
	public String getDescripcionSap() {
		return descripcionSap;
	}

	/**
	 * @param descripcionSap
	 */
	public void setDescripcionSap(String descripcionSap) {
		this.descripcionSap = descripcionSap;
	}

	/**
	 * @return
	 */
	public String getOidProducto() {
		return oidProducto;
	}

	/**
	 * @param oidProducto
	 */
	public void setOidProducto(String oidProducto) {
		this.oidProducto = oidProducto;
	}

	/**
	 * @return
	 */
	public String getCuvAnterior() {
		return cuvAnterior;
	}

	/**
	 * @param cuvAnterior
	 */
	public void setCuvAnterior(String cuvAnterior) {
		this.cuvAnterior = cuvAnterior;
	}

	/**
	 * @return
	 */
	public String getCuvNuevo() {
		return cuvNuevo;
	}

	/**
	 * @param cuvNuevo
	 */
	public void setCuvNuevo(String cuvNuevo) {
		this.cuvNuevo = cuvNuevo;
	}
}
