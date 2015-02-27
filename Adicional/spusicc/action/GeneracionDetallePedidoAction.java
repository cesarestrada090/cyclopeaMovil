package biz.belcorp.ssicc.web.spusicc.action;

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
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spisicc.GeneracionDetallePedidoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.form.GeneracionDetallePedidoForm;

/**
 * The Class InterfazADAEnviarInformacionAdamAction.
 * 
 * @autor: Hernando Huaman Flores
 * @version: 1.0 03/12/2014
 */
@ManagedBean
@SessionScoped
public class GeneracionDetallePedidoAction extends BaseProcesoAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1299251190036872208L;
	private List siccMarcaList;
	private List siccCanalList;
	private List impTipoSolicitudList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction
	 * #devuelveFormInterfaz()
	 */
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		GeneracionDetallePedidoForm form = new GeneracionDetallePedidoForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method de lista GeneracionDetallePedido");
		}
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();

		this.siccMarcaList = service.getMarcas();
		this.siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma()
				.getCodigoISO());

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

		GeneracionDetallePedidoForm detallepedidoForm = (GeneracionDetallePedidoForm) this.formProceso;
		MantenimientoOCRPedidoControlFacturacionService serviceMantPedidoCtrlFact = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceMantPedidoCtrlFact
				.getControlFacturacionById(criteria);

		// Carga Fecha y Periodo
		detallepedidoForm.setCodigoPeriodo(controlFacturacion
				.getCodigoPeriodo());
		detallepedidoForm.setFechaFacturacion(controlFacturacion
				.getFechaProceso());
		detallepedidoForm.setFechaFacturacionD(DateUtil
				.convertStringToDate(detallepedidoForm.getFechaFacturacion()));

		// Carga tipos de solicitud
		GeneracionDetallePedidoService serviceGeneracion = (GeneracionDetallePedidoService) getBean("spisicc.generacionDetallePedidoService");
		this.impTipoSolicitudList = serviceGeneracion.getTipoSolicitud();
		return;

	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		log.debug("Parameters List :  " + params.toString());
		// Obtenemos la instancia del servicio
		GeneracionDetallePedidoService service = (GeneracionDetallePedidoService) getBean("spisicc.generacionDetallePedidoService");
		// Ejecutamos el proceso de generacion de Detalle de Pedido
		GeneracionDetallePedidoForm f = (GeneracionDetallePedidoForm) this.formProceso;
		f.setFechaFacturacion(DateUtil.convertDateToString(f.getFechaFacturacionD()));
		params.put("fechaFacturacion", f.getFechaFacturacion());
		service.executeGenerarDetallePedido(params);
		return params;
	}

	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * @return the impTipoSolicitudList
	 */
	public List getImpTipoSolicitudList() {
		return impTipoSolicitudList;
	}

	/**
	 * @param impTipoSolicitudList the impTipoSolicitudList to set
	 */
	public void setImpTipoSolicitudList(List impTipoSolicitudList) {
		this.impTipoSolicitudList = impTipoSolicitudList;
	}
	
	

}
