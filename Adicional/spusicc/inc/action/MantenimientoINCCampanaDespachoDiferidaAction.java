package biz.belcorp.ssicc.web.spusicc.inc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCCampanaDespachoDiferidaService;
import biz.belcorp.ssicc.web.form.SistemaForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCCampanaDespachoDiferidaForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class MantenimientoINCCampanaDespachoDiferidaAction extends BaseMantenimientoSearchAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7381114950087376401L;

	private List incentivosConcursosParametrosList;
	private LabelValue[] nivelesList = {};
	private boolean habilitaBotonEliminar = false;
	private String periodoDespacho;
	private boolean periodoDeshabilitado;
	private boolean habilitaBotonGuardar;
	
	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception 
	{
		MantenimientoINCCampanaDespachoDiferidaForm searchForm = new MantenimientoINCCampanaDespachoDiferidaForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete' method");
		}
		
		try{
			MantenimientoINCCampanaDespachoDiferidaForm f = (MantenimientoINCCampanaDespachoDiferidaForm)this.formBusqueda;
			MantenimientoINCCampanaDespachoDiferidaService service = 
				(MantenimientoINCCampanaDespachoDiferidaService) getBean("spusicc.mantenimientoINCCampanaDespachoDiferidaService");
			
	        // Obtenemos las propiedades del bean como un 'Map'
	        Map params = new HashMap();
	        
	        params.put("codigoPais", f.getCodigoPais());
	        params.put("numeroConcurso", f.getNumeroConcurso());
	        params.put("nivel", f.getNivel());
	        
	        service.deleteNivelDespachoDiferido(params);
	        
			this.getResourceMessage("mantenimientoINCCampanaDespachoDiferidaForm.msg.deleted");
			
			f.setNumeroConcurso("");
			f.setNivel("");
			f.setCodigoPeriodo("");
			
		}catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
			throw new Exception(this.getResourceMessage("errors.detail",new Object[]{error}));
		}
		
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}

		MantenimientoINCCampanaDespachoDiferidaForm f = (MantenimientoINCCampanaDespachoDiferidaForm)this.formBusqueda;
		MantenimientoINCCampanaDespachoDiferidaService service = 
			(MantenimientoINCCampanaDespachoDiferidaService) getBean("spusicc.mantenimientoINCCampanaDespachoDiferidaService");
		
		boolean bGrabarCab = false;
		try {

	        // Obtenemos las propiedades del bean como un 'Map'
	        Map params = new HashMap();
	        
	        params.put("codigoPais", f.getCodigoPais());
	        params.put("numeroConcurso", f.getNumeroConcurso());
	        params.put("nivel", f.getNivel());
	        params.put("codigoPeriodo", f.getCodigoPeriodo()); 
	        
	        //validamos si el codigo de periodo existe
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			String[] fechas = ajaxService.getIntervalosFechaFacturasVentaDirecta(f.getCodigoPeriodo(), 
						f.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, null);
			
			if(fechas == null) {
				f.setNumeroConcurso("");
				f.setNivel("");
				
				this.getResourceMessage("mantenimientoINCCampanaDespachoDiferidaForm.msg.validarPeriodo", new Object[]{f.getCodigoPeriodo()});
				f.setCodigoPeriodo("");
			
				return false;
			}	        
	        
	        service.updateNivelDespachoDiferido(params);
	        
			this.getResourceMessage("mantenimientoINCCampanaDespachoDiferidaForm.msg.updated");
			bGrabarCab = true;
			
			f.setNumeroConcurso("");
			f.setNivel("");
			f.setCodigoPeriodo("");
		} 
		catch (Exception e) {
			e.printStackTrace();
			bGrabarCab = false;
			throw new Exception(this.getResourceMessage("mantenimientoEDULocal.cabecera.error",new Object[]{e.getMessage()}));
		}
					
		return bGrabarCab;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	protected void setViewAtributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		MantenimientoINCCampanaDespachoDiferidaForm f = (MantenimientoINCCampanaDespachoDiferidaForm) this.formBusqueda;
		
		MantenimientoINCCampanaDespachoDiferidaService service = 
				(MantenimientoINCCampanaDespachoDiferidaService) getBean("spusicc.mantenimientoINCCampanaDespachoDiferidaService");
		//se cargara la lista de parametros de concurso activos 
		List listConcursosActivo = service.getListConcursoDespachoDiferido();
 
		f.setNumeroConcurso("");
		f.setNivel("");
		f.setCodigoPeriodo("");
		
		this.incentivosConcursosParametrosList = listConcursosActivo;
		
		this.listaBusqueda = null;	
		this.mostrarBotonBuscar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = habilitaBotonEliminar;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
		
		this.habilitaBotonGuardar = true;
	}
	
	public void loadNiveles(ValueChangeEvent value)
	{
		String valor = value.getNewValue().toString();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		MantenimientoINCCampanaDespachoDiferidaForm f = (MantenimientoINCCampanaDespachoDiferidaForm) this.formBusqueda;
		f.setCodigoPeriodo("");
		this.mostrarBotonEliminar = habilitaBotonEliminar;
		this.habilitaBotonGuardar = false;
		
		if(valor != null)
			this.nivelesList = ajax.getNivelesConcurso(f.getCodigoPais(), valor);
		else this.nivelesList = null;		
	}
	
	public void loadPeriodo(ValueChangeEvent value)
	{
		String valor = value.getNewValue().toString();
		String resultado = null, indicadorHabilitado = "";
		String[] resultados = null;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		MantenimientoINCCampanaDespachoDiferidaForm f = (MantenimientoINCCampanaDespachoDiferidaForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		f.setCodigoPais(pais.getCodigo());
		this.mostrarBotonEliminar = habilitaBotonEliminar;
		this.habilitaBotonGuardar = false;
		
		if(f.getNumeroConcurso() != "" && valor != "")
		{
			f.setCodigoPeriodo("");
			resultado = ajax.getPeriodoNivelDespachoDiferido(f.getCodigoPais(), f.getNumeroConcurso(), valor);
		}
		
		if(resultado != null)
		{
			resultados = resultado.split("__");
	    	f.setCodigoPeriodo(resultados[0]); //Periodo Despacho Diferido
	    	this.periodoDespacho = resultados[2]; //Periodo Despacho
	    	indicadorHabilitado = resultados[1];	
	    	
	    	if(indicadorHabilitado.equalsIgnoreCase("0")) { //El periodo de Despacho del Concurso ya se inicio
	    		this.habilitaBotonGuardar = false;
	    		this.mostrarBotonEliminar = habilitaBotonEliminar;
	    		this.periodoDeshabilitado = true;
	        } else { //Sino
	        	this.habilitaBotonGuardar = true;
	        	this.periodoDeshabilitado = false;
	        	
	            if(f.getCodigoPeriodo() == "") {
	                f.setCodigoPeriodo(resultados[3]); //periodo de Despacho del concurso + 1 
	            } else { //si se recupero informacion
	                habilitaBotonEliminar = true;
	                this.mostrarBotonEliminar = habilitaBotonEliminar;
	            }
	        }
		}		
	}

	public List getIncentivosConcursosParametrosList() {
		return incentivosConcursosParametrosList;
	}

	public void setIncentivosConcursosParametrosList(
			List incentivosConcursosParametrosList) {
		this.incentivosConcursosParametrosList = incentivosConcursosParametrosList;
	}

	public LabelValue[] getNivelesList() {
		return nivelesList;
	}

	public void setNivelesList(LabelValue[] nivelesList) {
		this.nivelesList = nivelesList;
	}

	public boolean isHabilitaBotonEliminar() {
		return habilitaBotonEliminar;
	}

	public void setHabilitaBotonEliminar(boolean habilitaBotonEliminar) {
		this.habilitaBotonEliminar = habilitaBotonEliminar;
	}

	public String getPeriodoDespacho() {
		return periodoDespacho;
	}

	public void setPeriodoDespacho(String periodoDespacho) {
		this.periodoDespacho = periodoDespacho;
	}

	public boolean isPeriodoDeshabilitado() {
		return periodoDeshabilitado;
	}

	public void setPeriodoDeshabilitado(boolean periodoDeshabilitado) {
		this.periodoDeshabilitado = periodoDeshabilitado;
	}

	public boolean isHabilitaBotonGuardar() {
		return habilitaBotonGuardar;
	}

	public void setHabilitaBotonGuardar(boolean habilitaBotonGuardar) {
		this.habilitaBotonGuardar = habilitaBotonGuardar;
	}
}
