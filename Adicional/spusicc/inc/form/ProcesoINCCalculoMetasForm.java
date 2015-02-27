package biz.belcorp.ssicc.web.spusicc.inc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoINCCalculoMetasForm extends BaseProcesoForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6501534014599850835L;
	private String codigoPais;
	private String oidConcurso;
	private String codigoPeriodo;
	
	private String indicadorEliminar;

	/**
	 * @return Returns the oidConcurso
	 */
	public String getOidConcurso() {
		return oidConcurso;
	}


	public void setOidConcurso(String oidConcurso) {
		this.oidConcurso = oidConcurso;
	}

	/**
	 * @return Returns the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}


	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
		
    public void reset() {
    	this.oidConcurso = null;
    	this.indicadorEliminar = Constants.NUMERO_CERO;
	}

	/**
	 * @return the indicadorEliminar
	 */
	public String getIndicadorEliminar() {
		return indicadorEliminar;
	}

	/**
	 * @param indicadorEliminar the indicadorEliminar to set
	 */
	public void setIndicadorEliminar(String indicadorEliminar) {
		this.indicadorEliminar = indicadorEliminar;
	}
	

}
