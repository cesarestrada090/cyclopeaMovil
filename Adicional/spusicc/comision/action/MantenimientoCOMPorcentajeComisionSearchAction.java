package biz.belcorp.ssicc.web.spusicc.comision.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionCabecera;
import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionDetalle;
import biz.belcorp.ssicc.dao.spusicc.comision.model.Nivel;
import biz.belcorp.ssicc.dao.spusicc.pej.model.PorcentajeComisionesCabecera;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMPorcentajeComisionService;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCalculoCalificacionTramoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMPorcentajeComisionForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMPorcentajeComisionSearchForm;

@SessionScoped
@ManagedBean
public class MantenimientoCOMPorcentajeComisionSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4426674099402747496L;
	private List comPorcentajeComisionList;
	private List comPorcentajeComisionDetalList;
	private List comTipoComisionistaList;
	private List siccMarcaList;
	private List siccCanalList;
	private List comNivelList;
	private DataTableModel comDetalleTableModel;
	private Object beanRegistroDetalle;
	private boolean consultar;

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoCOMPorcentajeComisionList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoCOMPorcentajeComisionForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoCOMPorcentajeComisionSearchForm form = new MantenimientoCOMPorcentajeComisionSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Seting find Attributes.");
		MantenimientoCOMPorcentajeComisionSearchForm f = (MantenimientoCOMPorcentajeComisionSearchForm) this.formBusqueda;
		f.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());
		CalificacionComisionCabecera calificacionComisionCabecera = new CalificacionComisionCabecera();
		BeanUtils.copyProperties(calificacionComisionCabecera, f);
		MantenimientoCOMPorcentajeComisionService service = (MantenimientoCOMPorcentajeComisionService) getBean("spusicc.mantenimientoCOMPorcentajeComisionService");
		List porcentajeComisionList = service
				.getPorcentajesComisionesList(calificacionComisionCabecera);
		log.debug("List size :  " + porcentajeComisionList.size());
		this.comPorcentajeComisionList = porcentajeComisionList;
		return porcentajeComisionList;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {

		MantenimientoCOMPorcentajeComisionService service = (MantenimientoCOMPorcentajeComisionService) getBean("spusicc.mantenimientoCOMPorcentajeComisionService");
		CalificacionComisionCabecera calificacionComisionCabecera = (CalificacionComisionCabecera) this.beanRegistroSeleccionado;

		calificacionComisionCabecera.setCodigoPais(this.mPantallaPrincipalBean
				.getCurrentCountry().getCodigo());
		service.deletePorcentajeComisionCabeceraAndChild(calificacionComisionCabecera);

		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}
		MantenimientoCOMPorcentajeComisionForm f = (MantenimientoCOMPorcentajeComisionForm) this.formMantenimiento;
		f.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());
		MantenimientoCOMPorcentajeComisionService service = (MantenimientoCOMPorcentajeComisionService) getBean("spusicc.mantenimientoCOMPorcentajeComisionService");
		List detalList = this.comPorcentajeComisionDetalList;

		boolean bGrabarCab = false;
		try {

			CalificacionComisionCabecera calificacionComisionCabecera = new CalificacionComisionCabecera();
			BeanUtils.copyProperties(calificacionComisionCabecera, f);
			log.debug("Entidad : " + calificacionComisionCabecera);
			if (!f.isNewRecord()) {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA MODIFICACION");
				}
				service.updatePorcentajeComisionCabeceraAndDetalle(
						calificacionComisionCabecera, detalList);
				updateDetal(calificacionComisionCabecera, detalList, service);
				bGrabarCab = true;
			} else {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA CREACION");
				}
				log.debug("Inserting...");
				service.insertPorcentajeComisionCabeceraAndDetalle(
						calificacionComisionCabecera, detalList);
				bGrabarCab = true;
			}

		} catch (InvalidIdentifierException e) {
			// e.printStackTrace();
			 bGrabarCab = false;
			 throw new Exception(this.getResourceMessage("mantenimientoCOMPorcentajeComisionForm.msg.codigoExiste"));
		}
		return bGrabarCab;
	}

	public void updateDetal(
			CalificacionComisionCabecera calificacionComisionCabecera,
			List detalList, MantenimientoCOMPorcentajeComisionService service) {

		if (detalList != null)
			for (Iterator iterator = detalList.iterator(); iterator.hasNext();) {
				CalificacionComisionDetalle calificacionComisionDetalle = (CalificacionComisionDetalle) iterator
						.next();

				service.updatePorcentajeComisionDetalle(calificacionComisionDetalle);

			}

	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoCOMPorcentajeComisionForm form = new MantenimientoCOMPorcentajeComisionForm();
		MantenimientoCOMPorcentajeComisionService service = (MantenimientoCOMPorcentajeComisionService) getBean("spusicc.mantenimientoCOMPorcentajeComisionService");

		form.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());
		if (this.accion.equals(this.ACCION_NUEVO)) {
			this.comPorcentajeComisionDetalList = new ArrayList();
			this.comDetalleTableModel = new DataTableModel();
			this.mostrarBotonSave = true;
			form.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
			form.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
			form.setNewRecord(true);
			setConsultar(false);
		} else if (this.accion.equals(this.ACCION_MODIFICAR)) {
			CalificacionComisionCabecera calificacionComisionCabecera = (CalificacionComisionCabecera) this.beanRegistroSeleccionado;
			BeanUtils.copyProperties(form, calificacionComisionCabecera);
			log.debug("Porcentaje Comision Cabecera "
					+ calificacionComisionCabecera.getCodigoPorcentaje());

			List porcentajeComisionDetalleList = (List) service
					.getPorcentajeComisionDetalleList(calificacionComisionCabecera);
			log.debug("detalList : " + porcentajeComisionDetalleList.size());
			this.comPorcentajeComisionDetalList = porcentajeComisionDetalleList;
			this.comDetalleTableModel = new DataTableModel(
					this.comPorcentajeComisionDetalList);
			initDetal(form);
			setConsultar(false);
			form.setNewRecord(false);
			this.mostrarBotonSave = true;
		} else if (this.accion.equals(this.ACCION_CONSULTAR)) {
			setConsultar(true);
			form.setNewRecord(false);
			this.mostrarBotonSave = false;
			CalificacionComisionCabecera calificacionComisionCabecera = (CalificacionComisionCabecera) this.beanRegistroSeleccionado;
			BeanUtils.copyProperties(form, calificacionComisionCabecera);

			List porcentajeComisionDetalleList = (List) service
					.getPorcentajeComisionDetalleList(calificacionComisionCabecera);
			this.comPorcentajeComisionDetalList = porcentajeComisionDetalleList;
			this.comDetalleTableModel = new DataTableModel(
					this.comPorcentajeComisionDetalList);
			initDetal(form);
		}
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Seting Attributes.");
		this.salirGrabarPantallaPadre = true;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		MantenimientoCOMPorcentajeComisionService calificacionComisionService = (MantenimientoCOMPorcentajeComisionService) getBean("spusicc.mantenimientoCOMPorcentajeComisionService");
		ProcesoCOMCalculoCalificacionTramoService tramoService = (ProcesoCOMCalculoCalificacionTramoService) getBean("spusicc.procesoCOMCalculoCalificacionTramoService");
		this.comTipoComisionistaList = tramoService.getTiposComisionistas(pais
				.getCodigo());
		this.siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma()
				.getCodigoISO());
		this.siccMarcaList = service.getMarcas();
		this.comNivelList = calificacionComisionService.getNivelList(pais
				.getCodigo());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction
	 * #setValidarConfirmar(java.lang.String)
	 */
	public String setValidarConfirmar(String accion) {
		MantenimientoCOMPorcentajeComisionForm f = (MantenimientoCOMPorcentajeComisionForm) this.formMantenimiento;
		if (accion.equals("INSERTAR_DETALLE")) {
			// ############### VALIDAR CAMPOS REQUERIDOS #######################
			if (StringUtils.isBlank(f.getCodigoNivel()))
				return this
						.getResourceMessage("mantenimientoCOMPorcentajeComisionForm.codigoNivel.requerided");
			if (StringUtils.isBlank(f.getMontoDesde()))
				return this
						.getResourceMessage("mantenimientoCOMPorcentajeComisionForm.montoDesde.requerided");
			if (StringUtils.isBlank(f.getMontoHasta()))
				return this
						.getResourceMessage("mantenimientoCOMPorcentajeComisionForm.montoHasta.requerided");
			if (StringUtils.isBlank(f.getValorPorc()))
				return this
						.getResourceMessage("mantenimientoCOMPorcentajeComisionForm.valorPorc.requerided");
			List listDetalle = getListDetallePorcentaje();
			CalificacionComisionDetalle detalle = getPorcentajeComisionDetalle(f);
			if (existeDetallePorcentaje(detalle, listDetalle))
				return "Ya existe un Código Nivel";
		} else if (accion.equals("ELIMINAR_DETALLE")) {
			if (comPorcentajeComisionDetalList == null)
				return this
						.getResourceMessage("mantenimientoCOMPorcentajeComisionForm.error.notRegistros");
			if (beanRegistroDetalle == null)
				return this.getResourceMessage("errors.select.item");
		}
		return null;
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
		MantenimientoCOMPorcentajeComisionForm f = (MantenimientoCOMPorcentajeComisionForm) this.formMantenimiento;
		MantenimientoCOMPorcentajeComisionService service = (MantenimientoCOMPorcentajeComisionService) getBean("spusicc.mantenimientoCOMPorcentajeComisionService");

		f.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());
		f.setNumeroItem(1);
		List detalList = getDetalList();
		log.debug("formulario  :  " + f);
		CalificacionComisionDetalle calificacionComisionDetalle = getPorcentajeComisionDetalle(f);
		f.setNumeroItem(calificacionComisionDetalle.getNumeroItem());

		detalList.add(calificacionComisionDetalle);
		if (StringUtils.isNotEmpty(calificacionComisionDetalle
				.getCodigoPorcentaje())) {
			log.debug("xxxxxxxxxxxxxxxxxxxxxxx :" + calificacionComisionDetalle);
			criteria.put("codigoPais", this.mPantallaPrincipalBean
					.getCurrentCountry().getCodigo());
			criteria.put("codigoPorcentaje", f.getCodigoPorcentaje());
			criteria.put("codigoNivel", f.getCodigoNivel());
			codigo = service.getNumeroItem(criteria);
			service.insertPorcentajeComisionDetalle(
					calificacionComisionDetalle, codigo);
		}
		initDetal(f);
		this.comPorcentajeComisionDetalList = detalList;
		this.comDetalleTableModel = new DataTableModel(
				this.comPorcentajeComisionDetalList);

	}

	@Override
	public String setValidarMantenimiento() {
		MantenimientoCOMPorcentajeComisionForm f = (MantenimientoCOMPorcentajeComisionForm) this.formMantenimiento;
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

	public void deleteDetal(ActionEvent actionEvent) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deleteDetal' meth5od");
		}
		CalificacionComisionDetalle detalle = (CalificacionComisionDetalle) this.beanRegistroDetalle;
		MantenimientoCOMPorcentajeComisionForm f = (MantenimientoCOMPorcentajeComisionForm) this.formMantenimiento;
		MantenimientoCOMPorcentajeComisionService service = (MantenimientoCOMPorcentajeComisionService) getBean("spusicc.mantenimientoCOMPorcentajeComisionService");

		List detalList = this.comPorcentajeComisionDetalList;

		if (StringUtils.isNotEmpty(detalle.getCodigoPorcentaje())) {
			service.deletePorcentajeComisionDetalle(detalle);
		}
		detalList = eliminarDetallePorcentaje(detalle, detalList);
		this.comPorcentajeComisionDetalList = detalList;
		this.comDetalleTableModel = new DataTableModel(
				this.comPorcentajeComisionDetalList);
		f.setViewCodigoNivel(true);
	}

	/**
	 * Inicializa los datos del detalle
	 * 
	 * @param f
	 */
	private void initDetal(MantenimientoCOMPorcentajeComisionForm f) {
		f.setCodigoNivel("");
		f.setMontoDesde("");
		f.setMontoHasta("");
		f.setValorPorc("");
		f.setViewCodigoNivel(true);
		f.setNumeroItem(1);
	}

	/**
	 * retorna la lista de detalle del porcentaje de comisión
	 * 
	 * @param session
	 * @return
	 */
	private List getDetalList() {
		List list = this.comPorcentajeComisionDetalList;
		if (list == null) {
			list = new ArrayList();
		}
		return list;

	}

	/**
	 * Retorna una instancia de detalle de porcentajes de comisiones
	 * 
	 * @param f
	 * @param session
	 * @return
	 */
	private CalificacionComisionDetalle getPorcentajeComisionDetalle(
			MantenimientoCOMPorcentajeComisionForm f) {

		CalificacionComisionDetalle calificacionComisionDetalle = new CalificacionComisionDetalle();
		calificacionComisionDetalle.setCodigoPais(f.getCodigoPais());
		calificacionComisionDetalle
				.setCodigoPorcentaje(f.getCodigoPorcentaje());
		calificacionComisionDetalle.setCodigoNivel(f.getCodigoNivel());
		calificacionComisionDetalle
				.setDescripcionNivel(getDescripcionNivelToList(f
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
		calificacionComisionDetalle.setNumeroItem(f.getNumeroItem());
		calificacionComisionDetalle.setMontoDesde(f.getMontoDesde());
		calificacionComisionDetalle.setMontoHasta(f.getMontoHasta());
		calificacionComisionDetalle.setValorPorc(f.getValorPorc());

		return calificacionComisionDetalle;
	}

	/**
	 * Retorna la descripcion del nivel
	 * 
	 * @param codigoNivel
	 * @param session
	 * @return
	 */
	private String getDescripcionNivelToList(String codigoNivel) {
		List list = this.comNivelList;
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Nivel nivel = (Nivel) iterator.next();
			if (nivel.getCodigoNivel().equals(codigoNivel))
				return nivel.getDescripcion();
		}
		return null;
	}

	private boolean existeDetallePorcentaje(
			CalificacionComisionDetalle detalle, List listDetalle) {
		Iterator it = listDetalle.iterator();
		String descripcion = detalle.getCodigoNivel().trim();
		while (it.hasNext()) {
			CalificacionComisionDetalle s = (CalificacionComisionDetalle) it
					.next();
			String cadena = s.getCodigoNivel().trim();
			if (descripcion.equals(cadena)) {
				return true;
			}
		}
		return false;
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

	private List getListDetallePorcentaje() {
		List list = comPorcentajeComisionDetalList;
		if (list == null) {
			list = new ArrayList();
		}
		return list;

	}

	/**
	 * @return the comPorcentajeComisionList
	 */
	public List getComPorcentajeComisionList() {
		return comPorcentajeComisionList;
	}

	/**
	 * @param comPorcentajeComisionList
	 *            the comPorcentajeComisionList to set
	 */
	public void setComPorcentajeComisionList(List comPorcentajeComisionList) {
		this.comPorcentajeComisionList = comPorcentajeComisionList;
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
	 * @return the beanRegistroDetalleBonos
	 */
	public Object getBeanRegistroDetalle() {
		return beanRegistroDetalle;
	}

	/**
	 * @param beanRegistroDetalleBonos
	 *            the beanRegistroDetalleBonos to set
	 */
	public void setBeanRegistroDetalle(Object beanRegistroDetalle) {
		this.beanRegistroDetalle = beanRegistroDetalle;
	}

	/**
	 * @return the comPorcentajeComisionDetalList
	 */
	public List getComPorcentajeComisionDetalList() {
		return comPorcentajeComisionDetalList;
	}

	/**
	 * @param comPorcentajeComisionDetalList
	 *            the comPorcentajeComisionDetalList to set
	 */
	public void setComPorcentajeComisionDetalList(
			List comPorcentajeComisionDetalList) {
		this.comPorcentajeComisionDetalList = comPorcentajeComisionDetalList;
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

}
