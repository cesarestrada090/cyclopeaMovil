package biz.belcorp.ssicc.web.spusicc.lideres.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.lideres.form.ProcesoLIDCargaPedidosObjetivosForm;

public class ProcesoLIDCargaPedidosObjetivosAction extends
		BaseProcesoAbstractAction {

	private static final long serialVersionUID = 7453754370038131136L;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseProcesoAbstractAction#setViewAttributes(javax.servlet.http.HttpServletRequest, org.apache.struts.action.ActionForm)
	 */
	protected void setViewAttributes(HttpServletRequest request, ActionForm form) {

		HttpSession session = request.getSession();		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario= this.mPantallaPrincipalBean.getCurrentUser();
		ProcesoLIDCargaPedidosObjetivosForm f = (ProcesoLIDCargaPedidosObjetivosForm) this.formProceso;
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		//setamos periodo actual
		f.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(pais.getCodigo(),Constants.CODIGO_CANAL_DEFAULT));
		//cargamos combos de marca , canal y region
		session.setAttribute(Constants.SICC_MARCA_LIST, service.getMarcas());		
		session.setAttribute(Constants.SICC_CANAL_LIST, service
				.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO()));
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		session.setAttribute(
				Constants.SICC_REGION_LIST,
				reporteService.getListaGenerico("getRegionesByPais",
						criteriaOperacion));
		//seteamos la ruta temporal dodne guardar el excel
		ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		f.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(pais.getCodigo()));
		f.setCodigoPais(pais.getCodigo());
		///limpiamos flag indicador carga a O , para que se permita la validacion
		session.setAttribute("indCargar", Constants.NUMERO_CERO);
	}

	/**
	 * Carga , valida el excel
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
//	public ActionForward cargar(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		if (log.isDebugEnabled()) {
//			log.debug("Entering 'validar' method");
//		}
//
//		HttpSession sesion = request.getSession();
//		Usuario usuario= this.mPantallaPrincipalBean.getCurrentUser();
//		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
//
//		ProcesoLIDCargaPedidosObjetivosForm f = (ProcesoLIDCargaPedidosObjetivosForm) this.formProceso;			
//		// obtenemos el servicio 
//		ProcesoLIDGenerarNumeroPedidosSeccionPeriodoService service =  
//			(ProcesoLIDGenerarNumeroPedidosSeccionPeriodoService)getBean("spusicc.procesoLIDGenerarNumeroPedidosSeccionPeriodoService");
//		// Cargamos el archivo de la maquina del cliente al servidor
//			uploadArchivo(form);
//		String extensionArchivo = obtenerExtensionArchivo(f.getNombreArchivo());
//		f.setExtensionArchivo(extensionArchivo);
//	
//		Map criteria = new HashMap();
//		criteria.put("directorioTemporal",f.getDirectorioTemporal());
//		criteria.put("nombreArchivo",f.getNombreArchivo());
//		criteria.put("codigoPais", pais.getCodigo());
//		criteria.put("codigoMarca", f.getCodigoMarca());
//		criteria.put("codigoCanal", f.getCodigoCanal());
//		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
//		criteria.put("codigoRegion", f.getCodigoRegion());
//		criteria.put("usuario", usuario);
//		criteria.put("mensajeError", null);
//		//validamos el archivo excel
//		boolean isValido =service.validarArchivoExcel(criteria);
//		if(isValido){
//			try{
//				
//				log.debug("ejecutar validacion si es ok se actualiza tabla, sino se envia mensaje");				
//				service.executeCargaArchivoExcel(criteria);
//				String mensajeError = (String)criteria.get("mensajeError");
//				if(StringUtils.isNotEmpty(mensajeError))
//					throw new Exception(mensajeError); 
//					
//			}catch(Exception e){
//				log.debug("error " + e.getMessage());
//				ActionMessages messages = new ActionMessages();		
//				messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
//						"procesoLIDCargaPedidosObjetivosForm.cabecera.error",new String(e.getMessage())));
//				saveErrors(request, messages);
//				borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
//				return mapping.findForward(getViewForward());
//			}
//			sesion.setAttribute("indCargar", Constants.NUMERO_CERO);
//			ActionMessages messages = new ActionMessages();		
//			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
//					"procesoLIDCargaPedidosObjetivosForm.proceso.ok"));
//			saveMessages(request, messages);
//		}else{
//			ActionMessages messages = new ActionMessages();		
//			messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
//					"procesoLIDCargaPedidosObjetivosForm.archivo.novalido"));
//			saveErrors(request, messages);
//		}
//		borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
//		return mapping.findForward(getViewForward());
//	}

	
	
	/**
	 * carga el archivo al temporal
	 * @param form
	 * @throws Exception
	 */
	private void uploadArchivo(ActionForm form) throws Exception {
		ProcesoLIDCargaPedidosObjetivosForm f = (ProcesoLIDCargaPedidosObjetivosForm) this.formProceso;

		// recuperamos el fichero
		FormFile archivo = f.getArchivo();
		f.setNombreArchivo(archivo.getFileName());
		log.debug("Archivo Upload: " + f.getArchivo());
		log.debug("Nombre Archivo Upload: " + f.getNombreArchivo());
		// leyemos el stream de entrada
		InputStream is = archivo.getInputStream();
		// abrimos el stream de escritura, ubicacion al cual se grabara el
		// archivo del cliente
		log.debug("f.getDirectorioTemporal() " + f.getDirectorioTemporal());
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

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseProcesoAbstractAction#executeProcess(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, java.util.Map)
	 */
	protected Map executeProcess(ActionForm form, HttpServletRequest request,
			Map params) throws Exception {
		return null;
	}
	
	/**
	 * Valida que las regiones selecionadas no hayan cerrado en el periodo sellecionado
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
//	public ActionForward validarRegiones(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		if (log.isDebugEnabled()) {
//			log.debug("Entering 'validarRegiones' method");
//		}
//		HttpSession session = request.getSession();	
//		ProcesoLIDCargaPedidosObjetivosForm f = (ProcesoLIDCargaPedidosObjetivosForm) this.formProceso;
//		Map criteria = new HashMap(); 
//		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
//		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
//		Integer oidPeriodo = new Integer(reporteService.getOidString("getOidPeriodoByCodigoPeriodo", criteria));
//		Map params = new HashMap();
//	    params.put("oidPeriodo",oidPeriodo);
//	    params.put("codigoRegion", f.getCodigoRegion());
//	    params.put("tipoCierre", Constants.CODIGO_TIPO_CIERRE_REGION);
//		
//	    ProcesoLIDGenerarNumeroPedidosSeccionPeriodoService service =  
//			(ProcesoLIDGenerarNumeroPedidosSeccionPeriodoService)getBean("spusicc.procesoLIDGenerarNumeroPedidosSeccionPeriodoService");
//		
//	    List listRegiones =service.getRegionesCerradas(params);
//	    if(listRegiones.size() > 0){
//	    	String regiones = getRegiones(listRegiones);
//	    	ActionMessages messages = new ActionMessages();		
//			messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
//					"procesoLIDCargaPedidosObjetivosForm.regiones.cerradas",regiones));
//			saveErrors(request, messages);
//			session.setAttribute("indCargar", Constants.NUMERO_CERO);//SE DESHABILITA LA CARGA DEL EXCEL
//	    }else
//	    	session.setAttribute("indCargar", Constants.NUMERO_UNO);//SE HABILITA LA CARGA DEL EXCEL
//	    
//		return mapping.findForward(getViewForward());
//	}
//	

	/**
	 * Devulve un String concatenando todas las regiones que se encuentren en la lista
	 * @param listRegiones
	 * @return
	 */
	private String getRegiones(List listRegiones) {
		String cadena="";
		Iterator it = listRegiones.iterator();
		int i=0;
		while(it.hasNext()){
			String s = (String)it.next();
			
			if(i==listRegiones.size()-1)
				cadena += s ;
			else
				cadena += s + ", ";
			i++;
			
		}
		return cadena;
	}

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
