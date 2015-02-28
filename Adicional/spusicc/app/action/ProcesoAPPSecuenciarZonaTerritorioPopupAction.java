package biz.belcorp.ssicc.web.spusicc.app.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionDetalle;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.app.ProcesoAPPSecuenciarZonaTerritorioService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.app.form.ProcesoAPPSecuenciarZonaTerritorioPopupForm;

@ManagedBean
@SessionScoped
public class ProcesoAPPSecuenciarZonaTerritorioPopupAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -8165108009329445409L;

	private List appRegionZonaSecPopupList;
	private DataTableModel detallePopupTableModel;
	private String [] secuencias;

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
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ProcesoAPPSecuenciarZonaTerritorioPopupForm searchForm = new ProcesoAPPSecuenciarZonaTerritorioPopupForm();
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
	protected void setViewAtributes() throws Exception {	

	}
	
	public void inicializarValores(){
		ProcesoAPPSecuenciarZonaTerritorioPopupForm f = (ProcesoAPPSecuenciarZonaTerritorioPopupForm) this.formBusqueda;
		ProcesoAPPSecuenciarZonaTerritorioService service = (ProcesoAPPSecuenciarZonaTerritorioService) getBean("spusicc.procesoAPPSecuenciarZonaTerritorioService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		ExternalContext externalContext = FacesContext.getCurrentInstance()	.getExternalContext();
		String codigoRegion = externalContext.getRequestParameterMap().get("descRegion").toString();
		String codigoZona = externalContext.getRequestParameterMap().get("codigoZona").toString();
		String descZona = externalContext.getRequestParameterMap().get("descZona").toString();

		f.setCodigoRegion(codigoRegion);
		f.setCodigoZona(codigoZona);
		f.setDescripcionZona(descZona);
		f.setCodigoPais(pais.getCodigo());

		Map criteria = new HashMap();
		criteria.put("codigoZona", codigoZona);
		List resultado = new ArrayList();
		resultado = service.getTerritoriosSecuenciarList(criteria);
		this.appRegionZonaSecPopupList = resultado;
		this.detallePopupTableModel = new DataTableModel(
				this.appRegionZonaSecPopupList);

		Integer cont = 0;
		for (int i = 0; i < resultado.size(); i++) {
			Map map = new HashMap();
			map = (Map) resultado.get(i);
			if (map.get("numeroSecuencia") == null)
				cont++;
		}
		f.setIndicadorSinSecuencia(cont.toString());
		f.setFlgProceso("N");
		f.setMsgProceso("Pendiente de Ejecución...");

		// String rolGuardar = request.getParameter("rolGuardar");
		// String rolEjecutar = request.getParameter("rolEjecutar");
		// f.setRolGuardar(rolGuardar);
		// f.setRolEjecutar(rolEjecutar);

		String ventana = "PF('dialogMantenimientoForm2').show()";
		this.getRequestContext().execute(ventana);
		this.mostrarBotonSalir = false;
		this.mostrarBotonSave = false;
		return;
	}
	
	// guardar los cambios en Popup
	public void guardarPopup(ActionEvent event) throws Exception{	
		String valor=verificaSecuencia();
		if(valor.equals("D")){
			this.addError("ERROR: ", this.getResourceMessage("procesoAPPSecuenciarZonaTerritorioForm.secuencias.duplicadas"));
			throw new Exception(this.getResourceMessage("procesoAPPSecuenciarZonaTerritorioForm.secuencias.duplicadas"));
		}
			
		ProcesoAPPSecuenciarZonaTerritorioPopupForm f = (ProcesoAPPSecuenciarZonaTerritorioPopupForm) this.formBusqueda;		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ProcesoAPPSecuenciarZonaTerritorioService service = (ProcesoAPPSecuenciarZonaTerritorioService) getBean("spusicc.procesoAPPSecuenciarZonaTerritorioService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map criteriaOperacion = new HashMap();

		String codUsuario = usuario.getLogin();
		criteriaOperacion.put("codigoPais", f.getCodigoPais());
		String oidPais = reporteService.getOidString("getOidPaisByCodigoPais",
				criteriaOperacion);
		List lista = this.appRegionZonaSecPopupList;
		f.setSecuencia(this.secuencias);

		service.grabarSecuenciacionTerritorios(lista, f.getSecuencia(),codUsuario, oidPais);
		f.setFlgProceso("S");
		this.addInfo("INFO:", "Datos Guardados Correctamente");
	}

	// Ejecutar la Secuencia en el Popup
	public void ejecutarSecuenciaPopup(ActionEvent actionEvent)throws Exception {		
		ProcesoAPPSecuenciarZonaTerritorioPopupForm f = (ProcesoAPPSecuenciarZonaTerritorioPopupForm) this.formBusqueda;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		String verifica=ajax.validaCantidadTerritoriosSecuenciacion(f.getCodigoZona());
		if(!verifica.equals("0")){
			this.addError("ERROR: ", verifica);
			throw new Exception(verifica);
		}			
		
		ProcesoAPPSecuenciarZonaTerritorioService service = (ProcesoAPPSecuenciarZonaTerritorioService) getBean("spusicc.procesoAPPSecuenciarZonaTerritorioService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		List lista = new ArrayList();
		lista = this.appRegionZonaSecPopupList;

		String codUsuario = usuario.getLogin();
		String validar = service.executeResetearSecuenciaTerritorios2(lista,codUsuario);

		if (validar.equals("true")) {
			f.setMsgProceso("Proceso a Finalizado!");
			f.setFlgProceso("S");
			this.addInfo("INFO:", "Datos Secuenciados Correctamente");
		} else {
			f.setMsgProceso("En Proceso ...");
			f.setFlgProceso("N");
			this.addInfo("INFO:", "En Proceso ...");
		}
		
	}

	// metodo que sale del popup
	public void salirPopup(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering my method 'salirUA'");
		}
		String ventana = "PF('dialogMantenimientoForm2').hide()";
		this.getRequestContext().execute(ventana);
		this.mostrarBotonSalir = true;
	}
	
	public String setValidarConfirmar(String accion) {
		//ProcesoAPPSecuenciarZonaTerritorioPopupForm f = (ProcesoAPPSecuenciarZonaTerritorioPopupForm) this.formBusqueda;
		
		if(accion.equals("GUARDAR_SECUENCIA")){
			String valor=verificaSecuencia();
			if(valor.equals("D"))
				return this.getResourceMessage("procesoAPPSecuenciarZonaTerritorioForm.secuencias.duplicadas");		
			
		}
		
		
		
		
		return null;
		
	}

	
	public String verificaSecuencia(){
		List lista=this.appRegionZonaSecPopupList;
		String []secuencia=new String[lista.size()];
		int k=0,cont=0;
		
		if(lista!=null){
			for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
				Map objeto=(Map )iterator.next();				
				String dato=objeto.get("numeroSecuencia").toString();				
				secuencia[k]=dato;
				k++;
			}
			for(int i=0;i<secuencia.length;i++){
				String valor=secuencia[i];				
				for(int j=i+1;j<secuencia.length;j++){					
					if(secuencia[j].equals(valor))
						cont++;
				}
			}
		}
		this.secuencias=secuencia;
		//A-no hay elementos repetidos,D-hay elementos repetidos
		if(cont==0)
			return "A";
		else
			return "D";		
		
	}

	public List getAppRegionZonaSecPopupList() {
		return appRegionZonaSecPopupList;
	}

	public void setAppRegionZonaSecPopupList(List appRegionZonaSecPopupList) {
		this.appRegionZonaSecPopupList = appRegionZonaSecPopupList;
	}

	public DataTableModel getDetallePopupTableModel() {
		return detallePopupTableModel;
	}

	public void setDetallePopupTableModel(DataTableModel detallePopupTableModel) {
		this.detallePopupTableModel = detallePopupTableModel;
	}
	
	
	

}
