package biz.belcorp.ssicc.web.spusicc.lideres.form;


import java.util.Date;
import java.util.List;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;



/**
 * @author <a href="mailto:llizana@belcorp.biz">Leonardo Lizana Chauca</a>
 * 
 * @struts.form name = "procesoLIDGenerarNumeroPedidosSeccionPeriodoForm"
 * 
 */
public class ProcesoLIDGenerarNumeroPedidosSeccionPeriodoForm extends BaseProcesoForm{
	
	private String codigoPais;
	private String codigoMarca;
	private List marcaList;
	private String periodoProceso;
	private String codigoIdiomaISO;
	private String fechaProceso;
	private Date fechaProcesoD;
	private List regionList;
	private String codigoRegion;
	
	
	public Date getFechaProcesoD() {
		return fechaProcesoD;
	}
	public void setFechaProcesoD(Date fechaProcesoD) {
		this.fechaProcesoD = fechaProcesoD;
	}
	/**
	 * @return Returns the codigoIdiomaISO.
	 */
	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}
	/**
	 * @param codigoIdiomaISO The codigoIdiomaISO to set.
	 */
	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
	}
	
	/**
	 * @return Returns the marcaList.
	 */
	public List getMarcaList() {
		return marcaList;
	}
	/**
	 * @param marcaList The marcaList to set.
	 */
	public void setMarcaList(List marcaList) {
		this.marcaList = marcaList;
	}
	/**
	 * 
	 * @return Returns the codigoMarca.
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	/**
	 * @return Returns the periodoProceso.
	 */
	public String getPeriodoProceso() {
		return periodoProceso;
	}

	public void setPeriodoProceso(String periodoProceso) {
		this.periodoProceso = periodoProceso;
	}
	/**
	 * @return Returns the variableVentaList.
	 */
	
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	
	/**
	 * @return Returns the fechaProceso.
	 */
	public String getFechaProceso() {
		return fechaProceso;
	}
	/**
	 * @param fechaProceso The fechaProceso to set.
	 */
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
	/**
	 * @return Returns the regionList.
	 */
	public List getRegionList() {
		return regionList;
	}
	/**
	 * @param regionList The regionList to set.
	 */
	public void setRegionList(List regionList) {
		this.regionList = regionList;
	}
	/**
	 * @return Returns the codigoRegion.
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

		
}
