package biz.belcorp.ssicc.web.spusicc.cobranzas.action;

import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.CobradorPais;
import biz.belcorp.ssicc.service.spusicc.cobranzas.MantenimientoCOBGenericoService;
import biz.belcorp.ssicc.web.form.SistemaForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.MantenimientoCOBCobradorPaisForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.MantenimientoCOBCobradorPaisSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoCOBCobradorPaisSearchAction extends BaseMantenimientoSearchAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3443139709928961032L;

	@Override
	protected String getSalirForward() 
	{
		return "mantenimientoCOBCobradorPaisList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception 
	{
		return "mantenimientoCOBCobradorPaisForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception 
	{
		MantenimientoCOBCobradorPaisSearchForm searchForm = new MantenimientoCOBCobradorPaisSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			   this.log.debug("JFA Entering: setFindAttributes");
			}
		
		MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) this.getBean("spusicc.mantenimientoCOBGenericoService");
		
		/* obteniendo valores */
		CobradorPais bean = new CobradorPais();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		bean.setCodigoPais(pais.getCodigo());
		
		/* Obteniendo Lista */
		List lista = service.getListaCobradorPais(bean);
		
		if (log.isDebugEnabled()) {
			this.log.debug("JFA Todo Ok: Redireccionando");
		}
		
		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception 
	{
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		HashMap<String, Object> cobradorSeleccionado = (HashMap<String, Object>) this.beanRegistroSeleccionado;
		String codigoPais = cobradorSeleccionado.get("codigoPais").toString();
		String codigoCobrador = cobradorSeleccionado.get("codigoCobrador").toString();

		if (codigoCobrador != null) {

			try {			
				
				MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) 
				getBean("spusicc.mantenimientoCOBGenericoService");	

				CobradorPais CobradorPais  = new CobradorPais();
				
				CobradorPais.setCodigoPais(codigoPais);
				CobradorPais.setCodigoCobrador(codigoCobrador);
				
				service.deleteCobradorPais(CobradorPais, usuario);
				
				this.getResourceMessage("mantenimientoCOBCobradorPaisForm.deleted");
				
			}catch (Exception e) {
				String error = e.getMessage();
				if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();				
				throw new Exception(this.getResourceMessage("errors.detail", new Object[]{error}));
			}
		}
	
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'setSaveAttributes' method");
		}	
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		MantenimientoCOBCobradorPaisForm f = (MantenimientoCOBCobradorPaisForm) this.formMantenimiento;
		
		MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) getBean("spusicc.mantenimientoCOBGenericoService");
								
		CobradorPais cobrador = new CobradorPais();
		
		try {
			BeanUtils.copyProperties(cobrador, f);		
			
			if (!f.isNewRecord()) {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA MODIFICACION");
				}
				service.updateCobradorPais(cobrador, usuario);
				
			} else {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA CREACION");
				}
				service.insertCobradorPais(cobrador, usuario);
			}
		} catch (Exception e) {
				throw new Exception(this.getResourceMessage(e.getMessage()));
		}	
		
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception 
	{		
		MantenimientoCOBCobradorPaisForm f = new MantenimientoCOBCobradorPaisForm();
		HashMap<String, Object> cobradorSeleccionado = (HashMap<String, Object>) this.beanRegistroSeleccionado;
								
		//Seteando Codigo de Pais
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());	
														
		//Limpiando las propiedades del Form
		f.setCodigoCobrador("");
		f.setNombreCobrador("");
		f.setEmail("");
		f.setIndicadorActividad("");
		f.setIndicadorSupervisor("");
		f.setIndicadorJefe("");
		f.setIndicadorEmailProcesoAsignacion("");
		f.setIndicadorEmailProcesoActualizacion("");
		f.setRutaFTP("");
		f.setUsuarioFTP("");
		f.setClaveFTP("");
				
		if (!this.accion.equals(this.ACCION_NUEVO)) {			
			
			String codigoPais = cobradorSeleccionado.get("codigoPais").toString();
			String codigoCobrador = cobradorSeleccionado.get("codigoCobrador").toString();

			if (codigoCobrador != null) {
				if (log.isDebugEnabled()) {
					log.debug("Id seleccionado de la lista: " + codigoCobrador);
				}

				MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) getBean("spusicc.mantenimientoCOBGenericoService");

				CobradorPais cobradorPais = new CobradorPais();

				cobradorPais.setCodigoPais(codigoPais);
				cobradorPais.setCodigoCobrador(codigoCobrador);

				cobradorPais = (CobradorPais) service.getCobradorPais(cobradorPais);

				BeanUtils.copyProperties(f, cobradorPais);

				log.debug("JFA - Todo OK");
			}
		}
		
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			this.log.debug("JFA Entering: setViewAttributes");
		}

		MantenimientoCOBCobradorPaisSearchForm f = (MantenimientoCOBCobradorPaisSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());

		/* Obteniendo valores */
		CobradorPais bean = new CobradorPais();
		bean.setCodigoPais(pais.getCodigo());

		/* Obteniendo Lista */
		MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) this.getBean("spusicc.mantenimientoCOBGenericoService");
		
		if (log.isDebugEnabled()) {
			this.log.debug("JFA Todo Ok: Redireccionando");
		}
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoCOBCobradorPaisForm cobradorForm = (MantenimientoCOBCobradorPaisForm) this.formMantenimiento;
		boolean isNew = cobradorForm.isNewRecord();
		if(isNew){
			return "mantenimientoCOBCobradorPaisForm.add";
		}else{
			return "mantenimientoCOBCobradorPaisForm.updated";
		}	
	}
	
}
