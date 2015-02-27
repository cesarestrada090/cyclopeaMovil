package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoPEDReasignacionDocumentosLegalesForm extends BaseProcesoForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tipoDocumentoContable;
	
	private String codigoCanal;
	
	private String codigoAcceso;
	
	private String codigoSubacceso;
	
	private String ejercicio;
	
	private String serieDocLegal;
	
	private String rangoDesdeDocInterno;
	
	private String rangoHastaDocInterno;
	
	private String rangoDesdeDocLegal;
	
	private String rangoHastaDocLegal;
	
	private String rangoDesdeNrControl;
	
	private String rangoHastaNrControl;
	
	private String indicadorActDocCon;
		
	/**
	 * @return the tipoDocumentoContable
	 */
	public String getTipoDocumentoContable() {
		return tipoDocumentoContable;
	}

	/**
	 * @struts.validator type = "required"
	 * @param tipoDocumentoContable
	 */
	public void setTipoDocumentoContable(String tipoDocumentoContable) {
		this.tipoDocumentoContable = tipoDocumentoContable;
	}

	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @struts.validator type = "required"
	 * @param codigoCanal
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return the codigoAcceso
	 */
	public String getCodigoAcceso() {
		return codigoAcceso;
	}

	/**
	 * @struts.validator type = "required"
	 * @param codigoAcceso
	 */
	public void setCodigoAcceso(String codigoAcceso) {
		this.codigoAcceso = codigoAcceso;
	}

	/**
	 * @return the codigoSubacceso
	 */
	public String getCodigoSubacceso() {
		return codigoSubacceso;
	}

	/**
	 * @struts.validator type = "required"
	 * @param codigoSubacceso
	 */
	public void setCodigoSubacceso(String codigoSubacceso) {
		this.codigoSubacceso = codigoSubacceso;
	}

	/**
	 * @return the ejercicio
	 */
	public String getEjercicio() {
		return ejercicio;
	}

	/**
	 * @struts.validator type = "required"
	 * @param ejercicio
	 */
	public void setEjercicio(String ejercicio) {
		this.ejercicio = ejercicio;
	}

	/**
	 * @return the serieDocLegal
	 */
	public String getSerieDocLegal() {
		return serieDocLegal;
	}

	/**
	 * @struts.validator type = "required"
	 * @param serieDocLegal
	 */
	public void setSerieDocLegal(String serieDocLegal) {
		this.serieDocLegal = serieDocLegal;
	}

	/**
	 * @return the rangoDesdeDocInterno
	 */
	public String getRangoDesdeDocInterno() {
		return rangoDesdeDocInterno;
	}

	/**
	 * @struts.validator type = "required"
	 * @param rangoDesdeDocInterno
	 */
	public void setRangoDesdeDocInterno(String rangoDesdeDocInterno) {
		this.rangoDesdeDocInterno = rangoDesdeDocInterno;
	}

	/**
	 * @return the rangoHastaDocInterno
	 */
	public String getRangoHastaDocInterno() {
		return rangoHastaDocInterno;
	}

	/**
	 * @struts.validator type = "required"
	 * @param rangoHastaDocInterno
	 */
	public void setRangoHastaDocInterno(String rangoHastaDocInterno) {
		this.rangoHastaDocInterno = rangoHastaDocInterno;
	}

	/**
	 * @return the rangoDesdeDocLegal
	 */
	public String getRangoDesdeDocLegal() {
		return rangoDesdeDocLegal;
	}

	/**
	 * @struts.validator type = "required"
	 * @param rangoDesdeDocLegal
	 */
	public void setRangoDesdeDocLegal(String rangoDesdeDocLegal) {
		this.rangoDesdeDocLegal = rangoDesdeDocLegal;
	}

	/**
	 * @return the rangoHastaDocLegal
	 */
	public String getRangoHastaDocLegal() {
		return rangoHastaDocLegal;
	}

	/**
	 * @param rangoHastaDocLegal
	 */
	public void setRangoHastaDocLegal(String rangoHastaDocLegal) {
		this.rangoHastaDocLegal = rangoHastaDocLegal;
	}

	/**
	 * @return the rangoDesdeNrControl
	 */
	public String getRangoDesdeNrControl() {
		return rangoDesdeNrControl;
	}

	/**
	 * @struts.validator type = "required"
	 * @param rangoDesdeNrControl
	 */
	public void setRangoDesdeNrControl(String rangoDesdeNrControl) {
		this.rangoDesdeNrControl = rangoDesdeNrControl;
	}

	/**
	 * @return the rangoHastaNrControl
	 */
	public String getRangoHastaNrControl() {
		return rangoHastaNrControl;
	}

	/**
	 * @param rangoHastaNrControl
	 */
	public void setRangoHastaNrControl(String rangoHastaNrControl) {
		this.rangoHastaNrControl = rangoHastaNrControl;
	}

	/**
	 * @return the indicadorActDocCon
	 */
	public String getIndicadorActDocCon() {
		return indicadorActDocCon;
	}

	/**
	 * @param indicadorActDocCon the indicadorActDocCon to set
	 */
	public void setIndicadorActDocCon(String indicadorActDocCon) {
		this.indicadorActDocCon = indicadorActDocCon;
	}
}
