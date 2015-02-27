package biz.belcorp.ssicc.web.spusicc.ventas.action;
 
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.model.AuditInfo;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Sistema;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.PorcentajeIgv;
import biz.belcorp.ssicc.service.SistemaService;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spusicc.ventas.PorcentajeIgvVENService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.ventas.form.MantenimientoVENPorcentajeIgvForm;
import biz.belcorp.ssicc.web.spusicc.ventas.form.MantenimientoVENPorcentajeIgvSearchForm;

import com.evermind.util.StringUtils;

@ManagedBean
@SessionScoped
public class MantenimientoVENPorcentajeIgvAction extends BaseMantenimientoSearchAbstractAction{
	
	
	private static final long serialVersionUID = -831798654637570226L;
	private List mantenimientoVENPorcentajeIgvList;
	
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoVENPorcentajeIgvForm";
	}


	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {		
	
		MantenimientoVENPorcentajeIgvSearchForm searchForm = new MantenimientoVENPorcentajeIgvSearchForm();
		return searchForm;
		
	}

	@Override
	protected List setFindAttributes() throws Exception {
			
		if (this.log.isDebugEnabled()) {
            this.log.debug("Entering 'setFindAttributes' method");
        }

		MantenimientoVENPorcentajeIgvSearchForm searchForm = (MantenimientoVENPorcentajeIgvSearchForm)this.formBusqueda;
		PorcentajeIgvVENService service = (PorcentajeIgvVENService)this.getBeanService("spusicc.porcentajeIgvVENService");				
		PorcentajeIgv bporcentaje = new PorcentajeIgv();
		BeanUtils.copyProperties(bporcentaje,searchForm);
		
		List lista = service.getPorcentajeIgv(bporcentaje);
		
        return lista;
	}
	
	@Override
	protected boolean setDeleteAttributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete' method");
		}
		
		MantenimientoVENPorcentajeIgvForm mantenimiento = (MantenimientoVENPorcentajeIgvForm) this.formMantenimiento;
		PorcentajeIgv sistemabusqueda = (PorcentajeIgv)this.beanRegistroSeleccionado;
		String id = sistemabusqueda.getCodigoPeriodo();
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();// se obtiene el pais de la sesion
		
		if (id != null) {
			PorcentajeIgv bporcentaje = new PorcentajeIgv();
			bporcentaje.setCodigoPeriodo(id);
			bporcentaje.setCodigoPais(pais.getCodigo());
			PorcentajeIgvVENService service = (PorcentajeIgvVENService) getBean("spusicc.porcentajeIgvVENService");
			service.deletePorcentajeIgv(bporcentaje,usuario);		
		} else {
			//	session.removeAttribute(Constants.MANTENIMIENTO_PORCENTAJEIGV_LIST);
			mantenimientoVENPorcentajeIgvList.clear();
			
		}
		
		
	  return true;
	
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonConsultar = false;
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();		
		this.formBusqueda.setCodigoPais(usuario.getCodigoPais());
	}

	@Override
	protected String getSalirForward() {
		return "mantenimientoVENPorcentajeIgvList";
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method");
		}
		
		// Extraemos atributos y parámetros a usar
		
		MantenimientoVENPorcentajeIgvForm f = (MantenimientoVENPorcentajeIgvForm)this.formMantenimiento;

		// Extreamos el usuario de la sesión
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry(); // se corrigio el valor nulo de pais
		
		PorcentajeIgvVENService service = (PorcentajeIgvVENService) getBean("spusicc.porcentajeIgvVENService");
		 
		PorcentajeIgv bporcentaje = new PorcentajeIgv();
		BeanUtils.copyProperties(bporcentaje,f);
	 
		try {
				
				AuditInfo audi= usuario.getAuditInfo();
				if (!f.isNewRecord()) { //Modificacion	
						audi.setUpdatedBy(usuario.getLogin());
						bporcentaje.setAuditInfo(audi);
						service.updatePorcentajeIgv(bporcentaje, usuario);
						
						addInfo("Mensaje", getResourceMessage("sistema.updated"));
						return true;
//						messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
//								"sistema.updated"));
//						saveMessages(request, messages);
				
				}
				else { //Insercion
					service.insertPorcentajeIgv(bporcentaje, usuario);
					
					addInfo("Mensaje", getResourceMessage("sistema.added"));
					return true;
//					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
//					"sistema.added"));
//				    saveMessages(request, messages);
				   	
				}	
				
		} catch (InvalidIdentifierException iie) {
			String codigo = iie.getIdentifier().toString();
			
			addInfo("Mensaje", getResourceMessage("errors.invalid.id"));
//			messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
//					"errors.invalid.id", codigo));
//			saveErrors(request, messages);
//			return f.getInputForward();
			return false;
			
		} catch (InvalidDescriptionException ide) {
			String descripcion = ide.getDescription();
			addInfo("Mensaje", getResourceMessage("errors.invalid.description"));
//			messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
//					"errors.invalid.description", descripcion));
//			saveErrors(request, messages);
//			return mapping.getInputForward();
			return false;	
			
		}
  
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'edit' method");
		}
		
		MantenimientoVENPorcentajeIgvForm f = new MantenimientoVENPorcentajeIgvForm();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setEditable(true);
		f.setNewRecord(true);
		
//		SistemaService sistemaService = (SistemaService) getBean("sisicc.serviceBeanName");
//		Sistema sistema = new Sistema();
//		sistema.setCodigoPais(pais.getCodigo());
//		mantenimientoVENPorcentajeIgvList = sistemaService.getSistemas(sistema);
		
		PorcentajeIgv sistemabusqueda = (PorcentajeIgv)this.beanRegistroSeleccionado;
		
		if (!this.accion.equals(this.ACCION_NUEVO))
		{
			String codigoPais = sistemabusqueda.getCodigoPais();	
			String codigoPeriodo = sistemabusqueda.getCodigoPeriodo();
			Integer nombreParametro = sistemabusqueda.getValIgv();
			
		if ( codigoPais != null && codigoPeriodo != null && nombreParametro != null) {
			
			PorcentajeIgv bporcentaje = new PorcentajeIgv();
			PorcentajeIgv bporcentajefiltro = new PorcentajeIgv();
			bporcentaje.setCodigoPais(codigoPais);
			bporcentaje.setCodigoPeriodo(codigoPeriodo);
			bporcentaje.setValIgv(nombreParametro);
			PorcentajeIgvVENService service = (PorcentajeIgvVENService) getBean("spusicc.porcentajeIgvVENService");
			List resultado= service.getPorcentajeIgv(bporcentaje);
			bporcentajefiltro = (PorcentajeIgv)resultado.get(0);
			BeanUtils.copyProperties(f,bporcentajefiltro);
				
		}
		}
	  return f;
	
	  
	}


	public List getMantenimientoVENPorcentajeIgvList() {
		return mantenimientoVENPorcentajeIgvList;
	}


	public void setMantenimientoVENPorcentajeIgvList(
			List mantenimientoVENPorcentajeIgvList) {
		this.mantenimientoVENPorcentajeIgvList = mantenimientoVENPorcentajeIgvList;
	}


}
