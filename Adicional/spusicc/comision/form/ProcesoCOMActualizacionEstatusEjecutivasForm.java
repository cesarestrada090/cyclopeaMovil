package biz.belcorp.ssicc.web.spusicc.comision.form;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * The Class ProcesoCOMActualizacionEstatusEjecutivasForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 29/12/2014
 */
public class ProcesoCOMActualizacionEstatusEjecutivasForm extends BaseProcesoForm {
	
	private static final long serialVersionUID = 1L;
	private String codigoMarca;	
	private String codigoCanal;	
	private String tipoComisionista;	
	private String codComision;	
	private String anioInicTramo;
	private String codTramo;	
	private String codigoClienteBuscar;
	private String codigoNivelActual;
	private String codigoNivel;
    private String longitudCodigoCliente;
    private String oidPais;
    private String nombreCliente;
        
    
	/**
	 * @return the nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}



	/**
	 * @param nombreCliente the nombreCliente to set
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}



	/**
	 * @return the oidPais
	 */
	public String getOidPais() {
		return oidPais;
	}



	/**
	 * @param oidPais the oidPais to set
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}



	/**
	 * @return the longitudCodigoCliente
	 */
	public String getLongitudCodigoCliente() {
		return longitudCodigoCliente;
	}



	/**
	 * @param longitudCodigoCliente the longitudCodigoCliente to set
	 */
	public void setLongitudCodigoCliente(String longitudCodigoCliente) {
		this.longitudCodigoCliente = longitudCodigoCliente;
	}



	/**
	 * @return the codigoNivelActual
	 */
	public String getCodigoNivelActual() {
		return codigoNivelActual;
	}



	/**
	 * @param codigoNivelActual the codigoNivelActual to set
	 */
	public void setCodigoNivelActual(String codigoNivelActual) {
		this.codigoNivelActual = codigoNivelActual;
	}

	/**
	 * @return Returns the codigoCanal.
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal The codigoCanal to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return Returns the codigoMarca.
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca The codigoMarca to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}


	/**
	 * @return Returns the tipoComisionista.
	 */
	public String getTipoComisionista() {
		return tipoComisionista;
	}

	/**
	 * @param tipoComisionista The tipoComisionista to set.
	 * @struts.validator type = "required"
	 */
	public void setTipoComisionista(String tipoComisionista) {
		this.tipoComisionista = tipoComisionista;
	}


	/**
	 * @return the codComision
	 */
	public String getCodComision() {
		return codComision;
	}


	/**
	 * @param codComision the codComision to set
	 * 
	 */
	public void setCodComision(String codComision) {
		this.codComision = codComision;
	}



	/**
	 * @return the anioInicTramo
	 */
	public String getAnioInicTramo() {
		return anioInicTramo;
	}



	/**
	 * @param anioInicTramo the anioInicTramo to set
	 * @struts.validator type = "required"
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
	 *  @struts.validator type = "required"
	 */
	public void setCodTramo(String codTramo) {
		this.codTramo = codTramo;
	}	
	
	/**
	 * @return the codigoNivel
	 */
	public String getCodigoNivel() {
		return codigoNivel;
	}



	/**
	 * @param codigoNivel the codigoNivel to set
	 *
	 */
	public void setCodigoNivel(String codigoNivel) {
		this.codigoNivel = codigoNivel;
	}


	/**
	 * @return the codigoClienteBuscar
	 */
	public String getCodigoClienteBuscar() {
		return codigoClienteBuscar;
	}



	/**
	 * @param codigoClienteBuscar the codigoClienteBuscar to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoClienteBuscar(String codigoClienteBuscar) {
		this.codigoClienteBuscar = codigoClienteBuscar;
	}

}
