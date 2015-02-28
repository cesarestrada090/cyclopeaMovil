package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.aop.framework.adapter.ThrowsAdviceInterceptor;

import com.sun.faces.util.CollectionsUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.Flete;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.FleteDetalle;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMMinimoNuevasService;
import biz.belcorp.ssicc.service.spusicc.men.MantenimientoMENIngresoGerenteZonalesService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDClasificacionesChequeoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDFleteService;
import biz.belcorp.ssicc.web.MPantallaPrincipalBean;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.hip.action.ConsultaHIPDatosClienteAction;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMMinimoNuevasForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMMinimoNuevasSearchForm;
import biz.belcorp.ssicc.web.spusicc.men.form.MantenimientoMENEscaleraGananciaForm;
import biz.belcorp.ssicc.web.spusicc.men.form.MantenimientoMENEscaleraGananciaSearchForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDFleteDetalleForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDFleteForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDFleteSearchForm;

/**
 * @author Giovanni Ascarza
 */
@ManagedBean
@SessionScoped
public class MantenimientoPEDFleteDetalleAction extends BasePopupAbstractAction {

	private static final long serialVersionUID = -2039671691149644047L;

	public boolean error;
	private DataTableModel dataModelDetalle;
	private List listaFleteDetalle;
	
/*	@ManagedProperty(value="#{mantenimientoPEDFleteSearchAction}")
	private MantenimientoPEDFleteSearchAction mantenimientoPEDFleteSearchAction;
	*/
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		return new MantenimientoPEDFleteDetalleForm();
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("'setViewAttributes'");
 
		MantenimientoPEDFleteSearchAction mPadre = (MantenimientoPEDFleteSearchAction) this.mPantallaPrincipalBean.getManageBeanPadre();
		MantenimientoPEDFleteService servicePed = (MantenimientoPEDFleteService) getBean("spusicc.pedidos.mantenimientoPEDFleteService");
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoPEDFleteDetalleForm f = (MantenimientoPEDFleteDetalleForm) this.formBusqueda; 		
		
		f.setCodigoPais(pais.getCodigo());
		f.setRangoInferior("0");
		f.setRangoSuperior("0");
		f.setMontoFijo("0");
		 
		if (mPadre!=null) {
			if (mPadre.getAccion().equals(this.ACCION_MODIFICAR)) {
				
				Map mapa = (Map) mPadre.getBeanRegistroSeleccionado();

				//Obtenemos el ID 
				String oidFlete = MapUtils.getString(mapa, "oidFlete");
						
				if(StringUtils.isNotBlank(oidFlete) && Integer.parseInt(oidFlete) > 0){
						listaFleteDetalle = servicePed.getDetalleFleteList(oidFlete);
						dataModelDetalle = new DataTableModel(listaFleteDetalle);
				}
			}
		}
		

