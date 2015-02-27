package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoOCRActualizacionGruposProcesoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoOCRActualizacionGruposProcesoForm;

@SessionScoped
@ManagedBean
public class ProcesoOCRActualizacionGruposProcesoAction extends
		BaseProcesoAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2626609196524071545L;
	private List tipoSolicitudList;
	private List resultado;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoOCRActualizacionGruposProcesoForm form = new ProcesoOCRActualizacionGruposProcesoForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		ProcesoOCRActualizacionGruposProcesoForm f = (ProcesoOCRActualizacionGruposProcesoForm) this.formProceso;

		Map parametros = new HashMap();
		f.setFechaInicio(DateUtil.convertDateToString(f.getFechaInicioD()));
		f.setFechaFin(DateUtil.convertDateToString(f.getFechaFinD()));
		parametros.put("periodo", f.getPeriodo());
		parametros.put("tipoSolicitud", f.getTipoSolicitud());
		parametros.put("fechaInicio", f.getFechaInicio());
		parametros.put("fechaFin", f.getFechaFin());

		((ProcesoOCRActualizacionGruposProcesoService) getBean("spusicc.procesoOCRActualizacionGruposProcesoService"))
				.updateGrupoProceso(parametros);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		ProcesoOCRActualizacionGruposProcesoForm f = (ProcesoOCRActualizacionGruposProcesoForm) this.formProceso;

		this.mostrarBotonBuscar = true;
		Map criteriaPeriodo = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO);

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteriaPeriodo);

		ProcesoOCRActualizacionGruposProcesoService procesoservice = (ProcesoOCRActualizacionGruposProcesoService) getBean("spusicc.pedidos.procesoOCRActualizacionGruposProcesoService");
		this.tipoSolicitudList = procesoservice.getTiposSolicitud();
		this.resultado = null;
		f.setPeriodo(controlFacturacion.getCodigoPeriodo());

	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setFindAttributes");
		}
		ProcesoOCRActualizacionGruposProcesoForm f = (ProcesoOCRActualizacionGruposProcesoForm) this.formProceso;
		List<String> solicitudes = Arrays.<String> asList(f.getTipoSolicitud());

		ArrayList<String> valores = new ArrayList<String>(solicitudes);

		AjaxService ajax = (AjaxService) getBean("ajaxService");
		String fechaIni = DateUtil.convertDateToString(f.getFechaInicioD());
		String fechaFin = DateUtil.convertDateToString(f.getFechaFinD());
		String valor = ajax.getSolicitudesGP2ByPeriodoTipoSoliFecha(
				f.getPeriodo(), valores, fechaIni, fechaFin);
		ArrayList<String> lista = new ArrayList<String>();
		lista.add(valor);
		this.resultado = lista;
		return lista;

	}

	/**
	 * @return the tipoSolicitudList
	 */
	public List getTipoSolicitudList() {
		return tipoSolicitudList;
	}

	/**
	 * @param tipoSolicitudList
	 *            the tipoSolicitudList to set
	 */
	public void setTipoSolicitudList(List tipoSolicitudList) {
		this.tipoSolicitudList = tipoSolicitudList;
	}

	/**
	 * @return the resultado
	 */
	public List getResultado() {
		return resultado;
	}

	/**
	 * @param resultado
	 *            the resultado to set
	 */
	public void setResultado(List resultado) {
		this.resultado = resultado;
	}

}
