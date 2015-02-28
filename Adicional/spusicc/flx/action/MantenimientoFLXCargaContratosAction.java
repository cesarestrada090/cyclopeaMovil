package biz.belcorp.ssicc.web.spusicc.flx.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.flx.model.ConsultoraFlexipago;
import biz.belcorp.ssicc.dao.spusicc.flx.model.ConsultoraFlexipagoPK;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.spusicc.flx.MantenimientoFLXConsultoraService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.util.ExcelUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.flx.form.MantenimientoFLXCargaContratosForm;

@ManagedBean
@SessionScoped
public class MantenimientoFLXCargaContratosAction extends BaseMantenimientoSearchAbstractAction {

	
	private static final long serialVersionUID = 1L;
	private List flxConsultoraObjetadaList = new ArrayList();
	private String flxConsultoraCargaMasivaErroneos = "";
	private DataTableModel dataTableResultado = new DataTableModel();
	private String attachment = "";
	private String id = "";
	
	@Override	
	protected String getSalirForward() {
		return null;
	}
	
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return null;
	}
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("devuelveFormBusqueda");
		}
		MantenimientoFLXCargaContratosForm form = new MantenimientoFLXCargaContratosForm();
		return form;
	}
	
	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes");
		}
		MantenimientoFLXCargaContratosForm form = (MantenimientoFLXCargaContratosForm)formBusqueda;
		MantenimientoFLXConsultoraService service = (MantenimientoFLXConsultoraService)getBean("spusicc.mantenimientoFLXConsultoraService");
		
		Map criteria = BeanUtils.describe(form);
		
		List lista = service.getConsultorasObjetadaByCriteria(criteria);
			
		flxConsultoraObjetadaList =  lista;
		return lista;
	}
	
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setDeleteAttributes");
		}
		
        Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
        
        MantenimientoFLXCargaContratosForm f = (MantenimientoFLXCargaContratosForm)formBusqueda;
        MantenimientoFLXConsultoraService service = (MantenimientoFLXConsultoraService)getBean("spusicc.mantenimientoFLXConsultoraService");

        try{            
            
            log.debug("Id seleccionado = " + id);
            
            if(StringUtils.isNotBlank(id))
            {
            	String []codigosCompuestos = StringUtils.split(id, "~");
            	
            	for(int i=0; i<codigosCompuestos.length; i++)
            	{
            		String []codigoCompuesto = StringUtils.split(codigosCompuestos[i], "|");
            		ConsultoraFlexipagoPK pk = new ConsultoraFlexipagoPK(codigoCompuesto[0], codigoCompuesto[1], codigoCompuesto[2]);
            		
            		//service.deleteCargaContratos(pk, usuario);
            	}
            }
            
            Map criteria = BeanUtils.describe(f);
    		List lista = service.getConsultorasObjetadaByCriteria(criteria);
			
    		this.flxConsultoraObjetadaList =  lista;
    		dataTableResultado = new DataTableModel(flxConsultoraObjetadaList);
    		this.addInfo("Info:", this.getResourceMessage("consultoraFlexipagoObjetada.deleted"));
    		return true;
        }
        catch(Exception ex){
        	log.error(ex.getMessage());
        	this.addError("Error:", this.getResourceMessage("consultoraFlexipagoObjetada.deleted.error"));
        	return false;
        }		
	}
	
	public void ejecutar(ActionEvent e){{
		if(log.isDebugEnabled()){
			log.debug("ejecutar");
		}
		
		MantenimientoFLXCargaContratosForm f = (MantenimientoFLXCargaContratosForm)formBusqueda;
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		
        Map map = new HashMap();
        map.put("codigoPais", pais.getCodigo());
        map.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa
        map.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
        MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
        // Obtiene la informacion de la campaña actual
        PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(map);
        
        log.debug("Campaña actual --> " + controlFacturacion.getCodigoPeriodo());
		
		ExcelUtil excelUtil = null;
		List listaConsultoras = new ArrayList();
		int erroneos = 0;		
		this.setAttachment(f.getClienteFile().getFileName());
		
		try{
			MantenimientoFLXConsultoraService service = (MantenimientoFLXConsultoraService)getBean("spusicc.mantenimientoFLXConsultoraService");
			
			UploadedFile archivo = f.getClienteFile();
			
			excelUtil = new ExcelUtil(archivo.getInputstream());
			excelUtil.initSheet(0);
						
			
			while(excelUtil.hasNext()){
				Map mapRow = excelUtil.next();
				String codigoConsultora = ((String)mapRow.get("0")).substring(0,((String)mapRow.get("0")).length());
				String numeroContrato = ((String)mapRow.get("1")).substring(0,((String)mapRow.get("1")).length());											
				
				Map criteria = new HashMap();
				criteria.put("codigoCliente", codigoConsultora);
				criteria.put("numeroContrato", numeroContrato);
				
				String valido = service.getCodigoConsultoraHabil(codigoConsultora);
				String valido2 = service.getContratoConsultoraHabil(criteria);
				
				ConsultoraFlexipago c = new ConsultoraFlexipago();
								
				if(codigoConsultora.endsWith(".0")){
					codigoConsultora = codigoConsultora.substring(0, codigoConsultora.length()-2);
				}
				
				if(numeroContrato.endsWith(".0")){
					numeroContrato = numeroContrato.substring(0, numeroContrato.length()-2);
				}
				
				
				if(valido.equals("S") && valido2.equals("S"))
					c.setFlagActivo("S");
				else
					c.setFlagActivo("N");
				
				c.setCodigoPais(pais.getCodigo());
				c.setCodigoCliente(codigoConsultora);
				c.setNumeroContrato(numeroContrato);
				c.setCodigoCampanyaFacturacion("Enviado en "+controlFacturacion.getCodigoPeriodo());
				
				if(valido.equals("N")){
					c.setCodigoCampanyaFacturacion("Consultora no se encuentra en el listado de habiles");
				}	
				else{
					if(valido2.equals("N"))
						c.setCodigoCampanyaFacturacion("No existe el número de contrato");
					
				}				
								
				listaConsultoras.add(c);
				
				if(StringUtils.equals(valido, Constants.NO))
					erroneos++;
			}
			excelUtil.cerrar();
			
			this.flxConsultoraObjetadaList =  listaConsultoras;
			
			if(erroneos > 0)
				flxConsultoraCargaMasivaErroneos = Integer.toString(erroneos);
			else
				service.updateEnvioContrato(listaConsultoras);
			
			if(log.isDebugEnabled())
				log.debug("Tamaño de Lista: " + listaConsultoras.size());
			
			this.flxConsultoraObjetadaList = listaConsultoras;
			dataTableResultado = new DataTableModel(flxConsultoraObjetadaList);
			}catch(Exception ex){
				if(excelUtil != null)
					excelUtil.cerrar();
				
				this.addError("Error:", this.getResourceMessage("mantenimientoFLXConsultoraForm.file.load.error"));
			}
		}
		
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		if(log.isDebugEnabled()){
			log.debug("handleFileUpload");
		}
		MantenimientoFLXCargaContratosForm f = (MantenimientoFLXCargaContratosForm)formBusqueda;
		
		if(event != null){
			f.setClienteFile(event.getFile());
			setAttachment(event.getFile().getFileName());
		}
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		return false;
	}
	
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setObtenerRegistroAttributes");
		}
		Map criteria = (HashMap)this.beanRegistroSeleccionado;
		return null;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		this.mostrarBotonBuscar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonEliminar = false;
		MantenimientoFLXCargaContratosForm f = (MantenimientoFLXCargaContratosForm)this.formBusqueda;
		f.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List getFlxConsultoraObjetadaList() {
		return flxConsultoraObjetadaList;
	}

	public void setFlxConsultoraObjetadaList(List flxConsultoraObjetadaList) {
		this.flxConsultoraObjetadaList = flxConsultoraObjetadaList;
	}

	public String getFlxConsultoraCargaMasivaErroneos() {
		return flxConsultoraCargaMasivaErroneos;
	}

	public void setFlxConsultoraCargaMasivaErroneos(
			String flxConsultoraCargaMasivaErroneos) {
		this.flxConsultoraCargaMasivaErroneos = flxConsultoraCargaMasivaErroneos;
	}

	public DataTableModel getDataTableResultado() {
		return dataTableResultado;
	}

	public void setDataTableResultado(DataTableModel dataTableResultado) {
		this.dataTableResultado = dataTableResultado;
	}
}