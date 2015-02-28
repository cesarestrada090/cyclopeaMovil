package biz.belcorp.ssicc.web.spusicc.flexipago.action;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.ParametroFLX;
import biz.belcorp.ssicc.service.spusicc.flexipago.MantenimientoFLXModeloOtorgamientoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.flexipago.form.MantenimientoFLXParametrosForm;
import biz.belcorp.ssicc.web.spusicc.flexipago.form.ParametroForm;

@ManagedBean
@SessionScoped
public class MantenimientoFLXParametrosAction extends BaseMantenimientoSearchAbstractAction{

	private static final long serialVersionUID = -1725326452037834167L;

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {		
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoFLXParametrosForm searchForm = new MantenimientoFLXParametrosForm();
		return searchForm;
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
			log.debug("Entering 'setSaveAttributes' method - MantenimientoFLXParametrosAction");
		}		
		
		MantenimientoFLXParametrosForm f = (MantenimientoFLXParametrosForm) this.formBusqueda;
		MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService)getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		List parametros = new ArrayList();
		
		ParametroForm p1[] = f.getParametrosGrupo01();
		ParametroForm p3[] = f.getParametrosGrupo03();
		ParametroForm p4[] = f.getParametrosGrupo04();
		ParametroForm p5[] = f.getParametrosGrupo05();
		ParametroForm p8[] = f.getParametrosGrupo08();
		ParametroForm p9[] = f.getParametrosGrupo09();
		
		for (int i = 0; i < p1.length; i++) {
			ParametroFLX parametro = new ParametroFLX();
			BeanUtils.copyProperties(parametro, p1[i]);
			parametros.add(parametro);
		}
		
		for (int i = 0; i < p3.length; i++) {
			ParametroFLX parametro = new ParametroFLX();
			BeanUtils.copyProperties(parametro, p3[i]);
			parametros.add(parametro);
		}
		
		for (int i = 0; i < p4.length; i++) {
			ParametroFLX parametro = new ParametroFLX();
			BeanUtils.copyProperties(parametro, p4[i]);
			parametros.add(parametro);
		}
		
		for (int i = 0; i < p5.length; i++) {
			ParametroFLX parametro = new ParametroFLX();
			BeanUtils.copyProperties(parametro, p5[i]);
			parametros.add(parametro);
		}

		for (int i = 0; i < p8.length; i++) {
			ParametroFLX parametro = new ParametroFLX();
			BeanUtils.copyProperties(parametro, p8[i]);
			parametros.add(parametro);
		}
		
		for (int i = 0; i < p9.length; i++) {
			ParametroFLX parametro = new ParametroFLX();
			BeanUtils.copyProperties(parametro, p9[i]);
			parametros.add(parametro);
		}

		service.updateParametros(parametros, usuario);
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAttributes - MantenimientoFLXParametrosAction");
		}
		this.mostrarBotonNuevo=false;
		this.mostrarBotonBuscar=false;
		this.mostrarBotonConsultar=false;
		this.mostrarBotonModificar=false;
		this.mostrarBotonSalir=false;
		this.mostrarBotonEliminar=false;
		this.mostrarListaBusqueda=false;
		this.mostrarCriteriosBusqueda=false;
		
		
		
		MantenimientoFLXParametrosForm f = (MantenimientoFLXParametrosForm) this.formBusqueda;
		MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService)getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		
		List parametroGrupo01 = service.getParametrosByGrupo("01");
		List parametroGrupo02 = service.getParametrosByGrupo("02");
		List parametroGrupo03 = service.getParametrosByGrupo("03");
		List parametroGrupo04 = service.getParametrosByGrupo("04");
		List parametroGrupo05 = service.getParametrosByGrupo("05");
		List parametroGrupo06 = service.getParametrosByGrupo("06");
		List parametroGrupo08 = service.getParametrosByGrupo("08");
		List parametroGrupo09 = service.getParametrosByGrupo("09");
		
		ParametroForm p1[] = new ParametroForm[parametroGrupo01.size()]; 
		ParametroForm p2[] = new ParametroForm[parametroGrupo02.size()];
		ParametroForm p3[] = new ParametroForm[parametroGrupo03.size()];
		ParametroForm p4[] = new ParametroForm[parametroGrupo04.size()];
		ParametroForm p5[] = new ParametroForm[parametroGrupo05.size()];
		ParametroForm p6[] = new ParametroForm[parametroGrupo06.size()];
		ParametroForm p8[] = new ParametroForm[parametroGrupo08.size()];
		ParametroForm p9[] = new ParametroForm[parametroGrupo09.size()];
		
		try {
			for (int i = 0; i < parametroGrupo01.size(); i++) {
				p1[i] = new ParametroForm();
				BeanUtils.copyProperties(p1[i], parametroGrupo01.get(i));
			}
			f.setParametrosGrupo01(p1);
			
			for (int i = 0; i < parametroGrupo02.size(); i++) {
				p2[i] = new ParametroForm();
				BeanUtils.copyProperties(p2[i], parametroGrupo02.get(i));
				String[]valor = StringUtils.split(p2[i].getValorParametro(), "|");
				p2[i].setValorParametro(valor[0]);
				p2[i].setValorParametro2(valor[1]);
			}
			f.setParametrosGrupo02(p2);
			
			for (int i = 0; i < parametroGrupo03.size(); i++) {
				p3[i] = new ParametroForm();
				BeanUtils.copyProperties(p3[i], parametroGrupo03.get(i));
			}
			f.setParametrosGrupo03(p3);
			
			for (int i = 0; i < parametroGrupo04.size(); i++) {
				p4[i] = new ParametroForm();
				BeanUtils.copyProperties(p4[i], parametroGrupo04.get(i));
			}
			f.setParametrosGrupo04(p4);
			
			for (int i = 0; i < parametroGrupo05.size(); i++) {
				p5[i] = new ParametroForm();
				BeanUtils.copyProperties(p5[i], parametroGrupo05.get(i));
			}
			f.setParametrosGrupo05(p5);
			
			for (int i = 0; i < parametroGrupo06.size(); i++) {
				p6[i] = new ParametroForm();
				BeanUtils.copyProperties(p6[i], parametroGrupo06.get(i));
			}
			f.setParametrosGrupo06(p6);

			for (int i = 0; i < parametroGrupo08.size(); i++) {
				p8[i] = new ParametroForm();
				BeanUtils.copyProperties(p8[i], parametroGrupo08.get(i));
			}
			f.setParametrosGrupo08(p8);
			
			for (int i = 0; i < parametroGrupo09.size(); i++) {
				p9[i] = new ParametroForm();
				BeanUtils.copyProperties(p9[i], parametroGrupo09.get(i));
			}
			
			f.setParametrosGrupo09(p9);
			f.setCodigoPais(pais.getCodigo());
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() {
		//MantenimientoFLXParametrosForm sistemaForm = (MantenimientoFLXParametrosForm) this.formBusqueda;
		return "mantenimientoFLXParametrosForm.updated";
		
	}

	

}
