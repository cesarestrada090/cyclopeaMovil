package biz.belcorp.ssicc.web.spusicc.inc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.inc.ProcesoINCCargaOrdenesRetailService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.inc.form.ProcesoINCConsultaOrdenesRetailForm;


@ManagedBean
@SessionScoped
public class ProcesoINCConsultaOrdenesRetailAction extends BaseProcesoAbstractAction{

	private static final long serialVersionUID = 4697321769339094108L;
	
	private List incCargaRetailEcmList;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoINCConsultaOrdenesRetailForm procesoForm =new ProcesoINCConsultaOrdenesRetailForm();		
		return procesoForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		
		ProcesoINCConsultaOrdenesRetailForm f = (ProcesoINCConsultaOrdenesRetailForm) this.formProceso;		
		ProcesoINCCargaOrdenesRetailService service = (ProcesoINCCargaOrdenesRetailService) 
											getBean("spusicc.procesoINCCargaOrdenesRetailService");
		
		//Obtengo las listas a mostrar
		List cargasEjecutadasECM = service.getCargasEjecutadasECM(null);
		this.incCargaRetailEcmList=cargasEjecutadasECM;		
		
		// Consulta de PeriodoProceso y Fecha Facturacion
		f.setOidControlCarga("");
		
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		
		log.debug("Los parametros del Proceso en el executeProcess son: " + params.toString());
		
		ProcesoINCCargaOrdenesRetailService service = (ProcesoINCCargaOrdenesRetailService) 
											getBean("spusicc.procesoINCCargaOrdenesRetailService");
		service.executeAnularCargaOrdenesRetail(params);		
		return params;
	}

	public List getIncCargaRetailEcmList() {
		return incCargaRetailEcmList;
	}

	public void setIncCargaRetailEcmList(List incCargaRetailEcmList) {
		this.incCargaRetailEcmList = incCargaRetailEcmList;
	}	

}
