package biz.belcorp.ssicc.web.spusicc.cronograma.action;
import java.lang.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DualListModel;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.cronograma.MantenimientoCRAGrupoZonaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.form.ReporteAPEArmadoMgpedxdiaForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.MantenimientoCOBCobradorUnidadAdministrativaForm;
import biz.belcorp.ssicc.web.spusicc.cronograma.form.MantenimientoCRAGrupoZonaForm;
import biz.belcorp.ssicc.web.spusicc.cronograma.form.MantenimientoCRAGrupoZonaSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoCRAGrupoZonaSearchAction extends
		BaseMantenimientoSearchAbstractAction {


	public  MantenimientoCRAGrupoZonaSearchAction() {
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 9046087508667111219L;

	private List siccEtapaDeudaList;

	private boolean asignarDisponibleTab = true;
	private List craGruposComboList = new ArrayList();
	private List craRegionNoAsignadasList;

	private List craZonaAsignadasEntreGrupo = new ArrayList();

	// ***** Asignar Disponible *****
	private List craZonaNoAsignadasTemp = new ArrayList();
	private LabelValue[] craZonaNoAsignadas = {};
	private List craZonaAsignadas;

	// ** Mover Entre Grupo
	private List craZonasGrupoInicial;
	private LabelValue[] craZonasGrupoFinal;
	private List craZonasGrupoFinalTemp = new ArrayList();
	private List craGruposList = new ArrayList();

	private List zonaSelecionadas = new ArrayList();

	// List Dual
	private DualListModel<Base> listaZonas;
	private DualListModel<Base> listaZonasEntreGrupos;

	public boolean isAsignarDisponibleTab() {
		return asignarDisponibleTab;
	}

	public void setAsignarDisponibleTab(boolean asignarDisponibleTab) {
		this.asignarDisponibleTab = asignarDisponibleTab;
	}

	public List getCraZonasGrupoFinalTemp() {
		return craZonasGrupoFinalTemp;
	}

	public void setCraZonasGrupoFinalTemp(List craZonasGrupoFinalTemp) {
		this.craZonasGrupoFinalTemp = craZonasGrupoFinalTemp;
	}

	public List getCraZonaAsignadasEntreGrupo() {
		return craZonaAsignadasEntreGrupo;
	}

	public void setCraZonaAsignadasEntreGrupo(List craZonaAsignadasEntreGrupo) {
		this.craZonaAsignadasEntreGrupo = craZonaAsignadasEntreGrupo;
	}

	public DualListModel<Base> getListaZonasEntreGrupos() {
		return listaZonasEntreGrupos;
	}

	public void setListaZonasEntreGrupos(
			DualListModel<Base> listaZonasEntreGrupos) {
		this.listaZonasEntreGrupos = listaZonasEntreGrupos;
	}

	public List getCraZonaNoAsignadasTemp() {
		return craZonaNoAsignadasTemp;
	}

	public void setCraZonaNoAsignadasTemp(List craZonaNoAsignadasTemp) {
		this.craZonaNoAsignadasTemp = craZonaNoAsignadasTemp;
	}

	public List getZonaSelecionadas() {
		return zonaSelecionadas;
	}

	public void setZonaSelecionadas(List zonaSelecionadas) {
		this.zonaSelecionadas = zonaSelecionadas;
	}

	public DualListModel<Base> getListaZonas() {
		return listaZonas;
	}

	public void setListaZonas(DualListModel<Base> listaZonas) {
		this.listaZonas = listaZonas;
	}

	public LabelValue[] getCraZonaNoAsignadas() {
		return craZonaNoAsignadas;
	}

	public void setCraZonaNoAsignadas(LabelValue[] craZonaNoAsignadas) {
		this.craZonaNoAsignadas = craZonaNoAsignadas;
	}

	public List getCraZonasGrupoInicial() {
		return craZonasGrupoInicial;
	}

	public void setCraZonasGrupoInicial(List craZonasGrupoInicial) {
		this.craZonasGrupoInicial = craZonasGrupoInicial;
	}

	public LabelValue[] getCraZonasGrupoFinal() {
		return craZonasGrupoFinal;
	}

	public void setCraZonasGrupoFinal(LabelValue[] craZonasGrupoFinal) {
		this.craZonasGrupoFinal = craZonasGrupoFinal;
	}

	public List getCraRegionNoAsignadasList() {
		return craRegionNoAsignadasList;
	}

	public void setCraRegionNoAsignadasList(List craRegionNoAsignadasList) {
		this.craRegionNoAsignadasList = craRegionNoAsignadasList;
	}

	public List getCraZonaAsignadas() {
		return craZonaAsignadas;
	}

	public void setCraZonaAsignadas(List craZonaAsignadas) {
		this.craZonaAsignadas = craZonaAsignadas;
	}

	public List getCraGruposList() {
		return craGruposList;
	}

	public void setCraGruposList(List craGruposList) {
		this.craGruposList = craGruposList;
	}

	public List getCraGruposComboList() {
		return craGruposComboList;
	}

	public void setCraGruposComboList(List craGruposComboList) {
		this.craGruposComboList = craGruposComboList;
	}

	@Override
	protected String getSalirForward() {
		return "mantenimientoCRAGrupoZonaList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {

		return "mantenimientoCRAGrupoZonaForm";
	}


	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCRAGrupoZonaSearchForm searchForm = new MantenimientoCRAGrupoZonaSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		log.debug("setFindAttributes");

		MantenimientoCRAGrupoZonaSearchForm f = (MantenimientoCRAGrupoZonaSearchForm) formBusqueda;
		MantenimientoCRAGrupoZonaService service = (MantenimientoCRAGrupoZonaService) getBean("spusicc.mantenimientoCRAGrupoZonaService");

		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();

		Map criteria = new HashMap();

		criteria.put("oidGrupo", f.getOidGrupo());

		List list = new ArrayList();
		list = service.getGrupos(criteria);
		craGruposList = list;

		criteria.put("oidGrupo", "");
		list = service.getGrupos(criteria);

		craGruposComboList = list;

		return list;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {

		MantenimientoCRAGrupoZonaSearchForm f = (MantenimientoCRAGrupoZonaSearchForm) formBusqueda;
		MantenimientoCRAGrupoZonaService service = (MantenimientoCRAGrupoZonaService) getBean("spusicc.mantenimientoCRAGrupoZonaService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		HashMap<String, Object> grupos = (HashMap<String, Object>) this.beanRegistroSeleccionado;
		String oid = grupos.get("codigo").toString();
		Map criteria = new HashMap();
		String id = oid;
		if (id != null) {

			if (log.isDebugEnabled()) {
				log.debug("Id seleccionado de la lista: " + id);
			}
			try {

				criteria.put("usuario", usuario.getLogin());
				criteria.put("oidGrupo", oid);
				// Eliminar el grupo de zona y delete en la matriz
				service.deleteGrupoZona(criteria);
				this.getResourceMessage("mantenimientoCRAGrupoZonaForm.deleted");

			} catch (Exception e) {
				String error = e.getMessage();
				if (StringUtils.isBlank(error))
					error = e.getLocalizedMessage();
				throw new Exception(this.getResourceMessage("errors.detail",
						new Object[] { error }));
			}
		}
		return true;

	}

	public void showZonasxRegion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		log.debug("val: " + ArrayUtils.toString(val.getNewValue()));
		MantenimientoCRAGrupoZonaForm form = (MantenimientoCRAGrupoZonaForm) this.formMantenimiento;
		String[] regiones = (String[]) val.getNewValue();

		if (!val.equals(null) && regiones.length > 0) {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			craZonaNoAsignadas = aSvc.getZonasNoAsignadas(regiones);
			craZonaNoAsignadasTemp.clear();
			for (int i = 0; i < craZonaNoAsignadas.length; i++) {
				Base b = new Base();
				b.setCodigo(craZonaNoAsignadas[i].getValue());
				b.setDescripcion(craZonaNoAsignadas[i].getLabel());
				craZonaNoAsignadasTemp.add(b);

			}
			craZonaNoAsignadas = null;
		} else {
			craZonaNoAsignadas = null;
			craZonaNoAsignadasTemp.clear();
		}

	}

	@Override
	protected boolean setSaveAttributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}

		MantenimientoCRAGrupoZonaForm f = (MantenimientoCRAGrupoZonaForm) formMantenimiento;

		MantenimientoCRAGrupoZonaService service = (MantenimientoCRAGrupoZonaService) getBean("spusicc.mantenimientoCRAGrupoZonaService");

		String codigoUsuario = mPantallaPrincipalBean.getCurrentUser()
				.getLogin();

		Map map = new HashMap();

		try {
			map.put("codigoPais", f.getCodigoPais());
			map.put("nombreGrupo", f.getNombreGrupo());
			map.put("marca", Constants.CODIGO_MARCA_DEFAULT);
			map.put("canal", Constants.CODIGO_CANAL_DEFAULT);
			map.put("acceso", Constants.CODIGO_ACCESO_DEFAULT);
			map.put("usuario", codigoUsuario);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			String anhio = sdf.format(new Date(System.currentTimeMillis()));

			map.put("anhio", anhio);

			// Inserta grupo de zona y en el calendario
			if (f.isNewRecord())
				service.insertNombreGrupoZona(map);
			else {

				if (asignarDisponibleTab) {//seteado en el evento del tab
					List result=listaZonas.getTarget();
					String[] arrayZonasAsignadas = new String[result.size()];
					for (int i = 0; i < result.size(); i++)
						arrayZonasAsignadas[i] = ((Base) result.get(i))
								.getCodigo();
					f.setZonasAsignadas(arrayZonasAsignadas);
					map.put("oidGrupo", f.getOidGrupo());
					
					service.updateNombreGrupoZona(map);
					map.put("zonas", f.getZonasAsignadas());
					service.updateZonasAsignadas(map);

				} else {

					
					// *** seteando grupo inicial de la lista ***
					
					List result=listaZonasEntreGrupos.getTarget();
					String[] arrayZonasGrupoInicial = new String[result.size()];
					for (int i = 0; i <result.size(); i++)
						arrayZonasGrupoInicial[i] = ((Base) result.get(i))
								.getCodigo();

					f.setZonasGrupoInicial(arrayZonasGrupoInicial);
					
					
					
					// *** seteando grupo inicial de la lista ***
					
					String[] arrayZonasGrupoFinal = new String[craZonasGrupoFinalTemp.size()];
					for (int i = 0; i < craZonasGrupoFinalTemp.size(); i++)
						arrayZonasGrupoFinal[i] = ((Base) craZonasGrupoFinalTemp.get(i))
								.getCodigo();

					f.setZonasGrupoFinal(arrayZonasGrupoFinal);
					
					
					
					
					
					
					map.put("oidGrupo", f.getOidGrupo());
					service.updateNombreGrupoZona(map);
					map.put("zonas", f.getZonasGrupoInicial());
					service.updateZonasAsignadas(map);
					map.put("oidGrupo", f.getOidGrupoFinal());
					map.put("zonas", f.getZonasGrupoFinal());
					service.updateZonasAsignadas(map);

				}
			}

			return true;

		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error))
				error = e.getLocalizedMessage();
			throw new Exception(this.getResourceMessage(
					"mantenimientoCRAGrupoZonaForm.insert.error",
					new Object[] { error }));
		}

	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {

		/* *********** METODO ADD ******** */

		MantenimientoCRAGrupoZonaForm f = new MantenimientoCRAGrupoZonaForm();

		f.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		f.setOidGrupo("");
		f.setNombreGrupo("");

		/************** Fin ADD ***********/

		/* METODO CONSULTAR */
		log.debug("JFA Action: Entering setConsultarAttributes");

		HashMap<String, Object> grupos = (HashMap<String, Object>) this.beanRegistroSeleccionado;

		if (!this.accion.equals(this.ACCION_NUEVO)) {
			f.setNewRecord(false);
			String oid = grupos.get("codigo").toString();
			String desc = grupos.get("descripcion").toString();

			log.debug("setEditAttributes");

			Pais pais = mPantallaPrincipalBean.getCurrentCountry();

			MantenimientoCRAGrupoZonaService service = (MantenimientoCRAGrupoZonaService) getBean("spusicc.mantenimientoCRAGrupoZonaService");

			String id = oid;

			if (id != null) {

				if (log.isDebugEnabled()) {
					log.debug("Id seleccionado de la lista: " + id);
				}

				f.setCodigoPais(pais.getCodigo());
				f.setOidGrupo(oid);
				f.setNombreGrupo(desc);
				f.setOidGrupoFinal("");
				f.setGrupoFinal("");

				Map criteria = new HashMap();
				criteria.put("oidGrupo", "");

				String grupoZonaList[] = { f.getOidGrupo() };
				criteria.put("grupoZonaList", grupoZonaList);

				// Mover Entre Grupos
				craZonasGrupoInicial = service.getZonaAsignadasGrupo(criteria);
				craRegionNoAsignadasList = service.getGrupoRegionNoAsignadas();
				craZonaAsignadas = service.getZonaAsignadasGrupo(criteria);

				// Entre Grupos
				craGruposList = service.getGrupos(criteria);
				craZonasGrupoInicial.clear();
				craZonasGrupoInicial.addAll(craZonaAsignadas);

				// Asignar Disponible

				if (craZonaNoAsignadas != null) {
					for (int i = 0; i < craZonaNoAsignadas.length; i++) {
						Base b = new Base();
						b.setCodigo(craZonaNoAsignadas[i].getValue());
						b.setDescripcion(craZonaNoAsignadas[i].getLabel());
						craZonaNoAsignadasTemp.add(b);
					}

				}
				if (craZonasGrupoFinal != null) {
					for (int i = 0; i < craZonasGrupoFinal.length; i++) {
						Base b = new Base();
						b.setCodigo(craZonasGrupoFinal[i].getValue());
						b.setDescripcion(craZonasGrupoFinal[i].getLabel());
						craZonasGrupoFinalTemp.add(b);
					}

				}
				this.listaZonas = new DualListModel<Base>(
						craZonaNoAsignadasTemp, craZonaAsignadas);
				this.listaZonasEntreGrupos = new DualListModel<Base>(
						craZonasGrupoInicial, craZonasGrupoFinalTemp);
			}

		}

		return f;

	}

	public void getZonasGrupo(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		log.debug("val: " + ArrayUtils.toString(val.getNewValue()));
		String grupos = (String) val.getNewValue();
		String[] valor = new String[] { grupos };
		if (!val.equals(null) && grupos.length() > 0) {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			craZonasGrupoFinal = aSvc.getZonasGrupo(valor);
			craZonasGrupoFinalTemp.clear();
			for (int i = 0; i < craZonasGrupoFinal.length; i++) {
				Base b = new Base();
				b.setCodigo(craZonasGrupoFinal[i].getValue());
				b.setDescripcion(craZonasGrupoFinal[i].getLabel());
				craZonasGrupoFinalTemp.add(b);

			}
			craZonasGrupoFinal = null;
		} else {
			craZonasGrupoFinal = null;
			craZonasGrupoFinalTemp.clear();
		}

	}

	public void cambioTab(TabChangeEvent event) {
		String valorTab = event.getTab().getId();
		if(valorTab.equalsIgnoreCase("asignar")){
			this.asignarDisponibleTab=true;
		}
		else
			this.asignarDisponibleTab=false;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("setViewAttributes");
		this.mostrarBotonConsultar = false;
		MantenimientoCRAGrupoZonaSearchForm f = (MantenimientoCRAGrupoZonaSearchForm) formBusqueda;
		MantenimientoCRAGrupoZonaService service = (MantenimientoCRAGrupoZonaService) getBean("spusicc.mantenimientoCRAGrupoZonaService");
		f.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		Map criteria = new HashMap();

		criteria.put("oidGrupo", "");

		List list = new ArrayList();
		list = service.getGrupos(criteria);

		craGruposList = list;
		craGruposComboList = list;
		f.setOidGrupo("");
		
		this.listaBusqueda=this.setFindAttributes();
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
		
	}

	/**
	 * @param
	 * @throws Exception
	 */

	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoCRAGrupoZonaForm cobradorForm = (MantenimientoCRAGrupoZonaForm) this.formMantenimiento;
		boolean isNew = cobradorForm.isNewRecord();
		if (isNew) {
			return "mantenimientoCRAGrupoZonaForm.add.success";
		} else {
			return "mantenimientoCRAGrupoZonaForm.update.success";
		}
	}

	public List getSiccEtapaDeudaList() {
		return siccEtapaDeudaList;
	}

	public void setSiccEtapaDeudaList(List siccEtapaDeudaList) {
		this.siccEtapaDeudaList = siccEtapaDeudaList;
	}

}
