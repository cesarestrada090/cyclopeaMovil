package biz.belcorp.ssicc.web.spusicc.ape.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.ape.model.FactorConversion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEFactoresConversionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.ape.form.MantenimientoAPEFactoresConversionForm;
import biz.belcorp.ssicc.web.spusicc.ape.form.MantenimientoAPEFactoresConversionSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoAPEFactoresConversionAction extends BaseMantenimientoSearchAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7189231956315830040L;
	List listaMagnitudes;
	LabelValue[] listaUnidades;
	int valorPantalla = 0; 

	
	/**
	 * @return the listaUnidades
	 */
	public LabelValue[] getListaUnidades() {
		return listaUnidades;
	}

	/**
	 * @param listaUnidades the listaUnidades to set
	 */
	public void setListaUnidades(LabelValue[] listaUnidades) {
		this.listaUnidades = listaUnidades;
	}

	/**
	 * @return the listaMagnitudes
	 */
	public List getListaMagnitudes() {
		return listaMagnitudes;
	}

	/**
	 * @param listaMagnitudes the listaMagnitudes to set
	 */
	public void setListaMagnitudes(List listaMagnitudes) {
		this.listaMagnitudes = listaMagnitudes;
	}

	/**
	 * @return the valorPantalla
	 */
	public int getValorPantalla() {
		return valorPantalla;
	}

	/**
	 * @param valorPantalla the valorPantalla to set
	 */
	public void setValorPantalla(int valorPantalla) {
		this.valorPantalla = valorPantalla;
	}

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoAPEFactoresConversionSearchForm";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoAPEFactoresConversionForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoAPEFactoresConversionSearchForm objForm = new MantenimientoAPEFactoresConversionSearchForm();
		return objForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Entering 'find' method");
		
		//--  Variables
		MantenimientoAPEFactoresConversionSearchForm f = (MantenimientoAPEFactoresConversionSearchForm)this.formBusqueda;
		MantenimientoAPEFactoresConversionService serviceFC = 
						(MantenimientoAPEFactoresConversionService)this.getBean("spusicc.mantenimientoAPEFactoresConversionService");
		ReporteService reporteService =	(ReporteService) this.getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		//-- Crear Pojo
		Map criteria = new HashMap();
		
		//-- Lógica de Negocio
		criteria.put("codigoPais", pais.getCodigo());
		String oidPais=reporteService.getOidString("getOidPaisByCodigoPais", criteria);
		criteria.put("oidPais", oidPais);
		criteria.put("codigoPais", f.getCodigoPais());
		
		// -- Filtro de Código de Magnitud	
		if (StringUtils.isNotBlank(f.getCodigoMagn())){
			criteria.put("codMagn", f.getCodigoMagn());
			String OidMagnitud = serviceFC.getObtenerOidMagnitud(criteria);
			criteria.put("oidMagn", OidMagnitud);
		}else{
			criteria.put("oidMagn",null);
		}
		
		// -- Filtro de Unidad de Medida Origen
		if (StringUtils.isNotBlank(f.getCodigoUnidMediOrig())){
			criteria.put("codUnidMedi",f.getCodigoUnidMediOrig());
			String oIdUnidadMedOrig = serviceFC.getObtenerOidUnidadMedida(criteria);
			criteria.put("oidUnidMediOrig", oIdUnidadMedOrig);
		}else{
			criteria.put("oidUnidMediOrig",null);
		}

		// -- Filtro de Unidad de Medida Destino
		if (StringUtils.isNotBlank(f.getCodigoUnidMediDest())){
			criteria.remove("codUnidMedi");
			criteria.put("codUnidMedi",f.getCodigoUnidMediDest());
			String oIdUnidadMedDest = serviceFC.getObtenerOidUnidadMedida(criteria);
			criteria.put("oidUnidMediDest", oIdUnidadMedDest);
		}else{
			criteria.put("oidUnidMediDest",null);
		}

		// Peticiones
		List resultado = (List)serviceFC.getFactoresConversionList(criteria);
		
		f.setCodigoMagn(f.getCodigoMagn());
		f.setCodigoUnidMediOrig(f.getCodigoUnidMediOrig());
		f.setCodigoUnidMediDest(f.getCodigoUnidMediDest());
		
		return resultado;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Entering 'delete' method");
		
		// Variable
		Map map = (HashMap)this.beanRegistroSeleccionado;
		MantenimientoAPEFactoresConversionSearchForm f = new MantenimientoAPEFactoresConversionSearchForm();
		BeanUtils.copyProperties(f, map);
		MantenimientoAPEFactoresConversionService serviceFC = 
						(MantenimientoAPEFactoresConversionService)this.getBean("spusicc.mantenimientoAPEFactoresConversionService");
		ReporteService reporteService =	(ReporteService) this.getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		// Crear Pojo
		String[] items = {f.getCodigoMagn()};
		Map criteria = new HashMap();
		
		// Lógica de Negocio
		serviceFC.eliminarFactorConversion(criteria, items);
		
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Entering 'save' method");
		//--  Variables
		MantenimientoAPEFactoresConversionForm f =(MantenimientoAPEFactoresConversionForm)this.formMantenimiento;
		MantenimientoAPEFactoresConversionService serviceFC = 
				(MantenimientoAPEFactoresConversionService)this.getBean("spusicc.mantenimientoAPEFactoresConversionService");
		ReporteService reporteService = 
				(ReporteService) this.getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		int validaRepeticion = 0;
		
		// Crear Pojo
		Map criteria = new HashMap();
		
		// -- Lógica de Negocio
		criteria.put("codigoPais", pais.getCodigo());
		String oidPais=reporteService.getOidString("getOidPaisByCodigoPais", criteria);
		criteria.put("oidPais", oidPais);

		criteria.put("codMagn", f.getCodigoMagn());
		String OidMagnitud = serviceFC.getObtenerOidMagnitud(criteria);
		criteria.put("oidMagn", OidMagnitud);

		criteria.put("codUnidMedi",f.getCodigoUnidMediOrig());
		String oIdUnidadMedOrig = serviceFC.getObtenerOidUnidadMedida(criteria);
		criteria.put("oidUnidMediOrig", oIdUnidadMedOrig);

		criteria.remove("codUnidMedi");
		criteria.put("codUnidMedi",f.getCodigoUnidMediDest());
		String oIdUnidadMedDest = serviceFC.getObtenerOidUnidadMedida(criteria);
		criteria.put("oidUnidMediDest", oIdUnidadMedDest);

		criteria.put("numFactConv",f.getFactor());
		
		if (!this.accion.equals(this.ACCION_NUEVO)){
			log.debug("Modificación de Factores de Conversión");
			criteria.put("oidFactConv", f.getOidFactorConv());
			serviceFC.actualizarFactoresConversion(criteria);
			valorPantalla = 0;
		}else{
			log.debug("Inserción de Factores de Conversión");
			
			validaRepeticion = Integer.parseInt(serviceFC.validaRepeticionUndOrigenUndDestino(criteria));
			
			if (validaRepeticion>0){
				this.addError("Error: ", this.getResourceMessage("mantenimientoAPEFactoresConversionForm.valida"));
				valorPantalla = 1;
				f.setCodigoMagn("");
				return false;
			}
			
			serviceFC.insertarFactoresConversion(criteria);
			valorPantalla = 0;
		}
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Entering 'edit' method");
		
		MantenimientoAPEFactoresConversionForm f =new MantenimientoAPEFactoresConversionForm();
		MantenimientoAPEFactoresConversionService serviceFC = 
				(MantenimientoAPEFactoresConversionService)this.getBean("spusicc.mantenimientoAPEFactoresConversionService");
		ReporteService reporteService = 
				(ReporteService) this.getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		f.setFlagEdicion("F");
		f.setCodigoPais(pais.getCodigo());
		
		if(this.accion.equals(this.ACCION_CONSULTAR)){f.setActivo(false);}
		
		Map criteria=new HashMap();
		
		Map criteriaPais = new HashMap();
		criteriaPais.put("codigoPais", pais.getCodigo());
		
		String oidPais=reporteService.getOidString("getOidPaisByCodigoPais", criteriaPais);
		criteria.put("oidPais", oidPais);
		this.listaMagnitudes = (ArrayList)serviceFC.getMagnitudList(criteria);
		
		if(!this.accion.equals(this.ACCION_NUEVO)){
			Map map = (HashMap)this.beanRegistroSeleccionado;
			BeanUtils.copyProperties(f, map);
			
			String id = f.getCodigoMagn();
	
			if (log.isDebugEnabled()) {
				log.debug("Id seleccionado de la lista: " + id);
			}
			
			criteria.put("nombreTabla1", "APP_CONFI_CENTR_DISTR");
			criteria.put("oidFactConv",id);
			f.setOidFactorConv(id);
			
			FactorConversion bean = serviceFC.getFactorConversionObject(criteria);
				
			BeanUtils.copyProperties(f, bean);
			f.setCodigoPais(pais.getCodigo());
			f.setFlagEdicion("T");
			f.setCodigoMagn(bean.getCodigoMagn());
			f.setCodigoUnidMediOrig(bean.getCodigoUnidMediOrig());
			f.setCodigoUnidMediDest(bean.getCodigoUnidMediDest());
		}
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoAPEFactoresConversionSearchForm f = (MantenimientoAPEFactoresConversionSearchForm)this.formBusqueda;
		MantenimientoAPEFactoresConversionService serviceFC = 
						(MantenimientoAPEFactoresConversionService)this.getBean("spusicc.mantenimientoAPEFactoresConversionService");
		ReporteService reporteService = 
						(ReporteService) this.getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		//-- Crear pojo
		Map criteria=new HashMap();
		
		Map criteriaPais = new HashMap();
		criteriaPais.put("codigoPais", pais.getCodigo());

		//-- Logica negocio -----------------------------------------
		String oidPais=reporteService.getOidString("getOidPaisByCodigoPais", criteriaPais);
		criteria.put("oidPais", oidPais);
		this.listaMagnitudes = (ArrayList)serviceFC.getMagnitudList(criteria);

		//-- Peticiones
		f.setCodigoPais(pais.getCodigo());
	}

	@Override
	protected String devuelveMensajeKeySaveOK() {
	
		if(this.accion.equals(this.ACCION_NUEVO)){
			return "mantenimientoAPEFactoresConversionForm.insert";
		}else{
			return "mantenimientoAPEFactoresConversionForm.update";
		}	
	}
	
	
	public void loadLineaArmado(ValueChangeEvent val){
		if (log.isDebugEnabled()) {
			log.debug("loadSubTipoCliente");
		}
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		String valor = (String) val.getNewValue();
		
		this.listaUnidades = aSvc.getUnidadesMedidaListar(pais.getCodigo(), valor);
	}
	
}
