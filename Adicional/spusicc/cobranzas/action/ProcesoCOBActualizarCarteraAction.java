package biz.belcorp.ssicc.web.spusicc.cobranzas.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ProcesoCOBActualizarCarteraService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.ProcesoCOBActualizarCarteraForm;

@ManagedBean
@SessionScoped
public class ProcesoCOBActualizarCarteraAction extends BaseProcesoAbstractAction{

	private static final long serialVersionUID = 1021261530354760020L;
	
	private List siccSociedadList;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoCOBActualizarCarteraForm procesoForm =new ProcesoCOBActualizarCarteraForm();		
		return procesoForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("JFA Entrando setViewAttributes");
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();		
				
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.siccSociedadList=	service.getSociedadesByCodigoPais(pais.getCodigo());			 		
	
		ProcesoCOBActualizarCarteraForm f = (ProcesoCOBActualizarCarteraForm) this.formProceso;
		
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		
		log.debug("Entrando executeProcess");	
		
		ProcesoCOBActualizarCarteraService service = (ProcesoCOBActualizarCarteraService)getBean("spusicc.procesoCOBActualizarCarteraService");
		service.executeActualizarCartera(params);
		
		service.executeEnvioMail(params);
		
		return params;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseProcesoHiloAbstractAction#prepareParamsBeforeExecute(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
	 */
	protected Map<String, Object> prepareParamsBeforeExecute(Map params) throws Exception {
		params = super.prepareParamsBeforeExecute(params);
		Usuario usuario =this.mPantallaPrincipalBean.getCurrentUser();
		ProcesoCOBActualizarCarteraForm f = (ProcesoCOBActualizarCarteraForm)this.formProceso;
		params.put("codigoPais", f.getCodigoPais());
		params.put("codigoSociedad", f.getCodigoSociedad());
		params.put("codigoModulo",f.getCodigoModulo());
		params.put("codigoProceso",f.getCodigoProceso());
		params.put("codigoUsuario",usuario.getLogin());
		return params;
	}

	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}
	
	

}
