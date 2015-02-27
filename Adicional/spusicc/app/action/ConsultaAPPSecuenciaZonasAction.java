package biz.belcorp.ssicc.web.spusicc.app.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.app.ProcesoAPPSecuenciarZonaTerritorioService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.app.form.ConsultaAPPSecuenciaZonasForm;


/**
 * The Class ConsultaAPPSecuenciaZonasAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 13/11/2014
 */
@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class ConsultaAPPSecuenciaZonasAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 6384645120184584084L;
	private String formatoReporte;
	private List siccRegionList;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ConsultaAPPSecuenciaZonasForm reporteForm = new ConsultaAPPSecuenciaZonasForm();
		return reporteForm;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void setViewAtributes() throws Exception {
		ConsultaAPPSecuenciaZonasForm f = (ConsultaAPPSecuenciaZonasForm) this.formReporte;		
		this.mostrarListaBusqueda = true;
		this.mostrarBotonBuscar = true;
		this.mostrarReporteXLS = false;
		this.mostrarReportePDF = false;
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais", criteria); 
		f.setRegionList(null);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaCOMComisionGerenteZonaSearchAction.setFindAttributes' method");
		}
		ConsultaAPPSecuenciaZonasForm f = (ConsultaAPPSecuenciaZonasForm) this.formReporte;
		ProcesoAPPSecuenciarZonaTerritorioService service =  (ProcesoAPPSecuenciarZonaTerritorioService) getBean("spusicc.procesoAPPSecuenciarZonaTerritorioService");
		Map criteria = new HashMap();
		if(f.getRegionList() != null && f.getRegionList().length > 0 && !f.getRegionList()[0].equals("")){
			criteria.put("codigoRegion", f.getRegionList());
		}
		List listaZonas = service.getSecuenciaZonasList(criteria);
		return listaZonas;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return null;
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		return null;
	}
	
	
	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}
	
}