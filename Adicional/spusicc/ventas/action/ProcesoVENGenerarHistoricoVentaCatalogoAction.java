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
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.ventas.ProcesoVENService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.ventas.form.ProcesoVENGenerarHistoricoVentaCatalogoForm;

@ManagedBean
@SessionScoped
public class ProcesoVENGenerarHistoricoVentaCatalogoAction extends BaseProcesoAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8381539879366526269L;

	private List siccSociedadList;
	private List siccAlmacenList;
	private List siccMarcaList;
	private List siccCanalList;
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoVENGenerarHistoricoVentaCatalogoForm formProceso = new ProcesoVENGenerarHistoricoVentaCatalogoForm();
		return formProceso;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)throws Exception {
		super.executeProceso();

		Usuario usuario = (Usuario) params.get("usuario");
		params.put("login", usuario.getLogin());

		Map _params = new HashMap();
		_params.put("codigoPais", params.get("codigoPais"));
		_params.put("codigoMarca", params.get("codigoMarca"));
		_params.put("codigoCanal", params.get("codigoCanal"));
		_params.put("codigoCampanha", params.get("codigoCampanha"));
		_params.put("codigoAlmacen", params.get("codigoAlmacen"));
		_params.put("codigoSociedad", params.get("codigoSociedad"));

		log.debug("Los parametros del Generar en el executeProcess son: " + _params.toString());

		ProcesoVENService procesoVENService = (ProcesoVENService) getBean("spusicc.procesoVENService");
		procesoVENService.executeGenerico("procesoVENGenerarHistoricoVentaCatalogo", _params);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception
	{
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ProcesoVENGenerarHistoricoVentaCatalogoForm f = (ProcesoVENGenerarHistoricoVentaCatalogoForm)this.formProceso;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoSociedad(Constants.CODIGO_SOCIEDAD_DEFAULT);
	    f.setCodigoAlmacen(Constants.CODIGO_ALMACEN_DEFAULT);
		f.setCodigoCampanha(null);
		
		Map params = new HashMap();
		params.put("codigoISO", usuario.getIdioma().getCodigoISO());
		params.put("codigoPais", pais.getCodigo());

		this.siccSociedadList = service.getSociedadesByCodigoPais(pais.getCodigo());
		this.siccAlmacenList = service.getAlmacenesByCodigoISOPais(params);
		this.siccMarcaList = service.getMarcas();
		this.siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		
		/*obteniendo periodo actual*/
		PedidoControlFacturacion controlFacturacion = getPedidoControlFacturacion(pais);
		f.setCodigoCampanha(controlFacturacion.getCodigoPeriodo());
		
	}

	/**
	 * Retorna el objeto del periodo de facturacion
	 * @param pais
	 * @return
	 */
	private PedidoControlFacturacion getPedidoControlFacturacion(Pais pais) {
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa,Estado Abierto 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
        PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
		return controlFacturacion;
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params)
			throws Exception {
		// TODO Auto-generated method stub
		return super.prepareParamsBeforeExecute(params);
	}
	
	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}

	public List getSiccAlmacenList() {
		return siccAlmacenList;
	}

	public void setSiccAlmacenList(List siccAlmacenList) {
		this.siccAlmacenList = siccAlmacenList;
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
