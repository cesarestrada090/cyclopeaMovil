package biz.belcorp.ssicc.web.spusicc.action;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.spusicc.MantenimientoPERPercepcionesOtrosCanalesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoPERNumeracionComprobantesSunatForm;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoPERNumeracionComprobantesSunatSearchForm;

/**
 * The Class MantenimientoPERNumeracionComprobantesSunatSearchAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 14/01/2015
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@ManagedBean
@SessionScoped
public class MantenimientoPERNumeracionComprobantesSunatSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 5932564188548673264L;
	private List mantenimientoPERNumeracionComprobantesSunatList;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoPERNumeracionComprobantesSunatForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoPERNumeracionComprobantesSunatSearchForm searchForm = new MantenimientoPERNumeracionComprobantesSunatSearchForm();
		return searchForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("inicio setViewAttributes");
		String codigoPais = this.mPantallaPrincipalBean.getCountryCode();
		MantenimientoPERNumeracionComprobantesSunatSearchForm f = (MantenimientoPERNumeracionComprobantesSunatSearchForm) this.formBusqueda;
		f.setCodigoPais(codigoPais);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		MantenimientoPERNumeracionComprobantesSunatSearchForm f = (MantenimientoPERNumeracionComprobantesSunatSearchForm) this.formBusqueda;
		Map map = new HashMap();
		MantenimientoPERPercepcionesOtrosCanalesService service = (MantenimientoPERPercepcionesOtrosCanalesService) getBean("spusicc.mantenimientoPERPercepcionesOtrosCanalesService");				
		BeanUtils.copyProperties(map,f);
		List resultado = (List)service.getNumeracionComprobantesSunatList(map);
		this.mantenimientoPERNumeracionComprobantesSunatList = resultado;		
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {	
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map mapa = (Map) this.beanRegistroSeleccionado;	
		String id = mapa.get("correlativoAutorizacion").toString();
		if (id != null) {
			if (log.isDebugEnabled()) {
				log.debug("Id seleccionado de la lista: " + id);
			}
			try {			
				MantenimientoPERPercepcionesOtrosCanalesService service = (MantenimientoPERPercepcionesOtrosCanalesService) 
																						getBean("spusicc.mantenimientoPERPercepcionesOtrosCanalesService");
				mapa.put("estadoAutorizacion", Constants.ESTADO_INACTIVO);//VIGENCIA EN 9
				service.updateNumeracionComprobantesSunat(mapa, usuario);
			} catch (Exception e) {
				String error = e.getMessage();
				if (StringUtils.isBlank(error)) {
					error = e.getLocalizedMessage();
				}
				throw new Exception(this.getResourceMessage("errors.detail", new Object[]{ error }));			
			}
		}
		return true;
	}
	
	@Override
	protected String getSalirForward() {
		return "mantenimientoPERNumeracionComprobantesSunatList";
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method");
		}
		MantenimientoPERNumeracionComprobantesSunatForm f = (MantenimientoPERNumeracionComprobantesSunatForm) this.formMantenimiento;
		MantenimientoPERPercepcionesOtrosCanalesService service = (MantenimientoPERPercepcionesOtrosCanalesService) getBean("spusicc.mantenimientoPERPercepcionesOtrosCanalesService");		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		try {			
			if(f.isIndicadorVigenciaB()) {
				f.setIndicadorVigencia(Constants.NUMERO_UNO);
			} else {
				f.setIndicadorVigencia(Constants.NUMERO_CERO);
			}		
			if(f.getFechaAutorizacionD() != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				f.setFechaAutorizacion(sdf.format(f.getFechaAutorizacionD()));	
			}			
			Map map = new HashMap();			
			copyProperties(map, f);
			map.put("login", usuario.getLogin());
			map.put("estadoAutorizacion", Constants.ESTADO_ACTIVO);
			if(f.getIndicadorVigencia().equals(Constants.NUMERO_UNO)) {
				map.put("codigoVigencia", Constants.CODIGO_VIGENCIA_ACTIVO);
			} else {
				map.put("codigoVigencia", Constants.CODIGO_VIGENCIA_CERRADO);
			}	
			log.debug("map " + map);
			if (!f.isNewRecord()) {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA MODIFICACION");
				}				
				service.updateNumeracionComprobantesSunat(map, usuario);
			} else {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA CREACION");
				}
				try{
					service.insertNumeracionComprobantesSunat(map, usuario);
				} catch(Exception ex) {
					throw new Exception(this.getResourceMessage("mantenimientoPERNumeracionComprobantesSunat.nombre.existe"));
				}
			}			
		} catch (Exception e) {
			throw new Exception(this.getResourceMessage("mantenimientoPERNumeracionComprobantesSunat.cabecera.error",
							    new Object[]{ e.getMessage() }));	
		}		
		return true;
	}
	
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Map mapa = (Map) this.beanRegistroSeleccionado;		
		MantenimientoPERNumeracionComprobantesSunatForm mantenimientoForm = new MantenimientoPERNumeracionComprobantesSunatForm();
		if (!this.accion.equals(this.ACCION_NUEVO) ) {			
			BeanUtils.copyProperties(mantenimientoForm, mapa);
			String codigoVigencia = String.valueOf(mapa.get("codigoVigencia"));
			log.debug("CODIGO VIGENCIA ss " + codigoVigencia);
			if(Constants.CODIGO_VIGENCIA_ACTIVO.equals(codigoVigencia)) {
				mapa.put("indicadorVigencia", Constants.NUMERO_UNO);
				mantenimientoForm.setIndicadorVigencia(Constants.NUMERO_UNO);
				mantenimientoForm.setIndicadorVigenciaB(true);
			} else {
				mapa.put("indicadorVigencia", Constants.NUMERO_CERO);
				mantenimientoForm.setIndicadorVigencia(Constants.NUMERO_CERO);
				mantenimientoForm.setIndicadorVigenciaB(false);
			}	
			if(mantenimientoForm.getFechaAutorizacion() != null && !mantenimientoForm.getFechaAutorizacion().isEmpty()) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				mantenimientoForm.setFechaAutorizacionD(sdf.parse(mapa.get("fechaAutorizacion").toString()));	
			}
			mantenimientoForm.setNewRecord(false);
		} else {
			mantenimientoForm.setIndicadorVigencia(Constants.NUMERO_CERO);
			mantenimientoForm.setNewRecord(true);
		}
		return mantenimientoForm;
	}	
	
	/**
	 * Copia los properies del form al Map de objetos
	 * @param map
	 * @param f
	 */
	private void copyProperties(Map map, MantenimientoPERNumeracionComprobantesSunatForm f) {
		map.put("correlativoAutorizacion", f.getCorrelativoAutorizacion());
		map.put("codigoAutorizacion", f.getCodigoAutorizacion());
		map.put("fechaAutorizacion", f.getFechaAutorizacion());
		map.put("serieDocumento", f.getSerieDocumento());				
		map.put("numeroComprobanteInicial", f.getNumeroComprobanteInicial());
		map.put("numeroComprobanteFinal", f.getNumeroComprobanteFinal());
		map.put("numeroAutorizacion", f.getNumeroAutorizacion());
		map.put("indicadorVigencia", f.getIndicadorVigencia());
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoPERNumeracionComprobantesSunatForm mantenimientoForm = (MantenimientoPERNumeracionComprobantesSunatForm) this.formMantenimiento;
		boolean isNew = mantenimientoForm.isNewRecord();
		if(isNew) {
			return "mantenimientoPERNumeracionComprobantesSunat.cabecera.updated";
		}else{
			return "mantenimientoPERNumeracionComprobantesSunat.cabecera.add";
		}	
	}
	
	

	/**
	 * @return the mantenimientoPERNumeracionComprobantesSunatList
	 */
	public List getMantenimientoPERNumeracionComprobantesSunatList() {
		return mantenimientoPERNumeracionComprobantesSunatList;
	}

	/**
	 * @param mantenimientoPERNumeracionComprobantesSunatList the mantenimientoPERNumeracionComprobantesSunatList to set
	 */
	public void setMantenimientoPERNumeracionComprobantesSunatList(
			List mantenimientoPERNumeracionComprobantesSunatList) {
		this.mantenimientoPERNumeracionComprobantesSunatList = mantenimientoPERNumeracionComprobantesSunatList;
	}

}
