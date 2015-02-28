package biz.belcorp.ssicc.web.spusicc.gen.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.fac.MantenimientoFACGenericoService;
import biz.belcorp.ssicc.service.spusicc.gen.ProcesoGENProcesarCierreService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spusicc.gen.form.ProcesoGENProcesarCierreRegionForm;
import biz.belcorp.ssicc.web.spusicc.gen.form.ProcesoGENProcesarGP1Form;
import biz.belcorp.ssicc.web.util.StringUtil;

@ManagedBean
@SessionScoped
public class ProcesoGENProcesarCierreRegionAction extends
		BaseInterfazAbstractAction {

	private static final long serialVersionUID = 6757519628037552419L;
	private List genInterfacesPaquete = new ArrayList();
	private List siccMarcaList = new ArrayList();
	private List siccCanalList = new ArrayList();
	private List siccRegionList = new ArrayList();
	private boolean opcionPeriodoFecha;
	private String indicadorSeleccionInterfaz;
	private List genInterfazPaquete = new ArrayList();
	private int totalProcesosSICC;
	
	
	
	

	public int getTotalProcesosSICC() {
		return totalProcesosSICC;
	}

	public void setTotalProcesosSICC(int totalProcesosSICC) {
		this.totalProcesosSICC = totalProcesosSICC;
	}

	public List getGenInterfazPaquete() {
		return genInterfazPaquete;
	}

	public void setGenInterfazPaquete(List genInterfazPaquete) {
		this.genInterfazPaquete = genInterfazPaquete;
	}

	public String getIndicadorSeleccionInterfaz() {
		return indicadorSeleccionInterfaz;
	}

	public void setIndicadorSeleccionInterfaz(String indicadorSeleccionInterfaz) {
		this.indicadorSeleccionInterfaz = indicadorSeleccionInterfaz;
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.action.BaseAbstractAction#setViewAttributes(javax
	 * .servlet.http.HttpServletRequest, org.apache.struts.action.ActionForm)
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		ProcesoGENProcesarCierreRegionForm f = (ProcesoGENProcesarCierreRegionForm) formInterfaz;
		f.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();

		Map params = new HashMap();
		params.clear();
		params.put("codigoISO", usuario.getIdioma().getCodigoISO());
		params.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());

		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		String actualizarRegiones = this.getParametrosPantalla().get(
				"actualizarRegiones");

		// Carga de los combos Marca y Canal
		siccMarcaList = interfazSiCCService.getMarcas();
		siccCanalList = interfazSiCCService.getCanalesByCodigoISO(usuario
				.getIdioma().getCodigoISO());

		Map criteria = new HashMap();
		criteria.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());

		if (!"1".equals(actualizarRegiones)) {
			criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																	// Campanha
																	// Activa
			criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																		// Campanha
																		// activa
																		// q se
																		// procesa
																		// actualmente

			MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			PedidoControlFacturacion controlFacturacion = service
					.getControlFacturacionById(criteria);

			// Carga de PeriodoProceso y Fecha Facturacion
			f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
			f.setFechaFacturacion(controlFacturacion.getFechaProceso());
			SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");

			f.setFechaFacturacionD(formatoDeFecha.parse(controlFacturacion
					.getFechaProceso()));
		}

		String codigoProcesoBatch = f.getCodigoProcesoBatch();

		ProcesoGENProcesarCierreService procesoService = (ProcesoGENProcesarCierreService) getBean("spusicc.procesoGENProcesarCierreService");

		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("fechaFacturacion", f.getFechaFacturacion());
		criteria.put("tipoCierre", Constants.CODIGO_TIPO_CIERRE_REGION);

		List regiones = procesoService.getRegionesACerrar(criteria);

		siccRegionList = regiones;

		List lista = new ArrayList();

		criteria.put("codigoSistema", "GEN");
		criteria.put("codigoInterfaz", f.getCodigoInterfaz());

		lista = interfazSiCCService.getListaProcesosGenCierreCampania(criteria);

		List listaProcesosTemp = procesoService
				.getProcesosCierreRegion(criteria);
		List listaProcesos = new ArrayList();
		Base base;
		for (int i = 0; i < listaProcesosTemp.size(); i++) {
			Map baseResult = (HashMap) listaProcesosTemp.get(i);

			base = new Base();
			base.setCodigo((String) baseResult.get("value"));
			base.setDescripcion((String) baseResult.get("label"));

			listaProcesos.add(base);
		}
		totalProcesosSICC=listaProcesos.size();
		// sesion.setAttribute("totalProcesosSICC", listaProcesos.size());
		listaProcesos.addAll(lista);
		
		
		genInterfacesPaquete=listaProcesos;
		String indicadorSeleccion=f.getIndicadorSeleccionInterfaces();
		f.setIndicadorSeleccionInicial(indicadorSeleccion);
		// sesion.setAttribute(Constants.GEN_INTERFACES_PAQUETE, listaProcesos);

		// String indicadorSeleccion =
		// (String)request.getSession().getAttribute(Constants.INDICADOR_SELECCION_INTERFAZ);
		// f.setIndicadorSeleccionInicial(indicadorSeleccion);
		//
		if (regiones.size() == 0) {
			f.setMostrarBotonProcesar(false);

			addInfo("Mensaje",
					getResourceMessage("procesoGENProcesarCierreRegionForm.msg.validacionRegionesAProcesar"));
		} else {

			for (int i = 0; i < regiones.size(); i++) {
				Map mapRegion = (Map) regiones.get(i);
				String oidRegion = mapRegion.get("value").toString();
				String descripcionRegion = mapRegion.get("label").toString();

				criteria.put("oidRegion", oidRegion);
				boolean validar = procesoService
						.existeZonasxRegionSinProcesar(criteria);

				if (!validar) {

					addInfo("Mensaje",
							getResourceMessage("procesoGENProcesarCierreRegionForm.msg.validacionZonasAbiertas"));
				}
			}

		}

		// String frecuenciaSGR = request.getParameter("frecuenciaSGR");
		// f.setFrecuenciaSGR(frecuenciaSGR);
		//
		// Variable que indica el tipo del Proceso, viaja como un parámetro más
		// a través de la url.
		// String tipoProceso = request.getParameter("tipoProceso");
		// sesion.setAttribute("tipoProceso", tipoProceso);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {

		params=new HashMap();
		ProcesoGENProcesarCierreRegionForm f = (ProcesoGENProcesarCierreRegionForm) form;
		MantenimientoFACGenericoService mantenimientoFACGenericoService = (MantenimientoFACGenericoService) getBean("spusicc.mantenimientoFACGenericoService");
		
		List regiones = siccRegionList;

		Long[] oidRegiones = new Long[regiones.size()];
		String[] codigoRegiones = new String[regiones.size()];

		for (int i = 0; i < regiones.size(); i++) {
			Map mapRegion = (Map) regiones.get(i);
			oidRegiones[i] = new Long(mapRegion.get("value").toString());
			codigoRegiones[i] = mantenimientoFACGenericoService
					.getCodigoRegionByOidRegion(Integer.valueOf(oidRegiones[i]
							.toString()));
		}

		Integer contCierreZona;

		if (Constants.NO.equals(f.getIndicadorEjecucionSICC())) {
			InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

			for (int i = 0; i < oidRegiones.length; i++) {
				contCierreZona = interfazSiCCService
						.getContCierreRegionByCodigoPeriodoOidRegion(
								(String) params.get("codigoPeriodo"),
								Integer.valueOf(oidRegiones[i].toString()));
				if (contCierreZona == 0) {

					String codigoRegionNoCerrada = mantenimientoFACGenericoService
							.getCodigoRegionByOidRegion(Integer
									.valueOf(oidRegiones[i].toString()));

					String parametrosMsg[] = { codigoRegionNoCerrada,
							(String) params.get("codigoPeriodo") };
					this.getResourceMessage("procesoGENProcesarCierreRegionForm.msg.validacionCierreRegion");
					String mensaje =this.getResourceMessage("procesoGENProcesarCierreRegionForm.msg.validacionCierreRegion");
								
					throw new Exception(mensaje);
				}
			}
		}

		String tipoProceso ="";
		Usuario usuario= mPantallaPrincipalBean.getCurrentUser();
		
		params.put("tipoProceso", tipoProceso);
		params.put("regionList", oidRegiones);
		params.put("codigoRegionList", codigoRegiones);
		params.put("codigoISO",usuario.getIdioma().getCodigoISO());
        params.put("codigoPais",mPantallaPrincipalBean.getCurrentCountry().getCodigo());
        params.put("codigoSistema", "GEN");
        params.put("codigoInterfaz", f.getCodigoInterfaz());
        if (codigoRegiones != null && codigoRegiones.length > 0) {
			params.put("regionListAux",
					StringUtil.obtieneListaCodigos(codigoRegiones, "", ""));
		}
		
		String indicadorSeleccion = f.getIndicadorSeleccionInterfaces();
		if(f.getListaSeleccionadas().size()!=f.getListaInterfaces().length){
			indicadorSeleccion="S";
		}
		
		params.put(Constants.INDICADOR_SELECCION_INTERFAZ, indicadorSeleccion);

		params.put("frecuenciaSGR", f.getFrecuenciaSGR());

		return params;
	}

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		ProcesoGENProcesarCierreRegionForm form = new ProcesoGENProcesarCierreRegionForm();
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
	 * @param genInterfacesPaquete
	 *            the genInterfacesPaquete to set
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
	 * @param opcionPeriodoFecha
	 *            the opcionPeriodoFecha to set
	 */
	public void setOpcionPeriodoFecha(boolean opcionPeriodoFecha) {
		this.opcionPeriodoFecha = opcionPeriodoFecha;
	}

}