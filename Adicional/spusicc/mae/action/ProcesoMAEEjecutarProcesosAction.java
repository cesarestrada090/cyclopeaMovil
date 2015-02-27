package biz.belcorp.ssicc.web.spusicc.mae.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.util.StringUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spusicc.mae.form.ProcesoMAEEjecutarProcesosForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazAPEEnviarCostoCajaAction.java.html"> <i>View Source </i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ProcesoMAEEjecutarProcesosAction extends
		BaseInterfazAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1285279310924381494L;
	private List siccMarcaList;
	private List siccCanalList;
	private List incInterfacesPaquete;
	private String indicadorProceso;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		ProcesoMAEEjecutarProcesosForm form = new ProcesoMAEEjecutarProcesosForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		ProcesoMAEEjecutarProcesosForm f = (ProcesoMAEEjecutarProcesosForm) this.formInterfaz;

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		Map params = new HashMap();
		params.clear();
		params.put("codigoISO", usuario.getIdioma().getCodigoISO());
		params.put("codigoPais", pais.getCodigo());

		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		// Carga de los combos Marca y Canal
		this.siccMarcaList = interfazSiCCService.getMarcas();
		this.siccCanalList = interfazSiCCService.getCanalesByCodigoISO(usuario
				.getIdioma().getCodigoISO());

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

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteria);

		// Carga de PeriodoProceso y Fecha Facturacion
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setFechaFacturacion(controlFacturacion.getFechaProceso());

		// recuperamos el tipo de Proceso LOVE
		this.indicadorProceso = (String) this.parametrosPantalla
				.get("indicadorProceso");
		f.setIndicadorProceso(indicadorProceso);

		List lista = new ArrayList();

		if (indicadorProceso.equals("G") || indicadorProceso.equals("Z")
				|| indicadorProceso.equals("R") || indicadorProceso.equals("P")
				|| indicadorProceso.equals("I")) {

			criteria.put("codigoSistema", "MAE");
			criteria.put("codigoInterfaz", f.getCodigoInterfaz());

			lista = interfazSiCCService.getListaProcesosIncentivos(criteria);
			this.incInterfacesPaquete = lista;
		}

	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		params = super.prepareParamsBeforeExecute(params, form);
		ProcesoMAEEjecutarProcesosForm f = (ProcesoMAEEjecutarProcesosForm) this.formInterfaz;

		String servidor = this.getRequest().getServerName();
		params.put("zonaList",
                (f.getZonaList() == null) ? new ArrayList()
                                          : Arrays.asList(f.getZonaList()));
		
		if(f.getZonaList() != null && f.getZonaList().length > 0)
			params.put("zonaListAux", StringUtil.obtieneListaCodigos(f.getZonaList(), "", ""));
		params.put("nombreServidor", servidor);

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
	 * @return the incInterfacesPaquete
	 */
	public List getIncInterfacesPaquete() {
		return incInterfacesPaquete;
	}

	/**
	 * @param incInterfacesPaquete the incInterfacesPaquete to set
	 */
	public void setIncInterfacesPaquete(List incInterfacesPaquete) {
		this.incInterfacesPaquete = incInterfacesPaquete;
	}

	/**
	 * @return the indicadorProceso
	 */
	public String getIndicadorProceso() {
		return indicadorProceso;
	}

	/**
	 * @param indicadorProceso the indicadorProceso to set
	 */
	public void setIndicadorProceso(String indicadorProceso) {
		this.indicadorProceso = indicadorProceso;
	}
	
	

}
