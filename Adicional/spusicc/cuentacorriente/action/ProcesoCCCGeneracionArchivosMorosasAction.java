package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCGeneracionArchivosMorosasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.ProcesoCCCGeneracionArchivosMorosasForm;

@SessionScoped
@ManagedBean
public class ProcesoCCCGeneracionArchivosMorosasAction extends BaseProcesoAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4062057712725576758L;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoCCCGeneracionArchivosMorosasForm form = new ProcesoCCCGeneracionArchivosMorosasForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
        Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		params.put("codigoUsuario", usuario.getLogin());
		
		log.debug(params.toString());
		
		ProcesoCCCGeneracionArchivosMorosasService service = (ProcesoCCCGeneracionArchivosMorosasService)getBean("spusicc.procesoCCCGeneracionArchivosMorosasService");				
		service.executeGeneracionArchivosMorosas(params);
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
