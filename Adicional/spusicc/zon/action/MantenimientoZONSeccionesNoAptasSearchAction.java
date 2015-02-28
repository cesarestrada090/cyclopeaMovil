/*
 * 
 */
package biz.belcorp.ssicc.web.spusicc.zon.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.zon.model.SeccionNoApta;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.zon.MantenimientoZONSeccionesNoAptasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.zon.form.MantenimientoZONSeccionesNoAptasForm;
import biz.belcorp.ssicc.web.spusicc.zon.form.MantenimientoZONSeccionesNoAptasSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoZONSeccionesNoAptasSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 1L;
	
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private List zonSeccionesNoAptasList = new ArrayList();

	@Override
	protected String getSalirForward() {
		return "mantenimientoZONSeccionesNoAptasSearchList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoZONSeccionesNoAptasForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoZONSeccionesNoAptasSearchForm form = new MantenimientoZONSeccionesNoAptasSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes");
		}
		
		AjaxService ajaxService = (AjaxService) this.getBean("ajaxService");
		
		MantenimientoZONSeccionesNoAptasService service = (MantenimientoZONSeccionesNoAptasService) getBean("spusicc.mantenimientoZONSeccionesNoAptasService");
		MantenimientoZONSeccionesNoAptasSearchForm searchForm = (MantenimientoZONSeccionesNoAptasSearchForm)formBusqueda;
		
		LabelValue[] listaZonas = ajaxService.getZonasByPaisRegion(searchForm.getCodigoPais(), searchForm.getCodigoRegion());
		LabelValue[] listaZonasFinal = new LabelValue[listaZonas.length + 1];

		listaZonasFinal[0] = new LabelValue("", "");  
		System.arraycopy(listaZonas, 0, listaZonasFinal, 1, listaZonas.length);
		this.setSiccZonaList(listaZonasFinal);
		
		Map criteria = BeanUtils.describe(searchForm);
		List lista = service.getSeccionesAptasNoAptasByCriteria(criteria);
		
		this.setZonSeccionesNoAptasList(lista);
		
		return lista;
	}
	
	@Override
	protected void setEditAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setEditAttributes");
		}
		MantenimientoZONSeccionesNoAptasForm form = (MantenimientoZONSeccionesNoAptasForm)this.formMantenimiento;
		if(StringUtils.isNotBlank(form.getCodigoRegion())
				|| StringUtils.isNotEmpty(form.getCodigoRegion())
				&& StringUtils.isNotBlank(form.getCodigoSeccion())
				|| StringUtils.isNotEmpty(form.getCodigoSeccion())
				&& StringUtils.isNotBlank(form.getCodigoZona())
				|| StringUtils.isNotEmpty(form.getCodigoZona())){
			if(StringUtils.isBlank(form.getIndicadorApta())
					|| StringUtils.isEmpty(form.getIndicadorApta())){
				form.setIndicadorApta(Constants.NUMERO_UNO);
			}else{
				form.setIndicadorApta(Constants.NUMERO_CERO);
			}
		}
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setSaveAttributes");
		}
		
		MantenimientoZONSeccionesNoAptasForm f = (MantenimientoZONSeccionesNoAptasForm)formMantenimiento;
		MantenimientoZONSeccionesNoAptasService service = (MantenimientoZONSeccionesNoAptasService) getBean("spusicc.mantenimientoZONSeccionesNoAptasService");
		
		// buscar en la tabla de no aptas 
		SeccionNoApta seccion = service.getSeccionNoApta(f.getCodigoRegion(), f.getCodigoZona(), f.getCodigoSeccion());
		
		if(StringUtils.equals(f.getIndicadorApta(), Constants.NUMERO_UNO))
		{
			//si se encuentra eliminarla
			if(seccion != null)
			{
				service.deleteSeccionNoApta(f.getCodigoRegion(), f.getCodigoZona(), f.getCodigoSeccion());
			}
		}
		else
		{
			//si no existe registrarla
			if(seccion == null)
			{
				SeccionNoApta sna = new SeccionNoApta();
				BeanUtils.copyProperties(sna, f);
				
				service.insertSeccionNoApta(sna, this.getmPantallaPrincipalBean().getCurrentUser());
			}
		}
				
        this.addInfo("Info:", this.getResourceMessage("mantenimientoZONSeccionesNoAptasForm.updated"));
		
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setObtenerRegistroAttributes");
		}
		MantenimientoZONSeccionesNoAptasForm form = new MantenimientoZONSeccionesNoAptasForm();
		Map criteria = (HashMap) this.beanRegistroSeleccionado;
		form.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		BeanUtils.copyProperties(form, criteria);
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		this.mostrarBotonEliminar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonNuevo = false;
		MantenimientoZONSeccionesNoAptasSearchForm f = (MantenimientoZONSeccionesNoAptasSearchForm) this.formBusqueda;
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		AjaxService ajaxService = (AjaxService) this.getBean("ajaxService");
		this.setSiccRegionList(ajaxService.getRegionesByPais(pais.getCodigo()));
	}
	
	/**
	 * Show zonasx region.
	 *
	 * @param val the val
	 */
	public void showZonasxRegion(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("showZonasxRegion");
		}		
		if(StringUtils.isNotEmpty(val.getNewValue().toString()) 
				|| StringUtils.isNotBlank(val.getNewValue().toString())){						
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.setSiccZonaList(ajax.getZonasByPaisRegion(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),
																val.getNewValue().toString()));
		}
	}

	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public List getZonSeccionesNoAptasList() {
		return zonSeccionesNoAptasList;
	}

	public void setZonSeccionesNoAptasList(List zonSeccionesNoAptasList) {
		this.zonSeccionesNoAptasList = zonSeccionesNoAptasList;
	}
}
