package biz.belcorp.ssicc.web.spusicc.cobranzas.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ProcesoCOBCargarCronogramaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseCargaArchivoAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseCargaArchivoForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.ProcesoCOBCargarCronogramaForm;


/**
 * The Class ProcesoCOBCargarCronogramaAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 05/05/2014
 */

@ManagedBean  
@SessionScoped
public class ProcesoCOBCargarCronogramaAction extends BaseCargaArchivoAbstractAction {

	private static final long serialVersionUID = 1L;
	
	private List listaTipoEtapa = new ArrayList();

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		this.mostrarCabeceraFija = false;
		ProcesoCOBCargarCronogramaService service = (ProcesoCOBCargarCronogramaService) getBean("spusicc.procesoCOBCargarCronogramaService");
		Map criteria = new HashMap();
		criteria.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		criteria.put("indicadorCobranzaExterna", Constants.SI);
		listaTipoEtapa = service.getTiposEtapa(criteria);
		ProcesoCOBCargarCronogramaForm f = (ProcesoCOBCargarCronogramaForm)this.formCargaArchivo;
		
		
	}	
	
	@Override
	public BaseCargaArchivoForm setUploadAttibuttes(Map<String, Object> criteria) throws Exception {
		ProcesoCOBCargarCronogramaForm f = (ProcesoCOBCargarCronogramaForm) this.formCargaArchivo;
		
		ProcesoCOBCargarCronogramaService service = (ProcesoCOBCargarCronogramaService) getBean("spusicc.procesoCOBCargarCronogramaService");
		String ruta = service.obtenerPathUpload(criteria)!=null?
				service.obtenerPathUpload(criteria): service.obtenerPathUploadEstandar(criteria)!=null?
						service.obtenerPathUploadEstandar(criteria):this.obtenerPathPersonalizado();
		log.debug("ruta: " + ruta);
		f.setDirectorioTemporal(ruta);
		return f;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseCargaArchivoAbstractAction#setValidarAttributes(java.util.Map)
	 */
	protected List setValidarAttributes(Map<String, Object> datos) throws Exception {
		ProcesoCOBCargarCronogramaForm f = (ProcesoCOBCargarCronogramaForm)this.formCargaArchivo;
		
		datos.put("codigoEtapa", f.getTipoEtapa());
		if(StringUtils.isNotBlank(f.getTipoEtapa()) && StringUtils.isNotEmpty(f.getTipoEtapa())){
			EqualPredicate nameEqlPredicate = new EqualPredicate(f.getTipoEtapa());
			BeanPredicate beanPredicate = new BeanPredicate("codigo", nameEqlPredicate);
			Object objeto = CollectionUtils.find(this.getListaTipoEtapa(),beanPredicate); 
			datos.put("descripcionEtapa", ((Base)objeto).getDescripcion());
			
		}
		
		ProcesoCOBCargarCronogramaService service = (ProcesoCOBCargarCronogramaService) getBean("spusicc.procesoCOBCargarCronogramaService");				
	    List lista = service.executeValidarCargarCronograma(datos);
	    
	    String descripcionEtapa = (String) datos.get("descripcionEtapa");
		f.setDescripcionTipoEtapa(descripcionEtapa);
		
		return lista;
	}

	
	protected void setProcesarAttributes(Map<String, Object> datos) throws Exception {
		
	}
	
	
	
	
	/**
	 * Metodo que se encarga de ejecutar el proceso de la carga del archivo.
	 *
	 * @param e the e
	 */
	/**
	 * @param e
	 */
	public void procesar(ActionEvent e){
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'procesar' method");
		}
		
//        List lista = this.getCobCargarCronogramaList();
//		
//		ProcesoCOBCargarCronogramaService service = (ProcesoCOBCargarCronogramaService) getBean("spusicc.procesoCOBCargarCronogramaService");
//		ProcesoCOBCargarCronogramaForm f = (ProcesoCOBCargarCronogramaForm) this.formCargaArchivo;
//		        
//		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
//		log.debug("usuario"+usuario.getLogin());
//		
//		Map datos = new HashMap();        
//        datos.put("codigoPais",f.getCodigoPais());         
//        datos.put("codigoUsuario",usuario.getLogin());        
//        datos.put("usuario", usuario);	
//        datos.put("lista", lista);   
//		
//		service.executeProcesarCargarCronograma(datos);
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseCargaArchivoAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseCargaArchivoForm devuelveFormCarga() throws Exception {
		ProcesoCOBCargarCronogramaForm f = new ProcesoCOBCargarCronogramaForm();
		return f;
	}
	
	/**
	 * Metodos GET -- SET
	 * 
	 */

	public List getListaTipoEtapa() {
		return listaTipoEtapa;
	}

	public void setListaTipoEtapa(List listaTipoEtapa) {
		this.listaTipoEtapa = listaTipoEtapa;
	}

	

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}




	

}
