package biz.belcorp.ssicc.web.spusicc.cobranzas.action;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ProcesoCOBGenerarCronogramaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.ProcesoCOBGenerarCronogramaForm;

/**
 * The Class ProcesoCOBGenerarCronogramaAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 05/01/2015
 */
@SuppressWarnings({ "rawtypes" })
@ManagedBean
@SessionScoped
public class ProcesoCOBGenerarCronogramaAction extends BaseProcesoAbstractAction {
	
	private static final long serialVersionUID = 4127693256651944255L;
	private List siccSociedadList;
	private List siccEtapaDeudaList;
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoCOBGenerarCronogramaForm form = new ProcesoCOBGenerarCronogramaForm();
		return form;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executing :  setViewAttributes. ");		
		
		//Obtenemos los beans básicos de la sesion
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
				
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");		
		
		// Cargamos los combos a utilizar
		
		//La constante SICC_SOCIEDAD_LIST almacena la lista de Sociedades por Pais
		this.siccSociedadList = service.getSociedadesByCodigoPais(pais.getCodigo());				
		this.siccEtapaDeudaList = new ArrayList();			
		
		ProcesoCOBGenerarCronogramaForm form = (ProcesoCOBGenerarCronogramaForm) this.formProceso;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));
		form.setCodigoPeriodo(periodo);
		form.setCodigoPais(pais.getCodigo());
			
		log.debug("Todo Ok: Redireccionando");
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) throws Exception {
						
		ProcesoCOBGenerarCronogramaForm reporteCOBForm = (ProcesoCOBGenerarCronogramaForm) this.formProceso;
				
		params.put("codigoPais", reporteCOBForm.getCodigoPais());
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		params.put("codigoUsuario", usuario.getLogin());
					
		log.debug("Los parametros del Reporte en el executeProcess son: " + params.toString());
		
		ProcesoCOBGenerarCronogramaService service = (ProcesoCOBGenerarCronogramaService)getBean("spusicc.procesoCOBGenerarCronogramaService");
		service.executeGenerarCronograma(params);
				
		return params;
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
	 * @return the siccEtapaDeudaList
	 */
	public List getSiccEtapaDeudaList() {
		return siccEtapaDeudaList;
	}

	/**
	 * @param siccEtapaDeudaList the siccEtapaDeudaList to set
	 */
	public void setSiccEtapaDeudaList(List siccEtapaDeudaList) {
		this.siccEtapaDeudaList = siccEtapaDeudaList;
	}

}