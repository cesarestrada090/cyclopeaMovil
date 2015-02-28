package biz.belcorp.ssicc.web.spusicc.zon.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesAdministrativasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.zon.form.ProcesoZONEnviarEntidadesDirectorioForm;

@ManagedBean
@SessionScoped
public class ProcesoZONEnviarEntidadesDirectorioAction extends BaseProcesoAbstractAction{

	
	private static final long serialVersionUID = -8064135823908217556L;
	
	private String codigoSistema;
	private List cargaArchivos;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoZONEnviarEntidadesDirectorioForm procesoForm =new ProcesoZONEnviarEntidadesDirectorioForm();		
		return procesoForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		
		ProcesoZONEnviarEntidadesDirectorioForm f = (ProcesoZONEnviarEntidadesDirectorioForm)this.formProceso;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();	
		this.codigoSistema = (String) this.parametrosPantalla.get("codigoSistema");
		f.setCodigoSistema(codigoSistema);		
		f.setCodigoPais(pais.getCodigo());		
	
        List interfaces = new ArrayList();
        Base base= new Base();
        base.setCodigo("1");
        String proceso = this.getResourceMessage("procesoZONEnviarEntidadesDirectorioForm.cargaRegiones");
        base.setDescripcion(proceso);
        interfaces.add(base);
        
        base=new Base();
        base.setCodigo("2");
        proceso = this.getResourceMessage("procesoZONEnviarEntidadesDirectorioForm.cargaZonas");
        base.setDescripcion(proceso);
        interfaces.add(base);
        
        base=new Base();
        base.setCodigo("3");
        proceso =this.getResourceMessage("procesoZONEnviarEntidadesDirectorioForm.cargaHistorialGerentes");
        base.setDescripcion(proceso);
        interfaces.add(base);
        
       this.cargaArchivos=interfaces;
        
		
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		
		log.debug("Los parametros del Reporte en el executeProcess son: "
				+ params.toString());
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codigoUsuario = usuario.getLogin();
		params.put("codigoUsuario",codigoUsuario);
		String codigoPais = pais.getCodigo();
		ProcesoZONActualizarUnidadesAdministrativasService procesoZONActualizarUnidadesAdministrativasService = (ProcesoZONActualizarUnidadesAdministrativasService) getBean("spusicc.procesoZONUniAdmService");
		procesoZONActualizarUnidadesAdministrativasService.executeProcesoZONProcesoEnviarEntidadesDirectorio(codigoPais,params);
		return params;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseProcesoHiloAbstractAction#prepareParamsBeforeExecute(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
	 */
	protected Map<String, Object> prepareParamsBeforeExecute(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParamsBeforeExecute' method");
		}
		ProcesoZONEnviarEntidadesDirectorioForm f = (ProcesoZONEnviarEntidadesDirectorioForm)this.formProceso;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codigoPais = f.getCodigoPais();
		params = super.prepareParamsBeforeExecute(params);	
		params.put("codigoPais", codigoPais);
		params.put("usuario", usuario);
		return params;
	}

	public String getCodigoSistema() {
		return codigoSistema;
	}

	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}

	public List getCargaArchivos() {
		return cargaArchivos;
	}

	public void setCargaArchivos(List cargaArchivos) {
		this.cargaArchivos = cargaArchivos;
	}
	

	

}
