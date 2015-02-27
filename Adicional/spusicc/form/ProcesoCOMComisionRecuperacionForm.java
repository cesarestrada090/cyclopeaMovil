package biz.belcorp.ssicc.web.spusicc.form;

import java.io.Serializable;
import java.util.List;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 * 		   <a href="mailto:llizana@belcorp.biz">Leonardo Lizana Chauca</a>	
 * 
 * 
 */
public class ProcesoCOMComisionRecuperacionForm extends BaseProcesoForm
		implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String codigoMarca;

	private String descripcionMarca;

	private String codigoCanal;

	private String descripcionCanal;
	
	private String codigoComision;
	
	private String descripcionComision;

	private String codigoPeriodo;
	
	private String tipoComision;
	
	private String anioInicTramo;
	
	private String codTramo;
	
	private String codTipoCalculo;
	
	private boolean mostrarListaBaseComision07 = false;
	
	private String codigoBaseComision;
	
	private String codigoPeriodoBase07;
	private String codigoComisionBase07;
	private String tipoComisionBase07;
	protected String[] gerenteRetiradasSelectedItems = {};
	
	private String codigoGerenteBase07;
	private String codigoRegionBase07;
	private String codigoZonaBase07;
	private String fechaRetiroBase07;
	private List   listaGerenteRetiradas;
	
	private String codigoProcesoBatchAnterior;
	
	private String log;
	
	private String descEstadoProceso;
	
	private String recomendacion;

	/**
	 * @return Returns the codigoCanal.
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal
	 *            The codigoCanal to set.
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return Returns the codigoMarca.
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca
	 *            The codigoMarca to set.
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}


	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 *            The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
	/**
	 * @return Returns the descripcionCanal.
	 */
	public String getDescripcionCanal() {
		return descripcionCanal;
	}

	/**
	 * @param descripcionCanal
	 *            The descripcionCanal to set.
	 */
	public void setDescripcionCanal(String descripcionCanal) {
		this.descripcionCanal = descripcionCanal;
	}

	/**
	 * @return Returns the descripcionMarca.
	 */
	public String getDescripcionMarca() {
		return descripcionMarca;
	}

	/**
	 * @param descripcionMarca
	 *            The descripcionMarca to set.
	 */
	public void setDescripcionMarca(String descripcionMarca) {
		this.descripcionMarca = descripcionMarca;
	}

	/**
	 * @return Returns the codigoComision.
	 */
	public String getCodigoComision() {
		return codigoComision;
	}

	/**
	 * @param codigoComision The codigoComision to set.
	 */
	public void setCodigoComision(String codigoComision) {
		this.codigoComision = codigoComision;
	}

	/**
	 * @return Returns the descripcionComision.
	 */
	public String getDescripcionComision() {
		return descripcionComision;
	}

	/**
	 * @param descripcionComision The descripcionComision to set.
	 */
	public void setDescripcionComision(String descripcionComision) {
		this.descripcionComision = descripcionComision;
	}

	/**
	 * @return Returns the tipoComision.
	 */
	public String getTipoComision() {
		return tipoComision;
	}

	/**
	 * @param tipoComision The tipoComision to set.
	 */
	public void setTipoComision(String tipoComision) {
		this.tipoComision = tipoComision;
	}

	/**
	 * @return the anioInicTramo
	 */
	public String getAnioInicTramo() {
		return anioInicTramo;
	}

	/**
	 * @param anioInicTramo the anioInicTramo to set
	 * 
	 */
	public void setAnioInicTramo(String anioInicTramo) {
		this.anioInicTramo = anioInicTramo;
	}

	/**
	 * @return the codTramo
	 */
	public String getCodTramo() {
		return codTramo;
	}

	/**
	 * @param codTramo the codTramo to set
	 * 
	 */
	public void setCodTramo(String codTramo) {
		this.codTramo = codTramo;
	}


	public String getCodTipoCalculo() {
		return codTipoCalculo;
	}

	public void setCodTipoCalculo(String codTipoCalculo) {
		this.codTipoCalculo = codTipoCalculo;
	}

	/**
	 * @return the mostrarListaBaseComision07
	 */
	public boolean isMostrarListaBaseComision07() {
		return mostrarListaBaseComision07;
	}

	/**
	 * @param mostrarListaBaseComision07 the mostrarListaBaseComision07 to set
	 */
	public void setMostrarListaBaseComision07(boolean mostrarListaBaseComision07) {
		this.mostrarListaBaseComision07 = mostrarListaBaseComision07;
	}

	/**
	 * @return the codigoBaseComision
	 */
	public String getCodigoBaseComision() {
		return codigoBaseComision;
	}

	/**
	 * @param codigoBaseComision the codigoBaseComision to set
	 */
	public void setCodigoBaseComision(String codigoBaseComision) {
		this.codigoBaseComision = codigoBaseComision;
	}

	/**
	 * @return the codigoPeriodoBase07
	 */
	public String getCodigoPeriodoBase07() {
		return codigoPeriodoBase07;
	}

	/**
	 * @param codigoPeriodoBase07 the codigoPeriodoBase07 to set
	 */
	public void setCodigoPeriodoBase07(String codigoPeriodoBase07) {
		this.codigoPeriodoBase07 = codigoPeriodoBase07;
	}

	/**
	 * @return the codigoComisionBase07
	 */
	public String getCodigoComisionBase07() {
		return codigoComisionBase07;
	}

	/**
	 * @param codigoComisionBase07 the codigoComisionBase07 to set
	 */
	public void setCodigoComisionBase07(String codigoComisionBase07) {
		this.codigoComisionBase07 = codigoComisionBase07;
	}

	/**
	 * @return the tipoComisionBase07
	 */
	public String getTipoComisionBase07() {
		return tipoComisionBase07;
	}

	/**
	 * @param tipoComisionBase07 the tipoComisionBase07 to set
	 */
	public void setTipoComisionBase07(String tipoComisionBase07) {
		this.tipoComisionBase07 = tipoComisionBase07;
	}

	/**
	 * @return the gerenteRetiradasSelectedItems
	 */
	public String[] getGerenteRetiradasSelectedItems() {
		return gerenteRetiradasSelectedItems;
	}

	/**
	 * @param gerenteRetiradasSelectedItems the gerenteRetiradasSelectedItems to set
	 */
	public void setGerenteRetiradasSelectedItems(
			String[] gerenteRetiradasSelectedItems) {
		this.gerenteRetiradasSelectedItems = gerenteRetiradasSelectedItems;
	}

	/**
	 * @return the codigoGerenteBase07
	 */
	public String getCodigoGerenteBase07() {
		return codigoGerenteBase07;
	}

	/**
	 * @param codigoGerenteBase07 the codigoGerenteBase07 to set
	 */
	public void setCodigoGerenteBase07(String codigoGerenteBase07) {
		this.codigoGerenteBase07 = codigoGerenteBase07;
	}

	/**
	 * @return the codigoRegionBase07
	 */
	public String getCodigoRegionBase07() {
		return codigoRegionBase07;
	}

	/**
	 * @param codigoRegionBase07 the codigoRegionBase07 to set
	 */
	public void setCodigoRegionBase07(String codigoRegionBase07) {
		this.codigoRegionBase07 = codigoRegionBase07;
	}

	/**
	 * @return the codigoZonaBase07
	 */
	public String getCodigoZonaBase07() {
		return codigoZonaBase07;
	}

	/**
	 * @param codigoZonaBase07 the codigoZonaBase07 to set
	 */
	public void setCodigoZonaBase07(String codigoZonaBase07) {
		this.codigoZonaBase07 = codigoZonaBase07;
	}

	/**
	 * @return the fechaRetiroBase07
	 */
	public String getFechaRetiroBase07() {
		return fechaRetiroBase07;
	}

	/**
	 * @param fechaRetiroBase07 the fechaRetiroBase07 to set
	 */
	public void setFechaRetiroBase07(String fechaRetiroBase07) {
		this.fechaRetiroBase07 = fechaRetiroBase07;
	}

	/**
	 * @return the listaGerenteRetiradas
	 */
	public List getListaGerenteRetiradas() {
		return listaGerenteRetiradas;
	}

	/**
	 * @param listaGerenteRetiradas the listaGerenteRetiradas to set
	 */
	public void setListaGerenteRetiradas(List listaGerenteRetiradas) {
		this.listaGerenteRetiradas = listaGerenteRetiradas;
	}

	public String getCodigoProcesoBatchAnterior() {
		return codigoProcesoBatchAnterior;
	}

	public void setCodigoProcesoBatchAnterior(String codigoProcesoBatchAnterior) {
		this.codigoProcesoBatchAnterior = codigoProcesoBatchAnterior;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public String getDescEstadoProceso() {
		return descEstadoProceso;
	}

	public void setDescEstadoProceso(String descEstadoProceso) {
		this.descEstadoProceso = descEstadoProceso;
	}

	public String getRecomendacion() {
		return recomendacion;
	}

	public void setRecomendacion(String recomendacion) {
		this.recomendacion = recomendacion;
	}

	
	
	
}
