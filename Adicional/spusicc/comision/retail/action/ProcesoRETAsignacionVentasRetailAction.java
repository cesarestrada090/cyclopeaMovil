package biz.belcorp.ssicc.web.spusicc.comision.retail.action;

import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.comision.retail.ProcesoRETAsignacionVentasRetailService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.comision.retail.form.ProcesoRETAsignacionVentasRetailForm;

@ManagedBean
@SessionScoped
public class ProcesoRETAsignacionVentasRetailAction extends BaseProcesoAbstractAction{

	private static final long serialVersionUID = 2253612708804134433L;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoRETAsignacionVentasRetailForm procesoForm =new ProcesoRETAsignacionVentasRetailForm();		
		return procesoForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executin action : view.");
		
		ProcesoRETAsignacionVentasRetailForm f = (ProcesoRETAsignacionVentasRetailForm )this.formProceso;
		Date hoy = new Date(System.currentTimeMillis());		
		f.setFechaInicialDate(hoy);
		f.setFechaFinalDate(hoy);
		
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		
		log.debug("Exectuting action : executeProcess.");
		ProcesoRETAsignacionVentasRetailForm f = (ProcesoRETAsignacionVentasRetailForm )this.formProceso;
		f.setFechaInicial(DateUtil.convertDateToString(f.getFechaInicialDate()));
		f.setFechaFinal(DateUtil.convertDateToString(f.getFechaFinalDate()));
		if(params!=null){
			ProcesoRETAsignacionVentasRetailService procesoRETAsignacionVentasRetailService =  
				(ProcesoRETAsignacionVentasRetailService)getBean("spusicc.procesoRETAsignacionVentasRetailService");
			procesoRETAsignacionVentasRetailService.executeAsignacionVentasRetail(params);
		}
		return params;
	}
	
	public String setValidarProceso() {
		ProcesoRETAsignacionVentasRetailForm form = (ProcesoRETAsignacionVentasRetailForm) this.formProceso;
	    if (form.getFechaFinalDate().compareTo(form.getFechaInicialDate()) < 0 ){
	    	String mensaje =  this.getResourceMessage("errors.compare.dates");
			return mensaje;
	    }	    					
	    return null;
	}




}
