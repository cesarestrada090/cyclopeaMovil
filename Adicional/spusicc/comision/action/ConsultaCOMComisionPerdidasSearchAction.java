package biz.belcorp.ssicc.web.spusicc.comision.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCalculoComisionRecuperacionEjecutivasService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.ConsultaCOMComisionPerdidasSearchForm;

@ManagedBean
@SessionScoped
public class ConsultaCOMComisionPerdidasSearchAction extends BaseConsultaAbstractAction{

	
	private static final long serialVersionUID = 7824039200182734803L;
	
	private List siccMarcaList;
	private List siccCanalList;
	private List siccComisionList;
	private List siccRangoPeriodoList;
	private List comComisRecupPerdiList;
	

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaCOMComisionPerdidasSearchForm form = new ConsultaCOMComisionPerdidasSearchForm();
		return form;
	}	

	@Override
	protected void setViewAtributes() throws Exception {
		
		Pais pais =this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario =this.mPantallaPrincipalBean.getCurrentUser();
		ConsultaCOMComisionPerdidasSearchForm f =(ConsultaCOMComisionPerdidasSearchForm)this.formBusqueda;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		Map params = new HashMap();
		params.put("codigoISO", usuario.getIdioma().getCodigoISO());
		params.put("codigoPais", pais.getCodigo());
		
		this.siccMarcaList=service.getMarcas();
		this.siccCanalList=service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccComisionList=service.getComision();
		this.siccRangoPeriodoList=service.getRangosPeriodo();
	
		/*obteniendo periodo actual*/
		PedidoControlFacturacion controlFacturacion = getPedidoControlFacturacion();		
		f.setAnhoProceso(controlFacturacion.getCodigoPeriodo().substring(0,4));
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);	
	
		
	}
	
	/**
	 * Retorna el objeto del periodo de facturacion
	 * @param request
	 * @return
	 */
	private PedidoControlFacturacion getPedidoControlFacturacion() {
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		Pais pais =this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
		criteria.put("codigoPais",pais.getCodigo() );
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa,Estado Abierto 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
        PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
		return controlFacturacion;
	}
	
	@Override
	protected List setFindAttributes() throws Exception {
		log.debug("inicio setFindAttributes");
	
		ConsultaCOMComisionPerdidasSearchForm f = (ConsultaCOMComisionPerdidasSearchForm) this.formBusqueda;
		ProcesoCOMCalculoComisionRecuperacionEjecutivasService service = (ProcesoCOMCalculoComisionRecuperacionEjecutivasService)
																						getBean("spusicc.procesoCOMCalculoComisionRecuperacionEjecutivasService");
		
		Map params = BeanUtils.describe(f);
		String tipoGerente = f.getTipoGerente();
		
		List listComisionPerdidas = new ArrayList();
		
		if(tipoGerente.equals("Z"))
			listComisionPerdidas = service.getComisionRecuperacionPerdidas(params);
		else if(tipoGerente.equals("R"))
			listComisionPerdidas = service.getComisionRecuperacionPerdidasRegion(params);
		
		log.debug("findAttributes " +listComisionPerdidas.size());
		
		if(listComisionPerdidas.size()>0)
			this.comComisRecupPerdiList=listComisionPerdidas;
		else
			this.comComisRecupPerdiList=null;
		return listComisionPerdidas;
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

	public List getSiccComisionList() {
		return siccComisionList;
	}

	public void setSiccComisionList(List siccComisionList) {
		this.siccComisionList = siccComisionList;
	}

	public List getSiccRangoPeriodoList() {
		return siccRangoPeriodoList;
	}

	public void setSiccRangoPeriodoList(List siccRangoPeriodoList) {
		this.siccRangoPeriodoList = siccRangoPeriodoList;
	}

	public List getComComisRecupPerdiList() {
		return comComisRecupPerdiList;
	}

	public void setComComisRecupPerdiList(List comComisRecupPerdiList) {
		this.comComisRecupPerdiList = comComisRecupPerdiList;
	}
	
	

}
