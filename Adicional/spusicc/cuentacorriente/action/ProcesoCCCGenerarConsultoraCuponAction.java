package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCGenerarConsultoraCuponService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.ProcesoCCCGenerarConsultoraCuponForm;

@ManagedBean
@SessionScoped
public class ProcesoCCCGenerarConsultoraCuponAction extends BaseProcesoAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 916587038703646111L;
	
	private List siccSociedadList;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoCCCGenerarConsultoraCuponForm form= new ProcesoCCCGenerarConsultoraCuponForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		log.debug("JFA Entering executeProcess");
		ProcesoCCCGenerarConsultoraCuponService service = (ProcesoCCCGenerarConsultoraCuponService)getBean("spusicc.procesoCCCGenerarConsultoraCuponService");
		service.executeGenerarConsultoraCupon(params);
				
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		ProcesoCCCGenerarConsultoraCuponForm form=(ProcesoCCCGenerarConsultoraCuponForm)this.formProceso;
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));
		form.setCodigoPeriodo(periodo);		
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();		
				
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");		
		// Cargamos los combos a utilizar	
		//La constante SICC_SOCIEDAD_LIST almacena la lista de Sociedades por Pais
		siccSociedadList=service.getSociedadesByCodigoPais(pais.getCodigo());
													
		log.debug("JFA Todo Ok: Redireccionando");
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute (Map params,BaseForm form) throws Exception{
		params=super.prepareParamsBeforeExecute(params,form);
		return params;
	}

	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}
	
	

}

