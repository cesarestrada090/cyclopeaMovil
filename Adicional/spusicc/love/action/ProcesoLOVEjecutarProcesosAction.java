package biz.belcorp.ssicc.web.spusicc.love.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spusicc.love.form.ProcesoLOVEjecutarProcesosForm;

@SessionScoped
@ManagedBean
public class ProcesoLOVEjecutarProcesosAction extends
		BaseInterfazAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1635682784821209855L;
	private List siccMarcaList;
	private List siccCanalList;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private String LOV_INDICADOR_PROCESO_GP4 = Constants.LOV_INDICADOR_PROCESO_GP4;
	private String LOV_INDICADOR_PROCESO_CIERRE_ZONA = Constants.LOV_INDICADOR_PROCESO_CIERRE_ZONA;
	private String LOV_INDICADOR_PROCESO_CIERRE_REGION = Constants.LOV_INDICADOR_PROCESO_CIERRE_REGION;
	private String LOV_INDICADOR_PROCESO_CIERRE_CAMPANA = Constants.LOV_INDICADOR_PROCESO_CIERRE_CAMPANA;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		ProcesoLOVEjecutarProcesosForm form = new ProcesoLOVEjecutarProcesosForm();
		return form;
	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {

		ProcesoLOVEjecutarProcesosForm f = (ProcesoLOVEjecutarProcesosForm) this.formInterfaz;
		f.setFechaFacturacion(DateUtil.convertDateToString(f
				.getFechaFacturacionD()));
		params = super.prepareParamsBeforeExecute(params, form);
		params.put("zonaList", (f.getZonaList() == null) ? new ArrayList()
				: Arrays.asList(f.getZonaList()));

		return params;

	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		ProcesoLOVEjecutarProcesosForm f = (ProcesoLOVEjecutarProcesosForm) this.formInterfaz;

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
		f.setFechaFacturacionD(DateUtil.convertStringToDate(f
				.getFechaFacturacion()));

		// recuperamos el tipo de Proceso LOVE
		String indicadorProceso = getRequest().getParameter("indicadorProceso");
		f.setIndicadorProceso(indicadorProceso);

		AjaxService ajax = (AjaxService) getBean("ajaxService");
		String codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		String codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
		this.siccRegionList = ajax.getRegionesByPaisMarcaCanal(
				pais.getCodigo(), codigoMarca, codigoCanal);
		String[] values = { getSiccRegionList()[0].getValue() };
		this.siccZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(
				pais.getCodigo(), codigoMarca, codigoCanal, values, "N");

	}

	/**
	 * Metodo para obtener Lista de Zonas
	 * 
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		ProcesoLOVEjecutarProcesosForm f = (ProcesoLOVEjecutarProcesosForm) this.formInterfaz;
		String codigoMarca = f.getCodigoMarca();
		String codigoCanal = f.getCodigoCanal();
		String valor = (String) val.getNewValue();
		String[] value = { valor };
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.siccZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(
				pais.getCodigo(), codigoMarca, codigoCanal, value, "N");

	}

	public void loadZonasbyMarca(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		ProcesoLOVEjecutarProcesosForm f = (ProcesoLOVEjecutarProcesosForm) this.formInterfaz;
		String codigoCanal = f.getCodigoCanal();
		String marca = (String) val.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.siccRegionList=ajax.getRegionesByPaisMarcaCanal(
				pais.getCodigo(), marca, codigoCanal);
		String codregion0=getSiccRegionList()[0].getValue();
		String[] valor ={codregion0};
		this.siccZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(
				pais.getCodigo(), marca, codigoCanal, valor, "N");

	}

	public void loadZonasbyCanal(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		ProcesoLOVEjecutarProcesosForm f = (ProcesoLOVEjecutarProcesosForm) this.formInterfaz;
		String marca = f.getCodigoMarca();
		String canal = (String) val.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.siccRegionList=ajax.getRegionesByPaisMarcaCanal(
				pais.getCodigo(), marca, canal);
		String codregion0=getSiccRegionList()[0].getValue();
		String[] valor ={codregion0};
		this.siccZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(
				pais.getCodigo(), marca, canal, valor, "N");

	}

	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList
	 *            the siccMarcaList to set
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
	 * @param siccCanalList
	 *            the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * @return the siccRegionList
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 *            the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the lOV_INDICADOR_PROCESO_GP4
	 */
	public String getLOV_INDICADOR_PROCESO_GP4() {
		return LOV_INDICADOR_PROCESO_GP4;
	}

	/**
	 * @param lOV_INDICADOR_PROCESO_GP4
	 *            the lOV_INDICADOR_PROCESO_GP4 to set
	 */
	public void setLOV_INDICADOR_PROCESO_GP4(String lOV_INDICADOR_PROCESO_GP4) {
		LOV_INDICADOR_PROCESO_GP4 = lOV_INDICADOR_PROCESO_GP4;
	}

	/**
	 * @return the lOV_INDICADOR_PROCESO_CIERRE_ZONA
	 */
	public String getLOV_INDICADOR_PROCESO_CIERRE_ZONA() {
		return LOV_INDICADOR_PROCESO_CIERRE_ZONA;
	}

	/**
	 * @param lOV_INDICADOR_PROCESO_CIERRE_ZONA
	 *            the lOV_INDICADOR_PROCESO_CIERRE_ZONA to set
	 */
	public void setLOV_INDICADOR_PROCESO_CIERRE_ZONA(
			String lOV_INDICADOR_PROCESO_CIERRE_ZONA) {
		LOV_INDICADOR_PROCESO_CIERRE_ZONA = lOV_INDICADOR_PROCESO_CIERRE_ZONA;
	}

	/**
	 * @return the lOV_INDICADOR_PROCESO_CIERRE_REGION
	 */
	public String getLOV_INDICADOR_PROCESO_CIERRE_REGION() {
		return LOV_INDICADOR_PROCESO_CIERRE_REGION;
	}

	/**
	 * @param lOV_INDICADOR_PROCESO_CIERRE_REGION
	 *            the lOV_INDICADOR_PROCESO_CIERRE_REGION to set
	 */
	public void setLOV_INDICADOR_PROCESO_CIERRE_REGION(
			String lOV_INDICADOR_PROCESO_CIERRE_REGION) {
		LOV_INDICADOR_PROCESO_CIERRE_REGION = lOV_INDICADOR_PROCESO_CIERRE_REGION;
	}

	/**
	 * @return the lOV_INDICADOR_PROCESO_CIERRE_CAMPANA
	 */
	public String getLOV_INDICADOR_PROCESO_CIERRE_CAMPANA() {
		return LOV_INDICADOR_PROCESO_CIERRE_CAMPANA;
	}

	/**
	 * @param lOV_INDICADOR_PROCESO_CIERRE_CAMPANA
	 *            the lOV_INDICADOR_PROCESO_CIERRE_CAMPANA to set
	 */
	public void setLOV_INDICADOR_PROCESO_CIERRE_CAMPANA(
			String lOV_INDICADOR_PROCESO_CIERRE_CAMPANA) {
		LOV_INDICADOR_PROCESO_CIERRE_CAMPANA = lOV_INDICADOR_PROCESO_CIERRE_CAMPANA;
	}

}
