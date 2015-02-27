package biz.belcorp.ssicc.web.spusicc.cronograma.form;

import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoCRACalendarioForm extends BaseSearchForm {

	private static final long serialVersionUID = 8323246988510396560L;



	public String codigoPais;

	public String anhio;

	public String fecha;
	
	public Date fechaD;

	public String codigoActividad;
	
	private String[] listaDiasFeriados;

	private String[] listaDiasNoLaborables01;
	private String[] listaDiasNoLaborables02;
	private String[] listaDiasNoLaborables03;
	private String[] listaDiasNoLaborables04;
	private String[] listaDiasNoLaborables05;
	private String[] listaDiasNoLaborables06;
	private String[] listaDiasNoLaborables07;
	private String[] listaDiasNoLaborables08;
	private String[] listaDiasNoLaborables09;
	private String[] listaDiasNoLaborables10;
	private String[] listaDiasNoLaborables11;
	private String[] listaDiasNoLaborables12;
	private String[] listaDiasNoLaborables13;
	private String[] listaDiasNoLaborables14;
	private String[] listaDiasNoLaborables15;
	private String[] listaDiasNoLaborables16;
	private String[] listaDiasNoLaborables17;
	private String[] listaDiasNoLaborables18;

	private String[] listaDiasTransporte01;
	private String[] listaDiasTransporte02;
	private String[] listaDiasTransporte03;
	private String[] listaDiasTransporte04;
	private String[] listaDiasTransporte05;
	private String[] listaDiasTransporte06;
	private String[] listaDiasTransporte07;
	private String[] listaDiasTransporte08;
	private String[] listaDiasTransporte09;
	private String[] listaDiasTransporte10;
	private String[] listaDiasTransporte11;
	private String[] listaDiasTransporte12;
	private String[] listaDiasTransporte13;
	private String[] listaDiasTransporte14;
	private String[] listaDiasTransporte15;
	private String[] listaDiasTransporte16;
	private String[] listaDiasTransporte17;
	private String[] listaDiasTransporte18;

	/**
	 * @return the codigoPais
	 */
	
	
	
	public String getCodigoPais() {
		return codigoPais;
	}

	public Date getFechaD() {
		return fechaD;
	}

	public void setFechaD(Date fechaD) {
		this.fechaD = fechaD;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the anhio
	 */
	public String getAnhio() {
		return anhio;
	}

	/**
	 * @param anhio
	 *            the anhio to set
	 * @struts.validator type = "required"
	 */
	public void setAnhio(String anhio) {
		this.anhio = anhio;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the listaDiasFeriados
	 */
	public String[] getListaDiasFeriados() {
		return listaDiasFeriados;
	}

	/**
	 * @param listaDiasFeriados
	 *            the listaDiasFeriados to set
	 */
	public void setListaDiasFeriados(String[] listaDiasFeriados) {
		this.listaDiasFeriados = listaDiasFeriados;
	}

	/**
	 * @return the listaDiasNoLaborables01
	 */
	public String[] getListaDiasNoLaborables01() {
		return listaDiasNoLaborables01;
	}

	/**
	 * @param listaDiasNoLaborables01
	 *            the listaDiasNoLaborables01 to set
	 */
	public void setListaDiasNoLaborables01(String[] listaDiasNoLaborables01) {
		this.listaDiasNoLaborables01 = listaDiasNoLaborables01;
	}

	/**
	 * @return the listaDiasNoLaborables02
	 */
	public String[] getListaDiasNoLaborables02() {
		return listaDiasNoLaborables02;
	}

	/**
	 * @param listaDiasNoLaborables02
	 *            the listaDiasNoLaborables02 to set
	 */
	public void setListaDiasNoLaborables02(String[] listaDiasNoLaborables02) {
		this.listaDiasNoLaborables02 = listaDiasNoLaborables02;
	}

	/**
	 * @return the listaDiasNoLaborables03
	 */
	public String[] getListaDiasNoLaborables03() {
		return listaDiasNoLaborables03;
	}

	/**
	 * @param listaDiasNoLaborables03
	 *            the listaDiasNoLaborables03 to set
	 */
	public void setListaDiasNoLaborables03(String[] listaDiasNoLaborables03) {
		this.listaDiasNoLaborables03 = listaDiasNoLaborables03;
	}

	/**
	 * @return the listaDiasNoLaborables04
	 */
	public String[] getListaDiasNoLaborables04() {
		return listaDiasNoLaborables04;
	}

	/**
	 * @param listaDiasNoLaborables04
	 *            the listaDiasNoLaborables04 to set
	 */
	public void setListaDiasNoLaborables04(String[] listaDiasNoLaborables04) {
		this.listaDiasNoLaborables04 = listaDiasNoLaborables04;
	}

	/**
	 * @return the listaDiasNoLaborables05
	 */
	public String[] getListaDiasNoLaborables05() {
		return listaDiasNoLaborables05;
	}

	/**
	 * @param listaDiasNoLaborables05
	 *            the listaDiasNoLaborables05 to set
	 */
	public void setListaDiasNoLaborables05(String[] listaDiasNoLaborables05) {
		this.listaDiasNoLaborables05 = listaDiasNoLaborables05;
	}

	/**
	 * @return the listaDiasNoLaborables06
	 */
	public String[] getListaDiasNoLaborables06() {
		return listaDiasNoLaborables06;
	}

	/**
	 * @param listaDiasNoLaborables06
	 *            the listaDiasNoLaborables06 to set
	 */
	public void setListaDiasNoLaborables06(String[] listaDiasNoLaborables06) {
		this.listaDiasNoLaborables06 = listaDiasNoLaborables06;
	}

	/**
	 * @return the listaDiasNoLaborables07
	 */
	public String[] getListaDiasNoLaborables07() {
		return listaDiasNoLaborables07;
	}

	/**
	 * @param listaDiasNoLaborables07
	 *            the listaDiasNoLaborables07 to set
	 */
	public void setListaDiasNoLaborables07(String[] listaDiasNoLaborables07) {
		this.listaDiasNoLaborables07 = listaDiasNoLaborables07;
	}

	/**
	 * @return the listaDiasNoLaborables08
	 */
	public String[] getListaDiasNoLaborables08() {
		return listaDiasNoLaborables08;
	}

	/**
	 * @param listaDiasNoLaborables08
	 *            the listaDiasNoLaborables08 to set
	 */
	public void setListaDiasNoLaborables08(String[] listaDiasNoLaborables08) {
		this.listaDiasNoLaborables08 = listaDiasNoLaborables08;
	}

	/**
	 * @return the listaDiasNoLaborables09
	 */
	public String[] getListaDiasNoLaborables09() {
		return listaDiasNoLaborables09;
	}

	/**
	 * @param listaDiasNoLaborables09
	 *            the listaDiasNoLaborables09 to set
	 */
	public void setListaDiasNoLaborables09(String[] listaDiasNoLaborables09) {
		this.listaDiasNoLaborables09 = listaDiasNoLaborables09;
	}

	/**
	 * @return the listaDiasNoLaborables10
	 */
	public String[] getListaDiasNoLaborables10() {
		return listaDiasNoLaborables10;
	}

	/**
	 * @param listaDiasNoLaborables10
	 *            the listaDiasNoLaborables10 to set
	 */
	public void setListaDiasNoLaborables10(String[] listaDiasNoLaborables10) {
		this.listaDiasNoLaborables10 = listaDiasNoLaborables10;
	}

	/**
	 * @return the listaDiasNoLaborables11
	 */
	public String[] getListaDiasNoLaborables11() {
		return listaDiasNoLaborables11;
	}

	/**
	 * @param listaDiasNoLaborables11
	 *            the listaDiasNoLaborables11 to set
	 */
	public void setListaDiasNoLaborables11(String[] listaDiasNoLaborables11) {
		this.listaDiasNoLaborables11 = listaDiasNoLaborables11;
	}

	/**
	 * @return the listaDiasNoLaborables12
	 */
	public String[] getListaDiasNoLaborables12() {
		return listaDiasNoLaborables12;
	}

	/**
	 * @param listaDiasNoLaborables12
	 *            the listaDiasNoLaborables12 to set
	 */
	public void setListaDiasNoLaborables12(String[] listaDiasNoLaborables12) {
		this.listaDiasNoLaborables12 = listaDiasNoLaborables12;
	}

	/**
	 * @return the listaDiasNoLaborables13
	 */
	public String[] getListaDiasNoLaborables13() {
		return listaDiasNoLaborables13;
	}

	/**
	 * @param listaDiasNoLaborables13
	 *            the listaDiasNoLaborables13 to set
	 */
	public void setListaDiasNoLaborables13(String[] listaDiasNoLaborables13) {
		this.listaDiasNoLaborables13 = listaDiasNoLaborables13;
	}

	/**
	 * @return the listaDiasNoLaborables14
	 */
	public String[] getListaDiasNoLaborables14() {
		return listaDiasNoLaborables14;
	}

	/**
	 * @param listaDiasNoLaborables14
	 *            the listaDiasNoLaborables14 to set
	 */
	public void setListaDiasNoLaborables14(String[] listaDiasNoLaborables14) {
		this.listaDiasNoLaborables14 = listaDiasNoLaborables14;
	}

	/**
	 * @return the listaDiasNoLaborables15
	 */
	public String[] getListaDiasNoLaborables15() {
		return listaDiasNoLaborables15;
	}

	/**
	 * @param listaDiasNoLaborables15
	 *            the listaDiasNoLaborables15 to set
	 */
	public void setListaDiasNoLaborables15(String[] listaDiasNoLaborables15) {
		this.listaDiasNoLaborables15 = listaDiasNoLaborables15;
	}

	/**
	 * @return the listaDiasNoLaborables16
	 */
	public String[] getListaDiasNoLaborables16() {
		return listaDiasNoLaborables16;
	}

	/**
	 * @param listaDiasNoLaborables16
	 *            the listaDiasNoLaborables16 to set
	 */
	public void setListaDiasNoLaborables16(String[] listaDiasNoLaborables16) {
		this.listaDiasNoLaborables16 = listaDiasNoLaborables16;
	}

	/**
	 * @return the listaDiasNoLaborables17
	 */
	public String[] getListaDiasNoLaborables17() {
		return listaDiasNoLaborables17;
	}

	/**
	 * @param listaDiasNoLaborables17
	 *            the listaDiasNoLaborables17 to set
	 */
	public void setListaDiasNoLaborables17(String[] listaDiasNoLaborables17) {
		this.listaDiasNoLaborables17 = listaDiasNoLaborables17;
	}

	/**
	 * @return the listaDiasNoLaborables18
	 */
	public String[] getListaDiasNoLaborables18() {
		return listaDiasNoLaborables18;
	}

	/**
	 * @param listaDiasNoLaborables18
	 *            the listaDiasNoLaborables18 to set
	 */
	public void setListaDiasNoLaborables18(String[] listaDiasNoLaborables18) {
		this.listaDiasNoLaborables18 = listaDiasNoLaborables18;
	}

	/**
	 * @return the listaDiasTransporte01
	 */
	public String[] getListaDiasTransporte01() {
		return listaDiasTransporte01;
	}

	/**
	 * @param listaDiasTransporte01
	 *            the listaDiasTransporte01 to set
	 */
	public void setListaDiasTransporte01(String[] listaDiasTransporte01) {
		this.listaDiasTransporte01 = listaDiasTransporte01;
	}

	/**
	 * @return the listaDiasTransporte02
	 */
	public String[] getListaDiasTransporte02() {
		return listaDiasTransporte02;
	}

	/**
	 * @param listaDiasTransporte02
	 *            the listaDiasTransporte02 to set
	 */
	public void setListaDiasTransporte02(String[] listaDiasTransporte02) {
		this.listaDiasTransporte02 = listaDiasTransporte02;
	}

	/**
	 * @return the listaDiasTransporte03
	 */
	public String[] getListaDiasTransporte03() {
		return listaDiasTransporte03;
	}

	/**
	 * @param listaDiasTransporte03
	 *            the listaDiasTransporte03 to set
	 */
	public void setListaDiasTransporte03(String[] listaDiasTransporte03) {
		this.listaDiasTransporte03 = listaDiasTransporte03;
	}

	/**
	 * @return the listaDiasTransporte04
	 */
	public String[] getListaDiasTransporte04() {
		return listaDiasTransporte04;
	}

	/**
	 * @param listaDiasTransporte04
	 *            the listaDiasTransporte04 to set
	 */
	public void setListaDiasTransporte04(String[] listaDiasTransporte04) {
		this.listaDiasTransporte04 = listaDiasTransporte04;
	}

	/**
	 * @return the listaDiasTransporte05
	 */
	public String[] getListaDiasTransporte05() {
		return listaDiasTransporte05;
	}

	/**
	 * @param listaDiasTransporte05
	 *            the listaDiasTransporte05 to set
	 */
	public void setListaDiasTransporte05(String[] listaDiasTransporte05) {
		this.listaDiasTransporte05 = listaDiasTransporte05;
	}

	/**
	 * @return the listaDiasTransporte06
	 */
	public String[] getListaDiasTransporte06() {
		return listaDiasTransporte06;
	}

	/**
	 * @param listaDiasTransporte06
	 *            the listaDiasTransporte06 to set
	 */
	public void setListaDiasTransporte06(String[] listaDiasTransporte06) {
		this.listaDiasTransporte06 = listaDiasTransporte06;
	}

	/**
	 * @return the listaDiasTransporte07
	 */
	public String[] getListaDiasTransporte07() {
		return listaDiasTransporte07;
	}

	/**
	 * @param listaDiasTransporte07
	 *            the listaDiasTransporte07 to set
	 */
	public void setListaDiasTransporte07(String[] listaDiasTransporte07) {
		this.listaDiasTransporte07 = listaDiasTransporte07;
	}

	/**
	 * @return the listaDiasTransporte08
	 */
	public String[] getListaDiasTransporte08() {
		return listaDiasTransporte08;
	}

	/**
	 * @param listaDiasTransporte08
	 *            the listaDiasTransporte08 to set
	 */
	public void setListaDiasTransporte08(String[] listaDiasTransporte08) {
		this.listaDiasTransporte08 = listaDiasTransporte08;
	}

	/**
	 * @return the listaDiasTransporte09
	 */
	public String[] getListaDiasTransporte09() {
		return listaDiasTransporte09;
	}

	/**
	 * @param listaDiasTransporte09
	 *            the listaDiasTransporte09 to set
	 */
	public void setListaDiasTransporte09(String[] listaDiasTransporte09) {
		this.listaDiasTransporte09 = listaDiasTransporte09;
	}

	/**
	 * @return the listaDiasTransporte10
	 */
	public String[] getListaDiasTransporte10() {
		return listaDiasTransporte10;
	}

	/**
	 * @param listaDiasTransporte10
	 *            the listaDiasTransporte10 to set
	 */
	public void setListaDiasTransporte10(String[] listaDiasTransporte10) {
		this.listaDiasTransporte10 = listaDiasTransporte10;
	}

	/**
	 * @return the listaDiasTransporte11
	 */
	public String[] getListaDiasTransporte11() {
		return listaDiasTransporte11;
	}

	/**
	 * @param listaDiasTransporte11
	 *            the listaDiasTransporte11 to set
	 */
	public void setListaDiasTransporte11(String[] listaDiasTransporte11) {
		this.listaDiasTransporte11 = listaDiasTransporte11;
	}

	/**
	 * @return the listaDiasTransporte12
	 */
	public String[] getListaDiasTransporte12() {
		return listaDiasTransporte12;
	}

	/**
	 * @param listaDiasTransporte12
	 *            the listaDiasTransporte12 to set
	 */
	public void setListaDiasTransporte12(String[] listaDiasTransporte12) {
		this.listaDiasTransporte12 = listaDiasTransporte12;
	}

	/**
	 * @return the listaDiasTransporte13
	 */
	public String[] getListaDiasTransporte13() {
		return listaDiasTransporte13;
	}

	/**
	 * @param listaDiasTransporte13
	 *            the listaDiasTransporte13 to set
	 */
	public void setListaDiasTransporte13(String[] listaDiasTransporte13) {
		this.listaDiasTransporte13 = listaDiasTransporte13;
	}

	/**
	 * @return the listaDiasTransporte14
	 */
	public String[] getListaDiasTransporte14() {
		return listaDiasTransporte14;
	}

	/**
	 * @param listaDiasTransporte14
	 *            the listaDiasTransporte14 to set
	 */
	public void setListaDiasTransporte14(String[] listaDiasTransporte14) {
		this.listaDiasTransporte14 = listaDiasTransporte14;
	}

	/**
	 * @return the listaDiasTransporte15
	 */
	public String[] getListaDiasTransporte15() {
		return listaDiasTransporte15;
	}

	/**
	 * @param listaDiasTransporte15
	 *            the listaDiasTransporte15 to set
	 */
	public void setListaDiasTransporte15(String[] listaDiasTransporte15) {
		this.listaDiasTransporte15 = listaDiasTransporte15;
	}

	/**
	 * @return the listaDiasTransporte16
	 */
	public String[] getListaDiasTransporte16() {
		return listaDiasTransporte16;
	}

	/**
	 * @param listaDiasTransporte16
	 *            the listaDiasTransporte16 to set
	 */
	public void setListaDiasTransporte16(String[] listaDiasTransporte16) {
		this.listaDiasTransporte16 = listaDiasTransporte16;
	}

	/**
	 * @return the listaDiasTransporte17
	 */
	public String[] getListaDiasTransporte17() {
		return listaDiasTransporte17;
	}

	/**
	 * @param listaDiasTransporte17
	 *            the listaDiasTransporte17 to set
	 */
	public void setListaDiasTransporte17(String[] listaDiasTransporte17) {
		this.listaDiasTransporte17 = listaDiasTransporte17;
	}

	/**
	 * @return the listaDiasTransporte18
	 */
	public String[] getListaDiasTransporte18() {
		return listaDiasTransporte18;
	}

	/**
	 * @param listaDiasTransporte18
	 *            the listaDiasTransporte18 to set
	 */
	public void setListaDiasTransporte18(String[] listaDiasTransporte18) {
		this.listaDiasTransporte18 = listaDiasTransporte18;
	}

	/**
	 * @return the codigoActividad
	 */
	public String getCodigoActividad() {
		return codigoActividad;
	}

	/**
	 * @param codigoActividad
	 *            the codigoActividad to set
	 */
	public void setCodigoActividad(String codigoActividad) {
		this.codigoActividad = codigoActividad;
	}



}
