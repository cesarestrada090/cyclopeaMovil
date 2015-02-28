package biz.belcorp.ssicc.web.spusicc.app.form;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * 
 * <p>
 * <a href="ProcesoAPPCargarSecuenciaZonaForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 */
public class ProcesoAPPCargarSecuenciaZonaForm extends BaseProcesoForm {

	private static final long serialVersionUID = 1L;
	
	private String codigoPais;	
	private UploadedFile archivo;	        //objeto que se utilizara para el upload del Archivo
	private boolean flagMostrarErrores;

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
	 * @return
	 */
	public boolean isFlagMostrarErrores() {
		return flagMostrarErrores;
	}

	/**
	 * @param flagMostrarErrores
	 */
	public void setFlagMostrarErrores(boolean flagMostrarErrores) {
		this.flagMostrarErrores = flagMostrarErrores;
	}

	public UploadedFile getArchivo() {
		return archivo;
	}

	public void setArchivo(UploadedFile archivo) {
		this.archivo = archivo;
	}

}
