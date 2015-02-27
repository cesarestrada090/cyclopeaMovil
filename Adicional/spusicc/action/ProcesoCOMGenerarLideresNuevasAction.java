package biz.belcorp.ssicc.web.spusicc.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenerarLiderService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.form.ProcesoCOMGenerarLideresNuevasForm;

@ManagedBean
@SessionScoped
public class ProcesoCOMGenerarLideresNuevasAction extends
		BaseProcesoAbstractAction {

	private static final long serialVersionUID = -4204338631328719645L;
	private List siccMarcaList;
	private List siccCanalList;
	private LabelValue[] siccPeriodoList;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoCOMGenerarLideresNuevasForm form = new ProcesoCOMGenerarLideresNuevasForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'generate' method");
		}
		ActionMessages messages = new ActionMessages();
		ProcesoCOMGenerarLideresNuevasForm f = (ProcesoCOMGenerarLideresNuevasForm) this.formProceso;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		GenerarLiderService generarLiderService = (GenerarLiderService) getBean("generarLiderService");
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoMarca", f.getCodigoMarca());
		criteria.put("codigoCanal", f.getCodigoCanal());
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("codigoTipoDoc", f.getCodigoTipoDocumento());
		criteria.put("usuario", usuario.getLogin());
		generarLiderService.insertLideresGenerados(criteria, usuario);
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"generarLideres.message"));
		// saveMessages(request.getSession(), messages);
		//session.setAttribute("codigoPeriodo", f.getCodigoPeriodo());

		return params;
	}
	
	public void cargarCanalDate(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("Load Canal");
		}
		ProcesoCOMGenerarLideresNuevasForm form = (ProcesoCOMGenerarLideresNuevasForm) this.formProceso;

		String canal = (String) val.getNewValue();
		String marca = form.getCodigoMarca();
		if (marca.equals(Constants.CODIGO_MARCA_DEFAULT)
				&& canal.equals(Constants.CODIGO_CANAL_DEFAULT)) {

			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.siccPeriodoList = aSvc.getPeriodosByPaisMarcaCanalRangos(
					form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,
					Constants.CODIGO_CANAL_DEFAULT, "0", "1");

		} else {
			this.siccPeriodoList = null;
		}
	}

	public void cargarMarcaDate(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("Load Marca");
		}
		ProcesoCOMGenerarLideresNuevasForm form = (ProcesoCOMGenerarLideresNuevasForm) this.formProceso;

		String marca = (String) val.getNewValue();
		String canal = form.getCodigoCanal();
		if (marca.equals(Constants.CODIGO_MARCA_DEFAULT)
				&& canal.equals(Constants.CODIGO_CANAL_DEFAULT)) {

			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.siccPeriodoList = aSvc.getPeriodosByPaisMarcaCanalRangos(
					form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,
					Constants.CODIGO_CANAL_DEFAULT, "0", "1");

		} else {
			this.siccPeriodoList = null;
		}
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}

			ProcesoCOMGenerarLideresNuevasForm f = (ProcesoCOMGenerarLideresNuevasForm) this.formProceso;
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			f.setCodigoPais(pais.getCodigo());
			String tipoDocumento = this.getParametrosPantalla().get(
					"codigoTipoDoc");
			if (StringUtils.isNotBlank(tipoDocumento))
				f.setCodigoTipoDocumento(tipoDocumento);

			InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

			siccMarcaList = svc.getMarcas();
			siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma()
					.getCodigoISO());

			Map criteria = new HashMap();
			criteria.put("codigoPais", f.getCodigoPais());
			criteria.put("codigoMarca", f.getCodigoMarca());
			criteria.put("codigoCanal", f.getCodigoCanal());
			
			List siccPeriodoListVar = new ArrayList();
			siccPeriodoListVar = svc.getPeriodosByPMC(criteria);
			this.siccPeriodoList = new LabelValue[siccPeriodoListVar.size()];
			int z = 0;
			for (Object object : siccPeriodoListVar) {
				LabelValue labelValue = new LabelValue();
				labelValue.setLabel(((Base) object).getDescripcion());
				labelValue.setValue(((Base) object).getCodigo());
				this.getSiccPeriodoList()[z] = labelValue;
				z++;
			}
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			
	    	f.setCodigoMarca(Constants.CODIGO_MARCA_EBEL);
	    	f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
	    	f.setCodigoPeriodo(sdf.format(new Date(System.currentTimeMillis())));
	    	
			
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
	 * @return the siccPeriodoList
	 */
	public LabelValue[] getSiccPeriodoList() {
		return siccPeriodoList;
	}

	/**
	 * @param siccPeriodoList the siccPeriodoList to set
	 */
	public void setSiccPeriodoList(LabelValue[] siccPeriodoList) {
		this.siccPeriodoList = siccPeriodoList;
	}	
}