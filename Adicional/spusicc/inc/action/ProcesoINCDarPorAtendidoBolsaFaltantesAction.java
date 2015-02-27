package biz.belcorp.ssicc.web.spusicc.inc.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.inc.ProcesoINCDarPorAtendidoBolsaFaltantesService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.inc.form.ProcesoINCDarPorAtendidoBolsaFaltantesForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"unchecked", "rawtypes"})
public class ProcesoINCDarPorAtendidoBolsaFaltantesAction extends BaseProcesoAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5144348369864189812L;
	
	private List incentivosConcursosHabilitadosList;
	private LabelValue[] incPeriodosNoAtendidosList = {};
	private LabelValue[] incProductosNoAtendidosList = {};
	private boolean indicadorConsultorasBool;
	private boolean viewValida;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoINCDarPorAtendidoBolsaFaltantesForm formProceso = new ProcesoINCDarPorAtendidoBolsaFaltantesForm();
		return formProceso;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) throws Exception 
	{
		ProcesoINCDarPorAtendidoBolsaFaltantesService service = (ProcesoINCDarPorAtendidoBolsaFaltantesService) getBean("spusicc.procesoINCDarPorAtendidoBolsaFaltantesService");
		service.executeActualizarBolsaFaltantes(params);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception
	{	
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		ProcesoINCDarPorAtendidoBolsaFaltantesForm f = (ProcesoINCDarPorAtendidoBolsaFaltantesForm) this.formProceso;
			//seteamos la ruta temporal dodne guardar el excel
		ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) 
											getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		ProcesoINCDarPorAtendidoBolsaFaltantesService service = (ProcesoINCDarPorAtendidoBolsaFaltantesService) 
											getBean("spusicc.procesoINCDarPorAtendidoBolsaFaltantesService");

		f.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(pais.getCodigo()));
		//cargando en session la lista de concursos habilitados
		this.incentivosConcursosHabilitadosList = service.getListConcursoVigentes();

		//limpiando el flag de validacion de archivo
