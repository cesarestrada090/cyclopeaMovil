package biz.belcorp.ssicc.web.spusicc.men.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

public class ProcesoMENGeneracionMensajesForm extends BaseInterfazForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4296049642170532631L;
	private String codigoPais;
	private String codigoPeriodo;
	private String fechaFacturacion;
	private Date fechaFacturacionD;
	private String tipoProceso;
	private String indicadorProcesoActivo;
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}
	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}
	/**
	 * @return the tipoProceso
	 */
	public String getTipoProceso() {
		return tipoProceso;
	}
	/**
	 * @param tipoProceso the tipoProceso to set
	 */
	public void setTipoProceso(String tipoProceso) {
		this.tipoProceso = tipoProceso;
	}
	/**
	 * @return the indicadorProcesoActivo
	 */
	public String getIndicadorProcesoActivo() {
		return indicadorProcesoActivo;
	}
	/**
	 * @param indicadorProcesoActivo the indicadorProcesoActivo to set
	 */
	public void setIndicadorProcesoActivo(String indicadorProcesoActivo) {
		this.indicadorProcesoActivo = indicadorProcesoActivo;
	}
	/**
	 * @return the fechaFacturacionD
	 */
	public Date getFechaFacturacionD() {
		return fechaFacturacionD;
	}
	/**
	 * @param fechaFacturacionD the fechaFacturacionD to set
	 */
	public void setFechaFacturacionD(Date fechaFacturacionD) {
		this.fechaFacturacionD = fechaFacturacionD;
	}
	
	
	
	
}
