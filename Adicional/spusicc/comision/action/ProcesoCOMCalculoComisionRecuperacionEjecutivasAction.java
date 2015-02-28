package biz.belcorp.ssicc.web.spusicc.comision.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCalculoCalificacionTramoService;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCalculoComisionRecuperacionEjecutivasService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.comision.form.ProcesoCOMCalculoComisionRecuperacionEjecutivasForm;

@ManagedBean
@SessionScoped
public class ProcesoCOMCalculoComisionRecuperacionEjecutivasAction extends BaseProcesoAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8995113674228069855L;
	
	private List siccMarcaList;
	private List siccCanalList;
	private List comTipoComisionistaList;
	private List comTramoEjecutivasList;
	private LabelValue[] codComisionList={};

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoCOMCalculoComisionRecuperacionEjecutivasForm form= new ProcesoCOMCalculoComisionRecuperacionEjecutivasForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		log.debug("Parameters List :  " + params.toString());
		ProcesoCOMCalculoComisionRecuperacionEjecutivasService procesoService = (ProcesoCOMCalculoComisionRecuperacionEjecutivasService)getBean("spusicc.procesoCOMCalculoComisionRecuperacionEjecutivasService");
		Integer  numRow = procesoService.getCalculoComisionRecuperacionEjecutivasList(params);
		if(numRow.intValue()!=0){
			log.debug("Campaña ya fue procesada...!");
			throw new Exception("Ya existe registro(s) con este tipo de filtro : "+"[Pais = "+params.get("codigoPais")
																			    +"][Datos Comision = "+params.get("codComision")
																			    +"][Tipo Comisionista = " +params.get("tipoComisionista")
																			    +"][Campaña de proceso = " +params.get("codigoPeriodo")
																			    +"]");
			
		}
		procesoService.executeCalculoComisionRecuperacionEjecutivas(params);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Executing :  setViewAttributes. ");
		ProcesoCOMCalculoComisionRecuperacionEjecutivasForm form=(ProcesoCOMCalculoComisionRecuperacionEjecutivasForm)this.formProceso;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy"); 
		form.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		form.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		form.setTipoComisionista(Constants.CODIGO_TIPO_COMISIONISTA_DEFAULT);
		form.setAnioInicTramo(sdf.format(new Date(System.currentTimeMillis())));

		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ProcesoCOMCalculoCalificacionTramoService tramoService = (ProcesoCOMCalculoCalificacionTramoService)getBean("spusicc.procesoCOMCalculoCalificacionTramoService");
		
		Map criteria = new HashMap();
		criteria.put("codigoPais",pais.getCodigo());
		criteria.put("estadoCampanha",Constants.NUMERO_CERO);  
		criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); 
		siccMarcaList=service.getMarcas();
		siccCanalList=service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		comTipoComisionistaList=tramoService.getTiposComisionistas(pais.getCodigo());
		comTramoEjecutivasList= tramoService.getTramos(pais.getCodigo());
		
		MantenimientoOCRPedidoControlFacturacionService serviceFaturacion = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceFaturacion.getControlFacturacionById(criteria);
		form.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		
		AjaxService ajax=(AjaxService)getBean("ajaxService");
		codComisionList=ajax.getDatosComisionByTipoComisionista(pais.getCodigo(), Constants.CODIGO_TIPO_COMISIONISTA_DEFAULT, "S");
		
	}

	@Override
	protected Map<String, Object>prepareParamsBeforeExecute(Map params,BaseForm form)
	throws Exception{
		params=super.prepareParamsBeforeExecute(params,form);
		return params;
	}
	
	public void doDatosComision(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("doDatosComision");
		}
//	ProcesoCOMCalculoComisionRecuperacionEjecutivasForm form=(ProcesoCOMCalculoComisionRecuperacionEjecutivasForm)this.formProceso;
		String valor = (String) val.getNewValue();
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		AjaxService ajax=(AjaxService)getBean("ajaxService");
		codComisionList=ajax.getDatosComisionByTipoComisionista(pais.getCodigo(),valor, "S");
		//form.setCodComision(null);
		
	}

	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	public List getComTipoComisionistaList() {
		return comTipoComisionistaList;
	}

	public void setComTipoComisionistaList(List comTipoComisionistaList) {
		this.comTipoComisionistaList = comTipoComisionistaList;
	}

	public List getComTramoEjecutivasList() {
		return comTramoEjecutivasList;
	}

	public void setComTramoEjecutivasList(List comTramoEjecutivasList) {
		this.comTramoEjecutivasList = comTramoEjecutivasList;
	}

	public LabelValue[] getCodComisionList() {
		return codComisionList;
	}

	public void setCodComisionList(LabelValue[] codComisionList) {
		this.codComisionList = codComisionList;
	}
		
}

 






