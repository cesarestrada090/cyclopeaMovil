package biz.belcorp.ssicc.web.spusicc.cobranzas.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ConsultaCOBTelecobroService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.ConsultaCOBTelecobroForm;

/** 
 * <p>
 * <a href="ConsultaCOBTelecobroAction"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva Iriarte </a>
 * 
 */

@ManagedBean  
@SessionScoped
public class ConsultaCOBTelecobroAction extends BasePopupAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8192686483435889865L;
	
	private static final String ACCION_ULTIMOS_MOVIMIENTOS = "ULTIMOSMOVIMIENTOS";
		 
	private List siccSociedadList = new ArrayList();
	private List siccRegionList = new ArrayList();
	private List siccZonaList = new ArrayList();
	private List siccCobradorList = new ArrayList();
	private List siccEtapasList = new ArrayList();
	private List siccTelefonosListsiccAccionesCobranzaList = new ArrayList();
	private List siccAccionesCobranzaList = new ArrayList();
	
	private List bitacoraLlamadasList = new ArrayList();
	private List referenciasList = new ArrayList();
	
	private List listaTelefonos = new ArrayList();	
	private List listaConsultoras = new ArrayList();
	
	private Integer longitudCampoClientes;
	
	private List detalleMovimientoList = new ArrayList();	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaCOBTelecobroForm consultaCOBTelecobroForm = new ConsultaCOBTelecobroForm();	
		return consultaCOBTelecobroForm;
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaCOBTelecobroAction - setViewAtributes' method");
		}
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		List bitacoraLlamadasList = new ArrayList();		
		List referenciasList = new ArrayList();
						
		//Carga combo de Sociedades
		InterfazSiCCService service = (InterfazSiCCService)this.getBeanService("sisicc.interfazSiCCService"); 
				getBean("sisicc.interfazSiCCService");
		
		this.setSiccSociedadList(service.getSociedadesByCodigoPais(this.mPantallaPrincipalBean.getCountryCode()));
									
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
						
		Map criteria = new HashMap();
		criteria.put("codigoUsuario", usuario.getLogin());
		
