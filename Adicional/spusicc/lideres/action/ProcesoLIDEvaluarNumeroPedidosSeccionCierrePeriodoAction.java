package biz.belcorp.ssicc.web.spusicc.lideres.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.lideres.ProcesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.lideres.form.ProcesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoForm;

@ManagedBean
@SessionScoped
public class ProcesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoAction extends BaseProcesoAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5510041352017003997L;

	private List siccMarcaList;
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception
	{
		ProcesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoForm formProceso = new ProcesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoForm();
		return formProceso;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) throws Exception 
	{
		log.debug("Exectuting action : executeProcess.");
		
		if(params!=null){
			ProcesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoService procesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoService =  
							(ProcesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoService)getBean("spusicc.procesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoService");
			procesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoService.executeEvaluarNumeroPedidosSeccionCierrePeriodo(params);
		}
		return params;
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map<String, Object> params, BaseForm form) throws Exception 
	{
		
		
		if (log.isDebugEnabled()) {
			log.debug("Exectuting action : prepareParamsBeforeExecute.");
		}
		
		ProcesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoForm f = (ProcesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoForm)this.formProceso;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		params = super.prepareParamsBeforeExecute(params, form);
		
		params.put("codigoPais",pais.getCodigo());
		params.put("codigoMarca",f.getCodigoMarca());
		params.put("periodoProceso",f.getPeriodoProceso());
		params.put("fechaProceso",f.getFechaProceso());
		
		log.debug("codigoPais"+pais.getCodigo());
		log.debug("codigodMarca"+f.getCodigoMarca());
		log.debug("periodoProceso"+f.getPeriodoProceso());
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		log.debug("Executin action : view.");
		ProcesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoForm f = (ProcesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoForm )this.formProceso;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
        Map criteriaOperacion = new HashMap();
        criteriaOperacion.put("codigoPais", pais.getCodigo());
		        
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");	
		
		// Carga de los combos Marca
		this.siccMarcaList = reporteService.getMarcas();
		
		//Carga PeriodoProceso
		String codigoPeriodo  = pais.getCodigoPeriodoFacturado();
		
		f.setCodigoIdiomaISO(usuario.getIdioma().getCodigoISO());
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setPeriodoProceso(codigoPeriodo);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = new Date(System.currentTimeMillis());
		String fechaProceso = sdf.format(fecha);
		
		f.setFechaProceso(fechaProceso);	
	}

	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}
}
