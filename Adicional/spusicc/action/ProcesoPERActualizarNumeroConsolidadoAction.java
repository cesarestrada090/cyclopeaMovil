package biz.belcorp.ssicc.web.spusicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.ProcesoPERActualizarNumeroConsolidadoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.form.ProcesoPERActualizarNumeroConsolidadoForm;

@ManagedBean
@SessionScoped
public class ProcesoPERActualizarNumeroConsolidadoAction extends BaseProcesoAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6024088663671947173L;
	
	private List siccCanalList;
	private List siccAccesoList;
	private List siccSubAccesoList;
	private List siccGrupoProcesoList;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoPERActualizarNumeroConsolidadoForm form= new ProcesoPERActualizarNumeroConsolidadoForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'realize' method");
		}
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		ProcesoPERActualizarNumeroConsolidadoService service = (ProcesoPERActualizarNumeroConsolidadoService) getBean("spusicc.procesoPERActualizarNumeroConsolidadoService");
		ProcesoPERActualizarNumeroConsolidadoForm form = (ProcesoPERActualizarNumeroConsolidadoForm) this.formProceso;
		
		Map criteria = new HashMap();
		criteria.put("codigoCanal", form.getCodigoCanal());
		criteria.put("codigoAcceso", form.getCodigoAcceso());
		criteria.put("codigoSubacceso", form.getCodigoSubacceso());
		criteria.put("codigoGrupoProceso", form.getCodigoGrupoProceso());	
		service.executeActualizarNumeroConsolidado(criteria, usuario);
		this.addInfo("", "proceso.ok");
		
//		params.put("codigoCanal", form.getCodigoCanal());
//		params.put("codigoAcceso", form.getCodigoAcceso());
//		params.put("codigoSubacceso", form.getCodigoSubacceso());
//		params.put("codigoGrupoProceso", form.getCodigoGrupoProceso());
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		
		ProcesoPERActualizarNumeroConsolidadoForm form = (ProcesoPERActualizarNumeroConsolidadoForm) this.formProceso;

		// Cargamos los combos a utilizar
		InterfazSiCCService siccService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		siccCanalList=siccService.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		siccAccesoList=siccService.getAccesosByCanalByCodigoISO(usuario.getIdioma().getCodigoISO());
		siccSubAccesoList=siccService.getSubaccesosByCodigoISO(usuario.getIdioma().getCodigoISO());
		siccGrupoProcesoList=siccService.getGrupoProceso();
		
		form.setCodigoCanal(form.getCodigoCanal());
		form.setCodigoAcceso(form.getCodigoAcceso());
		form.setCodigoSubacceso(form.getCodigoSubacceso());
		form.setCodigoGrupoProceso(form.getCodigoGrupoProceso());
		form.setCodigoPais(pais.getCodigo());
		
		log.debug("Hasta aca todo ok...");
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute (Map params,BaseForm form) throws Exception{
		params=super.prepareParamsBeforeExecute(params,form);
		return params;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	public List getSiccAccesoList() {
		return siccAccesoList;
	}

	public void setSiccAccesoList(List siccAccesoList) {
		this.siccAccesoList = siccAccesoList;
	}

	public List getSiccSubAccesoList() {
		return siccSubAccesoList;
	}

	public void setSiccSubAccesoList(List siccSubAccesoList) {
		this.siccSubAccesoList = siccSubAccesoList;
	}

	public List getSiccGrupoProcesoList() {
		return siccGrupoProcesoList;
	}

	public void setSiccGrupoProcesoList(List siccGrupoProcesoList) {
		this.siccGrupoProcesoList = siccGrupoProcesoList;
	}

	
}

