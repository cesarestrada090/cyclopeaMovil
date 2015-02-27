package biz.belcorp.ssicc.web.spusicc.app.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.app.ProcesoAPPSecuenciarZonaTerritorioService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.app.form.ProcesoAPPZonasTerritoriosSinSecuenciaForm;

@ManagedBean
@SessionScoped
public class ProcesoAPPZonasTerritoriosSinSecuenciaAction extends BaseMantenimientoSearchAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2559305383512976618L;
	
	private List appZonaSinSecuenciarList;
	private List appTerritorioSinSecuenciarList;
		
	@Override
	protected String getSalirForward() 
	{
		return "procesoAPPZonasTerritoriosSinSecuenciaForm";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ProcesoAPPZonasTerritoriosSinSecuenciaForm searchForm = new ProcesoAPPZonasTerritoriosSinSecuenciaForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception 
	{
		ProcesoAPPSecuenciarZonaTerritorioService service = (ProcesoAPPSecuenciarZonaTerritorioService) getBean("spusicc.procesoAPPSecuenciarZonaTerritorioService");
		
		List listaZonas = service.getZonasSinSecuenciarList();
		
		this.appZonaSinSecuenciarList = listaZonas;
		
		List listaTerritorios = service.getTerritoriosSinSecuenciarList();
		this.appTerritorioSinSecuenciarList = listaTerritorios;
		
		List lista = new ArrayList();
		lista.add("0");
		
		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		this.mostrarBotonConsultar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		
		ProcesoAPPSecuenciarZonaTerritorioService service = (ProcesoAPPSecuenciarZonaTerritorioService) getBean("spusicc.procesoAPPSecuenciarZonaTerritorioService");
		service.deleteRutasTerri();
	}
		
	private void procesar() throws Exception
	{
		if (log.isDebugEnabled()){
			log.debug("Entering 'procesar' method");
		}
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		Map criteria = new HashMap();
		criteria.put("codUsuario", usuario.getLogin());
		
		ActionMessages messages = new ActionMessages();
		
		ProcesoAPPSecuenciarZonaTerritorioService service = (ProcesoAPPSecuenciarZonaTerritorioService) getBean("spusicc.procesoAPPSecuenciarZonaTerritorioService");
		service.executeProcesoSecuenciarZonaTerritorio(criteria);
		
		this.getResourceMessage("procesoAPPZonasTerritoriosSinSecuenciaForm.process");
		
		setFindAttributes();
	}
	
	public List getAppZonaSinSecuenciarList() {
		return appZonaSinSecuenciarList;
	}

	public void setAppZonaSinSecuenciarList(List appZonaSinSecuenciarList) {
		this.appZonaSinSecuenciarList = appZonaSinSecuenciarList;
	}

	public List getAppTerritorioSinSecuenciarList() {
		return appTerritorioSinSecuenciarList;
	}

	public void setAppTerritorioSinSecuenciarList(
			List appTerritorioSinSecuenciarList) {
		this.appTerritorioSinSecuenciarList = appTerritorioSinSecuenciarList;
	}
}
