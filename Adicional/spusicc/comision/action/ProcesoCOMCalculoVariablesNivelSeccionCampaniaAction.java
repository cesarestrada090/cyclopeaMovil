package biz.belcorp.ssicc.web.spusicc.comision.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCalculoCalificacionTramoService;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCalculoVariablesNivelSeccionCampaniaService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.comision.form.ProcesoCOMCalculoVariablesNivelSeccionCampaniaForm;

@ManagedBean
@SessionScoped
public class ProcesoCOMCalculoVariablesNivelSeccionCampaniaAction extends BaseProcesoAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5140247813815486466L;
	private List siccMarcaList;
	private List siccCanalList;
	private List comTipoComisionistaList;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoCOMCalculoVariablesNivelSeccionCampaniaForm form=new ProcesoCOMCalculoVariablesNivelSeccionCampaniaForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		log.debug("Parameters List :  " + params.toString());
		
		ProcesoCOMCalculoVariablesNivelSeccionCampaniaService procesoService = (ProcesoCOMCalculoVariablesNivelSeccionCampaniaService)getBean("spusicc.ProcesoCOMCalculoVariablesNivelSeccionCampaniaService");
		procesoService.executeCalculoVariablesNivelSeccionCampania(params);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Executing :  setViewAttributes. ");
		ProcesoCOMCalculoVariablesNivelSeccionCampaniaForm form=(ProcesoCOMCalculoVariablesNivelSeccionCampaniaForm) this.formProceso;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ProcesoCOMCalculoCalificacionTramoService tramoService = (ProcesoCOMCalculoCalificacionTramoService)getBean("spusicc.procesoCOMCalculoCalificacionTramoService");
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		form.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		form.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		form.setTipoComisionista(Constants.CODIGO_TIPO_COMISIONISTA_DEFAULT);
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha",Constants.NUMERO_CERO);  
		criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO);
		
		this.siccMarcaList=service.getMarcas();
		this.siccCanalList=service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.comTipoComisionistaList=tramoService.getTiposComisionistas(pais.getCodigo());
		
		MantenimientoOCRPedidoControlFacturacionService serviceFaturacion = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceFaturacion.getControlFacturacionById(criteria);
		
		form.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
	}
	
	@Override
	protected Map<String, Object>prepareParamsBeforeExecute(Map params, BaseForm form)throws Exception{
		params=super.prepareParamsBeforeExecute(params, form);
		return params;
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
	
	

}	





