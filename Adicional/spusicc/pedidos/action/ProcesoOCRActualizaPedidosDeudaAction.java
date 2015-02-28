package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.SolicitudConsolidadoCabecera;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.ProcesoCOMCargaCuentasDetraccionForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoOCRActualizaPedidosDeudaForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOControlFnePorcentajeForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOControlFnePorcentajeSearchForm;

@SessionScoped
@ManagedBean
public class ProcesoOCRActualizaPedidosDeudaAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8436872734581804482L;
	private List siccPeriodoList = new ArrayList();
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private List pedActualizaDeudaMasivaList = new ArrayList();
	private List clientesFileList = new ArrayList();
	private String attachment = "";

	public void handleFileUpload(FileUploadEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		MantenimientoSTOControlFnePorcentajeSearchForm f = (MantenimientoSTOControlFnePorcentajeSearchForm) this.formBusqueda;
		if (event != null) {
			// f.setArchivo(event.getFile());
			f.setClienteFile(event.getFile());
			this.setAttachment(event.getFile().getFileName());
			this.uploadArchivo();
		}
	}

	public void uploadArchivo() throws Exception {
		log.debug("ProcesoOCRActualizaPedidosDeudaSearchAction - loadfile");

		ProcesoOCRActualizaPedidosDeudaForm f = (ProcesoOCRActualizaPedidosDeudaForm) formBusqueda;

		List listaClientes = new ArrayList();

		UploadedFile archivo = f.getClienteFile();

		InputStream is = archivo.getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String linea;

		PaisService paisService = (PaisService) getBean("paisService");
		Pais pais = paisService.getPais(f.getCodigoPais());

		int contFilas = 0;
		int numMaximoRegistros = StringUtils.isNotEmpty(pais
				.getMaximoNumeroRegistrosFile()) ? Integer.parseInt(pais
				.getMaximoNumeroRegistrosFile()) : 0;
		while (true) {
			linea = br.readLine();
			if (linea == null)
				break;

			if (StringUtils.isNotEmpty(linea)) {
				contFilas++;
				if (contFilas > numMaximoRegistros) {
					
					addError("Error", getResourceMessage("errors.maximo.registro"));
					
					// f.reset(mapping, request);
					// return mapping.findForward("view");
					listaClientes = new ArrayList();
					break;

				}
				if (StringUtils.isNotBlank(linea.trim())) {
					Base base = new Base();
					base.setCodigo(linea.trim());
					listaClientes.add(base);
				}
			}
		}

		f.setSelectedItems(new String[] {});

		clientesFileList=listaClientes;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		LabelValue[] zonaLista = (LabelValue[]) aSvc
				.getZonasByPeriodoIntEviPerioRegioZona(mPantallaPrincipalBean.getCurrentCountry().getCodigo(),
						f.getCodigoPeriodo(), f.getCodigoRegion(), " ");

		siccZonaList=zonaLista == null ? null : zonaLista;
		

	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public List getPedActualizaDeudaMasivaList() {
		return pedActualizaDeudaMasivaList;
	}

	public void setPedActualizaDeudaMasivaList(List pedActualizaDeudaMasivaList) {
		this.pedActualizaDeudaMasivaList = pedActualizaDeudaMasivaList;
	}

	public List getSiccPeriodoList() {
		return siccPeriodoList;
	}

	public void setSiccPeriodoList(List siccPeriodoList) {
		this.siccPeriodoList = siccPeriodoList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ProcesoOCRActualizaPedidosDeudaForm e = new ProcesoOCRActualizaPedidosDeudaForm();
		return e;
	}

	public void loadfile() throws Exception {

		log.debug("ProcesoOCRActualizaPedidosDeudaSearchAction - loadfile");

		ProcesoOCRActualizaPedidosDeudaForm f = (ProcesoOCRActualizaPedidosDeudaForm) formBusqueda;

		List listaClientes = new ArrayList();

		UploadedFile archivo = f.getClienteFile();

		InputStream is = archivo.getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String linea;

		PaisService paisService = (PaisService) getBean("paisService");
		Pais pais = paisService.getPais(f.getCodigoPais());

		int contFilas = 0;
		int numMaximoRegistros = StringUtils.isNotEmpty(pais
				.getMaximoNumeroRegistrosFile()) ? Integer.parseInt(pais
				.getMaximoNumeroRegistrosFile()) : 0;
		while (true) {
			linea = br.readLine();
			if (linea == null)
				break;

			if (StringUtils.isNotEmpty(linea)) {
				contFilas++;
				if (contFilas > numMaximoRegistros) {
					addInfo("Mensaje",
							this.getResourceMessage("errors.maximo.registro"));

					// f.reset(mapping, request);
					// return mapping.findForward("view");
					listaClientes = new ArrayList();
					break;

				}
				if (StringUtils.isNotBlank(linea.trim())) {
					Base base = new Base();
					base.setCodigo(linea.trim());
					listaClientes.add(base);
				}
			}
		}

		f.setSelectedItems(new String[] {});

		clientesFileList = listaClientes;

		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		LabelValue[] zonaLista = (LabelValue[]) aSvc
				.getZonasByPeriodoIntEviPerioRegioZona(mPantallaPrincipalBean
						.getCurrentCountry().getCodigo(), f.getCodigoPeriodo(),
						f.getCodigoRegion(), " ");
		siccZonaList = zonaLista == null ? null : zonaLista;

	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}
		// Removemos atributos session

		pedActualizaDeudaMasivaList.clear();

		ProcesoOCRActualizaPedidosDeudaForm f = (ProcesoOCRActualizaPedidosDeudaForm) formBusqueda;
		f.setSelectedItems(new String[] {});

		// Obtenemos las propiedades del bean como un 'Map'
		Map criteria = BeanUtils.describe(f);
		// La busqueda solo la realizaremos en los sistemas activos
		criteria.put("estado", Constants.ESTADO_ACTIVO);
		criteria.put("tipoDocumento", Constants.STO_TIPO_DOCUMENTO_OCC);
		criteria.put("clienteFile", new ArrayList());

		List cliList = clientesFileList;

		if (cliList != null && !cliList.isEmpty()) {
			criteria.put("codigoCliente", null);
			List codigosCliList = new ArrayList();

			for (int i = 0; i < cliList.size(); i++) {
				Base base = (Base) cliList.get(i);
				codigosCliList.add(base.getCodigo());
			}

			criteria.put("clienteFile", codigosCliList);
		}

		if (log.isDebugEnabled()) {
			log.debug("criteria search " + criteria.toString());
		}
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		List list = service.getDeudaPedidosByCriteria(criteria);
		pedActualizaDeudaMasivaList = list;

		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		LabelValue[] zonaLista = (LabelValue[]) aSvc
				.getZonasByPeriodoIntEviPerioRegioZona(mPantallaPrincipalBean
						.getCurrentCountry().getCodigo(), f.getCodigoPeriodo(),
						f.getCodigoRegion(), " ");
		if (log.isDebugEnabled()) {
			log.debug("zonaLista " + zonaLista);
		}

		siccZonaList = zonaLista == null ? null : zonaLista;

		return null;
	}

	/**
	 * Metodo para realizar la busqueda
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	public List convertBaseListToLabelValue(LabelValue[] labelValue) {
		List a = new ArrayList();
		for (int i = 0; i < labelValue.length; i++) {
			Base b = new Base();
			b.setCodigo(labelValue[i].getValue());
			b.setDescripcion(labelValue[i].getLabel());
			a.add(b);
		}
		return a;
	}

	public LabelValue[] convertBaseLabelValueToList(List list) {
		LabelValue[] a = {};
		for (int i = 0; i < list.size(); i++) {
			a[i].setLabel(((Base) list).getDescripcion());
			a[i].setValue(((Base) list).getCodigo());
		}
		return a;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'grabar' method");
		}

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		ProcesoOCRActualizaPedidosDeudaForm actualizaPedidosDeudaForm = (ProcesoOCRActualizaPedidosDeudaForm) formBusqueda;

		int conIndActivos = 0;
		int conIndInactivos = 0;

		for (int i = 0; i < actualizaPedidosDeudaForm.getSelectedItems().length; i++) {
			if (getIndicador(actualizaPedidosDeudaForm.getSelectedItems()[i])
					.compareToIgnoreCase("1") == 0)
				conIndActivos = conIndActivos + 1;
			else
				conIndInactivos = conIndInactivos + 1;
		}

		String[] cadenaInactivo = new String[conIndInactivos];
		String[] cadenaActivo = new String[conIndActivos];

		int indiceActivos = 0;
		int indiceInactivos = 0;
		for (int i = 0; i < actualizaPedidosDeudaForm.getSelectedItems().length; i++) {

			if (getIndicador(actualizaPedidosDeudaForm.getSelectedItems()[i])
					.compareToIgnoreCase("1") == 0) {
				cadenaActivo[indiceActivos] = actualizaPedidosDeudaForm
						.getSelectedItems()[i].substring(0,
						actualizaPedidosDeudaForm.getSelectedItems()[i]
								.length() - 2);
				indiceActivos++;
			} else {
				cadenaInactivo[indiceInactivos] = actualizaPedidosDeudaForm
						.getSelectedItems()[i].substring(0,
						actualizaPedidosDeudaForm.getSelectedItems()[i]
								.length() - 2);
				indiceInactivos++;
			}
		}

		Usuario usuario = (mPantallaPrincipalBean.getCurrentUser());

		if (cadenaActivo.length > 0) {
			SolicitudConsolidadoCabecera cabeceraActivos = new SolicitudConsolidadoCabecera();
			BeanUtils
					.copyProperties(cabeceraActivos, actualizaPedidosDeudaForm);
			cabeceraActivos.setCodigos(cadenaActivo == null ? new ArrayList()
					: Arrays.asList(cadenaActivo));
			cabeceraActivos.setObservaciones("Levantamiento Masivo");
			cabeceraActivos
					.setIndErrorAdminCartera(Constants.IND_ERRO_ADM_CARTERA_INACT);
			service.updateDeudaGeneral(cabeceraActivos, usuario);
		}

		if (cadenaInactivo.length > 0) {
			SolicitudConsolidadoCabecera cabeceraInactivos = new SolicitudConsolidadoCabecera();
			BeanUtils.copyProperties(cabeceraInactivos,
					actualizaPedidosDeudaForm);
			cabeceraInactivos
					.setCodigos(cadenaInactivo == null ? new ArrayList()
							: Arrays.asList(cadenaInactivo));
			cabeceraInactivos.setObservaciones("Levantamiento Masivo");
			cabeceraInactivos
					.setIndErrorAdminCartera(Constants.IND_ERRO_ADM_CARTERA_ACT);
			service.updateDeudaGeneral(cabeceraInactivos, usuario);
		}
		// addInfo("Mensaje", this.getResourceMessage("deuda.updated"));
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	private String getIndicador(String cadena) {
		String indicador = new String();
		indicador = cadena.substring(cadena.length() - 1, cadena.length());
		return indicador;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		// Removemos atributos session

		pedActualizaDeudaMasivaList.clear();

		// Carga de los combos
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		List list = reporteService.getListaPeriodosByBasCtrlFact(
				mPantallaPrincipalBean.getCurrentCountry().getCodigo(),
				Constants.NUMERO_CERO);

		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		siccRegionList = aSvc.getRegionesByPais(mPantallaPrincipalBean
				.getCurrentCountry().getCodigo());
		siccPeriodoList = list;

		// importante request.getSession().removeAttribute("clientesFileList");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		ProcesoOCRActualizaPedidosDeudaForm f = (ProcesoOCRActualizaPedidosDeudaForm) formBusqueda;
		f.setFechaInicio(sdf.format(new Date(System.currentTimeMillis())));
		f.setFechaInicioD(sdf.parse(f.getFechaInicio()));
		f.setFechaFin(sdf.format(new Date(System.currentTimeMillis())));
		f.setFechaFinD(sdf.parse(f.getFechaFin()));
		f.setCodigoCliente("");

	}
	
	public void buscarZonasRegionForm(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		log.debug(val.getNewValue().toString());
		if (StringUtils.isNotEmpty(val.getNewValue().toString())
				|| StringUtils.isNotBlank(val.getNewValue().toString())) {
			ProcesoOCRActualizaPedidosDeudaForm f = (ProcesoOCRActualizaPedidosDeudaForm) this.formBusqueda;
			String regionListado = (String) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			siccZonaList = ajax.getZonasByPeriodoIntEviPerioRegioZona(this
					.getmPantallaPrincipalBean().getCurrentCountry()
					.getCodigo(), f.getCodigoPeriodo(),regionListado, "T");

		} else {
			siccZonaList = null;
		}
	}

}
