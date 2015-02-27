package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelDatosConsultoraValue;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoAtencionesService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOLimiteVentaFocalizadoConsejeraService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOLimiteVentaFocalizadoConsejeraForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOLimiteVentaFocalizadoConsejeraSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoSTOLimiteVentaFocalizadoConsejeraSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -9101034491694659451L;
	private List stoLevantamientoErroresClientesList;
	private List stoResumenClientesList;
	private List stoLimiteVentaFocalizadoConsejeraList;
	private String attachment = "";
	private Boolean mostrarGrilla;

	public void handleFileUpload(FileUploadEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		MantenimientoSTOLimiteVentaFocalizadoConsejeraSearchForm f = (MantenimientoSTOLimiteVentaFocalizadoConsejeraSearchForm) this.formBusqueda;
		if (event != null) {
			// f.setArchivo(event.getFile());
			f.setClienteFile(event.getFile());
			this.setAttachment(event.getFile().getFileName());
			this.uploadArchivo();
		}
	}

	public void uploadArchivo() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'load Clientes from file' method");
		}

		MantenimientoSTOLimiteVentaFocalizadoConsejeraSearchForm f = (MantenimientoSTOLimiteVentaFocalizadoConsejeraSearchForm) this.formBusqueda;
		MantenimientoRECIngresoAtencionesService service = (MantenimientoRECIngresoAtencionesService) getBean("spusicc.mantenimientoRECIngresoAtencionesService");
		GenericoService genericoService = (GenericoService) getBean("genericoService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		List listaClientes = new ArrayList();
		String indValidaCodConsultoraDocIdentidad = null;

		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(pais.getCodigo());
		parametroPais.setCodigoSistema("STO");
		parametroPais.setNombreParametro("indValidaCodConsultoraDocIdentidad");

		List lstParametros = genericoService.getParametrosPais(parametroPais);

		if (lstParametros != null && lstParametros.size() > 0) {
			ParametroPais ps = (ParametroPais) lstParametros.get(0);
			indValidaCodConsultoraDocIdentidad = ps.getValorParametro();
		}

		UploadedFile archivo = f.getClienteFile();
		Map criteria = new HashMap();
		InputStream is = archivo.getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		Long longitudPais = pais.getLongitudCodigoCliente();
		String linea = "";
		String codigoConsultora = "";
		int cont = 0;
		int numRegistros = 0;

		while (true) {
			linea = br.readLine();
			if (linea == null)
				break;

			codigoConsultora = StringUtils.leftPad(linea.trim(),
					longitudPais.intValue(), '0');
			criteria.put("codigoConsultora", codigoConsultora);
			LabelValue bean = new LabelValue(codigoConsultora,
					service.getCodigoConsultora(criteria));

			if (bean.getValue() == null
					&& StringUtils.equals(indValidaCodConsultoraDocIdentidad,
							Constants.SI)) {
				criteria.put("documentoIdentidad", codigoConsultora);
				String codigoConsultoraPorDocIden = service
						.getCodigoConsultoraPorDocumentoIdentidad(criteria);

				if (codigoConsultoraPorDocIden == null) {
					bean = new LabelValue(
							codigoConsultora,
							service.getCodigoConsultoraPorDocumentoIdentidad(criteria));
				} else {
					bean = new LabelValue(
							codigoConsultoraPorDocIden,
							service.getCodigoConsultoraPorDocumentoIdentidad(criteria));
				}
			}

			listaClientes.add(bean);

			if (bean.getValue() == null)
				cont++;

			numRegistros++;
		}

		// Se inserta en la tabla temporal
		String oidCarga = service.getOidCargaCliente();
		criteria.put("oid", oidCarga);
		service.insertaClienteFile(listaClientes, criteria);

		// Se obtiene la lista de la tabla temporal
		List list = new ArrayList();
		list = service.getCargaClienteList(criteria);

		f.setCodigosErradosFile("");

		if (cont != 0)
			f.setCodigosErradosFile("Existe(n) " + cont
					+ " codigo(s) errado(s)");

		criteria.put("numRegistros", numRegistros);
		List list2 = new ArrayList();
		list2 = service.getResumenCargaClienteList(criteria);

		stoLevantamientoErroresClientesList = listaClientes;
		if (stoLevantamientoErroresClientesList != null) {
			this.mostrarGrilla = true;
		}

		stoResumenClientesList = list2;

	}

	/**
	 * carga el archivo al temporal
	 * 
	 * @param form
	 * @throws Exception
	 */
	// private void uploadArchivo() throws IOException {
	// if(log.isDebugEnabled()){
	// log.debug("uploadArchivo");
	// }
	// MantenimientoSTOLimiteVentaFocalizadoConsejeraSearchForm f =
	// (MantenimientoSTOLimiteVentaFocalizadoConsejeraSearchForm)
	// this.formBusqueda;
	// // leyemos el stream de entrada
	// InputStream is = f.getClienteFile().getInputstream();
	// // archivo del cliente
	// FileOutputStream os = new FileOutputStream(new
	// File(f.getClienteFile().getFileName()));
	// // grabamos cada 1024 bytes
	// int bytesRead = 0;
	// byte[] buffer = new byte[1024];
	// while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
	// os.write(buffer, 0, bytesRead);
	// }
	// os.close();
	// f.setClienteFile(null);
	// }

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoSTOLimiteVentaFocalizadoConsejeraList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoSTOLimiteVentaFocalizadoConsejeraForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoSTOLimiteVentaFocalizadoConsejeraSearchForm form = new MantenimientoSTOLimiteVentaFocalizadoConsejeraSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("MantenimientoSTOLimiteVentaFocalizadoConsejeraSearchAction - setFindAttributes");

		limpiar();

		MantenimientoSTOLimiteVentaFocalizadoConsejeraSearchForm f = (MantenimientoSTOLimiteVentaFocalizadoConsejeraSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoSTOLimiteVentaFocalizadoConsejeraService service = (MantenimientoSTOLimiteVentaFocalizadoConsejeraService) getBean("spusicc.mantenimientoSTOLimiteVentaFocalizadoConsejeraService");

		Map criteria = new HashMap();

		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());

		if (StringUtils.isNotBlank(f.getCodigoCliente()))
			criteria.put("codigoCliente", f.getCodigoCliente());
		else
			criteria.put("codigoCliente", null);

		/*-------------------------*/
		String[] arrlistaClientes = new String[0];
		List clienteList = new ArrayList(); // result
		Long longitudPais = pais.getLongitudCodigoCliente();
		String codigoCliente = f.getCodigoCliente();
		String codigoCliente3 = f.getCodigoCliente();
		List listaClientes = (List) stoLevantamientoErroresClientesList;// grilla
		// del
		// archivo

		Map map = new HashMap();
		// SI cargo consultoras por el archivo
		if (listaClientes != null) {
			if (listaClientes.size() > 0) {
				for (int i = 0; i < listaClientes.size(); i++) {

					LabelValue bean = (LabelValue) listaClientes.get(i);
					codigoCliente3 = bean.getLabel();
					if (codigoCliente3 != null) {
						clienteList.add(codigoCliente3);
					}
					// map = (Map) listaClientes.get(i);
					// codigoCliente3 = (String) map.get("codigoCliente");
				}
			}
		}

		// Si es cargado por la caja de texto
		if (codigoCliente != null && codigoCliente.length() > 0)
			arrlistaClientes = codigoCliente.split(",+");

		for (int i = 0; i < arrlistaClientes.length; i++) {
			codigoCliente = StringUtils.leftPad(arrlistaClientes[i],
					longitudPais.intValue(), '0');

			clienteList.add(codigoCliente);
		}

		/*
		 * if(clienteList != null) criteria.put("clienteList",clienteList);
		 */
		criteria.put("clienteList", (clienteList == null) ? new ArrayList()
				: clienteList);
		/*-------------------------*/

		List list = new ArrayList();

		list = service.getLimiteVentaFocalizadoConsejeraList(criteria);

		stoLimiteVentaFocalizadoConsejeraList = list;

		return list;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		log.debug("MantenimientoSTOLimiteVentaFocalizadoConsejeraSearchAction - setDeleteAttributes");

		MantenimientoSTOLimiteVentaFocalizadoConsejeraSearchForm f = (MantenimientoSTOLimiteVentaFocalizadoConsejeraSearchForm) this.formBusqueda;
		HashMap<String, Object> objBeanSelect = (HashMap<String, Object>) this.beanRegistroSeleccionado;

		String codigoPais = (String) objBeanSelect.get("codigoPais");
		String codigoCliente = (String) objBeanSelect.get("codigoCliente");
		String codigoPeriodo = (String) objBeanSelect.get("codigoPeriodo");

		MantenimientoSTOLimiteVentaFocalizadoConsejeraService service = (MantenimientoSTOLimiteVentaFocalizadoConsejeraService) getBean("spusicc.mantenimientoSTOLimiteVentaFocalizadoConsejeraService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map parametros = new HashMap();

		String[] scripts = new String[1];
		scripts[0] = codigoPais + "-" + codigoPeriodo + "-" + codigoCliente;
		parametros.put("usuario", usuario.getLogin());

		parametros.put("idSeleccionados", scripts);

		service.deleteLimiteVentaFocalizadoConsejera(parametros);

		return true;
	}
	
