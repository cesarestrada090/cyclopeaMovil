package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoOCRPreProductosICESearchForm extends BaseEditForm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3140390694542470929L;
	private String codigoPais;
	private String oidImpuesto;
	private String codigoSAP;
	private String valorImpuesto;
	private String fechaCarga;
	private Date fechaCargaD;
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
	 * @return the oidImpuesto
	 */
	public String getOidImpuesto() {
		return oidImpuesto;
	}
	/**
	 * @param oidImpuesto the oidImpuesto to set
	 */
	public void setOidImpuesto(String oidImpuesto) {
		this.oidImpuesto = oidImpuesto;
	}
	/**
	 * @return the codigoSAP
	 */
	public String getCodigoSAP() {
		return codigoSAP;
	}
	/**
	 * @param codigoSAP the codigoSAP to set
	 */
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}
	/**
	 * @return the valorImpuesto
	 */
	public String getValorImpuesto() {
		return valorImpuesto;
	}
	/**
	 * @param valorImpuesto the valorImpuesto to set
	 */
	public void setValorImpuesto(String valorImpuesto) {
		this.valorImpuesto = valorImpuesto;
	}
	/**
	 * @return the fechaCarga
	 */
	public String getFechaCarga() {
		return fechaCarga;
	}
	/**
	 * @param fechaCarga the fechaCarga to set
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
	 * @param fechaCargaD the fechaCargaD to set
	 */
	public void setFechaCargaD(Date fechaCargaD) {
		this.fechaCargaD = fechaCargaD;
	}
	
	
	
	

}
