package biz.belcorp.ssicc.web.spusicc.ventas.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * The Class ReporteVENDetalleAnulacionesDevolucionesForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 14/11/2014
 */
public class ReporteVENDetalleAnulacionesDevolucionesForm extends BaseReporteForm {
	
	private static final long serialVersionUID = 1L;	
	private String codigoPais;		
	private String codigoCanal;	
	private String codigoAcceso;	
	private String codigoSubacceso;
	private String fechaDesde;
	private String fechaHasta;	
	private String indicadorActDocCon;
	private Date fechaDesdeD;
	private Date fechaHastaD;	
	

	/**
	 * @return the codigoAcceso
	 */
	public String getCodigoAcceso() {
		return this.codigoAcceso;
	}
	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return this.codigoCanal;
	}	
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return this.codigoPais;
	}
	
	/**
	 * @return the codigoSubacceso
	 */
	public String getCodigoSubacceso() {
		return this.codigoSubacceso;
	}
	/**
	 * @return Returns the fechaDesde.
	 */
	public String getFechaDesde() {
		return this.fechaDesde;
	}
	/**
	 * @return Returns the fechaHasta.
	 */
	public String getFechaHasta() {
		return this.fechaHasta;
	}
	/**
	 * @return the indicadorActDocCon
	 */
	public String getIndicadorActDocCon() {
		return this.indicadorActDocCon;
	}

	/**
	 * @param codigoAcceso the codigoAcceso to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoAcceso(String codigoAcceso) {
		this.codigoAcceso = codigoAcceso;
	}

	/**
	 * @param codigoCanal the codigoCanal to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 * 
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @param codigoSubacceso the codigoSubacceso to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoSubacceso(String codigoSubacceso) {
		this.codigoSubacceso = codigoSubacceso;
	}
	
	/**
	 * @param fechaDesde - The fechaDesde to set.
	 * @struts.validator type="required"
	 * @struts.validator type="date"
	 * @struts.validator-var name="datePatternStrict"
	 *                       value="${defaultDatePattern}"
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	
	/**
	 * *
	 * 
	 * @param fechaHasta
	 *            New value of property fechaHasta.
	 * @struts.validator type="required"
	 * @struts.validator type="date"
	 * @struts.validator-var name="datePatternStrict"
	 *                       value="${defaultDatePattern}"
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	/**
	 * @param indicadorActDocCon the indicadorActDocCon to set
	 */
	public void setIndicadorActDocCon(String indicadorActDocCon) {
		this.indicadorActDocCon = indicadorActDocCon;
	}
	/**
	 * @return the fechaDesdeD
	 */
	public Date getFechaDesdeD() {
		return fechaDesdeD;
	}
	/**
	 * @param fechaDesdeD the fechaDesdeD to set
	 */
	public void setFechaDesdeD(Date fechaDesdeD) {
		this.fechaDesdeD = fechaDesdeD;
	}
	/**
	 * @return the fechaHastaD
	 */
	public Date getFechaHastaD() {
		return fechaHastaD;
	}
	/**
	 * @param fechaHastaD the fechaHastaD to set
	 */
	public void setFechaHastaD(Date fechaHastaD) {
		this.fechaHastaD = fechaHastaD;
	}
	
}
