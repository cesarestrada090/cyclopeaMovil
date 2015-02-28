package biz.belcorp.ssicc.web.spusicc.men.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoMENMensajeCodigoVentaForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 */
public class MantenimientoMENMensajeCodigoVentaForm extends BaseEditForm {

	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String campanhaProceso;
	private String codigoVenta;
	private String oidMensaje;
	private String indicadorActivo;
	private String correlativo;
	
	public String getCodigoPais() {
		return codigoPais;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getCampanhaProceso() {
		return campanhaProceso;
	}
	public void setCampanhaProceso(String campanhaProceso) {
		this.campanhaProceso = campanhaProceso;
	}
	public String getCodigoVenta() {
		return codigoVenta;
	}
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}
	public String getOidMensaje() {
		return oidMensaje;
	}
	public void setOidMensaje(String oidMensaje) {
		this.oidMensaje = oidMensaje;
	}
	public String getIndicadorActivo() {
		return indicadorActivo;
	}
	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}
	/**
	 * @return the correlativo
	 */
	public String getCorrelativo() {
		return correlativo;
	}
	/**
	 * @param correlativo the correlativo to set
	 */
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}
	
	

}
