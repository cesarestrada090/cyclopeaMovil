package biz.belcorp.ssicc.web.spusicc.cuentacorriente.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoCCCGenerarInformacionSAPFIForm extends BaseProcesoForm
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6166628155686298476L;
	private String codigoPais;
	private String codigoModulo;
	private String codigoInterface;
	private String fechaProcesoHasta;
	private Date fechaProcesoHastaD;

	public ProcesoCCCGenerarInformacionSAPFIForm() {

		this.fechaProcesoHastaD = new Date();

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
	 * @return the codigoModulo
	 */
	public String getCodigoModulo() {
		return codigoModulo;
	}

	/**
	 * @param codigoModulo the codigoModulo to set
	 */
	public void setCodigoModulo(String codigoModulo) {
		this.codigoModulo = codigoModulo;
	}

	/**
	 * @return the codigoInterface
	 */
	public String getCodigoInterface() {
		return codigoInterface;
	}

	/**
	 * @param codigoInterface the codigoInterface to set
	 */
	public void setCodigoInterface(String codigoInterface) {
		this.codigoInterface = codigoInterface;
	}

	/**
	 * @return the fechaProcesoHasta
	 */
	public String getFechaProcesoHasta() {
		return fechaProcesoHasta;
	}

	/**
	 * @param fechaProcesoHasta the fechaProcesoHasta to set
	 */
	public void setFechaProcesoHasta(String fechaProcesoHasta) {
		this.fechaProcesoHasta = fechaProcesoHasta;
	}

	/**
	 * @return the fechaProcesoHastaD
	 */
	public Date getFechaProcesoHastaD() {
		return fechaProcesoHastaD;
	}

	/**
	 * @param fechaProcesoHastaD the fechaProcesoHastaD to set
	 */
	public void setFechaProcesoHastaD(Date fechaProcesoHastaD) {
		this.fechaProcesoHastaD = fechaProcesoHastaD;
	}
	
	

}
