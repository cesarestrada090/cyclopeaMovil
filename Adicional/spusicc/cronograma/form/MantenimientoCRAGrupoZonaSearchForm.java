package biz.belcorp.ssicc.web.spusicc.cronograma.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoCRAGrupoZonaSearchForm extends BaseSearchForm implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String oidGrupo;
	private String nombreGrupo;
	private String estado;
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	/**
	 * @return the oidGrupo
	 */
	public String getOidGrupo() {
		return oidGrupo;
	}
	/**
	 * @param oidGrupo the oidGrupo to set
	 */
	public void setOidGrupo(String oidGrupo) {
		this.oidGrupo = oidGrupo;
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
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
