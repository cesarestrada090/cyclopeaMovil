package biz.belcorp.ssicc.web.spusicc.let.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spusicc.let.form.ProcesoINCEjecutarProcesosInicioCampanaForm;

@ManagedBean
@SessionScoped
public class ProcesoINCEjecutarProcesosInicioCampanaAction extends
		BaseInterfazAbstractAction {

	private static final long serialVersionUID = 4719206813800027888L;
	private List cupProgramasList;
	private List siccMarcaList;
	private List siccCanalList;
	private List incInicioCampanhaInterfacesPaquete;
	
	public List getIncInicioCampanhaInterfacesPaquete() {
		return incInicioCampanhaInterfacesPaquete;
	}

	public void setIncInicioCampanhaInterfacesPaquete(
			List incInicioCampanhaInterfacesPaquete) {
		this.incInicioCampanhaInterfacesPaquete = incInicioCampanhaInterfacesPaquete;
	}

	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub

		ProcesoINCEjecutarProcesosInicioCampanaForm f = (ProcesoINCEjecutarProcesosInicioCampanaForm) formInterfaz;
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();

		Map params = new HashMap();
		params.clear();
		params.put("codigoISO", usuario.getIdioma().getCodigoISO());
		params.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());

		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		// Carga de los combos Marca y Canal
		siccMarcaList = interfazSiCCService.getMarcas();
		siccCanalList = interfazSiCCService.getCanalesByCodigoISO(usuario
				.getIdioma().getCodigoISO());

		Map criteria = new HashMap();
		criteria.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());
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

		String codigoProcesoBatch = mPantallaPrincipalBean
				.getCodigoProcesoBatch();
		f.setCodigoProcesoBatch(codigoProcesoBatch);
		// sesion.setAttribute("codigoProcesoBatch", codigoProcesoBatch);

		String indicadorProceso = f.getIndicadorProceso();
		f.setIndicadorProceso(indicadorProceso);

		List lista = new ArrayList();

		criteria.put("codigoSistema", "INC");
		criteria.put("codigoInterfaz", f.getCodigoInterfaz());

		lista = interfazSiCCService.getListaProcesosLet(criteria);

		// revisar
		
		// sesion.setAttribute(Constants.INC_INICIO_CAMPANHA_INTERFACES_PAQUETE,
		// lista);
	}

	/**
	 * @return the cupProgramasList
	 */
	public List getCupProgramasList() {
		return cupProgramasList;
	}

	/**
	 * @param cupProgramasList
	 *            the cupProgramasList to set
	 */
	public void setCupProgramasList(List cupProgramasList) {
		this.cupProgramasList = cupProgramasList;
	}

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		ProcesoINCEjecutarProcesosInicioCampanaForm form = new ProcesoINCEjecutarProcesosInicioCampanaForm();
		return form;
	}


	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) 
			throws Exception{	



		
		
		
		
		
		
		ProcesoINCEjecutarProcesosInicioCampanaForm f = (ProcesoINCEjecutarProcesosInicioCampanaForm) form;

		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();

		String servidor = getRequest().getServerName();
		params.put("nombreServidor", servidor);
		params.put("indicadorProceso", "P");
	
		return params;
	}
	
}
