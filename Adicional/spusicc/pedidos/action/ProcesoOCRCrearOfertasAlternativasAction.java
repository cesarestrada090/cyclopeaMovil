package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPreAlternativosAutomaticosService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoOCRCrearOfertasAlternativasForm;

@ManagedBean
@SessionScoped
public class ProcesoOCRCrearOfertasAlternativasAction extends
		BaseProcesoAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3430646839850023181L;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoOCRCrearOfertasAlternativasForm form = new ProcesoOCRCrearOfertasAlternativasForm();
		// TODO Auto-generated method stub
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		ProcesoOCRCrearOfertasAlternativasForm f = (ProcesoOCRCrearOfertasAlternativasForm) formProceso;

		MantenimientoOCRPreAlternativosAutomaticosService service = (MantenimientoOCRPreAlternativosAutomaticosService) getBean("spusicc.pedidos.mantenimientoOCRPreAlternativosAutomaticosService");
		params.put("codigoPais", f.getCodigoPais());
		params.put("codigoPerido", f.getCodigoPeriodo());

		service.executeProcesoCrearOfertas(params);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executing action : setViewAttributes.");
//		Obteniendo valores de la sesion
		String codigoUsuario= mPantallaPrincipalBean.getCurrentUser().getCodigo();
		String codigoPais   =  mPantallaPrincipalBean.getCurrentCountry().getCodigo();

		ProcesoOCRCrearOfertasAlternativasForm f = (ProcesoOCRCrearOfertasAlternativasForm)formProceso;
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", codigoPais);
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
	     
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaPeriodo);
		f.setCodigoPais(codigoPais);
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());

	}
}
