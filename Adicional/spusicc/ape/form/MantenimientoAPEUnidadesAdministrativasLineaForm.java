package biz.belcorp.ssicc.web.spusicc.ape.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoAPEUnidadesAdministrativasLineaForm extends BaseEditForm{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoCD;
	private String codigoLineaArmado;
	private String codigoNivelAgrpOlas;
	private String descripcionNivelAgrpOlas;
	private String codigoRegion;
	private String codigoZona;
	private String codigoSeccion;
	private boolean activo;
	
	
	
	/**
	 * @return the activo
	 */
	public boolean isActivo() {
		return activo;
	}
	/**
	 * @param activo the activo to set
	 */
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
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
	 * @return the codigoCD
	 */
	public String getCodigoCD() {
		return codigoCD;
	}
	/**
	 * @param codigoCD the codigoCD to set
	 */
	public void setCodigoCD(String codigoCD) {
		this.codigoCD = codigoCD;
	}
	/**
	 * @return the codigoLineaArmado
	 */
	public String getCodigoLineaArmado() {
		return codigoLineaArmado;
	}
	/**
	 * @param codigoLineaArmado the codigoLineaArmado to set
	 */
	public void setCodigoLineaArmado(String codigoLineaArmado) {
		this.codigoLineaArmado = codigoLineaArmado;
	}
	/**
	 * @return the codigoNivelAgrpOlas
	 */
	public String getCodigoNivelAgrpOlas() {
		return codigoNivelAgrpOlas;
	}
	/**
	 * @param codigoNivelAgrpOlas the codigoNivelAgrpOlas to set
	 */
	public void setCodigoNivelAgrpOlas(String codigoNivelAgrpOlas) {
		this.codigoNivelAgrpOlas = codigoNivelAgrpOlas;
	}
	/**
	 * @return the descripcionNivelAgrpOlas
	 */
	public String getDescripcionNivelAgrpOlas() {
		return descripcionNivelAgrpOlas;
	}
	/**
	 * @param descripcionNivelAgrpOlas the descripcionNivelAgrpOlas to set
	 */
	public void setDescripcionNivelAgrpOlas(String descripcionNivelAgrpOlas) {
		this.descripcionNivelAgrpOlas = descripcionNivelAgrpOlas;
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
	 * @return the codigoSeccion
	 */
	public String getCodigoSeccion() {
		return codigoSeccion;
	}
	/**
	 * @param codigoSeccion the codigoSeccion to set
	 */
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}
	
	
}
