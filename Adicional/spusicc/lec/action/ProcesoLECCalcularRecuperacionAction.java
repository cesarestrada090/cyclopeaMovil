package biz.belcorp.ssicc.web.spusicc.lec.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.edu.model.ParametroCursoCapacitacion;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUComercialService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.gen.ProcesoGENProcesarCierreService;
import biz.belcorp.ssicc.service.spusicc.lec.MantenimientoLECProgramaCorporativoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBloqueoControlService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spusicc.lec.form.ProcesoLECCalcularRecuperacionForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked", "deprecation"})
public class ProcesoLECCalcularRecuperacionAction extends BaseInterfazAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1415627464638554188L;
	
	private List lecGrupoRegionList;
	private LabelValue[] lecRegionList ={};
	private boolean opcionPeriodoFecha;
	private boolean opcionPeriodoFechaRecaudo;
	private String indTipoGrupoRegion;
	private boolean habilita;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception 
	{
		ProcesoLECCalcularRecuperacionForm formInterfaz = new ProcesoLECCalcularRecuperacionForm();
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		log.debug("Executin action : view.");
		ProcesoLECCalcularRecuperacionForm f = (ProcesoLECCalcularRecuperacionForm)this.formInterfaz;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		String codigoPais = pais.getCodigo();
		f.setCodigoPais(codigoPais);
		String conexionExterna = pais.getCodigoConexionExterna();
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
        criteria.put("estadoCampanha",Constants.NUMERO_CERO);
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO);
        criteria.put("codigoConexionExterna", conexionExterna);
        
        MantenimientoLECProgramaCorporativoService lecService = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");
       
        MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
		f.setPeriodoProceso(controlFacturacion.getCodigoPeriodo());
		f.setCodigoPeriodoActual(controlFacturacion.getCodigoPeriodo());
			      
        f.setCodigoConexionExterna(conexionExterna);
        
		String indicadorProceso = this.parametrosPantalla.get("indicadorProceso");
		f.setIndicadorProceso(indicadorProceso);
		/* INI JJ  PER-SiCC-2012-0361 */
		String indTipoValid = this.parametrosPantalla.get("indTipoValid");
		f.setIndTipoValid(indTipoValid);
		/* FIN JJ  PER-SiCC-2012-0361 */
		criteria.put("codigoSistema", "LEC");
		criteria.put("codigoInterfaz", f.getCodigoInterfaz());
						
		ProcesoGENProcesarCierreService procesoService = (ProcesoGENProcesarCierreService)getBean("spusicc.procesoGENProcesarCierreService");
		
		f.setIndicadorModEducacion(procesoService.getIndicadorModEducacion(codigoPais));
		
		if(f.getIndicadorModEducacion().equals(Constants.NUMERO_UNO)){		
				//cargarArchivoControlFacturacion(f,pais);
		}
		
		Map map = new HashMap();
		map.put("codigoPais", codigoPais);	
		Map map1 = lecService.getEncontrarProgramaLecCorporativo(f.getPeriodoProceso());
		
		f.setCodigoPrograma(map1.get("codigoPrograma").toString());
		f.setDescPrograma(map1.get("descPrograma").toString());
		
		String frecuenciaSGR = this.parametrosPantalla.get("frecuenciaSGR");
		f.setFrecuenciaSGR(frecuenciaSGR);		
		
		List list = lecService.getTipoGrupoRegion(map);
		this.lecGrupoRegionList = list;
		
		Map map2 = new HashMap();
		map2.put("codigoPais", codigoPais);
		map2.put("codigoTipoGroup", ((Base)list.get(0)).getCodigo());
		
		List listaRegiones = new ArrayList();
		listaRegiones = lecService.getRegionByTipoGrupoRegion(map2);
		this.lecRegionList = new LabelValue[listaRegiones.size()];
		
		int i = 0;
		for (Object object : listaRegiones) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base) object).getDescripcion());
			labelValue.setValue(((Base) object).getCodigo());
			this.getLecRegionList()[i] = labelValue;
			i++;
		}		
		
		String tipoGrupoRegion = getIndicadorGrupoRegion(pais);

		this.indTipoGrupoRegion = tipoGrupoRegion!= null? tipoGrupoRegion:Constants.NRO_CERO;
		
		f.setHabilitadorHidden(Constants.NUMERO_CERO);
		f.setHabilitadorRecaudoHidden("0");
		f.setHabilitadorPeriodo("0");
		f.setCodigoPeriodoRecaudo("");

		String habilitadorCampanna = this.getIndicadorCampannaProceso(pais);
		if (StringUtils.isNotBlank(habilitadorCampanna))
			f.setHabilitadorHidden(habilitadorCampanna);
		f.setHabilitadorPeriodo(habilitadorCampanna);
				    
		setOpcionPeriodoFecha(false);
		setOpcionPeriodoFechaRecaudo(true);
		setHabilita(true);
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		params = super.prepareParamsBeforeExecute(params, form); 
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		String codigoPais = pais.getCodigo();
		ProcesoLECCalcularRecuperacionForm f = (ProcesoLECCalcularRecuperacionForm) this.formInterfaz;
		params.put("codigoUsuario", usuario.getLogin());
		params.put("login", usuario.getLogin());
		params.put("codigoPais", pais.getCodigo());
		params.put("codigoConexionExterna", pais.getCodigoConexionExterna());

		String indicadorValMae = getIndicadorValMae(pais);
		params.put("indicadorValMae", indicadorValMae!= null? indicadorValMae:Constants.NRO_CERO);
		//if(Constants.NRO_UNO.equals(indicadorValMae)){
			params.put("periodoProceso", f.getPeriodoProceso());
		//	params.put("fechaProceso", f.getFechaProceso());
		//}
		/* INI JJ PER-SiCC-2012-0167 */
	   // params.put("fechaFacturacion", f.getFechaProceso());
	    /* FIN JJ PER-SiCC-2012-0167 */
		params.put("codigoPeriodo", f.getPeriodoProceso());
		params.put("periodo", f.getPeriodoProceso());
		params.put("codigoUsuario", usuario.getLogin());
		params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		/* INI JJ  PER-SiCC-2012-0361 */
		params.put("indTipoValid", f.getIndTipoValid());
		/* FIN JJ  PER-SiCC-2012-0361 */
		String servidor = getRequest().getServerName();
		params.put("nombreServidor", servidor);
		
		if (f.getIndicadorModEducacion().equals(Constants.NUMERO_UNO)) {

			MantenimientoEDUGenericoService mantenimientoEDUGenericoService = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
			MantenimientoEDUGenericoService siccService = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");

			EmpresaComercializadora empresaComercializadora = new EmpresaComercializadora();
			empresaComercializadora.setCodigoPais(codigoPais);
			empresaComercializadora.setEstadoRegistro(Constants.ESTADO_ACTIVO);

			params.put("codigoEmpresa", ((EmpresaComercializadora) mantenimientoEDUGenericoService.getEmpresasComercializadorasByPais(
									empresaComercializadora).get(0)).getCodigoEmpresa());

			ParametroCursoCapacitacion parametro = new ParametroCursoCapacitacion();
			parametro.setCodigoPais(codigoPais);
			parametro.setCodigoEmpresa((String) params.get("codigoEmpresa"));
			parametro.setCodigoPrograma(Constants.EDU_PARAMETROS_PROGRAMA_LBEL);
			parametro = siccService.getParametroCurso(parametro);
			params.put("indicadorEnvioCronograma", parametro.getIndicadorEnvioCronograma());
		}
		
		// proceso de Reactivacion de licencias

		/* obtiene ultima periodo Activo */
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente

		String conexionExterna = pais.getCodigoConexionExterna();
		criteria.put("codigoConexionExterna", conexionExterna);

		if (StringUtils.equals(conexionExterna,
				Constants.CONEXION_EXTERNA_ORACLE)) {
			MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
			String periodoActual = controlFacturacion.getCodigoPeriodo();

			params.put("periodoActivo", periodoActual);
		} else {
			InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
			Map datos = service.getPeriodoFechaCampanyaActivaSF(criteria);
			String[] result = new String[] {
					MapUtils.getString(datos, "periodo", ""),
					MapUtils.getString(datos, "fecha", "") };

			String periodoActual = result[0];
			params.put("periodoActivo", periodoActual);
		}

		params.put("frecuenciaSGR", f.getFrecuenciaSGR());
		
		String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
		params.put("rutaPath", path);
		
		params.put("usuarioTemp", usuario);
		params.put("tipoProceso", "D");
		params.put("codigoRegion", "");
		params.put("codigoGrupoRegion", StringUtils.isBlank(f.getGrupoRegion()) ? "" : f.getGrupoRegion());
		
		return params;
	}
	
	/**
	 * Obtiene indicador de Mostrar GrupoRegion
	 * @param pais
	 * @return indTipoGrupoRegion
	 */
	private String getIndicadorGrupoRegion(Pais pais) {
		Map criteriaParam = new HashMap();

		criteriaParam.put("codigoPais", pais.getCodigo());
		criteriaParam.put("codigoSistema", "LET");
		criteriaParam.put("nombreParametro", "indTipoGrupoRegion");
		return ((MantenimientoSTOBloqueoControlService)
				getBean("spusicc.mantenimientoSTOBloqueoControlService")).getParametroGenericoSistema(criteriaParam);
	}
	
	/**
	 * @param pais
	 * @return
	 */
	private String getIndicadorCampannaProceso(Pais pais) {
		Map criteriaParam = new HashMap();
		criteriaParam.put("codigoPais", pais.getCodigo());
		criteriaParam.put("codigoSistema", "LET");
		criteriaParam.put("nombreParametro", "INDCAMBIOCAMPANA");
		return ((MantenimientoSTOBloqueoControlService)
			getBean("spusicc.mantenimientoSTOBloqueoControlService")).getParametroGenericoSistema(criteriaParam);
	}
	
	/**
	 * Obtiene indicador de Validacion de Informacion MAE
	 * @param pais
	 * @return indicadorValMae
	 */
	private String getIndicadorValMae(Pais pais) {
		Map criteriaParam = new HashMap();
		criteriaParam.put("codigoPais", pais.getCodigo());
		criteriaParam.put("codigoSistema", "MAE");
		criteriaParam.put("nombreParametro", "indValidacionMAE");
		return ((MantenimientoSTOBloqueoControlService)
			getBean("spusicc.mantenimientoSTOBloqueoControlService")).getParametroGenericoSistema(criteriaParam);
	}
	
	
	/**
	 * @param f
	 * @param session
	 * @throws Exception
	 */
	private void cargarArchivoControlFacturacion(ProcesoLECCalcularRecuperacionForm f, Pais pais) throws Exception
	{
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		MantenimientoEDUGenericoService mantenimientoEDUGenericoService = (MantenimientoEDUGenericoService)getBean("edu.mantenimientoEDUGenericoService");
		
		EmpresaComercializadora empresaComercializadora = new EmpresaComercializadora();
		empresaComercializadora.setCodigoPais(pais.getCodigo());
		empresaComercializadora.setEstadoRegistro(Constants.ESTADO_ACTIVO);
		/* Cargando archivo de control de Facturacion */
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoEmpresa",((EmpresaComercializadora)mantenimientoEDUGenericoService.getEmpresasComercializadorasByPais(empresaComercializadora).get(0)).getCodigoEmpresa());
		criteria.put("usuario", usuario);
		criteria.put("noCopiarArchivos", Constants.SI);
		ProcesoEDUComercialService	procesoEDUComercialService = (ProcesoEDUComercialService) getBean("edu.procesoEDUComercialService");
		procesoEDUComercialService.executeProcesoEDUCargarControlFacturacion(f.getCodigoPais(), criteria);		
	}

	public void loadPeriodoFecha(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadPeriodoFecha");
		}
		String opcion = (String) val.getNewValue().toString();

		if (opcion == "true") {
			setOpcionPeriodoFecha(false);
		} else {
			setOpcionPeriodoFecha(true);
		}
	}
	
	public void loadPeriodoFechaRecaudo(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadPeriodoFechaRecaudo");
		}
		String opcion = (String) val.getNewValue().toString();

		if (opcion == "true") {
			setOpcionPeriodoFechaRecaudo(false);
		} else {
			setOpcionPeriodoFechaRecaudo(true);
		}
	}
	
	public void loadRegiones(ValueChangeEvent value)
	{
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		ProcesoLECCalcularRecuperacionForm f = (ProcesoLECCalcularRecuperacionForm)this.formInterfaz;
		String valor = value.getNewValue().toString();
		
		this.lecRegionList = ajax.getRegionByTipoGrupoRegion(f.getCodigoPais(), valor);
	}

	public List getLecGrupoRegionList() {
		return lecGrupoRegionList;
	}

	public void setLecGrupoRegionList(List lecGrupoRegionList) {
		this.lecGrupoRegionList = lecGrupoRegionList;
	}

	public LabelValue[] getLecRegionList() {
		return lecRegionList;
	}

	public void setLecRegionList(LabelValue[] lecRegionList) {
		this.lecRegionList = lecRegionList;
	}

	public boolean isOpcionPeriodoFecha() {
		return opcionPeriodoFecha;
	}

	public void setOpcionPeriodoFecha(boolean opcionPeriodoFecha) {
		this.opcionPeriodoFecha = opcionPeriodoFecha;
	}

	public boolean isOpcionPeriodoFechaRecaudo() {
		return opcionPeriodoFechaRecaudo;
	}

	public void setOpcionPeriodoFechaRecaudo(boolean opcionPeriodoFechaRecaudo) {
		this.opcionPeriodoFechaRecaudo = opcionPeriodoFechaRecaudo;
	}

	public String getIndTipoGrupoRegion() {
		return indTipoGrupoRegion;
	}

	public void setIndTipoGrupoRegion(String indTipoGrupoRegion) {
		this.indTipoGrupoRegion = indTipoGrupoRegion;
	}

	public boolean isHabilita() {
		return habilita;
	}

	public void setHabilita(boolean habilita) {
		this.habilita = habilita;
	}
}
