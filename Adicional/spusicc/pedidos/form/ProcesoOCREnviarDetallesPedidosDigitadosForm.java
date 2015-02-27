package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

public class ProcesoOCREnviarDetallesPedidosDigitadosForm extends  BaseInterfazForm implements Serializable{

	private static final long serialVersionUID = -2414807923287636570L;
	

	private String codigoPeriodo;
	
	private String fechaFacturacion;
	
	protected String[] selectedItems = {};
	
	private String numLoteSTO;
	
	private Integer numeroPedidos;
	
	private String indValidacionSTO;
	
	private String showValiAutoPediDigi;
	
	private Date fechaFacturacionDate;
	
	
	public Integer getNumeroPedidos() {
		return numeroPedidos;
	}

	public void setNumeroPedidos(Integer numeroPedidos) {
		this.numeroPedidos = numeroPedidos;
	}

	public String[] getSelectedItems() {
		return selectedItems;
	}
	
	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}
	
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}
	
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}
	
	public String getNumLoteSTO() {
		return numLoteSTO;
	}
	
	public void setNumLoteSTO(String numLoteSTO) {
		this.numLoteSTO = numLoteSTO;
	}
	
	public String getIndValidacionSTO() {
		return indValidacionSTO;
	}

	public void setIndValidacionSTO(String indValidacionSTO) {
		this.indValidacionSTO = indValidacionSTO;
	}
	
	public String getShowValiAutoPediDigi() {
		return showValiAutoPediDigi;
	}
	
	public void setShowValiAutoPediDigi(String showValiAutoPediDigi) {
		this.showValiAutoPediDigi = showValiAutoPediDigi;
	}

	public Date getFechaFacturacionDate() {
		return fechaFacturacionDate;
	}

	public void setFechaFacturacionDate(Date fechaFacturacionDate) {
		this.fechaFacturacionDate = fechaFacturacionDate;
	}
	
}
