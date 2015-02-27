package biz.belcorp.ssicc.web.spusicc.lideres.action;

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
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.lideres.ProcesoLIDEvaluarRecomendaciones2PedidosFacturacionService;
import biz.belcorp.ssicc.service.spusicc.lideres.ProcesoLIDEvaluarRecomendaciones3PedidosFacturacionService;
import biz.belcorp.ssicc.service.spusicc.lideres.ProcesoLIDGenerarMensajePuntajeObtenidoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.lideres.form.ProcesoLIDCierreProcesosDiariosForm;

@ManagedBean
@SessionScoped
public class ProcesoLIDCierreProcesosDiariosAction extends BaseProcesoAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6366577808823107867L;
	
	private List siccMarcaList;
	private List cargaArchivos;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoLIDCierreProcesosDiariosForm form=new ProcesoLIDCierreProcesosDiariosForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		log.debug("Exectuting action : executeProcess.");
		if(params!=null){
			log.debug("Exectuting action : procesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoService"); 
			ProcesoLIDEvaluarRecomendaciones2PedidosFacturacionService procesoLIDEvaluarRecomendaciones2PedidosFacturacionService=  
				(ProcesoLIDEvaluarRecomendaciones2PedidosFacturacionService)getBean("spusicc.procesoLIDEvaluarRecomendaciones2PedidosFacturacionService");
			
			log.debug("Exectuting action : procesoLIDEvaluarRecomendaciones3PedidosCierrePeriodoService");
			ProcesoLIDEvaluarRecomendaciones3PedidosFacturacionService procesoLIDEvaluarRecomendaciones3PedidosFacturacionService=  
				(ProcesoLIDEvaluarRecomendaciones3PedidosFacturacionService)getBean("spusicc.procesoLIDEvaluarRecomendaciones3PedidosFacturacionService");
			
			log.debug("Exectuting action : procesoLIDGenerarMensajePuntajeObtenidoService"); 
			ProcesoLIDGenerarMensajePuntajeObtenidoService procesoLIDGenerarMensajePuntajeObtenidoService=  
				(ProcesoLIDGenerarMensajePuntajeObtenidoService)getBean("spusicc.procesoLIDGenerarMensajePuntajeObtenidoService");
			procesoLIDGenerarMensajePuntajeObtenidoService.executeGenerarMensajePuntajeObtenido(params);
		}
		
		log.debug("Exectuting action : setExecuteAttributes.");
		ProcesoLIDCierreProcesosDiariosForm form = (ProcesoLIDCierreProcesosDiariosForm)this.formProceso;
		form.setIndicadorEjecucion(Constants.NUMERO_UNO);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Executin action : view.");
		ProcesoLIDCierreProcesosDiariosForm form = (ProcesoLIDCierreProcesosDiariosForm)this.formProceso;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario= this.mPantallaPrincipalBean.getCurrentUser();
		Map criteria = new HashMap();
		criteria.put("codigoPais",pais.getCodigo());
		criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
		criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
		
		InterfazSiCCService interfazService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
		
		// Carga de los combos Marca, PeriodoProceso
		siccMarcaList=interfazService.getMarcas();
		form.setFechaProceso(controlFacturacion.getFechaProceso());
		form.setFechaProcesoD(DateUtil.convertStringToDate(controlFacturacion.getFechaProceso()));
		form.setPeriodoProceso(controlFacturacion.getCodigoPeriodo());
		form.setCodigoIdiomaISO(usuario.getIdioma().getCodigoISO());
		form.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		form.setIndicadorEjecucion(Constants.NUMERO_CERO);
		
		/* Colocando procesos a ejecutar */
		setListProcesosPorMostrar(form);

	}
	
    private void setListProcesosPorMostrar(ProcesoLIDCierreProcesosDiariosForm form) {
		ArrayList resultado = new ArrayList();
		//MessageResources messageResources = getResources(request);
		
		for(int i=1, j=1; i <= 3; i++ ) {
			String cadena = new Integer(i).toString().trim();
			log.debug("dddddddddddd"+cadena);
			this.adicionarProceso(resultado,/*messageResources,*/ cadena, "procesoLIDCierreProcesosDiariosForm.proceso0" + cadena);
		}	
		//request.getSession().setAttribute("cargaArchivos",	resultado);
		log.debug("wqwqwqwq"+resultado);
		this.cargaArchivos=resultado;
	}
    
    private void adicionarProceso(ArrayList resultado,/* MessageResources messageResources,*/ String codigo, String keyMensaje) {
		Base bean = new Base();
		//String proceso = messageResources.getMessage(keyMensaje);
		String proceso= this.getResourceMessage(keyMensaje);
		bean.setCodigo(codigo);
		bean.setDescripcion(proceso);			
		resultado.add(bean);
	}
    
    @Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map<String, Object> params, BaseForm form) throws Exception 
	{
    	if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParamsBeforeExecute' method");
		}
    	ProcesoLIDCierreProcesosDiariosForm form1=(ProcesoLIDCierreProcesosDiariosForm)this.formProceso;
    	form1.setFechaProceso(DateUtil.convertDateToString(form1.getFechaProcesoD()));
    	params = super.prepareParamsBeforeExecute(params, form);
    	return params;
	}

	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	public List getCargaArchivos() {
		return cargaArchivos;
	}

	public void setCargaArchivos(List cargaArchivos) {
		this.cargaArchivos = cargaArchivos;
	}

    
}




