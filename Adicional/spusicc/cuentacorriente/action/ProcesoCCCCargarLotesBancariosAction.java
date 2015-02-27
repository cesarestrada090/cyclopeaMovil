package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.ProcesoCCCCargarLotesBancariosForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"unchecked", "rawtypes"})
public class ProcesoCCCCargarLotesBancariosAction extends BaseInterfazAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5753456202387410154L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception 
	{
		ProcesoCCCCargarLotesBancariosForm formInterfaz= new ProcesoCCCCargarLotesBancariosForm();
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();	
				
		ProcesoCCCCargarLotesBancariosForm f = (ProcesoCCCCargarLotesBancariosForm) this.formInterfaz;
		
		f.setCodigoModulo(this.parametrosPantalla.get("codigoSistema"));
		f.setCodigoProceso(this.parametrosPantalla.get("codigoProcesoBatch"));
		
		log.debug(f.getCodigoModulo());
		log.debug(f.getCodigoProceso());
		
		InterfazPK interfazEjecucionPK = new InterfazPK(pais.getCodigo(), f.getCodigoSistema(), f.getCodigoInterfaz());
		
		log.debug("Obtengo la Interface en Ejecucion");
		
		InterfazService interfazService = (InterfazService) getBean("sisicc.interfazService");
		Interfaz interfazEjecucion = interfazService.getInterfaz(interfazEjecucionPK);
									
		/*List archivos = null;
		archivos = getListaArchivos(interfazEjecucion);
		
		log.debug(archivos.toString());
		
		request.getSession().setAttribute("archivoslist",archivos);		*/
	}
	

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		params = super.prepareParamsBeforeExecute(params, form);
		ProcesoCCCCargarLotesBancariosForm f = (ProcesoCCCCargarLotesBancariosForm) this.formInterfaz;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String usuarioProceso = usuario.getNombres() + ' ' + usuario.getApellidos();
		
		params.put("descripcionPais", pais.getDescripcion());
		params.put("usuarioProceso", usuarioProceso);
		params.put("codigoModulo",f.getCodigoModulo());
		params.put("codigoProceso",f.getCodigoProceso());
		params.put("codigoUsuario", usuario.getLogin());
		
		log.debug("FUCK");
		log.debug(f.getCodigoModulo());
		log.debug(f.getCodigoProceso());
		
		log.debug("Los parametros del prepareParamsBeforeExecute son: " + params.toString());
		
		return params;
	}
	
	/**
	 * Metodo que devuelve el listado de archivos que estan en el directorio de entrada de interfaces
	 * @param interfaz
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	/*private List getListaArchivos(Interfaz interfaz) throws Exception 
	{
		String mensaje = this.getResourceMessage("mensaje.error.noExisteArchivo");
		List archivosList = new ArrayList();			
		InterfazParams interfazParams = new InterfazParams();
		interfazParams.setInterfaz(interfaz);
				
		archivosList = interfazParams.getListArchivosEntrada();		
		if(archivosList.size() == 0){
			LabelArchivos labelArchivos = new LabelArchivos();
			labelArchivos.setNombreArchivo(mensaje);
			archivosList.add(labelArchivos);
		}		
		return archivosList;
	}*/	   	

}
