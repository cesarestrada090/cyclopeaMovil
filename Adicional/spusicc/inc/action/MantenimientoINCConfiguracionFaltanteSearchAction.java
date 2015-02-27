package biz.belcorp.ssicc.web.spusicc.inc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionFaltanteService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultorasAction;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCConfiguracionFaltanteSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoINCConfiguracionFaltanteSearchAction extends BaseMantenimientoSearchAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2617695727338083138L;
	private List siccConcursoList;
	private List siccRegionList;
	private List incFaltantesList;
	private LabelValue[] siccPremioList = {};
	private LabelValue[] siccZonaList = {};
	
	private boolean mostrarPopUpCliente = false;
	private static final String POPUP_CLIENTES = "SCLIENTES";
	
	private String oidPremioElegido;
	private String oidZonaElegido;
	
	@ManagedProperty(value = "#{busquedaConsultorasAction}")
	private BusquedaConsultorasAction busquedaConsultorasAction;

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
	protected BaseSearchForm devuelveFormBusqueda() throws Exception 
	{
		MantenimientoINCConfiguracionFaltanteSearchForm searchForm = new MantenimientoINCConfiguracionFaltanteSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception 
	{		
		MantenimientoINCConfiguracionFaltanteService service = (MantenimientoINCConfiguracionFaltanteService) 
														getBean("spusicc.mantenimientoINCConfiguracionFaltanteService");
		MantenimientoINCConfiguracionFaltanteSearchForm f = (MantenimientoINCConfiguracionFaltanteSearchForm) this.formBusqueda;
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		
		if(f.getOidConcurso()!=null && !f.getOidConcurso()[0].equals(""))
			criteria.put("oidConcurso", f.getOidConcurso());
		
		if(f.getOidRegion()!=null && !f.getOidRegion()[0].equals(""))
			criteria.put("oidRegion", f.getOidRegion());
		
		if(f.getOidZona()!=null && !f.getOidZona()[0].equals("")) {
			criteria.put("oidZona", f.getOidZona());
			
			String oidZonaElegido = "";
			for(int i=0; i<f.getOidZona().length; i++) {
				if(i==0) 
					oidZonaElegido = f.getOidZona()[i];
				else 			
					oidZonaElegido = oidZonaElegido + "&&" + f.getOidZona()[i];
			}
			this.oidZonaElegido = oidZonaElegido;
		} else {
			this.oidZonaElegido = "";
		}
		
		if(f.getOidPremio()!=null && !f.getOidPremio()[0].equals("")) {
			String[] oidProducto = new String[f.getOidPremio().length];
			String[] oidCUV = new String[f.getOidPremio().length];
			
			for(int i=0; i<f.getOidPremio().length; i++) {
				oidProducto[i]=f.getOidPremio()[i].split("__")[0];
				oidCUV[i]=f.getOidPremio()[i].split("__")[1];
			}
			
			criteria.put("oidProducto", oidProducto);
			criteria.put("oidCUV", oidCUV);
			
			String oidPremioElegido = "";
			for(int i=0; i<f.getOidPremio().length; i++) {
				if(i==0) 
					oidPremioElegido = f.getOidPremio()[i];
				else 			
					oidPremioElegido = oidPremioElegido + "&&" + f.getOidPremio()[i];
			}
			this.oidPremioElegido = oidPremioElegido;
		} else {
			this.oidPremioElegido = "";
		}	
		
		if(!"".equals(f.getCodigoCliente()))
			criteria.put("codigoCliente", f.getCodigoCliente());

		criteria.put("oidPeriodo", f.getOidPeriodo());
		
		List lista = service.getFaltantes(criteria);
		this.incFaltantesList = lista;		
		
		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;		
	
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoINCConfiguracionFaltanteService service = (MantenimientoINCConfiguracionFaltanteService) 
										getBean("spusicc.mantenimientoINCConfiguracionFaltanteService");
		MantenimientoINCConfiguracionFaltanteSearchForm f = (MantenimientoINCConfiguracionFaltanteSearchForm) this.formBusqueda; 
		
		f.setOidPais(null);
		f.setOidConcurso(null);
		f.setOidPremio(null);
		f.setOidRegion(null);
		f.setOidZona(null);
		f.setCodigoCliente("");
		f.setNombreCliente("");
		f.setOidPeriodo(null);		
		
		f.setCodigoPais(pais.getCodigo());
		
		//Obtenemos oid Periodo Proceso
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
        
		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(criteria);
		
		ReporteService reporteService = (ReporteService)getBean("scsicc.reporteService");
		criteria.put("codigoPeriodo", controlFacturacion.getCodigoPeriodo());
		String oidPeriodo =reporteService.getOidString("getOidPeriodoByCodigoPeriodo",criteria);
		f.setOidPeriodo(oidPeriodo);
		criteria.put("oidPeriodo", oidPeriodo);

		Base baseTodos = new Base();
		baseTodos.setCodigo("");
		baseTodos.setDescripcion(Constants.OPCION_TODOS);

		//combo concursos
		List listConcursos =service.getConcursosFaltante(criteria);
		if(listConcursos.size() > 0) { 
			listConcursos.add(0, baseTodos);
			f.setOidConcurso(new String[]{""});
		}
		
		//combo regiones
		List listRegiones = service.getRegionesFaltante(criteria);
		if(listRegiones.size() > 0) {
			listRegiones.add(0, baseTodos);
			f.setOidRegion(new String[]{""});
		}	
		
		this.siccConcursoList = listConcursos;
		this.siccRegionList = listRegiones;
		
		//Obtenemos oid Pais
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) 
				getBean("spusicc.mantenimientoMAEClienteService");
		String oidPais = clienteService.getOidPais(criteria);
		f.setOidPais(oidPais);		
	}
	
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) 
	{
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}
		if (accion.equals(this.POPUP_CLIENTES)) 
		{
			this.busquedaConsultorasAction.verificarRegistro(event);
			if (this.busquedaConsultorasAction.isSeleccionoRegistro()) 
			{
				Map clienteHipMap = (Map) this.busquedaConsultorasAction.getBeanRegistroSeleccionado();
				MantenimientoINCConfiguracionFaltanteSearchForm f = (MantenimientoINCConfiguracionFaltanteSearchForm) this.formBusqueda;			
				
				f.setCodigoCliente((String)clienteHipMap.get("codigoCliente") );
				f.setNombreCliente((String)clienteHipMap.get("apellido1")+" "+(String)clienteHipMap.get("apellido2")+" "+
						(String)clienteHipMap.get("nombre1")+" "+(String)clienteHipMap.get("nombre2"));
				this.busquedaConsultorasAction.setBeanRegistroSeleccionado(null);
			}
		}
	}
	
	@Override
	protected void setSalirPopup()
	{
		this.mostrarPopUpCliente = false;
		this.busquedaConsultorasAction.setBeanRegistroSeleccionado(null);
	}
	
	@Override
	protected void setInvocarPopup(String accion) {
		this.mostrarProcesoBatch = false;

		if (accion.equals(this.POPUP_CLIENTES)) {
			this.mostrarPopUpCliente = true;
		}
	}
	
	public void actualizar(ActionEvent event)
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'actualizar()' method");
		}		
			
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		// obtiene el valor del parametro
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String indicadorCambiarPedido = externalContext.getRequestParameterMap().get("parametroAccion");
		
		try {							
			MantenimientoINCConfiguracionFaltanteService service = (MantenimientoINCConfiguracionFaltanteService) 
										getBean("spusicc.mantenimientoINCConfiguracionFaltanteService");
							
			List list = incFaltantesList;
			List listOids = new ArrayList();
			int total = 0;
			
			for(int i=0; i<list.size(); i++) {
				Map mapFaltante = (Map)list.get(i);
				
				String indicadorDespachoServicio = (String)mapFaltante.get("indicadorDespachoServicio");
				
				if(indicadorCambiarPedido.equals("1")) { //Cambiar a Pedido
					if(!"0".equalsIgnoreCase(indicadorDespachoServicio)) {
						Map mapAux = new HashMap();
						mapAux.put("oidFaltante", mapFaltante.get("oidFaltante"));
						mapAux.put("indicadorDespachoServicio", "0");
						mapAux.put("codigoUsuario", usuario.getLogin());
						
						total++;
						listOids.add(mapAux);
					}
				} else { //Cambiar a Solicitud de Servicio
					if(!"1".equalsIgnoreCase(indicadorDespachoServicio)) {
						Map mapAux = new HashMap();
						mapAux.put("oidFaltante", mapFaltante.get("oidFaltante"));
						mapAux.put("indicadorDespachoServicio", "1");
						mapAux.put("codigoUsuario", usuario.getLogin());
						
						total++;
						listOids.add(mapAux);
					}
				}	
			}
			
			service.updateFaltantes(listOids);
			
			//enviamos el mensaje de satisfaccion
			this.addInfo("", this.getResourceMessage("mantenimientoINCConfiguracionFaltanteSearchForm.msg.ok", new Object[]{total}));						
		}catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
			this.addError("", this.getResourceMessage("errors.detail",new Object[]{error}));	
		}		
	}
	
	public void loadPremios(ValueChangeEvent e)
	{
		String[] valorConcurso = (String[])e.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		MantenimientoINCConfiguracionFaltanteSearchForm f = (MantenimientoINCConfiguracionFaltanteSearchForm) this.formBusqueda;
		int salir = 1;
		
		for (String concurso : valorConcurso) 
		{
			if(concurso == "Todos")
			{
				salir = 1;
				break;
			}			
			salir = 0;		
		}
		
		if(salir == 0 )
			this.siccPremioList = ajax.getPremiosFaltante(valorConcurso, f.getOidPeriodo(), Constants.TODAS);		
	}
	
	public void loadZonas(ValueChangeEvent e)
	{
		String[] valorRegion = (String[])e.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		MantenimientoINCConfiguracionFaltanteSearchForm f = (MantenimientoINCConfiguracionFaltanteSearchForm) this.formBusqueda;
		int salir = 1;
		
		for (String region : valorRegion) 
		{
			if(region == "Todos")
			{
				salir = 1;
				break;
			}			
			salir = 0;				
		}
		
		if (salir==0)
			this.siccZonaList = ajax.getZonasFaltante(valorRegion, f.getOidPeriodo(), Constants.TODAS);
	}
	
	public void validaCodigoCliente(AjaxBehaviorEvent e)
	{
		String valor = (String) ((UIOutput)e.getSource()).getValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		MantenimientoINCConfiguracionFaltanteSearchForm f = (MantenimientoINCConfiguracionFaltanteSearchForm) this.formBusqueda;
		
		if(valor != null)
		{
			if(valor.length() > 0)
			{
				String resultado = ajax.getExisteCodigoCliente(f.getOidPais(), valor);
				if(resultado.length() > 0)
				{
					f.setCodigoCliente(resultado.split("\\|")[0]);
					f.setNombreCliente(resultado.split("\\|")[1]);					
				}else
				{
					f.setNombreCliente("");	
				}
			}else
			{
				f.setNombreCliente("");	
			}
		}
	}

	public List getSiccConcursoList() {
		return siccConcursoList;
	}

	public void setSiccConcursoList(List siccConcursoList) {
		this.siccConcursoList = siccConcursoList;
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public String getOidPremioElegido() {
		return oidPremioElegido;
	}

	public void setOidPremioElegido(String oidPremioElegido) {
		this.oidPremioElegido = oidPremioElegido;
	}

	public String getOidZonaElegido() {
		return oidZonaElegido;
	}

	public void setOidZonaElegido(String oidZonaElegido) {
		this.oidZonaElegido = oidZonaElegido;
	}

	public List getIncFaltantesList() {
		return incFaltantesList;
	}

	public void setIncFaltantesList(List incFaltantesList) {
		this.incFaltantesList = incFaltantesList;
	}

	public boolean isMostrarPopUpCliente() {
		return mostrarPopUpCliente;
	}

	public void setMostrarPopUpCliente(boolean mostrarPopUpCliente) {
		this.mostrarPopUpCliente = mostrarPopUpCliente;
	}

	public BusquedaConsultorasAction getBusquedaConsultorasAction() {
		return busquedaConsultorasAction;
	}

	public void setBusquedaConsultorasAction(
			BusquedaConsultorasAction busquedaConsultorasAction) {
		this.busquedaConsultorasAction = busquedaConsultorasAction;
	}

	public static String getPopupClientes() {
		return POPUP_CLIENTES;
	}

	public LabelValue[] getSiccPremioList() {
		return siccPremioList;
	}

	public void setSiccPremioList(LabelValue[] siccPremioList) {
		this.siccPremioList = siccPremioList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}
	
}
