package biz.belcorp.ssicc.web.spusicc.percepciones.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.percepciones.ProcesoPERGenerarCuentaCorrienteDocumentoLegalService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.percepciones.form.ProcesoPERGenerarCuentaCorrienteDocumentoLegalForm;

@ManagedBean
@SessionScoped
public class ProcesoPERGenerarCuentaCorrienteDocumentoLegalAction extends BaseProcesoAbstractAction{

	private static final long serialVersionUID = 8100185177531697491L;
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoPERGenerarCuentaCorrienteDocumentoLegalForm procesoForm =new ProcesoPERGenerarCuentaCorrienteDocumentoLegalForm();		
		return procesoForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug(" Entrando setViewAttributes");												
		ProcesoPERGenerarCuentaCorrienteDocumentoLegalForm f = (ProcesoPERGenerarCuentaCorrienteDocumentoLegalForm) this.formProceso;		
		String codigoProceso=(String )this.parametrosPantalla.get("codigoProcesoBatch");
		String codigoModulo=(String)this.parametrosPantalla.get("codigoModulo");
		
		Map params = new HashMap();		
		params.put("codigoModulo",codigoModulo);
		params.put("codigoProceso",codigoProceso);	
		f.setCodigoModulo (codigoModulo);
		f.setCodigoProceso (codigoProceso);
		
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {		
		ProcesoPERGenerarCuentaCorrienteDocumentoLegalService service = (ProcesoPERGenerarCuentaCorrienteDocumentoLegalService)getBean("spusicc.procesoPERGenerarCuentaCorrienteDocumentoLegalService");
		service.executeGenerarCuentaCorrienteDocumentoLegal(params);				
		return params;
		
	}
	
	protected Map<String, Object> prepareParamsBeforeExecute(Map params) throws Exception {		
		ProcesoPERGenerarCuentaCorrienteDocumentoLegalForm f = (ProcesoPERGenerarCuentaCorrienteDocumentoLegalForm) this.formProceso;
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		params = super.prepareParamsBeforeExecute(params);
		params.put("codigoModulo",f.getCodigoModulo());
		params.put("codigoProceso",f.getCodigoProceso());
		params.put("usuDigi",usuario.getLastLogin());
		params.put("codigoInterfaz", "PER-P1");
		params.put("tipOrigenDatos", "06");
		params.put("numeroLote", "");		
		return params;
	}


	

}
