package biz.belcorp.ssicc.web.spusicc.lideres.action;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import biz.belcorp.ssicc.service.spusicc.lideres.ProcesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoService;
import biz.belcorp.ssicc.service.spusicc.lideres.ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.lideres.form.ProcesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoForm;

@ManagedBean
@SessionScoped
public class ProcesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoAction extends BaseProcesoAbstractAction 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4139426667975096282L;

	private List siccMarcaList;
	private List lidTipoEvaluacionList;
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception 
	{
		ProcesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoForm formProceso = new ProcesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoForm();
		return formProceso;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) throws Exception 
	{
		log.debug("Exectuting action : executeProcess.");
		
		if(params!=null){
			ProcesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoService procesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoService =  
				(ProcesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoService)getBean("spusicc.procesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoService");
			procesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoService.executeEvaluarIngresosEfectivosSeccionCierrePeriodo(params);
		}
		return params;
	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map<String, Object> params, BaseForm form) throws Exception 
	{
		params = super.prepareParamsBeforeExecute(params, form); 
		
		if (log.isDebugEnabled()) {
			log.debug("Exectuting action : prepareParamsBeforeExecute.");
		}

		ProcesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoService procesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoService =  
			(ProcesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoService)getBean("spusicc.procesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoService");

		boolean esCampanaProcesada = procesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoService.verificaCampanaProcesada(params);
		
		if(!esCampanaProcesada) {
			throw new Exception(this.getResourceMessage("procesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoForm.msg.validaCampanaProcesada"));
		}
		
		return params;
	}
	
	@Override
	protected void setViewAtributes() throws Exception 
	{
		log.debug("Executin action : view.");
		ProcesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoForm f = (ProcesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoForm )this.formProceso;

		ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionService procesoRegionService =  (ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionService)getBean("spusicc.procesoLIDEvaluarIngresosEfectivosSeccionCierreRegionService");
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		// Carga de los combos Marca
		this.siccMarcaList =  reporteService.getMarcas();
		
		// Carga del combo tipo Evaluacion
		this.lidTipoEvaluacionList = procesoRegionService.getTiposEvaluacion(null);
		
		//Carga PeriodoProceso
		String codigoPeriodo  = pais.getCodigoPeriodoFacturado();
		f.setCodigoIdiomaISO(usuario.getIdioma().getCodigoISO());
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setTipoEvaluacion(Constants.LID_TIPO_EVALUACION_DEFAULT);
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

	public List getLidTipoEvaluacionList() {
		return lidTipoEvaluacionList;
	}

	public void setLidTipoEvaluacionList(List lidTipoEvaluacionList) {
		this.lidTipoEvaluacionList = lidTipoEvaluacionList;
	}
}
