package biz.belcorp.ssicc.web.spusicc.fdv.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.ConsultaFDVParametro;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.spusicc.fdv.ConsultaFDVParametroService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.fdv.form.ConsultaFDVParametroForm;


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
public class ConsultaFDVParametroAction  extends BaseMantenimientoSearchAbstractAction{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3037607338180378618L;
	private List lista;

	
	
	/**
	 * @return the lista
	 */
	public List getLista() {
		return lista;
	}

	/**
	 * @param lista the lista to set
	 */
	public void setLista(List lista) {
		this.lista = lista;
	}

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "consultaFDVParametroForm";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "lista";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		ConsultaFDVParametroForm objForm = new ConsultaFDVParametroForm();
		return objForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		List listParametros = new ArrayList();
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
        
        ConsultaFDVParametroForm searchForm = (ConsultaFDVParametroForm) this.formBusqueda;
        
        // Obtenemos las propiedades del bean como un 'Map'
        Map criteria = BeanUtils.describe(searchForm);
        
        if(StringUtils.isNotBlank(searchForm.getNombreGrupo())) {
            criteria.put("nomGroup", "%" + searchForm.getNombreGrupo() + "%");
        }
        
        if(StringUtils.isNotBlank(searchForm.getCodigoPais())) {
            criteria.put("codigoPais", searchForm.getCodigoPais());
        }else{
        	criteria.put("codigoPais", pais.getCodigo());
        }
        
        ConsultaFDVParametroService service = (ConsultaFDVParametroService)
        getBean("spusicc.consultaFDVParametroService");
        
        DecimalFormat formateador = new DecimalFormat("######.##%");
        ConsultaFDVParametro bean = new ConsultaFDVParametro();
        List list = service.getParametrosFDVByCriteria(criteria);
        
        if(list != null && !list.isEmpty()){
	        for (int i = 0; i < list.size(); i++) {
				
	        	bean = (ConsultaFDVParametro)list.get(i);
	        	
	        	if(!bean.getCodPara().equalsIgnoreCase("012")){
	        		bean.setValPara(formateador.format(Double.parseDouble(bean.getValPara())));
		        }
		        
	        	listParametros.add(bean);			        
			}
        }
        this.lista = listParametros;
        
        return listParametros;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
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

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		try {
			
			List listParametros = new ArrayList();
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
	        
	        ConsultaFDVParametroForm searchForm = (ConsultaFDVParametroForm) this.formBusqueda;

	        // Obtenemos las propiedades del bean como un 'Map'
	        Map criteria = BeanUtils.describe(searchForm);
	        criteria.put("codigoPais", pais.getCodigo());
	        
	        ConsultaFDVParametroService service = (ConsultaFDVParametroService)
	        getBean("spusicc.consultaFDVParametroService");
	        
	        DecimalFormat formateador = new DecimalFormat("######.##%");
	        ConsultaFDVParametro bean = new ConsultaFDVParametro();
	        List list = service.getParametrosFDVByCriteria(criteria);
	        
	        if(list != null && !list.isEmpty()){
		        for (int i = 0; i < list.size(); i++) {
					
		        	bean = (ConsultaFDVParametro)list.get(i);
		        	
		        	if(!bean.getCodPara().equalsIgnoreCase("012")){
		        		bean.setValPara(formateador.format(Double.parseDouble(bean.getValPara())));
			        }
			        
		        	listParametros.add(bean);			        
				}
	        }

	        this.lista = listParametros;
	        
		}
		catch (Exception e) {
			String error = e.getMessage();
			this.getResourceMessage("errors.detail", new Object[]{error});
		}
	}

}
