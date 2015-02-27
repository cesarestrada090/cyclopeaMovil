package biz.belcorp.ssicc.web.spusicc.cronograma.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.spusicc.cronograma.MantenimientoCRAActividadService;
import biz.belcorp.ssicc.service.spusicc.cronograma.MantenimientoCRAMatrizDiasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.cronograma.form.MantenimientoCRAActividadForm;
import biz.belcorp.ssicc.web.spusicc.cronograma.form.MantenimientoCRAActividadSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoCRAActividadSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 7850484395118578933L;

	private List craTipoActividadList;
	private List craActividadesList;
	private List craClaseList;
	private List craIndicadorDiasList;
	private boolean opcionMostrarActividad;
	private boolean opcionNombre;

	@Override
	protected String getSalirForward() {
		return "mantenimientoCRAActividadList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoCRAActividadForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCRAActividadSearchForm searchForm = new MantenimientoCRAActividadSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		log.debug("setFindAttributes");

		MantenimientoCRAActividadSearchForm f = (MantenimientoCRAActividadSearchForm) this.formBusqueda;
		MantenimientoCRAActividadService service = (MantenimientoCRAActividadService) getBean("spusicc.mantenimientoCRAActividadService");

		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("tipo", f.getTipo());
		criteria.put("nombre", f.getNombre());

		List list = new ArrayList();
		list = service.getActividades(criteria);

		this.craActividadesList = list;
		f.setTipo("");
		f.setNombre("");
		return list;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		log.debug("setDeleteAttributes");
		Map sistemabusqueda = (Map) this.beanRegistroSeleccionado;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoCRAActividadSearchForm f = (MantenimientoCRAActividadSearchForm) this.formBusqueda;
		MantenimientoCRAActividadService service = (MantenimientoCRAActividadService) getBean("spusicc.mantenimientoCRAActividadService");
		MantenimientoCRAMatrizDiasService serviceMatriz = (MantenimientoCRAMatrizDiasService) getBean("spusicc.cronograma.mantenimientoCRAMatrizDiasService");

		Map criteria = new HashMap();

		String id = sistemabusqueda.get("id").toString();

		if (id != null) {

			if (log.isDebugEnabled()) {
				log.debug("Id seleccionado de la lista: " + id);
			}
			try {
				criteria.put("usuario", usuario.getLogin());
				criteria.put("id", StringUtils.split(id, "|")[0]);
				criteria.put("codigoPais", pais.getCodigo());

				service.deleteActividad(criteria);
				// Eliminar actividad, delete matriz
				serviceMatriz.updateMatrizDeleteActividad(criteria);
				return true;
			} catch (Exception e) {
				String error = e.getMessage();
				if (StringUtils.isBlank(error))
					error = e.getLocalizedMessage();
				throw new Exception(this.getResourceMessage("errors.detail"));
			}
		}
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method");
		}

		MantenimientoCRAActividadForm f = (MantenimientoCRAActividadForm) this.formMantenimiento;
		MantenimientoCRAActividadService service = (MantenimientoCRAActividadService) getBean("spusicc.mantenimientoCRAActividadService");
		MantenimientoCRAMatrizDiasService serviceMatriz = (MantenimientoCRAMatrizDiasService) getBean("spusicc.cronograma.mantenimientoCRAMatrizDiasService");

		Map map = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		try {
			String id = f.getId();

			map.put("codigoPais", pais.getCodigo());
			map.put("marca", Constants.CODIGO_MARCA_DEFAULT);
			map.put("canal", Constants.CODIGO_CANAL_DEFAULT);
			map.put("acceso", Constants.CODIGO_ACCESO_DEFAULT);
			map.put("areaResponsable", Constants.CODIGO_AREA_RESPONSABLE);
			map.put("codigo", f.getCodigo());
			map.put("actividadOrigen",(f.getTipo().equals("0")) ? null : f.getActividadOrigen()); //Si es fijo no tiene actividad origen
			map.put("tipo", f.getTipo());
			map.put("diasDesplazamiento", f.getDiasDesplazamiento());
			map.put("indicadorDias", f.getIndicadorDias());
			map.put("clase", f.getClase());
			map.put("nombre", f.getNombre());
			map.put("usuario", usuario.getLogin());
			map.put("id", id);

			if (f.isNewRecord()) {
				service.insertActividad(map);
			} else {
				service.updateActividad(map);

				// Modifica actividad, update en la matriz
				map.put("usuario", usuario.getLogin() + "_DELACTI");
				serviceMatriz.updateMatrizUpdateActividad(map);
			}
		} catch (InvalidDescriptionException ide) {
			throw new Exception(
					this.getResourceMessage("mantenimientoCUPDespachoRegaloPedidoForm.insert.error"));
		}
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Map sistemabusqueda = (Map) this.beanRegistroSeleccionado;
		MantenimientoCRAActividadForm f = new MantenimientoCRAActividadForm();

		f.setCodigoPais(this.mPantallaPrincipalBean.getCountryCode());
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		MantenimientoCRAActividadService service = (MantenimientoCRAActividadService) getBean("spusicc.mantenimientoCRAActividadService");
		this.craClaseList = service.getClaseActividades(criteria);
		this.craTipoActividadList = llenaComboTipoActividad();
		this.craActividadesList = service.getActividades(criteria);
		this.craIndicadorDiasList = llenaComboIndicadorDias();
		setOpcionNombre(false);
		
		if (!this.accion.equals(this.ACCION_NUEVO)) {
			String codigo = sistemabusqueda.get("id").toString();
			String codigoPais = pais.getCodigo();
			setOpcionNombre(true);			

			String idClase = (String) sistemabusqueda.get("idClase");
			String nCodigo = (String) sistemabusqueda.get("codigo");
			String nombre = (String) sistemabusqueda.get("nombre");
			String tipo = (String) sistemabusqueda.get("tipo");
			String cActividad = (String) sistemabusqueda.get("oidActiOrig");
			String indicaDias = (String) sistemabusqueda.get("indicadorDias");
			String diasDespla = (String) sistemabusqueda.get("diasDesplazamiento");
			
			if(tipo.equals("0")){
				setOpcionMostrarActividad(false);
			}else{
				setOpcionMostrarActividad(true);
			}	

			if (codigo != null && codigoPais != null) {
				if (log.isDebugEnabled()) {
					log.debug("Id seleccionado de la lista: " + codigo + " "+ codigoPais);
			}
				f.setId(codigo);
				f.setCodigoPais(codigoPais);
				f.setCodigo(nCodigo);
				f.setClase(idClase);
				f.setNombre(nombre);
				f.setTipo(tipo);
				f.setActividadOrigen(cActividad);
				f.setIndicadorDias(indicaDias);
				f.setDiasDesplazamiento(diasDespla);
				f.setNewRecord(false);
			}
		}

		return f;
	}

	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoCRAActividadForm sistemaForm = (MantenimientoCRAActividadForm) this.formMantenimiento;
		boolean isNew = sistemaForm.isNewRecord();
		if (isNew) {
			return "mantenimientoCRAActividadForm.add.success";
		} else {
			return "mantenimientoCRAActividadForm.modify.success";
		}
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("setViewAttributes");
		
		this.mostrarBotonConsultar=false;

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoCRAActividadSearchForm f = (MantenimientoCRAActividadSearchForm) this.formBusqueda;
		MantenimientoCRAActividadService service = (MantenimientoCRAActividadService) getBean("spusicc.mantenimientoCRAActividadService");

		f.setCodigoPais(pais.getCodigo());

		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("tipo", "");
		criteria.put("nombre", "");

		List list = new ArrayList();
		list = service.getActividades(criteria);
		this.craTipoActividadList = llenaComboTipoActividad();
		this.craActividadesList = list;
		this.opcionMostrarActividad=false;
		this.opcionNombre=false;
		f.setNombre("");
	}

	private List llenaComboTipoActividad() {
		List lista = new ArrayList();
		Base base = new Base();

		base.setCodigo("0");
		base.setDescripcion(Constants.CRA_TPO_ACTIVIDAD_FIJO);

		lista.add(base);
		base = new Base();

		base.setCodigo("1");
		base.setDescripcion(Constants.CRA_TIPO_ACTIVIDAD_CON_ORIGEN);

		lista.add(base);
		return lista;
	}

	private List llenaComboIndicadorDias() {
		List lista = new ArrayList();
		Base base = new Base();

		base.setCodigo("0");
		base.setDescripcion(Constants.CRA_INDICADOR_DIAS_CONTINUOS);

		lista.add(base);
		base = new Base();

		base.setCodigo("1");
		base.setDescripcion(Constants.CRA_INDICADOR_DIAS_LABORABLES);
		lista.add(base);
		return lista;
	}
	
	public void mostrarActividad(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("mostrarActividad");
		}
		String opcion = (String) val.getNewValue().toString();
		if(opcion.equals("0")){
			setOpcionMostrarActividad(false);
		}else{
			setOpcionMostrarActividad(true);
		}		
	}	
	
	public boolean isOpcionNombre() {
		return opcionNombre;
	}

	public void setOpcionNombre(boolean opcionNombre) {
		this.opcionNombre = opcionNombre;
	}

	public boolean isOpcionMostrarActividad() {
		return opcionMostrarActividad;
	}

	public void setOpcionMostrarActividad(boolean opcionMostrarActividad) {
		this.opcionMostrarActividad = opcionMostrarActividad;
	}

	public List getCraTipoActividadList() {
		return craTipoActividadList;
	}

	public void setCraTipoActividadList(List craTipoActividadList) {
		this.craTipoActividadList = craTipoActividadList;
	}

	public List getCraActividadesList() {
		return craActividadesList;
	}

	public void setCraActividadesList(List craActividadesList) {
		this.craActividadesList = craActividadesList;
	}

	public List getCraClaseList() {
		return craClaseList;
	}

	public void setCraClaseList(List craClaseList) {
		this.craClaseList = craClaseList;
	}

	public List getCraIndicadorDiasList() {
		return craIndicadorDiasList;
	}

	public void setCraIndicadorDiasList(List craIndicadorDiasList) {
		this.craIndicadorDiasList = craIndicadorDiasList;
	}
}
