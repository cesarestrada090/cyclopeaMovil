package biz.belcorp.ssicc.web.spusicc.sto.action;


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
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitado;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.sto.form.ProcesoSTOAlmacenamientoHistoricoForm;

@ManagedBean
@SessionScoped
public class ProcesoSTOAlmacenamientoHistoricoAction extends BaseProcesoAbstractAction {
	
	private static final long serialVersionUID = 6970057243123347398L;
	private List stoTipoDocumentoList;
	
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute (Map params, BaseForm form)
	throws Exception{		
		Pais pais =  this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario =  this.mPantallaPrincipalBean.getCurrentUser();
		ProcesoSTOAlmacenamientoHistoricoForm f = (ProcesoSTOAlmacenamientoHistoricoForm)this.formProceso;
		f.setFechaInicio(DateUtil.convertDateToString(f.getFechaInicioD()));
		f.setFechaFin(DateUtil.convertDateToString(f.getFechaFinD()));
		params=super.prepareParamsBeforeExecute(params, form);
		params.put("codigoTipo",f.getTipoDocumento());
		params.put("codigoPais",pais.getCodigo());
		params.put("numLote",f.getNumeroLote());
    	params.put("fechaInicio",f.getFechaInicio());
    	params.put("fechaFin",f.getFechaFin());
    	params.put("codigoPeriodo",f.getCodigoPeriodo());
		return params;
	}

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoSTOAlmacenamientoHistoricoForm form = new ProcesoSTOAlmacenamientoHistoricoForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		ProcesoSTOAlmacenamientoHistoricoForm f = (ProcesoSTOAlmacenamientoHistoricoForm)this.formProceso;    	
    	ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
    	
    	TipoDocumentoDigitado  tipoDocumentoDigitado=(TipoDocumentoDigitado)procesoSTOEjecucionValidacionesService.getTipoDocumentoDigitado(f.getTipoDocumento());
    	procesoSTOEjecucionValidacionesService.executeAlmacenamientoHistorico(tipoDocumentoDigitado.getExeProcHist(),params);

    	return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		Pais pais =  this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario =  this.mPantallaPrincipalBean.getCurrentUser();
		log.debug("Executing action : setViewAttributes.");
		//session.setAttribute("errorProceso", Constants.NO);
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		Map criteria = new HashMap();
		
		criteria.put("codigoPais",pais.getCodigo());
		criteria.put("codigoUsuario", usuario.getLogin());
		
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		stoTipoDocumentoList = procesoSTOEjecucionValidacionesService.getTiposDocumentosHistoricoSTO(criteria);
		
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
	     
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaPeriodo);
		 
		ProcesoSTOAlmacenamientoHistoricoForm f =(ProcesoSTOAlmacenamientoHistoricoForm) this.formProceso;
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 
		 f.setFechaInicioD(new Date(System.currentTimeMillis()));
		 f.setFechaFinD(new Date(System.currentTimeMillis()));
	}

	/**
	 * @return the stoTipoDocumentoList
	 */
	public List getStoTipoDocumentoList() {
		return stoTipoDocumentoList;
	}

	/**
	 * @param stoTipoDocumentoList the stoTipoDocumentoList to set
	 */
	public void setStoTipoDocumentoList(List stoTipoDocumentoList) {
		this.stoTipoDocumentoList = stoTipoDocumentoList;
	}
	
	
 }