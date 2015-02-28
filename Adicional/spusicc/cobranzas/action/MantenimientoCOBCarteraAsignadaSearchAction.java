package biz.belcorp.ssicc.web.spusicc.cobranzas.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ConsultaCOBGenericoService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.MantenimientoCOBGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.MantenimientoCOBCarteraAsignadaSearchForm;

/**
 * The Class MantenimientoCOBCarteraAsignadaSearchAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 21/01/2015
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@ManagedBean
@SessionScoped
public class MantenimientoCOBCarteraAsignadaSearchAction extends BaseMantenimientoSearchAbstractAction {
	
	private static final long serialVersionUID = -8647323163683531947L;
	private List siccEtapaDeudaList;
	private List siccRegionList;
	private List siccZonaList;	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCOBCarteraAsignadaSearchForm searchForm = new MantenimientoCOBCarteraAsignadaSearchForm();
		return searchForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		
		if (log.isDebugEnabled()) {
		   this.log.debug("JFA Entering: setViewAttributes");
		}
		
		MantenimientoCOBCarteraAsignadaSearchForm f = (MantenimientoCOBCarteraAsignadaSearchForm) this.formBusqueda;
		String codigoPais = this.mPantallaPrincipalBean.getCountryCode();
		f.setCodigoPais(codigoPais);
						
		/* Obteniedo la Lista de Etapas de Deuda */
		ConsultaCOBGenericoService consultaCOBService = (ConsultaCOBGenericoService) this.getBean("spusicc.consultaCOBGenericoService");
		this.siccEtapaDeudaList = consultaCOBService.getEtapasDeuda(); 
		
		/* Obteniedo la Lista de Regiones */
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map datos = new HashMap();		
		datos.put("codigoPais", codigoPais);
		
		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais", datos);		
		this.siccZonaList = new ArrayList();
		
		/* Obteniendo Lista */				
		find();		
		
		this.mostrarBotonNuevo = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonModificar = false;
	}
	
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {	
		
		if (log.isDebugEnabled()) {
			   this.log.debug("JFA Entering: setFindAttributes");
		}

		/* Obteniendo valores */
		MantenimientoCOBCarteraAsignadaSearchForm f = (MantenimientoCOBCarteraAsignadaSearchForm) this.formBusqueda;
		
		Map datos = new HashMap();
		datos.put("codigoPais", f.getCodigoPais());
		datos.put("codigoEtapaDeuda", f.getCodigoEtapaDeuda());
		datos.put("codigoPeriodo", f.getCodigoPeriodo());
		datos.put("codigoRegion", f.getCodigoRegion());
		datos.put("codigoZona", f.getCodigoZona());
				
		MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) 
		this.getBean("spusicc.mantenimientoCOBGenericoService");
		List resultado = service.getListaCarteraAsignada(datos);	
		if (log.isDebugEnabled()) {
			this.log.debug("JFA Todo Ok: Redireccionando");
		}		
		return resultado;
	}
	
	
	public void depurar(ActionEvent event) {
		
		if (log.isDebugEnabled()) {
			log.debug("JFA : Entering 'depurar' method");
		}
		
		if (!this.verificarRegistroSeleccionado()) return;
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map mapa = (Map) this.beanRegistroSeleccionado;
		
		try {			
			MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) 
														getBean("spusicc.mantenimientoCOBGenericoService");	
										
			mapa.put("codigoUsuario",usuario.getLogin()); 
						
			service.executeDepurarCarteraAsignada(mapa);
			
			find();
			
			this.addInfo("Info: ", this.getResourceMessage("mantenimientoCOBCarteraAsignadaForm.depurada"));
						
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) {
				error = e.getLocalizedMessage();
			}
			this.getResourceMessage("errors.detail", new Object[]{ error });			
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("JFA : Entering 'eliminar' method");
		}
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map mapa = (Map) this.beanRegistroSeleccionado;
		try {																								
			mapa.put("codigoUsuario", usuario.getLogin());
			
			this.getResourceMessage("mantenimientoCOBCarteraAsignadaForm.eliminada");
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
		return "mantenimientoCOBCarteraAsignadaList";
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {

		MantenimientoCOBCarteraAsignadaSearchForm f = (MantenimientoCOBCarteraAsignadaSearchForm) this.formBusqueda;
		
		if (!this.accion.equals(this.ACCION_NUEVO)) { 
			Map mapa = (Map) this.beanRegistroSeleccionado;
			BeanUtils.copyProperties(f, mapa);
		}		
		
		return null;
	}	
	
	public void loadZonas(ValueChangeEvent val) {
		if(log.isDebugEnabled()){
			log.debug("loadZonas...");
		}
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		MantenimientoCOBCarteraAsignadaSearchForm f = (MantenimientoCOBCarteraAsignadaSearchForm) this.formBusqueda;
		if(val.getNewValue() != null) {
			this.siccZonaList = Arrays.asList(ajaxService.getZonasByPaisRegion(f.getCodigoPais(), val.getNewValue().toString()));
		} else {
			this.siccZonaList = new ArrayList();
		}
	}

	
	/**
	 * @return the siccEtapaDeudaList
	 */
	public List getSiccEtapaDeudaList() {
		return siccEtapaDeudaList;
	}

	/**
	 * @param siccEtapaDeudaList the siccEtapaDeudaList to set
	 */
	public void setSiccEtapaDeudaList(List siccEtapaDeudaList) {
		this.siccEtapaDeudaList = siccEtapaDeudaList;
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
