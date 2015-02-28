package biz.belcorp.ssicc.web.spusicc.mae.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.mae.ProcesoMAECalificacionEstatusService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.mae.form.ConsultaMAECalificacionEstatusResolverMensajesForm;

@ManagedBean
@SessionScoped
public class ConsultaMAECalificacionEstatusResolverMensajesAction extends
		BaseProcesoAbstractAction {

	/**
	 * 
	 */
	private List maeClasificacionEstatusList;
	private static final long serialVersionUID = -8882509855675570536L;
	private LabelValue[] siccPeriodoList = {};
	private List siccMarcaList = new ArrayList();
	private List siccCanalList = new ArrayList();
	private List maeTipoProceso = new ArrayList();

	public List getMaeClasificacionEstatusList() {
		return maeClasificacionEstatusList;
	}

	public void setMaeClasificacionEstatusList(List maeClasificacionEstatusList) {
		this.maeClasificacionEstatusList = maeClasificacionEstatusList;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	public List getMaeTipoProceso() {
		return maeTipoProceso;
	}

	public void setMaeTipoProceso(List maeTipoProceso) {
		this.maeTipoProceso = maeTipoProceso;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ConsultaMAECalificacionEstatusResolverMensajesForm form = new ConsultaMAECalificacionEstatusResolverMensajesForm();
		return form;
	}

	// @Override
	// protected Map<String, Object> executeProcess(Map<String, Object> params)
	// throws Exception {
	// // TODO Auto-generated method stub
	// if (log.isDebugEnabled()) log.debug("Entering 'procesar' method");
	// Usuario usuario =mPantallaPrincipalBean.getCurrentUser();
	//
	// ConsultaMAECalificacionEstatusResolverMensajesForm f =
	// (ConsultaMAECalificacionEstatusResolverMensajesForm) formProceso;
	// ArrayList clasificacionEstatusList = (ArrayList)
	// session.getAttribute(Constants.MAE_CLASIFICACION_ESTATUS_LIST);
	//
	// log.debug("clasificacionEstatusList.size :"+
	// clasificacionEstatusList.size());
	// log.debug("f.getSelectedItems().length :"+ f.getSelectedItems().length );
	//
	// ProcesoMAECalificacionEstatusService service =
	// (ProcesoMAECalificacionEstatusService)
	// getBean("spusicc.procesoMAECalificacionEstatusService");
	//
	// for (int i = 0; i < f.getSelectedItems().length; i++) {
	// //for (int i = 0; i < clasificacionEstatusList.size(); i++) {
	// //log.debug("f.getSelectedItems()[i] :"+ f.getSelectedItems()[i]);
	// int posicion = Integer.parseInt(f.getSelectedItems()[i]);
	// //int posicion = i;
	// log.debug("posicion: " +posicion);
	// Map clasificacionEstatusMap = new HashMap();
	//
	// log.debug("clasificacionEstatusList.get(posicion-1) " +
	// clasificacionEstatusList.get(posicion-1));
	// clasificacionEstatusMap = (HashMap)
	// clasificacionEstatusList.get(posicion-1);
	// clasificacionEstatusMap.put("CodigoPeriodo",f.getCodigoPeriodo());
	// clasificacionEstatusMap.put("TipoProceso",f.getTipoProceso());
	// clasificacionEstatusMap.put("usuario",usuario.getLogin());
	//
	// log.debug("usuario"+usuario.getLogin());
	//
	// try {
	// service.executeProcesoAtendidos(clasificacionEstatusMap);
	// }catch (Exception e) {
	// log.debug("e" + e );
	// }
	// }
	// params.put("codigoPeriodo", f.getCodigoPeriodo());
	// params.put("tipoProceso", f.getTipoProceso());
	// params.put("CodigoPais", f.getCodigoPais());
	//
	// maeClasificacionEstatusList=service.getClasificacionEstatusList(params);
	//
	//
	// return params;
	// }
	//
	//

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		this.mostrarBotonBuscar = true;
		try {
			log.debug("Seting Attributes.");
			Pais pais = mPantallaPrincipalBean.getCurrentCountry();

			InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
			// Asignamos al codigo del periodo el valor por defecto
			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
			criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
			List periodos = interfazSiCCService
					.getPeriodosDefaultByPMC(criteria);

			if (periodos != null && periodos.size() > 0) {
				ConsultaMAECalificacionEstatusResolverMensajesForm actionForm = (ConsultaMAECalificacionEstatusResolverMensajesForm) formProceso;
				Base base = (Base) periodos.get(0);
				actionForm.setCodigoPeriodo(base.getCodigo());
			}

			ProcesoMAECalificacionEstatusService service = (ProcesoMAECalificacionEstatusService) getBean("spusicc.procesoMAECalificacionEstatusService");
			loadCombos();

			maeTipoProceso = service.getTipoProcesoList();

		} catch (Exception e) {
			ActionMessages messages = new ActionMessages();
			String error = e.getMessage();
			if (StringUtils.isBlank(error))
				error = e.getLocalizedMessage();
			messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
					"errors.detail", error));
			// saveErrors(messages);
		}

	}

	private void loadCombos() throws Exception {
		log.debug("Loading Combos.");

		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		ConsultaMAECalificacionEstatusResolverMensajesForm actionForm = (ConsultaMAECalificacionEstatusResolverMensajesForm) formProceso;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		siccMarcaList = service.getMarcas();
		siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma()
				.getCodigoISO());

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", mPantallaPrincipalBean
				.getCurrentCountry().getCodigo());

	}

	/**
	 * @return the siccPeriodoList
	 */
	public LabelValue[] getSiccPeriodoList() {
		return siccPeriodoList;
	}

	/**
	 * @param siccPeriodoList
	 *            the siccPeriodoList to set
	 */
	public void setSiccPeriodoList(LabelValue[] siccPeriodoList) {
		this.siccPeriodoList = siccPeriodoList;
	}

	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		log.debug("Seting find Attributes.");
		ConsultaMAECalificacionEstatusResolverMensajesForm f = (ConsultaMAECalificacionEstatusResolverMensajesForm) formProceso;
		f.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		Map params = new HashMap();
		params.put("codigoPeriodo", f.getCodigoPeriodo());
		params.put("tipoProceso", f.getTipoProceso());
		params.put("CodigoPais", f.getCodigoPais());

		log.debug("f.getCodigoPais():  " + f.getCodigoPais());

		ProcesoMAECalificacionEstatusService service = (ProcesoMAECalificacionEstatusService) getBean("spusicc.procesoMAECalificacionEstatusService");
		List datosList = null;
		datosList = (ArrayList) service.getClasificacionEstatusList(params);
		maeClasificacionEstatusList = datosList;

		return datosList;
	}

	/**
	 * @param siccMarcaList
	 *            the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'procesar' method");
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();

		ConsultaMAECalificacionEstatusResolverMensajesForm f = (ConsultaMAECalificacionEstatusResolverMensajesForm) formProceso;
		List clasificacionEstatusList = maeClasificacionEstatusList;

		log.debug("clasificacionEstatusList.size :"
				+ clasificacionEstatusList.size());
		//log.debug("f.getSelectedItems().length :" + f.getSelectedItems().length);

		ProcesoMAECalificacionEstatusService service = (ProcesoMAECalificacionEstatusService) getBean("spusicc.procesoMAECalificacionEstatusService");

		for (int i = 0; i < clasificacionEstatusList.size(); i++) {
			// for (int i = 0; i < clasificacionEstatusList.size(); i++) {
			// log.debug("f.getSelectedItems()[i] :"+ f.getSelectedItems()[i]);
			// int posicion = Integer.parseInt(f.getSelectedItems()[i]);
			int posicion = i;
			log.debug("posicion: " + posicion);
			Map clasificacionEstatusMap = new HashMap();

			log.debug("clasificacionEstatusList.get(posicion-1) "
					+ clasificacionEstatusList.get(posicion - 1));
			clasificacionEstatusMap = (HashMap) clasificacionEstatusList
					.get(posicion - 1);
			clasificacionEstatusMap.put("CodigoPeriodo", f.getCodigoPeriodo());
			clasificacionEstatusMap.put("TipoProceso", f.getTipoProceso());
			clasificacionEstatusMap.put("usuario", usuario.getLogin());

			log.debug("usuario" + usuario.getLogin());

			try {
				service.executeProcesoAtendidos(clasificacionEstatusMap);
			} catch (Exception e) {
				log.debug("e" + e);
			}
		}
		params.put("codigoPeriodo", f.getCodigoPeriodo());
		params.put("tipoProceso", f.getTipoProceso());
		params.put("CodigoPais", f.getCodigoPais());
		maeClasificacionEstatusList = service
				.getClasificacionEstatusList(params);
		return params;
	}
}