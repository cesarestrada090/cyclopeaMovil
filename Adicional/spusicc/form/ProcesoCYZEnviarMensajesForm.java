package biz.belcorp.ssicc.web.spusicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoCYZEnviarMensajesForm extends BaseProcesoForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4007245969369839435L;
	
    protected String codigoPrograma;
    
    protected String codigoProgramaPremio;
    
    protected String codigoProgramaCumpleanyos;
    
    protected String codigoProgramaWelcomePack;
    
    protected String codigoProgramaCumpleanyosConsultoras;
    
    protected String codigoPeriodo;
    
    protected String fechaFacturacion;
    
    protected Date fechaFacturacionD;
    
    

	public Date getFechaFacturacionD() {
		return fechaFacturacionD;
	}

	public void setFechaFacturacionD(Date fechaFacturacionD) {
		this.fechaFacturacionD = fechaFacturacionD;
	}

	public String getCodigoPrograma() {
		return codigoPrograma;
	}

	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}

	public String getCodigoProgramaPremio() {
		return codigoProgramaPremio;
	}

	public void setCodigoProgramaPremio(String codigoProgramaPremio) {
		this.codigoProgramaPremio = codigoProgramaPremio;
	}

	public String getCodigoProgramaCumpleanyos() {
		return codigoProgramaCumpleanyos;
	}

	public void setCodigoProgramaCumpleanyos(String codigoProgramaCumpleanyos) {
		this.codigoProgramaCumpleanyos = codigoProgramaCumpleanyos;
	}

	public String getCodigoProgramaWelcomePack() {
		return codigoProgramaWelcomePack;
	}

	public void setCodigoProgramaWelcomePack(String codigoProgramaWelcomePack) {
		this.codigoProgramaWelcomePack = codigoProgramaWelcomePack;
	}

	public String getCodigoProgramaCumpleanyosConsultoras() {
		return codigoProgramaCumpleanyosConsultoras;
	}

	public void setCodigoProgramaCumpleanyosConsultoras(
			String codigoProgramaCumpleanyosConsultoras) {
		this.codigoProgramaCumpleanyosConsultoras = codigoProgramaCumpleanyosConsultoras;
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

	
	
}
