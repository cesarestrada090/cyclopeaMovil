package biz.belcorp.ssicc.web.spusicc.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazFACRecepcionarNotasCreditoRetailForm;

@ManagedBean
@SessionScoped
public class InterfazFACRecepcionarNotasCreditoRetailAction extends BaseInterfazAbstractAction {

	private static final long serialVersionUID = 6054253947638783422L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazFACRecepcionarNotasCreditoRetailForm form = new InterfazFACRecepcionarNotasCreditoRetailForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
	}	
}