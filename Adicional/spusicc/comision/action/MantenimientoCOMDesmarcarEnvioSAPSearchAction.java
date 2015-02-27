package biz.belcorp.ssicc.web.spusicc.comision.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMCalificacionComisionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMDesmarcarEnvioSAPSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class MantenimientoCOMDesmarcarEnvioSAPSearchAction extends BaseMantenimientoSearchAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1823385594499021362L;

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
		MantenimientoCOMDesmarcarEnvioSAPSearchForm searchForm = new MantenimientoCOMDesmarcarEnvioSAPSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception 
	{
		MantenimientoCOMCalificacionComisionService service = (MantenimientoCOMCalificacionComisionService)getBean("spusicc.mantenimientoCOMCalificacionComisionService");
		MantenimientoCOMDesmarcarEnvioSAPSearchForm f = (MantenimientoCOMDesmarcarEnvioSAPSearchForm)this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		f.setCodigoPais(pais.getCodigo());
		Map criteria = new HashMap();
		criteria.put("codigoPais",f.getCodigoPais());
		criteria.put("codigoLider",f.getCodigoLider());
				
		List lista = service.getEnviosSAP(criteria);
				
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		this.mostrarBotonNuevo = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		
		MantenimientoCOMDesmarcarEnvioSAPSearchForm f = (MantenimientoCOMDesmarcarEnvioSAPSearchForm)this.formBusqueda;
				
		f.setCodigoLider("");		
	}
	
	/**
	 * Metodo que desmarca el envio SAP
	 * @param request
	 * @param form
	 */
	public void desmarcar(ActionEvent event) throws Exception 
	{		
		MantenimientoCOMCalificacionComisionService service = (MantenimientoCOMCalificacionComisionService) getBean("spusicc.mantenimientoCOMCalificacionComisionService");
		
		Map envioSeleccionado = (Map) this.beanRegistroSeleccionado;		
		
		try {
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			Map criteria = new HashMap();
			criteria.put("codigoLider", envioSeleccionado.get("codigoLider"));
			criteria.put("usuarioEnvio", usuario.getLogin());

			service.desmarcarEnvio(criteria);
			
			this.addInfo("",this.getResourceMessage("mantenimientoCOMDesmarcarEnvioSAPSearchForm.edit"));

			this.listaBusqueda = this.setFindAttributes();
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);

		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
			throw new Exception(this.getResourceMessage("errors.detail", new Object[]{error}));
		}
	}
}
