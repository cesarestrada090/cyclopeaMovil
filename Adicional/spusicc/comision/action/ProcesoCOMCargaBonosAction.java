package biz.belcorp.ssicc.web.spusicc.comision.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.FileUploadEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCargaBonosService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.comision.form.ProcesoCOMCargaBonosForm;

@ManagedBean  
@SessionScoped
public class ProcesoCOMCargaBonosAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = 1L;
	private String attachment = "";

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoCOMCargaBonosForm form = new ProcesoCOMCargaBonosForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)throws Exception {
		if(log.isDebugEnabled()){
			log.debug("executeProcess");
		}	
		this.mostrarBotonExecute = false;
		this.setAttachment("");
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}		
		ProcesoCOMCargaBonosForm f = (ProcesoCOMCargaBonosForm)this.formProceso;
		ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		f.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo()));
		f.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		f.setIndicadorContinuar(Constants.NUMERO_CERO);
		f.setIndicadorHabMensaje(Constants.NUMERO_CERO);
		this.mostrarBotonExecute = false;
	}	
		
	public void handleFileUpload(FileUploadEvent event) {
		if(log.isDebugEnabled()){
			log.debug("handleFileUpload");
		}
		ProcesoCOMCargaBonosForm f = (ProcesoCOMCargaBonosForm)this.formProceso;
		
		Usuario usuario=this.getmPantallaPrincipalBean().getCurrentUser();
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();

		// obtenemos el servicio 
		ProcesoCOMCargaBonosService service = (ProcesoCOMCargaBonosService) getBean("spusicc.procesoCOMCargaBonosService");
		
		if(event != null){
			// Cargamos el archivo de la maquina del cliente al servidor solo si no es reproceso
			f.setArchivo(event.getFile());
			f.setNombreArchivo(event.getFile().getFileName());
			
			this.setAttachment(event.getFile().getFileName());			
			String extensionArchivo;
			boolean isValido;			
			
			try {
				if(f.getIndicadorContinuar().equals(Constants.NUMERO_CERO)){
					uploadArchivo();
				}else{
					return;
				}
				extensionArchivo = obtenerExtensionArchivo(f.getNombreArchivo());			
				f.setExtensionArchivo(extensionArchivo);			
				Map criteria = new HashMap();
				criteria.put("directorioTemporal",f.getDirectorioTemporal());
				criteria.put("nombreArchivo",f.getNombreArchivo());
				criteria.put("codigoPais", pais.getCodigo());
				criteria.put("usuario", usuario);
				criteria.put("indicadorContinuar", f.getIndicadorContinuar());
				//validamos el archivo excel
				isValido = service.validarArchivoExcel(criteria);
				if(isValido){					
					if(f.getIndicadorContinuar().equals(Constants.NUMERO_CERO)){//inicalmente es cero por que pasa hacer validado
						//se setara luego de mandarle el mensaje de coninuar , es seteado x el jsp
						boolean valida=service.validarCargaExcel(criteria);//
						if(valida){//mandaremos a preguntar si se continua o no por existir detalle con el concepto
							f.setIndicadorHabMensaje(Constants.NUMERO_UNO);//sirve para mandar el mensaje a preguntar
							f.setCodigoConcepto((String)criteria.get("codigoConcepto"));//el codigo de concept q existe
							return;
						}
					}//si no existe detalle pa concepto se procede con la carga	sea reenvi o no
					log.debug("deseo continuar se procede a cargar el archivo del temporal y ejecutar la carga");				
					service.executeCargaArchivoExcel(criteria);					
					this.addInfo("Info:", this.getResourceMessage("procesoCOMCargaBonosForm.proceso.ok"));
				}else{					
					this.addInfo("Info:", this.getResourceMessage("procesoCOMCargaBonosForm.archivo.novalido"));					
				}
				borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
			} catch (Exception e) {
				log.debug("error " + e.getMessage());
				this.addError("Error:", this.getResourceMessage("procesoCOMCargaBonosForm.cabecera.error"));						
				borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
				return;
			} finally{
				log.debug("finally limpiamos atributos");				 
				f.setIndicadorContinuar(Constants.NUMERO_CERO);
				//f.setIndicadorHabMensaje(Constants.NUMERO_CERO);
				//f.setCodigoConcepto("");
				
			}
		}	
		this.mostrarBotonExecute = false;
	}
	
	private void uploadArchivo() throws IOException {
		if(log.isDebugEnabled()){
			log.debug("uploadArchivo");			
		}
		ProcesoCOMCargaBonosForm f = (ProcesoCOMCargaBonosForm) this.formProceso;
		// leyemos el stream de entrada
		InputStream is = f.getArchivo().getInputstream();
		// archivo del cliente
		FileOutputStream os = new FileOutputStream(new File(f.getDirectorioTemporal(), 
								f.getNombreArchivo()));
		// grabamos cada 1024 bytes
		int bytesRead = 0;
		byte[] buffer = new byte[1024];
		while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();
		f.setArchivo(null);		
	}
	
	
	
	/**
	 * obtiene la extension del archivo
	 * @param nombreArchivo
	 * @return
	 * @throws Exception
	 */
	private String obtenerExtensionArchivo(String nombreArchivo)
			throws Exception {
		return nombreArchivo.substring(nombreArchivo.length() - 3);
	}

	
	/**
	 * elimina el fichero
	 * @param path
	 * @param nombreArchivo
	 */
	private void borrarFichero(String path, String nombreArchivo) {
		try {
			File file = new File(path, nombreArchivo);
			file.delete();
			log.debug("Se elimino el archivo");
		}	
		catch(Exception ex) {
			log.debug("No se pudo eliminar el archivo "+ex.getMessage());
		}
	}
	
	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public DataTableModel getDatatableBusqueda() {
		return datatableBusqueda;
	}

	public void setDatatableBusqueda(DataTableModel datatableBusqueda) {
		this.datatableBusqueda = datatableBusqueda;
	}
}