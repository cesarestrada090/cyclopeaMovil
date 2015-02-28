package biz.belcorp.ssicc.web.spusicc.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.AccesoCanal;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.Cliente;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPERActualizarPercepcionesConsolidado;
import biz.belcorp.ssicc.dao.sisicc.model.Subacceso;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.MantenimientoPERPercepcionesOtrosCanalesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultoraPOPUPSearchAction;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoPERPercepcionesOtrosCanalesForm;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoPERPercepcionesOtrosCanalesSearchForm;


/**
 * The Class MantenimientoPERPercepcionesOtrosCanalesSearchAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 13/01/2015
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@ManagedBean
@SessionScoped
public class MantenimientoPERPercepcionesOtrosCanalesSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 8692882631929698023L;
	private List siccSociedadList;
	private List siccCanalList;
	private List siccAccesoList;
	private List siccSubaccesoList;
	private List siccTipoClienteList;
	private List siccTipoDocumentoList;
	private List siccTipoComprobanteList;
	private List consolidadosOtrosCanalesList;
	private Integer longitudCampoClientes;
	
    private static final String POPUP_CONSULTORA = "CONSULTORA";
    private static final String POPUP_CONSULTORA_SEARCH = "CONSULTORA_SEARCH";
	private boolean mostrarPopupConsultora;
	
	@ManagedProperty(value="#{busquedaConsultoraPOPUPSearchAction}")
	private BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoPERPercepcionesOtrosCanalesForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoPERPercepcionesOtrosCanalesSearchForm searchForm = new MantenimientoPERPercepcionesOtrosCanalesSearchForm();
		return searchForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}

		String codigoPais = this.mPantallaPrincipalBean.getCountryCode();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		MantenimientoPERPercepcionesOtrosCanalesSearchForm f = (MantenimientoPERPercepcionesOtrosCanalesSearchForm) this.formBusqueda;

		// Cargamos los combos a utilizar
		InterfazSiCCService siccService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		this.siccSociedadList = siccService.getSociedadesByCodigoPais(codigoPais);
		this.siccCanalList = siccService.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		//this.siccAccesoList = siccService.getAccesosByCanalByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccSubaccesoList = siccService.getSubaccesosByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccTipoClienteList = siccService.getTiposClientesByCodigoISO(usuario.getIdioma().getCodigoISO());

		log.debug("Hasta aca todo ok...");

		f.setCodigoPais(codigoPais);

		log.debug("A punto de enviar...");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@SuppressWarnings("unused")
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'find' method");
		}
		
		String codigoPais = this.mPantallaPrincipalBean.getCountryCode();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoPERPercepcionesOtrosCanalesSearchForm f = (MantenimientoPERPercepcionesOtrosCanalesSearchForm) this.formBusqueda;
		
		if(!enviarFormulario(f)) {
			throw new Exception(this.getResourceMessage("mantenimientoPERPercepcionesOtrosCanalesSearchForm.nohayseleccion.busqueda"));
		}
		
		MantenimientoPERPercepcionesOtrosCanalesService service = (MantenimientoPERPercepcionesOtrosCanalesService) getBean("spusicc.mantenimientoPERPercepcionesOtrosCanalesService");
		InterfazPERActualizarPercepcionesConsolidado consolidado = new InterfazPERActualizarPercepcionesConsolidado();
		BeanUtils.copyProperties(consolidado, f);
	
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoSociedad", f.getCodigoSociedad());
		criteria.put("codigoCanal", f.getCodigoCanal());
		criteria.put("codigoAcceso", f.getCodigoAcceso());
		criteria.put("codigoSubacceso", f.getCodigoSubacceso());
		criteria.put("tipoCliente", f.getTipoCliente());
		criteria.put("codigoCliente", f.getCodigoClienteBuscar());
		criteria.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
		criteria.put("serieComproPercepcion", f.getSerieComproPercepcion());
		criteria.put("numeroComproPercepcion", f.getNumeroComproPercepcion());
		if(f.getFechaEmisionComprobantePercepcionD() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			f.setFechaEmisionComprobantePercepcion(sdf.format(f.getFechaEmisionComprobantePercepcionD()));
		}		
		criteria.put("fechaEmisionComprobantePercepcion", f.getFechaEmisionComprobantePercepcion());
		
		log.debug("criteria a buscar " + criteria);
		List resultado = service.getPercepcionesOtrosCanales(criteria);
		log.debug("Cantidad de resultado " + resultado.size());
		this.consolidadosOtrosCanalesList = resultado;
		if (resultado == null) {
			throw new Exception(this.getResourceMessage("errors.datos.fuentes.busquedar"));
		}
		return resultado;
	}
	
	private boolean enviarFormulario(MantenimientoPERPercepcionesOtrosCanalesSearchForm f) {
		boolean estado = false;
		if(f.getCodigoSociedad() != null && !f.getCodigoSociedad().isEmpty()) {
			estado = true;
		}
		if(f.getCodigoCanal() != null && !f.getCodigoCanal().isEmpty()) {
			estado = true;
		}
		if(f.getCodigoAcceso() != null && !f.getCodigoAcceso().isEmpty()) {
			estado = true;
		}
		if(f.getCodigoSubacceso() != null && !f.getCodigoSubacceso().isEmpty()) {
			estado = true;
		}
		if(f.getTipoCliente() != null && !f.getTipoCliente().isEmpty()) {
			estado = true;
		}
		if(f.getSerieComproPercepcion() != null && !f.getSerieComproPercepcion().isEmpty()) {
			estado = true;
		}
		if(f.getNumeroComproPercepcion() != null && !f.getNumeroComproPercepcion().isEmpty()) {
			estado = true;
		}
		if(f.getCodigoClienteBuscar() != null && !f.getCodigoClienteBuscar().isEmpty()) {
			estado = true;
		}
		return estado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {	
		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete' method");
		}
		MantenimientoPERPercepcionesOtrosCanalesService service = (MantenimientoPERPercepcionesOtrosCanalesService) getBean("spusicc.mantenimientoPERPercepcionesOtrosCanalesService");
		String codigoPais = this.mPantallaPrincipalBean.getCountryCode();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();		
		Map mapa = (Map) this.beanRegistroSeleccionado;	
		String id = mapa.get("correlativo").toString();
		InterfazPERActualizarPercepcionesConsolidado consolidado = new InterfazPERActualizarPercepcionesConsolidado();
		if (id != null) {
			if (log.isDebugEnabled()) {
				log.debug("Id seleccionado de la lista: " + id);
			}		
			consolidado.setCodigoPais(codigoPais);
			consolidado.setEstado(Constants.ESTADO_INACTIVO);
			consolidado.setCorrelativoComprobantePercepcion(id);			
			service.updatePercepcionesOtrosCanales(consolidado, usuario);
			this.getResourceMessage("consolidado.deleted");
		}
		return true;
	}

	@Override
	protected String getSalirForward() {
		return "mantenimientoPERPercepcionesOtrosCanalesList";
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}
		MantenimientoPERPercepcionesOtrosCanalesForm f = (MantenimientoPERPercepcionesOtrosCanalesForm) this.formMantenimiento;		
		MantenimientoPERPercepcionesOtrosCanalesService service = (MantenimientoPERPercepcionesOtrosCanalesService) getBean("spusicc.mantenimientoPERPercepcionesOtrosCanalesService");
		String codigoPais = this.mPantallaPrincipalBean.getCountryCode();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazPERActualizarPercepcionesConsolidado consolidado = new InterfazPERActualizarPercepcionesConsolidado();
		BeanUtils.copyProperties(consolidado, f);
		consolidado.setEstado(Constants.ESTADO_ACTIVO);
		consolidado.setCodigoCliente(f.getCodigoConsultora());
		log.debug("Mostramos el consolidadoBean " + consolidado.toString());
		if (!f.isNewRecord()) {
			if (log.isDebugEnabled()) {
				log.debug("EN EL CASO QUE SEA MODIFICACION");
			}
			service.updatePercepcionesOtrosCanales(consolidado, usuario);
		} else {
			if (log.isDebugEnabled()) {
				log.debug("EN EL CASO QUE SEA CREACION");
			}
			long correlativo = Long.parseLong(service.getNextCorrelativo(codigoPais));
			log.debug("Correlativo " + correlativo);
			consolidado.setCorrelativoComprobantePercepcion(""+ correlativo);
			service.insertInterfazPERActualizarPercepcionesConsolidado(consolidado, usuario);		
		}
		log.debug(f);
		return true;
	}
	
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Map mapa = (Map) this.beanRegistroSeleccionado;	
		MantenimientoPERPercepcionesOtrosCanalesForm mantenimientoForm = new MantenimientoPERPercepcionesOtrosCanalesForm();

		String codigoPais = this.mPantallaPrincipalBean.getCountryCode();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		// Cargamos los combos a utilizar
		InterfazSiCCService siccService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		MantenimientoPERPercepcionesOtrosCanalesService service = (MantenimientoPERPercepcionesOtrosCanalesService) getBean("spusicc.mantenimientoPERPercepcionesOtrosCanalesService");
		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");

		Map criteria = new HashMap();
		criteria.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
		criteria.put("codigoPais", codigoPais);
		this.siccTipoDocumentoList = siccService.getTipoDocumentosByCodigoISO(criteria);
		this.siccTipoComprobanteList = siccService.getTipoComprobantesPago();
		this.longitudCampoClientes = clienteService.getTamanhoNumeroCliente(codigoPais);
		
		// Si el id ha sido enviado, buscamos la informacion
		// en caso contrario, no hacemos nada, se esta insertando
		// un nuevo registro a la aplicaci�n
		if (!this.accion.equals(this.ACCION_NUEVO) ) {
			String id = mapa.get("correlativo").toString();
			if (log.isDebugEnabled()) {
				log.debug("Id seleccionado de la lista: " + id);
			}
			InterfazPERActualizarPercepcionesConsolidado consolidado = new InterfazPERActualizarPercepcionesConsolidado();
			consolidado.setCodigoPais(codigoPais);
			consolidado.setCorrelativoComprobantePercepcion(id);
			mantenimientoForm.setCorrelativoComprobantePercepcion(id);			
			List resultado = service.getConsolidadoPercepcion(consolidado);
			if (resultado.size() == 1) {
				consolidado = (InterfazPERActualizarPercepcionesConsolidado) resultado.get(0);
				log.debug("Mostramos el consolidadoBean " + consolidado.toString());
				// Copiamos los atributos del bean al form
				BeanUtils.copyProperties(mantenimientoForm, consolidado);
				
				// Obteniendo el SubAcceso
				Map criteriaSubAcceso = new HashMap();
				criteriaSubAcceso.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
				criteriaSubAcceso.put("codigoAcceso", mantenimientoForm.getCodigoAcceso());
				this.siccSubaccesoList = siccService.getSubaccesosByCriteria(criteriaSubAcceso);
				
				mantenimientoForm.setCodigoCanal(mapa.get("codigoCanal").toString());
				getAcceso(mapa.get("codigoCanal").toString());
				mantenimientoForm.setCodigoAcceso(mapa.get("codigoAcceso").toString());
				getSubAcceso(mapa.get("codigoAcceso").toString());				
				
				mantenimientoForm.setTipoCliente(consolidado.getTipoCliente());
				mantenimientoForm.setCodigoConsultora(consolidado.getCodigoCliente());
				if(StringUtils.isNotEmpty(consolidado.getCodigoCliente())) {
					String[] consultora = ajaxService.getConsultoraByCriteria(consolidado.getCodigoPais(),
																			  consolidado.getCodigoCliente());
					if (consultora != null) {
						mantenimientoForm.setDescripcionConsultora(consultora[1]);
					}
				}	
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(mapa.get("fechaEmisionComprobantePercepcion") != null) {
				mantenimientoForm.setFechaEmisionComprobantePercepcion(sdf.parse(mapa.get("fechaEmisionComprobantePercepcion").toString()));
			}
						
			mantenimientoForm.setNewRecord(false);
		} else {
			Base canal = (Base) this.siccCanalList.get(0);
			getAcceso(canal.getCodigo());			
			
			mantenimientoForm.setCodigoPais(codigoPais);
			mantenimientoForm.setNewRecord(true);
			Date fechaActual = new Date(System.currentTimeMillis());
			mantenimientoForm.setFechaEmisionComprobantePercepcion(fechaActual);
			mantenimientoForm.setNewRecord(true);
		}				
        return mantenimientoForm;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoPERPercepcionesOtrosCanalesForm mantenimientoForm = (MantenimientoPERPercepcionesOtrosCanalesForm) this.formMantenimiento;
		boolean isNew = mantenimientoForm.isNewRecord();
		if(isNew) {
			return "consolidado.added";
		}else{
			return "consolidado.updated";
		}	
	}

	/**
	 * setAcceso.
	 *
	 * @param val the val
	 */
	public void setAcceso(ValueChangeEvent val) {
		log.debug(">>setAcceso - val: "+val.getNewValue().toString());
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService siccService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		List<AccesoCanal> accesos = siccService.getAccesosByCanalByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccAccesoList = new ArrayList();
		for(AccesoCanal acceso : accesos) {
			if(val.getNewValue().toString().equals(acceso.getCodigoCanal())) {
				this.siccAccesoList.add(acceso);
			}			
		}		
	}
	
	/**
	 * getAcceso.
	 *
	 * @param val the val
	 */
	public void getAcceso(String val) {
		log.debug(">>getAcceso - val: "+val);
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService siccService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		List<AccesoCanal> accesos = siccService.getAccesosByCanalByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccAccesoList = new ArrayList();
		for(AccesoCanal acceso : accesos) {
			if(val.equals(acceso.getCodigoCanal())) {
				this.siccAccesoList.add(acceso);
			}			
		}		
	}
	
	/**
	 * cargarListaSubaccesos.
	 *
	 * @param val the val
	 */
	public void cargarListaSubaccesos(ValueChangeEvent val){
		log.debug(">>cargarListaSubaccesos - val: "+val.getNewValue().toString());
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService siccService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		List<Subacceso> subAccesos = siccService.getSubaccesosByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccSubaccesoList = new ArrayList();
		for(Subacceso subacceso : subAccesos) {
			if(val.getNewValue().toString().equals(subacceso.getCodigoAcceso())) {
				this.siccSubaccesoList.add(subacceso);
			}			
		}	
	}
	
	/**
	 * getSubAcceso.
	 *
	 * @param val the val
	 */
	public void getSubAcceso(String val){
		log.debug(">>getSubAcceso - val: "+val);
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService siccService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		List<Subacceso> subAccesos = siccService.getSubaccesosByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccSubaccesoList = new ArrayList();
		for(Subacceso subacceso : subAccesos) {
			if(val.equals(subacceso.getCodigoAcceso())) {
				this.siccSubaccesoList.add(subacceso);
			}			
		}	
	}


	@SuppressWarnings("static-access")
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopupHipCliente' method");
		}
		if (accion.equals(this.POPUP_CONSULTORA)) {
			this.busquedaConsultoraPOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaConsultoraPOPUPSearchAction.isSeleccionoRegistro()) {
				Cliente cliente = (Cliente) this.busquedaConsultoraPOPUPSearchAction.getBeanRegistroSeleccionado();
				MantenimientoPERPercepcionesOtrosCanalesForm f = (MantenimientoPERPercepcionesOtrosCanalesForm) this.formMantenimiento;
				f.setCodigoConsultora(cliente.getCodigo());
				this.busquedaConsultoraPOPUPSearchAction.setBeanRegistroSeleccionado(null);
			}
		} else if (accion.equals(this.POPUP_CONSULTORA_SEARCH)) {
			this.busquedaConsultoraPOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaConsultoraPOPUPSearchAction.isSeleccionoRegistro()) {
				Cliente cliente = (Cliente) this.busquedaConsultoraPOPUPSearchAction.getBeanRegistroSeleccionado();
				MantenimientoPERPercepcionesOtrosCanalesSearchForm f = (MantenimientoPERPercepcionesOtrosCanalesSearchForm) this.formBusqueda;
				f.setCodigoCliente(cliente.getCodigo());
				this.busquedaConsultoraPOPUPSearchAction.setBeanRegistroSeleccionado(null);
			}
		}
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setSalirPopup()
	 */
	protected void setSalirPopup() {
		this.mostrarPopupConsultora = false;
		this.busquedaConsultoraPOPUPSearchAction.setBeanRegistroSeleccionado(null);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setInvocarPopup(java.lang.String)
	 */
	@SuppressWarnings("static-access")
	protected void setInvocarPopup(String accion) {
		this.mostrarProcesoBatch = false;
		if (accion.equals(this.POPUP_CONSULTORA)){ 
			this.mostrarPopupConsultora = true;
		} else if(accion.equals(this.POPUP_CONSULTORA_SEARCH)){ 
			this.mostrarPopupConsultora = true;
		}
	}
	
	/**
	 * @return the siccSociedadList
	 */
	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	/**
	 * @param siccSociedadList the siccSociedadList to set
	 */
	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * @return the siccAccesoList
	 */
	public List getSiccAccesoList() {
		return siccAccesoList;
	}

	/**
	 * @param siccAccesoList the siccAccesoList to set
	 */
	public void setSiccAccesoList(List siccAccesoList) {
		this.siccAccesoList = siccAccesoList;
	}

	/**
	 * @return the siccSubaccesoList
	 */
	public List getSiccSubaccesoList() {
		return siccSubaccesoList;
	}

	/**
	 * @param siccSubaccesoList the siccSubaccesoList to set
	 */
	public void setSiccSubaccesoList(List siccSubaccesoList) {
		this.siccSubaccesoList = siccSubaccesoList;
	}

	/**
	 * @return the siccTipoClienteList
	 */
	public List getSiccTipoClienteList() {
		return siccTipoClienteList;
	}

	/**
	 * @param siccTipoClienteList the siccTipoClienteList to set
	 */
	public void setSiccTipoClienteList(List siccTipoClienteList) {
		this.siccTipoClienteList = siccTipoClienteList;
	}

	/**
	 * @return the siccTipoDocumentoList
	 */
	public List getSiccTipoDocumentoList() {
		return siccTipoDocumentoList;
	}

	/**
	 * @param siccTipoDocumentoList the siccTipoDocumentoList to set
	 */
	public void setSiccTipoDocumentoList(List siccTipoDocumentoList) {
		this.siccTipoDocumentoList = siccTipoDocumentoList;
	}

	/**
	 * @return the siccTipoComprobanteList
	 */
	public List getSiccTipoComprobanteList() {
		return siccTipoComprobanteList;
	}

	/**
	 * @param siccTipoComprobanteList the siccTipoComprobanteList to set
	 */
	public void setSiccTipoComprobanteList(List siccTipoComprobanteList) {
		this.siccTipoComprobanteList = siccTipoComprobanteList;
	}

	/**
	 * @return the longitudCampoClientes
	 */
	public Integer getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	/**
	 * @param longitudCampoClientes the longitudCampoClientes to set
	 */
	public void setLongitudCampoClientes(Integer longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}

	/**
	 * @return the consolidadosOtrosCanalesList
	 */
	public List getConsolidadosOtrosCanalesList() {
		return consolidadosOtrosCanalesList;
	}

	/**
	 * @param consolidadosOtrosCanalesList the consolidadosOtrosCanalesList to set
	 */
	public void setConsolidadosOtrosCanalesList(List consolidadosOtrosCanalesList) {
		this.consolidadosOtrosCanalesList = consolidadosOtrosCanalesList;
	}

	/**
	 * @return the mostrarPopupConsultora
	 */
	public boolean isMostrarPopupConsultora() {
		return mostrarPopupConsultora;
	}

	/**
	 * @param mostrarPopupConsultora the mostrarPopupConsultora to set
	 */
	public void setMostrarPopupConsultora(boolean mostrarPopupConsultora) {
		this.mostrarPopupConsultora = mostrarPopupConsultora;
	}


	/**
	 * @return the busquedaConsultoraPOPUPSearchAction
	 */
	public BusquedaConsultoraPOPUPSearchAction getBusquedaConsultoraPOPUPSearchAction() {
		return busquedaConsultoraPOPUPSearchAction;
	}

	/**
	 * @param busquedaConsultoraPOPUPSearchAction the busquedaConsultoraPOPUPSearchAction to set
	 */
	public void setBusquedaConsultoraPOPUPSearchAction(
			BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction) {
		this.busquedaConsultoraPOPUPSearchAction = busquedaConsultoraPOPUPSearchAction;
	}
	
	

}
