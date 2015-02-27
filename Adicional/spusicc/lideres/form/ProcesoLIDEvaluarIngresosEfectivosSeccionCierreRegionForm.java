package biz.belcorp.ssicc.web.spusicc.lideres.form;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;




/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 * @struts.form name = "procesoLIDEvaluarIngresosEfectivosSeccionCierreRegionForm"
 * 
 */
public class ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionForm extends BaseProcesoForm {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoMarca;
	private String periodoProceso;
	private String codigoIdiomaISO;
	private String fechaProceso;
	private String codigoRegion;
	private String tipoEvaluacion;
	
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
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return the tipoEvaluacion
	 */
	public String getTipoEvaluacion() {
		return tipoEvaluacion;
	}


	public void setTipoEvaluacion(String tipoEvaluacion) {
		this.tipoEvaluacion = tipoEvaluacion;
	}
	
}
