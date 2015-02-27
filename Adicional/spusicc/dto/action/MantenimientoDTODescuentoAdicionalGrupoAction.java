package biz.belcorp.ssicc.web.spusicc.dto.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.spusicc.dto.MantenimientoDTODescuentoAdicionalDetalleService;
import biz.belcorp.ssicc.service.spusicc.dto.MantenimientoDTODescuentoAdicionalGrupoService;
import biz.belcorp.ssicc.service.spusicc.dto.MantenimientoDTOMatrizDescuentoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.dto.form.MantenimientoDTODescuentoAdicionalGrupoForm;
import biz.belcorp.ssicc.web.spusicc.dto.form.MantenimientoDTODescuentoAdicionalGrupoSearchForm;

/**
 * @author <a href="mailto:kgomez@sigcomt.com">Karina Gomez</a>
 * 
 */
@ManagedBean
@SessionScoped
public class MantenimientoDTODescuentoAdicionalGrupoAction extends BaseMantenimientoSearchAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1131040737833682351L;
	List listDescuentos;
	List listGrupos;
	
	
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
	 * @return the listGrupos
	 */
	public List getListGrupos() {
		return listGrupos;
	}

	/**
	 * @param listGrupos the listGrupos to set
	 */
	public void setListGrupos(List listGrupos) {
		this.listGrupos = listGrupos;
	}

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoDTODescuentoAdicionalGrupoList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoDTODescuentoAdicionalGrupoForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoDTODescuentoAdicionalGrupoSearchForm objForm = new MantenimientoDTODescuentoAdicionalGrupoSearchForm();
		return objForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoDTODescuentoAdicionalGrupoSearchForm  f = (MantenimientoDTODescuentoAdicionalGrupoSearchForm) this.formBusqueda;
		MantenimientoDTODescuentoAdicionalGrupoService service = (MantenimientoDTODescuentoAdicionalGrupoService) 
					this.getBean("spusicc.mantenimientoDTODescuentoAdicionalGrupoService");
		
		/* obteniendo valores */
		Map criteria = BeanUtils.describe(f);
		
		/* Obteniendo Lista */
		List resultado = service.getListDescuentoAdicionalGrupo(criteria);
		
		return resultado;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		Map map = (HashMap)this.beanRegistroSeleccionado;
		MantenimientoDTODescuentoAdicionalGrupoForm f = new MantenimientoDTODescuentoAdicionalGrupoForm();
		BeanUtils.copyProperties(f, map);
		String id = f.getCodigoGrupo();
		log.debug("row id "+id);
		if (id != null) {
			try {							
				MantenimientoDTODescuentoAdicionalGrupoService service = (MantenimientoDTODescuentoAdicionalGrupoService) 
						this.getBean("spusicc.mantenimientoDTODescuentoAdicionalGrupoService");
				
				map.put("codigoUsuario", usuario.getLogin());
				
				service.deleteDescuentoAdicionalGrupo(map);
				
			}catch (Exception e) {
				String error = e.getMessage();
				if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
				this.addError("Error: ", this.getResourceMessage("errors.detail", new Object[]{error}));
				return false;	
			}
		}
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoDTODescuentoAdicionalGrupoService service = (MantenimientoDTODescuentoAdicionalGrupoService) 
													getBean("spusicc.mantenimientoDTODescuentoAdicionalGrupoService");
		MantenimientoDTODescuentoAdicionalGrupoForm f = (MantenimientoDTODescuentoAdicionalGrupoForm) this.formMantenimiento;
		
		Map params = BeanUtils.describe(f);
		params.put("codigoUsuario", usuario.getLogin());
		
		try{
			if(this.accion.equals(this.ACCION_NUEVO)){			
			   service.insertDescuentoAdicionalGrupo(params);//inserta
			}
			else{
				service.updateDescuentoAdicionalGrupo(params);//upadte
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
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();	
		Map map = (HashMap)this.beanRegistroSeleccionado;
		MantenimientoDTODescuentoAdicionalGrupoForm f = new MantenimientoDTODescuentoAdicionalGrupoForm();
		
		MantenimientoDTODescuentoAdicionalDetalleService service = (MantenimientoDTODescuentoAdicionalDetalleService) 
				getBean("spusicc.mantenimientoDTODescuentoAdicionalDetalleService");
		
		this.listDescuentos = service.getDescuentosAdicionales(null);
		
		MantenimientoDTOMatrizDescuentoService serviceGrupo = (MantenimientoDTOMatrizDescuentoService) 
				getBean("spusicc.mantenimientoDTOMatrizDescuentoService");
		
		this.listGrupos = serviceGrupo.getGruposDescuento(null);
		
		if(!this.accion.equals(this.ACCION_NUEVO)){
			BeanUtils.copyProperties(f, map);
			String id = f.getCodigoGrupo();
			
			log.debug("row id "+id);
			if (id != null) {
				try {							
					
					log.debug("map " +map);
					f.setCodigoPais(pais.getCodigo());
					f.setCodigoAdicional(String.valueOf(map.get("codigoAdicional")));
					f.setCodigoGrupo(String.valueOf(map.get("codigoGrupo")));
					
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
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		MantenimientoDTODescuentoAdicionalGrupoSearchForm  f = (MantenimientoDTODescuentoAdicionalGrupoSearchForm) this.formBusqueda;
		
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
			return "mantenimientoDTODescuentoAdicionalGrupoForm.insert";
		}else{
			return "mantenimientoDTODescuentoAdicionalGrupoForm.update";
		}	
	}
}