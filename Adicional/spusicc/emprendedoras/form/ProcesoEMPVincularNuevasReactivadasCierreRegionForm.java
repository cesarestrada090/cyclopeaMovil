package biz.belcorp.ssicc.web.spusicc.emprendedoras.form;


import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * @author <a href="mailto:dooliva@belcorp.biz">Dennys Oliva Iriarte</a>
 * 
 * @struts.form name = "procesoEMPVincularNuevasReactivadasCierreRegionForm" extends = "BaseSearchForm"
 */

public class ProcesoEMPVincularNuevasReactivadasCierreRegionForm extends BaseProcesoForm implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	
	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

}