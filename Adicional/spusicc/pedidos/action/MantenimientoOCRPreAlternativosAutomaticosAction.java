package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMMinimoNuevasService;
import biz.belcorp.ssicc.service.spusicc.men.MantenimientoMENIngresoGerenteZonalesService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPreAlternativosAutomaticosService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMMinimoNuevasForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMMinimoNuevasSearchForm;
import biz.belcorp.ssicc.web.spusicc.men.form.MantenimientoMENEscaleraGananciaForm;
import biz.belcorp.ssicc.web.spusicc.men.form.MantenimientoMENEscaleraGananciaSearchForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoOCRNuevoAlternativoAutomaticoForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoOCRPreAlternativosAutomaticosForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoOCRPreAlternativosAutomaticosAction.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:croman@csigcomt.com">Cristhian Roman
 *         </a>
 */
@ManagedBean
@SessionScoped
public class MantenimientoOCRPreAlternativosAutomaticosAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -2039671691149644047L;
	
	private List msgMensajeList;
	private String rangoInicialActual;
	private String rangoFinalActual;
	private String codigoEscalonActual;
	private String oidMensajeActual;
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoOCRPreAlternativosAutomaticosForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		return new MantenimientoOCRPreAlternativosAutomaticosForm();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("'setViewAttributes'");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoOCRPreAlternativosAutomaticosForm f = (MantenimientoOCRPreAlternativosAutomaticosForm) this.formBusqueda; 		
		f.setCodigoPais(pais.getCodigo());
		f.setCodigoPeriodo("");//ya no se usa
		f.setCodigoSAPAlternativo("");
		f.setCodigoSAPPrincipal("");
		
		this.setMostrarBotonModificar(false);
		this.setMostrarBotonConsultar(false);
		this.setMostrarBotonEliminar(false);
	}

	
	@Override
	protected void setAddAttributes() throws Exception {
		log.debug("'setAddAttributes'");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();	
		MantenimientoOCRNuevoAlternativoAutomaticoForm f = (MantenimientoOCRNuevoAlternativoAutomaticoForm) this.formMantenimiento;
		
		f.setCodigoPais(pais.getCodigo());

		f.setCodigoSAPPrincipal("");
		f.setCodigoSAPAlternativo("");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		log.debug("'setFindAttributes'");
	
		List list = null;
		try {

		MantenimientoOCRPreAlternativosAutomaticosForm f = (MantenimientoOCRPreAlternativosAutomaticosForm) this.formBusqueda;
		MantenimientoOCRPreAlternativosAutomaticosService service = (MantenimientoOCRPreAlternativosAutomaticosService)getBean("spusicc.pedidos.mantenimientoOCRPreAlternativosAutomaticosService");
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		//criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("codigoSAPPrincipal", f.getCodigoSAPPrincipal());
		criteria.put("codigoSAPAlternativo", f.getCodigoSAPAlternativo());
		
		list = service.getListaAlternativosAutomaticos(criteria);
		
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) {
			   error = e.getLocalizedMessage();
			}
		   throw new Exception(this.getResourceMessage("errors.detail", new Object[]{ error }));	
		}
		
		return list;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {	
		log.debug("'setDeleteAttributes'");
		return true;
	}
	
	@Override
	protected String getSalirForward() {
		return "mantenimientoOCRPreAlternativosAutomaticosList";
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		log.debug("entrando a: 'setSaveAttributes'");
        Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		MantenimientoOCRNuevoAlternativoAutomaticoForm f =(MantenimientoOCRNuevoAlternativoAutomaticoForm)this.formMantenimiento;
		MantenimientoOCRPreAlternativosAutomaticosService service = (MantenimientoOCRPreAlternativosAutomaticosService)getBean("spusicc.pedidos.mantenimientoOCRPreAlternativosAutomaticosService");

		 Map criteria = new HashMap(); 
		 criteria.put("codigoPais", pais.getCodigo());
		 criteria.put("usuario", usuario.getLogin());
		 criteria.put("tipoAccion", Constants.TIPO_ACCION_INSERCION);
		 //criteria.put("codigoPeriodo", f.getCodigoPeriodo(8));
		 criteria.put("codigoSAPPrincipal", f.getCodigoSAPPrincipal());
		 criteria.put("codigoSAPAlternativo", f.getCodigoSAPAlternativo());
			
		try{
			if(f.isNewRecord()){			
			//	service.insertPreAlternativosAutomaticos(criteria);
			}
		}catch(Exception e){
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) {
				error = e.getLocalizedMessage();
			}
			throw new Exception(this.getResourceMessage("errors.detail", new Object[]{ error }));				
		}

		return true;
	}
	
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {

		MantenimientoOCRNuevoAlternativoAutomaticoForm mForm = new MantenimientoOCRNuevoAlternativoAutomaticoForm();
		
		return mForm;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	/*@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoOCRNuevoAlternativoAutomaticoForm mantenimientoForm = (MantenimientoOCRNuevoAlternativoAutomaticoForm) this.formMantenimiento;
		boolean isNew = mantenimientoForm.isNewRecord();
		if(isNew) {
			return "mantenimientoMENEscaleraGananciaForm.cabecera.insert";
		}else{
			return "mantenimientoMENEscaleraGananciaForm.cabecera.update";
		}	
	}*/
	
	@Override
	public String setValidarMantenimiento() {
		MantenimientoOCRNuevoAlternativoAutomaticoForm f = (MantenimientoOCRNuevoAlternativoAutomaticoForm) this.formMantenimiento;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		String sap = null;
		sap = ajax.validarCodigoSAP(f.getCodigoPais(),f.getCodigoSAPPrincipal());
		if (StringUtils.isBlank(sap)) {
			String mensaje = "El codigo de SAP principal no existe";
			return mensaje;
		}
		
		sap = ajax.validarCodigoSAP(f.getCodigoPais(),f.getCodigoSAPAlternativo());
		if (StringUtils.isBlank(sap)) {
			String mensaje = "El codigo de SAP alternativo no existe";
			return mensaje;
		}
		
		return null;
	}
	 
	public List getMsgMensajeList() {
		return msgMensajeList;
	}

	public void setMsgMensajeList(List msgMensajeList) {
		this.msgMensajeList = msgMensajeList;
	}

	public String getRangoInicialActual() {
		return rangoInicialActual;
	}

	public void setRangoInicialActual(String rangoInicialActual) {
		this.rangoInicialActual = rangoInicialActual;
	}

	public String getRangoFinalActual() {
		return rangoFinalActual;
	}

	public void setRangoFinalActual(String rangoFinalActual) {
		this.rangoFinalActual = rangoFinalActual;
	}

	public String getCodigoEscalonActual() {
		return codigoEscalonActual;
	}

	public void setCodigoEscalonActual(String codigoEscalonActual) {
		this.codigoEscalonActual = codigoEscalonActual;
	}

	public String getOidMensajeActual() {
		return oidMensajeActual;
	}

	public void setOidMensajeActual(String oidMensajeActual) {
		this.oidMensajeActual = oidMensajeActual;
	}

	
	

	
}
