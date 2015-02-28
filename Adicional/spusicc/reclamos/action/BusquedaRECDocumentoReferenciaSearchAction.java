package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.BusquedaRECDocumentoReferenciaSearchForm;

@ManagedBean
@SessionScoped
public class BusquedaRECDocumentoReferenciaSearchAction extends BasePopupAbstractAction {

	private static final long serialVersionUID = 1L;
	
	private String longitudCampoClientes;
	private List stoDocumentosReferenciaList;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		BusquedaRECDocumentoReferenciaSearchForm busquedaRECDocumentoReferenciaSearchForm  = new BusquedaRECDocumentoReferenciaSearchForm();
		return busquedaRECDocumentoReferenciaSearchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'BusquedaRECDocumentoReferenciaSearchAction - setFindAttributes' method");
		}
		
		BusquedaRECDocumentoReferenciaSearchForm f = (BusquedaRECDocumentoReferenciaSearchForm) this.formBusqueda;
		return this.buscar(f.getCodigoClienteBuscar());
		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'BusquedaRECDocumentoReferenciaSearchAction - setViewAtributes' method");
		}
		
		BusquedaRECDocumentoReferenciaSearchForm f = (BusquedaRECDocumentoReferenciaSearchForm) this.formBusqueda;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService"); 
		Idioma idioma = this.getmPantallaPrincipalBean().getCurrentIdioma();
		Map parameterMap = new HashMap();
		parameterMap.put("codigoIdiomaIso", idioma.getCodigoSiCC());
		parameterMap.put("codigoIdioma", idioma.getCodigoISO());
		String oidIdiomaIso = reporteService.getOidString("getOidIdiomaByCodigoIdiomaIso", parameterMap);
		f.setOidIdioma(oidIdiomaIso);
		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		f.setCodigoClienteBuscar("");
	    longitudCampoClientes = clienteService.getTamanhoNumeroCliente(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo()).toString();
		
	    
	    //Verificamos si cargamos con busqueda por numero de documento
	  	Map<String, String> params = this.parametrosPantalla;
	  	
		//Obtenemos el parametro del codigo de cliente para realizar la busqueda por defecto
	  	String codigoCliente = params.get("codigoCliente");
		
		if(StringUtils.isNotBlank(codigoCliente)){
			f.setCodigoClienteBuscar(codigoCliente);
			Map criteria = new HashMap();
			criteria.put("codigoCliente", codigoCliente);
						
			// Removemos la lista
			stoDocumentosReferenciaList = new ArrayList();
			ProcesoSTOEjecucionValidacionesService service = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
			
			List lista = service.getDocumentosReferenciaConsultora(criteria);		
			this.stoDocumentosReferenciaList = lista;
		}
		else{
			this.stoDocumentosReferenciaList = new ArrayList();
		}		
		
	}
	
	
	private List buscar(String codigoCliente){
		Map criteria = new HashMap();
		criteria.put("codigoCliente", codigoCliente);
					
		// Removemos la lista
		this.stoDocumentosReferenciaList = new ArrayList();		
		ProcesoSTOEjecucionValidacionesService service = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		
		List lista = service.getDocumentosReferenciaConsultora(criteria);		
		return lista;		
	}

	public String getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	public void setLongitudCampoClientes(String longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}

	public List getStoDocumentosReferenciaList() {
		return stoDocumentosReferenciaList;
	}

	public void setStoDocumentosReferenciaList(List stoDocumentosReferenciaList) {
		this.stoDocumentosReferenciaList = stoDocumentosReferenciaList;
	}
}