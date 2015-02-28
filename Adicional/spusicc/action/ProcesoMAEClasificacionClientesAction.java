package biz.belcorp.ssicc.web.spusicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.ProcesoMAEClasificacionClientesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.form.ProcesoMAEClasificacionClientesForm;

@ManagedBean  
@SessionScoped
public class ProcesoMAEClasificacionClientesAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = 8966709354350962550L;
	
	private List siccMarcaList =  new ArrayList();
	private List siccCanalList =  new ArrayList();
	private List siccTipoClienteList =  new ArrayList();
	private LabelValue[] siccPeriodoList =  {};
	
	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
		Pais pais =  this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario =  this.mPantallaPrincipalBean.getCurrentUser();
		
		Map params = new HashMap();
        params.clear();
        params.put("codigoISO",usuario.getIdioma().getCodigoISO());
        params.put("codigoPais",pais.getCodigo());
		
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
        
		// Carga de los combos Marca, Canal , Tipo Cliente,
		siccMarcaList = interfazSiCCService.getMarcas();
		siccCanalList = interfazSiCCService.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		siccTipoClienteList = interfazSiCCService.getTiposClientesByCodigoISO(usuario.getIdioma().getCodigoISO());

		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		
        // Asignamos al codigo del periodo el valor por defecto
        Map criteria = new HashMap();
        criteria.put("codigoPais", pais.getCodigo());
        criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
        criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
        List periodos = interfazSiCCService.getPeriodosDefaultByPMCF(criteria);
        
        if(periodos != null && periodos.size() > 0) {
        	ProcesoMAEClasificacionClientesForm actionForm = (ProcesoMAEClasificacionClientesForm) this.formProceso;
            Base base = (Base)periodos.get(0);
            actionForm.setCodigoPeriodo(base.getCodigo());
            actionForm.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
            actionForm.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
            actionForm.setCodigoTipoCliente(Constants.CODIGO_TIPO_CLIENTE_DEFAULT);
            actionForm.setNroCampaniasEvaluar("4");
        }		
        
        siccPeriodoList = ajaxService.getPeriodosDefaultByPMCF(
						pais.getCodigo(),
						Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT);
	}
	
	public void cargarCanalDate(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("Load Canal");
		}
		ProcesoMAEClasificacionClientesForm form = (ProcesoMAEClasificacionClientesForm) this.formProceso;

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
		ProcesoMAEClasificacionClientesForm form = (ProcesoMAEClasificacionClientesForm) this.formProceso;

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
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoMAEClasificacionClientesForm form = new ProcesoMAEClasificacionClientesForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		ProcesoMAEClasificacionClientesService service = (ProcesoMAEClasificacionClientesService) 
				getBean("spusicc.procesoMAEClasificacionClientesService");

		service.executeProcesarClasificacion(params);
		return params;
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
	 * @return the siccTipoClienteList
	 */
	public List getSiccTipoClienteList() {
		return siccTipoClienteList;
	}

	/**
	 * @param siccTipoClienteList the siccTipoClienteList to set
	 */
	public void setSiccTipoClienteList(List siccTipoClienteList) {
		this.siccTipoClienteList = siccTipoClienteList;
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