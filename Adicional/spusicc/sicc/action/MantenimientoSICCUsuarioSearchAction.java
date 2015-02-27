package biz.belcorp.ssicc.web.spusicc.sicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.service.spusicc.sicc.MantenimientoSICCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.sicc.form.MantenimientoSICCUsuarioSearchForm;

/**
 * Action invocado desde la pantalla de mantenimiento del objeto Usuario SICC.
 * <p>
 * <a href="RolSearchAction.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a> 
 */

@ManagedBean
@SessionScoped
public class MantenimientoSICCUsuarioSearchAction extends BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6052662102155361651L;
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoSICCUsuarioList";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {		
		MantenimientoSICCUsuarioSearchForm mantenimientoSICCUsuarioSearchForm = new MantenimientoSICCUsuarioSearchForm();
		return mantenimientoSICCUsuarioSearchForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {		
		
		if (this.log.isDebugEnabled()) {
            this.log.debug("Entering 'setFindAttributes' method");
        }

		MantenimientoSICCUsuarioSearchForm searchForm = (MantenimientoSICCUsuarioSearchForm)this.formBusqueda;

        // Obtenemos las propiedades del bean como un 'Map'
        Map criteria = new HashMap();
        
        // Modificamos los valores que requieren el caracter '%'
        if(StringUtils.isNotBlank(searchForm.getLogin())) {
            criteria.put("login", searchForm.getLogin()+ "%");
        }
        if(StringUtils.isNotBlank(searchForm.getPrimerNombre())) {
            criteria.put("primerNombre", searchForm.getPrimerNombre()+ "%");
        }
        if(StringUtils.isNotBlank(searchForm.getSegundoNombre())) {
            criteria.put("segundoNombre", searchForm.getSegundoNombre()+ "%");
        }
        if(StringUtils.isNotBlank(searchForm.getApellidoPaterno())) {
            criteria.put("apellidoPaterno", searchForm.getApellidoPaterno()+ "%");
        }
        if(StringUtils.isNotBlank(searchForm.getApellidoMaterno())) {
            criteria.put("apellidoMaterno", searchForm.getApellidoMaterno()+ "%");
        }
        if(StringUtils.isNotBlank(searchForm.getEmail())) {
            criteria.put("email", searchForm.getEmail()+ "%");
        }
        if(StringUtils.isNotBlank(searchForm.getTelefono())) {
            criteria.put("telefono", searchForm.getTelefono()+ "%");
        }

        if (this.log.isDebugEnabled()) {
            this.log.debug(criteria.toString());
        }

        MantenimientoSICCService service = (MantenimientoSICCService) this.getBeanService("sicc.mantenimientoSICCService");
        List lista = service.getListaSICCUsuarioByCriteria(criteria);        		
        return lista;

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
		// TODO Auto-generated method stub		
	}

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}