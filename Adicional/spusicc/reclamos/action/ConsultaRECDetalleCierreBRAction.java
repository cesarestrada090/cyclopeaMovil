package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import biz.belcorp.ssicc.service.spusicc.reclamos.ProcesoRECCierreBRService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.ConsultaRECDetalleCierreBRForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class ConsultaRECDetalleCierreBRAction extends BaseMantenimientoSearchAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7564776203526197830L;
	
	private List recListaResultadoBrConsultaDetalleList;
	private DataTableModel detallePopupTableModel;

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
		ConsultaRECDetalleCierreBRForm form = new ConsultaRECDetalleCierreBRForm();
		return form;
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
	{/*
		log.debug("Entering my method 'ProcesoRECCierreBRAction'");

		ProcesoRECCierreBRService service = (ProcesoRECCierreBRService) getBean("spusicc.procesoRECCierreBRService");

		ExternalContext externalContext = FacesContext.getCurrentInstance()	.getExternalContext();
		String numeroLote = externalContext.getRequestParameterMap().get("numeroLoteBR").toString();

		Map criteria = new HashMap();
		criteria.put("numeroLote", numeroLote);

		this.recListaResultadoBrConsultaDetalleList = service.getListaBoletasDetalleBRList(criteria);;	
		
		this.detallePopupTableModel = new DataTableModel(this.recListaResultadoBrConsultaDetalleList);
		
		String ventana = "PF('dialogDetalle').show()";
		this.getRequestContext().execute(ventana);
		this.mostrarBotonSalir = false;
		this.mostrarBotonSave = false;*/
	}
	
	public void mostrarVentana()
	{
		log.debug("Entering my method 'ProcesoRECCierreBRAction'");

		ProcesoRECCierreBRService service = (ProcesoRECCierreBRService) getBean("spusicc.procesoRECCierreBRService");

		ExternalContext externalContext = FacesContext.getCurrentInstance()	.getExternalContext();
		String numeroLote = externalContext.getRequestParameterMap().get("numeroLoteBR").toString();

		Map criteria = new HashMap();
		criteria.put("numeroLote", numeroLote);

		this.recListaResultadoBrConsultaDetalleList = service.getListaBoletasDetalleBRList(criteria);;	
		
		this.detallePopupTableModel = new DataTableModel(this.recListaResultadoBrConsultaDetalleList);
		
		String ventana = "PF('dialogDetalle').show()";
		this.getRequestContext().execute(ventana);
		this.mostrarBotonSalir = false;
		this.mostrarBotonSave = false;		
	}

	//metodo que sale del popup
	public void salirUA(ActionEvent event)
	{
		if(log.isDebugEnabled()){
			log.debug("Entering my method 'salirUA'");
		}
		String ventana = "PF('dialogDetalle').hide()";
		this.getRequestContext().execute(ventana);
		this.mostrarBotonSalir = true;
	}
	
	public List getRecListaResultadoBrConsultaDetalleList() {
		return recListaResultadoBrConsultaDetalleList;
	}

	public void setRecListaResultadoBrConsultaDetalleList(
			List recListaResultadoBrConsultaDetalleList) {
		this.recListaResultadoBrConsultaDetalleList = recListaResultadoBrConsultaDetalleList;
	}

	public DataTableModel getDetallePopupTableModel() {
		return detallePopupTableModel;
	}

	public void setDetallePopupTableModel(DataTableModel detallePopupTableModel) {
		this.detallePopupTableModel = detallePopupTableModel;
	}
}
