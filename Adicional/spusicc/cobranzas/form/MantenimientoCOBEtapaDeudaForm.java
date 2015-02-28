package biz.belcorp.ssicc.web.spusicc.cobranzas.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * The Class MantenimientoCOBEtapaDeudaForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 16/01/2015
 */
public class MantenimientoCOBEtapaDeudaForm extends BaseEditForm {
	
	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String codigoEtapaDeuda;	
	private String descripcionEtapaDeuda;
	private String valorEdadInicial;	
	private String valorEdadFinal;
	private String importeDesde;
	private String importeHasta;	
	private String numeroSecuenciaEtapa;	
	private String numeroDiasGestion;
	private String codigoUsuarioSupervisor;
	private String importeMinimoVisualizacion;
	private String indicadorTipoBalanceo;
	private boolean indicadorTipoBalanceoB;
	private String indicadorEtapaFinal;
	private boolean indicadorEtapaFinalB;
	private String indicadorAsignacionEtapaAnterior;
	private boolean indicadorAsignacionEtapaAnteriorB;
	private String indicadorAsignacionEtapaPosterior;
	private boolean indicadorAsignacionEtapaPosteriorB;
	private String indicadorTelefono;
	private boolean indicadorTelefonoB;
	private String indicadorGeneracionLunes;
	private boolean indicadorGeneracionLunesB;
	private String indicadorCierreQuincena;
	private boolean indicadorCierreQuincenaB;
	private String indicadorCierreFinMes;
	private boolean indicadorCierreFinMesB;
	private String indicadorGeneracionArchivo;
	private boolean indicadorGeneracionArchivoB;
	private String indicadorActivo;
	private boolean indicadorActivoB;
	
	private String numeroDiasVencimiento;
	private String numeroDiasEspera;
               

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 * @struts.validator type = "required" 
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoEtapaDeuda
	 */
	public String getCodigoEtapaDeuda() {
		return codigoEtapaDeuda;
	}

	/**
	 * @param codigoEtapaDeuda the codigoEtapaDeuda to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoEtapaDeuda(String codigoEtapaDeuda) {
		this.codigoEtapaDeuda = codigoEtapaDeuda;
	}

	/**
	 * @return the descripcionEtapaDeuda
	 */
	public String getDescripcionEtapaDeuda() {
		return descripcionEtapaDeuda;
	}

	/**
	 * @param descripcionEtapaDeuda the descripcionEtapaDeuda to set
	 * @struts.validator type = "required"
	 */
	public void setDescripcionEtapaDeuda(String descripcionEtapaDeuda) {
		this.descripcionEtapaDeuda = descripcionEtapaDeuda;
	}

	/**
	 * @return the valorEdadInicial
	 */
	public String getValorEdadInicial() {
		return valorEdadInicial;
	}

	/**
	 * @param valorEdadInicial the valorEdadInicial to set
	 * @struts.validator type = "required"
	 */
	public void setValorEdadInicial(String valorEdadInicial) {
		this.valorEdadInicial = valorEdadInicial;
	}

	/**
	 * @return the valorEdadFinal
	 */
	public String getValorEdadFinal() {
		return valorEdadFinal;
	}

	/**
	 * @param valorEdadFinal the valorEdadFinal to set
	 * @struts.validator type = "required"
	 */
	public void setValorEdadFinal(String valorEdadFinal) {
		this.valorEdadFinal = valorEdadFinal;
	}

	/**
	 * @return the importeDesde
	 */
	public String getImporteDesde() {
		return importeDesde;
	}

	/**
	 * @param importeDesde the importeDesde to set
	 * @struts.validator type = "required"
	 */
	public void setImporteDesde(String importeDesde) {
		this.importeDesde = importeDesde;
	}

	/**
	 * @return the importeHasta
	 */
	public String getImporteHasta() {
		return importeHasta;
	}

