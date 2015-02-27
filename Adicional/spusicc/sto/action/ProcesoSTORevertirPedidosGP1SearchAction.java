package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.dao.DataAccessException;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.sto.model.ConsultaPedidosGP1;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitado;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitadoPK;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.MantenimientoPRECambioCodigoVentaService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoAtencionesService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoOCRActualizaPedidosDeudaForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOControlFnePorcentajeSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.ProcesoSTOConsultaClientesForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.ProcesoSTORevertirPedidosGP1Form;

@ManagedBean
@SessionScoped
public class ProcesoSTORevertirPedidosGP1SearchAction extends
		BaseProcesoAbstractAction {

	private LabelValue[] siccZonaList = {};
	private List siccRegionList = new ArrayList();
	private int longitudCampoClientes;
	private List stoEliminarPedidosClientesList = new ArrayList();
	private List stoResumenClientesList = new ArrayList();
	private List stoListaPedidos = new ArrayList();
	private String attachment = "";
	
	
	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public int getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	public void setLongitudCampoClientes(int longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}

	public List getStoEliminarPedidosClientesList() {
		return stoEliminarPedidosClientesList;
	}

	public void setStoEliminarPedidosClientesList(
			List stoEliminarPedidosClientesList) {
		this.stoEliminarPedidosClientesList = stoEliminarPedidosClientesList;
	}

	public List getStoResumenClientesList() {
		return stoResumenClientesList;
	}

	public void setStoResumenClientesList(List stoResumenClientesList) {
		this.stoResumenClientesList = stoResumenClientesList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public List getStoListaPedidos() {
		return stoListaPedidos;
	}

	public void setStoListaPedidos(List stoListaPedidos) {
		this.stoListaPedidos = stoListaPedidos;
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5014777774491437876L;

	@Override
	protected List setFindAttributes() throws Exception {
		log.debug("setFindAttributes.");
		Map params = null;
		String codigoPais = mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo();

		ProcesoSTOService procesoSTOService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");
		TipoDocumentoDigitadoPK tipoDocumentoDigitadoPK = new TipoDocumentoDigitadoPK(
				codigoPais, Constants.STO_TIPO_DOCUMENTO_OCC);
		TipoDocumentoDigitado tipoDocumentoDigitado = procesoSTOService
				.getTipoDocumentoDigitado(tipoDocumentoDigitadoPK);
		ProcesoSTORevertirPedidosGP1Form f = (ProcesoSTORevertirPedidosGP1Form) formProceso;

		f.setDescripcionDocumento(tipoDocumentoDigitado.getDesTipoDocu());

		f.setFechaFinProgramadaFacturacion(DateUtil.getDate(f.getFechaInicioProgramadaFacturacionD()));
		f.setFechaInicioProgramadaFacturacion(DateUtil.getDate(f.getFechaFinProgramadaFacturacionD()));
		params = getCriteria(f);

		List consultaPedidosList = procesoSTOService
				.getConsultaPedidosGP1List(params);
		stoListaPedidos = consultaPedidosList;

		setFilterSearch(f);
		f.setCargaCombo(Constants.NO);
		syncZona(f);

		return consultaPedidosList;
	}

	private void syncZona(ProcesoSTORevertirPedidosGP1Form cabecera) {

		String[] codigoRegion = cabecera.getRegionList();
		if (cabecera.getRegionList() != null) {
			LabelValue[] resultZona = this.getZonaByRegion(
					cabecera.getCodigoPais(), codigoRegion);
			siccZonaList = null;
			siccZonaList = resultZona;

		}
	}
	public void buscarZonasRegion(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			ProcesoSTORevertirPedidosGP1Form f = (ProcesoSTORevertirPedidosGP1Form) this.formProceso;
			String regionListado[] = (String[]) val.getNewValue();
			
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			LabelValue[] resultZona = this.getZonaByRegion(
					mPantallaPrincipalBean.getCurrentCountry().getCodigo(), regionListado);
			siccZonaList=resultZona;

		} else {
			siccZonaList = null;
		}
	}

	protected Map getCriteria(ProcesoSTORevertirPedidosGP1Form f) {
		Map params = new HashMap();
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();

		String codigoPeriodo = f.getCodigoPeriodo();
		String fechaInicioProgramadaFacturacion = f
				.getFechaInicioProgramadaFacturacion();
		String fechaFinProgramadaFacturacion = f
				.getFechaFinProgramadaFacturacion();
		String codigoCliente = f.getCodigoCliente();

		if (f.getRegionList() != null) {
			if (f.getRegionList().length == 1) {
				if (f.getRegionList()[0] == null
						|| f.getRegionList()[0].compareToIgnoreCase("") == 0) {
					f.setRegionList(null);
				}
			}
		}

		if (f.getZonaList() != null) {
			if (f.getZonaList().length == 1) {
				if (f.getZonaList()[0] == null
						|| f.getZonaList()[0].compareToIgnoreCase("") == 0) {
					f.setZonaList(null);
				}
			}
		}

		String grupoProceso = f.getGrupoProceso();
		String estadoGP1 = "";
		String estadoGP2 = "";
		String estadoGP3 = "";

		if (grupoProceso.equals(Constants.NUMERO_UNO))
			estadoGP1 = Constants.SI;
		if (grupoProceso.equals(Constants.NUMERO_DOS))
			estadoGP2 = Constants.SI;
		if (grupoProceso.equals("3"))
			estadoGP3 = Constants.SI;

		try {
			params = BeanUtils.describe(f);
			params.put("codigoPais", pais.getCodigo());
			params.put("tipoDocumento", f.getTipoDocumento());
			params.put("codigoPeriodo", codigoPeriodo);
			params.put("fechaInicioProgramadaFacturacion",
					fechaInicioProgramadaFacturacion);
			params.put("fechaFinProgramadaFacturacion",
					fechaFinProgramadaFacturacion);
			params.put("codigoCliente", codigoCliente);
			params.put("estadoGP1", estadoGP1);
			params.put("estadoGP2", estadoGP2);
			params.put("estadoGP3", estadoGP3);
			params.put(
					"regionList",
					(f.getRegionList() == null) ? new ArrayList() : Arrays
							.asList(f.getRegionList()));
			params.put("zonaList", (f.getZonaList() == null) ? new ArrayList()
					: Arrays.asList(f.getZonaList()));

			// ----------------------------------------------
			String[] arrlistaClientes = new String[0];
			List clienteList = new ArrayList();
			Long longitudPais = pais.getLongitudCodigoCliente();
			String codigoCliente2 = f.getCodigoCliente();
			String codigoCliente3 = f.getCodigoCliente();
			List listaClientes = stoEliminarPedidosClientesList;

			Map map = new HashMap();

			if (listaClientes != null) {
				if (listaClientes.size() > 0) {
					for (int i = 0; i < listaClientes.size(); i++) {
						/*
						 * LabelValue bean = (LabelValue) listaClientes.get(i);
						 * param.put("codigoCliente",
						 * StringUtils.leftPad(bean.getLabel(),
						 * longitudPais.intValue(), '0'));
						 */
						map = (Map) listaClientes.get(i);
						codigoCliente3 = (String) map.get("codigoCliente");

						clienteList.add(codigoCliente3);
					}
				}
			}

			// Si es cargado por la caja de texto
			if (codigoCliente2 != null && codigoCliente2.length() > 0)
				arrlistaClientes = codigoCliente2.split(",+");

			for (int i = 0; i < arrlistaClientes.length; i++) {
				codigoCliente2 = StringUtils.leftPad(arrlistaClientes[i],
						longitudPais.intValue(), '0');

				clienteList.add(codigoCliente2);
			}

			params.put("clienteList", (clienteList == null) ? new ArrayList()
					: clienteList);
			// ----------------------------------------------

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return params;
	}

	public LabelValue[] getZonaByRegion(final String codigoPais,
			final String[] codigoRegion) {

		LabelValue[] result = null;
		String condicionTodos = Constants.NO;
		InterfazSiCCService interfazSiCC = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		if (StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
			criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
			criteria.put("codigoRegion", codigoRegion);
			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");

				List zonasList = interfazSiCC.getLista(
						"getZonasMultipleByPaisMarcaCanalRegion", criteria);
				if (zonasList != null && zonasList.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[zonasList.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < zonasList.size(); i++) {
							Base periodo = (Base) zonasList.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[zonasList.size()];
						for (int i = 0; i < zonasList.size(); i++) {
							Base concurso = (Base) zonasList.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoSTORevertirPedidosGP1Form e = new ProcesoSTORevertirPedidosGP1Form();
		return e;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void clearFile() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'clearFile' method");
		}

		ProcesoSTORevertirPedidosGP1Form f = (ProcesoSTORevertirPedidosGP1Form)formProceso;
		f.setTipoDocumento(Constants.STO_TIPO_DOCUMENTO_OCC);				

		
		stoEliminarPedidosClientesList.clear();
		
		stoResumenClientesList.clear();


	}
	
	
	
	public void handleFileUpload(FileUploadEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		ProcesoSTORevertirPedidosGP1Form f = (ProcesoSTORevertirPedidosGP1Form) this.formProceso;
		if (event != null) {
			// f.setArchivo(event.getFile());
			f.setClienteFile(event.getFile());
			this.setAttachment(event.getFile().getFileName());
			this.uploadArchivo();
		}
	}

	public void uploadArchivo() throws Exception {

		ProcesoSTORevertirPedidosGP1Form f = (ProcesoSTORevertirPedidosGP1Form)formProceso;
		String codigoPais = mPantallaPrincipalBean.getCurrentCountry().getCodigo();

		f.setTipoDocumento(Constants.STO_TIPO_DOCUMENTO_OCC);
		ProcesoSTOService  procesoSTOService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");
		MantenimientoRECIngresoAtencionesService service = (MantenimientoRECIngresoAtencionesService)getBean("spusicc.mantenimientoRECIngresoAtencionesService");
		GenericoService genericoService = (GenericoService) getBean("genericoService");
		
		TipoDocumentoDigitadoPK tipoDocumentoDigitadoPK = new TipoDocumentoDigitadoPK(codigoPais,Constants.STO_TIPO_DOCUMENTO_OCC);
		TipoDocumentoDigitado  tipoDocumentoDigitado =procesoSTOService.getTipoDocumentoDigitado(tipoDocumentoDigitadoPK);

		f.setTipoDocumento(Constants.STO_TIPO_DOCUMENTO_OCC);
		f.setDescripcionDocumento(tipoDocumentoDigitado.getDesTipoDocu());

		List listaClientes = new ArrayList();
		
		String indValidaCodConsultoraDocIdentidad = null;
		
		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais( mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		parametroPais.setCodigoSistema("STO");
		parametroPais.setNombreParametro("indValidaCodConsultoraDocIdentidad");
		
		List lstParametros = genericoService.getParametrosPais(parametroPais);
		
		if(lstParametros != null && lstParametros.size() > 0){
			ParametroPais ps = (ParametroPais)lstParametros.get(0);
			indValidaCodConsultoraDocIdentidad = ps.getValorParametro();
		}
		
		UploadedFile archivo = f.getClienteFile();
		Map criteria = new HashMap();
		InputStream is = archivo.getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		Long longitudPais=( mPantallaPrincipalBean.getCurrentCountry()).getLongitudCodigoCliente();
		String linea = "";
		String codigoConsultora = "";
		int cont = 0;
		int numRegistros = 0;

		while (true) {
			linea = br.readLine();
			if (linea == null)
				break;

			codigoConsultora = StringUtils.leftPad(linea.trim(), longitudPais.intValue(), '0');
			criteria.put("codigoConsultora",codigoConsultora);
			LabelValue bean = new LabelValue(codigoConsultora, service.getCodigoConsultora(criteria));
			
			if(bean.getValue() == null && StringUtils.equals(indValidaCodConsultoraDocIdentidad, Constants.SI)){
				criteria.put("documentoIdentidad", codigoConsultora);
				String codigoConsultoraPorDocIden = service.getCodigoConsultoraPorDocumentoIdentidad(criteria);
				
				if(codigoConsultoraPorDocIden == null){
					bean = new LabelValue(codigoConsultora, service.getCodigoConsultoraPorDocumentoIdentidad(criteria));
				}else{
					bean = new LabelValue(codigoConsultoraPorDocIden, service.getCodigoConsultoraPorDocumentoIdentidad(criteria));
				}
			}
			
			listaClientes.add(bean);

			if(bean.getValue() == null)
				cont++;

			numRegistros++;
		}

		//Se inserta en la tabla temporal
		String oidCarga = service.getOidCargaCliente();
		criteria.put("oid", oidCarga);
		service.insertaClienteFile(listaClientes, criteria);

		//Se obtiene la lista de la tabla temporal
		List list = new ArrayList();
		list = service.getCargaClienteList(criteria);

		f.setCodigosErradosFile("");

		if(cont != 0)
			f.setCodigosErradosFile("Existe(n) "+cont+" codigo(s) errado(s)");

		criteria.put("numRegistros",  numRegistros);
		List list2 = new ArrayList();
		list2 = service.getResumenCargaClienteList(criteria);
		stoEliminarPedidosClientesList.clear();
		stoEliminarPedidosClientesList=list;
		stoResumenClientesList.clear();
		stoResumenClientesList=list2;
		

		

	}
	private void setFilterSearch(ProcesoSTORevertirPedidosGP1Form f) {

		f.setSelectedItemsSearch(f.getSelectedItems());
		f.setTipoDocumentoSearch(f.getTipoDocumento());
		f.setCodigoPeriodoSearch(f.getCodigoPeriodo());
		f.setFechaInicioProgramadaFacturacionSearch(f
				.getFechaInicioProgramadaFacturacion());
		f.setFechaFinProgramadaFacturacionSearch(f
				.getFechaFinProgramadaFacturacion());
		f.setCodigoClienteSearch(f.getCodigoCliente());
		f.setAccionSearch(f.getAccion());
		/*
		 * Important request.getSession() .setAttribute("regionListSearch",
		 * f.getRegionList());
		 * request.getSession().setAttribute("zonaListSearch", f.getZonaList());
		 */
	}

	private List getListaReversion(
			boolean isOnlySelectedItems) throws Exception {


		List listaEliminacion =stoListaPedidos;

		ProcesoSTORevertirPedidosGP1Form f = (ProcesoSTORevertirPedidosGP1Form) formProceso;

		if (!isOnlySelectedItems)
			return listaEliminacion;
		else {

			List lista = new ArrayList();
			for (int j = 0; j < f.getSelectedItems().length; j++) {

				int posicion = devuelvePosicion(f.getSelectedItems()[j]);
				posicion = posicion - 1;
				ConsultaPedidosGP1 pedido = (ConsultaPedidosGP1) listaEliminacion
						.get(posicion);
				lista.add(pedido);

			}
			return lista;
		}

	}
	
	public int devuelvePosicion(String posicion) {
		int posi = 0;
		int aux = posicion.indexOf('|');
		String id2 = posicion.substring(0, aux);
		posi = Integer.parseInt(id2);

		return posi;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executing action : setViewAttributes.");

		this.mostrarBotonBuscar=true;
		this.mostrarBotonExecute=false;
		String codigoPais = mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo();

		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");

		// Seteando el Tipo de documento
		ProcesoSTOService procesoSTOService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");
		TipoDocumentoDigitadoPK tipoDocumentoDigitadoPK = new TipoDocumentoDigitadoPK(
				codigoPais, Constants.STO_TIPO_DOCUMENTO_OCC);
		TipoDocumentoDigitado tipoDocumentoDigitado = procesoSTOService
				.getTipoDocumentoDigitado(tipoDocumentoDigitadoPK);

		ProcesoSTORevertirPedidosGP1Form f = (ProcesoSTORevertirPedidosGP1Form) formProceso;

		f.setTipoDocumento(Constants.STO_TIPO_DOCUMENTO_OCC);
		f.setDescripcionDocumento(tipoDocumentoDigitado.getDesTipoDocu());

		// important
		// longitudCampoClientes=clienteService.getTamanhoNumeroCliente(codigoPais)

		siccZonaList = null;
		siccRegionList.clear();

		// request.getSession().removeAttribute("regionListSearch");
		// request.getSession().removeAttribute("zonaListSearch");

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoUsuario", mPantallaPrincipalBean.getCurrentUser()
				.getLogin());

		siccRegionList = reporteService.getListaGenerico("getRegionesByPais",
				criteria);

		stoListaPedidos = new ArrayList();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaInicio = new Date(System.currentTimeMillis());

		f.setFechaInicioProgramadaFacturacionD((fechaInicio));
		f.setFechaFinProgramadaFacturacionD((fechaInicio));
		f.setAccion("");
		f.setRegionList(new String[1]);
		f.setZonaList(new String[1]);
		f.setRegionListSearch(new String[1]);
		f.setZonaListSearch(new String[1]);
		f.setCargaCombo(Constants.SI);
		f.setCodigoPeriodo(service.getPeriodoActivo(criteria));

		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");

		longitudCampoClientes = clienteService
				.getTamanhoNumeroCliente(codigoPais);
		stoEliminarPedidosClientesList.clear();
		stoEliminarPedidosClientesList = new ArrayList();

		stoResumenClientesList.clear();

		// topes
		String lineaDefecto = ajaxService.getNumeroRegistrosSTO(codigoPais,
				Constants.STO_TIPO_DOCUMENTO_OCC, "1");
		String lineaMaxima = ajaxService.getNumeroRegistrosSTO(codigoPais,
				Constants.STO_TIPO_DOCUMENTO_OCC, "2");
		f.setLineaDefecto(lineaDefecto);
		f.setLineaMaxima(lineaMaxima);

	}

}
