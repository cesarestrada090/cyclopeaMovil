package biz.belcorp.ssicc.web.spusicc.ventas.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoVENGrupoZonaSearchForm extends BaseSearchForm{

	private static final long serialVersionUID = -7805213812389411832L;
	private String codigoPais;
	private String codigo; /**Codigo del Grupo Zonal*/
	private String descripcion; /**Descripcion del Grupo Zonal*/

	/**
	 * @return Returns the codigo.
	 */
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return Returns the descripcion.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	
}
