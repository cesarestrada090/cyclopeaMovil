package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoPEDResultadoChequeoForm extends BaseEditForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String codigoResultadoChequeo;
	private String descripcionResultadoChequeo;
	private boolean activo;
	private String oid;
	
	
	/**
	 * @return the oid
	 */
	public String getOid() {
		return oid;
	}
	/**
	 * @param oid the oid to set
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the codigoResultadoChequeo
	 */
	public String getCodigoResultadoChequeo() {
		return codigoResultadoChequeo;
	}
	/**
	 * @param codigoResultadoChequeo the codigoResultadoChequeo to set
	 */
	public void setCodigoResultadoChequeo(String codigoResultadoChequeo) {
		this.codigoResultadoChequeo = codigoResultadoChequeo;
	}
	/**
	 * @return the descripcionResultadoChequeo
	 */
	public String getDescripcionResultadoChequeo() {
		return descripcionResultadoChequeo;
	}
	/**
	 * @param descripcionResultadoChequeo the descripcionResultadoChequeo to set
	 */
	public void setDescripcionResultadoChequeo(String descripcionResultadoChequeo) {
		this.descripcionResultadoChequeo = descripcionResultadoChequeo;
	}
	/**
	 * @return the activo
	 */
	public boolean isActivo() {
		return activo;
	}
	/**
	 * @param activo the activo to set
	 */
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	
	
}
