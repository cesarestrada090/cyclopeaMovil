 package biz.belcorp.ssicc.web.spusicc.lideres.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.lideres.model.ObjetivoAsignacionPuntaje;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.lideres.MantenimientoLIDObjetivosPorVariableAsignacionPuntajeService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.lideres.form.MantenimientoLIDObjetivosPorVariableAsignacionPuntajeForm;
import biz.belcorp.ssicc.web.spusicc.lideres.form.MantenimientoLIDObjetivosPorVariableAsignacionPuntajeSearchForm;

/**
 * The Class MantenimientoLIDObjetivosPorVariableAsignacionPuntajeSearchAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 16/01/2015
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@ManagedBean
@SessionScoped
public class MantenimientoLIDObjetivosPorVariableAsignacionPuntajeSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 4209745377698699015L;
	private List tipoAsignacionPuntajeList;
	private List lidObjetivoAsignacionPuntaje;
	private ObjetivoAsignacionPuntaje objeAsigPuntajeAnt;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoLIDObjetivosPorVariableAsignacionPuntajeForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoLIDObjetivosPorVariableAsignacionPuntajeSearchForm searchForm = new MantenimientoLIDObjetivosPorVariableAsignacionPuntajeSearchForm();
		return searchForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		try {
			log.debug("Entrando : MantenimientoLIDObjetivosPorVariableAsignacionPuntajeSearchAction.setViewAttributes" );
			
			MantenimientoLIDObjetivosPorVariableAsignacionPuntajeSearchForm f = (MantenimientoLIDObjetivosPorVariableAsignacionPuntajeSearchForm) this.formBusqueda;	
			MantenimientoLIDObjetivosPorVariableAsignacionPuntajeService manteService = (MantenimientoLIDObjetivosPorVariableAsignacionPuntajeService)
																					getBean("spusicc.mantenimientoLIDObjetivosPorVariableAsignacionPuntajeService");
			
			//Cargando datos a session
			this.tipoAsignacionPuntajeList = manteService.getTipoAsignacionPuntajeList();
						
			// Carga de parametros iniciales
			f.setCodigoPais(this.mPantallaPrincipalBean.getCountryCode());
			f.setCodigoTipoAsignacionPuntaje(Constants.LID_TIPO_ASIGNACION_PUNTAJE_DEFAULT);
			
			this.mostrarBotonConsultar = false;
			
			log.debug("Saliendo : MantenimientoLIDObjetivosPorVariableAsignacionPuntajeSearchAction.setViewAttributes" );
				
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) {
				error = e.getLocalizedMessage();
			}
			throw new Exception(this.getResourceMessage("errors.detail", new Object[]{ error }));
		}
		
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		
		log.debug("Entrando : MantenimientoLIDObjetivosPorVariableAsignacionPuntajeSearchAction.setFindAttributes" );
		
		MantenimientoLIDObjetivosPorVariableAsignacionPuntajeSearchForm f = (MantenimientoLIDObjetivosPorVariableAsignacionPuntajeSearchForm) this.formBusqueda;
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoTipoAsignacionPuntaje", f.getCodigoTipoAsignacionPuntaje());
		
		MantenimientoLIDObjetivosPorVariableAsignacionPuntajeService manteService = (MantenimientoLIDObjetivosPorVariableAsignacionPuntajeService)
																		getBean("spusicc.mantenimientoLIDObjetivosPorVariableAsignacionPuntajeService");
		
		List objetivoAsignacionPuntajeList = manteService.getObjetivoAsignacionPuntajeList(criteria);
		this.lidObjetivoAsignacionPuntaje = objetivoAsignacionPuntajeList;
		
		log.debug("Saliendo : MantenimientoLIDObjetivosPorVariableAsignacionPuntajeSearchAction.setFindAttributes" );
		
		return objetivoAsignacionPuntajeList;
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		try {							
			MantenimientoLIDObjetivosPorVariableAsignacionPuntajeService service = (MantenimientoLIDObjetivosPorVariableAsignacionPuntajeService)  getBean("spusicc.mantenimientoLIDObjetivosPorVariableAsignacionPuntajeService");
			service.deleteObjetivoAsignacionPuntaje(ObjeAsigPuntajeToCriteria((ObjetivoAsignacionPuntaje) this.beanRegistroSeleccionado));
			this.getResourceMessage("mantenimientoCOMCalificacionComision.deleted");
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) {
				error = e.getLocalizedMessage();
			}
			throw new Exception(this.getResourceMessage("errors.detail", new Object[]{ error }));			
		}
		return true;
	}
	
	private Map ObjeAsigPuntajeToCriteria(ObjetivoAsignacionPuntaje objeAsigPuntaje) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", objeAsigPuntaje.getCodigoPais());
		criteria.put("codigoTipoAsignacionPuntaje", objeAsigPuntaje.getCodigoTipoAsignacionPuntaje());
		criteria.put("codigoPeriodoIni", objeAsigPuntaje.getCodigoPeriodoIni());
		criteria.put("codigoPeriodoFin", objeAsigPuntaje.getCodigoPeriodoFin());
		criteria.put("valorObjetivo", String.valueOf(objeAsigPuntaje.getValorObjetivo()));
		return criteria;
	}
	
	@Override
	protected String getSalirForward() {
		return "mantenimientoLIDObjetivosPorVariableAsignacionPuntajeList";
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		Map criteria = new HashMap();
		MantenimientoLIDObjetivosPorVariableAsignacionPuntajeForm f = (MantenimientoLIDObjetivosPorVariableAsignacionPuntajeForm) this.formMantenimiento;
		MantenimientoLIDObjetivosPorVariableAsignacionPuntajeService manteService = (MantenimientoLIDObjetivosPorVariableAsignacionPuntajeService)
																			getBean("spusicc.mantenimientoLIDObjetivosPorVariableAsignacionPuntajeService");
		
		try {			
			// MODIFICAR
			if (!f.isNewRecord()) {	
				// Mapeo de los datos actuales del objeto
				criteria.put("codigoPais", f.getCodigoPais());
				criteria.put("codigoTipoAsignacionPuntaje", f.getCodigoTipoAsignacionPuntaje());
				criteria.put("codigoPeriodoIniNuevo", f.getPeriodoIni());
				criteria.put("codigoPeriodoFinNuevo", f.getPeriodoFin());
				criteria.put("valorObjetivo", f.getValorObjetivo());
				// Mapeo de los datos antiguos del objeto
				criteria.put("codigoPeriodoIni", this.objeAsigPuntajeAnt.getCodigoPeriodoIni());
				criteria.put("codigoPeriodoFin", this.objeAsigPuntajeAnt.getCodigoPeriodoFin());
				
				// Se actualiza el objeto
				if (!manteService.udpateObjetivoAsignacionPuntaje(criteria)) {
					String cadError = this.getResourceMessage("MantenimientoLIDObjetivosPorVariableAsignacionPuntajeService.msg.periodoNoValido");
					throw new Exception(this.getResourceMessage("MantenimientoLIDObjetivosPorVariableAsignacionPuntajeService.cabecera.error", new Object[]{ cadError }));
				} else {
					throw new Exception(this.getResourceMessage("MantenimientoLIDObjetivosPorVariableAsignacionPuntajeService.updated"));	
				}
			} else {
				// Mapeo de los datos actuales del objeto
				criteria.put("codigoPais", f.getCodigoPais());
				criteria.put("codigoTipoAsignacionPuntaje", f.getCodigoTipoAsignacionPuntaje());
				criteria.put("codigoPeriodoIni", f.getPeriodoIni());
				criteria.put("codigoPeriodoFin", f.getPeriodoFin());
				criteria.put("valorObjetivo", f.getValorObjetivo());
				// Mapeo de los datos antiguos del objeto
				criteria.put("codigoPeriodoIniNuevo", f.getPeriodoIni());
				criteria.put("codigoPeriodoFinNuevo", f.getPeriodoFin());
				
				//Se crea un nuevo registro
				if (!manteService.insertObjetivoAsignacionPuntaje(criteria)) {
					String cadError = this.getResourceMessage("MantenimientoLIDObjetivosPorVariableAsignacionPuntajeService.msg.periodoNoValido");
					throw new Exception(this.getResourceMessage("MantenimientoLIDObjetivosPorVariableAsignacionPuntajeService.cabecera.error", new Object[]{ cadError }));	
				}
			}
			
		} catch (Exception e) {
			String cadError = this.getResourceMessage("MantenimientoLIDObjetivosPorVariableAsignacionPuntajeService.msg.periodoNoValido");
			throw new Exception(this.getResourceMessage("MantenimientoLIDObjetivosPorVariableAsignacionPuntajeService.cabecera.error", new Object[]{ cadError }));	
		}
		
		return true;
	}
	
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
	
		log.debug("Entrando : MantenimientoLIDObjetivosPorVariableAsignacionPuntajeAction.setAddAttributes" );
		
		MantenimientoLIDObjetivosPorVariableAsignacionPuntajeForm mantenimientoForm = new MantenimientoLIDObjetivosPorVariableAsignacionPuntajeForm();
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		MantenimientoOCRPedidoControlFacturacionService servicePeriodo = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
				
		String codigoPais = this.mPantallaPrincipalBean.getCountryCode();
		Map params = new HashMap();	
		params.put("codigoPeriodo", service.getPeriodoDefaultByPaisCanal(codigoPais, Constants.CODIGO_CANAL_DEFAULT));
		params.put("numCampanhas", new BigDecimal(1)); // La siguiente campaña		
		
		if (!this.accion.equals(this.ACCION_NUEVO)) { 
			this.objeAsigPuntajeAnt = (ObjetivoAsignacionPuntaje) this.beanRegistroSeleccionado;				
			// obtiene el codigo que se selecciono en la pagina que antecede a esta ( Mante**List)
			String codigoSeleccionado = this.objeAsigPuntajeAnt.getCodigoTipoAsignacionPuntaje();
			// cargo valores al objeto form
			this.objeAsigPuntajeAnt.setCodigoPais(codigoPais);
			this.objeAsigPuntajeAnt.setCodigoTipoAsignacionPuntaje(codigoSeleccionado);
			this.objeAsigPuntajeAnt.setCodigoPeriodoIni(servicePeriodo.getPedidosNSiguienteCampanha(params));
			this.objeAsigPuntajeAnt.setCodigoPeriodoFin(servicePeriodo.getPedidosNSiguienteCampanha(params));
					
			// Cargo los datos a la pantalla
			setDatosToForm(this.objeAsigPuntajeAnt, mantenimientoForm);
			
			mantenimientoForm.setNewRecord(false);
		} else {
			MantenimientoLIDObjetivosPorVariableAsignacionPuntajeSearchForm f = (MantenimientoLIDObjetivosPorVariableAsignacionPuntajeSearchForm) this.formBusqueda;			
			mantenimientoForm.setCodigoPais(f.getCodigoPais());
			mantenimientoForm.setCodigoTipoAsignacionPuntaje(f.getCodigoTipoAsignacionPuntaje());
			mantenimientoForm.setPeriodoIni(servicePeriodo.getPedidosNSiguienteCampanha(params));
			mantenimientoForm.setPeriodoFin(servicePeriodo.getPedidosNSiguienteCampanha(params));
			mantenimientoForm.setNewRecord(true);
		}
		
		return mantenimientoForm;
	}
	
	private void setDatosToForm(ObjetivoAsignacionPuntaje objeAsigPuntajeAnt, MantenimientoLIDObjetivosPorVariableAsignacionPuntajeForm f) {		
		f.setCodigoPais(objeAsigPuntajeAnt.getCodigoPais());
		f.setCodigoTipoAsignacionPuntaje(objeAsigPuntajeAnt.getCodigoTipoAsignacionPuntaje());
		f.setPeriodoIni(objeAsigPuntajeAnt.getCodigoPeriodoIni());
		f.setPeriodoFin(objeAsigPuntajeAnt.getCodigoPeriodoFin());
		f.setValorObjetivo(String.valueOf(objeAsigPuntajeAnt.getValorObjetivo()));
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoLIDObjetivosPorVariableAsignacionPuntajeForm mantenimientoForm = (MantenimientoLIDObjetivosPorVariableAsignacionPuntajeForm) this.formMantenimiento;
		boolean isNew = mantenimientoForm.isNewRecord();
		if(isNew) {
			return "MantenimientoLIDObjetivosPorVariableAsignacionPuntajeService.added";
		} else {
			return "MantenimientoLIDObjetivosPorVariableAsignacionPuntajeService.updated";
		}	
	}
	

	/**
	 * @return the tipoAsignacionPuntajeList
	 */
	public List getTipoAsignacionPuntajeList() {
		return tipoAsignacionPuntajeList;
	}

	/**
	 * @param tipoAsignacionPuntajeList the tipoAsignacionPuntajeList to set
	 */
	public void setTipoAsignacionPuntajeList(List tipoAsignacionPuntajeList) {
		this.tipoAsignacionPuntajeList = tipoAsignacionPuntajeList;
	}

	/**
	 * @return the lidObjetivoAsignacionPuntaje
	 */
	public List getLidObjetivoAsignacionPuntaje() {
		return lidObjetivoAsignacionPuntaje;
	}

	/**
	 * @param lidObjetivoAsignacionPuntaje the lidObjetivoAsignacionPuntaje to set
	 */
	public void setLidObjetivoAsignacionPuntaje(List lidObjetivoAsignacionPuntaje) {
		this.lidObjetivoAsignacionPuntaje = lidObjetivoAsignacionPuntaje;
	}

	/**
	 * @return the objeAsigPuntajeAnt
	 */
	public ObjetivoAsignacionPuntaje getObjeAsigPuntajeAnt() {
		return objeAsigPuntajeAnt;
	}

	/**
	 * @param objeAsigPuntajeAnt the objeAsigPuntajeAnt to set
	 */
	public void setObjeAsigPuntajeAnt(ObjetivoAsignacionPuntaje objeAsigPuntajeAnt) {
		this.objeAsigPuntajeAnt = objeAsigPuntajeAnt;
	}

}
