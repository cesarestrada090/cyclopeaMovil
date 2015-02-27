package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import biz.belcorp.ssicc.dao.model.LabelValueCUV;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDModificacionCUVMaterialesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoPEDModificacionCUVMaterialesForm;

@ManagedBean
@SessionScoped
public class ProcesoPEDModificacionCUVMaterialesAction extends BaseProcesoAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4838263367506648389L;

	private String descripcionCUV;
	private boolean deshabilitado;
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception 
	{
		ProcesoPEDModificacionCUVMaterialesForm procesoForm = new ProcesoPEDModificacionCUVMaterialesForm();
		return procesoForm;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) throws Exception 
	{
		log.debug("paramsssss: "+params);
		
		ProcesoPEDModificacionCUVMaterialesService service = (ProcesoPEDModificacionCUVMaterialesService)getBean("spusicc.procesoPEDModificacionCUVMaterialesService");
		
		service.updateCodVentaByOidProducto(params);
		
		service.saveAuditoria(params);
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
			
		this.deshabilitado = true;
	}
	
	public void consultar(ActionEvent e)
	{		
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		ProcesoPEDModificacionCUVMaterialesForm f = (ProcesoPEDModificacionCUVMaterialesForm)this.formProceso;
		LabelValueCUV descripcion;
		this.deshabilitado = true;
		f.setCuvNuevo("");
		f.setCuvAnterior("");
		f.setOidProducto("");
		this.descripcionCUV = "";
		descripcion = ajax.getDatosCUVByCodigoSAP(f.getCodigoSap());		
		
		if(descripcion != null)
		{
			this.deshabilitado = false;
			this.descripcionCUV = descripcion.getLabel();
			f.setCuvNuevo(descripcion.getPrecio());
			f.setCuvAnterior(descripcion.getPrecio());
			f.setOidProducto(descripcion.getValue());
		}
	}

	public String getDescripcionCUV() {
		return descripcionCUV;
	}

	public void setDescripcionCUV(String descripcionCUV) {
		this.descripcionCUV = descripcionCUV;
	}

	public boolean isDeshabilitado() {
		return deshabilitado;
	}

	public void setDeshabilitado(boolean deshabilitado) {
		this.deshabilitado = deshabilitado;
	}
}
