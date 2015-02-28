package biz.belcorp.ssicc.web.spusicc.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.NroLoteMultiHilo;
import biz.belcorp.ssicc.dao.sisicc.model.ProcesoBatchActu;
import biz.belcorp.ssicc.dao.sisicc.model.ProcesoBatchActuMultiHilo;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.DatosComisiones;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.scsicc.ProcesoBatchService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMComisionService;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCalculoCalificacionTramoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.form.ProcesoCOMComisionRecuperacionForm;


// TODO: Auto-generated Javadoc
/**
 * The Class ProcesoCOMComisionRecuperacionAction.
 *
 * @author Belcorp
 * @version 1.0
 * 27/11/2014
 */
@ManagedBean  
@SessionScoped
public class ProcesoCOMComisionRecuperacionAction extends BaseProcesoAbstractAction {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The sicc marca list. */
	private List siccMarcaList = new ArrayList();
	
	/** The sicc canal list. */
	private List siccCanalList = new ArrayList();
	
	/** The sicc comision list. */
	private LabelValue[] siccComisionList = {};
	
	/** The com tramo ejecutivas list. */
	private List comTramoEjecutivasList = new ArrayList();
	
	/** The com tipo calulo list. */
	private List comTipoCaluloList = new ArrayList();
	
	/** The sicc tipo comision recuperacion list. */
	private List siccTipoComisionRecuperacionList = new ArrayList();
	
	/** The com gerente retiradas list. */
	private List comGerenteRetiradasList = new ArrayList();
	
	/** The mostrar tramos. */
	private boolean mostrarTramos;
	
