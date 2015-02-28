package biz.belcorp.ssicc.web.spusicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.ProcesoPERCruceSaldoPositivoNegativoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazIMPEnviarDocumentosMatricialesForm;

@ManagedBean
@SessionScoped
public class ProcesoPERCruceSaldoPositivoNegativoAction extends BaseInterfazAbstractAction{

	private static final long serialVersionUID = 6825673628686692052L;
	private String codigoTipoOrigenDatos;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazIMPEnviarDocumentosMatricialesForm interfazForm = new InterfazIMPEnviarDocumentosMatricialesForm();
		return interfazForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}		
		this.codigoTipoOrigenDatos = (String) this.parametrosPantalla.get("codigoTipoOrigenDatos");

	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParamsBeforeExecute' method");
		}
		params = super.prepareParamsBeforeExecute(params, form);
		/* Inicializando valores */
		String exito = "-1";
		//super.beforeExecuteInterfaz(params);
		
		params.put("exito", exito);

		/* Invocando al metodo del proceso de cargo y abono */
		ProcesoPERCruceSaldoPositivoNegativoService service = (ProcesoPERCruceSaldoPositivoNegativoService) getBean("spusicc.procesoPERCruceSaldoPositivoNegativoService");
		service.executeCruceSaldoPositivoNegativo(params);
		log.debug("Mostrando el numero de Lote " + params.get("numeroLote"));
		exito = "1";
		params.put("exito", exito);		
		return params;
	}	
}
