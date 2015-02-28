package biz.belcorp.ssicc.web.spusicc.sms.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.sms.ProcesoSMSService;

import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.sms.form.MantenimientoSMSForm;
import biz.belcorp.ssicc.web.spusicc.sms.form.MantenimientoSMSSearchForm;


@ManagedBean
@SessionScoped
public class MantenimientoSMSSearchAction extends BaseMantenimientoSearchAbstractAction{

	private static final long serialVersionUID = -7894553265542758184L;
	
	private List siccTipoClienteList;
	private List siccRegionList;
	private List smsMensajesList;
	private LabelValue[] siccSubTipoClienteList;
	private LabelValue[] siccTipoClasificacionList;
	private LabelValue[] siccClasificacionList;
	private LabelValue[] siccZonaList;
	private LabelValue[] siccSeccionList;
	private String codigoIdiomaIso;
	private boolean mantenimiento=false; 

	@Override
	protected String getSalirForward() {		
		return "mantenimientoSMSList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {		
		return "mantenimientoSMSForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoSMSSearchForm searchForm = new MantenimientoSMSSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'MantenimientoSMSSearchAction - search' method");
        }  	      	
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");		
		ProcesoSMSService service = (ProcesoSMSService)getBean("spusicc.procesoSMSService");		
		MantenimientoSMSSearchForm f = (MantenimientoSMSSearchForm)this.formBusqueda;		
		
		Map criteriaSearch = new HashMap();
		List lista = new ArrayList();
		
		criteriaSearch.put("codigoPais", pais.getCodigo());
		criteriaSearch.put("codigoIso", this.codigoIdiomaIso);
		criteriaSearch.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteriaSearch.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		criteriaSearch.put("codigoPeriodo", f.getCodigoPeriodo());
				
		if(StringUtils.isNotBlank(f.getCodigoPeriodo())){
			criteriaSearch.put("oidPeriodo", reporteService.getOidString("getOidPeriodoByCodigoPeriodo", criteriaSearch));
		}else{ 
			criteriaSearch.put("oidPeriodo", null);
		}
		
		if(StringUtils.isNotBlank(f.getOidTipoCliente()) && !f.getOidTipoCliente().equals("Todos")){
			criteriaSearch.put("oidTipoCliente", Integer.valueOf(f.getOidTipoCliente()));
		}else{
			criteriaSearch.put("oidTipoCliente", null);
		}
		
		if(StringUtils.isNotBlank(f.getOidSubTipoCliente()) && !f.getOidSubTipoCliente().equals("Todos")){
			criteriaSearch.put("oidSubTipoCliente", Integer.valueOf(f.getOidSubTipoCliente()));		
		}else{
			criteriaSearch.put("oidSubTipoCliente", null);			
		}
		
		if(StringUtils.isNotBlank(f.getOidTipoClasificacion()) && !f.getOidTipoClasificacion().equals("Todos")){
			criteriaSearch.put("oidSubTipoClasificacion", Integer.valueOf(f.getOidTipoClasificacion()));		
		}else{
			criteriaSearch.put("oidSubTipoClasificacion", null);			
		}
		
		if(StringUtils.isNotBlank(f.getOidClasificacion()) && !f.getOidClasificacion().equals("Todos")){
			criteriaSearch.put("oidClasificacion", Integer.valueOf(f.getOidClasificacion()));		
		}else{
			criteriaSearch.put("oidClasificacion", null);			
		}
		
		if(StringUtils.isNotBlank(f.getCodigoRegion()) && !f.getCodigoRegion().equals("Todos")){
			criteriaSearch.put("codigoRegion", f.getCodigoRegion());
			criteriaSearch.put("oidRegion", reporteService.getOidRegionByPaisMarcaCanal(criteriaSearch));
		}else{
			criteriaSearch.put("oidRegion", null);
		}
		
		if(StringUtils.isNotBlank(f.getCodigoZona())  && !f.getCodigoZona().equals("Todos")){
			criteriaSearch.put("codigoZona", f.getCodigoZona());
			criteriaSearch.put("oidZona", reporteService.getOidZonaByPaisMarcaCanalRegion(criteriaSearch));		
		}else{ 
			criteriaSearch.put("oidZona", null);			
		}
		
		if(StringUtils.isNotBlank(f.getCodigoSeccion()) && !f.getCodigoSeccion().equals("Todos")){
			criteriaSearch.put("codigoSeccion", f.getCodigoSeccion());
			criteriaSearch.put("oidSeccion",  reporteService.getOidSeccionByPaisMarcaCanalZona(criteriaSearch));		
		}else{ 
			criteriaSearch.put("oidSeccion", null);			
		}
		
		if(!f.getActivacion().equals("T")){
			criteriaSearch.put("estado", (f.getActivacion().equals(Constants.SI)?"1":"2"));
		}
			
		
		if(StringUtils.isNotBlank(f.getOidTipoCliente()) || StringUtils.isNotBlank(f.getCodigoRegion())){
			lista = service.getSMSList(criteriaSearch);	
			this.smsMensajesList=lista;					
		}		
        /*
        String ind_mensaje = (String)request.getSession().getAttribute("indExito");
        
        if (ind_mensaje != null) {
        	if(ind_mensaje.equals("0")){        		 
        		 messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("mantenimientoSMSForm.error"));
		         saveErrors(request, messages);		         
        	}
        	if(ind_mensaje.equals("1")){
        		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("mantenimientoSMSForm.exito"));	    			
				saveMessages(request.getSession(), messages);
        	}			
        }
        request.getSession().removeAttribute(Constants.IND_EXITO);
        */
		
        f.setIndBusqueda(true);
       
        
        return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		log.debug("Entering 'actualizaIndicadores' method");
		Map sistemabusqueda = (Map) this.beanRegistroSeleccionado;
		String id = sistemabusqueda.get("codigo").toString();
		
        if (log.isDebugEnabled()) {
            log.debug("Id seleccionado de la lista: " + id);
         }     
	     
	    ProcesoSMSService service = (ProcesoSMSService)getBean("spusicc.procesoSMSService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
	       
		Map criteriaDelete = new HashMap(); 	    
	    
		try{			
			if (StringUtils.isNotEmpty(id)){							   		
				criteriaDelete.put("codigo", id);
	    		criteriaDelete.put("usuario", usuario.getLogin());				
	    		service.removeSMS(criteriaDelete);					
			}			
		
		}catch (Exception e){
			log.debug("e" + e);
            String descripcion = e.getMessage();
            this.addError("ERROR: ", this.getResourceMessage("errors.invalid.transaction.clusterizacion"));
            throw new Exception(descripcion);          
		}
			
	return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'MantenimientoSMSAction - save' method");
        }
        
		MantenimientoSMSForm f = (MantenimientoSMSForm)this.formMantenimiento;		
				
		ReporteService reporteService = (ReporteService)getBean("scsicc.reporteService");
		ProcesoSMSService service = (ProcesoSMSService)getBean("spusicc.procesoSMSService");
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();	
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
				
		Map criteria = new HashMap();
		
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());		
		criteria.put("codigoIso", usuario.getIdioma().getCodigoISO());
		criteria.put("usuario", usuario.getLogin());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		
		if(StringUtils.isNotBlank(f.getCodigoPeriodo()))
			criteria.put("oidPeriodo", reporteService.getOidString("getOidPeriodoByCodigoPeriodo", criteria));			
		else 
			criteria.put("oidPeriodo", null);
				
		if(StringUtils.isNotBlank(f.getOidTipoCliente()) && !f.getOidTipoCliente().equals("Todos")){
			criteria.put("oidTipoCliente", f.getOidTipoCliente());
		}else{
			criteria.put("oidTipoCliente", null);
		}
		
		if(StringUtils.isNotBlank(f.getOidSubTipoCliente()) && !f.getOidSubTipoCliente().equals("Todos")){
			criteria.put("oidSubTipoCliente", f.getOidSubTipoCliente());		
		}else{
			criteria.put("oidSubTipoCliente", null);			
		}
		
		if(StringUtils.isNotBlank(f.getOidTipoClasificacion()) && !f.getOidTipoClasificacion().equals("Todos")){
			criteria.put("oidSubTipoClasificacion", f.getOidTipoClasificacion());			
		}else{
			criteria.put("oidSubTipoClasificacion", null);			
		}
		
		if(StringUtils.isNotBlank(f.getOidClasificacion()) && !f.getOidClasificacion().equals("Todos")){
			criteria.put("oidClasificacion", f.getOidClasificacion());			
		}else{
			criteria.put("oidClasificacion", null);			
		}
		
		if(StringUtils.isNotBlank(f.getCodigoRegion())&& 
				 !Constants.FORMATEAR_TODOS.equals(f.getCodigoRegion()) && !f.getCodigoRegion().equals("Todos")){//no es vacio y diferente de todos
			criteria.put("codigoRegion", f.getCodigoRegion());
			criteria.put("oidRegion", reporteService.getOidRegionByPaisMarcaCanal(criteria));
		}else{			
			criteria.put("codigoRegion", StringUtils.isEmpty(f.getCodigoRegion())?Constants.STO_VALOR_OID_NULL:Constants.FORMATEAR_TODOS);			
			criteria.put("oidRegion", null);
		}
		
		if(StringUtils.isNotBlank(f.getCodigoZona()) && !f.getCodigoZona().equals("Todos")){
			criteria.put("codigoZona", f.getCodigoZona());
			criteria.put("oidZona", reporteService.getOidZonaByPaisMarcaCanalRegion(criteria));		
		}else{ 
			criteria.put("oidZona", null);			
		}
		
		if(StringUtils.isNotBlank(f.getCodigoSeccion()) && !f.getCodigoSeccion().equals("Todos")){
			criteria.put("codigoSeccion", f.getCodigoSeccion());
			criteria.put("oidSeccion",  reporteService.getOidSeccionByPaisMarcaCanalZona(criteria));		
		}else{ 
			criteria.put("oidSeccion", null);			
		}
			
		criteria.put("usuario", usuario.getLogin());
		criteria.put("estado", (f.getActivacion().equals(Constants.SI)?"1":"2"));
		criteria.put("observaciones", f.getObservacion());
		
		try{
			service.insertSMS(criteria, usuario);			
			
		}catch(Exception e){
			
		}
				
        return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoSMSForm f = new MantenimientoSMSForm();
		valoresIniciales();
		limpiarValores();
		f.setCodigoPais(pais.getCodigo());
		f.setNewRecord(true);
		this.mantenimiento=true;
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonConsultar=false;
		this.mostrarBotonModificar=false;	
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();		
		MantenimientoSMSSearchForm f = (MantenimientoSMSSearchForm)this.formBusqueda;		
		f.setCodigoPais(pais.getCodigo());		
		valoresIniciales();
		limpiarValores();		
	}
	
	public String setValidarConfirmar(String accion) {		
		if(accion.equals("ACTIVAR") ){
			if(this.beanRegistroSeleccionado==null)
				return this.getResourceMessage("errors.select.item");
		}
		if(accion.equals("DESACTIVAR") ){
			if(this.beanRegistroSeleccionado==null)
				return this.getResourceMessage("errors.select.item");
		}
		return null;
	}
	
	public void activarSMS(ActionEvent actionEvent)   throws Exception {	        
	    log.debug("Entering 'actualizaIndicadores' method"); 
	    Map sistemabusqueda = (Map) this.beanRegistroSeleccionado;
	    String id = sistemabusqueda.get("codigo").toString();
	            
	    if (log.isDebugEnabled()) {
	       log.debug("Id seleccionado de la lista: " + id);
	    } 
	    
		      
		ProcesoSMSService service = (ProcesoSMSService)getBean("spusicc.procesoSMSService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();	       
		Map criteriaActivar = new HashMap();     
		try{
				
			if (StringUtils.isNotEmpty(id)){				
				criteriaActivar.put("codigo", id);
				criteriaActivar.put("usuario", usuario.getLogin());					
		    	service.activarSMS(criteriaActivar);		
				this.addInfo("INFO: ", this.getResourceMessage("mantenimientoSMSForm.activar.exito"));	    		
		    					
			}			
			
		}catch (Exception e){
			log.debug("e" + e);
	        String descripcion = e.getMessage();
	        this.addError("ERROR ", this.getResourceMessage("errors.invalid.transaction.clusterizacion")); 
	        throw new Exception(descripcion);
		}
		this.find(actionEvent);
		
	}
	
	public void desactivarSMS(ActionEvent actionEvent)   throws Exception {			        
	    log.debug("Entering 'eliminarBoletaRecojo' method");
	    Map sistemabusqueda = (Map) this.beanRegistroSeleccionado;
	    String id = sistemabusqueda.get("codigo").toString();
	           
	    if (log.isDebugEnabled()) {
	        log.debug("Id seleccionado de la lista: " + id);
	    }    
		   
	    ProcesoSMSService service = (ProcesoSMSService)getBean("spusicc.procesoSMSService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();		       
		Map criteriaDesactivar = new HashMap();	    
		    
		try{				
			if (StringUtils.isNotEmpty(id)){								   		
				criteriaDesactivar.put("codigo", id);
				criteriaDesactivar.put("usuario", usuario.getLogin());					
		    	service.desactivarSMS(criteriaDesactivar);		
				this.addInfo("INFO: ", this.getResourceMessage("mantenimientoSMSForm.desactivar.exito"));		    	
			}			
		}catch (Exception e){
			log.debug("e" + e);
	        String descripcion = e.getMessage();
	        this.addError("ERROR ", this.getResourceMessage("errors.invalid.transaction.clusterizacion")); 
	        throw new Exception(descripcion);	          
		}
		this.find(actionEvent);
		
	}
	       
	
	public void valoresIniciales(){
		
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");	
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");	
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario =this.mPantallaPrincipalBean.getCurrentUser();		
		this.codigoIdiomaIso=usuario.getIdioma().getCodigoISO();
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		criteriaOperacion.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteriaOperacion.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);		
		criteriaOperacion.put("estadoCampanha",Constants.NUMERO_CERO);
		criteriaOperacion.put("indicadorActiva",Constants.ESTADO_ACTIVO);
		
		this.siccTipoClienteList=interfazSiCCService.getTiposClientesByCodigoISOOID(this.codigoIdiomaIso);
		this.siccRegionList=reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
	}
	
	public void loadSubTiposClientes(ValueChangeEvent val){
		MantenimientoSMSSearchForm search = (MantenimientoSMSSearchForm)this.formBusqueda;
		String stipos=val.getNewValue().toString();		
		ArrayList tipos= new ArrayList();	
		tipos.add(stipos);
		if(!stipos.equals(null)){
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			LabelValue[] result=ajax.getSubTiposClientesPorPaisTipoClientesOID(this.codigoIdiomaIso, tipos);
			this.setSiccSubTipoClienteList(result);			
			
		}else{			
			this.siccSubTipoClienteList=null;					
		}
	}
	
	public void loadTiposClasificaciones(ValueChangeEvent val){	
		MantenimientoSMSSearchForm search = (MantenimientoSMSSearchForm)this.formBusqueda;
		MantenimientoSMSForm f = (MantenimientoSMSForm)this.formMantenimiento;
		String subTipo=val.getNewValue().toString();		
		ArrayList listSubtipo= new ArrayList();	
		listSubtipo.add(subTipo);
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		if(!subTipo.equals(null)){
			
			if(this.mantenimiento){
				LabelValue[] result=ajax.getTiposClasificacionesByCriteriaMultipleOID(this.codigoIdiomaIso,f.getOidTipoCliente(), listSubtipo);
				this.setSiccTipoClasificacionList(result);
			}else{
				LabelValue[] result=ajax.getTiposClasificacionesByCriteriaMultipleOID(this.codigoIdiomaIso,search.getOidTipoCliente(), listSubtipo);
				this.setSiccTipoClasificacionList(result);	
			}
		}else{
			this.siccTipoClasificacionList=null;
		}
		
	}
	
	public void loadClasificaciones(ValueChangeEvent val){
		MantenimientoSMSSearchForm search = (MantenimientoSMSSearchForm)this.formBusqueda;
		MantenimientoSMSForm f = (MantenimientoSMSForm)this.formMantenimiento;
		ArrayList listSubtipo= new ArrayList();
		listSubtipo.add(search.getOidSubTipoCliente());	
		
		String tipoClasificacion=val.getNewValue().toString();
		ArrayList listTipoClasificacion=new ArrayList();
		listTipoClasificacion.add(tipoClasificacion);
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		if(!tipoClasificacion.equals(null)){
			if(this.mantenimiento){
				ArrayList listSubtipoForm= new ArrayList();
				listSubtipoForm.add(f.getOidSubTipoCliente());	
				LabelValue[] result=ajax.getClasificacionesByCriteriaMultipleOID(this.codigoIdiomaIso, f.getOidTipoCliente(), listSubtipoForm, listTipoClasificacion);
				this.setSiccClasificacionList(result);
			}else{
				LabelValue[] result=ajax.getClasificacionesByCriteriaMultipleOID(this.codigoIdiomaIso, search.getOidTipoCliente(), listSubtipo, listTipoClasificacion);
				this.setSiccClasificacionList(result);
			}
		}else{
			this.siccClasificacionList=null;
		}
	}
		
	public void loadZonas(ValueChangeEvent val){
		log.debug(">>loadZonas");
		log.debug("val: " + val.getNewValue().toString());
		MantenimientoSMSSearchForm search = (MantenimientoSMSSearchForm)this.formBusqueda;
		MantenimientoSMSForm f = (MantenimientoSMSForm)this.formMantenimiento;
		String valor = val.getNewValue().toString();
		String [] regiones={valor};
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		
		if(regiones.length > 0){
			if(this.mantenimiento){
				LabelValue[] result=ajax.getZonasMultipleByPaisMarcaCanalRegion(f.getCodigoPais(), "T", "VD",  regiones,""); 
				this.setSiccZonaList(result);
			}else{
				LabelValue[] result=ajax.getZonasMultipleByPaisMarcaCanalRegion(search.getCodigoPais(), "T", "VD",  regiones,""); 
				this.setSiccZonaList(result);
			}
		}else{
			this.siccZonaList=null;
		}
	}
	
	
	public void loadSeccion(ValueChangeEvent val){
		
		MantenimientoSMSForm f = (MantenimientoSMSForm)this.formMantenimiento;
		String region=f.getCodigoRegion();
		String [] regiones={region};
		String zona=val.getNewValue().toString();
		String [] zonas={zona};
		if(!region.equals(null)){
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			LabelValue[] result=ajax.getSeccionMultipleByPaisMarcaCanalRegionZona(f.getCodigoPais(), "T", "VD", regiones,zonas,"");	
			this.setSiccSeccionList(result);
			
		}else{
			this.siccSeccionList=null;
		}
	}
	public void limpiarValores(){
		this.siccZonaList=null;
		this.siccSeccionList=null;
		this.siccSubTipoClienteList=null;
		this.siccTipoClasificacionList=null;
		this.siccClasificacionList=null;
		
	}
	

	public List getSiccTipoClienteList() {
		return siccTipoClienteList;
	}

	public void setSiccTipoClienteList(List siccTipoClienteList) {
		this.siccTipoClienteList = siccTipoClienteList;
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public LabelValue[] getSiccSubTipoClienteList() {
		return siccSubTipoClienteList;
	}

	public void setSiccSubTipoClienteList(LabelValue[] siccSubTipoClienteList) {
		this.siccSubTipoClienteList = siccSubTipoClienteList;
	}

	public LabelValue[] getSiccTipoClasificacionList() {
		return siccTipoClasificacionList;
	}

	public void setSiccTipoClasificacionList(LabelValue[] siccTipoClasificacionList) {
		this.siccTipoClasificacionList = siccTipoClasificacionList;
	}

	public LabelValue[] getSiccClasificacionList() {
		return siccClasificacionList;
	}

	public void setSiccClasificacionList(LabelValue[] siccClasificacionList) {
		this.siccClasificacionList = siccClasificacionList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public LabelValue[] getSiccSeccionList() {
		return siccSeccionList;
	}

	public void setSiccSeccionList(LabelValue[] siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
	}

	public String getCodigoIdiomaIso() {
		return codigoIdiomaIso;
	}

	public void setCodigoIdiomaIso(String codigoIdiomaIso) {
		this.codigoIdiomaIso = codigoIdiomaIso;
	}

	public List getSmsMensajesList() {
		return smsMensajesList;
	}

	public void setSmsMensajesList(List smsMensajesList) {
		this.smsMensajesList = smsMensajesList;
	}

	public boolean isMantenimiento() {
		return mantenimiento;
	}

	public void setMantenimiento(boolean mantenimiento) {
		this.mantenimiento = mantenimiento;
	}
	
	
}
