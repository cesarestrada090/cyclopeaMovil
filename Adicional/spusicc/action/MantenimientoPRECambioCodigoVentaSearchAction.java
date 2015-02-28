package biz.belcorp.ssicc.web.spusicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.MantenimientoPRECambioCodigoVentaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoPRECambioCodigoVentaSearchForm;

@SessionScoped
@ManagedBean
public class MantenimientoPRECambioCodigoVentaSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3745381541348661985L;
	private String codigoPais;
	private List siccMarcaList;
	private List siccCatalogoList;
	private List siccTipoOfertaList;
	private List preEstrategiaList;
	private List tipoVarianteList;
	private List listaCUV;
	private String numSecUsuario;
	private String periodoActivo;
	private List preCuvList;
	private List preCodigoVentaList;

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoPRECambioCodigoVentaList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoPRECambioCodigoVentaModificaCUVForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoPRECambioCodigoVentaSearchForm form = new MantenimientoPRECambioCodigoVentaSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		Map criteria = new HashMap();

		MantenimientoPRECambioCodigoVentaSearchForm f = (MantenimientoPRECambioCodigoVentaSearchForm) this.formBusqueda;
		MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("oidPeriodo", reporteService.getOidString(
				"getOidPeriodoByCodigoPeriodo", criteria));
		criteria.put("codigoMarcaProducto", f.getCodigoMarcaProducto());
		criteria.put("cuv", f.getCUV());
		criteria.put("codigoTipoOferta", f.getCodigoTipoOferta());
		criteria.put("codigoCatalogo", f.getCodigoCatalogo());
		criteria.put("numeroPagina", f.getNumeroPagina());
		criteria.put("codigoVariante", f.getCodigoVariante());
		criteria.put("numSecUsuario", this.numSecUsuario);
		criteria.put("oidEstrategia", f.getCodigoEstrategia());

		if (f.getCodigoVariante() != null) {
			criteria.put("oidVariante", service.getOidVariante(criteria));
		} else {
			criteria.put("oidVariante", "");
		}

		List resultado = (List) service.getCodigoVentaList(criteria);
		this.preCuvList = resultado;
		this.listaCUV = resultado;

		List resultadoMod = (List) service
				.getCodigoVentaModificarList(criteria);
		
		this.preCodigoVentaList= resultadoMod;

		return resultado;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		codigoPais = pais.getCodigo();
		MantenimientoPRECambioCodigoVentaSearchForm f = (MantenimientoPRECambioCodigoVentaSearchForm) this.formBusqueda;
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");

		f.setCodigoPais(codigoPais);
		f.setCodigoPeriodo(interfazSiCCService.getPeriodoDefaultByPaisCanal(
				codigoPais, Constants.CODIGO_CANAL_DEFAULT));

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", codigoPais);
		this.siccMarcaList = reporteService.getListaGenerico("getMarcaProdu",
				null);

		f.setCUV("");
		this.siccTipoOfertaList = service.getTipoOfertaList(criteriaOperacion);
		this.siccCatalogoList = reporteService.getListaGenerico(
				"getCatalogoProductos", null);

		f.setNumeroPagina("");
		f.setCodigoCatalogo("");
		f.setCodigoMarcaProducto("");
		f.setCodigoTipoOferta("");
		f.setCodigoVariante("");
		f.setCodigoEstrategia("");
		this.tipoVarianteList = service.getTipoVarianteList(criteriaOperacion);
		this.preEstrategiaList = service.getEstrategiaList(criteriaOperacion);

		this.periodoActivo = service.getPeriodoActivo(criteriaOperacion);
		f.setCodigoPeriodoActivo(this.periodoActivo);

		Map criteria = new HashMap();

		// Se obtiene el numero de secuencia de la sesion para asignarlo al
		// usuario
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		criteria.put("usuario", usuario.getLogin());
		this.numSecUsuario = service.getNumeroSecuenciaUsuario();
		criteria.put("numSecUsuario", this.numSecUsuario);
		service.deleteCUVTemporal(criteria);
		this.mostrarBotonConsultar=false;

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
	 * @param siccMarcaList the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccCatalogoList
	 */
	public List getSiccCatalogoList() {
		return siccCatalogoList;
	}

	/**
	 * @param siccCatalogoList the siccCatalogoList to set
	 */
	public void setSiccCatalogoList(List siccCatalogoList) {
		this.siccCatalogoList = siccCatalogoList;
	}

	/**
	 * @return the siccTipoOfertaList
	 */
	public List getSiccTipoOfertaList() {
		return siccTipoOfertaList;
	}

	/**
	 * @param siccTipoOfertaList the siccTipoOfertaList to set
	 */
	public void setSiccTipoOfertaList(List siccTipoOfertaList) {
		this.siccTipoOfertaList = siccTipoOfertaList;
	}

	/**
	 * @return the preCodigoVentaList
	 */
	public List getPreCodigoVentaList() {
		return preCodigoVentaList;
	}

	/**
	 * @param preCodigoVentaList the preCodigoVentaList to set
	 */
	public void setPreCodigoVentaList(List preCodigoVentaList) {
		this.preCodigoVentaList = preCodigoVentaList;
	}

	/**
	 * @return the preEstrategiaList
	 */
	public List getPreEstrategiaList() {
		return preEstrategiaList;
	}

	/**
	 * @param preEstrategiaList the preEstrategiaList to set
	 */
	public void setPreEstrategiaList(List preEstrategiaList) {
		this.preEstrategiaList = preEstrategiaList;
	}

	/**
	 * @return the tipoVarianteList
	 */
	public List getTipoVarianteList() {
		return tipoVarianteList;
	}

	/**
	 * @param tipoVarianteList the tipoVarianteList to set
	 */
	public void setTipoVarianteList(List tipoVarianteList) {
		this.tipoVarianteList = tipoVarianteList;
	}

	/**
	 * @return the listaCUV
	 */
	public List getListaCUV() {
		return listaCUV;
	}

	/**
	 * @param listaCUV the listaCUV to set
	 */
	public void setListaCUV(List listaCUV) {
		this.listaCUV = listaCUV;
	}

	/**
	 * @return the numSecUsuario
	 */
	public String getNumSecUsuario() {
		return numSecUsuario;
	}

	/**
	 * @param numSecUsuario the numSecUsuario to set
	 */
	public void setNumSecUsuario(String numSecUsuario) {
		this.numSecUsuario = numSecUsuario;
	}

	/**
	 * @return the periodoActivo
	 */
	public String getPeriodoActivo() {
		return periodoActivo;
	}

	/**
	 * @param periodoActivo the periodoActivo to set
	 */
	public void setPeriodoActivo(String periodoActivo) {
		this.periodoActivo = periodoActivo;
	}

	/**
	 * @return the preCuvList
	 */
	public List getPreCuvList() {
		return preCuvList;
	}

	/**
	 * @param preCuvList the preCuvList to set
	 */
	public void setPreCuvList(List preCuvList) {
		this.preCuvList = preCuvList;
	}
	

}
