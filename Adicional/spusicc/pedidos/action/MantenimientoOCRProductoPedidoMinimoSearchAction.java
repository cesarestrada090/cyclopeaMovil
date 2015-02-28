package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
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
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.ProductoAgregacion;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoOCRProductoPedidoMinimoForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoOCRProductoPedidoMinimoSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoOCRProductoPedidoMinimoSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 1L;
	private List pedProductoPedidoMinimoList;
	private List siccMarcaList;
	private List siccCanalList;
	private String variable;
	private String mostrarCodigoVenta = "false";

	public void valida(ActionEvent evt) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		MantenimientoOCRProductoPedidoMinimoForm pedidoMinimoForm = (MantenimientoOCRProductoPedidoMinimoForm) this.formMantenimiento;

		if (validationSuccessful()) {
			
			if (pedidoMinimoForm.getCodigoValida().equals(
					Constants.IND_VALIDA_PERIODO)) {
				pedidoMinimoForm
						.setCodigoValida(Constants.IND_VALIDA_COD_VENTA);
			} else {
				//this.mostrarBotonSave = true;
				Map map = BeanUtils.describe(pedidoMinimoForm);
				BigDecimal oidOferta = service
						.getOfertaDetalleByPeriodoCodigoVenta(map);
				map.put("idDetalleOferta", oidOferta);
				ProductoAgregacion productoAgregacion = service
						.getOfertaDetalleById(map);
				BeanUtils.copyProperties(pedidoMinimoForm, productoAgregacion);
				pedidoMinimoForm.setCodigoValida(Constants.IND_NO_VALIDA);
			}
		}
	}

	private Map getCriteria() throws Exception {
		MantenimientoOCRProductoPedidoMinimoSearchForm pedidoMinimoForm = (MantenimientoOCRProductoPedidoMinimoSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = BeanUtils.describe(pedidoMinimoForm);
		criteria.put("estado", Constants.ESTADO_ACTIVO);
		criteria.put("codigoPais", pais.getCodigo());
		String codigoPeriodo = pedidoMinimoForm.getCodigoPeriodo();

		if (StringUtils.isNotBlank(pedidoMinimoForm.getCodigoPeriodo())) {
			criteria.put("codigoPeriodo", pedidoMinimoForm.getCodigoPeriodo());
		}
		return criteria;
	}

	private String getCodigoPais() {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		return pais.getCodigo();
	}

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoOCRProductoPedidoMinimoList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoOCRProductoPedidoMinimoForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoOCRProductoPedidoMinimoSearchForm form = new MantenimientoOCRProductoPedidoMinimoSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}
		MantenimientoOCRProductoPedidoMinimoSearchForm pedidoMinimoForm = (MantenimientoOCRProductoPedidoMinimoSearchForm) this.formBusqueda;
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		List list = service.getProductoAgregacionByCriteria(getCriteria());
		pedProductoPedidoMinimoList = list;

		return list;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete' method");
		}
		MantenimientoOCRProductoPedidoMinimoSearchForm pedidoMinimoForm = (MantenimientoOCRProductoPedidoMinimoSearchForm) this.formBusqueda;
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		ProductoAgregacion agregacion = getControlBean();
		service.desactivarProductoPedidoMinimo(agregacion);
		service.executeActualizaPrioridad(BeanUtils.describe(agregacion));
		BeanUtils.copyProperties(pedidoMinimoForm, agregacion);

		return false;
	}

	private ProductoAgregacion getControlBean() {
		MantenimientoOCRProductoPedidoMinimoSearchForm pedidoMinimoForm = (MantenimientoOCRProductoPedidoMinimoSearchForm) this.formBusqueda;
		ProductoAgregacion sistemabusqueda = (ProductoAgregacion) this.beanRegistroSeleccionado;

		String id = sistemabusqueda.getId();

		ProductoAgregacion productoAgregacion = new ProductoAgregacion();
		productoAgregacion.setCodigoPais(StringUtils.split(id, "-")[0]);
		productoAgregacion.setCodigoPeriodo(StringUtils.split(id, "-")[1]);
		productoAgregacion.setCodigoVenta(StringUtils.split(id, "-")[2]);
		// Se cierra campanha con estado 1
		productoAgregacion.setEstadoRegistro(Constants.NUMERO_CERO);
		return productoAgregacion;
	}

	private boolean validationSuccessful() throws Exception {
		MantenimientoOCRProductoPedidoMinimoForm form = (MantenimientoOCRProductoPedidoMinimoForm) this.formMantenimiento;
		boolean isOk = true;
		// ActionMessages errors = new ActionMessages();
		Map map = BeanUtils.describe(form);
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		String mensaje = null;
		if (form.getCodigoValida().equals(Constants.IND_VALIDA_PERIODO)) {
			BigDecimal oidMatriz = service.getMatrizFacturacionByPeriodo(map);
			if (oidMatriz.intValue() <= 0) {

				mensaje = getResourceMessage("errors.campanha.matriz");
				this.addError("Error: ", mensaje);
				this.setVariable("false");
				return false;
			}
		} else if (form.getCodigoValida()
				.equals(Constants.IND_VALIDA_COD_VENTA)) {
			this.setVariable("true");
			BigDecimal existeCodigo = service.getCodVentaAgregacById(map);
			if (existeCodigo.intValue() > 0) {
				this.mostrarBotonSave = false;
				mensaje = getResourceMessage("errors.codigo.venta.tabla");
				this.addError("Error: ", mensaje);
				this.setVariable("false");
				return false;
			} else {
				BigDecimal oidOferta = service
						.getOfertaDetalleByPeriodoCodigoVenta(map);
				if (oidOferta.intValue() <= 0) {
					this.mostrarBotonSave = false;
					mensaje = getResourceMessage("errors.codigo.venta.matriz");
					this.addError("Error: ", mensaje);
					this.setVariable("false");
					return false;

				} else {
					this.mostrarCodigoVenta = "true";
					map.put("idDetalleOferta", oidOferta);
					BigDecimal indicaDigit = service
							.getDetOfertaIndicaDigitableById(map);
					BigDecimal precioCatal = service
							.getDetOfertaPrecioCatalogoById(map);
					BigDecimal estrategia = service
							.getDetOfertaEstrategiaIndividualById(map);
					if (indicaDigit.intValue() <= 0) {
						this.mostrarBotonSave = false;
						mensaje = getResourceMessage("errors.indica.digitable.matriz");
						this.addError("Error: ", mensaje);
						this.setVariable("false");
						return false;
					}
					if (precioCatal.intValue() <= 0) {
						this.mostrarBotonSave = false;
						mensaje = getResourceMessage("errors.precio.matriz");
						this.addError("Error: ", mensaje);
						this.setVariable("false");
						return false;
					}
					if (estrategia.intValue() <= 0) {
						this.mostrarBotonSave = false;
						mensaje = getResourceMessage("errors.estrategia.matriz");
						this.addError("Error: ", mensaje);
						this.setVariable("false");
						return false;
					}
				}
			}
		}
		if (mensaje != null) {
			this.addError("Error", mensaje);
			// saveErrors(request, errors);
			isOk = false;
		}
		this.mostrarCodigoVenta = "true";
		return isOk;
	}

	private boolean isUpdate() {
		//MantenimientoOCRProductoPedidoMinimoForm pedidoMinimoForm = (MantenimientoOCRProductoPedidoMinimoForm) this.formMantenimiento;
		ProductoAgregacion sistemabusqueda = (ProductoAgregacion) this.beanRegistroSeleccionado;
		boolean updateFlag = true;
		// String id = request.getParameter("id");
		String id = sistemabusqueda.getId();
		if (StringUtils.isBlank(id)) {
			updateFlag = false;
		}
		return updateFlag;
	}

	/**
	 * Metodo para habilitar el texbox
	 * 
	 * @param val
	 * @throws Exception
	 * @throws ParseException
	 */
	public void loadFechasPeriodo(String valor) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("loadFechasPeriodo");
		}

		MantenimientoOCRProductoPedidoMinimoForm pedidoMinimoForm = (MantenimientoOCRProductoPedidoMinimoForm) this.formMantenimiento;
		pedidoMinimoForm.setCodigoPeriodo(valor);
		String periodo = pedidoMinimoForm.getCodigoPeriodo();
		if (validationSuccessful()) {
			this.setVariable("true");
		} else {
			this.setVariable("false");
		}

	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		MantenimientoOCRProductoPedidoMinimoForm pedidoMinimoForm = (MantenimientoOCRProductoPedidoMinimoForm) this.formMantenimiento;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		if (validationSuccessful()) {
			ProductoAgregacion productoAgregacion = new ProductoAgregacion();
			BeanUtils.copyProperties(productoAgregacion, pedidoMinimoForm);
			if (isUpdate()) {
				String mensaje =  this.getResourceMessage("codigo.venta.updated");
				this.addInfo("Info : ", mensaje);
			} else {
				Map params = BeanUtils.describe(pedidoMinimoForm);
				productoAgregacion.setCodigoPais(pais.getCodigo());
				// productoAgregacion.setCodigoProducto(pais.getCodigo());
				log.debug("insert bean " + pedidoMinimoForm);
				productoAgregacion.setEstadoRegistro(Constants.ESTADO_ACTIVO);
				service.insertProductoPedidoMinimo(productoAgregacion, usuario);
				service.executeActualizaPrioridad(params);
				String mensaje =  this.getResourceMessage("codigo.venta.added");
				this.addInfo("Info : ", mensaje);
				// saveMessages(messages);
				log.debug("insert bean " + productoAgregacion);
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoOCRProductoPedidoMinimoForm sistemaForm = (MantenimientoOCRProductoPedidoMinimoForm) this.formMantenimiento;
		boolean isNew = sistemaForm.isNewRecord();
		if (isNew) {
			return "codigo.venta.added";
		} else {
			return "codigo.venta.updated";
		}

	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub

		if (log.isDebugEnabled()) {
			log.debug("Entering 'edit' method");
		}
		ProductoAgregacion sistemabusqueda = (ProductoAgregacion) this.beanRegistroSeleccionado;

		// Sistema sistemabusqueda1 = (Sistema) this.beanRegistroSeleccionado;

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		MantenimientoOCRProductoPedidoMinimoForm pedidoMinimoForm = new MantenimientoOCRProductoPedidoMinimoForm();
		this.mostrarBotonSave = false;
		// setViewAttributes(request, form);
		if (!this.accion.equals(this.ACCION_NUEVO)) {
			pedidoMinimoForm.setNewRecord(false);
			this.mostrarBotonSave = true;
			String id = sistemabusqueda.getId();
			// String id = pedidoMinimoForm.getId();
			ProductoAgregacion productoAgregacion = service
					.getProductoAgregacionById(getCriteria(id));
			BeanUtils.copyProperties(pedidoMinimoForm, productoAgregacion);
			pedidoMinimoForm.setCodigoValida(Constants.IND_NO_VALIDA);
		} else {
			pedidoMinimoForm.setCodigoValida(Constants.IND_VALIDA_PERIODO);
		}
		return pedidoMinimoForm;
	}

	private Map getCriteria(String id) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", StringUtils.split(id, "-")[0]);
		criteria.put("codigoPeriodo", StringUtils.split(id, "-")[1]);
		criteria.put("codigoVenta", StringUtils.split(id, "-")[2]);
		return criteria;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		this.setVariable("false");
		this.mostrarCodigoVenta = "false";

		this.mostrarBotonSave = false;
		this.mostrarBotonConsultar = false;

		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		// Carga de los combos Marca, Canal
		siccMarcaList = interfazSiCCService.getMarcas();
		siccCanalList = interfazSiCCService.getCanalesByCodigoISO(usuario
				.getIdioma().getCodigoISO());
	}

	/**
	 * @return the pedProductoPedidoMinimoList
	 */
	public List getPedProductoPedidoMinimoList() {
		return pedProductoPedidoMinimoList;
	}

	/**
	 * @param pedProductoPedidoMinimoList
	 *            the pedProductoPedidoMinimoList to set
	 */
	public void setPedProductoPedidoMinimoList(List pedProductoPedidoMinimoList) {
		this.pedProductoPedidoMinimoList = pedProductoPedidoMinimoList;
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
	 * @return the variable
	 */
	public String getVariable() {
		return variable;
	}

	/**
	 * @param variable
	 *            the variable to set
	 */
	public void setVariable(String variable) {
		this.variable = variable;
	}

	/**
	 * @return the mostrarCodigoVenta
	 */
	public String getMostrarCodigoVenta() {
		return mostrarCodigoVenta;
	}

	/**
	 * @param mostrarCodigoVenta the mostrarCodigoVenta to set
	 */
	public void setMostrarCodigoVenta(String mostrarCodigoVenta) {
		this.mostrarCodigoVenta = mostrarCodigoVenta;
	}
}