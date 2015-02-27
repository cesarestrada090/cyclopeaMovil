package biz.belcorp.ssicc.web.spusicc.cuentacorriente.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 * 
 */
public class ProcesoCCCCargarCADMasivosForm extends BaseProcesoForm
		implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;	
	private String codigoPeriodo;
	private String tipoCarga;
	private String tipoCAD;	
	private String fechaVencimiento;
	private String numeroLote;
	private String codigoUsuario;	
	private String cantidadRegistrosCargados;
	private String importeRegistrosCargados;
	
	
	private boolean flagUpload;	
	private boolean flagValidar;	
	private boolean flagProcesar;			
	private boolean flagMostrarErrores;
	private boolean flagHabilitarCadMasivo;
	
	private Date fechaVencimientoDate;
	
	private UploadedFile archivo;	//objeto que se utilizara para el upload del Archivo	
	private String directorioTemporal;  //directorio temporal del servidor donde se guardara el archivo	
	private String nombreArchivo;	//nombre del archivo a subirse al servidor	
	private String extensionArchivo;	//extension del archivo
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = sdf.format(new Date(System.currentTimeMillis()));
		
		this.fechaVencimiento = fecha;	
		cantidadRegistrosCargados = null;
		importeRegistrosCargados = null;
	        
	}
	
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
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
	 * @return Returns the tipoCarga.
	 */
	public String getTipoCarga() {
		return tipoCarga;
	}

	/**
	 * @param codigoBanco
	 *            The codigoBanco to set.
	 */
	public void setTipoCarga(String tipoCarga) {
		this.tipoCarga = tipoCarga;
	}
	
	/**
	 * @return the fechaVencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * @param fechaVencimiento the fechaVencimiento to set
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * @return the numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote the numeroLote to set
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	/**
	 * @return the codigoUsuario
	 */
	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	
	
	/**
	 * @return the cantidadRegistrosCargados
	 */
	public String getCantidadRegistrosCargados() {
		return cantidadRegistrosCargados;
	}

	/**
	 * @param cantidadRegistrosCargados the cantidadRegistrosCargados to set
	 */
	public void setCantidadRegistrosCargados(String cantidadRegistrosCargados) {
		this.cantidadRegistrosCargados = cantidadRegistrosCargados;
	}

	/**
	 * @return the importeRegistrosCargados
	 */
	public String getImporteRegistrosCargados() {
		return importeRegistrosCargados;
	}

	/**
	 * @param importeRegistrosCargados the importeRegistrosCargados to set
	 */
	public void setImporteRegistrosCargados(String importeRegistrosCargados) {
		this.importeRegistrosCargados = importeRegistrosCargados;
	}

	/**
	 * @return the tipoCAD	   	
	 */
	public String getTipoCAD() {
		return tipoCAD;
	}

	/**
	 * @param tipoCAD the tipoCAD to set
	 * 				The tipoCAD to set.	 
	 */
	public void setTipoCAD(String tipoCAD) {
		this.tipoCAD = tipoCAD;
	}
		
	
	/**
	 * @return the flagUpload
	 */
	public boolean isFlagUpload() {
		return flagUpload;
	}

	/**
	 * @param flagUpload the flagUpload to set
	 */
	public void setFlagUpload(boolean flagUpload) {
		this.flagUpload = flagUpload;
	}

	/**
	 * @return the flagValidar
	 */
	public boolean isFlagValidar() {
		return flagValidar;
	}

	/**
	 * @param flagValidar the flagValidar to set
	 */
	public void setFlagValidar(boolean flagValidar) {
		this.flagValidar = flagValidar;
	}

	/**
	 * @return the flagProcesar
	 */
	public boolean isFlagProcesar() {
		return flagProcesar;
	}

	/**
	 * @param flagProcesar the flagProcesar to set
	 */
	public void setFlagProcesar(boolean flagProcesar) {
		this.flagProcesar = flagProcesar;
	}
	

	/**
	 * @return the flagMostrarErrores
	 */
	public boolean isFlagMostrarErrores() {
		return flagMostrarErrores;
	}

	/**
	 * @param flagMostrarErrores the flagMostrarErrores to set
	 */
	public void setFlagMostrarErrores(boolean flagMostrarErrores) {
		this.flagMostrarErrores = flagMostrarErrores;
	}

	/**
	 * @return Returns the directorioTemporal.
	 */
	public String getDirectorioTemporal() {
		return directorioTemporal;
	}
	/**
	 * @param directorioTemporal The directorioTemporal to set.
	 */
	public void setDirectorioTemporal(String directorioTemporal) {
		this.directorioTemporal = directorioTemporal;
	}
	/**
	 * @return Returns the extensionArchivo.
	 */
	public String getExtensionArchivo() {
		return extensionArchivo;
	}
	/**
	 * @param extensionArchivo The extensionArchivo to set.
	 */
	public void setExtensionArchivo(String extensionArchivo) {
		this.extensionArchivo = extensionArchivo;
	}
	/**
	 * @return Returns the nombreArchivo.
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	/**
	 * @param nombreArchivo The nombreArchivo to set.
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	/**
	 * @return the flagHabilitarCadMasivo
	 */
	public boolean isFlagHabilitarCadMasivo() {
		return flagHabilitarCadMasivo;
	}

	/**
	 * @param flagHabilitarCadMasivo the flagHabilitarCadMasivo to set
	 */
	public void setFlagHabilitarCadMasivo(boolean flagHabilitarCadMasivo) {
		this.flagHabilitarCadMasivo = flagHabilitarCadMasivo;
	}

	public Date getFechaVencimientoDate() {
		return fechaVencimientoDate;
	}

	public void setFechaVencimientoDate(Date fechaVencimientoDate) {
		this.fechaVencimientoDate = fechaVencimientoDate;
	}

	public UploadedFile getArchivo() {
		return archivo;
	}

	public void setArchivo(UploadedFile archivo) {
		this.archivo = archivo;
	}

}

