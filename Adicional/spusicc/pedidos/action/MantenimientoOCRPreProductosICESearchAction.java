package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionCabecera;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PreProductosICE;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMPorcentajeComisionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPreProductosICEService;
import biz.belcorp.ssicc.web.form.SistemaForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMPorcentajeComisionForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoOCRPreProductosICEForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoOCRPreProductosICESearchForm;

@SessionScoped
@ManagedBean
public class MantenimientoOCRPreProductosICESearchAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8453156936128240986L;
	private String codigoPais;
	private boolean viewEdit;
	private List ocrPreProductosIceList;

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoOCRPreProductosICEList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoOCRPreProductosICEForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoOCRPreProductosICEForm form = new MantenimientoOCRPreProductosICEForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoOCRPreProductosICEForm f = (MantenimientoOCRPreProductosICEForm) this.formBusqueda;
		if (f.getFechaCargaD() != null)
			f.setFechaCarga(DateUtil.convertDateToString(f.getFechaCargaD()));
		MantenimientoOCRPreProductosICEService service = (MantenimientoOCRPreProductosICEService) getBean("spusicc.pedidos.mantenimientoOCRPreProductosICEService");

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoSAP", f.getCodigoSAP());
		criteria.put("fechaCarga", f.getFechaCarga());

		List resultado = (List) service.getListaProductosICE(criteria);
		this.ocrPreProductosIceList = resultado;
		return resultado;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		PreProductosICE p = (PreProductosICE) this.beanRegistroSeleccionado;
		MantenimientoOCRPreProductosICEService service = (MantenimientoOCRPreProductosICEService) getBean("spusicc.pedidos.mantenimientoOCRPreProductosICEService");
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoSAP", p.getCodigoSAP());
		criteria.put("fechaCarga", p.getFechaCarga());
		criteria.put("oidImpuesto", p.getOidImpuesto());

		service.deleteProductosICE(criteria);
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}
		MantenimientoOCRPreProductosICESearchForm f = (MantenimientoOCRPreProductosICESearchForm) this.formMantenimiento;
		MantenimientoOCRPreProductosICEService service = (MantenimientoOCRPreProductosICEService) getBean("spusicc.pedidos.mantenimientoOCRPreProductosICEService");
		AjaxService ajax = (AjaxService) getBean("ajaxService");

		f.setCodigoPais(codigoPais);

		boolean bGrabarCab = false;
		try {

			if (!f.isNewRecord()) {
				Map criteria = new HashMap();
				criteria.put("oidImpuesto", f.getOidImpuesto());
				criteria.put("valorImpuesto", f.getValorImpuesto());

				service.updateProductoICE(criteria);

				bGrabarCab = true;
			} else {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA CREACION");
				}
				String codSap = ajax.validarCodigoSAP(codigoPais,
						f.getCodigoSAP());
				if (StringUtils.isBlank(codSap)) {
					throw new Exception("el código de SAP no existe");
				}
				f.setFechaCarga(DateUtil.convertDateToString(f.getFechaCargaD()));
				Map criteria = new HashMap();
				criteria.put("codigoSAP", f.getCodigoSAP());
				criteria.put("valorImpuesto", f.getValorImpuesto());
				criteria.put("fechaCarga", f.getFechaCarga());
				List lista = service.getListaProductosICE(criteria);
				if (lista != null && lista.size() > 0) {
					throw new Exception(
							this.getResourceMessage("mantenimientoOCRPreProductosICESearchForm.error.codigoProducto.unique"));

				}

				service.insertProductoICE(criteria);
				bGrabarCab = true;
			}

		} catch (Exception e) {

			bGrabarCab = false;
			throw new Exception(e.getMessage());
		}
		return bGrabarCab;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoOCRPreProductosICESearchForm f = new MantenimientoOCRPreProductosICESearchForm();
		f.setCodigoPais(codigoPais);
		if (this.accion.equals(this.ACCION_NUEVO)) {
			f.setNewRecord(true);
			setViewEdit(false);
		} else if (this.accion.equals(this.ACCION_MODIFICAR)) {
			PreProductosICE preProductosICE = (PreProductosICE) this.beanRegistroSeleccionado;
			BeanUtils.copyProperties(f, preProductosICE);
			f.setNewRecord(false);
			setViewEdit(true);
			this.mostrarBotonSave = true;
		} else if (this.accion.equals(this.ACCION_CONSULTAR)) {
			PreProductosICE preProductosICE = (PreProductosICE) this.beanRegistroSeleccionado;
			BeanUtils.copyProperties(f, preProductosICE);
			f.setNewRecord(false);
			setViewEdit(false);
			this.mostrarBotonSave = false;
		}
		return f;
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
		MantenimientoOCRPreProductosICESearchForm sistemaForm = (MantenimientoOCRPreProductosICESearchForm) this.formMantenimiento;
		boolean isNew = sistemaForm.isNewRecord();
		if (isNew) {
			return "mantenimientoOCRProductoICEForm.add";
		} else {
			return "mantenimientoOCRProductoICEForm.updated";
		}
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub

		codigoPais = this.mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo();
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the ocrPreProductosIceList
	 */
	public List getOcrPreProductosIceList() {
		return ocrPreProductosIceList;
	}

	/**
	 * @param ocrPreProductosIceList
	 *            the ocrPreProductosIceList to set
	 */
	public void setOcrPreProductosIceList(List ocrPreProductosIceList) {
		this.ocrPreProductosIceList = ocrPreProductosIceList;
	}

	/**
	 * @return the viewEdit
	 */
	public boolean isViewEdit() {
		return viewEdit;
	}

	/**
	 * @param viewEdit
	 *            the viewEdit to set
	 */
	public void setViewEdit(boolean viewEdit) {
		this.viewEdit = viewEdit;
	}

}