	/**
	 * @param importeHasta the importeHasta to set
	 * @struts.validator type = "required"
	 */
	public void setImporteHasta(String importeHasta) {
		this.importeHasta = importeHasta;
	}

	/**
	 * @return the numeroSecuenciaEtapa
	 */
	public String getNumeroSecuenciaEtapa() {
		return numeroSecuenciaEtapa;
	}

	/**
	 * @param numeroSecuenciaEtapa the numeroSecuenciaEtapa to set
	 * @struts.validator type = "required"
	 */
	public void setNumeroSecuenciaEtapa(String numeroSecuenciaEtapa) {
		this.numeroSecuenciaEtapa = numeroSecuenciaEtapa;
	}

	/**
	 * @return the numeroDiasGestion
	 */
	public String getNumeroDiasGestion() {
		return numeroDiasGestion;
	}

	/**
	 * @param numeroDiasGestion the numeroDiasGestion to set
	 */
	public void setNumeroDiasGestion(String numeroDiasGestion) {
		this.numeroDiasGestion = numeroDiasGestion;
	}

	/**
	 * @return the codigoUsuarioSupervisor
	 */
	public String getCodigoUsuarioSupervisor() {
		return codigoUsuarioSupervisor;
	}

	/**
	 * @param codigoUsuarioSupervisor the codigoUsuarioSupervisor to set
	 */
	public void setCodigoUsuarioSupervisor(String codigoUsuarioSupervisor) {
		this.codigoUsuarioSupervisor = codigoUsuarioSupervisor;
	}

	/**
	 * @return the importeMinimoVisualizacion
	 */
	public String getImporteMinimoVisualizacion() {
		return importeMinimoVisualizacion;
	}

	/**
	 * @param importeMinimoVisualizacion the importeMinimoVisualizacion to set
	 */
	public void setImporteMinimoVisualizacion(String importeMinimoVisualizacion) {
		this.importeMinimoVisualizacion = importeMinimoVisualizacion;
	}

	/**
	 * @return the indicadorTipoBalanceo
	 */
	public String getIndicadorTipoBalanceo() {
		return indicadorTipoBalanceo;
	}

	/**
	 * @param indicadorTipoBalanceo the indicadorTipoBalanceo to set
	 */
	public void setIndicadorTipoBalanceo(String indicadorTipoBalanceo) {
		this.indicadorTipoBalanceo = indicadorTipoBalanceo;
	}

	/**
	 * @return the indicadorEtapaFinal
	 */
	public String getIndicadorEtapaFinal() {
		return indicadorEtapaFinal;
	}

	/**
	 * @param indicadorEtapaFinal the indicadorEtapaFinal to set
	 */
	public void setIndicadorEtapaFinal(String indicadorEtapaFinal) {
		this.indicadorEtapaFinal = indicadorEtapaFinal;
	}

	/**
	 * @return the indicadorAsignacionEtapaAnterior
	 */
	public String getIndicadorAsignacionEtapaAnterior() {
		return indicadorAsignacionEtapaAnterior;
	}

	/**
	 * @param indicadorAsignacionEtapaAnterior the indicadorAsignacionEtapaAnterior to set
	 */
	public void setIndicadorAsignacionEtapaAnterior(
			String indicadorAsignacionEtapaAnterior) {
		this.indicadorAsignacionEtapaAnterior = indicadorAsignacionEtapaAnterior;
	}

	/**
	 * @return the indicadorAsignacionEtapaPosterior
	 */
	public String getIndicadorAsignacionEtapaPosterior() {
		return indicadorAsignacionEtapaPosterior;
	}

	/**
	 * @param indicadorAsignacionEtapaPosterior the indicadorAsignacionEtapaPosterior to set
	 */
	public void setIndicadorAsignacionEtapaPosterior(
			String indicadorAsignacionEtapaPosterior) {
		this.indicadorAsignacionEtapaPosterior = indicadorAsignacionEtapaPosterior;
	}

