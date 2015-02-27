package biz.belcorp.ssicc.web.spusicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazSICConcursoDuplaCyzone;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.MantenimientoSICConcursoDuplaCyzoneService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoSICConcursoDuplaCyzoneForm;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoSICConcursoDuplaCyzoneSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"unchecked", "rawtypes"})
public class MantenimientoSICConcursoDuplaCyzoneSearchAction extends BaseMantenimientoSearchAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -974921669397397597L;
	
	private List siccSociedadList;
	private List siccCanalList;
	private List siccMarcaList;
	private List siccConcursoList;
	private List siccPlantillaList;
	
	private List busquedaConcursosMantenerDuplaCyzoneList;
	

	@Override
	protected String getSalirForward()
	{
		return "mantenimientoSICConcursoDuplaCyzoneList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception 
	{
		return "mantenimientoSICConcursoDuplaCyzoneForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception 
	{
		MantenimientoSICConcursoDuplaCyzoneSearchForm searchForm = new MantenimientoSICConcursoDuplaCyzoneSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'find' method");
		}
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		MantenimientoSICConcursoDuplaCyzoneSearchForm f = (MantenimientoSICConcursoDuplaCyzoneSearchForm) this.formBusqueda;
		MantenimientoSICConcursoDuplaCyzoneService service = (MantenimientoSICConcursoDuplaCyzoneService) getBean("spusicc.mantenimientoSICConcursoDuplaCyzoneService");

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoConcurso", f.getCodigoConcursoSearch());
		criteria.put("codigoCanal", f.getCodigoCanalSearch());
		criteria.put("codigoMarca", f.getCodigoMarcaSearch());
		criteria.put("nombreConcurso", f.getNombreConcursoSearch());
		criteria.put("nombrePlantilla", f.getNombrePlantillaSearch());
		criteria.put("codigoPlantilla", f.getCodigoPlantillaSearch());
		criteria.put("version", f.getVersionSearch());
		criteria.put("codigoIdiomaISO", usuario.getIdioma().getCodigoISO());

		List lista = service.getConcursosMantenenimientoDuplaCyzoneList(criteria);
		
		log.debug("Cantidad de resultado " + lista.size());
		
		this.busquedaConcursosMantenerDuplaCyzoneList = lista;
		
//		if (lista == null) 
//		{
//			this.addError("errors.datos.fuentes.busqueda", "");
////			this.getResourceMessage("errors.datos.fuentes.busqueda");
//		}
		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}
		
		MantenimientoSICConcursoDuplaCyzoneForm f = (MantenimientoSICConcursoDuplaCyzoneForm) this.formMantenimiento;
		MantenimientoSICConcursoDuplaCyzoneService service = (MantenimientoSICConcursoDuplaCyzoneService) getBean("spusicc.mantenimientoSICConcursoDuplaCyzoneService");

		InterfazSICConcursoDuplaCyzone concursoDuplaCyzone = new InterfazSICConcursoDuplaCyzone();

		BeanUtils.copyProperties(concursoDuplaCyzone, f);
		if (f.getIndicadorDuplaCyzone()) 
		{
			concursoDuplaCyzone.setIndicadorDupla(Constants.ESTADO_ACTIVO);
		} else 
		{
			concursoDuplaCyzone.setIndicadorDupla(Constants.ESTADO_INACTIVO);
		}

		if (!f.isNewRecord())
		{
			if (log.isDebugEnabled()) {
				log.debug("EN EL CASO QUE SEA MODIFICACION");
			}
			service.updateConcursoDuplaCyzone(concursoDuplaCyzone);
		} else 
		{
			if (log.isDebugEnabled()) {
				log.debug("EN EL CASO QUE SEA NUEVO");
				}

			String nuevoCorrelativo = service.getNextConcursoDuplaCyzone();
			concursoDuplaCyzone.setIdConcursoDuplaCyzone(nuevoCorrelativo);

			service.insertConcursoDuplaCyzone(concursoDuplaCyzone);
			f.setNewRecord(false);
		}
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception 
	{
		HashMap<String, Object> sistemabusqueda = (HashMap<String, Object>)this.beanRegistroSeleccionado;
		
		MantenimientoSICConcursoDuplaCyzoneForm mantenimientoForm = new MantenimientoSICConcursoDuplaCyzoneForm();
		
		mantenimientoForm.setCodigoConcurso("");
		mantenimientoForm.setCodigoPais("");
		mantenimientoForm.setNombreConcurso("");
		mantenimientoForm.setIndicadorDuplaCyzone(false);
		mantenimientoForm.setEditable(true);
		
		if (!this.accion.equals(this.ACCION_NUEVO) ) { 

			  String codigo = sistemabusqueda.get("idConcurso").toString();
			  String nombre = sistemabusqueda.get("nombreConcurso").toString();
			  
			  if(codigo != null)
			  {
				  if (log.isDebugEnabled()) {
	    				log.debug("Id seleccionado de la lista: " + codigo + " " + nombre);
	    			}
				  
				  MantenimientoSICConcursoDuplaCyzoneService service = (MantenimientoSICConcursoDuplaCyzoneService) getBean("spusicc.mantenimientoSICConcursoDuplaCyzoneService");			
				  InterfazSICConcursoDuplaCyzone concursoDuplaCyzone = service.getConcursoDuplaCyzone(codigo);
					
		            BeanUtils.copyProperties(mantenimientoForm, concursoDuplaCyzone);
		            
		            if (concursoDuplaCyzone.getIndicadorDupla()!=null && concursoDuplaCyzone.getIndicadorDupla().equals(Constants.ESTADO_ACTIVO)) {
		            	mantenimientoForm.setIndicadorDuplaCyzone(true);
		            
		            }
		            else {
		            	mantenimientoForm.setIndicadorDuplaCyzone(false);		            
		            }
		            
		            if (concursoDuplaCyzone.getIdConcursoDuplaCyzone() == null ) {
		            	mantenimientoForm.setNewRecord(true);	
		            } else {            	
		            	mantenimientoForm.setNewRecord(false);
		            }
			  }	            
		}		
		return mantenimientoForm;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		this.mostrarBotonConsultar = false;		
		this.mostrarBotonEliminar = false;
		this.mostrarBotonNuevo = false;
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		MantenimientoSICConcursoDuplaCyzoneSearchForm f = (MantenimientoSICConcursoDuplaCyzoneSearchForm) this.formBusqueda;
		// Cargamos los combos a utilizar
		InterfazSiCCService siccService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		this.siccSociedadList = siccService.getSociedadesByCodigoPais(pais.getCodigo());
		this.siccCanalList = siccService.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccMarcaList = siccService.getMarcas();
		Map criteria=new HashMap();
		criteria.put("indicadorDuplaCyzone", Constants.ESTADO_ACTIVO );
		this.siccConcursoList = siccService.getConcursos(criteria);
		this.siccPlantillaList = siccService.getPlantillasConcursos();
		f.setCodigoPaisSearch(pais.getCodigo());
		
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK()
	{
		MantenimientoSICConcursoDuplaCyzoneForm mantenimientoForm = (MantenimientoSICConcursoDuplaCyzoneForm) this.formMantenimiento;
		boolean isNew = mantenimientoForm.isNewRecord();
		if(isNew){
			return "concursoDuplaCyzone.updated";
		}else{
			return "concursoDuplaCyzone.updated";
		}	

	}

	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	public List getSiccConcursoList() {
		return siccConcursoList;
	}

	public void setSiccConcursoList(List siccConcursoList) {
		this.siccConcursoList = siccConcursoList;
	}

	public List getSiccPlantillaList() {
		return siccPlantillaList;
	}

	public void setSiccPlantillaList(List siccPlantillaList) {
		this.siccPlantillaList = siccPlantillaList;
	}

	public List getBusquedaConcursosMantenerDuplaCyzoneList() {
		return busquedaConcursosMantenerDuplaCyzoneList;
	}

	public void setBusquedaConcursosMantenerDuplaCyzoneList(
			List busquedaConcursosMantenerDuplaCyzoneList) {
		this.busquedaConcursosMantenerDuplaCyzoneList = busquedaConcursosMantenerDuplaCyzoneList;
	}
}
