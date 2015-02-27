package biz.belcorp.ssicc.web.spusicc.comision.retail.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.comision.retail.ProcesoRETCalculoComisionRetailService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.comision.retail.form.ProcesoRETCalculoComisionRetailForm;
import biz.belcorp.ssicc.web.util.StringUtil;

@ManagedBean
@SessionScoped
public class ProcesoRETCalculoComisionRetailAction extends BaseProcesoAbstractAction{

	private static final long serialVersionUID = 5862333948432163036L;
	
	private List siccRegionList;
	private LabelValue[] siccZonaList;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoRETCalculoComisionRetailForm procesoForm =new ProcesoRETCalculoComisionRetailForm();		
		return procesoForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executin action : view.");
		
		ProcesoRETCalculoComisionRetailForm f = (ProcesoRETCalculoComisionRetailForm )this.formProceso;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		
		Date hoy = new Date(System.currentTimeMillis());
		f.setFechaFinalDate(hoy);
		f.setFechaInicialDate(hoy);
		
		//obtenemos la lista de regiones del pais
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		
		this.siccRegionList=reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion);
		
		
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {		
		log.debug("Exectuting action : executeProcess.");
		
		if(params!=null){
			ProcesoRETCalculoComisionRetailService procesoRETCalculoComisionRetailService =  
				(ProcesoRETCalculoComisionRetailService)getBean("spusicc.procesoRETCalculoComisionRetailService");
			procesoRETCalculoComisionRetailService.executeCalculoComisionRetail(params);
		}
		return params;
	}
	
	/* 
	 * Prepara los datos a ser enviados al proceso
	 * 
	 * @see biz.belcorp.ssicc.web.action.BaseAbstractAction#prepareParamsBeforeExecute(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
	 */	
	protected Map<String, Object> prepareParamsBeforeExecute(Map params) throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Exectuting action : prepareParamsBeforeExecute.");		}

		ProcesoRETCalculoComisionRetailForm f = (ProcesoRETCalculoComisionRetailForm )this.formProceso;
		f.setFechaFinal(DateUtil.convertDateToString(f.getFechaFinalDate()));
		f.setFechaInicial(DateUtil.convertDateToString(f.getFechaInicialDate()));
		params = super.prepareParamsBeforeExecute(params);
		
		if(f.getRegionList() != null && f.getRegionList().length > 0) 
			params.put("regionList", StringUtil.obtieneListaCodigos(f.getRegionList(), "", ""));

		if(f.getZonaList() != null && f.getZonaList().length > 0)
			params.put("zonaList", StringUtil.obtieneListaCodigos(f.getZonaList(), "", ""));
		
		return params;
	}
	
	public void showZonasxRegion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		log.debug("val: " + val.getNewValue().toString());
		
		
		ProcesoRETCalculoComisionRetailForm f = (ProcesoRETCalculoComisionRetailForm )this.formProceso;
		String[] regiones = (String[]) val.getNewValue();
		if (!val.equals(null) && regiones.length > 0) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.setSiccZonaList(ajax.getZonasMultipleByPaisMarcaCanalRegion(
					f.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,Constants.CODIGO_CANAL_DEFAULT, regiones,
					Constants.FORMATO_TOTAL));			
			f.setZonaList(null);
		} else {
			this.siccZonaList = null;
			f.setZonaList(null);			
		}
	}
	
	public String setValidarProceso() {
		ProcesoRETCalculoComisionRetailForm f = (ProcesoRETCalculoComisionRetailForm )this.formProceso;
	    if (f.getFechaFinalDate().compareTo(f.getFechaInicialDate()) < 0 ){
	    	String mensaje =  this.getResourceMessage("errors.compare.dates");
			return mensaje;
	    }	    					
	    return null;	   
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}
	


	

}
