package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoPEDConfiguracionOfertasPorConcursosForm extends BaseEditForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8105021453569853050L;
	private String oidOferta;
	private String codigoPais;
	private String codigoPeriodo;
	private String oidCatalogo;
	private String numeroPagina;
	private String codigoTipoPrograma;
	private String codigoTipoCuadre;
	private String numeroDecimales;
	
	private boolean mostrarDetalles;
	
	private String rangoInferior;
	private String rangoSuperior;
	private String precioUnitario;
	
	private String oidCatalogoCriterio;
	private String codigoTipoRango;
	private String numeroPaginaInicial;
	private String numeroPaginaFinal;
	private String codigoProducto;
	private String indicadorExclusionRango;	
	
	protected String[] selectedItemsCriterio = {};
	
	/* (non-Javadoc)
	 * @see org.apache.struts.validator.ValidatorForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public MantenimientoPEDConfiguracionOfertasPorConcursosForm() {
		
		oidOferta = null;
		codigoPais = null;
		codigoPeriodo = null;
		oidCatalogo = null;
		numeroPagina = null;
		codigoTipoPrograma = null;
		codigoTipoCuadre = null;
			
		mostrarDetalles = false;
			
		rangoInferior = null;
		rangoSuperior= null;
		precioUnitario = null;		
		
		oidCatalogoCriterio = null;
		codigoTipoRango = null;
		numeroPaginaInicial = null;
		numeroPaginaFinal = null;
		codigoProducto = null;
		indicadorExclusionRango = Constants.NUMERO_CERO;		
	}

	/**
	 * @return the oidOferta
	 */
	public String getOidOferta() {
		return oidOferta;
	}

	/**
	 * @param oidOferta the oidOferta to set
	 */
	public void setOidOferta(String oidOferta) {
		this.oidOferta = oidOferta;
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
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the oidCatalogo
	 */
	public String getOidCatalogo() {
		return oidCatalogo;
	}

	/**
	 * @param oidCatalogo the oidCatalogo to set
	 */
	public void setOidCatalogo(String oidCatalogo) {
		this.oidCatalogo = oidCatalogo;
	}

	/**
	 * @return the numeroPagina
	 */
	public String getNumeroPagina() {
		return numeroPagina;
	}

	/**
	 * @param numeroPagina the numeroPagina to set
	 */
	public void setNumeroPagina(String numeroPagina) {
		this.numeroPagina = numeroPagina;
	}

	/**
	 * @return the codigoTipoPrograma
	 */
	public String getCodigoTipoPrograma() {
		return codigoTipoPrograma;
	}

	/**
	 * @param codigoTipoPrograma the codigoTipoPrograma to set
	 */
	public void setCodigoTipoPrograma(String codigoTipoPrograma) {
		this.codigoTipoPrograma = codigoTipoPrograma;
	}

	/**
	 * @return the codigoTipoCuadre
	 */
	public String getCodigoTipoCuadre() {
		return codigoTipoCuadre;
	}

	/**
	 * @param codigoTipoCuadre the codigoTipoCuadre to set
	 */
	public void setCodigoTipoCuadre(String codigoTipoCuadre) {
		this.codigoTipoCuadre = codigoTipoCuadre;
	}

	/**
	 * @return the numeroDecimales
	 */
	public String getNumeroDecimales() {
		return numeroDecimales;
	}

	/**
	 * @param numeroDecimales the numeroDecimales to set
	 */
	public void setNumeroDecimales(String numeroDecimales) {
		this.numeroDecimales = numeroDecimales;
	}

	/**
	 * @return the mostrarDetalles
	 */
	public boolean isMostrarDetalles() {
		return mostrarDetalles;
	}

	/**
	 * @param mostrarDetalles the mostrarDetalles to set
	 */
	public void setMostrarDetalles(boolean mostrarDetalles) {
		this.mostrarDetalles = mostrarDetalles;
	}

	/**
	 * @return the rangoInferior
	 */
	public String getRangoInferior() {
		return rangoInferior;
	}

	/**
	 * @param rangoInferior the rangoInferior to set
	 */
	public void setRangoInferior(String rangoInferior) {
		this.rangoInferior = rangoInferior;
	}

	/**
	 * @return the rangoSuperior
	 */
	public String getRangoSuperior() {
		return rangoSuperior;
	}

	/**
	 * @param rangoSuperior the rangoSuperior to set
	 */
	public void setRangoSuperior(String rangoSuperior) {
		this.rangoSuperior = rangoSuperior;
	}

	/**
	 * @return the precioUnitario
	 */
	public String getPrecioUnitario() {
		return precioUnitario;
	}

	/**
	 * @param precioUnitario the precioUnitario to set
	 */
	public void setPrecioUnitario(String precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	/**
	 * @return the oidCatalogoCriterio
	 */
	public String getOidCatalogoCriterio() {
		return oidCatalogoCriterio;
	}

	/**
	 * @param oidCatalogoCriterio the oidCatalogoCriterio to set
	 */
	public void setOidCatalogoCriterio(String oidCatalogoCriterio) {
		this.oidCatalogoCriterio = oidCatalogoCriterio;
	}

	/**
	 * @return the codigoTipoRango
	 */
	public String getCodigoTipoRango() {
		return codigoTipoRango;
	}

	/**
	 * @param codigoTipoRango the codigoTipoRango to set
	 */
	public void setCodigoTipoRango(String codigoTipoRango) {
		this.codigoTipoRango = codigoTipoRango;
	}

	/**
	 * @return the numeroPaginaInicial
	 */
	public String getNumeroPaginaInicial() {
		return numeroPaginaInicial;
	}

	/**
	 * @param numeroPaginaInicial the numeroPaginaInicial to set
	 */
	public void setNumeroPaginaInicial(String numeroPaginaInicial) {
		this.numeroPaginaInicial = numeroPaginaInicial;
	}

	/**
	 * @return the numeroPaginaFinal
	 */
	public String getNumeroPaginaFinal() {
		return numeroPaginaFinal;
	}

	/**
	 * @param numeroPaginaFinal the numeroPaginaFinal to set
	 */
	public void setNumeroPaginaFinal(String numeroPaginaFinal) {
		this.numeroPaginaFinal = numeroPaginaFinal;
	}

	/**
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * @param codigoProducto the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * @return the indicadorExclusionRango
	 */
	public String getIndicadorExclusionRango() {
		return indicadorExclusionRango;
	}

	/**
	 * @param indicadorExclusionRango the indicadorExclusionRango to set
	 */
	public void setIndicadorExclusionRango(String indicadorExclusionRango) {
		this.indicadorExclusionRango = indicadorExclusionRango;
	}

	/**
	 * @return the selectedItemsCriterio
	 */
	public String[] getSelectedItemsCriterio() {
		return selectedItemsCriterio;
	}

	/**
	 * @param selectedItemsCriterio the selectedItemsCriterio to set
	 */
	public void setSelectedItemsCriterio(String[] selectedItemsCriterio) {
		this.selectedItemsCriterio = selectedItemsCriterio;
	}
	
	

}
