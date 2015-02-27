package biz.belcorp.ssicc.web.spusicc.ventas.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.ventas.form.ProcesoVENCalculoRecuperacionCobranzaForm;

@ManagedBean
@SessionScoped
public class ProcesoVENCalculoRecuperacionCobranzaAction extends BaseProcesoAbstractAction{

	private static final long serialVersionUID = -654767746474714851L;
	
	private List siccSociedadList;
	private List siccMarcaList;
	private List siccCanalList;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoVENCalculoRecuperacionCobranzaForm procesoForm =new ProcesoVENCalculoRecuperacionCobranzaForm();		
		return procesoForm;
		
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}
		ProcesoVENCalculoRecuperacionCobranzaForm f=(ProcesoVENCalculoRecuperacionCobranzaForm)this.formProceso;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario =this.mPantallaPrincipalBean.getCurrentUser();

		// Cargamos los combos a utilizar
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.siccSociedadList=svc.getSociedadesByCodigoPais(pais.getCodigo());
		this.siccMarcaList=svc.getMarcas();
		this.siccCanalList=svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoSociedad(Constants.CODIGO_SOCIEDAD_DEFAULT_CALCULO_RECU_COBRANZA);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));
		f.setCodigoPeriodo(periodo);
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
		return params;		
	}

	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}

	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}
	

	

}
