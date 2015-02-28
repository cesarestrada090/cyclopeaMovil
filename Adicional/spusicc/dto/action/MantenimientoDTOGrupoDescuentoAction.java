package biz.belcorp.ssicc.web.spusicc.dto.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.axis.utils.JavaUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spusicc.dto.MantenimientoDTOGrupoDescuentoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.dto.form.MantenimientoDTOGrupoDescuentoForm;
import biz.belcorp.ssicc.web.spusicc.dto.form.MantenimientoDTOGrupoDescuentoSearchForm;
import biz.belcorp.ssicc.web.spusicc.zon.form.MantenimientoZONRolForm;

/**
 * @author <a href="mailto:kgomez@sigcomt.com">Karina Gomez</a>
 * 
 */
@ManagedBean
@SessionScoped
public class MantenimientoDTOGrupoDescuentoAction extends BaseMantenimientoSearchAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1996325786563780731L;
	
	List listaGrupos;
	
	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoDTOGrupoDescuentoList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoDTOGrupoDescuentoForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoDTOGrupoDescuentoSearchForm objForm = new MantenimientoDTOGrupoDescuentoSearchForm();
		return objForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoDTOGrupoDescuentoSearchForm  f = (MantenimientoDTOGrupoDescuentoSearchForm) this.formBusqueda;
		MantenimientoDTOGrupoDescuentoService service = (MantenimientoDTOGrupoDescuentoService) 
					this.getBean("spusicc.mantenimientoDTOGrupoDescuentoService");
		
		/* obteniendo valores */
		Map criteria = BeanUtils.describe(f);
		
		/* Obteniendo Lista */
		this.listaGrupos = service.getListGrupoDescuento(criteria);
		
		return this.listaGrupos;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map obj = (HashMap) this.beanRegistroSeleccionado;
		MantenimientoDTOGrupoDescuentoForm objForm = new MantenimientoDTOGrupoDescuentoForm();
		
		BeanUtils.copyProperties(objForm, obj);
		
		String id = objForm.getCodigoGrupo();
		log.debug("row id "+id);
		if (id != null) {
			try {							
				MantenimientoDTOGrupoDescuentoService service = (MantenimientoDTOGrupoDescuentoService) 
						this.getBean("spusicc.mantenimientoDTOGrupoDescuentoService");
								
				//List listBloqueoPremios = (List)session.getAttribute(Constants.DTO_GRUPO_DESCUENTO_LIST);
				Map params = (Map)this.listaGrupos.get(Integer.parseInt(id)-1);
				params.put("codigoUsuario", usuario.getLogin());
				
				service.deleteGrupoDescuento(params);
				
			}catch (Exception e) {
				String error = e.getMessage();
				if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
				this.addError("Error: ", this.getResourceMessage("errors.detail", new Object[]{error}));
				return false;
				//messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.detail",error));
			}
		}
		return true;
	}
	
	public boolean validaCampanas(MantenimientoDTOGrupoDescuentoForm f){
		
		int Pinicial = Integer.parseInt(f.getPeriodoInicial());
		int Pproceso = Integer.parseInt(f.getPeriodoProceso());
		int Pfinal = Integer.parseInt(f.getPeriodoFinal());
		if( Pinicial <= Pproceso){
			this.addWarn("Adv. : ", this.getResourceMessage("mantenimientoDTOGrupoDescuentoForm.msg.periodoInicial"));
			return false;
		}
		
		if( Pinicial >= Pfinal){
			this.addWarn("Adv. : ", this.getResourceMessage("mantenimientoDTOGrupoDescuentoForm.msg.periodoFinal"));
			return false;
		}
		
		return true;
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoDTOGrupoDescuentoService service = (MantenimientoDTOGrupoDescuentoService) 
													getBean("spusicc.mantenimientoDTOGrupoDescuentoService");
		MantenimientoDTOGrupoDescuentoForm f = (MantenimientoDTOGrupoDescuentoForm) this.formMantenimiento;
		
		Map params = BeanUtils.describe(f);
		params.put("codigoUsuario", usuario.getLogin());
		
		if(!validaCampanas(f)){
			return false;
		}
		
		try{
			if(this.accion.equals(this.ACCION_NUEVO)){		
			   service.insertGrupoDescuento(params);//inserta
			}
			else{
				service.updateGrupoDescuento(params);//upadte
			}	
			
		}catch (InvalidIdentifierException iie) {
        	String codigo = iie.getIdentifier().toString();
            throw new Exception(this.getResourceMessage("errors.invalid.id", new Object[]{codigo}));
            
        }
        catch (InvalidDescriptionException ide) {
        	 String descripcion = ide.getDescription();
             throw new Exception(this.getResourceMessage("errors.invalid.description", new Object[]{descripcion}));
             
        }
			//messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("mantenimientoEDULocal.cabecera.error",e.getMessage()));
			
		
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoDTOGrupoDescuentoForm f = new MantenimientoDTOGrupoDescuentoForm();
		
		Map c= new HashMap();
		c.put("codigoPais", pais.getCodigo());
		
        c.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        c.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
		MantenimientoOCRPedidoControlFacturacionService serviceOCR = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFact = serviceOCR.getControlFacturacionById(c);

		// Carga el periodo proceso
		f.setPeriodoProceso(controlFact.getCodigoPeriodo());
		
		if(!this.accion.equals(this.ACCION_NUEVO)){
			Map bean = (HashMap)this.beanRegistroSeleccionado;
			
			BeanUtils.copyProperties(f, bean);
			
			String id = f.getCodigoGrupo();
			MantenimientoDTOGrupoDescuentoService service = (MantenimientoDTOGrupoDescuentoService) 
													getBean("spusicc.mantenimientoDTOGrupoDescuentoService");
			
			log.debug("row id "+id);
			
			if (id != null) {
				try {							
					//List list=(List)session.getAttribute(Constants.DTO_GRUPO_DESCUENTO_LIST); >> listaGrupos
					Map map = (Map)listaGrupos.get(Integer.parseInt(id)-1);
					log.debug("map " +map);
					f.setCodigoPais(String.valueOf(map.get("codigoPais")));
					f.setCodigoGrupo(String.valueOf(map.get("codigoGrupo")));
					f.setDescripcionGrupo(String.valueOf(map.get("descripcionGrupo")));
					f.setPeriodoInicial(String.valueOf(map.get("periodoInicial")));
					if(map.get("periodoFinal") != null)
						f.setPeriodoFinal(String.valueOf(map.get("periodoFinal")));
					
					Map criteria= new HashMap();
					criteria.put("codigoPais", pais.getCodigo());
					
			        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
			        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
					PedidoControlFacturacion controlFacturacion = serviceOCR.getControlFacturacionById(criteria);
	
					// Carga el periodo proceso
					f.setPeriodoProceso(controlFacturacion.getCodigoPeriodo());
					
					log.debug("enviando para editar");
				}catch (Exception e) {
					String error = e.getMessage();
					if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
					//messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.detail",error));
					this.addError("Error", this.getResourceMessage("errors.detail", new Object[]{error}));
					//saveErrors(request, messages);			
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
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		MantenimientoDTOGrupoDescuentoSearchForm  f = (MantenimientoDTOGrupoDescuentoSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());			
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
	
		if(this.accion.equals(this.ACCION_NUEVO)){
			return "mantenimientoDTOGrupoDescuentoForm.insert";
		}else{
			return "mantenimientoDTOGrupoDescuentoForm.update";
		}	
	}
	
}
