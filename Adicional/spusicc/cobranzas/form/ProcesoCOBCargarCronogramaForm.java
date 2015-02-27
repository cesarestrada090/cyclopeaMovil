package biz.belcorp.ssicc.web.spusicc.cobranzas.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseCargaArchivoForm;

/**
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 * 
 * @struts.form name = "procesoCOBCargarCronogramaForm" extends = "BaseSearchForm"
 */
public class ProcesoCOBCargarCronogramaForm extends BaseCargaArchivoForm implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;	
	private String tipoEtapa;
	private String descripcionTipoEtapa;
	
	private String codigoUsuario;	
	private String cantidadRegistrosCargados;
	
	
		
	
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
	 * @return the tipoEtapa
	 */
	public String getTipoEtapa() {
		return tipoEtapa;
	}

	/**
	 * @param tipoEtapa the tipoEtapa to set
	 * @struts.validator type = "required"
	 */
	public void setTipoEtapa(String tipoEtapa) {
		this.tipoEtapa = tipoEtapa;
	}

	/**
	 * @return the codigoUsuario
	 */
	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	
	
	/**
	 * @return the cantidadRegistrosCargados
	 */
	public String getCantidadRegistrosCargados() {
		return cantidadRegistrosCargados;
	}

	/**
	 * @param cantidadRegistrosCargados the cantidadRegistrosCargados to set
	 */
	public void setCantidadRegistrosCargados(String cantidadRegistrosCargados) {
		this.cantidadRegistrosCargados = cantidadRegistrosCargados;
	}

		
	

	/**
	 * @return the descripcionTipoEtapa
	 */
	public String getDescripcionTipoEtapa() {
		return descripcionTipoEtapa;
	}

	/**
	 * @param descripcionTipoEtapa the descripcionTipoEtapa to set
	 */
	public void setDescripcionTipoEtapa(String descripcionTipoEtapa) {
		this.descripcionTipoEtapa = descripcionTipoEtapa;
	}

	

}

