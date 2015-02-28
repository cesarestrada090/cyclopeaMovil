package biz.belcorp.ssicc.web.spusicc.let.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.let.MantenimientoLETLideresService;
import biz.belcorp.ssicc.service.spusicc.let.ProcesoLETCargaPedidosObjetivosRezonificacionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.let.form.ProcesoLETCargaPedidosObjetivosRezonificacionForm;

@ManagedBean
@SessionScoped
public class ProcesoLETCargaPedidosObjetivosRezonificacionAction extends BaseProcesoAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3816115477283919552L;
	
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private List letRegionesCerradasList;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception 
	{
		ProcesoLETCargaPedidosObjetivosRezonificacionForm formProceso = new ProcesoLETCargaPedidosObjetivosRezonificacionForm();
		return formProceso;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) throws Exception 
	{
		log.debug("ProcesoLETCargaPedidosObjetivosRezonificacionAction - executeProcess");
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		ProcesoLETCargaPedidosObjetivosRezonificacionForm f = (ProcesoLETCargaPedidosObjetivosRezonificacionForm)this.formProceso;
		
		ProcesoLETCargaPedidosObjetivosRezonificacionService service = (ProcesoLETCargaPedidosObjetivosRezonificacionService)getBean("spusicc.procesoLETCargaPedidosObjetivosRezonificacionService");
		MantenimientoLETLideresService mantenimientoLETLideresService = (MantenimientoLETLideresService) getBean("spusicc.mantenimientoLETLideresService");
		
		String oidPais;
        String oidMarca;
        String oidCanal;
        
        Map criteriaOid = new HashMap();
        criteriaOid.put("codigoPais", f.getCodigoPais());
        criteriaOid.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
        criteriaOid.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
        
        oidPais = mantenimientoLETLideresService.getOidPaisByCodigoPaisLET(criteriaOid);
        oidMarca = mantenimientoLETLideresService.getOidMarcaByCodigoMarcaGenericoLET(criteriaOid);
        oidCanal =  mantenimientoLETLideresService.getOidCanalByCodigoCanalGenericoLET(criteriaOid);
        
        Map criteria = new HashMap();
        
        criteria.put("codigoPais",f.getCodigoPais());
        criteria.put("oidPais",Integer.valueOf(oidPais));
        criteria.put("oidMarca",Integer.valueOf(oidMarca));
        criteria.put("oidCanal",Integer.valueOf(oidCanal));
        criteria.put("codigoPeriodo",f.getCodigoPeriodo());
        
		boolean flagConcurso = service.getValidaExisteConcursoByCodigoPeriodo(criteria);
		List regionesCerradasList = new ArrayList(); 
		boolean flagRegionesCerradas = service.getValidaRegionesCerradas(Integer.valueOf(oidPais), f.getCodigoPeriodo(),f.getCodigoZona(),regionesCerradasList);
		String codigoZonaError = "";
		boolean flagZonaNuevaRezonificada = service.getValidaZonaNuevaRezonificada(f.getCodigoPais(),f.getIndicadorValidaCargaObjetivos(),f.getCodigoPeriodo(),Constants.CODIGO_MARCA_DEFAULT,Constants.CODIGO_CANAL_DEFAULT,f.getCodigoZona(),codigoZonaError);
		
		this.letRegionesCerradasList =  regionesCerradasList;
		
		if(!flagConcurso){
			throw new Exception(getResourceMessage("procesoLETCargaPedidosObjetivosRezonificacionForm.mensaje.error.noEncontroConcurso"));
		}
		
		if(flagRegionesCerradas){
			f.setMostrarRegionesCerradasList(true);
			throw new Exception(getResourceMessage("procesoLETCargaPedidosObjetivosRezonificacionForm.mensaje.error.regionesCerradas",new String[]{f.getCodigoPeriodo()}));
		}
		
		if(!flagZonaNuevaRezonificada){
			throw new Exception(getResourceMessage("procesoLETCargaPedidosObjetivosRezonificacionForm.mensaje.error.zonaNoNuevaRezonificada",new String[]{codigoZonaError}));
		}
		
		params.put("zonasList", f.getCodigoZona());
		params.put("codigoPeriodo", f.getCodigoPeriodo());
		params.put("codigoUsuario", usuario.getLogin());
		
		service.executeCargaPedidosObjetivosRezonificacion(params);
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		log.debug("ProcesoLETCargaPedidosObjetivosRezonificacionAction - setViewAttributes");
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
		
        ProcesoLETCargaPedidosObjetivosRezonificacionService service = (ProcesoLETCargaPedidosObjetivosRezonificacionService)getBean("spusicc.procesoLETCargaPedidosObjetivosRezonificacionService");
		MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(criteria);
		MantenimientoLETLideresService mantenimientoLETLideresService = (MantenimientoLETLideresService) getBean("spusicc.mantenimientoLETLideresService");
		
		ProcesoLETCargaPedidosObjetivosRezonificacionForm f = (ProcesoLETCargaPedidosObjetivosRezonificacionForm)this.formProceso;
		
		String codigoParametro = this.parametrosPantalla.get("codigoParametro");
		
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoParametro", codigoParametro);
        
        String indicadorValidaCargaObjetivos = mantenimientoLETLideresService.getIndicadorAsignarLider(criteria);
		// Carga de PeriodoProceso y Fecha Facturacion
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setIndicadorValidaCargaObjetivos(indicadorValidaCargaObjetivos);
			
		criteria.put("codigoPeriodo",f.getCodigoPeriodo());
		AjaxService ajaxService = (AjaxService)this.getBeanService("ajaxService");
		
		this.siccRegionList = ajaxService.getRegionesByPaisMarcaCanal(f.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT);
		String[] codigoRegionList = null;
		
		if (siccRegionList != null) {
			codigoRegionList = new String[siccRegionList.length];
			for(int i=0;i<siccRegionList.length;i++){
				codigoRegionList[i] = siccRegionList[i].getValue();
			}
		}		
		String[] codregion = {getSiccRegionList()[0].getValue()};
		f.setCodigoRegion(codregion);
		
		this.siccZonaList = ajaxService.getZonasMultipleByPaisMarcaCanalRegion(f.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, f.getCodigoRegion(), Constants.TODAS);
		String[] codzona = {getSiccZonaList()[1].getValue()};
		f.setCodigoZona(codzona);
		
		/*METODO RESET*/
		f.setMostrarRegionesCerradasList(false);				
	}
	
	
	 public void loadZonas(ValueChangeEvent value)
	{
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
				
		String[] valor = (String[]) value.getNewValue();
			ProcesoLETCargaPedidosObjetivosRezonificacionForm procesoForm = (ProcesoLETCargaPedidosObjetivosRezonificacionForm) this.formProceso;
			AjaxService ajaxService = (AjaxService)this.getBeanService("ajaxService");
			this.siccZonaList = ajaxService.getZonasMultipleByPaisMarcaCanalRegion(procesoForm.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, valor, Constants.TODAS);
		
	}

	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public List getLetRegionesCerrdasList() {
		return letRegionesCerradasList;
	}

	public void setLetRegionesCerrdasList(List letRegionesCerrdasList) {
		this.letRegionesCerradasList = letRegionesCerrdasList;
	}
}
