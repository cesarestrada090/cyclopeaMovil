package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.lang.reflect.InvocationTargetException;
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
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.ClasificacionesChequeo;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDClasificacionesChequeoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDTipoChequeoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.dto.form.MantenimientoDTODescuentoAdicionalDetalleForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDClasificacionesChequeoForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDClasificacionesChequeoSearchForm;

@SessionScoped
@ManagedBean
public class MantenimientoPEDClasificacionesChequeoAction extends BaseMantenimientoSearchAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List clasificacionChequeoList;
	List listTipoChequeo;
	List listTipoCliente;
	LabelValue[] listSubTiposClientes;
	LabelValue[] listTiposClasificaciones;
	LabelValue[] listClasificaciones;
	
	
	
	/**
	 * @return the clasificacionChequeoList
	 */
	public List getClasificacionChequeoList() {
		return clasificacionChequeoList;
	}

	/**
	 * @param clasificacionChequeoList the clasificacionChequeoList to set
	 */
	public void setClasificacionChequeoList(List clasificacionChequeoList) {
		this.clasificacionChequeoList = clasificacionChequeoList;
	}

	/**
	 * @return the listTipoChequeo
	 */
	public List getListTipoChequeo() {
		return listTipoChequeo;
	}

	/**
	 * @param listTipoChequeo the listTipoChequeo to set
	 */
	public void setListTipoChequeo(List listTipoChequeo) {
		this.listTipoChequeo = listTipoChequeo;
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

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoPEDClasificacionesChequeoList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoPEDClasificacionesChequeoForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoPEDClasificacionesChequeoSearchForm objForm = new MantenimientoPEDClasificacionesChequeoSearchForm();
		return objForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoPEDClasificacionesChequeoSearchForm f = (MantenimientoPEDClasificacionesChequeoSearchForm)this.formBusqueda;
		
		MantenimientoPEDClasificacionesChequeoService service = (MantenimientoPEDClasificacionesChequeoService)getBean("spusicc.pedidos.mantenimientoPEDClasificacionesChequeoService");
		
		Map map = new HashMap();
		
		map.put("codTipoChequeo", f.getCodigoTipoChequeo());
		map.put("oidTipoCliente", f.getOidTipoCliente());
		map.put("oidSubTipoCliente", f.getOidSubTipoCliente());
		map.put("oidTipoClasificacion", f.getOidTipoClasificacion());
		map.put("oidClasificacion", f.getOidClasificacion());
		
		List list = service.getClasificacionChequeo(map);
		
		return list;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		
		Map bean = (HashMap)this.beanRegistroSeleccionado;
		MantenimientoPEDClasificacionesChequeoForm f = new MantenimientoPEDClasificacionesChequeoForm();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		BeanUtils.copyProperties(f, bean);
		
		f.setCodigoTipoChequeo(String.valueOf(bean.get("codTipoChequeo")));
		f.setCodigoTipoChequeoOld(String.valueOf(bean.get("codTipoChequeo")));
		
		MantenimientoPEDClasificacionesChequeoService service = (MantenimientoPEDClasificacionesChequeoService)getBean("spusicc.pedidos.mantenimientoPEDClasificacionesChequeoService");
		
		Map map = new HashMap();
		
		map.put("codigoPais", pais.getCodigo());
		String id = f.getCodigoTipoChequeo()+"|"+f.getOidTipoCliente()+"|"+f.getOidSubTipoCliente()+"|"+f.getOidTipoClasificacion()+"|"+f.getOidClasificacion();
		String[] items = {id};
		
		service.deleteClasificacionChequeo(map,items);
		
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoPEDClasificacionesChequeoForm f = (MantenimientoPEDClasificacionesChequeoForm)this.formMantenimiento;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoPEDClasificacionesChequeoService service = (MantenimientoPEDClasificacionesChequeoService)getBean("spusicc.pedidos.mantenimientoPEDClasificacionesChequeoService");
		
		Map params = new HashMap();
		
		try{
			
			if(this.accion.equals(this.ACCION_NUEVO)){
				
				params.put("codigoPais", pais.getCodigo());
				params.put("codTipoChequeo", f.getCodigoTipoChequeo());
				params.put("oidTipoCliente", f.getOidTipoCliente());
				params.put("oidSubTipoCliente", f.getOidSubTipoCliente());
				params.put("oidTipoClasificacion", f.getOidTipoClasificacion());
				params.put("oidClasificacion", f.getOidClasificacion());
				
				service.insertClasificacionChequeo(params);
			}else{
				
				params.put("codigoPais", pais.getCodigo());
				params.put("codTipoChequeo", f.getCodigoTipoChequeo());
				params.put("oidTipoCliente", f.getOidTipoCliente());
				params.put("oidSubTipoCliente", f.getOidSubTipoCliente());
				params.put("oidTipoClasificacion", f.getOidTipoClasificacion());
				params.put("oidClasificacion", f.getOidClasificacion());
				
				//los ids temporales antes de ser actualizados
				params.put("codigoPaisOld", f.getCodigoPaisOld());
				params.put("codTipoChequeoOld", f.getCodigoTipoChequeoOld());
				params.put("oidTipoClienteOld", f.getOidTipoClienteOld());
				params.put("oidSubTipoClienteOld", f.getOidSubTipoClienteOld());
				params.put("oidTipoClasificacionOld", f.getOidTipoClasificacionOld());
				params.put("oidClasificacionOld", f.getOidClasificacionOld());
				
				service.updateClasificacionChequeo(params);
			}
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) 
				error = e.getLocalizedMessage();
			this.addError("Error: ", this.getResourceMessage("errors.detail", new Object[] {error}));
			
		}	
		this.clasificacionChequeoList = service.getClasificacionChequeo(new HashMap());
		
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoPEDClasificacionesChequeoForm f = new MantenimientoPEDClasificacionesChequeoForm();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoPEDClasificacionesChequeoService service = (MantenimientoPEDClasificacionesChequeoService)getBean("spusicc.pedidos.mantenimientoPEDClasificacionesChequeoService");
		MantenimientoPEDTipoChequeoService mantenimientoPEDTipoChequeoService = (MantenimientoPEDTipoChequeoService)getBean("spusicc.pedidos.mantenimientoPEDTipoChequeoService");
		
		f.setCodigoPais(pais.getCodigo());
		f.setOidTipoCliente(null);
		f.setOidSubTipoCliente(null);
		f.setOidTipoClasificacion(null);
		f.setOidClasificacion(null);
		
		if(this.accion.equals(this.ACCION_CONSULTAR)){ f.setActivo(true);}
		this.listTipoChequeo = mantenimientoPEDTipoChequeoService.getTipoChequeoAll();
		
		if(!this.accion.equals(this.ACCION_NUEVO)){
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			Map bean = (HashMap)this.beanRegistroSeleccionado;
			BeanUtils.copyProperties(f, bean);
			
			Map map = new HashMap();
			String[] id = {f.getCodigoTipoChequeo()};
			f.setCodigoTipoChequeo(String.valueOf(bean.get("codTipoChequeo")));
			f.setCodigoTipoChequeoOld(String.valueOf(bean.get("codTipoChequeo")));
			f.setCodigoPaisOld(f.getCodigoPais());
			
			f.setOidTipoClienteOld(f.getOidTipoCliente());
			f.setOidSubTipoClienteOld(f.getOidSubTipoCliente());
			f.setOidTipoClasificacionOld(f.getOidTipoClasificacion());
			f.setOidClasificacionOld(f.getOidClasificacion());
			
			if(StringUtils.isNotBlank(f.getOidSubTipoCliente())) {
				this.listSubTiposClientes = aSvc.getSubTipoClienteByOidTipoCliente(f.getOidTipoCliente());
			}
			
			if(StringUtils.isNotBlank(f.getOidTipoClasificacion())) {
				this.listTiposClasificaciones = aSvc.getTipoClasificacionByOidSubTipoCliente(f.getOidSubTipoCliente());
			}
			
			if(StringUtils.isNotBlank(f.getOidClasificacion()))  {
				this.listClasificaciones = aSvc.getClasificacionByOidTipoClasificacion(f.getOidTipoClasificacion());
			} 
		}
		
		return f;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		try {
			
			MantenimientoPEDClasificacionesChequeoSearchForm f = (MantenimientoPEDClasificacionesChequeoSearchForm)this.formBusqueda;
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			f.setCodigoPais(pais.getCodigo());
			
			MantenimientoPEDTipoChequeoService mantenimientoPEDTipoChequeoService = (MantenimientoPEDTipoChequeoService)getBean("spusicc.pedidos.mantenimientoPEDTipoChequeoService");
			MantenimientoPEDClasificacionesChequeoService mantenimientoPEDClasificacionesChequeoService = (MantenimientoPEDClasificacionesChequeoService)getBean("spusicc.pedidos.mantenimientoPEDClasificacionesChequeoService");
			
			this.listTipoChequeo = mantenimientoPEDTipoChequeoService.getTipoChequeoAll();
			this.listTipoCliente = mantenimientoPEDClasificacionesChequeoService.getTipoCliente();
			
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) 
				error = e.getLocalizedMessage();
			this.addError("Error: ", this.getResourceMessage("errors.detail", new Object[] {error}));
			
		}
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() {
		
		if (this.accion.equals(this.ACCION_NUEVO)) {
			return "mantenimientoPEDClasificacionesChequeoForm.cabecera.insert";
		} else {
			return "mantenimientoPEDClasificacionesChequeoForm.cabecera.update";
		}
	}
	

	public void loadSubTipoCliente(ValueChangeEvent val){
		if (log.isDebugEnabled()) {
			log.debug("loadSubTipoCliente");
		}
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		String valor = (String) val.getNewValue();
		
		this.listSubTiposClientes = aSvc.getSubTipoClienteByOidTipoCliente(valor);
		
	}
	
	public void loadTipoClasificacion(ValueChangeEvent val){
		if (log.isDebugEnabled()) log.debug("loadTipoClasificacion");
		String TipoCliente;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		String valor = (String) val.getNewValue();
		
		this.listTiposClasificaciones = aSvc.getTipoClasificacionByOidSubTipoCliente(valor);
		
	}
	
	public void loadClasificacion(ValueChangeEvent val){
		if (log.isDebugEnabled()) log.debug("loadClasificacion");
		String TipoCliente,TipoClasificacion;
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoPEDClasificacionesChequeoSearchForm objSearchForm = (MantenimientoPEDClasificacionesChequeoSearchForm)this.formBusqueda;
		MantenimientoPEDClasificacionesChequeoForm objForm = (MantenimientoPEDClasificacionesChequeoForm)this.formMantenimiento;
		
		if(this.accion.equals(this.ACCION_BUSCAR)){
			TipoCliente = objSearchForm.getOidTipoCliente(); TipoClasificacion= objSearchForm.getOidTipoClasificacion();
		}else{
			TipoCliente = objForm.getOidTipoCliente(); TipoClasificacion= objForm.getOidTipoClasificacion();
		}
		
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		String valor = (String) val.getNewValue();
		
		this.listClasificaciones = aSvc.getClasificacionByOidTipoClasificacion(valor);
	}
	
}
