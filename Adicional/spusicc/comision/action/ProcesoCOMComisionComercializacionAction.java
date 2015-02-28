package biz.belcorp.ssicc.web.spusicc.comision.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.comision.form.ProcesoCOMComisionComercializacionForm;

@ManagedBean
@SessionScoped
public class ProcesoCOMComisionComercializacionAction extends BaseProcesoAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2226312427050073064L;
	
	private List siccMarcaList;
	
	private List siccCanalList;
	
	private List siccComisionList;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoCOMComisionComercializacionForm form=new ProcesoCOMComisionComercializacionForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		log.debug("Los parametros del Reporte en el executeProcess son: "+ params.toString());		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		reporteService.executeComisionComercializacion(params);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		ProcesoCOMComisionComercializacionForm form=(ProcesoCOMComisionComercializacionForm) this.formProceso;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		form.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		form.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		form.setFechaDesdeD(DateUtil.convertStringToDate(sdf.format(new Date(System.currentTimeMillis()))));
		form.setFechaHastaD(DateUtil.convertStringToDate(sdf.format(new Date(System.currentTimeMillis()))));
		this.siccMarcaList=service.getMarcas();
		this.siccCanalList=service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccComisionList=service.getComision();
		log.debug("Todo Ok: Redireccionando");
	}
	
	@Override
	protected Map<String, Object>prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception{
		ProcesoCOMComisionComercializacionForm f=(ProcesoCOMComisionComercializacionForm) this.formProceso;
		f.setFechaDesde(DateUtil.convertDateToString(f.getFechaDesdeD()));
		f.setFechaHasta(DateUtil.convertDateToString(f.getFechaHastaD()));
		
		params=super.prepareParamsBeforeExecute(params, form);
		return params;
	}
	
	public String setValidarProceso(){
		ProcesoCOMComisionComercializacionForm form= (ProcesoCOMComisionComercializacionForm) this.formProceso;
		if(form.getFechaDesdeD().compareTo(form.getFechaHastaD())>0)
		{
			String mensaje="Fecha Desde debe ser mayor a Fecha Hasta";
			return mensaje;
		}
		return null;
		
	}

	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	public List getSiccComisionList() {
		return siccComisionList;
	}

	public void setSiccComisionList(List siccComisionList) {
		this.siccComisionList = siccComisionList;
	}
	
	
}



