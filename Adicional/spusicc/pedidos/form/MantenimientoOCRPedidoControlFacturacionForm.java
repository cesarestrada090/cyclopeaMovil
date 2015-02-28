package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.math.BigDecimal;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoOCRPedidoControlFacturacionForm extends BaseEditForm {

	private static final long serialVersionUID = -4395694297091609948L;

	private String codigoPais;

	private String codigoPeriodo;

	private String fechaProceso;

	private Date fechaProcesoD;

	private BigDecimal montoMinimoFact;

	private BigDecimal montoMinimoAcept;

	private BigDecimal montoMinimoDeuda;

	private BigDecimal montoMaximo;

	private int unidadesMaximo;

	private String estadoCampanha;

	private String id;

	private String codigoMarca;

	private String codigoCanal;

	private String numLote;

	protected String[] selectedItems = {};
	protected String selectedItem = null;

	private String indVisualizar;

	public String getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}

	public String[] getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
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
	 * @param CodigoPeriodo
	 * 
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public String getEstadoCampanha() {
		return estadoCampanha;
	}

	public void setEstadoCampanha(String estadoCampanha) {
		this.estadoCampanha = estadoCampanha;
	}

	public String getFechaProceso() {
		return fechaProceso;
	}

	/**
	 * 
	 * @param fechaProceso
	 */
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public BigDecimal getMontoMaximo() {
		return montoMaximo;
	}

	/**
	 * 
	 * @param montoMaximo
	 */
	public void setMontoMaximo(BigDecimal montoMaximo) {
		this.montoMaximo = montoMaximo;
	}

	public BigDecimal getMontoMinimoAcept() {
		return montoMinimoAcept;
	}

	/**
	 * 
	 * @param montoMinimoAcept
	 */
	public void setMontoMinimoAcept(BigDecimal montoMinimoAcept) {
		this.montoMinimoAcept = montoMinimoAcept;
	}

	public BigDecimal getMontoMinimoFact() {
		return montoMinimoFact;
	}

	/**
	 * 
	 * @param montoMinimoFact
	 */
	public void setMontoMinimoFact(BigDecimal montoMinimoFact) {
		this.montoMinimoFact = montoMinimoFact;
	}

	public int getUnidadesMaximo() {
		return unidadesMaximo;
	}

	/**
	 * 
	 * @param unidadesMaximo
	 */
	public void setUnidadesMaximo(int unidadesMaximo) {
		this.unidadesMaximo = unidadesMaximo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// public void reset(ActionMapping mapping, HttpServletRequest request) {
	// this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
	// this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
	// this.montoMinimoFact = new BigDecimal(0);
	// this.montoMinimoAcept = new BigDecimal(0);
	// this.montoMinimoDeuda= new BigDecimal(0);
	// this.montoMaximo= new BigDecimal(0);
	// this.unidadesMaximo = 0;
	// }

	public String getCodigoCanal() {
		return codigoCanal;
	}

	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	public String getCodigoMarca() {
		return codigoMarca;
	}

	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	public BigDecimal getMontoMinimoDeuda() {
		return montoMinimoDeuda;
	}

	/**
	 * 
	 * @param montoMinimoDeuda
	 */
	public void setMontoMinimoDeuda(BigDecimal montoMinimoDeuda) {
		this.montoMinimoDeuda = montoMinimoDeuda;
	}

	/**
	 * @return Returns the numLote.
	 */
	public String getNumLote() {
		return numLote;
	}

	/**
	 * @param numLote
	 *            The numLote to set.
	 */
	public void setNumLote(String numLote) {
		this.numLote = numLote;
	}

	/**
	 * @return the indVisualizar
	 */
	public String getIndVisualizar() {
		return indVisualizar;
	}

	/**
	 * @param indVisualizar
	 *            the indVisualizar to set
	 */
	public void setIndVisualizar(String indVisualizar) {
		this.indVisualizar = indVisualizar;
	}

	/**
	 * @return the fechaProcesoD
	 */
	public Date getFechaProcesoD() {
		return fechaProcesoD;
	}

	/**
	 * @param fechaProcesoD the fechaProcesoD to set
	 */
	public void setFechaProcesoD(Date fechaProcesoD) {
		this.fechaProcesoD = fechaProcesoD;
	}
}