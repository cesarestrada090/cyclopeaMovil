package biz.belcorp.ssicc.web.spusicc.inc.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.inc.ProcesoINCGenerarReporteIncentivosService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.inc.form.ProcesoINCGenerarReporteIncentivosForm;

@ManagedBean
@SessionScoped
public class ProcesoINCGenerarReporteIncentivosAction extends
		BaseProcesoAbstractAction {

	private static final long serialVersionUID = 7907351865030816486L;
	private String codigoPais;
	private List siccMarcaList;
	private List siccCanalList;
	private List incTipoCierreList;

	/**
	 * Retorna una lista con los tipos de comision Recuperacion y Actividad
	 * 
	 * @param request
	 * @return
	 */
	private List loadProcesos() {
		List resultado = new ArrayList();
		Base[] mes = new Base[4];
		
		String tipoDiario = this.getResourceMessage("procesoINCGenerarReporteIncentivosForm.tipoDiario");
		String tipoRegion = this.getResourceMessage("procesoINCGenerarReporteIncentivosForm.tipoRegion");

		String tipoZona = this.getResourceMessage("procesoINCGenerarReporteIncentivosForm.tipoZona");
		String tipoCampana = this.getResourceMessage("procesoINCGenerarReporteIncentivosForm.tipoCampana");

		mes[0] = new Base();
		mes[0].setCodigo(Constants.INC_TIPO_CIERRE_DIARIO);// RECUPERACION
		mes[0].setDescripcion(tipoDiario);
		resultado.add(mes[0]);

		mes[1] = new Base();
		mes[1].setCodigo(Constants.INC_TIPO_CIERRE_REGION);
		mes[1].setDescripcion(tipoRegion);
		resultado.add(mes[1]);

		mes[2] = new Base();
		mes[2].setCodigo(Constants.INC_TIPO_CIERRE_ZONA);
		mes[2].setDescripcion(tipoZona);
		resultado.add(mes[2]);

		mes[3] = new Base();
		mes[3].setCodigo(Constants.INC_TIPO_CIERRE_CAMPANA);
		mes[3].setDescripcion(tipoCampana);
		resultado.add(mes[3]);
		return resultado;
	}

	/**
	 * Retorna el objeto del periodo de facturacion
	 * 
	 * @param request
	 * @return
	 */
	private PedidoControlFacturacion getPedidoControlFacturacion() {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																// Campanha
																// Activa,Estado
																// Abierto
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																	// Campanha
																	// activa q
																	// se
																	// procesa
																	// actualmente
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteria);
		return controlFacturacion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.action.BaseAbstractAction#getExecuteForward()
	 */
	protected String getExecuteForward() {
		ReporteService reporteService = (ReporteService) this
				.getBean("scsicc.reporteService");
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("nombreReporte", "reporteINCPremiosDespachadosPDF");
		Map paramReporte = reporteService.getParametrosReporte(criteria);

		if (paramReporte != null)
			return "reporte";
		return "view";
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoINCGenerarReporteIncentivosForm form  = new ProcesoINCGenerarReporteIncentivosForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		ProcesoINCGenerarReporteIncentivosForm f = (ProcesoINCGenerarReporteIncentivosForm) this.formProceso;
		f.setFechaProceso(DateUtil.convertDateToString(f.getFechaProcesoD()));
		Usuario usuario = (Usuario) params.get("usuario");
		params.put("login", usuario.getLogin());

		Map map = new HashMap();
		map.put("codigoPais", params.get("codigoPais"));
		map.put("codigoCanal", params.get("codigoCanal"));
		map.put("codigoMarca", params.get("codigoMarca"));
		map.put("tipoCierre", params.get("tipoCierre"));
		map.put("codigoPeriodo", params.get("codigoPeriodo"));
		map.put("fechaProceso", params.get("fechaProceso"));
		map.put("login", params.get("login"));

		log.debug("Los parametros del Generar en el executeProcess son: "
				+ map.toString());

		ProcesoINCGenerarReporteIncentivosService service = (ProcesoINCGenerarReporteIncentivosService) getBean("spusicc.procesoINCGenerarReporteIncentivosService");
		service.executeGenerarReporteIncentivos(map);
		f.setNumeroLote((String) map.get("numeroLote"));
		log.debug("numero lote " + f.getNumeroLote());
		codigoPais = f.getCodigoPais();
		params.put("numeroLote", f.getNumeroLote());
		// mandamos en session el numero de lote para luego ser recuperado en el
		// envio del reporte x mail , para su ridereccion
		// correspondiente
//		request.getSession().setAttribute("numeroLoteReporteDespachoInc",
//				f.getNumeroLote());

		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub

		// Obtenemos los beans b�sicos de la sesi�n
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		ProcesoINCGenerarReporteIncentivosForm f = (ProcesoINCGenerarReporteIncentivosForm) this.formProceso;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		Map params = new HashMap();
		params.put("codigoISO", usuario.getIdioma().getCodigoISO());
		params.put("codigoPais", pais.getCodigo());

		siccMarcaList = service.getMarcas();
		siccCanalList =  service
				.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		/* Tipos de CIERRE */
		List resultado = loadProcesos();
		incTipoCierreList = resultado;
		/* obteniendo periodo actual */
		PedidoControlFacturacion controlFacturacion = getPedidoControlFacturacion();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		f.setFechaProceso(sdf.format(new Date(System.currentTimeMillis())));
		f.setNumeroLote("");
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setFechaProcesoD(DateUtil.convertStringToDate(f.getFechaProceso()));
		f.setTipoCierre(Constants.INC_TIPO_CIERRE_DIARIO);
		codigoPais = f.getCodigoPais();
//		request.getSession().removeAttribute("numeroLoteReporteDespachoInc");
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		
		//this.tipoCierre = codigoPeriodo = "";
	
		
		
		
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
	 * @return the incTipoCierreList
	 */
	public List getIncTipoCierreList() {
		return incTipoCierreList;
	}

	/**
	 * @param incTipoCierreList the incTipoCierreList to set
	 */
	public void setIncTipoCierreList(List incTipoCierreList) {
		this.incTipoCierreList = incTipoCierreList;
	}
}