//		sesion.removeAttribute("viewValida");
//		sesion.removeAttribute(Constants.INC_ARCHIVO_LIST);
		
		f.setIndicadorConsultoras(Constants.NUMERO_CERO);
		f.setOidConcurso(null);
		f.setNumeroLote(null);
		
		recargarCombos(true);		
		this.indicadorConsultorasBool = false;
		this.viewValida = false;
	}
	
	/**
	 * obtiene el numero del concurso
	 * @param oidConcurso
	 * @return
	 */
	private String getNumeroConcurso(String oidConcurso) 
	{
		String numeroConcurso="";
		List listConcursos = this.incentivosConcursosHabilitadosList;
		Iterator it = listConcursos.iterator();
		while (it.hasNext()){
			Base concurso = (Base)it.next();
			if(oidConcurso.equals(concurso.getCodigo())){				
				numeroConcurso = (StringUtils.split(concurso.getDescripcion(),"-")[0]).trim();
				break;
			}			
		}
		return numeroConcurso;
	}

	/**
	 * carga el archivo
	 * @param 
	 * @throws Exception
	 */
	private void uploadArchivo(FileUploadEvent event ) throws Exception 
	{
		ProcesoINCDarPorAtendidoBolsaFaltantesForm f = (ProcesoINCDarPorAtendidoBolsaFaltantesForm) this.formProceso;

		// recuperamos el fichero
		UploadedFile archivo = event.getFile();
		f.setNombreArchivo(archivo.getFileName());
		log.debug("Nombre Archivo Upload: " + f.getNombreArchivo());
		// leyemos el stream de entrada
		InputStream is = archivo.getInputstream();
		// abrimos el stream de escritura, ubicacion al cual se grabara el
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
	private String obtenerExtensionArchivo(String nombreArchivo) throws Exception 
	{
		return nombreArchivo.substring(nombreArchivo.length() - 3);
	}
	
	/**
	 * elimina el fichero del temporal
	 * @param path
	 * @param nombreArchivo
	 */
	private void borrarFichero(String path, String nombreArchivo) 
	{
		try {
			File file = new File(path, nombreArchivo);
			file.delete();
			log.debug("Se elimino el archivo");
		}	
		catch(Exception ex) {
			log.debug("No se pudo eliminar el archivo "+ex.getMessage());
		}
	}
	
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params)throws Exception 
	{		
		ProcesoINCDarPorAtendidoBolsaFaltantesForm f = (ProcesoINCDarPorAtendidoBolsaFaltantesForm) this.formProceso;
		params.put("numeroConcurso",getNumeroConcurso(f.getOidConcurso()));
		
		if(f.getOidProducto().equalsIgnoreCase(Constants.OPCION_TODOS))
			params.put("oidProducto", "");

		if(f.getOidPeriodo().equalsIgnoreCase(Constants.OPCION_TODOS))
			params.put("oidPeriodo", "");
		
		params = super.prepareParamsBeforeExecute(params);
		return params;
	}
	
	@Override
	public void afterExecuteProcess(BaseProcesoForm form) throws Exception 
	{
		this.addInfo("procesoINCDarPorAtendidoBolsaFaltantesForm.proceso.ok", "");

		ProcesoINCDarPorAtendidoBolsaFaltantesForm f = (ProcesoINCDarPorAtendidoBolsaFaltantesForm)form;

		f.setIndicadorConsultoras(Constants.NUMERO_CERO);
		recargarCombos(true);
		this.viewValida = false;
	}
	
	public void validar(FileUploadEvent event) throws Exception
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validar' method");
		}

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		ProcesoINCDarPorAtendidoBolsaFaltantesForm f = (ProcesoINCDarPorAtendidoBolsaFaltantesForm) this.formProceso;
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
				
		//obtenemos el periodo desde la fecha de proceso 
		InterfazSiCCService serviceSiCC = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		String codigoPeriodo =serviceSiCC.getPeriodoDefaultByPaisCanal(pais.getCodigo(),Constants.CODIGO_CANAL_DEFAULT);
				
		// obtenemos el servicio 
		ProcesoINCDarPorAtendidoBolsaFaltantesService service = (ProcesoINCDarPorAtendidoBolsaFaltantesService) getBean("spusicc.procesoINCDarPorAtendidoBolsaFaltantesService");
		// Cargamos el archivo de la maquina del cliente al servidor
		uploadArchivo(event);
		// Leemos la primera linea del archivo, para recuperar los campos filtro
		// que nos servira para determinar que campo corresponde a que nivel de
		// 
		String extensionArchivo = obtenerExtensionArchivo(f.getNombreArchivo());
		f.setExtensionArchivo(extensionArchivo);
	
		Map criteria = new HashMap();
		criteria.put("directorioTemporal",f.getDirectorioTemporal());
		criteria.put("nombreArchivo",f.getNombreArchivo());
		criteria.put("numeroConcurso",getNumeroConcurso(f.getOidConcurso()));
		criteria.put("oidPeriodo",f.getOidPeriodo());
		criteria.put("oidProducto",f.getOidProducto());
		criteria.put("observaciones",f.getObservaciones());
		criteria.put("usuario",usuario);
		criteria.put("codigoPais", pais.getCodigo());
		String oidPais= clienteService.getOidPais(criteria);
		criteria.put("oidPais",oidPais );

		//validamos el archivo excel y en criteria mandamos que estructura es sin period o con periodo
		boolean isValido = true; //service.validarArchivoExcel(criteria);
		//cargamos toda los registros del excel con la sgte informacion y simultaneamente cargara la 
		//informcion en la taba de puntaje
		//num fila codconsultora mensaje error num errores , este campo sera llenado y acualizado en el ultimo registro
		List listaArchivo =null;
		if(isValido){	
		listaArchivo = service.cargarArchivoExcel(criteria);				
		f.setNumRegistros(String.valueOf(listaArchivo.size()));
		Map map =(Map) listaArchivo.get(listaArchivo.size()-1);
		f.setNumRegistrosError(String.valueOf(map.get("numErrores")));
		f.setNumeroLote(String.valueOf(map.get("numeroLote")));
		f.setNumRegistrosValido(String.valueOf(listaArchivo.size() - Integer.parseInt(f.getNumRegistrosError())));
		//es valido si por lo menos hay un registro por cargar , es decir numero errors < num registros
		if(Integer.parseInt(f.getNumRegistrosError())< Integer.parseInt(f.getNumRegistros()))
			f.setIndicadorValido(Constants.NUMERO_UNO);
		else
			f.setIndicadorValido(Constants.NUMERO_CERO);		
		
		borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
		this.viewValida = true; //flag para mostrar el resultado de la validacion
//		sesion.setAttribute(Constants.INC_ARCHIVO_LIST,listaArchivo);
		}else{
			listaArchivo = new ArrayList();
//			MessageResources messageResources = getResources(request);
			
			f.setIndicadorValido(Constants.NUMERO_CERO);
			f.setNumRegistrosError(Constants.NUMERO_UNO);
			f.setNumRegistros(Constants.NUMERO_UNO);
			f.setNumRegistrosValido(Constants.NUMERO_CERO);
			
			this.viewValida = true;  //flag para mostrar el resultado de la validacion
			//mandamos infomacion del archivo
			Map mapFila = new HashMap();
			mapFila.put("numeroFila", Constants.NUMERO_UNO);
			mapFila.put("codigoCliente", "");			
			mapFila.put("mensajeError", this.getResourceMessage("procesoINCDarPorAtendidoBolsaFaltantesForm.error.noFormatoExcel"));				
			listaArchivo.add(mapFila);		
//			sesion.setAttribute(Constants.INC_ARCHIVO_LIST,listaArchivo);
		}
		
		//recarga de Combos de Periodo y Producto
		recargarCombos(false);
	}
	
	/**
	 * @param request
	 * @param form
	 * @param inicio
	 */
	private void recargarCombos(boolean inicio) 
	{
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ProcesoINCDarPorAtendidoBolsaFaltantesForm f = (ProcesoINCDarPorAtendidoBolsaFaltantesForm) this.formProceso;
		
		if(inicio) {			
			f.setOidConcurso("");
			f.setObservaciones("");
		} else {
			this.incPeriodosNoAtendidosList = aSvc.getPeriodosProductosNoAtendidos(null, f.getOidConcurso());
			this.incProductosNoAtendidosList = aSvc.getProductosNoAtendidos(null, f.getOidConcurso(), f.getOidPeriodo());
			
		}
	}
	
	public void loadPeriodos(ValueChangeEvent e)
	{
		String valor = e.getNewValue().toString();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		ProcesoINCDarPorAtendidoBolsaFaltantesForm f = (ProcesoINCDarPorAtendidoBolsaFaltantesForm) this.formProceso;
		if(valor != null)
			this.incPeriodosNoAtendidosList = ajax.getPeriodosProductosNoAtendidos(f.getCodigoPais(), valor);
		else this.incPeriodosNoAtendidosList = null;
	}
	
	public void loadProductos(ValueChangeEvent e)
	{
		String valor = e.getNewValue().toString();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		ProcesoINCDarPorAtendidoBolsaFaltantesForm f = (ProcesoINCDarPorAtendidoBolsaFaltantesForm) this.formProceso;
		
		if(valor != null)
			this.incProductosNoAtendidosList = ajax.getProductosNoAtendidos(f.getCodigoPais(), f.getOidConcurso(), valor);
		else this.incProductosNoAtendidosList = null;
	}
	
	public void mostrarCargaArchivo(ValueChangeEvent e)
	{
		String valor = e.getNewValue().toString();
		ProcesoINCDarPorAtendidoBolsaFaltantesForm f = (ProcesoINCDarPorAtendidoBolsaFaltantesForm) this.formProceso;
		
		if(valor.equalsIgnoreCase("true"))
		{
			this.indicadorConsultorasBool = true;
			this.viewValida = true;
			f.setIndicadorConsultoras(Constants.NUMERO_UNO);
		}else
		{
			this.indicadorConsultorasBool = false;
			this.viewValida = false;
			f.setIndicadorConsultoras(Constants.NUMERO_CERO);			
		}
	}
	
	public List getIncentivosConcursosHabilitadosList() {
		return incentivosConcursosHabilitadosList;
	}

	public void setIncentivosConcursosHabilitadosList(
			List incentivosConcursosHabilitadosList) {
		this.incentivosConcursosHabilitadosList = incentivosConcursosHabilitadosList;
	}

	public LabelValue[] getIncPeriodosNoAtendidosList() {
		return incPeriodosNoAtendidosList;
	}

	public void setIncPeriodosNoAtendidosList(LabelValue[] incPeriodosNoAtendidosList) {
		this.incPeriodosNoAtendidosList = incPeriodosNoAtendidosList;
	}

	public LabelValue[] getIncProductosNoAtendidosList() {
		return incProductosNoAtendidosList;
	}

	public void setIncProductosNoAtendidosList(LabelValue[] incProductosNoAtendidosList) {
		this.incProductosNoAtendidosList = incProductosNoAtendidosList;
	}

	public boolean isIndicadorConsultorasBool() {
		return indicadorConsultorasBool;
	}

	public void setIndicadorConsultorasBool(boolean indicadorConsultorasBool) {
		this.indicadorConsultorasBool = indicadorConsultorasBool;
	}

	public boolean isViewValida() {
		return viewValida;
	}

	public void setViewValida(boolean viewValida) {
		this.viewValida = viewValida;
	}

}
