package biz.belcorp.ssicc.web.spusicc.inc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCBloqueoPremiosService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCBloqueoPremiosForm;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCBloqueoPremiosSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class MantenimientoINCBloqueoPremiosSearchAction extends BaseMantenimientoSearchAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7165536966218412688L;

	
	private LabelValue[] siccConcursoList; // cambiar 
	private List siccRegionList;
	private List incBloqueoPremiosList;
	private LabelValue[] siccPremioList; // cambiar
	private boolean indicadorReemplazoBool;
	private boolean deshabilitaIndicadorReemplazo;
	
	@Override
	protected String getSalirForward() 
	{
		return "mantenimientoINCBloqueoPremiosList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception 
	{
		return "mantenimientoINCBloqueoPremiosForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception 
	{
		MantenimientoINCBloqueoPremiosSearchForm searchForm = new MantenimientoINCBloqueoPremiosSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception 
	{
		MantenimientoINCBloqueoPremiosService service = (MantenimientoINCBloqueoPremiosService) getBean("spusicc.mantenimientoINCBloqueoPremiosService");
		MantenimientoINCBloqueoPremiosSearchForm f = (MantenimientoINCBloqueoPremiosSearchForm) this.formBusqueda;
		//enviando en session los parametros de mensaje
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoConcurso", f.getCodigoConcurso());
		
		String codigoPremio = f.getCodigoPremio();
		if(!StringUtils.isEmpty(codigoPremio))
			criteria.put("codigoSAP", codigoPremio.split("__")[0]);
		
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		
		if(f.getCodigoRegion()!=null && !StringUtils.isEmpty(f.getCodigoRegion()[0])) {
			criteria.put("codigoRegion", f.getCodigoRegion());
		}
//		session.setAttribute("codigoPremioElegido", f.getCodigoPremio());
		
		List lista = service.getBloqueoPremios(criteria);
		this.incBloqueoPremiosList = lista;
		
		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception 
	{
		Map premioSeleccionado = (Map) this.beanRegistroSeleccionado;
		try {
			MantenimientoINCBloqueoPremiosService service = (MantenimientoINCBloqueoPremiosService) getBean("spusicc.mantenimientoINCBloqueoPremiosService");

			List listBloqueoPremios = this.incBloqueoPremiosList;
			Integer rowNum = Integer.parseInt(premioSeleccionado.get("rowNum").toString());
			Map params = (Map) listBloqueoPremios.get(rowNum);
//
			service.deleteBloqueoPremios(params);
			
			// enviamos el mensaje de satisfaccion
			this.getResourceMessage("mantenimientoINCBloqueoPremiosSearchForm.deleted");

		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error))
				error = e.getLocalizedMessage();
			this.getResourceMessage("errors.detail", new Object[] { error });
		}

		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception 
	{
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoINCBloqueoPremiosService service = (MantenimientoINCBloqueoPremiosService) 
													getBean("spusicc.mantenimientoINCBloqueoPremiosService");
		MantenimientoINCBloqueoPremiosForm f = (MantenimientoINCBloqueoPremiosForm) this.formMantenimiento;
		
		List listaBloqueoPremios = new ArrayList();
		Map mapBloqueoPremioUpdate = new HashMap();
		String[] codigoPremio = f.getCodigoPremio().split("__");
		
		if(f.isNewRecord()){
			if(f.getCodigoRegion()[0].equals("Todos")) {
				List listaRegiones = this.siccRegionList;
				
				for(int i = 0; i< listaRegiones.size(); i++) {
					Base baseRegion = (Base)listaRegiones.get(i);
					
					Map mapBloqueoPremio = new HashMap();
					mapBloqueoPremio.put("codigoPais", f.getCodigoPais());	
					mapBloqueoPremio.put("codigoPeriodo", f.getCodigoPeriodo());
					mapBloqueoPremio.put("codigoConcurso",f.getCodigoConcurso());
					mapBloqueoPremio.put("codigoSAP", codigoPremio[0]);
					mapBloqueoPremio.put("codigoVenta", codigoPremio[1]);
					mapBloqueoPremio.put("codigoRegion", baseRegion.getCodigo());
					mapBloqueoPremio.put("codigoUsuario", usuario.getLogin());
					
					if(!StringUtils.isEmpty(f.getIndicadorReemplazo()))
							mapBloqueoPremio.put("indicadorReemplazo", f.getIndicadorReemplazo());
					
					listaBloqueoPremios.add(mapBloqueoPremio);
				}
			} else {
				for(int i = 0; i< f.getCodigoRegion().length; i++) {
					String codigoRegion = f.getCodigoRegion()[i];
					
					Map mapBloqueoPremio = new HashMap();
					mapBloqueoPremio.put("codigoPais", f.getCodigoPais());	
					mapBloqueoPremio.put("codigoPeriodo", f.getCodigoPeriodo());
					mapBloqueoPremio.put("codigoConcurso",f.getCodigoConcurso());
					mapBloqueoPremio.put("codigoSAP", codigoPremio[0]);
					mapBloqueoPremio.put("codigoVenta", codigoPremio[1]);
					mapBloqueoPremio.put("codigoRegion", codigoRegion);
					mapBloqueoPremio.put("codigoUsuario", usuario.getLogin());
					
					if(!StringUtils.isEmpty(f.getIndicadorReemplazo()))
						mapBloqueoPremio.put("indicadorReemplazo", f.getIndicadorReemplazo());
					
					if(service.existeBloqueoPremio(mapBloqueoPremio)) {
						this.getResourceMessage("mantenimientoINCBloqueoPremiosForm.msg.existeBloqueoPremios");
						return false;			
					} else
						listaBloqueoPremios.add(mapBloqueoPremio);
				}
			}
		} else {
			mapBloqueoPremioUpdate.put("codigoPais", f.getCodigoPais());	
			mapBloqueoPremioUpdate.put("codigoPeriodo", f.getCodigoPeriodo());
			mapBloqueoPremioUpdate.put("codigoConcurso",f.getCodigoConcurso());
			mapBloqueoPremioUpdate.put("codigoSAP", codigoPremio[0]);
			mapBloqueoPremioUpdate.put("codigoVenta", codigoPremio[1]);
			mapBloqueoPremioUpdate.put("codigoRegion", f.getCodigoRegion()[0]);
			mapBloqueoPremioUpdate.put("indicadorReemplazo", f.getIndicadorReemplazo());
			mapBloqueoPremioUpdate.put("identificador", f.getIdentificador());
			mapBloqueoPremioUpdate.put("codigoUsuario", usuario.getLogin());
			
			if(service.existeBloqueoPremio(mapBloqueoPremioUpdate)) {
				this.getResourceMessage("mantenimientoINCBloqueoPremiosForm.msg.existeBloqueoPremios");
				return false;			
			}
		}
		
		try{
			if(f.isNewRecord()){			
			   service.insertBloqueoPremios(listaBloqueoPremios);//inserta
				//enviamos el mensaje de satisfaccion
			}
			else{
				service.updateBloqueoPremios(mapBloqueoPremioUpdate);//upadte
				//enviamos el mensaje de satisfaccion
			}	
			
		}catch(Exception e){
			log.debug("error " +e.getMessage());	
			throw new Exception(this.getResourceMessage("mantenimientoEDULocal.cabecera.error",new Object[]{e.getMessage()}));
		}
		
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAddAttributes ' method");
		}

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoINCBloqueoPremiosForm f = new MantenimientoINCBloqueoPremiosForm();

		f.setCodigoPeriodo(null);
		f.setCodigoConcurso(null);
		f.setCodigoPremio(null);
		f.setCodigoRegion(null);
		f.setIndicadorReemplazo(Constants.NUMERO_CERO);
		this.indicadorReemplazoBool = false;
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());

		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
		
		MantenimientoOCRPedidoControlFacturacionService serviceOCR = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceOCR.getControlFacturacionById(criteria);

		// Carga el periodo actual
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		String fechaProceso = controlFacturacion.getFechaProceso();

		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());

		// Verificamos que solo se cree al inicio de la campaña
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Base baseInicioPeriodo = (Base) reporteService.getListaGenerico("getFechaInicioPeriodoByPaisMarcaCanal", criteria).get(0);
		String fechaInicioPeriodo = baseInicioPeriodo.getCodigo();

		if (!fechaProceso.equals(fechaInicioPeriodo)) {
			f.setEditable(false);
			this.mostrarBotonSave = false;
			this.deshabilitaIndicadorReemplazo = true;
			this.addError("Error:",this.getResourceMessage("mantenimientoINCBloqueoPremiosForm.msg.configuracionInicioCampana") + fechaInicioPeriodo);
		}		
		
		/* METODO EDIT */
		Map premioSeleccionado = (Map) this.beanRegistroSeleccionado;

		if (!this.accion.equals(this.ACCION_NUEVO)) 
		{
			Integer rowNum = Integer.parseInt(premioSeleccionado.get("rowNum").toString());
			MantenimientoINCBloqueoPremiosService service = (MantenimientoINCBloqueoPremiosService) getBean("spusicc.mantenimientoINCBloqueoPremiosService");

			if (rowNum != null) 
			{
				try {
					List list = this.incBloqueoPremiosList;
					Map map = (Map) list.get(rowNum);
					log.debug("map " + map);
					f.setCodigoPais(String.valueOf(map.get("codigoPais")));
					f.setCodigoConcurso(String.valueOf(map.get("codigoConcurso")));
					f.setCodigoPeriodo(String.valueOf(map.get("codigoPeriodo")));
					f.setIdentificador(String.valueOf(map.get("identificador")));

					String[] regiones = new String[1];
					regiones[0] = String.valueOf(map.get("codigoRegion"));
					f.setCodigoRegion(regiones);

					Map criteria1 = new HashMap();
					criteria1.put("codigoPais", pais.getCodigo());
					criteria1.put("codigoPeriodo", f.getCodigoPeriodo());
					criteria1.put("codigoRegion", regiones[0]);

					// combo concursos
					List listConcursos = service.getConcursosBloqueoPremios(criteria1);

					// combo Premios
					criteria.put("codigoConcurso", f.getCodigoConcurso());
					List listPremios = service.getPremiosxConcurso(criteria1);

					String codigoSAP = String.valueOf(map.get("codigoSAP"));
					String codigoVenta = String.valueOf(map.get("codigoVenta"));

					// ubicamos el premio respectivo
					for (int i = 0; i < listPremios.size(); i++) 
					{
						Base basePremio = (Base) listPremios.get(i);
						String[] premio = basePremio.getCodigo().split("__");

						if (codigoSAP.equals(premio[0])	&& codigoVenta.equals(premio[1])) 
						{
							f.setCodigoPremio(basePremio.getCodigo());
						}
					}
					
					this.siccPremioList = new LabelValue[listPremios.size()];
					int i = 0;
					for (Object object : listPremios) {
						LabelValue labelValue = new LabelValue();
						labelValue.setLabel(((Base) object).getDescripcion());
						labelValue.setValue(((Base) object).getCodigo());
						this.getSiccPremioList()[i] = labelValue;
						i++;
					}						
					
					f.setIndicadorReemplazo(String.valueOf(map.get("indicadorReemplazo")));

					criteria1.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
					criteria1.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
					criteria1.put("codigoPeriodo", controlFacturacion.getCodigoPeriodo());

					log.debug("enviando para editar");
				} catch (Exception e) {
					String error = e.getMessage();
					if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
				this.getResourceMessage("errors.detail", new Object[]{error});
				}
			}
		}
		/* FIN METODO EDIT */

		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
	
		this.mostrarBotonConsultar = false;
	
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoINCBloqueoPremiosService service = (MantenimientoINCBloqueoPremiosService) 
										getBean("spusicc.mantenimientoINCBloqueoPremiosService");
		MantenimientoINCBloqueoPremiosSearchForm f = (MantenimientoINCBloqueoPremiosSearchForm) this.formBusqueda; 		
		f.setCodigoPais(pais.getCodigo());
		Map criteria= new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
		MantenimientoOCRPedidoControlFacturacionService serviceOCR = 
				(MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceOCR.getControlFacturacionById(criteria);

		// Carga el periodo actual
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setCodigoRegion(new String[]{""});
		f.setCodigoConcurso(null);
		f.setCodigoPremio(null);
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		
		//combo concursos
		List listConcursos =service.getConcursosBloqueoPremios(criteria);
		
		this.siccConcursoList = new LabelValue[listConcursos.size()];
		int i = 0;
		for (Object object : listConcursos) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base) object).getDescripcion());
			labelValue.setValue(((Base) object).getCodigo());
			this.getSiccConcursoList()[i] = labelValue;
			i++;
		}		
		
		//combo regiones
		this.siccRegionList = service.getRegionesBloqueoPremios();

		//combo tipo proceso										