	private boolean mostrarTipoCalculo;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoHiloAbstractAction#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {		
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}		
		this.valoresPorDefault();
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ProcesoCOMComisionRecuperacionForm form = (ProcesoCOMComisionRecuperacionForm) this.formProceso;
		ProcesoCOMCalculoCalificacionTramoService tramoService = (ProcesoCOMCalculoCalificacionTramoService) getBean("spusicc.procesoCOMCalculoCalificacionTramoService");
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		
		this.siccMarcaList = service.getMarcas();
		this.siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.comTramoEjecutivasList = tramoService.getTramos(pais.getCodigo());
		this.comTipoCaluloList = tramoService.getTipoCalculoList(pais.getCodigo());
		AjaxService ajax=(AjaxService)getBean("ajaxService");
		this.siccComisionList=ajax.getComisionesByTipo(Constants.CODIGO_TIPO_COMISIONISTA_DEFAULT);
		this.mostrarTipoCalculo=false;
		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(pais.getCodigo(),Constants.CODIGO_CANAL_DEFAULT);
		
		form.setCodigoPeriodo(codigoPeriodo);
		form.setMostrarListaBaseComision07(false);
		form.setCodigoComisionBase07(null);
		form.setCodigoPeriodoBase07(null);
		form.setTipoComisionBase07(null);
		
		form.setCodigoGerenteBase07("");
		form.setCodigoRegionBase07("");
		form.setCodigoZonaBase07("");
		form.setFechaRetiroBase07("");
		
		
		
		this.siccTipoComisionRecuperacionList = tramoService.getTiposComisionistas(pais.getCodigo());
		
		log.debug("Todo Ok: Redireccionando");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoHiloAbstractAction#devuelveFormProceso()
	 */
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoCOMComisionRecuperacionForm form = new ProcesoCOMComisionRecuperacionForm();
		return form;
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#executeProceso()
	 */
	@Override
	public void executeProceso()  {
		if(log.isDebugEnabled()){
			log.debug("executeProceso");
		}
		this.enEjecucion = true;
		ProcesoCOMComisionRecuperacionForm form = (ProcesoCOMComisionRecuperacionForm)this.formProceso;
		//params = prepareParamsBeforeExecute();
		String codigoBaseComision = form.getCodigoBaseComision();
		form.setMostrarListaBaseComision07(false);
		if (StringUtils.equals(codigoBaseComision, Constants.COM_CODIGO_BASE_COMISION_07)) {
			Map criteria = new HashMap();
			criteria.put("codigoPais", form.getCodigoPais());
			criteria.put("codigoPeriodo", form.getCodigoPeriodo());
			criteria.put("tipoComision", form.getTipoComision());
			criteria.put("codigoComision", form.getCodigoComision());
			if (StringUtils.equals( form.getTipoComision(), Constants.COM_COMISION_TIPO_COMISION_GERENTE_ZONA))
				criteria.put("codigoTipoCargo", Constants.COM_CODIGO_TIPO_CARGO_GZ);
			else
				criteria.put("codigoTipoCargo", Constants.COM_CODIGO_TIPO_CARGO_GR);
			
			MantenimientoCOMComisionService mantenimientoCOMComisionService = (MantenimientoCOMComisionService) getBean("spusicc.mantenimientoCOMComisionService");
			List listaGerenteRetiradas = mantenimientoCOMComisionService.getListaMostrarGerentesRetiradas(criteria);
			
			form.setCodigoPeriodoBase07(form.getCodigoPeriodo());
			form.setCodigoComisionBase07(form.getCodigoComision());
			form.setTipoComisionBase07(form.getTipoComision());
			form.setMostrarListaBaseComision07(true);
			
			this.comGerenteRetiradasList = listaGerenteRetiradas;
		}
		
		super.executeProceso();
		log.debug("fin de execute proceso");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoHiloAbstractAction#executeProcess(java.util.Map)
	 */
	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("executeProcess");
		}
		ProcesoCOMComisionRecuperacionForm form = (ProcesoCOMComisionRecuperacionForm) this.formProceso;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		MantenimientoCOMComisionService service = (MantenimientoCOMComisionService) getBean("spusicc.mantenimientoCOMComisionService");
		DatosComisiones comision = service.getDatosComisionRecuperacion(params);
		
		params.put("oidComision", String.valueOf(comision.getOidComision()));
		
		
		GenericoService genericoService = (GenericoService) getBean("genericoService");
		ParametroPais parametroPais = new ParametroPais();
		
		parametroPais.setCodigoPais(form.getCodigoPais());
		parametroPais.setCodigoSistema(Constants.COM_CODIGO_SISTEMA);
		parametroPais.setCodigoParametro(Constants.COM_CODIGO_PARAMETRO_TIPO_PROCESO_COMISION);
		parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
		Usuario usuario = (Usuario) params.get("usuario");
		params.put("login", usuario.getLogin());
		
		List lstParametros = genericoService.getParametrosPais(parametroPais);
		
		ParametroPais parametro = null;
		if(CollectionUtils.size(lstParametros)==1){
			parametro = (ParametroPais) lstParametros.get(0);
		}
		
		if(comision.getCodigoBaseComision().equals(Constants.CODIGO_BASE_COMISION_07)) {
			
			List listaGerenteRetiradas = form.getListaGerenteRetiradas();
			String id[] = form.getGerenteRetiradasSelectedItems();
			for (int i=0; i < id.length; i++) {
				String idValor = id[i];
				Map criteria = (Map) listaGerenteRetiradas.get(new Integer(idValor));
				String codigoRegion = (String) criteria.get("codigoRegion");
				String codigoZona = (String) criteria.get("codigoZona");
				String codigoCliente = (String) criteria.get("codigoCliente");
				String fechaRegistro = (String) criteria.get("fechaRegistro");
				params.put("codigoRegionBase07", codigoRegion);
				params.put("codigoZonaBase07", codigoZona);
				params.put("codigoGerenteBase07", codigoCliente);
				params.put("fechaRetiroBase07", fechaRegistro);
				
				reporteService.executeComisionRecuperacion(params);
			}
			return params;
		}
		
		if(comision.getCodigoBaseComision().equals(Constants.CODIGO_BASE_COMISION_01) && StringUtils.equals(parametro.getValorParametro(), "2")){
			log.debug("Los parametros del executeCalcularComisionVentaNetaEfectiva son: " + params.toString());			
			reporteService.executeCalcularComisionVentaNetaEfectiva(params);
		}		
		else if((comision.getCodigoBaseComision().equals(Constants.CODIGO_BASE_COMISION_01) || 
				 comision.getCodigoBaseComision().equals(Constants.CODIGO_BASE_COMISION_07) || 
				 comision.getCodigoBaseComision().equals(Constants.CODIGO_BASE_COMISION_02) || comision.getCodigoBaseComision().equals(Constants.CODIGO_BASE_COMISION_04))  && StringUtils.equals(parametro.getValorParametro(), "1")){
			log.debug("Los parametros del executeProcess son: "	+ params.toString());
			reporteService.executeComisionRecuperacion(params);
		}		
		else if(comision.getCodigoBaseComision().equals(Constants.CODIGO_BASE_COMISION_03) ){
			log.debug("Los parametros del executeComisionesCalculadasPorRegion son: "	+ params.toString());
			reporteService.executeComisionesCalculadasPorRegion(params);	
		}
		else if(comision.getCodigoBaseComision().equals(Constants.CODIGO_BASE_COMISION_06) ){
			log.debug("Los parametros del executeComisionesVentaRetail son: "	+ params.toString());
			reporteService.executeComisionesVentaRetail(params);	
		}
		
		return params;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#prepareParamsBeforeExecute()
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params) throws Exception {
		params = super.prepareParamsBeforeExecute(params);
		ProcesoCOMComisionRecuperacionForm form = (ProcesoCOMComisionRecuperacionForm) this.formProceso;
		String tipoComision = form.getTipoComision();
		
		params.put("tipoComision", tipoComision);
		params.put("codigoComision", form.getCodigoComision());
		String codTipoCalculo = form.getCodTipoCalculo();
		
		if(StringUtils.isBlank(codTipoCalculo)){
			params.put("codTipoCalculo", "01_PARAM_TODAS");
		}
		
		params.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		params.put("codigoPeriodo", form.getCodigoPeriodo());
		
		return params;
	}
	
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes");
		}
		Map criteria = BeanUtils.describe(this.formProceso);
		ProcesoBatchService service = (ProcesoBatchService) getBean("scsicc.procesoBatchService");
		HistoricoService historicoService = (HistoricoService) getBean("sisicc.historicoService");
		
		List resultado = service.getProcesoBatchActuByCriteria(criteria);
		List resultadoMultiHilo = new ArrayList();
		Map mapaNroLote = new HashMap();
		
		for(int i=0; i < resultado.size(); i++) {
			ProcesoBatchActu procesoBatchActu = (ProcesoBatchActu)resultado.get(i);
			String log = procesoBatchActu.getLog();
			log = StringEscapeUtils.escapeJavaScript(log);
			procesoBatchActu.setLog(log);
			
			ProcesoBatchActuMultiHilo registroMultiHilo = new ProcesoBatchActuMultiHilo();
			registroMultiHilo.setProcesoBatchActu(procesoBatchActu);
			mapaNroLote.put("codigoPais", procesoBatchActu.getCodigoPais());
			mapaNroLote.put("codigoSistema", procesoBatchActu.getCodigoSistema());
			mapaNroLote.put("idProcesoBatch", procesoBatchActu.getIdProcesoBatch());
			
			List listaNroLoteMultiHilo = service.getObtieneNroLoteProcesoBatchActu(mapaNroLote);
			for(int j=0; j < listaNroLoteMultiHilo.size(); j++) {
				NroLoteMultiHilo registroNroLoteMultiHilo = (NroLoteMultiHilo)listaNroLoteMultiHilo.get(j);
				mapaNroLote.put("numeroLote", registroNroLoteMultiHilo.getNumeroLote());
				List listaHistorico = historicoService.getHistoricosLotesMultiHilo(mapaNroLote);
				registroNroLoteMultiHilo.setListaHistorico(listaHistorico);
				listaNroLoteMultiHilo.set(j, registroNroLoteMultiHilo);
			}
			
			registroMultiHilo.setListaNroLoteMultiHilo(listaNroLoteMultiHilo);
			resultadoMultiHilo.add(registroMultiHilo);
		}		
		
		if (resultado == null) {
			this.addError(this.getResourceMessage("errors.datos.fuentes.busqueda"), null);
		}
		return resultadoMultiHilo; 
	}
	
	/**
	 * Valores por default.
	 */
	protected void valoresPorDefault(){
		if(log.isDebugEnabled()){
			log.debug("valoresPorDefault");
		}
		ProcesoCOMComisionRecuperacionForm form = (ProcesoCOMComisionRecuperacionForm) this.formProceso;
		form.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		form.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		form.setAnioInicTramo(new SimpleDateFormat("yyyy").format(new Date(System.currentTimeMillis())));
	}
	
	/**
	 * Load comision by tipo.
	 *
	 * @param val the val
	 */
	public void loadComisionByTipo(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("loadComisionByTipo");
		}
		String valor = (String) val.getNewValue();
		if(StringUtils.isNotBlank(valor) || StringUtils.isNotEmpty(valor)){
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			this.siccComisionList = ajaxService.getComisionesByTipo(valor);
		}
		ocultarTipoCalculo(valor);
	}
	
	public void ocultarTipoCalculo(String valor){
		ProcesoCOMComisionRecuperacionForm form = (ProcesoCOMComisionRecuperacionForm) this.formProceso;
		if(valor.equals("03")){
			this.mostrarTipoCalculo=false;
		 }else{
			this.mostrarTipoCalculo=true;
			this.mostrarTramos = false;
			form.setCodTipoCalculo("0");
			//form.setCodTipoCalculo(this.comTipoCaluloList.get(0).toString());

		 }
	}
	
	/**
	 * Obtener codigo base.
	 *
	 * @param val the val
	 */
	public void obtenerCodigoBase(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("obtenerCodigoBase");
		}
		String valor = (String) val.getNewValue();
		if(StringUtils.isNotBlank(valor) || StringUtils.isNotEmpty(valor)){
			ProcesoCOMComisionRecuperacionForm form = (ProcesoCOMComisionRecuperacionForm) this.formProceso;
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			String codigoBaseComision = ajaxService.getCodigoBaseComision(valor); 
			form.setCodigoBaseComision(codigoBaseComision);
		}		
	}
	
	/**
	 * Do tipo calculo.
	 *
	 * @param val the val
	 */
	public void doTipoCalculo(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("doTipoCalculo");
		}
		String valor = (String) val.getNewValue();
		if(StringUtils.isNotBlank(valor) || StringUtils.isNotEmpty(valor)){
			if(StringUtils.equals(valor, "01_PARAM_TODAS")){
				this.mostrarTramos = false;
			}else{
				this.mostrarTramos = true;
			}
		}
	}
	
	
	
	
	/* GET - SET */

	/**
	 * Gets the sicc marca list.
	 *
	 * @return the sicc marca list
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * Sets the sicc marca list.
	 *
	 * @param siccMarcaList the new sicc marca list
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * Gets the sicc canal list.
	 *
	 * @return the sicc canal list
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * Sets the sicc canal list.
	 *
	 * @param siccCanalList the new sicc canal list
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}


	/**
	 * Gets the com tramo ejecutivas list.
	 *
	 * @return the com tramo ejecutivas list
	 */
	public List getComTramoEjecutivasList() {
		return comTramoEjecutivasList;
	}

	/**
	 * Sets the com tramo ejecutivas list.
	 *
	 * @param comTramoEjecutivasList the new com tramo ejecutivas list
	 */
	public void setComTramoEjecutivasList(List comTramoEjecutivasList) {
		this.comTramoEjecutivasList = comTramoEjecutivasList;
	}

	/**
	 * Gets the com tipo calulo list.
	 *
	 * @return the com tipo calulo list
	 */
	public List getComTipoCaluloList() {
		return comTipoCaluloList;
	}

	/**
	 * Sets the com tipo calulo list.
	 *
	 * @param comTipoCaluloList the new com tipo calulo list
	 */
	public void setComTipoCaluloList(List comTipoCaluloList) {
		this.comTipoCaluloList = comTipoCaluloList;
	}

	/**
	 * Gets the sicc tipo comision recuperacion list.
	 *
	 * @return the sicc tipo comision recuperacion list
	 */
	public List getSiccTipoComisionRecuperacionList() {
		return siccTipoComisionRecuperacionList;
	}

	/**
	 * Sets the sicc tipo comision recuperacion list.
	 *
	 * @param siccTipoComisionRecuperacionList the new sicc tipo comision recuperacion list
	 */
	public void setSiccTipoComisionRecuperacionList(
			List siccTipoComisionRecuperacionList) {
		this.siccTipoComisionRecuperacionList = siccTipoComisionRecuperacionList;
	}

	/**
	 * Gets the sicc comision list.
	 *
	 * @return the sicc comision list
	 */
	public LabelValue[] getSiccComisionList() {
		return siccComisionList;
	}

	/**
	 * Sets the sicc comision list.
	 *
	 * @param siccComisionList the new sicc comision list
	 */
	public void setSiccComisionList(LabelValue[] siccComisionList) {
		this.siccComisionList = siccComisionList;
	}

	/**
	 * Gets the com gerente retiradas list.
	 *
	 * @return the com gerente retiradas list
	 */
	public List getComGerenteRetiradasList() {
		return comGerenteRetiradasList;
	}

	/**
	 * Sets the com gerente retiradas list.
	 *
	 * @param comGerenteRetiradasList the new com gerente retiradas list
	 */
	public void setComGerenteRetiradasList(List comGerenteRetiradasList) {
		this.comGerenteRetiradasList = comGerenteRetiradasList;
	}

	/**
	 * Checks if is mostrar tramos.
	 *
	 * @return true, if is mostrar tramos
	 */
	public boolean isMostrarTramos() {
		return mostrarTramos;
	}

	/**
	 * Sets the mostrar tramos.
	 *
	 * @param mostrarTramos the new mostrar tramos
	 */
	public void setMostrarTramos(boolean mostrarTramos) {
		this.mostrarTramos = mostrarTramos;
	}

	public boolean isMostrarTipoCalculo() {
		return mostrarTipoCalculo;
	}

	public void setMostrarTipoCalculo(boolean mostrarTipoCalculo) {
		this.mostrarTipoCalculo = mostrarTipoCalculo;
	}
	
	
}
