package biz.belcorp.ssicc.web.spusicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spusicc.form.ProcesoPERCargoAbonoDirectoIncobrableForm;

@ManagedBean
@SessionScoped
public class ProcesoPERCargoAbonoDirectoIncobrableAction extends BaseInterfazAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7795673763992890460L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		ProcesoPERCargoAbonoDirectoIncobrableForm form=new ProcesoPERCargoAbonoDirectoIncobrableForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		ProcesoPERCargoAbonoDirectoIncobrableForm form=(ProcesoPERCargoAbonoDirectoIncobrableForm) this.formInterfaz;
		form.setNumeroLote(null);
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception{
		params=super.prepareParamsBeforeExecute(params, form);
		return params;
		
	}

}
