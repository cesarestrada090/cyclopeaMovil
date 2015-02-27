package biz.belcorp.ssicc.web.spusicc.ape.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.spusicc.ape.ConsultaAPESistemaPicadoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.ape.form.ConsultaAPESistemaPicadoSearchForm;

@ManagedBean
@SessionScoped
public class ConsultaAPESistemaPicadoSearchAction extends BaseConsultaAbstractAction 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -695544320994241152L;
	
	private List apeSistemaPicadoList;

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaAPESistemaPicadoSearchForm formBusqueda = new ConsultaAPESistemaPicadoSearchForm();
		return formBusqueda;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		// Cargamos el Listado de Sistema de Picado

		ConsultaAPESistemaPicadoService service = (ConsultaAPESistemaPicadoService) this.getBean("spusicc.consultaAPESistemaPicadoService");
		Map criteria = new HashMap();

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ConsultaAPESistemaPicadoSearchForm f = (ConsultaAPESistemaPicadoSearchForm) this.formBusqueda;
		f.setCodigoPais(pais.getCodigo());

		this.apeSistemaPicadoList = service.getSistemaPicadoLista(criteria);
		this.mostrarBotonBuscar = false;
	}

	public List getApeSistemaPicadoList() {
		return apeSistemaPicadoList;
	}

	public void setApeSistemaPicadoList(List apeSistemaPicadoList) {
		this.apeSistemaPicadoList = apeSistemaPicadoList;
	}
}
