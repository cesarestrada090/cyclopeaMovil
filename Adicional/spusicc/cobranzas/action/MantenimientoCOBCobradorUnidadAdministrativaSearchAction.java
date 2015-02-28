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
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.CobradorUnidadAdministrativa;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ConsultaCOBGenericoService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.MantenimientoCOBGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.MantenimientoCOBCobradorUnidadAdministrativaForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.MantenimientoCOBCobradorUnidadAdministrativaSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class MantenimientoCOBCobradorUnidadAdministrativaSearchAction extends BaseMantenimientoSearchAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9046087508667111219L;
	
	private List siccEtapaDeudaList;
		
	private List siccRegionList;
	private LabelValue[] siccZonaList = {};
	private List cobCobradoresList;

	@Override
	protected String getSalirForward() 
	{
		return "mantenimientoCOBCobradorUnidadAdministrativaList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception 
	{
		return "mantenimientoCOBCobradorUnidadAdministrativaForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception 
	{
		MantenimientoCOBCobradorUnidadAdministrativaSearchForm searchForm = new MantenimientoCOBCobradorUnidadAdministrativaSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			   this.log.debug("JFA Entering: setFindAttributes");
			}
									
		/* obteniendo valores */
		MantenimientoCOBCobradorUnidadAdministrativaSearchForm f = (MantenimientoCOBCobradorUnidadAdministrativaSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		CobradorUnidadAdministrativa bean = new CobradorUnidadAdministrativa();
		bean.setCodigoPais(pais.getCodigo());
		bean.setCodigoEtapaDeuda(f.getCodigoEtapaDeuda());
				
		/* Obteniedo la Lista de Etapas de Deuda */
		ConsultaCOBGenericoService consultaCOBService = (ConsultaCOBGenericoService) this.getBean("spusicc.consultaCOBGenericoService");
		this.siccEtapaDeudaList = consultaCOBService.getEtapasDeuda(); 
				
		MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) 
		this.getBean("spusicc.mantenimientoCOBGenericoService");
		List lista = service.getListaCobradorUnidadAdministrativa(bean);		
				
		if (log.isDebugEnabled()) {
			this.log.debug("JFA Todo Ok: Redireccionando");
		}
		
		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception 
	{
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		HashMap<String, Object> cobradorSeleccionado = (HashMap<String, Object>) this.beanRegistroSeleccionado;
		
		String codigoPais = cobradorSeleccionado.get("codigoPais").toString();
		String etapa = cobradorSeleccionado.get("codigoEtapaDeuda").toString();
		String region = cobradorSeleccionado.get("codigoRegion").toString();
		String zona = cobradorSeleccionado.get("codigoZona").toString();
		String cobrador = cobradorSeleccionado.get("codigoCobrador").toString();

		try {
			MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) getBean("spusicc.mantenimientoCOBGenericoService");

			CobradorUnidadAdministrativa CobradorUnidadAdministrativa = new CobradorUnidadAdministrativa();

			CobradorUnidadAdministrativa.setCodigoPais(codigoPais);
			CobradorUnidadAdministrativa.setCodigoEtapaDeuda(etapa);
			CobradorUnidadAdministrativa.setCodigoRegion(region);
			CobradorUnidadAdministrativa.setCodigoZona(zona);
			CobradorUnidadAdministrativa.setCodigoCobrador(cobrador);

			service.deleteCobradorUnidadAdministrativa(CobradorUnidadAdministrativa, usuario);

			this.getResourceMessage("mantenimientoCOBCobradorUnidadAdministrativaForm.deleted");

		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
			throw new Exception(this.getResourceMessage("errors.detail",new Object[] { error }));
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
		
		MantenimientoCOBCobradorUnidadAdministrativaForm f = (MantenimientoCOBCobradorUnidadAdministrativaForm) this.formMantenimiento;
		
		MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) getBean("spusicc.mantenimientoCOBGenericoService");
							
		CobradorUnidadAdministrativa cobrador = new CobradorUnidadAdministrativa();
		
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
				service.updateCobradorUnidadAdministrativa(cobrador, usuario);
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
				service.insertCobradorUnidadAdministrativa(cobrador, usuario);
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
		MantenimientoCOBCobradorUnidadAdministrativaForm f = new MantenimientoCOBCobradorUnidadAdministrativaForm();

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
		f.setCodigoCobrador("");

		loadCombos();
		/* FIN METODO ADD */

		/* METODO CONSULTAR */
		log.debug("JFA Action: Entering setConsultarAttributes");

		HashMap<String, Object> cobradorSeleccionado = (HashMap<String, Object>) this.beanRegistroSeleccionado;

		if (!this.accion.equals(this.ACCION_NUEVO)) 
		{
			String codigoPais = cobradorSeleccionado.get("codigoPais").toString();
			String etapa = cobradorSeleccionado.get("codigoEtapaDeuda").toString();
			String region = cobradorSeleccionado.get("codigoRegion").toString();
			String zona = cobradorSeleccionado.get("codigoZona").toString();
			String cobrador = cobradorSeleccionado.get("codigoCobrador").toString();

			if (codigoPais != null && etapa != null && region != null && zona != null && cobrador != null) 
			{
				if (log.isDebugEnabled()) {
					log.debug("Id seleccionado de la lista: " + codigoPais + " "+ etapa + " "+ region + " "+ zona + " "+ cobrador);
				}

				MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) getBean("spusicc.mantenimientoCOBGenericoService");

				CobradorUnidadAdministrativa CobradorUnidadAdministrativa = new CobradorUnidadAdministrativa();

				log.debug("JFA - obtenerKeyMensaje");
				CobradorUnidadAdministrativa.setCodigoPais(codigoPais);
				CobradorUnidadAdministrativa.setCodigoEtapaDeuda(etapa);
				CobradorUnidadAdministrativa.setCodigoRegion(region);
				CobradorUnidadAdministrativa.setCodigoZona(zona);
				CobradorUnidadAdministrativa.setCodigoCobrador(cobrador);

				log.debug("JFA - obtener Bean");
				CobradorUnidadAdministrativa = (CobradorUnidadAdministrativa) service.getCobradorUnidadAdministrativa(CobradorUnidadAdministrativa);

				BeanUtils.copyProperties(f, CobradorUnidadAdministrativa);
				f.setCodigoZona(CobradorUnidadAdministrativa.getCodigoZona());
				f.setCodigoZonaSeleccionado(CobradorUnidadAdministrativa.getCodigoZona());
				if(CobradorUnidadAdministrativa.getIndicadorActivo().equalsIgnoreCase("1"))
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
				/* FIN METODO CONSULTAR */
			}
		}

		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			this.log.debug("JFA Entering: setViewAttributes");
		}

		MantenimientoCOBCobradorUnidadAdministrativaSearchForm f = (MantenimientoCOBCobradorUnidadAdministrativaSearchForm) this.formBusqueda;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());

		/* Obteniedo la Lista de Etapas de Deuda */
		ConsultaCOBGenericoService consultaCOBService = (ConsultaCOBGenericoService) this.getBean("spusicc.consultaCOBGenericoService");
		this.siccEtapaDeudaList = consultaCOBService.getEtapasDeuda();

		/* Obteniendo valores */
		CobradorUnidadAdministrativa bean = new CobradorUnidadAdministrativa();
		bean.setCodigoPais(pais.getCodigo());

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
		this.siccEtapaDeudaList =consultaCOBService.getEtapasDeuda();
		
		/* Obteniedo la Lista de Regiones */
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteria = new HashMap();		
		criteria.put("codigoPais", pais.getCodigo());
		
		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais", criteria);		
		/*Obteniendo la lista de zonas*/
		AjaxService ajaxService = (AjaxService)this.getBeanService("ajaxService");

		Base region0 = (Base)getSiccRegionList().get(0);
		
        this.siccZonaList = ajaxService.getZonasByPaisRegion(pais.getCodigo(), region0.getCodigo());
        
        this.cobCobradoresList = consultaCOBService.getCobradoresActivos();        
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

	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoCOBCobradorUnidadAdministrativaForm cobradorForm = (MantenimientoCOBCobradorUnidadAdministrativaForm) this.formMantenimiento;
		boolean isNew = cobradorForm.isNewRecord();
		if(isNew){
			return "mantenimientoCOBCobradorUnidadAdministrativaForm.add";
		}else{
			return "mantenimientoCOBCobradorUnidadAdministrativaForm.updated";
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

	public List getCobCobradoresList() {
		return cobCobradoresList;
	}

	public void setCobCobradoresList(List cobCobradoresList) {
		this.cobCobradoresList = cobCobradoresList;
	}
}
