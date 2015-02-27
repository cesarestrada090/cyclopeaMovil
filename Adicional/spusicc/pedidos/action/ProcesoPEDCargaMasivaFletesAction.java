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
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDCargaMasivaFletesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoPEDCargaMasivaFletesForm;

@ManagedBean  
@SessionScoped
public class ProcesoPEDCargaMasivaFletesAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = 1L;
	private List cargaMasivaFletesList = new ArrayList();
	private DataTableModel dataTableResultado = new DataTableModel();
	private String attachment = "";
	private String viewValida = "";

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoPEDCargaMasivaFletesForm form = new ProcesoPEDCargaMasivaFletesForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)throws Exception {
		if(log.isDebugEnabled()){
			log.debug("executeProcess");
		}
		ProcesoPEDCargaMasivaFletesForm f = (ProcesoPEDCargaMasivaFletesForm)this.formProceso;
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
        ProcesoPEDCargaMasivaFletesService service = (ProcesoPEDCargaMasivaFletesService)getBean("spusicc.procesoPEDCargaMasivaFletesService");
        params.put("codigoUsuario", usuario.getLogin());
        service.executeActualizarCargaMasivaFlete(params);
        f.setFlagBotonValidar(true);
        f.setFlagBotonActualizar(false);
        
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		
		ProcesoPEDCargaMasivaFletesForm f = (ProcesoPEDCargaMasivaFletesForm)this.formProceso;
		f.setFlagBotonValidar(false);
        f.setFlagBotonActualizar(false);
        this.setViewValida("1");
	}	
		
	public void handleFileUpload(FileUploadEvent event) {
		if(log.isDebugEnabled()){
			log.debug("handleFileUpload");
		}
		ProcesoPEDCargaMasivaFletesForm f = (ProcesoPEDCargaMasivaFletesForm)this.formProceso;
		if(event != null){
			f.setArchivo(event.getFile());
			this.setAttachment(event.getFile().getFileName());
			
			try {
				Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();		        
		        ProcesoPEDCargaMasivaFletesService service = (ProcesoPEDCargaMasivaFletesService)getBean("spusicc.procesoPEDCargaMasivaFletesService");
				
				InputStream is = f.getArchivo().getInputstream();
				f.setNombreArchivo(f.getArchivo().getFileName());
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				List lineas = new ArrayList();
				String linea = "";
				String strCodZona = "";
				String strMontoFijo = "";
				String strRecargo = "";
				String strMontoNoFlete = "";
				
				while (true) {
					Map criteria = new HashMap();
					linea = br.readLine();
					if (linea == null)
						break;
					StringTokenizer st = new StringTokenizer(linea, ",");
					try {
						strCodZona = st.nextToken();
						criteria.put("codigoZona", strCodZona);
					} catch (Exception e) {
						strCodZona = "";
					}
					try {
						strMontoFijo = st.nextToken();
						criteria.put("montoFijo", strMontoFijo);
					} catch (Exception e) {
						strMontoFijo = "";
					}
					try {
						strRecargo = st.nextToken();
						criteria.put("recargo", strRecargo);
					} catch (Exception e) {
						strRecargo = "";
					}
					try {
						strMontoNoFlete = st.nextToken();
						criteria.put("montoNoFlete", strMontoNoFlete);
					} catch (Exception e) {
						strMontoNoFlete = "";
					}
					// Todos los campos son obligatorios, si falta alguno, no se considera el registro
					if(!strCodZona.equals("") && !strMontoFijo.equals("") && !strRecargo.equals("") && !strMontoNoFlete.equals(""))
						lineas.add(criteria);
				}
				
				Map resultados = service.cargarArchivoExcelCSV(lineas,usuario);
		        f.setNumRegistros((String)resultados.get("totalRegistros"));
		        f.setNumRegistrosError("N");
		        f.setNumRegistrosValido("N");
		        f.setFlagBotonValidar(true);
		        f.setFlagBotonActualizar(false);
		        
		        cargaMasivaFletesList = new ArrayList();
		        //sesion.setAttribute("viewValida", "1");
				this.setDataTableResultado(new DataTableModel(this.getCargaMasivaFletesList()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}	
	
	public void validar(){
		if(log.isDebugEnabled()){
			log.debug("validar");
		}
		ProcesoPEDCargaMasivaFletesForm f = (ProcesoPEDCargaMasivaFletesForm)this.formProceso;
        String codigoUsuario = this.getmPantallaPrincipalBean().getCurrentUser().getCodigo();
        Map params = new HashMap();
        params.put("codigoUsuario", codigoUsuario);
        ProcesoPEDCargaMasivaFletesService service = (ProcesoPEDCargaMasivaFletesService)getBean("spusicc.procesoPEDCargaMasivaFletesService");
        List resultados = service.executeValidarCargaMasivaFlete(params);
        int totalErrores = resultados.size();
        int totalValidos = Integer.parseInt(f.getNumRegistros()) - totalErrores;
        f.setNumRegistrosError(String.valueOf(totalErrores));
        f.setNumRegistrosValido(String.valueOf(totalValidos));
        f.setFlagBotonValidar(false);
        f.setFlagBotonActualizar(true);
        
        cargaMasivaFletesList =  resultados;
	}
	
	public void actualizar(ActionEvent e){
		if(log.isDebugEnabled()){
			log.debug("actualizar");
		}
		//la logica de acuerdo a operacion en AJAX   - PENDIENTE
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public DataTableModel getDatatableBusqueda() {
		return datatableBusqueda;
	}

	public void setDatatableBusqueda(DataTableModel datatableBusqueda) {
		this.datatableBusqueda = datatableBusqueda;
	}

	public List getCargaMasivaFletesList() {
		return cargaMasivaFletesList;
	}

	public void setCargaMasivaFletesList(List cargaMasivaFletesList) {
		this.cargaMasivaFletesList = cargaMasivaFletesList;
	}

	public DataTableModel getDataTableResultado() {
		return dataTableResultado;
	}

	public void setDataTableResultado(DataTableModel dataTableResultado) {
		this.dataTableResultado = dataTableResultado;
	}

	public String getViewValida() {
		return viewValida;
	}

	public void setViewValida(String viewValida) {
		this.viewValida = viewValida;
	}

}