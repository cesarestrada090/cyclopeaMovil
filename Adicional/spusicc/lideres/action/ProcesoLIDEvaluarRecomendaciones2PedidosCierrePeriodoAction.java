package biz.belcorp.ssicc.web.spusicc.lideres.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.lideres.ProcesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.lideres.form.ProcesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoForm;

@SessionScoped
@ManagedBean
public class ProcesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoAction extends BaseProcesoAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3757538434827121113L;
	
	private List siccRegionList;
	private String letTipoCierreRegion;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoForm form = new ProcesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		log.debug("Exectuting action : executeProcess.");

		ProcesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoForm f = (ProcesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoForm) this.formProceso;

		if(params!=null){
			ProcesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoService procesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoService=  
				(ProcesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoService)getBean("spusicc.procesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoService");

			String tipoCierre = f.getTipoCierre();
			if(tipoCierre.equals(Constants.LET_TIPO_CIERRE_REGION)){
				params.put("codigoRegion", f.getCodigoRegion());
				procesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoService.executeEvaluarRecomendaciones2PedidosCierreRegion(params);
			}else{
				procesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoService.executeEvaluarRecomendaciones2PedidosCierrePeriodo(params);	
			}
		}

		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Executin action : view.");
		ProcesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoForm f = (ProcesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoForm) this.formProceso;

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		// Carga de los combos Marca, PeriodoProceso
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codigoPeriodo  = pais.getCodigoPeriodoFacturado();
		f.setMarcaList(reporteService.getMarcas());
		f.setCodigoIdiomaISO(usuario.getIdioma().getCodigoISO());
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setPeriodoProceso(codigoPeriodo);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = new Date(System.currentTimeMillis());
		String fechaProceso = sdf.format(fecha);

		f.setFechaProceso(fechaProceso);

		String tipoCierre = this.parametrosPantalla.get("tipoCierre");
		f.setTipoCierre(tipoCierre);
		// Carga del combo Regiones
		Map criteriaOperacion = new HashMap();
        criteriaOperacion.put("codigoPais", pais.getCodigo());
        this.siccRegionList=reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion);
        
        this.letTipoCierreRegion = Constants.LET_TIPO_CIERRE_REGION;
		
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

	public String getLetTipoCierreRegion() {
		return letTipoCierreRegion;
	}

	public void setLetTipoCierreRegion(String letTipoCierreRegion) {
		this.letTipoCierreRegion = letTipoCierreRegion;
	}
	
	

}
