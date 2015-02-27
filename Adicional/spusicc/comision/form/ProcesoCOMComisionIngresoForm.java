package biz.belcorp.ssicc.web.spusicc.comision.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoCOMComisionIngresoForm extends BaseProcesoForm implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6110798070101085704L;
	private String codigoMarca;
	private String descripcionMarca;
	private String codigoCanal;
	private String descripcionCanal;
	private String codigoComision;
	private String descripcionComision;
	private String codigoPeriodo;
	private String anioInicTramo;
	private String codTramo;
	private String codTipoCalculo;

	public ProcesoCOMComisionIngresoForm() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
		this.codigoPeriodo = null;
		this.anioInicTramo = sdf.format(new Date(System.currentTimeMillis()));
	}

	/**
	 * @return the codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca the codigoMarca to set
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return the descripcionMarca
	 */
	public String getDescripcionMarca() {
		return descripcionMarca;
	}

	/**
	 * @param descripcionMarca the descripcionMarca to set
	 */
	public void setDescripcionMarca(String descripcionMarca) {
		this.descripcionMarca = descripcionMarca;
	}

	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal the codigoCanal to set
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return the descripcionCanal
	 */
	public String getDescripcionCanal() {
		return descripcionCanal;
	}

	/**
	 * @param descripcionCanal the descripcionCanal to set
	 */
	public void setDescripcionCanal(String descripcionCanal) {
		this.descripcionCanal = descripcionCanal;
	}

	/**
	 * @return the codigoComision
	 */
	public String getCodigoComision() {
		return codigoComision;
	}

	/**
	 * @param codigoComision the codigoComision to set
	 */
	public void setCodigoComision(String codigoComision) {
		this.codigoComision = codigoComision;
	}

	/**
	 * @return the descripcionComision
	 */
	public String getDescripcionComision() {
		return descripcionComision;
	}

	/**
	 * @param descripcionComision the descripcionComision to set
	 */
	public void setDescripcionComision(String descripcionComision) {
		this.descripcionComision = descripcionComision;
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
	 * @return the anioInicTramo
	 */
	public String getAnioInicTramo() {
		return anioInicTramo;
	}

	/**
	 * @param anioInicTramo the anioInicTramo to set
	 */
	public void setAnioInicTramo(String anioInicTramo) {
		this.anioInicTramo = anioInicTramo;
	}

	/**
	 * @return the codTramo
	 */
	public String getCodTramo() {
		return codTramo;
	}

	/**
	 * @param codTramo the codTramo to set
	 */
	public void setCodTramo(String codTramo) {
		this.codTramo = codTramo;
	}

	/**
	 * @return the codTipoCalculo
	 */
	public String getCodTipoCalculo() {
		return codTipoCalculo;
	}

	/**
	 * @param codTipoCalculo the codTipoCalculo to set
	 */
	public void setCodTipoCalculo(String codTipoCalculo) {
		this.codTipoCalculo = codTipoCalculo;
	}
	
	

}
