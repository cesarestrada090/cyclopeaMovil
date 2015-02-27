package biz.belcorp.ssicc.web.spusicc.lideres.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.lideres.MantenimientoLIDLideresService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.lideres.form.MantenimientoLIDCondicionDespachoCanastaForm;

@SessionScoped
@ManagedBean
public class MantenimientoLIDCondicionDespachoCanastaAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8376258651862780454L;
	private String codigoPeriodoActual;
	private List lidCondiList;
	private boolean habilitadorHidden;
	private boolean indicadorLider;

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoLIDCondicionDespachoCanastaForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoLIDCondicionDespachoCanastaForm f = new MantenimientoLIDCondicionDespachoCanastaForm();
		return f;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoLIDCondicionDespachoCanastaForm f = (MantenimientoLIDCondicionDespachoCanastaForm) this.formBusqueda;
		// if (f.getCodigoPeriodo().compareTo(codigoPeriodoActual) >= 0)
		// this.mostrarBotonSave = true;
		MantenimientoLIDLideresService service = (MantenimientoLIDLideresService) getBean("spusicc.mantenimientoLIDLideresService");
		f.setCodigoCondicionDespacho("");
		f.setIndicadorLider("");
		f.setMetaCondicion("");
		List list = service.getListCondicionDespachoCanasta(f
				.getCodigoPeriodo());
		if (list.size() > 0) {
			Map map = (Map) list.get(0);
			f.setCodigoCondicionDespacho((String) map.get("codigoCondicion"));
			f.setIndicadorLider((String) map.get("valorRequiereLider"));
			f.setMetaCondicion((String) map.get("valorMeta"));
		}

		if (f.getCodigoPeriodo().equals(codigoPeriodoActual))
			this.indicadorLider = true;
		else
			this.indicadorLider = false;

		if (Integer.parseInt(f.getCodigoPeriodo()) >= Integer
				.parseInt(codigoPeriodoActual))
			this.mostrarBotonSave = true;
		else
			this.mostrarBotonSave = false;

		List listFake = new ArrayList();
		this.mostrarListaBusqueda = false;
		this.habilitadorHidden = false;
		listFake.add("listaFalsa");
		return listFake;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoLIDLideresService service = (MantenimientoLIDLideresService) getBean("spusicc.mantenimientoLIDLideresService");
		MantenimientoLIDCondicionDespachoCanastaForm f = (MantenimientoLIDCondicionDespachoCanastaForm) this.formBusqueda;
		List list = service.getListCondicionDespachoCanasta(f
				.getCodigoPeriodo());

		Map map = new HashMap();
		map.put("codigoPais", pais.getCodigo());
		map.put("codigoPeriodo", f.getCodigoPeriodo());
		map.put("codigoCondicionDespacho", f.getCodigoCondicionDespacho());
		String valor = "0";
		if (indicadorLider)
			valor = "1";

		map.put("meta", f.getMetaCondicion());
		map.put("indicadorLider", valor);
		map.put("usuario", usuario.getLogin());
		try {
			if (list.size() == 0) {
				service.insertCondicionDespachoCanasta(map);
				listaBusqueda=null;
				throw new Exception(
						this.getResourceMessage("mantenimientoLIDCondicionDespachoCanastaSearchForm.cabecera.save"));

			} else {
				service.updateCondicionDespachoCanasta(map);
				listaBusqueda=null;
				throw new Exception(
						this.getResourceMessage("mantenimientoLIDCondicionDespachoCanastaSearchForm.cabecera.update"));

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;

	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub

		MantenimientoLIDCondicionDespachoCanastaForm f = (MantenimientoLIDCondicionDespachoCanastaForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		f.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(
				pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT));
		this.codigoPeriodoActual = f.getCodigoPeriodo();
		MantenimientoLIDLideresService serviceLideres = (MantenimientoLIDLideresService) getBean("spusicc.mantenimientoLIDLideresService");
		List listCondicionesLideres = serviceLideres
				.getListCondicionesLideres();// lista de condicion de despacho
		this.lidCondiList = listCondicionesLideres;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonSave = false;

	}

	/**
	 * Metodo para Ocultar
	 * 
	 * @param val
	 */
	public void ocultarCampo(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("ocultarCampo");
		}
		String valor = (String) val.getNewValue();
		if (valor.equals("03"))
			habilitadorHidden = true;
		else
			habilitadorHidden = false;
	}

	
	/**
	 * @return the codigoPeriodoActual
	 */
	public String getCodigoPeriodoActual() {
		return codigoPeriodoActual;
	}

	/**
	 * @param codigoPeriodoActual
	 *            the codigoPeriodoActual to set
	 */
	public void setCodigoPeriodoActual(String codigoPeriodoActual) {
		this.codigoPeriodoActual = codigoPeriodoActual;
	}

	/**
	 * @return the lidCondiList
	 */
	public List getLidCondiList() {
		return lidCondiList;
	}

	/**
	 * @param lidCondiList
	 *            the lidCondiList to set
	 */
	public void setLidCondiList(List lidCondiList) {
		this.lidCondiList = lidCondiList;
	}

	/**
	 * @return the habilitadorHidden
	 */
	public boolean isHabilitadorHidden() {
		return habilitadorHidden;
	}

	/**
	 * @param habilitadorHidden
	 *            the habilitadorHidden to set
	 */
	public void setHabilitadorHidden(boolean habilitadorHidden) {
		this.habilitadorHidden = habilitadorHidden;
	}

	/**
	 * @return the indicadorLider
	 */
	public boolean isIndicadorLider() {
		return indicadorLider;
	}

	/**
	 * @param indicadorLider
	 *            the indicadorLider to set
	 */
	public void setIndicadorLider(boolean indicadorLider) {
		this.indicadorLider = indicadorLider;
	}

}
