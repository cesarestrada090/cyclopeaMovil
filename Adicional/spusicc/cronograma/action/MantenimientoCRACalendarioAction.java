package biz.belcorp.ssicc.web.spusicc.cronograma.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang3.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.cronograma.MantenimientoCRACalendarioService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.cronograma.form.MantenimientoCRACalendarioForm;
import biz.belcorp.ssicc.web.spusicc.cronograma.form.MantenimientoCRAGrupoZonaForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoCRACalendarioAction extends
		BaseMantenimientoSearchAbstractAction {

	
	private boolean mostrarPopUpCliente = false;
	private static final String POPCALENDARIO = "CALENDARIO";

	
	private Object feriadosSeleccionados;
	private List craListaFechaNoLaborables01 = new ArrayList();
	private List craListaFechaNoLaborables02 = new ArrayList();
	private List craListaFechaNoLaborables03 = new ArrayList();
	private List craListaFechaNoLaborables04 = new ArrayList();
	private List craListaFechaNoLaborables05 = new ArrayList();
	private List craListaFechaNoLaborables06 = new ArrayList();
	private List craListaFechaNoLaborables07 = new ArrayList();
	private List craListaFechaNoLaborables08 = new ArrayList();
	private List craListaFechaNoLaborables09 = new ArrayList();
	private List craListaFechaNoLaborables10 = new ArrayList();
	private List craListaFechaNoLaborables11 = new ArrayList();
	private List craListaFechaNoLaborables12 = new ArrayList();
	private List craListaFechaNoLaborables13 = new ArrayList();
	private List craListaFechaNoLaborables14 = new ArrayList();
	private List craListaFechaNoLaborables15 = new ArrayList();
	private List craListaFechaNoLaborables16 = new ArrayList();
	private List craListaFechaNoLaborables17 = new ArrayList();
	private List craListaFechaNoLaborables18 = new ArrayList();
	private DataTableModel listaEstructuraArchivoModel;
	private List craListaFechaFeriado = new ArrayList();
	private List craListaFechaNoLaborables = new ArrayList();
	private List siccActividadList = new ArrayList();

	private Object[] columnasSeleccionadas;

	private String seleccionoRegistros = Constants.NUMERO_CERO;

	private boolean valorTrue = true;
	private boolean valorFalse = false;

	
	@ManagedProperty(value = "#{mantenimientoCRACalendarioCopiaPopupAction}")
	private MantenimientoCRACalendarioCopiaPopupAction mantenimientoCRACalendarioCopiaPopupAction;
	
	
	public boolean isMostrarPopUpCliente() {
		return mostrarPopUpCliente;
	}

	public void setMostrarPopUpCliente(boolean mostrarPopUpCliente) {
		this.mostrarPopUpCliente = mostrarPopUpCliente;
	}

	public MantenimientoCRACalendarioCopiaPopupAction getMantenimientoCRACalendarioCopiaPopupAction() {
		return mantenimientoCRACalendarioCopiaPopupAction;
	}

	public void setMantenimientoCRACalendarioCopiaPopupAction(
			MantenimientoCRACalendarioCopiaPopupAction mantenimientoCRACalendarioCopiaPopupAction) {
		this.mantenimientoCRACalendarioCopiaPopupAction = mantenimientoCRACalendarioCopiaPopupAction;
	}




	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setInvocarPopup(java.lang.String)
	 */

	@Override
	protected void setInvocarPopup(String accion) {
		this.mostrarProcesoBatch = false;
		MantenimientoCRACalendarioForm f =(MantenimientoCRACalendarioForm)formBusqueda;
		if (accion.equals(this.POPCALENDARIO)) {
			this.mostrarPopUpCliente = true;
			this.mantenimientoCRACalendarioCopiaPopupAction.setAnhio(f.getAnhio());
			this.mantenimientoCRACalendarioCopiaPopupAction.setOidActividad(f.getCodigoActividad());
			for(int i=0; i < this.siccActividadList.size(); i++) {
				Base base = (Base) this.siccActividadList.get(i);
				if (StringUtils.equals(f.getCodigoActividad(), base.getCodigo())) {
					this.mantenimientoCRACalendarioCopiaPopupAction.setNombreActividad(base.getDescripcion());
					break;
				}
			}
			
			this.mantenimientoCRACalendarioCopiaPopupAction.obtenerValores();
		}
	}




	public static String getPopcalendario() {
		return POPCALENDARIO;
	}

	public boolean isValorTrue() {
		return valorTrue;
	}

	public void setValorTrue(boolean valorTrue) {
		this.valorTrue = valorTrue;
	}

	public boolean isValorFalse() {
		return valorFalse;
	}

	public void setValorFalse(boolean valorFalse) {
		this.valorFalse = valorFalse;
	}

	public DataTableModel getListaEstructuraArchivoModel() {
		return listaEstructuraArchivoModel;
	}

	public void setListaEstructuraArchivoModel(
			DataTableModel listaEstructuraArchivoModel) {
		this.listaEstructuraArchivoModel = listaEstructuraArchivoModel;
	}

	/**
	 * SICC_ACTIVIDAD_LIST
	 */

	private static final long serialVersionUID = 8584703116370067386L;

	public List getSiccActividadList() {
		return siccActividadList;
	}

	public void setSiccActividadList(List siccActividadList) {
		this.siccActividadList = siccActividadList;
	}

	public List getCraListaFechaFeriado() {
		return craListaFechaFeriado;
	}

	public void setCraListaFechaFeriado(List craListaFechaFeriado) {
		this.craListaFechaFeriado = craListaFechaFeriado;
	}

	public List getCraListaFechaNoLaborables() {
		return craListaFechaNoLaborables;
	}

	public void setCraListaFechaNoLaborables(List craListaFechaNoLaborables) {
		this.craListaFechaNoLaborables = craListaFechaNoLaborables;
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
		MantenimientoCRACalendarioForm searchForm = new MantenimientoCRACalendarioForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'find' method");
		}

		MantenimientoCRACalendarioService service = (MantenimientoCRACalendarioService) getBean("spusicc.mantenimientoCRACalendarioService");
		MantenimientoCRACalendarioForm f = (MantenimientoCRACalendarioForm) formBusqueda;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String anhio = sdf.format(new Date(System.currentTimeMillis()));

		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("anhio", f.getAnhio());
		criteria.put("codigoActividad", f.getCodigoActividad());

		craListaFechaFeriado.clear();
		craListaFechaNoLaborables.clear();

		criteria.put("indicadorFestivo", "1");
		criteria.put("indicadorLaborable", "0");
		craListaFechaFeriado = service.getCalendarioFeriados(criteria);

		criteria.put("indicadorFestivo", "0");
		criteria.put("indicadorLaborable", "");

		asignarValores(criteria);

		return craListaFechaNoLaborables;
	}

	private void asignarValores(Map m) {

		MantenimientoCRACalendarioForm f = (MantenimientoCRACalendarioForm) formBusqueda;
		MantenimientoCRACalendarioService service = (MantenimientoCRACalendarioService) getBean("spusicc.mantenimientoCRACalendarioService");
		StringBuilder b = new StringBuilder();
		b.append(f.getAnhio());
		// periodo 1

		b.append("01");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables01 = service.getCalendarioFeriados(m);

		b.replace(4, 6, "02");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables02 = service.getCalendarioFeriados(m);

		b.replace(4, 6, "03");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables03 = service.getCalendarioFeriados(m);

		b.replace(4, 6, "04");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables04 = service.getCalendarioFeriados(m);

		b.replace(4, 6, "05");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables05 = service.getCalendarioFeriados(m);

		b.replace(4, 6, "06");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables06 = service.getCalendarioFeriados(m);

		b.replace(4, 6, "07");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables07 = service.getCalendarioFeriados(m);

		b.replace(4, 6, "08");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables08 = service.getCalendarioFeriados(m);

		b.replace(4, 6, "09");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables09 = service.getCalendarioFeriados(m);

		b.replace(4, 6, "10");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables10 = service.getCalendarioFeriados(m);

		b.replace(4, 6, "11");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables11 = service.getCalendarioFeriados(m);

		b.replace(4, 6, "12");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables12 = service.getCalendarioFeriados(m);

		b.replace(4, 6, "13");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables13 = service.getCalendarioFeriados(m);

		b.replace(4, 6, "14");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables14 = service.getCalendarioFeriados(m);

		b.replace(4, 6, "15");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables15 = service.getCalendarioFeriados(m);

		b.replace(4, 6, "16");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables16 = service.getCalendarioFeriados(m);

		b.replace(4, 6, "17");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables17 = service.getCalendarioFeriados(m);

		b.replace(4, 6, "18");
		m.put("codigoPeriodo", b.toString());
		this.craListaFechaNoLaborables18 = service.getCalendarioFeriados(m);

	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {

		return true;

	}

	@Override
	protected boolean setSaveAttributes() throws Exception {

		return true;

	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {

		/* *********** METODO ADD ******** */

		MantenimientoCRAGrupoZonaForm f = new MantenimientoCRAGrupoZonaForm();

		return f;

	}

	@Override
	protected void setViewAtributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'setViewAttributes' method");
		}
		this.mostrarBotonConsultar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonSave = false;
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoCRACalendarioForm f = (MantenimientoCRACalendarioForm) formBusqueda;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String anhio = sdf.format(new Date(System.currentTimeMillis()));

		if (f.getAnhio() == null)
			f.setAnhio(anhio);

		f.setCodigoActividad("");
		f.setCodigoPais(pais.getCodigo());

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		// obteniendo las lista de grupode facturacion y actividad
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		Long oidMarca = clienteService
				.getOidMarca(Constants.CODIGO_MARCA_DEFAULT);
		Long oidCanal = clienteService
				.getOidCanal(Constants.CODIGO_CANAL_DEFAULT);

		Map params = new HashMap();
		params.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());
		params.put(
				"oidPais",
				new Long(reporteService.getOidString("getOidPaisByCodigoPais",
						params)));
		params.put("oidMarca", oidMarca);
		params.put("oidCanal", oidCanal);

		this.siccActividadList = reporteService.getActividad(params);
		if (this.siccActividadList != null && this.siccActividadList.size() > 0) {
			Base base = (Base) this.siccActividadList.get(0);
			f.setCodigoActividad(base.getCodigo());
		}

	}

	/**
	 * @param
	 * @throws Exception
	 */

	public void generarDiasLaborables(String method) {
		if (log.isDebugEnabled()) {
			log.debug("JFA : Entering 'generarDiasLaborables' method");
		}

        MantenimientoCRACalendarioService service= (MantenimientoCRACalendarioService)getBean("spusicc.mantenimientoCRACalendarioService");
        MantenimientoCRACalendarioForm f =(MantenimientoCRACalendarioForm)formBusqueda;        
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("anhio", f.getAnhio());
		criteria.put("codigoActividad", f.getCodigoActividad());
		criteria.put("marca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("canal", Constants.CODIGO_CANAL_DEFAULT);		
		criteria.put("usuario", mPantallaPrincipalBean.getCurrentUser().getLogin());
		
		String id = method;
		if (id.equals("generarSabados")){
			criteria.put("fecha",Constants.CRA_DIA_SABADO);
		}
		if (id.equals("generarDomingos")){
			criteria.put("fecha",Constants.CRA_DIA_DOMINGO);
		}
			
		service.insertCalendarioDiaNoLaborable(criteria);
		criteria.put("indicadorFestivo", "0");
		criteria.put("indicadorLaborable", "");
		asignarValores(criteria);
		addInfo("Mensaje",
				getResourceMessage("mantenimientoCRACalendarioForm.msj.registrar"));											

	}

	public void deleteDiasLaborables(String method) {
		if (log.isDebugEnabled()) {
			log.debug("JFA : Entering 'delete' method");
		}

        MantenimientoCRACalendarioService service= (MantenimientoCRACalendarioService)getBean("spusicc.mantenimientoCRACalendarioService");
        MantenimientoCRACalendarioForm f =(MantenimientoCRACalendarioForm)formBusqueda;        
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("anhio", f.getAnhio());
		criteria.put("codigoActividad", f.getCodigoActividad());
		criteria.put("marca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("canal", Constants.CODIGO_CANAL_DEFAULT);		
		criteria.put("usuario", mPantallaPrincipalBean.getCurrentUser().getLogin());
		
		String id =method;
		if (id.equals("deleteSabados")){
			criteria.put("fecha",Constants.CRA_DIA_SABADO);
		}
		if (id.equals("deleteDomingos")){
			criteria.put("fecha",Constants.CRA_DIA_DOMINGO);
		}
			
		service.deleteDiaNoLaborable(criteria);
		criteria.put("indicadorFestivo", "0");
		criteria.put("indicadorLaborable", "");
		asignarValores(criteria);
		addInfo("Mensaje",
				getResourceMessage("mantenimientoCRACalendarioForm.msj.deleteDiaNoLaborable"));	
	}

	public void grabarFeriados() {

		if (log.isDebugEnabled()) {
			log.debug("JFA : Entering 'add' method");
		}

		MantenimientoCRACalendarioService service = (MantenimientoCRACalendarioService) getBean("spusicc.mantenimientoCRACalendarioService");
		MantenimientoCRACalendarioForm f = (MantenimientoCRACalendarioForm) formBusqueda;
		f.setFecha(DateUtil.getDate(f.getFechaD()));
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("anhio", f.getAnhio());
		criteria.put("codigoActividad", f.getCodigoActividad());
		criteria.put("marca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("canal", Constants.CODIGO_CANAL_DEFAULT);

		criteria.put("fecha", f.getFecha());
		criteria.put("usuario", mPantallaPrincipalBean.getCurrentUser()
				.getLogin());

		int result = service.insertFeriados(criteria);
		criteria.put("indicadorFestivo", "1");
		criteria.put("indicadorLaborable", "0");
		craListaFechaFeriado = service.getCalendarioFeriados(criteria);
		if (result != 0) {
			addInfo("Mensaje",
					getResourceMessage("mantenimientoCRACalendarioForm.msj.existeFecha"));
		} else {
			addInfo("Mensaje",
					getResourceMessage("mantenimientoCRACalendarioForm.msj.registrar"));
		}

	}

	public void eliminarFeriados() {
		Map feriados = (Map) feriadosSeleccionados;
		if (log.isDebugEnabled()) {
			log.debug("JFA : Entering 'remove' method");
		}
		log.debug("setDeleteAttributes");

		MantenimientoCRACalendarioService service = (MantenimientoCRACalendarioService) getBean("spusicc.mantenimientoCRACalendarioService");
		MantenimientoCRACalendarioForm f = (MantenimientoCRACalendarioForm) formBusqueda;

		Map criteria = new HashMap();

		String id = feriados.get("fecha").toString();

		if (id != null) {

			if (log.isDebugEnabled()) {
				log.debug("Id seleccionado de la lista: " + id);
			}

			try {

				criteria.put("usuario", mPantallaPrincipalBean.getCurrentUser()
						.getLogin());
				criteria.put("fecha", id);
				criteria.put("codigoPais", f.getCodigoPais());
				criteria.put("anhio", f.getAnhio());
				criteria.put("indicadorFestivo", "1");
				criteria.put("indicadorLaborable", "0");
				criteria.put("codigoActividad", f.getCodigoActividad());
				service.deleteFeriado(criteria);
				feriadosSeleccionados = null;
				craListaFechaFeriado = service.getCalendarioFeriados(criteria);
				addInfo("Mensaje", "Feriado eliminado correctamente");
			} catch (Exception e) {
				String error = e.getMessage();
				addError("Mensaje", error);

			}

		}
	}

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

	public List getCraListaFechaNoLaborables01() {
		return craListaFechaNoLaborables01;
	}

	public void setCraListaFechaNoLaborables01(List craListaFechaNoLaborables01) {
		this.craListaFechaNoLaborables01 = craListaFechaNoLaborables01;
	}

	public List getCraListaFechaNoLaborables02() {
		return craListaFechaNoLaborables02;
	}

	public void setCraListaFechaNoLaborables02(List craListaFechaNoLaborables02) {
		this.craListaFechaNoLaborables02 = craListaFechaNoLaborables02;
	}

	public List getCraListaFechaNoLaborables03() {
		return craListaFechaNoLaborables03;
	}

	public void setCraListaFechaNoLaborables03(List craListaFechaNoLaborables03) {
		this.craListaFechaNoLaborables03 = craListaFechaNoLaborables03;
	}

	public List getCraListaFechaNoLaborables04() {
		return craListaFechaNoLaborables04;
	}

	public void setCraListaFechaNoLaborables04(List craListaFechaNoLaborables04) {
		this.craListaFechaNoLaborables04 = craListaFechaNoLaborables04;
	}

	public List getCraListaFechaNoLaborables05() {
		return craListaFechaNoLaborables05;
	}

	public void setCraListaFechaNoLaborables05(List craListaFechaNoLaborables05) {
		this.craListaFechaNoLaborables05 = craListaFechaNoLaborables05;
	}

	public List getCraListaFechaNoLaborables06() {
		return craListaFechaNoLaborables06;
	}

	public void setCraListaFechaNoLaborables06(List craListaFechaNoLaborables06) {
		this.craListaFechaNoLaborables06 = craListaFechaNoLaborables06;
	}

	public List getCraListaFechaNoLaborables07() {
		return craListaFechaNoLaborables07;
	}

	public void setCraListaFechaNoLaborables07(List craListaFechaNoLaborables07) {
		this.craListaFechaNoLaborables07 = craListaFechaNoLaborables07;
	}

	public List getCraListaFechaNoLaborables08() {
		return craListaFechaNoLaborables08;
	}

	public void setCraListaFechaNoLaborables08(List craListaFechaNoLaborables08) {
		this.craListaFechaNoLaborables08 = craListaFechaNoLaborables08;
	}

	public List getCraListaFechaNoLaborables09() {
		return craListaFechaNoLaborables09;
	}

	public void setCraListaFechaNoLaborables09(List craListaFechaNoLaborables09) {
		this.craListaFechaNoLaborables09 = craListaFechaNoLaborables09;
	}

	public List getCraListaFechaNoLaborables10() {
		return craListaFechaNoLaborables10;
	}

	public void setCraListaFechaNoLaborables10(List craListaFechaNoLaborables10) {
		this.craListaFechaNoLaborables10 = craListaFechaNoLaborables10;
	}

	public List getCraListaFechaNoLaborables11() {
		return craListaFechaNoLaborables11;
	}

	public void setCraListaFechaNoLaborables11(List craListaFechaNoLaborables11) {
		this.craListaFechaNoLaborables11 = craListaFechaNoLaborables11;
	}

	public List getCraListaFechaNoLaborables12() {
		return craListaFechaNoLaborables12;
	}

	public void setCraListaFechaNoLaborables12(List craListaFechaNoLaborables12) {
		this.craListaFechaNoLaborables12 = craListaFechaNoLaborables12;
	}

	public List getCraListaFechaNoLaborables13() {
		return craListaFechaNoLaborables13;
	}

	public void setCraListaFechaNoLaborables13(List craListaFechaNoLaborables13) {
		this.craListaFechaNoLaborables13 = craListaFechaNoLaborables13;
	}

	public List getCraListaFechaNoLaborables14() {
		return craListaFechaNoLaborables14;
	}

	public void setCraListaFechaNoLaborables14(List craListaFechaNoLaborables14) {
		this.craListaFechaNoLaborables14 = craListaFechaNoLaborables14;
	}

	public List getCraListaFechaNoLaborables15() {
		return craListaFechaNoLaborables15;
	}

	public void setCraListaFechaNoLaborables15(List craListaFechaNoLaborables15) {
		this.craListaFechaNoLaborables15 = craListaFechaNoLaborables15;
	}

	public List getCraListaFechaNoLaborables16() {
		return craListaFechaNoLaborables16;
	}

	public void setCraListaFechaNoLaborables16(List craListaFechaNoLaborables16) {
		this.craListaFechaNoLaborables16 = craListaFechaNoLaborables16;
	}

	public List getCraListaFechaNoLaborables17() {
		return craListaFechaNoLaborables17;
	}

	public void setCraListaFechaNoLaborables17(List craListaFechaNoLaborables17) {
		this.craListaFechaNoLaborables17 = craListaFechaNoLaborables17;
	}

	public List getCraListaFechaNoLaborables18() {
		return craListaFechaNoLaborables18;
	}

	public void setCraListaFechaNoLaborables18(List craListaFechaNoLaborables18) {
		this.craListaFechaNoLaborables18 = craListaFechaNoLaborables18;
	}

	public Object[] getColumnasSeleccionadas() {
		return columnasSeleccionadas;
	}

	public void setColumnasSeleccionadas(Object[] columnasSeleccionadas) {
		this.columnasSeleccionadas = columnasSeleccionadas;
	}

	public String getSeleccionoRegistros() {
		return seleccionoRegistros;
	}

	public void setSeleccionoRegistros(String seleccionoRegistros) {
		this.seleccionoRegistros = seleccionoRegistros;
	}

	public Object getFeriadosSeleccionados() {
		return feriadosSeleccionados;
	}

	public void setFeriadosSeleccionados(Object feriadosSeleccionados) {
		this.feriadosSeleccionados = feriadosSeleccionados;
	}
	public void definirUA(ActionEvent event) throws IOException
	{
	

		String ventana = "PF('dialogGrabarCalendar').show()";
		this.getRequestContext().execute(ventana);

	
	}
	

}
