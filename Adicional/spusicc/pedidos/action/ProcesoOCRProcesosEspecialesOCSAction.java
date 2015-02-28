package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoOCRProcesosEspecialesOCSForm;

@ManagedBean  
@SessionScoped
public class ProcesoOCRProcesosEspecialesOCSAction extends BaseProcesoAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3430646839850023181L;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoOCRProcesosEspecialesOCSForm form = new ProcesoOCRProcesosEspecialesOCSForm();
		// TODO Auto-generated method stub
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		log.debug("Los parametros del Reporte en el executeProcess son: "
				+ params.toString());
        MantenimientoOCRPedidoControlFacturacionService service = 
        		(MantenimientoOCRPedidoControlFacturacionService)
        					getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
        Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		params.put("psusuario",usuario.getLogin());
		ProcesoOCRProcesosEspecialesOCSForm f = (ProcesoOCRProcesosEspecialesOCSForm) this.formProceso ; 
		f.setFechaFact(DateUtil.convertDateToString(f.getFechaFactD()));
		params.put("fechaFact", f.getFechaFact());
		service.executeProcesoOCRProcesosEspecialesOCS(params);
		return params;

	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		Map criteria = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
        
        ProcesoOCRProcesosEspecialesOCSForm procesosEspecialesOCSForm = (ProcesoOCRProcesosEspecialesOCSForm) this.formProceso ; 
        MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
        PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
		
		// Carga Fecha y Periodo
        procesosEspecialesOCSForm.setFechaFact(controlFacturacion.getFechaProceso());
        procesosEspecialesOCSForm.setPeriodo(controlFacturacion.getCodigoPeriodo());
        procesosEspecialesOCSForm.setFechaFactD(DateUtil.convertStringToDate(procesosEspecialesOCSForm.getFechaFact()));
		
	}
}
