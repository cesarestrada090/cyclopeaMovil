package biz.belcorp.ssicc.web.spusicc.proyeccion.action;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.proyeccion.model.PorcentajeFaltante;
import biz.belcorp.ssicc.service.spusicc.ProcesoPRYProyeccionFaltanteDiaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.proyeccion.form.MantenimientoPRYPorcentajeFaltanteForm;
import biz.belcorp.ssicc.web.spusicc.proyeccion.form.MantenimientoPRYPorcentajeFaltanteSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes"})
public class MantenimientoPRYPorcentajeFaltanteSearchAction extends BaseMantenimientoSearchAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6431243868107404435L;

	@Override
	protected String getSalirForward() 
	{
		return "mantenimientoPRYPorcentajeFaltanteList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception 
	{
		return "mantenimientoPRYPorcentajeFaltanteForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception 
	{
		MantenimientoPRYPorcentajeFaltanteSearchForm searchForm = new MantenimientoPRYPorcentajeFaltanteSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception 
	{
		log.debug("Seting find Attributes.");
		
		ProcesoPRYProyeccionFaltanteDiaService service = (ProcesoPRYProyeccionFaltanteDiaService) 
										getBean("spusicc.procesoPRYProyeccionFaltanteDiaService");
		PorcentajeFaltante bean = new PorcentajeFaltante();
		
		MantenimientoPRYPorcentajeFaltanteSearchForm f = (MantenimientoPRYPorcentajeFaltanteSearchForm) this.formBusqueda;
		BeanUtils.copyProperties(bean, f);
		bean.setCodigoUnidadNegocio(f.getCodigoUnidadNegocioAux());
		
		List lista = service.getPorcentajeFaltante(bean);
		
		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		// Extraemos atributos y parámetros a usar
		MantenimientoPRYPorcentajeFaltanteForm f = (MantenimientoPRYPorcentajeFaltanteForm) this.formMantenimiento;
		
		// Extraemos el usuario de la sesión
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			
		ProcesoPRYProyeccionFaltanteDiaService service = (ProcesoPRYProyeccionFaltanteDiaService) 
			getBean("spusicc.procesoPRYProyeccionFaltanteDiaService");
		
		boolean bGrabarCab = false;
		
		PorcentajeFaltante bPorcentaje = new PorcentajeFaltante();
		BeanUtils.copyProperties(bPorcentaje,f);
		bPorcentaje.setCodigoPais(pais.getCodigo());
		
		try {
			if (!f.isNewRecord()) { //Modificacion	
				service.updatePorcentajeFaltante(bPorcentaje, usuario);
				bGrabarCab = true;				
			}
			else { //Insercion
				service.insertPorcentajeFaltante(bPorcentaje, usuario);
				bGrabarCab = true;				
			}
		} catch (Exception e) {
			e.printStackTrace();
			bGrabarCab = false;
			this.getResourceMessage("mantenimientoEDULocal.cabecera.error", new Object[]{e.getMessage()});		
		}
		
		return bGrabarCab;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception 
	{
		log.debug("add new");
		MantenimientoPRYPorcentajeFaltanteForm f = new MantenimientoPRYPorcentajeFaltanteForm();
		f.setCodigoUnidadNegocio("");
		f.setCodigoUnidadNegocio("");
		f.setDescripcionUnidadNegocio("");
		f.setPorcentajeMaximo("");
		
		if(!this.accion.equals(this.ACCION_NUEVO))
		{
			log.debug("edit");	
			
			PorcentajeFaltante registroSeleccionado = (PorcentajeFaltante)this.beanRegistroSeleccionado;
			
			String codigoUnidadNegocio = registroSeleccionado.getCodigoUnidadNegocio();
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			
			if (codigoUnidadNegocio != null) {
				PorcentajeFaltante bPorcentaje = new PorcentajeFaltante();
				bPorcentaje.setCodigoUnidadNegocio(codigoUnidadNegocio);
				bPorcentaje.setCodigoPais(pais.getCodigo());
				ProcesoPRYProyeccionFaltanteDiaService service = (ProcesoPRYProyeccionFaltanteDiaService) 
						getBean("spusicc.procesoPRYProyeccionFaltanteDiaService");
				List resultado = service.getPorcentajeFaltante(bPorcentaje);
				
				PorcentajeFaltante bPorcentajefiltro = new PorcentajeFaltante();
				bPorcentajefiltro = (PorcentajeFaltante)resultado.get(0);
				BeanUtils.copyProperties(f,bPorcentajefiltro);
				
				f.setNewRecord(false);
			}
		}
				
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonNuevo = false;
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'enter' method");
		}
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoPRYPorcentajeFaltanteSearchForm f = (MantenimientoPRYPorcentajeFaltanteSearchForm) this.formBusqueda;
		f.setCodigoPais(pais.getCodigo());
		f.setCodigoUnidadNegocioAux("");
		f.setDescripcionUnidadNegocio("");
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() 
	{
		MantenimientoPRYPorcentajeFaltanteForm f = (MantenimientoPRYPorcentajeFaltanteForm) this.formMantenimiento;
		boolean isNewRecord = f.isNewRecord();
		if(isNewRecord)
			return "sistema.added";
		else
			return "sistema.updated";
	}

}
