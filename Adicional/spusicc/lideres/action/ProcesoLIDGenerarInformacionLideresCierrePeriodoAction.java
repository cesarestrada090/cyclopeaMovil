package biz.belcorp.ssicc.web.spusicc.lideres.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.lideres.ProcesoLIDGenerarInformacionLideresCierrePeriodoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.lideres.form.ProcesoLIDGenerarInformacionLideresCierrePeriodoForm;

@ManagedBean
@SessionScoped
public class ProcesoLIDGenerarInformacionLideresCierrePeriodoAction extends BaseProcesoAbstractAction 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1022676834336962996L;

	
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception 
	{
		ProcesoLIDGenerarInformacionLideresCierrePeriodoForm formProceso = new ProcesoLIDGenerarInformacionLideresCierrePeriodoForm();
		return formProceso;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) throws Exception
	{
		log.debug("Exectuting action : executeProcess.");

		if (params != null) {
			ProcesoLIDGenerarInformacionLideresCierrePeriodoService procesoLIDGenerarInformacionLideresCierrePeriodoService = (ProcesoLIDGenerarInformacionLideresCierrePeriodoService) getBean("spusicc.ProcesoLIDGenerarInformacionLideresCierrePeriodoService");
			procesoLIDGenerarInformacionLideresCierrePeriodoService.executeGenerarInformacionLideresCierrePeriodo(params);
		}
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception
	{
		log.debug("Executin action : view.");
		ProcesoLIDGenerarInformacionLideresCierrePeriodoForm f = (ProcesoLIDGenerarInformacionLideresCierrePeriodoForm)this.formProceso;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		String codigoPeriodo  = pais.getCodigoPeriodoFacturado();
		
		// Carga de los combos Marca, PeriodoProceso
		f.setMarcaList(reporteService.getMarcas());
		f.setCodigoIdiomaISO(usuario.getIdioma().getCodigoISO());
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setPeriodoProceso(codigoPeriodo);		
	}

}
