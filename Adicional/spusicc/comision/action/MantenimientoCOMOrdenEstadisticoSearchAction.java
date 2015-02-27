package biz.belcorp.ssicc.web.spusicc.comision.action;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.comision.model.OrdenEstadisticoPorZona;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMCalificacionComisionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMOrdenEstadisticoForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMOrdenEstadisticoSearchForm;

@SessionScoped
@ManagedBean
public class MantenimientoCOMOrdenEstadisticoSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2198452989178527862L;
	private List comOrdenEstadisticoZonaList;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoCOMOrdenEstadisticoList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoCOMOrdenEstadisticoForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoCOMOrdenEstadisticoSearchForm form = new MantenimientoCOMOrdenEstadisticoSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Seting find Attributes");

		MantenimientoCOMOrdenEstadisticoSearchForm f = (MantenimientoCOMOrdenEstadisticoSearchForm) this.formBusqueda;
		OrdenEstadisticoPorZona ordenEstadisticoPorZona = new OrdenEstadisticoPorZona();
		BeanUtils.copyProperties(ordenEstadisticoPorZona, f);

		MantenimientoCOMCalificacionComisionService service = (MantenimientoCOMCalificacionComisionService) getBean("spusicc.mantenimientoCOMCalificacionComisionService");
		List estadisticoZonas = service
				.getOrdenEstadisticoPorZonaList(ordenEstadisticoPorZona);
		this.comOrdenEstadisticoZonaList = estadisticoZonas;
		return estadisticoZonas;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Seting delete Attributes");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		OrdenEstadisticoPorZona ordenbusqueda = (OrdenEstadisticoPorZona) this.beanRegistroSeleccionado;
		MantenimientoCOMCalificacionComisionService service = (MantenimientoCOMCalificacionComisionService) getBean("spusicc.mantenimientoCOMCalificacionComisionService");
		ordenbusqueda.setEstatusRegistro(Constants.ESTADO_INACTIVO);
		service.deleteOrdenEstadisticoPorZona(ordenbusqueda, usuario);
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}
		MantenimientoCOMOrdenEstadisticoForm f = (MantenimientoCOMOrdenEstadisticoForm) this.formMantenimiento;
		MantenimientoCOMCalificacionComisionService service = (MantenimientoCOMCalificacionComisionService) getBean("spusicc.mantenimientoCOMCalificacionComisionService");
		boolean bGrabarCab = false;

		try {
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			OrdenEstadisticoPorZona ordenEstadisticoPorZona = new OrdenEstadisticoPorZona();
			BeanUtils.copyProperties(ordenEstadisticoPorZona, f);
			if (!f.isNewRecord()) {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA MODIFICACION");
				}
				service.updateOrdenEstadisticoPorZona(ordenEstadisticoPorZona,usuario);
				bGrabarCab = true;
			} else {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA CREACION");
				}
				log.debug("Inserting...");
				service.insertOrdenEstadisticoPorZona(ordenEstadisticoPorZona,
						usuario);
				bGrabarCab = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			bGrabarCab = false;
			throw new Exception(
					this.getResourceMessage("mantenimientoCOMOrdenEstadistico.msg.codigoExiste"));

		}

		return bGrabarCab;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		OrdenEstadisticoPorZona ordenbusqueda = (OrdenEstadisticoPorZona) this.beanRegistroSeleccionado;
		MantenimientoCOMOrdenEstadisticoForm form = new MantenimientoCOMOrdenEstadisticoForm();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		if (this.accion.equals(this.ACCION_NUEVO)) {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.siccRegionList = aSvc.getRegionesByPaisMarcaCanal(
					pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
					Constants.CODIGO_CANAL_DEFAULT);
			String valor0 = getSiccRegionList()[0].getValue();
			this.siccZonaList = aSvc.getZonasByPaisActivasNoActivas(
					pais.getCodigo(), valor0);

		} else if (this.accion.equals(this.ACCION_MODIFICAR)) {
			form.setCodigoPais(pais.getCodigo());
			form.setCodigoRegion(ordenbusqueda.getCodigoRegion());
			form.setCodigoZona(ordenbusqueda.getCodigoZona());
			form.setOrdenEstadistico(ordenbusqueda.getOrdenEstadistico());
			form.setNewRecord(false);
		}

		return form;

	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoCOMOrdenEstadisticoSearchForm form = (MantenimientoCOMOrdenEstadisticoSearchForm) this.formBusqueda;
		form.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());

		this.mostrarBotonConsultar = false;

	}


	@Override
	protected String devuelveMensajeKeySaveOK() {

		MantenimientoCOMOrdenEstadisticoForm form = (MantenimientoCOMOrdenEstadisticoForm) this.formMantenimiento;
		boolean isNew = form.isNewRecord();
		if (isNew) {
			return "mantenimientoCOMOrdenEstadistico.added";
		} else {
			return "mantenimientoCOMOrdenEstadistico.updated";
		}
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
		String valor = (String) val.getNewValue();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		this.setSiccZonaList(ajaxService.getZonasByPaisActivasNoActivas(
				pais.getCodigo(), valor));

	}

	/**
	 * @return the comOrdenEstadisticoZonaList
	 */
	public List getComOrdenEstadisticoZonaList() {
		return comOrdenEstadisticoZonaList;
	}

	/**
	 * @param comOrdenEstadisticoZonaList
	 *            the comOrdenEstadisticoZonaList to set
	 */
	public void setComOrdenEstadisticoZonaList(List comOrdenEstadisticoZonaList) {
		this.comOrdenEstadisticoZonaList = comOrdenEstadisticoZonaList;
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

}
