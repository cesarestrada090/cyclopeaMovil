package biz.belcorp.ssicc.web.spusicc.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.Estimado;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.MantenimientoDATEstimadosService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMPorcentajeComisionService;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCalculoCalificacionTramoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMCalificacionComisionForm;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoDATEstimadosSearchForm;

@SessionScoped
@ManagedBean
public class MantenimientoDATEstimadosSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8693514371461481532L;
	private boolean viewEdit;
	private boolean viewConsultar;
	private String codigoPais;
	private List siccMarcaList;
	private List siccCanalList;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private LabelValue[] siccSeccionList = {};
	private List siccRangoPeriodoList;
	private ArrayList listaEstimada;

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoDATEstimadosList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoDATEstimadosList";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoDATEstimadosSearchForm form = new MantenimientoDATEstimadosSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}
		MantenimientoDATEstimadosSearchForm searchForm = (MantenimientoDATEstimadosSearchForm) this.formBusqueda;
		searchForm.setCodigoPais(codigoPais);
		// Obtenemmos el periodo Inicio y Fin
		MantenimientoDATEstimadosService service = (MantenimientoDATEstimadosService) getBean("spusicc.mantenimientoDATEstimadosService");
		Map criteria = obtenerPeriodoInicioFin(searchForm, service);

		// Realizamos la búsqueda con los criterios ingresados
		List listaEstimados = service.getEstimadosByCriteria(criteria);
		ArrayList listaEstimadosAux = new ArrayList(listaEstimados);

		if (listaEstimadosAux.size() > 0)
			searchForm.listaACampos(listaEstimadosAux);
		else {
			addError("Error",
					getResourceMessage("errors.datos.fuentes.busqueda"));
		}

		this.listaEstimada = listaEstimadosAux;
		this.viewConsultar=true;
		this.viewEdit=false;
		return listaEstimadosAux;
	}

	private Map obtenerPeriodoInicioFin(
			MantenimientoDATEstimadosSearchForm searchForm,
			MantenimientoDATEstimadosService service)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Map criteria = BeanUtils.describe(searchForm);
		String periodoInicio = service.getPeriodoInicio(searchForm
				.getCodigoRangoPeriodo());
		String periodoFin = service.getPeriodoFin(searchForm
				.getCodigoRangoPeriodo());
		String periodoMenor = searchForm.getCodigoAnio() + periodoInicio;
		String periodoMayor = searchForm.getCodigoAnio() + periodoFin;

		criteria.put("periodoMenor", periodoMenor);
		criteria.put("periodoMayor", periodoMayor);
		criteria.put("periodoInicio", periodoInicio);
		criteria.put("periodoFin", periodoFin);
		return criteria;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		
		//Obtenemos el Form
		MantenimientoDATEstimadosSearchForm searchForm = (MantenimientoDATEstimadosSearchForm) this.formBusqueda;
		    	
		//Obtenemos el service
       	MantenimientoDATEstimadosService service = (MantenimientoDATEstimadosService) getBean("spusicc.mantenimientoDATEstimadosService");
		
       	
       	//Obtenemmos el periodo Inicio y Fin
       	String periodoInicio = service.getPeriodoInicio(searchForm.getCodigoRangoPeriodo());
		String periodoFin = service.getPeriodoFin(searchForm.getCodigoRangoPeriodo());
		String periodoMenor = searchForm.getCodigoAnio() + periodoInicio;
		String periodoMayor = searchForm.getCodigoAnio() + periodoFin;

		//Seteamos los periodos
		Map criteria = BeanUtils.describe(searchForm);
		criteria.put("periodoMenor", periodoMenor);
		criteria.put("periodoMayor", periodoMayor);
		criteria.put("periodoInicio", periodoInicio);
		criteria.put("periodoFin", periodoFin);
		
		
		//Obtenemos la lista de Estimados par los parametrso actuales
		List listaEstimados = service.getEstimadosByCriteria(criteria);
		//Cargamos los Datos Modificados
		ArrayList lista = searchForm.camposALista(listaEstimados);
		
		//Obtenemos el Usuario Actual
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		//Guardamos los Registros
		Estimado estimado;
		for (int i = 0; i < lista.size(); i++) {
			estimado = new Estimado();
			estimado = (Estimado) lista.get(i);
			//SI EL OBJETIVO DE PEDIDO HA SIDO GENERADO
			if (!estimado.getIndModiPedidosObjetivo().equals(Constants.INDICADOR_OBJ_PEDIDO_NOGENERADO)){
				if (service.getEstimadosById(estimado)!=null) service.updateEstimado(estimado, usuario);
				else service.insertEstimado(estimado, usuario);
			}
			
		}
				
