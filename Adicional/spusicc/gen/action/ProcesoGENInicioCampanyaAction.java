package biz.belcorp.ssicc.web.spusicc.gen.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spusicc.gen.form.ProcesoGENInicioCampanyaForm;

@ManagedBean
@SessionScoped
public class ProcesoGENInicioCampanyaAction extends BaseInterfazAbstractAction {

	private static final long serialVersionUID = 4460488242489358779L;
	private List siccMarcaList;
	private List siccCanalList;
	private boolean opcionPeriodoFecha;
	private String bloqueo;

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Executing :  setViewAttributes. ");
		ProcesoGENInicioCampanyaForm f = (ProcesoGENInicioCampanyaForm) this.formInterfaz;
		Pais pais =  this.mPantallaPrincipalBean.getCurrentCountry();		
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codigoProceso =	this.parametrosPantalla.get("codigoProcesoBatch");
		String indTipoValid =this.parametrosPantalla.get("indTipoValid");		
		
		Map criteria = new HashMap();
		criteria.put("codigoPais",pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO);  
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO);
        InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
        
        siccMarcaList =  service.getMarcas();		
		siccCanalList =  service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		//f.setHabilitadorHidden(Constants.NUMERO_CERO);
		setBloqueo("true");
		setOpcionPeriodoFecha(true);
        
		MantenimientoOCRPedidoControlFacturacionService serviceFaturacion = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceFaturacion.getControlFacturacionById(criteria);
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setFechaProceso(controlFacturacion.getFechaProceso());
		f.setFechaProcesoD(DateUtil.convertStringToDate(f.getFechaProceso()));
		
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		
		//session.setAttribute("alturaVariableInterfaz", "200px");
	}	

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		
		Usuario usuario =  this.mPantallaPrincipalBean.getCurrentUser();
		ProcesoGENInicioCampanyaForm f = (ProcesoGENInicioCampanyaForm) this.formInterfaz;
		f.setFechaProceso(DateUtil.convertDateToString(f.getFechaProcesoD()));
		params = super.prepareParamsBeforeExecute(params, form);
		String indTipoValid =this.parametrosPantalla.get("indTipoValid");
		params.put("periodoProceso", f.getCodigoPeriodo()); 
		params.put("fechaProceso", f.getFechaProceso());
		params.put("login", usuario.getLogin());
		params.put("indTipoValid", indTipoValid);
		params.put("codigoRegion", "");
				
		return params;
	}

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub}
		ProcesoGENInicioCampanyaForm form = new ProcesoGENInicioCampanyaForm();
		return form;
	}

	public void loadPeriodoFecha(ValueChangeEvent val) throws Exception 
	{
		String opcion = (String) val.getNewValue().toString();
		ProcesoGENInicioCampanyaForm f = (ProcesoGENInicioCampanyaForm) this.formInterfaz;
				
		if (opcion == "true") {
			setOpcionPeriodoFecha(false);
			setBloqueo("false");
			f.setHabilitadorHidden(Constants.NUMERO_UNO);
		} else {
			setOpcionPeriodoFecha(true);
			setBloqueo("true");
			f.setCodigoPeriodo(f.getCodigoPeriodo());
			f.setFechaProcesoD(DateUtil.convertStringToDate(f.getFechaProceso()));
		}
	}
	
	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * @return the opcionPeriodoFecha
	 */
	public boolean isOpcionPeriodoFecha() {
		return opcionPeriodoFecha;
	}

	/**
	 * @param opcionPeriodoFecha the opcionPeriodoFecha to set
	 */
	public void setOpcionPeriodoFecha(boolean opcionPeriodoFecha) {
		this.opcionPeriodoFecha = opcionPeriodoFecha;
	}

	/**
	 * @return the bloqueo
	 */
	public String getBloqueo() {
		return bloqueo;
	}

	/**
	 * @param bloqueo the bloqueo to set
	 */
	public void setBloqueo(String bloqueo) {
		this.bloqueo = bloqueo;
	}
}