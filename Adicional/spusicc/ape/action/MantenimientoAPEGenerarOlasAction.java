package biz.belcorp.ssicc.web.spusicc.ape.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEGenerarEstimadoProductoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.ape.form.MantenimientoAPEGenerarOlasForm;

/**
 * @author <a href="mailto:kgomez@sigcomt.com">Karina Gomez</a>
 * 
 */
@ManagedBean
@SessionScoped
public class MantenimientoAPEGenerarOlasAction extends BaseProcesoAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4390812022275447582L;



	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */

//mensaje = "mantenimientoAPEGenerarOlas.insert";
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoAPEGenerarOlasForm objForm = new MantenimientoAPEGenerarOlasForm();
		return objForm;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		MantenimientoAPEGenerarEstimadoProductoService service = 
				(MantenimientoAPEGenerarEstimadoProductoService)getBean("spusicc.mantenimientoAPEGenerarEstimadoProductoService");
				
			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			Idioma idioma = this.mPantallaPrincipalBean.getCurrentIdioma();
			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("oidPais", reporteService.getOidString("getOidPaisByCodigoPais", criteria));
			criteria.put("oidIdiomaISO", idioma.getCodigoISO());
			
			//Llamando a la generacion de las olas
			service.executeGenerarOlas(criteria);
		    this.addInfo("Info: ", this.getResourceMessage("mantenimientoAPEGenerarOlas.insert"));
			return criteria;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoAPEGenerarOlasForm f = (MantenimientoAPEGenerarOlasForm)this.formProceso;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Pais pais  = this.mPantallaPrincipalBean.getCurrentCountry();
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("oidPais", reporteService.getOidString("getOidPaisByCodigoPais", criteria));
		f.setCodigoPais(pais.getCodigo());
		
	}
	
	
}
