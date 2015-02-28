package biz.belcorp.ssicc.web.spusicc.fac.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.fac.MantenimientoFACGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.fac.form.ProcesoFACEliminarTablasTemporalesForm;

@ManagedBean
@SessionScoped
public class ProcesoFACEliminarTablasTemporalesAction extends BaseProcesoAbstractAction{

	private static final long serialVersionUID = 6534304393940040327L;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoFACEliminarTablasTemporalesForm procesoForm =new ProcesoFACEliminarTablasTemporalesForm();		
		return procesoForm;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		log.debug("Los parametros del Proceso en el executeProcess son: " + params.toString());
		
		MantenimientoFACGenericoService service = (MantenimientoFACGenericoService)getBean("spusicc.mantenimientoFACGenericoService");
		service.deleteTablasTemporales();				
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
