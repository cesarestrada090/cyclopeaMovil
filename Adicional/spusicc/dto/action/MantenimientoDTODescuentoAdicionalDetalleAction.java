package biz.belcorp.ssicc.web.spusicc.dto.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.dto.MantenimientoDTODescuentoAdicionalDetalleService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.dto.form.MantenimientoDTODescuentoAdicionalDetalleForm;
import biz.belcorp.ssicc.web.spusicc.dto.form.MantenimientoDTODescuentoAdicionalDetalleSearchForm;


/**
 * @author <a href="mailto:kgomez@sigcomt.com">Karina Gomez</a>
 * 
 */
@ManagedBean
@SessionScoped
public class MantenimientoDTODescuentoAdicionalDetalleAction extends BaseMantenimientoSearchAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8475780064075421450L;
	
	
	List listDescuentos;
	List listTipoCliente;
	LabelValue[] listRegion;
	LabelValue[]  listSubTiposClientes;
	LabelValue[] listTiposClasificaciones;
	LabelValue[] listClasificaciones;
	LabelValue[] listZonas;
	
	
	
	/**
	 * @return the listDescuentos
	 */
	public List getListDescuentos() {
		return listDescuentos;
	}

	/**
	 * @param listDescuentos the listDescuentos to set
	 */
	public void setListDescuentos(List listDescuentos) {
		this.listDescuentos = listDescuentos;
	}

	/**
	 * @return the listTipoCliente
	 */
	public List getListTipoCliente() {
		return listTipoCliente;
	}

	/**
	 * @param listTipoCliente the listTipoCliente to set
	 */
	public void setListTipoCliente(List listTipoCliente) {
		this.listTipoCliente = listTipoCliente;
	}

	/**
	 * @return the listRegion
	 */
	public LabelValue[] getListRegion() {
		return listRegion;
	}

	/**
	 * @param listRegion the listRegion to set
	 */
	public void setListRegion(LabelValue[] listRegion) {
		this.listRegion = listRegion;
	}

	/**
	 * @return the listSubTiposClientes
	 */
	public LabelValue[] getListSubTiposClientes() {
		return listSubTiposClientes;
	}

	/**
	 * @param listSubTiposClientes the listSubTiposClientes to set
	 */
	public void setListSubTiposClientes(LabelValue[] listSubTiposClientes) {
		this.listSubTiposClientes = listSubTiposClientes;
	}

	/**
	 * @return the listTiposClasificaciones
	 */
	public LabelValue[] getListTiposClasificaciones() {
		return listTiposClasificaciones;
	}

	/**
	 * @param listTiposClasificaciones the listTiposClasificaciones to set
	 */
	public void setListTiposClasificaciones(LabelValue[] listTiposClasificaciones) {
		this.listTiposClasificaciones = listTiposClasificaciones;
	}

	/**
	 * @return the listClasificaciones
	 */
	public LabelValue[] getListClasificaciones() {
		return listClasificaciones;
	}

	/**
	 * @param listClasificaciones the listClasificaciones to set
	 */
	public void setListClasificaciones(LabelValue[] listClasificaciones) {
		this.listClasificaciones = listClasificaciones;
	}

	/**
	 * @return the listZonas
	 */
	public LabelValue[] getListZonas() {
		return listZonas;
	}

	/**
	 * @param listZonas the listZonas to set
	 */
	public void setListZonas(LabelValue[] listZonas) {
		this.listZonas = listZonas;
	}

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoDTODescuentoAdicionalDetalleList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoDTODescuentoAdicionalDetalleForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoDTODescuentoAdicionalDetalleSearchForm objForm = new MantenimientoDTODescuentoAdicionalDetalleSearchForm();
		return objForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoDTODescuentoAdicionalDetalleSearchForm  f = (MantenimientoDTODescuentoAdicionalDetalleSearchForm) this.formBusqueda;
		MantenimientoDTODescuentoAdicionalDetalleService service = (MantenimientoDTODescuentoAdicionalDetalleService) 
					this.getBean("spusicc.mantenimientoDTODescuentoAdicionalDetalleService");
		
		/* obteniendo valores */
		Map criteria = BeanUtils.describe(f);
		
		/* Obteniendo Lista */
		List resultado = service.getListDescuentoAdicionalDetalle(criteria);
		
		return resultado;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map map = (HashMap)this.beanRegistroSeleccionado;
		MantenimientoDTODescuentoAdicionalDetalleForm f = new MantenimientoDTODescuentoAdicionalDetalleForm();
		BeanUtils.copyProperties(f, map);
		String id = f.getCodigoDetalle();
		log.debug("row id "+id);
		if (id != null) {
			try {							
				MantenimientoDTODescuentoAdicionalDetalleService service = (MantenimientoDTODescuentoAdicionalDetalleService) 
						this.getBean("spusicc.mantenimientoDTODescuentoAdicionalDetalleService");
								
				map.put("codigoUsuario", usuario.getLogin());
				
				service.deleteDescuentoAdicionalDetalle(map);
				
				
			}catch (Exception e) {
				String error = e.getMessage();
				if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
				this.addError("Error", this.getResourceMessage("errors.detail", new Object[]{error}));
			}
		}
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoDTODescuentoAdicionalDetalleService service = (MantenimientoDTODescuentoAdicionalDetalleService) 
													getBean("spusicc.mantenimientoDTODescuentoAdicionalDetalleService");
		MantenimientoDTODescuentoAdicionalDetalleForm f = (MantenimientoDTODescuentoAdicionalDetalleForm) this.formMantenimiento;
		
		Map params = BeanUtils.describe(f);
		params.put("codigoUsuario", usuario.getLogin());
		if(StringUtils.isNotEmpty(f.getMontoLimite()))
			params.put("montoLimite", new BigDecimal(f.getMontoLimite()));
		else
			params.put("montoLimite", null);
		
		try{
			if(this.accion.equals(this.ACCION_NUEVO)){			
			   service.insertDescuentoAdicionalDetalle(params);//inserta
			}
			else{
				service.updateDescuentoAdicionalDetalle(params);//upadte
			}	
			
		}catch(Exception e){
			log.debug("error " +e.getMessage());	
			this.addError("Error", this.getResourceMessage("mantenimientoEDULocal.cabecera.error", new Object[]{e.getMessage()}));
			
			return false;
		}
		
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();	
		Map map = (HashMap)this.beanRegistroSeleccionado;
		MantenimientoDTODescuentoAdicionalDetalleForm f = new MantenimientoDTODescuentoAdicionalDetalleForm();
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		f.setCodigoPais(pais.getCodigo());
		
		Map criteria= new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.listTipoCliente = interfazSiCCService.getTiposClientesByCodigoISO(usuario.getIdioma().getCodigoISO());
		
		this.listRegion = aSvc.getRegionesByPaisMarcaCanal(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT);
		
		MantenimientoDTODescuentoAdicionalDetalleService service = (MantenimientoDTODescuentoAdicionalDetalleService) 
				getBean("spusicc.mantenimientoDTODescuentoAdicionalDetalleService");
		
		this.listDescuentos = service.getDescuentosAdicionales(null);
		
		if(!this.accion.equals(this.ACCION_NUEVO)){
				
				BeanUtils.copyProperties(f, map);
				String id = f.getCodigoDetalle();
				
				log.debug("row id "+id);
				if (id != null) {
					try {							
						log.debug("map " +map);
						f.setCodigoPais(pais.getCodigo());
						f.setCodigoDetalle(String.valueOf(map.get("codigoDetalle")));
						f.setCodigoAdicional(String.valueOf(map.get("codigoAdicional")));
						f.setCodigoTipoCliente(String.valueOf(map.get("codigoTipoCliente")));
						f.setCodigoSubTipoCliente(String.valueOf(map.get("codigoSubTipoCliente")));
						
						if(map.get("codigoTipoClasificacion") != null)
							f.setCodigoTipoClasificacion(String.valueOf(map.get("codigoTipoClasificacion")));
						if(map.get("codigoClasificacion") != null)
							f.setCodigoClasificacion(String.valueOf(map.get("codigoClasificacion")));
						if(map.get("codigoRegion") != null)
							f.setCodigoRegion(String.valueOf(map.get("codigoRegion")));
						if(map.get("codigoZona") != null)
							f.setCodigoZona(String.valueOf(map.get("codigoZona")));
						if(map.get("montoLimite") != null)
							f.setMontoLimite(String.valueOf(map.get("montoLimite")));
						
						this.listSubTiposClientes = aSvc.getSubTiposClientesPorPaisTipoCliente(f.getCodigoPais(), f.getCodigoTipoCliente());
						
						if(map.get("codigoTipoClasificacion") != null) {
							this.listTiposClasificaciones = aSvc.getTiposClasificacionesByCodISOIdiomaTClienteSubTCliente
											(usuario.getIdioma().getCodigoISO(), f.getCodigoTipoCliente(), f.getCodigoSubTipoCliente());
						} else {
							this.listTiposClasificaciones = null;
						}	
						
						if(map.get("codigoClasificacion") != null) {
							this.listClasificaciones = aSvc.getClasificacionesByCodISOIdiomaTClienteSubTClienteTClasificacion
									(usuario.getIdioma().getCodigoISO(), f.getCodigoTipoCliente(), f.getCodigoSubTipoCliente(),
											f.getCodigoTipoClasificacion());
						} else {
							this.listClasificaciones = null;
						}	
						
						if(map.get("codigoZona") != null) {
							this.listZonas = aSvc.getZonasMultipleByPaisMarcaCanalRegion(f.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,
														Constants.CODIGO_CANAL_DEFAULT, new String[]{f.getCodigoRegion()}, "");
							
						} else {
							this.listZonas = null;
						}	
						//session.setAttribute("codigoIdiomaISO", usuario.getIdioma().getCodigoISO());
						
						log.debug("enviando para editar");
					}catch (Exception e) {
						String error = e.getMessage();
						if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
						this.addError("Error", this.getResourceMessage("errors.detail", new Object[]{error}));
									
					}
				}
		}
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		
		MantenimientoDTODescuentoAdicionalDetalleSearchForm  f = (MantenimientoDTODescuentoAdicionalDetalleSearchForm) this.formBusqueda;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());			
		this.mostrarBotonConsultar = false;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
	
		if(this.accion.equals(this.ACCION_NUEVO)){
			return "mantenimientoDTODescuentoAdicionalDetalleForm.insert";
		}else{
			return "mantenimientoDTODescuentoAdicionalDetalleForm.update";
		}	
	}
	
	
	public void loadSubTipoCliente(ValueChangeEvent val){
		if (log.isDebugEnabled()) {
			log.debug("loadSubTipoCliente");
		}
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		String valor = (String) val.getNewValue();
		
		this.listSubTiposClientes = aSvc.getSubTiposClientesPorPaisTipoCliente(usuario.getIdioma().getCodigoISO(), valor);
		
	}
	
	public void loadTipoClasificacion(ValueChangeEvent val){
		if (log.isDebugEnabled()) {
			log.debug("loadTipoClasificacion");
		}
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoDTODescuentoAdicionalDetalleForm objForm = (MantenimientoDTODescuentoAdicionalDetalleForm)this.formMantenimiento;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		String valor = (String) val.getNewValue();
		
		this.listTiposClasificaciones = aSvc.getTiposClasificacionesByCodISOIdiomaTClienteSubTCliente(usuario.getIdioma().getCodigoISO(), objForm.getCodigoTipoCliente(), valor);
		
	}
	
	public void loadClasificacion(ValueChangeEvent val){
		if (log.isDebugEnabled()) {
			log.debug("loadClasificacion");
		}
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoDTODescuentoAdicionalDetalleForm objForm = (MantenimientoDTODescuentoAdicionalDetalleForm)this.formMantenimiento;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		String valor = (String) val.getNewValue();
		
		this.listClasificaciones = aSvc.getClasificacionesByCodISOIdiomaTClienteSubTClienteTClasificacion(usuario.getIdioma().getCodigoISO(), objForm.getCodigoTipoCliente(), 
				objForm.getCodigoTipoClasificacion(), valor);
	}
	
	public void loadZonas(ValueChangeEvent val){
		if (log.isDebugEnabled()) {
			log.debug("loadClasificacion");
		}
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoDTODescuentoAdicionalDetalleForm objForm = (MantenimientoDTODescuentoAdicionalDetalleForm)this.formMantenimiento;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		String valor = (String) val.getNewValue();
		    
		String[] values=new String[1];     	
    	values[0]=valor;
		this.listZonas = aSvc.getZonasMultipleByPaisMarcaCanalRegion( pais.getCodigo(), "T", "VD", values, "N");
	}
}
