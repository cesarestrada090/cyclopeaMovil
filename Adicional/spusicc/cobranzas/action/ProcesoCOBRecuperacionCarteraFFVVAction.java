package biz.belcorp.ssicc.web.spusicc.cobranzas.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ProcesoCOBRecuperacionCarteraFFVVService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.ProcesoCOBRecuperacionCarteraFFVVForm;



@ManagedBean
@SessionScoped
public class ProcesoCOBRecuperacionCarteraFFVVAction extends BaseProcesoAbstractAction{

	private static final long serialVersionUID = -4212206510168888230L;
	
	private List siccSociedadList;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoCOBRecuperacionCarteraFFVVForm procesoForm =new ProcesoCOBRecuperacionCarteraFFVVForm();		
		return procesoForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();				
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ProcesoCOBRecuperacionCarteraFFVVForm f=(ProcesoCOBRecuperacionCarteraFFVVForm)this.formProceso;
		
		this.siccSociedadList=service.getSociedadesByCodigoPais(pais.getCodigo());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));
		f.setCodigoPeriodo(periodo);
		f.setCodigoPais(pais.getCodigo());
		
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		log.debug("executeProcess - ProcesoCOBRecuperacionCarteraFFVVAction");
		
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		ProcesoCOBRecuperacionCarteraFFVVForm f = (ProcesoCOBRecuperacionCarteraFFVVForm) this.formProceso;
		ProcesoCOBRecuperacionCarteraFFVVService service = (ProcesoCOBRecuperacionCarteraFFVVService)getBean("spusicc.procesoCOBRecuperacionCarteraFFVVService");
		
		params.put("codigoPais", f.getCodigoPais());
		params.put("codigoPeriodo",f.getCodigoPeriodo());
		params.put("codigoSociedad", f.getCodigoSociedad());
		params.put("codigoSistema", f.getCodigoSistema());
		
		params.put("pais",pais);

		service.executeRecuperacionCarteraFFVV(params);		
		return params;
	}

	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}	
	
}
