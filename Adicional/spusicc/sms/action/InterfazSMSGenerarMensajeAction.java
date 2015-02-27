package biz.belcorp.ssicc.web.spusicc.sms.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spusicc.sms.form.InterfazSMSGenerarMensajeForm;

/**
 * Action invocado desde la pantalla de mantenimiento del objeto Usuario SICC.
 * <p>
 * <a href="RolSearchAction.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a> 
 */

@ManagedBean
@SessionScoped
public class InterfazSMSGenerarMensajeAction  extends BaseInterfazAbstractAction{


	/**
	 * 
	 */
	private static final long serialVersionUID = -1515064982421602260L;
	
	private List interfazSMSGenerarMensajeList;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		
		InterfazSMSGenerarMensajeForm objForm = new InterfazSMSGenerarMensajeForm();
		return objForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		
		InterfazSMSGenerarMensajeForm objForm =  (InterfazSMSGenerarMensajeForm)this.formInterfaz;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		objForm.setCodigoPais(pais.getCodigo()); 
		
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaPeriodo);

		objForm.setPeriodo(controlFacturacion.getCodigoPeriodo());
		objForm.setFechaFacturacionD(new Date(controlFacturacion.getFechaProceso().toString()));
		
	}
	

}
