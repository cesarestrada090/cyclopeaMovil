package biz.belcorp.ssicc.web.spusicc.ape.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.ape.model.UnidadAdministrativa;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEConfiguracionCentroDistribucionService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPESubLineaArmadoService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEUnidadesAdministrativasLineaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.ape.form.MantenimientoAPEUnidadesAdministrativasLineaForm;
import biz.belcorp.ssicc.web.spusicc.ape.form.MantenimientoAPEUnidadesAdministrativasLineaSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoAPEUnidadesAdministrativasLineaAction extends BaseMantenimientoSearchAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2243288465125963264L;
	LabelValue[] listaLineaArmado;
	LabelValue[] listZonas;
	LabelValue[] listaSeccion;
	List listaRegion;
	List listaCD;
	List listaNivelAgrpOlas;
	List prueba;
	boolean flgRegion;
	boolean flgZona;
	boolean flgSeccion;
	
	
	/**
	 * @return the flgRegion
	 */
	public boolean isFlgRegion() {
		return flgRegion;
	}

	/**
	 * @param flgRegion the flgRegion to set
	 */
	public void setFlgRegion(boolean flgRegion) {
		this.flgRegion = flgRegion;
	}

	/**
	 * @return the flgZona
	 */
	public boolean isFlgZona() {
		return flgZona;
	}

	/**
	 * @param flgZona the flgZona to set
	 */
	public void setFlgZona(boolean flgZona) {
		this.flgZona = flgZona;
	}

	/**
	 * @return the flgSeccion
	 */
	public boolean isFlgSeccion() {
		return flgSeccion;
	}

	/**
	 * @param flgSeccion the flgSeccion to set
	 */
	public void setFlgSeccion(boolean flgSeccion) {
		this.flgSeccion = flgSeccion;
	}

	/**
	 * @return the listZonas
	 */
	public LabelValue[] getListZonas() {
		return listZonas;
	}

	/**
	 * @param listZonas the listZonas to set
	 */
	public void setListZonas(LabelValue[] listZonas) {
		this.listZonas = listZonas;
	}

	/**
	 * @return the listaLineaArmado
	 */
	public LabelValue[] getListaLineaArmado() {
		return listaLineaArmado;
	}

	/**
	 * @param listaLineaArmado the listaLineaArmado to set
	 */
	public void setListaLineaArmado(LabelValue[] listaLineaArmado) {
		this.listaLineaArmado = listaLineaArmado;
	}
	
	/**
	 * @return the listaSeccion
	 */
	public LabelValue[] getListaSeccion() {
		return listaSeccion;
	}

	/**
	 * @param listaSeccion the listaSeccion to set
	 */
	public void setListaSeccion(LabelValue[] listaSeccion) {
		this.listaSeccion = listaSeccion;
	}

	/**
	 * @return the listaRegion
	 */
	public List getListaRegion() {
		return listaRegion;
	}

	/**
	 * @param listaRegion the listaRegion to set
	 */
	public void setListaRegion(List listaRegion) {
		this.listaRegion = listaRegion;
	}

	/**
	 * @return the listaCD
	 */
	public List getListaCD() {
		return listaCD;
	}

	/**
	 * @param listaCD the listaCD to set
	 */
	public void setListaCD(List listaCD) {
		this.listaCD = listaCD;
	}

	/**
	 * @return the listaNivelAgrpOlas
	 */
	public List getListaNivelAgrpOlas() {
		return listaNivelAgrpOlas;
	}

	/**
	 * @param listaNivelAgrpOlas the listaNivelAgrpOlas to set
	 */
	public void setListaNivelAgrpOlas(List listaNivelAgrpOlas) {
		this.listaNivelAgrpOlas = listaNivelAgrpOlas;
	}

	/**
	 * @return the prueba
	 */
	public List getPrueba() {
		return prueba;
	}

	/**
	 * @param prueba the prueba to set
	 */
	public void setPrueba(List prueba) {
		this.prueba = prueba;
	}

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoAPEUnidadesAdministrativasLineaSearchForm";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoAPEUnidadesAdministrativasLineaForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoAPEUnidadesAdministrativasLineaSearchForm objForm = new MantenimientoAPEUnidadesAdministrativasLineaSearchForm();
		return objForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Entering 'find' method");
		
		//--  Variables
		MantenimientoAPEUnidadesAdministrativasLineaSearchForm f = (MantenimientoAPEUnidadesAdministrativasLineaSearchForm)this.formBusqueda;
		MantenimientoAPEUnidadesAdministrativasLineaService service = 
				(MantenimientoAPEUnidadesAdministrativasLineaService)this.getBean("spusicc.mantenimientoAPEUnidadesAdministrativasLineaService");
		MantenimientoAPESubLineaArmadoService serviceSL = 
				(MantenimientoAPESubLineaArmadoService)this.getBean("spusicc.mantenimientoAPESubLineaArmadoService");
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		//-- Crear Pojo
		Map criteria = new HashMap();
		
		//-- Lógica de Negocio
		criteria.put("codigoPais", pais.getCodigo());
		String oidPais=reporteService.getOidString("getOidPaisByCodigoPais", criteria);
		criteria.put("oidPais", oidPais);
		criteria.put("codigoPais", f.getCodigoPais());
		
		if (f.getCodigoCD().equals("0")) {
			criteria.put("oidCentro", null);
		}else{
			criteria.put("codCentro", f.getCodigoCD());
			String OidCentroDistribucion = service.getOidCentroDistribucionPais(criteria);
			criteria.put("oidCentro", OidCentroDistribucion);
		}
		
		if (f.getCodigoLineaArmado().equals("0")){
			 criteria.put("oidLinArmado",null);
		}else{
			criteria.put("codLinea",f.getCodigoLineaArmado());
			String oIdLineaArmado = serviceSL.getOidLineaArmadobyCodigo(criteria);
			criteria.put("oidLinArmado", oIdLineaArmado);
		}
		
		List resultado = (List)service.getUnidadAdministrativaLineaList(criteria);
		f.setCodigoCD(f.getCodigoCD());
		f.setCodigoLineaArmadoSelected(f.getCodigoLineaArmado());
		
		return resultado;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Entering 'delete' method");
		
		Map bean = (HashMap)this.beanRegistroSeleccionado;
		// Variables
		MantenimientoAPEUnidadesAdministrativasLineaForm f = new MantenimientoAPEUnidadesAdministrativasLineaForm();
		MantenimientoAPEUnidadesAdministrativasLineaService service = 
			(MantenimientoAPEUnidadesAdministrativasLineaService)this.getBean("spusicc.mantenimientoAPEUnidadesAdministrativasLineaService");
		BeanUtils.copyProperties(f, bean);
		//Crear Pojo
		Map criteria = new HashMap();
		String[] items = {f.getCodigoLineaArmado()};

		// Lógica de Negocios
		service.deleteUnidadAdministrativa(criteria,items);
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Entering 'save' method");
		//--  Variables
		MantenimientoAPEUnidadesAdministrativasLineaForm f = (MantenimientoAPEUnidadesAdministrativasLineaForm)this.formMantenimiento;
		MantenimientoAPEUnidadesAdministrativasLineaService service = 
			(MantenimientoAPEUnidadesAdministrativasLineaService)this.getBean("spusicc.mantenimientoAPEUnidadesAdministrativasLineaService");
		MantenimientoAPEConfiguracionCentroDistribucionService serviceCentro = 
			(MantenimientoAPEConfiguracionCentroDistribucionService)this.getBean("spusicc.mantenimientoAPEConfiguracionCentroDistribucionService");
		MantenimientoAPESubLineaArmadoService serviceSL = 
			(MantenimientoAPESubLineaArmadoService)this.getBean("spusicc.mantenimientoAPESubLineaArmadoService");
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		UnidadAdministrativa unidadadministrativa = new UnidadAdministrativa();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map paiscriteria = new HashMap();
		
		// -- Lógica de Negocio
		paiscriteria.put("codigoPais", pais.getCodigo());
		String oidPais=reporteService.getOidString("getOidPaisByCodigoPais", paiscriteria);
		
		// Crear Pojo
		BeanUtils.copyProperties(unidadadministrativa, f);
		Map params = new HashMap();
		
		String[] arrCodRegion = {f.getCodigoRegion()};
		String[] arrCodZona	  = {f.getCodigoZona()};
		String[] arrCodSeccion= {f.getCodigoSeccion()};
		
		// -- Lógica de Negocio
		params.put("oidPais", oidPais);
		params.put("codigoPais", pais.getCodigo());
		
		params.put("codLinea",unidadadministrativa.getCodigoLineaArmado());
		String oIdLineaArmado = serviceSL.getOidLineaArmadobyCodigo(params); 
		
		params.put("oidLineaArmado", oIdLineaArmado);
		params.put("oidAAFP",unidadadministrativa.getCodigoNivelAgrpOlas());
		
		service.insertUnidadAdministrativaLinea(params,arrCodRegion,arrCodZona,arrCodSeccion);
		
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("---- Form Unidades Adm Linea : Ingreso ----");		
		//--  Variables
		MantenimientoAPEUnidadesAdministrativasLineaForm f = new MantenimientoAPEUnidadesAdministrativasLineaForm();
		MantenimientoAPEUnidadesAdministrativasLineaService service = 
				(MantenimientoAPEUnidadesAdministrativasLineaService)this.getBean("spusicc.mantenimientoAPEUnidadesAdministrativasLineaService");
		MantenimientoAPEConfiguracionCentroDistribucionService serviceConf = 
				(MantenimientoAPEConfiguracionCentroDistribucionService)this.getBean("spusicc.mantenimientoAPEConfiguracionCentroDistribucionService");
		MantenimientoAPESubLineaArmadoService serviceSL = 
				(MantenimientoAPESubLineaArmadoService)this.getBean("spusicc.mantenimientoAPESubLineaArmadoService");
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codCDDefecto = "";
		int validaCD = 0;
		String codLineArmadoDefecto = "";
		String OidCentroDistribucion = "";
		Base base = null;
		
		
		//-- Crear pojo
		Map criteria=new HashMap();
		criteria.put("nombreTabla1", "APP_CONFI_CENTR_DISTR");
		
		Map criteriaPais = new HashMap();
		criteriaPais.put("codigoPais", pais.getCodigo());

		//-- Logica negocio -----------------------------------------
		String oidPais=reporteService.getOidString("getOidPaisByCodigoPais", criteriaPais);
		criteria.put("oidPais", oidPais);
		this.listaCD=(ArrayList)service.getCodigoCentroDistribucionList(criteria);
		validaCD = serviceConf.getExisteCentroDefault(criteria);
		codCDDefecto = service.getCodigoCDDefecto(criteria);
		criteria.put("codCentro",codCDDefecto);
		OidCentroDistribucion = service.getOidCentroDistribucionPais(criteria);
		criteria.put("oidCentro", OidCentroDistribucion);
		criteria.put("nombreTabla2",Constants.TABLA_LINEA_ARMADO);
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		
		// this.listaLineaArmado = aSvc.getLineaArmadoListar(pais.getCodigo(), valor);(ArrayList)serviceSL.getLineaArmadoComboList(criteria);
		codLineArmadoDefecto = serviceSL.getCodLineaArmadaDefectoList(criteria);
		
		this.listaNivelAgrpOlas = service.getObtenerNivelOlas(criteria);
		
		if (listaNivelAgrpOlas.size()>0){
			base = (Base)listaNivelAgrpOlas.get(0);
		}else{
			base = new Base();
			base.setCodigo("2");
			base.setDescripcion("Region");
		}
		
		this.listaRegion = reporteService.getListaGenerico("getRegionesByPais", criteriaPais);
		//-- Peticiones
		f.setCodigoPais(pais.getCodigo());
		f.setCodigoCD(codCDDefecto);
		f.setCodigoLineaArmado(codLineArmadoDefecto);
		f.setCodigoNivelAgrpOlas(base.getCodigo());
		f.setDescripcionNivelAgrpOlas(base.getDescripcion());
		
		if(f.getCodigoNivelAgrpOlas().equals("1")){
			flgRegion = true;
			flgZona	= true;
	  	  	flgSeccion= true;
	  	 }
		else if(f.getCodigoNivelAgrpOlas().equals("2")){
			flgRegion = true;
			flgZona	= false;
	  	  	flgSeccion= false;
	  	 }
		else if(f.getCodigoNivelAgrpOlas().equals("3")){
			flgRegion = true;
			flgZona	= true;
	  	  	flgSeccion= false;
	  	 }else{
	  		flgRegion = false;
			flgZona	= false;
	  	  	flgSeccion= false; 
	  	 }
		
		
		String[] arrRegion = new String[1];
		arrRegion[0] = ""; 
		//f.setCodigoRegion(arrRegion);
		
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoAPEUnidadesAdministrativasLineaSearchForm f = (MantenimientoAPEUnidadesAdministrativasLineaSearchForm)this.formBusqueda;
		MantenimientoAPEUnidadesAdministrativasLineaService service = 
				(MantenimientoAPEUnidadesAdministrativasLineaService)this.getBean("spusicc.mantenimientoAPEUnidadesAdministrativasLineaService");
		MantenimientoAPEConfiguracionCentroDistribucionService serviceConf = 
				(MantenimientoAPEConfiguracionCentroDistribucionService)this.getBean("spusicc.mantenimientoAPEConfiguracionCentroDistribucionService");
		MantenimientoAPESubLineaArmadoService serviceSL = 
				(MantenimientoAPESubLineaArmadoService)this.getBean("spusicc.mantenimientoAPESubLineaArmadoService");
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codCDDefecto = "";
		String OidCentroDistribucion = "";
		int validaCD = 0;
		
		//-- Crear pojo
		Map criteria=new HashMap();
		criteria.put("nombreTabla1", "APP_CONFI_CENTR_DISTR");
		
		Map criteriaPais = new HashMap();
		criteriaPais.put("codigoPais", pais.getCodigo());

		//-- Logica negocio -----------------------------------------
		String oidPais=reporteService.getOidString("getOidPaisByCodigoPais", criteriaPais);
		criteria.put("oidPais", oidPais);
		this.listaCD=(ArrayList)service.getCodigoCentroDistribucionList(criteria);
		validaCD = serviceConf.getExisteCentroDefault(criteria);
		codCDDefecto = service.getCodigoCDDefecto(criteria);
		criteria.put("codCentro", codCDDefecto);
		OidCentroDistribucion = service.getOidCentroDistribucionPais(criteria);
		criteria.put("oidCentro", OidCentroDistribucion);
		criteria.put("nombreTabla2",Constants.TABLA_LINEA_ARMADO);
		this.prueba = (ArrayList)serviceSL.getLineaArmadoComboList(criteria);
		String codLineArmadoDefecto = serviceSL.getCodLineaArmadaDefectoList(criteria);

		//-- Peticiones
		f.setCodigoPais(pais.getCodigo());
		f.setCodigoCD(codCDDefecto);
		f.setCodigoLineaArmado(codLineArmadoDefecto);
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() {
		String mensaje="";
		if(this.accion.equals(this.ACCION_NUEVO)){
			mensaje =  "mantenimientoAPEUnidadesAdministrativasLineaForm.insert"; 
		}
		
		return mensaje;
	}
	
	public void loadLineaArmado(ValueChangeEvent val){
		if (log.isDebugEnabled()) {
			log.debug("loadLineaArmado");
		}
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoAPEUnidadesAdministrativasLineaService service = 
				(MantenimientoAPEUnidadesAdministrativasLineaService)this.getBean("spusicc.mantenimientoAPEUnidadesAdministrativasLineaService");
		MantenimientoAPESubLineaArmadoService serviceSL = 
				(MantenimientoAPESubLineaArmadoService)this.getBean("spusicc.mantenimientoAPESubLineaArmadoService");
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		
		String valor = (String) val.getNewValue();
		
		this.listaLineaArmado = aSvc.getLineaArmadoListar(pais.getCodigo(), valor);
		
		Map criteria=new HashMap();
		criteria.put("nombreTabla1", "APP_CONFI_CENTR_DISTR");
		
		Map criteriaPais = new HashMap();
		criteriaPais.put("codigoPais", pais.getCodigo());
		
		String codCDDefecto = "";

		String codLineArmadoDefecto = "";
		String OidCentroDistribucion = "";
		//-- Logica negocio -----------------------------------------
		String oidPais=reporteService.getOidString("getOidPaisByCodigoPais", criteriaPais);
		criteria.put("oidPais", oidPais);
		this.listaCD=(ArrayList)service.getCodigoCentroDistribucionList(criteria);
		
		codCDDefecto = service.getCodigoCDDefecto(criteria);
		criteria.put("codCentro",codCDDefecto);
		OidCentroDistribucion = service.getOidCentroDistribucionPais(criteria);
		criteria.put("oidCentro", OidCentroDistribucion);
		
		// this.listaLineaArmado = aSvc.getLineaArmadoListar(pais.getCodigo(), valor);(ArrayList)serviceSL.getLineaArmadoComboList(criteria);
		codLineArmadoDefecto = serviceSL.getCodLineaArmadaDefectoList(criteria);
		
		this.listaNivelAgrpOlas = service.getObtenerNivelOlas(criteria);
		
	}
	
	public void loadZonas(ValueChangeEvent val){
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String valor = (String) val.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		
		String[] values=new String[1];     	
    	values[0]=valor;
		this.listZonas = ajax.getZonasMultipleByPaisMarcaCanalRegion( pais.getCodigo(), "T", "VD", values, "N");
	}
	
	public void loadSeccion(ValueChangeEvent val){
		if (log.isDebugEnabled()) {
			log.debug("loadSeccion");
		}
		MantenimientoAPEUnidadesAdministrativasLineaForm objForm = (MantenimientoAPEUnidadesAdministrativasLineaForm)this.formMantenimiento;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String[] valor = {(String) val.getNewValue()};
		String[] region = {(String) val.getNewValue()};
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		this.listaSeccion = ajax.getSeccionMultipleByPaisMarcaCanalRegionZona( pais.getCodigo(),  "T", "VD", region,valor, "");
		
	}
	//ajax.getSeccionMultipleByPaisMarcaCanalRegionZona( pais.value, 'T', 'VD', valuesRegion,valuesZonas,'', loadSeccionCallback);
	  
}
