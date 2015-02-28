package biz.belcorp.ssicc.web.spusicc.mae.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.mae.form.MantenimientoMAEActualizacionPeriodosRetiradasForm;

@ManagedBean
@SessionScoped
public class MantenimientoMAEActualizacionPeriodosRetiradasAction extends BaseMantenimientoSearchAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6393984440572918487L;

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoMAEActualizacionPeriodosRetiradasForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoMAEActualizacionPeriodosRetiradasForm objForm = new MantenimientoMAEActualizacionPeriodosRetiradasForm();
		return objForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}
		MantenimientoMAEActualizacionPeriodosRetiradasForm f = (MantenimientoMAEActualizacionPeriodosRetiradasForm) this.formBusqueda;
		MantenimientoMAEClienteService service = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");	
		
		boolean bGrabarCab = false;
		try {

			// Obtenemos las propiedades del bean como un 'Map'
	        Map params = BeanUtils.describe(f);
			
			//actualizamos los parametros de lideres
        	service.updatePeriodosRetiradas(params);
			bGrabarCab = true;
				
		} catch (Exception e) {
			e.printStackTrace();
			bGrabarCab = false;
			this.addError("Error: ", this.getResourceMessage("mantenimientoEDULocal.cabecera.error", new Object[]{e.getMessage()}));
			
		}
		
					
			
		return bGrabarCab;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
            log.debug("Entering 'view' method");
        }
        MantenimientoMAEClienteService service = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		MantenimientoMAEActualizacionPeriodosRetiradasForm f = (MantenimientoMAEActualizacionPeriodosRetiradasForm)this.formBusqueda;

		// Obtenemos los beans básicos de la sesión
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map params = new HashMap();
		params.put("codigoPais", pais.getCodigo());
		String Numeros = service.getPeriodosRetiradas(pais.getCodigo());
		f.setNumeroPeriodos(Numeros);
		
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO);
	    
		MantenimientoOCRPedidoControlFacturacionService serviceAux = 
							(MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceAux.getControlFacturacionById(criteriaPeriodo);
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() {
		
		return "mantenimientoMAEActualizacionPeriodosRetiradasForm.msg.updated";
	}
	
}
