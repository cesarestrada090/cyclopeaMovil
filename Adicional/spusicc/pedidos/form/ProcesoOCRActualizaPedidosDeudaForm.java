package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.util.Arrays;
import java.util.Date;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class ProcesoOCRActualizaPedidosDeudaForm  extends BaseSearchForm {
	
	private static final long serialVersionUID = 1L;

	/**
     * Propiedad que contiene los ids seleccionados, en caso de un listado con
     * seleccion multiple
     */
	
	protected String[] selectedItems = {  };
	
    private String id;
    
    private String tipoIngreso;
    private String fechaInicio;
    private Date fechaInicioD;
    private String fechaFin;
    private Date fechaFinD;
    private String operacion; // indicador Deuda o Rechazado o ...
    private String codigoRegion;
    private String codigoZona;    
    private String[] regiones;
    private String[] zonas;
    private String opcion;
    
    
    
    public Date getFechaInicioD() {
		return fechaInicioD;
	}
	public void setFechaInicioD(Date fechaInicioD) {
		this.fechaInicioD = fechaInicioD;
	}
	public Date getFechaFinD() {
		return fechaFinD;
	}
	public void setFechaFinD(Date fechaFinD) {
		this.fechaFinD = fechaFinD;
	}
	public UploadedFile getClienteFile() {
		return clienteFile;
	}
	public void setClienteFile(UploadedFile clienteFile) {
		this.clienteFile = clienteFile;
	}

	private String subAcceso;
    private String codigoPeriodo;
    private String codigoCliente;
    private String[] subAccesos;    
    
	private String codigoMarca;	
	private String codigoCanal;
	
	private String estadoDeuda;	
	private String fechaSolicitud;
	
	private String codigoPais	;	
	private String estadoProceso	;
	private String nombreCliente ;
	private String descripcionRegion ;
	private String descripcionZona 	;
	private String codigoTerritorio ;
	private String valorSaldoDeudor	;
	
    /**
     * Holds value of property indicadorAdmCartera.
     */
    protected boolean indicadorAdmCartera;
	private String observaciones 							;	
	
	private String indErrorAdminCartera						;	
	
	private UploadedFile clienteFile;
	
    /**
     * @return
     */
  
	
	/**
	 * @return
	 */
	public String getObservaciones() {
		return observaciones;
	}
    /**
     * 
     * @param observaciones
     *
     */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return
	 */
	public String getEstadoDeuda() {
		return estadoDeuda;
	}

	/**
	 * @param estadoDeuda
	 */
	public void setEstadoDeuda(String estadoDeuda) {
		this.estadoDeuda = estadoDeuda;
	}

	/**
	 * @return
	 */
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	/**
	 * @param fechaSolicitud
	 */
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
     * @return Returns the fechaFin.
     */
    public String getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin
     */
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return Returns the fechaInicio.
     */
    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return Returns the operacion.
     */
    public String getOperacion() {
        return operacion;
    }

    /**
     * @param operacion
     *            The operacion to set.
     */
    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    /**
     * @return Returns the tipoIngreso.
     */
    public String getTipoIngreso() {
        return tipoIngreso;
    }

    /**
     * @param tipoIngreso
     *            The tipoIngreso to set.
     */
    public void setTipoIngreso(String tipoIngreso) {
        this.tipoIngreso = tipoIngreso;
    }

    /*
     *  (non-Javadoc)
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    /*public void reset(ActionMapping mapping, HttpServletRequest request) {
        // TODO Auto-generated method stub       
		this.subAccesos = new String[]{ Constants.REC_SUBACCESO_DEFAULT };     
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;		
		this.clienteFile = null;
    }*/

    /**
     * @return
     */
    public String[] getRegiones() {
        return regiones;
    }

    /**
     * @param regiones
     */
    public void setRegiones(String[] regiones) {
        this.regiones = regiones;
    }



    public String getOpcion() {
        return opcion;
    }

    /**
     * @param opcion
     */
    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

	/**
	 * @return
	 */
	public String[] getSelectedItems() {
		return selectedItems;
	}

	/**
	 * @param selectedItems
	 */
	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}

	/**
	 * @return
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return
	 */
	public String getSubAcceso() {
		return subAcceso;
	}

	/**
	 * @param subAcceso
	 */
	public void setSubAcceso(String subAcceso) {
		this.subAcceso = subAcceso;
	}

	/**
	 * @return
	 */
	public String[] getSubAccesos() {
		return subAccesos;
	}

	/**
	 * @param subAccesos
	 */
	public void setSubAccesos(String[] subAccesos) {
		this.subAccesos = subAccesos;
	}

	/**
	 * @return
	 */
	public String[] getZonas() {
		return zonas;
	}

	/**
	 * @param zonas
	 */
	public void setZonas(String[] zonas) {
		this.zonas = zonas;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	

	/**
	 * @return
	 */
	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "ProcesoOCRActualizaPedidosDeudaForm [selectedItems="
				+ Arrays.toString(selectedItems) + ", id=" + id
				+ ", tipoIngreso=" + tipoIngreso + ", fechaInicio="
				+ fechaInicio + ", fechaFin=" + fechaFin + ", operacion="
				+ operacion + ", codigoRegion=" + codigoRegion
				+ ", codigoZona=" + codigoZona + ", regiones="
				+ Arrays.toString(regiones) + ", zonas="
				+ Arrays.toString(zonas) + ", opcion=" + opcion
				+ ", subAcceso=" + subAcceso + ", codigoPeriodo="
				+ codigoPeriodo + ", codigoCliente=" + codigoCliente
				+ ", subAccesos=" + Arrays.toString(subAccesos)
				+ ", codigoMarca=" + codigoMarca + ", codigoCanal="
				+ codigoCanal + ", estadoDeuda=" + estadoDeuda
				+ ", fechaSolicitud=" + fechaSolicitud + ", codigoPais="
				+ codigoPais + ", estadoProceso=" + estadoProceso
				+ ", nombreCliente=" + nombreCliente + ", descripcionRegion="
				+ descripcionRegion + ", descripcionZona=" + descripcionZona
				+ ", codigoTerritorio=" + codigoTerritorio
				+ ", valorSaldoDeudor=" + valorSaldoDeudor
				+ ", indicadorAdmCartera=" + indicadorAdmCartera
				+ ", observaciones=" + observaciones
				+ ", indErrorAdminCartera=" + indErrorAdminCartera
				+ ", clienteFile=" + clienteFile + "]";
	}
	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	
	/**
	 * @return
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return
	 */
	public String getCodigoTerritorio() {
		return codigoTerritorio;
	}

	/**
	 * @param codigoTerritorio
	 */
	public void setCodigoTerritorio(String codigoTerritorio) {
		this.codigoTerritorio = codigoTerritorio;
	}

	/**
	 * @return
	 */
	public String getDescripcionRegion() {
		return descripcionRegion;
	}

	/**
	 * @param descripcionRegion
	 */
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}

	/**
	 * @return
	 */
	public String getDescripcionZona() {
		return descripcionZona;
	}

	/**
	 * @param descripcionZona
	 */
	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}

	/**
	 * @return
	 */
	public String getEstadoProceso() {
		return estadoProceso;
	}

	/**
	 * @param estadoProceso
	 */
	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}

	/**
	 * @return
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * @param nombreCliente
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	/**
	 * @return
	 */
	public String getValorSaldoDeudor() {
		return valorSaldoDeudor;
	}

	/**
	 * @param valorSaldoDeudor
	 */
	public void setValorSaldoDeudor(String valorSaldoDeudor) {
		this.valorSaldoDeudor = valorSaldoDeudor;
	}

	/**
	 * @return
	 */
	public boolean isIndicadorAdmCartera() {
		return indicadorAdmCartera;
	}

	/**
	 * @param indicadorAdmCartera
	 */
	public void setIndicadorAdmCartera(boolean indicadorAdmCartera) {
		this.indicadorAdmCartera = indicadorAdmCartera;
	}

	/**
	 * @return
	 */
	public String getIndErrorAdminCartera() {
		return indErrorAdminCartera;
	}

	/**
	 * @param indErrorAdminCartera
	 */
	public void setIndErrorAdminCartera(String indErrorAdminCartera) {
		this.indErrorAdminCartera = indErrorAdminCartera;
	}

	/**
	 * @return
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
}