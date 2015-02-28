package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoRUVEliminacionRUVOtrosCanalesForm;

@ManagedBean
@SessionScoped
public class ProcesoRUVEliminacionRUVOtrosCanalesAction extends BaseMantenimientoSearchAbstractAction{

	private static final long serialVersionUID = -2482617653077037419L;
	
	private List ruvEliminacionSubaccessoList;

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ProcesoRUVEliminacionRUVOtrosCanalesForm searchForm = new ProcesoRUVEliminacionRUVOtrosCanalesForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {		
		
		ProcesoRUVEliminacionRUVOtrosCanalesForm f = (ProcesoRUVEliminacionRUVOtrosCanalesForm) this.formBusqueda;
		ProcesoPEDService servicePed = (ProcesoPEDService) getBean("spusicc.procesoPEDService");		
	
		f.setFechaInicio(DateUtil.convertDateToString(f.getFechaInicioDate()));
		f.setFechaFin(DateUtil.convertDateToString(f.getFechaFinDate()));
		
		Map criteria = new HashMap();
		criteria.put("codigoSubAcceso", f.getCodigoSubAcceso());		
		criteria.put("fechaInicio", f.getFechaInicio());
		criteria.put("fechaFin", f.getFechaFin());
		
		servicePed.eliminaRegistroRUV(criteria);
		this.setViewAtributes();
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAttributes");
		}
		this.mostrarBotonBuscar=false;
		this.mostrarBotonConsultar=false;
		this.mostrarBotonNuevo=false;
		this.mostrarBotonSalir=false;
		this.mostrarBotonModificar=false;
		this.mostrarBotonEliminar=false;
		this.mostrarListaBusqueda=false;
		this.mostrarCriteriosBusqueda=false;
		
		ProcesoRUVEliminacionRUVOtrosCanalesForm f = (ProcesoRUVEliminacionRUVOtrosCanalesForm) this.formBusqueda;		
		ProcesoPEDService servicePed = (ProcesoPEDService) getBean("spusicc.procesoPEDService");
		
		f.setCodigoSubAcceso("");	
		f.setFechaFinDate(null);
		f.setFechaInicioDate(null);
		this.ruvEliminacionSubaccessoList=servicePed.getSubacceso();		
	}
	
	@Override
	public String setValidarMantenimiento() {
		ProcesoRUVEliminacionRUVOtrosCanalesForm f = (ProcesoRUVEliminacionRUVOtrosCanalesForm) this.formBusqueda;
		
		if(f.getFechaFinDate().compareTo(f.getFechaInicioDate())<0){
			String mensaje =  this.getResourceMessage("errors.compare.dates");
			return mensaje;
		}
		Calendar primero= Calendar.getInstance();
		Calendar segundo=Calendar.getInstance();
		primero.setTime(f.getFechaInicioDate());
		segundo.setTime(f.getFechaFinDate());
		
		double douInicio=primero.getTimeInMillis();
		double douFin=segundo.getTimeInMillis();
		double rest=douFin-douInicio;
		double diffDays =rest / (24 * 60 * 60 * 1000);
		
		if(diffDays>31){
			String mensaje ="La diferencia entre ambas fechas es mayor a 31 dias";
			return mensaje;
		}
		return null;
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() {	
		return "procesoRUVEliminacionRUVOtrosCanalesForm.deleted";		
	}

	public List getRuvEliminacionSubaccessoList() {
		return ruvEliminacionSubaccessoList;
	}

	public void setRuvEliminacionSubaccessoList(List ruvEliminacionSubaccessoList) {
		this.ruvEliminacionSubaccessoList = ruvEliminacionSubaccessoList;
	}

}
