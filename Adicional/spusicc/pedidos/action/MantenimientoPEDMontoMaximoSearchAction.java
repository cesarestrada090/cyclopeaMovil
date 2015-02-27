package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.CarParamCarte;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDMontoMaximoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDMontoMaximoForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDMontoMaximoSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class MantenimientoPEDMontoMaximoSearchAction extends BaseMantenimientoSearchAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8970712279595414897L;
	
	private List siccNivelRiesgoList;
	private List pedMontoMaximoList;
	private List siccSubGerenciaList;
	private List siccRegionList;
	private LabelValue[] siccZonaList = {};
	private LabelValue[] siccSeccionList = {};
	
	@Override
	protected String getSalirForward() 
	{
		return "mantenimientoPEDMontoMaximoSearchForm";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception 
	{
		return "mantenimientoPEDMontoMaximoForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception 
	{
		MantenimientoPEDMontoMaximoSearchForm search = new MantenimientoPEDMontoMaximoSearchForm();
		return search;
	}

	@Override
	protected List setFindAttributes() throws Exception 
	{
		MantenimientoPEDMontoMaximoSearchForm f = (MantenimientoPEDMontoMaximoSearchForm) this.formBusqueda;
		MantenimientoPEDMontoMaximoService service = (MantenimientoPEDMontoMaximoService) getBean("spusicc.mantenimientoPEDMontoMaximoService");

		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoNivelRiesgo", f.getCodigoNivelRiesgo());
		/*criteria.put("listRegion", (f.getCodigoRegion() == null) ? new ArrayList() : Arrays.asList(f.getCodigoRegion()));
		criteria.put("listZona", (f.getCodigoZona() == null) ? new ArrayList() : Arrays.asList(f.getCodigoZona()));
		criteria.put("listSeccion", (f.getCodigoSeccion() == null) ? new ArrayList() : Arrays.asList(f.getCodigoSeccion()));*/
		List lista = service.getMontoMaximoList(criteria);
		
		CarParamCarte[] carParamCarteForm = new CarParamCarte[lista.size()];
		
		for (int i = 0; i < lista.size(); i++) {
        	Map carte = (Map)lista.get(i);
        	carParamCarteForm[i] = new CarParamCarte();
        	carParamCarteForm[i].setOidParaCart(MapUtils.getString(carte, "oidParaCart"));
        	carParamCarteForm[i].setNiriOidNiveRies(MapUtils.getString(carte, "niriOidNiveRies"));
        	carParamCarteForm[i].setDesNivelRiesgo(MapUtils.getString(carte, "valI18n"));
        	carParamCarteForm[i].setValMontMaxiPerm(MapUtils.getString(carte, "valMontMaxiPerm"));
        	carParamCarteForm[i].setIndMontMaxi(MapUtils.getString(carte, "indMontoMaximo"));
        }
		
		f.setCarParamCarteForm(carParamCarteForm);
		
		this.pedMontoMaximoList = lista;

		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception 
	{
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map registroSeleccionado = (Map)this.beanRegistroSeleccionado;
		MantenimientoPEDMontoMaximoService service = (MantenimientoPEDMontoMaximoService) getBean("spusicc.mantenimientoPEDMontoMaximoService");
		
		try{
			String oidParaCart = registroSeleccionado.get("oidParaCart").toString();
			String niriOidNiveRies = registroSeleccionado.get("niriOidNiveRies").toString();
			String valI18n = registroSeleccionado.get("valI18n").toString();
			String valMontMaxiPerm = registroSeleccionado.get("valMontMaxiPerm").toString();
			String indMontoMaximo = registroSeleccionado.get("indMontoMaximo").toString();
			
			CarParamCarte carParamCarte = new CarParamCarte();
			if(oidParaCart != null && niriOidNiveRies != null && valI18n != null && valMontMaxiPerm != null && indMontoMaximo != null)
			{
				carParamCarte.setOidParaCart(oidParaCart);
				carParamCarte.setNiriOidNiveRies(niriOidNiveRies);
				carParamCarte.setDesNivelRiesgo(valI18n);
				carParamCarte.setValMontMaxiPerm(valMontMaxiPerm);
				carParamCarte.setIndMontMaxi(indMontoMaximo);
				carParamCarte.setSelected(true);
				
				Map criteria = BeanUtils.describe(carParamCarte);
            	criteria.put("usuario", usuario.getLogin());
            	
            	service.deleteCarAsignCodigConfi(criteria);
        		service.deleteCarParamCarte(criteria);
        		
        		this.getResourceMessage("mantenimientoPEDMontoMaximoSearchForm.cabecera.deleted");
			}			
		}catch(Exception e)
		{
			String error = e.getMessage();
			if (StringUtils.isBlank(error))	error = e.getLocalizedMessage();
			throw new Exception(this.getResourceMessage("errors.detail", new Object[] { error }));			
		}
		
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception 
	{
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		MantenimientoPEDMontoMaximoForm f = (MantenimientoPEDMontoMaximoForm) this.formMantenimiento;
		MantenimientoPEDMontoMaximoService service = (MantenimientoPEDMontoMaximoService) getBean("spusicc.mantenimientoPEDMontoMaximoService");
		
		Map criteriaCount = new HashMap();
		criteriaCount.put("codigoPais", f.getCodigoPais());
		criteriaCount.put("codigoNivelRiesgo", Integer.parseInt(f.getCodigoNivelRiesgo()));
		/*criteriaCount.put("listRegion", (f.getCodigoRegion() == null) ? new ArrayList() : Arrays.asList(f.getCodigoRegion()));
		criteriaCount.put("listZona", (f.getCodigoZona() == null) ? new ArrayList() : Arrays.asList(f.getCodigoZona()));
		criteriaCount.put("listSeccion", (f.getCodigoSeccion() == null) ? new ArrayList() : Arrays.asList(f.getCodigoSeccion()));*/

		String countExits = service.getCountExisteMontoMaximo(criteriaCount);
		
		if (countExits.equals(Constants.NUMERO_CERO)) {
			Map criteria = new HashMap();
			criteria.put("codigoNivelRiesgo", Integer.parseInt(f.getCodigoNivelRiesgo()));
			criteria.put("listRegion", (f.getCodigoRegion() == null) ? new ArrayList() : Arrays.asList(f.getCodigoRegion()));
			criteria.put("listZona", (f.getCodigoZona() == null) ? new ArrayList() : Arrays.asList(f.getCodigoZona()));
			criteria.put("listSeccion", (f.getCodigoSeccion() == null) ? new ArrayList() : Arrays.asList(f.getCodigoSeccion()));
			criteria.put("codigoPais", f.getCodigoPais());
			criteria.put("montoMaximo", f.getMontoMaximo());
			criteria.put("usuario", usuario.getLogin());

			criteria = service.insertCarParamCarte(criteria);
			
			service.insertCarAsignCodigConfi(criteria);

//			this.addInfo("", this.getResourceMessage("mantenimientoPEDMontoMaximoForm.cabecera.insert"));
			
			return true;
		} else {
			this.addError("", this.getResourceMessage("mantenimientoPEDMontoMaximoForm.countExisteMontoMaximo"));
			return false;
		}
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception 
	{
		this.mostrarBotonSave = true;
		
		MantenimientoPEDMontoMaximoForm f = new MantenimientoPEDMontoMaximoForm();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		f.setCodigoPais(pais.getCodigo());
		f.setCodigoNivelRiesgo(null);
		f.setCodigoRegion(null);
		f.setCodigoZona(null);
		f.setCodigoSeccion(null);
		f.setMontoMaximo(Constants.NUMERO_CERO);
		
		MantenimientoPEDMontoMaximoService service = (MantenimientoPEDMontoMaximoService) getBean("spusicc.mantenimientoPEDMontoMaximoService");

		this.siccNivelRiesgoList = service.getNivelRiesgo();

		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		this.mostrarBotonConsultar = false;
		this.mostrarBotonModificar = false;
		
		MantenimientoPEDMontoMaximoSearchForm f = (MantenimientoPEDMontoMaximoSearchForm) this.formBusqueda;

		f.setCodigoNivelRiesgo("");
		f.setMontoMaximo(Constants.NUMERO_CERO);
		f.setCarParamCarteForm(null);

		MantenimientoPEDMontoMaximoService service = (MantenimientoPEDMontoMaximoService) getBean("spusicc.mantenimientoPEDMontoMaximoService");

		this.siccNivelRiesgoList = service.getNivelRiesgo();
//		request.getSession(true).removeAttribute(Constants.PED_MONTO_MAXIMO_LIST);
	}
	
	// metodo que realiza el cambio en el indicador activo
	public void modificarIndMontoMaximo(ActionEvent event) 
	{
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		try {
			Map registroSeleccionado = (Map)this.beanRegistroSeleccionado;
			MantenimientoPEDMontoMaximoService service = (MantenimientoPEDMontoMaximoService) getBean("spusicc.mantenimientoPEDMontoMaximoService");

			// obtiene el valor del parametro
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			String accion = externalContext.getRequestParameterMap().get("parametroAccion");
			
			String oidParaCart = registroSeleccionado.get("oidParaCart").toString();
			String niriOidNiveRies = registroSeleccionado.get("niriOidNiveRies").toString();
			String valI18n = registroSeleccionado.get("valI18n").toString();
			String valMontMaxiPerm = registroSeleccionado.get("valMontMaxiPerm").toString();
			String indMontoMaximo = registroSeleccionado.get("indMontoMaximo").toString();
			
			CarParamCarte carParamCarte = new CarParamCarte();
			if(oidParaCart != null && niriOidNiveRies != null && valI18n != null && valMontMaxiPerm != null && indMontoMaximo != null)
			{
				carParamCarte.setOidParaCart(oidParaCart);
				carParamCarte.setNiriOidNiveRies(niriOidNiveRies);
				carParamCarte.setDesNivelRiesgo(valI18n);
				carParamCarte.setValMontMaxiPerm(valMontMaxiPerm);
				carParamCarte.setIndMontMaxi(indMontoMaximo);
				carParamCarte.setSelected(true);
				
				Map criteria = BeanUtils.describe(carParamCarte);
				criteria.put("usuario", usuario.getLogin());
				criteria.put("accion", accion);

				service.updateIndMontMaxi(criteria);
				
				this.addInfo("", this.getResourceMessage("mantenimientoPEDMontoMaximoSearchForm.cabecera.update"));
				
				this.listaBusqueda = this.setFindAttributes();
				this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			}
			
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
			this.addError("", this.getResourceMessage("errors.detail", new Object[]{error}));
		}
	}
	
	// metodo que actualiza en registro seleccionado
	public void modificarRegistro(ActionEvent event)
	{		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		try {
			Map registroSeleccionado = (Map)this.beanRegistroSeleccionado;
			MantenimientoPEDMontoMaximoService service = (MantenimientoPEDMontoMaximoService) getBean("spusicc.mantenimientoPEDMontoMaximoService");
			
			String oidParaCart = registroSeleccionado.get("oidParaCart").toString();
			String niriOidNiveRies = registroSeleccionado.get("niriOidNiveRies").toString();
			String valI18n = registroSeleccionado.get("valI18n").toString();
			String valMontMaxiPerm = registroSeleccionado.get("valMontMaxiPerm").toString();
			String indMontoMaximo = registroSeleccionado.get("indMontoMaximo").toString();
			
			CarParamCarte carParamCarte = new CarParamCarte();
			if(oidParaCart != null && niriOidNiveRies != null && valI18n != null && valMontMaxiPerm != null && indMontoMaximo != null)
			{
				carParamCarte.setOidParaCart(oidParaCart);
				carParamCarte.setNiriOidNiveRies(niriOidNiveRies);
				carParamCarte.setDesNivelRiesgo(valI18n);
				carParamCarte.setValMontMaxiPerm(valMontMaxiPerm);
				carParamCarte.setIndMontMaxi(indMontoMaximo);
				carParamCarte.setSelected(true);
				
            	Map criteria = BeanUtils.describe(carParamCarte);
            	criteria.put("usuario", usuario.getLogin());
            	
            	service.updateRegMontMaxi(criteria);
            	
            	this.addInfo("", this.getResourceMessage("mantenimientoPEDMontoMaximoSearchForm.montoMaximo.update"));
            	
            	this.listaBusqueda = this.setFindAttributes();
    			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			}				
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
			this.addError("", this.getResourceMessage("errors.detail", new Object[]{error}));
		}			
	}
		
	//metodo que inicializa el popup
	public void definirUA(ActionEvent event) throws IOException
	{
		if(log.isDebugEnabled()){
			log.debug("Entering my method 'definirUA'");
		}

		this.mostrarBotonSave = false;
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoPais = pais.getCodigo();
		
		MantenimientoPEDMontoMaximoSearchForm f = (MantenimientoPEDMontoMaximoSearchForm) this.formBusqueda;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoPEDMontoMaximoService service = (MantenimientoPEDMontoMaximoService) getBean("spusicc.mantenimientoPEDMontoMaximoService");
		InterfazSiCCService serviceSiCC = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		
		Map registroSeleccionado = (Map)this.beanRegistroSeleccionado;
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", codigoPais);
		criteriaOperacion.put("indicadorActivo", null);
		criteriaOperacion.put("indicadorBorrado", "0");
		criteriaOperacion.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteriaOperacion.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		
		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion);

		this.siccSubGerenciaList = reporteService.getListaGenerico("getSubGerenciasByPaisMarcaCanal", criteriaOperacion);
		
		try {
			String oidParaCart = registroSeleccionado.get("oidParaCart").toString();
			String niriOidNiveRies = registroSeleccionado.get("niriOidNiveRies").toString();
			String valI18n = registroSeleccionado.get("valI18n").toString();
			String valMontMaxiPerm = registroSeleccionado.get("valMontMaxiPerm").toString();
			String indMontoMaximo = registroSeleccionado.get("indMontoMaximo").toString();

			if (StringUtils.isNotBlank(oidParaCart)) {
				// Map criteria = BeanUtils.describe(arg);
				Map criteria = new HashMap();
				criteria.put("usuario", usuario.getLogin());
				criteria.put("oidParaCart", oidParaCart);

				List listCarParamCarte = service.getCarParamCarte(criteria);
				if (!listCarParamCarte.isEmpty()) {
					CarParamCarte paramCarte = (CarParamCarte) listCarParamCarte
							.get(0);

					if (paramCarte != null) {
						f.setOidParaCart(paramCarte.getOidParaCart());
						f.setValI18n(paramCarte.getDesNivelRiesgo());
						f.setValMontMaxiPerm(paramCarte.getValMontMaxiPerm());
						f.setIndMontoMaximo(paramCarte.getIndMontMaxi());
					}
				}

				List listRegiones = service.getCodigoRegionUA(criteria);
				if (listRegiones != null) {
					String[] regiones = new String[listRegiones.size()];

					for (int i = 0; i < listRegiones.size(); i++) {
						regiones[i] = MapUtils.getString((Map) listRegiones.get(i), "codigoRegion");
					}

					f.setCodigoRegion(regiones);
				}

				List listZonas = service.getCodigoZonaUA(criteria);
				if (listZonas != null) {
					String[] zonas = new String[listZonas.size()];

					for (int i = 0; i < listZonas.size(); i++) {
						zonas[i] = MapUtils.getString((Map) listZonas.get(i),
								"codigoZona");
					}

					f.setCodigoZona(zonas);
				}

				List listSecciones = service.getCodigoSeccionUA(criteria);
				if (listSecciones != null) {
					String[] secciones = new String[listSecciones.size()];

					for (int i = 0; i < listSecciones.size(); i++) {
						secciones[i] = MapUtils.getString((Map) listSecciones.get(i), "codigoSeccion");
					}

					f.setCodigoSeccion(secciones);
				}

				criteriaOperacion.put("codigoRegion", f.getCodigoRegion());
				criteriaOperacion.put("codigoZona", f.getCodigoZona());
				List listaZonas = serviceSiCC.getLista("getZonasMultipleByPaisMarcaCanalRegion", criteriaOperacion);
				
				this.siccZonaList = new LabelValue[listaZonas.size()];
				int i = 0;
				for (Object zona : listaZonas) {
					LabelValue labelValue = new LabelValue();
					labelValue.setLabel(((Base) zona).getDescripcion());
					labelValue.setValue(((Base) zona).getCodigo());
					this.getSiccZonaList()[i] = labelValue;
					i++;
				}
							
				List listaSecciones = serviceSiCC.getLista("getCompuestoSeccionMultipleByPaisMarcaCanalRegionZona1", criteriaOperacion);
				
				this.siccSeccionList = new LabelValue[listaSecciones.size()];
				int j = 0;
				for (Object seccion : listaSecciones) {
					LabelValue labelValue = new LabelValue();
					labelValue.setLabel(((Base) seccion).getDescripcion());
					labelValue.setValue(((Base) seccion).getCodigo());
					this.getSiccSeccionList()[j] = labelValue;
					j++;
				}
			}
		} catch (Exception e) 
		{
			String error = e.getMessage();
			if (StringUtils.isBlank(error))	error = e.getLocalizedMessage();
			try {
				throw new Exception(this.getResourceMessage("errors.detail", new Object[] { error }));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

//		this.redireccionarPagina("mantenimientoPEDMontoMaximoDefinirUAPopup");	
		String ventana = "PF('dialogMantenimientoForm2').show()";
		this.getRequestContext().execute(ventana);
		this.mostrarBotonSalir = false;
		return;
	}
	
	//metodo que guarda los valores del popup
	public void guardarUA(ActionEvent event)
	{
		if(log.isDebugEnabled()){
			log.debug("Entering my method 'guardarUA'");
		}
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoPais = pais.getCodigo();
		
		MantenimientoPEDMontoMaximoSearchForm f = (MantenimientoPEDMontoMaximoSearchForm) this.formBusqueda;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoPEDMontoMaximoService service = (MantenimientoPEDMontoMaximoService) getBean("spusicc.mantenimientoPEDMontoMaximoService");
		
		Map criteriaDel = new HashMap();
		criteriaDel.put("usuario", usuario.getLogin());
		criteriaDel.put("oidParaCart", f.getOidParaCart());
		
		service.deleteCarAsignCodigConfi(criteriaDel);
		
		/*Map criteriaCount = new HashMap();
		criteriaCount.put("codigoPais", f.getCodigoPais());
		criteriaCount.put("codigoNivelRiesgo", Integer.parseInt(f.getCodigoNivelRiesgo()));
		criteriaCount.put("listRegion", (f.getCodigoRegion() == null) ? new ArrayList() : Arrays.asList(f.getCodigoRegion()));
		criteriaCount.put("listZona", (f.getCodigoZona() == null) ? new ArrayList() : Arrays.asList(f.getCodigoZona()));
		criteriaCount.put("listSeccion", (f.getCodigoSeccion() == null) ? new ArrayList() : Arrays.asList(f.getCodigoSeccion()));*/
		
		Map criteria = new HashMap();
		criteria.put("codigoNivelRiesgo", Integer.parseInt(f.getCodigoNivelRiesgo()));
		criteria.put("listRegion", (f.getCodigoRegion() == null) ? new ArrayList() : Arrays.asList(f.getCodigoRegion()));
		criteria.put("listZona", (f.getCodigoZona() == null) ? new ArrayList() : Arrays.asList(f.getCodigoZona()));
		criteria.put("listSeccion", (f.getCodigoSeccion() == null) ? new ArrayList() : Arrays.asList(f.getCodigoSeccion()));
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("montoMaximo", f.getMontoMaximo());
		criteria.put("usuario", usuario.getLogin());

		//criteria = service.insertCarParamCarte(criteria);
		
		criteria.put("oidCarpPaca", f.getOidParaCart());
		service.insertCarAsignCodigConfi(criteria);

		this.addInfo("", this.getResourceMessage("mantenimientoPEDMontoMaximoForm.cabecera.insert"));
		this.mostrarBotonSalir = true;
	}
	
	//metodo que sale del popup
	public void salirUA(ActionEvent event)
	{
		if(log.isDebugEnabled()){
			log.debug("Entering my method 'salirUA'");
		}
		String ventana = "PF('dialogMantenimientoForm2').hide()";
		this.getRequestContext().execute(ventana);
		this.mostrarBotonSalir = true;
	}
	
	public void loadZonas(ValueChangeEvent event)
	{
		String[] regiones = (String[]) event.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		MantenimientoPEDMontoMaximoSearchForm f = (MantenimientoPEDMontoMaximoSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		int salir = 1;
		
		if(regiones.length > 0)
		{
			for (String region : regiones) 
			{
				if(region != null)
				{
					salir = 0;					
				}				
			}			
		}
		
		if(salir == 0)
			this.siccZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(f.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, 
					Constants.CODIGO_CANAL_DEFAULT, regiones, "");
	}
	
	public void loadSeccion(ValueChangeEvent event)
	{
		String[] zonas = (String[]) event.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		MantenimientoPEDMontoMaximoSearchForm f = (MantenimientoPEDMontoMaximoSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		int salir = 1;
		ArrayList zonasValue = new ArrayList();
		ArrayList regionesValue = new ArrayList();
		
		for (String region : f.getCodigoRegion()) {
			regionesValue.add(region);
		}
		
		if(zonas.length > 0)
		{
			for (String zona : zonas) 
			{
				salir = 0;		
				zonasValue.add(zona);			
			}			
		}
		
		if(salir == 0)
			this.siccSeccionList = ajax.getCompuestoSeccionMultipleByPaisMarcaCanalRegionZona1(f.getCodigoPais(), 
					Constants. CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, regionesValue, zonasValue, "");
	}
	
	public List getSiccNivelRiesgoList() {
		return siccNivelRiesgoList;
	}

	public void setSiccNivelRiesgoList(List siccNivelRiesgoList) {
		this.siccNivelRiesgoList = siccNivelRiesgoList;
	}

	public List getPedMontoMaximoList() {
		return pedMontoMaximoList;
	}

	public void setPedMontoMaximoList(List pedMontoMaximoList) {
		this.pedMontoMaximoList = pedMontoMaximoList;
	}

	public List getSiccSubGerenciaList() {
		return siccSubGerenciaList;
	}

	public void setSiccSubGerenciaList(List siccSubGerenciaList) {
		this.siccSubGerenciaList = siccSubGerenciaList;
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public LabelValue[] getSiccSeccionList() {
		return siccSeccionList;
	}

	public void setSiccSeccionList(LabelValue[] siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
	}
}
