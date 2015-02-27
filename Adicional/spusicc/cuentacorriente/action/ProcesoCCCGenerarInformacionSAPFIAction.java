package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCGenerarInformacionSAPFIService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.ProcesoCCCGenerarInformacionSAPFIForm;

@SessionScoped
@ManagedBean
public class ProcesoCCCGenerarInformacionSAPFIAction extends BaseProcesoAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1727484125813360390L;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoCCCGenerarInformacionSAPFIForm form = new ProcesoCCCGenerarInformacionSAPFIForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		ProcesoCCCGenerarInformacionSAPFIForm f = (ProcesoCCCGenerarInformacionSAPFIForm) this.formProceso;
		f.setFechaProcesoHasta(DateUtil.convertDateToString(f.getFechaProcesoHastaD()));
		ProcesoCCCGenerarInformacionSAPFIService service = (ProcesoCCCGenerarInformacionSAPFIService)getBean("spusicc.procesoCCCGenerarInformacionSAPFIService");
		service.executeGenerarInformacionSAPFI(params);
		return params;
	}

}
