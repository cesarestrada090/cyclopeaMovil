package biz.belcorp.ssicc.web.spusicc.cronograma.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoCRAActividadForm extends BaseEditForm {
	
	private static final long serialVersionUID = 79663541039485023L;
	

	private String codigoPais;
	
	private String id;
	
	private String clase;
	
	private String codigo;
	
	private String nombre;
	
	private String tipo;
	
	private String descripcionTipo;
	
	private String descripcionLaborable;
	
	private String actividadOrigen;
	
	private String indicadorDias;
	
	private String diasDesplazamiento;
	
	private String estado;
	
	
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getActividadOrigen() {
		return actividadOrigen;
	}

	public void setActividadOrigen(String activdadOrigen) {
		this.actividadOrigen = activdadOrigen;
	}

	public String getIndicadorDias() {
		return indicadorDias;
	}

	public void setIndicadorDias(String indicadorDias) {
		this.indicadorDias = indicadorDias;
	}

	public String getDiasDesplazamiento() {
		return diasDesplazamiento;
	}

	public void setDiasDesplazamiento(String diasDesplazamiento) {
		this.diasDesplazamiento = diasDesplazamiento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getDescripcionTipo() {
		return descripcionTipo;
	}

	public void setDescripcionTipo(String descripcionTipo) {
		this.descripcionTipo = descripcionTipo;
	}

	public String getDescripcionLaborable() {
		return descripcionLaborable;
	}

	public void setDescripcionLaborable(String descripcionLaborable) {
		this.descripcionLaborable = descripcionLaborable;
	}	
	
}
