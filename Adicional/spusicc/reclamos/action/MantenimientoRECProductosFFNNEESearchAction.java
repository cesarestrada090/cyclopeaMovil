package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.ProdutosFFNNEE;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECProductosFFNNEEService;
import biz.belcorp.ssicc.service.util.ExcelUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.cronograma.action.MantenimientoCRACalendarioCopiaPopupAction;
import biz.belcorp.ssicc.web.spusicc.cronograma.form.MantenimientoCRACalendarioForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.BusquedaRECProductoFFNNEESearchForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECProductosFFNNEESearchForm;
@ManagedBean
@SessionScoped
@SuppressWarnings("rawtypes")
public class MantenimientoRECProductosFFNNEESearchAction extends BaseMantenimientoSearchAbstractAction{

	/**
	 * 
	 */
	@SuppressWarnings("rawtypes")
	private static final long serialVersionUID = 3249245000633402274L;
	
	private String codigoPais;
	private List recCodigosSapValidosList;
	private List siccRegionList;
	private List recProductosFFNNEEArchivoList;
	private boolean flagBorrado;
	private String indicadorIngreso="";
	private Object codigoSAPSeleccionados;
	private String attachment="";
	private boolean mostrarPopUpProductos = false;
	private static final String POPPRODUCTOS = "POPPRODUCTOS";

	
	
	


	public boolean isMostrarPopUpProductos() {
		return mostrarPopUpProductos;
	}

	public void setMostrarPopUpProductos(boolean mostrarPopUpProductos) {
		this.mostrarPopUpProductos = mostrarPopUpProductos;
	}

	public static String getPopproductos() {
		return POPPRODUCTOS;
	}

	@ManagedProperty(value = "#{busquedaRECProductosFFNNEESearchAction}")
	private BusquedaRECProductosFFNNEESearchAction busquedaRECProductosFFNNEESearchAction;
	
	




	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setInvocarPopup(java.lang.String)
	 */

	

	

	public BusquedaRECProductosFFNNEESearchAction getBusquedaRECProductosFFNNEESearchAction() {
		return busquedaRECProductosFFNNEESearchAction;
	}

	public void setBusquedaRECProductosFFNNEESearchAction(
			BusquedaRECProductosFFNNEESearchAction busquedaRECProductosFFNNEESearchAction) {
		this.busquedaRECProductosFFNNEESearchAction = busquedaRECProductosFFNNEESearchAction;
	}

	@Override
	protected void setInvocarPopup(String accion) {
		this.mostrarProcesoBatch = false;
		BusquedaRECProductoFFNNEESearchForm f =(BusquedaRECProductoFFNNEESearchForm)formBusqueda;
		if (accion.equals(this.POPPRODUCTOS)) {			
			this.mostrarPopUpProductos=true;	
		}
	}






	
	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoRECProductosFFNNEESearchForm";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoRECProductosFFNNEESearchForm form = new MantenimientoRECProductosFFNNEESearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {

