package biz.belcorp.ssicc.web.spusicc.let.action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.spusicc.let.ConsultaLETResultadosConcursosService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.let.form.ConsultaLETResultadosConcursosSearchForm;

@ManagedBean
@SessionScoped
public class ConsultaLETResultadosConcursosSearchAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8301336699640579012L;
	private List siccTipoUnidadList;
	private List letParametrosConcursoList;
	private List consultaLetResultadosConcursos;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ConsultaLETResultadosConcursosSearchForm form = new ConsultaLETResultadosConcursosSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'find' method");
		}
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ConsultaLETResultadosConcursosService service = (ConsultaLETResultadosConcursosService)getBean("spusicc.consultaLETResultadosConcursosService");
		ConsultaLETResultadosConcursosSearchForm f = (ConsultaLETResultadosConcursosSearchForm) this.formReporte;
		
		/* obteniendo valores */
		Map criterios = new HashMap();
		criterios.put("codigoPais", pais.getCodigo());
		criterios.put("tipoUnidad", f.getTipoUnidad());
		criterios.put("codigoZona", f.getCodigoZona());
		criterios.put("codigoConcurso", f.getCodigoConcurso());
		
		/* Obteniendo Lista */
		List resultado = service.getConsultaLETResultadosConcursos(criterios);
		this.consultaLetResultadosConcursos = resultado;
		return resultado;

		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setViewAtributes");
		}
		this.mostrarListaBusqueda = true;
		this.mostrarBotonBuscar = true;
		this.mostrarReportePDF = false;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		ConsultaLETResultadosConcursosSearchForm f = (ConsultaLETResultadosConcursosSearchForm) this.formReporte;
		
		ArrayList resultadoUnidad = new ArrayList();
		Base[] mesUnidad = new Base[3];
		String[] presentacionesUnidad = {"Region","Zona","Seccion"};
		
		for (int i = 0; i < mesUnidad.length ; i++) {
			mesUnidad[i] = new Base();
			mesUnidad[i].setCodigo("" + (i + 1));
			mesUnidad[i].setDescripcion(presentacionesUnidad[i]);
			resultadoUnidad.add(mesUnidad[i]);
		}
		
		ConsultaLETResultadosConcursosService service = (ConsultaLETResultadosConcursosService)getBean("spusicc.consultaLETResultadosConcursosService");
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		
		List parametroConcursoDescCompletaList = service.getParametroConcursoDescCompletaList(criteria); 
		this.siccTipoUnidadList=resultadoUnidad;
		this.letParametrosConcursoList=parametroConcursoDescCompletaList;

		f.setTipoUnidad("2");

	}

	

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return " ";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return " ";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		return params;
	}

	/**
	 * @return the siccTipoUnidadList
	 */
	public List getSiccTipoUnidadList() {
		return siccTipoUnidadList;
	}

	/**
	 * @param siccTipoUnidadList the siccTipoUnidadList to set
	 */
	public void setSiccTipoUnidadList(List siccTipoUnidadList) {
		this.siccTipoUnidadList = siccTipoUnidadList;
	}

	/**
	 * @return the letParametrosConcursoList
	 */
	public List getLetParametrosConcursoList() {
		return letParametrosConcursoList;
	}

	/**
	 * @param letParametrosConcursoList the letParametrosConcursoList to set
	 */
	public void setLetParametrosConcursoList(List letParametrosConcursoList) {
		this.letParametrosConcursoList = letParametrosConcursoList;
	}

	/**
	 * @return the consultaLetResultadosConcursos
	 */
	public List getConsultaLetResultadosConcursos() {
		return consultaLetResultadosConcursos;
	}

	/**
	 * @param consultaLetResultadosConcursos the consultaLetResultadosConcursos to set
	 */
	public void setConsultaLetResultadosConcursos(
			List consultaLetResultadosConcursos) {
		this.consultaLetResultadosConcursos = consultaLetResultadosConcursos;
	}

	

}

