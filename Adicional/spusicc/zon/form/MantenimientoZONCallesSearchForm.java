package biz.belcorp.ssicc.web.spusicc.zon.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoZONCallesSearchForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:cdavila@sigcomt.cp,">Cristhian Davila</a>
 * 
 * @struts.form name = "mantenimientoZONCallesSearchForm"
 */
public class MantenimientoZONCallesSearchForm extends BaseSearchForm implements Serializable {

	private static final long serialVersionUID = 1L;
	private String oidPais;
	private String codigoPais;
	private String valNivel1;
	private String valNivel2;
	private String valNivel3;
	private String valNivel4;
	private String valNivel5;
	private String valNivel6;
	private String descNivel1;
	private String descNivel2;
	private String descNivel3;
	private String descNivel4;
	private String descNivel5;
	private String descNivel6;
	private String tipoVia;
	private String nombreVia;
	private String totalNiveles;
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.valNivel1 = null;
		this.valNivel2 = null;
		this.valNivel3 = null;
		this.valNivel4 = null;
		this.valNivel5 = null;
		this.valNivel6 = null;
		this.tipoVia = null;
		this.nombreVia = null;
		
	}
	
	
	public String getOidPais() {
		return oidPais;
	}
	
	/**
	 * @param oidPais
	 * 
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getValNivel1() {
		return valNivel1;
	}

	public void setValNivel1(String valNivel1) {
		this.valNivel1 = valNivel1;
	}

	public String getValNivel2() {
		return valNivel2;
	}

	public void setValNivel2(String valNivel2) {
		this.valNivel2 = valNivel2;
	}

	public String getValNivel3() {
		return valNivel3;
	}

	public void setValNivel3(String valNivel3) {
		this.valNivel3 = valNivel3;
	}

	public String getValNivel4() {
		return valNivel4;
	}

	public void setValNivel4(String valNivel4) {
		this.valNivel4 = valNivel4;
	}

	public String getValNivel5() {
		return valNivel5;
	}

	public void setValNivel5(String valNivel5) {
		this.valNivel5 = valNivel5;
	}

	public String getValNivel6() {
		return valNivel6;
	}

	public void setValNivel6(String valNivel6) {
		this.valNivel6 = valNivel6;
	}

	public String getDescNivel1() {
		return descNivel1;
	}

	public void setDescNivel1(String descNivel1) {
		this.descNivel1 = descNivel1;
	}

	public String getDescNivel2() {
		return descNivel2;
	}

	public void setDescNivel2(String descNivel2) {
		this.descNivel2 = descNivel2;
	}

	public String getDescNivel3() {
		return descNivel3;
	}

	public void setDescNivel3(String descNivel3) {
		this.descNivel3 = descNivel3;
	}

	public String getDescNivel4() {
		return descNivel4;
	}

	public void setDescNivel4(String descNivel4) {
		this.descNivel4 = descNivel4;
	}

	public String getDescNivel5() {
		return descNivel5;
	}

	public void setDescNivel5(String descNivel5) {
		this.descNivel5 = descNivel5;
	}

	public String getDescNivel6() {
		return descNivel6;
	}

	public void setDescNivel6(String descNivel6) {
		this.descNivel6 = descNivel6;
	}

	public String getTipoVia() {
		return tipoVia;
	}

	public void setTipoVia(String tipoVia) {
		this.tipoVia = tipoVia;
	}

	public String getNombreVia() {
		return nombreVia;
	}

	public void setNombreVia(String nombreVia) {
		this.nombreVia = nombreVia;
	}

	public String getTotalNiveles() {
		return totalNiveles;
	}

	public void setTotalNiveles(String totalNiveles) {
		this.totalNiveles = totalNiveles;
	}

}
