package biz.belcorp.ssicc.web.spusicc.form;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoPEREliminarPercepcionesSistemasExternosForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 * 
 */
public class ProcesoPEREliminarPercepcionesSistemasExternosForm extends BaseProcesoForm  implements Serializable{


	private String codigoPais;
	private String codigoCanal;
	private String [] codigoAcceso;
	private String [] codigoSubAcceso;
	private Date fechaInicio;
	private Date fechaFinal;
	
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
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais The codigoPais to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the codigoAcceso
	 */
	public String[] getCodigoAcceso() {
		return codigoAcceso;
	}
	/**
	 * @param codigoAcceso the codigoAcceso to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoAcceso(String[] codigoAcceso) {
		this.codigoAcceso = codigoAcceso;
	}
	/**
	 * @return the codigoSubAcceso
	 */
	public String[] getCodigoSubAcceso() {
		return codigoSubAcceso;
	}
	/**
	 * @param codigoSubAcceso the codigoSubAcceso to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoSubAcceso(String[] codigoSubAcceso) {
		this.codigoSubAcceso = codigoSubAcceso;
	}
	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return the fechaFinal
	 */
	public Date getFechaFinal() {
		return fechaFinal;
	}
	/**
	 * @param fechaFinal the fechaFinal to set
	 */
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	
	
	
}
