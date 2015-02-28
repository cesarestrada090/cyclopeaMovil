package biz.belcorp.ssicc.web.spusicc.app.action;

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

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.app.ProcesoAPPCargarSecuenciaZonaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.app.form.ProcesoAPPCargarSecuenciaZonaForm;

@ManagedBean
@SessionScoped
public class ProcesoAPPCargarSecuenciaZonaAction extends BaseProcesoAbstractAction {

	
	private static final long serialVersionUID = 1L;
	private List appCargarHomolYobelErroresList = new ArrayList();
	private DataTableModel dataTableResultado = new DataTableModel();
	private String attachment = "";
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("devuelveFormProceso");
		}
		ProcesoAPPCargarSecuenciaZonaForm form = new ProcesoAPPCargarSecuenciaZonaForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("executeProcess");
		}
		ProcesoAPPCargarSecuenciaZonaForm f = (ProcesoAPPCargarSecuenciaZonaForm)this.formProceso;
		ProcesoAPPCargarSecuenciaZonaService service = (ProcesoAPPCargarSecuenciaZonaService)getBean("spusicc.procesoAPPCargarSecuenciaZonaService");
		UploadedFile archivo = f.getArchivo();		
		InputStream is = archivo.getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		List lineas = new ArrayList();
		String linea            = "";
		String strNumSecu 		= "";
		String strCodZona 		= "";

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
				strNumSecu = st.nextToken();
				// Debe ser un numero entero
				Integer.parseInt(strNumSecu);
				criteria.put("numeroSecuencia", strNumSecu);
			} catch (Exception e) {
				strNumSecu = "";
			}

			// Todos los campos son obligatorios, si falta alguno, no se considera el registro
			if(!strNumSecu.equals("") && !strCodZona.equals("") )
				lineas.add(criteria);
		}

		//Borramos e insertamos en la tabla temporal
		service.deleteTablaSecuenciaZona();
		service.insertaSecuenciaZona(lineas);

		Map criteria = new HashMap();
		criteria.put("codigoPais",f.getCodigoPais());
		service.executeCargaSecuenciaZona(criteria);
		String respuesta = (String) criteria.get("valError");
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();

		if (Constants.NUMERO_CERO.equals(respuesta)){
			this.addInfo("Info:", this.getResourceMessage("procesoAPPCargarSecuenciaZonaForm.carga.correcta"));
		}

		if (Constants.NUMERO_UNO.equals(respuesta)){
			//messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("procesoAPPCargarSecuenciaZonaForm.error.zona"));
			//saveMessages(request.getSession(), messages);
			this.addError("Error:", service.getMessageResource(usuario, "procesoAPPCargarSecuenciaZonaForm.error.zona"));
		}

		if (Constants.NUMERO_DOS.equals(respuesta)){
			//messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("procesoAPPCargarSecuenciaZonaForm.error.secuencia1"));
			//saveMessages(request.getSession(), messages);
			this.addError("Error:", service.getMessageResource(usuario, "procesoAPPCargarSecuenciaZonaForm.error.secuencia1"));
		}

		if (Constants.NUMERO_TRES.equals(respuesta)){
			//messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("procesoAPPCargarSecuenciaZonaForm.error.secuencia2"));
			//saveMessages(request.getSession(), messages);
			this.addError("Error:", service.getMessageResource(usuario, "procesoAPPCargarSecuenciaZonaForm.error.secuencia2"));
		}
        return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		ProcesoAPPCargarSecuenciaZonaForm f = (ProcesoAPPCargarSecuenciaZonaForm)formProceso;
		f.setFlagMostrarErrores(false);
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		if(log.isDebugEnabled()){
			log.debug("handleFileUpload");
		}
		ProcesoAPPCargarSecuenciaZonaForm f = (ProcesoAPPCargarSecuenciaZonaForm)formProceso;
		if(event != null){
			f.setArchivo(event.getFile());
			this.setAttachment(f.getArchivo().getFileName());
		}
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