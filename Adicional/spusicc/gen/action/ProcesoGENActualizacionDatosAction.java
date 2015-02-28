package biz.belcorp.ssicc.web.spusicc.gen.action;

import java.text.ParseException;
import java.util.ArrayList;
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
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spusicc.gen.form.ProcesoGENActualizacionDatosForm;

@ManagedBean
@SessionScoped
public class ProcesoGENActualizacionDatosAction  extends BaseInterfazAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3666208239885439931L;

	private List genInterfazPaquete;
	private boolean opcionPeriodoFecha;
	
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		ProcesoGENActualizacionDatosForm formInterfaz = new ProcesoGENActualizacionDatosForm();
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		ProcesoGENActualizacionDatosForm f = (ProcesoGENActualizacionDatosForm) this.formInterfaz;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);

		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setFechaFacturacion(controlFacturacion.getFechaProceso());
		f.setCodigoPeriodoActual(controlFacturacion.getCodigoPeriodo());
		f.setHabilitadorHidden(Constants.NUMERO_CERO);
		f.setFechaFacturacionDate(DateUtil.convertStringToDate(f.getFechaFacturacion()));
		
		List lista = new ArrayList();
		
		criteria.put("codigoInterfaz", f.getCodigoInterfaz());

		lista = interfazSiCCService.getListaProcesosGenCierreCampania(criteria);
		this.genInterfazPaquete = lista;
		setOpcionPeriodoFecha(true);
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		ProcesoGENActualizacionDatosForm f = (ProcesoGENActualizacionDatosForm) this.formInterfaz;
		f.setFechaFacturacion(DateUtil.convertDateToString(f.getFechaFacturacionDate()));
		params = super.prepareParamsBeforeExecute(params, form); 
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();		
		
		params.put("login", usuario.getLogin());	    
    	params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
    	
		return params;
	}
	
	public void loadPeriodoFecha(ValueChangeEvent val) throws Exception 
	{
		String opcion = (String) val.getNewValue().toString();
		ProcesoGENActualizacionDatosForm f = (ProcesoGENActualizacionDatosForm) this.formInterfaz;
				
		if (opcion == "true") {
			setOpcionPeriodoFecha(false);
			f.setHabilitadorHidden(Constants.NUMERO_UNO);
		} else {
			setOpcionPeriodoFecha(true);
			f.setCodigoPeriodo(f.getCodigoPeriodoActual());
		}
	}
	
	public String setValidarInterfaz() 
	{
		ProcesoGENActualizacionDatosForm f = (ProcesoGENActualizacionDatosForm) this.formInterfaz;
		String mensaje = null;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		
		String periodo = f.getCodigoPeriodo();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String fechaDesde = ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT, periodo);
		String fechaHasta = ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT, periodo);

			try {
				if(f.getFechaFacturacionDate().after(DateUtil.convertStringToDate(fechaHasta)) ||
						f.getFechaFacturacionDate().before(DateUtil.convertStringToDate(fechaDesde)))
				{
					mensaje = this
							.getResourceMessage("errors.compare.campaigns.periodo.fechaFacturacion")
							+ " (" + fechaDesde + " - " + fechaHasta + ")";
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return mensaje;
	}

	public List getGenInterfazPaquete() {
		return genInterfazPaquete;
	}

	public void setGenInterfazPaquete(List genInterfazPaquete) {
		this.genInterfazPaquete = genInterfazPaquete;
	}

	public boolean isOpcionPeriodoFecha() {
		return opcionPeriodoFecha;
	}

	public void setOpcionPeriodoFecha(boolean opcionPeriodoFecha) {
		this.opcionPeriodoFecha = opcionPeriodoFecha;
	}

}
