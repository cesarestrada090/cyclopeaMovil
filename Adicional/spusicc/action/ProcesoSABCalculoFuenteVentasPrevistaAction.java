package biz.belcorp.ssicc.web.spusicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.FuenteVentasService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.form.ProcesoSABCalculoFuenteVentasPrevistaForm;

@ManagedBean  
@SessionScoped
public class ProcesoSABCalculoFuenteVentasPrevistaAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = 6844613724569558929L;
	private List siccSociedadList;
	private List siccAlmacenList;
	private List siccMarcaList;
	private List siccCanalList;
	private List siccRangoPeriodoList;
	private List siccRegionList;
	private List siccZonaList;
	private LabelValue[] siccPeriodoInicialList;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoSABCalculoFuenteVentasPrevistaForm form =  new ProcesoSABCalculoFuenteVentasPrevistaForm();
		return form;
	}

	public void cargarCanalDate(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("Load Canal");
		}
		ProcesoSABCalculoFuenteVentasPrevistaForm form = (ProcesoSABCalculoFuenteVentasPrevistaForm) this.formProceso;

		String canal = (String) val.getNewValue();
		if (canal.equals(Constants.CODIGO_CANAL_DEFAULT)) {

			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			this.siccPeriodoInicialList = aSvc.getListaPeriodosPaisCanalAnioRango(
					pais.getCodigo(), form.getCodigoCanal(), 
					form.getCodigoAnio(), form.getCodigoPeriodoInicial());
		} else {
			this.siccPeriodoInicialList = null;
		}
	}
	
	
	public void cargarRangoPeriodo(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("Load Canal");
		}
		ProcesoSABCalculoFuenteVentasPrevistaForm form = (ProcesoSABCalculoFuenteVentasPrevistaForm) this.formProceso;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String rangoPeriodo = (String) val.getNewValue();
		if (form.getCodigoCanal().equals(Constants.CODIGO_CANAL_DEFAULT)) {

			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			
			this.siccPeriodoInicialList = aSvc.getListaPeriodosPaisCanalAnioRango(
					pais.getCodigo(), form.getCodigoCanal(), 
					form.getCodigoAnio(), rangoPeriodo);
		} else {
			this.siccPeriodoInicialList = null;
		}
	}
	
	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'process' method");
		}
		Usuario usuario  = this.mPantallaPrincipalBean.getCurrentUser();
		
		ProcesoSABCalculoFuenteVentasPrevistaForm calculoFVPSABForm = (ProcesoSABCalculoFuenteVentasPrevistaForm) this.formProceso;

		// Obtenemos las propiedades del bean como un 'Map'
		Map criteria = BeanUtils.describe(calculoFVPSABForm);

		FuenteVentasService service = (FuenteVentasService) getBean("sisicc.fuenteVentasService");

		ActionMessages messages = new ActionMessages();

		/*String flag = service.getPeriodoAbierto(calculoFVPSABForm
				.getCodigoPeriodoInicial());*/

        String flag = "N";
        
		if (flag.equalsIgnoreCase("N")) {
			// Obtenemmos el periodo Inicio y Fin
			String periodoInicio = service.getPeriodoInicio(calculoFVPSABForm
					.getCodigoRangoPeriodo());
			String periodoFin = service.getPeriodoFin(calculoFVPSABForm
					.getCodigoRangoPeriodo());
			String periodoMenor = calculoFVPSABForm.getCodigoAnio()
					+ periodoInicio;
			String periodoMayor = calculoFVPSABForm.getCodigoAnio()
					+ periodoFin;

			criteria.put("periodoInicio", periodoInicio);
			criteria.put("periodoFin", periodoFin);
			criteria.put("periodoInicialRango", periodoMenor);
			criteria.put("periodoFinalRango", periodoMayor);

			// Realizamos el procesos con los criterios ingresados
			service.executeCalculoFuenteVentasPrevista(criteria, usuario);

			int resultZona = service
					.executeCalculoFuenteZona(criteria, usuario);
			int resultRegion = service.executeCalculoFuenteRegion(criteria,
					usuario);
			int resultPais = service
					.executeCalculoFuentePais(criteria, usuario);
			// int resultRegion = 0;
			// int resultPais = 0;

			if ((resultZona == 0) || (resultRegion == 0) || (resultPais == 0)) { // messages.add(ActionMessages.GLOBAL_MESSAGE,
				// new
				// ActionMessage("fuenteVentasPrevista.executedError"));
				if (resultZona == 0)
					messages.add(ActionMessages.GLOBAL_MESSAGE,
							new ActionMessage(
									"fuenteVentasPrevista.executedErrorZona"));
				if (resultRegion == 0)
					messages
							.add(
									ActionMessages.GLOBAL_MESSAGE,
									new ActionMessage(
											"fuenteVentasPrevista.executedErrorRegion"));
				if (resultPais == 0)
					messages.add(ActionMessages.GLOBAL_MESSAGE,
							new ActionMessage(
									"fuenteVentasPrevista.executedErrorPais"));
			} else
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"fuenteVentasPrevista.executed"));

			//session.setAttribute("message", messages);

		} else {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"interfazSiCC.error.periodo.abierto"));
		}
