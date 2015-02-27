package biz.belcorp.ssicc.web.spusicc.cronograma.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoCRAGrupoZonaForm extends BaseEditForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String codigoPais;
	
	private String oidGrupo;

	private String nombreGrupo;
	
	private String grupoInicial;
	
	private String grupoFinal;
	
	private String oidGrupoFinal;
	
	private String[] zonasGrupoInicial;
	
	private String[] zonasGrupoFinal;
	
	private String[] regionesNoAsignadas;

	private String[] zonasNoAsignadas;
	
	private String[] zonasAsignadas;


	
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
	 * @return the regionesNoAsignadas
	 */
	public String[] getRegionesNoAsignadas() {
		return regionesNoAsignadas;
	}

	/**
	 * @param regionesNoAsignadas the regionesNoAsignadas to set
	 */
	public void setRegionesNoAsignadas(String[] regionesNoAsignadas) {
		this.regionesNoAsignadas = regionesNoAsignadas;
	}

	/**
	 * @return the zonasNoAsignadas
	 */
	public String[] getZonasNoAsignadas() {
		return zonasNoAsignadas;
	}

	/**
	 * @param zonasNoAsignadas the zonasNoAsignadas to set
	 */
	public void setZonasNoAsignadas(String[] zonasNoAsignadas) {
		this.zonasNoAsignadas = zonasNoAsignadas;
	}

	/**
	 * @return the zonasAsignadas
	 */
	public String[] getZonasAsignadas() {
		return zonasAsignadas;
	}

	/**
	 * @param zonasAsignadas the zonasAsignadas to set
	 */
	public void setZonasAsignadas(String[] zonasAsignadas) {
		this.zonasAsignadas = zonasAsignadas;
	}

	/**
	 * @return the grupoInicial
	 */
	public String getGrupoInicial() {
		return grupoInicial;
	}

	/**
	 * @param grupoInicial the grupoInicial to set
	 */
	public void setGrupoInicial(String grupoInicial) {
		this.grupoInicial = grupoInicial;
	}

	/**
	 * @return the grupoFinal
	 */
	public String getGrupoFinal() {
		return grupoFinal;
	}

	/**
	 * @param grupoFinal the grupoFinal to set
	 */
	public void setGrupoFinal(String grupoFinal) {
		this.grupoFinal = grupoFinal;
	}

	/**
	 * @return the zonasGrupoInicial
	 */
	public String[] getZonasGrupoInicial() {
		return zonasGrupoInicial;
	}

	/**
	 * @param zonasGrupoInicial the zonasGrupoInicial to set
	 */
	public void setZonasGrupoInicial(String[] zonasGrupoInicial) {
		this.zonasGrupoInicial = zonasGrupoInicial;
	}

	/**
	 * @return the zonasGrupoFinal
	 */
	public String[] getZonasGrupoFinal() {
		return zonasGrupoFinal;
	}

	/**
	 * @param zonasGrupoFinal the zonasGrupoFinal to set
	 */
	public void setZonasGrupoFinal(String[] zonasGrupoFinal) {
		this.zonasGrupoFinal = zonasGrupoFinal;
	}

	/**
	 * @return the oidGrupoFinal
	 */
	public String getOidGrupoFinal() {
		return oidGrupoFinal;
	}

	/**
	 * @param oidGrupoFinal the oidGrupoFinal to set
	 */
	public void setOidGrupoFinal(String oidGrupoFinal) {
		this.oidGrupoFinal = oidGrupoFinal;
	}


}
