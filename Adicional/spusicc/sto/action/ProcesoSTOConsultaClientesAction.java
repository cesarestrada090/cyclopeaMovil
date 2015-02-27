package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.sto.form.ProcesoSTOConsultaClientesForm;


@ManagedBean
@SessionScoped
public class ProcesoSTOConsultaClientesAction extends BaseProcesoAbstractAction{

	private static final long serialVersionUID = 5266019788517661773L;
	
	private List stoTipoDocumentoIdentidad;
	private List stoClientesList;
	private String codigoVenta;
	private Boolean mostrar=false;
	

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoSTOConsultaClientesForm procesoForm =new ProcesoSTOConsultaClientesForm();		
		return procesoForm;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) throws Exception {
		 if(params!=null){  	       
 			ProcesoSTOEjecucionValidacionesService service = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
 			service.executeGenerarCodigoClienteSTO(params);
 			this.addError("ERROR: ", this.getResourceMessage("errors.datos.fuentes.busqueda"));		
 		 }

		 return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executing action : setViewAttributes.");
		this.mostrarBotonBuscar=true;
		this.mostrarListaBusqueda=true;
		
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		this.stoTipoDocumentoIdentidad=procesoSTOEjecucionValidacionesService.getTiposDocumentosIdentidadSTO();		
		
	}
	
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Exectuting action : prepareParamsBeforeExecute.");
		}
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();		
		params = super.prepareParamsBeforeExecute(params);
		params.put("codigoPais",pais.getCodigo());

		return params;
		
	}
	
	
	@Override
	protected List setFindAttributes() throws Exception {
		log.debug("setFindAttributes.");		
		Map params = null;
		
		ProcesoSTOConsultaClientesForm f = (ProcesoSTOConsultaClientesForm)this.formProceso;
		ProcesoSTOEjecucionValidacionesService service = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		try {
			params = BeanUtils.describe(f);
			params.put("codigoPais",pais.getCodigo());
			params.put("tipoDocumento",f.getTipoDocumento());
			params.put("numeroDocumento",'%'+f.getNumeroDocumento()+'%');			
			
		} catch (IllegalAccessException e) {			
			e.printStackTrace();
		} catch (InvocationTargetException e) {			
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			
			e.printStackTrace();
		}
		
		List consultaClienteList =  service.getConsultaClientesList(params);
		this.stoClientesList=consultaClienteList;		
		return 	consultaClienteList;	
	}
	
	@Override
	public void executeProceso(ActionEvent actionEvent) {
		mostrarCodigo();
	}
	
	
	public void mostrarCodigo(){
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		this.codigoVenta=ajax.mostrarCodigoVenta(pais.getCodigo());
		this.mostrar=true;
		
	}

	public List getStoTipoDocumentoIdentidad() {
		return stoTipoDocumentoIdentidad;
	}

	public void setStoTipoDocumentoIdentidad(List stoTipoDocumentoIdentidad) {
		this.stoTipoDocumentoIdentidad = stoTipoDocumentoIdentidad;
	}

	public List getStoClientesList() {
		return stoClientesList;
	}

	public void setStoClientesList(List stoClientesList) {
		this.stoClientesList = stoClientesList;
	}

	public String getCodigoVenta() {
		return codigoVenta;
	}

	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}

	public Boolean getMostrar() {
		return mostrar;
	}

	public void setMostrar(Boolean mostrar) {
		this.mostrar = mostrar;
	}
	
	

}
