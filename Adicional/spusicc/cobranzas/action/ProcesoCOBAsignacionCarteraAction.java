package biz.belcorp.ssicc.web.spusicc.cobranzas.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ProcesoCOBAsignacionCarteraService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.ProcesoCOBAsignacionCarteraForm;

@ManagedBean
@SessionScoped
public class ProcesoCOBAsignacionCarteraAction extends BaseProcesoAbstractAction{

	
	private static final long serialVersionUID = -4087749643546775500L;
	
	private List siccSociedadList;
	private LabelValue[] siccEtapaDeudaList;
	private LabelValue[] siccRegionList;
	private LabelValue[] siccZonaList;
	

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoCOBAsignacionCarteraForm procesoForm =new ProcesoCOBAsignacionCarteraForm();		
		return procesoForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		
	
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ProcesoCOBAsignacionCarteraForm f = (ProcesoCOBAsignacionCarteraForm) this.formProceso;
				
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));
		f.setCodigoPeriodo(periodo);
		
		this.siccSociedadList=service.getSociedadesByCodigoPais(pais.getCodigo());		
	
		log.debug("Todo Ok: Redireccionando");
		
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		params.put("codigoUsuario", usuario.getLogin());		
		ProcesoCOBAsignacionCarteraService service = (ProcesoCOBAsignacionCarteraService)getBean("spusicc.procesoCOBAsignacionCarteraService");
		service.executeAsignacionCartera(params);			
		return params;
	}
	
	
	protected void afterExecuteProcessSuccess() {
		
		log.debug("JFA Entrando afterExecuteProcessSuccess");
			
		ProcesoCOBAsignacionCarteraForm f = (ProcesoCOBAsignacionCarteraForm) this.formProceso;
			
		Map params = new HashMap();			
		params.put("codigoPais", f.getCodigoPais());
		params.put("codigoSociedad", f.getCodigoSociedad());
		params.put("codigoModulo",f.getCodigoModulo());
		params.put("codigoProceso",f.getCodigoProceso());				
	
		ProcesoCOBAsignacionCarteraService service = (ProcesoCOBAsignacionCarteraService)getBean("spusicc.procesoCOBAsignacionCarteraService");
		service.executeEnvioMail(params);
			
		log.debug("JFA Finalizando afterExecuteProcessSuccess");
	}
	
	/* Muestra la etapa por la Sociedad*/
	public void mostrarEtapasxSociedad(ValueChangeEvent val) {
		log.debug(">>mostrarEtapasxSociedad ");
		log.debug("val: " + val.getNewValue().toString());		
		
		ProcesoCOBAsignacionCarteraForm f = (ProcesoCOBAsignacionCarteraForm) this.formProceso;
		String sociedad = (String) val.getNewValue();
		
		if (!val.equals(null)) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.siccEtapaDeudaList=ajax.getEtapasDeudaByPaisSociedad( f.getCodigoPais(), sociedad);
			f.setCodigoEtapaDeuda(null);
		
		} else {
			this.siccEtapaDeudaList=null;
			f.setCodigoEtapaDeuda(null);
		}
	}
	
	/* Muestra la Region por la Etapa*/
	public void mostrarRegionxEtapa(ValueChangeEvent val) {
		log.debug(">>mostrarEtapasxSociedad ");
		log.debug("val: " + val.getNewValue().toString());		
		
		ProcesoCOBAsignacionCarteraForm f = (ProcesoCOBAsignacionCarteraForm) this.formProceso;
		String etapa = (String) val.getNewValue();
		
		
		if (!val.equals(null)) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.siccRegionList=ajax.getRegionesSinAsignarByPaisSociedadEtapaDeudaPeriodo( f.getCodigoPais(), f.getCodigoSociedad(),etapa, f.getCodigoPeriodo());
			f.setCodigoRegion(null);
					
		} else {
			this.siccRegionList=null;
			f.setCodigoRegion(null);
		}
	}
	
	/* Muestra la Zona por la Region*/
	public void mostrarZonaxRegion(ValueChangeEvent val) {
		log.debug(">>mostrarEtapasxSociedad ");
		log.debug("val: " + val.getNewValue().toString());		
		
		ProcesoCOBAsignacionCarteraForm f = (ProcesoCOBAsignacionCarteraForm) this.formProceso;
		String region = (String) val.getNewValue();		
		
		if (!val.equals(null)) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.siccZonaList=ajax.getZonasByPaisSociedadEtapaDeudaRegion(f.getCodigoPais(), f.getCodigoSociedad(),f.getcodigoEtapaDeuda(), region);
			f.setCodigoZona(null);
					
		} else {
			this.siccZonaList=null;
			f.setCodigoZona(null);
		}
	}

	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}

	public LabelValue[] getSiccEtapaDeudaList() {
		return siccEtapaDeudaList;
	}

	public void setSiccEtapaDeudaList(LabelValue[] siccEtapaDeudaList) {
		this.siccEtapaDeudaList = siccEtapaDeudaList;
	}

	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}	
	
}
