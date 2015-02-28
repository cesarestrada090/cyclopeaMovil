package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDMontoMinimoService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDMontoMinimoHistoricoSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class MantenimientoPEDMontoMinimoAction extends BasePopupAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9149000756600207373L;

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoPEDMontoMinimoHistoricoSearchForm popupForm = new MantenimientoPEDMontoMinimoHistoricoSearchForm();
		return popupForm;
	}
	
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'buscarhistorico' method");
		}
		MantenimientoPEDMontoMinimoHistoricoSearchForm f = (MantenimientoPEDMontoMinimoHistoricoSearchForm) this.formBusqueda;
		MantenimientoPEDMontoMinimoService servicePed = (MantenimientoPEDMontoMinimoService) getBean("spusicc.mantenimientoPEDMontoMinimoService");

		if (f.getFechaInicioDate() != null)
			f.setFechaInicio(DateUtil.convertDateToString(f
					.getFechaInicioDate()));

		if (f.getFechaFinDate() != null)
			f.setFechaFin(DateUtil.convertDateToString(f.getFechaFinDate()));

		Map params = BeanUtils.describe(f);

		List lista = servicePed.getMontominimoHistoricoList(params);

//		this.pedMontoMinimoList = list;

		return lista;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'consultarhistorico' method");
		}
		
		MantenimientoPEDMontoMinimoHistoricoSearchForm f = new MantenimientoPEDMontoMinimoHistoricoSearchForm();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		f.setFechaInicio("");
		f.setFechaFin("");
		f.setFechaInicioDate(null);
		f.setFechaFinDate(null);
		f.setPeriodoInicio("");
		f.setPeriodoFin("");
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa periodoFin
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  

        MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
        PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
		
		f.setPeriodoInicio(controlFacturacion.getCodigoPeriodo());
		f.setPeriodoFin(controlFacturacion.getCodigoPeriodo());	
	}
}
