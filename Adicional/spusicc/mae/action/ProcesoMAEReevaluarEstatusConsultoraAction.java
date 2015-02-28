package biz.belcorp.ssicc.web.spusicc.mae.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.mae.ProcesoMAEReevaluarEstatusConsultoraService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.mae.form.ProcesoMAEReevaluarEstatusConsultoraForm;

@ManagedBean
@SessionScoped
public class ProcesoMAEReevaluarEstatusConsultoraAction extends BaseProcesoAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6540796022309325587L;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoMAEReevaluarEstatusConsultoraForm form=new ProcesoMAEReevaluarEstatusConsultoraForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		//super.executeProcess(form, request, params);
		ProcesoMAEReevaluarEstatusConsultoraForm form=(ProcesoMAEReevaluarEstatusConsultoraForm) this.formProceso;
		log.debug("Los parametros del Proceso en el executeProcess son: "+ params.toString());
		
		ProcesoMAEReevaluarEstatusConsultoraService service = (ProcesoMAEReevaluarEstatusConsultoraService) getBean("spusicc.procesoMAEReevaluarEstatusConsultoraService");
		params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);

		service.executeReevaluarEstatusConsultora(params);

		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		ProcesoMAEReevaluarEstatusConsultoraForm form=(ProcesoMAEReevaluarEstatusConsultoraForm) this.formProceso;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
		criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)	getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);

		// Periodo
		form.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		criteria.put("codigoPeriodo", form.getCodigoPeriodo());
		
		
		ProcesoMAEReevaluarEstatusConsultoraService procesoService = (ProcesoMAEReevaluarEstatusConsultoraService) getBean("spusicc.procesoMAEReevaluarEstatusConsultoraService");
		boolean validar = procesoService.existenRegionesCerradas(criteria);
		
		if(!validar){
			this.addWarn("", this.getResourceMessage("procesoMAEReevaluarEstatusConsultoraForm.msg.noExistenRegionesCerradas"));
//			FacesContext.getCurrentInstance().addMessage("msj", new FacesMessage(FacesMessage.SEVERITY_WARN, "", this.getResourceMessage("procesoMAEReevaluarEstatusConsultoraForm.msg.noExistenRegionesCerradas")));
			 //form.setMostrarBotonProcesar(false);
			this.mostrarBotonExecute=false;
		}
		else{
			//form.setMostrarBotonProcesar(true);
			this.mostrarBotonExecute=true;
		}
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,BaseForm form) throws Exception {
		params=super.prepareParamsBeforeExecute(params,form);
		return params;
	}
}

	

