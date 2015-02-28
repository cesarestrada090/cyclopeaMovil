package biz.belcorp.ssicc.web.spusicc.gen.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.gen.ProcesoGENProcesarCierreService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spusicc.gen.form.ProcesoGENProcesosCierreCampaniaForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProcesoGENProcesosCierreCampaniaAction extends BaseInterfazAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4105409380582820874L;
	
	private List siccMarcaList;
	private List siccCanalList;
	private boolean habilitador;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception
	{
		ProcesoGENProcesosCierreCampaniaForm formInterfaz = new ProcesoGENProcesosCierreCampaniaForm();
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		ProcesoGENProcesosCierreCampaniaForm f = (ProcesoGENProcesosCierreCampaniaForm)this.formInterfaz;
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		f.setHabilitadorHidden(Constants.NUMERO_CERO);		
		f.setMostrarBotonProcesar(true);
		f.setIndicadorEjecucionSICC(Constants.NO);
		f.setIndicadorEjecucionInterfaces(Constants.NO);
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
        
		// Carga de los combos Marca y Canal
		this.siccMarcaList = interfazSiCCService.getMarcas();
		this.siccCanalList = interfazSiCCService.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
        
        MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
		
		// Carga de PeriodoProceso y Fecha Facturacion
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setFechaFacturacion(controlFacturacion.getFechaProceso());
		f.setCodigoPeriodoActual(controlFacturacion.getCodigoPeriodo());
//		String codigoProcesoBatch = this.getParametrosPantalla().get("codigoProcesoBatch"); comente yo
		
		String tipoCierre = this.getParametrosPantalla().get("tipoCierre");
		f.setTipoCierre(tipoCierre);
		
		List lista = new ArrayList() ;
		
		criteria.put("codigoSistema", "GEN");
		criteria.put("codigoInterfaz", f.getCodigoInterfaz());
		
		lista = interfazSiCCService.getListaProcesosGenCierreCampania(criteria);
		
		ProcesoGENProcesarCierreService procesoService = (ProcesoGENProcesarCierreService) 
													getBean("spusicc.procesoGENProcesarCierreService");
		
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		boolean validacionCierre =  procesoService.validaPeriodoACerrar(criteria);
				
		/* INI JJ  PER-SiCC-2012-0361 */
		String indTipoValid = this.getParametrosPantalla().get("indTipoValid");
		f.setIndTipoValid(indTipoValid);
		
		/* FIN JJ  PER-SiCC-2012-0361 */
		if(!validacionCierre) 
		{ 
			if(Constants.NO.equals(f.getIndicadorSeleccionInterfaces())) 
			{
				f.setMostrarBotonProcesar(false);
				this.getResourceMessage("procesoGENProcesosCierreCampaniaForm.msg.validacionPeriodoAProcesar");
			}	
		} else 
		{
			boolean validacionRegiones =  procesoService.existeRegionesSinProcesar(criteria);
			
			if(!validacionRegiones) 
			{
				f.setMostrarBotonProcesar(false);
				this.getResourceMessage("procesoGENProcesosCierreCampaniaForm.msg.validacionRegionesAbiertas");
			} 
		}	
		
		f.setIndicadorModEducacion(procesoService.getIndicadorModEducacion(pais.getCodigo()));
		
		String frecuenciaSGR = this.getParametrosPantalla().get("frecuenciaSGR");
		f.setFrecuenciaSGR(frecuenciaSGR);	
		
		this.habilitador = true;
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		params = super.prepareParamsBeforeExecute(params, form); 
		
		MantenimientoEDUGenericoService mantenimientoEDUGenericoService = (MantenimientoEDUGenericoService)getBean("edu.mantenimientoEDUGenericoService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ProcesoGENProcesosCierreCampaniaForm f = (ProcesoGENProcesosCierreCampaniaForm)this.formInterfaz;
		
		if(Constants.NO.equals(f.getIndicadorEjecucionSICC())) {
			InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
			Integer contCierreCampa = interfazSiCCService.getContCierreCampaByCodigoPeriodo((String)params.get("codigoPeriodo"));
			
			if(contCierreCampa == 0){
				String mensaje = this.getResourceMessage("procesos.gen.mensaje.error.cierrePeriodo",new Object[]{(String)params.get("codigoPeriodo")});
				throw new Exception(mensaje);
			}
		} else {
			ProcesoGENProcesarCierreService procesoService = (ProcesoGENProcesarCierreService) 
															getBean("spusicc.procesoGENProcesarCierreService");
			
			Map criteria = new HashMap();
			criteria.put("codigoPeriodo", f.getCodigoPeriodo());
			boolean validacionCierre =  procesoService.validaPeriodoACerrar(criteria);
			
			if(!validacionCierre) {
				String mensaje = this.getResourceMessage("procesoGENProcesosCierreCampaniaForm.msg.validacionPeriodoAProcesar");
				throw new Exception(mensaje);
			}	
		}
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codigoPais = pais.getCodigo();
//		sesion.setAttribute("codigoUsuario", usuario.getLogin()); comente yo
		params.put("codigoPais", codigoPais);
		
		String servidor = getRequest().getServerName();
		params.put("nombreServidor", servidor);
		params.put("codigoRegion", "-1");
		/* INI JJ  PER-SiCC-2012-0361 */
		params.put("login", usuario.getLogin());
		params.put("periodoProceso", f.getCodigoPeriodo());
		params.put("fechaProceso", f.getFechaFacturacion());
		params.put("indTipoValid", f.getIndTipoValid());
		/* FIN JJ  PER-SiCC-2012-0361 */
		EmpresaComercializadora empresaComercializadora = new EmpresaComercializadora();
		empresaComercializadora.setCodigoPais(codigoPais);
		empresaComercializadora.setEstadoRegistro(Constants.ESTADO_ACTIVO);
		
		if(f.getIndicadorModEducacion().equals(Constants.NUMERO_UNO)){
			params.put("codigoEmpresa", ((EmpresaComercializadora)mantenimientoEDUGenericoService.getEmpresasComercializadorasByPais(empresaComercializadora).get(0)).getCodigoEmpresa());
			
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			LabelValue [] lv= ajaxService.getRegionesEDUByPaisEmpresa(codigoPais,(String)params.get("codigoEmpresa"));
			String [] regionList = getRegionList(lv);
			
			params.put("regionList", regionList);
		}
		
		params.put("frecuenciaSGR", f.getFrecuenciaSGR());
		
		params.put("indicadorProceso", "P");
		params.put("tipoProceso", Constants.CODIGO_TIPO_CIERRE_PERIODO);
				
		return params;
	}
	
	/**
	 * Obtengo la lista de solo codgos de regiones
	 * @param lv
	 * @return
	 */
	private String[] getRegionList(LabelValue[] lv) {
		String [] regionList = new String[lv.length];
		for(int i=0;i<lv.length;i++){
			LabelValue l =  lv[i];
			regionList[i]=l.getValue();
		}
		log.debug("list region "+regionList);
		return regionList;
	}

	public void habilitarParametros(ValueChangeEvent event)
	{
		String valor = (String)event.getNewValue().toString();
		ProcesoGENProcesosCierreCampaniaForm f = (ProcesoGENProcesosCierreCampaniaForm)this.formInterfaz;
		if(valor.equalsIgnoreCase("true"))
		{
			this.habilitador = false;
			f.setHabilitadorHidden(Constants.NUMERO_CERO);			
		}
		else
		{
			this.habilitador = true;					
			f.setHabilitadorHidden(Constants.NUMERO_UNO);
		}
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

	public boolean isHabilitador() {
		return habilitador;
	}

	public void setHabilitador(boolean habilitador) {
		this.habilitador = habilitador;
	}
}
