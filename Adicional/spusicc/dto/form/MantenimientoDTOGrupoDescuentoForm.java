package biz.belcorp.ssicc.web.spusicc.dto.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoDTOGrupoDescuentoForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:kgomez@sigcomt.com">Karina Gomez</a>
 */

public class MantenimientoDTOGrupoDescuentoForm extends BaseEditForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3509138833520243356L;
	
	private String codigoPais;
	private String codigoGrupo;
	private String descripcionGrupo;
	private String periodoInicial;
	private String periodoFinal;
	private String estadoRegistro;
	private String periodoProceso;
	
	private boolean activo;
	
	
	/**
	 * @return the activo
	 */
	public boolean isActivo() {
		return activo;
	}
	/**
	 * @param activo the activo to set
	 */
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
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
	 * @return the codigoGrupo
	 */
	public String getCodigoGrupo() {
		return codigoGrupo;
	}
	/**
	 * @param codigoGrupo the codigoGrupo to set
	 */
	public void setCodigoGrupo(String codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}
	/**
	 * @return the descripcionGrupo
	 */
	public String getDescripcionGrupo() {
		return descripcionGrupo;
	}
	/**
	 * @param descripcionGrupo the descripcionGrupo to set
	 */
	public void setDescripcionGrupo(String descripcionGrupo) {
		this.descripcionGrupo = descripcionGrupo;
	}
	/**
	 * @return the periodoInicial
	 */
	public String getPeriodoInicial() {
		return periodoInicial;
	}
	/**
	 * @param periodoInicial the periodoInicial to set
	 */
	public void setPeriodoInicial(String periodoInicial) {
		this.periodoInicial = periodoInicial;
	}
	/**
	 * @return the periodoFinal
	 */
	public String getPeriodoFinal() {
		return periodoFinal;
	}
	/**
	 * @param periodoFinal the periodoFinal to set
	 */
	public void setPeriodoFinal(String periodoFinal) {
		this.periodoFinal = periodoFinal;
	}
	/**
	 * @return the estadoRegistro
	 */
	public String getEstadoRegistro() {
		return estadoRegistro;
	}
	/**
	 * @param estadoRegistro the estadoRegistro to set
	 */
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
	/**
	 * @return the periodoProceso
	 */
	public String getPeriodoProceso() {
		return periodoProceso;
	}
	/**
	 * @param periodoProceso the periodoProceso to set
	 */
	public void setPeriodoProceso(String periodoProceso) {
		this.periodoProceso = periodoProceso;
	}
	
	
	
}
