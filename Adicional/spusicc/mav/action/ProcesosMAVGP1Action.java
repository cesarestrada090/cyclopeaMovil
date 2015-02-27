package biz.belcorp.ssicc.web.spusicc.mav.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spusicc.mav.form.ProcesosMAVGP1Form;

@ManagedBean
@SessionScoped
public class ProcesosMAVGP1Action extends BaseInterfazAbstractAction {

	private static final long serialVersionUID = -3849209546493772124L;
	private List genInterfacesPaquete;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		ProcesosMAVGP1Form form = new ProcesosMAVGP1Form();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		ProcesosMAVGP1Form f = (ProcesosMAVGP1Form)this.formInterfaz;

		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		List lista = new ArrayList();

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoSistema", this.mPantallaPrincipalBean.getCodigoSistema());
		criteria.put("codigoInterfaz", f.getCodigoInterfaz());

		lista = interfazSiCCService.getListaProcesosGenCierreCampania(criteria);
		genInterfacesPaquete =  lista;
		
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {		
		params = super.prepareParamsBeforeExecute(params, form);
		return params;
	}

	/**
	 * @return the genInterfacesPaquete
	 */
	public List getGenInterfacesPaquete() {
		return genInterfacesPaquete;
	}

	/**
	 * @param genInterfacesPaquete the genInterfacesPaquete to set
	 */
	public void setGenInterfacesPaquete(List genInterfacesPaquete) {
		this.genInterfacesPaquete = genInterfacesPaquete;
	}	
}