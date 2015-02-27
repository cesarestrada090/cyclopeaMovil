package biz.belcorp.ssicc.web.spusicc.form;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

@ManagedBean
@SessionScoped
public class ProcesoPERGeneracionCtaCteDocumentoLegalAction extends BaseInterfazAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7887034311729808722L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		ProcesoPERGeneracionCtaCteDocumentoLegalForm form=new ProcesoPERGeneracionCtaCteDocumentoLegalForm();
		// TODO Auto-generated method stub
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		ProcesoPERGeneracionCtaCteDocumentoLegalForm form=(ProcesoPERGeneracionCtaCteDocumentoLegalForm)this.formInterfaz;
		form.setNumeroLote(null);
		
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form)throws Exception{
		params=super.prepareParamsBeforeExecute(params, form);
		return params;
	}

}
