package biz.belcorp.ssicc.web.spusicc.dto.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.spusicc.dto.MantenimientoDTORangoDescuentoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.dto.form.MantenimientoDTORangoDescuentoForm;
import biz.belcorp.ssicc.web.spusicc.dto.form.MantenimientoDTORangoDescuentoSearchForm;

/**
 * @author <a href="mailto:kgomez@sigcomt.com">Karina Gomez</a>
 * 
 */
@ManagedBean
@SessionScoped
public class MantenimientoDTORangoDescuentoAction extends BaseMantenimientoSearchAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2846256109947212386L;
	private List listaGrupos;
	private List listaRangosDescuentos;
	
	
	/**
	 * @return the listaGrupos
	 */
	public List getListaGrupos() {
		return listaGrupos;
	}

	/**
	 * @param listaGrupos the listaGrupos to set
	 */
	public void setListaGrupos(List listaGrupos) {
		this.listaGrupos = listaGrupos;
	}

	/**
	 * @return the listaRangosDescuentos
	 */
	public List getListaRangosDescuentos() {
		return listaRangosDescuentos;
	}

	/**
	 * @param listaRangosDescuentos the listaRangosDescuentos to set
	 */
	public void setListaRangosDescuentos(List listaRangosDescuentos) {
		this.listaRangosDescuentos = listaRangosDescuentos;
	}

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoDTORangoDescuentoList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoDTORangoDescuentoForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoDTORangoDescuentoSearchForm objFrom = new MantenimientoDTORangoDescuentoSearchForm();
		return objFrom;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoDTORangoDescuentoSearchForm  f = (MantenimientoDTORangoDescuentoSearchForm) this.formBusqueda;
		MantenimientoDTORangoDescuentoService service = (MantenimientoDTORangoDescuentoService) 
					this.getBean("spusicc.mantenimientoDTORangoDescuentoService");
		
		/* obteniendo valores */
		Map criteria = BeanUtils.describe(f);
		
		/* Obteniendo Lista */
		this.listaRangosDescuentos = service.getListRangoDescuento(criteria);
		
		return this.listaRangosDescuentos;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map obj = (HashMap)this.beanRegistroSeleccionado;
		
		MantenimientoDTORangoDescuentoForm form = new MantenimientoDTORangoDescuentoForm();
		BeanUtils.copyProperties(form,obj);
		String id = form.getCodigoRango();
		log.debug("row id "+id);
		if (id != null) {
			try {							
				MantenimientoDTORangoDescuentoService service = (MantenimientoDTORangoDescuentoService) 
						this.getBean("spusicc.mantenimientoDTORangoDescuentoService");
								
				
				Map params = (Map)this.listaRangosDescuentos.get(Integer.parseInt(id)-1);
				params.put("codigoUsuario", usuario.getLogin());
				
				service.deleteRangoDescuento(params);
				
			}catch (Exception e) {
				String error = e.getMessage();
				if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
				this.addError("Error: ", this.getResourceMessage("errors.detail",new Object[]{error}));
						
			}
		}
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoDTORangoDescuentoService service = (MantenimientoDTORangoDescuentoService) 
													getBean("spusicc.mantenimientoDTORangoDescuentoService");
		MantenimientoDTORangoDescuentoForm f = (MantenimientoDTORangoDescuentoForm) this.formMantenimiento;
		
		Map params = BeanUtils.describe(f);
		params.put("codigoUsuario", usuario.getLogin());
		params.put("importeHasta", new BigDecimal(f.getImporteHasta()));  
		params.put("porcentajeDescuento", new BigDecimal(f.getPorcentajeDescuento()));
		
		try{
			if(this.accion.equals(this.ACCION_NUEVO)){			
			   service.insertRangoDescuento(params);//inserta
			}
			else{
				service.updateRangoDescuento(params);//upadte
			}	
			
		}catch(Exception e){
			log.debug("error " +e.getMessage());	
			this.addError("Error: ", this.getResourceMessage("mantenimientoEDULocal.cabecera.error", new Object[]{e.getMessage()}));
			
			return false;
		}
		
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		MantenimientoDTORangoDescuentoForm f = new MantenimientoDTORangoDescuentoForm();
		
		f.setCodigoPais(pais.getCodigo());
		f.setActivo(false);
		if(!this.accion.equals(this.ACCION_NUEVO)){
			f.setActivo(true);
			Map obj = (HashMap) this.beanRegistroSeleccionado;
			BeanUtils.copyProperties(f, obj);
			
			String id = f.getCodigoRango();
			ActionMessages messages = new ActionMessages();
			MantenimientoDTORangoDescuentoService service = (MantenimientoDTORangoDescuentoService) 
													getBean("spusicc.mantenimientoDTORangoDescuentoService");
			
			log.debug("row id "+id);
			if (id != null) {
				try {							
					//Map map = (Map)this.listaRangosDescuentos.get(Integer.parseInt(id)-1);
					log.debug("map " +obj);
					f.setCodigoPais(String.valueOf(obj.get("codigoPais")));
					f.setCodigoGrupo(String.valueOf(obj.get("codigoGrupo")));
					f.setCodigoRango(String.valueOf(obj.get("codigoRango")));
					f.setImporteHasta(String.valueOf(obj.get("importeHasta")));
					f.setPorcentajeDescuento(String.valueOf(obj.get("porcentajeDescuento")));
					
					List listGrupos = service.getGruposDescuento(null);
					
					log.debug("enviando para editar");
				}catch (Exception e) {
					String error = e.getMessage();
					if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
					this.addError("Error: ", this.getResourceMessage("errors.detail", new Object[]{error}));
				}
			}
		}
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoDTORangoDescuentoService service = (MantenimientoDTORangoDescuentoService) 
				getBean("spusicc.mantenimientoDTORangoDescuentoService");
		this.mostrarBotonConsultar = false;
		
		this.listaGrupos = service.getGruposDescuento(null);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
	
		if(this.accion.equals(this.ACCION_NUEVO)){
			return "mantenimientoDTORangoDescuentoForm.insert";
		}else{
			return "mantenimientoDTORangoDescuentoForm.update";
		}	
	}

}
