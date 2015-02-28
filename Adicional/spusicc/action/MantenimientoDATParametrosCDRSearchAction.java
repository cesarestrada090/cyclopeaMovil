package biz.belcorp.ssicc.web.spusicc.action;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.ParametroCDR;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.MantenimientoDATParametrosCDRService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoDATParametrosCDRForm;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoDATParametrosCDRSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes"})
public class MantenimientoDATParametrosCDRSearchAction extends BaseMantenimientoSearchAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8373639028959823898L;
	
	private List siccTipoSolicitudPaisList;
	
	private boolean indicadorAnuladoBool;
	private boolean indicadorDevolucionBool;
	private boolean indicadorTruequeBool;
	private boolean indicadorCanjeBool;
	private boolean indicadorFaltanteBool;
	private boolean indicadorPremioBool;	
	private boolean indicadorNMPBool;
	private boolean indicadorPedidoServicioBool;
	private boolean indicadorVentaBool;

	@Override
	protected String getSalirForward() 
	{
		return "mantenimientoDATParametrosCDRList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception 
	{
		return "mantenimientoDATParametrosCDRForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception
	{
		MantenimientoDATParametrosCDRSearchForm searchForm = new MantenimientoDATParametrosCDRSearchForm();
		return searchForm;
	}

	
	@Override
	protected List setFindAttributes() throws Exception 
	{
		MantenimientoDATParametrosCDRSearchForm f = (MantenimientoDATParametrosCDRSearchForm) this.formBusqueda;
		ParametroCDR parametro = new ParametroCDR();
		
		MantenimientoDATParametrosCDRService service = (MantenimientoDATParametrosCDRService) getBean("spusicc.mantenimientoDATParametrosCDRService");		
		
		BeanUtils.copyProperties(parametro, f);
		
		List lista = (List)service.getParametrosCDRByPais(parametro);
		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception
	{
		ParametroCDR parametroSeleccionado = (ParametroCDR) this.beanRegistroSeleccionado;
		
			try {			
				MantenimientoDATParametrosCDRService service = (MantenimientoDATParametrosCDRService) getBean("spusicc.mantenimientoDATParametrosCDRService");
				ParametroCDR cabecera = new ParametroCDR();
				
				// obteniendo Codigo de Pais
				cabecera.setCodigoPais(parametroSeleccionado.getCodigoPais());
				
				// obteniendo Id de TipoSolicitud
				cabecera.setIdTipoSolicitud(parametroSeleccionado.getIdTipoSolicitud());
				
				service.deleteParametrosCDR(cabecera);
				this.getResourceMessage("mantenimientoDATParametrosCDRForm.deleted");
				
			}catch (Exception e) {
				String error = e.getMessage();
				if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
				this.getResourceMessage("errors.detail",new Object[]{ error});		
			}
		
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method");
		}
		MantenimientoDATParametrosCDRForm f = (MantenimientoDATParametrosCDRForm) this.formMantenimiento;
		MantenimientoDATParametrosCDRService service = (MantenimientoDATParametrosCDRService) 
													getBean("spusicc.mantenimientoDATParametrosCDRService");		
		
		try {
			ParametroCDR cabecera = new ParametroCDR();
			BeanUtils.copyProperties(cabecera, f);
			DevuelveValorGuardar(cabecera);
			
			if (!f.isNewRecord()) {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA MODIFICACION");
				}
				service.updateParametrosCDR(cabecera);
							
			} else {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA CREACION");
				}
				service.insertParametrosCDR(cabecera);
			}			
		} catch (Exception e) {
				throw new Exception(this.getResourceMessage(e.getMessage()));
		}
		
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception 
	{
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ParametroCDR parametroSeleccionado = (ParametroCDR) this.beanRegistroSeleccionado;

		MantenimientoDATParametrosCDRForm f = new MantenimientoDATParametrosCDRForm();
		ParametroCDR cabecera = new ParametroCDR();

		/* Seteamos el Formulario */
		f.setIndicadorAnulado(Constants.NUMERO_CERO);
		f.setIndicadorDevolucion(Constants.NUMERO_CERO);
		f.setIndicadorTrueque(Constants.NUMERO_CERO);
		f.setIndicadorCanje(Constants.NUMERO_CERO);
		f.setIndicadorFaltante(Constants.NUMERO_CERO);
		f.setIndicadorPremio(Constants.NUMERO_CERO);
		f.setIndicadorNMP(Constants.NUMERO_CERO);
		f.setIndicadorPedidoServicio(Constants.NUMERO_CERO);
		f.setIndicadorVenta(Constants.NUMERO_CERO);
		
		this.indicadorAnuladoBool = false;
		this.indicadorDevolucionBool = false;
		this.indicadorTruequeBool = false;
		this.indicadorCanjeBool = false;
		this.indicadorFaltanteBool = false;
		this.indicadorPremioBool = false;
		this.indicadorNMPBool = false;
		this.indicadorPedidoServicioBool = false;		
		this.indicadorVentaBool = false;
		
		cabecera.setCodigoPais(pais.getCodigo());
		f.setCodigoPais(pais.getCodigo());

		if (!this.accion.equals(this.ACCION_NUEVO)) 
		{
			String codigoPais = parametroSeleccionado.getCodigoPais();
			String tipoSolicitud = parametroSeleccionado.getIdTipoSolicitud();
			
			if (codigoPais != null && tipoSolicitud != null) 
			{
				MantenimientoDATParametrosCDRService service = (MantenimientoDATParametrosCDRService) getBean("spusicc.mantenimientoDATParametrosCDRService");
				
				// obteniendo Codigo de Pais
				cabecera.setCodigoPais(codigoPais);
				
				// obteniendo Id de TipoSolicitud
				cabecera.setIdTipoSolicitud(tipoSolicitud);
								
				cabecera = (ParametroCDR) service.getParametroCDRById(cabecera);
				BeanUtils.copyProperties(f, cabecera);
				
				DevuelveValor(cabecera);
				
				f.setNewRecord(false);
			}
		}
		
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		// Cargamos los combos a utilizar
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoDATParametrosCDRSearchForm f = (MantenimientoDATParametrosCDRSearchForm) this.formBusqueda;
		f.setCodigoPais(pais.getCodigo());

		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		String codigoISO = (String) getRequest().getParameter("codigoISO");
		this.siccTipoSolicitudPaisList = svc.getTiposSolicitudPais(codigoISO);
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() 
	{
		MantenimientoDATParametrosCDRForm f = (MantenimientoDATParametrosCDRForm) this.formMantenimiento;
		boolean isNew = f.isNewRecord();
		if(isNew){
			return "mantenimientoDATParametrosCDRForm.add";
		}else{
			return "mantenimientoDATParametrosCDRForm.updated";
		}	
	}

	private void DevuelveValor(ParametroCDR cabecera)
	{
		this.indicadorAnuladoBool =cabecera.getIndicadorAnulado().equalsIgnoreCase("1");
		this.indicadorCanjeBool = cabecera.getIndicadorCanje().equalsIgnoreCase("1");		
		this.indicadorDevolucionBool = cabecera.getIndicadorDevolucion().equalsIgnoreCase("1");
		this.indicadorFaltanteBool = cabecera.getIndicadorFaltante().equalsIgnoreCase("1");
		this.indicadorNMPBool = cabecera.getIndicadorNMP().equalsIgnoreCase("1");
		this.indicadorPedidoServicioBool = cabecera.getIndicadorPedidoServicio().equalsIgnoreCase("1");
		this.indicadorPremioBool = cabecera.getIndicadorPremio().equalsIgnoreCase("1");
		this.indicadorTruequeBool = cabecera.getIndicadorTrueque().equalsIgnoreCase("1");
		this.indicadorVentaBool = cabecera.getIndicadorVenta().equalsIgnoreCase("1");
	}
	
	private void DevuelveValorGuardar(ParametroCDR cabecera)
	{
		String indicadorAnulado = (this.indicadorAnuladoBool == true)?"1":"0";
		cabecera.setIndicadorAnulado(indicadorAnulado);
		String indicadorCanje = (this.indicadorCanjeBool == true)?"1":"0";
		cabecera.setIndicadorCanje(indicadorCanje);
		String indicadorDevolucion = (this.indicadorDevolucionBool == true)?"1":"0";
		cabecera.setIndicadorDevolucion(indicadorDevolucion);
		String indicadorFaltante = (this.indicadorFaltanteBool == true)?"1":"0";
		cabecera.setIndicadorFaltante(indicadorFaltante);
		String indicadorNMP = (this.indicadorNMPBool == true)?"1":"0";
		cabecera.setIndicadorNMP(indicadorNMP);
		String indicadorPedidoServicio = (this.indicadorPedidoServicioBool == true)?"1":"0";
		cabecera.setIndicadorPedidoServicio(indicadorPedidoServicio);
		String indicadorPremio = (this.indicadorPremioBool == true)?"1":"0";
		cabecera.setIndicadorPremio(indicadorPremio);
		String indicadorTrueque = (this.indicadorTruequeBool == true)?"1":"0";
		cabecera.setIndicadorTrueque(indicadorTrueque);
		String indicadorVenta = (this.indicadorVentaBool == true)?"1":"0";
		cabecera.setIndicadorVenta(indicadorVenta);
	}
	
	public List getSiccTipoSolicitudPaisList() {
		return siccTipoSolicitudPaisList;
	}

	public void setSiccTipoSolicitudPaisList(List siccTipoSolicitudPaisList) {
		this.siccTipoSolicitudPaisList = siccTipoSolicitudPaisList;
	}

	public boolean isIndicadorAnuladoBool() {
		return indicadorAnuladoBool;
	}

	public void setIndicadorAnuladoBool(boolean indicadorAnuladoBool) {
		this.indicadorAnuladoBool = indicadorAnuladoBool;
	}

	public boolean isIndicadorDevolucionBool() {
		return indicadorDevolucionBool;
	}

	public void setIndicadorDevolucionBool(boolean indicadorDevolucionBool) {
		this.indicadorDevolucionBool = indicadorDevolucionBool;
	}

	public boolean isIndicadorTruequeBool() {
		return indicadorTruequeBool;
	}

	public void setIndicadorTruequeBool(boolean indicadorTruequeBool) {
		this.indicadorTruequeBool = indicadorTruequeBool;
	}

	public boolean isIndicadorCanjeBool() {
		return indicadorCanjeBool;
	}

	public void setIndicadorCanjeBool(boolean indicadorCanjeBool) {
		this.indicadorCanjeBool = indicadorCanjeBool;
	}

	public boolean isIndicadorFaltanteBool() {
		return indicadorFaltanteBool;
	}

	public void setIndicadorFaltanteBool(boolean indicadorFaltanteBool) {
		this.indicadorFaltanteBool = indicadorFaltanteBool;
	}

	public boolean isIndicadorPremioBool() {
		return indicadorPremioBool;
	}

	public void setIndicadorPremioBool(boolean indicadorPremioBool) {
		this.indicadorPremioBool = indicadorPremioBool;
	}

	public boolean isIndicadorNMPBool() {
		return indicadorNMPBool;
	}

	public void setIndicadorNMPBool(boolean indicadorNMPBool) {
		this.indicadorNMPBool = indicadorNMPBool;
	}

	public boolean isIndicadorPedidoServicioBool() {
		return indicadorPedidoServicioBool;
	}

	public void setIndicadorPedidoServicioBool(boolean indicadorPedidoServicioBool) {
		this.indicadorPedidoServicioBool = indicadorPedidoServicioBool;
	}

	public boolean isIndicadorVentaBool() {
		return indicadorVentaBool;
	}

	public void setIndicadorVentaBool(boolean indicadorVentaBool) {
		this.indicadorVentaBool = indicadorVentaBool;
	}

}
