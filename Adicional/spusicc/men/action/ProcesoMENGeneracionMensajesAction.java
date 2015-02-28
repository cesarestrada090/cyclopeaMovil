package biz.belcorp.ssicc.web.spusicc.men.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.men.MantenimientoMENPlantillaService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spusicc.men.form.ProcesoMENGeneracionMensajesForm;

@SessionScoped
@ManagedBean
public class ProcesoMENGeneracionMensajesAction extends
		BaseInterfazAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4474347800071890981L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		ProcesoMENGeneracionMensajesForm form = new ProcesoMENGeneracionMensajesForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		ProcesoMENGeneracionMensajesForm f = (ProcesoMENGeneracionMensajesForm) this.formInterfaz;
		MantenimientoMENPlantillaService service = (MantenimientoMENPlantillaService) getBean("spusicc.mantenimientoMENPlantillaService");
		// Pais pais = getPais(sesion);
		// InterfazSiCCService interfazSiCCService = (InterfazSiCCService)
		// getBean("sisicc.interfazSiCCService");
		// interfazSiCCService.getPeriodoDefaultByPaisCanal(pais.getCodigo(),
		// Constants.CODIGO_CANAL_DEFAULT)
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																// Campanha
																// Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																	// Campanha
																	// activa q
																	// se
																	// procesa
																	// actualmente

		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceFact
				.getControlFacturacionById(criteria);

		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setFechaFacturacion(controlFacturacion.getFechaProceso());
		f.setFechaFacturacionD(DateUtil.convertStringToDate(f
				.getFechaFacturacion()));
		//
		f.setIndicadorProcesoActivo(Integer.parseInt(service
				.getIndicadorProcesoActivo()) > 0 ? Constants.NUMERO_UNO
				: Constants.NUMERO_CERO);
		if (Constants.NUMERO_CERO.equals(f.getIndicadorProcesoActivo())) {
			// ActionMessages messages = new ActionMessages();
			// messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
			// "procesoMENGeneracionMensajesForm.errors.indicadorProceso.inactivo"));
			// saveErrors(request, messages);
		}

	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		ProcesoMENGeneracionMensajesForm f = (ProcesoMENGeneracionMensajesForm) this.formInterfaz;
		f.setFechaFacturacion(DateUtil.convertDateToString(f
				.getFechaFacturacionD()));
		params = super.prepareParamsBeforeExecute(params, form);
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		// se valida que haya proceos habilitados
		MantenimientoMENPlantillaService service = (MantenimientoMENPlantillaService) getBean("spusicc.mantenimientoMENPlantillaService");
		List list = service.getProcesosHabilitados(params);
		if (list.size() == 0) {
			String mensaje = this
					.getResourceMessage("procesoMENGeneracionMensajesForm.no.procesos.habilitados");
			throw new Exception(mensaje);
		}
		// llenamos el compo paquete para el tipo de operacion seleccionado
		String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/") ;
		params.put("rutaPath", path);
		params.put("usuarioTemp", usuario);
		service.setProcesoPaquete(params);
		log.debug("finalizando prepareParamsBeforeExecute ===>" + params);
		return params;
	}

}