		MantenimientoRECProductosFFNNEESearchForm search = (MantenimientoRECProductosFFNNEESearchForm) this.formBusqueda;
		MantenimientoRECProductosFFNNEEService service = (MantenimientoRECProductosFFNNEEService)  getBean("spusicc.mantenimientoRECProductosFFNNEEService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		Map criteria = new HashMap();
		this.codigoPais = pais.getCodigo();
		
		if(search.getCodigoRegion().compareToIgnoreCase("TODOS")==0) {
			criteria.put("codigoRegion", "");
		}
		else
			criteria.put("codigoRegion", search.getCodigoRegion());
			
		
		List lista = (List)service.getProductosFFNNEEList(criteria);
		
		this.recCodigosSapValidosList = lista;
		
		return lista;
		
	
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {	
		
		   MantenimientoRECProductosFFNNEESearchForm search = (MantenimientoRECProductosFFNNEESearchForm) this.formBusqueda;
		   MantenimientoRECProductosFFNNEEService service = (MantenimientoRECProductosFFNNEEService)  getBean("spusicc.mantenimientoRECProductosFFNNEEService");
		   Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			
			try {
				if(search.getIndicadorIngreso().compareToIgnoreCase("DIGITACION")==0 || search.getIndicadorIngreso().compareToIgnoreCase("01")==0){
					Map criteria = new HashMap();
					this.codigoPais = pais.getCodigo();
					criteria.put("codigoPeriodoInicio", search.getCodigoPeriodoInicio());
					criteria.put("codigoPeriodoFin", search.getCodigoPeriodoFin());
					if(search.getCodigoRegion().compareToIgnoreCase("TODOS")==0) {
						criteria.put("codigoRegion", "");
					
					}
					else
						criteria.put("codigoRegion", search.getCodigoRegion());
					
					if(flagBorrado){
						service.deleteProductosFFNNEE(criteria);
						List detalle = recCodigosSapValidosList;
						List regionList = siccRegionList;
						
						service.insertProductosFFNNEE(pais.getCodigo(), search.getCodigoPeriodoInicio(),search.getCodigoPeriodoFin(),search.getCodigoRegion(),detalle,regionList);
						addInfo("GLOBAL_MESSAGE", getResourceMessage("mantenimientoRECProductosFFNNEEForm.delete"));
					}
					else{
						List detalle= recCodigosSapValidosList;
						List regionList= siccRegionList;
						service.insertProductosFFNNEE(pais.getCodigo(), search.getCodigoPeriodoInicio(), search.getCodigoPeriodoFin(), search.getCodigoRegion(), detalle,regionList);
						addInfo("Mensaje", getResourceMessage("mantenimientoRECProductosFFNNEEForm.insert"));
						
					}
					
					}
				else{
					
					List detalle= recProductosFFNNEEArchivoList;
					
					Map criteria = new HashMap();
					this.codigoPais = pais.getCodigo();
					criteria.put("codigoPeriodoInicio","");
					criteria.put("codigoPeriodoFin", "");
					criteria.put("codigoRegion", "");
					
					if(flagBorrado){
						service.deleteProductosFFNNEE(criteria);
						service.insertProductosFFNNEEArchivo(pais.getCodigo(), detalle);
						addInfo("Mensaje", getResourceMessage("mantenimientoRECProductosFFNNEEForm.delete"));
						
					}
					else{

						service.insertProductosFFNNEEArchivo(pais.getCodigo(), detalle);
						addInfo("Mensaje", getResourceMessage("mantenimientoRECProductosFFNNEEForm.insert"));
					}
				}
			
			search.setIndicadorIngreso(null);
			 
		} catch (Exception e) {
				throw new Exception(e.getMessage());
		}		
	   
		return true;
}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
			
		this.mostrarBotonConsultar = false;
		this.mostrarBotonModificar =false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonEliminar = false;
		this.attachment = "";
		this.mostrarBotonBuscar = false;
		
		try {
			
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			MantenimientoRECProductosFFNNEESearchForm search =  (MantenimientoRECProductosFFNNEESearchForm) this.formBusqueda;
			search.setCodigoPais(pais.getCodigo());
			Map criteriaOperacion = new HashMap();
			
			this.codigoPais = pais.getCodigo();
			this.recCodigosSapValidosList = new ArrayList();
			
			
			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
			this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
			search.setCodigoPeriodoFin("");
			search.setCodigoPeriodoInicio("");
			search.setCodigoSAP("");
			MantenimientoRECProductosFFNNEEService service = (MantenimientoRECProductosFFNNEEService)  getBean("spusicc.mantenimientoRECProductosFFNNEEService");
			search.setDirectorioTemporal(service.obtenerPathUpload(pais.getCodigo()));
			
		}
		catch (Exception e) {
			
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();	
			addInfo("Mensaje", getResourceMessage("errors.detail"));
		}
	
	}

	public void insertar()
	{

		if(this.validarRegistros()==1){
		MantenimientoRECProductosFFNNEESearchForm search = ( MantenimientoRECProductosFFNNEESearchForm) this.formBusqueda;
		Map criteriaBusqueda = new HashMap();
		
		criteriaBusqueda.put("codigoSAP", search.getCodigoSAP());
		criteriaBusqueda.put("codigoPais", search.getCodigoPais());
		
		MantenimientoRECProductosFFNNEEService service = (MantenimientoRECProductosFFNNEEService)  getBean("spusicc.mantenimientoRECProductosFFNNEEService");
		String oidProducto=service.getOidProducto(criteriaBusqueda);
		criteriaBusqueda.put("oidProducto",oidProducto);
		String desProducto=service.getDescripcionProducto(criteriaBusqueda);
		
		List detalle = recCodigosSapValidosList;
		
		Base base=new Base();
		base.setCodigo(search.getCodigoSAP());
		base.setDescripcion(desProducto);
		detalle.add(base);
		search.setCodigoSAP("");

		}
		
	}
	
