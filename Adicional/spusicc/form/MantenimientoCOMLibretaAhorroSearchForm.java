package biz.belcorp.ssicc.web.spusicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoCOMLibretaAhorroSearchForm extends BaseSearchForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected String codigoPais;
	
	protected String descripcionPais;
    
	protected String codigoLider; 
    
	protected String codigoPlanilla;
    
	protected String centroCosto;
    
	protected String documentoIdentidad;
    
	protected String numeroLibretaAhorro;
    
	protected String periodoIngreso;

	protected String codCcci;

	public String getCodCcci() {
		return codCcci;
	}

	public void setCodCcci(String codCcci) {
		this.codCcci = codCcci;
	}

	public String getCentroCosto() {
		return centroCosto;
	}

	public void setCentroCosto(String centroCosto) {
		this.centroCosto = centroCosto;
	}

	public String getCodigoLider() {
		return codigoLider;
	}

	public void setCodigoLider(String codigoLider) {
		this.codigoLider = codigoLider;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoPlanilla() {
		return codigoPlanilla;
	}

	public void setCodigoPlanilla(String codigoPlanilla) {
		this.codigoPlanilla = codigoPlanilla;
	}

	public String getDocumentoIdentidad() {
		return documentoIdentidad;
	}

	public void setDocumentoIdentidad(String documentoIdentidad) {
		this.documentoIdentidad = documentoIdentidad;
	}

	public String getNumeroLibretaAhorro() {
		return numeroLibretaAhorro;
	}

	public void setNumeroLibretaAhorro(String numeroLibretaAhorro) {
		this.numeroLibretaAhorro = numeroLibretaAhorro;
	}

	public String getPeriodoIngreso() {
		return periodoIngreso;
	}

	/**
	 * 
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="${campaign}"
	 */
	public void setPeriodoIngreso(String periodoIngreso) {
		this.periodoIngreso = periodoIngreso;
	}

	public String getDescripcionPais() {
		return descripcionPais;
	}

	public void setDescripcionPais(String descripcionPais) {
		this.descripcionPais = descripcionPais;
	}
}
