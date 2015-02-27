package biz.belcorp.ssicc.web.spusicc.comision.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCalculoCalificacionTramoService;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCalculoComisionRecuperacionEjecutivasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.comision.form.ProcesoCOMEliminarComisionRecuperacionForm;

/**
 * The Class ProcesoCOMEliminarComisionRecuperacionAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 05/01/2015
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@ManagedBean
@SessionScoped
public class ProcesoCOMEliminarComisionRecuperacionAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = 3050186930401974443L;
	private List siccMarcaList;
	private List siccCanalList;
	private List siccComisionList;
	private List siccTipoComisionRecuperacionList;	
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoCOMEliminarComisionRecuperacionForm form = new ProcesoCOMEliminarComisionRecuperacionForm();
		return form;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executing :  setViewAttributes. ");
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ProcesoCOMCalculoCalificacionTramoService tramoService = (ProcesoCOMCalculoCalificacionTramoService)getBean("spusicc.procesoCOMCalculoCalificacionTramoService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();	
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		this.siccMarcaList = service.getMarcas();		
		this.siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccComisionList = service.getListCodComision(pais.getCodigo());
		ProcesoCOMEliminarComisionRecuperacionForm f = (ProcesoCOMEliminarComisionRecuperacionForm) this.formProceso;
		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);		
		f.setCodigoPeriodo(codigoPeriodo);		
		this.siccTipoComisionRecuperacionList = tramoService.getTiposComisionistas(pais.getCodigo());
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		log.debug("Todo Ok: Redireccionando");
	}
	
	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) throws Exception {
		
		ProcesoCOMCalculoComisionRecuperacionEjecutivasService procesoService = (ProcesoCOMCalculoComisionRecuperacionEjecutivasService)getBean("spusicc.procesoCOMCalculoComisionRecuperacionEjecutivasService");
		
		params.put("mensajeError", null);
		
		procesoService.executeEliminarComisionRecuperacion(params);
		
		setMensajeError(params);
		
		return params;
	}

	private void setMensajeError(Map params) {
		String keyMensaje = (String) params.get("mensajeError");
		log.debug("keyMessage "+ keyMensaje);
		params.put("mensajeError", keyMensaje);
		
		if (StringUtils.isNotEmpty(keyMensaje)) {
			String mensaje = this.getResourceMessage("procesoCOMEliminarComisionRecuperacionForm.noComisiones");
			if(StringUtils.isNotEmpty(mensaje)) {
				params.put("mensajeError", mensaje);
				this.setMensajeAlertaDefault(mensaje);
				
			} else {
				params.put("mensajeError", keyMensaje);	
				this.setMensajeAlertaDefault(keyMensaje);
			}
			this.getRequestContext().execute("PF('principalFormAlert_alertDialog').show()");
		}
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseProcesoAbstractAction#setErrorLogicaNegocio(java.util.Map)
	 */
	protected String setErrorLogicaNegocio(Map params) {
		String mensaje = (String) params.get("mensajeError");
		if (StringUtils.isBlank(mensaje)) {
			return null;
		}
		return mensaje;
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
	 * @return the siccComisionList
	 */
	public List getSiccComisionList() {
		return siccComisionList;
	}

	/**
	 * @param siccComisionList the siccComisionList to set
	 */
	public void setSiccComisionList(List siccComisionList) {
		this.siccComisionList = siccComisionList;
	}

	/**
	 * @return the siccTipoComisionRecuperacionList
	 */
	public List getSiccTipoComisionRecuperacionList() {
		return siccTipoComisionRecuperacionList;
	}

	/**
	 * @param siccTipoComisionRecuperacionList the siccTipoComisionRecuperacionList to set
	 */
	public void setSiccTipoComisionRecuperacionList(
			List siccTipoComisionRecuperacionList) {
		this.siccTipoComisionRecuperacionList = siccTipoComisionRecuperacionList;
	}
	
}