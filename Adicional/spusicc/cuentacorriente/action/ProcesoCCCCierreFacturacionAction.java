package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCReprocesarInformacionSAPFIService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.ProcesoCCCCierreFacturacionForm;

@ManagedBean
@SessionScoped
public class ProcesoCCCCierreFacturacionAction extends BaseProcesoAbstractAction{

	private static final long serialVersionUID = 6233847498596472802L;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoCCCCierreFacturacionForm procesoForm =new ProcesoCCCCierreFacturacionForm();		
		return procesoForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executing action : setViewAttributes.");
		ProcesoCCCCierreFacturacionForm f =(ProcesoCCCCierreFacturacionForm)this.formProceso;
		Pais pais =this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());		
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {			    	    	
    	ProcesoCCCReprocesarInformacionSAPFIService service = (ProcesoCCCReprocesarInformacionSAPFIService) getBean("spusicc.procesoCCCReprocesarInformacionSAPFIService");   	
    	service.executeCierreFacturacion(params);
    	return params;
	}

	

	

}
