package biz.belcorp.ssicc.web.spusicc.zon.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.zon.MantenimientoZONCallesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.zon.form.MantenimientoZONCallesForm;
import biz.belcorp.ssicc.web.spusicc.zon.form.MantenimientoZONCallesSearchForm;

/**
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@ManagedBean
@SessionScoped
public class MantenimientoZONCallesSearchAction extends BaseMantenimientoSearchAbstractAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	LabelValue[] maeClienteNivel1List = {};
	List maeClienteTipoViaList = new ArrayList();
	List zonCallesList = new ArrayList();
	LabelValue[] listaNivel2 = {};
	LabelValue[] listaNivel3 = {};
	LabelValue[] listaNivel4 = {};
	LabelValue[] listaNivel5 = {};
	LabelValue[] listaNivel6 = {};
	String oidCalle = null;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoZONCallesForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoZONCallesSearchForm searchForm = new MantenimientoZONCallesSearchForm();
		return searchForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setFindAttributes' method");
		}

		MantenimientoZONCallesSearchForm f = (MantenimientoZONCallesSearchForm) this.formBusqueda;
		MantenimientoZONCallesService service = (MantenimientoZONCallesService) getBean("spusicc.mantenimientoZONCallesService");
		Map criteria = new HashMap();
		String nivel1 = f.getValNivel1();
		String nivel2 = "";
		String nivel3 = "";
		String nivel4 = "";
		String nivel5 = "";
		String nivel6 = "";
		if (StringUtils.isNotEmpty(f.getValNivel2())) {
			nivel2 = f.getValNivel2().substring(6, 12);
		}
		if (StringUtils.isNotEmpty(f.getValNivel3())) {
			nivel3 = f.getValNivel3().substring(12, 18);
		}
		if (StringUtils.isNotEmpty(f.getValNivel4())) {
			nivel4 = f.getValNivel4().substring(18, 24);
		}
		if (StringUtils.isNotEmpty(f.getValNivel5())) {
			nivel5 = f.getValNivel5().substring(24, 30);
		}
		if (StringUtils.isNotEmpty(f.getValNivel6())) {
			nivel6 = f.getValNivel6().substring(30, 36);
		}
		criteria.put("codPais", f.getCodigoPais());
		criteria.put("nivel1", nivel1);
		criteria.put("nivel2", nivel2);
		criteria.put("nivel3", nivel3);
		criteria.put("nivel4", nivel4);
		criteria.put("nivel5", nivel5);
		criteria.put("nivel6", nivel6);
		criteria.put("tipoVia", f.getTipoVia());
		criteria.put("nombreVia", f.getNombreVia() != null ? f.getNombreVia().trim() : "");
		criteria.put("nombreViB", "");
		List list = service.getDireccionesClientesList(criteria);
		this.setZonCallesList(list);
		
		return list;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setDeleteAttributes' method");
		}
		MantenimientoZONCallesService service = (MantenimientoZONCallesService) getBean("spusicc.mantenimientoZONCallesService");
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		Map criteria = (HashMap)this.beanRegistroSeleccionado;
		setOidCalle(((BigDecimal)criteria.get("oidCalle")).toString());
		if(StringUtils.isNotBlank(this.getOidCalle()) ||
				StringUtils.isNotEmpty(this.getOidCalle())){
			criteria.put("oidCalle", this.getOidCalle());
			String valid = service.getValidaConsultoraCalle(criteria);
			if (valid.equals("T")) {
				criteria.put("usuario", usuario.getLogin());
				service.deleteZonCalle(criteria);
				this.addInfo("Info:", this.getResourceMessage("mantenimientoZONCallesForm.cabecera.delete"));
			}
			else{
				this.addWarn("Info", this.getResourceMessage("mantenimientoZONCallesForm.existeConsultoraCalle"));
			}
		}
		return true;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {		
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		
		this.mostrarBotonConsultar = false;
		
		MantenimientoZONCallesSearchForm f = (MantenimientoZONCallesSearchForm) this.formBusqueda;
		f.setCodigoPais(this.mPantallaPrincipalBean.getCountryCode());
		
		Map criteria = new HashMap();

		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");

		criteria.put("codigoPais", f.getCodigoPais());
		String oidPais = clienteService.getOidPais(criteria);
		criteria.put("oidPais", oidPais);
		f.setOidPais(oidPais);

		List nivelesGeograficos = clienteService.getNivelesGeograficos(criteria);

		// seteamos los niveles geograficos del pais
		if (nivelesGeograficos != null && nivelesGeograficos.size() > 0) {
			f.setTotalNiveles(String.valueOf(nivelesGeograficos.size()));
			for (int i = 0; i < nivelesGeograficos.size(); i++) {
				Base nivel = (Base) nivelesGeograficos.get(i);
				if (i == 0)
					f.setDescNivel1(nivel.getDescripcion());
				if (i == 1)
					f.setDescNivel2(nivel.getDescripcion());
				if (i == 2)
					f.setDescNivel3(nivel.getDescripcion());
				if (i == 3)
					f.setDescNivel4(nivel.getDescripcion());
				if (i == 4)
					f.setDescNivel5(nivel.getDescripcion());
				if (i == 5)
					f.setDescNivel6(nivel.getDescripcion());
			}
		}
		this.setMaeClienteNivel1List(ajaxService.getUnidadesGeograficas(oidPais, ""));
		this.setMaeClienteTipoViaList(clienteService.getTiposVias(criteria));
	}

	@Override
	protected String getSalirForward() {
		return "mantenimientoZONCallesList";
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method");
		}

		MantenimientoZONCallesForm f = (MantenimientoZONCallesForm) this.formMantenimiento;
		boolean isNew = f.isNewRecord();

		MantenimientoZONCallesService service = (MantenimientoZONCallesService) getBean("spusicc.mantenimientoZONCallesService");

		Map criteria = new HashMap();
		String nivel1 = f.getValNivel1();
		String nivel2 = "";
		String nivel3 = "";
		String nivel4 = "";
		String nivel5 = "";
		String nivel6 = "";
		if (StringUtils.isNotEmpty(f.getValNivel2())) {
			nivel2 = f.getValNivel2().substring(6, 12);
		}
		if (StringUtils.isNotEmpty(f.getValNivel3())) {
			nivel3 = f.getValNivel3().substring(12, 18);
		}
		if (StringUtils.isNotEmpty(f.getValNivel4())) {
			nivel4 = f.getValNivel4().substring(18, 24);
		}
		if (StringUtils.isNotEmpty(f.getValNivel5())) {
			nivel5 = f.getValNivel5().substring(24, 30);
		}
		if (StringUtils.isNotEmpty(f.getValNivel6())) {
			nivel6 = f.getValNivel6().substring(30, 36);
		}

		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		criteria.put("oidCalle", f.getOidCalle());
		criteria.put("codPais", f.getCodigoPais());
		criteria.put("oidPais", f.getOidPais());
		criteria.put("nivel1", nivel1);
		criteria.put("nivel2", nivel2);
		criteria.put("nivel3", nivel3);
		criteria.put("nivel4", nivel4);
		criteria.put("nivel5", nivel5);
		criteria.put("nivel6", nivel6);
		criteria.put("tipoVia", f.getTipoVia());
		criteria.put("nombreVia", "");
		criteria.put("nombreViaB", f.getNombreVia() != null ? f.getNombreVia().trim() : "");
		criteria.put("usuario", usuario.getLogin());

		List list = service.getDireccionesClientesList(criteria);

		if (list.isEmpty()) {
			if (f.isNewRecord()) {
				service.insertZonCalle(criteria);				
				this.addInfo("Info:",this.getResourceMessage("mantenimientoZONCallesForm.cabecera.insert"));
			} else {
				service.updateZonCalle(criteria);
				this.addInfo("Info:",this.getResourceMessage("mantenimientoZONCallesForm.cabecera.update"));
			}
		} else {
			this.addError("Error:", this.getResourceMessage("mantenimientoZONCallesForm.existeCalle"));
			return false;
		}

		return true;
	}
	
	@Override
	protected void setAddAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setAddAttributes");
		}
		MantenimientoZONCallesForm f = (MantenimientoZONCallesForm) this.formMantenimiento;
		Map criteria = new HashMap();
		f.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());

		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");

		criteria.put("codigoPais", f.getCodigoPais());
		String oidPais = clienteService.getOidPais(criteria);
		criteria.put("oidPais", oidPais);
		f.setOidPais(oidPais);

		List nivelesGeograficos = clienteService.getNivelesGeograficos(criteria);

		// seteamos los niveles geograficos del pais
		if (nivelesGeograficos != null && nivelesGeograficos.size() > 0) {
			f.setTotalNiveles(String.valueOf(nivelesGeograficos.size()));
			for (int i = 0; i < nivelesGeograficos.size(); i++) {
				Base nivel = (Base) nivelesGeograficos.get(i);
				if (i == 0)
					f.setDescNivel1(nivel.getDescripcion());
				if (i == 1)
					f.setDescNivel2(nivel.getDescripcion());
				if (i == 2)
					f.setDescNivel3(nivel.getDescripcion());
				if (i == 3)
					f.setDescNivel4(nivel.getDescripcion());
				if (i == 4)
					f.setDescNivel5(nivel.getDescripcion());
				if (i == 5)
					f.setDescNivel6(nivel.getDescripcion());
			}
		}
		this.setMaeClienteNivel1List(ajaxService.getUnidadesGeograficas(oidPais, ""));
		this.setMaeClienteTipoViaList(clienteService.getTiposVias(criteria));
	}
	
	@Override
	protected void setEditAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setEditAttributes");
		}
		MantenimientoZONCallesForm f = (MantenimientoZONCallesForm) formMantenimiento;
		MantenimientoZONCallesService service = (MantenimientoZONCallesService) getBean("spusicc.mantenimientoZONCallesService");		

		f.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());

		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");

		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		String oidPais = clienteService.getOidPais(criteria);
		criteria.put("oidPais", oidPais);
		f.setOidPais(oidPais);

		List nivelesGeograficos = clienteService.getNivelesGeograficos(criteria);

		// seteamos los niveles geograficos del pais
		if (nivelesGeograficos != null && nivelesGeograficos.size() > 0) {
			f.setTotalNiveles(String.valueOf(nivelesGeograficos.size()));
			for (int i = 0; i < nivelesGeograficos.size(); i++) {
				Base nivel = (Base) nivelesGeograficos.get(i);
				if (i == 0)
					f.setDescNivel1(nivel.getDescripcion());
				if (i == 1)
					f.setDescNivel2(nivel.getDescripcion());
				if (i == 2)
					f.setDescNivel3(nivel.getDescripcion());
				if (i == 3)
					f.setDescNivel4(nivel.getDescripcion());
				if (i == 4)
					f.setDescNivel5(nivel.getDescripcion());
				if (i == 5)
					f.setDescNivel6(nivel.getDescripcion());
			}
		}

		Map callesMap = service.getZonCalle(Long.parseLong(f.getOidCalle()));
		BeanUtils.copyProperties(f, callesMap);
		if(StringUtils.isNotBlank(f.getValNivel1()) ||
				StringUtils.isNotBlank(f.getValNivel1())){
			loadNivel(f.getValNivel1(), 2);
		}
		
		if(StringUtils.isNotBlank(f.getValNivel2()) ||
				StringUtils.isNotEmpty(f.getValNivel2())){
			loadNivel(f.getValNivel2(),3);			
		}
		
		if(StringUtils.isNotBlank(f.getValNivel3()) ||
				StringUtils.isNotEmpty(f.getValNivel3())){
			loadNivel(f.getValNivel3(),4);
		}
		
		if(StringUtils.isNotBlank(f.getValNivel4()) ||
				StringUtils.isNotEmpty(f.getValNivel4())){
			loadNivel(f.getValNivel4(),5);
		}
		
		if(StringUtils.isNotBlank(f.getValNivel5()) ||
				StringUtils.isNotEmpty(f.getValNivel5())){
			loadNivel(f.getValNivel5(),6);
		}
		
//		if(StringUtils.isNotBlank(f.getValNivel6()) ||
//				StringUtils.isNotEmpty(f.getValNivel6())){
//			loadNivel(f.getValNivel6(),6);
//		}
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setObtenerRegistroAttributes");
		}
		MantenimientoZONCallesForm f = new MantenimientoZONCallesForm();
		f.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		Map criteria = (HashMap)this.beanRegistroSeleccionado;
		
        if (!this.accion.equals(this.ACCION_NUEVO) ) {
        	
        	setOidCalle(((BigDecimal)criteria.get("oidCalle")).toString());
        	
        	if(StringUtils.isNotBlank(oidCalle) ||
        			StringUtils.isNotEmpty(oidCalle)){
        		f.setOidCalle(oidCalle);
        	}
        }
        
        if(this.accion.equals(this.ACCION_NUEVO)){
        	f.setNewRecord(true);
        	f.setEditable(false);
        }
        	
        if(this.accion.equals(this.ACCION_MODIFICAR)){
        	f.setNewRecord(false);
        	f.setEditable(true);        	
        }
        
        return f;
	}
	
	public void loadNivel2(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("loadNivel2");
		}		
		
		if(StringUtils.isNotEmpty(val.getNewValue().toString()) 
				|| StringUtils.isNotBlank(val.getNewValue().toString())){
			String codigoUnidadGeografica = (String)val.getNewValue();			
			if(StringUtils.isNotBlank(codigoUnidadGeografica) || 
					StringUtils.isNotEmpty(codigoUnidadGeografica)){
				this.loadNivel(codigoUnidadGeografica, 2);
			}
		}
	}
	
	public void loadNivel3(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("loadNivel2");
		}		
		
		if(StringUtils.isNotEmpty(val.getNewValue().toString()) 
				|| StringUtils.isNotBlank(val.getNewValue().toString())){
			String codigoUnidadGeografica = (String)val.getNewValue();			
			if(StringUtils.isNotBlank(codigoUnidadGeografica) || 
					StringUtils.isNotEmpty(codigoUnidadGeografica)){
				this.loadNivel(codigoUnidadGeografica, 3);
			}
		}
	}
	
	public void loadNivel4(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("loadNivel2");
		}		
		
		if(StringUtils.isNotEmpty(val.getNewValue().toString()) 
				|| StringUtils.isNotBlank(val.getNewValue().toString())){
			String codigoUnidadGeografica = (String)val.getNewValue();			
			if(StringUtils.isNotBlank(codigoUnidadGeografica) || 
					StringUtils.isNotEmpty(codigoUnidadGeografica)){
				this.loadNivel(codigoUnidadGeografica, 4);
			}
		}
	}
	
	public void loadNivel5(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("loadNivel2");
		}		
		
		if(StringUtils.isNotEmpty(val.getNewValue().toString()) 
				|| StringUtils.isNotBlank(val.getNewValue().toString())){
			String codigoUnidadGeografica = (String)val.getNewValue();			
			if(StringUtils.isNotBlank(codigoUnidadGeografica) || 
					StringUtils.isNotEmpty(codigoUnidadGeografica)){
				this.loadNivel(codigoUnidadGeografica, 5);
			}
		}
	}
	
	public void loadNivel6(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("loadNivel2");
		}		
		
		if(StringUtils.isNotEmpty(val.getNewValue().toString()) 
				|| StringUtils.isNotBlank(val.getNewValue().toString())){
			String codigoUnidadGeografica = (String)val.getNewValue();			
			if(StringUtils.isNotBlank(codigoUnidadGeografica) || 
					StringUtils.isNotEmpty(codigoUnidadGeografica)){
				this.loadNivel(codigoUnidadGeografica, 6);
			}
		}
	}	
	
	private void loadNivel(String codigoUnidadGeografica, int NumeroNivel){
		MantenimientoZONCallesSearchForm f = (MantenimientoZONCallesSearchForm) this.formBusqueda;
		switch (NumeroNivel) {		
		case 2:
			this.setListaNivel2(obtenerListaGeoPorCodUniGeo(f.getOidPais(), codigoUnidadGeografica));
			break;
		case 3:
			this.setListaNivel3(obtenerListaGeoPorCodUniGeo(f.getOidPais(), codigoUnidadGeografica));
			break;
		case 4:
			this.setListaNivel4(obtenerListaGeoPorCodUniGeo(f.getOidPais(), codigoUnidadGeografica));
			break;
		case 5:
			this.setListaNivel5(obtenerListaGeoPorCodUniGeo(f.getOidPais(), codigoUnidadGeografica));
			break;		
		default:
			break;
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {		
		return null;
	}

	public LabelValue[] getMaeClienteNivel1List() {
		return maeClienteNivel1List;
	}

	public void setMaeClienteNivel1List(LabelValue[] maeClienteNivel1List) {
		this.maeClienteNivel1List = maeClienteNivel1List;
	}

	public List getMaeClienteTipoViaList() {
		return maeClienteTipoViaList;
	}

	public void setMaeClienteTipoViaList(List maeClienteTipoViaList) {
		this.maeClienteTipoViaList = maeClienteTipoViaList;
	}

	public List getZonCallesList() {
		return zonCallesList;
	}

	public void setZonCallesList(List zonCallesList) {
		this.zonCallesList = zonCallesList;
	}

	public LabelValue[] getListaNivel2() {
		return listaNivel2;
	}

	public void setListaNivel2(LabelValue[] listaNivel2) {
		this.listaNivel2 = listaNivel2;
	}

	public LabelValue[] getListaNivel3() {
		return listaNivel3;
	}

	public void setListaNivel3(LabelValue[] listaNivel3) {
		this.listaNivel3 = listaNivel3;
	}

	public LabelValue[] getListaNivel4() {
		return listaNivel4;
	}

	public void setListaNivel4(LabelValue[] listaNivel4) {
		this.listaNivel4 = listaNivel4;
	}

	public LabelValue[] getListaNivel5() {
		return listaNivel5;
	}

	public void setListaNivel5(LabelValue[] listaNivel5) {
		this.listaNivel5 = listaNivel5;
	}

	public LabelValue[] getListaNivel6() {
		return listaNivel6;
	}

	public void setListaNivel6(LabelValue[] listaNivel6) {
		this.listaNivel6 = listaNivel6;
	}

	public String getOidCalle() {
		return oidCalle;
	}

	public void setOidCalle(String oidCalle) {
		this.oidCalle = oidCalle;
	}

}
