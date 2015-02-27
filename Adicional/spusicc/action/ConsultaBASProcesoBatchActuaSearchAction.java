
package biz.belcorp.ssicc.web.spusicc.action;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * The Class ProcesoCOMComisionRecuperacionAction.
 */
@ManagedBean  
@SessionScoped
public class ConsultaBASProcesoBatchActuaSearchAction extends BasePopupAbstractAction{

	private static final long serialVersionUID = 1L;	

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes");
		}
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
