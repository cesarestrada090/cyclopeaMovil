package biz.belcorp.ssicc.web.spusicc.ventas.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Calendario;
import biz.belcorp.ssicc.service.spusicc.ventas.ProcesoVENService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.ventas.form.MantenimientoBASFeriadoForm;
import biz.belcorp.ssicc.web.spusicc.ventas.form.MantenimientoBASFeriadoSearchForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoBASFeriadoSearchAction.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar </a>                      
 * 
 */

@ManagedBean
@SessionScoped
public class MantenimientoBASFeriadoSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -2182745424800699888L;
	
	protected boolean idAnioGenerado = false;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoBASFeriadoForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {		
		MantenimientoBASFeriadoSearchForm mantenimientoBASFeriadoSearchForm = new MantenimientoBASFeriadoSearchForm();
		return mantenimientoBASFeriadoSearchForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'find' method");
		}
		
		MantenimientoBASFeriadoSearchForm searchForm = (MantenimientoBASFeriadoSearchForm)this.formBusqueda;
		
		Map criteria = BeanUtils.describe(searchForm);
		ProcesoVENService service = (ProcesoVENService)this.getBeanService("spusicc.procesoVENService");
					
		List lista = service.getCalendarios(criteria);				

		return lista;

	}
	
	/**
	 * Metodo ejecutarProceso
	 * @return
	 */
	public void ejecutarProceso(ActionEvent actionEvent)  {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ejecutarProceso' method");
		}	
		try{
						
			ProcesoVENService service = (ProcesoVENService)this.getBeanService("spusicc.procesoVENService"); 			
			MantenimientoBASFeriadoSearchForm searchForm = (MantenimientoBASFeriadoSearchForm)this.formBusqueda;			
			
			
					
				Map criteria = BeanUtils.describe(searchForm);			
				Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();					
				criteria.put("codigoUsuario", usuario.getLogin());			
				service.executeGenerico("executeGenerarCalendario", criteria);			
				String error = (String)criteria.get("error");
			
			if(StringUtils.isBlank(searchForm.getCodigoAnio())){	
				
				this.addWarn("", this.getResourceMessage("advertencia.generaCalendario"));
				
			}else{
			
				/* Devolviendo mensaje correspondiente */			
				if (StringUtils.isBlank(error)) {				
					this.addInfo("Info: ", this.getResourceMessage("procesoSiCC.proceso.ejecucionOK"));
				}
				else {
					this.addError("Error: ", error);					
				}
			}
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			return;
		}finally{
			if (log.isDebugEnabled()) {
				log.debug("Finish 'ejecutarProceso' method");
			}			
		}		
	}

	/**
	 * Metodo confirmarProceso
	 * @return
	 */
	public void confirmarProceso(ActionEvent actionEvent)  {
		if (log.isDebugEnabled()) {
			log.debug("Enter 'confirmarProceso' method");
		}
		MantenimientoBASFeriadoSearchForm searchForm = (MantenimientoBASFeriadoSearchForm)this.formBusqueda;
		if(searchForm.getCodigoAnio().length() == 4){
			this.idAnioGenerado = true;			
		}
		this.formBusqueda = searchForm;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		MantenimientoBASFeriadoSearchForm obj = (MantenimientoBASFeriadoSearchForm)this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		obj.setCodigoPais(pais.getCodigo());
		this.mostrarBotonConsultar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonEliminar = false;
		this.idAnioGenerado = false;
	}
	
	/**
	 * @param idAnioGenerado the idAnioGenerado to set
	 */
	public void setIdAnioGenerado(boolean idAnioGenerado) {
		this.idAnioGenerado = idAnioGenerado;
	}
	
	/**
	 * @return the idAnioGenerado
	 */
	public boolean isIdAnioGenerado() {
		return idAnioGenerado;
	}

	

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}

		// Extraemos atributos y parámetros a usar		
		MantenimientoBASFeriadoForm f = (MantenimientoBASFeriadoForm)this.getFormMantenimiento(); 
						
		// Extreamos el usuario de la sesión
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();				

		// Creamos la instancia del servicio y le asignamos
		// el usuario que va a realizar las operaciones
		ProcesoVENService service = (ProcesoVENService)this.getBeanService("spusicc.procesoVENService"); 
				
		Calendario calendario = new Calendario();
		calendario.setCodigoAnio(f.getCodigoAnio());
		calendario.setDescIndicadorFeriado(f.getDescIndicadorFeriado());
		calendario.setDescripcion(f.getDescripcion());		
	    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");;		
		calendario.setFechaCalendario((Date)formatter.parse(f.getFechaCalendario()));
		calendario.setIndicadorFeriado(f.getIndicadorFeriado());
		calendario.setPosicionSemana(Integer.parseInt(f.getPosicionSemana()));
		
		try {
			service.updateCalendario(calendario, usuario);			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
    		return false;
		} 

		return true;
	}
	
	@Override
	protected String getSalirForward() {		
		return "mantenimientoBASFeriadoList";
	}
    
	@Override
	protected String devuelveMensajeKeySaveOK() {
		return "sistema.updated";
	}


	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Calendario calendariobusqueda= (Calendario)this.beanRegistroSeleccionado;
		SimpleDateFormat sdfbusqueda = new SimpleDateFormat("dd/MM/yyyy");
		String parametrosMantenimiento = sdfbusqueda.format(calendariobusqueda.getFechaCalendario());
		
		MantenimientoBASFeriadoForm mantenimientoBASFeriadoForm = new MantenimientoBASFeriadoForm();
		mantenimientoBASFeriadoForm.setEditable(true);
		
		// Cargamos los combos a utilizar
		// Si el id ha sido enviado, buscamos la informacion
		// en caso contrario, no hacemos nada, se esta insertando
		// un nuevo registro a la aplicación
		if (this.getAccion().equals(this.ACCION_MODIFICAR)){
			
			String codigo = parametrosMantenimiento;
						
			if(codigo != null){
				if (log.isDebugEnabled()) {
					log.debug("Id seleccionado de la lista: " + codigo);
				}
				ProcesoVENService service = (ProcesoVENService)this.getBeanService("spusicc.procesoVENService");					
				Calendario calendario = new Calendario();
				String fecha = codigo;
				String anio = fecha.substring(6,10);
				Map criteria = new HashMap();
				criteria.put("fechaCalendario", fecha);
				criteria.put("codigoAnio", anio);
				List resultado = service.getCalendarios(criteria);
				if (resultado.size() > 0) {
					calendario = (Calendario) resultado.get(0);
					BeanUtils.copyProperties(mantenimientoBASFeriadoForm, calendario);
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					mantenimientoBASFeriadoForm.setCodigoAnio(sdf.format(calendario.getFechaCalendario()).substring(6, 10));							
					mantenimientoBASFeriadoForm.setFechaCalendario(sdf.format(calendario.getFechaCalendario()));
				}	
				
			} 
		}
		
		return mantenimientoBASFeriadoForm;
		
	}
	
	
}
