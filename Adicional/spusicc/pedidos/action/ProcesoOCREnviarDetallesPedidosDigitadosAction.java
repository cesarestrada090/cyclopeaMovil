package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitadoPK;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoOCREnviarDetallesPedidosDigitadosForm;


@ManagedBean
@SessionScoped
public class ProcesoOCREnviarDetallesPedidosDigitadosAction extends BaseInterfazAbstractAction{

	private static final long serialVersionUID = -8287612088064443313L;
	
	private List consultorasList;
	private  boolean validarSTO;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		ProcesoOCREnviarDetallesPedidosDigitadosForm interfazForm= new  ProcesoOCREnviarDetallesPedidosDigitadosForm();
		return interfazForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) 	log.debug("Entering 'setViewAttributes' method");
		
		this.mostrarBotonBuscar=true;
		this.mostrarListaBusqueda=true;
		Map criteriaPeriodo = new HashMap();
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoPais = pais.getCodigo();
		
		criteriaPeriodo.put("codigoPais", codigoPais);
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente

		MantenimientoOCRPedidoControlFacturacionService serviceControl = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceControl.getControlFacturacionById(criteriaPeriodo);
		
		String codigoPeriodo = controlFacturacion.getCodigoPeriodo();	
		
		Map criteria = new HashMap();
		criteria.put("codigoPais",codigoPais);
		criteria.put("codigoPeriodo",codigoPeriodo);
		criteria.put("indOrigen", Constants.STO_ORIGEN_DIGITADO);
		criteria.put("indicadorCarga", Constants.SI);
		
		ProcesoPEDService service = (ProcesoPEDService) getBean("spusicc.procesoPEDService");
		List list = service.getPedidosDigitadosByCriteria(criteria);
		this.consultorasList=list;		
		
		ProcesoOCREnviarDetallesPedidosDigitadosForm f = (ProcesoOCREnviarDetallesPedidosDigitadosForm)this.formInterfaz;
		f.setCodigoPeriodo(codigoPeriodo);
		f.setNumeroPedidos(list.size());
		f.setIndValidacionSTO(Constants.SI);
		GenericoService genericoService = (GenericoService)getBean("genericoService");		
		String showValiAutoPediDigi = genericoService.getParametroPais(codigoPais, Constants.SISTEMA_PED, Constants.PED_PARAM_SHOW_VALI_PEDI_DIGI);		
		f.setShowValiAutoPediDigi(showValiAutoPediDigi);		
		this.validarSTO=true;
	}
	
	@Override
    protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		if (log.isDebugEnabled()) log.debug("Exectuting action : prepareParamsBeforeExecute.");		
		
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codigoPais   =  pais.getCodigo();		
		
		params.put("codigoPais", codigoPais);
		params.put("codigoUsuario", usuario.getLogin());
		params.put("indOrigen", Constants.STO_ORIGEN_DIGITADO);		
		params.put("codTipoDocu", Constants.STO_TIPO_DOCUMENTO_OCC);
		params.put("usuario", usuario);
		
		ProcesoOCREnviarDetallesPedidosDigitadosForm f = (ProcesoOCREnviarDetallesPedidosDigitadosForm)this.formInterfaz;
		params.put("codigoPeriodo", f.getCodigoPeriodo());		
		ProcesoSTOService stoService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");				
		String numLoteSTO = stoService.updateLoteSTO(new TipoDocumentoDigitadoPK(codigoPais,Constants.STO_TIPO_DOCUMENTO_OCC));
		
		log.info("numLoteSTO : "+ numLoteSTO);
		f.setNumLoteSTO(numLoteSTO);
		if(this.isValidarSTO()==true)
			f.setIndValidacionSTO(Constants.SI);
		else
			f.setIndValidacionSTO(Constants.NO);
		params = super.prepareParamsBeforeExecute(params, form);
		params.put("numLoteSTO",numLoteSTO);
		params.put("registrosProcesados", f.getNumeroPedidos());
		params.put("indValidacionSTO",f.getIndValidacionSTO());		
			
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		interfazSiCCService.updateInterfazProcesoBatch(params);		
		
		return params;
	}
	
	 @Override
	 public void afterExecuteInterfazAction(Map<String, Object> params) throws Exception {		
		 InterfazExecutionResult interfazExecutionResult =new  InterfazExecutionResult(); 
		 ProcesoOCREnviarDetallesPedidosDigitadosForm f = (ProcesoOCREnviarDetallesPedidosDigitadosForm)this.formInterfaz;
	        if (interfazExecutionResult.ejecucionCompletada()){
	        	String	indValidacionSTO = f.getIndValidacionSTO();
	        	if (indValidacionSTO!=null && indValidacionSTO.equals(Constants.SI)){
	            	String codigoPais = (String)params.get("codigoPais");
	            	ProcesoSTOExecutionService procesoSTOExecutionService = (ProcesoSTOExecutionService) getBean("spusicc.procesoSTOExecutionService");
	        	   	AccionTipoDocumento accionTipoDocumento = new AccionTipoDocumento(codigoPais,Constants.STO_TIPO_DOCUMENTO_OCC,Constants.STO_ACCI_VALI_AUTO);    			
	            	procesoSTOExecutionService.execute(accionTipoDocumento,params, null);
	            }
	        }
			
	}
	 
	@Override
	protected List setFindAttributes() throws Exception {
		ProcesoOCREnviarDetallesPedidosDigitadosForm f = (ProcesoOCREnviarDetallesPedidosDigitadosForm)this.formInterfaz;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoPais =pais.getCodigo();
		f.setFechaFacturacion(DateUtil.convertDateToString(f.getFechaFacturacionDate()));
		Map criteria = new HashMap();
		criteria.put("codigoPais",codigoPais);
		criteria.put("codigoPeriodo",f.getCodigoPeriodo());
		criteria.put("indOrigen", Constants.STO_ORIGEN_DIGITADO);
		criteria.put("indicadorCarga", Constants.SI);
		criteria.put("fechaFacturacion", f.getFechaFacturacion());
		ProcesoPEDService service = (ProcesoPEDService) getBean("spusicc.procesoPEDService");
		List list = service.getPedidosDigitadosByCriteria(criteria);
		this.consultorasList=list;		
		return list;			
	}
	
	@Override
	public String setValidarInterfaz(){
		ProcesoOCREnviarDetallesPedidosDigitadosForm f = (ProcesoOCREnviarDetallesPedidosDigitadosForm)this.formInterfaz;
		if(f.getNumeroPedidos()==0)			
			return this.getResourceMessage("procesoOCREnviarDetallesPedidosDigitadosForm.sinPedidos");
		return null;
	}		
	
	public List getConsultorasList() {
		return consultorasList;
	}

	public void setConsultorasList(List consultorasList) {
		this.consultorasList = consultorasList;
	}

	public boolean isValidarSTO() {
		return validarSTO;
	}

	public void setValidarSTO(boolean validarSTO) {
		this.validarSTO = validarSTO;
	}
		

}
