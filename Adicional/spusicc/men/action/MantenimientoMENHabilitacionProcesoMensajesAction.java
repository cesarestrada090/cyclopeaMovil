package biz.belcorp.ssicc.web.spusicc.men.action;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.DualListModel;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.men.MantenimientoMENPlantillaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.men.form.MantenimientoMENHabilitacionProcesoMensajesForm;


@ManagedBean
@SessionScoped
public class MantenimientoMENHabilitacionProcesoMensajesAction extends BaseMantenimientoSearchAbstractAction{

	private static final long serialVersionUID = 6101113280385017219L;
	
	private List siccMarcaList;
	private List siccCanalList;
	private List msgProceDispoList;
	private List msgProceHabiList;
	private Boolean indicaProceso;
	
	private DualListModel<String> listaProcesos;

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoMENHabilitacionProcesoMensajesForm searchForm = new MantenimientoMENHabilitacionProcesoMensajesForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		 if (log.isDebugEnabled()) {
	            log.debug("Entering 'save' method");
	        }
		 
	      
	    Usuario usuario =this.mPantallaPrincipalBean.getCurrentUser();
	   	MantenimientoMENPlantillaService service = (MantenimientoMENPlantillaService) getBean("spusicc.mantenimientoMENPlantillaService");
	    MantenimientoMENHabilitacionProcesoMensajesForm f = (MantenimientoMENHabilitacionProcesoMensajesForm)this.formBusqueda;
	    if(getIndicaProceso()==true)
	    	f.setIndicadorProceso(Constants.NUMERO_UNO);
	    else
	    	f.setIndicadorProceso(Constants.NUMERO_CERO);
	    
	    List listProcesoSeleccionado = null;
	    if(this.listaProcesos.getTarget()!=null && this.listaProcesos.getTarget().size()>0){
	    	 listProcesoSeleccionado=listaProcesos.getTarget();
	    	 Collections.sort(listProcesoSeleccionado);
	    }
	   
	    	     
	    Map criteria = new HashMap();
	    criteria.put("codigoPais",f.getCodigoPais());
	    criteria.put("codigoMarca",f.getCodigoMarca());
	    criteria.put("codigoCanal",f.getCodigoCanal());
	    criteria.put("indicadorProceso",f.getIndicadorProceso());
	    criteria.put("listaProceso",listProcesoSeleccionado);        
	    criteria.put("login", usuario.getLogin());      
	        		
	    if(listProcesoSeleccionado.size()>0){
	        service.insertProcesoHabilitado(criteria);        
	     }else{
	        criteria.put("flag", Constants.NUMERO_UNO);  
	        service.deleteProcesoHabilitado(criteria);
	    }      
	     
	    this.setViewAtributes();
	    return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		
		this.mostrarBotonBuscar=false;
		this.mostrarCriteriosBusqueda=false;
		this.mostrarBotonConsultar=false;
		this.mostrarBotonEliminar=false;
		this.mostrarBotonNuevo=false;
		this.mostrarBotonModificar=false;
		this.mostrarBotonSave=true;
		this.mostrarListaBusqueda=false;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoMENHabilitacionProcesoMensajesForm f = (MantenimientoMENHabilitacionProcesoMensajesForm)this.formBusqueda;
		MantenimientoMENPlantillaService service = (MantenimientoMENPlantillaService) getBean("spusicc.mantenimientoMENPlantillaService");
		//se cargara la lista de parametros de concurso activos 
		Map map= new HashMap();
		map.put("indicadorDisponible", Constants.NUMERO_UNO);
		List listProcesosActivo = service.getProcesosDisponibles(map);
		List listProcesoHabilitado = service.getListProcesoHabilitado();
		
		InterfazSiCCService iservice = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		Map params = new HashMap();
		params.put("codigoISO", usuario.getIdioma().getCodigoISO());
		params.put("codigoPais", pais.getCodigo());
		this.siccMarcaList=iservice.getMarcas();
		this.siccCanalList=iservice.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		
		
		this.msgProceDispoList=listProcesosActivo;
		this.msgProceHabiList=listProcesoHabilitado;
		this.listaProcesos=new DualListModel<String>(msgProceDispoList, msgProceHabiList);
		
		
		if(listProcesoHabilitado.size()>0){
			map=(Map)listProcesoHabilitado.get(0);
			f.setIndicadorProceso((String)map.get("indicadorProceso"));				
		}else{
			f.setIndicadorProceso(Constants.NUMERO_UNO);			
		}
		
		if(f.getIndicadorProceso().equals(Constants.NUMERO_UNO))
			this.indicaProceso=true;
		else
			this.indicaProceso=false;
			
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoPais(pais.getCodigo());
		
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() {	
		return "mantenimientoMENHabilitacionProcesoMensajesForm.cabecera.add";
	}

	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	public List getMsgProceDispoList() {
		return msgProceDispoList;
	}

	public void setMsgProceDispoList(List msgProceDispoList) {
		this.msgProceDispoList = msgProceDispoList;
	}

	public List getMsgProceHabiList() {
		return msgProceHabiList;
	}

	public void setMsgProceHabiList(List msgProceHabiList) {
		this.msgProceHabiList = msgProceHabiList;
	}

	public DualListModel<String> getListaProcesos() {
		return listaProcesos;
	}

	public void setListaProcesos(DualListModel<String> listaProcesos) {
		this.listaProcesos = listaProcesos;
	}

	public Boolean getIndicaProceso() {
		return indicaProceso;
	}

	public void setIndicaProceso(Boolean indicaProceso) {
		this.indicaProceso = indicaProceso;
	}

	
	
	
}
