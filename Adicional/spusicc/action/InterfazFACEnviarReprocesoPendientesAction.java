package biz.belcorp.ssicc.web.spusicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.collections.CollectionUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spisicc.InterfazIMPGeneracionDocumentosNumeroInternoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spusicc.form.InterfazFACEnviarReprocesoPendientesForm;

@ManagedBean
@SessionScoped
public class InterfazFACEnviarReprocesoPendientesAction extends
		BaseInterfazAbstractAction {

	private static final long serialVersionUID = 9149221962492764399L;


	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazFACEnviarReprocesoPendientesForm form = new InterfazFACEnviarReprocesoPendientesForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		InterfazFACEnviarReprocesoPendientesForm f = (InterfazFACEnviarReprocesoPendientesForm) this.formInterfaz;
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																// Campanha
																// Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																	// Campanha
																	// activa
																	// que se
																	// procesa
																	// actualmente

		GenericoService genericoService = (GenericoService) getBean("genericoService");

		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(pais.getCodigo());
		parametroPais.setCodigoSistema(Constants.FAC_CODIGO_SISTEMA);
		parametroPais
				.setNombreParametro(Constants.FAC_NOM_PARA_DIFERENCIA_SYSDATE);
		parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
		List lstParametros = genericoService.getParametrosPais(parametroPais);
		String valParaDiferenciaSysdate = Constants.FAC_VAL_PARA_DIFERENCIA_SYSDATE_DEFAULT;

		ParametroPais parametro = null;
		if (CollectionUtils.size(lstParametros) == 1) {
			parametro = (ParametroPais) lstParametros.get(0);
			valParaDiferenciaSysdate = parametro.getValorParametro();
		}
		//sisicc.interfazIMPGeneracionDocumentosNumeroInternoService
		InterfazIMPGeneracionDocumentosNumeroInternoService interfazService = (InterfazIMPGeneracionDocumentosNumeroInternoService) getBean("sisicc.interfazIMPGeneracionDocumentosNumeroInternoService");
		// PedidoControlFacturacion controlFacturacion =
		// interfazService.getControlFacturacionById(criteria);

		criteria.put("valParaDiferenciaSysdate", valParaDiferenciaSysdate);
		String desdeFechaFacturacion = interfazService
				.getDesdeFechaFacturacion(criteria);

//		String codigoProcesoBatch = request.getParameter("codigoProcesoBatch");
		// f.setFechaFacturacion(controlFacturacion.getFechaProceso());
		f.setFechaFacturacion(desdeFechaFacturacion);

//		request.getSession().setAttribute("codigoProcesoBatch",
//				codigoProcesoBatch);

	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {		
		params = super.prepareParamsBeforeExecute(params, form);
		return params;
	}

}