//		Seteo periodo actual		
		criteria.put("codigoPais", this.mPantallaPrincipalBean.getCountryCode());
        //criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        //criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
		
        //MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
        //PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(criteria);
        
        ConsultaCOBTelecobroForm f = (ConsultaCOBTelecobroForm)this.formBusqueda;        		
        
        f.reset();        
        f.setIndice(0);        
        f.setCodigoCobrador(usuario.getLogin());
        //f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
        
		this.setSiccRegionList(new ArrayList());
		this.setSiccZonaList(new ArrayList());
		this.setSiccCobradorList(new ArrayList());
		this.setSiccEtapasList(new ArrayList());
		this.setSiccTelefonosListsiccAccionesCobranzaList(new ArrayList());
		this.setSiccAccionesCobranzaList(new ArrayList());
		
		this.setBitacoraLlamadasList(bitacoraLlamadasList);
		this.setReferenciasList(referenciasList);
				
		//***** B O R R A R **********
		List listaTelefonos = new ArrayList();
		this.setListaTelefonos(listaTelefonos);		
		//****************************
		
		this.setListaConsultoras(null);
		 
		log.debug("pais"+pais);
		ClienteUAGenerarService svc = (ClienteUAGenerarService)this.getBeanService("sisicc.clienteUAGenerarService");
				
		log.debug("svc.getTamanhoNumeroCliente(pais.getCodigo(): " + svc.getTamanhoNumeroCliente(pais.getCodigo()));
		
		this.setLongitudCampoClientes(svc.getTamanhoNumeroCliente(this.mPantallaPrincipalBean.getCountryCode()));
				
		if (log.isDebugEnabled()) {
			log.debug("End 'ConsultaCOBTelecobroAction - setViewAtributes' method");
		}
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaCOBTelecobroAction - setFindAttributes' method");
		}
		
		List lista = null;
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String accion = externalContext.getRequestParameterMap().get("accion");
		
		log.debug("accion:" + accion);
		
		if(accion!=null){
			
			if(accion.equals(this.ACCION_ULTIMOS_MOVIMIENTOS)){
				
				ConsultaCOBTelecobroService service = (ConsultaCOBTelecobroService)this.getBeanService("spusicc.consultaCOBTelecobroService");
				String parametro = externalContext.getRequestParameterMap().get("parametro");
				
				Map criteria = new HashMap();				
				criteria.put("oidConsolidado", parametro);
				
				lista = service.getDetalleMovimiento(criteria);
				
			}
			
		}

		if (log.isDebugEnabled()) {
			log.debug("End 'ConsultaCOBTelecobroAction - setFindAttributes' method");
		}
		
		return lista;
	}

	/**
	 * @return the siccSociedadList
	 */
	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	/**
	 * @param siccSociedadList the siccSociedadList to set
	 */
	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public List getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(List siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the siccCobradorList
	 */
	public List getSiccCobradorList() {
		return siccCobradorList;
	}

	/**
	 * @param siccCobradorList the siccCobradorList to set
	 */
	public void setSiccCobradorList(List siccCobradorList) {
		this.siccCobradorList = siccCobradorList;
	}

	/**
	 * @return the siccEtapasList
	 */
	public List getSiccEtapasList() {
		return siccEtapasList;
	}

	/**
	 * @param siccEtapasList the siccEtapasList to set
	 */
	public void setSiccEtapasList(List siccEtapasList) {
		this.siccEtapasList = siccEtapasList;
	}

	/**
	 * @return the siccTelefonosListsiccAccionesCobranzaList
	 */
	public List getSiccTelefonosListsiccAccionesCobranzaList() {
		return siccTelefonosListsiccAccionesCobranzaList;
	}

	/**
	 * @param siccTelefonosListsiccAccionesCobranzaList the siccTelefonosListsiccAccionesCobranzaList to set
	 */
	public void setSiccTelefonosListsiccAccionesCobranzaList(
			List siccTelefonosListsiccAccionesCobranzaList) {
		this.siccTelefonosListsiccAccionesCobranzaList = siccTelefonosListsiccAccionesCobranzaList;
	}

	/**
	 * @return the siccAccionesCobranzaList
	 */
	public List getSiccAccionesCobranzaList() {
		return siccAccionesCobranzaList;
	}

	/**
	 * @param siccAccionesCobranzaList the siccAccionesCobranzaList to set
	 */
	public void setSiccAccionesCobranzaList(List siccAccionesCobranzaList) {
		this.siccAccionesCobranzaList = siccAccionesCobranzaList;
	}

	/**
	 * @return the bitacoraLlamadasList
	 */
	public List getBitacoraLlamadasList() {
		return bitacoraLlamadasList;
	}

	/**
	 * @param bitacoraLlamadasList the bitacoraLlamadasList to set
	 */
	public void setBitacoraLlamadasList(List bitacoraLlamadasList) {
		this.bitacoraLlamadasList = bitacoraLlamadasList;
	}

	/**
	 * @return the referenciasList
	 */
	public List getReferenciasList() {
		return referenciasList;
	}

	/**
	 * @param referenciasList the referenciasList to set
	 */
	public void setReferenciasList(List referenciasList) {
		this.referenciasList = referenciasList;
	}

	/**
	 * @return the listaTelefonos
	 */
	public List getListaTelefonos() {
		return listaTelefonos;
	}

	/**
	 * @param listaTelefonos the listaTelefonos to set
	 */
	public void setListaTelefonos(List listaTelefonos) {
		this.listaTelefonos = listaTelefonos;
	}

	/**
	 * @return the listaConsultoras
	 */
	public List getListaConsultoras() {
		return listaConsultoras;
	}

	/**
	 * @param listaConsultoras the listaConsultoras to set
	 */
	public void setListaConsultoras(List listaConsultoras) {
		this.listaConsultoras = listaConsultoras;
	}

	/**
	 * @return the longitudCampoClientes
	 */
	public Integer getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	/**
	 * @param longitudCampoClientes the longitudCampoClientes to set
	 */
	public void setLongitudCampoClientes(Integer longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}

	/**
	 * @return the detalleMovimientoList
	 */
	public List getDetalleMovimientoList() {
		return detalleMovimientoList;
	}

	/**
	 * @param detalleMovimientoList the detalleMovimientoList to set
	 */
	public void setDetalleMovimientoList(List detalleMovimientoList) {
		this.detalleMovimientoList = detalleMovimientoList;
	}
	
}