//	public List<String> cerosIzquierdaCodigoClienteBuscar(String query){
//		if (log.isDebugEnabled()) {
//			log.debug("Entering 'cerosIzquierdaCodigoClienteBuscar' method");
//		}
//		Integer longitud = 9;
//		MantenimientoSTOLimiteVentaFocalizadoConsejeraForm f = (MantenimientoSTOLimiteVentaFocalizadoConsejeraForm)this.formMantenimiento;
//		List<String> listado = new ArrayList<String>();
//		listado.add(StringUtils.leftPad(query, Integer.parseInt(f.getLongitudCodigoCliente()),"0"));
//		f.setCodigoClienteBuscar(StringUtils.leftPad(query, Integer.parseInt(f.getLongitudCodigoCliente()),"0"));
//		return listado;
//	}
//	
	
	public List<String> cerosIzquierdaCodigoClienteBuscar(String query){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cerosIzquierdaCodigoClienteBuscar' method");
		}
		Integer longitud = 9;
		MantenimientoSTOLimiteVentaFocalizadoConsejeraForm f = (MantenimientoSTOLimiteVentaFocalizadoConsejeraForm)this.formMantenimiento;
		List<String> listado = new ArrayList<String>();
		listado.add(StringUtils.leftPad(query, longitud,"0"));
		f.setCodigoCliente(StringUtils.leftPad(query,longitud,"0"));
		return listado;
	}
	
