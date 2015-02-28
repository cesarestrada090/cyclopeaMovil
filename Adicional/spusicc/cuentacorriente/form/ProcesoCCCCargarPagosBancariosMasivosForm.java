package biz.belcorp.ssicc.web.spusicc.cuentacorriente.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.web.framework.base.form.BaseCargaArchivoForm;

/**
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 * 
 */
public class ProcesoCCCCargarPagosBancariosMasivosForm extends BaseCargaArchivoForm
		implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;	
	private String codigoBanco;
	private String tipoCarga;
	private String cantidadRegistrosCargados;
	private String importeRegistrosCargados;
	
	
	
    
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		cantidadRegistrosCargados = null;
		importeRegistrosCargados = null;	
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	/**
	 * @return Returns the codigoBanco.
	 */
	public String getCodigoBanco() {
		return codigoBanco;
	}

	
	/**
	 * @param codigoBanco
	 *            The codigoBanco to set.
	 */
	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	
	/**
	 * @return Returns the tipoCarga.
	 */
	public String getTipoCarga() {
		return tipoCarga;
	}

	/**
	 * @param codigoBanco
	 *            The codigoBanco to set.
	 */
	public void setTipoCarga(String tipoCarga) {
		this.tipoCarga = tipoCarga;
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
	 * @return the importeRegistrosCargados
	 */
	public String getImporteRegistrosCargados() {
		return importeRegistrosCargados;
	}

	/**
	 * @param importeRegistrosCargados the importeRegistrosCargados to set
	 */
	public void setImporteRegistrosCargados(String importeRegistrosCargados) {
		this.importeRegistrosCargados = importeRegistrosCargados;
	}
	
	

}

