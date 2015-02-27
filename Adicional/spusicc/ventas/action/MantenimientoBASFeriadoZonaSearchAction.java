package biz.belcorp.ssicc.web.spusicc.ventas.action;

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

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.FeriadoZona;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.GrupoZona;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.spusicc.ventas.ProcesoVENService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaZonaPOPUPSearchAction;
import biz.belcorp.ssicc.web.spusicc.ventas.form.MantenimientoBASFeriadoZonaForm;
import biz.belcorp.ssicc.web.spusicc.ventas.form.MantenimientoBASFeriadoZonaSearchForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoBASFeriadoZonaSearchAction.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar </a>
 */

@ManagedBean
@SessionScoped
public class MantenimientoBASFeriadoZonaSearchAction extends BaseMantenimientoSearchAbstractAction {
		
	private static final long serialVersionUID = -6040884360207272482L;
	private boolean mostrarPopupZona = false;
	private static final String POPUP_ZONA = "ZONA";
	
	
	@ManagedProperty(value="#{busquedaZonaPOPUPSearchAction}")
	private BusquedaZonaPOPUPSearchAction busquedaZonaPOPUPSearchAction;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoBASFeriadoZonaForm";
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setInvocarPopup(java.lang.String)
	 */
	@Override
	protected void setInvocarPopup(String accion) {
		this.mostrarProcesoBatch = false;
		if (accion.equals(this.POPUP_ZONA)) 
			this.mostrarPopupZona = true;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopup' method");
		}
		this.mostrarProcesoBatch = true;
		if (accion.equals(this.POPUP_ZONA)) {
			this.mostrarPopupZona = false;
			this.busquedaZonaPOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaZonaPOPUPSearchAction.isSeleccionoRegistro()) {
				GrupoZona bgrupozona = (GrupoZona)this.busquedaZonaPOPUPSearchAction.getBeanRegistroSeleccionado();
				MantenimientoBASFeriadoZonaForm mante = (MantenimientoBASFeriadoZonaForm)this.formMantenimiento;
				mante.setCodigoZona(bgrupozona.getCodigoZona());
				this.formMantenimiento = mante;
				this.busquedaZonaPOPUPSearchAction.setBeanRegistroSeleccionado(null);
			}
		}	
		if (log.isDebugEnabled()) {
			log.debug("Finish 'retornarPopupZona' method");
		}
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setSalirPopup()
	 */
	@Override
	protected void setSalirPopup() {
		this.mostrarPopupZona = false;
		this.busquedaZonaPOPUPSearchAction.setBeanRegistroSeleccionado(null);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {		
		MantenimientoBASFeriadoZonaSearchForm mantenimientoBASFeriadoZonaSearchForm = new MantenimientoBASFeriadoZonaSearchForm();
		return mantenimientoBASFeriadoZonaSearchForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'find' method");
		}
		
		MantenimientoBASFeriadoZonaSearchForm searchForm = (MantenimientoBASFeriadoZonaSearchForm)this.formBusqueda;
		
		Map criteria = BeanUtils.describe(searchForm);
		ProcesoVENService service = (ProcesoVENService)this.getBeanService("spusicc.procesoVENService");				
				
		List resultado = service.getFeriadoZona(criteria);

		return resultado;

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return true;		
	}

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonConsultar = false;		
	}

	@Override
	protected String getSalirForward() {		
		return "mantenimientoBASFeriadoZonaList";
	}
    
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoBASFeriadoZonaForm f = (MantenimientoBASFeriadoZonaForm)this.formMantenimiento;
		boolean isNew = f.isNewRecord();
		if(isNew){
			return "sistema.added";
		}else{
			return "sistema.updated";
		}
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method");
		}

		// Extraemos atributos y parámetros a usar		
		MantenimientoBASFeriadoZonaForm f = (MantenimientoBASFeriadoZonaForm)this.formMantenimiento;
										
		// Extreamos el usuario de la sesión
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();				
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
				
		// Creamos la instancia del servicio y le asignamos
		// el usuario que va a realizar las operaciones
		ProcesoVENService service = (ProcesoVENService)this.getBeanService("spusicc.procesoVENService"); 
				
		FeriadoZona feriadoZona = new FeriadoZona();
		feriadoZona.setCodigoAnio(f.getCodigoAnio());
		feriadoZona.setCodigoZona(f.getCodigoZona());
		feriadoZona.setDescripcion(f.getDescripcion());
		feriadoZona.setFechaCalendario(DateUtil.convertStringToDate(f.getFechaCalendario()));
				
		String feriadoRegion = f.getCodigoRegion();
		
		try {
			
			if (!f.isNewRecord()) { //Modificacion
				service.updateFeriadoZona(feriadoZona, usuario);				
			}
			else { 
				List codigoZonas = new ArrayList();
				if (StringUtils.isBlank(feriadoRegion))
					codigoZonas.add(f.getCodigoZona());									
				else
					codigoZonas = service.getZonasRegion(feriadoRegion);	
											
				for (int i=0;i<codigoZonas.size();i++){					
					String anio = DateUtil.convertDateToString(feriadoZona.getFechaCalendario()).substring(6,10);
					feriadoZona.setCodigoAnio(anio);
						
					/* Verificando que no se haya insertado previamente */
					Map criteria = new HashMap();
					String fecha = DateUtil.convertDateToString(feriadoZona.getFechaCalendario());
					criteria.put("codigoAnio", anio);
					criteria.put("fechaCalendario", fecha);
					criteria.put("codigoPais", pais.getCodigo());				
					criteria.put("codigoZona", codigoZonas.get(i));
						
					List resultado = service.getFeriadoZona(criteria);
					
					if (StringUtils.isBlank(feriadoRegion)){					
						if (resultado.size() > 0) {
							this.addError("Error: ", this.getResourceMessage("errors.FeriadoZonaExiste"));
							return false;
						}
						else{
							/* Insertando Feriado x Zona */
							feriadoZona.setCodigoZona(codigoZonas.get(i).toString());
							service.insertFeriadoZona(feriadoZona, usuario);
						}
					}
					else{ //Hay una region
						if (resultado.size() == 0){
						/* Insertando Feriado x Zona */
						feriadoZona.setCodigoZona(codigoZonas.get(i).toString());
						service.insertFeriadoZona(feriadoZona, usuario);
						}
					}
				}				
			}		
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
    		return false;
		}
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		FeriadoZona feriadoZonabusqueda= (FeriadoZona)this.beanRegistroSeleccionado;
		
		MantenimientoBASFeriadoZonaForm f = new MantenimientoBASFeriadoZonaForm();				
		f.setEditable(true);
		
		if(this.accion.equals(this.ACCION_NUEVO)){			
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ADD' method");
			}		
			f.setNewRecord(true);			
			FeriadoZona feriadoZona = new FeriadoZona();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
			feriadoZona.setCodigoAnio(sdf.format(new Date(System.currentTimeMillis())).substring(6, 10));
			feriadoZona.setFechaCalendario(new Date(System.currentTimeMillis()));			
			BeanUtils.copyProperties(f, feriadoZona);
			f.setCalendar(new Date(System.currentTimeMillis()));
		}
	
		// Cargamos los combos a utilizar
		// Si el id ha sido enviado, buscamos la informacion
		// en caso contrario, no hacemos nada, se esta insertando
		// un nuevo registro a la aplicación

		if(this.accion.equals(this.ACCION_MODIFICAR)){
			
			if (log.isDebugEnabled()) {
				log.debug("Entering 'EDIT' method");
			}
			SimpleDateFormat sdfbusqueda = new SimpleDateFormat("dd/MM/yyyy");
			String parametrosMantenimiento = sdfbusqueda.format(feriadoZonabusqueda.getFechaCalendario())+feriadoZonabusqueda.getCodigoZona();	
			
			String codigo = parametrosMantenimiento;
			f.setNewRecord(false);
			
			if (codigo != null) {
				
				if (log.isDebugEnabled()) {
					log.debug("Id seleccionado de la lista: " + codigo);
				}
				
				ProcesoVENService service = (ProcesoVENService)this.getBeanService("spusicc.procesoVENService");						
				FeriadoZona feriadoZona = new FeriadoZona();
				String fecha = codigo.substring(0,10);
				String anio = fecha.substring(6,10);
				String codigoZona = codigo.substring(10);
				
				Map criteria = new HashMap();
				criteria.put("fechaCalendario", fecha);
				criteria.put("codigoAnio", anio);
				criteria.put("codigoZona", new Integer(codigoZona));
				List resultado = service.getFeriadoZona(criteria);
				if (resultado.size() > 0) {
					feriadoZona = (FeriadoZona) resultado.get(0);
					BeanUtils.copyProperties(f, feriadoZona);
				}
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				f.setFechaCalendario(sdf.format(feriadoZona.getFechaCalendario()));
			} 
			
		}
		return f;
	}
	
	
	/**
	 * @return the busquedaZonaPOPUPSearchAction
	 */
	public BusquedaZonaPOPUPSearchAction getBusquedaZonaPOPUPSearchAction() {
		return busquedaZonaPOPUPSearchAction;
	}

	/**
	 * @param busquedaZonaPOPUPSearchAction the busquedaZonaPOPUPSearchAction to set
	 */
	public void setBusquedaZonaPOPUPSearchAction(
			BusquedaZonaPOPUPSearchAction busquedaZonaPOPUPSearchAction) {
		this.busquedaZonaPOPUPSearchAction = busquedaZonaPOPUPSearchAction;
	}

	/**
	 * @return the mostrarPopupZona
	 */
	public boolean isMostrarPopupZona() {
		return mostrarPopupZona;
	}

	/**
	 * @param mostrarPopupZona the mostrarPopupZona to set
	 */
	public void setMostrarPopupZona(boolean mostrarPopupZona) {
		this.mostrarPopupZona = mostrarPopupZona;
	}
	
	
		
}
