package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ConsultaOCRRecepcionPedidosService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ConsultaOCRRecepcionPedidosForm;

@SessionScoped
@ManagedBean
public class ConsultaOCRRecepcionPedidosAction extends
		BaseConsultaAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8271679937013922665L;
	private List siccRegionList;
	private LabelValue[] siccZonaList = {};
	private LabelValue[] stoOrigenesList = {};
	private List ocrRecepcionPedidosList;

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		ConsultaOCRRecepcionPedidosForm form = new ConsultaOCRRecepcionPedidosForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}
		// Removemos atributos session
		ConsultaOCRRecepcionPedidosService service = (ConsultaOCRRecepcionPedidosService) getBean("spusicc.pedidos.consultaOCRRecepcionPedidosService");
		ConsultaOCRRecepcionPedidosForm f = (ConsultaOCRRecepcionPedidosForm) this.formBusqueda;
		f.setSelectedItems(new String[] {});
		String indBloqueo = new String();
		if (f.getIndicadorBloqueo() == null)
			indBloqueo = null;

		else {
			if (f.getIndicadorBloqueo().compareToIgnoreCase("SI") == 0)
				indBloqueo = "1";
			else if (f.getIndicadorBloqueo().compareToIgnoreCase("NO") == 0)
				indBloqueo = "0";
			else
				indBloqueo = f.getIndicadorBloqueo();
		}

		String indFacturacion = new String();
		if (f.getIndicadorFacturacion() == null)
			indFacturacion = null;

		else {
			if (f.getIndicadorFacturacion().compareToIgnoreCase("SI") == 0)
				indFacturacion = "1";
			else if (f.getIndicadorFacturacion().compareToIgnoreCase("NO") == 0)
				indFacturacion = "0";
			else
				indFacturacion = f.getIndicadorFacturacion();
		}

		String indRechazo = new String();
		if (f.getIndicadorRechazo() == null)
			indRechazo = null;

		else {
			if (f.getIndicadorRechazo().compareToIgnoreCase("SI") == 0)
				indRechazo = "1";
			else if (f.getIndicadorRechazo().compareToIgnoreCase("NO") == 0)
				indRechazo = "0";
			else
				indRechazo = f.getIndicadorRechazo();
		}
		if (f.getRegionList() != null) {
			if (f.getRegionList().length == 1) {
				if (f.getRegionList()[0].compareToIgnoreCase("") == 0) {
					f.setRegionList(null);
				}
			}
		}
		if (f.getZonaList() != null) {

			if (f.getZonaList().length == 1) {
				if (f.getZonaList()[0].compareToIgnoreCase("") == 0) {
					f.setZonaList(null);
				}
			}
		}

		// Obtenemos las propiedades del bean como un 'Map'
		Map criteria = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("tipoDocumento", Constants.STO_TIPO_DOCUMENTO_OCC);
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("indBloqueo", indBloqueo);
		criteria.put("indFacturacion", indFacturacion);
		criteria.put("indRechazo", indRechazo);
		criteria.put("codigoOrigen", f.getCodigoOrigen());
		criteria.put(
				"regionList",
				(f.getRegionList() == null) ? new ArrayList() : Arrays.asList(f
						.getRegionList()));
		criteria.put("zonaList", (f.getZonaList() == null) ? new ArrayList()
				: Arrays.asList(f.getZonaList()));
		criteria.put("tipoConsulta", f.getTipoConsulta());

		List lista = new ArrayList();
		if (f.getTipoConsulta().compareToIgnoreCase("0") == 0)
			lista = service.getListaRecepcionPedidosHistorico(criteria);
		else
			lista = service.getListaRecepcionPedidosActual(criteria);

		this.ocrRecepcionPedidosList = lista;

		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		List l = new ArrayList();

		LabelValue[] lv = aSvc.getZonasMultipleByPaisMarcaCanalRegion(
				pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT, f.getRegionList(),
				Constants.FORMATEAR_TODOS);
		for (int i = 1; i < lv.length; i++) {
			Base base = new Base();
			base.setCodigo(lv[i].getValue());
			base.setDescripcion(lv[i].getLabel());
			l.add(base);
		}
		if (StringUtils.isBlank(f.getIndicadorBloqueo()))
			f.setIndicadorBloqueo("");
		if (StringUtils.isBlank(f.getIndicadorFacturacion()))
			f.setIndicadorFacturacion("");
		if (StringUtils.isBlank(f.getIndicadorRechazo()))
			f.setIndicadorRechazo("");

		// request.getSession().removeAttribute(Constants.SICC_ZONA_LIST);
		// request.getSession().setAttribute(Constants.SICC_ZONA_LIST,l);
		return lista;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}

		ConsultaOCRRecepcionPedidosForm f = (ConsultaOCRRecepcionPedidosForm) this.formBusqueda;
		// Carga de los combos
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoUsuario = usuario.getLogin();
		String codigoPais = pais.getCodigo();
		Map criteria = new HashMap();

		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoUsuario", codigoUsuario);

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		this.siccRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteria);
		this.stoOrigenesList = ajax.getOrigenSTOByTipoDocumento(
				pais.getCodigo(), "OCC");
		f.setIndicadorBloqueo(Constants.OCR_BLOQUEO_CONTROL_TODOS);
		f.setIndicadorFacturacion(Constants.OCR_FACTURADOS_TODOS);
		f.setIndicadorRechazo(Constants.OCR_RECHAZO_OCR_TODOS);

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
		String[] valores = (String[]) val.getNewValue();
		if (valores.length > 0) {
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			setSiccZonaList(ajaxService.getZonasMultipleByPaisMarcaCanalRegion(
					this.getmPantallaPrincipalBean().getCurrentCountry()
							.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
					Constants.CODIGO_CANAL_DEFAULT, valores,
					Constants.OPCION_TODOS));

		} else {
			setSiccZonaList(null);
		}
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
	 * @return the stoOrigenesList
	 */
	public LabelValue[] getStoOrigenesList() {
		return stoOrigenesList;
	}

	/**
	 * @param stoOrigenesList
	 *            the stoOrigenesList to set
	 */
	public void setStoOrigenesList(LabelValue[] stoOrigenesList) {
		this.stoOrigenesList = stoOrigenesList;
	}

	/**
	 * @return the ocrRecepcionPedidosList
	 */
	public List getOcrRecepcionPedidosList() {
		return ocrRecepcionPedidosList;
	}

	/**
	 * @param ocrRecepcionPedidosList
	 *            the ocrRecepcionPedidosList to set
	 */
	public void setOcrRecepcionPedidosList(List ocrRecepcionPedidosList) {
		this.ocrRecepcionPedidosList = ocrRecepcionPedidosList;
	}

}
