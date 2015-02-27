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
import biz.belcorp.ssicc.dao.spusicc.comision.model.Bonos;
import biz.belcorp.ssicc.dao.spusicc.comision.model.DetalleBonos;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMBonosService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMBonosForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMBonosSearchForm;

@SessionScoped
@ManagedBean
public class MantenimientoCOMBonosSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6262283535533268593L;
	private List comBonosList;
	private List comDetalleBonosList;
	private List siccRegionList;
	private LabelValue[] siccZonaList = {};
	private LabelValue[] siccSeccionList = {};

	private DataTableModel comDetalleBonosTableModel;
	private Object beanRegistroDetalleBonos;

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoCOMBonosList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoCOMBonosForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoCOMBonosSearchForm form = new MantenimientoCOMBonosSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("inicio find");
		MantenimientoCOMBonosSearchForm f = (MantenimientoCOMBonosSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		MantenimientoCOMBonosService service = (MantenimientoCOMBonosService) getBean("spusicc.mantenimientoCOMBonosService");
		Map params = BeanUtils.describe(f);

		List listBonos = service.getBonosEjecutivas(params);
		this.comBonosList = listBonos;
		return listBonos;
	}

	@Override
	public String setValidarMantenimiento() {
		MantenimientoCOMBonosForm f = (MantenimientoCOMBonosForm) this.formMantenimiento;
		if (!StringUtils.isBlank(f.getCampanhaFinal())) {
			int codperini = Integer.parseInt(f.getCampanhaInicial());
			int codperfin = Integer.parseInt(f.getCampanhaFinal());
			if (codperfin < codperini) {
				String mensaje = this
						.getResourceMessage("mantenimientoCOMBonosForm.campanhas.no.valida");
				return mensaje;
			}
		}

		return null;

	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}
		MantenimientoCOMBonosForm f = (MantenimientoCOMBonosForm) this.formMantenimiento;
		MantenimientoCOMBonosService service = (MantenimientoCOMBonosService) getBean("spusicc.mantenimientoCOMBonosService");

		boolean bGrabarCab = false;
		try {

			Bonos bono = new Bonos();
			f.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry()
					.getCodigo());
			BeanUtils.copyProperties(bono, f);
			log.debug("Entidad : " + bono);
			if (!f.isNewRecord()) {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA MODIFICACION");
				}
				service.updateBonos(bono);
				updateDetalleBonos(service, bono);
				// cuando es update

				// messages.add(ActionMessages.GLOBAL_MESSAGE, new
				// ActionMessage(
				// "mantenimientoCOMBonos.cabecera.updated"));
				bGrabarCab = true;
			} else {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA CREACION");
				}

				String codigo = service.getSiguienteCodigoConcepto(bono);
				log.debug("codigo " + codigo);
				bono.setCodigoConcepto(codigo);
				service.insertBonos(bono);
				// se anhade las detalleBonoss
				addDetalleBonosToBonos(service, bono);
				bGrabarCab = true;
			}

		} catch (Exception e) {
			// e.printStackTrace();
			// bGrabarCab = false;
			// MessageResources messageResources = getResources(request);
			// cadError=messageResources.getMessage(
			// "mantenimientoCOMBonosForm.msg.codigoExiste");
			// messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
			// "mantenimientoCOMBonos.cabecera.error",cadError));
		}
		return bGrabarCab;
	}

	/**
	 * Inserta lso detalles del Bono
	 * 
	 * @param session
	 * @param service
	 * @param bono
	 */
	private void addDetalleBonosToBonos(MantenimientoCOMBonosService service,
			Bonos bono) {
		List listDetalleBonos = this.comDetalleBonosList;
		if (listDetalleBonos != null) {
			Iterator it = listDetalleBonos.iterator();
			while (it.hasNext()) {
				DetalleBonos detalle = (DetalleBonos) it.next();
				detalle.setCodigoConcepto(bono.getCodigoConcepto());
				service.insertDetalleBonos(detalle);
			}

		}
	}

	/**
	 * Inserta lso detalles del Bono
	 * 
	 * @param session
	 * @param service
	 * @param bono
	 */
	private void updateDetalleBonos(MantenimientoCOMBonosService service,
			Bonos bono) {
		List listDetalleBonos = this.comDetalleBonosList;
		if (listDetalleBonos != null) {
			Iterator it = listDetalleBonos.iterator();
			while (it.hasNext()) {
				DetalleBonos detalle = (DetalleBonos) it.next();
				detalle.setIndicadorActivo(Constants.NUMERO_UNO);
				service.updateDetalleBonos(detalle);
			}

		}
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoCOMBonosForm form = new MantenimientoCOMBonosForm();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteriaOperacion = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		this.siccRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteriaOperacion);
		if (this.accion.equals(this.ACCION_NUEVO)) {
			this.comDetalleBonosList = new ArrayList();
			this.comDetalleBonosTableModel = new DataTableModel();
			form.setNewRecord(true);
		} else if (this.accion.equals(this.ACCION_MODIFICAR)) {
			log.debug("edit");
			Bonos bono = (Bonos) this.beanRegistroSeleccionado;
			BeanUtils.copyProperties(form, bono);
			MantenimientoCOMBonosService service = (MantenimientoCOMBonosService) getBean("spusicc.mantenimientoCOMBonosService");

			// obtenemos las detalleBonoss
			DetalleBonos detalleBonos = getDetalleBonos(bono);
			List listDetalleBonos = service.getListDetalleBonos(detalleBonos);
			this.comDetalleBonosList = listDetalleBonos;
			this.comDetalleBonosTableModel = new DataTableModel(
					this.comDetalleBonosList);
			form.setViewEdit(false);
			form.setViewDetalleBono(true);
			form.setNewRecord(false);
		}

		return form;
	}

	/**
	 * Retorna una Instancia de DetalleBono
	 * 
	 * @param bono
	 * @return
	 */
	private DetalleBonos getDetalleBonos(Bonos bono) {
		DetalleBonos detalleBonos = new DetalleBonos();
		detalleBonos.setCodigoPais(bono.getCodigoPais());
		detalleBonos.setCodigoConcepto(bono.getCodigoConcepto());
		detalleBonos.setIndicadorActivo(Constants.NUMERO_UNO);
		return detalleBonos;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		this.mostrarBotonConsultar = false;
		this.salirGrabarPantallaPadre = true;
		this.mostrarBotonEliminar = false;
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
		String codRegion = (String) val.getNewValue();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		this.siccZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(
				pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT, new String[] { codRegion }, "");
		this.siccSeccionList = null;

	}

	/**
	 * Metodo para obtener Lista de Secciones
	 * 
	 * @param val
	 */
	public void loadSeccion(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadSeccion");
		}
		MantenimientoCOMBonosForm form = (MantenimientoCOMBonosForm) this.formMantenimiento;
		String codRegion = form.getCodigoRegion();
		String codZona = (String) val.getNewValue();
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		setSiccSeccionList(ajaxService
				.getSeccionMultipleByPaisMarcaCanalRegionZona(this
						.getmPantallaPrincipalBean().getCurrentCountry()
						.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT,
						new String[] { codRegion }, new String[] { codZona },
						""));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction
	 * #setValidarConfirmar(java.lang.String)
	 */
	public String setValidarConfirmar(String accion) {
		MantenimientoCOMBonosForm f = (MantenimientoCOMBonosForm) this.formMantenimiento;
		if (accion.equals("INSERTAR_DETALLE")) {
			// ############### VALIDAR CAMPOS REQUERIDOS #######################
			if (StringUtils.isBlank(f.getCodigoRegion()))
				return this
						.getResourceMessage("mantenimientoCOMBonosForm.codigoRegion.requerided");
			if (StringUtils.isBlank(f.getCodigoZona()))
				return this
						.getResourceMessage("mantenimientoCOMBonosForm.codigoZona.requerided");
			if (StringUtils.isBlank(f.getCodigoSeccion()))
				return this
						.getResourceMessage("mantenimientoCOMBonosForm.codigoSeccion.requerided");
			if (StringUtils.isBlank(f.getMonto()))
				return this
						.getResourceMessage("mantenimientoCOMBonosForm.monto.requerided");

			f.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry()
					.getCodigo());
			List listDetalleBonos = getListDetalleBonos();
			DetalleBonos detalleBonos = getDetalleBonos(f);
			if (existeDetalleBonos(detalleBonos, listDetalleBonos))
				return "Ya existe Bono en Región:"
						+ detalleBonos.getCodigoRegion() + " Zona:"
						+ detalleBonos.getCodigoZona() + " y Sección:"
						+ detalleBonos.getCodigoSeccion();

		} else if (accion.equals("ELIMINAR_DETALLE")) {
			if (comDetalleBonosList == null)
				return this
						.getResourceMessage("mantenimientoCOMBonosForm.error.notRegistros");
			if (beanRegistroDetalleBonos == null)
				return this.getResourceMessage("errors.select.item");
		}
		return null;
	}

	/**
	 * @param actionEvent
	 */
	public void addDetalleBonos(ActionEvent actionEvent) {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'addDetalleBonos' method");
		}
		MantenimientoCOMBonosForm f = (MantenimientoCOMBonosForm) this.formMantenimiento;
		MantenimientoCOMBonosService service = (MantenimientoCOMBonosService) getBean("spusicc.mantenimientoCOMBonosService");

		f.setViewDetalleBono(true);
		f.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());
		List listDetalleBonos = getListDetalleBonos();
		DetalleBonos detalleBonos = getDetalleBonos(f);
		listDetalleBonos.add(detalleBonos);
		if (StringUtils.isNotEmpty(detalleBonos.getCodigoConcepto()))// cuando
																		// es
																		// update
			service.insertDetalleBonos(detalleBonos);
		// limpiamos variables
		f.setCodigoRegion("");
		f.setCodigoZona("");
		f.setCodigoSeccion("");
		f.setMonto("");
		this.siccZonaList = null;
		this.siccSeccionList = null;
		this.comDetalleBonosList = listDetalleBonos;
		this.comDetalleBonosTableModel = new DataTableModel(
				this.comDetalleBonosList);

	}

	/**
	 * Elimina un detalle del bono
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void deleteDetalleBonos(ActionEvent actionEvent) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deleteDetalleBonos' method");
		}
		DetalleBonos detalleBonos = (DetalleBonos) this.beanRegistroDetalleBonos;
		MantenimientoCOMBonosForm f = (MantenimientoCOMBonosForm) this.formMantenimiento;
		MantenimientoCOMBonosService service = (MantenimientoCOMBonosService) getBean("spusicc.mantenimientoCOMBonosService");

		List listDetalleBonos = this.comDetalleBonosList;

		if (StringUtils.isNotEmpty(detalleBonos.getCodigoConcepto())) {
			detalleBonos.setIndicadorActivo(Constants.NUMERO_CERO);
			service.updateDetalleBonos(detalleBonos);
		}
		listDetalleBonos.remove(detalleBonos);
		f.setCodigoRegion("");
		f.setCodigoZona("");
		f.setCodigoSeccion("");
		f.setMonto("");
		this.siccZonaList = null;
		this.siccSeccionList = null;
		f.setViewDetalleBono(true);
	}

	private List getListDetalleBonos() {
		List list = comDetalleBonosList;
		if (list == null) {
			list = new ArrayList();
		}
		return list;

	}

	/**
	 * Retrona una instancia de detalle Bono
	 * 
	 * @param f
	 * @param session
	 * @return
	 */
	private DetalleBonos getDetalleBonos(MantenimientoCOMBonosForm f) {

		DetalleBonos detalleBonos = new DetalleBonos();
		detalleBonos.setCodigoPais(f.getCodigoPais());
		detalleBonos.setCodigoRegion(f.getCodigoRegion());
		detalleBonos.setCodigoZona(f.getCodigoZona());
		detalleBonos.setCodigoSeccion(f.getCodigoSeccion());
		detalleBonos.setMonto(f.getMonto());

		if (StringUtils.isNotEmpty(f.getCodigoConcepto())) {
			detalleBonos.setCodigoConcepto(f.getCodigoConcepto());
		}
		// seteando las descripciones de region y zona
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		List listRegiones = siccRegionList;
		detalleBonos.setDescripcionRegion(getDescripcionRegion(
				f.getCodigoRegion(), listRegiones));
		LabelValue[] listZonas = ajaxService
				.getZonasMultipleByPaisMarcaCanalRegion(
						this.mPantallaPrincipalBean.getCurrentCountry()
								.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT, new String[] { f
								.getCodigoRegion() }, "");
		detalleBonos.setDescripcionZona(getDescripcionZona(f.getCodigoZona(),
				listZonas));

		return detalleBonos;
	}

	/**
	 * Retorna la descripcion de la region
	 * 
	 * @param codigoRegion
	 * @param listRegiones
	 * @return
	 */
	private String getDescripcionRegion(String codigoRegion, List listRegiones) {
		log.debug("[getDescripcionRegion]codigo reg " + codigoRegion);
		Iterator it = listRegiones.iterator();
		while (it.hasNext()) {
			Base b = (Base) it.next();
			if (codigoRegion.equals(b.getCodigo()))
				return b.getDescripcion();
		}
		return "";
	}

	/**
	 * Retorna la descripcion de la zona
	 * 
	 * @param codigoZona
	 * @param listZonas
	 * @return
	 */
	private String getDescripcionZona(String codigoZona, LabelValue[] listZonas) {
		log.debug("[getDescripcionZona]codigo zona " + codigoZona);
		for (int i = 0; i < listZonas.length; i++) {
			LabelValue lv = listZonas[i];
			if (codigoZona.equals(lv.getValue()))
				return lv.getLabel();
		}
		return "";
	}

	/**
	 * Retorna true si el detalle del bono existe en la lista
	 * 
	 * @param detalleBonos
	 * @param listDetalleBonos
	 * @return
	 */
	private boolean existeDetalleBonos(DetalleBonos detalleBonos,
			List listDetalleBonos) {
		Iterator it = listDetalleBonos.iterator();
		String descripcion = detalleBonos.getCodigoRegion().trim()
				+ detalleBonos.getCodigoZona().trim()
				+ detalleBonos.getCodigoSeccion().trim();
		while (it.hasNext()) {
			DetalleBonos s = (DetalleBonos) it.next();
			String cadena = s.getCodigoRegion().trim()
					+ s.getCodigoZona().trim() + s.getCodigoSeccion().trim();
			if (descripcion.equals(cadena)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return the comBonosList
	 */
	public List getComBonosList() {
		return comBonosList;
	}

	/**
	 * @param comBonosList
	 *            the comBonosList to set
	 */
	public void setComBonosList(List comBonosList) {
		this.comBonosList = comBonosList;
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
	 * @return the comDetalleBonosList
	 */
	public List getComDetalleBonosList() {
		return comDetalleBonosList;
	}

	/**
	 * @param comDetalleBonosList
	 *            the comDetalleBonosList to set
	 */
	public void setComDetalleBonosList(List comDetalleBonosList) {
		this.comDetalleBonosList = comDetalleBonosList;
	}

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the comDetalleBonosTableModel
	 */
	public DataTableModel getComDetalleBonosTableModel() {
		return comDetalleBonosTableModel;
	}

	/**
	 * @param comDetalleBonosTableModel
	 *            the comDetalleBonosTableModel to set
	 */
	public void setComDetalleBonosTableModel(
			DataTableModel comDetalleBonosTableModel) {
		this.comDetalleBonosTableModel = comDetalleBonosTableModel;
	}

	/**
	 * @return the beanRegistroDetalleBonos
	 */
	public Object getBeanRegistroDetalleBonos() {
		return beanRegistroDetalleBonos;
	}

	/**
	 * @param beanRegistroDetalleBonos
	 *            the beanRegistroDetalleBonos to set
	 */
	public void setBeanRegistroDetalleBonos(Object beanRegistroDetalleBonos) {
		this.beanRegistroDetalleBonos = beanRegistroDetalleBonos;
	}

}