//		session.setAttribute("codigoPeriodo", calculoFVPSABForm
//				.getCodigoPeriodoInicial());
		//saveMessages(request, messages);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		  if (log.isDebugEnabled()) {
	            log.debug("Entering 'view' method");
	        }
		  
			//removeFormBean(mapping, request);
			ProcesoSABCalculoFuenteVentasPrevistaForm calculoFVPSABForm = (ProcesoSABCalculoFuenteVentasPrevistaForm) this.formProceso;

			// Obtenemos los beans b�sicos de la sesi�n
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

			Map params = BeanUtils.describe(calculoFVPSABForm);
			params.clear();
			params.put("codigoISO", usuario.getIdioma().getCodigoISO());
			params.put("codigoPais", pais.getCodigo());

			// Cargamos los combos a utilizar
			InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
			siccSociedadList = svc.getSociedadesByCodigoPais(pais.getCodigo());
			siccAlmacenList = svc.getAlmacenesByCodigoISOPais(params);
			siccMarcaList = svc.getMarcas();
			siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
			siccRangoPeriodoList = svc.getRangosPeriodo();
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			
			
			// Ini efernandezo
			//calculoFVPSABForm.setCodigoAlmacen(Constants.CODIGO_ALMACEN_DEFAULT);
			
			// Fin efernandezo
			//session.removeAttribute("codigoPeriodo");
			//getListaPeriodosPaisCanalAnioRango 
			//aSvc.getListaPeriodosPaisCanalAnioRango(codigoPais, codigoCanal, codigoAnio, codigoRango)
			
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			calculoFVPSABForm.setCodigoSociedad(Constants.CODIGO_SOCIEDAD_DEFAULT);
			calculoFVPSABForm.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
			calculoFVPSABForm.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
			calculoFVPSABForm.setCodigoAnio(sdf.format(new Date(System.currentTimeMillis()))
					.substring(6, 10));
			calculoFVPSABForm.setCodigoAlmacen(Constants.CODIGO_ALMACEN_DEFAULT);
			
			String codigoPais = pais.getCodigo();
			String codigoCanal = calculoFVPSABForm.getCodigoCanal();
			String codigoAnio= calculoFVPSABForm.getCodigoAnio();
			String codigoRangoPeriodo = ((Base)siccRangoPeriodoList.get(0)).getCodigo();
			calculoFVPSABForm.setCodigoRangoPeriodo(codigoRangoPeriodo);
			siccPeriodoInicialList = aSvc.getListaPeriodosPaisCanalAnioRango(
					codigoPais, codigoCanal, 
					codigoAnio, calculoFVPSABForm.getCodigoRangoPeriodo());
		
	}

	/**
	 * @return the siccSociedadList
	 */
	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	/**
	 * @param siccSociedadList the siccSociedadList to set
	 */
	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}

	/**
	 * @return the siccAlmacenList
	 */
	public List getSiccAlmacenList() {
		return siccAlmacenList;
	}

	/**
	 * @param siccAlmacenList the siccAlmacenList to set
	 */
	public void setSiccAlmacenList(List siccAlmacenList) {
		this.siccAlmacenList = siccAlmacenList;
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
	 * @return the siccRangoPeriodoList
	 */
	public List getSiccRangoPeriodoList() {
		return siccRangoPeriodoList;
	}

	/**
	 * @param siccRangoPeriodoList the siccRangoPeriodoList to set
	 */
	public void setSiccRangoPeriodoList(List siccRangoPeriodoList) {
		this.siccRangoPeriodoList = siccRangoPeriodoList;
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
	 * @return the siccZonaList
	 */
	public List getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(List siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the siccPeriodoInicialList
	 */
	public LabelValue[] getSiccPeriodoInicialList() {
		return siccPeriodoInicialList;
	}

	/**
	 * @param siccPeriodoInicialList the siccPeriodoInicialList to set
	 */
	public void setSiccPeriodoInicialList(LabelValue[] siccPeriodoInicialList) {
		this.siccPeriodoInicialList = siccPeriodoInicialList;
	}
}