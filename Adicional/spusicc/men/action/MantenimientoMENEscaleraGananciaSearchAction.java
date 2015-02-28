package biz.belcorp.ssicc.web.spusicc.men.action;

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
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMMinimoNuevasForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMMinimoNuevasSearchForm;
import biz.belcorp.ssicc.web.spusicc.men.form.MantenimientoMENEscaleraGananciaForm;
import biz.belcorp.ssicc.web.spusicc.men.form.MantenimientoMENEscaleraGananciaSearchForm;


@ManagedBean
@SessionScoped
public class MantenimientoMENEscaleraGananciaSearchAction extends BaseMantenimientoSearchAbstractAction {

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
		return "mantenimientoMENEscaleraGananciaForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		return new MantenimientoMENEscaleraGananciaSearchForm();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("'setViewAttributes'");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoMENEscaleraGananciaSearchForm f = (MantenimientoMENEscaleraGananciaSearchForm) this.formBusqueda; 		
		f.setCodigoPais(pais.getCodigo());

	}
	
	public List listarCodigosMensajes(String codigoPais){
		MantenimientoMENIngresoGerenteZonalesService service = (MantenimientoMENIngresoGerenteZonalesService) getBean("spusicc.mantenimientoMENIngresoGerenteZonalesService");
		
		Map map= new HashMap();
		map.put("codigoPais", codigoPais);
		map.put("prefijo", Constants.PREFIJO_EGA);	
		
		return service.getCodigosMensaje(map);
	}
	
	@Override
	protected void setAddAttributes() throws Exception {
		log.debug("'setAddAttributes'");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();	
		MantenimientoMENEscaleraGananciaForm f = (MantenimientoMENEscaleraGananciaForm) this.formMantenimiento;
		
		f.setCodigoPais(pais.getCodigo());
		msgMensajeList = listarCodigosMensajes(pais.getCodigo());
		
		this.setRangoFinalActual("");
		this.setRangoInicialActual("");
		this.setCodigoEscalonActual("");
		this.setOidMensajeActual("");
		
		f.setCodigoEscalon("");
		f.setRangoInicial("");
		f.setRangoFinal("");
		f.setCodigoMensaje("");
		f.setOidMensaje("");
		f.setIndicadorActivo(Constants.NUMERO_UNO);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		log.debug("'setFindAttributes'");
	
		List list = null;
		try {
			
		MantenimientoMENIngresoGerenteZonalesService service = (MantenimientoMENIngresoGerenteZonalesService) getBean("spusicc.mantenimientoMENIngresoGerenteZonalesService");
		MantenimientoMENEscaleraGananciaSearchForm f = (MantenimientoMENEscaleraGananciaSearchForm) this.formBusqueda;
	
		//enviando en session los parametros de mensaje
		Map map = new HashMap();
		map.put("codigoPais", f.getCodigoPais());
		map.put("codigoEscalon", f.getCodigoEscalon());
		list = service.getEscaleraGanaciaMensaje(map);
		
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
		 Map map = (Map) this.beanRegistroSeleccionado;
		 MantenimientoMENEscaleraGananciaSearchForm f = (MantenimientoMENEscaleraGananciaSearchForm) this.formBusqueda;
		 Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

			try {							
				MantenimientoMENIngresoGerenteZonalesService service = (MantenimientoMENIngresoGerenteZonalesService) getBean("spusicc.mantenimientoMENIngresoGerenteZonalesService");								

				map.put("codigoPais", f.getCodigoPais());
				map.put("login", usuario.getLogin());
				
				service.deleteEstadoGananciaMensaje(map);
				//enviamos el mensaje de satisfaccion
				/*messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"mantenimientoMENEscaleraGananciaCronogramaSearchForm.cabecera.deleted"));
				saveMessages(request.getSession(), messages);
				*/
			}catch (Exception e) {
				String error = e.getMessage();
				if (StringUtils.isBlank(error)) {
					error = e.getLocalizedMessage();
				}
				throw new Exception(this.getResourceMessage("errors.detail", new Object[]{ error }));
		}
		
		return true;
	}
	
	@Override
	protected String getSalirForward() {
		return "mantenimientoMENEscaleraGananciaList";
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		log.debug("entrando a: 'setSaveAttributes'");
        Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoMENIngresoGerenteZonalesService service = (MantenimientoMENIngresoGerenteZonalesService) getBean("spusicc.mantenimientoMENIngresoGerenteZonalesService");
		MantenimientoMENEscaleraGananciaForm f = (MantenimientoMENEscaleraGananciaForm) this.formMantenimiento;
		Map map = new HashMap();
		map.put("codigoPais", pais.getCodigo());	
		map.put("indicadorActivo", f.getIndicadorActivo());
		map.put("oidMensaje",f.getOidMensaje());
		map.put("rangoInicial",f.getRangoInicial());
		map.put("rangoFinal",f.getRangoFinal());	
		map.put("login",usuario.getLogin());		
		boolean existe=false;
		//obtenemos los rangos iniciales no modificados
		String rinicial = this.getRangoInicialActual();
		String rfinal=	this.getRangoFinalActual();
		String cescalon = this.getCodigoEscalonActual();
		if(!(f.getRangoInicial().equals(rinicial) && f.getRangoFinal().equals(rfinal))){
			//se valida que no se traslapen
			map.put("cescalon",cescalon);
			existe = service.getExisteRangoTraslapado(map);		
			if(existe){	
				    String msg = this.getResourceMessage("mantenimientoMENEscaleraGananciaForm.existe.registro.traslapado");
				    addError("",msg);
					return false;			
					
			}	
		}
		String oidMensajeActual= this.getOidMensajeActual();
		if(!f.getOidMensaje().equals(oidMensajeActual)){//si son iguales no valido se trata del mismo objeto
			//validamos que no existan oid mensaje para un rango ya ingresado		
			existe = service.getExisteMensajeRangos(map);		
			if(existe){
					 String msg = this.getResourceMessage("mantenimientoMENEscaleraGananciaForm.existe.registro.rango");
					 addError("", msg);
				return false;
			}		
		}
		
		try{
			if(f.isNewRecord()){			
			   service.insertEscaleraGananciaMensaje(map);//inserta
			}
			else{
				map.put("codigoEscalon", f.getCodigoEscalon());
				service.updateEscaleraGananciaMensaje(map);//upadte
				
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
		Map mapa = (Map) this.beanRegistroSeleccionado;
		MantenimientoMENEscaleraGananciaSearchForm form = (MantenimientoMENEscaleraGananciaSearchForm) this.formBusqueda;
		MantenimientoMENEscaleraGananciaForm mForm = new MantenimientoMENEscaleraGananciaForm();
		
		BeanUtils.populate(mForm, mapa);

		if (this.accion.equals(this.ACCION_CONSULTAR) || this.accion.equals(this.ACCION_MODIFICAR)) {
			this.setOidMensajeActual(mForm.getOidMensaje());
			this.setRangoInicialActual(mForm.getRangoInicial());
			this.setRangoFinalActual(mForm.getRangoFinal());
			this.setCodigoEscalonActual(mForm.getCodigoEscalon());
		}
		
		if(this.accion.equals(this.ACCION_CONSULTAR)){
			this.setMostrarBotonSave(false);
		}else{
			this.setMostrarBotonSave(true);
		}
		
		if(!this.accion.equals(this.ACCION_NUEVO)){
			mForm.setNewRecord(false);
		}
		
		msgMensajeList = listarCodigosMensajes(form.getCodigoPais());

		return mForm;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoMENEscaleraGananciaForm mantenimientoForm = (MantenimientoMENEscaleraGananciaForm) this.formMantenimiento;
		boolean isNew = mantenimientoForm.isNewRecord();
		if(isNew) {
			return "mantenimientoMENEscaleraGananciaForm.cabecera.insert";
		}else{
			return "mantenimientoMENEscaleraGananciaForm.cabecera.update";
		}	
	}
	
	@Override
	public String setValidarMantenimiento() {
		MantenimientoMENEscaleraGananciaForm f = (MantenimientoMENEscaleraGananciaForm) this.formMantenimiento;
		int rangoInicial = Integer.valueOf(f.getRangoInicial());
		int rangoFinal = Integer.valueOf(f.getRangoFinal());
		
		if (rangoInicial >= rangoFinal) {
				String mensaje = this.getResourceMessage("mantenimientoMENEscaleraGananciaForm.rango.mayor");
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
