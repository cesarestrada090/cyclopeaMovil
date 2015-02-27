package biz.belcorp.ssicc.web.spusicc.inc.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.DragDropEvent;
import org.primefaces.model.DualListModel;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.BaseOID;
import biz.belcorp.ssicc.dao.sisicc.model.EstructuraArchivo;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.GrupoRegionFLX;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCHabilitacionConcursoCargaPuntajeService;
import biz.belcorp.ssicc.web.form.EstructuraArchivoForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCHabilitacionConcursoCargaPuntajeForm;

/**
 * @author Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
public class MantenimientoINCHabilitacionConcursoCargaPuntajeAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5971443383274369404L;
	private List listConcursosActivos; // listConcursosActivos;
	private List listConcursosHabilitados;// listConcursosHabilitados;
	private List concursosSeleccionados = new ArrayList();
	private DualListModel<Base> listaConcursos;

	
	

	public DualListModel<Base> getListaConcursos() {
		return listaConcursos;
	}

	public void setListaConcursos(DualListModel<Base> listaConcursos) {
		this.listaConcursos = listaConcursos;
	}


	public List getConcursosSeleccionados() {
		return concursosSeleccionados;
	}

	public void setConcursosSeleccionados(List concursosSeleccionados) {
		this.concursosSeleccionados = concursosSeleccionados;
	}

	public List getlistConcursosActivos() {
		return listConcursosActivos;
	}

	public List getListConcursosActivos() {
		return listConcursosActivos;
	}

	public void setListConcursosActivos(List listConcursosActivos) {
		this.listConcursosActivos = listConcursosActivos;
	}

	public List getListConcursosHabilitados() {
		return listConcursosHabilitados;
	}

	public void setListConcursosHabilitados(List listConcursosHabilitados) {
		this.listConcursosHabilitados = listConcursosHabilitados;
	}

	public void setlistConcursosActivos(List listConcursosActivos) {
		this.listConcursosActivos = listConcursosActivos;
	}

	public List getlistConcursosHabilitados() {
		return listConcursosHabilitados;
	}

	public void setlistConcursosHabilitados(List listConcursosHabilitados) {
		this.listConcursosHabilitados = listConcursosHabilitados;
	}

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoINCHabilitacionConcursoCargaPuntajeForm f = new MantenimientoINCHabilitacionConcursoCargaPuntajeForm();
		return f;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}

		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		MantenimientoINCHabilitacionConcursoCargaPuntajeService service = (MantenimientoINCHabilitacionConcursoCargaPuntajeService) getBean("spusicc.mantenimientoINCHabilitacionConcursoCargaPuntajeService");
		MantenimientoINCHabilitacionConcursoCargaPuntajeForm f = (MantenimientoINCHabilitacionConcursoCargaPuntajeForm) this.formBusqueda;

		ArrayList listConcursoSeleccionado = new ArrayList();
		
		if(this.listaConcursos.getTarget()!=null && this.listaConcursos.getTarget().size()>0){
			for(int i=0;i<listaConcursos.getTarget().size();i++){	
				Base baseSeleccionada=(Base)listaConcursos.getTarget().get(i);
				listConcursoSeleccionado.add(baseSeleccionada.getCodigo());
			}
		}
        
		Map criteria = new HashMap();
		criteria.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());

		criteria.put("listaConcurso", listConcursoSeleccionado);
		criteria.put("login", usuario.getLogin());

		if (listConcursoSeleccionado.size() > 0) {
			service.insertConcursoHabilitado(criteria);
		} else {
			service.deleteConcursoHabilitado();
		}
		return true;
	}
	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveMensajeKeySaveOK()
	 */
	protected String devuelveMensajeKeySaveOK() {
		return "mantenimientoINCHabilitacionConcursoCargaPuntajeForm.cabecera.add";
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonNuevo = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonBuscar = false;
		this.mostrarCriteriosBusqueda = false;
		MantenimientoINCHabilitacionConcursoCargaPuntajeService service = (MantenimientoINCHabilitacionConcursoCargaPuntajeService) getBean("spusicc.mantenimientoINCHabilitacionConcursoCargaPuntajeService");
		listConcursosActivos = service.getListParametrosConcursosActivos();
		listConcursosHabilitados = service.getListConcursoHabilitado();
		this.listaConcursos = new DualListModel<Base>(listConcursosActivos, listConcursosHabilitados);

		MantenimientoINCHabilitacionConcursoCargaPuntajeForm f = (MantenimientoINCHabilitacionConcursoCargaPuntajeForm) this.formBusqueda;
		f.setCodigoPais(this.mPantallaPrincipalBean.getCountryCode());

	}



}
