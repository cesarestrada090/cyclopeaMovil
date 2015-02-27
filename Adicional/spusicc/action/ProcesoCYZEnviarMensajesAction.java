package biz.belcorp.ssicc.web.spusicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.ProcesoCYZProgramaDuplaService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.form.ProcesoCYZEnviarMensajesForm;

@ManagedBean
@SessionScoped
public class ProcesoCYZEnviarMensajesAction extends BaseProcesoAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7142239450465403216L;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoCYZEnviarMensajesForm form=new ProcesoCYZEnviarMensajesForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		log.debug("Ejecutando el proceso de insercion de mensajes.");
		ProcesoCYZProgramaDuplaService service = (ProcesoCYZProgramaDuplaService) getBean("spusicc.procesoCYZProgramaDuplaService");
		service.executeEnviarMensajeProgramaDupla(params);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		// Casteamos el formulario
		ProcesoCYZEnviarMensajesForm form=(ProcesoCYZEnviarMensajesForm) this.formProceso;
		form.setCodigoPrograma("001");
		form.setCodigoProgramaPremio("002");
		form.setCodigoProgramaCumpleanyos("006");
		form.setCodigoProgramaWelcomePack("007");
		form.setCodigoProgramaCumpleanyosConsultoras("008");
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
		criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
		
		MantenimientoOCRPedidoControlFacturacionService serviceOCR = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
		PedidoControlFacturacion controlFacturacion = serviceOCR.getControlFacturacionById(criteria);
		
		form.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		form.setFechaFacturacion(controlFacturacion.getFechaProceso());
		form.setFechaFacturacionD(DateUtil.convertStringToDate(controlFacturacion.getFechaProceso()));
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form)
	throws Exception{
		ProcesoCYZEnviarMensajesForm form1= (ProcesoCYZEnviarMensajesForm) this.formProceso;
		form1.setFechaFacturacion(DateUtil.convertDateToString(form1.getFechaFacturacionD()));
		params=super.prepareParamsBeforeExecute(params, form);
		return params;
	}

}



