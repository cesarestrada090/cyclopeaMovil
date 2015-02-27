package biz.belcorp.ssicc.web.spusicc.cronograma.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.cronograma.model.PeriodoCronograma;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.spusicc.cronograma.MantenimientoCRAPeriodoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.cronograma.form.MantenimientoCRAPeriodoForm;

@SessionScoped
@ManagedBean
public class MantenimientoCRAPeriodoAction extends
		BaseMantenimientoSearchAbstractAction {
	/**
	 * 
	 */

	private static final long serialVersionUID = 3235563050175383094L;
	private List craPeriodoCorporativoList;

	public List getCraPeriodoCorporativoList() {
		return craPeriodoCorporativoList;
	}

	public void setCraPeriodoCorporativoList(List craPeriodoCorporativoList) {
		this.craPeriodoCorporativoList = craPeriodoCorporativoList;
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
		MantenimientoCRAPeriodoForm searchForm = new MantenimientoCRAPeriodoForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();

		MantenimientoCRAPeriodoService service = (MantenimientoCRAPeriodoService) getBean("spusicc.cronograma.mantenimientoCRAPeriodoService");
		MantenimientoCRAPeriodoForm f = (MantenimientoCRAPeriodoForm) formBusqueda;

		craPeriodoCorporativoList = new ArrayList();
		// Seteamos año actual
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String anhio = sdf.format(new Date(System.currentTimeMillis()));

		if (f.getAnhio() == null)
			f.setAnhio(anhio);

		List periodoList = service.getPeriodoCorporativoList(f.getAnhio());
		List periodoNuevoList = new ArrayList();

		List periodoCronogramaList = new ArrayList();
		periodoCronogramaList = service.getPeriodoCronogramaList(f.getAnhio());

		if (periodoCronogramaList.size() == 0) {

			// si no existen periodos corporativos (seg_perio_corpo), se crean
			if (periodoList.size() == 0) {
				Map criteria = new HashMap();
				criteria.put("codigoPais", pais.getCodigo());
				criteria.put("anhio", f.getAnhio());
				periodoNuevoList = service.insertPeriodoCorporativo(criteria);
				craPeriodoCorporativoList = periodoNuevoList;
			} else
				craPeriodoCorporativoList = periodoList;
		} else {

			craPeriodoCorporativoList = periodoCronogramaList;

		}

		return craPeriodoCorporativoList;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("JFA : Entering 'save' method");
		}

		MantenimientoCRAPeriodoForm f = (MantenimientoCRAPeriodoForm) formBusqueda;

		int tamanio = this.listaBusqueda.size();
		String[] listaCodigoPeriodo = new String[tamanio];
		String[] listaDuracion = new String[tamanio];
		;
		String[] listaFechaFin = new String[tamanio];
		;
		String[] listaFechaInicio = new String[tamanio];
		;
		String[] listaIndicadorPeriodoCorto = new String[tamanio];
		;
		String[] listaIndicadorPeriodoCruce = new String[tamanio];
		;
		String[] listaOidPeriodo = new String[tamanio];
		;
		for (int i = 0; i < this.listaBusqueda.size(); i++) {

			listaCodigoPeriodo[i] = ((PeriodoCronograma) listaBusqueda.get(i))
					.getCodigoPeriodo();
			listaDuracion[i] = ((PeriodoCronograma) listaBusqueda.get(i))
					.getDuracion();
			listaFechaFin[i] = ((PeriodoCronograma) listaBusqueda.get(i))
					.getFechaFin();
			listaFechaInicio[i] = ((PeriodoCronograma) listaBusqueda.get(i))
					.getFechaInicio();
			listaIndicadorPeriodoCorto[i] = ((PeriodoCronograma) listaBusqueda
					.get(i)).getIndicadorPeriodoCorto();
			listaIndicadorPeriodoCruce[i] = ((PeriodoCronograma) listaBusqueda
					.get(i)).getIndicadorPeriodoCruce();
			listaOidPeriodo[i] = ((PeriodoCronograma) listaBusqueda.get(i))
					.getOid();
		}
		f.setListaCodigoPeriodo(listaCodigoPeriodo);
		f.setListaOidPeriodo(listaOidPeriodo);
		f.setListaDuracion(listaDuracion);
		f.setListaIndicadorPeriodoCruce(listaIndicadorPeriodoCruce);
		f.setListaIndicadorPeriodoCorto(listaIndicadorPeriodoCorto);
		f.setListaFechaFin(listaFechaFin);
		f.setListaFechaInicio(listaFechaInicio);

		// Extraemos el usuario de la sesión
		String codigoUsuario = mPantallaPrincipalBean.getCurrentUser()
				.getCodigo();

		MantenimientoCRAPeriodoService service = (MantenimientoCRAPeriodoService) getBean("spusicc.cronograma.mantenimientoCRAPeriodoService");

		Map criteria = new HashMap();
		f.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		criteria.put("pais", f.getCodigoPais());
		criteria.put("marca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("canal", Constants.CODIGO_CANAL_DEFAULT);
		criteria.put("acceso", Constants.CODIGO_ACCESO_DEFAULT);
		criteria.put("anhio", f.getAnhio());

		criteria.put("listaOidPeriodo", (String[]) f.getListaOidPeriodo());
		criteria.put("listaCodigoPeriodo", (String[]) f.getListaCodigoPeriodo());
		criteria.put("listaFechaInicio", (String[]) f.getListaFechaInicio());
		criteria.put("listaFechaFin", (String[]) f.getListaFechaFin());
		criteria.put("listaIndicadorPeriodoCorto",
				(String[]) f.getListaIndicadorPeriodoCorto());
		criteria.put("listaIndicadorPeriodoCruce",
				(String[]) f.getListaIndicadorPeriodoCruce());
		criteria.put("usuario", codigoUsuario);
		criteria.put("newRecord", f.isNewRecord());
		service.insertPeriodoCronograma(criteria);

		return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();
		this.mostrarBotonNuevo = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonModificar = false;
		MantenimientoCRAPeriodoService service = (MantenimientoCRAPeriodoService) getBean("spusicc.cronograma.mantenimientoCRAPeriodoService");
		MantenimientoCRAPeriodoForm f = (MantenimientoCRAPeriodoForm) formBusqueda;

		craPeriodoCorporativoList = new ArrayList();
		// Seteamos año actual
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String anhio = sdf.format(new Date(System.currentTimeMillis()));

		if (f.getAnhio() == null)
			f.setAnhio(anhio);

		List periodoList = service.getPeriodoCorporativoList(f.getAnhio());
		List periodoNuevoList = new ArrayList();

		List periodoCronogramaList = new ArrayList();
		periodoCronogramaList = service.getPeriodoCronogramaList(f.getAnhio());

		if (periodoCronogramaList.size() == 0) {

			// si no existen periodos corporativos (seg_perio_corpo), se crean
			if (periodoList.size() == 0) {
				Map criteria = new HashMap();
				criteria.put("codigoPais", mPantallaPrincipalBean
						.getCurrentCountry().getCodigo());
				criteria.put("anhio", f.getAnhio());
				periodoNuevoList = service.insertPeriodoCorporativo(criteria);
				craPeriodoCorporativoList = periodoNuevoList;
			} else
				craPeriodoCorporativoList = periodoList;
		} else {

			craPeriodoCorporativoList = periodoCronogramaList;

		}

		this.listaBusqueda = craPeriodoCorporativoList;
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
	}

	public void recalcular(String duracion, String codigoPeriodo) {

		// String name= (String) ((UIOutput) vce.getSource()).getValue();
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
		String valor = null;
		int pos = -1;
		for (int i = 0; i < this.listaBusqueda.size(); i++) {
			if (((PeriodoCronograma) listaBusqueda.get(i)).getCodigoPeriodo()
					.equals(codigoPeriodo)) {
				valor = ((PeriodoCronograma) listaBusqueda.get(i))
						.getCodigoPeriodo();
				pos = i;
			}
		}

		Calendar calendar = Calendar.getInstance();

		for (int i = pos; i < this.listaBusqueda.size(); i++) {
			Date fechaTemp = new Date();
			try {
				fechaTemp = formatoDeFecha
						.parse(((PeriodoCronograma) listaBusqueda.get(i))
								.getFechaInicio());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println("Error en parsear la fecha" + i);
			}
			calendar.setTime(fechaTemp);
			calendar.add(Calendar.DAY_OF_YEAR, Integer
					.parseInt(((PeriodoCronograma) listaBusqueda.get(i))
							.getDuracion()) - 1);
			String fechaFin = DateUtil.getDate(calendar.getTime());
			((PeriodoCronograma) listaBusqueda.get(i)).setFechaFin(fechaFin);
			if (i + 1 < listaBusqueda.size()) {

				// tengo que agregar que le sume los dias de duracion del
				// dia siguiente
				try {
					Date fechaIniSig = formatoDeFecha
							.parse(((PeriodoCronograma) listaBusqueda.get(i))
									.getFechaFin());
					calendar.setTime(fechaIniSig);
					calendar.add(Calendar.DATE, 1);

					((PeriodoCronograma) listaBusqueda.get(i + 1))
							.setFechaInicio(DateUtil.getDate(calendar.getTime()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					System.out.println("Error en parsear la fecha" + i);
				}

			}
		}
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);

	}

	public void recalcularFechaInicio(String fechaInicioSelected,
			String fechaFinSelected, String codigoPeriodo)
			throws ParseException {

		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
		String valor = null;
		int pos = -1;
		for (int i = 0; i < this.listaBusqueda.size(); i++) {
			if (((PeriodoCronograma) listaBusqueda.get(i)).getCodigoPeriodo()
					.equals(codigoPeriodo)) {
				valor = ((PeriodoCronograma) listaBusqueda.get(i))
						.getCodigoPeriodo();
				pos = i;
			}
		}

		for (int i = pos; i < this.listaBusqueda.size(); i++) {
			Date dtInicioArray = formatoDeFecha
					.parse(((PeriodoCronograma) listaBusqueda.get(i))
							.getFechaInicio());
			Date dtFinArray = formatoDeFecha
					.parse(((PeriodoCronograma) listaBusqueda.get(i))
							.getFechaFin());

			long dif = dtFinArray.getTime() - dtInicioArray.getTime();

			Long duracion = dif / (1000L * 60L * 60L * 24L);
			((PeriodoCronograma) listaBusqueda.get(i)).setDuracion((duracion
					.toString()));

			if (i + 1 < this.listaBusqueda.size()) {
				// obtengo fecha final actual para sumarle uno y añadirle al
				// siguiente registro
				Calendar c = Calendar.getInstance();
				c.setTime(dtFinArray);
				c.add(Calendar.DATE, 1);
				// le añado uno y seteo la fecha inicio del siguiente registro
				((PeriodoCronograma) listaBusqueda.get(i + 1))
						.setFechaInicio(DateUtil.getDate(c.getTime()));
				// sig como utilitario para rapido acceso
				PeriodoCronograma sig = ((PeriodoCronograma) listaBusqueda
						.get(i + 1));
				c.setTime(formatoDeFecha.parse(sig.getFechaInicio()));
				c.add(Calendar.DATE, Integer.parseInt(sig.getDuracion()));
				((PeriodoCronograma) listaBusqueda.get(i + 1))
						.setFechaFin(DateUtil.getDate(c.getTime()));
			}

		}

	}

	public void recalcularFechaFin(String fechaInicioSelected,
			String fechaFinSelected, String codigoPeriodo)
			throws ParseException {
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
		String valor = null;
		int pos = -1;
		for (int i = 0; i < this.listaBusqueda.size(); i++) {
			if (((PeriodoCronograma) listaBusqueda.get(i)).getCodigoPeriodo()
					.equals(codigoPeriodo)) {
				valor = ((PeriodoCronograma) listaBusqueda.get(i))
						.getCodigoPeriodo();
				pos = i;
			}
		}

		for (int i = pos; i < this.listaBusqueda.size(); i++) {
			Date dtInicioArray = formatoDeFecha
					.parse(((PeriodoCronograma) listaBusqueda.get(i))
							.getFechaInicio());
			Date dtFinArray = formatoDeFecha
					.parse(((PeriodoCronograma) listaBusqueda.get(i))
							.getFechaFin());

			int duracionPeriodo = Integer
					.parseInt(((PeriodoCronograma) listaBusqueda.get(i))
							.getDuracion())-1;
			//aumento la fecha actual sumandole la duracion del periodo
			Calendar cr = Calendar.getInstance();
			cr.setTime(dtInicioArray);
			cr.add(Calendar.DATE, duracionPeriodo);
			((PeriodoCronograma) listaBusqueda.get(i)).setFechaFin(DateUtil
					.getDate(cr.getTime()));

			if (i + 1 < this.listaBusqueda.size()) {
				// obtengo fecha final actual para sumarle uno y añadirle al
				// siguiente registro
				Calendar c = Calendar.getInstance();
				c.setTime(formatoDeFecha.parse(((PeriodoCronograma) listaBusqueda.get(i)).getFechaFin()));
				c.add(Calendar.DAY_OF_YEAR, 1);
				// le añado uno y seteo la fecha inicio del siguiente registro
				((PeriodoCronograma) listaBusqueda.get(i + 1))
						.setFechaInicio(DateUtil.getDate(c.getTime()));
				// sig como utilitario para rapido acceso
				
				PeriodoCronograma sig = ((PeriodoCronograma) listaBusqueda
						.get(i + 1));
				c.setTime(formatoDeFecha.parse(sig.getFechaInicio()));
				c.add(Calendar.DATE, Integer.parseInt(sig.getDuracion()));
				((PeriodoCronograma) listaBusqueda.get(i + 1))
						.setFechaFin(DateUtil.getDate(c.getTime()));
			}

		}

	}

}
