package biz.belcorp.ssicc.web.spusicc.gen.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spusicc.gen.form.ProcesoGENProcesarGP3Form;

/**

	  */
@ManagedBean
@SessionScoped
public class ProcesoGENProcesarGP3Action extends BaseInterfazAbstractAction {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -678114646811596943L;
	private List genINterfacesPaquete;
	private boolean opcionPeriodoFecha;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction
	 * #devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		ProcesoGENProcesarGP3Form form = new ProcesoGENProcesarGP3Form();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executin action : view.");

		ProcesoGENProcesarGP3Form f = (ProcesoGENProcesarGP3Form) this.formInterfaz;

		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente

        MantenimientoOCRPedidoControlFacturacionService service = 
        		(MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);

		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setCodigoPeriodoActual(controlFacturacion.getCodigoPeriodo());
		f.setFechaFacturacionD(DateUtil.convertStringToDate(controlFacturacion
				.getFechaProceso()));
		setOpcionPeriodoFecha(true);

		String codigoProceso = getRequest().getParameter("codigoProcesoBatch");
		String codigoSistema = getRequest().getParameter("codigoSistema");


		List lista = new ArrayList();

		criteria.put("codigoSistema", codigoSistema);
		criteria.put("codigoInterfaz", f.getCodigoInterfaz());

		lista = interfazSiCCService.getListaProcesosGenCierreCampania(criteria);
		this.genINterfacesPaquete=lista;


	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction
	 * #prepareParamsBeforeExecute(java.util.Map,
	 * biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		ProcesoGENProcesarGP3Form f = (ProcesoGENProcesarGP3Form) this.formInterfaz;
		f.setFechaFacturacion(DateUtil.convertDateToString(f.getFechaFacturacionD()));
		params = super.prepareParamsBeforeExecute(params, form);
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
    	String codigoPais = pais.getCodigo();
    	params.put("codigoPais", codigoPais);

    	Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
    	params.put("codigoUsuario", usuario.getLogin());
    	params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
    	
    	/* INI SA PER-SiCC-2012-0375 */
    	params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
    	/* FIN SA PER-SiCC-2012-0375 */

    	params.put("codigoPeriodo",f.getCodigoPeriodo());
    	
    	params.put("fechaFacturacion",f.getFechaFacturacion());

    	return params;
	}

	/**
	 * Metodo para validar nuevo periodo
	 * 
	 * @param val
	 */
	public void loadPeriodoFecha(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadPeriodoFecha");
		}
		String opcion = (String) val.getNewValue().toString();

		if (opcion == "true") {
			setOpcionPeriodoFecha(false);
		} else {
			setOpcionPeriodoFecha(true);
		}
	}

	/**
	 * @return the opcionPeriodoFecha
	 */
	public boolean isOpcionPeriodoFecha() {
		return opcionPeriodoFecha;
	}

	/**
	 * @param opcionPeriodoFecha
	 *            the opcionPeriodoFecha to set
	 */
	public void setOpcionPeriodoFecha(boolean opcionPeriodoFecha) {
		this.opcionPeriodoFecha = opcionPeriodoFecha;
	}

}
