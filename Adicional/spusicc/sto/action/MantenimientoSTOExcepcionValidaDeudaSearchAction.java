package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOExcepcionValidaDeudaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOExcepcionValidaDeudaForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOExcepcionValidaDeudaSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class MantenimientoSTOExcepcionValidaDeudaSearchAction extends BaseMantenimientoSearchAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 960524848685706712L;
		
	//flags
	private boolean flagValidador;
	
	@Override
	protected String getSalirForward() 
	{
		return "mantenimientoSTOExcepcionValidaDeudaList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception 
	{
		return "mantenimientoSTOExcepcionValidaDeudaForm";
	}

	@Override 
	protected BaseSearchForm devuelveFormBusqueda() throws Exception
	{
		MantenimientoSTOExcepcionValidaDeudaSearchForm searchForm = new MantenimientoSTOExcepcionValidaDeudaSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception 
	{
		log.debug("MantenimientoSTOExcepcionValidaDeudaSearchAction - setFindAttributes");

		MantenimientoSTOExcepcionValidaDeudaSearchForm f = (MantenimientoSTOExcepcionValidaDeudaSearchForm) this.formBusqueda;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoSTOExcepcionValidaDeudaService service = (MantenimientoSTOExcepcionValidaDeudaService) getBean("spusicc.mantenimientoSTOExcepcionValidaDeudaService");

		List lista = new ArrayList();
		Map criteria = new HashMap();

		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("codigoCliente", f.getCodigoCliente());

		try {
			if (StringUtils.isNotBlank(f.getCodigoCliente()))
				criteria.put("oidCliente", reporteService.getOidString(
						"getOidClienteByCodigoCliente", criteria));
			else
				criteria.put("oidCliente", null);

			if (StringUtils.isNotBlank(f.getCodigoPeriodo()))
				criteria.put("oidPeriodo", reporteService.getOidString(
						"getOidPeriodoByCodigoPeriodo", criteria));
			else
				criteria.put("oidPeriodo", null);

			lista = service.getExcepcionValidaDeudaList(criteria);

			return lista;

		} catch (Exception e) 
		{
			String error = e.getMessage();
			if (StringUtils.isBlank(error))	error = e.getLocalizedMessage();
			throw new Exception(this.getResourceMessage("errors.detail", new Object[] { error }));
		}
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception 
	{

		log.debug("MantenimientoSTOExcepcionValidaDeudaSearchAction - setDeleteAttributes");
		
		MantenimientoSTOExcepcionValidaDeudaSearchForm f  = (MantenimientoSTOExcepcionValidaDeudaSearchForm)this.formBusqueda;
		Map registroSeleccionado = (Map)this.beanRegistroSeleccionado;
		
		MantenimientoSTOExcepcionValidaDeudaService service = (MantenimientoSTOExcepcionValidaDeudaService)getBean("spusicc.mantenimientoSTOExcepcionValidaDeudaService");
		
		Map parametros = new HashMap();
		try{
			String[] oidClienExcepValiDeud = new String[]{registroSeleccionado.get("oidClienExcepValiDeud").toString()};
			parametros.put("idSeleccionados", oidClienExcepValiDeud);
			
			service.deleteExcepcionValidaDeuda(parametros);
				
		}catch(Exception e)
		{
			String error = e.getMessage();
			if (StringUtils.isBlank(error))	error = e.getLocalizedMessage();
			throw new Exception(this.getResourceMessage("errors.detail", new Object[] { error }));			
		}
		return true;		
	}

	@Override
	protected boolean setSaveAttributes() throws Exception 
	{
		MantenimientoSTOExcepcionValidaDeudaForm f = (MantenimientoSTOExcepcionValidaDeudaForm)this.formMantenimiento;
		ReporteService reporteService = (ReporteService)getBean("scsicc.reporteService");
		
		MantenimientoSTOExcepcionValidaDeudaService service = (MantenimientoSTOExcepcionValidaDeudaService)getBean("spusicc.mantenimientoSTOExcepcionValidaDeudaService");
		
		Map criteria = new HashMap();
		if(this.accion.equals(this.ACCION_NUEVO))
		{
			criteria.put("codigoPeriodo", f.getCodigoPeriodo());
			criteria.put("codigoCliente", f.getCodigoCliente());
			
			if(StringUtils.isNotBlank(f.getCodigoPeriodo()))
				criteria.put("oidPeriodo", reporteService.getOidString("getOidPeriodoByCodigoPeriodo", criteria));
			else 
				criteria.put("oidPeriodo", null);
			
			if(StringUtils.isNotBlank(f.getCodigoCliente()))
				criteria.put("oidCliente", reporteService.getOidString("getOidClienteByCodigoCliente", criteria));
			else
				criteria.put("oidCliente", null);
			

				service.insertExcepValidDeuda(criteria);
				return true;
		}else
			return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception 
	{
		log.debug("MantenimientoSTOExcepcionValidaDeudaAction - setAddAttributes");
		
		MantenimientoSTOExcepcionValidaDeudaForm f = new MantenimientoSTOExcepcionValidaDeudaForm();
		f.setCodigoCliente(null);
		f.setCodigoPeriodo(null);

		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		this.mostrarMantenimientoEnPopup = true;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonModificar = false;
		
		log.debug("MantenimientoSTOExcepcionValidaDeudaSearchAction - setViewAttributes");

		MantenimientoSTOExcepcionValidaDeudaSearchForm f = (MantenimientoSTOExcepcionValidaDeudaSearchForm) this.formBusqueda;

		cleanForm(f);
	}
	
	/**
	 * @param f
	 */
	private void cleanForm(MantenimientoSTOExcepcionValidaDeudaSearchForm f) {
		f.setCodigoCliente("");
		f.setCodigoPeriodo("");
	}
	
	/*
	 * @param  
	 */
	public void consultar()
	{
		MantenimientoSTOExcepcionValidaDeudaForm f = (MantenimientoSTOExcepcionValidaDeudaForm)this.formMantenimiento;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		String dato = null;
		
		if(f == null)
		{
			MantenimientoSTOExcepcionValidaDeudaSearchForm f1 = (MantenimientoSTOExcepcionValidaDeudaSearchForm) this.formBusqueda;	
			dato =  ajax.getNombreCliente(f1.getCodigoCliente());
		}else
			dato =  ajax.getNombreCliente(f.getCodigoCliente());
		
		if(dato == null)
		{
			this.addError("", this.getResourceMessage("mantenimientoSTOExcepcionValidaDeudaForm.validaCliente"));
			this.flagValidador = false;
		}else
			this.flagValidador = true;
	}
	
	@Override
	public String setValidarMantenimiento() 
	{
		String mensaje = null;
		MantenimientoSTOExcepcionValidaDeudaForm f = (MantenimientoSTOExcepcionValidaDeudaForm)this.formMantenimiento;
		
		if(f.getCodigoCliente()== null)
		{
			mensaje = "campo cliente es requerido";
		}else
		{
			if(!this.flagValidador)
				mensaje = "Antes de Guardar por favor corrija el Codigo del Cliente";
		}	
		
		return mensaje;
	}

	public boolean getFlagValidador() {
		return flagValidador;
	}

	public void setFlagValidador(boolean flagValidador) {
		this.flagValidador = flagValidador;
	}

}
