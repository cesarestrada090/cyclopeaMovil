package biz.belcorp.ssicc.web.spusicc.cronograma.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.spusicc.cronograma.MantenimientoCRAActividadService;
import biz.belcorp.ssicc.service.spusicc.cronograma.MantenimientoCRACalendarioService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.cronograma.form.MantenimientoCRACalendarioCopiaPopupForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoCRACalendarioCopiaPopupAction extends
		BaseMantenimientoSearchAbstractAction {
	/**
	 * 
	 */
	private String anhio="";
	private String oidActividad;
	private String nombreActividad;
	private static final long serialVersionUID = -2157397641169060734L;
	private List craActividadRegenerarList = new ArrayList();

	
	
	public List getCraActividadRegenerarList() {
		return craActividadRegenerarList;
	}

	public void setCraActividadRegenerarList(List craActividadRegenerarList) {
		this.craActividadRegenerarList = craActividadRegenerarList;
	}

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCRACalendarioCopiaPopupForm searchForm = new MantenimientoCRACalendarioCopiaPopupForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
//		log.debug("Executing action : setSaveAttributes. ");
//
//		MantenimientoCRACalendarioCopiaPopupForm f = (MantenimientoCRACalendarioCopiaPopupForm) formBusqueda;
//		MantenimientoCRACalendarioService service = (MantenimientoCRACalendarioService) getBean("spusicc.mantenimientoCRACalendarioService");
//
//		String codigoUsuario = mPantallaPrincipalBean.getCurrentUser()
//				.getLogin();
//
//		Map criteria = new HashMap();
//		criteria.put("codigoPais", f.getCodigoPais());
//		criteria.put("anhio", f.getAnhio());
//		criteria.put("oidActiReferencia", f.getOidActividad());
//		criteria.put("actividadRegenerarList",
//				Arrays.asList(f.getActividadRegenerar()));
//		criteria.put("usuario", codigoUsuario);
//
//		service.copiarCalendarioPorActividad(criteria);
//
//		addInfo("Mensaje",
//				this.getResourceMessage("mantenimientoCRACalendarioCopiaPopupForm.msj.registrar"));
//
//		f.setSalirPantalla(Constants.SI);
		return true;
	}
	
	public boolean grabarPop(){
		log.debug("Executing action : setSaveAttributes. ");

		MantenimientoCRACalendarioCopiaPopupForm f = (MantenimientoCRACalendarioCopiaPopupForm) formBusqueda;
		MantenimientoCRACalendarioService service = (MantenimientoCRACalendarioService) getBean("spusicc.mantenimientoCRACalendarioService");

		String codigoUsuario = mPantallaPrincipalBean.getCurrentUser()
				.getLogin();

		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("anhio", f.getAnhio());
		criteria.put("oidActiReferencia", f.getOidActividad());
		criteria.put("actividadRegenerarList",
				Arrays.asList(f.getActividadRegenerar()));
		criteria.put("usuario", codigoUsuario);

		service.copiarCalendarioPorActividad(criteria);

		addInfo("Mensaje",
				this.getResourceMessage("mantenimientoCRACalendarioCopiaPopupForm.msj.registrar"));
		
	
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executing action : setViewAttributes. ");

		MantenimientoCRACalendarioCopiaPopupForm f = (MantenimientoCRACalendarioCopiaPopupForm) formBusqueda;
		f.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		this.mostrarBotonBuscar = false;
		this.mostrarListaBusqueda = false;

	}
	
	public void obtenerValores()  {
		log.debug("Executing action : obtenerValores. ");
		this.mostrarBotonSave=true;
		MantenimientoCRACalendarioCopiaPopupForm f = (MantenimientoCRACalendarioCopiaPopupForm) formBusqueda;
		f.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		
		String anhio = this.anhio;
		String oidActividad = this.oidActividad;

		MantenimientoCRAActividadService service = (MantenimientoCRAActividadService) getBean("spusicc.mantenimientoCRAActividadService");

		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("id", oidActividad);

		List list = service.getActividades(criteria);
		
		f.setNombreActividad(this.nombreActividad);
		f.setAnhio(anhio);
		f.setOidActividad(oidActividad);

		this.craActividadRegenerarList.clear();
		this.craActividadRegenerarList = service.getActividadesExcepto(criteria);
		
		this.formBusqueda = f;
		return;
	}

	/**
	 * @return the anhio
	 */
	public String getAnhio() {
		return anhio;
	}

	/**
	 * @param anhio the anhio to set
	 */
	public void setAnhio(String anhio) {
		this.anhio = anhio;
	}

	/**
	 * @return the oidActividad
	 */
	public String getOidActividad() {
		return oidActividad;
	}

	/**
	 * @param oidActividad the oidActividad to set
	 */
	public void setOidActividad(String oidActividad) {
		this.oidActividad = oidActividad;
	}

	/**
	 * @return the nombreActividad
	 */
	public String getNombreActividad() {
		return nombreActividad;
	}

	/**
	 * @param nombreActividad the nombreActividad to set
	 */
	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}
	
	
	

}
