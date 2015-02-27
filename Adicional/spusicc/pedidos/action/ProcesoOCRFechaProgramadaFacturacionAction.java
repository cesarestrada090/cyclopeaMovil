package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.BaseOID;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoOCRFechaProgramadaFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoOCRFechaProgramadaFacturacionForm;

@ManagedBean
@SessionScoped
public class ProcesoOCRFechaProgramadaFacturacionAction extends BaseProcesoAbstractAction{

	private static final long serialVersionUID = 1L;
	private List resultado;
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoOCRFechaProgramadaFacturacionForm form =  new ProcesoOCRFechaProgramadaFacturacionForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		ProcesoOCRFechaProgramadaFacturacionForm f = (ProcesoOCRFechaProgramadaFacturacionForm) this.formProceso;
		f.setFecha(DateUtil.convertDateToString(f.getFechaD()));
		f.setNuevaFecha(DateUtil.convertDateToString(f.getNuevaFechaD()));
		//f.setFechaInicio(DateUtil.convertDateToString(f.getFechaInicioD()));
		ProcesoOCRFechaProgramadaFacturacionService procesoOCRFechaProgramadaFacturacionService = ((ProcesoOCRFechaProgramadaFacturacionService)getBean("spusicc.pedidos.procesoOCRFechaProgramadaFacturacionService"));
		
		Map parametros = new HashMap();
		
		parametros.put("periodo", f.getPeriodo());
		//parametros.put("tipoSolicitud", f.getTipoSolicitud());
		parametros.put("tipoSolicitud", f.getOidTipoSolicitud());
		parametros.put("fecha", f.getFecha());
		parametros.put("nuevaFecha", f.getNuevaFecha());
		parametros.put("updateFecha", "updateFechaProgramada");
		
		String grupoProceso = f.getGrupoProceso();
		if (Constants.NUMERO_CERO.equals(grupoProceso)){
			parametros.put("grupoProceso","");
		}
		else{
			parametros.put("grupoProceso",grupoProceso);
		}
	
		procesoOCRFechaProgramadaFacturacionService.getExeProcFech(parametros);
		
		return params;
	}
	
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setFindAttributes");
		}
		ProcesoOCRFechaProgramadaFacturacionForm f = (ProcesoOCRFechaProgramadaFacturacionForm) this.formProceso;

		AjaxService ajax = (AjaxService) getBean("ajaxService");
		//getRegistroByPeriodoTipoSoliFecha
		
		String fecha = DateUtil.convertDateToString(f.getFechaD());
		String grupoProceso= f.getGrupoProceso();
		String periodo = f.getPeriodo();
		String solicitud = f.getOidTipoSolicitud();
		String valor = ajax.getRegistroByPeriodoTipoSoliFecha(periodo, solicitud, fecha, grupoProceso);
		ArrayList<String> lista = new ArrayList<String>();
		lista.add(valor);
		this.resultado = lista;
		return lista;

	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		ProcesoOCRFechaProgramadaFacturacionForm f = (ProcesoOCRFechaProgramadaFacturacionForm) this.formProceso;
		Pais pais =  this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO);
	     
		MantenimientoOCRPedidoControlFacturacionService service = 
			(MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaPeriodo);
		this.mostrarBotonBuscar = true;
		//request.getSession(true).setAttribute(Constants.TIPO_SOLICITUD_LIST, ((ProcesoOCRFechaProgramadaFacturacionService)getBean("spusicc.procesoOCRFechaProgramadaFacturacionService")).getTiposSolicitudOcr());
		ProcesoOCRFechaProgramadaFacturacionService service2 = (ProcesoOCRFechaProgramadaFacturacionService)getBean("spusicc.pedidos.procesoOCRFechaProgramadaFacturacionService"); 
		List list = service2.getTiposSolicitudOcr();
		String tipoSolicitud = "";
		String oitTipoSolicitud = "";
		 
		if (list != null){
			BaseOID b = (BaseOID)list.get(0);
			tipoSolicitud = b.getDescripcion();
			oitTipoSolicitud = b.getOid().toString();
		}
		f.setTipoSolicitud(tipoSolicitud);
		f.setOidTipoSolicitud(oitTipoSolicitud);
		
		f.setPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));
		f.setNuevaFecha(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));
		f.setFechaD(Calendar.getInstance().getTime());
		f.setNuevaFechaD(Calendar.getInstance().getTime());
	}

	/**
	 * @return the resultado
	 */
	public List getResultado() {
		return resultado;
	}

	/**
	 * @param resultado the resultado to set
	 */
	public void setResultado(List resultado) {
		this.resultado = resultado;
	}
}