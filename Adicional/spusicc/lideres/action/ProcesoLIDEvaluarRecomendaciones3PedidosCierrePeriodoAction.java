package biz.belcorp.ssicc.web.spusicc.lideres.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.lideres.ProcesoLIDEvaluarRecomendaciones3PedidosCierrePeriodoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.lideres.form.ProcesoLIDEvaluarRecomendaciones3PedidosCierrePeriodoForm;

@ManagedBean
@SessionScoped
public class ProcesoLIDEvaluarRecomendaciones3PedidosCierrePeriodoAction extends BaseProcesoAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7888407813831134631L;

	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoLIDEvaluarRecomendaciones3PedidosCierrePeriodoForm formProceso = new ProcesoLIDEvaluarRecomendaciones3PedidosCierrePeriodoForm();
		return formProceso;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) throws Exception 
	{
		log.debug("Exectuting action : executeProcess.");

		if (params != null) {
			ProcesoLIDEvaluarRecomendaciones3PedidosCierrePeriodoService procesoLIDEvaluarRecomendaciones3PedidosCierrePeriodoService = (ProcesoLIDEvaluarRecomendaciones3PedidosCierrePeriodoService) getBean("spusicc.procesoLIDEvaluarRecomendaciones3PedidosCierrePeriodoService");

			procesoLIDEvaluarRecomendaciones3PedidosCierrePeriodoService.executeEvaluarRecomendaciones3PedidosCierrePeriodo(params);
		}

		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		log.debug("Executin action : view.");
		ProcesoLIDEvaluarRecomendaciones3PedidosCierrePeriodoForm f = (ProcesoLIDEvaluarRecomendaciones3PedidosCierrePeriodoForm) this.formProceso;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();		        
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		// Carga de los combos Marca, PeriodoProceso
		String codigoPeriodo  = pais.getCodigoPeriodoFacturado();
		f.setMarcaList(reporteService.getMarcas());
		f.setCodigoIdiomaISO(usuario.getIdioma().getCodigoISO());
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setPeriodoProceso(codigoPeriodo);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = new Date(System.currentTimeMillis());
		String fechaProceso = sdf.format(fecha);
		
		f.setFechaProceso(fechaProceso);	
	}
}
