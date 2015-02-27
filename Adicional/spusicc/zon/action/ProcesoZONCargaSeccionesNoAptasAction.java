package biz.belcorp.ssicc.web.spusicc.zon.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.FileUploadEvent;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCargaBonosService;
import biz.belcorp.ssicc.service.spusicc.zon.MantenimientoZONSeccionesNoAptasService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.comision.form.ProcesoCOMCargaBonosForm;
import biz.belcorp.ssicc.web.spusicc.zon.form.ProcesoZONCargaSeccionesNoAptasForm;

@ManagedBean  
@SessionScoped
public class ProcesoZONCargaSeccionesNoAptasAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = 1L;
	
	private String attachment = "";

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoZONCargaSeccionesNoAptasForm form = new ProcesoZONCargaSeccionesNoAptasForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'executeProcess' method");
		}
		ProcesoZONCargaSeccionesNoAptasForm f = (ProcesoZONCargaSeccionesNoAptasForm) this.formProceso;
		
		MantenimientoZONSeccionesNoAptasService service = (MantenimientoZONSeccionesNoAptasService) getBean("spusicc.mantenimientoZONSeccionesNoAptasService");
		
		// Cargamos el archivo de la maquina del cliente al servidor
		this.uploadArchivo();
		//
		
		//Procesamos el archivo
		try{
			service.executeProcesarArchivoExcel(f.getDirectorioTemporal(), f.getNombreArchivo(), this.getmPantallaPrincipalBean().getCurrentUser());
		}catch(Exception e){
			throw e;
		}finally{
			borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
		}				
		this.mostrarBotonExecute = false;
		this.setAttachment("");
				
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		ProcesoZONCargaSeccionesNoAptasForm f = (ProcesoZONCargaSeccionesNoAptasForm) this.formProceso;
		ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		f.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(pais.getCodigo()));
		this.mostrarBotonExecute = false;
	}
	
	/**
	 * @param event
	 */
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
		}
		this.mostrarBotonExecute = true;
	}
	
	/**
	 * @throws IOException
	 */
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
	
	private void borrarFichero(String path, String nombreArchivo) {
		try {
			File file = new File(path, nombreArchivo);
			file.delete();
			log.debug("Se elimino el archivo");
		}catch(Exception ex) {
			log.debug("No se pudo eliminar el archivo");
		}
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

}