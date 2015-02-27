package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.ArrayList;
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
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDAsignacionStockService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBloqueoControlService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoPEDAsignacionStockForm;

@ManagedBean
@SessionScoped
public class ProcesoPEDAsignacionStockAction extends BaseProcesoAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8069515524326284281L;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoPEDAsignacionStockForm f = new ProcesoPEDAsignacionStockForm();
		return f;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		log.info("Entro a ProcesoPEDAsignacionStockAction - executeProcess");

		// -- Variables
		ProcesoPEDAsignacionStockService service = (ProcesoPEDAsignacionStockService) getBean("spusicc.pedidos.procesoPEDAsignacionStockService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		// -- Agregar parametro
		params.put("psusuario", usuario.getLogin());

		// -- Logica
		service.executeAsignacionStock(params);

		log.info("Salio a ProcesoPEDAsignacionStockAction - executeProcess");
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		log.info("Entro a ProcesoPEDAsignacionStockAction - setViewAttributes");

		// -- Variables ----------------------------------------------

		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ProcesoPEDAsignacionStockForm f = (ProcesoPEDAsignacionStockForm) this.formProceso;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoSTOBloqueoControlService stoService = (MantenimientoSTOBloqueoControlService) getBean("spusicc.mantenimientoSTOBloqueoControlService");
		String codigoPais = pais.getCodigo();
		ArrayList listaFamilia = new ArrayList();

		// -- Logica inicializar campos ------------------------------

		// -- Pojo
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);

		// -- Logica
		List lista = interfazSiCCService.getPeriodoFechaProcesoActual(criteria);
		HashMap mapeo = (HashMap) lista.get(0);

		// -- Logica Cargar Procesos Familia Protegida ---------------

		// -- Pojo
		criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoSistema", "SGR");
		criteria.put("nombreParametro", "indFamiliaProtegida");

		// -- Logica
		String indFamiliaProtegida = stoService
				.getParametroGenericoSistema(criteria);

		for (int i = 1, j = 1; i <= 3; i++) {
			String cadena = new Integer(i).toString().trim();
			String cadenaMostrar = new Integer(j).toString().trim();// imprime
																	// el numero
			switch (i) {
			case 1:
				if (Constants.NUMERO_UNO.equals(indFamiliaProtegida)) {
					this.adicionarProceso(listaFamilia, cadenaMostrar,
							"procesoPEDAsignacionStockForm.proceso0" + cadena);
					j++;
				}
				break;

			case 2:
				if (Constants.NUMERO_UNO.equals(indFamiliaProtegida)) {
					this.adicionarProceso(listaFamilia, cadenaMostrar,
							"procesoPEDAsignacionStockForm.proceso0" + cadena);
					j++;
				}
				break;
			case 3:
				this.adicionarProceso(listaFamilia, cadenaMostrar,
						"procesoPEDAsignacionStockForm.proceso0" + cadena);
				j++;
				break;
			}
		}

		// -- Peticiones ---------------------------------------------
		f.setCodigoPeriodo(mapeo.get("cod_peri").toString());
		f.setFechaProceso(mapeo.get("fec_proc").toString());

		log.info("Salio a ProcesoPEDAsignacionStockAction - setViewAttributes");

	}

	/**
	 * Obtiene descripcion del proceso del archivo de recursos
	 * 
	 * @param resultado
	 * @param messageResources
	 * @param codigo
	 * @param keyMensaje
	 */
	private void adicionarProceso(ArrayList resultado, String codigo,
			String keyMensaje) {
		Base bean = new Base();
		String proceso = this.getResourceMessage(keyMensaje);
		bean.setCodigo(codigo);
		bean.setDescripcion(proceso);
		resultado.add(bean);
	}

}