//	public void formatClientes(){
//		MantenimientoSTOLimiteVentaFocalizadoConsejeraForm f = (MantenimientoSTOLimiteVentaFocalizadoConsejeraForm) this.formMantenimiento;
//		String codigoCliente = f.getCodigoCliente();
//		String nuevoNumerosCLientes="";
//		int maxLength = 0;
//		String[] fragmentoTexto = codigoCliente.split(',');//En el ejemplo las palabras en la cadena de texto (la variable cadenaTexto) están separadas por guiones. Al utilizar el método split() la separación viene dada por esos guiones.
//		for (int i = 0; i < fragmentoTexto.length; i++) {
//			if (i>0){
//				nuevoNumerosCLientes = nuevoNumerosCLientes + ',' +leftpad(fragmentoTexto[i], '0', maxLength,"");				
//
//			}else {
//				nuevoNumerosCLientes = nuevoNumerosCLientes +leftpad(fragmentoTexto[i], '0', maxLength, "");
//			}
//			
//		}		
//		ajax.getCodigosErrados( newStringClientes, pais, loadCodigosErradosCallBack);  
//		return newStringClientes;
//	}


	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("MantenimientoSTOLimiteVentaFocalizadoConsejeraAction - setSaveAttributes");

		MantenimientoSTOLimiteVentaFocalizadoConsejeraForm f = (MantenimientoSTOLimiteVentaFocalizadoConsejeraForm) this.formMantenimiento;
		MantenimientoSTOLimiteVentaFocalizadoConsejeraService service = (MantenimientoSTOLimiteVentaFocalizadoConsejeraService) getBean("spusicc.mantenimientoSTOLimiteVentaFocalizadoConsejeraService");

		MantenimientoRECIngresoAtencionesService service2 = (MantenimientoRECIngresoAtencionesService) getBean("spusicc.mantenimientoRECIngresoAtencionesService");
		boolean isArchivo = false;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();

		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("usuario", usuario.getLogin());
		criteria.put("numUnidadesLimite",
				Integer.valueOf(f.getNumUnidadesLimite()));

		/*-------------------------*/
		String[] arrlistaClientes = new String[0];
		List clienteList = new ArrayList(); // result
		Long longitudPais = pais.getLongitudCodigoCliente();
		String codigoCliente = f.getCodigoCliente();
		List listaClientes = this.stoLevantamientoErroresClientesList; // grilla
																		// del
																		// archivo

		Map map = new HashMap();

		// SI cargo consultoras por el archivo
		if (listaClientes != null) {
			isArchivo = true;
			for (int i = 0; i < listaClientes.size(); i++) {

				LabelValue bean = (LabelValue) listaClientes.get(i);
				codigoCliente = bean.getLabel();
				if (codigoCliente != null) {
					clienteList.add(codigoCliente);
				}

				// map = (Map) listaClientes.get(i);
				// codigoCliente = (String) map.get("codigoCliente");
				// clienteList.add(codigoCliente);
			}
		} else {
			// Si es cargado por la caja de texto
			if (codigoCliente.length() > 0)
				arrlistaClientes = codigoCliente.split(",+");
			for (int i = 0; i < arrlistaClientes.length; i++) {
				clienteList.add(StringUtils.leftPad(arrlistaClientes[i],
						longitudPais.intValue(), '0'));
			}
		}
		criteria.put("clienteList", (clienteList == null) ? new ArrayList()
				: clienteList);
		/*-------------------------*/

		int cantidadInsertados = service
				.insertSTOLimiteVentaFocalizadoConsejera(criteria);

		// session.setAttribute("indicadorGrabo", Constants.NUMERO_UNO);
		//
		// if (isArchivo)
		// session.setAttribute("cantidadInsertados", cantidadInsertados);
		// else
		// session.setAttribute("cantidadInsertados", -1);

		f.setEditable(false);

		if (!this.accion.equals(this.ACCION_NUEVO)) {

			Map criteria1 = new HashMap();

			criteria1.put("numUnidadesLimite", f.getNumUnidadesLimite());
			criteria1.put("usuario", usuario.getLogin());
			criteria1.put("codigoPeriodo", f.getCodigoPeriodo());
			criteria1.put("codigoPais", f.getCodigoPais());
			criteria1.put("codigoCliente", f.getCodigoCliente());

			service.updateObjetoSTOLimiteVentaFocalizadoConsejera(criteria1);

			// session.setAttribute("indicadorGrabo", Constants.NUMERO_UNO);
			// session.setAttribute("cantidadInsertados", -1);

			f.setEditable(false);
			f.setNewRecord(true);
		}
		return true;
	}
	
	public Boolean validarConsultora(){
		MantenimientoSTOLimiteVentaFocalizadoConsejeraForm f = (MantenimientoSTOLimiteVentaFocalizadoConsejeraForm) this.formMantenimiento;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		LabelDatosConsultoraValue[] consultora = null;
		consultora = aSvc.getCabeceraConsultoraSimple(f.getCodigoPais(), f.getCodigoCliente());
		if(consultora != null){
			return true;			
		}else{
			return false;
		}
	}

	@Override
	public String setValidarMantenimiento() {
		int flag = 0;
		MantenimientoSTOLimiteVentaFocalizadoConsejeraForm f = (MantenimientoSTOLimiteVentaFocalizadoConsejeraForm) this.formMantenimiento;
		if (f.isNewRecord()) {
			if (f.getCodigoPeriodo() != null && f.getCodigoPeriodo() == "") {
				String mensaje = this
						.getResourceMessage("mensaje.mantenimientoSTOLimiteVentaFocalizadoConsejeraForm.periodo.obligatorio");
				addError("Error : ", mensaje);
				return "";
			}
			if (stoLevantamientoErroresClientesList != null) {
				flag = 1;
			}

			if (flag == 0 && f.getCodigoCliente() != null
					&& f.getCodigoCliente() == "" && f.getClienteFile() != null) {
				String mensaje = this
						.getResourceMessage("mensaje.mantenimientoSTOLimiteVentaFocalizadoConsejeraForm.noCodigonoClienteFile");
				addError("Error : ", mensaje);
				return "";
			}
			
			 if(!validarConsultora()){
				String mensaje = "Antes de Guardar por favor corrija el Codigo del Cliente";
				addError("Error : ", mensaje);
				return "";
		        }
			 
			 if( flag == 0 && f.getCodigoCliente() == ""){
		            String mensaje = "Codigo de Cliente deben estar ingresadas";
					addError("Error : ", mensaje);
					return "";
		        }

		}
		
		   if(f.getNumUnidadesLimite() != null && f.getNumUnidadesLimite() == ""){
				String mensaje = "mensaje.mantenimientoSTOLimiteVentaFocalizadoConsejeraForm.numUnidadesLimite";
				addError("Error : ", mensaje);
				return "";
	        }
		   
		return "";

	}

	public void limpiar() {
		this.stoResumenClientesList = null;
		this.stoLevantamientoErroresClientesList = null;
		this.mostrarGrilla = false;
		this.attachment = null;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("MantenimientoSTOLimiteVentaFocalizadoConsejeraAction - setEditAttributes");
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Long longitud = pais.getLongitudCodigoCliente();

		MantenimientoSTOLimiteVentaFocalizadoConsejeraService service = (MantenimientoSTOLimiteVentaFocalizadoConsejeraService) getBean("spusicc.mantenimientoSTOLimiteVentaFocalizadoConsejeraService");

		MantenimientoSTOLimiteVentaFocalizadoConsejeraForm f = new MantenimientoSTOLimiteVentaFocalizadoConsejeraForm();
		f.setNewRecord(false);
		limpiar();

		if (!this.accion.equals(this.ACCION_NUEVO)) {
			f.setNewRecord(true);
			HashMap<String, Object> objBeanSelect = (HashMap<String, Object>) this.beanRegistroSeleccionado;

			String codigoCliente = (String) objBeanSelect.get("codigoCliente");

			f.setNewRecord(false);

			if (StringUtils.isNotBlank(codigoCliente)) {

				// String numeroUnidadesLimite = (String)
				// objBeanSelect.get("numeroUnidadesLimite");
				// String nombres =(String) objBeanSelect.get("nombres");
				String codigoPeriodo = (String) objBeanSelect
						.get("codigoPeriodo");
				String codigoPais = (String) objBeanSelect.get("codigoPais");
				f.setNewRecord(true);

				Map criteria = new HashMap();

				criteria.put("codigoPais", codigoPais);
				criteria.put("codigoPeriodo", codigoPeriodo);
				criteria.put("codigoCliente", codigoCliente);

				Map objeto = service
						.getObjectoSTOLimiteVentaFocalizadoConsejera(criteria);

				// request.getSession().setAttribute("objeto", objeto);

				f.setCodigoPais((String) objeto.get("codigoPais"));
				f.setCodigoPeriodo((String) objeto.get("codigoPeriodo"));
				f.setCodigoCliente((String) objeto.get("codigoCliente"));
				f.setNumUnidadesLimite(String.valueOf(((BigDecimal) objeto
						.get("numUnidadesLimite")).intValue()));
			}
		}
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		this.mostrarGrilla = false;
		this.mostrarBotonConsultar = false;
	}

	/**
	 * @return the stoLevantamientoErroresClientesList
	 */
	public List getStoLevantamientoErroresClientesList() {
		return stoLevantamientoErroresClientesList;
	}

	/**
	 * @param stoLevantamientoErroresClientesList
	 *            the stoLevantamientoErroresClientesList to set
	 */
	public void setStoLevantamientoErroresClientesList(
			List stoLevantamientoErroresClientesList) {
		this.stoLevantamientoErroresClientesList = stoLevantamientoErroresClientesList;
	}

	/**
	 * @return the stoResumenClientesList
	 */
	public List getStoResumenClientesList() {
		return stoResumenClientesList;
	}

	/**
	 * @param stoResumenClientesList
	 *            the stoResumenClientesList to set
	 */
	public void setStoResumenClientesList(List stoResumenClientesList) {
		this.stoResumenClientesList = stoResumenClientesList;
	}

	/**
	 * @return the stoLimiteVentaFocalizadoConsejeraList
	 */
	public List getStoLimiteVentaFocalizadoConsejeraList() {
		return stoLimiteVentaFocalizadoConsejeraList;
	}

	/**
	 * @param stoLimiteVentaFocalizadoConsejeraList
	 *            the stoLimiteVentaFocalizadoConsejeraList to set
	 */
	public void setStoLimiteVentaFocalizadoConsejeraList(
			List stoLimiteVentaFocalizadoConsejeraList) {
		this.stoLimiteVentaFocalizadoConsejeraList = stoLimiteVentaFocalizadoConsejeraList;
	}

	/**
	 * @return the attachment
	 */
	public String getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment
	 *            the attachment to set
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	/**
	 * @return the mostrarGrilla
	 */
	public Boolean getMostrarGrilla() {
		return mostrarGrilla;
	}

	/**
	 * @param mostrarGrilla
	 *            the mostrarGrilla to set
	 */
	public void setMostrarGrilla(Boolean mostrarGrilla) {
		this.mostrarGrilla = mostrarGrilla;
	}
}