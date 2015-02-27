package biz.belcorp.ssicc.web.spusicc.comision.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoCOMBonosForm extends BaseEditForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5549217198685192905L;
	private String codigoPais;
	private String codigoConcepto;
	private String descripcionConcepto;
	private String campanhaInicial;
	private String campanhaFinal;
//	para el detalle de Bono
	private String codigoRegion;
	private String codigoZona;
	private String codigoSeccion;
	private String monto;
	private String indicadorActivo;//1:Activo 0:Inactivo
	private boolean viewDetalleBono;
	private boolean viewEdit;
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
	 * @return the codigoConcepto
	 */
	public String getCodigoConcepto() {
		return codigoConcepto;
	}
	/**
	 * @param codigoConcepto the codigoConcepto to set
	 */
	public void setCodigoConcepto(String codigoConcepto) {
		this.codigoConcepto = codigoConcepto;
	}
	/**
	 * @return the descripcionConcepto
	 */
	public String getDescripcionConcepto() {
		return descripcionConcepto;
	}
	/**
	 * @param descripcionConcepto the descripcionConcepto to set
	 */
	public void setDescripcionConcepto(String descripcionConcepto) {
		this.descripcionConcepto = descripcionConcepto;
	}
	/**
	 * @return the campanhaInicial
	 */
	public String getCampanhaInicial() {
		return campanhaInicial;
	}
	/**
	 * @param campanhaInicial the campanhaInicial to set
	 */
	public void setCampanhaInicial(String campanhaInicial) {
		this.campanhaInicial = campanhaInicial;
	}
	/**
	 * @return the campanhaFinal
	 */
	public String getCampanhaFinal() {
		return campanhaFinal;
	}
	/**
	 * @param campanhaFinal the campanhaFinal to set
	 */
	public void setCampanhaFinal(String campanhaFinal) {
		this.campanhaFinal = campanhaFinal;
	}
	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	/**
	 * @return the codigoSeccion
	 */
	public String getCodigoSeccion() {
		return codigoSeccion;
	}
	/**
	 * @param codigoSeccion the codigoSeccion to set
	 */
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}
	/**
	 * @return the monto
	 */
	public String getMonto() {
		return monto;
	}
	/**
	 * @param monto the monto to set
	 */
	public void setMonto(String monto) {
		this.monto = monto;
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
	 * @return the viewDetalleBono
	 */
	public boolean isViewDetalleBono() {
		return viewDetalleBono;
	}
	/**
	 * @param viewDetalleBono the viewDetalleBono to set
	 */
	public void setViewDetalleBono(boolean viewDetalleBono) {
		this.viewDetalleBono = viewDetalleBono;
	}
	/**
	 * @return the viewEdit
	 */
	public boolean isViewEdit() {
		return viewEdit;
	}
	/**
	 * @param viewEdit the viewEdit to set
	 */
	public void setViewEdit(boolean viewEdit) {
		this.viewEdit = viewEdit;
	}
	
	

}