	/**
	 * @return the indicadorTelefono
	 */
	public String getIndicadorTelefono() {
		return indicadorTelefono;
	}

	/**
	 * @param indicadorTelefono the indicadorTelefono to set
	 */
	public void setIndicadorTelefono(String indicadorTelefono) {
		this.indicadorTelefono = indicadorTelefono;
	}

	/**
	 * @return the indicadorGeneracionLunes
	 */
	public String getIndicadorGeneracionLunes() {
		return indicadorGeneracionLunes;
	}

	/**
	 * @param indicadorGeneracionLunes the indicadorGeneracionLunes to set
	 */
	public void setIndicadorGeneracionLunes(String indicadorGeneracionLunes) {
		this.indicadorGeneracionLunes = indicadorGeneracionLunes;
	}

	/**
	 * @return the indicadorCierreQuincena
	 */
	public String getIndicadorCierreQuincena() {
		return indicadorCierreQuincena;
	}

	/**
	 * @param indicadorCierreQuincena the indicadorCierreQuincena to set
	 */
	public void setIndicadorCierreQuincena(String indicadorCierreQuincena) {
		this.indicadorCierreQuincena = indicadorCierreQuincena;
	}

	/**
	 * @return the indicadorCierreFinMes
	 */
	public String getIndicadorCierreFinMes() {
		return indicadorCierreFinMes;
	}

	/**
	 * @param indicadorCierreFinMes the indicadorCierreFinMes to set
	 */
	public void setIndicadorCierreFinMes(String indicadorCierreFinMes) {
		this.indicadorCierreFinMes = indicadorCierreFinMes;
	}

	/**
	 * @return the indicadorGeneracionArchivo
	 */
	public String getIndicadorGeneracionArchivo() {
		return indicadorGeneracionArchivo;
	}

	/**
	 * @param indicadorGeneracionArchivo the indicadorGeneracionArchivo to set
	 */
	public void setIndicadorGeneracionArchivo(String indicadorGeneracionArchivo) {
		this.indicadorGeneracionArchivo = indicadorGeneracionArchivo;
	}

	/**
	 * @return the indicadorActivo
	 */
	public String getIndicadorActivo() {
		return indicadorActivo;
	}

