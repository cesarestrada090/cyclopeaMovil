package biz.belcorp.ssicc.web.spusicc.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.MantenimientoPERPercepcionesOtrosCanalesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.form.ProcesoPEREliminarPercepcionesSistemasExternosForm;

@ManagedBean
@SessionScoped
public class ProcesoPEREliminarPercepcionesSistemasExternosAction extends BaseProcesoAbstractAction{

	private static final long serialVersionUID = -654767746474714851L;
	
	private List siccSubAccesoList;
	private List siccAccesoList;
	private List siccCanalList;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
				
		return new ProcesoPEREliminarPercepcionesSistemasExternosForm();
		
	}
	
	  public void showAccesoXCanal(ValueChangeEvent val){
			log.debug(">>showAccesoXCanal ");
			log.debug("val: "+ val.getNewValue());
			ProcesoPEREliminarPercepcionesSistemasExternosForm form = (ProcesoPEREliminarPercepcionesSistemasExternosForm) this.formProceso;
			String canal = (String) val.getNewValue();		
		      
			
			if (log.isDebugEnabled()) {
				log.debug("loadAcceso");
			}
		
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		
			if (StringUtils.isBlank(canal)) {
				this.siccAccesoList = svc
						.getAccesosTodosByCanalByCodigoISO(usuario.getIdioma()
								.getCodigoISO());
				this.siccSubAccesoList = svc
						.getSubaccesosByCodigoISO(usuario.getIdioma()
								.getCodigoISO());
			} else {

				this.siccAccesoList = getAccesoList(canal);
			}

			form.setCodigoAcceso(null);
		}
	   
	   public void showSubAccesoXAcesso(ValueChangeEvent val){
			log.debug(">>showSubAccesoXAcesso ");
			log.debug("val: "+ val.getNewValue());
			ProcesoPEREliminarPercepcionesSistemasExternosForm form = (ProcesoPEREliminarPercepcionesSistemasExternosForm) this.formProceso;
			String[] acceso = (String[]) val.getNewValue();		
		      
			
			if (log.isDebugEnabled()) {
				log.debug("loadSubAcceso");
			}
		
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		
			if (ArrayUtils.isEmpty(acceso)) {
				this.siccSubAccesoList = svc
						.getSubaccesosByCodigoISO(usuario.getIdioma()
								.getCodigoISO());

			} else {
                List aux = ListUtils.EMPTY_LIST;
				for (int i = 0; i < acceso.length; i++) {
					List list = getSubAccesoList(acceso[i]);
					aux = ListUtils.union(aux, list);
				}
				this.siccSubAccesoList = aux;
			}

			form.setCodigoSubAcceso(null);
		}
	   
	
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}
		ProcesoPEREliminarPercepcionesSistemasExternosForm f=(ProcesoPEREliminarPercepcionesSistemasExternosForm)this.formProceso;
		Usuario usuario =this.mPantallaPrincipalBean.getCurrentUser();

		// Cargamos los combos a utilizar
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		
		this.siccCanalList=svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());

		Date hoy = new Date(System.currentTimeMillis());
				
		//inicializamos las fechas con la fecha actual
		f.setFechaInicio(hoy);
		f.setFechaFinal(hoy);
		log.debug("Hasta aca todo ok...");
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'execute' method");
		}
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario =this.mPantallaPrincipalBean.getCurrentUser();
		params.put("usuario", usuario);
		params.put("descripcionPais",pais.getDescripcion());
	
		MantenimientoPERPercepcionesOtrosCanalesService service = (MantenimientoPERPercepcionesOtrosCanalesService)
				getBean("spusicc.mantenimientoPERPercepcionesOtrosCanalesService");
		service.deletePercepcionesSistemasExternos(params);
		
		return params;
		
	}
	

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(
			Map<String, Object> params, BaseForm form) throws Exception {
		// TODO Auto-generated method stub
		params = super.prepareParamsBeforeExecute(params, form);

		ProcesoPEREliminarPercepcionesSistemasExternosForm f = (ProcesoPEREliminarPercepcionesSistemasExternosForm) this.formProceso;
		params.put("codigoPais",f.getCodigoPais());
		params.put("codigoCanal",f.getCodigoCanal());
		params.put("codigoAcceso",(String [])f.getCodigoAcceso());
		params.put("codigoSubacceso",(String [])f.getCodigoSubAcceso());
		params.put("fechaInicio",DateFormatUtils.format(f.getFechaInicio(),"dd/MM/yyyy"));
		params.put("fechaFinal",DateFormatUtils.format(f.getFechaFinal(),"dd/MM/yyyy"));
		
		return params;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	
	/**
	 * @return the siccSubAccesoList
	 */
	public List getSiccSubAccesoList() {
		return siccSubAccesoList;
	}

	/**
	 * @param siccSubAccesoList the siccSubAccesoList to set
	 */
	public void setSiccSubAccesoList(List siccSubAccesoList) {
		this.siccSubAccesoList = siccSubAccesoList;
	}

	/**
	 * @return the siccAccesoList
	 */
	public List getSiccAccesoList() {
		return siccAccesoList;
	}

	/**
	 * @param siccAccesoList the siccAccesoList to set
	 */
	public void setSiccAccesoList(List siccAccesoList) {
		this.siccAccesoList = siccAccesoList;
	}
	

	
	

}
