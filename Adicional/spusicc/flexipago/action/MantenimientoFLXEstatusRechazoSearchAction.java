package biz.belcorp.ssicc.web.spusicc.flexipago.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.service.spusicc.flexipago.MantenimientoFLXModeloOtorgamientoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.app.form.ProcesoAPPSecuenciarZonaTerritorioForm;
import biz.belcorp.ssicc.web.spusicc.flexipago.form.MantenimientoFLXEstatusRechazoSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoFLXEstatusRechazoSearchAction extends BaseMantenimientoSearchAbstractAction{

	
	private static final long serialVersionUID = -4915794957803055167L;
	
	private List flxEstatusRechazoList;

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
		MantenimientoFLXEstatusRechazoSearchForm searchForm = new MantenimientoFLXEstatusRechazoSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes - MantenimientoFLXEstatusRechazoSearchAction");
		}
		
		MantenimientoFLXEstatusRechazoSearchForm f = (MantenimientoFLXEstatusRechazoSearchForm) this.formBusqueda;
		MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService)getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");
		String descripcion = f.getDescripcion();
		
		Map params = BeanUtils.describe(f);
		params.put("descripcion", "%" + (StringUtils.isBlank(descripcion)?"":StringUtils.trim(descripcion)));
		
		List estatus = (List)service.getEstatusRechazoByCriteria(params);
		this.flxEstatusRechazoList=estatus;		
		return estatus;
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
		this.mostrarBotonConsultar=false;
		this.mostrarBotonEliminar=false;
		this.mostrarBotonNuevo=false;
		this.mostrarBotonModificar=false;
		this.mostrarBotonSalir=false;
		
	}

	public List getFlxEstatusRechazoList() {
		return flxEstatusRechazoList;
	}

	public void setFlxEstatusRechazoList(List flxEstatusRechazoList) {
		this.flxEstatusRechazoList = flxEstatusRechazoList;
	}
	
}