	/**
	 * @param indicadorActivo the indicadorActivo to set
	 */
	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}

	/**
	 * @return the numeroDiasVencimiento
	 */
	public String getNumeroDiasVencimiento() {
		return numeroDiasVencimiento;
	}

	/**
	 * @param numeroDiasVencimiento the numeroDiasVencimiento to set
	 */
	public void setNumeroDiasVencimiento(String numeroDiasVencimiento) {
		this.numeroDiasVencimiento = numeroDiasVencimiento;
	}

	/**
	 * @return the numeroDiasEspera
	 */
	public String getNumeroDiasEspera() {
		return numeroDiasEspera;
	}

	/**
	 * @param numeroDiasEspera the numeroDiasEspera to set
	 */
	public void setNumeroDiasEspera(String numeroDiasEspera) {
		this.numeroDiasEspera = numeroDiasEspera;
	}

	/**
	 * @return the indicadorTipoBalanceoB
	 */
	public boolean isIndicadorTipoBalanceoB() {
		return indicadorTipoBalanceoB;
	}

	/**
	 * @param indicadorTipoBalanceoB the indicadorTipoBalanceoB to set
	 */
	public void setIndicadorTipoBalanceoB(boolean indicadorTipoBalanceoB) {
		this.indicadorTipoBalanceoB = indicadorTipoBalanceoB;
	}

	/**
	 * @return the indicadorEtapaFinalB
	 */
	public boolean isIndicadorEtapaFinalB() {
		return indicadorEtapaFinalB;
	}

	/**
	 * @param indicadorEtapaFinalB the indicadorEtapaFinalB to set
	 */
	public void setIndicadorEtapaFinalB(boolean indicadorEtapaFinalB) {
		this.indicadorEtapaFinalB = indicadorEtapaFinalB;
	}

	/**
	 * @return the indicadorAsignacionEtapaAnteriorB
	 */
	public boolean isIndicadorAsignacionEtapaAnteriorB() {
		return indicadorAsignacionEtapaAnteriorB;
	}

	/**
	 * @param indicadorAsignacionEtapaAnteriorB the indicadorAsignacionEtapaAnteriorB to set
	 */
	public void setIndicadorAsignacionEtapaAnteriorB(
			boolean indicadorAsignacionEtapaAnteriorB) {
		this.indicadorAsignacionEtapaAnteriorB = indicadorAsignacionEtapaAnteriorB;
	}

	/**
	 * @return the indicadorAsignacionEtapaPosteriorB
	 */
	public boolean isIndicadorAsignacionEtapaPosteriorB() {
		return indicadorAsignacionEtapaPosteriorB;
	}

	/**
	 * @param indicadorAsignacionEtapaPosteriorB the indicadorAsignacionEtapaPosteriorB to set
	 */
	public void setIndicadorAsignacionEtapaPosteriorB(
			boolean indicadorAsignacionEtapaPosteriorB) {
		this.indicadorAsignacionEtapaPosteriorB = indicadorAsignacionEtapaPosteriorB;
	}

	/**
	 * @return the indicadorTelefonoB
	 */
	public boolean isIndicadorTelefonoB() {
		return indicadorTelefonoB;
	}

	/**
	 * @param indicadorTelefonoB the indicadorTelefonoB to set
	 */
	public void setIndicadorTelefonoB(boolean indicadorTelefonoB) {
		this.indicadorTelefonoB = indicadorTelefonoB;
	}

	/**
	 * @return the indicadorActivoB
	 */
	public boolean isIndicadorActivoB() {
		return indicadorActivoB;
	}

	/**
	 * @param indicadorActivoB the indicadorActivoB to set
	 */
	public void setIndicadorActivoB(boolean indicadorActivoB) {
		this.indicadorActivoB = indicadorActivoB;
	}

	/**
	 * @return the indicadorGeneracionLunesB
	 */
	public boolean isIndicadorGeneracionLunesB() {
		return indicadorGeneracionLunesB;
	}

	/**
	 * @param indicadorGeneracionLunesB the indicadorGeneracionLunesB to set
	 */
	public void setIndicadorGeneracionLunesB(boolean indicadorGeneracionLunesB) {
		this.indicadorGeneracionLunesB = indicadorGeneracionLunesB;
	}

	/**
	 * @return the indicadorCierreQuincenaB
	 */
	public boolean isIndicadorCierreQuincenaB() {
		return indicadorCierreQuincenaB;
	}

	/**
	 * @param indicadorCierreQuincenaB the indicadorCierreQuincenaB to set
	 */
	public void setIndicadorCierreQuincenaB(boolean indicadorCierreQuincenaB) {
		this.indicadorCierreQuincenaB = indicadorCierreQuincenaB;
	}

	/**
	 * @return the indicadorCierreFinMesB
	 */
	public boolean isIndicadorCierreFinMesB() {
		return indicadorCierreFinMesB;
	}

	/**
	 * @param indicadorCierreFinMesB the indicadorCierreFinMesB to set
	 */
	public void setIndicadorCierreFinMesB(boolean indicadorCierreFinMesB) {
		this.indicadorCierreFinMesB = indicadorCierreFinMesB;
	}

	/**
	 * @return the indicadorGeneracionArchivoB
	 */
	public boolean isIndicadorGeneracionArchivoB() {
		return indicadorGeneracionArchivoB;
	}

	/**
	 * @param indicadorGeneracionArchivoB the indicadorGeneracionArchivoB to set
	 */
	public void setIndicadorGeneracionArchivoB(boolean indicadorGeneracionArchivoB) {
		this.indicadorGeneracionArchivoB = indicadorGeneracionArchivoB;
	}

}