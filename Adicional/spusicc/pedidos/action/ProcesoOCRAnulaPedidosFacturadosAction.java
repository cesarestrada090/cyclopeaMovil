package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoOCRAnulaPedidosFacturadosForm;

@ManagedBean
@SessionScoped
public class ProcesoOCRAnulaPedidosFacturadosAction extends BaseMantenimientoSearchAbstractAction {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -9717837268930438L;
	private List pedPedidosFactList;
	private List ocrIndicadoresList;

	

    /**
     * Pone en session la lista de indicadores anulados (0) , facturados(1) o todos("")
     * @param request
     */
    private void loadCombo() {
    	List resultado=new ArrayList();
		Base[] mes = new Base[3];
		String indicadorAnulado = this.getResourceMessage(
				"procesoOCRAnulaPedidosFacturadosForm.indicadorAnulado");
	
		String indicadorFacturado = this.getResourceMessage(
				"procesoOCRAnulaPedidosFacturadosForm.indicadorFacturado");
		
		String indicadorTodo = this.getResourceMessage(
			"procesoOCRAnulaPedidosFacturadosForm.indicadorTodo");
		
		mes[0] = new Base();
		mes[0].setCodigo("");
		mes[0].setDescripcion(indicadorTodo);
		resultado.add(mes[0]);
		
		mes[1] = new Base();
		mes[1].setCodigo(Constants.NUMERO_UNO);
		mes[1].setDescripcion(indicadorFacturado);
		resultado.add(mes[1]);
		
		mes[2] = new Base();
		mes[2].setCodigo(Constants.NUMERO_CERO);
		mes[2].setDescripcion(indicadorAnulado);
		resultado.add(mes[2]);
		
		ocrIndicadoresList = resultado;
		
	}
	
	/**
	 * Metodo que completa el codigo de cliente
	 * @param codigoCliente
	 * @param codigoPais
	 * @return
	 */
	private String completacodigoCliente(String codigoCliente, String codigoPais) {
		PaisService service = (PaisService) getBean("paisService");
		Pais pais = service.getPais(codigoPais);			
		if (codigoCliente.length() < pais.getLongitudCodigoCliente()){			
			for (int i=codigoCliente.length(); i< pais.getLongitudCodigoCliente(); i++)
				codigoCliente = "0"+codigoCliente;							
		}		                       
		return codigoCliente;	
	}

	/**
	 * Metodo que consulta los historicos anulados
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void consultar()
            throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'consultar' method" );
        }
        
		ProcesoOCRAnulaPedidosFacturadosForm f = (ProcesoOCRAnulaPedidosFacturadosForm) this.formBusqueda;
		f.setFechaFacturacion(DateUtil.convertDateToString(f.getFechaFacturacionD()));
        f.setFechaSolicitud(DateUtil.convertDateToString(f.getFechaSolicitudD()));
        //f.setSelectedItems(new String[]{});
        
        Map criteria =  BeanUtils.describe(f);
        criteria.put("estado", Constants.ESTADO_ACTIVO);
        
        MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
        List list = service.getPedidosFacturadosAnuladosByCriteria(criteria);
        
    }

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "procesoOCRAnulaPedidosFacturadosList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "procesoOCRAnulaPedidosFacturadosForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		ProcesoOCRAnulaPedidosFacturadosForm form =  new ProcesoOCRAnulaPedidosFacturadosForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
            log.debug("Entering 'search' method");
        }

		
        MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
        ProcesoOCRAnulaPedidosFacturadosForm anulaPedidosFacturadosForm = (ProcesoOCRAnulaPedidosFacturadosForm)this.formBusqueda;
       // anulaPedidosFacturadosForm.setFechaSolicitudD(new Date());
        //anulaPedidosFacturadosForm.setFechaFacturacionD(new Date());       
        anulaPedidosFacturadosForm.setFechaFacturacion(DateUtil.convertDateToString(anulaPedidosFacturadosForm.getFechaFacturacionD()));
        anulaPedidosFacturadosForm.setFechaSolicitud(DateUtil.convertDateToString(anulaPedidosFacturadosForm.getFechaSolicitudD()));
        //Seteando los indicadores del combo
        if(Constants.NUMERO_UNO.equals(anulaPedidosFacturadosForm.getIndicadorBusqueda())){//es facturadas
        	anulaPedidosFacturadosForm.setIndicadorFacturado(Constants.NUMERO_UNO);
        	anulaPedidosFacturadosForm.setIndicadorAnulado(Constants.NUMERO_CERO);
        }
        if(Constants.NUMERO_CERO.equals(anulaPedidosFacturadosForm.getIndicadorBusqueda())){//es anulada
        	anulaPedidosFacturadosForm.setIndicadorFacturado(Constants.NUMERO_CERO);
        	anulaPedidosFacturadosForm.setIndicadorAnulado(Constants.NUMERO_UNO);
        }
        /*si no es ninguno es todas y ya viene con valores nulos los indicadores de fact y anulado */  
        
