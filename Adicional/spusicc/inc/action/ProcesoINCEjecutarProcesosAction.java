package biz.belcorp.ssicc.web.spusicc.inc.action;

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
import biz.belcorp.ssicc.service.spusicc.fac.MantenimientoFACGenericoService;
import biz.belcorp.ssicc.service.spusicc.gen.ProcesoGENProcesarCierreService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.util.StringUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spusicc.inc.form.ProcesoINCEjecutarProcesosForm;

@SessionScoped
@ManagedBean
public class ProcesoINCEjecutarProcesosAction extends
		BaseInterfazAbstractAction {

	private static final long serialVersionUID = 4515418264534705843L;
	private List siccMarcaList;
	private List siccCanalList;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private List incInterfacesPaquete;
	private String LOV_INDICADOR_PROCESO_GP3 = Constants.LOV_INDICADOR_PROCESO_GP3;
	private String LOV_INDICADOR_PROCESO_GP4 = Constants.LOV_INDICADOR_PROCESO_GP4;
	private String LOV_INDICADOR_PROCESO_CIERRE_ZONA = Constants.LOV_INDICADOR_PROCESO_CIERRE_ZONA;
	private String LOV_INDICADOR_PROCESO_CIERRE_REGION = Constants.LOV_INDICADOR_PROCESO_CIERRE_REGION;
	private String LOV_INDICADOR_PROCESO_CIERRE_CAMPANA = Constants.LOV_INDICADOR_PROCESO_CIERRE_CAMPANA;
	private String fechaFacturacion;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		ProcesoINCEjecutarProcesosForm form = new ProcesoINCEjecutarProcesosForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		ProcesoINCEjecutarProcesosForm f = (ProcesoINCEjecutarProcesosForm) this.formInterfaz;

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
		fechaFacturacion=controlFacturacion.getFechaProceso();
		f.setFechaFacturacionD(DateUtil.convertStringToDate(fechaFacturacion));

		// sesion.removeAttribute("codigoZonaElegida");

		/* INI SA PER-SiCC-2012-0900 */
		// recuperamos el tipo de Proceso LOVE
		String indicadorProceso = getRequest().getParameter("indicadorProceso");
		f.setIndicadorProceso(indicadorProceso);

		ProcesoGENProcesarCierreService procesoService = (ProcesoGENProcesarCierreService) getBean("spusicc.procesoGENProcesarCierreService");

		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("fechaFacturacion", f.getFechaFacturacion());
		criteria.put("tipoCierre", Constants.CODIGO_TIPO_CIERRE_ZONA);

		// List regiones = new ArrayList();
		// if(f.getIndicadorProceso().equals("Z")) {
		// regiones = procesoService.getRegionesACerrar(criteria);
		// }
		// this.siccRegionList=regiones; COMENTADO PORQUE EN EL SSICC CARGA EN
		// EL JSP "getRegionesByPaisMarcaCanal"

		AjaxService ajax = (AjaxService) getBean("ajaxService");
		String codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		String codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
		this.siccRegionList = ajax.getRegionesByPaisMarcaCanal(
				pais.getCodigo(), codigoMarca, codigoCanal);
		this.siccZonaList = ajax.getZonasACerrar(f.getCodigoPeriodo(), f.getFechaFacturacion(), getSiccRegionList()[0].getValue());
		/* FIN SA PER-SiCC-2012-0900 */

		List lista = new ArrayList();
		/* INI JJ PER-SiCC-2012-0637 */
		if (indicadorProceso.equals("G") || indicadorProceso.equals("Z")
				|| indicadorProceso.equals("R") || indicadorProceso.equals("P")
				|| indicadorProceso.equals("GP3")) {
			/* INI JJ PER-SiCC-2012-0637 */
			criteria.put("codigoSistema", "INC");
			criteria.put("codigoInterfaz", f.getCodigoInterfaz());

			lista = interfazSiCCService.getListaProcesosIncentivos(criteria);
			this.incInterfacesPaquete = lista;
		}
	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		ProcesoINCEjecutarProcesosForm f = (ProcesoINCEjecutarProcesosForm) this.formInterfaz;
		f.setFechaFacturacion(DateUtil.convertDateToString(f
				.getFechaFacturacionD()));
		params = super.prepareParamsBeforeExecute(params, form);

		MantenimientoFACGenericoService mantenimientoFACGenericoService = (MantenimientoFACGenericoService) getBean("spusicc.mantenimientoFACGenericoService");

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		params.put("zonaList", (f.getZonaList() == null) ? new ArrayList()
				: Arrays.asList(f.getZonaList()));

		/* INI SA PER-SiCC-2012-0900 */
		if (f.getIndicadorProceso().equals("Z")) {
			String[] codigoZonas = null;
			if (f.getZonaList() == null) {
				codigoZonas = new String[0];
				f.setZonaList(codigoZonas);
			} else {
				codigoZonas = new String[f.getZonaList().length];
			}

			for (int i = 0; i < f.getZonaList().length; i++) {
				String oidZona = f.getZonaList()[i];

				codigoZonas[i] = mantenimientoFACGenericoService
						.getCodigoZonaByOidZona(Integer.valueOf(oidZona));

				if (i == 0) {
					String codigoRegion = mantenimientoFACGenericoService
							.getCodigoRegion(codigoZonas[i]);
					params.put("codigoRegion", codigoRegion);
				}
			}

			if (f.getZonaList() != null && f.getZonaList().length > 0)
				params.put("zonaListAux",
						StringUtil.obtieneListaCodigos(codigoZonas, "", ""));

		}
		/* FIN SA PER-SiCC-2012-0900 */

		// sesion.setAttribute("codigoRegionElegido", f.getCodigoRegion());
		// sesion.setAttribute("codigoZonaElegida", f.getCodigoZona());
		// sesion.setAttribute("codigoUsuario", usuario.getLogin());

		String servidor = getRequest().getServerName();
		params.put("nombreServidor", servidor);

		return params;
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

		ProcesoINCEjecutarProcesosForm f = (ProcesoINCEjecutarProcesosForm) this.formInterfaz;
		String valor = (String) val.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		this.siccZonaList = ajax.getZonasACerrar(
				f.getCodigoPeriodo(), fechaFacturacion, valor);
//		String codigoPeriodo, String fechaFacturacion, String codigoRegion);

	}
	
	/**
	 * Metodo para obtener Lista de Zonas por Codigo Periodo
	 * 
	 * @param val
	 */
	public void loadZonasbyPeriodo(String valor) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		ProcesoINCEjecutarProcesosForm f = (ProcesoINCEjecutarProcesosForm) this.formInterfaz;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		this.siccZonaList = ajax.getZonasACerrar(
				valor, fechaFacturacion, f.getCodigoRegion());

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
	 * @return the incInterfacesPaquete
	 */
	public List getIncInterfacesPaquete() {
		return incInterfacesPaquete;
	}

	/**
	 * @param incInterfacesPaquete
	 *            the incInterfacesPaquete to set
	 */
	public void setIncInterfacesPaquete(List incInterfacesPaquete) {
		this.incInterfacesPaquete = incInterfacesPaquete;
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
	 * @return the lOV_INDICADOR_PROCESO_GP3
	 */
	public String getLOV_INDICADOR_PROCESO_GP3() {
		return LOV_INDICADOR_PROCESO_GP3;
	}

	/**
	 * @param lOV_INDICADOR_PROCESO_GP3
	 *            the lOV_INDICADOR_PROCESO_GP3 to set
	 */
	public void setLOV_INDICADOR_PROCESO_GP3(String lOV_INDICADOR_PROCESO_GP3) {
		LOV_INDICADOR_PROCESO_GP3 = lOV_INDICADOR_PROCESO_GP3;
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

	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}

	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}
	
	

}
