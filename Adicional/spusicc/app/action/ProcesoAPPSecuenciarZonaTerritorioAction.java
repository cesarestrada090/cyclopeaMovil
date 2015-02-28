package biz.belcorp.ssicc.web.spusicc.app.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.app.ProcesoAPPSecuenciarZonaTerritorioService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.app.form.ProcesoAPPSecuenciarZonaTerritorioForm;

@ManagedBean
@SessionScoped
public class ProcesoAPPSecuenciarZonaTerritorioAction extends BaseMantenimientoSearchAbstractAction{

	private static final long serialVersionUID = 1010904253023352122L;
	
	private List siccRegionList;
	private List appRegionZonaSecList;
	private String[] secuencias;
	
	@ManagedProperty(value="#{procesoAPPSecuenciarZonaTerritorioPopupAction}")	
	private ProcesoAPPSecuenciarZonaTerritorioPopupAction popupProcesoAPPSecuenciar;
	
	@ManagedProperty(value="#{reporteAPPSecuenciarZonaTerritorioAction}")	
	private ReporteAPPSecuenciarZonaTerritorioAction reporteAPPSecuenciar;
	

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
		ProcesoAPPSecuenciarZonaTerritorioForm searchForm = new ProcesoAPPSecuenciarZonaTerritorioForm();
		return searchForm;
	}
	
	public void abrirPopup(ActionEvent event){
		try {
			popupProcesoAPPSecuenciar.inicializarValores();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	@Override
	protected List setFindAttributes() throws Exception {
		
		if (log.isDebugEnabled()) {
            log.debug("Entering 'find' method");
        }

		ProcesoAPPSecuenciarZonaTerritorioForm f = (ProcesoAPPSecuenciarZonaTerritorioForm) this.formBusqueda;
		ProcesoAPPSecuenciarZonaTerritorioService service = 
			(ProcesoAPPSecuenciarZonaTerritorioService) getBean("spusicc.procesoAPPSecuenciarZonaTerritorioService");
		Map criteria = new HashMap();

		if(f.getRegionList() != null && f.getRegionList().length > 0 && !f.getRegionList()[0].equals(""))
			criteria.put("codigoRegion", f.getRegionList());

		List resultado = service.getRegionZonaSecuenciarList(criteria);
		Map map = new HashMap();
		Integer cont = new Integer(0);
		for (int i = 0; i < resultado.size(); i++) {
			map = (Map)resultado.get(i);
			if(map.get("numeroSecuencia")== null) cont++;
		}

		f.setIndicadorSinSecuencia(cont.toString());
		this.appRegionZonaSecList=resultado;		
		return resultado;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		String valor=verificaSecuenciaPadre();
		if(valor.equals("D")){
			this.addError("ERROR: ", this.getResourceMessage("procesoAPPSecuenciarZonaTerritorioForm.secuencias.duplicadas"));
			throw new Exception(this.getResourceMessage("procesoAPPSecuenciarZonaTerritorioForm.secuencias.duplicadas"));
		}
			
		ProcesoAPPSecuenciarZonaTerritorioForm f = (ProcesoAPPSecuenciarZonaTerritorioForm) this.formBusqueda;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ProcesoAPPSecuenciarZonaTerritorioService service = 
			(ProcesoAPPSecuenciarZonaTerritorioService) getBean("spusicc.procesoAPPSecuenciarZonaTerritorioService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map criteriaOperacion = new HashMap();

		criteriaOperacion.put("codigoPais", f.getCodigoPais());
		String oidPais = reporteService.getOidString("getOidPaisByCodigoPais",criteriaOperacion);
		String codUsuario = usuario.getLogin();

		//Valida que las secuencias del grid no existan en la tabla app_rutas_trans
		//criteriaOperacion.put("secuencias", f.getSecuencia());

		List lista = this.appRegionZonaSecList;
		f.setSecuencia(this.secuencias);

		/////////////////////
		List novedades = new ArrayList();
		for (int i = 0; i < lista.size(); i++) {
			Map map = (Map)lista.get(i);
			String oidPais2 = (String)map.get("oidPais");
			String numSecuencia = f.getSecuencia()[i];
			
			if(numSecuencia != null && oidPais2 == null){
				novedades.add(f.getSecuencia()[i]);
			}else{
				if(oidPais2 != null ){
					if(!map.get("numeroSecuencia").toString().equals(f.getSecuencia()[i])){
						novedades.add(f.getSecuencia()[i]);
					}
				}
			}
		}

		String[] array = new String[novedades.size()];
		for (int i = 0; i < novedades.size(); i++) {
			array[i] = novedades.get(i).toString();
		}

		criteriaOperacion.put("secuencias", array);
		/////////////////////

		List l = service.getCantidadSecuenciasExistentes(criteriaOperacion);
		if (l.size() == 0){
			service.grabarSecuenciacionZonas(lista, f.getSecuencia(), oidPais, codUsuario);
		}
		else{			
			String cadenaSecuencias = "";
			for(int i=0; i<l.size(); i++){
				cadenaSecuencias += l.get(i).toString();
				if(i!=l.size()-1){
					cadenaSecuencias = cadenaSecuencias + ",";
				}
			}			
			this.addError("ERROR: ", this.getResourceMessage("errors.secuencia.existe")+cadenaSecuencias);
			throw new Exception(this.getResourceMessage("errors.secuencia.existe"));
		}

		setFindAttributes();
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		
		this.mostrarBotonConsultar=false;
		this.mostrarBotonModificar=false;
		this.mostrarBotonNuevo=false;
		this.mostrarBotonSave=true;
		this.mostrarBotonEliminar=false;
		ProcesoAPPSecuenciarZonaTerritorioService service = 
				(ProcesoAPPSecuenciarZonaTerritorioService) getBean("spusicc.procesoAPPSecuenciarZonaTerritorioService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		ProcesoAPPSecuenciarZonaTerritorioForm f = (ProcesoAPPSecuenciarZonaTerritorioForm) this.formBusqueda;
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		this.siccRegionList=reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);		
		f.setIndicadorSinSecuencia(Constants.NUMERO_CERO);
		f.setRegionList(null);
		f.setCodigoPais(pais.getCodigo());
		service.deleteRutasTerri();		
	}
	
	//Ejecutar Secuencia del Padre
	public void executeProcess(ActionEvent actionEvent) throws Exception {		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ProcesoAPPSecuenciarZonaTerritorioService service = 
			(ProcesoAPPSecuenciarZonaTerritorioService) getBean("spusicc.procesoAPPSecuenciarZonaTerritorioService");
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario =this.mPantallaPrincipalBean.getCurrentUser();
		Map criteriaOperacion = new HashMap();

		List lista = new ArrayList();
		lista = this.appRegionZonaSecList;

		criteriaOperacion.put("codigoPais",pais.getCodigo());
        String oidPais = reporteService.getOidString("getOidPaisByCodigoPais",criteriaOperacion);
        String codUsuario = usuario.getLogin();

		service.executeResetearSecuencia(lista,oidPais, codUsuario);
		setFindAttributes();
	}
	
	//enviar EMail
	public void enviarMail(ActionEvent actionEvent)throws Exception {
		String condicionRegion = "";
		ProcesoAPPSecuenciarZonaTerritorioForm f = (ProcesoAPPSecuenciarZonaTerritorioForm) this.formBusqueda;

		if (!(f.getRegionList() == null) || (Constants.OPCION_TODOS.equals(f.getRegionList()) )){
			condicionRegion = this.obtieneCondicion(f.getRegionList(), "f.COD_REGI ", "'");
		}	
		
		//Se graba en la tabla de audioria
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ProcesoAPPSecuenciarZonaTerritorioService service = 
			(ProcesoAPPSecuenciarZonaTerritorioService) getBean("spusicc.procesoAPPSecuenciarZonaTerritorioService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map criteriaOperacion = new HashMap();

		criteriaOperacion.put("codigoPais", f.getCodigoPais());
		String oidPais = reporteService.getOidString("getOidPaisByCodigoPais",criteriaOperacion);
		
		if (!(f.getRegionList() == null) || (Constants.OPCION_TODOS.equals(f.getRegionList()) )){
			criteriaOperacion.put("condicionRegion", condicionRegion);
		}
		
		if(f.getRegionList() != null && f.getRegionList().length > 0 && !f.getRegionList()[0].equals(""))
			criteriaOperacion.put("codigoRegion", f.getRegionList());

		String codUsuario = usuario.getLogin();
		String codAuditoria = "APPTRA";
		String codAccion = "MAIL";
		String codScrip = "enviarMail";
		String oidRuta ="";
		String dataAuditoria ="";
		String codRuta = "";
		String numSecuencia = "";
		Map map = new HashMap();
		List resultado = service.getAuditoriaList(criteriaOperacion);

		for (int i = 0; i < resultado.size(); i++) {
			map = (Map)resultado.get(i);

			map.put("oidPais", oidPais);
			map.put("codUsuario", codUsuario);
			map.put("codAuditoria", codAuditoria);
			map.put("codAccion", codAccion);
			map.put("codScrip", codScrip);

			oidRuta = map.get("oidRuta").toString();
			codRuta = (String)map.get("codRuta");
			numSecuencia = map.get("numSecuencia").toString();

			dataAuditoria = "/2="+oidPais+"/3="+codRuta+"/5="+numSecuencia+"/*";
			map.put("dataAuditoria",dataAuditoria);
			map.put("oidRuta", oidRuta);
			
			service.insertaAuditoria(map);
		}
		
		this.reporteAPPSecuenciar.grabarReporte();
	}
	
	
	//verifica Secuencia del padre-no repetidos
	public String verificaSecuenciaPadre(){
		List lista=this.appRegionZonaSecList;
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
	
	public String devuelveMensajeKeySaveOK() {		
		return "datos.actualizado.ok";	
	}

	
	/** * * Devuelve la condicion a ser enviada como parametros	 */	 	
	protected String obtieneCondicion(String[] lista, String parametro, String comilla) {
		if (lista == null || lista.length == 0)
			return "";
		else {
			String resultado = "";

			for (int i = 0; i < lista.length; i++) {
				String dato = lista[i];

				if (StringUtils.isEmpty(dato) || StringUtils.equals(dato, "Todos"))
					return "";

				if (i == 0)
					resultado = resultado + "(" + comilla + dato + comilla;
				else
					resultado = resultado + "," + comilla + dato + comilla;
			}

			resultado = resultado + ")";
			resultado = " AND " + parametro + " IN " + resultado;

			return resultado;
		}
	}		
	

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public List getAppRegionZonaSecList() {
		return appRegionZonaSecList;
	}

	public void setAppRegionZonaSecList(List appRegionZonaSecList) {
		this.appRegionZonaSecList = appRegionZonaSecList;
	}

	public ProcesoAPPSecuenciarZonaTerritorioPopupAction getPopupProcesoAPPSecuenciar() {
		return popupProcesoAPPSecuenciar;
	}

	public void setPopupProcesoAPPSecuenciar(
			ProcesoAPPSecuenciarZonaTerritorioPopupAction popupProcesoAPPSecuenciar) {
		this.popupProcesoAPPSecuenciar = popupProcesoAPPSecuenciar;
	}

	public String[] getSecuencias() {
		return secuencias;
	}

	public void setSecuencias(String[] secuencias) {
		this.secuencias = secuencias;
	}

	public ReporteAPPSecuenciarZonaTerritorioAction getReporteAPPSecuenciar() {
		return reporteAPPSecuenciar;
	}

	public void setReporteAPPSecuenciar(
			ReporteAPPSecuenciarZonaTerritorioAction reporteAPPSecuenciar) {
		this.reporteAPPSecuenciar = reporteAPPSecuenciar;
	}
	
	
	
}
