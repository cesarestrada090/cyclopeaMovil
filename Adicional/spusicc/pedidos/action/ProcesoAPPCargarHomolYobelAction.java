package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.app.ProcesoAPPCargarHomolYobelService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoAPPCargarHomolYobelForm;

@ManagedBean
@SessionScoped
public class ProcesoAPPCargarHomolYobelAction extends BaseProcesoAbstractAction {

	
	private static final long serialVersionUID = 1L;
	private List appCargarHomolYobelErroresList = new ArrayList();
	private DataTableModel dataTableResultado = new DataTableModel();
	private String attachment = "";
	private String viewValida = "";
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("devuelveFormProceso");
		}
		ProcesoAPPCargarHomolYobelForm form = new ProcesoAPPCargarHomolYobelForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("executeProcess");
		}
		ProcesoAPPCargarHomolYobelForm f = (ProcesoAPPCargarHomolYobelForm)this.formProceso;
		ProcesoAPPCargarHomolYobelService service = (ProcesoAPPCargarHomolYobelService)getBean("spusicc.procesoAPPCargarHomolYobelService");
		UploadedFile archivo = f.getArchivo();		
		InputStream is = archivo.getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		List lineas = new ArrayList();
		String linea            = "";
		String strNumSecu 		= "";
		String strCodZona 		= "";
		String strCodSeccion 	= "";
		String strCodTerritorio = "";
		
		while (true) {
			Map criteria = new HashMap();			
			linea = br.readLine();
			if (linea == null)
				break;						
			//log.debug(linea);
			StringTokenizer st = new StringTokenizer(linea, ",");
			try {
				strCodZona = st.nextToken();
				criteria.put("codigoZona", strCodZona);
			} catch (Exception e) {
				strCodZona = "";
			}
			try {
				strCodSeccion = st.nextToken();
				criteria.put("codigoSeccion", strCodSeccion);
			} catch (Exception e) {
				strCodSeccion = "";
			}		
			try {
				strCodTerritorio = st.nextToken();
				criteria.put("codigoTerritorio", strCodTerritorio);
			} catch (Exception e) {
				strCodTerritorio = "";
			}
			try {
				strNumSecu = st.nextToken();	
				// Debe ser un numero entero				
				Integer.parseInt(strNumSecu);
				criteria.put("numeroSecuencia", strNumSecu);				
			} catch (Exception e) {
				strNumSecu = "";
			}			
			// Todos los campos son obligatorios, si falta alguno, no se considera el registro
			if(!strNumSecu.equals("") && !strCodZona.equals("") && !strCodSeccion.equals("") && !strCodTerritorio.equals(""))
				lineas.add(criteria);
		}
		
		List erroresList = new ArrayList();
		List returnErroresList = new ArrayList();
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		
		erroresList = service.insertaHomologadoYobel(lineas,usuario);
		if(erroresList.size() != 0){
			f.setFlagMostrarErrores(true);
			for(int i=0;i<erroresList.size();i++){
				Map temp = new HashMap();
				temp.put("descripcionError", erroresList.get(i));
			    returnErroresList.add(temp);
			}
//			session.setAttribute(Constants.APP_CARGA_HOMOLOGACION_ERRORES_LIST, returnErroresList);
//			throw new ServiceException(service.getMessageResource(usuario, "errors.cancel"));			
		}
		f.setFlagMostrarErrores(false);
		this.setDataTableResultado(new DataTableModel(this.getAppCargarHomolYobelErroresList()));
        return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		ProcesoAPPCargarHomolYobelForm f = (ProcesoAPPCargarHomolYobelForm)formProceso;
		f.setFlagMostrarErrores(false);		
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		if(log.isDebugEnabled()){
			log.debug("handleFileUpload");
		}
		ProcesoAPPCargarHomolYobelForm f = (ProcesoAPPCargarHomolYobelForm)formProceso;
		if(event!=null){
			f.setArchivo(event.getFile());
			setAttachment(f.getArchivo().getFileName());
		}
	}
	
	public void limpiar(){
        if (log.isDebugEnabled()) {
            log.debug("Entering 'limpiar' method");
        }
        ProcesoAPPCargarHomolYobelService service = (ProcesoAPPCargarHomolYobelService)getBean("spusicc.procesoAPPCargarHomolYobelService");
        service.deleteTablaHomologacion();
        this.addInfo("Info:", this.getResourceMessage("registros.eliminados.exito"));
    }



	public String getViewValida() {
		return viewValida;
	}

	public void setViewValida(String viewValida) {
		this.viewValida = viewValida;
	}

	public DataTableModel getDataTableResultado() {
		return dataTableResultado;
	}

	public void setDataTableResultado(DataTableModel dataTableResultado) {
		this.dataTableResultado = dataTableResultado;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public List getAppCargarHomolYobelErroresList() {
		return appCargarHomolYobelErroresList;
	}

	public void setAppCargarHomolYobelErroresList(
			List appCargarHomolYobelErroresList) {
		this.appCargarHomolYobelErroresList = appCargarHomolYobelErroresList;
	}

}