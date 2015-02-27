package biz.belcorp.ssicc.web.spusicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.Estimado;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoDATEstimadosSearchForm extends BaseSearchForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2952118645421784563L;
	private String codigoPais;

	private String codigoMarca;
	
	private String codigoCanal;
	
	private String codigoPeriodo;
	
	private String codigoRangoPeriodo;
	
	private String codigoAnio;
	
	private String codigoRegion;
	
	private String codigoZona;
		
	private String codigoSeccion;

	private String nombreMarca;
	
	private String nombreCanal;
	
	private String nombreRangoPeriodo;
	
	private String nombreRegion;
	
	private String nombreZona;
		
	private String nombreSeccion;
	
	private String indicadorModificacion;

	
	public MantenimientoDATEstimadosSearchForm() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
		this.codigoAnio = sdf.format(new Date(System.currentTimeMillis()))
				.substring(6, 10);
		log.debug("En el form hallando el codigoAnio:" + this.codigoAnio);
	}

	public String getCodigoAnio() {
		return codigoAnio;
	}

	/**
	 * @param codigoanio
	 *            The codigoanio to set.
	 * 
	 * @struts.validator type = "required"
	 */

	public void setCodigoAnio(String codigoAnio) {
		this.codigoAnio = codigoAnio;
	}

	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoalmacen
	 *            The codigocanal to set.
	 * 
	 * @struts.validator type = "required"
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoalmacen
	 *            The codigopais to set.
	 * 
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoRangoPeriodo() {
		return codigoRangoPeriodo;
	}

	/**
	 * @param codigorangoperiodo
	 *            The codigorangoperiodo to set.
	 * 
	 * @struts.validator type = "required"
	 */
	public void setCodigoRangoPeriodo(String codigoRangoPeriodo) {
		this.codigoRangoPeriodo = codigoRangoPeriodo;
	}

	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoregion
	 *            The codigoregion to set.
	 * 
	 * @struts.validator type = "required"
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoalmacen
	 *            The codigozona to set.
	 * 
	 * @struts.validator type = "required"
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	private String c1 = "";

	private String c2 = "";

	private String c3 = "";

	private String c4 = "";

	private String c5 = "";

	private String c6 = "";


	// public listaACampos

	public ArrayList camposALista(List lista) {
		
		ArrayList listaResult = new ArrayList();
		Estimado estimado=null;
		if (lista.size()>0) {
			estimado = (Estimado) lista.get(0);
		
			if (!estimado.getIndModiPedidosObjetivo().equals(Constants.INDICADOR_OBJ_PEDIDO_NOGENERADO)){
				this.campoAEstimado(estimado, c1f1, c1f2, c1f3, c1f4, c1f5, c1f6, c1f7, c1f8, c1f9, c1f10, c1f11, c1f12);
				listaResult.add(estimado);
			}
		}
		
		if (lista.size()>1) {
			estimado = (Estimado) lista.get(1);
			if (!estimado.getIndModiPedidosObjetivo().equals(Constants.INDICADOR_OBJ_PEDIDO_NOGENERADO)){
				this.campoAEstimado(estimado, c2f1, c2f2, c2f3, c2f4, c2f5, c2f6, c2f7, c2f8, c2f9, c2f10, c2f11, c2f12);
				listaResult.add(estimado);
			}
		}
		
		if (lista.size()>2) {
			estimado = (Estimado) lista.get(2);
			if (!estimado.getIndModiPedidosObjetivo().equals(Constants.INDICADOR_OBJ_PEDIDO_NOGENERADO)){
				this.campoAEstimado(estimado, c3f1, c3f2, c3f3, c3f4, c3f5, c3f6, c3f7, c3f8, c3f9, c3f10, c3f11, c3f12);
				listaResult.add(estimado);
			}
		}
		
		if (lista.size()>3) {
			estimado = (Estimado) lista.get(3);
			if (!estimado.getIndModiPedidosObjetivo().equals(Constants.INDICADOR_OBJ_PEDIDO_NOGENERADO)){
				this.campoAEstimado(estimado, c4f1, c4f2, c4f3, c4f4, c4f5, c4f6, c4f7, c4f8, c4f9, c4f10, c4f11, c4f12);
				listaResult.add(estimado);
			}
		}
		if (lista.size()>4) {
			estimado = (Estimado) lista.get(4);
			if (!estimado.getIndModiPedidosObjetivo().equals(Constants.INDICADOR_OBJ_PEDIDO_NOGENERADO)){
				this.campoAEstimado(estimado, c5f1, c5f2, c5f3, c5f4, c5f5, c5f6, c5f7, c5f8, c5f9, c5f10, c5f11, c5f12);
				listaResult.add(estimado);
			}
		}
		if (lista.size()>5) {
			estimado = (Estimado) lista.get(5);
			if (!estimado.getIndModiPedidosObjetivo().equals(Constants.INDICADOR_OBJ_PEDIDO_NOGENERADO)){
				this.campoAEstimado(estimado, c6f1, c6f2, c6f3, c6f4, c6f5, c6f6, c6f7, c6f8, c6f9, c6f10, c6f11, c6f12);
				listaResult.add(estimado);
			}
		}
		return listaResult;

	}
	
	
	private void campoAEstimado(Estimado estimado, String cf1, String cf2,String cf3, String cf4, 
            String cf5, String cf6, String cf7,String cf8, 
            String cf9, String cf10, String cf11, String cf12){
		
		Long estimadoVendidas      =    null;   
		Double estimadoVentaNeta   =    null; 
		Long estimadoPedidos       =    null;   
		Long estimadoClientes      =    null;   
		Long estimadoActivas       =    null;   
		Long estimadoIngresos      =    null;   
		Long estimadoEgresos       =    null;   
		Long estimadoReingresos    =    null;   
		Long objetivoPedidos       =    null;   
		Long objetivoActivas       =    null;   
		Double realTCPromedio      =    null; 
		Double estimadoTCPromedio  =    null;
		
		if (!cf1.equals("") ) estimadoVendidas = (new Long(cf1));
		if (!cf2 .equals("") ) estimadoVentaNeta = (new Double(cf2));
		if (!cf3 .equals("") ) estimadoPedidos = (new Long(cf3));
		if (!cf4 .equals("") ) estimadoClientes = (new Long(cf4));
		if (!cf5 .equals("") ) estimadoActivas = (new Long(cf5));
		if (!cf6 .equals("") ) estimadoIngresos = (new Long(cf6));
		if (!cf7 .equals("") ) estimadoEgresos = (new Long(cf7));
		if (!cf8 .equals("") ) estimadoReingresos = (new Long(cf8));
		if (!cf9 .equals("") ) objetivoPedidos = (new Long(cf9));
		if (!cf10 .equals("") ) objetivoActivas = (new Long(cf10));
		if (!cf11 .equals("") )	realTCPromedio = (new Double(cf11));
		if (!cf12 .equals("") )	estimadoTCPromedio = (new Double(cf12));
		
		estimado.setEstimadoVendidas(estimadoVendidas);
		estimado.setEstimadoVentaNeta(estimadoVentaNeta);
		estimado.setEstimadoPedidos(estimadoPedidos);
		estimado.setEstimadoClientes(estimadoClientes);
		estimado.setEstimadoActivas(estimadoActivas);
		estimado.setEstimadoIngresos(estimadoIngresos);
		estimado.setEstimadoEgresos(estimadoEgresos);
		estimado.setEstimadoReingresos(estimadoReingresos);
		estimado.setObjetivoPedidos(objetivoPedidos);
		estimado.setObjetivoActivas(objetivoActivas);
		estimado.setRealTCPromedio(realTCPromedio);
		estimado.setEstimadoTCPromedio(estimadoTCPromedio);
		
		
	}

	public void listaACampos(ArrayList lista) {
		this.inicializaCampos(); 
		Estimado estimado= new Estimado();
		boolean  indEst  = false;
		
		if (lista.size()>0){
			estimado = (Estimado) lista.get(0);
			if (estimado.getEstimadoVendidas()!=null)   c1f1  = String.valueOf(estimado.getEstimadoVendidas());
			if (estimado.getEstimadoVentaNeta()!=null)  c1f2  = String.valueOf(estimado.getEstimadoVentaNeta());
			if (estimado.getEstimadoPedidos()!=null)    c1f3  = String.valueOf(estimado.getEstimadoPedidos());
			if (estimado.getEstimadoClientes()!=null)   c1f4  = String.valueOf(estimado.getEstimadoClientes());
			if (estimado.getEstimadoActivas()!=null)    c1f5  = String.valueOf(estimado.getEstimadoActivas());
			if (estimado.getEstimadoIngresos()!=null)   c1f6  = String.valueOf(estimado.getEstimadoIngresos());
			if (estimado.getEstimadoEgresos()!=null)    c1f7  = String.valueOf(estimado.getEstimadoEgresos());
			if (estimado.getEstimadoReingresos()!=null) c1f8  = String.valueOf(estimado.getEstimadoReingresos());
			if (estimado.getObjetivoPedidos()!=null)    c1f9  = String.valueOf(estimado.getObjetivoPedidos());
			if (estimado.getObjetivoActivas()!=null)    c1f10 = String.valueOf(estimado.getObjetivoActivas());
			if (estimado.getRealTCPromedio()!=null)     c1f11 = String.valueOf(estimado.getRealTCPromedio());
			if (estimado.getEstimadoTCPromedio()!=null) c1f12 = String.valueOf(estimado.getEstimadoTCPromedio());
			c1 = estimado.getCodigoPeriodo();
			if (indEst || estimado.getIndModiPedidosObjetivo().equals(Constants.INDICADOR_OBJ_PEDIDO_MODIFICADO))  indEst=true;
				
		}
		
		if (lista.size()>1){
		estimado = (Estimado) lista.get(1);
		if (estimado.getEstimadoVendidas()!=null)   c2f1  = String.valueOf(estimado.getEstimadoVendidas());
		if (estimado.getEstimadoVentaNeta()!=null)  c2f2  = String.valueOf(estimado.getEstimadoVentaNeta());
		if (estimado.getEstimadoPedidos()!=null)    c2f3  = String.valueOf(estimado.getEstimadoPedidos());
		if (estimado.getEstimadoClientes()!=null)   c2f4  = String.valueOf(estimado.getEstimadoClientes());
		if (estimado.getEstimadoActivas()!=null)    c2f5  = String.valueOf(estimado.getEstimadoActivas());
		if (estimado.getEstimadoIngresos()!=null)   c2f6  = String.valueOf(estimado.getEstimadoIngresos());
		if (estimado.getEstimadoEgresos()!=null)    c2f7  = String.valueOf(estimado.getEstimadoEgresos());
		if (estimado.getEstimadoReingresos()!=null) c2f8  = String.valueOf(estimado.getEstimadoReingresos());
		if (estimado.getObjetivoPedidos()!=null)    c2f9  = String.valueOf(estimado.getObjetivoPedidos());
		if (estimado.getObjetivoActivas()!=null)    c2f10 = String.valueOf(estimado.getObjetivoActivas());
		if (estimado.getRealTCPromedio()!=null)     c2f11 = String.valueOf(estimado.getRealTCPromedio());
		if (estimado.getEstimadoTCPromedio()!=null) c2f12 = String.valueOf(estimado.getEstimadoTCPromedio());
		c2 = estimado.getCodigoPeriodo();
		if (indEst || estimado.getIndModiPedidosObjetivo().equals(Constants.INDICADOR_OBJ_PEDIDO_MODIFICADO))  indEst=true;
		}
		
		if (lista.size()>2){
		estimado = (Estimado) lista.get(2);
		if (estimado.getEstimadoVendidas()!=null)   c3f1  = String.valueOf(estimado.getEstimadoVendidas());
		if (estimado.getEstimadoVentaNeta()!=null)  c3f2  = String.valueOf(estimado.getEstimadoVentaNeta());
		if (estimado.getEstimadoPedidos()!=null)    c3f3  = String.valueOf(estimado.getEstimadoPedidos());
		if (estimado.getEstimadoClientes()!=null)   c3f4  = String.valueOf(estimado.getEstimadoClientes());
		if (estimado.getEstimadoActivas()!=null)    c3f5  = String.valueOf(estimado.getEstimadoActivas());
		if (estimado.getEstimadoIngresos()!=null)   c3f6  = String.valueOf(estimado.getEstimadoIngresos());
		if (estimado.getEstimadoEgresos()!=null)    c3f7  = String.valueOf(estimado.getEstimadoEgresos());
		if (estimado.getEstimadoReingresos()!=null) c3f8  = String.valueOf(estimado.getEstimadoReingresos());
		if (estimado.getObjetivoPedidos()!=null)    c3f9  = String.valueOf(estimado.getObjetivoPedidos());
		if (estimado.getObjetivoActivas()!=null)    c3f10 = String.valueOf(estimado.getObjetivoActivas());
		if (estimado.getRealTCPromedio()!=null)     c3f11 = String.valueOf(estimado.getRealTCPromedio());
		if (estimado.getEstimadoTCPromedio()!=null) c3f12 = String.valueOf(estimado.getEstimadoTCPromedio());
		c3 = estimado.getCodigoPeriodo();
		if (indEst || estimado.getIndModiPedidosObjetivo().equals(Constants.INDICADOR_OBJ_PEDIDO_MODIFICADO))  indEst=true;
		}
		
		if (lista.size()>3){
		estimado = (Estimado) lista.get(3);
		if (estimado.getEstimadoVendidas()!=null)   c4f1  = String.valueOf(estimado.getEstimadoVendidas());
		if (estimado.getEstimadoVentaNeta()!=null)  c4f2  = String.valueOf(estimado.getEstimadoVentaNeta());
		if (estimado.getEstimadoPedidos()!=null)    c4f3  = String.valueOf(estimado.getEstimadoPedidos());
		if (estimado.getEstimadoClientes()!=null)   c4f4  = String.valueOf(estimado.getEstimadoClientes());
		if (estimado.getEstimadoActivas()!=null)    c4f5  = String.valueOf(estimado.getEstimadoActivas());
		if (estimado.getEstimadoIngresos()!=null)   c4f6  = String.valueOf(estimado.getEstimadoIngresos());
		if (estimado.getEstimadoEgresos()!=null)    c4f7  = String.valueOf(estimado.getEstimadoEgresos());
		if (estimado.getEstimadoReingresos()!=null) c4f8  = String.valueOf(estimado.getEstimadoReingresos());
		if (estimado.getObjetivoPedidos()!=null)    c4f9  = String.valueOf(estimado.getObjetivoPedidos());
		if (estimado.getObjetivoActivas()!=null)    c4f10 = String.valueOf(estimado.getObjetivoActivas());
		if (estimado.getRealTCPromedio()!=null)     c4f11 = String.valueOf(estimado.getRealTCPromedio());
		if (estimado.getEstimadoTCPromedio()!=null) c4f12 = String.valueOf(estimado.getEstimadoTCPromedio());
		c4 = estimado.getCodigoPeriodo();
		if (indEst || estimado.getIndModiPedidosObjetivo().equals(Constants.INDICADOR_OBJ_PEDIDO_MODIFICADO))  indEst=true;
		}
		
		if (lista.size()>4){
		estimado = (Estimado) lista.get(4);
		if (estimado.getEstimadoVendidas()!=null)   c5f1  = String.valueOf(estimado.getEstimadoVendidas());
		if (estimado.getEstimadoVentaNeta()!=null)  c5f2  = String.valueOf(estimado.getEstimadoVentaNeta());
		if (estimado.getEstimadoPedidos()!=null)    c5f3  = String.valueOf(estimado.getEstimadoPedidos());
		if (estimado.getEstimadoClientes()!=null)   c5f4  = String.valueOf(estimado.getEstimadoClientes());
		if (estimado.getEstimadoActivas()!=null)    c5f5  = String.valueOf(estimado.getEstimadoActivas());
		if (estimado.getEstimadoIngresos()!=null)   c5f6  = String.valueOf(estimado.getEstimadoIngresos());
		if (estimado.getEstimadoEgresos()!=null)    c5f7  = String.valueOf(estimado.getEstimadoEgresos());
		if (estimado.getEstimadoReingresos()!=null) c5f8  = String.valueOf(estimado.getEstimadoReingresos());
		if (estimado.getObjetivoPedidos()!=null)    c5f9  = String.valueOf(estimado.getObjetivoPedidos());
		if (estimado.getObjetivoActivas()!=null)    c5f10 = String.valueOf(estimado.getObjetivoActivas());
		if (estimado.getRealTCPromedio()!=null)     c5f11 = String.valueOf(estimado.getRealTCPromedio());
		if (estimado.getEstimadoTCPromedio()!=null) c5f12 = String.valueOf(estimado.getEstimadoTCPromedio());
		c5 = estimado.getCodigoPeriodo();
		if (indEst || estimado.getIndModiPedidosObjetivo().equals(Constants.INDICADOR_OBJ_PEDIDO_MODIFICADO))  indEst=true;
		}
		
		if (lista.size()>5){
		estimado = (Estimado) lista.get(5);
		if (estimado.getEstimadoVendidas()!=null)   c6f1  = String.valueOf(estimado.getEstimadoVendidas());
		if (estimado.getEstimadoVentaNeta()!=null)  c6f2  = String.valueOf(estimado.getEstimadoVentaNeta());
		if (estimado.getEstimadoPedidos()!=null)    c6f3  = String.valueOf(estimado.getEstimadoPedidos());
		if (estimado.getEstimadoClientes()!=null)   c6f4  = String.valueOf(estimado.getEstimadoClientes());
		if (estimado.getEstimadoActivas()!=null)    c6f5  = String.valueOf(estimado.getEstimadoActivas());
		if (estimado.getEstimadoIngresos()!=null)   c6f6  = String.valueOf(estimado.getEstimadoIngresos());
		if (estimado.getEstimadoEgresos()!=null)    c6f7  = String.valueOf(estimado.getEstimadoEgresos());
		if (estimado.getEstimadoReingresos()!=null) c6f8  = String.valueOf(estimado.getEstimadoReingresos());
		if (estimado.getObjetivoPedidos()!=null)    c6f9  = String.valueOf(estimado.getObjetivoPedidos());
		if (estimado.getObjetivoActivas()!=null)    c6f10 = String.valueOf(estimado.getObjetivoActivas());
		if (estimado.getRealTCPromedio()!=null)     c6f11 = String.valueOf(estimado.getRealTCPromedio());
		if (estimado.getEstimadoTCPromedio()!=null) c6f12 = String.valueOf(estimado.getEstimadoTCPromedio());
		c6 = estimado.getCodigoPeriodo();
		if (indEst || estimado.getIndModiPedidosObjetivo().equals(Constants.INDICADOR_OBJ_PEDIDO_MODIFICADO))  indEst=true;
		}
		if (indEst) indicadorModificacion=Constants.INDICADOR_OBJ_PEDIDO_MODIFICADO;	

	}
	
	private void inicializaCampos() {
		c1f1  ="";
		c1f2  ="";
		c1f3  ="";
		c1f4  ="";
		c1f5  ="";
		c1f6  ="";
		c1f7  ="";
		c1f8  ="";
		c1f9  ="";
		c1f10 ="";
		c1f11 ="";
		c1f12 ="";
	
		c2f1  ="";
		c2f2  ="";
		c2f3  ="";
		c2f4  ="";
		c2f5  ="";
		c2f6  ="";
		c2f7  ="";
		c2f8  ="";
		c2f9  ="";
		c2f10 ="";
		c2f11 ="";
		c2f12 ="";
		
		c3f1  ="";
		c3f2  ="";
		c3f3  ="";
		c3f4  ="";
		c3f5  ="";
		c3f6  ="";
		c3f7  ="";
		c3f8  ="";
		c3f9  ="";
		c3f10 ="";
		c3f11 ="";
		c3f12 ="";
	
		c4f1  ="";
		c4f2  ="";
		c4f3  ="";
		c4f4  ="";
		c4f5  ="";
		c4f6  ="";
		c4f7  ="";
		c4f8  ="";
		c4f9  ="";
		c4f10 ="";
		c4f11 ="";
		c4f12 ="";

		c5f1  ="";
		c5f2  ="";
		c5f3  ="";
		c5f4  ="";
		c5f5  ="";
		c5f6  ="";
		c5f7  ="";
		c5f8  ="";
		c5f9  ="";
		c5f10 ="";
		c5f11 ="";
		c5f12 ="";
		  
		c6f1  ="";
		c6f2  ="";
		c6f3  ="";
		c6f4  ="";
		c6f5  ="";
		c6f6  ="";
		c6f7  ="";
		c6f8  ="";
		c6f9  ="";
		c6f10 ="";
		c6f11 ="";
		c6f12 ="";
	}

	/**
	 * @return
	 */
	public String getC1() {
		return c1;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	public String getC2() {
		return c2;
	}

	public void setC2(String c2) {
		this.c2 = c2;
	}

	public String getC3() {
		return c3;
	}

	public void setC3(String c3) {
		this.c3 = c3;
	}

	public String getC4() {
		return c4;
	}

	public void setC4(String c4) {
		this.c4 = c4;
	}

	public String getC5() {
		return c5;
	}

	public void setC5(String c5) {
		this.c5 = c5;
	}

	public String getC6() {
		return c6;
	}

	public void setC6(String c6) {
		this.c6 = c6;
	}

	/**
	 * @return Returns the nombreCanal.
	 */
	public String getNombreCanal() {
		return nombreCanal;
	}

	/**
	 * @param nombreCanal
	 *            The nombreCanal to set.
	 */
	public void setNombreCanal(String nombreCanal) {
		this.nombreCanal = nombreCanal;
	}

	/**
	 * @return Returns the nombreRangoPeriodo.
	 */
	public String getNombreRangoPeriodo() {
		return nombreRangoPeriodo;
	}

	/**
	 * @param nombreRangoPeriodo
	 *            The nombreRangoPeriodo to set.
	 */
	public void setNombreRangoPeriodo(String nombreRangoPeriodo) {
		this.nombreRangoPeriodo = nombreRangoPeriodo;
	}

	/**
	 * @return Returns the nombreRegion.
	 */
	public String getNombreRegion() {
		return nombreRegion;
	}

	/**
	 * @param nombreRegion
	 *            The nombreRegion to set.
	 */
	public void setNombreRegion(String nombreRegion) {
		this.nombreRegion = nombreRegion;
	}

	/**
	 * @return Returns the nombreZona.
	 */
	public String getNombreZona() {
		return nombreZona;
	}

	/**
	 * @param nombreZona
	 *            The nombreZona to set.
	 */
	public void setNombreZona(String nombreZona) {
		this.nombreZona = nombreZona;
	}

	/**
	 * @return Returns the codigoMarca.
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca
	 *            The codigoMarca to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return Returns the nombreMarca.
	 */
	public String getNombreMarca() {
		return nombreMarca;
	}

	/**
	 * @param nombreMarca
	 *            The nombreMarca to set.
	 */
	public void setNombreMarca(String nombreMarca) {
		this.nombreMarca = nombreMarca;
	}

	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 *            The codigoPeriodo to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
	/**
	 * @return Returns the codigoSeccion.
	 */
	public String getCodigoSeccion() {
		return codigoSeccion;
	}
	
	/**
	 * @param codigoSeccion
	 *            The codigoSeccion to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}
	
	/**
	 * @return Returns the nombreSeccion.
	 */
	public String getNombreSeccion() {
		return nombreSeccion;
	}
	/**
	 * @param nombreSeccion
	 *            The nombreSeccion to set.
	 * 
	 */
	public void setNombreSeccion(String nombreSeccion) {
		this.nombreSeccion = nombreSeccion;
	}
	
	private String c1f1  = null;
	private String c1f2  = null;
	private String c1f3  = null;
	private String c1f4  = null;
	private String c1f5  = null;
	private String c1f6  = null;
	private String c1f7  = null;
	private String c1f8  = null;
	private String c1f9  = null;
	private String c1f10 = null;
	private String c1f11 = null;
	private String c1f12 = null;

	private String c2f1  = null;
	private String c2f2  = null;
	private String c2f3  = null;
	private String c2f4  = null;
	private String c2f5  = null;
	private String c2f6  = null;
	private String c2f7  = null;
	private String c2f8  = null;
	private String c2f9  = null;
	private String c2f10 = null;
	private String c2f11 = null;
	private String c2f12 = null;

	private String c3f1  = null;
	private String c3f2  = null;
	private String c3f3  = null;
	private String c3f4  = null;
	private String c3f5  = null;
	private String c3f6  = null;
	private String c3f7  = null;
	private String c3f8  = null;
	private String c3f9  = null;
	private String c3f10 = null;
	private String c3f11 = null;
	private String c3f12 = null;

	private String c4f1  = null;
	private String c4f2  = null;
	private String c4f3  = null;
	private String c4f4  = null;
	private String c4f5  = null;
	private String c4f6  = null;
	private String c4f7  = null;
	private String c4f8  = null;
	private String c4f9  = null;
	private String c4f10 = null;
	private String c4f11 = null;
	private String c4f12 = null;

	private String c5f1  = null;
	private String c5f2  = null;
	private String c5f3  = null;
	private String c5f4  = null;
	private String c5f5  = null;
	private String c5f6  = null;
	private String c5f7  = null;
	private String c5f8  = null;
	private String c5f9  = null;
	private String c5f10 = null;
	private String c5f11 = null;
	private String c5f12 = null;

	private String c6f1  = null;
	private String c6f2  = null;
	private String c6f3  = null;
	private String c6f4  = null;
	private String c6f5  = null;
	private String c6f6  = null;
	private String c6f7  = null;
	private String c6f8  = null;
	private String c6f9  = null;
	private String c6f10 = null;
	private String c6f11 = null;
	private String c6f12 = null;



	public String getC1f1() {
		return c1f1;
	}

	public void setC1f1(String c1f1) {
		this.c1f1 = c1f1;
	}

	public String getC1f10() {
		return c1f10;
	}

	public void setC1f10(String c1f10) {
		this.c1f10 = c1f10;
	}

	public String getC1f11() {
		return c1f11;
	}

	public void setC1f11(String c1f11) {
		this.c1f11 = c1f11;
	}

	public String getC1f12() {
		return c1f12;
	}

	public void setC1f12(String c1f12) {
		this.c1f12 = c1f12;
	}

	public String getC1f2() {
		return c1f2;
	}

	public void setC1f2(String c1f2) {
		this.c1f2 = c1f2;
	}

	public String getC1f3() {
		return c1f3;
	}

	public void setC1f3(String c1f3) {
		this.c1f3 = c1f3;
	}

	public String getC1f4() {
		return c1f4;
	}

	public void setC1f4(String c1f4) {
		this.c1f4 = c1f4;
	}

	public String getC1f5() {
		return c1f5;
	}

	public void setC1f5(String c1f5) {
		this.c1f5 = c1f5;
	}

	public String getC1f6() {
		return c1f6;
	}

	public void setC1f6(String c1f6) {
		this.c1f6 = c1f6;
	}

	public String getC1f7() {
		return c1f7;
	}

	public void setC1f7(String c1f7) {
		this.c1f7 = c1f7;
	}

	public String getC1f8() {
		return c1f8;
	}

	public void setC1f8(String c1f8) {
		this.c1f8 = c1f8;
	}

	public String getC1f9() {
		return c1f9;
	}

	public void setC1f9(String c1f9) {
		this.c1f9 = c1f9;
	}

	public String getC2f1() {
		return c2f1;
	}

	public void setC2f1(String c2f1) {
		this.c2f1 = c2f1;
	}

	public String getC2f10() {
		return c2f10;
	}

	public void setC2f10(String c2f10) {
		this.c2f10 = c2f10;
	}

	public String getC2f11() {
		return c2f11;
	}

	public void setC2f11(String c2f11) {
		this.c2f11 = c2f11;
	}

	public String getC2f12() {
		return c2f12;
	}

	public void setC2f12(String c2f12) {
		this.c2f12 = c2f12;
	}

	public String getC2f2() {
		return c2f2;
	}

	public void setC2f2(String c2f2) {
		this.c2f2 = c2f2;
	}

	public String getC2f3() {
		return c2f3;
	}

	public void setC2f3(String c2f3) {
		this.c2f3 = c2f3;
	}

	public String getC2f4() {
		return c2f4;
	}

	public void setC2f4(String c2f4) {
		this.c2f4 = c2f4;
	}

	public String getC2f5() {
		return c2f5;
	}

	public void setC2f5(String c2f5) {
		this.c2f5 = c2f5;
	}

	public String getC2f6() {
		return c2f6;
	}

	public void setC2f6(String c2f6) {
		this.c2f6 = c2f6;
	}

	public String getC2f7() {
		return c2f7;
	}

	public void setC2f7(String c2f7) {
		this.c2f7 = c2f7;
	}

	public String getC2f8() {
		return c2f8;
	}

	public void setC2f8(String c2f8) {
		this.c2f8 = c2f8;
	}

	public String getC2f9() {
		return c2f9;
	}

	public void setC2f9(String c2f9) {
		this.c2f9 = c2f9;
	}

	public String getC3f1() {
		return c3f1;
	}

	public void setC3f1(String c3f1) {
		this.c3f1 = c3f1;
	}

	public String getC3f10() {
		return c3f10;
	}

	public void setC3f10(String c3f10) {
		this.c3f10 = c3f10;
	}

	public String getC3f11() {
		return c3f11;
	}

	public void setC3f11(String c3f11) {
		this.c3f11 = c3f11;
	}

	public String getC3f12() {
		return c3f12;
	}

	public void setC3f12(String c3f12) {
		this.c3f12 = c3f12;
	}

	public String getC3f2() {
		return c3f2;
	}

	public void setC3f2(String c3f2) {
		this.c3f2 = c3f2;
	}

	public String getC3f3() {
		return c3f3;
	}

	public void setC3f3(String c3f3) {
		this.c3f3 = c3f3;
	}

	public String getC3f4() {
		return c3f4;
	}

	public void setC3f4(String c3f4) {
		this.c3f4 = c3f4;
	}

	public String getC3f5() {
		return c3f5;
	}

	public void setC3f5(String c3f5) {
		this.c3f5 = c3f5;
	}

	public String getC3f6() {
		return c3f6;
	}

	public void setC3f6(String c3f6) {
		this.c3f6 = c3f6;
	}

	public String getC3f7() {
		return c3f7;
	}

	public void setC3f7(String c3f7) {
		this.c3f7 = c3f7;
	}

	public String getC3f8() {
		return c3f8;
	}

	public void setC3f8(String c3f8) {
		this.c3f8 = c3f8;
	}

	public String getC3f9() {
		return c3f9;
	}

	public void setC3f9(String c3f9) {
		this.c3f9 = c3f9;
	}

	public String getC4f1() {
		return c4f1;
	}

	public void setC4f1(String c4f1) {
		this.c4f1 = c4f1;
	}

	public String getC4f10() {
		return c4f10;
	}

	public void setC4f10(String c4f10) {
		this.c4f10 = c4f10;
	}

	public String getC4f11() {
		return c4f11;
	}

	public void setC4f11(String c4f11) {
		this.c4f11 = c4f11;
	}

	public String getC4f12() {
		return c4f12;
	}

	public void setC4f12(String c4f12) {
		this.c4f12 = c4f12;
	}

	public String getC4f2() {
		return c4f2;
	}

	public void setC4f2(String c4f2) {
		this.c4f2 = c4f2;
	}

	public String getC4f3() {
		return c4f3;
	}

	public void setC4f3(String c4f3) {
		this.c4f3 = c4f3;
	}

	public String getC4f4() {
		return c4f4;
	}

	public void setC4f4(String c4f4) {
		this.c4f4 = c4f4;
	}

	public String getC4f5() {
		return c4f5;
	}

	public void setC4f5(String c4f5) {
		this.c4f5 = c4f5;
	}

	public String getC4f6() {
		return c4f6;
	}

	public void setC4f6(String c4f6) {
		this.c4f6 = c4f6;
	}

	public String getC4f7() {
		return c4f7;
	}

	public void setC4f7(String c4f7) {
		this.c4f7 = c4f7;
	}

	public String getC4f8() {
		return c4f8;
	}

	public void setC4f8(String c4f8) {
		this.c4f8 = c4f8;
	}

	public String getC4f9() {
		return c4f9;
	}

	public void setC4f9(String c4f9) {
		this.c4f9 = c4f9;
	}

	public String getC5f1() {
		return c5f1;
	}

	public void setC5f1(String c5f1) {
		this.c5f1 = c5f1;
	}

	public String getC5f10() {
		return c5f10;
	}

	public void setC5f10(String c5f10) {
		this.c5f10 = c5f10;
	}

	public String getC5f11() {
		return c5f11;
	}

	public void setC5f11(String c5f11) {
		this.c5f11 = c5f11;
	}

	public String getC5f12() {
		return c5f12;
	}

	public void setC5f12(String c5f12) {
		this.c5f12 = c5f12;
	}

	public String getC5f2() {
		return c5f2;
	}

	public void setC5f2(String c5f2) {
		this.c5f2 = c5f2;
	}

	public String getC5f3() {
		return c5f3;
	}

	public void setC5f3(String c5f3) {
		this.c5f3 = c5f3;
	}

	public String getC5f4() {
		return c5f4;
	}

	public void setC5f4(String c5f4) {
		this.c5f4 = c5f4;
	}

	public String getC5f5() {
		return c5f5;
	}

	public void setC5f5(String c5f5) {
		this.c5f5 = c5f5;
	}

	public String getC5f6() {
		return c5f6;
	}

	public void setC5f6(String c5f6) {
		this.c5f6 = c5f6;
	}

	public String getC5f7() {
		return c5f7;
	}

	public void setC5f7(String c5f7) {
		this.c5f7 = c5f7;
	}

	public String getC5f8() {
		return c5f8;
	}

	public void setC5f8(String c5f8) {
		this.c5f8 = c5f8;
	}

	public String getC5f9() {
		return c5f9;
	}

	public void setC5f9(String c5f9) {
		this.c5f9 = c5f9;
	}

	public String getC6f1() {
		return c6f1;
	}

	public void setC6f1(String c6f1) {
		this.c6f1 = c6f1;
	}

	public String getC6f10() {
		return c6f10;
	}

	public void setC6f10(String c6f10) {
		this.c6f10 = c6f10;
	}

	public String getC6f11() {
		return c6f11;
	}

	public void setC6f11(String c6f11) {
		this.c6f11 = c6f11;
	}

	public String getC6f12() {
		return c6f12;
	}

	public void setC6f12(String c6f12) {
		this.c6f12 = c6f12;
	}

	public String getC6f2() {
		return c6f2;
	}

	public void setC6f2(String c6f2) {
		this.c6f2 = c6f2;
	}

	public String getC6f3() {
		return c6f3;
	}

	public void setC6f3(String c6f3) {
		this.c6f3 = c6f3;
	}

	public String getC6f4() {
		return c6f4;
	}

	public void setC6f4(String c6f4) {
		this.c6f4 = c6f4;
	}

	public String getC6f5() {
		return c6f5;
	}

	public void setC6f5(String c6f5) {
		this.c6f5 = c6f5;
	}

	public String getC6f6() {
		return c6f6;
	}

	public void setC6f6(String c6f6) {
		this.c6f6 = c6f6;
	}

	public String getC6f7() {
		return c6f7;
	}

	public void setC6f7(String c6f7) {
		this.c6f7 = c6f7;
	}

	public String getC6f8() {
		return c6f8;
	}

	public void setC6f8(String c6f8) {
		this.c6f8 = c6f8;
	}

	public String getC6f9() {
		return c6f9;
	}

	public void setC6f9(String c6f9) {
		this.c6f9 = c6f9;
	}

	public String getIndicadorModificacion() {
		return indicadorModificacion;
	}

	public void setIndicadorModificacion(String indicadorModificacion) {
		this.indicadorModificacion = indicadorModificacion;
	}


}
