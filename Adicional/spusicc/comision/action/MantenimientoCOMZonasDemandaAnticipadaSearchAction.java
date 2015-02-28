package biz.belcorp.ssicc.web.spusicc.comision.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMComisionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMZonasDemandaAnticipadaForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMZonasDemandaAnticipadaSearchForm;

/**
 * The Class MantenimientoCOMZonasDemandaAnticipadaSearchAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 08/01/2015
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@ManagedBean
@SessionScoped
public class MantenimientoCOMZonasDemandaAnticipadaSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -8031202878227954106L;
	private String codigoZonaAntiguo;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoCOMZonasDemandaAnticipadaForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCOMZonasDemandaAnticipadaSearchForm searchForm = new MantenimientoCOMZonasDemandaAnticipadaSearchForm();
		return searchForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		MantenimientoCOMZonasDemandaAnticipadaSearchForm f = (MantenimientoCOMZonasDemandaAnticipadaSearchForm) this.formBusqueda;
		f.setCodigoPais(this.mPantallaPrincipalBean.getCountryCode());
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setFindAttributes' method");
		}
		MantenimientoCOMComisionService service = (MantenimientoCOMComisionService) 
					getBean("spusicc.mantenimientoCOMComisionService");
		MantenimientoCOMZonasDemandaAnticipadaSearchForm f = (MantenimientoCOMZonasDemandaAnticipadaSearchForm) this.formBusqueda;
		/* obteniendo valores */
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoZona", f.getCodigoZona());
		criteria.put("descripcionZona", f.getDescripcionZona());
		
		f.setCodigoZona("");
		f.setDescripcionZona("");
		/* Obteniendo Lista */
		List resultado = service.getZonasDemandaAnticipadaDetalle(criteria);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setDeleteAttributes' method");
		}
		
		MantenimientoCOMComisionService service = (MantenimientoCOMComisionService) getBean("spusicc.mantenimientoCOMComisionService");
		MantenimientoCOMZonasDemandaAnticipadaForm f = (MantenimientoCOMZonasDemandaAnticipadaForm) this.formMantenimiento;
		String id = f.getCodigoZona();		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map criteria = new HashMap();
		if (id != null) {
			if (log.isDebugEnabled()) {
				log.debug("Codigo de Zona es: "+ id);
			}
			try {
				criteria.put("codigoZona", id);
				criteria.put("usuario", usuario.getLogin());
				service.deleteZonasDemandaAnticipada(criteria);
				this.getResourceMessage("mantenimientoCOMZonasDemandaAnticipadaForm.delete");
			} catch (Exception e) {
				throw new Exception(this.getResourceMessage("errors.detail"));
			}
		}
		return true;
	}
	
	@Override
	protected String getSalirForward() {
		return "mantenimientoCOMZonasDemandaAnticipadaList";
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method");
		}

		MantenimientoCOMComisionService service = (MantenimientoCOMComisionService) getBean("spusicc.mantenimientoCOMComisionService");
		MantenimientoCOMZonasDemandaAnticipadaForm f = (MantenimientoCOMZonasDemandaAnticipadaForm) this.formMantenimiento;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoZona", f.getCodigoZona());
		criteria.put("usuario", usuario.getLogin());
		
		int validarZona = service.getValidarZonasDemanda(criteria);
		int zonaExiste = service.getValidaExisteZona(criteria);
		
		if(!f.isNewRecord()){
			if (log.isDebugEnabled()) {
				log.debug("Iniciando para modificar Zonas Demanda");
			}
			if (zonaExiste == 1) {
				if (validarZona == 0) {
					log.debug("entrando a update Zona");
					criteria.put("codigoZonaAntiguo", this.codigoZonaAntiguo);
					log.debug("codigo de Zona Antigua: "+ this.codigoZonaAntiguo);
					service.updateZonasDemandaAnticipada(criteria);
				}else {
					throw new Exception(this.getResourceMessage("mantenimientoCOMZonasDemandaAnticipadaForm.error.existe"));				
				}
			}else {
				throw new Exception(this.getResourceMessage("mantenimientoCOMZonasDemandaAnticipadaForm.codigo.invalido"));
			}
		}else {
			if (log.isDebugEnabled()) {
				log.debug("Iniciando para registrar Zonas Demanda");
			}
			if (zonaExiste == 1) {
				if (validarZona == 0) {
					service.insertZonasDemandaAnticipada(criteria);
				}else {
					throw new Exception(this.getResourceMessage("mantenimientoCOMZonasDemandaAnticipadaForm.error.existe"));
				}
			}else {
				throw new Exception(this.getResourceMessage("mantenimientoCOMZonasDemandaAnticipadaForm.codigo.invalido"));
			}
		}
		
		log.debug("redireccion del metodo");
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Map mapa = (Map) this.beanRegistroSeleccionado;	
		
		MantenimientoCOMZonasDemandaAnticipadaForm mantenimientoForm = new MantenimientoCOMZonasDemandaAnticipadaForm();
		mantenimientoForm.setCodigoPais(this.mPantallaPrincipalBean.getCountryCode());		
		if (!this.accion.equals(this.ACCION_NUEVO) ) {  	            
			String codigo = mapa.get("codigoZona").toString(); 		
			String codigoPais = mapa.get("codigoDescripcion").toString(); 
            
            if(codigo != null && codigoPais != null)
            {
    			if (log.isDebugEnabled()) {
    				log.debug("Id seleccionado de la lista: " + codigo + " " + codigoPais);
    			}
    			mantenimientoForm.setCodigoZona(codigo);
    			mantenimientoForm.setNewRecord(false);
    			this.codigoZonaAntiguo = codigo;
            }
        }
        
        return mantenimientoForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoCOMZonasDemandaAnticipadaForm mantenimientoForm = (MantenimientoCOMZonasDemandaAnticipadaForm) this.formMantenimiento;
		boolean isNew = mantenimientoForm.isNewRecord();
		if(isNew){
			return "mantenimientoCOMZonasDemandaAnticipadaForm.add";
		}else{
			return "mantenimientoCOMZonasDemandaAnticipadaForm.update";
		}	
	}

	/**
	 * @return the codigoZonaAntiguo
	 */
	public String getCodigoZonaAntiguo() {
		return codigoZonaAntiguo;
	}

	/**
	 * @param codigoZonaAntiguo the codigoZonaAntiguo to set
	 */
	public void setCodigoZonaAntiguo(String codigoZonaAntiguo) {
		this.codigoZonaAntiguo = codigoZonaAntiguo;
	}

}
