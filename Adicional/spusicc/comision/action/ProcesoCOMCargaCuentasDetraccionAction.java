package biz.belcorp.ssicc.web.spusicc.comision.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.FileUploadEvent;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCargaCuentasDetraccionService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.comision.form.ProcesoCOMCargaCuentasDetraccionForm;

@ManagedBean  
@SessionScoped
public class ProcesoCOMCargaCuentasDetraccionAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = 1L;
	private String attachment = "";

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoCOMCargaCuentasDetraccionForm form = new ProcesoCOMCargaCuentasDetraccionForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)throws Exception {
		if(log.isDebugEnabled()){
			log.debug("executeProcess");
		}
		ProcesoCOMCargaCuentasDetraccionForm f = (ProcesoCOMCargaCuentasDetraccionForm)this.formProceso;
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		
		// obtenemos el servicio
		ProcesoCOMCargaCuentasDetraccionService service = (ProcesoCOMCargaCuentasDetraccionService)getBean("spusicc.procesoCOMCargaCuentasDetraccionService");

		// Cargamos el archivo de la maquina del cliente al servidor
		this.uploadArchivo();

		params.put("directorioTemporal", f.getDirectorioTemporal());
		params.put("nombreArchivo", f.getNombreArchivo());
		params.put("extensionArchivo", f.getExtensionArchivo());
		params.put("codigoUsuario",usuario.getLogin());  
		
		boolean isValido = service.validarArchivoExcel(params);
		if(isValido){
			service.validarCargaExcel(params);
			
			service.executeCargaArchivoExcel(params);
		} 
		else{
			String texto =this.getResourceMessage("procesoCOMCargaCuentasDetraccionForm.archivo.novalido");			
			params.put("error", texto);
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
		
		ProcesoCOMCargaCuentasDetraccionForm f = (ProcesoCOMCargaCuentasDetraccionForm)this.formProceso;
		ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		f.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo()));
		f.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		this.mostrarBotonExecute = false;
	}	
	
	public void handleFileUpload(FileUploadEvent event) {
		if(log.isDebugEnabled()){
			log.debug("handleFileUpload");
		}
		ProcesoCOMCargaCuentasDetraccionForm f = (ProcesoCOMCargaCuentasDetraccionForm)this.formProceso;
		if(event != null){
			f.setArchivo(event.getFile());
			f.setNombreArchivo(event.getFile().getFileName());
			this.setAttachment(event.getFile().getFileName());
		}
		this.mostrarBotonExecute = true;
	}
	
	/**
	 * carga el archivo al temporal
	 * @param form
	 * @throws Exception
	 */
	private void uploadArchivo() throws IOException {
		if(log.isDebugEnabled()){
			log.debug("uploadArchivo");			
		}
		ProcesoCOMCargaCuentasDetraccionForm f = (ProcesoCOMCargaCuentasDetraccionForm) this.formProceso;
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

	
	
	
	
	public DataTableModel getDatatableBusqueda() {
		return datatableBusqueda;
	}

	public void setDatatableBusqueda(DataTableModel datatableBusqueda) {
		this.datatableBusqueda = datatableBusqueda;
	}

		
	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

}