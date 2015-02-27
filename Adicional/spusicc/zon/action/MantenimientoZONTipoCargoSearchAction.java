package biz.belcorp.ssicc.web.spusicc.zon.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.spusicc.zon.MantenimientoZONDirectorioService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.zon.form.MantenimientoZONTipoCargoForm;
import biz.belcorp.ssicc.web.spusicc.zon.form.MantenimientoZONTipoCargoSearchForm;

/**
 * The Class MantenimientoZONTipoCargoSearchAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 26/01/2015
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@ManagedBean
@SessionScoped
public class MantenimientoZONTipoCargoSearchAction extends BaseMantenimientoSearchAbstractAction {
	
	private static final long serialVersionUID = -1509113840168731432L;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoZONTipoCargoForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoZONTipoCargoSearchForm searchForm = new MantenimientoZONTipoCargoSearchForm();
		return searchForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		MantenimientoZONTipoCargoSearchForm  f = (MantenimientoZONTipoCargoSearchForm) this.formBusqueda;
		String codigoPais = this.mPantallaPrincipalBean.getCountryCode();
		f.setCodigoPais(codigoPais);							
		/* obteniendo valores */
		find();
		this.mostrarBotonConsultar = false;				
		this.log.debug("Todo Ok: Redireccionando");	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		MantenimientoZONTipoCargoSearchForm  f = (MantenimientoZONTipoCargoSearchForm) this.formBusqueda;
		MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService) 
														this.getBean("spusicc.mantenimientoZONDirectorioService");

		/* obteniendo valores */
		Map criteria = BeanUtils.describe(f);		
		criteria.put("estado", Constants.ESTADO_ACTIVO);
		/* Obteniendo Lista */
		List resultado = service.getTipoCargo(criteria);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		try {							
			MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService) 
															this.getBean("spusicc.mantenimientoZONDirectorioService");															
			Map map = (Map) this.beanRegistroSeleccionado;
			if(!existeDirectorioVentas(service, map)){
			    service.deleteCargo(map);
				//enviamos el mensaje de satisfaccion
				this.getResourceMessage("mantenimientoZONTipoCargo.cabecera.deleted");
			}else{
				this.getResourceMessage("mantenimientoZONTipoCargo.cabecera.deleted.existe");
			}			
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) {
				error = e.getLocalizedMessage();
			}
			throw new Exception(this.getResourceMessage("errors.detail", new Object[]{ error }));		
		}
		return true;
	}
	
	@Override
	protected String getSalirForward() {
		return "mantenimientoZONTipoCargoList";
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method");
		}
		MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService) 
														this.getBean("spusicc.mantenimientoZONDirectorioService");	
		MantenimientoZONTipoCargoForm f = (MantenimientoZONTipoCargoForm) this.formMantenimiento;
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map map = BeanUtils.describe(f);
		map.put("login", usuario.getLogin());
		if (!f.isNewRecord()) {
				service.updateCargo(map);
		} else {
			try{
				service.insertCargo(map);
			} catch(Exception e) {
				throw new Exception(this.getResourceMessage("mantenimientoZonTipoCargo.created.duplicate"));
			}	
		}
		return true;
	}
	
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		MantenimientoZONTipoCargoForm mantenimientoForm = new MantenimientoZONTipoCargoForm();
		mantenimientoForm.setCodigoPais(this.mPantallaPrincipalBean.getCountryCode());
		if (!this.accion.equals(this.ACCION_NUEVO)) { 
			Map mapa = (Map) this.beanRegistroSeleccionado;
			BeanUtils.copyProperties(mantenimientoForm, mapa);
			mantenimientoForm.setNewRecord(false);
		} else {
			mantenimientoForm.setNewRecord(true);
		}
		return mantenimientoForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoZONTipoCargoForm mantenimientoForm = (MantenimientoZONTipoCargoForm) this.formMantenimiento;
		boolean isNew = mantenimientoForm.isNewRecord();
		if(isNew) {
			return "mantenimientoZonTipoCargo.created";
		} else {
			return "mantenimientoZonTipoCargo.updated";
		}	
	}	


	/**
	 * Devuelve true si elc argo existe en el directorio ventas caso contraio false
	 * @param service
	 * @param map
	 * @return
	 */
	private boolean existeDirectorioVentas(MantenimientoZONDirectorioService service, Map map) {
		List list = service.getDirectorioVentas(map);
		return (list.size()>0?true:false);
	}
}
