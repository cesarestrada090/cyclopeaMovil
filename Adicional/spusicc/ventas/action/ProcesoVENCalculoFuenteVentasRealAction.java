package biz.belcorp.ssicc.web.spusicc.ventas.action;

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
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.ventas.ProcesoVENService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.ventas.form.ProcesoVENCalculoFuenteVentasRealForm;

@ManagedBean
@SessionScoped
public class ProcesoVENCalculoFuenteVentasRealAction extends BaseProcesoAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1722821800403271914L;
	
	private List siccMarcaList;
	
	private List siccCanalList;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoVENCalculoFuenteVentasRealForm form= new ProcesoVENCalculoFuenteVentasRealForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		params.put("login", usuario.getLogin());

		Map _params = new HashMap();
		_params.put("codigoPais", params.get("codigoPais"));
		_params.put("codigoMarca", params.get("codigoMarca"));
		_params.put("codigoCanal", params.get("codigoCanal"));
		_params.put("codigoPeriodo", params.get("codigoPeriodo"));
		
		log.debug("Los parametros del Generar en el executeProcess son: "
				+ _params.toString());

		ProcesoVENService procesoVENService = (ProcesoVENService) getBean("spusicc.procesoVENService");
		procesoVENService.executeGenerico("procesoVENCalculoFuenteVentasReal", _params);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		ProcesoVENCalculoFuenteVentasRealForm form=(ProcesoVENCalculoFuenteVentasRealForm) this.formProceso;
		Pais pais= this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		Map params = new HashMap();
		params.put("codigoISO", usuario.getIdioma().getCodigoISO());
		params.put("codigoPais", pais.getCodigo());
		
		siccMarcaList=service.getMarcas();
		siccCanalList=service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		
		/*obteniendo periodo actual*/
		PedidoControlFacturacion controlFacturacion = getPedidoControlFacturacion(pais);
		form.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		form.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		form.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);		
	}
	
	private PedidoControlFacturacion getPedidoControlFacturacion(
			Pais pais) {
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
	    criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa,Estado Abierto 
	    criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
	    PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
		return controlFacturacion;
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute (Map params, BaseForm form)
	throws Exception{		
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
}














