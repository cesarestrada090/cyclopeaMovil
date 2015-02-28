package biz.belcorp.ssicc.web.spusicc.comision.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCalculoCalificacionTramoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.comision.form.ProcesoCOMCalculoCalificacionTramoForm;

@ManagedBean
@SessionScoped
public class ProcesoCOMCalculoCalificacionTramoAction extends BaseProcesoAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5401157583842905484L;
	
	private List siccMarcaList;
	private List siccCanalList;
	private List comTipoComisionistaList;
	private List comTramoList;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoCOMCalculoCalificacionTramoForm form=new ProcesoCOMCalculoCalificacionTramoForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		log.debug("Los parametros del Proceso en el executeProcess son: "
				+ params.toString());
		
		ProcesoCOMCalculoCalificacionTramoService tramoService = (ProcesoCOMCalculoCalificacionTramoService)getBean("spusicc.procesoCOMCalculoCalificacionTramoService");
		tramoService.executeCalculoCalificacionTramo(params);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		ProcesoCOMCalculoCalificacionTramoForm form=(ProcesoCOMCalculoCalificacionTramoForm) this.formProceso;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ProcesoCOMCalculoCalificacionTramoService tramoService = (ProcesoCOMCalculoCalificacionTramoService)getBean("spusicc.procesoCOMCalculoCalificacionTramoService");
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario= this.mPantallaPrincipalBean.getCurrentUser();
		
		form.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		form.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		form.setTipoComisionista(Constants.CODIGO_TIPO_COMISIONISTA_DEFAULT);
		
		this.siccMarcaList=service.getMarcas();
		this.siccCanalList=service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.comTipoComisionistaList=tramoService.getTiposComisionistas(pais.getCodigo());
		this.comTramoList= tramoService.getTramos(pais.getCodigo());
		

		//Asignamos al codigo del periodo el valor por defecto
		Map criteria = new HashMap();
		criteria.put("codigoPais",pais.getCodigo());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		List periodos = service.getPeriodosDefaultByPMC(criteria);

		if(periodos != null && periodos.size() > 0) {
		    Base base = (Base)periodos.get(0);
		    form.setAnioInicial(base.getCodigo().substring(0,4));
		}
	}
	
	@Override
	protected Map<String, Object>prepareParamsBeforeExecute(Map params, BaseForm form)throws Exception{
		params=super.prepareParamsBeforeExecute(params, form);
		return params;
	}

}

