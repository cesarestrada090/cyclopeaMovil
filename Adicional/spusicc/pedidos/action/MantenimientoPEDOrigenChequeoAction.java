package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.OrigenChequeo;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDOrigenChequeoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDOrigenChequeoForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class MantenimientoPEDOrigenChequeoAction extends BaseMantenimientoSearchAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2288715923049322802L;
	
	private List origenPedidoList;
	private DataTableModel tableModel;

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
		MantenimientoPEDOrigenChequeoForm searchForm = new MantenimientoPEDOrigenChequeoForm(); 
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
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
	protected void setViewAtributes() throws Exception 
	{
		this.mostrarBotonNuevo = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarListaBusqueda = false;
		this.mostrarBotonBuscar = false;
		
		MantenimientoPEDOrigenChequeoService mantenimientoPEDOrigenChequeoService = (MantenimientoPEDOrigenChequeoService) getBean("spusicc.pedidos.mantenimientoPEDOrigenChequeoService");

		List origenPedidoList = mantenimientoPEDOrigenChequeoService.getOrigenChequeoList();

		this.origenPedidoList = origenPedidoList;
		this.tableModel = new DataTableModel(this.origenPedidoList);
	}
	
	public void guardar(ActionEvent event)
	{
		MantenimientoPEDOrigenChequeoService mantenimientoPEDOrigenChequeoService = (MantenimientoPEDOrigenChequeoService)getBean("spusicc.pedidos.mantenimientoPEDOrigenChequeoService");	
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map criteria = new HashMap();
		
		criteria.put("usuario", usuario.getLogin());
		List origenChequeoList = mantenimientoPEDOrigenChequeoService.getOrigenChequeoList();
		List sencuenciaEvalList =  new ArrayList();
				
		for(int i = 0 ; i<this.origenPedidoList.size(); i++){
			
			OrigenChequeo origenPedido = (OrigenChequeo)this.origenPedidoList.get(i);
			String secuenciaEval = origenPedido.getSecuenciaEvaluacion().toString();			
			sencuenciaEvalList.add(secuenciaEval);
		}
		
		mantenimientoPEDOrigenChequeoService.updateOrigenChequeo(origenChequeoList,criteria,sencuenciaEvalList);
		
		this.addInfo("", this.getResourceMessage("mantenimientoPEDOrigenChequeoForm.cabecera.update"));
		
		this.origenPedidoList = mantenimientoPEDOrigenChequeoService.getOrigenChequeoList();
		this.tableModel = new DataTableModel(this.origenPedidoList);
	}

	public List getOrigenPedidoList() {
		return origenPedidoList;
	}

	public void setOrigenPedidoList(List origenPedidoList) {
		this.origenPedidoList = origenPedidoList;
	}

	public DataTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(DataTableModel tableModel) {
		this.tableModel = tableModel;
	}
}
