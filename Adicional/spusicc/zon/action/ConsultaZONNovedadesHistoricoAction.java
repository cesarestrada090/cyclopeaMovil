package biz.belcorp.ssicc.web.spusicc.zon.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.LabelValueCDR;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Cliente;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.IdiomaService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.zon.MantenimientoZONDirectorioService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultoraPOPUPSearchAction;
import biz.belcorp.ssicc.web.spusicc.zon.form.ConsultaZONNovedadesHistoricoForm;

@ManagedBean
@SessionScoped
public class ConsultaZONNovedadesHistoricoAction extends BaseConsultaAbstractAction{
	
	
	private static final long serialVersionUID = 3975733363462696067L;
	
	private List zonMantOperList;
	private LabelValue[] siccRegionList;
	private LabelValueCDR[] siccZonaList;
	private List zonMantCargList;
	private Integer longitudCampoClientes;
	private List zonPerfilList;
	private List zonRolList;
	
	private boolean mostrarPopupCliente;
	private static final String POPUP_CLIENTE = "POPUP_CLIENTE";
	
	@ManagedProperty(value="#{busquedaConsultoraPOPUPSearchAction}")
	private BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction;
	
	

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaZONNovedadesHistoricoForm form = new ConsultaZONNovedadesHistoricoForm();
		return form;
	}
	
	@Override
	public void setViewAtributes() throws Exception {
		
		ConsultaZONNovedadesHistoricoForm f = (ConsultaZONNovedadesHistoricoForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codpais=pais.getCodigo();
		f.setCodigoPais(codpais);	
						
		/* obteniendo valores */
		MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService)this.getBean("spusicc.mantenimientoZONDirectorioService");		
		AjaxService ajaxService = (AjaxService) this.getBean("ajaxService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
	
		
		this.zonMantOperList=service.getOperaciones(new HashMap());
		this.siccRegionList=ajaxService.getRegionesAllDirectorioMantenimientoZON(codpais, pais.getCodigoConexionExterna(), Constants.NUMERO_CERO, "ASD");
		this.zonMantCargList=service.getTipoCargoList();
		
		//Recuperamos el idioma
  		IdiomaService idiomaService = (IdiomaService) getBean("idiomaService");
  		String s=usuario.getIdioma().getCodigoISO();  		
  		Idioma idioma = idiomaService.getIdiomaByCodigoISO(s);
  		
    
	    Map parameterMap = new HashMap();
		parameterMap.put("codigoIdiomaIso", idioma.getCodigoSiCC());
		parameterMap.put("codigoIdioma", idioma.getCodigoISO());	    
	    String oidIdiomaIso = reporteService.getOidString("getOidIdiomaByCodigoIdiomaIso", parameterMap);
		f.setOidIdioma(oidIdiomaIso);
		
		// Longitud del campo consultora
		ClienteUAGenerarService clienteService2 = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		this.longitudCampoClientes= clienteService2.getTamanhoNumeroCliente(pais.getCodigo());
		
		Map params = new HashMap();
		params.put("indicadorActivo", Constants.UNO);	
		this.zonPerfilList=service.getPerfilesByCriteria(params);
		this.zonRolList=service.getRolesByCriteria(params);
		
		this.log.debug("Todo Ok !");	
	}	
	


	/**
	 * Busca las UA asignadas 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'find' method");
		}
		
		
		
		//datosZONConsultora
		MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService) this.getBean("spusicc.mantenimientoZONDirectorioService");
		
		ConsultaZONNovedadesHistoricoForm f = (ConsultaZONNovedadesHistoricoForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		Map map = BeanUtils.describe(f);
		map.put("codigoCliente",f.getCodigoClienteBuscar());
		map.put("codigoConexionExterna", pais.getCodigoConexionExterna());
		List listDirectorioVentas = service.getDirectorioHistorioNovedades(map);
		
		AjaxService ajaxService = (AjaxService) this.getBean("ajaxService");
		/*
		if (listDirectorioVentas != null && listDirectorioVentas.size() > 0) {
			session.setAttribute("consultaList", listDirectorioVentas);
		}else {
			session.setAttribute("consultaList", null);
			messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.datos.fuentes.busqueda"));
			saveMessages(request, messages);
		}
		*/
		
		LabelValueCDR []listaZonas = ajaxService.getZonasAllDirectorioActivas(pais.getCodigo(), pais.getCodigoConexionExterna(), new String[]{f.getCodigoRegion()}, Constants.NUMERO_CERO);
		LabelValueCDR []listaZonasFinal = null;
		
		if(listaZonas != null)
		{
			listaZonasFinal = new LabelValueCDR[listaZonas.length + 1];
			listaZonasFinal[0] = new LabelValueCDR();
			System.arraycopy(listaZonas, 0, listaZonasFinal, 1, listaZonas.length);
		}
		
		this.siccZonaList= listaZonasFinal;		
		
		return listDirectorioVentas;
	}
	
	/**
	 * Retorna la lista depurada
	 * @param listDirectorioVentas
	 * @return
	 */
	private List getListDirectorioVentas(List listDirectorioVentas) {
		List listResult = new ArrayList();
		Iterator it = listDirectorioVentas.iterator();
		while(it.hasNext()){
		  Map map =(Map)it.next();
		  if(Constants.NUMERO_UNO.equals((String)map.get("verDirectorio")))
			  listResult.add(map);
		}
		return listResult;
	}
	
	public void loadZonas(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("cargar Zonas:ValueChangeEvent");
		}
		
		ConsultaZONNovedadesHistoricoForm f = (ConsultaZONNovedadesHistoricoForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String valor= (String)	val.getNewValue();
		String []valor0={valor};
		//valor0[0]=valor;
		log.debug(valor);
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		this.siccZonaList=ajax.getZonasAllDirectorioActivas(f.getCodigoPais(), pais.getCodigoConexionExterna(), valor0,"0");		
	}
	
	@Override
	protected void setInvocarPopup(String accion) {
		if (accion.equals(this.POPUP_CLIENTE)){ 
			this.mostrarPopupCliente = true;
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */	
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopupHipCliente' method");
		}
		
		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;
		if (accion.equals(this.POPUP_CLIENTE)) {
			this.busquedaConsultoraPOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaConsultoraPOPUPSearchAction.isSeleccionoRegistro()) {	
				
				Cliente cliente= (Cliente)this.busquedaConsultoraPOPUPSearchAction.getBeanRegistroSeleccionado(); 
				
				ConsultaZONNovedadesHistoricoForm f = (ConsultaZONNovedadesHistoricoForm) this.formBusqueda;				
				f.setCodigoClienteBuscar(cliente.getCodigo());
				String nombre=cliente.getNombre() +" " + cliente.getApellidoPaterno()+" "+cliente.getApellidoMaterno();				
				f.setNombreCliente(nombre);
				f.setNumeroDocIdentidadBuscar(cliente.getNumeroDocumento());
				this.busquedaConsultoraPOPUPSearchAction.setBeanRegistroSeleccionado(null);
				this.formBusqueda =  f;		
				
			}
		}	
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setSalirPopup()
	 */
	@Override
	protected void setSalirPopup() {
		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;
		this.busquedaConsultoraPOPUPSearchAction.setBeanRegistroSeleccionado(null);
	}

	public List getZonMantOperList() {
		return zonMantOperList;
	}

	public void setZonMantOperList(List zonMantOperList) {
		this.zonMantOperList = zonMantOperList;
	}

	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public LabelValueCDR[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValueCDR[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public List getZonMantCargList() {
		return zonMantCargList;
	}

	public void setZonMantCargList(List zonMantCargList) {
		this.zonMantCargList = zonMantCargList;
	}

	public Integer getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	public void setLongitudCampoClientes(Integer longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}

	public List getZonPerfilList() {
		return zonPerfilList;
	}

	public void setZonPerfilList(List zonPerfilList) {
		this.zonPerfilList = zonPerfilList;
	}

	public List getZonRolList() {
		return zonRolList;
	}

	public void setZonRolList(List zonRolList) {
		this.zonRolList = zonRolList;
	}

	public boolean isMostrarPopupCliente() {
		return mostrarPopupCliente;
	}

	public void setMostrarPopupCliente(boolean mostrarPopupCliente) {
		this.mostrarPopupCliente = mostrarPopupCliente;
	}

	public BusquedaConsultoraPOPUPSearchAction getBusquedaConsultoraPOPUPSearchAction() {
		return busquedaConsultoraPOPUPSearchAction;
	}

	public void setBusquedaConsultoraPOPUPSearchAction(
			BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction) {
		this.busquedaConsultoraPOPUPSearchAction = busquedaConsultoraPOPUPSearchAction;
	}
	
	
	
}
