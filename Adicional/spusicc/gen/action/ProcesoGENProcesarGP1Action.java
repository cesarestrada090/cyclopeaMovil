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
import biz.belcorp.ssicc.web.spusicc.gen.form.ProcesoGENProcesarGP1Form;

@ManagedBean
@SessionScoped
public class ProcesoGENProcesarGP1Action extends BaseInterfazAbstractAction {
	
	private static final long serialVersionUID = 6757519628037552419L;
	private List genInterfacesPaquete = new ArrayList();
	private boolean opcionPeriodoFecha;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.action.BaseAbstractAction#setViewAttributes(javax.servlet.http.HttpServletRequest, org.apache.struts.action.ActionForm)
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		
		log.debug("Executin action : view.");

		ProcesoGENProcesarGP1Form f = (ProcesoGENProcesarGP1Form)this.formInterfaz;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente

        MantenimientoOCRPedidoControlFacturacionService service = 
        		(MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);

		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setFechaFacturacion(controlFacturacion.getFechaProceso());
		f.setCodigoPeriodoActual(controlFacturacion.getCodigoPeriodo());
		f.setFechaFacturacionD(DateUtil.convertStringToDate(controlFacturacion
				.getFechaProceso()));
		setOpcionPeriodoFecha(true);

		List lista = new ArrayList();

		criteria.put("codigoSistema", this.mPantallaPrincipalBean.getCodigoSistema());
		criteria.put("codigoInterfaz", f.getCodigoInterfaz());

		lista = interfazSiCCService.getListaProcesosGenCierreCampania(criteria);
		
		genInterfacesPaquete = lista;
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		// TODO Auto-generated method stub
//		Map params = super.prepareParamsBeforeExecute(form, request);
    	ProcesoGENProcesarGP1Form f = (ProcesoGENProcesarGP1Form)this.formInterfaz;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setFechaFacturacion(DateUtil.convertDateToString(f.getFechaFacturacionD()));
		params = super.prepareParamsBeforeExecute(params, form);
    	String codigoPais = pais.getCodigo();
    	params.put("codigoPais", codigoPais);

    	Usuario usuario = (Usuario) params.get("usuario");
    	params.put("codigoUsuario", usuario.getLogin());
    	params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
    	
    	params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);

    	params.put("codigoPeriodo",f.getCodigoPeriodo());
    	params.put("fechaFacturacion",f.getFechaFacturacion());

    	return params;
	}
	
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		ProcesoGENProcesarGP1Form form = new ProcesoGENProcesarGP1Form();
		return form;
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
	 * @return the genInterfacesPaquete
	 */
	public List getGenInterfacesPaquete() {
		return genInterfacesPaquete;
	}

	/**
	 * @param genInterfacesPaquete the genInterfacesPaquete to set
	 */
	public void setGenInterfacesPaquete(List genInterfacesPaquete) {
		this.genInterfacesPaquete = genInterfacesPaquete;
	}

	/**
	 * @return the opcionPeriodoFecha
	 */
	public boolean isOpcionPeriodoFecha() {
		return opcionPeriodoFecha;
	}

	/**
	 * @param opcionPeriodoFecha the opcionPeriodoFecha to set
	 */
	public void setOpcionPeriodoFecha(boolean opcionPeriodoFecha) {
		this.opcionPeriodoFecha = opcionPeriodoFecha;
	}
}