package biz.belcorp.ssicc.web.spusicc.lec.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ProcesoCOBCargarCronogramaService;
import biz.belcorp.ssicc.service.spusicc.emprendedoras.ProcesoEMPVincularNuevasReactivadasService;
import biz.belcorp.ssicc.service.spusicc.lec.MantenimientoLECProgramaCorporativoService;
import biz.belcorp.ssicc.service.spusicc.lec.ProcesoLECCargaDatosExcelService;
import biz.belcorp.ssicc.service.spusicc.lec.ProcesoLECGenerarPagosService;
import biz.belcorp.ssicc.service.spusicc.pej.MantenimientoPEJProgramaEjecutivasService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBloqueoControlService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteAPEArmadoEbanuladosForm;
import biz.belcorp.ssicc.web.scsicc.form.ReporteAPEArmadoWHFACTForm;
import biz.belcorp.ssicc.web.spusicc.emprendedoras.form.ProcesoEMPVincularNuevasReactivadasCierreRegionForm;
import biz.belcorp.ssicc.web.spusicc.lec.form.ProcesoLECGenerarPagosForm;

/**
 * @author Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
public class ProcesoLECGenerarPagosAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = 4719206813800027889L;
	private List siccRegionList;
	private LabelValue[] siccZonaList;
	private List lecTipoPagoList;
	private List lecGrupoRegionList;
	private LabelValue[] lecTipoGananciaList;
	private List lecRegionList;
	private String attachment;
	private List lecProgramaArchivoList = new ArrayList();

	public LabelValue[] getLecTipoGananciaList() {
		return lecTipoGananciaList;
	}

	public void setLecTipoGananciaList(LabelValue[] lecTipoGananciaList) {
		this.lecTipoGananciaList = lecTipoGananciaList;
	}

	public List getLecProgramaArchivoList() {
		return lecProgramaArchivoList;
	}

	public void setLecProgramaArchivoList(List lecProgramaArchivoList) {
		this.lecProgramaArchivoList = lecProgramaArchivoList;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List getLecGrupoRegionList() {
		return lecGrupoRegionList;
	}

	public void setLecGrupoRegionList(List lecGrupoRegionList) {
		this.lecGrupoRegionList = lecGrupoRegionList;
	}

	public List getLecRegionList() {
		return lecRegionList;
	}

	public void setLecRegionList(List lecRegionList) {
		this.lecRegionList = lecRegionList;
	}

	public List getLecTipoPagoList() {
		return lecTipoPagoList;
	}

	public void setLecTipoPagoList(List lecTipoPagoList) {
		this.lecTipoPagoList = lecTipoPagoList;
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		Pais pais = mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
		ProcesoLECGenerarPagosForm f = (ProcesoLECGenerarPagosForm) formProceso;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		MantenimientoPEJProgramaEjecutivasService service = (MantenimientoPEJProgramaEjecutivasService) getBean("spusicc.mantenimientoPEJProgramaEjecutivasService");
		ProcesoLECCargaDatosExcelService servicePEJ = (ProcesoLECCargaDatosExcelService) getBean("spusicc.procesoLECCargaDatosExcelService");
		ProcesoLECGenerarPagosService serviceGP = (ProcesoLECGenerarPagosService) getBean("spusicc.procesoLECGenerarPagosService");
		List list0 = new ArrayList();
		criteria.put("indPago", "1");
		lecTipoPagoList = serviceGP.getTipoPago(criteria);
		f.setTipoPago(((Base) lecTipoPagoList.get(0)).getCodigo());
		Map result = service.getPeriodoDefault();
		f.setGrupoPago("1");
		String codigoPeriodo = (String) result.get("codigoPeriodo");
		String fechaProceso = (String) result.get("fechaProceso");
		f.setCodigoPeriodo(codigoPeriodo);
		f.setFechaProceso(fechaProceso);
		// seteamos la ruta temporal dodne guardar el excel
		ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		f.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(pais
				.getCodigo()));

		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estado", Constants.ESTADO_ACTIVO);
		criteria.put("campana", codigoPeriodo);
		List list = servicePEJ.getPrograma(criteria);
		Base m = (Base) list.get(0);

		f.setCodigoPrograma(m.getCodigo());
		f.setCodigoPais(pais.getCodigo());
		f.setFlagBotonValidar(false);
		f.setFlagBotonActualizar(false);

		// sesion.removeAttribute("viewValida");
		lecTipoGananciaList = aSvc.getTipoGanancia(f.getTipoPago());

		MantenimientoLECProgramaCorporativoService lecService = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");

		list = lecService.getTipoGrupoRegion(criteria);
		lecGrupoRegionList = list;

		Map map2 = new HashMap();
		map2.put("codigoPais", pais.getCodigo());
		map2.put("codigoTipoGroup", ((Base) list.get(0)).getCodigo());
		lecRegionList = lecService.getRegionByTipoGrupoRegion(map2);

		// String indTipoGrupoRegion = getIndicadorGrupoRegion(request);
		// request.getSession().setAttribute("indTipoGrupoRegion",
		// indTipoGrupoRegion!= null? indTipoGrupoRegion:Constants.NRO_CERO);

		f.setHabilitadorHidden("0");
		f.setHabilitadorRecaudoHidden("0");
		f.setHabilitadorPeriodo("0");
		f.setCodigoPeriodoRecaudo("");
		String habilitadorCampanna = this
				.getIndicadorCampannaProceso(getRequest());
		if (StringUtils.isNotBlank(habilitadorCampanna))
			f.setHabilitadorHidden(habilitadorCampanna);
		f.setHabilitadorPeriodo(habilitadorCampanna);
		log.info("Entro a ProcesoLETAsignarDesvincularLiderAction - setViewAttributes");
	}

	private String getIndicadorCampannaProceso(
			HttpServletRequest httpServletRequest) {
		Map criteriaParam = new HashMap();
		criteriaParam.put("codigoPais", mPantallaPrincipalBean
				.getCurrentCountry().getCodigo());
		criteriaParam.put("codigoSistema", "LET");
		criteriaParam.put("nombreParametro", "INDCAMBIOCAMPANA");
		return ((MantenimientoSTOBloqueoControlService) getBean("spusicc.mantenimientoSTOBloqueoControlService"))
				.getParametroGenericoSistema(criteriaParam);
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		ProcesoLECGenerarPagosForm f = (ProcesoLECGenerarPagosForm) formProceso;
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		ProcesoLECGenerarPagosService service = (ProcesoLECGenerarPagosService) getBean("spusicc.procesoLECGenerarPagosService");
		params.put("codigoPais", f.getCodigoPais());
		params.put("codigoPrograma", f.getCodigoPrograma());
		params.put("codigoPeriodo", f.getCodigoPeriodo());
		params.put("codigoTipoPago", f.getTipoPago());
		params.put("fechaProceso", f.getFechaProceso());
		params.put("codigoUsuario", usuario.getLogin());
		params.put("numeroCarga", f.getNumeroCarga());
		params.put("codigoPeriodoRecaudo", f.getCodigoPeriodoRecaudo());
		params.put("codigoPeriodoBono", f.getCodigoPeriodoBono());
		params.put("grupoRegion", f.getGrupoRegion());
		if (f.getGrupoPago() != null && f.getGrupoPago().compareTo("1") == 0)
			service.executeGenerarPagoRegular(params);
		if (f.getGrupoPago() != null && f.getGrupoPago().compareTo("0") == 0)
			service.executeGenerarPagoAdicional(params);

		f.setFlagBotonValidar(true);
		f.setFlagBotonActualizar(false);

		return params;
	}

	@Override
	public void afterExecuteProcess(BaseProcesoForm form) throws Exception {
		ProcesoLECGenerarPagosForm f = (ProcesoLECGenerarPagosForm) form;
		f.setFlagBotonValidar(false);
		f.setFlagBotonActualizar(false);

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"procesoLECGenerarPagosForm.proceso.ok"));

	}

	private String obtenerExtensionArchivo(String nombreArchivo)
			throws Exception {
		return nombreArchivo.substring(nombreArchivo.length() - 3);
	}

	private void borrarFichero(String path, String nombreArchivo) {
		try {
			File file = new File(path, nombreArchivo);
			file.delete();
			log.debug("Se eliminó el archivo");
		} catch (Exception ex) {
			log.debug("No se pudo eliminar el archivo " + ex.getMessage());
		}
	}

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoLECGenerarPagosForm form = new ProcesoLECGenerarPagosForm();
		return form;
	}

	public void showZonasxRegion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		log.debug("val: " + ArrayUtils.toString(val.getNewValue()));
		ProcesoLECGenerarPagosForm form = (ProcesoLECGenerarPagosForm) this.formProceso;
		String[] regiones = (String[]) val.getNewValue();

		if (!ArrayUtils.isEmpty(regiones)) {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			setSiccZonaList(aSvc.getZonasMultipleByPeriodoIntEviPerioRegioZona(
					form.getCodigoPais(), form.getCodigoPeriodo(),
					Constants.CODIGO_MARCA_DEFAULT,
					Constants.CODIGO_CANAL_DEFAULT, regiones,
					Constants.FORMATO_TOTAL));
		} else {
			setSiccZonaList(null);
		}

		// form.setCodigoZona(null);

	}

	public void validar() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validar' method");
		}

		ProcesoLECGenerarPagosForm f = (ProcesoLECGenerarPagosForm) formProceso;

		Map params = BeanUtils.describe(f);

		ProcesoLECCargaDatosExcelService service = (ProcesoLECCargaDatosExcelService) getBean("spusicc.procesoLECCargaDatosExcelService");

		int totalErrores = 0;
		params.put("indicadorCarga", null);
		// validamos los datos cargados del archivo excel
		params.put("tipoCarga", f.getTipoPago());
		List resultados = service.executeValidarCargaDatos(params);
		for (int i = 0; i < resultados.size(); i++) {
			Map errores = (Map) resultados.get(i);
			if (errores.get("codigoMotivo") != null) {
				totalErrores++;
			}
		}

		int totalValidos = Integer.parseInt(f.getNumRegistros()) - totalErrores;

		f.setNumRegistrosError(String.valueOf(totalErrores));
		f.setNumRegistrosValido(String.valueOf(totalValidos));

		lecProgramaArchivoList = resultados;

		if (totalErrores > 0) {
			f.setFlagBotonValidar(true);
			f.setFlagBotonActualizar(false);
		} else {
			f.setFlagBotonValidar(false);
			f.setFlagBotonActualizar(true);
		}

	}

	public void handleFileUpload(FileUploadEvent event) throws Exception {
		ProcesoLECGenerarPagosForm f = (ProcesoLECGenerarPagosForm) formProceso;
		// recuperamos el fichero
		
		f.setArchivo(event.getFile());
		this.setAttachment(f.getArchivo().getFileName());
		UploadedFile archivo = f.getArchivo();
		f.setNombreArchivo(archivo.getFileName());
		log.debug("Nombre Archivo Upload: " + f.getNombreArchivo());

		// leemos el stream de entrada
		InputStream is = archivo.getInputstream();
		// abrimos el stream de escritura, ubicacion al cual se grabara el
		// archivo del cliente
		FileOutputStream os = new FileOutputStream(new File(
				f.getDirectorioTemporal(), f.getNombreArchivo()));
		// grabamos cada 1024 bytes
		int bytesRead = 0;
		byte[] buffer = new byte[1024];
		while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();
		f.setArchivo(null);

		ProcesoLECCargaDatosExcelService service = (ProcesoLECCargaDatosExcelService) getBean("spusicc.procesoLECCargaDatosExcelService");
		if (f.getGrupoPago().compareTo("0") == 0) {
			lecTipoGananciaList = null;
			Map criteria1 = new HashMap();
			criteria1.put("indGrup", "A");
			lecTipoPagoList=service.getTipoCarga(criteria1);
			
				;
		}
		// Cargamos el archivo de la maquina del cliente al servidor

		// Obtenemos la extension del archivo
		String extensionArchivo = obtenerExtensionArchivo(f.getNombreArchivo());
		f.setExtensionArchivo(extensionArchivo);

		Map criteria = new HashMap();
		criteria.put("directorioTemporal", f.getDirectorioTemporal());
		criteria.put("nombreArchivo", f.getNombreArchivo());
		criteria.put("usuario", mPantallaPrincipalBean.getCurrentUser());
		criteria.put("tipoCarga", f.getTipoPago());
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoPrograma", f.getCodigoPrograma());
		// List tipoCargaList =
		// (List)sesion.getAttribute(Constants.LEC_TIPO_CARGA_LIST);
		// validamos el archivo excel y en criteria mandamos que estructura es
		// sin period o con periodo
		Map resultados = service.cargarArchivoExcel(criteria);
		int totalRegistros = Integer.parseInt((String) resultados
				.get("totalRegistros"));
	
		
		f.setNumeroCarga((String) resultados.get("numeroCarga"));
		f.setNumRegistros((String) resultados.get("totalRegistros"));
		f.setNumRegistrosInsertados((String) resultados
				.get("totalRegistrosInsertados"));
		f.setNumRegistrosError(Constants.NUMERO_CERO);
		f.setNumRegistrosValido(Constants.NUMERO_CERO);
		Integer numRegNoInsertados = Integer.parseInt(f.getNumRegistros())
				- Integer.parseInt(f.getNumRegistrosInsertados());
		f.setNumRegistrosNoInsertados(numRegNoInsertados.toString());
		lecProgramaArchivoList=new ArrayList();
		

		borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
		
		f.setFlagBotonValidar(true);
		f.setFlagBotonActualizar(false);
		this.validar();
	}

}
