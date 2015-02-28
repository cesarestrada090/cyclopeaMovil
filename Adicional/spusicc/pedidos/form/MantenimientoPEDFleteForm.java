package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;



/**
 * <p>
 * <a href="MantenimientoPEDFleteForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 	
 * @author Giovanni Ascarza

 */

public class MantenimientoPEDFleteForm extends BaseEditForm {

	private static final long serialVersionUID = 1L;	

	private String codigoPais;
	private String oidTipoDespacho;
	private String oidTipoCliente;
	private String oidSubTipoCliente;
	private String oidTipoClasificacion;
	private String oidClasificacion;
	private String codigoRegion;
	private String codigoZona;
	private String montoFijo;
	private String tasa;
	private String fleteMinimo;
	private String fleteMaximo;
	private String recargo;
	private String oidFlete;
	
	
	private String desTipoDespacho;
	private String desTipoCliente;
	private String desSubTipoCliente;
	private String desTipoClasificacion;
	private String desClasificacion;
	private String desCodigoRegion;
	private String desCodigoZona;
	
	private String validMontoFijo;

	/**
	 * @return the recargo
	 */
	public String getRecargo() {
		return recargo;
	}

	/**
	 * @param recargo the recargo to set
	 */
	public void setRecargo(String recargo) {
		this.recargo = recargo;
	}

	/**
	 * @return the oidTipoDespacho
	 */
	public String getOidTipoDespacho() {
		return oidTipoDespacho;
	}

	/**
	 * @param oidTipoDespacho the oidTipoDespacho to set
	 * @struts.validator type = "required"
	 */
	public void setOidTipoDespacho(String oidTipoDespacho) {
		this.oidTipoDespacho = oidTipoDespacho;
	}

	/**
	 * @return the montoFijo
	 */
	public String getMontoFijo() {
		return montoFijo;
	}

	/**
	 * @param montoFijo the montoFijo to set
	 */
	public void setMontoFijo(String montoFijo) {
		this.montoFijo = montoFijo;
	}

	/**
	 * @return the tasa
	 */
	public String getTasa() {
		return tasa;
	}

	/**
	 * @param tasa the tasa to set
	 */
	public void setTasa(String tasa) {
		this.tasa = tasa;
	}

	/**
	 * @return the fleteMinimo
	 */
	public String getFleteMinimo() {
		return fleteMinimo;
	}

	/**
	 * @param fleteMinimo the fleteMinimo to set
	 */
	public void setFleteMinimo(String fleteMinimo) {
		this.fleteMinimo = fleteMinimo;
	}

	/**
	 * @return the fleteMaximo
	 */
	public String getFleteMaximo() {
		return fleteMaximo;
	}

	/**
	 * @param fleteMaximo the fleteMaximo to set
	 */
	public void setFleteMaximo(String fleteMaximo) {
		this.fleteMaximo = fleteMaximo;
	}


	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return oidTipoCliente
	 */
	public String getOidTipoCliente() {
		return oidTipoCliente;
	}

	/**
	 * @param oidTipoCliente
	 * @struts.validator type = "required"
	 */
	public void setOidTipoCliente(String oidTipoCliente) {
		this.oidTipoCliente = oidTipoCliente;
	}

	/**
	 * @return oidSubTipoCliente
	 */
	public String getOidSubTipoCliente() {
		return oidSubTipoCliente;
	}

	/**
	 * @param oidSubTipoCliente
	 */
	public void setOidSubTipoCliente(String oidSubTipoCliente) {
		this.oidSubTipoCliente = oidSubTipoCliente;
	}

	/**
	 * @return oidTipoClasificacion
	 */
	public String getOidTipoClasificacion() {
		return oidTipoClasificacion;
	}

	/**
	 * @param oidTipoClasificacion
	 */
	public void setOidTipoClasificacion(String oidTipoClasificacion) {
		this.oidTipoClasificacion = oidTipoClasificacion;
	}

	/**
	 * @return oidClasificacion
	 */
	public String getOidClasificacion() {
		return oidClasificacion;
	}

	/**
	 * @param oidClasificacion
	 */
	public void setOidClasificacion(String oidClasificacion) {
		this.oidClasificacion = oidClasificacion;
	}

	public String getOidFlete() {
		return oidFlete;
	}

	public void setOidFlete(String oidFlete) {
		this.oidFlete = oidFlete;
	}

	public String getDesTipoDespacho() {
		return desTipoDespacho;
	}

	public void setDesTipoDespacho(String desTipoDespacho) {
		this.desTipoDespacho = desTipoDespacho;
	}

	public String getDesTipoCliente() {
		return desTipoCliente;
	}

	public void setDesTipoCliente(String desTipoCliente) {
		this.desTipoCliente = desTipoCliente;
	}

	public String getDesSubTipoCliente() {
		return desSubTipoCliente;
	}

	public void setDesSubTipoCliente(String desSubTipoCliente) {
		this.desSubTipoCliente = desSubTipoCliente;
	}

	public String getDesTipoClasificacion() {
		return desTipoClasificacion;
	}

	public void setDesTipoClasificacion(String desTipoClasificacion) {
		this.desTipoClasificacion = desTipoClasificacion;
	}

	public String getDesClasificacion() {
		return desClasificacion;
	}

	public void setDesClasificacion(String desClasificacion) {
		this.desClasificacion = desClasificacion;
	}

	public String getDesCodigoRegion() {
		return desCodigoRegion;
	}

	public void setDesCodigoRegion(String desCodigoRegion) {
		this.desCodigoRegion = desCodigoRegion;
	}

	public String getDesCodigoZona() {
		return desCodigoZona;
	}

	public void setDesCodigoZona(String desCodigoZona) {
		this.desCodigoZona = desCodigoZona;
	}

	public String getValidMontoFijo() {
		return validMontoFijo;
	}

	public void setValidMontoFijo(String validMontoFijo) {
		this.validMontoFijo = validMontoFijo;
	}
	
	
}
