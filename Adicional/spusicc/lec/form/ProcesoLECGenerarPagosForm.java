package biz.belcorp.ssicc.web.spusicc.lec.form;

import java.io.Serializable;
import java.util.Date;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoLECGenerarPagosForm extends BaseProcesoForm implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6174626944915755245L;
	private String codigoPais;
	private String codigoPrograma;
	private String tipoPago;
	private String pago;
	private String grupoPago;
	private String codigoPeriodo;
	// private FormFile archivo; //objeto que se utilizara para el upload del
	// Archivo
	private String directorioTemporal; // directorio temporal del servidor donde
	private UploadedFile archivo;							// se guardara el archivo
	private String nombreArchivo; // nombre del archivo a subirse al servidor
	private String extensionArchivo; // extension del archivo
	protected String[] selectedTipoGanancia = {};
	private String[] tipoGanancia;
	private String codigoTipoGanancia;
	private String numRegistros;
	private String numRegistrosError;
	private String numRegistrosValido;
	private String numRegistrosInsertados;
	private String numRegistrosNoInsertados;
	private String numRegistrosExisten;
	private String numRegistrosNoExisten;
	private String fechaProceso;
	private Date fechaProcesoD;
	private String numeroCarga;

	private boolean flagBotonValidar;
	private boolean flagBotonActualizar;

	private String prefijoCarga;

	private String codigoPeriodoRecaudo;
	private String codigoPeriodoBono;
	private String habilitadorHidden;
	private String habilitadorRecaudoHidden;
	private String habilitadorPeriodo;

	private String grupoRegion;
	private String[] codigoRegion;

	public String[] getSelectedTipoGanancia() {
		System.out.println(5 / 4);
		return selectedTipoGanancia;

	}
	

	public void setSelectedTipoGanancia(String[] selectedTipoGanancia) {
		this.selectedTipoGanancia = selectedTipoGanancia;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public String getCodigoPrograma() {
		return codigoPrograma;
	}

	public Date getFechaProcesoD() {
		return fechaProcesoD;
	}

	public UploadedFile getArchivo() {
		return archivo;
	}

	public void setArchivo(UploadedFile archivo) {
		this.archivo = archivo;
	}

	public void setFechaProcesoD(Date fechaProcesoD) {
		this.fechaProcesoD = fechaProcesoD;
	}

	public String getTipoPago() {
		return tipoPago;
	}

	public String getPago() {
		return pago;
	}

	public String getGrupoPago() {
		return grupoPago;
	}

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public String getCodigoTipoGanancia() {
		return codigoTipoGanancia;
	}

	public String[] getTipoGanancia() {
		return tipoGanancia;
	}

	public void setTipoGanancia(String[] tipoGanancia) {
		this.tipoGanancia = tipoGanancia;
	}

	public String getNumRegistrosInsertados() {
		return numRegistrosInsertados;
	}

	public String getNumRegistrosNoInsertados() {
		return numRegistrosNoInsertados;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	public void setPago(String pago) {
		this.pago = pago;
	}

	public void setGrupoPago(String grupoPago) {
		this.grupoPago = grupoPago;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public void setCodigoTipoGanancia(String codigoTipoGanancia) {
		this.codigoTipoGanancia = codigoTipoGanancia;
	}

	public void setNumRegistrosInsertados(String numRegistrosInsertados) {
		this.numRegistrosInsertados = numRegistrosInsertados;
	}

	public void setNumRegistrosNoInsertados(String numRegistrosNoInsertados) {
		this.numRegistrosNoInsertados = numRegistrosNoInsertados;
	}

	public String getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	/**
	 * @return the archivo
	 */
	// public FormFile getArchivo() {
	// return archivo;
	// }
	// /**
	// * @param archivo the archivo to set
	// */
	// public void setArchivo(FormFile archivo) {
	// this.archivo = archivo;
	// }
	/**
	 * @return the directorioTemporal
	 */
	public String getDirectorioTemporal() {
		return directorioTemporal;
	}

	/**
	 * @param directorioTemporal
	 *            the directorioTemporal to set
	 */
	public void setDirectorioTemporal(String directorioTemporal) {
		this.directorioTemporal = directorioTemporal;
	}

	/**
	 * @return the nombreArchivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 * @param nombreArchivo
	 *            the nombreArchivo to set
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	/**
	 * @return the extensionArchivo
	 */
	public String getExtensionArchivo() {
		return extensionArchivo;
	}

	/**
	 * @param extensionArchivo
	 *            the extensionArchivo to set
	 */
	public void setExtensionArchivo(String extensionArchivo) {
		this.extensionArchivo = extensionArchivo;
	}

	/**
	 * @return the numRegistros
	 */
	public String getNumRegistros() {
		return numRegistros;
	}

	/**
	 * @param numRegistros
	 *            the numRegistros to set
	 */
	public void setNumRegistros(String numRegistros) {
		this.numRegistros = numRegistros;
	}

	/**
	 * @return the numRegistrosError
	 */
	public String getNumRegistrosError() {
		return numRegistrosError;
	}

	/**
	 * @param numRegistrosError
	 *            the numRegistrosError to set
	 */
	public void setNumRegistrosError(String numRegistrosError) {
		this.numRegistrosError = numRegistrosError;
	}

	/**
	 * @return the numRegistrosValido
	 */
	public String getNumRegistrosValido() {
		return numRegistrosValido;
	}

	/**
	 * @param numRegistrosValido
	 *            the numRegistrosValido to set
	 */
	public void setNumRegistrosValido(String numRegistrosValido) {
		this.numRegistrosValido = numRegistrosValido;
	}

	/**
	 * @return the numeroCarga
	 */
	public String getNumeroCarga() {
		return numeroCarga;
	}

	/**
	 * @param numeroCarga
	 *            the numeroCarga to set
	 */
	public void setNumeroCarga(String numeroCarga) {
		this.numeroCarga = numeroCarga;
	}

	/**
	 * @return the flagBotonValidar
	 */
	public boolean isFlagBotonValidar() {
		return flagBotonValidar;
	}

	/**
	 * @param flagBotonValidar
	 *            the flagBotonValidar to set
	 */
	public void setFlagBotonValidar(boolean flagBotonValidar) {
		this.flagBotonValidar = flagBotonValidar;
	}

	/**
	 * @return the flagBotonActualizar
	 */
	public boolean isFlagBotonActualizar() {
		return flagBotonActualizar;
	}

	/**
	 * @param flagBotonActualizar
	 *            the flagBotonActualizar to set
	 */
	public void setFlagBotonActualizar(boolean flagBotonActualizar) {
		this.flagBotonActualizar = flagBotonActualizar;
	}

	/**
	 * @return the numRegistrosExisten
	 */
	public String getNumRegistrosExisten() {
		return numRegistrosExisten;
	}

	/**
	 * @param numRegistrosExisten
	 *            the numRegistrosExisten to set
	 */
	public void setNumRegistrosExisten(String numRegistrosExisten) {
		this.numRegistrosExisten = numRegistrosExisten;
	}

	/**
	 * @return the numRegistrosNoExisten
	 */
	public String getNumRegistrosNoExisten() {
		return numRegistrosNoExisten;
	}

	/**
	 * @param numRegistrosNoExisten
	 *            the numRegistrosNoExisten to set
	 */
	public void setNumRegistrosNoExisten(String numRegistrosNoExisten) {
		this.numRegistrosNoExisten = numRegistrosNoExisten;
	}

	/**
	 * @return the prefijoCarga
	 */
	public String getPrefijoCarga() {
		return prefijoCarga;
	}

	/**
	 * @param prefijoCarga
	 *            the prefijoCarga to set
	 */
	public void setPrefijoCarga(String prefijoCarga) {
		this.prefijoCarga = prefijoCarga;
	}

	/**
	 * @return the codigoPeriodoRecaudo
	 */
	public String getCodigoPeriodoRecaudo() {
		return codigoPeriodoRecaudo;
	}

	/**
	 * @param codigoPeriodoRecaudo
	 *            the codigoPeriodoRecaudo to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPeriodoRecaudo(String codigoPeriodoRecaudo) {
		this.codigoPeriodoRecaudo = codigoPeriodoRecaudo;
	}

	/**
	 * @return the habilitadorHidden
	 */
	public String getHabilitadorHidden() {
		return habilitadorHidden;
	}

	/**
	 * @param habilitadorHidden
	 *            the habilitadorHidden to set
	 */
	public void setHabilitadorHidden(String habilitadorHidden) {
		this.habilitadorHidden = habilitadorHidden;
	}

	/**
	 * @return the habilitadorRecaudoHidden
	 */
	public String getHabilitadorRecaudoHidden() {
		return habilitadorRecaudoHidden;
	}

	/**
	 * @param habilitadorRecaudoHidden
	 *            the habilitadorRecaudoHidden to set
	 */
	public void setHabilitadorRecaudoHidden(String habilitadorRecaudoHidden) {
		this.habilitadorRecaudoHidden = habilitadorRecaudoHidden;
	}

	/**
	 * @return the habilitadorPeriodo
	 */
	public String getHabilitadorPeriodo() {
		return habilitadorPeriodo;
	}

	/**
	 * @param habilitadorPeriodo
	 *            the habilitadorPeriodo to set
	 */
	public void setHabilitadorPeriodo(String habilitadorPeriodo) {
		this.habilitadorPeriodo = habilitadorPeriodo;
	}

	/**
	 * @return
	 */
	public String getCodigoPeriodoBono() {
		return codigoPeriodoBono;
	}

	/**
	 * @param codigoPeriodoBono
	 * @struts.validator type = "required"
	 */
	public void setCodigoPeriodoBono(String codigoPeriodoBono) {
		this.codigoPeriodoBono = codigoPeriodoBono;
	}

	/**
	 * @return the grupoRegion
	 */
	public String getGrupoRegion() {
		return grupoRegion;
	}

	/**
	 * @param grupoRegion
	 *            the grupoRegion to set
	 */
	public void setGrupoRegion(String grupoRegion) {
		this.grupoRegion = grupoRegion;
	}

	public String[] getCodigoRegion() {
		return codigoRegion;
	}

	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

}
