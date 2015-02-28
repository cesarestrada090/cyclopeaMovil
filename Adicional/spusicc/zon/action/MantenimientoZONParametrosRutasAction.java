package biz.belcorp.ssicc.web.spusicc.zon.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBloqueoControlService;
import biz.belcorp.ssicc.service.spusicc.zon.MantenimientoZONDirectorioService;
import biz.belcorp.ssicc.service.spusicc.zon.MantenimientoZONParametrosRutasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.zon.form.MantenimientoZONParametrosRutasForm;
import biz.belcorp.ssicc.web.spusicc.zon.form.MantenimientoZONParametrosRutasSearchForm;


/**
 * Action invocado desde la pantalla de mantenimiento del objeto Usuario SICC.
 * <p>
 * <a href="RolSearchAction.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:kgomez@sigcomt.com">Karina Gomez</a> 
 */

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class MantenimientoZONParametrosRutasAction extends BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8628815748108002118L;
	private List lista;
	private List listaOperaciones;
	private boolean esconsulta = false;
	
	/**
	 * @return the lista
	 */
	public List getLista() {
		return lista;
	}

	/**
	 * @param lista the lista to set
	 */
	public void setLista(List lista) {
		this.lista = lista;
	}

	/**
	 * @return the listaOperaciones
	 */
	public List getListaOperaciones() {
		return listaOperaciones;
	}

	/**
	 * @param listaOperaciones the listaOperaciones to set
	 */
	public void setListaOperaciones(List listaOperaciones) {
		this.listaOperaciones = listaOperaciones;
	}

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoZONParametrosRutasList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoZONParametrosRutasForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoZONParametrosRutasSearchForm searchForm = new MantenimientoZONParametrosRutasSearchForm();
		return searchForm;
	}

	

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		
		MantenimientoZONParametrosRutasSearchForm objForm = new MantenimientoZONParametrosRutasSearchForm();
		Map obj = new HashMap(); 
		obj = (HashMap) this.beanRegistroSeleccionado;
		BeanUtils.copyProperties(objForm, obj);
		
		MantenimientoZONParametrosRutasService service = (MantenimientoZONParametrosRutasService)getBean("spusicc.mantenimientoZONParametrosRutasService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		String[] items=new String[1];
		items[0] = objForm.getCodigoPais()+"|"+objForm.getCodigoNovedad();
		
		
		service.deleteParametroRuta(items,usuario.getLogin());
		
		if (log.isDebugEnabled()) {
			log.debug("ID  eliminado ");
		}
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoZONParametrosRutasForm f = (MantenimientoZONParametrosRutasForm)this.formMantenimiento;
		MantenimientoZONParametrosRutasService service = (MantenimientoZONParametrosRutasService)getBean("spusicc.mantenimientoZONParametrosRutasService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		if(this.accion.equals(this.ACCION_CONSULTAR)){
			return false;
		}
		
		if(!f.getIndicadorDesactivarEnvioHidden().equals("true") || !f.getDescripcionNovedad().equals("true")){
			String msg= "";
			if(!f.getIndicadorDesactivarEnvioHidden().equals("true"))
				msg=f.getIndicadorDesactivarEnvioHidden();
			else
				msg=f.getDescripcionNovedad();
			
			this.addError("Error: ", this.getResourceMessage("errors.invalid.consolidated.process",new Object[]{msg}));
			return false;
		}else{
		
		Map params = new HashMap();		
		
		params.put("codigoPais",f.getCodigoPais());		
		params.put("codigoNovedad",f.getCodigoNovedad());
		params.put("emailAprobador",f.getEmailAprobador());
		params.put("emailDestinatarios",f.getEmailDestinatarios());
		params.put("asunto",f.getAsunto());
		params.put("mensaje",f.getMensaje());
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoSistema", "ZON");
        criteria.put("nombreParametro", "indEmailOrigen");
		
		MantenimientoSTOBloqueoControlService mantenimientoSTOBloqueoControlService = (MantenimientoSTOBloqueoControlService)getBean("spusicc.mantenimientoSTOBloqueoControlService");		
		String emailOrigen = mantenimientoSTOBloqueoControlService.getParametroGenericoSistema(criteria); 		
		f.setEmailOrigen(emailOrigen);
		
		params.put("emailOrigen",f.getEmailOrigen());	
		
		if(f.getIndicadorDesactivarEnvio()==null || f.getIndicadorDesactivarEnvio().equals(""))
			params.put("indicadorDesactivaEnvio",Constants.NUMERO_CERO);
		else
			if(f.getIndicadorDesactivarEnvio().equals("false"))
				params.put("indicadorDesactivaEnvio",Constants.NUMERO_CERO);
			else
				params.put("indicadorDesactivaEnvio",Constants.NUMERO_UNO);

		log.debug("___indicadorDesactivaEnvio = "+f.getIndicadorDesactivarEnvio());
		params.put("codigoUsuario", usuario.getLogin());
		
		List list=null;
		Map params2 = new HashMap();
		params2.put("codigoPais",f.getCodigoPais());		
		params2.put("codigoNovedad",f.getCodigoNovedad());
		list=service.getParametrosRutasList(params2);
		if(f.getIndicadorEdit().equals(Constants.NUMERO_CERO)){
			//valida que no exista registro activo
			
			if (list.size()!=0){
				this.addError("Error: ", this.getResourceMessage("mantenimientoZONParametrosRutasForm.msg.validaTipoNovedad"));
				return false;	
			}else{
				//Nuevo registro - INSERT
				service.insertParametroRutaDirectorio(params);
			}
		}
		else{			
			//Registro existente - UPDATE
			service.updateParametroRutaDirectorio(params);
		}
		
		return true;
		}
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		if(log.isDebugEnabled()){
			log.debug("Entering 'edit' method");
		}
		
		MantenimientoZONParametrosRutasForm objForm = new MantenimientoZONParametrosRutasForm();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		objForm.setIndicadorEdit(Constants.NUMERO_CERO);
		objForm.setCodigoPais(pais.getCodigo());
		
		Map p = new HashMap();
		p.put("codigoPais", pais.getCodigo());
		p.put("codigoSistema", "ZON");
        p.put("nombreParametro", "indEmailOrigen");
		
		MantenimientoSTOBloqueoControlService mantenimientoSTOBloqueoControlService = (MantenimientoSTOBloqueoControlService)getBean("spusicc.mantenimientoSTOBloqueoControlService");		
		String emailOrigen = mantenimientoSTOBloqueoControlService.getParametroGenericoSistema(p); 		
		objForm.setEmailOrigen(emailOrigen);
		
		if(!this.accion.equals(this.ACCION_NUEVO)){
		
		Map obj = new HashMap(); 
		obj = (HashMap) this.beanRegistroSeleccionado;
		BeanUtils.copyProperties(objForm, obj);
		
		MantenimientoZONDirectorioService serviceDir = (MantenimientoZONDirectorioService)this.getBean("spusicc.mantenimientoZONDirectorioService");
		MantenimientoZONParametrosRutasService service = (MantenimientoZONParametrosRutasService)getBean("spusicc.mantenimientoZONParametrosRutasService");				
		
			objForm.setIndicadorEdit(Constants.NUMERO_UNO);
			Map criteria = new HashMap();
			this.listaOperaciones = serviceDir.getOperaciones(criteria);
			
			//String codigo=objForm.getSelectedItems()[0];
			criteria.put("codigoPais", objForm.getCodigoPais());//StringUtils.split(codigo, "|")[0]
			criteria.put("codigoNovedad", objForm.getCodigoNovedad()); //StringUtils.split(codigo, "|")[1]
			Map result = new HashMap();
			result = (Map)service.getParametrosRutasList(criteria).get(0);
			
			cargarDatos(objForm, result);
			if(objForm.getIndicadorDesactivarEnvio().equals(Constants.NUMERO_UNO)){
				objForm.setIndicadorDesactivarEnvio("true");
			}else{objForm.setIndicadorDesactivarEnvio("false");}
					
			objForm.setIndicadorEdit(Constants.NUMERO_UNO);
		}
		if(StringUtils.equals(this.accion, this.ACCION_CONSULTAR)){
			this.setEsconsulta(true);
		}else{
			this.setEsconsulta(false);
		}
		objForm.setDescripcionNovedad("true");
		objForm.setIndicadorDesactivarEnvioHidden("true");
		return objForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
		MantenimientoZONParametrosRutasSearchForm f = (MantenimientoZONParametrosRutasSearchForm)this.formBusqueda;
		MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService)this.getBean("spusicc.mantenimientoZONDirectorioService");	
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());		
		Map map = new HashMap();
		this.listaOperaciones=service.getOperaciones(map);
		
	}
	
	/**
	 * Setea los datos del formulario en base a un Map
	 * @param f
	 * @param result
	 */
	private void cargarDatos(MantenimientoZONParametrosRutasForm f, Map result) {
		f.setCodigoNovedad(result.get("codigoNovedad").toString());
		f.setDescripcionNovedad(result.get("descripcionNovedad").toString());
		f.setEmailAprobador(result.get("correoUsuarioAprobador").toString());
		f.setEmailDestinatarios(result.get("correosDestinatarios").toString());
		if(result.get("asunto")!=null)
			f.setAsunto(result.get("asunto").toString());
		if(result.get("mensaje")!=null)
			f.setMensaje(result.get("mensaje").toString());
		f.setEmailOrigen(result.get("emailOrigen").toString());
		f.setIndicadorDesactivarEnvioHidden(result.get("indicadorDesactivarEnvio").toString());
	}
	
	
	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoZONParametrosRutasSearchForm f = (MantenimientoZONParametrosRutasSearchForm)this.formBusqueda;
		MantenimientoZONParametrosRutasService service = (MantenimientoZONParametrosRutasService)getBean("spusicc.mantenimientoZONParametrosRutasService");
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoNovedad", f.getCodigoNovedad());		
		
		this.lista = (List)service.getParametrosRutasList(criteria);
		
		return lista;	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
	
		MantenimientoZONParametrosRutasForm objForm = (MantenimientoZONParametrosRutasForm)this.formMantenimiento;
		boolean isNew = objForm.isNewRecord();
		if(this.accion.equals(this.ACCION_NUEVO)){
			return "mantenimientoZONParametrosRutasForm.insert";
		}else{
			return "mantenimientoZONParametrosRutasForm.update";
		}	
	}

	public boolean isEsconsulta() {
		return esconsulta;
	}

	public void setEsconsulta(boolean esconsulta) {
		this.esconsulta = esconsulta;
	}

}
