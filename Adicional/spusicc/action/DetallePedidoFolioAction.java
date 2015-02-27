/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.action;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.spusicc.pedidos.model.DetallePedidoFolio;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.form.DetallePedidoFolioForm;

@ManagedBean  
@SessionScoped
public class DetallePedidoFolioAction extends BasePopupAbstractAction {
	
	private static final String PEDIDO_FOLIO = "PEDIDOFOLIO";

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		DetallePedidoFolioForm detallePedidoFolioForm = new DetallePedidoFolioForm();				
		return detallePedidoFolioForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'DetallePedidoFolioAction - setViewAtributes' method");
		}	
		DetallePedidoFolioForm detallePedidoFolioForm = (DetallePedidoFolioForm)this.getFormBusqueda();
		detallePedidoFolioForm.setCkhVerDatos(false);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'DetallePedidoFolioAction - setFindAttributes' method");
		}
		
		DetallePedidoFolioForm detallePedidoFolioForm = (DetallePedidoFolioForm)this.getFormBusqueda();
		
		List lista = new ArrayList();
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String accion = externalContext.getRequestParameterMap().get("accion");
		
		log.debug("accion:" + accion);
		
		if(accion!=null){
			
			if(accion.equals(this.PEDIDO_FOLIO)){
				
				ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) 
						this.getBeanService("spusicc.procesoSTOEjecucionValidacionesService");
				
				String parametro = externalContext.getRequestParameterMap().get("parametro");
				
				lista = procesoSTOEjecucionValidacionesService.getDetallePedidoFolio(parametro);
				
				//			
				if(lista != null && lista.size() > 0)
				{
					//Objeto a session
					DetallePedidoFolio folio = (DetallePedidoFolio)lista.get(0);				
					try {
						BeanUtils.copyProperties(detallePedidoFolioForm, folio);
					} 
					catch (Exception e) {
						log.error(e.getMessage(), e);
					}				
				}	
				
			}
			
		}
				
		return lista;

	}

}
