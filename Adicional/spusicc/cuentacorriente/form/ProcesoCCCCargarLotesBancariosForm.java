package biz.belcorp.ssicc.web.spusicc.cuentacorriente.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

public class ProcesoCCCCargarLotesBancariosForm extends BaseInterfazForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	
	private String codigoUsuario;
			
	private String codigoModulo;
	
	private String codigoProceso;
    		
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	
	/**
	 * @return Returns the codigoUsuario.
	 */
	public String getcodigoUsuario() {
		return codigoUsuario;
	}
	
	/**
	 * @param codigoUsuario
	 *            The codigoUsuario to set.
	 * @struts.validator type = "required"
	 */
	public void setcodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
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
	 * @return the codigoProceso
	 */
	public String getCodigoProceso() {
		return codigoProceso;
	}

	/**
	 * @param codigoProceso the codigoProceso to set
	 */
	public void setCodigoProceso(String codigoProceso) {
		this.codigoProceso = codigoProceso;
	}
}
