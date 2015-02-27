package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCGenerarInformacionDatamartService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.ProcesoCCCGenerarInformacionDatamartForm;


@ManagedBean
@SessionScoped
public class ProcesoCCCGenerarInformacionDatamartAction extends BaseProcesoAbstractAction{

	private static final long serialVersionUID = -5477355483906585312L;
	
	private String codigoSistema;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoCCCGenerarInformacionDatamartForm procesoForm =new ProcesoCCCGenerarInformacionDatamartForm();		
		return procesoForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}
		ProcesoCCCGenerarInformacionDatamartForm f=(ProcesoCCCGenerarInformacionDatamartForm)this.formProceso;	
		Pais pais =this.mPantallaPrincipalBean.getCurrentCountry();		
		//String codigoProceso = request.getParameter("codigoProcesoBatch");	
		//session.setAttribute("codigoProcesoBatch", codigoProceso);		
		this.codigoSistema = (String) this.parametrosPantalla.get("codigoTipoOrigenDatos");
		f.setCodigoPais(pais.getCodigo());
		
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		log.debug("JFA Entrando executeProcess");
		
        ProcesoCCCGenerarInformacionDatamartForm f = (ProcesoCCCGenerarInformacionDatamartForm) this.formProceso;       
	
		Usuario usuario =this.mPantallaPrincipalBean.getCurrentUser();		
		params.put("codigoInterface",f.getCodigoProceso());
		params.put("codigoUsuario", usuario.getLogin());		
	
		ProcesoCCCGenerarInformacionDatamartService service = (ProcesoCCCGenerarInformacionDatamartService)getBean("spusicc.procesoCCCGenerarInformacionDatamartService");				
		service.executeGenerarInformacionDatamart(params);		
		log.debug("JFA Finalizando executeProcess");
		return params;
	}

	public String getCodigoSistema() {
		return codigoSistema;
	}

	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}
	

	

}
