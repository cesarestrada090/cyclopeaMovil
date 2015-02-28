package biz.belcorp.ssicc.web.spusicc.comision.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionCabecera;
import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionDetalle;
import biz.belcorp.ssicc.dao.spusicc.comision.model.Nivel;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMCalificacionComisionService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMPorcentajeComisionService;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCalculoCalificacionTramoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMCalificacionComisionForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMCalificacionComisionSearchForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMPorcentajeComisionForm;

@SessionScoped
@ManagedBean
public class MantenimientoCOMCalificacionComisionSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1714770607184699536L;
	private List comTipoComisionistaList;
	private List comCalificacionComicionList;
	private String codigoPais;
	private List siccMarcaList;
	private List siccCanalList;
	private List comNivelList;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private DataTableModel comDetalleTableModel;
	private Object beanRegistroDetalle;
	private boolean consultar;
	private boolean indicadorPasePedidoBoolean;
	private List comCalificacionComisionDetailList;

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoCOMCalificacionComisionList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoCOMCalificacionComisionForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoCOMCalificacionComisionSearchForm form = new MantenimientoCOMCalificacionComisionSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Seting find Attributes.");
		MantenimientoCOMCalificacionComisionSearchForm f = (MantenimientoCOMCalificacionComisionSearchForm) this.formBusqueda;
		f.setCodigoPais(codigoPais);
		CalificacionComisionCabecera calificacionComisionCabecera = new CalificacionComisionCabecera();
		BeanUtils.copyProperties(calificacionComisionCabecera, f);
		MantenimientoCOMCalificacionComisionService service = (MantenimientoCOMCalificacionComisionService) getBean("spusicc.mantenimientoCOMCalificacionComisionService");
		List calificacionComisionList = service
				.getCalificacionesComisionesList(calificacionComisionCabecera);
		log.debug("List size :  " + calificacionComisionList.size());
		this.comCalificacionComicionList = calificacionComisionList;
		return calificacionComisionList;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub

		CalificacionComisionCabecera calificacionComisionCabecera = (CalificacionComisionCabecera) this.beanRegistroSeleccionado;
		calificacionComisionCabecera.setCodigoPais(codigoPais);
		MantenimientoCOMCalificacionComisionService service = (MantenimientoCOMCalificacionComisionService) getBean("spusicc.mantenimientoCOMCalificacionComisionService");
		service.deleteCalificacionComisionCabeceraAndChild(calificacionComisionCabecera);
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}
		MantenimientoCOMCalificacionComisionForm f = (MantenimientoCOMCalificacionComisionForm) this.formMantenimiento;
		f.setCodigoPais(codigoPais);
		MantenimientoCOMCalificacionComisionService service = (MantenimientoCOMCalificacionComisionService) getBean("spusicc.mantenimientoCOMCalificacionComisionService");
		List detalList = this.comCalificacionComisionDetailList;

		boolean bGrabarCab = false;
		try {

			CalificacionComisionCabecera calificacionComisionCabecera = new CalificacionComisionCabecera();
			f.setIndicadorPasePedido(0);
			if (indicadorPasePedidoBoolean)
				f.setIndicadorPasePedido(1);

			BeanUtils.copyProperties(calificacionComisionCabecera, f);
			log.debug("Entidad : " + calificacionComisionCabecera);
			if (!f.isNewRecord()) {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA MODIFICACION");
				}
				service.updateCalificacionComisionCabeceraAndDetalle(
						calificacionComisionCabecera, detalList);
				updateDetal(calificacionComisionCabecera, detalList, service);
				bGrabarCab = true;
			} else {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA CREACION");
				}
				log.debug("Inserting...");
				service.insertCalificacionComisionCabeceraAndDetalle(
						calificacionComisionCabecera, detalList);
				bGrabarCab = true;
			}

		} catch (InvalidIdentifierException e) {
			// e.printStackTrace();
			bGrabarCab = false;
			throw new Exception(
					this.getResourceMessage("mantenimientoCOMPorcentajeComisionForm.msg.codigoExiste"));
		}
		return bGrabarCab;
	}

	public void updateDetal(
			CalificacionComisionCabecera calificacionComisionCabecera,
			List detalList, MantenimientoCOMCalificacionComisionService service) {
		//
		// List calificacionComisionDetalleList = (List) service
		// .getCalificacionComisionDetalleList(calificacionComisionCabecera);

		if (detalList != null)
			for (Iterator iterator = detalList.iterator(); iterator.hasNext();) {
				CalificacionComisionDetalle calificacionComisionDetalle = (CalificacionComisionDetalle) iterator
						.next();

				service.updateCalificacionComisionDetalle(calificacionComisionDetalle);

			}

	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoCOMCalificacionComisionForm form = new MantenimientoCOMCalificacionComisionForm();
		MantenimientoCOMCalificacionComisionService service = (MantenimientoCOMCalificacionComisionService) getBean("spusicc.mantenimientoCOMCalificacionComisionService");

		if (this.accion.equals(this.ACCION_NUEVO)) {
			this.comCalificacionComisionDetailList = new ArrayList();
			this.comDetalleTableModel = new DataTableModel();
			log.debug("add new");
			form.setCodigoCalificacion("");
			form.setDescripcion("");
			form.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
			form.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
			form.setCodigoTipoComisionista(Constants.CODIGO_TIPO_COMISIONISTA_DEFAULT);
			form.setNewRecord(true);
			setConsultar(false);
			form.setViewCodigoCalificacion(false);
			setIndicadorPasePedidoBoolean(true);
			initCabecera(form);
		} else if (this.accion.equals(this.ACCION_MODIFICAR)) {

			CalificacionComisionCabecera calificacionComisionCabecera = (CalificacionComisionCabecera) this.beanRegistroSeleccionado;
			setIndicadorPasePedidoBoolean(false);
			if (calificacionComisionCabecera.getIndicadorPasePedido() == 1)
				setIndicadorPasePedidoBoolean(true);
			BeanUtils.copyProperties(form, calificacionComisionCabecera);
			log.debug("Porcentaje Comision Cabecera "
					+ calificacionComisionCabecera.getCodigoPorcentaje());

			List calificacionjeComisionDetalleList = (List) service
					.getCalificacionComisionDetalleList(calificacionComisionCabecera);
			log.debug("detalList : " + calificacionjeComisionDetalleList.size());
			this.comCalificacionComisionDetailList = calificacionjeComisionDetalleList;
			this.comDetalleTableModel = new DataTableModel(
					this.comCalificacionComisionDetailList);
			initDetal(form);
			setConsultar(false);
			form.setNewRecord(false);
			this.mostrarBotonSave = true;

		} else if (this.accion.equals(this.ACCION_CONSULTAR)) {
			setConsultar(true);
			form.setNewRecord(false);
			this.mostrarBotonSave = false;
			CalificacionComisionCabecera calificacionComisionCabecera = (CalificacionComisionCabecera) this.beanRegistroSeleccionado;
			setIndicadorPasePedidoBoolean(false);
			if (calificacionComisionCabecera.getIndicadorPasePedido() == 1)
				setIndicadorPasePedidoBoolean(true);
			BeanUtils.copyProperties(form, calificacionComisionCabecera);

			List calificacionjeComisionDetalleList = (List) service
					.getCalificacionComisionDetalleList(calificacionComisionCabecera);
			this.comCalificacionComisionDetailList = calificacionjeComisionDetalleList;
			this.comDetalleTableModel = new DataTableModel(
					this.comCalificacionComisionDetailList);
			initDetal(form);

		}
		return form;
	}

	private void initCabecera(MantenimientoCOMCalificacionComisionForm f) {
		f.setCampaniaDesde("");
		f.setCampaniaHasta("");
		f.setComodin(new Integer(0));
		f.setComodinRecuperacion(new Integer(0));
		/* INI JJ PER-SiCC-2012-0530 */
		f.setComodinRecuperacionAspirantes(new Integer(0));
		/* FIN JJ PER-SiCC-2012-0530 */
		f.setViewCodigoNivel(true);
	}

	@Override
	public String setValidarMantenimiento() {
		MantenimientoCOMCalificacionComisionForm f = (MantenimientoCOMCalificacionComisionForm) this.formMantenimiento;
		if (!StringUtils.isBlank(f.getCampaniaHasta())) {
			int codperini = Integer.parseInt(f.getCampaniaDesde());
			int codperfin = Integer.parseInt(f.getCampaniaHasta());
			if (codperfin < codperini) {
				String mensaje = this
						.getResourceMessage("mantenimientoCOMBonosForm.campanhas.no.valida");
				return mensaje;
			}
		}

		return null;

	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Seting Attributes.");
		this.salirGrabarPantallaPadre = true;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.codigoPais = pais.getCodigo();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		MantenimientoCOMPorcentajeComisionService calificacionComisionService = (MantenimientoCOMPorcentajeComisionService) getBean("spusicc.mantenimientoCOMPorcentajeComisionService");
		ProcesoCOMCalculoCalificacionTramoService tramoService = (ProcesoCOMCalculoCalificacionTramoService) getBean("spusicc.procesoCOMCalculoCalificacionTramoService");
		this.comTipoComisionistaList = tramoService
				.getTiposComisionistas(codigoPais);
		this.siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma()
				.getCodigoISO());
		this.siccMarcaList = service.getMarcas();
		this.comNivelList = calificacionComisionService
				.getNivelList(codigoPais);

		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		this.siccRegionList = aSvc.getRegionesByPaisMarcaCanal(codigoPais,
				Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT);

	}

	/**
	 * Metodo para obtener Lista de Regiones por Marca
	 * 
	 * @param val
	 */
	public void loadRegionesByMarca(ValueChangeEvent val) {

		MantenimientoCOMCalificacionComisionForm f = (MantenimientoCOMCalificacionComisionForm) this.formMantenimiento;
		String marca = (String) val.getNewValue();
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		setSiccRegionList(ajaxService.getRegionesByPaisMarcaCanal(codigoPais,
				marca, f.getCodigoCanal()));
		if (StringUtils.isBlank(getSiccRegionList()[0].getValue())) {
			setSiccZonaList(null);
		}
		f.setCodigoRegion("");
	}

	/**
	 * Metodo para obtener Lista de Regiones por Marca
	 * 
	 * @param val
	 */
	public void loadRegionesByCanal(ValueChangeEvent val) {

		MantenimientoCOMCalificacionComisionForm f = (MantenimientoCOMCalificacionComisionForm) this.formMantenimiento;
		String canal = (String) val.getNewValue();
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		setSiccRegionList(ajaxService.getRegionesByPaisMarcaCanal(codigoPais,
				f.getCodigoMarca(), canal));
		if (StringUtils.isBlank(getSiccRegionList()[0].getValue())) {
			setSiccZonaList(null);
		}
		f.setCodigoRegion("");

	}

	/**
	 * @param actionEvent
	 */
	public void addDetal(ActionEvent actionEvent) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'addDetal' method");
		}
		Map criteria = new HashMap();
		Integer codigo;
		MantenimientoCOMCalificacionComisionForm f = (MantenimientoCOMCalificacionComisionForm) this.formMantenimiento;
		MantenimientoCOMCalificacionComisionService service = (MantenimientoCOMCalificacionComisionService) getBean("spusicc.mantenimientoCOMCalificacionComisionService");
		f.setDescripcionRegion(getDescripsionRegion(f.getCodigoRegion()));
		f.setCodigoPais(codigoPais);
		List detalList = getDetalList();
		log.debug("formulario  :  " + f);
		CalificacionComisionDetalle calificacionComisionDetalle = getCalificacionComisionDetalle(f);

		detalList.add(calificacionComisionDetalle);
		if (StringUtils.isNotEmpty(calificacionComisionDetalle
				.getCodigoCalificacion())) {
			service.insertCalificacionComisionDetalle(calificacionComisionDetalle);
		}
		initDetal(f);
		this.comCalificacionComisionDetailList = detalList;
		this.comDetalleTableModel = new DataTableModel(
				this.comCalificacionComisionDetailList);

	}

	public void deleteDetal(ActionEvent actionEvent) {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'deleteDetal' method");
		}
		CalificacionComisionDetalle detalle = (CalificacionComisionDetalle) this.beanRegistroDetalle;
		MantenimientoCOMCalificacionComisionForm f = (MantenimientoCOMCalificacionComisionForm) this.formMantenimiento;
		MantenimientoCOMCalificacionComisionService service = (MantenimientoCOMCalificacionComisionService) getBean("spusicc.mantenimientoCOMCalificacionComisionService");

		List listDetalle = this.comCalificacionComisionDetailList;

		if (StringUtils.isNotEmpty(detalle.getCodigoCalificacion())) {
			service.deleteCalificacionComisionDetalle(detalle);
		}
		listDetalle = eliminarDetallePorcentaje(detalle, listDetalle);
		this.comCalificacionComisionDetailList = listDetalle;
		this.comDetalleTableModel = new DataTableModel(
				comCalificacionComisionDetailList);
		initDetal(f);
	}

	private List eliminarDetallePorcentaje(CalificacionComisionDetalle detalle,
			List listDetalle) {
		ArrayList<CalificacionComisionDetalle> nombreArrayList = new ArrayList<CalificacionComisionDetalle>();
		Iterator it = listDetalle.iterator();
		String descripcion = detalle.getCodigoNivel().trim();
		while (it.hasNext()) {
			CalificacionComisionDetalle s = (CalificacionComisionDetalle) it
					.next();
			String cadena = s.getCodigoNivel().trim();
			if (!descripcion.equals(cadena)) {
				nombreArrayList.add(s);
			}
		}
		return nombreArrayList;
	}

	private void initDetal(MantenimientoCOMCalificacionComisionForm f) {
		f.setCodigoNivel(null);
		f.setNumeroIngresosDesde(null);
		f.setNumeroIngresosHasta(null);
		f.setNumeroPedidosDesde(null);
		f.setNumeroPedidosHasta(null);
		f.setCodigoRegion(null);
		this.siccZonaList = null;
	}

	private CalificacionComisionDetalle getCalificacionComisionDetalle(
			MantenimientoCOMCalificacionComisionForm f) {

		CalificacionComisionDetalle calificacionComisionDetalle = new CalificacionComisionDetalle();
		calificacionComisionDetalle.setCodigoPais(f.getCodigoPais());
		calificacionComisionDetalle.setCodigoCalificacion(f
				.getCodigoCalificacion());
		calificacionComisionDetalle.setCodigoCalificacion(f
				.getCodigoCalificacion());
		calificacionComisionDetalle.setCodigoNivel(f.getCodigoNivel());
		calificacionComisionDetalle
				.setDescripcionNivel(getDescripsionNivelToList(f
						.getCodigoNivel()));
		calificacionComisionDetalle.setNumeroPedidosDesde(f
				.getNumeroPedidosDesde());
		calificacionComisionDetalle.setNumeroPedidosHasta(f
				.getNumeroPedidosHasta());
		calificacionComisionDetalle.setNumeroIngresosDesde(f
				.getNumeroIngresosDesde());
		calificacionComisionDetalle.setNumeroIngresosHasta(f
				.getNumeroIngresosHasta());
		calificacionComisionDetalle.setCodigoRegion(f.getCodigoRegion());
		calificacionComisionDetalle.setCodigoZona(f.getCodigoZona());
		calificacionComisionDetalle.setDescripcionRegion(f
				.getDescripcionRegion());

		return calificacionComisionDetalle;
	}

	private String getDescripsionNivelToList(String codigoNivel) {
		List list = this.comNivelList;
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Nivel nivel = (Nivel) iterator.next();
			if (nivel.getCodigoNivel().equals(codigoNivel))
				return nivel.getDescripcion();
		}
		return null;
	}

	private String getDescripsionRegion(String codigoRegion) {
		String desRegion = "";
		for (int i = 0; i < siccRegionList.length; i++) {
			if (this.siccRegionList[i].getValue().equals(codigoRegion))
				desRegion = getSiccRegionList()[i].getLabel();
		}
		return desRegion;
	}

	/**
	 * @param session
	 * @return
	 */
	private List getDetalList() {
		List list = this.comCalificacionComisionDetailList;
		if (list == null) {
			list = new ArrayList();
		}
		return list;

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
		MantenimientoCOMCalificacionComisionForm f = (MantenimientoCOMCalificacionComisionForm) this.formMantenimiento;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		String valor = (String) val.getNewValue();
		setSiccZonaList(ajaxService.getZonasByPaisCanalRegion(codigoPais,
				f.getCodigoCanal(), valor));
		if (StringUtils.isBlank(valor))
			setSiccZonaList(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction
	 * #setValidarConfirmar(java.lang.String)
	 */
	public String setValidarConfirmar(String accion) {
		MantenimientoCOMCalificacionComisionForm f = (MantenimientoCOMCalificacionComisionForm) this.formMantenimiento;

		if (accion.equals("INSERTAR_DETALLE")) {
			// ############### VALIDAR CAMPOS REQUERIDOS #######################
			if (StringUtils.isBlank(f.getCodigoNivel()))
				return this
						.getResourceMessage("mantenimientoCOMCalificacionComisionForm.codigoNivel.requerided");
			if (f.getNumeroPedidosDesde() == null)
				return this
						.getResourceMessage("mantenimientoCOMCalificacionComisionForm.numeroPedidosDesde.requerided");
			if (f.getNumeroPedidosHasta() == null)
				return this
						.getResourceMessage("mantenimientoCOMCalificacionComisionForm.numeroPedidosHasta.requerided");
			if (f.getNumeroIngresosDesde() == null)
				return this
						.getResourceMessage("mantenimientoCOMCalificacionComisionForm.numeroIngresosDesde.requerided");
			if (f.getNumeroIngresosHasta() == null)
				return this
						.getResourceMessage("mantenimientoCOMCalificacionComisionForm.numeroIngresosHasta.requerided");
			List listDetalle = getDetalList();
			CalificacionComisionDetalle detalle = getCalificacionComisionDetalle(f);
			if (existeDetalle(detalle, listDetalle))
				return "Ya existe una Nivel con descripcion "
						+ getDescripsionNivelToList(f.getCodigoNivel());
		} else if (accion.equals("ELIMINAR_DETALLE")) {
			if (comCalificacionComisionDetailList == null)
				return this
						.getResourceMessage("mantenimientoCOMCalificacionComisionForm.error.notRegistros");
			if (beanRegistroDetalle == null)
				return this.getResourceMessage("errors.select.item");
		}
		return null;
	}

	private boolean existeDetalle(CalificacionComisionDetalle detalle,
			List listDetalle) {
		Iterator it = listDetalle.iterator();
		String descripcion = detalle.getCodigoNivel().trim();
		String region = detalle.getCodigoRegion().trim();
		String zona = detalle.getCodigoZona().trim();
		while (it.hasNext()) {
			CalificacionComisionDetalle s = (CalificacionComisionDetalle) it
					.next();
			String cadena = s.getCodigoNivel().trim();
			String codregion = s.getCodigoRegion().trim();
			String codzona = s.getCodigoZona().trim();
			if (descripcion.equals(cadena) && region.equals(codregion)
					&& zona.equals(codzona)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return the comTipoComisionistaList
	 */
	public List getComTipoComisionistaList() {
		return comTipoComisionistaList;
	}

	/**
	 * @param comTipoComisionistaList
	 *            the comTipoComisionistaList to set
	 */
	public void setComTipoComisionistaList(List comTipoComisionistaList) {
		this.comTipoComisionistaList = comTipoComisionistaList;
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
	 * @return the comCalificacionComicionList
	 */
	public List getComCalificacionComicionList() {
		return comCalificacionComicionList;
	}

	/**
	 * @param comCalificacionComicionList
	 *            the comCalificacionComicionList to set
	 */
	public void setComCalificacionComicionList(List comCalificacionComicionList) {
		this.comCalificacionComicionList = comCalificacionComicionList;
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
	 * @return the comNivelList
	 */
	public List getComNivelList() {
		return comNivelList;
	}

	/**
	 * @param comNivelList
	 *            the comNivelList to set
	 */
	public void setComNivelList(List comNivelList) {
		this.comNivelList = comNivelList;
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
	 * @return the comDetalleTableModel
	 */
	public DataTableModel getComDetalleTableModel() {
		return comDetalleTableModel;
	}

	/**
	 * @param comDetalleTableModel
	 *            the comDetalleTableModel to set
	 */
	public void setComDetalleTableModel(DataTableModel comDetalleTableModel) {
		this.comDetalleTableModel = comDetalleTableModel;
	}

	/**
	 * @return the beanRegistroDetalle
	 */
	public Object getBeanRegistroDetalle() {
		return beanRegistroDetalle;
	}

	/**
	 * @param beanRegistroDetalle
	 *            the beanRegistroDetalle to set
	 */
	public void setBeanRegistroDetalle(Object beanRegistroDetalle) {
		this.beanRegistroDetalle = beanRegistroDetalle;
	}

	/**
	 * @return the consultar
	 */
	public boolean isConsultar() {
		return consultar;
	}

	/**
	 * @param consultar
	 *            the consultar to set
	 */
	public void setConsultar(boolean consultar) {
		this.consultar = consultar;
	}

	/**
	 * @return the indicadorPasePedidoBoolean
	 */
	public boolean isIndicadorPasePedidoBoolean() {
		return indicadorPasePedidoBoolean;
	}

	/**
	 * @param indicadorPasePedidoBoolean
	 *            the indicadorPasePedidoBoolean to set
	 */
	public void setIndicadorPasePedidoBoolean(boolean indicadorPasePedidoBoolean) {
		this.indicadorPasePedidoBoolean = indicadorPasePedidoBoolean;
	}

	/**
	 * @return the comCalificacionComisionDetailList
	 */
	public List getComCalificacionComisionDetailList() {
		return comCalificacionComisionDetailList;
	}

	/**
	 * @param comCalificacionComisionDetailList
	 *            the comCalificacionComisionDetailList to set
	 */
	public void setComCalificacionComisionDetailList(
			List comCalificacionComisionDetailList) {
		this.comCalificacionComisionDetailList = comCalificacionComisionDetailList;
	}

}
