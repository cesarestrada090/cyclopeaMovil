package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRReemplazosService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoAtencionesService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBloqueoControlService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.form.SistemaForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.form.ProcesoMAEClasificacionClientesForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOControlFnePorcentajeForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOControlFnePorcentajeSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOExcepcionValidaDeudaSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOIngresoCuponForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOLimiteVentaFocalizadoConsejeraSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoSTOControlFnePorcentajeSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private List stoTipoClienteList = new ArrayList();
	private LabelValue[] stoSubtipoClienteList = {};
	private LabelValue[] stoTipoClasificacionList = {};
	private LabelValue[] stoClasificacionList = {};
	private List stoRegionList = new ArrayList();
	private LabelValue[] stoZonaList = {};
	private List stoBloqueoControlList = new ArrayList();
	private List stoLevantamientoErroresClientesList = new ArrayList();
	private List stoTipoDocumentoSpvd = new ArrayList();
	private List stoControlFNEPorcentajeList = new ArrayList();
	private String attachment = "";
	private String attachmentForm = "";
	private Object[] columnasSeleccionadas;
	
	
	public Object[] getColumnasSeleccionadas() {
		return columnasSeleccionadas;
	}

	public void setColumnasSeleccionadas(Object[] columnasSeleccionadas) {
		this.columnasSeleccionadas = columnasSeleccionadas;
	}

	public String getAttachmentForm() {
		return attachmentForm;
	}

	public void setAttachmentForm(String attachmentForm) {
		this.attachmentForm = attachmentForm;
	}

	public List getStoControlFNEPorcentajeList() {
		return stoControlFNEPorcentajeList;
	}

	public void setStoControlFNEPorcentajeList(List stoControlFNEPorcentajeList) {
		this.stoControlFNEPorcentajeList = stoControlFNEPorcentajeList;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public void handleFileUploadSearch(FileUploadEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		MantenimientoSTOControlFnePorcentajeSearchForm f = (MantenimientoSTOControlFnePorcentajeSearchForm) this.formBusqueda;
		if (event != null) {
			// f.setArchivo(event.getFile());
			f.setClienteFile(event.getFile());
			this.setAttachment(event.getFile().getFileName());
			this.uploadArchivoSearch();
		}
	}

	public void handleFileUploadForm(FileUploadEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		MantenimientoSTOControlFnePorcentajeForm f = (MantenimientoSTOControlFnePorcentajeForm) this.formMantenimiento;
		if (event != null) {
			// f.setArchivo(event.getFile());
			f.setClienteFile(event.getFile());
			this.setAttachmentForm(event.getFile().getFileName());
			this.uploadArchivoForm();
		}
	}

	public void uploadArchivoSearch() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'load Clientes from file' method");
		}
		MantenimientoSTOControlFnePorcentajeSearchForm f = (MantenimientoSTOControlFnePorcentajeSearchForm) formBusqueda;
		List listaClientes = new ArrayList();
		UploadedFile archivo = f.getClienteFile();
		InputStream is = archivo.getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String linea = "";
		while (true) {
			linea = br.readLine();
			if (linea == null)
				break;
			LabelValue bean = new LabelValue(linea.trim(), linea.trim());
			listaClientes.add(bean);
		}

		stoLevantamientoErroresClientesList.clear();
		stoLevantamientoErroresClientesList = listaClientes;

	}

	public void uploadArchivoForm() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'load Clientes from file' method");
		}

		MantenimientoSTOControlFnePorcentajeForm f = (MantenimientoSTOControlFnePorcentajeForm) formMantenimiento;
		MantenimientoRECIngresoAtencionesService service = (MantenimientoRECIngresoAtencionesService) getBean("spusicc.mantenimientoRECIngresoAtencionesService");
		List listaClientes = new ArrayList();

		UploadedFile archivo = f.getClienteFile();
		Map criteria = new HashMap();
		InputStream is = archivo.getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String linea = "";
		int cont = 0;
		while (true) {
			linea = br.readLine();
			if (linea == null)
				break;

			criteria.put("codigoConsultora", linea.trim());
			LabelValue bean = new LabelValue(linea.trim(),
					service.getCodigoConsultora(criteria));
			listaClientes.add(bean);
			if (bean.getValue() == null)
				cont++;
		}
		f.setCodigosErradosFile("");
		if (cont != 0)
			f.setCodigosErradosFile("Existe(n) " + cont
					+ " codigo(s) errado(s)");

		stoLevantamientoErroresClientesList.clear();
		stoLevantamientoErroresClientesList = listaClientes;

	}

	@Override
	public String setValidarFind() {
		// TODO Auto-generated method stub
		return super.setValidarFind();
	}

	@Override
	public String setValidarMantenimiento() {
		MantenimientoSTOControlFnePorcentajeForm f = (MantenimientoSTOControlFnePorcentajeForm) formMantenimiento;
		if (!StringUtils.isBlank(f.getCodigoCliente())
				|| !StringUtils.isBlank(f.getCodigoRegion())
				|| !StringUtils.isBlank(f.getTipoCliente())) {
			return "";
		} else {
			return "Por lo menos Tipo Cliente, Región o Código Cliente deben estar Ingresadas";
		}
	}

	public List getStoTipoDocumentoSpvd() {
		return stoTipoDocumentoSpvd;
	}

	public void setStoTipoDocumentoSpvd(List stoTipoDocumentoSpvd) {
		this.stoTipoDocumentoSpvd = stoTipoDocumentoSpvd;
	}

	public List getStoTipoClienteList() {
		return stoTipoClienteList;
	}

	public void setStoTipoClienteList(List stoTipoClienteList) {
		this.stoTipoClienteList = stoTipoClienteList;
	}

	public LabelValue[] getStoSubtipoClienteList() {
		return stoSubtipoClienteList;
	}

	public void setStoSubtipoClienteList(LabelValue[] stoSubtipoClienteList) {
		this.stoSubtipoClienteList = stoSubtipoClienteList;
	}

	public LabelValue[] getStoTipoClasificacionList() {
		return stoTipoClasificacionList;
	}

	public void setStoTipoClasificacionList(
			LabelValue[] stoTipoClasificacionList) {
		this.stoTipoClasificacionList = stoTipoClasificacionList;
	}

	public LabelValue[] getStoClasificacionList() {
		return stoClasificacionList;
	}

	public void setStoClasificacionList(LabelValue[] stoClasificacionList) {
		this.stoClasificacionList = stoClasificacionList;
	}

	public List getStoRegionList() {
		return stoRegionList;
	}

	public void setStoRegionList(List stoRegionList) {
		this.stoRegionList = stoRegionList;
	}

	public LabelValue[] getStoZonaList() {
		return stoZonaList;
	}

	public void setStoZonaList(LabelValue[] stoZonaList) {
		this.stoZonaList = stoZonaList;
	}

	public List getStoBloqueoControlList() {
		return stoBloqueoControlList;
	}

	public void setStoBloqueoControlList(List stoBloqueoControlList) {
		this.stoBloqueoControlList = stoBloqueoControlList;
	}

	public List getStoLevantamientoErroresClientesList() {
		return stoLevantamientoErroresClientesList;
	}

	public void setStoLevantamientoErroresClientesList(
			List stoLevantamientoErroresClientesList) {
		this.stoLevantamientoErroresClientesList = stoLevantamientoErroresClientesList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -7766588187041148487L;

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoSTOControlFnePorcentajeList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoSTOControlFnePorcentajeForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoSTOControlFnePorcentajeSearchForm searchForm = new MantenimientoSTOControlFnePorcentajeSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		

		return null;
	}

	public void buscarAtributos() {
		log.debug("MantenimientoSTOControlDevolucionesSearchAction - setFindAttributes");

		MantenimientoSTOControlFnePorcentajeSearchForm f = (MantenimientoSTOControlFnePorcentajeSearchForm) formBusqueda;

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		// MantenimientoSTOControlDevolucionesService service =
		// (MantenimientoSTOControlDevolucionesService)getBean("spusicc.mantenimientoSTOControlDevolucionesService");
		MantenimientoSTOBloqueoControlService service = (MantenimientoSTOBloqueoControlService) getBean("spusicc.mantenimientoSTOBloqueoControlService");

		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();

		Map criteria = new HashMap();

		criteria.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("codigoCliente", f.getCodigoCliente());
		criteria.put("codigoIso", usuario.getIdioma().getCodigoISO());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);

		if (StringUtils.isNotBlank(f.getCodigoPeriodo()))
			criteria.put("oidPeriodo", reporteService.getOidString(
					"getOidPeriodoByCodigoPeriodo", criteria));
		else
			criteria.put("oidPeriodo", null);

		if (StringUtils.isNotBlank(f.getTipoCliente()))
			criteria.put("oidTipoCliente", f.getTipoCliente());
		else
			criteria.put("oidTipoCliente", null);

		if (StringUtils.isNotBlank(f.getSubTipoCliente())) {
			criteria.put("oidSubTipoCliente", f.getSubTipoCliente());
			// request.setAttribute("oidSubTipoCliente",
			// criteria.get("oidSubTipoCliente"));
		} else {
			criteria.put("oidSubTipoCliente", null);
			// request.setAttribute("oidSubTipoCliente", null);
		}

		if (StringUtils.isNotBlank(f.getTipoClasificacion())) {
			criteria.put("oidSubTipoClasificacion", f.getTipoClasificacion());
			// request.setAttribute("oidSubTipoClasificacion",
			// criteria.get("oidSubTipoClasificacion"));
		} else {
			criteria.put("oidSubTipoClasificacion", null);
			// request.setAttribute("oidSubTipoClasificacion",null);
		}

		if (StringUtils.isNotBlank(f.getClasificacion())) {
			criteria.put("oidClasificacion", f.getClasificacion());
			// request.setAttribute("oidClasificacion",
			// criteria.get("oidClasificacion"));
		} else {
			criteria.put("oidClasificacion", null);
			// request.setAttribute("oidClasificacion", null);
		}

		if (StringUtils.isNotBlank(f.getCodigoRegion()))
			criteria.put("codigoRegion", f.getCodigoRegion());
		else
			criteria.put("codigoRegion", null);

		if (StringUtils.isNotBlank(f.getCodigoZona())) {
			criteria.put("codigoZona", f.getCodigoZona());
			// request.setAttribute("codigoZona", criteria.get("codigoZona"));
		} else {
			criteria.put("codigoZona", null);
			// request.setAttribute("codigoZona", null);
		}

		/*-------------------------*/
		if (StringUtils.isNotBlank(f.getTipoBloqueo())) {
			criteria.put("tipoBloqueo", f.getTipoBloqueo());
		} else {
			criteria.put("tipoBloqueo", null);
		}
		/*-------------------------*/
		String[] arrlistaClientes = new String[0];
		List clienteList = new ArrayList(); // result
		Long longitudPais = mPantallaPrincipalBean.getCurrentCountry()
				.getLongitudCodigoCliente();
		String codigoCliente = f.getCodigoCliente();
		List listaClientes = stoLevantamientoErroresClientesList; // grilla del
																	// archivo

		Map param = new HashMap();

		// SI cargo consultoras por el archivo
		if (listaClientes != null) {
			for (int i = 0; i < listaClientes.size(); i++) {
				LabelValue bean = (LabelValue) listaClientes.get(i);
				param.put(
						"codigoCliente",
						StringUtils.leftPad(bean.getLabel(),
								longitudPais.intValue(), '0'));
				try {
					clienteList.add(reporteService.getOidString(
							"getOidClienteByCodigoCliente", param));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} else {
			// Si es cargado por la caja de texto
			if (codigoCliente != null && codigoCliente.length() > 0)
				arrlistaClientes = codigoCliente.split(",+");
			else
				clienteList = null;
			for (int i = 0; i < arrlistaClientes.length; i++) {
				param.put(
						"codigoCliente",
						StringUtils.leftPad(arrlistaClientes[i],
								longitudPais.intValue(), '0'));
				try {
					clienteList.add(reporteService.getOidString(
							"getOidClienteByCodigoCliente", param));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		if (!clienteList.isEmpty())
			criteria.put("clienteList", clienteList);

		/*-------------------------*/

		List list = new ArrayList();
		criteria.put("codigoTipoDocumento", f.getCodigoTipoDocumento());

		list = service.getBloqueoControlList(criteria);

		this.stoControlFNEPorcentajeList = list;
		this.listaBusqueda = list;
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		log.debug("MantenimientoSTOControlDevolucionesSearchAction - setDeleteAttributes");

		MantenimientoSTOControlFnePorcentajeSearchForm f = (MantenimientoSTOControlFnePorcentajeSearchForm) formBusqueda;

		MantenimientoSTOBloqueoControlService service = (MantenimientoSTOBloqueoControlService) getBean("spusicc.mantenimientoSTOBloqueoControlService");

		Map parametros = new HashMap();
		parametros.put("usuario", mPantallaPrincipalBean.getCurrentUser()
				.getLogin());
		
		
		
		

		

		// importante //String flagBorrarTodos =
		// request.getParameter("selectallmensaje");

		if (StringUtils.equals("0", Constants.NUMERO_UNO)) {

			// Borra todos sto
			List lista = stoControlFNEPorcentajeList;

			if (lista != null && lista.size() > 0) {
				String[] selectedItems = new String[lista.size()];

				for (int i = 0; i < lista.size(); i++) {
					selectedItems[i] = MapUtils.getString((Map) lista.get(i),
							"codigoBloqueo", "");
				}

				parametros.put("idSeleccionados", selectedItems);
			}
		} else {
			// Borra solo los seleccionados
			
			//{tipoCliente=Consultor(a), tipoClasificacionCliente=null, observaciones=RECURRENCIA 3 A 6, valorPorcentaje=    .00, oidRegion=null, oidCliente=4755, descripcionRegion=null, usuarioCreacion=PEJALIAGA, oidTipoCliente=2, codigoCliente=002936364, subTipoCliente=null, clasificacion=null, fechaCreacion=07/01/2015 16:02:41, codigoPeriodo=201502, descripcionZona=null, codigoTipoBloqueo=0, codigoBloqueo=113514}
			int tamanio = columnasSeleccionadas.length;
			String [] array = new String[tamanio];
			for (int i = 0; i < tamanio; i++) {
				Map form = (Map) columnasSeleccionadas[i];
				array[i]=form.get("codigoBloqueo").toString();
			}
			parametros.put("idSeleccionados", array);

		}

		service.deleteBloqueoControl(parametros);
		columnasSeleccionadas=null;
		return true;
	}

	public void deleteAttributes(){
		log.debug("MantenimientoSTOControlDevolucionesSearchAction - setDeleteAttributes");

		MantenimientoSTOControlFnePorcentajeSearchForm f = (MantenimientoSTOControlFnePorcentajeSearchForm) formBusqueda;

		MantenimientoSTOBloqueoControlService service = (MantenimientoSTOBloqueoControlService) getBean("spusicc.mantenimientoSTOBloqueoControlService");

		Map parametros = new HashMap();
		parametros.put("usuario", mPantallaPrincipalBean.getCurrentUser()
				.getLogin());
		
		
		
		

		

		// importante //String flagBorrarTodos =
		// request.getParameter("selectallmensaje");

		if (StringUtils.equals("0", Constants.NUMERO_UNO)) {

			// Borra todos sto
			List lista = stoControlFNEPorcentajeList;

			if (lista != null && lista.size() > 0) {
				String[] selectedItems = new String[lista.size()];

				for (int i = 0; i < lista.size(); i++) {
					selectedItems[i] = MapUtils.getString((Map) lista.get(i),
							"codigoBloqueo", "");
				}

				parametros.put("idSeleccionados", selectedItems);
			}
		} else {
			// Borra solo los seleccionados
			
			//{tipoCliente=Consultor(a), tipoClasificacionCliente=null, observaciones=RECURRENCIA 3 A 6, valorPorcentaje=    .00, oidRegion=null, oidCliente=4755, descripcionRegion=null, usuarioCreacion=PEJALIAGA, oidTipoCliente=2, codigoCliente=002936364, subTipoCliente=null, clasificacion=null, fechaCreacion=07/01/2015 16:02:41, codigoPeriodo=201502, descripcionZona=null, codigoTipoBloqueo=0, codigoBloqueo=113514}
			int tamanio = columnasSeleccionadas.length;
			String [] array = new String[tamanio];
			for (int i = 0; i < tamanio; i++) {
				Map form = (Map) columnasSeleccionadas[i];
				array[i]=form.get("codigoBloqueo").toString();
			}
			parametros.put("idSeleccionados", array);

		}

		service.deleteBloqueoControl(parametros);
		columnasSeleccionadas=null;
		addInfo("Mensaje","Eliminados Correctamente" );
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		log.debug("MantenimientoSTOControlDevolucionesAction - setAddAttributes");

		MantenimientoSTOControlFnePorcentajeForm f = (MantenimientoSTOControlFnePorcentajeForm) formMantenimiento;

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoSTOBloqueoControlService service = (MantenimientoSTOBloqueoControlService) getBean("spusicc.mantenimientoSTOBloqueoControlService");

		boolean isArchivo = false;

		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		Map criteria = new HashMap();

		criteria.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("codigoCliente", f.getCodigoCliente());
		criteria.put("codigoIso", usuario.getIdioma().getCodigoISO());
		criteria.put("usuario", usuario.getLogin());

		if (StringUtils.isNotBlank(f.getCodigoPeriodo()))
			criteria.put("oidPeriodo", reporteService.getOidString(
					"getOidPeriodoByCodigoPeriodo", criteria));
		else
			criteria.put("oidPeriodo", null);

		/*
		 * if(StringUtils.isNotBlank(f.getCodigoCliente()))
		 * criteria.put("oidCliente",
		 * reporteService.getOidString("getOidClienteByCodigoCliente",
		 * criteria)); else criteria.put("oidCliente", null);
		 */
		if (StringUtils.isNotBlank(f.getTipoCliente()))
			criteria.put("oidTipoCliente", f.getTipoCliente());
		else
			criteria.put("oidTipoCliente", null);

		if (StringUtils.isNotBlank(f.getSubTipoCliente())) {
			criteria.put("oidSubTipoCliente", f.getSubTipoCliente());
			// request.setAttribute("oidSubTipoCliente",
			// criteria.get("oidSubTipoCliente"));
		} else {
			criteria.put("oidSubTipoCliente", null);
			// request.setAttribute("oidSubTipoCliente", null);
		}

		if (StringUtils.isNotBlank(f.getTipoClasificacion())) {
			criteria.put("oidSubTipoClasificacion", f.getTipoClasificacion());
			// request.setAttribute("oidSubTipoClasificacion",
			// criteria.get("oidSubTipoClasificacion"));
		} else {
			criteria.put("oidSubTipoClasificacion", null);
			// request.setAttribute("oidSubTipoClasificacion",null);
		}

		if (StringUtils.isNotBlank(f.getClasificacion())) {
			criteria.put("oidClasificacion", f.getClasificacion());
			// request.setAttribute("oidClasificacion",
			// criteria.get("oidClasificacion"));
		} else {
			criteria.put("oidClasificacion", null);
			// request.setAttribute("oidClasificacion", null);
		}

		if (StringUtils.isNotBlank(f.getCodigoRegion()))
			criteria.put("codigoRegion", f.getCodigoRegion());
		else
			criteria.put("codigoRegion", Constants.STO_VALOR_OID_NULL);

		if (StringUtils.isNotBlank(f.getCodigoZona())) {
			criteria.put("codigoZona", f.getCodigoZona());
			// request.setAttribute("codigoZona", criteria.get("codigoZona"));
		} else {
			criteria.put("codigoZona", null);
			// request.setAttribute("codigoZona", null);
		}

		criteria.put("fechaCreacion", Calendar.getInstance().getTime());
		criteria.put("observaciones", f.getObservaciones());

		criteria.put("tipoBloqueo", f.getTipoBloqueo());

		/*-------------------------*/
		String[] arrlistaClientes = new String[0];
		List clienteList = new ArrayList(); // result
		Long longitudPais = mPantallaPrincipalBean.getCurrentCountry()
				.getLongitudCodigoCliente();
		String codigoCliente = f.getCodigoCliente();
		List listaClientes = stoLevantamientoErroresClientesList; // grilla del
																	// archivo

		Map param = new HashMap();

		// SI cargo consultoras por el archivo
		if (listaClientes != null) {
			isArchivo = true;
			for (int i = 0; i < listaClientes.size(); i++) {
				LabelValue bean = (LabelValue) listaClientes.get(i);
				param.put(
						"codigoCliente",
						StringUtils.leftPad(bean.getLabel(),
								longitudPais.intValue(), '0'));
				try {
					clienteList.add(reporteService.getOidString(
							"getOidClienteByCodigoCliente", param));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} else {
			// Si es cargado por la caja de texto
			if (codigoCliente.length() > 0)
				arrlistaClientes = codigoCliente.split(",+");
			for (int i = 0; i < arrlistaClientes.length; i++) {
				param.put(
						"codigoCliente",
						StringUtils.leftPad(arrlistaClientes[i],
								longitudPais.intValue(), '0'));
				try {
					clienteList.add(reporteService.getOidString(
							"getOidClienteByCodigoCliente", param));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		criteria.put("clienteList", (clienteList == null) ? new ArrayList()
				: clienteList);
		/*-------------------------*/

		criteria.put("codigoTipoDocumento", f.getCodigoTipoDocumento());
		criteria.put("porcentaje", f.getPorcentaje());

		// insertando motivo de bloqueo F
		criteria.put("motivoBloqueo", Constants.MOTIVO_BLOQUEO_FNE);

		int cantidadInsertados = service.insertSTOBloqueoControl(criteria);

		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		log.debug("MantenimientoSTOControlDevolucionesAction - setAddAttributes");

		MantenimientoSTOControlFnePorcentajeForm f = new MantenimientoSTOControlFnePorcentajeForm();
		f.setCodigoTipoDocumento(Constants.STO_TIPO_DOCUMENTO_SPVD);

		return f;

	}

	private void cleanForm(MantenimientoSTOControlFnePorcentajeSearchForm f) {
		f.setCodigoCliente("");
		f.setCodigoPeriodo("");
		f.setTipoCliente("");// this.tipoCliente = null;
		f.setSubTipoCliente(""); // this.subTipoCliente = null;
		f.setTipoClasificacion(""); // this.tipoClasificacion = null;
		f.setClasificacion(""); // this.clasificacion = null;
		f.setCodigoRegion(""); // this.codigoRegion = null;
		f.setCodigoZona(""); // this.codigoZona = null;
		f.setTipoBloqueo("");
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("MantenimientoSTOControlFnePorcentajeSearchAction - setViewAttributes");
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar=false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonBuscar=false;
		MantenimientoSTOControlFnePorcentajeSearchForm f = (MantenimientoSTOControlFnePorcentajeSearchForm) formBusqueda;
		cleanForm(f);
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
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

		// Carga el combo Tipo cliente
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		stoTipoClienteList = interfazSiCCService
				.getTiposClientesByCodigoISOOID(usuario.getIdioma()
						.getCodigoISO());

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		stoRegionList = reporteService.getListaGenerico("getRegionesByPais",
				criteria);

		// session.setAttribute("codigoIdiomaISO", usuario.getIdioma()
		// .getCodigoISO());
		stoBloqueoControlList.clear();
		// session.removeAttribute(Constants.STO_BLOQUEO_CONTROL_LIST);

		stoLevantamientoErroresClientesList.clear();

		f.setCodigoTipoDocumento(Constants.STO_TIPO_DOCUMENTO_SPVD);

		f.setLineaDefecto(Constants.STO_CONTROL_FNE_PORCENTAJE_NRO_LINEAS_DEFECTO);
		f.setLineaMaxima(Constants.STO_CONTROL_FNE_PORCENTAJE_NRO_LINEAS_MAXIMO);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction
	 * #devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoSTOControlFnePorcentajeForm sistemaForm = (MantenimientoSTOControlFnePorcentajeForm) this.formMantenimiento;
		boolean isNew = sistemaForm.isNewRecord();
		if (isNew) {
			return "mantenimientoSTOCuponForm.insert";
		} else {
			return "mantenimientoSTOCuponForm.insert";
		}
	}

	public void buscarSubTipoClientes(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			ArrayList x = new ArrayList();

			MantenimientoSTOControlFnePorcentajeSearchForm f = (MantenimientoSTOControlFnePorcentajeSearchForm) this.formBusqueda;

			String regionListado = (String) val.getNewValue();
			x.add(regionListado);

			// ajax.getSubTiposClientesPorPaisTipoClientesOID( codigoIdiomaISO,
			// values, loadSubTiposClientesCallback);
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			stoSubtipoClienteList = ajax
					.getSubTiposClientesPorPaisTipoClientesOID(this
							.getmPantallaPrincipalBean().getCurrentUser()
							.getIdioma().getCodigoISO(), x);
			stoTipoClasificacionList = null;
			stoClasificacionList = null;
		} else {
			stoSubtipoClienteList = null;
		}
	}

	public void buscarTipoClasificacion(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			ArrayList x = new ArrayList();
			MantenimientoSTOControlFnePorcentajeSearchForm f = (MantenimientoSTOControlFnePorcentajeSearchForm) this.formBusqueda;

			MantenimientoSTOControlFnePorcentajeForm f2 = (MantenimientoSTOControlFnePorcentajeForm) this.formMantenimiento;
			String regionListado = (String) val.getNewValue();
			x.add(regionListado);

			// ajax.getSubTiposClientesPorPaisTipoClientesOID( codigoIdiomaISO,
			// values, loadSubTiposClientesCallback);
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			stoTipoClasificacionList = ajax
					.getTiposClasificacionesByCriteriaMultipleOID(this
							.getmPantallaPrincipalBean().getCurrentUser()
							.getIdioma().getCodigoISO(), f.getTipoCliente(), x);
			stoClasificacionList = null;
		} else {
			stoTipoClasificacionList = null;
		}
	}

	public void buscarClasificacion(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			ArrayList x = new ArrayList();
			MantenimientoSTOControlFnePorcentajeSearchForm f = (MantenimientoSTOControlFnePorcentajeSearchForm) this.formBusqueda;
			String regionListado = (String) val.getNewValue();
			x.add(regionListado);
			ArrayList subTiposCliente = new ArrayList();
			subTiposCliente.add(f.getSubTipoCliente());

			// ajax.getClasificacionesByCriteriaMultipleOID
			// (codigoIdiomaISO, tipoCliente.value, valuesSubTipoCliente,
			// valuesClasificacion, loadClasificacionesCallback);
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			stoClasificacionList = ajax
					.getClasificacionesByCriteriaMultipleOID(this
							.getmPantallaPrincipalBean().getCurrentUser()
							.getIdioma().getCodigoISO(), f.getTipoCliente(),
							subTiposCliente, x);

		} else {
			stoClasificacionList = null;
		}
	}

	public void buscarZonasRegion(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			MantenimientoSTOControlFnePorcentajeSearchForm f = (MantenimientoSTOControlFnePorcentajeSearchForm) this.formBusqueda;
			String regionListado = (String) val.getNewValue();
			String regiones[] = new String[1];
			regiones[0] = regionListado;
			// ajax.getClasificacionesByCriteriaMultipleOID
			// (codigoIdiomaISO, tipoCliente.value, valuesSubTipoCliente,
			// valuesClasificacion, loadClasificacionesCallback);
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			stoZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(this
					.getmPantallaPrincipalBean().getCurrentCountry()
					.getCodigo(), "T", "VD", regiones, "");

		} else {
			stoZonaList = null;
		}
	}

	/* BaseEdit */
	public void buscarSubTipoClientesForm(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			ArrayList x = new ArrayList();
			MantenimientoSTOControlFnePorcentajeForm f = (MantenimientoSTOControlFnePorcentajeForm) this.formMantenimiento;
			String regionListado = (String) val.getNewValue();
			x.add(regionListado);

			// ajax.getSubTiposClientesPorPaisTipoClientesOID( codigoIdiomaISO,
			// values, loadSubTiposClientesCallback);
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			stoSubtipoClienteList = ajax
					.getSubTiposClientesPorPaisTipoClientesOID(this
							.getmPantallaPrincipalBean().getCurrentUser()
							.getIdioma().getCodigoISO(), x);
			stoTipoClasificacionList = null;
			stoClasificacionList = null;
		} else {
			stoSubtipoClienteList = null;
		}
	}

	public void buscarTipoClasificacionForm(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			ArrayList x = new ArrayList();
			MantenimientoSTOControlFnePorcentajeForm f = (MantenimientoSTOControlFnePorcentajeForm) this.formMantenimiento;
			String regionListado = (String) val.getNewValue();
			x.add(regionListado);

			// ajax.getSubTiposClientesPorPaisTipoClientesOID( codigoIdiomaISO,
			// values, loadSubTiposClientesCallback);
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			stoTipoClasificacionList = ajax
					.getTiposClasificacionesByCriteriaMultipleOID(this
							.getmPantallaPrincipalBean().getCurrentUser()
							.getIdioma().getCodigoISO(), f.getTipoCliente(), x);
			stoClasificacionList = null;
		} else {
			stoTipoClasificacionList = null;
		}
	}

	public void buscarClasificacionForm(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			ArrayList x = new ArrayList();
			MantenimientoSTOControlFnePorcentajeForm f = (MantenimientoSTOControlFnePorcentajeForm) this.formMantenimiento;
			String regionListado = (String) val.getNewValue();
			x.add(regionListado);
			ArrayList subTiposCliente = new ArrayList();
			subTiposCliente.add(f.getSubTipoCliente());

			// ajax.getClasificacionesByCriteriaMultipleOID
			// (codigoIdiomaISO, tipoCliente.value, valuesSubTipoCliente,
			// valuesClasificacion, loadClasificacionesCallback);
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			stoClasificacionList = ajax
					.getClasificacionesByCriteriaMultipleOID(this
							.getmPantallaPrincipalBean().getCurrentUser()
							.getIdioma().getCodigoISO(), f.getTipoCliente(),
							subTiposCliente, x);

		} else {
			stoClasificacionList = null;
		}
	}

	public void buscarZonasRegionForm(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			MantenimientoSTOControlFnePorcentajeForm f = (MantenimientoSTOControlFnePorcentajeForm) this.formMantenimiento;
			String regionListado = (String) val.getNewValue();
			String regiones[] = new String[1];
			regiones[0] = regionListado;
			// ajax.getClasificacionesByCriteriaMultipleOID
			// (codigoIdiomaISO, tipoCliente.value, valuesSubTipoCliente,
			// valuesClasificacion, loadClasificacionesCallback);
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			stoZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(this
					.getmPantallaPrincipalBean().getCurrentCountry()
					.getCodigo(), "T", "VD", regiones, "");

		} else {
			stoZonaList = null;
		}
	}

}
