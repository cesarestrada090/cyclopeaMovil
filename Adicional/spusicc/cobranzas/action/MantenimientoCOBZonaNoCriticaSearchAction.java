package biz.belcorp.ssicc.web.spusicc.cobranzas.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.ZonaNoCritica;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ConsultaCOBGenericoService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.MantenimientoCOBGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.MantenimientoCOBCobradorUnidadAdministrativaForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.MantenimientoCOBZonaNoCriticaForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.MantenimientoCOBZonaNoCriticaSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class MantenimientoCOBZonaNoCriticaSearchAction extends BaseMantenimientoSearchAbstractAction 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6561568425150251264L;
	
	private List siccEtapaDeudaList;
	private List siccRegionList;
	private LabelValue[] siccZonaList = {};

	@Override
	protected String getSalirForward() 
	{
		return "mantenimientoCOBZonaNoCriticaList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception 
	{
		return "mantenimientoCOBZonaNoCriticaForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception 
	{
		MantenimientoCOBZonaNoCriticaSearchForm searchForm = new MantenimientoCOBZonaNoCriticaSearchForm();
		return searchForm;
	}


	@Override
	protected List setFindAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			this.log.debug("JFA Entering: setFindAttributes");
		}

		/* obteniendo valores */
		MantenimientoCOBZonaNoCriticaSearchForm f = (MantenimientoCOBZonaNoCriticaSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		ZonaNoCritica bean = new ZonaNoCritica();
		bean.setCodigoPais(pais.getCodigo());
		bean.setCodigoEtapaDeuda(f.getCodigoEtapaDeuda());

		/* Obteniedo la Lista de Etapas de Deuda */
		ConsultaCOBGenericoService consultaCOBService = (ConsultaCOBGenericoService) this.getBean("spusicc.consultaCOBGenericoService");
		this.siccEtapaDeudaList = consultaCOBService.getEtapasDeuda();

		MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) this.getBean("spusicc.mantenimientoCOBGenericoService");
		List lista = service.getListaZonaNoCritica(bean);

		if (log.isDebugEnabled()) {
			this.log.debug("JFA Todo Ok: Redireccionando");
		}

		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception 
	{
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		// String id = request.getParameter("id");

		HashMap<String, Object> zonaSeleccionado = (HashMap<String, Object>) this.beanRegistroSeleccionado;
		try {
			MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) getBean("spusicc.mantenimientoCOBGenericoService");

			ZonaNoCritica ZonaNoCritica = new ZonaNoCritica();
			ZonaNoCritica.setCodigoPais(zonaSeleccionado.get("codigoPais").toString());
			ZonaNoCritica.setCodigoEtapaDeuda(zonaSeleccionado.get("codigoEtapaDeuda").toString());
			ZonaNoCritica.setCodigoRegion(zonaSeleccionado.get("codigoRegion").toString());
			ZonaNoCritica.setCodigoZona(zonaSeleccionado.get("codigoZona").toString());

			service.deleteZonaNoCritica(ZonaNoCritica, usuario);

			this.getResourceMessage("mantenimientoCOBZonaNoCriticaForm.deleted");

		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error))
				error = e.getLocalizedMessage();
			throw new Exception(this.getResourceMessage("errors.detail",
					new Object[] { error }));
		}

		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'setSaveAttributes' method");
		}

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		MantenimientoCOBZonaNoCriticaForm f = (MantenimientoCOBZonaNoCriticaForm) this.formMantenimiento;

		MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) getBean("spusicc.mantenimientoCOBGenericoService");

		ZonaNoCritica cobrador = new ZonaNoCritica();

		try {
			BeanUtils.copyProperties(cobrador, f);

			if (!f.isNewRecord()) {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA MODIFICACION");
				}
				
				if(!f.isIndicadorActivoBool()) 
				{
					cobrador.setIndicadorActivo("0");
					f.setIndicadorActivo("0");
				}else
				{
					cobrador.setIndicadorActivo("1");
					f.setIndicadorActivo("1");
				}
				
				service.updateZonaNoCritica(cobrador, usuario);
			} else {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA CREACION");
				}
				
				if(!f.isIndicadorActivoBool()) 
				{
					cobrador.setIndicadorActivo("0");
					f.setIndicadorActivo("0");
				}else
				{
					cobrador.setIndicadorActivo("1");
					f.setIndicadorActivo("1");
				}
				
				service.insertZonaNoCritica(cobrador, usuario);
			}
		} catch (Exception e) {
			throw new Exception(this.getResourceMessage(e.getMessage()));
		}
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception 
	{
		/* METODO ADD */
		log.debug("JFA Action: Entering setAddAttributes");

		MantenimientoCOBZonaNoCriticaForm f = new MantenimientoCOBZonaNoCriticaForm();

		/* METODO RESET */
		f.setIndicadorActivo("0");
		f.setIndicadorActivoBool(false); 
		/* FIN METODO RESET */

		// Seteando Codigo de Pais
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		f.setObservaciones("");
		f.setCodigoRegion("");
		f.setCodigoZona("");

		loadCombos();
		/* FIN METODO ADD */

		/* METODO CONSULTAR */
		log.debug("JFA Action: Entering setConsultarAttributes");

		HashMap<String, Object> zonaSeleccionado = (HashMap<String, Object>) this.beanRegistroSeleccionado;

		if (!this.accion.equals(this.ACCION_NUEVO))
		{
			String codigoPais = zonaSeleccionado.get("codigoPais").toString();
			String etapa = zonaSeleccionado.get("codigoEtapaDeuda").toString();
			String region = zonaSeleccionado.get("codigoRegion").toString();
			String zona = zonaSeleccionado.get("codigoZona").toString();

			if (codigoPais != null && etapa != null && region != null
					&& zona != null) 
			{
				MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) getBean("spusicc.mantenimientoCOBGenericoService");

				ZonaNoCritica ZonaNoCritica = new ZonaNoCritica();
				ZonaNoCritica.setCodigoPais(codigoPais);
				ZonaNoCritica.setCodigoEtapaDeuda(etapa);
				ZonaNoCritica.setCodigoRegion(region);
				ZonaNoCritica.setCodigoZona(zona);

				log.debug("JFA - obtener Bean");
				ZonaNoCritica = (ZonaNoCritica) service
						.getZonaNoCritica(ZonaNoCritica);

				BeanUtils.copyProperties(f, ZonaNoCritica);
				f.setCodigoZona(ZonaNoCritica.getCodigoZona());
				f.setCodigoZonaSeleccionado(ZonaNoCritica.getCodigoZona());
				if(ZonaNoCritica.getIndicadorActivo().equalsIgnoreCase("1"))
				{
					f.setIndicadorActivoBool(true);
					f.setIndicadorActivo("1");
					
				}else
				{
					f.setIndicadorActivoBool(false);
					f.setIndicadorActivo("0");
				}
				
				f.setNewRecord(false);

				log.debug("JFA - Todo OK");
			}
		}
		/* METODO CONSULTAR */

		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			this.log.debug("JFA Entering: setViewAttributes");
		}

		MantenimientoCOBZonaNoCriticaSearchForm f = (MantenimientoCOBZonaNoCriticaSearchForm) this.formBusqueda;

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());

		/* Obteniedo la Lista de Etapas de Deuda */
		ConsultaCOBGenericoService consultaCOBService = (ConsultaCOBGenericoService) this.getBean("spusicc.consultaCOBGenericoService");
		this.siccEtapaDeudaList = consultaCOBService.getEtapasDeuda();

		/* Obteniedo la Lista de Regiones */
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());

		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais", criteria);

		/* Obteniendo valores */
		ZonaNoCritica bean = new ZonaNoCritica();
		bean.setCodigoPais(pais.getCodigo());

		/* Obteniendo Lista */
