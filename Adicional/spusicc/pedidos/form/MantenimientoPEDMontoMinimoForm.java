package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoPEDMontoMinimoForm extends BaseEditForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String oidMontoMinimo;
	private String oidTipoSolicitud;
	private String oidTipoCliente;
	private String oidSubTipoCliente;
	private String oidTipoClasificacion;	
	private String oidClasificacion;
	private String codigoRegion;
	private String codigoZona;
	private String nivel1;
	private String nivel2;
	private String nivel3;
	private String recargo;
	private String nominal;
	private String codigoPaisOld;
	private String oidTipoSolicitudOld;
	private String oidTipoClienteOld;
	private String oidSubTipoClienteOld;
	private String oidTipoClasificacionOld;
	private String oidClasificacionOld;
	private String codigoRegionOld;
	private String codigoZonaOld;
	private String nivel1Old;
	private String nivel2Old;
	private String nivel3Old;
	private String recargoOld;
	private String nominalOld;

	/**
	 * @return the oidMontoMinimo
	 */
	public String getOidMontoMinimo() {
		return oidMontoMinimo;
	}

	/**
	 * @param oidMontoMinimo the oidMontoMinimo to set
	 */
	public void setOidMontoMinimo(String oidMontoMinimo) {
		this.oidMontoMinimo = oidMontoMinimo;
	}

	/**
	 * @return the oidTipoSolicitud
	 */
	public String getOidTipoSolicitud() {
		return oidTipoSolicitud;
	}

	/**
	 * @param oidTipoSolicitud the oidTipoSolicitud to set
	 * @struts.validator type = "required"
	 */
	public void setOidTipoSolicitud(String oidTipoSolicitud) {
		this.oidTipoSolicitud = oidTipoSolicitud;
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
	 * @return the nivel1
	 */
	public String getNivel1() {
		return nivel1;
	}

	/**
	 * @param nivel1 the nivel1 to set
	 * @struts.validator type = "required"
	 */
	public void setNivel1(String nivel1) {
		this.nivel1 = nivel1;
	}

	/**
	 * @return the nivel2
	 */
	public String getNivel2() {
		return nivel2;
	}

	/**
	 * @param nivel2 the nivel2 to set
	 *@struts.validator type = "required"
	 */
	public void setNivel2(String nivel2) {
		this.nivel2 = nivel2;
	}

	/**
	 * @return the nivel3
	 */
	public String getNivel3() {
		return nivel3;
	}

	/**
	 * @param nivel3 the nivel3 to set
	 * @struts.validator type = "required"
	 */
	public void setNivel3(String nivel3) {
		this.nivel3 = nivel3;
	}

	/**
	 * @return the recargo
	 */
	public String getRecargo() {
		return recargo;
	}

	/**
	 * @param recargo the recargo to set
	 * 
	 */
	public void setRecargo(String recargo) {
		this.recargo = recargo;
	}

	/**
	 * @return the nominal
	 */
	public String getNominal() {
		return nominal;
	}

	/**
	 * @param nominal the nominal to set
	 *
	 */
	public void setNominal(String nominal) {
		this.nominal = nominal;
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

	/**
	 * @return the codigoPaisOld
	 */
	public String getCodigoPaisOld() {
		return codigoPaisOld;
	}

	/**
	 * @param codigoPaisOld the codigoPaisOld to set
	 */
	public void setCodigoPaisOld(String codigoPaisOld) {
		this.codigoPaisOld = codigoPaisOld;
	}

	/**
	 * @return the oidTipoSolicitudOld
	 */
	public String getOidTipoSolicitudOld() {
		return oidTipoSolicitudOld;
	}

	/**
	 * @param oidTipoSolicitudOld the oidTipoSolicitudOld to set
	 */
	public void setOidTipoSolicitudOld(String oidTipoSolicitudOld) {
		this.oidTipoSolicitudOld = oidTipoSolicitudOld;
	}

	/**
	 * @return the oidTipoClienteOld
	 */
	public String getOidTipoClienteOld() {
		return oidTipoClienteOld;
	}

	/**
	 * @param oidTipoClienteOld the oidTipoClienteOld to set
	 */
	public void setOidTipoClienteOld(String oidTipoClienteOld) {
		this.oidTipoClienteOld = oidTipoClienteOld;
	}

	/**
	 * @return the oidSubTipoClienteOld
	 */
	public String getOidSubTipoClienteOld() {
		return oidSubTipoClienteOld;
	}

	/**
	 * @param oidSubTipoClienteOld the oidSubTipoClienteOld to set
	 */
	public void setOidSubTipoClienteOld(String oidSubTipoClienteOld) {
		this.oidSubTipoClienteOld = oidSubTipoClienteOld;
	}

	/**
	 * @return the oidTipoClasificacionOld
	 */
	public String getOidTipoClasificacionOld() {
		return oidTipoClasificacionOld;
	}

	/**
	 * @param oidTipoClasificacionOld the oidTipoClasificacionOld to set
	 */
	public void setOidTipoClasificacionOld(String oidTipoClasificacionOld) {
		this.oidTipoClasificacionOld = oidTipoClasificacionOld;
	}

	/**
	 * @return the oidClasificacionOld
	 */
	public String getOidClasificacionOld() {
		return oidClasificacionOld;
	}

	/**
	 * @param oidClasificacionOld the oidClasificacionOld to set
	 */
	public void setOidClasificacionOld(String oidClasificacionOld) {
		this.oidClasificacionOld = oidClasificacionOld;
	}

	/**
	 * @return the codigoRegionOld
	 */
	public String getCodigoRegionOld() {
		return codigoRegionOld;
	}

	/**
	 * @param codigoRegionOld the codigoRegionOld to set
	 */
	public void setCodigoRegionOld(String codigoRegionOld) {
		this.codigoRegionOld = codigoRegionOld;
	}

	/**
	 * @return the codigoZonaOld
	 */
	public String getCodigoZonaOld() {
		return codigoZonaOld;
	}

	/**
	 * @param codigoZonaOld the codigoZonaOld to set
	 */
	public void setCodigoZonaOld(String codigoZonaOld) {
		this.codigoZonaOld = codigoZonaOld;
	}

	/**
	 * @return the nivel1Old
	 */
	public String getNivel1Old() {
		return nivel1Old;
	}

	/**
	 * @param nivel1Old the nivel1Old to set
	 */
	public void setNivel1Old(String nivel1Old) {
		this.nivel1Old = nivel1Old;
	}

	/**
	 * @return the nivel2Old
	 */
	public String getNivel2Old() {
		return nivel2Old;
	}

	/**
	 * @param nivel2Old the nivel2Old to set
	 */
	public void setNivel2Old(String nivel2Old) {
		this.nivel2Old = nivel2Old;
	}

	/**
	 * @return the nivel3Old
	 */
	public String getNivel3Old() {
		return nivel3Old;
	}

	/**
	 * @param nivel3Old the nivel3Old to set
	 */
	public void setNivel3Old(String nivel3Old) {
		this.nivel3Old = nivel3Old;
	}

	/**
	 * @return the recargoOld
	 */
	public String getRecargoOld() {
		return recargoOld;
	}

	/**
	 * @param recargoOld the recargoOld to set
	 */
	public void setRecargoOld(String recargoOld) {
		this.recargoOld = recargoOld;
	}

	/**
	 * @return the nominalOld
	 */
	public String getNominalOld() {
		return nominalOld;
	}

	/**
	 * @param nominalOld the nominalOld to set
	 */
	public void setNominalOld(String nominalOld) {
		this.nominalOld = nominalOld;
	}
}