		this.setListaBusqueda(null);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		log.debug("'setFindAttributes'");
	    return null;
	}

	public void insertarRegistro(ActionEvent actionEvent) {
	   log.debug("'insertarRegistro'");
	
	   MantenimientoPEDFleteDetalleForm detForm = (MantenimientoPEDFleteDetalleForm)this.formBusqueda;
		
		boolean agregarInicio = false;
		try {
		if (CollectionUtils.isEmpty(listaFleteDetalle)) {
			this.listaFleteDetalle = new ArrayList();
		} else {
			// Valida que el nro días comisión sea > al del tramo anterior
			FleteDetalle primerRegistro = (FleteDetalle) listaFleteDetalle.get(0);
			FleteDetalle ultimoRegistro = (FleteDetalle) listaFleteDetalle.get(listaFleteDetalle.size() - 1);

			for (int i = 0; i < listaFleteDetalle.size(); i++) {
				// Si el rango que ingreso es mayor al que tengo 8 7
				if ((Float.parseFloat(ultimoRegistro.getRangoInferior()) < Float
						.parseFloat(detForm.getRangoInferior()) && Float
						.parseFloat(detForm.getRangoInferior()) < Float
						.parseFloat(ultimoRegistro.getRangoSuperior()))
						|| (Float.parseFloat(ultimoRegistro.getRangoInferior()) < Float
								.parseFloat(detForm.getRangoSuperior()) && Float
								.parseFloat(detForm.getRangoSuperior()) < Float
								.parseFloat(ultimoRegistro.getRangoSuperior()))) {

					log.debug("Rango ya contenido ");
					this.addWarn("", this.getResourceMessage("mantenimientoPEDFleteDetalleForm.validationRangoSuperpuesto"));
					return;
				} else {
					if ((Float.parseFloat(detForm.getRangoInferior()) == Float.parseFloat(ultimoRegistro.getRangoSuperior()) + 1f) || (Float.parseFloat(detForm.getRangoInferior()) == Float.parseFloat(ultimoRegistro.getRangoSuperior()) + 0.1f) || (Float.parseFloat(detForm.getRangoInferior()) == Float.parseFloat(ultimoRegistro.getRangoSuperior()) + 0.01f) ) {
						log.debug("Rango OK - Debajo");
						agregarInicio = false;
					} else {

						if ((Float.parseFloat(primerRegistro.getRangoInferior()) == Float.parseFloat(detForm.getRangoSuperior()) + 1f) || (Float.parseFloat(primerRegistro.getRangoInferior()) == Float.parseFloat(detForm.getRangoSuperior()) + 0.1f) || (Float.parseFloat(primerRegistro.getRangoInferior()) == Float.parseFloat(detForm.getRangoSuperior()) + 0.01f)) {
							log.debug("Rango OK - Primero");
							agregarInicio = true;
						} else {

							log.debug("Rango con hueco");
							
							this.addWarn("", this.getResourceMessage("mantenimientoPEDFleteDetalleForm.validationNoSeguida"));
						        return;
						}
					}
				}

				break;
			}
		}

		FleteDetalle bean = new FleteDetalle();
		bean.setMontoFijo(detForm.getMontoFijo());
		bean.setRangoInferior(detForm.getRangoInferior());
		bean.setRangoSuperior(detForm.getRangoSuperior());
	//	bean.setOidFlete(oidFlete);
		bean.setOidDetFlete(String.valueOf(System.currentTimeMillis()));

		if (agregarInicio)
			listaFleteDetalle.add(0, bean);
		else
			listaFleteDetalle.add(bean);

		detForm.setRangoInferior("");
		detForm.setRangoSuperior("");
		detForm.setMontoFijo("");
	
		if (listaFleteDetalle.size() == 1) {
			dataModelDetalle = new DataTableModel(listaFleteDetalle);
		}
		
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
    }
	
	public void eliminarRegistro(ActionEvent actionEvent) {
      
       log.debug("'eliminarRegistro'");
       
       FleteDetalle fleteDetalle =(FleteDetalle)this.beanRegistroSeleccionado;
	   
	   //Solo se puede eliminar todos o el primero y el ultimo
		int indiceEliminar = -1;
	
	    for (int i = 0; i < listaFleteDetalle.size(); i++) {
	    	FleteDetalle obj = (FleteDetalle) listaFleteDetalle.get(i);
			if (obj.getOidDetFlete().equals(fleteDetalle.getOidDetFlete())) {
				if (i==0) {
					indiceEliminar = 0;
				}else if(i==(listaFleteDetalle.size() - 1)){
					indiceEliminar = (listaFleteDetalle.size() - 1);
				}
				
				if(indiceEliminar != -1)
				{
					listaFleteDetalle.remove(fleteDetalle);
				}
				else
				{
				  addError("", this.getResourceMessage("mantenimientoPEDFleteDetalleForm.lista.delete.error"));
					
				}
				break;
			}
		}
       
    }


	public DataTableModel getDataModelDetalle() {
		return dataModelDetalle;
	}


	public void setDataModelDetalle(DataTableModel dataModelDetalle) {
		this.dataModelDetalle = dataModelDetalle;
	}


	public List getListaFleteDetalle() {
		return listaFleteDetalle;
	}


	public void setListaFleteDetalle(List listaFleteDetalle) {
		this.listaFleteDetalle = listaFleteDetalle;
	}


	/*public MantenimientoPEDFleteSearchAction getMantenimientoPEDFleteSearchAction() {
		return mantenimientoPEDFleteSearchAction;
	}


	public void setMantenimientoPEDFleteSearchAction(
			MantenimientoPEDFleteSearchAction mantenimientoPEDFleteSearchAction) {
		this.mantenimientoPEDFleteSearchAction = mantenimientoPEDFleteSearchAction;
	}*/


	
	

}