	public void remove()
	{
		
	    Base seleccionado = (Base) codigoSAPSeleccionados;
	    
		MantenimientoRECProductosFFNNEESearchForm search = (MantenimientoRECProductosFFNNEESearchForm) this.formBusqueda;
	    List detalle = recCodigosSapValidosList;
	    String codigo = seleccionado.getCodigo();
	    String descripcion = seleccionado.getDescripcion();
	    
		for (int j = 0; j < recCodigosSapValidosList.size(); j++) {
			
			if(((Base)recCodigosSapValidosList.get(j)).getCodigo().equals(codigo)){
				recCodigosSapValidosList.remove(j);
			}
//			int posicion = Integer.parseInt(search.getSelectedItems()[j]);
//			detalle.remove(posicion-(j+1));
		}
		

	}
	
	/**Metodo que con el Flag "Borrar lo existente" borra los registros*/
	public void borrar() throws Exception
	{
		   
    	MantenimientoRECProductosFFNNEESearchForm f = (MantenimientoRECProductosFFNNEESearchForm) this.formBusqueda;
    	
    	
    	f.setIndicadorIngreso(indicadorIngreso);
    	if(StringUtils.isEmpty(f.getIndicadorIngreso()))
    		f.setIndicadorIngreso("00");
    	
    if(!f.getIndicadorIngreso().equals("01") && !f.getIndicadorIngreso().equals("02")){
		addInfo("", "Debe de escojer la carga por digitacion o por archivo");
		return;
    } 	
    if(f.getIndicadorIngreso().equals("01")){
		if(f.getCodigoPeriodoInicio().isEmpty()){
			addError("", "Debe de ingresar el Periodo Inicial");	
			return;
		}
		if(f.getCodigoPeriodoFin().isEmpty()){
			addError("", "Debe de ingresar el Periodo Final");
			return;	
		}
    }
      setDeleteAttributes();    
	}
	
	public void load(FileUploadEvent event) throws Exception
	{
		
	    MantenimientoRECProductosFFNNEESearchForm search = (MantenimientoRECProductosFFNNEESearchForm) this.formBusqueda;
	    search.setArchivo(event.getFile());
	    if(search.getArchivo()!=null){
	    uploadArchivo();
		// Leemos la primera linea del archivo, para recuperar los campos filtro
		// que nos
		// servira para determinar que campo corresponde a que nivel de
		// estructura geopolitica
		String extensionArchivo = obtenerExtensionArchivo(search.getNombreArchivo());
		search.setExtensionArchivo(extensionArchivo);
		List listCamposArchivo = null;
		this.setAttachment(event.getFile().getFileName());
		
	    listCamposArchivo = leerArchivoExcel(search.getDirectorioTemporal(), search.getNombreArchivo());
		this.recProductosFFNNEEArchivoList = listCamposArchivo;
		
		
	    }
	   

	}
	
