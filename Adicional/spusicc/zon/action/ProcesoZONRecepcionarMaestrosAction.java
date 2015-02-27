package biz.belcorp.ssicc.web.spusicc.zon.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesAdministrativasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.zon.form.ProcesoZONRecepcionarMaestrosForm;

@SessionScoped
@ManagedBean
public class ProcesoZONRecepcionarMaestrosAction extends BaseProcesoAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7063017513995979587L;
	private List cargaArchivos;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoZONRecepcionarMaestrosForm form = new ProcesoZONRecepcionarMaestrosForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		log.debug("Los parametros del Reporte en el executeProcess son: "
				+ params.toString());
		Usuario usuario = (Usuario)params.get("usuario");
		String codigoUsuario = usuario.getLogin();
		params.put("codigoUsuario",codigoUsuario);
		String codigoPais = (String) params.get("codigoPais");
		ProcesoZONActualizarUnidadesAdministrativasService procesoZONActualizarUnidadesAdministrativasService = (ProcesoZONActualizarUnidadesAdministrativasService) getBean("spusicc.procesoZONUniAdmService");
		procesoZONActualizarUnidadesAdministrativasService.executeProcesoZONProcesoCargaRecepcionMaestros(codigoPais,params);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub

		// Carga de los combos
		MantenimientoEDUGenericoService service = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codPais =pais.getCodigo() ;
		
        List interfaces = new ArrayList();
        
        Base base= new Base();
        base.setCodigo("1");
        String proceso = getResourceMessage("procesoZONRecepcionarMaestrosForm.cargaConsultoras");
        base.setDescripcion(proceso);
        interfaces.add(base);
        
        base=new Base();
        base.setCodigo("2");
        proceso = getResourceMessage("procesoZONRecepcionarMaestrosForm.cargaRegiones");
        base.setDescripcion(proceso);
        interfaces.add(base);
        
        base=new Base();
        base.setCodigo("3");
        proceso = getResourceMessage("procesoZONRecepcionarMaestrosForm.cargaZonas");
        base.setDescripcion(proceso);
        interfaces.add(base);
        
        base=new Base();
        base.setCodigo("4");
        proceso = getResourceMessage("procesoZONRecepcionarMaestrosForm.cargaCampanna");
        base.setDescripcion(proceso);
        interfaces.add(base);
        
        base=new Base();
        base.setCodigo("5");
        proceso = getResourceMessage("procesoZONRecepcionarMaestrosForm.cargaControlFacturacion");
        base.setDescripcion(proceso);
        interfaces.add(base);
        this.cargaArchivos = interfaces;
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseProcesoHiloAbstractAction#prepareParamsBeforeExecute(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
	 */
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {	
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParamsBeforeExecute' method");
		}
		params = super.prepareParamsBeforeExecute(params, form);
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoPais = pais.getCodigo();
		params.put("codigoPais", codigoPais);
		params.put("usuario", usuario);
		return params;
	}

	/**
	 * @return the cargaArchivos
	 */
	public List getCargaArchivos() {
		return cargaArchivos;
	}

	/**
	 * @param cargaArchivos the cargaArchivos to set
	 */
	public void setCargaArchivos(List cargaArchivos) {
		this.cargaArchivos = cargaArchivos;
	}
	
	

}
