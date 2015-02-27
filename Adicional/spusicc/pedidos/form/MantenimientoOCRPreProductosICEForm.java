package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


public class MantenimientoOCRPreProductosICEForm extends BaseSearchForm implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2251686961457504289L;
	private String codigoPais;
	private String codigoSAP;
	private String fechaCarga;
	private Date fechaCargaD;

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoSAP
	 */
	public String getCodigoSAP() {
		return codigoSAP;
	}

	/**
	 * @param codigoSAP
	 *            the codigoSAP to set
	 */
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}

	/**
	 * @return the fechaCarga
	 */
	public String getFechaCarga() {
		return fechaCarga;
	}

	/**
	 * @param fechaCarga
	 *            the fechaCarga to set
	 */
	public void setFechaCarga(String fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

	/**
	 * @return the fechaCargaD
	 */
	public Date getFechaCargaD() {
		return fechaCargaD;
	}

	/**
	 * @param fechaCargaD
	 *            the fechaCargaD to set
	 */
	public void setFechaCargaD(Date fechaCargaD) {
		this.fechaCargaD = fechaCargaD;
	}

}
