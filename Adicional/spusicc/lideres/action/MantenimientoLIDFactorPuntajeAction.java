package biz.belcorp.ssicc.web.spusicc.lideres.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.lideres.model.FactorPuntaje;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.lideres.MantenimientoLIDFactorPuntajeService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.lideres.form.MantenimientoLIDFactorPuntajeForm;

/**
 * Action invocado desde la pantalla de mantenimiento del objeto Usuario SICC.
 * <p>
 * <a href="RolSearchAction.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:kgomez@sigcomt.com">Karina Gomez</a> 
 */

@ManagedBean
@SessionScoped
public class MantenimientoLIDFactorPuntajeAction extends BaseMantenimientoSearchAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7582188752809601016L;
	private List listaNumeros;
	private List listaMarca;
	LabelValue [] lv;
	
	
	
	/**
	 * @return the listaNumeros
	 */
	public List getListaNumeros() {
		return listaNumeros;
	}

	/**
	 * @param listaNumeros the listaNumeros to set
	 */
	public void setListaNumeros(List listaNumeros) {
		this.listaNumeros = listaNumeros;
	}

	/**
	 * @return the listaMarca
	 */
	public List getListaMarca() {
		return listaMarca;
	}

	/**
	 * @param listaMarca the listaMarca to set
	 */
	public void setListaMarca(List listaMarca) {
		this.listaMarca = listaMarca;
	}

	/**
	 * @return the lv
	 */
	public LabelValue[] getLv() {
		return lv;
	}

	/**
	 * @param lv the lv to set
	 */
	public void setLv(LabelValue[] lv) {
		this.lv = lv;
	}

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoLIDFactorPuntajeList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoLIDFactorPuntajeList";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoLIDFactorPuntajeForm objForm = new MantenimientoLIDFactorPuntajeForm();
		return objForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub

		MantenimientoLIDFactorPuntajeService mantenimientoLIDFactorPuntajeService = (MantenimientoLIDFactorPuntajeService)getBean("spusicc.mantenimientoLIDFactorPuntajeService"); 
		MantenimientoLIDFactorPuntajeForm f = (MantenimientoLIDFactorPuntajeForm)this.formBusqueda;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		if(f.getCodigoNumeroConcurso() != null){
			Map criteria = new HashMap();
			
			log.debug("codPais          "+ pais.getCodigo());
			log.debug("codMarca         "+ f.getCodigoMarca());
			log.debug("periodoProceso   "+ f.getPeriodoProceso());
			log.debug("codigoNumeroConcurso "+ f.getCodigoNumeroConcurso());
			log.debug("codigoTipoAsignacionPuntaje "+ f.getCodigoTipoAsignacionPuntaje());
			
	        
	        criteria.put("codPais", pais.getCodigo());
	        criteria.put("codMarca", f.getCodigoMarca());
	        criteria.put("periodoProceso", f.getPeriodoProceso());
	        criteria.put("codigoNumeroConcurso", f.getCodigoNumeroConcurso());
	        criteria.put("codigoTipoAsignacionPuntaje", f.getCodigoTipoAsignacionPuntaje());
	        if(f.getFactorPuntajeList()!=null){
				f.getFactorPuntajeList().clear();
			}
	        f.setFactorPuntajeList(mantenimientoLIDFactorPuntajeService.getFactorPuntajeList(criteria));
	        log.debug(" tamaño de la lista : " + f.getFactorPuntajeList());
		}else{
			this.addWarn("Advertencia ", this.getResourceMessage("advertencia.concurso"));
		}
		return f.getFactorPuntajeList();
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Exectuting action : setSaveAttributes.");
		}
		FactorPuntaje factorPuntaje = null;;
		List _factorPuntaje = new ArrayList(); 
		MantenimientoLIDFactorPuntajeService mantenimientoLIDFactorPuntajeService = (MantenimientoLIDFactorPuntajeService)getBean("spusicc.mantenimientoLIDFactorPuntajeService"); 
		MantenimientoLIDFactorPuntajeForm f = (MantenimientoLIDFactorPuntajeForm)this.formBusqueda;
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		String[] codConcurso = f.getCodConcurso();
		String[] tipAsignacionPuntaje = f.getTipAsignacionPuntaje();
		String[] codPais = f.getCodPais();
		String[] codPeriodo = f.getCodPeriodo();
		String[] valorFactorMultiplicador = f.getValorFactorMultiplicador();
		String[] flag = f.getFlag();
		
		try {
			
				for(int i = 0;i<f.getCodConcurso().length;i++ ){
					
					factorPuntaje = new FactorPuntaje();
					factorPuntaje.setCodConcurso(f.getCodigoNumeroConcurso());
					factorPuntaje.setTipAsignacionPuntaje(f.getCodigoTipoAsignacionPuntaje());
					factorPuntaje.setCodPais(pais.getCodigo());
					factorPuntaje.setCodPeriodo(codPeriodo[i]);
					factorPuntaje.setValorFactorMultiplicador(valorFactorMultiplicador[i]);
					log.debug("codPais             : " +codPais[i]);
					log.debug("tipAsignacionPuntaje    : " +f.getCodigoTipoAsignacionPuntaje());
					log.debug("codConcurso          : " +f.getCodigoNumeroConcurso());
					log.debug("valorFactorMultiplicador       : " +valorFactorMultiplicador[i]);
					log.debug("codCondurso : " +codConcurso[i]);
					log.debug("Tipo asignacion : " +tipAsignacionPuntaje[i]);
					log.debug("codPerido  : " +codPeriodo[i]);
					log.debug("flag : " + flag[i]);
					if(Integer.parseInt(flag[i])!=Constants.FLAG_DEFAULT){
						if(codConcurso[i]!=null && tipAsignacionPuntaje[i]!=null && codPeriodo[i]!=null &&
								!codPais[i].equals("") && !tipAsignacionPuntaje[i].equals("") && !codPeriodo[i].equals("")){
							log.debug("actualizando...");
							mantenimientoLIDFactorPuntajeService.updateFactorPuntaje(factorPuntaje);
						}else{
							log.debug("guardando...");
							mantenimientoLIDFactorPuntajeService.saveFactorPuntaje(factorPuntaje);
						}
					}
		
					_factorPuntaje.add(factorPuntaje);
				}
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
				f.getFactorPuntajeList().clear();
				f.setFactorPuntajeList(_factorPuntaje);
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Executin action : view.");
		
		this.mostrarBotonNuevo = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		
		MantenimientoLIDFactorPuntajeForm f = (MantenimientoLIDFactorPuntajeForm)this.formBusqueda;
		if(f.getFactorPuntajeList()!=null){
			f.getFactorPuntajeList().clear();
		}
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
        
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
	
		MantenimientoLIDFactorPuntajeService mantenimientoLIDFactorPuntajeService = (MantenimientoLIDFactorPuntajeService)getBean("spusicc.mantenimientoLIDFactorPuntajeService");		
		// Carga de los combos Marca, PeriodoProceso
		this.listaMarca = reporteService.getMarcas();
		f.setMarcaList(reporteService.getMarcas());
		f.setPeriodoProceso(controlFacturacion.getCodigoPeriodo());
		f.setFechaProceso(controlFacturacion.getFechaProceso());
		f.setTipoAsignacionPuntajeList(mantenimientoLIDFactorPuntajeService.getTipoAsignacionPuntajeList());
		f.setCodigoIdiomaISO(usuario.getCodigoIdioma()); // getUsuario(request.getSession()).getIdioma().getCodigoISO()
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		this.lv =ajaxService.getNumeroConcursoList(f.getCodigoPais(),f.getCodigoMarca(),f.getPeriodoProceso());		
		
		
		
		//log.debug("lv " +lv);
		
		if(this.lv==null)
			this.lv = new LabelValue[0];
		
		//request.getSession().setAttribute(LID_CONCURSO_LIST, lv);
		
	
	}

}
