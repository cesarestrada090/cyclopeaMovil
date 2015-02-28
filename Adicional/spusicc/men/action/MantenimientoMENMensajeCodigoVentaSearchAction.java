package biz.belcorp.ssicc.web.spusicc.men.action;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.aop.framework.adapter.ThrowsAdviceInterceptor;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.LabelValueCUV;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.Flete;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.FleteDetalle;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMMinimoNuevasService;
import biz.belcorp.ssicc.service.spusicc.men.MantenimientoMENIngresoGerenteZonalesService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDClasificacionesChequeoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDFleteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMMinimoNuevasForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMMinimoNuevasSearchForm;
import biz.belcorp.ssicc.web.spusicc.men.form.MantenimientoMENEscaleraGananciaForm;
import biz.belcorp.ssicc.web.spusicc.men.form.MantenimientoMENEscaleraGananciaSearchForm;
import biz.belcorp.ssicc.web.spusicc.men.form.MantenimientoMENMensajeCodigoVentaForm;
import biz.belcorp.ssicc.web.spusicc.men.form.MantenimientoMENMensajeCodigoVentaSearchForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDFleteDetalleForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDFleteForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDFleteSearchForm;

/**
 * @author Giovanni Ascarza
 */
@ManagedBean
@SessionScoped
public class MantenimientoMENMensajeCodigoVentaSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -2039671691149644047L;
	
	private List msgMensajeList;
	private DataTableModel dataModelDetalle;
	private List listaDetalle;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoMENMensajeCodigoVentaForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		return new MantenimientoMENMensajeCodigoVentaSearchForm();
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("'setViewAttributes'");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoMENMensajeCodigoVentaSearchForm f = (MantenimientoMENMensajeCodigoVentaSearchForm) this.formBusqueda; 		
		MantenimientoMENIngresoGerenteZonalesService service = (MantenimientoMENIngresoGerenteZonalesService) getBean("spusicc.mantenimientoMENIngresoGerenteZonalesService");
		
		f.setCodigoPais(pais.getCodigo());
		
		Map hmap= new HashMap();
		hmap.put("codigoPais", pais.getCodigo());
		hmap.put("prefijo", Constants.PREFIJO_EGA);		
		
		msgMensajeList = service.getCodigosMensaje(hmap);
	}
	
	@Override
	protected void setAddAttributes() throws Exception {
		log.debug("'setAddAttributes'");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();	
		MantenimientoMENMensajeCodigoVentaForm form = (MantenimientoMENMensajeCodigoVentaForm) this.formMantenimiento;
		form.setIndicadorActivo(Constants.NUMERO_UNO);
		form.setCodigoPais(pais.getCodigo());
		form.setCodigoVenta("");
		form.setOidMensaje("");
		form.setCampanhaProceso("");
	}
	
	/**
	 * retorna el codigo mensaje buscado por oid
	 * @param oidMensaje
	 * @param session
	 * @return
	 */
	private String getCodigoMensaje(String oidMensaje) {
		List list= this.getMsgMensajeList();
		Iterator it = list.iterator();
		while (it.hasNext()){
			Base base =(Base)it.next();
			String codMensaje = base.getDescripcion().split("-")[0];
			if(base.getCodigo().equals(oidMensaje))
				 return (StringUtils.isNotEmpty(codMensaje)?codMensaje.trim():"");
		}
		return "";
	}
	
	/**
	 * valida que no exitsa ya un registro duplicado con campanha , codigo
	 * venta y codigo mensaje
	 * @param list
	 * @param map
	 * @return
	 */
	private boolean isValido(List list, Map map) {
		String codigoCampanha = (String)map.get("campanhaProceso");
		String codigoVenta = (String)map.get("codigoVenta");
		String oidMensaje = (String)map.get("oidMensaje");
		Iterator it = list.iterator();
		while(it.hasNext()){
			Map mapAux =(Map)it.next();
			String codigoCampanhaAux = (String)mapAux.get("campanhaProceso");
			String codigoVentaAux = (String)mapAux.get("codigoVenta");
			String oidMensajeAux = (String)mapAux.get("oidMensaje");
			
			if(codigoCampanhaAux.equals(codigoCampanha) &&
					codigoVentaAux.equals(codigoVenta) &&
					oidMensajeAux.equals(oidMensaje) )
			    return false;
			
		}
		return true;
	}
	
	public void insertarRegistro(ActionEvent actionEvent) {
		   log.debug("'insertarRegistro'");
		   
		MantenimientoMENMensajeCodigoVentaForm f = (MantenimientoMENMensajeCodigoVentaForm) this.formMantenimiento;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");

		if(CollectionUtils.isEmpty(listaDetalle)){
			listaDetalle = new ArrayList();	
			dataModelDetalle = new DataTableModel(listaDetalle);
		}
		try{			
				Map map = BeanUtils.describe(f);
				String codigoMensaje = getCodigoMensaje(f.getOidMensaje());
				LabelValueCUV lb=ajaxService.getCodigoVentaPrecio(f.getCodigoVenta(), f.getCampanhaProceso());
				//se valida que exista en matriz de facturacion
				if(lb==null || lb.getLabel() == null){
					this.addWarn("", this.getResourceMessage("mantenimientoMENMensajeCodigoVentaForm.noexiste.campa.factu").concat(f.getCampanhaProceso()));
					return;					
				}
	            boolean valido = isValido(listaDetalle,map);
	            if(valido) {
	            	map.put("codigoMensaje", codigoMensaje);
	            	listaDetalle.add(map);
	            }
	            else{
					String sms = this.getResourceMessage("mantenimientoMENMensajeCodigoVentaForm.existe.registro", new String[]{f.getCampanhaProceso(),f.getCodigoVenta(),codigoMensaje});
					this.addWarn("", sms);
					return;
	            }
	           									
			}catch (Exception e) {
				String error = e.getMessage();
				if (StringUtils.isBlank(error)) {
				   error = e.getLocalizedMessage();
				}
			 
				this.addError("", this.getResourceMessage("errors.detail", new Object[]{ error }));		
		
			}

	}

	
	public void eliminarRegistro(ActionEvent actionEvent) {
	       log.debug("'eliminarRegistro'");
	  Object fleteDetalle =(Object)this.beanRegistroSeleccionado;
	  if (fleteDetalle != null) {
		  listaDetalle.remove(fleteDetalle);
	  }
	  
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		log.debug("'setFindAttributes'");
	
		List list = null;
		try {
			
			MantenimientoMENMensajeCodigoVentaSearchForm f = (MantenimientoMENMensajeCodigoVentaSearchForm) this.formBusqueda;
			MantenimientoMENIngresoGerenteZonalesService service = (MantenimientoMENIngresoGerenteZonalesService) getBean("spusicc.mantenimientoMENIngresoGerenteZonalesService");
			
			//enviando en session los parametros de mensaje			
			Map map = BeanUtils.describe(f);
			list = service.getMensajeCodigoVenta(map);

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
		 MantenimientoMENIngresoGerenteZonalesService service = (MantenimientoMENIngresoGerenteZonalesService) getBean("spusicc.mantenimientoMENIngresoGerenteZonalesService");								
		 MantenimientoMENMensajeCodigoVentaSearchForm f = (MantenimientoMENMensajeCodigoVentaSearchForm)this.formBusqueda;
		 Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

			try {
				map.put("login", usuario.getLogin());
				map.put("codigoPais", f.getCodigoPais());
				
				service.deleteMensajeCodigoVenta(map);
				//enviamos el mensaje de satisfaccion	
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
		return "mantenimientoMENMensajeCodigoVentaList";
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		log.debug("entrando a: 'setSaveAttributes'");
        Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoMENIngresoGerenteZonalesService service = (MantenimientoMENIngresoGerenteZonalesService) getBean("spusicc.mantenimientoMENIngresoGerenteZonalesService");
		MantenimientoMENMensajeCodigoVentaForm f = (MantenimientoMENMensajeCodigoVentaForm) this.formMantenimiento;
		Map map = BeanUtils.describe(f);
		map.put("login",usuario.getLogin());
		
		
		try{
			
			if(f.isNewRecord()){	
				List list= this.getListaDetalle();
				if(CollectionUtils.isNotEmpty(list)){
					map.put("list",list);					
					service.insertMensajeCodigoVentaMensaje(map);//upadte
				}else{
					
					this.addError("", this.getResourceMessage("mantenimientoMENMensajeCodigoVentaForm.noexiste.registros.insertar"));
					
					return false;
				}
			}
			else{

				service.updateMensajeCodigoVentaMensaje(map);//upadte
			}	
		
			
		}catch(Exception e){
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) {
				error = e.getLocalizedMessage();
			}
	
			log.debug("error " +e.getMessage() + "map " + map);
			String []split= e.getMessage().split("_");//0:periodo 1:mensaje 2:venta
			throw new Exception(this.getResourceMessage("mantenimientoMENMensajeCodigoVentaForm.existe.registro", new String[]{split[0],
					split[2],getCodigoMensaje(split[1])}));
						
		}

		return true;
	
			
	}
	
	@Override
	protected void setEditAttributes() throws Exception {
		log.debug("entrando a: 'setEditAttributes'");
		try {
			
		} catch (Exception e) {

			String error = e.getMessage();
			if (StringUtils.isBlank(error)) {
				error = e.getLocalizedMessage();
			}
			throw new Exception(this.getResourceMessage("errors.detail", new Object[]{ error }));
		}  
	}
	

	
	@Override
	protected void setConsultarAttributes() throws Exception{
		
		log.debug("entrando a: 'setConsultarAttributes'");

		MantenimientoMENMensajeCodigoVentaForm f = (MantenimientoMENMensajeCodigoVentaForm) this.formMantenimiento;
	
		try {
			//session.setAttribute("correlativo", String.valueOf(map.get("correlativo")));
				log.debug("enviando para editar");
				
		} catch (Exception e) {

			String error = e.getMessage();
			if (StringUtils.isBlank(error)) {
				error = e.getLocalizedMessage();
			}
			throw new Exception(this.getResourceMessage("errors.detail", new Object[]{ error }));
		}  
	}
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Map mapa = (Map) this.beanRegistroSeleccionado;
		MantenimientoMENMensajeCodigoVentaSearchForm form = (MantenimientoMENMensajeCodigoVentaSearchForm) this.formBusqueda;
		MantenimientoMENMensajeCodigoVentaForm mForm = new MantenimientoMENMensajeCodigoVentaForm();
		
		BeanUtils.copyProperties(mForm, form);
		
		BeanUtils.populate(mForm, mapa);
		
		if(this.accion.equals(this.ACCION_CONSULTAR)){
			this.setMostrarBotonSave(false);
		}else{
			this.setMostrarBotonSave(true);
		}
		
		if(!this.accion.equals(this.ACCION_NUEVO)){
			mForm.setNewRecord(false);
		}
		if(this.accion.equals(this.ACCION_NUEVO)){
			this.setBeanRegistroSeleccionado(null);
		}
		
		MantenimientoMENIngresoGerenteZonalesService service = (MantenimientoMENIngresoGerenteZonalesService) getBean("spusicc.mantenimientoMENIngresoGerenteZonalesService");
		Map hmap= new HashMap();
		hmap.put("codigoPais", mForm.getCodigoPais());
		hmap.put("prefijo", Constants.PREFIJO_EGA);		
		
		msgMensajeList = service.getCodigosMensaje(hmap);
		
		
		return mForm;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoMENMensajeCodigoVentaForm mantenimientoForm = (MantenimientoMENMensajeCodigoVentaForm) this.formMantenimiento;
		boolean isNew = mantenimientoForm.isNewRecord();
		if(isNew) {
			return "mantenimientoMENMensajeCodigoVentaForm.cabecera.insert";
		}else{
			return "mantenimientoMENMensajeCodigoVentaForm.cabecera.update";
		}	

	}
	
	@Override
	public String setValidarMantenimiento() {
		/*MantenimientoMENEscaleraGananciaForm f = (MantenimientoMENEscaleraGananciaForm) this.formMantenimiento;
		int rangoInicial = Integer.valueOf(f.getRangoInicial());
		int rangoFinal = Integer.valueOf(f.getRangoFinal());
		
		if (rangoInicial >= rangoFinal) {
				String mensaje = this.getResourceMessage("mantenimientoMENEscaleraGananciaForm.rango.mayor");
				return mensaje;
		
		}*/

		return null;
	}

	/**
	 * @return the msgMensajeList
	 */
	public List getMsgMensajeList() {
		return msgMensajeList;
	}

	/**
	 * @param msgMensajeList the msgMensajeList to set
	 */
	public void setMsgMensajeList(List msgMensajeList) {
		this.msgMensajeList = msgMensajeList;
	}

	/**
	 * @return the dataModelDetalle
	 */
	public DataTableModel getDataModelDetalle() {
		return dataModelDetalle;
	}

	/**
	 * @param dataModelDetalle the dataModelDetalle to set
	 */
	public void setDataModelDetalle(DataTableModel dataModelDetalle) {
		this.dataModelDetalle = dataModelDetalle;
	}

	/**
	 * @return the listaDetalle
	 */
	public List getListaDetalle() {
		return listaDetalle;
	}

	/**
	 * @param listaDetalle the listaDetalle to set
	 */
	public void setListaDetalle(List listaDetalle) {
		this.listaDetalle = listaDetalle;
	}

	
	

	

	
	

	
}
