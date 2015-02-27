package biz.belcorp.ssicc.web.spusicc.mav.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.mav.MantenimientoMAVConfiguracionService;
import biz.belcorp.ssicc.service.spusicc.mav.ProcesoMAVFacturacionGerentesService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.mav.form.ProcesoMAVFacturacionGerentesForm;

@ManagedBean
@SessionScoped
public class ProcesoMAVFacturacionGerentesAction extends BaseProcesoAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -727147480664798868L;
	
	private List mavActividadesList;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoMAVFacturacionGerentesForm form=new ProcesoMAVFacturacionGerentesForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		//super.executeProcess(form, request, params);
		ProcesoMAVFacturacionGerentesService service = (ProcesoMAVFacturacionGerentesService) getBean("spusicc.procesoMAVFacturacionGerentesService");
		service.executeFacturacionGerentes(params);
		return params;
		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		ProcesoMAVFacturacionGerentesForm form=(ProcesoMAVFacturacionGerentesForm) this.formProceso;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoMAVConfiguracionService service = (MantenimientoMAVConfiguracionService) this.getBean("spusicc.mantenimientoMAVConfiguracionService");
		form.setCodigoActividad(null);
		
		//combo actividades
		List listActividades = service.getActividadesGerente();
		mavActividadesList=listActividades;
		
		Map criteria= new HashMap();
		criteria.put("codigoPais", pais.getCodigo());

		criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
		criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
		MantenimientoOCRPedidoControlFacturacionService serviceOCR = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceOCR.getControlFacturacionById(criteria);

		// Carga el periodo proceso
		form.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
	}
	
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,BaseForm form) throws Exception {
		params=super.prepareParamsBeforeExecute(params,form);
		return params;
	}

	public List getMavActividadesList() {
		return mavActividadesList;
	}

	public void setMavActividadesList(List mavActividadesList) {
		this.mavActividadesList = mavActividadesList;
	}
	
	
	

}




