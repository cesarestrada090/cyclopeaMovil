package biz.belcorp.ssicc.web.spusicc.fac.action;

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
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.mae.model.Cliente;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.fac.MantenimientoFACGenericoService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoAtencionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.fac.form.ProcesoFACCargaConsultorasAutorFacturacionElectForm;


@ManagedBean
@SessionScoped
public class ProcesoFACCargaConsultorasAutorFacturacionElectAction extends BaseProcesoAbstractAction {

	
	private static final long serialVersionUID = 1L;
	private List siccClienteList = new ArrayList();
	private DataTableModel dataTableResultado = new DataTableModel();
	private String attachment = "";
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("devuelveFormProceso");
		}
		ProcesoFACCargaConsultorasAutorFacturacionElectForm form = new ProcesoFACCargaConsultorasAutorFacturacionElectForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("executeProcess");
		}
		// Extraemos atributos y parámetros a usar
        Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
               
        ProcesoFACCargaConsultorasAutorFacturacionElectForm saveForm = (ProcesoFACCargaConsultorasAutorFacturacionElectForm)formProceso;
        
        MantenimientoFACGenericoService service = (MantenimientoFACGenericoService)getBean("spusicc.mantenimientoFACGenericoService");
        MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
                        
    	List listaClientes = this.getSiccClienteList();
    	log.debug("pedClientesList : "+listaClientes.size());
    	    	
    	int errados = saveForm.getErrados();
    	
    	Map criteria = new HashMap();
    	List listResult = new ArrayList();
    	    		
   		try {
   			
   			Map bean = new HashMap();
   			String indValido = "";
   			String codigoConsultora = "";
   			
   			for (int i = 0; i < listaClientes.size(); i++) {
   				
   				bean = (Map)listaClientes.get(i);
   				indValido = (String)bean.get("indicadorValido");
   				codigoConsultora = (String)bean.get("codigoCliente");
   				
   				if(indValido != null && indValido.equals("1")){
   					
   					Cliente cliente = clienteService.getDatosBasicosCliente(usuario.getCodigoPais(), codigoConsultora);
   					
   					criteria.put("oidCliente", cliente.getOid());
   					criteria.put("usurioCarga", usuario.getLogin());
   					criteria.put("tipoCarga", Constants.UNO);
   					service.updateAutorizacionFacturacionElectronica(criteria);
   					
   				}
   				   				
   			}
   			
   			this.addInfo("Info:", "procesoFACCargaConsultorasAutorFacturacionElectForm.exito");

   		}catch (InvalidIdentifierException iie) {
            String codigo = iie.getIdentifier().toString();
            this.addError("Error:", this.getResourceMessage("errors.invalid.id", new Object[]{codigo}));            
        }catch (InvalidDescriptionException ide) {
            String descripcion = ide.getDescription();
            this.addError("Error:", this.getResourceMessage("errors.invalid.description", new Object[]{descripcion}));
        }
        
        return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		ProcesoFACCargaConsultorasAutorFacturacionElectForm f = (ProcesoFACCargaConsultorasAutorFacturacionElectForm)formProceso;		
		f.setClienteFile(null);
		f.setNroConsultorasConRUC(0);		
	}
	
	public void handleFileUpload(FileUploadEvent event) throws IOException {
		if(log.isDebugEnabled()){
			log.debug("handleFileUpload");
		}
		ProcesoFACCargaConsultorasAutorFacturacionElectForm f = (ProcesoFACCargaConsultorasAutorFacturacionElectForm)formProceso;
		if(event!=null){
			f.setClienteFile(event.getFile());
			setAttachment(f.getClienteFile().getFileName());
			MantenimientoRECIngresoAtencionesService service = (MantenimientoRECIngresoAtencionesService)getBean("spusicc.mantenimientoRECIngresoAtencionesService");
			MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
			
			List listaClientes = new ArrayList();
			UploadedFile archivo = f.getClienteFile();
			Map criteria = new HashMap();
			InputStream is = archivo.getInputstream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			Long longitudPais=this.getmPantallaPrincipalBean().getCurrentCountry().getLongitudCodigoCliente();
			String linea = "";
			String codigoConsultora = "";
			int cont = 0;
			int numRegistros = 0;
			int nroConsultorasConRUC = 0;
			
			f.setNroConsultorasConRUC(nroConsultorasConRUC);
			
			while (true) {
				linea = br.readLine();
				if (linea == null)
					break;

				codigoConsultora = StringUtils.leftPad(linea.trim(), longitudPais.intValue(), '0');
				criteria.put("codigoConsultora",codigoConsultora);
				LabelValue bean = new LabelValue(codigoConsultora, service.getCodigoConsultora(criteria));
				
				//Validamos si tienen como documento principal el RUC, RUC-2
				Map docParmas = new HashMap();
				docParmas.put("siglasDocumento", new String[]{"RUC", "RUC-2"});
				docParmas.put("codigoConsultora", codigoConsultora);
				List documentos = clienteService.getDocumentosConsultora(docParmas);
				
				if(documentos != null && documentos.size() > 0)
				{
					nroConsultorasConRUC++;
					bean.setValue(null); // Para que se marque como ERRONEA y se pint de ROJO
				}
				//
				
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

			if(cont > 0 && nroConsultorasConRUC == 0){
				f.setCodigosErradosFile("Existe(n) "+cont+" codigo(s) errado(s)");
				f.setErrados(cont);
			}
			else if(cont == 0 && nroConsultorasConRUC > 0){
				f.setNroConsultorasConRUC(nroConsultorasConRUC);
				f.setCodigosErradosFile("NO DEBE EXISTIR NINGÚN CÓDIGO DE CONSULTORA CON RUC");
			}
			else if(cont > 0 && nroConsultorasConRUC > 0){
				f.setErrados(cont);
				f.setNroConsultorasConRUC(nroConsultorasConRUC);
				f.setCodigosErradosFile("Existe(n) "+cont+" codigo(s) errado(s) - NO DEBE EXISTIR NINGÚN CÓDIGO DE CONSULTORA CON RUC");
			}
			
			criteria.put("numRegistros",  numRegistros);
			List list2 = new ArrayList();
			list2 = service.getResumenCargaClienteList(criteria);

			this.setSiccClienteList(list);
		}
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public List getSiccClienteList() {
		return siccClienteList;
	}

	public void setSiccClienteList(List siccClienteList) {
		this.siccClienteList = siccClienteList;
	}
}