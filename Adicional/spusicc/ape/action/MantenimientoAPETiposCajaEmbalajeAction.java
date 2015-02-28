package biz.belcorp.ssicc.web.spusicc.ape.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessages;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.ape.model.TiposCajaEmbalaje;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.IdiomaService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEAsignarCajaEmbalajeLineaService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEConfiguracionCentroDistribucionService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEFactoresConversionService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPESubLineaArmadoService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPETiposCajaEmbalajeService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.ape.form.MantenimientoAPETiposCajaEmbalajeForm;
import biz.belcorp.ssicc.web.spusicc.ape.form.MantenimientoAPETiposCajaEmbalajeSearchForm;
import biz.belcorp.ssicc.web.spusicc.dto.form.MantenimientoDTODescuentoAdicionalDetalleForm;

@ManagedBean
@SessionScoped
public class MantenimientoAPETiposCajaEmbalajeAction extends BaseMantenimientoSearchAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2641891370672713438L;

	
	int valorPantalla = 0;
	List listTipoCaja;
	List listUnidades;
	List listUnidadesExt;
	List listaTipoCajaEmbalaje;
	boolean valueTipoCaja=true;
	
	
	/**
	 * @return the valueTipoCaja
	 */
	public boolean isValueTipoCaja() {
		return valueTipoCaja;
	}

	/**
	 * @param valueTipoCaja the valueTipoCaja to set
	 */
	public void setValueTipoCaja(boolean valueTipoCaja) {
		this.valueTipoCaja = valueTipoCaja;
	}

	/**
	 * @return the listTipoCaja
	 */
	public List getListTipoCaja() {
		return listTipoCaja;
	}

	/**
	 * @param listTipoCaja the listTipoCaja to set
	 */
	public void setListTipoCaja(List listTipoCaja) {
		this.listTipoCaja = listTipoCaja;
	}

	/**
	 * @return the listUnidades
	 */
	public List getListUnidades() {
		return listUnidades;
	}

	/**
	 * @param listUnidades the listUnidades to set
	 */
	public void setListUnidades(List listUnidades) {
		this.listUnidades = listUnidades;
	}

	/**
	 * @return the listUnidadesExt
	 */
	public List getListUnidadesExt() {
		return listUnidadesExt;
	}

	/**
	 * @param listUnidadesExt the listUnidadesExt to set
	 */
	public void setListUnidadesExt(List listUnidadesExt) {
		this.listUnidadesExt = listUnidadesExt;
	}

	/**
	 * @return the listaTipoCajaEmbalaje
	 */
	public List getListaTipoCajaEmbalaje() {
		return listaTipoCajaEmbalaje;
	}

	/**
	 * @param listaTipoCajaEmbalaje the listaTipoCajaEmbalaje to set
	 */
	public void setListaTipoCajaEmbalaje(List listaTipoCajaEmbalaje) {
		this.listaTipoCajaEmbalaje = listaTipoCajaEmbalaje;
	}

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoAPETiposCajaEmbalajeSearchForm";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoAPETiposCajaEmbalajeForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoAPETiposCajaEmbalajeSearchForm objForm = new MantenimientoAPETiposCajaEmbalajeSearchForm();
		return objForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Entering 'find' method");
		
		//--  Variables
		MantenimientoAPETiposCajaEmbalajeSearchForm f = (MantenimientoAPETiposCajaEmbalajeSearchForm)this.formBusqueda;
		MantenimientoAPETiposCajaEmbalajeService serviceTCEM = 
			(MantenimientoAPETiposCajaEmbalajeService)this.getBean("spusicc.mantenimientoAPETiposCajaEmbalajeService");
		MantenimientoAPEAsignarCajaEmbalajeLineaService serviceTipoCaja = 
			(MantenimientoAPEAsignarCajaEmbalajeLineaService)getBean("spusicc.mantenimientoAPEAsignarCajaEmbalajeLineaService");
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		//-- Crear Pojo
		Map criteria = new HashMap();
		
		//-- Lógica de Negocio
		criteria.put("codigoPais", pais.getCodigo());
		String oidPais=reporteService.getOidString("getOidPaisByCodigoPais", criteria);
		criteria.put("oidPais", oidPais);
		criteria.put("codigoPais", f.getCodigoPais());
		
		if (StringUtils.isNotBlank(f.getCodigoTipoCaja())) {
			criteria.put("codTipoCajaEmbalaje",f.getCodigoTipoCaja());
			String oidTipoCaja = serviceTipoCaja.getOidTipoCajaEmbalbyCodigo(criteria);
			criteria.put("oidTipoCaja",oidTipoCaja);
		}else{
			criteria.put("oidTipoCaja",null);
		}
		
		criteria.put("descripcionTipoCaja",f.getDescripcionTipoCaja());
		
		List resultado = (List)serviceTCEM.getTipoCajaEmbalajeList(criteria);
		f.setCodigoTipoCaja(f.getCodigoTipoCaja());
		f.setDescripcionTipoCaja(f.getDescripcionTipoCaja());
		
		return resultado;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Entering 'delete' method");
		
		// Variables
		Map map = (HashMap)this.beanRegistroSeleccionado;
		MantenimientoAPETiposCajaEmbalajeForm f = new MantenimientoAPETiposCajaEmbalajeForm();
		BeanUtils.copyProperties(f, map);
		MantenimientoAPETiposCajaEmbalajeService serviceTCEM = 
			(MantenimientoAPETiposCajaEmbalajeService)this.getBean("spusicc.mantenimientoAPETiposCajaEmbalajeService");
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ActionMessages messages = new ActionMessages();
		
		// Crear Pojo
		String[] items = {f.getCodigoTipoCajaEmbalaje()};
		Map criteria = new HashMap();
		
		//-- Lógica de Negocio
		Idioma oidIdiomaIso = this.mPantallaPrincipalBean.getCurrentIdioma();
		
		String nombTablaHija = serviceTCEM.deleteTiposCajaEmbalaje(criteria,items,oidIdiomaIso.getCodigoISO());
		
		if(Constants.NUMERO_UNO.equals(nombTablaHija)){
			//entro
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Entering 'save' method");
		//--  Variables
		MantenimientoAPETiposCajaEmbalajeForm f =(MantenimientoAPETiposCajaEmbalajeForm)this.formMantenimiento;
		MantenimientoAPETiposCajaEmbalajeService serviceTCEM = 
			(MantenimientoAPETiposCajaEmbalajeService)this.getBean("spusicc.mantenimientoAPETiposCajaEmbalajeService");
		MantenimientoAPEAsignarCajaEmbalajeLineaService serviceTipoCaja = 
			(MantenimientoAPEAsignarCajaEmbalajeLineaService)getBean("spusicc.mantenimientoAPEAsignarCajaEmbalajeLineaService");
		MantenimientoAPEFactoresConversionService serviceFC = 
			(MantenimientoAPEFactoresConversionService)this.getBean("spusicc.mantenimientoAPEFactoresConversionService");
		MantenimientoAPESubLineaArmadoService serviceSL = 
			(MantenimientoAPESubLineaArmadoService)this.getBean("spusicc.mantenimientoAPESubLineaArmadoService");
		MantenimientoAPEConfiguracionCentroDistribucionService service2 = 
			(MantenimientoAPEConfiguracionCentroDistribucionService)getBean("spusicc.mantenimientoAPEConfiguracionCentroDistribucionService");
		ReporteService reporteService = 
				(ReporteService) this.getBean("scsicc.reporteService");
		ActionMessages messages = new ActionMessages();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		int nextOidTipoCaja = 0;
		
		// Crear Pojo
		Map criteria = new HashMap();
		Map params = new HashMap();
		
		// -- Lógica de Negocio
		criteria.put("codigoPais", pais.getCodigo());
		String oidPais=reporteService.getOidString("getOidPaisByCodigoPais", criteria);
		criteria.put("oidPais", oidPais);
		params.put("oidPais", oidPais);
		
		criteria.put("codigoTipoCaja",f.getCodigoTipoCajaEmbalaje());
		criteria.put("descripcionTipoCaja",f.getDescripcionTipoCaja());
		criteria.put("capacidad",f.getCapacidad());
		criteria.put("capacidadMinima",f.getCapacidadMinima());

		// Se Valida que la capacidad Mínima no sea mayor que la capacidad de la caja
		if (Double.parseDouble(f.getCapacidadMinima())>Double.parseDouble(f.getCapacidad())){
			this.addError("Error: ", this.getResourceMessage("mantenimientoAPETiposCajaEmbalajeForm.msg.capacidadMinima"));
			valorPantalla = 1;
			return false;
		}
		
		if (Constants.APE_TC_INDICADORCUBICAJE_UNO.equals(f.getIndicadorCubicaje())){
			criteria.put("indicadorCubicaje",Constants.APE_TC_INDICADORCUBICAJE_UNO);	
		}else{
			criteria.put("indicadorCubicaje",Constants.APE_TC_INDICADORCUBICAJE_CERO);
		}
		
		// Se Valida que el porcentaje de seguridad no sea mayor que 100
		if (Double.parseDouble(f.getPorcentajeSeguridad())>=100.0){
			this.addError("Error: ", this.getResourceMessage("mantenimientoAPETiposCajaEmbalajeForm.msg.porcentajeSeguridad"));
			valorPantalla = 1;
			return false;
		}
		
		criteria.put("porcentajeSeguridad", f.getPorcentajeSeguridad());
		
		criteria.put("nivelAplicacion", f.getNivelAplicacion());
		
		criteria.put("numEtiquetasCaja", f.getNumEtiquetasCaja());
		
		criteria.put("alto", f.getAlto());
		
		criteria.put("largo", f.getLargo());
		
		criteria.put("ancho", f.getAncho());

		criteria.put("codUnidMedi", f.getCodigoUnidMedidaCapa());
		String oIdUnidadMedCapa = serviceFC.getObtenerOidUnidadMedida(criteria);
		criteria.put("oidUnidMedidaCapa",oIdUnidadMedCapa);
		
		criteria.remove("codUnidMedi");
		criteria.put("codUnidMedi", f.getCodigoUnidMedidaExte());
		String oIdUnidadMedExte = serviceFC.getObtenerOidUnidadMedida(criteria);
		criteria.put("oidUnidMedidaExte",oIdUnidadMedExte);
		
		if (Constants.APE_TC_INDICADORCAJAMAES_UNO.equals(f.getIndicadorCajaMaestra())){
			criteria.put("indicadorCajaMaestra",Constants.APE_TC_INDICADORCAJAMAES_UNO);
			params.put("codigoTipoCaja",f.getCodigoTipoCajaProducto());
			String oidTipoCajaProd = serviceSL.getOidTipoCajaProducto(params);
			criteria.put("oidTipoCajaProd",oidTipoCajaProd);
		}else{
			criteria.put("indicadorCajaMaestra",Constants.APE_TC_INDICADORCAJAMAES_CERO);
			criteria.put("oidTipoCajaProd",null);
		}
		
		if (!this.accion.equals(this.ACCION_NUEVO)){
			log.debug("Modificación de Tipos de Caja Embalaje");
			
			criteria.put("oidTipoCajaEmb",f.getOidTipoCaja());
			
			// Actualizamos en la tabla de idiomas
			params = cargaMapIdioma(f);
			params.put("valOidAtributo",f.getOidTipoCaja());
			service2.updateIdiomasAPE(params);

			serviceTCEM.updateTipoCajaEmbalaje(criteria);
			valorPantalla = 0;
		}else{
			log.debug("Inserción de Tipos de Caja Embalaje");
			
			// Obtenemos el Next Oid Tipo Caja Embalaje
			nextOidTipoCaja = Integer.parseInt(serviceTCEM.getNextOidTipoCajaEmbalaje(criteria));
			criteria.put("oidTipoCajaEmb",nextOidTipoCaja);
			serviceTCEM.insertarTipoCajaEmbalaje(criteria);
			
			//Insertamos en la tabla de idiomas
			criteria = cargaMapIdioma(f);
			criteria.put("valOidAtributo", nextOidTipoCaja);
			service2.insertIdiomasAPE(criteria);
			
			valorPantalla = 0;
		}
		
		f.setNumEtiquetasCaja("1");
		f.setFlagEdicion("F");
		f.setCodigoUnidMedidaCapa("");
		f.setCodigoUnidMedidaExte("");
		f.setOidTipoCaja("");
		f.setCodigoTipoCajaProducto("");
		f.setDescripcionTipoCaja("");
		f.setFlagEdicion("");
		f.setCodigoTipoCajaEmbalaje("");
		f.setCapacidad("");
		f.setCapacidadMinima("");
		f.setIndicadorCubicaje("");
		f.setPorcentajeSeguridad("");
		f.setNivelAplicacion("");
		f.setNumEtiquetasCaja("");
		f.setCodigoTipoCajaProducto("");
		f.setAlto("");
		f.setAncho("");
		f.setLargo("");
		f.setIndicadorCajaMaestra("");
		
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		Map obj = (HashMap)this.beanRegistroSeleccionado;
		MantenimientoAPETiposCajaEmbalajeForm f = new MantenimientoAPETiposCajaEmbalajeForm();
		
		MantenimientoAPETiposCajaEmbalajeService serviceTCEM = 
				(MantenimientoAPETiposCajaEmbalajeService)this.getBean("spusicc.mantenimientoAPETiposCajaEmbalajeService");
		MantenimientoAPEFactoresConversionService serviceFC = 
			(MantenimientoAPEFactoresConversionService)this.getBean("spusicc.mantenimientoAPEFactoresConversionService");
		MantenimientoAPESubLineaArmadoService serviceSL = 
			(MantenimientoAPESubLineaArmadoService)this.getBean("spusicc.mantenimientoAPESubLineaArmadoService");
		ReporteService reporteService = 
			(ReporteService) this.getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		Map criteria=new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		String oidPais=reporteService.getOidString("getOidPaisByCodigoPais", criteria);
		criteria.put("oidPais", oidPais);
		
		loadCombosUnidades(serviceFC, criteria);
		loadComboTipoCajaProducto(serviceSL, criteria);
		f.setCodigoPais(pais.getCodigo());
		f.setNumEtiquetasCaja("1");
		f.setFlagEdicion("F");
		f.setCodigoUnidMedidaCapa("");
		f.setCodigoUnidMedidaExte("");
		f.setOidTipoCaja("");
		f.setCodigoTipoCajaProducto("");
		f.setDescripcionTipoCaja("");
		f.setFlagEdicion("");
		f.setCodigoTipoCajaEmbalaje("");
		f.setCapacidad("");
		f.setCapacidadMinima("");
		f.setIndicadorCubicaje("");
		f.setPorcentajeSeguridad("");
		f.setNivelAplicacion("");
		f.setNumEtiquetasCaja("1");
		f.setCodigoTipoCajaProducto("");
		f.setAlto("");
		f.setAncho("");
		f.setLargo("");
		f.setIndicadorCajaMaestra("");
		valueTipoCaja = false;
		if(!this.accion.equals(this.ACCION_CONSULTAR)){f.setActivo(false); this.valueTipoCaja = false;}
		
		if(!this.accion.equals(this.ACCION_NUEVO)){
			BeanUtils.copyProperties(f, obj);
			
			String id = f.getCodigoTipoCajaEmbalaje();
	
			if (log.isDebugEnabled()) {
				log.debug("Id seleccionado de la lista: " + id);
			}
			
			// Filtro de Tipo Caja Embalaje
			criteria.put("oidTipoCajaEmb",id);
			f.setOidTipoCaja(id);
			
			// --- Se obtiene los datos de Tipo Caja Embalaje
			TiposCajaEmbalaje bean = serviceTCEM.getTiposCajaEmbalajeObject(criteria);
			
			BeanUtils.copyProperties(f, bean);
		}
		f.setCodigoPais(pais.getCodigo());
		
		f.setFlagEdicion("T");
		
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoAPETiposCajaEmbalajeSearchForm f = (MantenimientoAPETiposCajaEmbalajeSearchForm)this.formBusqueda;
		MantenimientoAPETiposCajaEmbalajeService serviceTCEM = 
			(MantenimientoAPETiposCajaEmbalajeService)this.getBean("spusicc.mantenimientoAPETiposCajaEmbalajeService");
		MantenimientoAPEAsignarCajaEmbalajeLineaService serviceTipoCaja = 
			(MantenimientoAPEAsignarCajaEmbalajeLineaService)getBean("spusicc.mantenimientoAPEAsignarCajaEmbalajeLineaService");
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

		// Guardamos el oid de idioma en sesion
		Idioma idioma = this.mPantallaPrincipalBean.getCurrentIdioma();
		
		
		Map parameterMap = new HashMap();
		parameterMap.put("codigoIdiomaIso", idioma.getCodigoSiCC());
		parameterMap.put("codigoIdioma", idioma.getCodigoISO());
		String oidIdiomaIso = reporteService.getOidString("getOidIdiomaByCodigoIdiomaIso", parameterMap);
		
		//-- Logica negocio -----------------------------------------
		String oidPais=reporteService.getOidString("getOidPaisByCodigoPais", criteriaPais);
		criteria.put("oidPais", oidPais);
		criteria.put("nombreTablaTipoCajaEmbalaje", Constants.TABLA_TIPO_CAJA_EMBALAJE);
		criteria.put("oidIdiomaIso", oidIdiomaIso);

		this.listaTipoCajaEmbalaje=(ArrayList)serviceTipoCaja.getTipoCajaEmbalajeComboList(criteria);

		//-- Peticiones
		f.setCodigoPais(pais.getCodigo());
		
		f.setCodigoTipoCaja("");
		f.setDescripcionTipoCaja("");
		
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() {
	
		if(this.accion.equals(this.ACCION_NUEVO)){
			return "mantenimientoAPETiposCajaEmbalajeForm.insert";
		}else{
			return "mantenimientoAPETiposCajaEmbalajeForm.update";
		}	
	}
	
	/**
	 * Devuelve el Map
	 * @param session
	 * @param f
	 * @return
	 */
	private Map cargaMapIdioma(MantenimientoAPETiposCajaEmbalajeForm f) {
		
		MantenimientoAPEConfiguracionCentroDistribucionService service = 
			(MantenimientoAPEConfiguracionCentroDistribucionService)getBean("spusicc.mantenimientoAPEConfiguracionCentroDistribucionService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map map = new HashMap();
		
		map.put("nombreTabla", Constants.APE_TC_NOMBRETABLA);
		map.put("numAtributo", service.getNumAtributoIdioma(map));
		map.put("oidIdioma", pais.getCodigoIdiomaIso());
		map.put("valAtributo", f.getDescripcionTipoCaja());
		
		return map;
	}
	
	/**
	 * Metodo que carga los combos de Unidades de Medida
	 * @param session
	 * @param service
	 * @param criteria
	 */
	private void loadCombosUnidades(MantenimientoAPEFactoresConversionService service, Map criteria) {
		criteria.put("oidMagn","2002");
		this.listUnidades = service.getUnidadMedidaList(criteria);
		criteria.remove("oidMagn");
		criteria.put("oidMagn","2001");
		this.listUnidadesExt = service.getUnidadMedidaList(criteria);
		
	}
	
	/**
	 * Metodo que carga el combo de Tipo Caja Producto
	 * @param session
	 * @param service
	 * @param criteria
	 */
	private void loadComboTipoCajaProducto(MantenimientoAPESubLineaArmadoService service, Map criteria){
		this.listTipoCaja = service.getTipoCajaProductoList(criteria);
	}
	
	public void loadTipoCaja(ValueChangeEvent val){
		
		boolean valor = (Boolean) val.getNewValue();
		if(valor){valueTipoCaja = true;}else{valueTipoCaja = false;}
	}
	
}
