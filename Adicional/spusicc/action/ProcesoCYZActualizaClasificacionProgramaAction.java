package biz.belcorp.ssicc.web.spusicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.ProcesoCYZProgramaDuplaService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.form.ProcesoCYZActualizaClasificacionProgramaForm;

@ManagedBean  
@SessionScoped
public class ProcesoCYZActualizaClasificacionProgramaAction extends
        BaseProcesoAbstractAction {

	private static final long serialVersionUID = -8012359498362656732L;
    
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoCYZActualizaClasificacionProgramaForm form = new ProcesoCYZActualizaClasificacionProgramaForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		log.debug("Ejecutando la actualizacion de clasificaciones del programa dupla cyzone.");
        ProcesoCYZProgramaDuplaService service = (ProcesoCYZProgramaDuplaService) this.getBean("spusicc.procesoCYZProgramaDuplaService");
        service.executeProcesarClasificacionProgramaDupla(params);
        return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		 // Casteamos el formulario
        ProcesoCYZActualizaClasificacionProgramaForm actionForm = (ProcesoCYZActualizaClasificacionProgramaForm) this.formProceso;
        Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
        Map criteria = new HashMap();
        criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
        
        MantenimientoOCRPedidoControlFacturacionService serviceOCR = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
        PedidoControlFacturacion controlFacturacion = serviceOCR.getControlFacturacionById(criteria);
        
        // Actualizamos los valores en caso estos esten vacios
        if(StringUtils.isBlank(actionForm.getCodigoPeriodo())) {
            actionForm.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
        }
	}
}