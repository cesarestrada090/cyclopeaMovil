package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.spusicc.reclamos.ProcesoRECCierreBRService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.ConsultaRECCierreBRForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class ConsultaRECCierreBRAction extends BaseMantenimientoSearchAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4572455370216664817L;
	
	private List recListaResultadoBrConsultaList;
	
	@ManagedProperty(value="#{consultaRECDetalleCierreBRAction}")
	private ConsultaRECDetalleCierreBRAction popupDetalle;

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
	protected BaseSearchForm devuelveFormBusqueda() throws Exception
	{
		ConsultaRECCierreBRForm searchForm = new ConsultaRECCierreBRForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception 
	{
		ConsultaRECCierreBRForm f = (ConsultaRECCierreBRForm )this.formBusqueda;
		if(f.getFechaDate() != null)
			f.setFecha(DateUtil.convertDateToString(f.getFechaDate()));
		
		ProcesoRECCierreBRService service = (ProcesoRECCierreBRService) getBean("spusicc.procesoRECCierreBRService");
		
		List lista  = new ArrayList();
		try {
			Map criteria = new HashMap();
			criteria.put("numeroLote", f.getNumeroLote());
			criteria.put("resultadoBR",f.getCodigoResultadoBR());
			criteria.put("fecha", f.getFecha());
			
			
			lista = service.getListaBoletasBRList(criteria);
			
//			if(lista != null && lista.size() > 0){
//				this.recListaResultadoBrConsultaList = lista;
//			}else {
//				this.recListaResultadoBrConsultaList = new ArrayList();
//			}
			
		} catch (Exception e) {
			String error = e.getMessage();
			log.debug(error);
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
			throw new  Exception(this.getResourceMessage("errors.detail", new Object[]{error}));
		}
		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		log.debug("Entering my method 'ConsultaRECCierreBRAction'");
		
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
		
		ConsultaRECCierreBRForm f = (ConsultaRECCierreBRForm) this.formBusqueda;
		f.setCodigoResultadoBR("");
		f.setFecha("");
		f.setFechaDate(null);
		f.setNumeroLote("");
	}
	
	public void abrirPopup(ActionEvent event){
		try {
//			popupDetalle.setViewAtributes();
			popupDetalle.mostrarVentana();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public List getRecListaResultadoBrConsultaList() {
		return recListaResultadoBrConsultaList;
	}

	public void setRecListaResultadoBrConsultaList(
			List recListaResultadoBrConsultaList) {
		this.recListaResultadoBrConsultaList = recListaResultadoBrConsultaList;
	}

	public ConsultaRECDetalleCierreBRAction getPopupDetalle() {
		return popupDetalle;
	}

	public void setPopupDetalle(ConsultaRECDetalleCierreBRAction popupDetalle) {
		this.popupDetalle = popupDetalle;
	}
}
