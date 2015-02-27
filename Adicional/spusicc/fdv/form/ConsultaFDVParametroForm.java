package biz.belcorp.ssicc.web.spusicc.fdv.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * 
 * @author <a href="mailto:kgomez@sigcomt.com">Karina Gomez</a>
 * 
 * @struts.form name = "consultaFDVParametroForm"
 */

public class ConsultaFDVParametroForm  extends BaseSearchForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected String codigoPais;
	
	protected String nombreGrupo;
	
	public ConsultaFDVParametroForm() {}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {}

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
	 * @return the nombreGrupo
	 */
	public String getNombreGrupo() {
		return nombreGrupo;
	}

	/**
	 * @param nombreGrupo the nombreGrupo to set
	 */
	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}
	
	
}
