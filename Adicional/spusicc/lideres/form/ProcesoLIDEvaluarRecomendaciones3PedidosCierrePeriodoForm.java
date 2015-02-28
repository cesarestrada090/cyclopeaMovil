package biz.belcorp.ssicc.web.spusicc.lideres.form;

import java.io.Serializable;
import java.util.List;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

@SuppressWarnings("rawtypes")
public class ProcesoLIDEvaluarRecomendaciones3PedidosCierrePeriodoForm extends BaseProcesoForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private List marcaList;
	private String codigoMarca;
	private String periodoProceso;
	private String codigoIdiomaISO;
	private String fechaProceso;
	
	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public List getMarcaList() {
		return marcaList;
	}
	
	public void setMarcaList(List marcaList) {
		this.marcaList = marcaList;
	}
	
	public String getCodigoMarca() {
		return codigoMarca;
	}
	
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	
	public String getPeriodoProceso() {
		return periodoProceso;
	}
	
	public void setPeriodoProceso(String periodoProceso) {
		this.periodoProceso = periodoProceso;
	}
	
	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}
	
	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
	}
	
	public String getFechaProceso() {
		return fechaProceso;
	}
	
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
}