	public void uploadArchivo() throws IOException
	{

		MantenimientoRECProductosFFNNEESearchForm f = (MantenimientoRECProductosFFNNEESearchForm) this.formBusqueda;

		// recuperamos el fichero
		UploadedFile archivo = f.getArchivo();

		f.setNombreArchivo(archivo.getFileName());
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
	
	private String obtenerExtensionArchivo(String nombreArchivo) throws Exception {
		return nombreArchivo.substring(nombreArchivo.length() - 3);
	}
	
	private List leerArchivoExcel(String directorioTemp,
			String nombreArchivo) throws Exception {
		ExcelUtil excelUtil = new ExcelUtil(directorioTemp, nombreArchivo);
		excelUtil.initSheet(0);
		int fila = 0;
		
		List listArchivo = new ArrayList();
		
		while(excelUtil.hasNext()) {

			Map mapRow = excelUtil.next();
			ProdutosFFNNEE producto = new ProdutosFFNNEE();
			
			fila = fila + 1;
			producto.setCodigoSAP((String)mapRow.get("0"));
			producto.setCodigoPeriodoInicio((String)mapRow.get("1"));
			producto.setCodigoPeriodoFin((String)mapRow.get("2"));
			producto.setCodigoRegion((String)mapRow.get("3"));
			
			listArchivo.add(producto);
			
		}
		
		excelUtil.cerrar();

		return listArchivo;
	}
	
	public int validarRegistros() 
 {
		MantenimientoRECProductosFFNNEESearchForm search = (MantenimientoRECProductosFFNNEESearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		int numero = 0;
		String codigoSAP = "";
		codigoSAP = search.getCodigoSAP();

		if (!StringUtils.isEmpty(codigoSAP)) {
			if (recCodigosSapValidosList.size() > 0) {
				String[] lista2 = new String[recCodigosSapValidosList.size()];
				int j = 0;

				for (Object string : recCodigosSapValidosList) {
					Base aux = (Base) string;
					lista2[j] = aux.getCodigo();
					j++;
				}

				for (int i = 0; i < lista2.length; i++) {
					if (lista2[i].equals(codigoSAP)) {
						numero = 1;
					}
				}

				if (numero == 0) {
					AjaxService ajax = (AjaxService) getBean("ajaxService");
					String existe= ajax.validarCodigoSAP(pais.getCodigo(), codigoSAP);
					if(existe==null)
					{
						addInfo("", "Codigo SAP no existe");
						return 0;
					}
					else
						return 1;
				} else {
					addError("", "el codigo SAP" + " " + codigoSAP + " "
							+ "ya fue agregado");
					return 0;
				}

			} else {				
					AjaxService ajax = (AjaxService) getBean("ajaxService");
					String existe = ajax.validarCodigoSAP(pais.getCodigo(), codigoSAP);
					if(existe==null)
					{
						addInfo("", "Codigo SAP no existe");
						return 0;
					}
					else
						return 1;
			}
		} else {
			addError("", "Debe de ingresar el codigo SAP a agregar");
			return 0;
		}
	}
	
	public void openDocumentoPopupBuscar(ActionEvent event)
	{
		MantenimientoRECProductosFFNNEESearchForm search = (MantenimientoRECProductosFFNNEESearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoRECProductosFFNNEEService service = (MantenimientoRECProductosFFNNEEService)  getBean("spusicc.mantenimientoRECProductosFFNNEEService");

		Map parameterMap = new HashMap();
		parameterMap.put("codigoPais", pais.getCodigo());
		parameterMap.put("codigoPeriodoInicio", search.getCodigoPeriodoInicio());
		parameterMap.put("codigoPeriodoFin", search.getCodigoPeriodoFin());
		List lista=service.getProductosFFNNEEList(parameterMap);

	}

	public String cancelar(ActionEvent actionEvent) throws Exception
	{
		return "mantenimientoRECProductosFFNNEEList";
	} 
	
	/**======= */
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public List getRecCodigosSapValidosList() {
		return recCodigosSapValidosList;
	}

	public void setRecCodigosSapValidosList(List recCodigosSapValidosList) {
		this.recCodigosSapValidosList = recCodigosSapValidosList;
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public List getRecProductosFFNNEEArchivoList() {
		return recProductosFFNNEEArchivoList;
	}

	public void setRecProductosFFNNEEArchivoList(
			List recProductosFFNNEEArchivoList) {
		this.recProductosFFNNEEArchivoList = recProductosFFNNEEArchivoList;
	}

	public boolean isFlagBorrado() {
		return flagBorrado;
	}

	public void setFlagBorrado(boolean flagBorrado) {
		this.flagBorrado = flagBorrado;
	}

	/**
	 * @return the indicadorIngreso
	 */
	public String getIndicadorIngreso() {
		return indicadorIngreso;
	}

	/**
	 * @param indicadorIngreso the indicadorIngreso to set
	 */
	public void setIndicadorIngreso(String indicadorIngreso) {
		this.indicadorIngreso = indicadorIngreso;
	}

	public Object getCodigoSAPSeleccionados() {
		return codigoSAPSeleccionados;
	}

	public void setCodigoSAPSeleccionados(Object codigoSAPSeleccionados) {
		this.codigoSAPSeleccionados = codigoSAPSeleccionados;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	
}
