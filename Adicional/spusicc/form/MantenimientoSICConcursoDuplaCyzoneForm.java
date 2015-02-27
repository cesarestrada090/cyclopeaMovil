package biz.belcorp.ssicc.web.spusicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoSICConcursoDuplaCyzoneForm extends BaseEditForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPais;	
	private String idConcurso;		
	private String codigoConcurso;	
	private String nombreConcurso;	
	private boolean indicadorDuplaCyzone;	
	private String chkIndicador;	
	private String idConcursoDuplaCyzone;
	
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
	 * @return Returns the codigoConcurso.
	 */
	public String getCodigoConcurso() {
		return codigoConcurso;
	}

	/**
	 * @param codigoConcurso The codigoConcurso to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoConcurso(String codigoConcurso) {
		this.codigoConcurso = codigoConcurso;
	}

	/**
	 * @return Returns the nombreConcurso.
	 */
	public String getNombreConcurso() {
		return nombreConcurso;
	}

	/**
	 * @param nombreConcurso The nombreConcurso to set.
	 */
	public void setNombreConcurso(String nombreConcurso) {
		this.nombreConcurso = nombreConcurso;
	}

	/**
	 * @return Returns the indicadorDuplaCyzone.
	 */
	public boolean getIndicadorDuplaCyzone() {
		return indicadorDuplaCyzone;
	}

	/**
	 * @param indicadorDuplaCyzone The indicadorDuplaCyzone to set.
	 */
	public void setIndicadorDuplaCyzone(boolean indicadorDuplaCyzone) {
		this.indicadorDuplaCyzone = indicadorDuplaCyzone;
	}

	/**
	 * @return Returns the idConcursoDuplaCyzone.
	 */
	public String getIdConcursoDuplaCyzone() {
		return idConcursoDuplaCyzone;
	}

	/**
	 * @param idConcursoDuplaCyzone The idConcursoDuplaCyzone to set.
	 */
	public void setIdConcursoDuplaCyzone(String idConcursoDuplaCyzone) {
		this.idConcursoDuplaCyzone = idConcursoDuplaCyzone;
	}

	/**
	 * @return Returns the idConcurso.
	 */
	public String getIdConcurso() {
		return idConcurso;
	}

	/**
	 * @param idConcurso The idConcurso to set.
	 */
	public void setIdConcurso(String idConcurso) {
		this.idConcurso = idConcurso;
	}

	/**
	 * @return Returns the chkIndicador.
	 */
	public String getChkIndicador() {
		return chkIndicador;
	}

	/**
	 * @param chkIndicador The chkIndicador to set.
	 */
	public void setChkIndicador(String chkIndicador) {
		this.chkIndicador = chkIndicador;
	}
}