//		MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) this.getBean("spusicc.mantenimientoCOBGenericoService");
		

		if (log.isDebugEnabled()) {
			this.log.debug("JFA Todo Ok: Redireccionando");
		}
	}
	
	/**
	 * @param 
	 * @throws Exception
	 */
	private void loadCombos()throws Exception 
	{
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();	
		
		/* Obteniedo la Lista de Etapas de Deuda */
		ConsultaCOBGenericoService consultaCOBService = (ConsultaCOBGenericoService) this.getBean("spusicc.consultaCOBGenericoService");
		this.siccEtapaDeudaList = consultaCOBService.getEtapasDeuda();
		
		/* Obteniedo la Lista de Regiones */
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteria = new HashMap();		
		criteria.put("codigoPais", pais.getCodigo());
		
		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais", criteria);
		
		/*Obteniendo la lista de zonas*/
		AjaxService ajaxService = (AjaxService)this.getBeanService("ajaxService");

		Base region0 = (Base)getSiccRegionList().get(0);		
        this.siccZonaList = ajaxService.getZonasByPaisRegion(pais.getCodigo(), region0.getCodigo());        
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK()
	{
		MantenimientoCOBZonaNoCriticaForm zonaForm = (MantenimientoCOBZonaNoCriticaForm) this.formMantenimiento;
		boolean isNew = zonaForm.isNewRecord();
		if(isNew){
			return "mantenimientoCOBZonaNoCriticaForm.add";
		}else{
			return "mantenimientoCOBZonaNoCriticaForm.updated";
		}	
	}
	
	public void loadZonas(ValueChangeEvent value)
	{
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		String valor = (String) value.getNewValue();
		if (valor.trim().length() > 0) {
			MantenimientoCOBCobradorUnidadAdministrativaForm mantenimientoForm = (MantenimientoCOBCobradorUnidadAdministrativaForm) this.formMantenimiento;
			AjaxService ajaxService = (AjaxService)this.getBeanService("ajaxService");
			this.siccZonaList = ajaxService.getZonasByPaisRegion(mantenimientoForm.getCodigoPais(), valor);
		}else
		{
			this.siccZonaList = null;
		}
	}

	public List getSiccEtapaDeudaList() {
		return siccEtapaDeudaList;
	}

	public void setSiccEtapaDeudaList(List siccEtapaDeudaList) {
		this.siccEtapaDeudaList = siccEtapaDeudaList;
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
	

}
