package biz.belcorp.ssicc.web.spusicc.lideres.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.lideres.ProcesoLIDGenerarMensajePuntajeObtenidoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.lideres.form.ProcesoLIDGenerarMensajePuntajeObtenidoForm;

@ManagedBean
@SessionScoped
public class ProcesoLIDGenerarMensajePuntajeObtenidoAction extends BaseProcesoAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1584810441613495120L;
	
	private List siccMarcaList;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoLIDGenerarMensajePuntajeObtenidoForm form= new ProcesoLIDGenerarMensajePuntajeObtenidoForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		
		log.debug("Exectuting action : executeProcess.");
		if(params!=null){
			ProcesoLIDGenerarMensajePuntajeObtenidoService procesoLIDGenerarMensajePuntajeObtenidoService=  
						(ProcesoLIDGenerarMensajePuntajeObtenidoService)getBean("spusicc.procesoLIDGenerarMensajePuntajeObtenidoService");
			procesoLIDGenerarMensajePuntajeObtenidoService.executeGenerarMensajePuntajeObtenido(params);
		}
		ProcesoLIDGenerarMensajePuntajeObtenidoForm form= (ProcesoLIDGenerarMensajePuntajeObtenidoForm)this.formProceso;
		form.setIndicadorEjecucion(Constants.NUMERO_UNO);
		return params;
		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Executin action : view.");
		ProcesoLIDGenerarMensajePuntajeObtenidoForm form=(ProcesoLIDGenerarMensajePuntajeObtenidoForm) this.formProceso;
		Pais pais= this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
		criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
		// Carga de los combos Marca, PeriodoProceso
		form.setFechaProceso(controlFacturacion.getFechaProceso());
		siccMarcaList=reporteService.getMarcas();
		form.setPeriodoProceso(controlFacturacion.getCodigoPeriodo());
		form.setFechaProceso(controlFacturacion.getFechaProceso());
		form.setFechaProcesoD(DateUtil.convertStringToDate(controlFacturacion.getFechaProceso()));
		form.setCodigoIdiomaISO(usuario.getIdioma().getCodigoISO());
		form.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		form.setIndicadorEjecucion(Constants.NUMERO_CERO);
	}
	
	@Override
	protected Map<String, Object>prepareParamsBeforeExecute(Map params, BaseForm form)
	throws Exception{
		ProcesoLIDGenerarMensajePuntajeObtenidoForm form1=(ProcesoLIDGenerarMensajePuntajeObtenidoForm)this.formProceso;
		form1.setFechaProceso(DateUtil.convertDateToString(form1.getFechaProcesoD()));
		params=super.prepareParamsBeforeExecute(params,form);
		return params;
	}

	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	
}


