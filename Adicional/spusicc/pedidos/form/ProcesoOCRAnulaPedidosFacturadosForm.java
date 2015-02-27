/*
 * Created on 11-jul-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class ProcesoOCRAnulaPedidosFacturadosForm extends BaseSearchForm
    implements Serializable {
 
	private static final long serialVersionUID = -7881106519662353482L;

	/**
     * Propiedad que contiene los ids seleccionados, en caso de un listado con
     * seleccion multiple
     */
	
	protected String[] selectedItems = {  };
	
    private String id;
    
    private String fechaFacturacion;
    private Date fechaFacturacionD;
    private String fechaAnulacion;
    private String opcion;
    
    private String codigoPeriodo;
    private String codigoCliente;
	
	private String codigoPais	;	
	private String nombreCliente ;
	
	private String fechaSolicitud;
	private Date fechaSolicitudD;
	

	private String 	indicadorAnulado;
	private String  indicadorFacturado;
	
	private String indicadorBusqueda;
	
	private String numLote;
	/**
	 * @return the numLote
	 */
	public String getNumLote() {
		return numLote;
	}

	/**
	 * @param numLote the numLote to set
	 */
	public void setNumLote(String numLote) {
		this.numLote = numLote;
	}

	/**
	 * @return the indicadorBusqueda
	 */
	public String getIndicadorBusqueda() {
		return indicadorBusqueda;
	}

	/**
	 * @param indicadorBusqueda the indicadorBusqueda to set
	 */
	public void setIndicadorBusqueda(String indicadorBusqueda) {
		this.indicadorBusqueda = indicadorBusqueda;
	}

	/**
	 * @return the indicadorAnulado
	 */
	public String getIndicadorAnulado() {
		return indicadorAnulado;
	}

	/**
	 * @param indicadorAnulado the indicadorAnulado to set
	 */
	public void setIndicadorAnulado(String indicadorAnulado) {
		this.indicadorAnulado = indicadorAnulado;
	}

	/**
	 * @return the indicadorFacturado
	 */
	public String getIndicadorFacturado() {
		return indicadorFacturado;
	}

	/**
	 * @param indicadorFacturado the indicadorFacturado to set
	 */
	public void setIndicadorFacturado(String indicadorFacturado) {
		this.indicadorFacturado = indicadorFacturado;
	}

	/**
	 * @return the fechaSolicitud
	 */
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	/**
	 * @param fechaSolicitud the fechaSolicitud to set
	 */
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	
	/**
	 * @param codigoPeriodo
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public String getFechaAnulacion() {
		return fechaAnulacion;
	}

	public void setFechaAnulacion(String fechaAnulacion) {
		this.fechaAnulacion = fechaAnulacion;
	}

	public String getFechaFacturacion() {
		return fechaFacturacion;
	}

	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public String[] getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}

	/**
	 * @return the fechaFacturacionD
	 */
	public Date getFechaFacturacionD() {
		return fechaFacturacionD;
	}

	/**
	 * @param fechaFacturacionD the fechaFacturacionD to set
	 */
	public void setFechaFacturacionD(Date fechaFacturacionD) {
		this.fechaFacturacionD = fechaFacturacionD;
	}

	/**
	 * @return the fechaSolicitudD
	 */
	public Date getFechaSolicitudD() {
		return fechaSolicitudD;
	}

	/**
	 * @param fechaSolicitudD the fechaSolicitudD to set
	 */
	public void setFechaSolicitudD(Date fechaSolicitudD) {
		this.fechaSolicitudD = fechaSolicitudD;
	}
}