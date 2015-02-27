package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoOCRActualizacionMatrizFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoOCRActualizacionMatrizFacturacionForm;

@SessionScoped
@ManagedBean
public class ProcesoOCRActualizacionMatrizFacturacionAction extends BaseProcesoAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 748369449828103050L;
	private List siccMarcaList;
	private List siccCanalList;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoOCRActualizacionMatrizFacturacionForm form = new ProcesoOCRActualizacionMatrizFacturacionForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		ProcesoOCRActualizacionMatrizFacturacionService service = (ProcesoOCRActualizacionMatrizFacturacionService) getBean("spusicc.procesoOCRActualizacionMatrizFacturacionService");
    	
    	service.executeOCRActualizacionMatrizFacturacion(params);
    
    	return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Executing action : setViewAttributes.");
	
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.siccMarcaList=service.getMarcas();	
		this.siccCanalList=service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());

		ProcesoOCRActualizacionMatrizFacturacionForm f = ( ProcesoOCRActualizacionMatrizFacturacionForm) this.formProceso;
		
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
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
	
	

	
}
