package biz.belcorp.ssicc.web.spusicc.zon.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.zon.model.EstructuraGeopolitica;
import biz.belcorp.ssicc.dao.spusicc.zon.model.UnidadGeografica;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spusicc.zon.MantenimientoZONUnidadGeograficaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.zon.form.MantenimientoZONUnidadGeograficaForm;
import biz.belcorp.ssicc.web.spusicc.zon.form.MantenimientoZONUnidadGeograficaSearchForm;

/**
 * The Class MantenimientoZONUnidadGeograficaSearchAction.
 *
 * @author Belcorp
 * @version 1.0
 * 27/01/2015
 */
@ManagedBean
@SessionScoped
public class MantenimientoZONUnidadGeograficaSearchAction extends BaseMantenimientoSearchAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The zon unidad geografica list. */
	private List zonUnidadGeograficaList = new ArrayList();
	
	/** The zon estructura geopolitica list. */
	private List zonEstructuraGeopoliticaList = new ArrayList();
	
	/** The zon unidad geografica nivel1 list. */
	private LabelValue[] zonUnidadGeograficaNivel1List = {};
	
	/** The zon unidad geografica nivel2 list. */
	private LabelValue[] zonUnidadGeograficaNivel2List = {};
	
	/** The zon unidad geografica nivel3 list. */
	private LabelValue[] zonUnidadGeograficaNivel3List = {};
	
	/** The zon unidad geografica nivel4 list. */
	private LabelValue[] zonUnidadGeograficaNivel4List = {};
	
	/** The Lista estado. */
	private String ListaEstado = "";
	
	/** The id. */
	private String id="";
	
	/** The solo consulta. */
	private boolean soloConsulta = false;
	
	/**
	 * Gets the salir forward.
	 *
	 * @return the salir forward
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoZONUnidadGeograficaList";
	}

	/**
	 * Gets the pagina mantenimiento.
	 *
	 * @return the pagina mantenimiento
	 * @throws Exception the exception
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoZONUnidadGeograficaForm";
	}

	/**
	 * Devuelve form busqueda.
	 *
	 * @return the base search form
	 * @throws Exception the exception
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoZONUnidadGeograficaSearchForm form = new MantenimientoZONUnidadGeograficaSearchForm();
		return form;
	}

	/**
	 * Sets the find attributes.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes");
		}
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		
		MantenimientoZONUnidadGeograficaSearchForm f = (MantenimientoZONUnidadGeograficaSearchForm) this.formBusqueda;
		f.setCodigoPais(pais.getCodigo());
		
		MantenimientoZONUnidadGeograficaService mantenimientoZONUnidadGeograficaService = (MantenimientoZONUnidadGeograficaService) getBean("spusicc.mantenimientoZONUnidadGeograficaService");
		
		Map criteria = new HashMap();
		criteria.put("orde1", f.getNivel1());
		criteria.put("orde2", f.getNivel2());
		criteria.put("orde3", f.getNivel3());
		criteria.put("orde4", f.getNivel4());
		
		List lista = mantenimientoZONUnidadGeograficaService.getUnidadesGeograficasByCriteria(criteria);
		
		this.setZonUnidadGeograficaList(lista);
		
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		
		this.setZonUnidadGeograficaNivel1List(getListaNivel1(mantenimientoZONUnidadGeograficaService));
		
		if(StringUtils.isNotBlank(f.getNivel1())){
			this.setZonUnidadGeograficaNivel2List(ajax.getProvinciasPorDepartamento(f.getNivel1(), "Depa"));
		}
		if(StringUtils.isNotBlank(f.getNivel2())){
			this.setZonUnidadGeograficaNivel3List(ajax.getDistritosPorProvincia(f.getNivel2(), f.getNivel1(), "Prov"));
		}
		if(StringUtils.isNotBlank(f.getNivel3())){
			this.setZonUnidadGeograficaNivel4List(ajax.getCentrosPobladosPorDistrito(f.getNivel3(), f.getNivel2(), f.getNivel1(), "Dist"));
		}
		
		return lista;
	}

	/**
	 * Sets the delete attributes.
	 *
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setDeleteAttributes");
		}
		MantenimientoZONUnidadGeograficaSearchForm f = (MantenimientoZONUnidadGeograficaSearchForm)this.formBusqueda;		
		MantenimientoZONUnidadGeograficaService service = (MantenimientoZONUnidadGeograficaService) getBean("spusicc.mantenimientoZONUnidadGeograficaService");
		try	{
			service.deleteUnidadGeografica(this.getId(), this.getmPantallaPrincipalBean().getCurrentUser());
			this.addInfo("Info:", this.getResourceMessage("mantenimientoZONUnidadGeograficaForm.deleted"));
		}catch(InvalidIdentifierException iie){
			String errorCode = iie.getIdentifier().toString();
			this.addError("Error", errorCode);
			return false;
		}
		return true;
	}

	/**
	 * Sets the save attributes.
	 *
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setSaveAttributes");
		}
		MantenimientoZONUnidadGeograficaService service = (MantenimientoZONUnidadGeograficaService) getBean("spusicc.mantenimientoZONUnidadGeograficaService");		
		MantenimientoZONUnidadGeograficaForm f = (MantenimientoZONUnidadGeograficaForm)this.formMantenimiento;
		UnidadGeografica ug = new UnidadGeografica();
		BeanUtils.copyProperties(f, ug);
		try{
			if (f.isNewRecord()) {
				
				//Determinamos la estructura geopolitica
				if(StringUtils.isNotBlank(f.getNivel1()) && StringUtils.isBlank(f.getNivel2()) && StringUtils.isBlank(f.getNivel3()) && StringUtils.isBlank(f.getNivel4()))
					ug.setOidEstruGeopo(f.getOidEstructuraGeopolitica1());
				
				if(StringUtils.isNotBlank(f.getNivel1()) && StringUtils.isNotBlank(f.getNivel2()) && StringUtils.isBlank(f.getNivel3()) && StringUtils.isBlank(f.getNivel4()))
					ug.setOidEstruGeopo(f.getOidEstructuraGeopolitica2());
				
				if(StringUtils.isNotBlank(f.getNivel1()) && StringUtils.isNotBlank(f.getNivel2()) && StringUtils.isNotBlank(f.getNivel3()) && StringUtils.isBlank(f.getNivel4()))
					ug.setOidEstruGeopo(f.getOidEstructuraGeopolitica3());
				
				if(StringUtils.isNotBlank(f.getNivel1()) && StringUtils.isNotBlank(f.getNivel2()) && StringUtils.isNotBlank(f.getNivel3()) && StringUtils.isNotBlank(f.getNivel4()))
					ug.setOidEstruGeopo(f.getOidEstructuraGeopolitica4());
				//
				
				service.insertUnidadGeografica(ug, this.getmPantallaPrincipalBean().getCurrentUser());
				this.addInfo("info:", this.getResourceMessage("mantenimientoZONUnidadGeograficaForm.created"));				
			}else{
				service.updateUnidadGeografica(ug, this.getmPantallaPrincipalBean().getCurrentUser());
				this.addInfo("info:", this.getResourceMessage("mantenimientoZONUnidadGeograficaForm.updated"));
			}
		}catch(InvalidIdentifierException iie){
			String errorCode = iie.getIdentifier().toString();
			this.addError("Error:", errorCode);
			return false;
		}
		
		return true;
	}

	/**
	 * Sets the obtener registro attributes.
	 *
	 * @return the base edit form
	 * @throws Exception the exception
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("");
		}
		MantenimientoZONUnidadGeograficaForm form = new MantenimientoZONUnidadGeograficaForm();
		form.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		UnidadGeografica unidadGeografica = (UnidadGeografica)this.beanRegistroSeleccionado;
		form.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());		
		if(!this.accion.equals(this.ACCION_NUEVO)){
			this.setId(unidadGeografica.getOidValorEstructuraGeo());
			BeanUtils.copyProperties(form, unidadGeografica);
		}
		if(this.accion.equals(this.ACCION_NUEVO)){			
			form.setNewRecord(true);
			form.setEditable(false);
			this.setSoloConsulta(false);
		}
		if(this.accion.equals(this.ACCION_MODIFICAR)){
			form.setNewRecord(false);
			form.setEditable(true);
			this.setSoloConsulta(false);
		}
		if(this.accion.equals(this.ACCION_CONSULTAR)){
			this.setSoloConsulta(true);
			this.mostrarBotonSave = false;
		}else{
			this.setSoloConsulta(false);
			this.mostrarBotonSave = true;
		}
		return form;		
	}

	/**
	 * Sets the view atributes.
	 *
	 * @throws Exception the exception
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'setViewAttributes' method");
        }
		
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		MantenimientoZONUnidadGeograficaSearchForm f = (MantenimientoZONUnidadGeograficaSearchForm) this.formBusqueda;
		f.setCodigoPais(pais.getCodigo());
		MantenimientoZONUnidadGeograficaService mantenimientoZONUnidadGeograficaService = (MantenimientoZONUnidadGeograficaService) getBean("spusicc.mantenimientoZONUnidadGeograficaService");
		//Obtenemos la estructura geopolitica
		List estructuraGeo = mantenimientoZONUnidadGeograficaService.getEstructuraGeopoliticaList();
		this.setZonEstructuraGeopoliticaList(estructuraGeo);
		this.setZonUnidadGeograficaNivel1List(getListaNivel1(mantenimientoZONUnidadGeograficaService));
        f.setNivel1("1");        
	}

	/**
	 * Gets the lista nivel1.
	 *
	 * @param service the service
	 * @return the lista nivel1
	 */
	private LabelValue[] getListaNivel1(MantenimientoZONUnidadGeograficaService service){
		Map criteria = new HashMap();
		LabelValue []listaNivel1 = new LabelValue[0];
		List lista = service.getUnidadesGeograficasList(criteria);
		
		if(lista != null && lista.size() > 0)
		{
			listaNivel1 = new LabelValue[lista.size()];  
			for(int i=0; i<lista.size(); i++)
			{
				UnidadGeografica ug = (UnidadGeografica) lista.get(i);
				LabelValue item = new LabelValue(ug.getDescripcion(), ug.getNivel1());				
				listaNivel1[i] = item;
			}
		}		
		return listaNivel1;
	}
	
	/**
	 * Load provincias.
	 *
	 * @param codigo the codigo
	 */
	public void loadProvincias(String codigo){
		if(log.isDebugEnabled()){
			log.debug("loadProvincias");			
		}
		MantenimientoZONUnidadGeograficaSearchForm f = (MantenimientoZONUnidadGeograficaSearchForm) this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		f.setNivel1(codigo);
		LabelValue[] lista =  ajax.getProvinciasPorDepartamento(f.getNivel1(), "Depa");
		if(lista.length > 0){
//			f.setNivel2(((LabelValue)lista[0]).getValue());
			this.zonUnidadGeograficaNivel2List = lista;			
		}		
	}
	
	/**
	 * Load distritos.
	 *
	 * @param codigo the codigo
	 */
	public void loadDistritos(String codigo){
		if(log.isDebugEnabled()){
			log.debug("loadDistritos");			
		}
		MantenimientoZONUnidadGeograficaSearchForm f = (MantenimientoZONUnidadGeograficaSearchForm) this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		f.setNivel2(codigo);
		LabelValue[] lista =  ajax.getDistritosPorProvincia(f.getNivel2(),f.getNivel1(), "Prov");
		if(lista.length > 0){
//			f.setNivel3(((LabelValue)lista[0]).getValue());
			this.zonUnidadGeograficaNivel3List = lista;			
		}		
	}
	
	/**
	 * Load centros poblados.
	 *
	 * @param codigo the codigo
	 */
	public void loadCentrosPoblados(String codigo){
		if(log.isDebugEnabled()){
			log.debug("loadCentrosPoblados");			
		}
		MantenimientoZONUnidadGeograficaSearchForm f = (MantenimientoZONUnidadGeograficaSearchForm) this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		f.setNivel3(codigo);
		LabelValue[] lista =  ajax.getCentrosPobladosPorDistrito(f.getNivel3(),f.getNivel2(), f.getNivel1(), "Dist");
		if(lista.length > 0){
//			f.setNivel4(((LabelValue)lista[0]).getValue());
			this.zonUnidadGeograficaNivel4List = lista;			
		}		
	}
	
	/**
	 * Load nivel1.
	 *
	 * @param e the e
	 */
	public void loadNivel1(ValueChangeEvent e){
		if(log.isDebugEnabled()){
			log.debug("loadNivel1");
		}
		String valor = (String)e.getNewValue();
		this.loadProvincias(valor);
	}
	
	/**
	 * Load nivel2.
	 *
	 * @param e the e
	 */
	public void loadNivel2(ValueChangeEvent e){
		if(log.isDebugEnabled()){
			log.debug("loadNivel2");
		}
		String valor = (String)e.getNewValue();
		this.loadDistritos(valor);
	}
	
	/**
	 * Load nivel3.
	 *
	 * @param e the e
	 */
	public void loadNivel3(ValueChangeEvent e){
		if(log.isDebugEnabled()){
			log.debug("loadNivel3");
		}
		String valor = (String)e.getNewValue();
		this.loadCentrosPoblados(valor);
	}
	
	/**
	 * Load nivel4.
	 *
	 * @param e the e
	 */
	public void loadNivel4(ValueChangeEvent e){
		if(log.isDebugEnabled()){
			log.debug("loadNivel4");
		}
		MantenimientoZONUnidadGeograficaSearchForm f = (MantenimientoZONUnidadGeograficaSearchForm) this.formBusqueda;
		String valor = (String)e.getNewValue();
		f.setNivel4(valor);
	}
	
	/**
	 * Sets the add attributes.
	 *
	 * @throws Exception the exception
	 */
	@Override
	protected void setAddAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setAddAttributes");
		}
		MantenimientoZONUnidadGeograficaForm f = (MantenimientoZONUnidadGeograficaForm)this.formMantenimiento;		
		MantenimientoZONUnidadGeograficaForm n = new MantenimientoZONUnidadGeograficaForm();		
		f.setIndicadorGeoreferencia(Constants.NUMERO_CERO);
		this.establecerValores();
		BeanUtils.copyProperties(n, f);
		f.setIndicadorGeoreferencia(Constants.NUMERO_CERO);
		f.setDescripcionActual(null);
	}
	
	/**
	 * Sets the edit attributes.
	 *
	 * @throws Exception the exception
	 */
	@Override
	protected void setEditAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setEditAttributes");
		}
		MantenimientoZONUnidadGeograficaForm f = (MantenimientoZONUnidadGeograficaForm)formMantenimiento;
		this.establecerValores();
		cargarDatos(f, this.getId());
	}
	
	/**
	 * Establecer valores.
	 */
	private void establecerValores(){
		MantenimientoZONUnidadGeograficaForm f = (MantenimientoZONUnidadGeograficaForm)this.formMantenimiento;
		int i = 0;
		int tamanio = this.zonEstructuraGeopoliticaList.size();
		if(tamanio>0){
			for(i = 1; i <=tamanio; i++){
				switch (i) {
					case 1:					
						BeanPredicate beanPredicate1 = new BeanPredicate("codigoOrden", new EqualPredicate(new String("1")));
						if(CollectionUtils.exists(this.zonEstructuraGeopoliticaList, beanPredicate1)){
							Object obj = CollectionUtils.find(this.zonEstructuraGeopoliticaList, beanPredicate1);
							f.setOidEstructuraGeopolitica1(((EstructuraGeopolitica)obj).getOid());
						}
						break;
					case 2:					
						BeanPredicate beanPredicate2 = new BeanPredicate("codigoOrden", new EqualPredicate(new String("2")));
						if(CollectionUtils.exists(this.zonEstructuraGeopoliticaList, beanPredicate2)){
							Object obj = CollectionUtils.find(this.zonEstructuraGeopoliticaList, beanPredicate2);
							f.setOidEstructuraGeopolitica2(((EstructuraGeopolitica)obj).getOid());
						}
						break;
					case 3:					
						BeanPredicate beanPredicate3 = new BeanPredicate("codigoOrden", new EqualPredicate(new String("3")));
						if(CollectionUtils.exists(this.zonEstructuraGeopoliticaList, beanPredicate3)){
							Object obj = CollectionUtils.find(this.zonEstructuraGeopoliticaList, beanPredicate3);
							f.setOidEstructuraGeopolitica3(((EstructuraGeopolitica)obj).getOid());
						}
						break;
					case 4:					
						BeanPredicate beanPredicate4 = new BeanPredicate("codigoOrden", new EqualPredicate(new String("4")));
						if(CollectionUtils.exists(this.zonEstructuraGeopoliticaList, beanPredicate4)){
							Object obj = CollectionUtils.find(this.zonEstructuraGeopoliticaList, beanPredicate4);
							f.setOidEstructuraGeopolitica4(((EstructuraGeopolitica)obj).getOid());
						}
						break;
					default:
						break;
				}
			}
		}
	}
	
	/**
	 * Cargar datos.
	 *
	 * @param f the f
	 * @param id the id
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	private void cargarDatos(MantenimientoZONUnidadGeograficaForm f, String id) throws IllegalAccessException, InvocationTargetException{
		MantenimientoZONUnidadGeograficaService service = (MantenimientoZONUnidadGeograficaService) getBean("spusicc.mantenimientoZONUnidadGeograficaService");
		Map criteria = new HashMap();
		criteria.put("oid", id);
		List lista = service.getUnidadesGeograficasByCriteria(criteria);
		
		if(lista != null && lista.size() > 0){
			UnidadGeografica ug = (UnidadGeografica)lista.get(0);
			ug.setDescripcionActual(ug.getDescripcion());
			BeanUtils.copyProperties(ug, f);
			f.setOidValorEstructuraGeo(id);
		}
	}

	/**
	 * Gets the zon unidad geografica list.
	 *
	 * @return the zon unidad geografica list
	 */
	public List getZonUnidadGeograficaList() {
		return zonUnidadGeograficaList;
	}

	/**
	 * Sets the zon unidad geografica list.
	 *
	 * @param zonUnidadGeograficaList the new zon unidad geografica list
	 */
	public void setZonUnidadGeograficaList(List zonUnidadGeograficaList) {
		this.zonUnidadGeograficaList = zonUnidadGeograficaList;
	}

	/**
	 * Gets the zon estructura geopolitica list.
	 *
	 * @return the zon estructura geopolitica list
	 */
	public List getZonEstructuraGeopoliticaList() {
		return zonEstructuraGeopoliticaList;
	}

	/**
	 * Sets the zon estructura geopolitica list.
	 *
	 * @param zonEstructuraGeopoliticaList the new zon estructura geopolitica list
	 */
	public void setZonEstructuraGeopoliticaList(List zonEstructuraGeopoliticaList) {
		this.zonEstructuraGeopoliticaList = zonEstructuraGeopoliticaList;
	}

	/**
	 * Gets the zon unidad geografica nivel1 list.
	 *
	 * @return the zon unidad geografica nivel1 list
	 */
	public LabelValue[] getZonUnidadGeograficaNivel1List() {
		return zonUnidadGeograficaNivel1List;
	}

	/**
	 * Sets the zon unidad geografica nivel1 list.
	 *
	 * @param zonUnidadGeograficaNivel1List the new zon unidad geografica nivel1 list
	 */
	public void setZonUnidadGeograficaNivel1List(
			LabelValue[] zonUnidadGeograficaNivel1List) {
		this.zonUnidadGeograficaNivel1List = zonUnidadGeograficaNivel1List;
	}

	/**
	 * Gets the zon unidad geografica nivel2 list.
	 *
	 * @return the zon unidad geografica nivel2 list
	 */
	public LabelValue[] getZonUnidadGeograficaNivel2List() {
		return zonUnidadGeograficaNivel2List;
	}

	/**
	 * Sets the zon unidad geografica nivel2 list.
	 *
	 * @param zonUnidadGeograficaNivel2List the new zon unidad geografica nivel2 list
	 */
	public void setZonUnidadGeograficaNivel2List(
			LabelValue[] zonUnidadGeograficaNivel2List) {
		this.zonUnidadGeograficaNivel2List = zonUnidadGeograficaNivel2List;
	}

	/**
	 * Gets the zon unidad geografica nivel3 list.
	 *
	 * @return the zon unidad geografica nivel3 list
	 */
	public LabelValue[] getZonUnidadGeograficaNivel3List() {
		return zonUnidadGeograficaNivel3List;
	}

	/**
	 * Sets the zon unidad geografica nivel3 list.
	 *
	 * @param zonUnidadGeograficaNivel3List the new zon unidad geografica nivel3 list
	 */
	public void setZonUnidadGeograficaNivel3List(
			LabelValue[] zonUnidadGeograficaNivel3List) {
		this.zonUnidadGeograficaNivel3List = zonUnidadGeograficaNivel3List;
	}

	/**
	 * Gets the zon unidad geografica nivel4 list.
	 *
	 * @return the zon unidad geografica nivel4 list
	 */
	public LabelValue[] getZonUnidadGeograficaNivel4List() {
		return zonUnidadGeograficaNivel4List;
	}

	/**
	 * Sets the zon unidad geografica nivel4 list.
	 *
	 * @param zonUnidadGeograficaNivel4List the new zon unidad geografica nivel4 list
	 */
	public void setZonUnidadGeograficaNivel4List(
			LabelValue[] zonUnidadGeograficaNivel4List) {
		this.zonUnidadGeograficaNivel4List = zonUnidadGeograficaNivel4List;
	}

	/**
	 * Gets the lista estado.
	 *
	 * @return the lista estado
	 */
	public String getListaEstado() {
		return ListaEstado;
	}

	/**
	 * Sets the lista estado.
	 *
	 * @param listaEstado the new lista estado
	 */
	public void setListaEstado(String listaEstado) {
		ListaEstado = listaEstado;
	}	

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Checks if is solo consulta.
	 *
	 * @return true, if is solo consulta
	 */
	public boolean isSoloConsulta() {
		return soloConsulta;
	}

	/**
	 * Sets the solo consulta.
	 *
	 * @param soloConsulta the new solo consulta
	 */
	public void setSoloConsulta(boolean soloConsulta) {
		this.soloConsulta = soloConsulta;
	}
}
