package biz.belcorp.ssicc.web.spusicc.cobranzas.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


public class ConsultaCOBCronogramaCarteraSearchForm extends BaseSearchForm implements Serializable{
	
	
	private static final long serialVersionUID = 4296833235714654883L;
	

	private String codigoPais;
	
	private String codigoSociedad;
		
	private String codigoEtapaDeuda;
	
	private String codigoPeriodo;
	
	private String codigoRegion;
	
	private String codigoZona;
	
	private String fechaGeneracion;
	private Date fechaGeneracionD;
	
	
	public ConsultaCOBCronogramaCarteraSearchForm(){	
		 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));		
		
		if (StringUtils.isEmpty(this.codigoPeriodo))
			this.codigoPeriodo = periodo;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}


	public String getcodigoEtapaDeuda() {
		return codigoEtapaDeuda;
	}

	public void setCodigoEtapaDeuda(String codigoEtapaDeuda) {
		this.codigoEtapaDeuda = codigoEtapaDeuda;
	}
	
	
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	

	public String getCodigoRegion() {
		return codigoRegion;
	}

	
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	
	public String getCodigoZona() {
		return codigoZona;
	}

	
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	
	public String getFechaGeneracion() {
		return fechaGeneracion;
	}

	
	public void setFechaGeneracion(String fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public Date getFechaGeneracionD() {
		return fechaGeneracionD;
	}

	public void setFechaGeneracionD(Date fechaGeneracionD) {
		this.fechaGeneracionD = fechaGeneracionD;
	}

	public String getCodigoEtapaDeuda() {
		return codigoEtapaDeuda;
	}
	

}
