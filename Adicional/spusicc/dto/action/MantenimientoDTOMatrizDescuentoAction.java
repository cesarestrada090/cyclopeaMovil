package biz.belcorp.ssicc.web.spusicc.dto.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.dto.MantenimientoDTOMatrizDescuentoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.dto.form.MantenimientoDTOMatrizDescuentoForm;
import biz.belcorp.ssicc.web.spusicc.dto.form.MantenimientoDTOMatrizDescuentoSearchForm;

/**
 * @author <a href="mailto:kgomez@sigcomt.com">Karina Gomez</a>
 * 
 */
@ManagedBean
@SessionScoped
public class MantenimientoDTOMatrizDescuentoAction extends BaseMantenimientoSearchAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8971049025564935987L;

	private List listaMatriz;
	private List listaCategoria;
	private List listaSubCategoria1;
	private List listaSubCategoria2;
	private List listaGDescuento;
	
	private List listaNegocio;
	private List listaOferta;
	private List listaUnidadNegocio;
	
	LabelValue[] categoria1;
	
	
	
	/**
	 * @return the listaNegocio
	 */
	public List getListaNegocio() {
		return listaNegocio;
	}

	/**
	 * @param listaNegocio the listaNegocio to set
	 */
	public void setListaNegocio(List listaNegocio) {
		this.listaNegocio = listaNegocio;
	}

	/**
	 * @return the listaOferta
	 */
	public List getListaOferta() {
		return listaOferta;
	}

	/**
	 * @param listaOferta the listaOferta to set
	 */
	public void setListaOferta(List listaOferta) {
		this.listaOferta = listaOferta;
	}

	/**
	 * @return the listaUnidadNegocio
	 */
	public List getListaUnidadNegocio() {
		return listaUnidadNegocio;
	}

	/**
	 * @param listaUnidadNegocio the listaUnidadNegocio to set
	 */
	public void setListaUnidadNegocio(List listaUnidadNegocio) {
		this.listaUnidadNegocio = listaUnidadNegocio;
	}

	/**
	 * @return the categoria1
	 */
	public LabelValue[] getCategoria1() {
		return categoria1;
	}

	/**
	 * @param categoria1 the categoria1 to set
	 */
	public void setCategoria1(LabelValue[] categoria1) {
		this.categoria1 = categoria1;
	}

	/**
	 * @return the listaMatriz
	 */
	public List getListaMatriz() {
		return listaMatriz;
	}

	/**
	 * @param listaMatriz the listaMatriz to set
	 */
	public void setListaMatriz(List listaMatriz) {
		this.listaMatriz = listaMatriz;
	}

	/**
	 * @return the listaCategoria
	 */
	public List getListaCategoria() {
		return listaCategoria;
	}

	/**
	 * @param listaCategoria the listaCategoria to set
	 */
	public void setListaCategoria(List listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	/**
	 * @return the listaSubCategoria1
	 */
	public List getListaSubCategoria1() {
		return listaSubCategoria1;
	}

	/**
	 * @param listaSubCategoria1 the listaSubCategoria1 to set
	 */
	public void setListaSubCategoria1(List listaSubCategoria1) {
		this.listaSubCategoria1 = listaSubCategoria1;
	}

	/**
	 * @return the listaSubCategoria2
	 */
	public List getListaSubCategoria2() {
		return listaSubCategoria2;
	}

	/**
	 * @param listaSubCategoria2 the listaSubCategoria2 to set
	 */
	public void setListaSubCategoria2(List listaSubCategoria2) {
		this.listaSubCategoria2 = listaSubCategoria2;
	}

	/**
	 * @return the listaGDescuento
	 */
	public List getListaGDescuento() {
		return listaGDescuento;
	}

	/**
	 * @param listaGDescuento the listaGDescuento to set
	 */
	public void setListaGDescuento(List listaGDescuento) {
		this.listaGDescuento = listaGDescuento;
	}

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoDTOMatrizDescuentoList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoDTOMatrizDescuentoForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoDTOMatrizDescuentoSearchForm objForm = new MantenimientoDTOMatrizDescuentoSearchForm();
		return objForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoDTOMatrizDescuentoSearchForm  f = (MantenimientoDTOMatrizDescuentoSearchForm) this.formBusqueda;
		MantenimientoDTOMatrizDescuentoService service = (MantenimientoDTOMatrizDescuentoService) 
					this.getBean("spusicc.mantenimientoDTOMatrizDescuentoService");
		
		/* obteniendo valores */
		Map criteria = BeanUtils.describe(f);
		
		/* Obteniendo Lista */
		this.listaMatriz = service.getListMatrizDescuento(criteria);
		return this.listaMatriz;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map bean = (HashMap)this.beanRegistroSeleccionado;
		MantenimientoDTOMatrizDescuentoForm obj = new MantenimientoDTOMatrizDescuentoForm();
		
		BeanUtils.copyProperties(obj, bean);
		
		String id = obj.getCodigoCategoria();
		log.debug("row id "+id);
		if (id != null) {
			try {							
				MantenimientoDTOMatrizDescuentoService service = (MantenimientoDTOMatrizDescuentoService) 
						this.getBean("spusicc.mantenimientoDTOMatrizDescuentoService");
								
				bean.put("codigoUsuario", usuario.getLogin());
				service.deleteMatrizDescuento(bean);
				
			}catch (Exception e) {
				String error = e.getMessage();
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
		MantenimientoDTOMatrizDescuentoService service = (MantenimientoDTOMatrizDescuentoService) 
													getBean("spusicc.mantenimientoDTOMatrizDescuentoService");
		MantenimientoDTOMatrizDescuentoForm f = (MantenimientoDTOMatrizDescuentoForm) this.formMantenimiento;
		
		Map params = BeanUtils.describe(f);
		params.put("codigoUsuario", usuario.getLogin());
		
		try{
			/* INI SA PER-SiCC-2013-0268 */
			if(f.getCodigoCategoria().equals("0")) { //Producto
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				
				params.put("subCategoria1", aSvc.validarCodigoSAP(f.getCodigoPais(), f.getCodigoProducto()));
				params.put("subCategoria2", "0");
			}
			/* FIN SA PER-SiCC-2013-0268 */
			if(f.getCodigoCategoria().equals("1")) { //Tipo/SubTipo Cliente
				params.put("subCategoria1", f.getOidTipoCliente());
				if(f.getOidSubTipoCliente()!=null && !f.getOidSubTipoCliente().equals(""))
					params.put("subCategoria2", f.getOidSubTipoCliente());
				else
					params.put("subCategoria2", "0");
			}
			if(f.getCodigoCategoria().equals("2")) { //Tipo de Oferta
				params.put("subCategoria1", f.getOidTipoOferta());
				params.put("subCategoria2", "0");
			}
			if(f.getCodigoCategoria().equals("3")) { //Negocio/Unidad de Negocio
				if(f.getOidNegocio()!=null && !f.getOidNegocio().equals(""))
					params.put("subCategoria1", f.getOidNegocio());
				else
					params.put("subCategoria1", "0");				
				
				if(f.getOidUnidadNegocio()!=null && !f.getOidUnidadNegocio().equals(""))
					params.put("subCategoria2", f.getOidUnidadNegocio());
				else
					params.put("subCategoria2", "0");				
			} 
			if(f.getCodigoCategoria().equals("4")) { //Ninguno
				params.put("subCategoria1", "0");
				params.put("subCategoria2", "0");
			}
				
			//Valida que no exista la combinacion = cod_cate+sub_cat1+sub_cat2+est_regi=1
			if(service.existeMatrizDescuento(params)) {
				
				this.addError("Error: ", this.getResourceMessage("mantenimientoDTOMatrizDescuentoForm.msg.existeMatrizDescuento"));
				return false;			
			}

			if(f.isNewRecord()){			
			   service.insertMatrizDescuento(params);//inserta
			}
			else{
				service.updateMatrizDescuento(params);//upadte
			}	
			
		} catch(Exception e){
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
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		MantenimientoDTOMatrizDescuentoForm f = new MantenimientoDTOMatrizDescuentoForm();
		
		f.setCodigoPais(pais.getCodigo());
		f.setCodigoCategoria("1");
		if(!this.accion.equals(this.ACCION_NUEVO)){
		
		
			Map bean = (HashMap) this.beanRegistroSeleccionado;
			BeanUtils.copyProperties(f, bean);
			
			String id = f.getCodigoCategoria();
			
			MantenimientoDTOMatrizDescuentoService service = (MantenimientoDTOMatrizDescuentoService) getBean("spusicc.mantenimientoDTOMatrizDescuentoService");
			
			log.debug("row id "+id);
			if (id != null) {
				try {							
					//List list=(List)session.getAttribute(Constants.DTO_MATRIZ_DESCUENTO_LIST);
					log.debug("map " +bean);
					f.setCodigoPais(String.valueOf(bean.get("codigoPais")));
					f.setCodigoGrupo(String.valueOf(bean.get("codigoGrupo")));
					f.setCodigoCategoria(String.valueOf(bean.get("codigoCategoria")));
					f.setSubCategoria1(String.valueOf(bean.get("subCategoria1")));
					f.setSubCategoria2(String.valueOf(bean.get("subCategoria2")));
	
					if(f.getCodigoCategoria().equals("1")) { //Tipo/SubTipo Cliente
						f.setOidTipoCliente(f.getSubCategoria1());
						f.setOidSubTipoCliente(f.getSubCategoria2());
					}
					if(f.getCodigoCategoria().equals("2")) { //Tipo de Oferta
						f.setOidTipoOferta(f.getSubCategoria1());
					}
					if(f.getCodigoCategoria().equals("3")) { //Negocio/Unidad de Negocio
						f.setOidNegocio(f.getSubCategoria1());
						f.setOidUnidadNegocio(f.getSubCategoria2());
					}
					
					cargarCombos();
					
					AjaxService aSvc = (AjaxService) getBean("ajaxService");
					ArrayList temp = new ArrayList();
					temp = new ArrayList();
					temp.add(f.getOidTipoCliente());
					
					categoria1 = aSvc.getSubTiposClientesPorPaisTipoClientesOID(usuario.getIdioma().getCodigoISO(), temp);
					/* INI SA PER-SiCC-2013-0268 */	
					if(f.getCodigoCategoria().equals("0")) { //Codigo Producto y Descripcion Producto
						String datosProducto = String.valueOf(bean.get("descripcionSubCategoria1"));
						int posicion = datosProducto.indexOf(" - ");
						f.setCodigoProducto(datosProducto.substring(0, posicion));
						f.setDescripcionProducto(datosProducto.substring(posicion+3));
					}
					/* FIN SA PER-SiCC-2013-0268 */	
					
					log.debug("enviando para editar");
				}catch (Exception e) {
					String error = e.getMessage();
					this.addError("Error: ", this.getResourceMessage("errors.detail", new Object[]{error}));
					
					
				}
			}
		}
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoDTOMatrizDescuentoSearchForm f = (MantenimientoDTOMatrizDescuentoSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		cargarCombos();
		f.setCodigoPais(pais.getCodigo());
		//f.setCodigoCategoria("1");
	}
	
	private void cargarCombos() {
		
		MantenimientoDTOMatrizDescuentoService service = (MantenimientoDTOMatrizDescuentoService) 
				getBean("spusicc.mantenimientoDTOMatrizDescuentoService");
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		this.listaGDescuento = service.getGruposDescuento(null);
		this.listaCategoria = service.getCategoriasDescuento(null);
		
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
				
		this.listaSubCategoria1 = interfazSiCCService.getTiposClientesByCodigoISOOID(usuario.getIdioma().getCodigoISO());
		
		this.listaOferta = service.getTiposOferta(null);
		
		this.listaNegocio = service.getNegocios(null);
		this.listaUnidadNegocio = service.getUnidadesNegocio(null);
		this.listaSubCategoria2 = new ArrayList();  
	
		//session.setAttribute(Constants.SICC_SUB_TIPO_CLIENTE_LIST, new ArrayList());  
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
	
		if(this.accion.equals(this.ACCION_NUEVO)){
			return "mantenimientoDTOMatrizDescuentoForm.insert";
		}else{
			return "mantenimientoDTOMatrizDescuentoForm.update";
		}	
	}

}
