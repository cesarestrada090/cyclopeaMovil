package biz.belcorp.ssicc.web.spusicc.let.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spusicc.let.form.ProcesoLETEjecutarProcesosForm;

@ManagedBean
@SessionScoped
public class ProcesoLETEjecutarProcesosAction extends
		BaseInterfazAbstractAction {

	private static final long serialVersionUID = -1421082419147031528L;

	private List siccRegionList;
	private List siccCanalList;
	private List siccMarcaList;
	private List letInterfacesPaquete;

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		ProcesoLETEjecutarProcesosForm f = (ProcesoLETEjecutarProcesosForm) this.formInterfaz;

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		// Carga de los combos Marca y Canal
		siccMarcaList = interfazSiCCService.getMarcas();
		siccCanalList = interfazSiCCService.getCanalesByCodigoISO(usuario
				.getIdioma().getCodigoISO());

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																// Campanha
																// Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																	// Campanha
																	// activa q
																	// se
																	// procesa
																	// actualmente

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteria);

		// Carga de PeriodoProceso y Fecha Facturacion
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());

		// String codigoProcesoBatch =
		// request.getParameter("codigoProcesoBatch");
		// sesion.setAttribute("codigoProcesoBatch",
		// this.mPantallaPrincipalBean.getCodigoProcesoBatch());

		String tipoCierre = (String) this.parametrosPantalla.get("tipoCierre");
		f.setTipoCierre(tipoCierre);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);

		// La busqueda solo la realizaremos en los sistemas activos
		// sesion.removeAttribute("codigoRegionElegido");
		// sesion.removeAttribute("codigoZonaElegida");

		List lista = new ArrayList();
		// sesion.removeAttribute(Constants.LET_INTERFACES_PAQUETE);

		criteria.put("codigoSistema", "LET");
		criteria.put("codigoInterfaz", f.getCodigoInterfaz());

		/* INI JJ PER-SiCC-2012-0297 */
		String tipoTransaccion = (String) this.parametrosPantalla
				.get("tipoTransaccion");
		String tipoOperacion = (String) this.parametrosPantalla
				.get("tipoOperacion");

		f.setTipoTransaccion(tipoTransaccion);
		f.setTipoOperacion(tipoOperacion);

		/* FIN JJ PER-SiCC-2012-0297 */
		lista = interfazSiCCService.getListaProcesosLet(criteria);
		letInterfacesPaquete = lista;

	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		params = super.prepareParamsBeforeExecute(params, form);
		ProcesoLETEjecutarProcesosForm f = (ProcesoLETEjecutarProcesosForm) this.formInterfaz;
		// HttpSession sesion = request.getSession();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		// sesion.setAttribute("codigoRegionElegido", f.getCodigoRegion());

		// sesion.setAttribute("codigoUsuario", usuario.getLogin());

		String servidor = this.getRequest().getServerName();
		params.put("nombreServidor", servidor);

		String tipoCierre = f.getTipoCierre();

		if (tipoCierre != null
				&& tipoCierre.equals(Constants.LET_TIPO_CIERRE_REGION)) {
			params.put("codigoRegion", f.getCodigoRegion());
		} else
			params.put("codigoRegion", "-1");
		/* INI JJ PER-SiCC-2012-0297 */
		if (f.getTipoTransaccion() != null && f.getTipoOperacion() != null
				&& f.getTipoTransaccion().equals("1")
				&& f.getTipoOperacion().equals("2")) {
			params.put("codigoCliente", "-1");
		}
		/* FIN JJ PER-SiCC-2012-0297 */
		return params;
	}

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		ProcesoLETEjecutarProcesosForm f = new ProcesoLETEjecutarProcesosForm();
		return f;
	}

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the letInterfacesPaquete
	 */
	public List getLetInterfacesPaquete() {
		return letInterfacesPaquete;
	}

	/**
	 * @param letInterfacesPaquete the letInterfacesPaquete to set
	 */
	public void setLetInterfacesPaquete(List letInterfacesPaquete) {
		this.letInterfacesPaquete = letInterfacesPaquete;
	}	
}