package biz.belcorp.ssicc.web.spusicc.ruv.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteRUVDocumentosContablesConsultaForm extends BaseReporteForm implements Serializable{
	
	
	private static final long serialVersionUID = -5412717625763410573L;
	

	private String codigoPais;
	
	private String codigoTipoDocumentoContable;
	
	private String codigoCanal;
	
	private String codigoAcceso;
	
	private String codigoSubacceso;
	
	private String serie;
	
	private String numDocLegal;
	
	private String numDocInterno;
	private String fechaDesde;	
	private String fechaHasta;
	private Date fechaDesdeD;
	private Date fechaHastaD;
	
	public ReporteRUVDocumentosContablesConsultaForm(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.fechaDesde= sdf.format(new Date(System.currentTimeMillis()));
		this.fechaHasta= sdf.format(new Date(System.currentTimeMillis()));	
		this.fechaDesdeD=new Date(System.currentTimeMillis());
		this.fechaHastaD=new Date(System.currentTimeMillis());
		this.codigoTipoDocumentoContable="";
		this.codigoCanal="";
		this.codigoAcceso="";
		this.codigoSubacceso="";
		this.numDocLegal="";
		this.numDocInterno="";
		this.serie = "";	
	} 
	
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	
	public String getCodigoTipoDocumentoContable() {
		return codigoTipoDocumentoContable;
	}

	
	public void setCodigoTipoDocumentoContable(String codigoTipoDocumentoContable) {
		this.codigoTipoDocumentoContable = codigoTipoDocumentoContable;
	}

	
	public String getCodigoCanal() {
		return codigoCanal;
	}

	
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	
	public String getCodigoAcceso() {
		return codigoAcceso;
	}

	
	public void setCodigoAcceso(String codigoAcceso) {
		this.codigoAcceso = codigoAcceso;
	}

	
	public String getCodigoSubacceso() {
		return codigoSubacceso;
	}

	
	public void setCodigoSubacceso(String codigoSubacceso) {
		this.codigoSubacceso = codigoSubacceso;
	}

	
	public String getSerie() {
		return serie;
	}

	
	public void setSerie(String serie) {
		this.serie = serie;
	}

	
	public String getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	
	public String getFechaHasta() {
		return fechaHasta;
	}
	
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getNumDocLegal() {
		return numDocLegal;
	}
	
	public void setNumDocLegal(String numDocLegal) {
		this.numDocLegal = numDocLegal;
	}

	
	public String getNumDocInterno() {
		return numDocInterno;
	}

	public void setNumDocInterno(String numDocInterno) {
		this.numDocInterno = numDocInterno;
	}

	public Date getFechaDesdeD() {
		return fechaDesdeD;
	}

	public void setFechaDesdeD(Date fechaDesdeD) {
		this.fechaDesdeD = fechaDesdeD;
	}

	public Date getFechaHastaD() {
		return fechaHastaD;
	}

	public void setFechaHastaD(Date fechaHastaD) {
		this.fechaHastaD = fechaHastaD;
	}	
}
