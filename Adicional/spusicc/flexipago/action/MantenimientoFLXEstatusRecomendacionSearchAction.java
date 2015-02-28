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
import biz.belcorp.ssicc.web.spusicc.flexipago.form.MantenimientoFLXEstatusRecomendacionSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoFLXEstatusRecomendacionSearchAction extends BaseMantenimientoSearchAbstractAction{

	private static final long serialVersionUID = -1040073799625215275L;
	
	private List flxEstatusRecomendacionList;

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
		MantenimientoFLXEstatusRecomendacionSearchForm searchForm = new MantenimientoFLXEstatusRecomendacionSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes - MantenimientoFLXEstatusRecomendacionSearchAction");
		}
		
		MantenimientoFLXEstatusRecomendacionSearchForm f = (MantenimientoFLXEstatusRecomendacionSearchForm) this.formBusqueda;
		MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService)getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");
		String descripcion = f.getDescripcion();
		
		Map params = BeanUtils.describe(f);
		params.put("descripcion", "%" + (StringUtils.isBlank(descripcion)?"":StringUtils.trim(descripcion)));
		
		List estatus = (List)service.getEstatusRecomendacionByCriteria(params);
		this.flxEstatusRecomendacionList=estatus;		
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
		this.mostrarBotonModificar=false;
		this.mostrarBotonNuevo=false;
		this.mostrarBotonSalir=false;
		
	}

	public List getFlxEstatusRecomendacionList() {
		return flxEstatusRecomendacionList;
	}

	public void setFlxEstatusRecomendacionList(List flxEstatusRecomendacionList) {
		this.flxEstatusRecomendacionList = flxEstatusRecomendacionList;
	}
	
}
