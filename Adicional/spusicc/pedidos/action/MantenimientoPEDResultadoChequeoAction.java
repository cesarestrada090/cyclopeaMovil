package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.ResultadoChequeo;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDResultadoChequeoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoOCRPreProductosICESearchForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDResultadoChequeoForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDResultadoChequeoSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoPEDResultadoChequeoAction extends BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List resultadosChequeoList;
	
	
	
	/**
	 * @return the resultadosChequeoList
	 */
	public List getResultadosChequeoList() {
		return resultadosChequeoList;
	}

	/**
	 * @param resultadosChequeoList the resultadosChequeoList to set
	 */
	public void setResultadosChequeoList(List resultadosChequeoList) {
		this.resultadosChequeoList = resultadosChequeoList;
	}

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoPEDResultadoChequeoList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoPEDResultadoChequeoForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoPEDResultadoChequeoSearchForm objForm = new MantenimientoPEDResultadoChequeoSearchForm();
		return objForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoPEDResultadoChequeoSearchForm f = (MantenimientoPEDResultadoChequeoSearchForm)this.formBusqueda;
		
		MantenimientoPEDResultadoChequeoService service = (MantenimientoPEDResultadoChequeoService)getBean("spusicc.pedidos.mantenimientoPEDResultadoChequeoService");
		
		Map map = new HashMap();
		
		map.put("codigoResultadoChequeo", f.getCodigoResultadoChequeo());
		
		List list = service.getResultadosChequeoList(map);
		
		return list;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoPEDResultadoChequeoForm f = new MantenimientoPEDResultadoChequeoForm();
		ResultadoChequeo bean = (ResultadoChequeo)this.beanRegistroSeleccionado;
		BeanUtils.copyProperties(f, bean);
		MantenimientoPEDResultadoChequeoService service = (MantenimientoPEDResultadoChequeoService)getBean("spusicc.pedidos.mantenimientoPEDResultadoChequeoService");
		boolean estado=true;
		Map map = new HashMap();
		String[] codigos = {f.getCodigoResultadoChequeo()};
		map.put("selectedItems", codigos );
		
		service.deleteResultadosChequeo(map);
		
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoPEDResultadoChequeoForm f = (MantenimientoPEDResultadoChequeoForm)this.formMantenimiento;
		
		MantenimientoPEDResultadoChequeoService service = (MantenimientoPEDResultadoChequeoService)getBean("spusicc.pedidos.mantenimientoPEDResultadoChequeoService");
		
		Map params = new HashMap();
		
		try{
		
			if(this.accion.equals(this.ACCION_NUEVO)){
				
				params.put("codigoPais", f.getCodigoPais());
				params.put("codigoResultadoChequeo", f.getCodigoResultadoChequeo());
				params.put("descripcionResultadoChequeo", f.getDescripcionResultadoChequeo());
				
				service.insertResultadoChequeo(params);
				
			}else{
				
				String idOld = f.getOid();
				
				params.put("idOld", idOld);
				params.put("codigoPais", f.getCodigoPais());
				params.put("codigoResultadoChequeo", f.getCodigoResultadoChequeo());
				params.put("descripcionResultadoChequeo", f.getDescripcionResultadoChequeo());
				
				service.updateResultadoChequeo(params);
			}
			
			Map map = new HashMap();
			
			map.put("codigoResultadoChequeo", null);
			
			this.resultadosChequeoList = service.getResultadosChequeoList(map);
			
			return true;
			
		}catch(Exception e){
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) {
				error = e.getLocalizedMessage();
			}
			this.addError("Error:",this.getResourceMessage("errors.detail", new Object[]{ error }));	
			return false;
		}
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		
		
		ResultadoChequeo bean = (ResultadoChequeo)this.beanRegistroSeleccionado;
		MantenimientoPEDResultadoChequeoForm f = new MantenimientoPEDResultadoChequeoForm();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		f.setCodigoResultadoChequeo(null);
		f.setDescripcionResultadoChequeo(null);
		
		if(this.accion.equals(this.ACCION_CONSULTAR)){ f.setActivo(true);}
		
		if(!this.accion.equals(this.ACCION_NUEVO)){
			BeanUtils.copyProperties(f, bean);
			String id = f.getCodigoResultadoChequeo();
			
			MantenimientoPEDResultadoChequeoService service = (MantenimientoPEDResultadoChequeoService)getBean("spusicc.pedidos.mantenimientoPEDResultadoChequeoService");
			
			ResultadoChequeo resultadoChequeo = service.getResultadoChequeoObject(id);
			
			f.setCodigoPais(resultadoChequeo.getCodigoPais());
			f.setCodigoResultadoChequeo(resultadoChequeo.getCodigoResultadoChequeo());
			f.setDescripcionResultadoChequeo(resultadoChequeo.getDescripcionResultadoChequeo());
			f.setOid(f.getCodigoResultadoChequeo());	
		}
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoPEDResultadoChequeoSearchForm f = (MantenimientoPEDResultadoChequeoSearchForm)this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() {
		
		if (this.accion.equals(this.ACCION_NUEVO)) {
			return "mantenimientoPEDResultadoChequeoForm.cabecera.insert";
		} else {
			return "mantenimientoPEDResultadoChequeoForm.cabecera.update";
		}
	}

}