//		listaEstimados = service.getEstimadosByCriteria(criteria);
		
//		ArrayList listaEstimadosAux = new ArrayList(listaEstimados);
//		searchForm.listaACampos(listaEstimadosAux);


		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub

		log.debug("Seting Attributes.");
		// this.salirGrabarPantallaPadre = true;
		MantenimientoDATEstimadosSearchForm f = (MantenimientoDATEstimadosSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.codigoPais = pais.getCodigo();
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ProcesoCOMCalculoCalificacionTramoService tramoService = (ProcesoCOMCalculoCalificacionTramoService) getBean("spusicc.procesoCOMCalculoCalificacionTramoService");

		this.siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma()
				.getCodigoISO());
		this.siccMarcaList = service.getMarcas();
		this.siccRegionList = aSvc.getRegionesByPaisMarcaCanal(codigoPais,
				Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT);
		String codRegion0 = getSiccRegionList()[0].getValue();
		this.siccZonaList = aSvc.getZonasByPaisCanalRegion(codigoPais,
				Constants.CODIGO_CANAL_DEFAULT, codRegion0);
		String codZona0 = getSiccZonaList()[0].getValue();
		this.siccRangoPeriodoList = service.getRangosPeriodo();
		f.setCodigoRegion(codRegion0);
		this.siccSeccionList = aSvc.getSeccionesByPaisMarcaCanalRegionZona(
				codigoPais, Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT, codRegion0, codZona0);
		setViewEdit(false);
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonModificar = false;
		this.viewConsultar=false;
		mostrarListaBusqueda = false;

	}

	/**
	 * Metodo para obtener Lista de Regiones por Marca
	 * 
	 * @param val
	 */
	public void loadRegionesByMarca(ValueChangeEvent val) {

		MantenimientoDATEstimadosSearchForm f = (MantenimientoDATEstimadosSearchForm) this.formBusqueda;
		String marca = (String) val.getNewValue();
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		setSiccRegionList(ajaxService.getRegionesByPaisMarcaCanal(codigoPais,
				marca, f.getCodigoCanal()));
		String codRegion0 = getSiccRegionList()[0].getValue();
		if (!StringUtils.isBlank(getSiccRegionList()[0].getValue())) {
			this.siccZonaList = ajaxService.getZonasByPaisCanalRegion(
					codigoPais, f.getCodigoCanal(), codRegion0);
			this.siccSeccionList = ajaxService
					.getSeccionesByPaisMarcaCanalRegionZona(codigoPais, marca,
							f.getCodigoCanal(), codRegion0,
							getSiccZonaList()[0].getValue());
		} else {
			setSiccZonaList(null);
			setSiccSeccionList(null);
		}
		f.setCodigoRegion(codRegion0);

	}

	/**
	 * Metodo para obtener Lista de Regiones por Marca
	 * 
	 * @param val
	 */
	public void loadRegionesByCanal(ValueChangeEvent val) {

		MantenimientoDATEstimadosSearchForm f = (MantenimientoDATEstimadosSearchForm) this.formBusqueda;
		String canal = (String) val.getNewValue();
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		setSiccRegionList(ajaxService.getRegionesByPaisMarcaCanal(codigoPais,
				f.getCodigoMarca(), canal));
		String codRegion0 = getSiccRegionList()[0].getValue();
		this.siccZonaList = ajaxService.getZonasByPaisCanalRegion(codigoPais,
				canal, codRegion0);
		this.siccSeccionList = ajaxService
				.getSeccionesByPaisMarcaCanalRegionZona(codigoPais,
						f.getCodigoMarca(), canal, codRegion0,
						getSiccZonaList()[0].getValue());
		if (StringUtils.isBlank(getSiccRegionList()[0].getValue())) {
			setSiccZonaList(null);
			setSiccSeccionList(null);
		}
		f.setCodigoRegion(codRegion0);

	}

	/**
	 * Metodo para obtener Lista de Zonas
	 * 
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		MantenimientoDATEstimadosSearchForm f = (MantenimientoDATEstimadosSearchForm) this.formBusqueda;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		String valor = (String) val.getNewValue();
		setSiccZonaList(ajaxService.getZonasByPaisCanalRegion(codigoPais,
				f.getCodigoCanal(), valor));

		this.siccSeccionList = ajaxService
				.getSeccionesByPaisMarcaCanalRegionZona(codigoPais,
						f.getCodigoMarca(), f.getCodigoCanal(), valor,
						getSiccZonaList()[0].getValue());

	}

	/**
	 * Metodo para obtener Lista de Seccion
	 * 
	 * @param val
	 */
	public void loadSeccion(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadSeccion");
		}
		MantenimientoDATEstimadosSearchForm f = (MantenimientoDATEstimadosSearchForm) this.formBusqueda;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		String zona = (String) val.getNewValue();

		this.siccSeccionList = ajaxService
				.getSeccionesByPaisMarcaCanalRegionZona(codigoPais,
						f.getCodigoMarca(), f.getCodigoCanal(),
						f.getCodigoRegion(), zona);

	}

	public void regresar(ActionEvent actionEvent){
		this.viewEdit=false;
		this.mostrarBotonBuscar=true;
		
	}
	
	public void modificar(ActionEvent actionEvent){
		this.viewEdit=true;
		this.mostrarBotonBuscar=false;

		
	}
	/**
	 * @return the viewEdit
	 */
	public boolean isViewEdit() {
		return viewEdit;
	}

	/**
	 * @param viewEdit
	 *            the viewEdit to set
	 */
	public void setViewEdit(boolean viewEdit) {
		this.viewEdit = viewEdit;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList
	 *            the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList
	 *            the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * @return the siccRegionList
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 *            the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the siccRangoPeriodoList
	 */
	public List getSiccRangoPeriodoList() {
		return siccRangoPeriodoList;
	}

	/**
	 * @param siccRangoPeriodoList
	 *            the siccRangoPeriodoList to set
	 */
	public void setSiccRangoPeriodoList(List siccRangoPeriodoList) {
		this.siccRangoPeriodoList = siccRangoPeriodoList;
	}

	/**
	 * @return the siccSeccionList
	 */
	public LabelValue[] getSiccSeccionList() {
		return siccSeccionList;
	}

	/**
	 * @param siccSeccionList
	 *            the siccSeccionList to set
	 */
	public void setSiccSeccionList(LabelValue[] siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
	}

	/**
	 * @return the listaEstimada
	 */
	public ArrayList getListaEstimada() {
		return listaEstimada;
	}

	/**
	 * @param listaEstimada
	 *            the listaEstimada to set
	 */
	public void setListaEstimada(ArrayList listaEstimada) {
		this.listaEstimada = listaEstimada;
	}

	/**
	 * @return the viewConsultar
	 */
	public boolean isViewConsultar() {
		return viewConsultar;
	}

	/**
	 * @param viewConsultar the viewConsultar to set
	 */
	public void setViewConsultar(boolean viewConsultar) {
		this.viewConsultar = viewConsultar;
	}
	
	

}
