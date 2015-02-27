package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCCargarDeudasWebService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.ProcesoCCCCargarDeudasWebForm;

@ManagedBean
@SessionScoped
public class ProcesoCCCCargarDeudasWebAction extends BaseProcesoAbstractAction{

	private static final long serialVersionUID = -1565659720329070564L;
	
	private String codigoSistema;
	private String codigoPrograma;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoCCCCargarDeudasWebForm procesoForm =new ProcesoCCCCargarDeudasWebForm();		
		return procesoForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		
		log.debug("Executing action : setViewAttributes.");
		ProcesoCCCCargarDeudasWebForm f =(ProcesoCCCCargarDeudasWebForm)this.formProceso;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		this.codigoSistema = (String) this.parametrosPantalla.get("codigoSistema");
		this.codigoPrograma = (String) this.parametrosPantalla.get("codigoPrograma");		
				
		f.setCodigoModulo(codigoSistema);
		f.setCodigoPrograma(codigoPrograma);
		f.setCodigoPais(pais.getCodigo());		
		
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		
    	ProcesoCCCCargarDeudasWebService service = (ProcesoCCCCargarDeudasWebService) getBean("spusicc.procesoCCCCargarDeudasWebService");     	
    	service.executeCargarDeudasWeb(params);    	
    	return params;
	}
	
	protected Map<String, Object> prepareParamsBeforeExecute(Map params) throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Exectuting action : prepareParamsBeforeExecute.");
		}
		
		ProcesoCCCCargarDeudasWebForm f = (ProcesoCCCCargarDeudasWebForm)this.formProceso;
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		params = super.prepareParamsBeforeExecute(params);
															
    	params.put("codigoPais",f.getCodigoPais());		
    	params.put("codigoUsuario", usuario.getLogin());
    	params.put("codigoModulo",f.getCodigoModulo());
		params.put("codigoPrograma",f.getCodigoPrograma());
        
		log.debug("Los parametros del prepareParamsBeforeExecute son: "
				+ params.toString());		
		return params;
		
	}

	

}
