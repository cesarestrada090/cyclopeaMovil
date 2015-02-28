package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOCambioTipoOrdenPedidosCargadosService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.sto.form.ProcesoSTOCambioTipoOrdenPedidosCargadosForm;

@ManagedBean  
@SessionScoped
public class ProcesoSTOCambioTipoOrdenPedidosCargadosAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = 1L;
	private List clientesTipoOrdenList = new ArrayList();
	private List codigoClienteList = new ArrayList();
	private String attachment = "";
	protected DataTableModel datatableResultado = new DataTableModel();

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoSTOCambioTipoOrdenPedidosCargadosForm form = new ProcesoSTOCambioTipoOrdenPedidosCargadosForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)throws Exception {
		if(log.isDebugEnabled()){
			log.debug("executeProcess");
		}
		ProcesoSTOCambioTipoOrdenPedidosCargadosForm f = (ProcesoSTOCambioTipoOrdenPedidosCargadosForm)formProceso;		
		ProcesoSTOCambioTipoOrdenPedidosCargadosService service = (ProcesoSTOCambioTipoOrdenPedidosCargadosService)getBean("spusicc.procesoSTOCambioTipoOrdenPedidosCargadosService");		
		List clientesTipoOrdenList = new ArrayList();		
		Object ob = this.getCodigoClienteList();		
		if(ob != null)
			clientesTipoOrdenList = (List)ob;		
		params.put("listaClientes", clientesTipoOrdenList);		
		if(!clientesTipoOrdenList.isEmpty()){
			Map criteria = new HashMap();			
			criteria.put("numeroLote", f.getNumeroLote());
			criteria.put("listaClientes", clientesTipoOrdenList);
			criteria.put("codigoPeriodo", f.getCodigoPeriodo());
			criteria.put("tipoOrden", f.getTipoOrden());			
			service.updateTipoOrdenPedidosCargados(criteria);
		}
		
		this.mostrarBotonExecute = false;
		this.setAttachment("");
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		
		ProcesoSTOCambioTipoOrdenPedidosCargadosForm f = (ProcesoSTOCambioTipoOrdenPedidosCargadosForm)formProceso;		
		Map criteria = new HashMap();
		criteria.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
		criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(criteria);
		// Setea la campaña con el valor del Archivo de control
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setNumeroLote(controlFacturacion.getNumeroLote());
		f.setTipoOrden(Constants.STO_TIPO_ORDEN_PRIMEROS_PEDIDOS);	
		this.mostrarBotonExecute = false;
	}
	
	@Override
	protected void afterExecuteProcessSuccess() {
		if(log.isDebugEnabled()){
			log.debug("afterExecuteProcessSuccess");
		}
		ProcesoSTOCambioTipoOrdenPedidosCargadosService procesoSTOCambioTipoOrdenPedidosCargadosService = (ProcesoSTOCambioTipoOrdenPedidosCargadosService)getBean("spusicc.procesoSTOCambioTipoOrdenPedidosCargadosService");		
		ProcesoSTOCambioTipoOrdenPedidosCargadosForm f = (ProcesoSTOCambioTipoOrdenPedidosCargadosForm)this.formProceso;
		List listaClientes = codigoClienteList;		
		Map criteria = new HashMap();		
		criteria.put("numeroLote", f.getNumeroLote());
		criteria.put("listaClientes", listaClientes);
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());		
		clientesTipoOrdenList = procesoSTOCambioTipoOrdenPedidosCargadosService.getClientesTipoOrdenList(criteria);
		this.datatableResultado = new DataTableModel(clientesTipoOrdenList);
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		if(log.isDebugEnabled()){
			log.debug("handleFileUpload");
		}
		ProcesoSTOCambioTipoOrdenPedidosCargadosForm f = (ProcesoSTOCambioTipoOrdenPedidosCargadosForm)this.formProceso;
		ProcesoSTOCambioTipoOrdenPedidosCargadosService procesoSTOCambioTipoOrdenPedidosCargadosService = (ProcesoSTOCambioTipoOrdenPedidosCargadosService)getBean("spusicc.procesoSTOCambioTipoOrdenPedidosCargadosService");
		List listaClientes = new ArrayList();
		if(event != null){
			f.setClienteFile(event.getFile());
			
			this.setAttachment(event.getFile().getFileName());
			Map params;
			try {
				uploadArchivo();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.mostrarBotonExecute = true;
	}
	
	private void uploadArchivo() throws IOException {
		if(log.isDebugEnabled()){
			log.debug("uploadArchivo");			
		}
		ProcesoSTOCambioTipoOrdenPedidosCargadosForm f = (ProcesoSTOCambioTipoOrdenPedidosCargadosForm) this.formProceso;
		ProcesoSTOCambioTipoOrdenPedidosCargadosService procesoSTOCambioTipoOrdenPedidosCargadosService = (ProcesoSTOCambioTipoOrdenPedidosCargadosService)getBean("spusicc.procesoSTOCambioTipoOrdenPedidosCargadosService");
		List listaClientes = new ArrayList();
		UploadedFile archivo = f.getClienteFile();
		// leyemos el stream de entrada
		InputStream is;
		is = f.getClienteFile().getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String linea;
		
		while(true) {
			linea = br.readLine();
			if (linea == null)
				break;
			
			if(StringUtils.isNotBlank(linea.trim()))
				listaClientes.add(linea.trim());
		}
		is.close();
		Map criteria = new HashMap();
		
		criteria.put("numeroLote", f.getNumeroLote());
		criteria.put("listaClientes", listaClientes);
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());		
		
		if(!listaClientes.isEmpty()){
			clientesTipoOrdenList = procesoSTOCambioTipoOrdenPedidosCargadosService.getClientesTipoOrdenList(criteria);
		}
		
		codigoClienteList = listaClientes;
		
	}

	public List getClientesTipoOrdenList() {
		return clientesTipoOrdenList;
	}

	public void setClientesTipoOrdenList(List clientesTipoOrdenList) {
		this.clientesTipoOrdenList = clientesTipoOrdenList;
	}

	public List getCodigoClienteList() {
		return codigoClienteList;
	}

	public void setCodigoClienteList(List codigoClienteList) {
		this.codigoClienteList = codigoClienteList;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}	

	public DataTableModel getDatatableResultado() {
		return datatableResultado;
	}

	public void setDatatableResultado(DataTableModel datatableResultado) {
		this.datatableResultado = datatableResultado;
	}
}