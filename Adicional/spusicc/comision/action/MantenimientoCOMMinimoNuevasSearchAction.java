package biz.belcorp.ssicc.web.spusicc.comision.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMMinimoNuevasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMMinimoNuevasForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMMinimoNuevasSearchForm;

/**
 * The Class MantenimientoCOMMinimoNuevasSearchAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 09/01/2015
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@ManagedBean
@SessionScoped
public class MantenimientoCOMMinimoNuevasSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -2039671691149644047L;
	private List siccRegionList;
	private List siccZonaList;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoCOMMinimoNuevasForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCOMMinimoNuevasSearchForm searchForm = new MantenimientoCOMMinimoNuevasSearchForm();
		return searchForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("'setViewAttributes'");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoCOMMinimoNuevasSearchForm f =(MantenimientoCOMMinimoNuevasSearchForm) this.formBusqueda; 
		try {
			String codigoPais = this.mPantallaPrincipalBean.getCountryCode();
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			f.setCodigoPais(codigoPais);
			this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais", criteria);	
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
		log.debug("'setFindAttributes'");
		MantenimientoCOMMinimoNuevasService service = (MantenimientoCOMMinimoNuevasService) getBean("spusicc.mantenimientoCOMMinimoNuevasService");
		List lista = new ArrayList();
		try {			
			MantenimientoCOMMinimoNuevasSearchForm f =(MantenimientoCOMMinimoNuevasSearchForm) this.formBusqueda;
			Map criteria = new HashMap();
			criteria.put("codigoRegion", f.getCodigoRegionSearch());			
			lista = service.getMinimoNuevasList(criteria);
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) {
				error = e.getLocalizedMessage();
			}
			throw new Exception(this.getResourceMessage("errors.detail", new Object[]{ error }));	
		}
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {	
		log.debug("'setDeleteAttributes'");
		try {
			MantenimientoCOMMinimoNuevasForm f = (MantenimientoCOMMinimoNuevasForm) this.formMantenimiento;
			MantenimientoCOMMinimoNuevasService service = (MantenimientoCOMMinimoNuevasService) getBean("spusicc.mantenimientoCOMMinimoNuevasService");
			Map criteria = new HashMap();
			criteria.put("codigoRegion", f.getCodigoRegion());
			criteria.put("codigoZona", f.getCodigoZona());
			
			service.deleteMinimoNuevas(criteria);
			
			this.getResourceMessage("mantenimientoCOMMinimoNuevasForm.deleted");
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) {
				error = e.getLocalizedMessage();
			}
			throw new Exception(this.getResourceMessage("errors.detail", new Object[]{ error }));
		}
		return true;
	}
	
	@Override
	protected String getSalirForward() {
		return "mantenimientoCOMMinimoNuevasList";
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		log.debug("entrando a: 'setSaveAttributes'");
		String resultado = "";
		try {
			MantenimientoCOMMinimoNuevasForm f = (MantenimientoCOMMinimoNuevasForm) this.formMantenimiento;
			MantenimientoCOMMinimoNuevasService service = (MantenimientoCOMMinimoNuevasService) getBean("spusicc.mantenimientoCOMMinimoNuevasService");
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			Map criteria = new HashMap();
			criteria.put("codigoRegion", f.getCodigoRegion());
			criteria.put("codigoZona",f.getCodigoZona());
			criteria.put("cantidadMinima", f.getCantidadMinima());
			criteria.put("usuarioLogin", usuario.getLogin());
			
			if (!f.isNewRecord()) {
				//UPDATE
				log.debug("preparando para modificar");				
				service.updateMinimoNuevas(criteria);				
				if (StringUtils.isNotEmpty(resultado)) {
					throw new Exception(this.getResourceMessage("mantenimientoCOMMinimoNuevasForm.message.validation.error", new Object[]{ resultado }));
				}
			}else {
				//SAVE
				log.debug("preparando para guardar");
				resultado = service.insertMinimoNuevas(criteria);				
				if (StringUtils.isNotEmpty(resultado)) {
					throw new Exception(this.getResourceMessage("mantenimientoCOMMinimoNuevasForm.message.validation.error", new Object[]{ resultado }));
				}
			}
			
			log.debug("fin del metodo 'setSaveAttributes'");
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) {
				error = e.getLocalizedMessage();
			}
			throw new Exception(this.getResourceMessage("errors.detail", new Object[]{ error }));
		}		
		log.debug("redireccion del metodo");
		return true;
	}
	
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Map mapa = (Map) this.beanRegistroSeleccionado;	
		MantenimientoCOMMinimoNuevasSearchForm form = (MantenimientoCOMMinimoNuevasSearchForm) this.formBusqueda;
		MantenimientoCOMMinimoNuevasForm mantenimientoForm = new MantenimientoCOMMinimoNuevasForm();
		mantenimientoForm.setCodigoPais(this.mPantallaPrincipalBean.getCountryCode());		
		String codigoRegionSearch = null;
		if (!this.accion.equals(this.ACCION_NUEVO)) {  	            
			String codigoZona = mapa.get("codigoZona").toString(); 		
			String cantidadMinimaNueva = mapa.get("cantidadMinimaNueva").toString(); 
			codigoRegionSearch = mapa.get("codigoRegion").toString();
            if (log.isDebugEnabled()) {
				log.debug("Id seleccionado de la lista: " + codigoZona + " " + codigoRegionSearch);
			}
			mantenimientoForm.setCodigoZona(codigoZona);
			mantenimientoForm.setCodigoRegion(codigoRegionSearch);
			mantenimientoForm.setCantidadMinima(cantidadMinimaNueva);
			mantenimientoForm.setNewRecord(false);
        } else {
        	codigoRegionSearch = form.getCodigoRegionSearch();
        	mantenimientoForm.setCodigoRegion(codigoRegionSearch);
        }
		
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		LabelValue[] lista = ajaxService.getZonasByPaisActivasNoActivas(mantenimientoForm.getCodigoPais(), codigoRegionSearch);
		if(lista != null && lista.length > 0) {
			this.siccZonaList = Arrays.asList(lista);
		}

        return mantenimientoForm;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoCOMMinimoNuevasForm mantenimientoForm = (MantenimientoCOMMinimoNuevasForm) this.formMantenimiento;
		boolean isNew = mantenimientoForm.isNewRecord();
		if(isNew) {
			return "mantenimientoCOMMinimoNuevasForm.added";
		}else{
			return "mantenimientoCOMMinimoNuevasForm.updated";
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

}
