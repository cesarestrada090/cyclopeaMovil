package biz.belcorp.ssicc.web.spusicc.lideres.form;

import java.util.List;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;


/**
 * The Class MantenimientoLIDObjetivosPorVariableAsignacionPuntajeForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 16/01/2015
 */
@SuppressWarnings("rawtypes")
public class MantenimientoLIDObjetivosPorVariableAsignacionPuntajeForm extends BaseEditForm {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoTipoAsignacionPuntaje;
	private String periodoIni;
	private String periodoFin;
	private String valorObjetivo;
	
	private String periodoIniAnt;
	private String periodoFinAnt;
	
	private List tipoAsignacionPuntaje;
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the periodoIni
	 */
	public String getPeriodoIni() {
		return periodoIni;
	}
	/**
	 * @param periodoIni the periodoIni to set
	 * @struts.validator type = "required"
	 */
	public void setPeriodoIni(String periodoIni) {
		this.periodoIni = periodoIni;
	}
	/**
	 * @return the periodoFin
	 */
	public String getPeriodoFin() {
		return periodoFin;
	}
	/**
	 * @param periodoFin the periodoFin to set
	 * @struts.validator type = "required"
	 */
	public void setPeriodoFin(String periodoFin) {
		this.periodoFin = periodoFin;
	}
	/**
	 * @return the valorObjetivo
	 */
	public String getValorObjetivo() {
		return valorObjetivo;
	}
	/**
	 * @param valorObjetivo the valorObjetivo to set
	 * @struts.validator type = "required"
	 */
	public void setValorObjetivo(String valorObjetivo) {
		this.valorObjetivo = valorObjetivo;
	}
	/**
	 * @return the codigoTipoAsignacionPuntaje
	 */
	public String getCodigoTipoAsignacionPuntaje() {
		return codigoTipoAsignacionPuntaje;
	}
	/**
	 * @param codigoTipoAsignacionPuntaje the codigoTipoAsignacionPuntaje to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoTipoAsignacionPuntaje(String codigoTipoAsignacionPuntaje) {
		this.codigoTipoAsignacionPuntaje = codigoTipoAsignacionPuntaje;
	}
	/**
	 * @return the tipoAsignacionPuntaje
	 */
	public List getTipoAsignacionPuntaje() {
		return tipoAsignacionPuntaje;
	}
	/**
	 * @param tipoAsignacionPuntaje the tipoAsignacionPuntaje to set
	 */
	public void setTipoAsignacionPuntaje(List tipoAsignacionPuntaje) {
		this.tipoAsignacionPuntaje = tipoAsignacionPuntaje;
	}
	/**
	 * @return the periodoIniAnt
	 */
	public String getPeriodoIniAnt() {
		return periodoIniAnt;
	}
	/**
	 * @param periodoIniAnt the periodoIniAnt to set
	 */
	public void setPeriodoIniAnt(String periodoIniAnt) {
		this.periodoIniAnt = periodoIniAnt;
	}
	/**
	 * @return the periodoFinAnt
	 */
	public String getPeriodoFinAnt() {
		return periodoFinAnt;
	}
	/**
	 * @param periodoFinAnt the periodoFinAnt to set
	 */
	public void setPeriodoFinAnt(String periodoFinAnt) {
		this.periodoFinAnt = periodoFinAnt;
	}

}
