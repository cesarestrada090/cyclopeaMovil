package biz.belcorp.ssicc.web.spusicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ProcesarINCConfiguracionConcursoCuponElectronico;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCHabilitacionConcursoCargaPuntajeService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoINCConfiguracionConcursoCuponElectronicoForm;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoINCConfiguracionConcursoCuponElectronicoSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class MantenimientoINCConfiguracionConcursoCuponElectronicoSearchAction extends BaseMantenimientoSearchAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2061253932079914608L;
	
	private List incentivosConcursosHabilitadosList;
	private int ultimoCuponOriginal;

	@Override
	protected String getSalirForward() 
	{
		return "mantenimientoINCConfiguracionConcursoCuponElectronicoList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception
	{		
		return "mantenimientoINCConfiguracionConcursoCuponElectronicoForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoINCConfiguracionConcursoCuponElectronicoSearchForm searchForm = new MantenimientoINCConfiguracionConcursoCuponElectronicoSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception
	{
		MantenimientoINCConfiguracionConcursoCuponElectronicoSearchForm searchForm = (MantenimientoINCConfiguracionConcursoCuponElectronicoSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
	
		searchForm.setCodigoPais(pais.getCodigo());
		// Obtenemos las propiedades del bean como un 'Map'
		Map criteria = BeanUtils.describe(searchForm);
		
		MantenimientoINCHabilitacionConcursoCargaPuntajeService serviceConcurso = (MantenimientoINCHabilitacionConcursoCargaPuntajeService) getBean("spusicc.mantenimientoINCHabilitacionConcursoCargaPuntajeService");
		List lista = serviceConcurso.getListaConcursosCEActivosByConcursoPeriodo(criteria);
		
		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}

		// Extraemos atributos y parámetros a usar
		MantenimientoINCConfiguracionConcursoCuponElectronicoForm saveForm = (MantenimientoINCConfiguracionConcursoCuponElectronicoForm) this.formMantenimiento;
		MantenimientoINCHabilitacionConcursoCargaPuntajeService serviceConcurso = (MantenimientoINCHabilitacionConcursoCargaPuntajeService) getBean("spusicc.mantenimientoINCHabilitacionConcursoCargaPuntajeService");
		boolean isNew = saveForm.isNewRecord();

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
				
		String codigoUsuario = usuario.getLogin();
		String codigoPais = pais.getCodigo();
		if(isNew)
		{
			String[] parts = saveForm.getCodigoConcurso().split(":");
			String part1 = parts[0]; 
			String part2 = parts[1]; 
			saveForm.setCodigoConcurso(part1);
			saveForm.setDescripcionConcurso(part2);			
		}		
		
		ProcesarINCConfiguracionConcursoCuponElectronico procesarINCConfiguracionConcursoCuponElectronico = new ProcesarINCConfiguracionConcursoCuponElectronico();
        BeanUtils.copyProperties(procesarINCConfiguracionConcursoCuponElectronico, saveForm);
        
        procesarINCConfiguracionConcursoCuponElectronico.setCodigoUsuario(codigoUsuario);
        procesarINCConfiguracionConcursoCuponElectronico.setCodigoPais(codigoPais);
        
		try {
			
			if (isNew) {
				String existe = serviceConcurso.insertMantenimientoINCConfiguracionConcursoCE(procesarINCConfiguracionConcursoCuponElectronico);
				
				if (StringUtils.equals(existe, Constants.NUMERO_CERO)) {
					// agregamos los mensajes de exito		
					this.ultimoCuponOriginal = Integer.parseInt(procesarINCConfiguracionConcursoCuponElectronico.getCuponInicial());
				}else{
					// agregamos los mensajes de ERROR
					this.getResourceMessage("mantenimientoINCConfiguracionConcursoCuponElectronicoForm.error.concursoExiste");
					return false;					
				}

			} else {
				serviceConcurso.updateMantenimientoINCConfiguracionConcursoCE(procesarINCConfiguracionConcursoCuponElectronico);
				this.ultimoCuponOriginal = Integer.parseInt(procesarINCConfiguracionConcursoCuponElectronico.getUltimoCupon());
			}
			
		} catch (InvalidIdentifierException iie) {
			String codigo = iie.getIdentifier().toString();
			this.getResourceMessage("errors.invalid.id", new Object[]{codigo});
	
			return false;
		} catch (InvalidDescriptionException ide) {
			String descripcion = ide.getDescription();
			
			this.getResourceMessage("errors.invalid.description", new Object[]{descripcion});
			return false;
		}
		
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception 
	{
		MantenimientoINCConfiguracionConcursoCuponElectronicoForm f = new MantenimientoINCConfiguracionConcursoCuponElectronicoForm();

		f.setCodigoConcurso(null);
		f.setCodigoPeriodoInicial(null);
		f.setCodigoPeriodoFinal(null);
		f.setCuponInicial(null);
		f.setUltimoCupon(null);
		f.setEstado(null);

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		MantenimientoINCHabilitacionConcursoCargaPuntajeService serviceConcurso = (MantenimientoINCHabilitacionConcursoCargaPuntajeService) getBean("spusicc.mantenimientoINCHabilitacionConcursoCargaPuntajeService");

		// cargando en session la lista de concursos habilitados
		this.incentivosConcursosHabilitadosList = serviceConcurso.getListConcursoHabilitado();

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteria);

		// Carga de PeriodoProceso
		f.setCodigoPeriodoProcesoAux(controlFacturacion.getCodigoPeriodo());

		Map registroSeleccionado = (Map) this.beanRegistroSeleccionado;

		if (!this.accion.equals(this.ACCION_NUEVO)) {
			// Si el id ha sido enviado, buscamos la informacion
			// en caso contrario, no hacemos nada, se esta insertando
			// un nuevo registro a la aplicación
			String codigoConcurso = registroSeleccionado.get("numeroConcurso").toString();
			
			if (codigoConcurso != null) {
				if (log.isDebugEnabled()) {
					log.debug("codigoConcurso seleccionado de la lista: "
							+ codigoConcurso);
				}

				Map params = new HashMap();
				params.put("codigoPais", pais.getCodigo());
				params.put("codigoConcurso", codigoConcurso);

				Map datos = serviceConcurso.getDatosConcursoCuponElectronico(params);
				f.setDescripcionConcurso(datos.get("descripcionConcurso").toString());
				f.setCodigoConcurso(datos.get("codigoConcurso").toString());
				f.setCodigoPeriodoInicial(datos.get("codigoPeriodoInicial").toString());
				f.setCodigoPeriodoFinal(datos.get("codigoPeriodoFinal").toString());
				f.setCuponInicial(datos.get("cuponInicial").toString());
				f.setUltimoCupon(datos.get("ultimoCupon").toString());
				f.setEstado(datos.get("estado").toString());

				f.setNewRecord(false);
				
				this.ultimoCuponOriginal = Integer.parseInt(f.getUltimoCupon());
			}
		}

		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
	}
	
	
	@Override
	public String setValidarMantenimiento() 
	{
		MantenimientoINCConfiguracionConcursoCuponElectronicoForm concursoForm = (MantenimientoINCConfiguracionConcursoCuponElectronicoForm) this.formMantenimiento;
		String mensaje = null;
		
		if(new Integer(concursoForm.getCuponInicial()) <= 0){
			mensaje = this.getResourceMessage("mantenimientoINCConfiguracionConcursoCuponElectronicoForm.error.cuponInicialMayorCero");
		}
		
		if ((concursoForm.getCodigoPeriodoInicial() != null && concursoForm.getCodigoPeriodoInicial().length() > 1) 
				&& (concursoForm.getCodigoPeriodoProcesoAux() != null && concursoForm.getCodigoPeriodoProcesoAux().length() > 1))
		{
            Long periodoInicial = new Long(concursoForm.getCodigoPeriodoInicial());
            Long periodoAux = new Long(concursoForm.getCodigoPeriodoProcesoAux());

            if (periodoInicial.longValue() < periodoAux.longValue()) {
            	mensaje = this.getResourceMessage("mantenimientoINCConfiguracionConcursoCuponElectronicoForm.error.periodoInicialNoValido");
            }
        }
		
		if ((concursoForm.getCodigoPeriodoFinal() != null && concursoForm.getCodigoPeriodoFinal().length() > 1) 
				&& (concursoForm.getCodigoPeriodoInicial() != null && concursoForm.getCodigoPeriodoInicial().length() > 1))
		{
            Long periodoFinal = new Long(concursoForm.getCodigoPeriodoFinal());
            Long periodoInicial = new Long(concursoForm.getCodigoPeriodoInicial());

            if (periodoFinal.longValue() < periodoInicial.longValue()) {
            	mensaje = this.getResourceMessage("mantenimientoINCConfiguracionConcursoCuponElectronicoForm.error.rangoPeriodos");
            }
        }	
		
		if (!this.accion.equals(this.ACCION_NUEVO)) 
		{
			if(new Integer(concursoForm.getUltimoCupon()) < this.ultimoCuponOriginal)
        	{
				mensaje = this.getResourceMessage("mantenimientoINCConfiguracionConcursoCuponElectronicoForm.error.ultimoCuponMayorOriginal") + this.ultimoCuponOriginal;
            }						
		}
		
		return mensaje;
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() 
	{
		MantenimientoINCConfiguracionConcursoCuponElectronicoForm concursoForm = (MantenimientoINCConfiguracionConcursoCuponElectronicoForm) this.formMantenimiento;
		boolean isNew = concursoForm.isNewRecord();
		if(isNew){
			return "mantenimientoINCConfiguracionConcursoCuponElectronicoForm.added";
		}else{
			return "mantenimientoINCConfiguracionConcursoCuponElectronicoForm.updated";
		}	
	}

	public List getIncentivosConcursosHabilitadosList() {
		return incentivosConcursosHabilitadosList;
	}

	public void setIncentivosConcursosHabilitadosList(
			List incentivosConcursosHabilitadosList) {
		this.incentivosConcursosHabilitadosList = incentivosConcursosHabilitadosList;
	}

	public int getUltimoCuponOriginal() {
		return ultimoCuponOriginal;
	}

	public void setUltimoCuponOriginal(int ultimoCuponOriginal) {
		this.ultimoCuponOriginal = ultimoCuponOriginal;
	}
}
