package biz.belcorp.ssicc.web.spusicc.comision.retail.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.spusicc.comision.retail.MantenimientoRETPorcentajeComisionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.comision.retail.form.MantenimientoRETPorcentajeComisionForm;
import biz.belcorp.ssicc.web.spusicc.comision.retail.form.MantenimientoRETPorcentajeComisionSearchForm;

/**
 * The Class MantenimientoRETPorcentajeComisionSearchAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 12/01/2015
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@ManagedBean
@SessionScoped
public class MantenimientoRETPorcentajeComisionSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -8397100529567377604L;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoRETPorcentajeComisionForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoRETPorcentajeComisionSearchForm searchForm = new MantenimientoRETPorcentajeComisionSearchForm();
		return searchForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		MantenimientoRETPorcentajeComisionSearchForm f = (MantenimientoRETPorcentajeComisionSearchForm) this.formBusqueda;
		f.setCodigoPais(this.mPantallaPrincipalBean.getCountryCode());
		this.log.debug("Todo Ok: Redireccionando");
		
		/* Obteniendo Lista */
		find();
		
		this.mostrarBotonEliminar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonConsultar = false;
		this.mostrarMantenimientoEnPopup = true;
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		MantenimientoRETPorcentajeComisionService service = (MantenimientoRETPorcentajeComisionService) 
					this.getBean("spusicc.mantenimientoRETPorcentajeComisionService");
		MantenimientoRETPorcentajeComisionSearchForm f = (MantenimientoRETPorcentajeComisionSearchForm) this.formBusqueda;
		/* obteniendo valores */
		Map params = new HashMap();
		params.put("codigoPais",f.getCodigoPais());
		
		/* Obteniendo Lista */
		List resultado = service.getPorcentajeComision(params);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		
		return true;
	}
	
	@Override
	protected String getSalirForward() {
		return "mantenimientoRETPorcentajeComisionList";
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method");
		}
		MantenimientoRETPorcentajeComisionService service = (MantenimientoRETPorcentajeComisionService) 
				getBean("spusicc.mantenimientoRETPorcentajeComisionService");
		MantenimientoRETPorcentajeComisionForm f = (MantenimientoRETPorcentajeComisionForm) this.formMantenimiento;
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		Map params = new HashMap();
		params.put("codigoPais", f.getCodigoPais());
		params.put("porcentajeComision", f.getPorcentajeComision());
		params.put("login", usuario.getLogin());
		
		if (!f.isNewRecord()) {
			service.updatePorcentajeComision(params);
		}
		return true;
	}
	
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Map mapa = (Map) this.beanRegistroSeleccionado;
		MantenimientoRETPorcentajeComisionForm mantenimientoForm = new MantenimientoRETPorcentajeComisionForm();	
		mantenimientoForm.setCodigoPais(this.mPantallaPrincipalBean.getCountryCode());
		if (!this.accion.equals(this.ACCION_NUEVO) ) {  	            
			mantenimientoForm.setCodigoPais(mapa.get("codigoPais").toString());
			mantenimientoForm.setPorcentajeComision(mapa.get("porcentajeComision").toString());
			mantenimientoForm.setNewRecord(false);
        }
		return mantenimientoForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoRETPorcentajeComisionForm mantenimientoForm = (MantenimientoRETPorcentajeComisionForm) this.formMantenimiento;
		boolean isNew = mantenimientoForm.isNewRecord();
		if(!isNew) {
			return "mantenimientoRETPorcentajeComision.updated";
		}
		return null;
	}

}
