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

import org.apache.commons.lang.StringUtils;

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
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.zon.MantenimientoZONDirectorioService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultoraPOPUPSearchAction;
import biz.belcorp.ssicc.web.spusicc.zon.form.ConsultaZONDirectorioVentasForm;


@ManagedBean
@SessionScoped
public class ConsultaZONDirectorioVentasAction extends BaseConsultaAbstractAction{
	
	private static final long serialVersionUID = 72766850286458344L;
	
	private List zonMantCargList;
	private LabelValue[] siccRegionList;
	private LabelValueCDR[] siccZonaList;
	private LabelValue[] zonEstadosList;
	private Integer longitudCampoClientes;
	private List zonPerfilList;
	private List zonRolList;
	private String zonIndiResuDeta;
	private String validarBarrio; 
	
	private boolean mostrarPopupCliente;
	private static final String POPUP_CLIENTE = "POPUP_CLIENTE";
	
	@ManagedProperty(value="#{busquedaConsultoraPOPUPSearchAction}")
	private BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction;
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaZONDirectorioVentasForm form = new ConsultaZONDirectorioVentasForm();
		return form;
	}
	
	@Override
	public void setViewAtributes() throws Exception {
		
		ConsultaZONDirectorioVentasForm f = (ConsultaZONDirectorioVentasForm) this.formBusqueda;
		AjaxService ajaxService = (AjaxService) this.getBean("ajaxService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService) this.getBean("spusicc.mantenimientoZONDirectorioService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codpais=pais.getCodigo();
		f.setCodigoPais(codpais);		
		f.setResumenDetalle("RES");
		
		/* obteniendo valores */	
		
		this.zonMantCargList=service.getTipoCargoList();
		this.siccRegionList=ajaxService.getRegionesAllDirectorioMantenimientoZON(codpais, pais.getCodigoConexionExterna(), Constants.NUMERO_CERO, "ASD");
				
		LabelValue[] estadoList = null;
		estadoList = new LabelValue[4];
		estadoList[0] = new LabelValue("ACTIVA", "A");
		estadoList[1] = new LabelValue("INACTIVA", "I");
		estadoList[2] = new LabelValue("INACTIVA TEMPORAL", "IT");
		estadoList[3] = new LabelValue("NO ASIGNADA", "NA");
		this.zonEstadosList=estadoList;
				
		//Recuperamos el idioma
  		IdiomaService idiomaService = (IdiomaService) getBean("idiomaService");
  		String s=usuario.getIdioma().getCodigoISO();  		
  		Idioma idioma = idiomaService.getIdiomaByCodigoISO(s);
  		
  		// Longitud del campo consultora
		ClienteUAGenerarService clienteService2 = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		
    	this.longitudCampoClientes= clienteService2.getTamanhoNumeroCliente(pais.getCodigo()); 
		Map params = new HashMap();
		params.put("indicadorActivo", Constants.UNO);
		params.put("codigoIdiomaIso", idioma.getCodigoSiCC());
	    params.put("codigoIdioma", idioma.getCodigoISO());
	    
		this.zonPerfilList=service.getPerfilesByCriteria(params);
		this.zonRolList=service.getRolesByCriteria(params);		
		String oidIdiomaIso = reporteService.getOidString("getOidIdiomaByCodigoIdiomaIso", params);
		f.setOidIdioma(oidIdiomaIso);
		
		this.log.debug("Todo Oki");	
		
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		this.validarBarrio = clienteService.getValorModuloxPaisTipoValidacion(pais.getCodigo(), Constants.MAE_VALID_VAL_BARRIO);
		if(StringUtils.isNotBlank(validarBarrio))
			validarBarrio = Constants.ESTADO_ACTIVO;		
		
		 params.put("validarBarrio", validarBarrio);
	}	

	@Override
	public List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'find' method");
		}
		
	
		MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService) this.getBean("spusicc.mantenimientoZONDirectorioService");
		AjaxService ajaxService = (AjaxService) this.getBean("ajaxService");		
		ConsultaZONDirectorioVentasForm f = (ConsultaZONDirectorioVentasForm) this.formBusqueda;	
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
		criteria.put("codigoRegion", f.getCodigoRegion());
		criteria.put("codigoZona", f.getCodigoZona());
		criteria.put("cargo", f.getCodigoCargo());
		
		
		if(f.getTipoCargo().equals("CV")){ //para CargosVigentes
			criteria.put("tipoCargoVigente", '1');
		}else if(f.getTipoCargo().equals("CF")){ //para cargos futuros
			criteria.put("tipoCargoFuturo", '1');
		}
		//criteria.put("estado", f.getEstado());
		//criteria.put("estadoList", (f.getEstadoList() == null || StringUtils.isEmpty(f.getEstadoList()[0])) ? new ArrayList() : Arrays
		//		.asList(f.getEstadoList()));
		
		criteria.put("email", f.getEmail());
		criteria.put("barrio", f.getBarrio());
		criteria.put("cub", f.getCub());
		criteria.put("codigoRol", f.getCodigoRol());
		criteria.put("codigoPerfil", f.getCodigoPerfil());
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoCliente",f.getCodigoClienteBuscar());
		
		int tam=f.getEstadoList().length;	
		
		
		if(f.getEstadoList() == null ||tam==0){
			criteria.put("estadoList", new ArrayList());
		}else{
			//String[] sa = {"A", "I", "IT", "NA"};
			String[] sa = f.getEstadoList();
			ArrayList coleccion = new ArrayList();
			System.out.println("tamańo: "+sa.length);
			for(int i=0; i< sa.length; i++){
				System.out.println(sa[i].toString());
				if(sa[i].equals("A")){
					coleccion.add("ACTIVA");
				}else if(sa[i].equals("I")){
					coleccion.add("INACTIVA");
				}else if(sa[i].equals("IT")){
					coleccion.add("INACTIVA TEMPORAL");
				}else if(sa[i].equals("NA")){
					coleccion.add("NO ASIGNADA");
				}
			}
			
			System.out.println(coleccion.toString());
			criteria.put("estadoList", coleccion);
		}
		
		criteria.put("codigoConexionExterna", pais.getCodigoConexionExterna());
		
		List listDirectorioVentas = service.getConsultarDirectorioVentas(criteria);
		
		
		LabelValueCDR []listaZonas = ajaxService.getZonasAllDirectorioActivas(pais.getCodigo(), pais.getCodigoConexionExterna(), new String[]{f.getCodigoRegion()}, Constants.NUMERO_CERO);
		LabelValueCDR []listaZonasFinal = null;
		
		if(listaZonas != null)
		{
			listaZonasFinal = new LabelValueCDR[listaZonas.length + 1];
			listaZonasFinal[0] = new LabelValueCDR();
			System.arraycopy(listaZonas, 0, listaZonasFinal, 1, listaZonas.length);
		}
		
		
		this.siccZonaList=listaZonasFinal;
		this.siccRegionList=ajaxService.getRegionesAllDirectorioMantenimientoZON(pais.getCodigo(), pais.getCodigoConexionExterna(), Constants.NUMERO_CERO, "ASD");
		this.zonIndiResuDeta=f.getResumenDetalle();//Indicador Resumen/Detalle
		criteria.put("idCargo", f.getCodigoCargo());	
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
		
		ConsultaZONDirectorioVentasForm f = (ConsultaZONDirectorioVentasForm) this.formBusqueda;
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
				
				ConsultaZONDirectorioVentasForm f = (ConsultaZONDirectorioVentasForm) this.formBusqueda;				
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
	
	@Override
	protected void setSalirPopup() {
		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;
		this.busquedaConsultoraPOPUPSearchAction.setBeanRegistroSeleccionado(null);
	}

	public List getZonMantCargList() {
		return zonMantCargList;
	}

	public void setZonMantCargList(List zonMantCargList) {
		this.zonMantCargList = zonMantCargList;
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

	public LabelValue[] getZonEstadosList() {
		return zonEstadosList;
	}

	public void setZonEstadosList(LabelValue[] zonEstadosList) {
		this.zonEstadosList = zonEstadosList;
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

	public String getZonIndiResuDeta() {
		return zonIndiResuDeta;
	}

	public void setZonIndiResuDeta(String zonIndiResuDeta) {
		this.zonIndiResuDeta = zonIndiResuDeta;
	}

	public String getValidarBarrio() {
		return validarBarrio;
	}

	public void setValidarBarrio(String validarBarrio) {
		this.validarBarrio = validarBarrio;
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
