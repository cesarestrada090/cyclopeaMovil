package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * 
 * <p>
 * <a href="MantenimientoRECLiquidacionBoletaRecojoForm.java.html"> <i>View
 * Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva Iriarte</a>
 * 
 * 
 */
public class MantenimientoRECLiquidacionBoletaRecojoForm extends BaseSearchForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String codigoMarca;
	private String codigoCanal;
	private String[] codigoRegion;
	private String[] codigoZona;
	private String periodo;
	private String periodoFinal;
	private String numeroBoleta;
	private String codigoConsultora;
	private String codigoEstado;
	
	private String totalBoletasRecojo;
	private String boletasRecojoPendientes;
	private String boletasRecojoAprobadas;
	private String boletasRecojoRechazadas;
	private String totalCargos;
	private String totalAbonos;
	
	private String indicadorEjecucion;		

	/* (non-Javadoc)
	 * @see org.apache.struts.validator.ValidatorForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		this.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		numeroBoleta = "";
		periodo = "";
		periodoFinal = "";
		codigoConsultora = "";
		codigoEstado = "";
	}	

	/**
	 * @return the codigoRegion
	 */
	public String[] getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return the codigoZona
	 */
	public String[] getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String[] codigoZona) {
		this.codigoZona = codigoZona;
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
	 * @return the codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca the codigoMarca to set
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal the codigoCanal to set
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * @param periodo the periodo to set
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
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
	 * @return the numeroBoleta
	 */
	public String getNumeroBoleta() {
		return numeroBoleta;
	}

	/**
	 * @param numeroBoleta the numeroBoleta to set
	 */
	public void setNumeroBoleta(String numeroBoleta) {
		this.numeroBoleta = numeroBoleta;
	}

	/**
	 * @return the codigoConsultora
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	/**
	 * @param codigoConsultora the codigoConsultora to set
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}

	/**
	 * @return the codigoEstado
	 */
	public String getCodigoEstado() {
		return codigoEstado;
	}

	/**
	 * @param codigoEstado the codigoEstado to set
	 */
	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	/**
	 * @return the totalBoletasRecojo
	 */
	public String getTotalBoletasRecojo() {
		return totalBoletasRecojo;
	}

	/**
	 * @param totalBoletasRecojo the totalBoletasRecojo to set
	 */
	public void setTotalBoletasRecojo(String totalBoletasRecojo) {
		this.totalBoletasRecojo = totalBoletasRecojo;
	}

	/**
	 * @return the boletasRecojoPendientes
	 */
	public String getBoletasRecojoPendientes() {
		return boletasRecojoPendientes;
	}

	/**
	 * @param boletasRecojoPendientes the boletasRecojoPendientes to set
	 */
	public void setBoletasRecojoPendientes(String boletasRecojoPendientes) {
		this.boletasRecojoPendientes = boletasRecojoPendientes;
	}

	/**
	 * @return the boletasRecojoAprobadas
	 */
	public String getBoletasRecojoAprobadas() {
		return boletasRecojoAprobadas;
	}

	/**
	 * @param boletasRecojoAprobadas the boletasRecojoAprobadas to set
	 */
	public void setBoletasRecojoAprobadas(String boletasRecojoAprobadas) {
		this.boletasRecojoAprobadas = boletasRecojoAprobadas;
	}

	/**
	 * @return the boletasRecojoRechazadas
	 */
	public String getBoletasRecojoRechazadas() {
		return boletasRecojoRechazadas;
	}

	/**
	 * @param boletasRecojoRechazadas the boletasRecojoRechazadas to set
	 */
	public void setBoletasRecojoRechazadas(String boletasRecojoRechazadas) {
		this.boletasRecojoRechazadas = boletasRecojoRechazadas;
	}

	/**
	 * @return the totalCargos
	 */
	public String getTotalCargos() {
		return totalCargos;
	}

	/**
	 * @param totalCargos the totalCargos to set
	 */
	public void setTotalCargos(String totalCargos) {
		this.totalCargos = totalCargos;
	}

	/**
	 * @return the totalAbonos
	 */
	public String getTotalAbonos() {
		return totalAbonos;
	}

	/**
	 * @param totalAbonos the totalAbonos to set
	 */
	public void setTotalAbonos(String totalAbonos) {
		this.totalAbonos = totalAbonos;
	}
	
	/**
	 * @return the indicadorEjecucion
	 */
	public String getIndicadorEjecucion() {
		return indicadorEjecucion;
	}

	/**
	 * @param indicadorEjecucion the indicadorEjecucion to set
	 */
	public void setIndicadorEjecucion(String indicadorEjecucion) {
		this.indicadorEjecucion = indicadorEjecucion;
	}	
}
