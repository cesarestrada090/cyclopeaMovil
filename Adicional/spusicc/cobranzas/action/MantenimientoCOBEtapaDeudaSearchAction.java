package biz.belcorp.ssicc.web.spusicc.cobranzas.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.EtapaDeuda;
import biz.belcorp.ssicc.service.spusicc.cobranzas.MantenimientoCOBGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.MantenimientoCOBEtapaDeudaForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.MantenimientoCOBEtapaDeudaSearchForm;

/**
 * The Class MantenimientoCOBEtapaDeudaSearchAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 16/01/2015
 */
@SuppressWarnings({ "rawtypes" })
@ManagedBean
@SessionScoped
public class MantenimientoCOBEtapaDeudaSearchAction extends BaseMantenimientoSearchAbstractAction {
	
	private static final long serialVersionUID = 2799993778588027202L;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoCOBEtapaDeudaForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCOBEtapaDeudaSearchForm searchForm = new MantenimientoCOBEtapaDeudaSearchForm();
		return searchForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		
		if (log.isDebugEnabled()) {
		   this.log.debug("JFA Entering: setViewAttributes");
		}
		
		MantenimientoCOBEtapaDeudaSearchForm f = (MantenimientoCOBEtapaDeudaSearchForm) this.formBusqueda;
		String codigoPais = this.mPantallaPrincipalBean.getCountryCode();
		f.setCodigoPais(codigoPais);
										
		/* Obteniendo valores */
		find();  
		
