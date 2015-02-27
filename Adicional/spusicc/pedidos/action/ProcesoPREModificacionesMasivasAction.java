package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.struts.action.ActionMessages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPREModificacionesMasivasService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoPREModificacionesMasivasForm;

@ManagedBean  
@SessionScoped
public class ProcesoPREModificacionesMasivasAction extends BaseProcesoAbstractAction {

	
	private static final long serialVersionUID = 1L;
	private List cargaModificacionesMasivasArchivolist = new ArrayList();
	private DataTableModel dataTableResultado = new DataTableModel();
	private String attachment = "";
	private String viewValida = "";

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("devuelveFormProceso");
		}
		ProcesoPREModificacionesMasivasForm form = new ProcesoPREModificacionesMasivasForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("executeProcess");
		}
        Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
    	
    	ProcesoPREModificacionesMasivasForm f = (ProcesoPREModificacionesMasivasForm)formProceso;
    	ProcesoPREModificacionesMasivasService service = (ProcesoPREModificacionesMasivasService)getBean("spusicc.procesoPREModificacionesMasivasService");
        
        params.put("codigoPais", f.getCodigoPais());
        params.put("codigoUsuario", usuario.getLogin());
        
        service.executeActualizarCargaModificacionesMasivas(params);
        
        f.setFlagBotonValidar(true);
        f.setFlagBotonActualizar(false);
        return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		
        ProcesoPREModificacionesMasivasForm f = (ProcesoPREModificacionesMasivasForm)this.formProceso;
        Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
        ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService)getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");        
        
        f.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(pais.getCodigo()));
        f.setFlagBotonValidar(false);
        f.setFlagBotonActualizar(false);
        f.setFlagMostrarErrores(false);
        
