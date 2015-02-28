/*
 * 
 */
package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseCargaArchivoAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseCargaArchivoForm;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.ProcesoCCCCargarPagosBancariosMasivosForm;



@ManagedBean
@SessionScoped
public class ProcesoCCCCargarPagosBancariosMasivosAction extends BaseCargaArchivoAbstractAction {

	private static final long serialVersionUID = 1L;
	
	private List siccCuentaCorrienteList = new ArrayList();
	
	@Override
	protected BaseCargaArchivoForm devuelveFormCarga() throws Exception {
		ProcesoCCCCargarPagosBancariosMasivosForm form = new ProcesoCCCCargarPagosBancariosMasivosForm();
		return form;
	}

	

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setViewAtributes");
		}	
		ProcesoCCCCargarPagosBancariosMasivosForm f = (ProcesoCCCCargarPagosBancariosMasivosForm)this.formCargaArchivo;
		
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		
		//Map para almacenar los parametros
		Map criteria = new HashMap();
																
		//Obteniedo el listado de las Cuentas Corrientes Bancarias	        		
		criteria.put("codigoPais", pais.getCodigo());
        ConsultaCCCGenericoService serviceCCC = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");	        
		this.setSiccCuentaCorrienteList(serviceCCC.getCuentasCorrientesBancariasList(criteria));
		
		
	}
	

	@Override
	public BaseCargaArchivoForm setUploadAttibuttes(Map<String, Object> criteria) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	protected List setValidarAttributes(Map<String, Object> datos) 	throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
    protected void setProcesarAttributes(Map<String, Object> datos) throws Exception {
		
	}
	
	
	
    
    
    
	public List getSiccCuentaCorrienteList() {
		return siccCuentaCorrienteList;
	}

	public void setSiccCuentaCorrienteList(List siccCuentaCorrienteList) {
		this.siccCuentaCorrienteList = siccCuentaCorrienteList;
	}

}