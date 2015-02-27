package biz.belcorp.ssicc.web.spusicc.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spusicc.form.ProcesoOCRCalculoDeudaForm;

/**
 * The Class ProcesoOCRCalculoDeudaAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 06/01/2015
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@ManagedBean
@SessionScoped
public class ProcesoOCRCalculoDeudaAction extends BaseInterfazAbstractAction {

	private static final long serialVersionUID = 228196791270413275L;	
	private List siccPeriodoList;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		ProcesoOCRCalculoDeudaForm form = new ProcesoOCRCalculoDeudaForm();
		return form;
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'enter' method");
		}
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		
		List l = reporteService.getListaPeriodosByBasCtrlFact(pais.getCodigo(), "0");
		if (l == null) {
			l = new ArrayList();
		}
		this.siccPeriodoList = l;
		/*
		String codigoInterfaz = "";
		String codigoSistema = "OCR";
		String codigoProcesoBatch = "01"; */
		 
		ProcesoOCRCalculoDeudaForm f = (ProcesoOCRCalculoDeudaForm) this.formInterfaz;		
		f.setCodigoPais(pais.getCodigo());
		/*	
	    f.setCodigoInterfaz(codigoInterfaz);
		f.setCodigoSistema(codigoSistema);
 		f.setCodigoProcesoBatch(codigoProcesoBatch); */
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		params = super.prepareParamsBeforeExecute(params, form);
		//params.put("codigoInterfaz", "");
		return params;
	}	

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseInterfazAbstractAction#continueExecuteInterfaz(java.util.Map)
	 */
	protected boolean continueExecuteInterfaz(Map params) {
		boolean flag = false;
		return flag;
	}

	/**
	 * @return the siccPeriodoList
	 */
	public List getSiccPeriodoList() {
		return siccPeriodoList;
	}

	/**
	 * @param siccPeriodoList the siccPeriodoList to set
	 */
	public void setSiccPeriodoList(List siccPeriodoList) {
		this.siccPeriodoList = siccPeriodoList;
	}

}