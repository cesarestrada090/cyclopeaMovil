package biz.belcorp.ssicc.web.spusicc.inc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.spusicc.inc.form.ReporteINCEstadoPremioDespachadoForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.inc.ProcesoINCGenerarReporteIncentivosService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

/**
 * 
 * @author <a href="">Sergio Buchelli Silva</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteINCGenerarReporteIncentivosAction extends BaseReporteAbstractAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3077245934908815253L;
	
	private List listaMarca;
	private List listaCanal;
	private List listaTipoCierre;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {		
		ReporteINCEstadoPremioDespachadoForm reporteForm = new ReporteINCEstadoPremioDespachadoForm();
		return reporteForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	protected String devuelveNombreReporte() throws Exception {				
		ReporteINCEstadoPremioDespachadoForm reporteForm = (ReporteINCEstadoPremioDespachadoForm)this.formReporte;
		String formatoReporte = reporteForm.getFormatoExportacion();
		
		if(formatoReporte.equals("XLS") || formatoReporte.equals("CSV") || formatoReporte.equals("XLSX")){
			return "reporteINCPremiosDespachadosXLS";			
		}		
		return "reporteMaestroHorizontal";		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {		
		
		ReporteINCEstadoPremioDespachadoForm reporteForm = (ReporteINCEstadoPremioDespachadoForm)this.formReporte;
		String formatoReporte = reporteForm.getFormatoExportacion();
		
		if(formatoReporte.equals("XLS") || formatoReporte.equals("CSV") || formatoReporte.equals("XLSX")){
			return "";
		}
		return "reporteINCPremiosDespachadosPDF";		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanConstructorService()
	 */
	protected String devuelveBeanReporteService(){
		return "reportes.reporteINCGenerarReporteIncentivosService";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanMailService()
	 */
	protected String devuelveBeanMailService(){
		return "inc.mailReporteGenerarReporteIncentivosService";
	}
	
	/* Realiza la carga Inicial 
	 * (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {		
		if (this.log.isDebugEnabled()) {
            this.log.debug("Entering 'ReporteINCEstadoPremioDespachadoAction.setViewAtributes' method");            
        }
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		ReporteINCEstadoPremioDespachadoForm reporteForm = (ReporteINCEstadoPremioDespachadoForm)this.formReporte;
		
		InterfazSiCCService service = (InterfazSiCCService)this.getBeanService("sisicc.interfazSiCCService");
		/*
		Map params = new HashMap();
		params.put("codigoISO", usuario.getIdioma().getCodigoISO());				
		params.put("codigoPais", pais.getCodigo());
		*/
		this.listaMarca = (List)service.getMarcas();
		this.listaCanal = (List)service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		
		this.listaTipoCierre = this.loadProcesos();
		
		/*obteniendo periodo actual*/
		PedidoControlFacturacion controlFacturacion = getPedidoControlFacturacion();		
		//reporteForm.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		
		reporteForm.setTipoCierre(Constants.INC_TIPO_CIERRE_DIARIO);
			
		this.mostrarReporteXLS = true;
		this.mostrarEnviarEmail = true;
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception{	

		log.debug("Entering to ReporteINCConsolidadoPremioDespachadoAction - prepareParameterMap");
		
		ReporteINCEstadoPremioDespachadoForm reporteForm = (ReporteINCEstadoPremioDespachadoForm)this.formReporte;
			
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoPais = pais.getCodigo();
			
		ReporteService reporteService = (ReporteService) this.getBeanService("scsicc.reporteService");				
		
		Map criteria = params;
		criteria.put("codigoPais", codigoPais);
		criteria.put("nombreReporte","reporteINCPremiosDespachadosPDF");
		
		Map paramReporte = reporteService.getParametrosReporte(criteria);
				
		if(paramReporte == null){
			//Muestra mensaje de error 
			/** TODO */
			/*
			ActionMessages msg = new ActionMessages();
			MessageResources messageResources = getResources(request);
			String msgWarning = messageResources.getMessage(
					"procesoINCGenerarReporteEstadoPremioDespachadoForm.no.configuracion.envio.mail");
			msg.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.detail",msgWarning));
			saveErrors(request, msg);
			reporteForward = "view";
			*/
		}else{
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser(); 
			
			reporteForm.setNroReporte("");
			reporteForm.setTitulo(this.getReportResourceMessage("reporteINCConsolidadoPremioDespachadoForm.title"));
			reporteForm.setCodigoPais(codigoPais);
			reporteForm.setUsuario(usuario);
			reporteForm.setCodigoUsuario(usuario.getLogin());
		}
		
		return params;
		
	}

	/**
	 * Retorna una lista con los tipos de comision Recuperacion y Actividad
	 * @param request
	 * @return
	 */
	private List loadProcesos() {
		List resultado=new ArrayList();
		Base[] mes = new Base[4];
		
		String tipoDiario = this.getResourceMessage("procesoINCGenerarReporteIncentivosForm.tipoDiario"); 
		String tipoRegion = this.getResourceMessage("procesoINCGenerarReporteIncentivosForm.tipoRegion");				
		String tipoZona = this.getResourceMessage("procesoINCGenerarReporteIncentivosForm.tipoZona");								
		String tipoCampana = this.getResourceMessage("procesoINCGenerarReporteIncentivosForm.tipoCampana"); 
				
		mes[0] = new Base();
		mes[0].setCodigo(Constants.INC_TIPO_CIERRE_DIARIO);//RECUPERACION
		mes[0].setDescripcion(tipoDiario);
		resultado.add(mes[0]);
		
		mes[1] = new Base();
		mes[1].setCodigo(Constants.INC_TIPO_CIERRE_REGION);
		mes[1].setDescripcion(tipoRegion);
		resultado.add(mes[1]);

		mes[2] = new Base();
		mes[2].setCodigo(Constants.INC_TIPO_CIERRE_ZONA);
		mes[2].setDescripcion(tipoZona);
		resultado.add(mes[2]);

		mes[3] = new Base();
		mes[3].setCodigo(Constants.INC_TIPO_CIERRE_CAMPANA);
		mes[3].setDescripcion(tipoCampana);
		resultado.add(mes[3]);
		return resultado;
	}

	/**
	 * Retorna el objeto del periodo de facturacion
	 * @param request
	 * @return
	 */
	private PedidoControlFacturacion getPedidoControlFacturacion() {
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)
				this.getBeanService("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");				
		Map criteria = new HashMap();
		criteria.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa,Estado Abierto 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
        PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
		return controlFacturacion;
	}
	
	/* 
	 * Devuelve la cantida de reportes a generar en base ala region y sus zonas correspondientes
	 * (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#getNroReportesAGenerar()
	 */
	protected int getNroReportesAGenerar() {		
		ReporteINCEstadoPremioDespachadoForm f = (ReporteINCEstadoPremioDespachadoForm) this.getFormReporte();		
		return (f.getNumRegistros() == 0)?0:1;		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#errorKeyGenerarMultipleReporte()
	 */
	protected String errorKeyGenerarMultipleReporte() {
		return "reporteINCGenerarReporteIncentivos.msg.envioMailCronograma.vacio";						
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#exitoKeyEnvioReporteViaMail()
	 */
	protected String exitoKeyEnvioReporteViaMail() {
		return "reporteINCGenerarReporteIncentivos.msg.envioMailCronograma";						
	}

	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#mensajeErrorValidacionPrevia()
	 */
	public String setValidarReporte(){
		
        ReporteINCEstadoPremioDespachadoForm f = (ReporteINCEstadoPremioDespachadoForm) this.getFormReporte(); 
		
		Map  map = new HashMap();
		ProcesoINCGenerarReporteIncentivosService service =	(ProcesoINCGenerarReporteIncentivosService)
				this.getBeanService("spusicc.procesoINCGenerarReporteIncentivosService");									
		map.put("tipoCierre", f.getTipoCierre());
		map.put("fechaProceso",f.getFechaProceso());
		
		Integer size =service.getSizeEstadoPremioDespacho(map);
		
		if(size!=null && size.intValue()==0){		
			//request.getSession().removeAttribute("numeroLoteReporteDespachoInc");
			f.setNumRegistros(0);						
			String msg1 = this.getResourceMessage("reporteINCEstadoPremioDespachadoForm.no.informacion.reporte1");
			String msg2 = this.getResourceMessage("reporteINCEstadoPremioDespachadoForm.no.informacion.envio.reporte2");

			String m2 = "";
			
			String tipoCierre = f.getTipoCierre();
			
			if (tipoCierre.equals(Constants.INC_TIPO_CIERRE_DIARIO)){
				m2 = this.getResourceMessage("procesoINCGenerarReporteIncentivosForm.tipoDiario");
			}else if(tipoCierre.equals(Constants.INC_TIPO_CIERRE_REGION)){
				m2 = this.getResourceMessage("procesoINCGenerarReporteIncentivosForm.tipoRegion");
			}else if(tipoCierre.equals(Constants.INC_TIPO_CIERRE_ZONA)){
				m2 = this.getResourceMessage("procesoINCGenerarReporteIncentivosForm.tipoZona");
			}else if(tipoCierre.equals(Constants.INC_TIPO_CIERRE_CAMPANA)){
				m2 = this.getResourceMessage("procesoINCGenerarReporteIncentivosForm.tipoCampana");
			}
					
			return msg1 + " " + f.getFechaProceso() + " " + msg2 + " "+ m2;
		}
		
		f.setNumRegistros(size.intValue());
		return "";
	}
		
	/**
	 * @return the listaMarca
	 */
	public List getListaMarca() {
		return listaMarca;
	}

	/**
	 * @param listaMarca the listaMarca to set
	 */
	public void setListaMarca(List listaMarca) {
		this.listaMarca = listaMarca;
	}

	/**
	 * @return the listaCanal
	 */
	public List getListaCanal() {
		return listaCanal;
	}

	/**
	 * @param listaCanal the listaCanal to set
	 */
	public void setListaCanal(List listaCanal) {
		this.listaCanal = listaCanal;
	}
	
	/**
	 * @return the listaTipoCierre
	 */
	public List getListaTipoCierre() {
		return listaTipoCierre;
	}

	/**
	 * @param listaTipoCierre the listaTipoCierre to set
	 */
	public void setListaTipoCierre(List listaTipoCierre) {
		this.listaTipoCierre = listaTipoCierre;
	}

}