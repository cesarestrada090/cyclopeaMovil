package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.form.SistemaForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOIngresoCuponForm;

@ManagedBean
@SessionScoped
public class MantenimientoSTOIngresoCuponAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7766588187031148487L;
	private Date fechaRegistroD;
	private String codigoPeriodo;
	private Integer longitudCampoClientes;
	private String oidPais;

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoSTOIngresoCuponForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		Map criteria = new HashMap();
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		MantenimientoSTOIngresoCuponForm f = (MantenimientoSTOIngresoCuponForm) this.formMantenimiento;
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		try {
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codCliente", f.getCodCliente());
			criteria.put("valorPagado", f.getValorPagado());
			criteria.put("fechaRegistro", f.getFechaRegistro());
			criteria.put("codigoUsuario", usuario.getLogin());
			criteria.put("codigoDocumento", Constants.STO_TIPO_DOCUMENTO_CUP);
			criteria.put("caracterDocumento",
					Constants.STO_CODIGO_NUMERO_DOCUMENTO_CUPON);
			criteria.put("codigoPeriodo", f.getCodigoPeriodo());
			String codigoCompania = procesoSTOEjecucionValidacionesService
					.getCodigoCompania(criteria);
			String numLoteSTO = interfazSiCCService.getNumLoteSTO(criteria);
			String secuenciaCupon = procesoSTOEjecucionValidacionesService
					.getSecuenciaCuponPagoSTONextValue();
			criteria.put("secuenciaCupon", secuenciaCupon);
			String secuenciaSTO = procesoSTOEjecucionValidacionesService
					.getSecuenciaSTONextValue();
			criteria.put("secuenciaSTO", secuenciaSTO);
			criteria.put("numLoteSTO", numLoteSTO);
			criteria.put("codigoCompania", codigoCompania);
			if (f.getIndicadorRechazo().compareToIgnoreCase("S") == 0) {

				criteria.put("indicadorRechazo", "1");
			} else {

				criteria.put("indicadorRechazo", "0");
			}

			procesoSTOEjecucionValidacionesService.insertCuponPago(criteria);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		f.setCodCliente("");
		f.setValorPagado("");
		f.setIndicadorRechazo("N");

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
		this.mostrarBotonEliminar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonSalir = false;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoPais = pais.getCodigo();

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", codigoPais);

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		String oidPais = reporteService.getOidString("getOidPaisByCodigoPais",
				criteriaOperacion);

		setOidPais(String.valueOf(oidPais));

		// Seteo la longitud del codigo de consultora de acuerdo al pais
		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");

		this.longitudCampoClientes = clienteService
				.getTamanhoNumeroCliente(codigoPais);
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", codigoPais);
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																		// Campanha
																		// Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																			// Campanha
																			// activa
																			// q
																			// se
																			// procesa
																			// actualmente
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteriaPeriodo);
		setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		setFechaRegistroD(new Date());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction
	 * #devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		SistemaForm sistemaForm = (SistemaForm) this.formMantenimiento;
		boolean isNew = sistemaForm.isNewRecord();
		if (isNew) {
			return "mantenimientoSTOCuponForm.insert";
		} else {
			return "mantenimientoSTOCuponForm.insert";
		}
	}

	/**
	 * @return the fechaRegistroD
	 */
	public Date getFechaRegistroD() {
		return fechaRegistroD;
	}

	/**
	 * @param fechaRegistroD
	 *            the fechaRegistroD to set
	 */
	public void setFechaRegistroD(Date fechaRegistroD) {
		this.fechaRegistroD = fechaRegistroD;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 *            the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the longitudCampoClientes
	 */
	public Integer getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	/**
	 * @param longitudCampoClientes
	 *            the longitudCampoClientes to set
	 */
	public void setLongitudCampoClientes(Integer longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}

	/**
	 * @return the oidPais
	 */
	public String getOidPais() {
		return oidPais;
	}

	/**
	 * @param oidPais
	 *            the oidPais to set
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}

}
