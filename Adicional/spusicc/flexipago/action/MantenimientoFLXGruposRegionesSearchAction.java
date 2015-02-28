package biz.belcorp.ssicc.web.spusicc.flexipago.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.model.DualListModel;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.BaseOID;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.GrupoFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.GrupoRegionFLX;
import biz.belcorp.ssicc.service.spusicc.flexipago.MantenimientoFLXModeloOtorgamientoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.flexipago.form.MantenimientoFLXGruposRegionesForm;
import biz.belcorp.ssicc.web.spusicc.flexipago.form.MantenimientoFLXGruposRegionesSearchForm;


@ManagedBean
@SessionScoped
public class MantenimientoFLXGruposRegionesSearchAction extends BaseMantenimientoSearchAbstractAction{

	private static final long serialVersionUID = -8785418658223213857L;
	
	private List flxGrupoList;
	private List flxGrupoRegionList;
	private List flxRegionesDisponibles;
	private List flxRegionesAsignadas;
	
	private DualListModel<BaseOID> listaRegiones;
	

	@Override
	protected String getSalirForward() {		
		return "mantenimientoFLXGruposRegionesList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoFLXGruposRegionesForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoFLXGruposRegionesSearchForm searchForm = new MantenimientoFLXGruposRegionesSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAttributes - MantenimientoFLXGruposRegionesSearchAction");
		}
		MantenimientoFLXGruposRegionesSearchForm f = (MantenimientoFLXGruposRegionesSearchForm) this.formBusqueda;
		MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService) getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");
		List gruposRegionesFLXList = service.getGruposRegiones(f.getCodigoGrupo());
		this.flxGrupoRegionList=gruposRegionesFLXList;
	
		return gruposRegionesFLXList;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return false;
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setSaveAttributes - MantenimientoFLXGruposRegionesSearchAction");
		}
				
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoFLXGruposRegionesForm f = (MantenimientoFLXGruposRegionesForm) this.formMantenimiento;
		Map sistemabusqueda = (Map) this.beanRegistroSeleccionado;
		MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService) getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");
		String codigoGrupo =sistemabusqueda.get("codigoGrupo").toString();
		List sele=this.listaRegiones.getTarget();
		GrupoFLX grupo = new GrupoFLX();
		BeanUtils.copyProperties(f, grupo);
		grupo.setCodigo(codigoGrupo);
		
		List regiones = new ArrayList();
		if(this.listaRegiones.getTarget()!=null && this.listaRegiones.getTarget().size()>0){
			for(int i=0;i<listaRegiones.getTarget().size();i++){	
				BaseOID baseSeleccionada=(BaseOID)listaRegiones.getTarget().get(i);
				 Integer cod=baseSeleccionada.getOid();
				 String codigo = Integer.toString(cod);
				
				if(StringUtils.isNotBlank(codigo)){
					GrupoRegionFLX grupoRegion = new GrupoRegionFLX();
					grupoRegion.setCodigoGrupo(grupo.getCodigo());
					grupoRegion.setCodigoRegion(codigo);
					regiones.add(grupoRegion);				
				}
			}
		}
		
		grupo.setRegiones(regiones);		
		service.updateRegionesGrupo(grupo, usuario);		
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Map sistemabusqueda = (Map) this.beanRegistroSeleccionado;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoFLXGruposRegionesForm f = new MantenimientoFLXGruposRegionesForm();
		
		MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService) getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");
		
		if (!this.accion.equals(this.ACCION_NUEVO)) {
			String codigoGrupo =sistemabusqueda.get("codigoGrupo").toString();		
			String codigoPais = pais.getCodigo();
			
			if (codigoGrupo != null && codigoPais != null) {
				if (log.isDebugEnabled()) {
					log.debug("Id seleccionado de la lista: " + codigoGrupo + " "+ codigoPais);
				}
				
				 
				GrupoFLX grupo = service.getGrupo(codigoGrupo);
				String descri=grupo.getDescripcion();
				
				BeanUtils.copyProperties(grupo, f);
				this.flxRegionesDisponibles=service.getRegionesAgrupadas(codigoGrupo, false);
				this.flxRegionesAsignadas=service.getRegionesAgrupadas(codigoGrupo, true);
				
				this.listaRegiones = new DualListModel<BaseOID>(flxRegionesDisponibles, flxRegionesAsignadas);
				f.setCodigoPais(codigoPais);
				f.setDescripcion(descri);
								
			}		
		
		}
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAttributes - MantenimientoFLXGruposRegionesSearchAction");
		}
		
		MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService) getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");
		this.flxGrupoList=service.getGrupos("%");		
		this.mostrarBotonConsultar=false;
		this.mostrarBotonEliminar=false;
		this.mostrarBotonNuevo=false;
		
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoFLXGruposRegionesForm sistemaForm = (MantenimientoFLXGruposRegionesForm) this.formMantenimiento;
		boolean isNew = sistemaForm.isNewRecord();
		if (isNew) {
			return null;
		} else {
			return "mantenimientoFLXGruposRegionesForm.updated";
		}
	}

	public List getFlxGrupoList() {
		return flxGrupoList;
	}

	public void setFlxGrupoList(List flxGrupoList) {
		this.flxGrupoList = flxGrupoList;
	}

	public List getFlxGrupoRegionList() {
		return flxGrupoRegionList;
	}

	public void setFlxGrupoRegionList(List flxGrupoRegionList) {
		this.flxGrupoRegionList = flxGrupoRegionList;
	}

	public List getFlxRegionesDisponibles() {
		return flxRegionesDisponibles;
	}

	public void setFlxRegionesDisponibles(List flxRegionesDisponibles) {
		this.flxRegionesDisponibles = flxRegionesDisponibles;
	}

	public List getFlxRegionesAsignadas() {
		return flxRegionesAsignadas;
	}

	public void setFlxRegionesAsignadas(List flxRegionesAsignadas) {
		this.flxRegionesAsignadas = flxRegionesAsignadas;
	}

	public DualListModel<BaseOID> getListaRegiones() {
		return listaRegiones;
	}

	public void setListaRegiones(DualListModel<BaseOID> listaRegiones) {
		this.listaRegiones = listaRegiones;
	}	
	
}
