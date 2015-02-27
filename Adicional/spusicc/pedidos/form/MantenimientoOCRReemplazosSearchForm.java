package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoOCRReemplazosSearchForm extends BaseSearchForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 112027953967715392L;
	private String codigoPais;
	private String codigoPeriodo;
	private String codigoVentaPrincipal;
	private String codigoVentaReemplazo;
	private String indicadorActivo;
	private String oidTipoReemplazo;
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
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return the codigoVentaPrincipal
	 */
	public String getCodigoVentaPrincipal() {
		return codigoVentaPrincipal;
	}
	/**
	 * @param codigoVentaPrincipal the codigoVentaPrincipal to set
	 */
	public void setCodigoVentaPrincipal(String codigoVentaPrincipal) {
		this.codigoVentaPrincipal = codigoVentaPrincipal;
	}
	/**
	 * @return the codigoVentaReemplazo
	 */
	public String getCodigoVentaReemplazo() {
		return codigoVentaReemplazo;
	}
	/**
	 * @param codigoVentaReemplazo the codigoVentaReemplazo to set
	 */
	public void setCodigoVentaReemplazo(String codigoVentaReemplazo) {
		this.codigoVentaReemplazo = codigoVentaReemplazo;
	}
	/**
	 * @return the indicadorActivo
	 */
	public String getIndicadorActivo() {
		return indicadorActivo;
	}
	/**
	 * @param indicadorActivo the indicadorActivo to set
	 */
	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}
	/**
	 * @return the oidTipoReemplazo
	 */
	public String getOidTipoReemplazo() {
		return oidTipoReemplazo;
	}
	/**
	 * @param oidTipoReemplazo the oidTipoReemplazo to set
	 */
	public void setOidTipoReemplazo(String oidTipoReemplazo) {
		this.oidTipoReemplazo = oidTipoReemplazo;
	}
	
	

}
