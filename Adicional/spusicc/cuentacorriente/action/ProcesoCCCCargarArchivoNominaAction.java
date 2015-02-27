package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCCargarArchivoNominaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.ProcesoCCCCargarArchivoNominaForm;

@ManagedBean
@SessionScoped
public class ProcesoCCCCargarArchivoNominaAction extends BaseProcesoAbstractAction{

	
	private static final long serialVersionUID = -2784449749155361864L;
	
	private String codigoSistema;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoCCCCargarArchivoNominaForm procesoForm =new ProcesoCCCCargarArchivoNominaForm();		
		return procesoForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {

		log.debug("Executing action : setViewAttributes.");
		ProcesoCCCCargarArchivoNominaForm f =(ProcesoCCCCargarArchivoNominaForm)this.formProceso;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		this.codigoSistema = (String) this.parametrosPantalla.get("codigoSistema");
		f.setCodigoPais(pais.getCodigo());	
		
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		Usuario usuario =this.mPantallaPrincipalBean.getCurrentUser();
		params.put("codigoUsuario", usuario.getLogin());	
	
		ProcesoCCCCargarArchivoNominaService service = (ProcesoCCCCargarArchivoNominaService)getBean("spusicc.procesoCCCCargarArchivoNominaService");				
		service.executeCargarArchivoNomina(params);		
		return params;
	}

	public String getCodigoSistema() {
		return codigoSistema;
	}

	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}
	

	

}