//        sesion.removeAttribute("viewValida");
//        sesion.removeAttribute("cargaModificacionesMasivasArchivolist");
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		if(log.isDebugEnabled()){
			log.debug("handleFileUpload");
		}
		try {
			cargar();
		} catch (Exception e) {
			e.printStackTrace();
			this.addError("Error:", e.getMessage());
		}
	}


	public void cargar() throws Exception{
        if(log.isDebugEnabled()){
            log.debug("Entering 'cargar' method");
        }

        Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
        ActionMessages messages = new ActionMessages();
        
        ProcesoPREModificacionesMasivasForm f = (ProcesoPREModificacionesMasivasForm)this.formProceso;
		ProcesoPREModificacionesMasivasService service = (ProcesoPREModificacionesMasivasService)getBean("spusicc.procesoPREModificacionesMasivasService");

		f.setNumRegistrosError("N");
        f.setNumRegistrosValido("N");
		
		UploadedFile archivo = f.getArchivo();
		InputStream is = archivo.getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		List lineas = new ArrayList();
		String linea = "";
		String strCodTipMod = "";
		String strOidDetOfe = "";
		String strValor = "";

		while (true) {
			Map criteria = new HashMap();
			criteria.put("codigoUsuario", usuario.getLogin());
			linea = br.readLine();

			if (linea == null)
				break;
			
			StringTokenizer st = new StringTokenizer(linea, ",");
			
			try {
				strCodTipMod = st.nextToken();
				criteria.put("codigoTipoModi", strCodTipMod);
			} catch (Exception e) {
				strCodTipMod = "";
			}

			try {
				strOidDetOfe = st.nextToken();
				criteria.put("oidDetalleOferta", strOidDetOfe);
			} catch (Exception e) {
				strOidDetOfe = "";
			}
			
			try {
				strValor = st.nextToken();
				criteria.put("valor", strValor);
			} catch (Exception e) {
				strValor = "";
			}

			// Todos los campos son obligatorios, si falta alguno, no se considera el registro
			if(!strCodTipMod.equals("") && !strOidDetOfe.equals("") && !strValor.equals(""))
				lineas.add(criteria);
		}

		//Borramos e insertamos en la tabla temporal
		service.deleteTablaModificacionesMasivasTemporal();
		service.insertModificacionesMasivasTemporal(lineas);

		Map criteria = new HashMap();
		criteria.put("codigoPais",f.getCodigoPais());
		
		String respuesta = (String) criteria.get("valError");
		//Usuario usuario = getUsuario(request.getSession());

		if (Constants.NUMERO_CERO.equals(respuesta)){
			this.addInfo("Info:", this.getResourceMessage("procesoAPPCargarSecuenciaZonaForm.carga.correcta"));
		}

		f.setNumRegistros(String.valueOf(lineas.size()));
		this.setCargaModificacionesMasivasArchivolist(new ArrayList());
		f.setNumRegistrosError("N");
		f.setNombreArchivo(archivo.getFileName());
		
		borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());

		this.setViewValida("1");
		f.setFlagBotonValidar(true);
        f.setFlagBotonActualizar(false);
        
    }
	
	public void validar(ActionEvent e) throws Exception {
        if(log.isDebugEnabled()) {
            log.debug("Entering 'validar' method");
        }
        
        ProcesoPREModificacionesMasivasForm f = (ProcesoPREModificacionesMasivasForm)formProceso;
        ProcesoPREModificacionesMasivasService service = (ProcesoPREModificacionesMasivasService)getBean("spusicc.procesoPREModificacionesMasivasService");
        
        String codigoUsuario = this.getmPantallaPrincipalBean().getCurrentUser().getCodigo();
        
        Map params = new HashMap();
        params.put("codigoUsuario", codigoUsuario);
        
        List resultados = service.executeValidarCargaModificacionesMasivas(params);
        int totalErrores = resultados.size();
        int totalValidos = Integer.parseInt(f.getNumRegistros()) - totalErrores;
        
        f.setNumRegistrosError(String.valueOf(totalErrores));
        f.setNumRegistrosValido(String.valueOf(totalValidos));
        
        this.setCargaModificacionesMasivasArchivolist(resultados);
        
        f.setFlagBotonValidar(false);
        
        if(resultados.size() == 0){
        	f.setFlagBotonActualizar(true);
        }else{
        	f.setFlagBotonActualizar(false);
        }
        
    }
	
	private void uploadArchivo() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("uploadArchivo");
		}
    	ProcesoPREModificacionesMasivasForm f = (ProcesoPREModificacionesMasivasForm)this.formProceso;
        UploadedFile archivo = f.getArchivo();
        f.setNombreArchivo(archivo.getFileName());
        log.debug((new StringBuilder()).append("Nombre Archivo Upload: ").append(f.getNombreArchivo()).toString());
        
        InputStream is = archivo.getInputstream();
        FileOutputStream os = new FileOutputStream(new File(f.getDirectorioTemporal(), f.getNombreArchivo()));
        int bytesRead = 0;
        byte buffer[] = new byte[1024];
        
        while((bytesRead = is.read(buffer, 0, 1024)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        
        os.close();
        f.setArchivo(null);
    }
	
	public void actualizar(ActionEvent e){
		if(log.isDebugEnabled()){
			log.debug("actualizar");
		}
		//la logica de acuerdo a operacion en AJAX   - PENDIENTE
	}
	
	private String obtenerExtensionArchivo(String nombreArchivo) throws Exception {
		return nombreArchivo.substring(nombreArchivo.length() - 3);
	}

	private void borrarFichero(String path, String nombreArchivo) {
		try{
			File file = new File(path, nombreArchivo);
	        file.delete();
	        log.debug("Se eliminó el archivo");
		}catch(Exception ex) {
			log.debug((new StringBuilder()).append("No se pudo eliminar el archivo ").append(ex.getMessage()).toString());
		}
	}

	public List getCargaModificacionesMasivasArchivolist() {
		return cargaModificacionesMasivasArchivolist;
	}

	public void setCargaModificacionesMasivasArchivolist(
			List cargaModificacionesMasivasArchivolist) {
		this.cargaModificacionesMasivasArchivolist = cargaModificacionesMasivasArchivolist;
	}

	public String getViewValida() {
		return viewValida;
	}

	public void setViewValida(String viewValida) {
		this.viewValida = viewValida;
	}

	public DataTableModel getDataTableResultado() {
		return dataTableResultado;
	}

	public void setDataTableResultado(DataTableModel dataTableResultado) {
		this.dataTableResultado = dataTableResultado;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
}