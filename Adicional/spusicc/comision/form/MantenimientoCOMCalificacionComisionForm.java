package biz.belcorp.ssicc.web.spusicc.comision.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoCOMCalificacionComisionForm extends BaseEditForm
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3909875246601992185L;
	private String codigoPais;
	private String codigoMarca;
	private String codigoCanal;
	private String codigoTipoComisionista;
	private String descripcion;
	private String campaniaDesde;
	private String campaniaHasta;
	private String codigoCalificacion;
	private Integer comodin;
	private String codigoNivel;
	private boolean viewEdit;
	private boolean viewCodigoCalificacion;
	private boolean viewCodigoDetal;
	private Integer numeroPedidosDesde;
	private Integer numeroPedidosHasta;
	private Integer numeroIngresosDesde;
	private Integer numeroIngresosHasta;
	private boolean viewCodigoNivel;
	private String codigoRegion;
	private String codigoZona;
	private String descripcionRegion;
	private Integer comodinRecuperacion;
	private Integer indicadorPasePedido;
	/* INI JJ PER-SiCC-2012-0530 */
	private Integer comodinRecuperacionAspirantes;

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
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
	 *            the codigoCanal to set
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return the codigoTipoComisionista
	 */
	public String getCodigoTipoComisionista() {
		return codigoTipoComisionista;
	}

	/**
	 * @param codigoTipoComisionista
	 *            the codigoTipoComisionista to set
	 */
	public void setCodigoTipoComisionista(String codigoTipoComisionista) {
		this.codigoTipoComisionista = codigoTipoComisionista;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the campaniaDesde
	 */
	public String getCampaniaDesde() {
		return campaniaDesde;
	}

	/**
	 * @param campaniaDesde
	 *            the campaniaDesde to set
	 */
	public void setCampaniaDesde(String campaniaDesde) {
		this.campaniaDesde = campaniaDesde;
	}

	/**
	 * @return the campaniaHasta
	 */
	public String getCampaniaHasta() {
		return campaniaHasta;
	}

	/**
	 * @param campaniaHasta
	 *            the campaniaHasta to set
	 */
	public void setCampaniaHasta(String campaniaHasta) {
		this.campaniaHasta = campaniaHasta;
	}

	/**
	 * @return the codigoCalificacion
	 */
	public String getCodigoCalificacion() {
		return codigoCalificacion;
	}

	/**
	 * @param codigoCalificacion
	 *            the codigoCalificacion to set
	 */
	public void setCodigoCalificacion(String codigoCalificacion) {
		this.codigoCalificacion = codigoCalificacion;
	}

	/**
	 * @return the comodin
	 */
	public Integer getComodin() {
		return comodin;
	}

	/**
	 * @param comodin
	 *            the comodin to set
	 */
	public void setComodin(Integer comodin) {
		this.comodin = comodin;
	}

	/**
	 * @return the codigoNivel
	 */
	public String getCodigoNivel() {
		return codigoNivel;
	}

	/**
	 * @param codigoNivel
	 *            the codigoNivel to set
	 */
	public void setCodigoNivel(String codigoNivel) {
		this.codigoNivel = codigoNivel;
	}

	/**
	 * @return the viewEdit
	 */
	public boolean isViewEdit() {
		return viewEdit;
	}

	/**
	 * @param viewEdit
	 *            the viewEdit to set
	 */
	public void setViewEdit(boolean viewEdit) {
		this.viewEdit = viewEdit;
	}

	/**
	 * @return the viewCodigoCalificacion
	 */
	public boolean isViewCodigoCalificacion() {
		return viewCodigoCalificacion;
	}

	/**
	 * @param viewCodigoCalificacion
	 *            the viewCodigoCalificacion to set
	 */
	public void setViewCodigoCalificacion(boolean viewCodigoCalificacion) {
		this.viewCodigoCalificacion = viewCodigoCalificacion;
	}

	/**
	 * @return the viewCodigoDetal
	 */
	public boolean isViewCodigoDetal() {
		return viewCodigoDetal;
	}

	/**
	 * @param viewCodigoDetal
	 *            the viewCodigoDetal to set
	 */
	public void setViewCodigoDetal(boolean viewCodigoDetal) {
		this.viewCodigoDetal = viewCodigoDetal;
	}

	/**
	 * @return the numeroPedidosDesde
	 */
	public Integer getNumeroPedidosDesde() {
		return numeroPedidosDesde;
	}

	/**
	 * @param numeroPedidosDesde
	 *            the numeroPedidosDesde to set
	 */
	public void setNumeroPedidosDesde(Integer numeroPedidosDesde) {
		this.numeroPedidosDesde = numeroPedidosDesde;
	}

	/**
	 * @return the numeroPedidosHasta
	 */
	public Integer getNumeroPedidosHasta() {
		return numeroPedidosHasta;
	}

	/**
	 * @param numeroPedidosHasta
	 *            the numeroPedidosHasta to set
	 */
	public void setNumeroPedidosHasta(Integer numeroPedidosHasta) {
		this.numeroPedidosHasta = numeroPedidosHasta;
	}

	/**
	 * @return the numeroIngresosDesde
	 */
	public Integer getNumeroIngresosDesde() {
		return numeroIngresosDesde;
	}

	/**
	 * @param numeroIngresosDesde
	 *            the numeroIngresosDesde to set
	 */
	public void setNumeroIngresosDesde(Integer numeroIngresosDesde) {
		this.numeroIngresosDesde = numeroIngresosDesde;
	}

	/**
	 * @return the numeroIngresosHasta
	 */
	public Integer getNumeroIngresosHasta() {
		return numeroIngresosHasta;
	}

	/**
	 * @param numeroIngresosHasta
	 *            the numeroIngresosHasta to set
	 */
	public void setNumeroIngresosHasta(Integer numeroIngresosHasta) {
		this.numeroIngresosHasta = numeroIngresosHasta;
	}

	/**
	 * @return the viewCodigoNivel
	 */
	public boolean isViewCodigoNivel() {
		return viewCodigoNivel;
	}

	/**
	 * @param viewCodigoNivel
	 *            the viewCodigoNivel to set
	 */
	public void setViewCodigoNivel(boolean viewCodigoNivel) {
		this.viewCodigoNivel = viewCodigoNivel;
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

	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona
	 *            the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return the descripcionRegion
	 */
	public String getDescripcionRegion() {
		return descripcionRegion;
	}

	/**
	 * @param descripcionRegion
	 *            the descripcionRegion to set
	 */
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}

	/**
	 * @return the comodinRecuperacion
	 */
	public Integer getComodinRecuperacion() {
		return comodinRecuperacion;
	}

	/**
	 * @param comodinRecuperacion
	 *            the comodinRecuperacion to set
	 */
	public void setComodinRecuperacion(Integer comodinRecuperacion) {
		this.comodinRecuperacion = comodinRecuperacion;
	}

	/**
	 * @return the indicadorPasePedido
	 */
	public Integer getIndicadorPasePedido() {
		return indicadorPasePedido;
	}

	/**
	 * @param indicadorPasePedido
	 *            the indicadorPasePedido to set
	 */
	public void setIndicadorPasePedido(Integer indicadorPasePedido) {
		this.indicadorPasePedido = indicadorPasePedido;
	}

	/**
	 * @return the comodinRecuperacionAspirantes
	 */
	public Integer getComodinRecuperacionAspirantes() {
		return comodinRecuperacionAspirantes;
	}

	/**
	 * @param comodinRecuperacionAspirantes
	 *            the comodinRecuperacionAspirantes to set
	 */
	public void setComodinRecuperacionAspirantes(
			Integer comodinRecuperacionAspirantes) {
		this.comodinRecuperacionAspirantes = comodinRecuperacionAspirantes;
	}

}
