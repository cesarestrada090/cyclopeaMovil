package biz.belcorp.ssicc.web.spusicc.car.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.car.MantenimientoCARNivelRiesgoSeccionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.car.form.MantenimientoCARNivelRiesgoSeccionForm;
import biz.belcorp.ssicc.web.spusicc.car.form.MantenimientoCARNivelRiesgoSeccionSearchForm;

/**
 * The Class MantenimientoCARNivelRiesgoSeccionSearchAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 15/01/2015
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@ManagedBean
@SessionScoped
public class MantenimientoCARNivelRiesgoSeccionSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 8060340852370639603L;
	private List siccRegionList;
	private List siccZonaList;
	private List siccSeccionList;
	private List carNivelRiesgoList;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoCARNivelRiesgoSeccionForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCARNivelRiesgoSeccionSearchForm searchForm = new MantenimientoCARNivelRiesgoSeccionSearchForm();
		return searchForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		MantenimientoCARNivelRiesgoSeccionSearchForm f = (MantenimientoCARNivelRiesgoSeccionSearchForm) this.formBusqueda;
		
		f.setCodigoRegion(null);
		f.setCodigoSeccion(null);
		f.setCodigoZona(null);
		
		String codigoPais = this.mPantallaPrincipalBean.getCountryCode();
		f.setCodigoPais(codigoPais);

		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		this.siccRegionList = Arrays.asList(aSvc.getRegionesByPais(codigoPais));
		this.siccZonaList = new ArrayList();
		this.siccSeccionList = new ArrayList();		
		this.mostrarBotonConsultar = false;		
		this.mostrarBotonEliminar = false;
		this.mostrarBotonNuevo = false;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		
		MantenimientoCARNivelRiesgoSeccionSearchForm f = (MantenimientoCARNivelRiesgoSeccionSearchForm) this.formBusqueda;
		MantenimientoCARNivelRiesgoSeccionService service = (MantenimientoCARNivelRiesgoSeccionService)getBean("spusicc.mantenimientoCARNivelRiesgoSeccionService");

		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoRegion", f.getCodigoRegion());
		criteria.put("codigoZona", f.getCodigoZona());
		criteria.put("codigoSeccion", f.getCodigoSeccion());
		
		List list = service.getNivelRiesgoSeccionList(criteria);
		return list;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {	
		return false;
	}
	
	@Override
	protected String getSalirForward() {
		return "mantenimientoCARNivelRiesgoSeccionList";
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}
		MantenimientoCARNivelRiesgoSeccionForm f = (MantenimientoCARNivelRiesgoSeccionForm) this.formMantenimiento;
		MantenimientoCARNivelRiesgoSeccionService service = (MantenimientoCARNivelRiesgoSeccionService) getBean("spusicc.mantenimientoCARNivelRiesgoSeccionService");		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		try {			
		    Map params = new HashMap();
		    params.put("oidSeccion", f.getOidSeccion());
		    params.put("oidNivelRiesgo", f.getOidNuevoNivelRiesgo());
		    params.put("codigoRegion", f.getCodigoRegion());
		    params.put("codigoZona", f.getCodigoZona());
		    params.put("codigoSeccion", f.getCodigoSeccion());
		    params.put("usuario", usuario.getLogin());
		    
			log.debug("Entidad : "+params);
			service.updateNivelRiesgoSeccion(params);
		} catch (Exception e) {
			throw new Exception(this.getResourceMessage("mantenimientoEDULocal.cabecera.error", new Object[]{ e.getMessage() }));
		}		
		return true;
	}
	
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		log.debug("edit");
		Map mapa = (Map) this.beanRegistroSeleccionado;	
		MantenimientoCARNivelRiesgoSeccionService service = (MantenimientoCARNivelRiesgoSeccionService) getBean("spusicc.mantenimientoCARNivelRiesgoSeccionService");
		
		MantenimientoCARNivelRiesgoSeccionForm mantenimientoForm = new MantenimientoCARNivelRiesgoSeccionForm();
		
		BeanUtils.copyProperties(mantenimientoForm, mapa);
		
		this.carNivelRiesgoList = service.getNivelesRiesgos(null);
		
		List<Base> niveles = new ArrayList<Base>(this.carNivelRiesgoList);		
		for(Base nivelRiesgo : niveles) {
			if(nivelRiesgo.getDescripcion().equals(mantenimientoForm.getNivelRiesgo())) {
				mantenimientoForm.setOidNuevoNivelRiesgo(nivelRiesgo.getCodigo());
				break;
			}
		}

		return mantenimientoForm; 		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		return "mantenimientoCARNivelRiesgoSeccionForm.updated";
	}
	
	/**
	 * loadZonas.
	 *
	 * @param val the val
	 */
	public void loadZonas(ValueChangeEvent val){
		log.debug(">>loadZonas ");
		log.debug("val: "+val.getNewValue().toString());		
		
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		String [] regiones = (String [])val.getNewValue();
		if(!val.equals(null) && regiones.length > 0 ){
			this.siccZonaList = Arrays.asList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(this.mPantallaPrincipalBean.getCountryCode(),
																						  "T", "VD", regiones, "T"));
			this.siccSeccionList = null;
		} else {
			this.siccSeccionList = null;
			this.siccZonaList = null;
		}
	}
	
	/**
	 * Metodo para obtener Seccion por Zona
	 * 
	 * @param val
	 */
	public void loadSecciones(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadSecciones");
		}
		MantenimientoCARNivelRiesgoSeccionForm form = (MantenimientoCARNivelRiesgoSeccionForm) this.formMantenimiento;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		if(!val.equals(null) && form.getCodigoRegion().length() > 0){
			this.siccSeccionList = Arrays.asList(aSvc.getSeccionesByPaisMarcaCanalRegionZona(this.mPantallaPrincipalBean.getCountryCode(), "T", "VD",
								   															 form.getCodigoRegion(), val.getNewValue().toString()));
			
		}else {
			this.siccSeccionList = null;
		}
	}
	
	
	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public List getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(List siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the siccSeccionList
	 */
	public List getSiccSeccionList() {
		return siccSeccionList;
	}

	/**
	 * @param siccSeccionList the siccSeccionList to set
	 */
	public void setSiccSeccionList(List siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
	}

	/**
	 * @return the carNivelRiesgoList
	 */
	public List getCarNivelRiesgoList() {
		return carNivelRiesgoList;
	}

	/**
	 * @param carNivelRiesgoList the carNivelRiesgoList to set
	 */
	public void setCarNivelRiesgoList(List carNivelRiesgoList) {
		this.carNivelRiesgoList = carNivelRiesgoList;
	}

}
