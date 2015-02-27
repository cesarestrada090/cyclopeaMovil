package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECProductosFFNNEEService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaZonaPOPUPSearchAction;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.BusquedaRECProductoFFNNEESearchForm;

import com.ibatis.sqlmap.engine.accessplan.BaseAccessPlan;

public class BusquedaRECProductosFFNNEESearchAction extends BaseMantenimientoSearchAbstractAction {

	/**
	 * Juan Pablo Pescorán
	 */
	private static final long serialVersionUID = -3303938592762630869L;
	
	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoRECProductosFFNNEEList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		BusquedaRECProductoFFNNEESearchForm b = new BusquedaRECProductoFFNNEESearchForm();
		return b;
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
	protected void setViewAtributes() throws Exception {

		this.mostrarBotonSave = false;
		BusquedaRECProductoFFNNEESearchForm f= (BusquedaRECProductoFFNNEESearchForm) this.formBusqueda;
//		f.setCodigoPais(pais.getCodigo());
//
//		List lista=service.getProductosFFNNEEList(parameterMap);
//		
//		request.getSession().setAttribute(Constants.REC_PRODUCTOS_FFNNEE_LIST,lista);
//		return mapping.findForward("list");
	
		
	}

}
