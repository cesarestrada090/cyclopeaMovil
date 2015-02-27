package biz.belcorp.ssicc.web.spusicc.ape.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.ape.model.ConfiguracionTextosVariables;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.IdiomaService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEConfiguracionTextosVariablesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.ape.form.MantenimientoAPEConfiguracionTextosVariablesForm;
import biz.belcorp.ssicc.web.spusicc.ape.form.MantenimientoAPEConfiguracionTextosVariablesSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoAPEConfiguracionTextosVariablesAction extends BaseMantenimientoSearchAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1463661692368475390L;


	private String sConfTextVari;
	private String sCodigoPais;
	private String sCodigoTipoCliente;
	private String sCodigoSubTipoCliente;
	private String sCodigoTipoClasificacion;
	private String sCodigoClasificacion;
	private String sValTextVari;
	
	List listaTipoCliente;
	LabelValue[] listaSubTipoCliente;
	LabelValue[] listaTipoClasificacion;
	LabelValue[] listaClasificacion;
	
	
	
	/**
	 * @return the listaSubTipoCliente
	 */
	public LabelValue[] getListaSubTipoCliente() {
		return listaSubTipoCliente;
	}

	/**
	 * @param listaSubTipoCliente the listaSubTipoCliente to set
	 */
	public void setListaSubTipoCliente(LabelValue[] listaSubTipoCliente) {
		this.listaSubTipoCliente = listaSubTipoCliente;
	}

	/**
	 * @return the listaTipoClasificacion
	 */
	public LabelValue[] getListaTipoClasificacion() {
		return listaTipoClasificacion;
	}

	/**
	 * @param listaTipoClasificacion the listaTipoClasificacion to set
	 */
	public void setListaTipoClasificacion(LabelValue[] listaTipoClasificacion) {
		this.listaTipoClasificacion = listaTipoClasificacion;
	}

	/**
	 * @return the listaClasificacion
	 */
	public LabelValue[] getListaClasificacion() {
		return listaClasificacion;
	}

	/**
	 * @param listaClasificacion the listaClasificacion to set
	 */
	public void setListaClasificacion(LabelValue[] listaClasificacion) {
		this.listaClasificacion = listaClasificacion;
	}

	/**
	 * @return the listaTipoCliente
	 */
	public List getListaTipoCliente() {
		return listaTipoCliente;
	}

	/**
	 * @param listaTipoCliente the listaTipoCliente to set
	 */
	public void setListaTipoCliente(List listaTipoCliente) {
		this.listaTipoCliente = listaTipoCliente;
	}

	
	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoAPEConfiguracionTextosVariablesSearchForm";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoAPEConfiguracionTextosVariablesForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoAPEConfiguracionTextosVariablesSearchForm objForm = new MantenimientoAPEConfiguracionTextosVariablesSearchForm();
		return objForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoAPEConfiguracionTextosVariablesSearchForm f = (MantenimientoAPEConfiguracionTextosVariablesSearchForm)this.formBusqueda;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoAPEConfiguracionTextosVariablesService serviceConfiguracionTextosVariables = 
			(MantenimientoAPEConfiguracionTextosVariablesService)getBean("spusicc.mantenimientoAPEConfiguracionTextosVariablesService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		Map criteriaPais = new HashMap();
		Map criteria = new HashMap();
		
		criteriaPais.put("codigoPais", pais.getCodigo());
		criteria.put("oidPais", reporteService.getOidString("getOidPaisByCodigoPais", criteriaPais));
		
		
		criteria.put("oidTipoClie", f.getCodigoTipoCliente());
		criteria.put("oidSubtClie", f.getCodigoSubTipoCliente());
		criteria.put("oidTipoClas", f.getCodigoTipoClasificacion());
		criteria.put("oidClas", f.getCodigoClasificacion());
		
		List resultado = (List)serviceConfiguracionTextosVariables.getConfiTextoVariaList(criteria);
		
		f.setCodigoSubTipoClienteDefault(f.getCodigoSubTipoCliente());
		f.setCodigoTipoClasificacionDefault(f.getCodigoTipoClasificacion());
		f.setCodigoClasificacionDefault(f.getCodigoClasificacion());
		
		//String sMod = (String)session.getAttribute("Modificado");
		return resultado;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Entering 'delete' method");
		Map bean = (HashMap)this.beanRegistroSeleccionado;
		MantenimientoAPEConfiguracionTextosVariablesForm f = new MantenimientoAPEConfiguracionTextosVariablesForm();
		BeanUtils.copyProperties(f, bean);
		
		MantenimientoAPEConfiguracionTextosVariablesService service = 
			(MantenimientoAPEConfiguracionTextosVariablesService)getBean("spusicc.mantenimientoAPEConfiguracionTextosVariablesService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
		Map criteriaPais = new HashMap();
		
		f.setCodigoPais(pais.getCodigo());
		criteriaPais.put("codigoPais", pais.getCodigo());
		criteria.put("oidPais", reporteService.getOidString("getOidPaisByCodigoPais", criteriaPais));
		
		String[] items = {f.getConfTextVari()};
		service.deleteConfiguracionTextosVariables(criteria,items);
	
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoAPEConfiguracionTextosVariablesForm f = (MantenimientoAPEConfiguracionTextosVariablesForm)this.formMantenimiento;
		MantenimientoAPEConfiguracionTextosVariablesService service = 
			(MantenimientoAPEConfiguracionTextosVariablesService)getBean("spusicc.mantenimientoAPEConfiguracionTextosVariablesService");
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");		
		
		ConfiguracionTextosVariables ConfiguracionTextosVariables = new ConfiguracionTextosVariables();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		BeanUtils.copyProperties(ConfiguracionTextosVariables, f);
		Map params = new HashMap();
		f.setCodigoPais(pais.getCodigo());
		Map criteriaPais = new HashMap();
		Map criteria = new HashMap();
		
		criteriaPais.put("codigoPais", pais.getCodigo());
		criteria.put("oidPais", reporteService.getOidString("getOidPaisByCodigoPais", criteriaPais));
		params.put("oidPais", reporteService.getOidString("getOidPaisByCodigoPais", criteriaPais));
			
		f.setCodigoSubTipoClienteDefault(f.getCodigoSubTipoCliente());
		f.setCodigoTipoClasificacionDefault(f.getCodigoTipoClasificacion());
		f.setCodigoClasificacionDefault(f.getCodigoClasificacion());
		
		if(this.accion.equals(this.ACCION_MODIFICAR)){
			if (log.isDebugEnabled()) {
				log.debug("EN EL CASO QUE SEA MODIFICACION");
			}
			params.put("oidConfTextVari", f.getConfTextVari());
			params.put("valTextVari", f.getValTextVari());
			service.updateConfiguracionTextosVariables(params);
			
		}else{
			if (log.isDebugEnabled()) {
				log.debug("EN EL CASO QUE SEA CREACION");
			}
			params.put("oidTipoClie", f.getCodigoTipoCliente());
			params.put("oidSubtClie", f.getCodigoSubTipoCliente());
			params.put("oidTipoClas", f.getCodigoTipoClasificacion());
			params.put("oidClas", f.getCodigoClasificacion());
			params.put("valTextVari", f.getValTextVari());
			
			Integer nExisteConfiguracionTextosVariables = service.getExisteConfiguracionTextosVariablesCD(params);
			if (nExisteConfiguracionTextosVariables == 0){
				int oidConfiguracionTextosVariablesNuevo = service.getNextOidConfiguracionTextosVariables();
				params.put("oidSec", oidConfiguracionTextosVariablesNuevo);
				service.insertConfiguracionTextosVariables(params);
			}
			else{
				this.addError("Error: ", "mantenimientoAPEConfiguracionTextosVariablesForm.cabecera.existe");
				return false;
			}
			
		}
		//session.setAttribute("Modificado", "1");
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		
		log.debug("Entering 'edit' method");

		MantenimientoAPEConfiguracionTextosVariablesForm f = new MantenimientoAPEConfiguracionTextosVariablesForm();
		MantenimientoAPEConfiguracionTextosVariablesService service = 
			(MantenimientoAPEConfiguracionTextosVariablesService)getBean("spusicc.mantenimientoAPEConfiguracionTextosVariablesService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		

		
		if(this.accion.equals(this.ACCION_CONSULTAR)){f.setActivo(false);}
		
		if(!this.accion.equals(this.ACCION_NUEVO)){
			Map bean = (HashMap)this.beanRegistroSeleccionado;
			BeanUtils.copyProperties(f, bean);
			
			String id  = f.getConfTextVari();
			ObtieneObjetoConfiguracionTextosVariables(f, id);
			
			f.setCodigoSubTipoClienteDefault(f.getCodigoSubTipoCliente());
			f.setCodigoTipoClasificacionDefault(f.getCodigoTipoClasificacion());
	        f.setCodigoClasificacionDefault(f.getCodigoClasificacion());		
		}
		
		loadCombos(f);
		
		f.setCodigoPais(pais.getCodigo());
        return f;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoAPEConfiguracionTextosVariablesSearchForm f = (MantenimientoAPEConfiguracionTextosVariablesSearchForm)this.formBusqueda;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoAPEConfiguracionTextosVariablesService serviceConfiguracionTextosVariables = 
			(MantenimientoAPEConfiguracionTextosVariablesService)getBean("spusicc.mantenimientoAPEConfiguracionTextosVariablesService");
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		f.setCodigoPais(pais.getCodigo());
		Map criteriaPais = new HashMap();
		Map criteria = new HashMap();
		
		criteriaPais.put("codigoPais", pais.getCodigo());
		criteria.put("oidPais", reporteService.getOidString("getOidPaisByCodigoPais", criteriaPais));
		
		this.listaTipoCliente = serviceConfiguracionTextosVariables.getTipoClienteList();
		

		// Guardamos el oid de idioma en sesion
		Idioma idioma = this.mPantallaPrincipalBean.getCurrentIdioma();
		Map parameterMap = new HashMap();
		parameterMap.put("codigoIdiomaIso", idioma.getCodigoSiCC());
		parameterMap.put("codigoIdioma", idioma.getCodigoISO());
		String oidIdiomaIso = reporteService.getOidString("getOidIdiomaByCodigoIdiomaIso", parameterMap);
		
	}
	
	/**
	 * Metodo que carga los combos iniciales
	 * @param session
	 * @param service
	 * @param criteria
	 */
	
	private void loadCombos(MantenimientoAPEConfiguracionTextosVariablesForm f) {
		
		MantenimientoAPEConfiguracionTextosVariablesService serviceConfiguracionTextosVariables = 
				(MantenimientoAPEConfiguracionTextosVariablesService)getBean("spusicc.mantenimientoAPEConfiguracionTextosVariablesService");
		
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		
		this.listaTipoCliente = serviceConfiguracionTextosVariables.getTipoClienteList();
		
		if(StringUtils.isNotBlank(f.getCodigoTipoCliente())){
			this.listaSubTipoCliente = aSvc.getSubTiClienteList(f.getCodigoTipoCliente());
		}
		if(StringUtils.isNotBlank(f.getCodigoSubTipoCliente())){
			this.listaTipoClasificacion = aSvc.getTipoClasificacionByOidSubTipoClienteList(f.getCodigoSubTipoCliente());
		}
		if(StringUtils.isNotBlank(f.getCodigoTipoClasificacion())){
			this.listaClasificacion = aSvc.getClasificacionByOidTipoClasificacionList(f.getCodigoTipoClasificacion());
		}	
	}
	
	/**
	 * Obtiene el Objeto de SubLinea de Armado
	 * @param request
	 * @param form
	 * @throws Exception
	 */
	private void ObtieneObjetoConfiguracionTextosVariables( MantenimientoAPEConfiguracionTextosVariablesForm form, String idSele )throws Exception {
		
		MantenimientoAPEConfiguracionTextosVariablesForm f = (MantenimientoAPEConfiguracionTextosVariablesForm)form;
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String id = idSele;
		
		Map criteriaPais = new HashMap();
		criteriaPais.put("codigoPais", pais.getCodigo());
		
		Map map = new HashMap();
		map.put("oidPais", reporteService.getOidString("getOidPaisByCodigoPais", criteriaPais));
		map.put("codigoPais", pais.getCodigo());
		
		if (log.isDebugEnabled()) {
			log.debug("Id seleccionado de la lista: " + id);
		}
		
		this.sConfTextVari            = StringUtils.split(id, "|")[0];		
		this.sCodigoTipoCliente       = StringUtils.split(id, "|")[1];
		this.sCodigoSubTipoCliente    = StringUtils.split(id, "|")[2];
		this.sCodigoTipoClasificacion = StringUtils.split(id, "|")[3];
		this.sCodigoClasificacion     = StringUtils.split(id, "|")[4];
		this.sValTextVari             = StringUtils.split(id, "|")[5];
		
		f.setConfTextVari(sConfTextVari);
		f.setCodigoTipoCliente(sCodigoTipoCliente);
		f.setCodigoSubTipoCliente(sCodigoSubTipoCliente);
		f.setCodigoTipoClasificacion(sCodigoTipoClasificacion);
		f.setCodigoClasificacion(sCodigoClasificacion);
		f.setValTextVari(sValTextVari);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
	
		if(this.accion.equals(this.ACCION_NUEVO)){
			return "mantenimientoAPEConfiguracionTextosVariablesForm.cabecera.insert";
		}else{
			return "mantenimientoAPEConfiguracionTextosVariablesForm.cabecera.updated";
		}	
	}
	
	public void loadSubTipoCliente(ValueChangeEvent val){
		if (log.isDebugEnabled()) {
			log.debug("loadSubTipoCliente");
		}
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		String valor = (String) val.getNewValue();
		this.listaSubTipoCliente = aSvc.getSubTiClienteList(valor);
	}
	
	public void loadTipoClasificacion(ValueChangeEvent val){
		if (log.isDebugEnabled()) {
			log.debug("loadTipoClasificacion");
		}
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		String valor = (String) val.getNewValue();
		
		this.listaTipoClasificacion = aSvc.getTipoClasificacionByOidSubTipoClienteList(valor);
	}
	
	public void loadClasificacion(ValueChangeEvent val){
		if (log.isDebugEnabled()) {
			log.debug("loadClasificacion");
		}
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		String valor = (String) val.getNewValue();
		
		this.listaClasificacion = aSvc.getClasificacionByOidTipoClasificacionList(valor);
	}
	
	
}
