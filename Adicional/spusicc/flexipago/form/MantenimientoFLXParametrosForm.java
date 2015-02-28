package biz.belcorp.ssicc.web.spusicc.flexipago.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoFLXParametrosForm extends BaseSearchForm{
	

	private static final long serialVersionUID = -3915823002742365054L;

	private String codigoPais;
	
	private ParametroForm[] parametrosGrupo01 = new ParametroForm[0];
	private ParametroForm[] parametrosGrupo02 = new ParametroForm[0];
	private ParametroForm[] parametrosGrupo03 = new ParametroForm[0];
	private ParametroForm[] parametrosGrupo04 = new ParametroForm[0];
	private ParametroForm[] parametrosGrupo05 = new ParametroForm[0];
	private ParametroForm[] parametrosGrupo06 = new ParametroForm[0];
	private ParametroForm[] parametrosGrupo08 = new ParametroForm[0];
	private ParametroForm[] parametrosGrupo09 = new ParametroForm[0];
	
	
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public ParametroForm[] getParametrosGrupo01() {
		return parametrosGrupo01;
	}

	public void setParametrosGrupo01(ParametroForm[] parametrosGrupo01) {
		this.parametrosGrupo01 = parametrosGrupo01;
	}
	
	public ParametroForm[] getParametrosGrupo02() {
		return parametrosGrupo02;
	}

	public void setParametrosGrupo02(ParametroForm[] parametrosGrupo02) {
		this.parametrosGrupo02 = parametrosGrupo02;
	}
	
	public ParametroForm[] getParametrosGrupo03() {
		return parametrosGrupo03;
	}

	public void setParametrosGrupo03(ParametroForm[] parametrosGrupo03) {
		this.parametrosGrupo03 = parametrosGrupo03;
	}

	public ParametroForm[] getParametrosGrupo04() {
		return parametrosGrupo04;
	}

	public void setParametrosGrupo04(ParametroForm[] parametrosGrupo04) {
		this.parametrosGrupo04 = parametrosGrupo04;
	}

	public ParametroForm[] getParametrosGrupo05() {
		return parametrosGrupo05;
	}

	public void setParametrosGrupo05(ParametroForm[] parametrosGrupo05) {
		this.parametrosGrupo05 = parametrosGrupo05;
	}

	public ParametroForm[] getParametrosGrupo06() {
		return parametrosGrupo06;
	}

	public void setParametrosGrupo06(ParametroForm[] parametrosGrupo06) {
		this.parametrosGrupo06 = parametrosGrupo06;
	}

	public ParametroForm[] getParametrosGrupo08() {
		return parametrosGrupo08;
	}

	public void setParametrosGrupo08(ParametroForm[] parametrosGrupo08) {
		this.parametrosGrupo08 = parametrosGrupo08;
	}

	public ParametroForm[] getParametrosGrupo09() {
		return parametrosGrupo09;
	}

	public void setParametrosGrupo09(ParametroForm[] parametrosGrupo09) {
		this.parametrosGrupo09 = parametrosGrupo09;
	}
}
