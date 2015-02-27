package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoOCRProductoPedidoMinimoForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 	
 * @author <a href="mailto:msilva@belcorp.biz">Marco Silva Moreno</a>
 * 
 * @struts.form name = "mantenimientoOCRProductoPedidoMinimoForm" extends = "baseEditForm"
 */

public class MantenimientoOCRProductoPedidoMinimoForm  extends BaseEditForm {
	
	private static final long serialVersionUID = 1L;

	private String id;
	
    private String codigoPais              ;
    private String codigoPeriodo           ;
    private String codigoVenta             ;
    private String valorUnitario           ;
    private String descripcionProducto     ;
    private String codigoProducto          ;
    private String nivelPriorizacion       ;
    private String estadoRegistro          ;
    private String codigoValida          ; // 0: sin validar 1: valida Periodo, 2: valida Codigo de Venta 
	
	protected String[] selectedItems = {};
	protected String selectedItem = null;
	

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
	 * @param codigoPeriodo
	 * 
	 */  

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getCodigoVenta() {
		return codigoVenta;
	}

	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public String getEstadoRegistro() {
		return estadoRegistro;
	}

	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNivelPriorizacion() {
		return nivelPriorizacion;
	}

	public void setNivelPriorizacion(String nivelPriorizacion) {
		this.nivelPriorizacion = nivelPriorizacion;
	}

	public String getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(String valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public String getCodigoValida() {
		return codigoValida;
	}

	public void setCodigoValida(String codigoValida) {
		this.codigoValida = codigoValida;
	}
}