        Map map = BeanUtils.describe(anulaPedidosFacturadosForm);
               
        if (map.get("codigoCliente").toString().length() > 0){        	
        	String codigoCliente = completacodigoCliente(map.get("codigoCliente").toString(),anulaPedidosFacturadosForm.getCodigoPais());
        	map.put("codigoCliente",codigoCliente);        	
        }	
                
        List list = service.getPedidosByCriteria(map);
			        
		return list;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
        MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
        ProcesoOCRAnulaPedidosFacturadosForm f = (ProcesoOCRAnulaPedidosFacturadosForm) this.formBusqueda;
		HashMap<String, Object> sistemabusqueda = (HashMap<String, Object>) this.beanRegistroSeleccionado;
		String codigoPais = (String) sistemabusqueda.get("codigoPais");
		String codigoPeriodo = (String) sistemabusqueda.get("codigoPeriodo");
		String codigoCliente = (String) sistemabusqueda.get("codigoCliente");
		String numeroLote = (String) sistemabusqueda.get("numeroLote");
        Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();		
		String[] scripts = new String [1]; 
		scripts[0] = codigoPais + "-" +  codigoPeriodo + "-" + codigoCliente + "-" + numeroLote;

        String id="";
        for (int i=0; i<scripts.length;i++){
        	//id=f.getSelectedItems()[i];     	
        	id=scripts[i];
        	Map criteria = new HashMap();
        	criteria.put("codigoPais",StringUtils.split(id, "-")[0]);
        	criteria.put("codigoPeriodo",StringUtils.split(id, "-")[1]);
        	criteria.put("codigoCliente",StringUtils.split(id, "-")[2]);
        	criteria.put("numeroLote",StringUtils.split(id, "-")[3]);
        	criteria.put("codigoUsuario",usuario.getLogin());        	
        	/*
        	//anular pedido
        	service.anularPedidoFacturado(criteria);
        	//grabar en historico de anulados
            service.grabarPedidoFacturadoAnulado(criteria);
            */ 
        	//anular pedido Marca Flags en INT_SOLIC_CONSO_CABEC
        	//y en cabeceras y detalles de STO_DOCUM_DIGIT
        	service.anularPedidoFacturadoSTO(criteria);        	
        }
        
        String mensaje =  this.getResourceMessage("anulacion.updated");
        addError("Info : ", mensaje);                
        return true;
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
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
        
		this.mostrarBotonConsultar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonModificar = false;
		
		
        ProcesoOCRAnulaPedidosFacturadosForm anulaPedidosFacturadosForm = (ProcesoOCRAnulaPedidosFacturadosForm)this.formBusqueda;		
		// Removemos atributos session
        Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
        
        anulaPedidosFacturadosForm.setCodigoPais(pais.getCodigo());
 
        MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
        
        PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
		//carga el combo 
        loadCombo();
		// Carga Fecha y Periodo
        anulaPedidosFacturadosForm.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
       
	}

	/**
	 * @return the pedPedidosFactList
	 */
	public List getPedPedidosFactList() {
		return pedPedidosFactList;
	}

	/**
	 * @param pedPedidosFactList the pedPedidosFactList to set
	 */
	public void setPedPedidosFactList(List pedPedidosFactList) {
		this.pedPedidosFactList = pedPedidosFactList;
	}

	/**
	 * @return the ocrIndicadoresList
	 */
	public List getOcrIndicadoresList() {
		return ocrIndicadoresList;
	}

	/**
	 * @param ocrIndicadoresList the ocrIndicadoresList to set
	 */
	public void setOcrIndicadoresList(List ocrIndicadoresList) {
		this.ocrIndicadoresList = ocrIndicadoresList;
	}
}