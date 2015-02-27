package biz.belcorp.ssicc.web.spusicc.dto.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoDTORangoDescuentoForm extends BaseEditForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7245130907788033843L;
	
	private String codigoPais;
	private String codigoGrupo;
	private String codigoRango;
	private String importeHasta;
	private String porcentajeDescuento;
	private String estadoRegistro;
	private boolean activo;
	
	
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
	 * @return the codigoGrupo
	 */
	public String getCodigoGrupo() {
		return codigoGrupo;
	}
	/**
	 * @param codigoGrupo the codigoGrupo to set
	 */
	public void setCodigoGrupo(String codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}
	/**
	 * @return the codigoRango
	 */
	public String getCodigoRango() {
		return codigoRango;
	}
	/**
	 * @param codigoRango the codigoRango to set
	 */
	public void setCodigoRango(String codigoRango) {
		this.codigoRango = codigoRango;
	}
	/**
	 * @return the importeHasta
	 */
	public String getImporteHasta() {
		return importeHasta;
	}
	/**
	 * @param importeHasta the importeHasta to set
	 */
	public void setImporteHasta(String importeHasta) {
		this.importeHasta = importeHasta;
	}
	/**
	 * @return the porcentajeDescuento
	 */
	public String getPorcentajeDescuento() {
		return porcentajeDescuento;
	}
	/**
	 * @param porcentajeDescuento the porcentajeDescuento to set
	 */
	public void setPorcentajeDescuento(String porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}
	/**
	 * @return the estadoRegistro
	 */
	public String getEstadoRegistro() {
		return estadoRegistro;
	}
	/**
	 * @param estadoRegistro the estadoRegistro to set
	 */
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
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
