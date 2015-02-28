package biz.belcorp.ssicc.web.spusicc.inc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCHabilitacionConcursoCargaPuntajeService;
import biz.belcorp.ssicc.service.spusicc.inc.ProcesoINCCalculoMetasService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.inc.form.ProcesoINCCalculoMetasForm;

@ManagedBean
@SessionScoped
public class ProcesoINCCalculoMetasAction extends
		BaseProcesoAbstractAction {
	private List incentivosConcursosHabilitadosList;
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 916587038703146111L;


	public List getIncentivosConcursosHabilitadosList() {
		return incentivosConcursosHabilitadosList;
	}

	public void setIncentivosConcursosHabilitadosList(
			List incentivosConcursosHabilitadosList) {
		this.incentivosConcursosHabilitadosList = incentivosConcursosHabilitadosList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {

		ProcesoINCCalculoMetasForm form = new ProcesoINCCalculoMetasForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		ProcesoINCCalculoMetasForm form = new ProcesoINCCalculoMetasForm();

		super.executeProceso();
   	 
		ProcesoINCCalculoMetasService service = (ProcesoINCCalculoMetasService) getBean("spusicc.procesoINCCalculoMetasService");
		service.executeCalculoMetas(params);

		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		

	
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();

		ProcesoINCCalculoMetasForm f = (ProcesoINCCalculoMetasForm) formProceso;
		f.reset();
		MantenimientoINCHabilitacionConcursoCargaPuntajeService service = (MantenimientoINCHabilitacionConcursoCargaPuntajeService) getBean("spusicc.mantenimientoINCHabilitacionConcursoCargaPuntajeService");
		//cargando en session la lista de concursos habilitados
		incentivosConcursosHabilitadosList=service.getListConcursoCyzo();
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
		MantenimientoOCRPedidoControlFacturacionService serviceAux = 
			(MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceAux.getControlFacturacionById(criteria);

		// Carga el periodo actual
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
	}



}
