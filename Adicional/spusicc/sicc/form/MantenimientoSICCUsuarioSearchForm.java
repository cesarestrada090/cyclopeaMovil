package biz.belcorp.ssicc.web.spusicc.sicc.form;

import javax.validation.constraints.Size;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * 
 * <p>
 * <a href="MantenimientoSICCUsuarioForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 */

public class MantenimientoSICCUsuarioSearchForm extends BaseSearchForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6697089275875275283L;

	private String codigoPais;

    private String oid;

    private String login;
    
    private String apellidoPaterno;
    
    private String apellidoMaterno;
    
    private String primerNombre;
    
    private String segundoNombre;
        
    private String email;
    
    private String telefono;

   
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
	 * @return the oid
	 */
	public String getOid() {
		return oid;
	}

	/**
	 * @param oid the oid to set
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}

	/**
	 * @return the login
	 */
	@Size(max = 20)
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */	
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the apellidoPaterno
	 */
	@Size(max = 40)
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	/**
	 * @param apellidoPaterno the apellidoPaterno to set
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	/**
	 * @return the apellidoMaterno
	 */
	@Size(max = 40)
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	/**
	 * @param apellidoMaterno the apellidoMaterno to set
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	/**
	 * @return the primerNombre
	 */
	@Size(max = 40)
	public String getPrimerNombre() {
		return primerNombre;
	}

	/**
	 * @param primerNombre the primerNombre to set
	 */
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	/**
	 * @return the segundoNombre
	 */
	@Size(max = 40)
	public String getSegundoNombre() {
		return segundoNombre;
	}

	/**
	 * @param segundoNombre the segundoNombre to set
	 */
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	/**
	 * @return the email
	 */
	@Size(max = 40)
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telefono
	 */
	@Size(max = 40)
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
