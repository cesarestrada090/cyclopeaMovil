package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCActualizarSaldosSeguimientoLevantamientosService;
import biz.belcorp.ssicc.service.util.MailUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.ProcesoCCCActualizarSaldosSeguimientoLevantamientosForm;

@ManagedBean
@SessionScoped
public class ProcesoCCCActualizarSaldosSeguimientoLevantamientosAction extends BaseProcesoAbstractAction{

	private static final long serialVersionUID = -6552715815979716274L;
	
	private List siccSociedadList;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoCCCActualizarSaldosSeguimientoLevantamientosForm procesoForm =new ProcesoCCCActualizarSaldosSeguimientoLevantamientosForm();		
		return procesoForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		
		ProcesoCCCActualizarSaldosSeguimientoLevantamientosForm f=(ProcesoCCCActualizarSaldosSeguimientoLevantamientosForm)this.formProceso;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();		
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.siccSociedadList=svc.getSociedadesByCodigoPais(pais.getCodigo());
		f.setCodigoPais(pais.getCodigo());
		
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		
		
		ProcesoCCCActualizarSaldosSeguimientoLevantamientosForm f = (ProcesoCCCActualizarSaldosSeguimientoLevantamientosForm)this.formProceso;
		ProcesoCCCActualizarSaldosSeguimientoLevantamientosService service = (ProcesoCCCActualizarSaldosSeguimientoLevantamientosService) 
														getBean("spusicc.procesoCCCActualizarSaldosSeguimientoLevantamientosService");
		
		Map criteria = new HashMap();
		criteria.put("codigoPais",f.getCodigoPais());
		criteria.put("codigoSociedad",f.getCodigoSociedad());
		service.executeProcesarSaldosSeguimientoLevantamientos(criteria); 
		return params;
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseProcesoAbstractAction#afterExecuteProcess(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
	 */
	public void afterExecuteProcess() {
        
		log.debug("inicio afterExecuteProcessSuccess");		
		ProcesoCCCActualizarSaldosSeguimientoLevantamientosForm f = (ProcesoCCCActualizarSaldosSeguimientoLevantamientosForm) this.formProceso;
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		Map criteria = new HashMap();
		criteria.put("codigoPais",f.getCodigoPais());
		criteria.put("nombreReporte","procesoCCCActualizarSaldos"); //nombre del proceso sirve para buscar en la tabla generica de envios de correo
				
		Map paramReporte = reporteService.getParametrosReporte(criteria);
		paramReporte.put("correosDestinos",(String) paramReporte.get("correoDefault"));
		if(paramReporte!=null){
						
			String enviarCorreo = (String) paramReporte.get("enviarCorreo");						
			if (Constants.SI.equals(enviarCorreo)) {
								MailParams mailParams = new MailParams();
								paramReporte.put("parameterMap",criteria);
								//mailParams.setUsuario(f.get);
								//mailParams.setPais();
								String body = (String) paramReporte.get("bodyTxt");
								mailParams.setQueryParams(paramReporte);
								
								MailUtil mailService = (MailUtil) this.getBean(this.getMailService()); 
								criteria.put("bodyTxt", body);
								//mailService.setPlantillaBodyTxt(body);
								mailService.enviarMail(mailParams);	
				
			} 				
		}
				

		
		log.debug("Finalizando afterExecuteProcessSuccess");
	}

	/**
	 * Devuelve Service a trabajar para el envio de correo del reporte
	 * Dicho metodo debe ser sobreescrito para que devuelva el Service correspondiente al reporte en
	 * ejecución	
	 * @return
	 */
	public String getMailService () {
		String service = "sisicc.mailUtil";// "sisicc.baseMailAbstractService";		
		return service;
	}
	

	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}

}