		if (log.isDebugEnabled()) {
			this.log.debug("JFA Todo Ok: Redireccionando");
		}		
	}	
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {		
		if (log.isDebugEnabled()) {
			   this.log.debug("JFA Entering: setFindAttributes");
		}
		
		/* obteniendo valores */
		MantenimientoCOBEtapaDeudaSearchForm f = (MantenimientoCOBEtapaDeudaSearchForm) this.formBusqueda;
		
		EtapaDeuda bean = new EtapaDeuda();
		bean.setCodigoPais(f.getCodigoPais());		
									
		/* Obteniendo Lista de Etapas de Deuda*/
		MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) 
		this.getBean("spusicc.mantenimientoCOBGenericoService");
		List resultado = service.getListaEtapaDeuda(bean);
		if (log.isDebugEnabled()) {
			this.log.debug("JFA Todo Ok: Redireccionando");
		}		
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		try {			
			Map mapa = (Map) this.beanRegistroSeleccionado;				
			log.debug("JFA - obtenerKeyMensaje");	
			EtapaDeuda etapaDeuda = new EtapaDeuda();
			BeanUtils.copyProperties(etapaDeuda, mapa);			
			
			MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) 
													getBean("spusicc.mantenimientoCOBGenericoService");	
			service.deleteEtapaDeuda(etapaDeuda, usuario);
			
			this.getResourceMessage("mantenimientoCOBEtapaDeudaForm.deleted");
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
		return "mantenimientoCOBEtapaDeudaList";
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'setSaveAttributes' method");
		}
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		MantenimientoCOBEtapaDeudaForm f = (MantenimientoCOBEtapaDeudaForm) this.formMantenimiento;
		
		f.setIndicadorEtapaFinal(obtenerValor(f.isIndicadorEtapaFinalB()));
		f.setIndicadorAsignacionEtapaAnterior(obtenerValor(f.isIndicadorAsignacionEtapaAnteriorB()));
		f.setIndicadorAsignacionEtapaPosterior(obtenerValor(f.isIndicadorAsignacionEtapaPosteriorB()));
		f.setIndicadorTelefono(obtenerValor(f.isIndicadorTelefonoB()));
		f.setIndicadorGeneracionLunes(obtenerValor(f.isIndicadorGeneracionLunesB()));			
		f.setIndicadorCierreQuincena(obtenerValor(f.isIndicadorCierreQuincenaB()));
		f.setIndicadorCierreFinMes(obtenerValor(f.isIndicadorCierreFinMesB()));
		f.setIndicadorGeneracionArchivo(obtenerValor(f.isIndicadorGeneracionArchivoB()));
		f.setIndicadorActivo(obtenerValor(f.isIndicadorActivoB()));
		
		MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) getBean("spusicc.mantenimientoCOBGenericoService");
		
		EtapaDeuda cobrador = new EtapaDeuda();		
		try {
			BeanUtils.copyProperties(cobrador, f);	
			if (!f.isNewRecord()) {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA MODIFICACION");
				}
				service.updateEtapaDeuda(cobrador, usuario);
			} else {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA CREACION");
				}
				service.insertEtapaDeuda(cobrador, usuario);
			}
		} catch (Exception e) {
				throw new Exception(e.getMessage());
		}		
		return true;
	}
	
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		log.debug("JFA Action: Entering setConsultarAttributes");
		
		MantenimientoCOBEtapaDeudaForm mantenimientoForm = new MantenimientoCOBEtapaDeudaForm();
										
		MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) 
												getBean("spusicc.mantenimientoCOBGenericoService");	
		
		if (!this.accion.equals(this.ACCION_NUEVO)) {  		
			Map mapa = (Map) this.beanRegistroSeleccionado;	
			
			log.debug("JFA - obtenerKeyMensaje");	
			EtapaDeuda etapaDeuda = new EtapaDeuda();
			BeanUtils.copyProperties(etapaDeuda, mapa);
						
			log.debug("JFA - obtener Bean");
			etapaDeuda = (EtapaDeuda)service.getEtapaDeuda(etapaDeuda);
							
			BeanUtils.copyProperties(mantenimientoForm, etapaDeuda);
			mantenimientoForm.setIndicadorEtapaFinalB(obtenerValorBoolean(mantenimientoForm.getIndicadorEtapaFinal()));
			mantenimientoForm.setIndicadorAsignacionEtapaAnteriorB(obtenerValorBoolean(mantenimientoForm.getIndicadorAsignacionEtapaAnterior()));
			mantenimientoForm.setIndicadorAsignacionEtapaPosteriorB(obtenerValorBoolean(mantenimientoForm.getIndicadorAsignacionEtapaPosterior()));
			mantenimientoForm.setIndicadorTelefonoB(obtenerValorBoolean(mantenimientoForm.getIndicadorTelefono()));
			mantenimientoForm.setIndicadorGeneracionLunesB(obtenerValorBoolean(mantenimientoForm.getIndicadorGeneracionLunes()));			
			mantenimientoForm.setIndicadorCierreQuincenaB(obtenerValorBoolean(mantenimientoForm.getIndicadorCierreQuincena()));
			mantenimientoForm.setIndicadorCierreFinMesB(obtenerValorBoolean(mantenimientoForm.getIndicadorCierreFinMes()));
			mantenimientoForm.setIndicadorGeneracionArchivoB(obtenerValorBoolean(mantenimientoForm.getIndicadorGeneracionArchivo()));	
			mantenimientoForm.setIndicadorActivoB(obtenerValorBoolean(mantenimientoForm.getIndicadorActivo()));
			mantenimientoForm.setNewRecord(false);
		} else {
			mantenimientoForm.setCodigoPais(this.mPantallaPrincipalBean.getCountryCode());
			mantenimientoForm.setIndicadorTipoBalanceo("C");
			mantenimientoForm.setNewRecord(true);
		}
		
		log.debug("JFA - Todo OK");					
		return mantenimientoForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoCOBEtapaDeudaForm mantenimientoForm = (MantenimientoCOBEtapaDeudaForm) this.formMantenimiento;
		boolean isNew = mantenimientoForm.isNewRecord();
		if(isNew) {
			return "mantenimientoCOBEtapaDeudaForm.add";
		} else {
			return "mantenimientoCOBEtapaDeudaForm.updated";
		}	
	}
	
	private boolean obtenerValorBoolean(String indicador) {
		if(indicador != null && indicador.endsWith("1")) {
			return true;
		} else {
			return false;
		}
	}
	
	private String obtenerValor(boolean estado) {
		if(estado) {
			return "1";
		} else {
			return "0";
		}
	}
				
}
