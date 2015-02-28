package biz.belcorp.ssicc.web.spusicc.lideres.form;

import java.io.Serializable;
import java.util.List;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

@SuppressWarnings("rawtypes")
public class ProcesoLIDGenerarInformacionLideresCierrePeriodoForm extends BaseProcesoForm implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoMarca;	
	private List marcaList;
	private String periodoProceso;
	private String codigoIdiomaISO;
	
	public String getCodigoPais() {
		return codigoPais;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getCodigoMarca() {
		return codigoMarca;
	}
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	public List getMarcaList() {
		return marcaList;
	}
	public void setMarcaList(List marcaList) {
		this.marcaList = marcaList;
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
}
