/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.zon.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * <p>
 * <a href="MantenimientoZONUnidadGeograficaForm.java.html"><i>View Source</i></a>
 * </p>
 * 	
 * @author Ivan Tocto
 * 
 * 
 */
public class MantenimientoZONUnidadGeograficaForm extends BaseEditForm implements Serializable{
	
	private static final long serialVersionUID = 3231786337961622013L;
	private String codigoPais;	
	private String nivel1;
	private String nivel2;
	private String nivel3;
	private String nivel4;
	private String descripcion;
	private String indicadorGeoreferencia;
	
	private String oidEstructuraGeopolitica1;
	private String oidEstructuraGeopolitica2;
	private String oidEstructuraGeopolitica3;
	private String oidEstructuraGeopolitica4;
	
	private String oidValorEstructuraGeo;
	
	private String descripcionActual;
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * 
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the nivel1
	 */
	public String getNivel1() {
		return nivel1;
	}
	/**
	 * @param nivel1 the nivel1 to set
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
	 */
	public void setNivel3(String nivel3) {
		this.nivel3 = nivel3;
	}
	/**
	 * @return the nivel4
	 */
	public String getNivel4() {
		return nivel4;
	}
	/**
	 * @param nivel4 the nivel4 to set
	 */
	public void setNivel4(String nivel4) {
		this.nivel4 = nivel4;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the indicadorGeoreferencia
	 */
	public String getIndicadorGeoreferencia() {
		return indicadorGeoreferencia;
	}
	/**
	 */
	public void setIndicadorGeoreferencia(String indicadorGeoreferencia) {
		this.indicadorGeoreferencia = indicadorGeoreferencia;
	}
	/**
	 * @return the oidEstructuraGeopolitica1
	 */
	public String getOidEstructuraGeopolitica1() {
		return oidEstructuraGeopolitica1;
	}
	/**
	 * @param oidEstructuraGeopolitica1 the oidEstructuraGeopolitica1 to set
	 */
	public void setOidEstructuraGeopolitica1(String oidEstructuraGeopolitica1) {
		this.oidEstructuraGeopolitica1 = oidEstructuraGeopolitica1;
	}
	/**
	 * @return the oidEstructuraGeopolitica2
	 */
	public String getOidEstructuraGeopolitica2() {
		return oidEstructuraGeopolitica2;
	}
	/**
	 */
	public void setOidEstructuraGeopolitica2(String oidEstructuraGeopolitica2) {
		this.oidEstructuraGeopolitica2 = oidEstructuraGeopolitica2;
	}
	/**
	 * @return the oidEstructuraGeopolitica3
	 */
	public String getOidEstructuraGeopolitica3() {
		return oidEstructuraGeopolitica3;
	}
	/**
	 */
	public void setOidEstructuraGeopolitica3(String oidEstructuraGeopolitica3) {
		this.oidEstructuraGeopolitica3 = oidEstructuraGeopolitica3;
	}
	/**
	 * @return the oidEstructuraGeopolitica4
	 */
	public String getOidEstructuraGeopolitica4() {
		return oidEstructuraGeopolitica4;
	}
	/**
	 */
	public void setOidEstructuraGeopolitica4(String oidEstructuraGeopolitica4) {
		this.oidEstructuraGeopolitica4 = oidEstructuraGeopolitica4;
	}
	/**
	 * @return the oidValorEstructuraGeo
	 */
	public String getOidValorEstructuraGeo() {
		return oidValorEstructuraGeo;
	}
	/**
	 */
	public void setOidValorEstructuraGeo(String oidValorEstructuraGeo) {
		this.oidValorEstructuraGeo = oidValorEstructuraGeo;
	}
	/**
	 * @return the descripcionActual
	 */
	public String getDescripcionActual() {
		return descripcionActual;
	}

	public void setDescripcionActual(String descripcionActual) {
		this.descripcionActual = descripcionActual;
	}
	
	
}
