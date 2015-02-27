/*
 * 
 */
package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCDigitacionCADService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCCargarCADMasivosService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.comision.form.ProcesoCOMCargaBonosForm;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.ProcesoCCCCargarCADMasivosForm;



@ManagedBean
@SessionScoped
public class ProcesoCCCCargarCADMasivosAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = 1L;
	
	private List siccSociedadList = new ArrayList();
	private List cccTiposCargoAbonosDirectosList = new ArrayList();
	private List cccErroresCargaMasivaList = new ArrayList();
	private String attachment;

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setViewAtributes");
		}	
		
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();			
		
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
                        
		siccSociedadList =	svc.getSociedadesByCodigoPais(pais.getCodigo());
								
		List listaTipoCAD = new ArrayList();
		
		MantenimientoCCCDigitacionCADService service = (MantenimientoCCCDigitacionCADService)getBean("spusicc.mantenimientoCCCDigitacionCADService");
		listaTipoCAD = service.getTiposCargoAbonoDirectos();						
		cccTiposCargoAbonosDirectosList = listaTipoCAD;
				
		ProcesoCCCCargarCADMasivosForm f = (ProcesoCCCCargarCADMasivosForm)this.formProceso;
		
		InterfazSiCCService serviceInt = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		String periodoActual = serviceInt.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoPeriodo(periodoActual);
		f.setFlagUpload(true);
		f.setFlagValidar(false);		
		f.setFlagProcesar(false);
		f.setFlagMostrarErrores(false);
		
		//realizamos la consulta para obtener 
		Map datos = new HashMap();
		datos.put("indicadorParametro", Constants.CCC_VALOR_PARAMETRO_HABILITAR_PROCESO_CAD_MASIVO);
		String indicador=service.getIndicadorParametro(datos);
		if(indicador!=null && StringUtils.isNotBlank(indicador)&& StringUtils.equals("S", indicador)){
			f.setFlagHabilitarCadMasivo(true);
		}else{
			f.setFlagHabilitarCadMasivo(false);
		}		
	}
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoCCCCargarCADMasivosForm form = new ProcesoCCCCargarCADMasivosForm();
		return form;
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		if(log.isDebugEnabled()){
			log.debug("handleFileUpload");
		}
		ProcesoCCCCargarCADMasivosForm f = (ProcesoCCCCargarCADMasivosForm)this.formProceso;
		
		try {
			Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
			f.setArchivo(event.getFile());
			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			
			ProcesoCCCCargarCADMasivosService service = (ProcesoCCCCargarCADMasivosService) getBean("spusicc.procesoCCCCargarCADMasivosService");
									
			f.setDirectorioTemporal(service.obtenerPathUpload(criteria));
			
			//Cargamos el archivo de la maquina del cliente al servidor
			if (log.isDebugEnabled()) {
				log.debug("JFA Cargando Archivo");
			}
			uploadArchivo();
			String extensionArchivo = obtenerExtensionArchivo(f.getNombreArchivo());
			f.setExtensionArchivo(extensionArchivo);
			//Borramos las tablas Temporales de Cargos y Abonos Masivos
			//service.deleteTablasCADMasivos();			
			f.setFlagValidar(true);
			f.setFlagUpload(false);
		} catch (IOException e) {
			this.addError("Error:", e.getMessage());
		}
	}

	private void uploadArchivo() throws IOException {
		if(log.isDebugEnabled()){
			log.debug("uploadArchivo");			
		}
		ProcesoCOMCargaBonosForm f = (ProcesoCOMCargaBonosForm) this.formProceso;
		// leyemos el stream de entrada
		InputStream is = f.getArchivo().getInputstream();
		// archivo del cliente
		FileOutputStream os = new FileOutputStream(new File(f.getDirectorioTemporal(), f.getNombreArchivo()));
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
	 * Metodo que obtiene la extension del archivo
	 * @param nombreArchivo
	 * @return 
	 */
	private String obtenerExtensionArchivo(String nombreArchivo){
		return nombreArchivo.substring(nombreArchivo.length() - 3);
	}


	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		return null;
	}
	
	
	public void validar(){
		if(log.isDebugEnabled()){
			log.debug("validar");
		}
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ProcesoCCCCargarCADMasivosForm f = (ProcesoCCCCargarCADMasivosForm) formProceso;
		
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();				
		String strMessage = null;
		try {

			File file = new File(f.getDirectorioTemporal(), f.getNombreArchivo());
			
			if (file.exists()){
				Map criteria = new HashMap();
				criteria.put("codigoPais", pais.getCodigo());
					
				Map datos = new HashMap();
				datos.put("directorioTemporal", f.getDirectorioTemporal());
				datos.put("nombreArchivo", f.getNombreArchivo());
				datos.put("extensionArchivo", f.getExtensionArchivo());
				datos.put("tipoCarga", f.getTipoCarga());
				datos.put("codigoPais", pais.getCodigo());
				datos.put("oidCodPais", reporteService.getOidString("getOidPaisByCodigoPais", criteria));			
				
				Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();				
				datos.put("codigoUsuario", usuario.getLogin());			
				
				// Obteniendo el Numero de Lote												
				ConsultaCCCGenericoService serviceGenericoCCC = (ConsultaCCCGenericoService)getBean("spusicc.consultaCCCGenericoService");
												
				serviceGenericoCCC.getNumeroLote(datos);	
				
				f.setNumeroLote(datos.get("numeroLote").toString());
				
				if (log.isDebugEnabled()) {
					log.debug("JFA : Parametros " + datos.toString());	
				}						
																																												
				ProcesoCCCCargarCADMasivosService service = (ProcesoCCCCargarCADMasivosService) getBean("spusicc.procesoCCCCargarCADMasivosService");
				
				if (log.isDebugEnabled()) {
					log.debug("JFA Llamando deleteTablasCargaCargosAbonosMasivos");
				}
				service.deleteTablasCargaCargosAbonosMasivos(datos);
								
				if (log.isDebugEnabled()) {
					log.debug("JFA Llamando executeValidarCargosAbonosMasivos");
				}
				service.executeValidarCargosAbonosMasivos(datos);
												
				borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
				
				String error = (String) datos.get("error");				
				
				if (log.isDebugEnabled()) {
					log.debug("JFA Validando Errores");
				}
				
				if (error.equals("0")){
					strMessage = "procesoCCCCargarCADMasivosUpload.msg.CargaDatos.ok";
					f.setCantidadRegistrosCargados((String) datos.get("cantidadRegistrosCargados"));
					f.setImporteRegistrosCargados((String) datos.get("importeRegistrosCargados"));
					f.setFlagValidar(false);
					f.setFlagProcesar(true);
					this.addInfo("Info:", this.getResourceMessage(strMessage));
				}else{		
					f.setFlagMostrarErrores(true);
					service.deleteTablasCargaCargosAbonosMasivos(datos);
					List list = service.getErroresCargaCargosAbonosMasivos(datos);
					cccErroresCargaMasivaList = list;
					strMessage = "procesoCCCCargarCADMasivosUpload.msg.CargaDatos.error";
					this.addInfo("Error:", this.getResourceMessage(strMessage));
				}
			}
		}catch (Exception ex) {			
			strMessage = ex.getMessage();
			if (StringUtils.isBlank(strMessage))
				strMessage = ex.getLocalizedMessage();
			this.addError("Error:", this.getResourceMessage(strMessage));
		}		
	}
	
	
	/**
	 *Metodo que borra el archivo
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
			log.debug("No se pudo eliminar el archivo");
		}
	}	
	
	/* GET - SET */
	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}

	public List getCccTiposCargoAbonosDirectosList() {
		return cccTiposCargoAbonosDirectosList;
	}

	public void setCccTiposCargoAbonosDirectosList(
			List cccTiposCargoAbonosDirectosList) {
		this.cccTiposCargoAbonosDirectosList = cccTiposCargoAbonosDirectosList;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public List getCccErroresCargaMasivaList() {
		return cccErroresCargaMasivaList;
	}

	public void setCccErroresCargaMasivaList(List cccErroresCargaMasivaList) {
		this.cccErroresCargaMasivaList = cccErroresCargaMasivaList;
	}	
}