package biz.belcorp.ssicc.web.spusicc.gen.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.fac.MantenimientoFACGenericoService;
import biz.belcorp.ssicc.service.spusicc.gen.ProcesoGENProcesarCierreService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.util.StringUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ProcesoGENProcesarCierreZonaAction extends BaseInterfazAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5042001743476757926L;
	
	private List siccMarcaList;
	private List siccCanalList;
	private List siccRegionList;
	private List siccZonaList;
	private List genInterfazPaquete;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception 
	{
		ProcesoGENProcesarCierreZonaForm interfazForm = new ProcesoGENProcesarCierreZonaForm();
		return interfazForm;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		ProcesoGENProcesarCierreZonaForm f = (ProcesoGENProcesarCierreZonaForm)this.formInterfaz;
		
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		
		f.setRegiones(null);
		f.setZonas(null);
		f.setMostrarBotonProcesar(true);
		/* INI JJ  PER-SiCC-2012-0388 */
		f.setIndicadorEjecucionSICC(Constants.NO);
		f.setIndicadorEjecucionInterfaces(Constants.NO);
		/* FIN JJ  PER-SiCC-2012-0388 */
				
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		Map params = new HashMap();
        params.clear();
        params.put("codigoISO", usuario.getIdioma().getCodigoISO());
        params.put("codigoPais", pais.getCodigo());
		
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
        
		// Carga de los combos Marca y Canal
		this.siccMarcaList = interfazSiCCService.getMarcas();
		this.siccCanalList = interfazSiCCService.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
        
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)
									getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
		
		// Carga de PeriodoProceso y Fecha Facturacion
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setFechaFacturacion(controlFacturacion.getFechaProceso());
		f.setFechaFacturacionDate(DateUtil.convertStringToDate(f.getFechaFacturacion()));

		ProcesoGENProcesarCierreService procesoService = (ProcesoGENProcesarCierreService) 
									getBean("spusicc.procesoGENProcesarCierreService");
		
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("fechaFacturacion", f.getFechaFacturacion());
		criteria.put("tipoCierre", Constants.CODIGO_TIPO_CIERRE_ZONA);
		
		this.siccRegionList =  procesoService.getRegionesACerrar(criteria);
		this.siccZonaList =  procesoService.getZonasACerrar(criteria);
		
		/* INI JJ  PER-SiCC-2012-0388 */
//		String codigoProcesoBatch = request.getParameter("codigoProcesoBatch");
//		sesion.setAttribute("codigoProcesoBatch", codigoProcesoBatch);
		
		String indicadorProceso = this.getParametrosPantalla().get("indicadorProceso");
		f.setIndicadorProceso(indicadorProceso);
		
		List lista = new ArrayList() ;
		
		criteria.put("codigoSistema", "GEN");
		criteria.put("codigoInterfaz", f.getCodigoInterfaz());
		
		lista = interfazSiCCService.getListaProcesosGenCierreCampania(criteria);
		
		List listaProcesosTemp = procesoService.getProcesosCierreZona(criteria);
		List listaProcesos = new ArrayList();
		Base base;
		for(int i=0;i<listaProcesosTemp.size();i++){
			Map baseResult = (HashMap)listaProcesosTemp.get(i);
			
			base = new Base();
			base.setCodigo((String)baseResult.get("value"));
			base.setDescripcion((String)baseResult.get("label"));
			
			listaProcesos.add(base);
		}
		
//		sesion.setAttribute("totalProcesosSICC", listaProcesos.size());
		listaProcesos.addAll(lista);
		/* FIN JJ  PER-SiCC-2012-0388 */
		this.genInterfazPaquete = listaProcesos;
		
		if(this.siccZonaList.size() == 0) 
		{ 
			f.setMostrarBotonProcesar(false);
			this.addError("",this.getResourceMessage("procesoGENProcesarCierreZonaForm.msg.validacionZonasAProcesar"));
			this.mostrarBotonExecute = false;
		} 		
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{		
		ProcesoGENProcesarCierreZonaForm f = (ProcesoGENProcesarCierreZonaForm)this.formInterfaz;
		MantenimientoFACGenericoService mantenimientoFACGenericoService = (MantenimientoFACGenericoService)getBean("spusicc.mantenimientoFACGenericoService");
		f.setFechaFacturacion(DateUtil.convertDateToString(f.getFechaFacturacionDate()));
		List zonas = this.siccZonaList;
		
		Long []oidZonas = new Long[zonas.size()];
		String []codigoZonas = new String[zonas.size()];
		String oidZonasString[] = new String[zonas.size()];
		
		for(int i=0; i<zonas.size(); i++) {
			Map mapZona = (Map)zonas.get(i);
			oidZonas[i] = new Long(mapZona.get("value").toString());
			oidZonasString[i] = new String(mapZona.get("value").toString());
			
			codigoZonas[i] = mantenimientoFACGenericoService.getCodigoZonaByOidZona(Integer.valueOf(oidZonas[i].toString()));
		}
		
		Integer contCierreZona;
		
		if(Constants.NO.equals(f.getIndicadorEjecucionSICC())) {
			InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
			
			for(int i=0;i<oidZonas.length;i++){
				contCierreZona = interfazSiCCService.getContCierreZonaByCodigoPeriodoOidZona((String)params.get("codigoPeriodo"),Integer.valueOf(oidZonas[i].toString()));
				if(contCierreZona == 0){
					
					String codigoZonaNoCerrada = mantenimientoFACGenericoService.getCodigoZonaByOidZona(Integer.valueOf(oidZonas[i].toString()));
					
					String parametrosMsg[] = {codigoZonaNoCerrada,(String)params.get("codigoPeriodo")};
					String mensaje = this.getResourceMessage("procesoGENProcesarCierreZonaForm.msg.validacionCierreZona", new Object[]{parametrosMsg});
					throw new Exception(mensaje);
				}
			}
		} 
		
		params.put("zonaList", oidZonas);
		if(oidZonasString != null && oidZonasString.length > 0) {
			params.put("zonaListAux", StringUtil.obtieneListaCodigos(codigoZonas, "", ""));
		}
		params.put("codigoPaquete", f.getCodigoInterfaz());
		
		params = super.prepareParamsBeforeExecute(params, form); 
	
		return params;
	}

	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public List getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(List siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public List getGenInterfazPaquete() {
		return genInterfazPaquete;
	}

	public void setGenInterfazPaquete(List genInterfazPaquete) {
		this.genInterfazPaquete = genInterfazPaquete;
	}

}