//		session.removeAttribute("codigoPremioElegido");	
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK()
	{
		MantenimientoINCBloqueoPremiosForm premioForm = (MantenimientoINCBloqueoPremiosForm) this.formMantenimiento;
		boolean isNew = premioForm.isNewRecord();
		if (isNew) {
			return "mantenimientoINCBloqueoPremiosForm.insert";
		} else {
			return "mantenimientoINCBloqueoPremiosForm.update";
		}
	}
	
	public void loadPremios(ValueChangeEvent e)
	{
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		MantenimientoINCBloqueoPremiosForm premioForm = (MantenimientoINCBloqueoPremiosForm) this.formMantenimiento;
	
		String valor = e.getNewValue().toString();
		if(valor != null && valor != "")
		{
			this.siccPremioList = ajax.getPremiosxConcurso(premioForm.getCodigoConcurso(), Constants.TODAS);		
		}			
	}

	public void loadConcursos(ValueChangeEvent e)
	{
		MantenimientoINCBloqueoPremiosForm f = (MantenimientoINCBloqueoPremiosForm) this.formMantenimiento;
		String[] valorRegion = (String[])e.getNewValue();
		String resultado= " ";
		ArrayList regiones = new ArrayList();
		regiones.add(" ");
		int i=0;
		
		for (String region : valorRegion) {
			if (region != null){
				if(region == "") { 
					regiones.add(" ");
					break;
				}	
				else {
					if(regiones.size() == 1) {
						regiones.add(region);
						resultado = regiones.get(i+1).toString(); 
					} else {
						regiones.add(region); //regiones + "," + region;
						resultado = resultado + "," + regiones.get(i+1).toString();
					}
				}			
			}
			i++;
		}
		
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		this.siccConcursoList = ajax.getConcursosBloqueoPremios(f.getCodigoPeriodo(), resultado, Constants.TODAS);
		loadPremios(e);
	}
	
	 
	public void validarIndicadorReemplazo(ValueChangeEvent e)
	{
		String valorPremio = e.getNewValue().toString();
		MantenimientoINCBloqueoPremiosForm f = (MantenimientoINCBloqueoPremiosForm) this.formMantenimiento;
		String aux = null;
		
		if(!f.isNewRecord()) aux = "modificar";
		
		if(valorPremio == "")
		{
			this.indicadorReemplazoBool = false;
			this.deshabilitaIndicadorReemplazo = true;
		}else
		{
			String codigoSAP = valorPremio.split("__")[0];
			String codigoVenta = valorPremio.split("__")[1];
			String indReemplazo = valorPremio.split("__")[2];
			
			if(indReemplazo.equalsIgnoreCase("1"))
			{
				if(aux == null)
					this.indicadorReemplazoBool =false;
				
				this.deshabilitaIndicadorReemplazo = false;
			} else {
				this.indicadorReemplazoBool = false;			
				this.deshabilitaIndicadorReemplazo = true;
			}			
		}
	}
	
	public LabelValue[] getSiccConcursoList() {
		return siccConcursoList;
	}

	public void setSiccConcursoList(LabelValue[] siccConcursoList) {
		this.siccConcursoList = siccConcursoList;
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public List getIncBloqueoPremiosList() {
		return incBloqueoPremiosList;
	}

	public void setIncBloqueoPremiosList(List incBloqueoPremiosList) {
		this.incBloqueoPremiosList = incBloqueoPremiosList;
	}

	public LabelValue[] getSiccPremioList() {
		return siccPremioList;
	}

	public void setSiccPremioList(LabelValue[] siccPremioList) {
		this.siccPremioList = siccPremioList;
	}

	public boolean isIndicadorReemplazoBool() {
		return indicadorReemplazoBool;
	}

	public void setIndicadorReemplazoBool(boolean indicadorReemplazoBool) {
		this.indicadorReemplazoBool = indicadorReemplazoBool;
	}

	public boolean isDeshabilitaIndicadorReemplazo() {
		return deshabilitaIndicadorReemplazo;
	}

	public void setDeshabilitaIndicadorReemplazo(boolean deshabilitaIndicadorReemplazo) {
		this.deshabilitaIndicadorReemplazo = deshabilitaIndicadorReemplazo;
	}
}
