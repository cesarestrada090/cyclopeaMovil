package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


/**
 * The Class MantenimientoRECDigitacionCDRForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 05/12/2013
 */
public class MantenimientoRECDigitacionCDRForm extends BaseSearchForm {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The codigo pais. */
	private String codigoPais;
	
	/** The numero cdr. */
	private String numeroCDR;
	
	/** The numero boleta despacho. */
	private String numeroBoletaDespacho;
	
	/** The codigo consejera. */
	private String codigoConsejera;
	
	/** The periodo. */
	private String periodo;
	
	/** The nombre consejera. */
	private String nombreConsejera;
		
	/** The lista operacion. */
	private String[] listaOperacion;
	
	/** The lista cuv cambia. */
	private String[] listaCUVCambia;
	
	/** The lista cantidad cambia. */
	private String[] listaCantidadCambia;
	
	/** The lista motivo. */
	private String[] listaMotivo;
	
	/** The lista igual envio. */
	private String[] listaIgualEnvio;
	
	/** The lista cuv desea. */
	private String[] listaCUVDesea;
	
	/** The lista cantidad desea. */
	private String[] listaCantidadDesea;
	
	/** The lista descripcion cambia. */
	private String[] listaDescripcionCambia;
	
	/** The lista precio cambia. */
	private String[] listaPrecioCambia;
	
	/** The str combo operacion. */
	private String strComboOperacion;
	
	/** The periodo activo. */
	private String periodoActivo;
	
	/** The selected items delete. */
	private String[] selectedItemsDelete;
	
	/** The indicador modifica. */
	private String indicadorModifica;
	
	/** The indicador hay registros. */
	private String indicadorHayRegistros;
	
	/** The indicador express. */
	private boolean indicadorExpress;
	
	/** The indicador express hidden. */
	private String indicadorExpressHidden;
	
	/** The zona. */
	private String zona;
	
	/** The indicador online. */
	private String indicadorOnline;
	
	/** The indicador valida devolucion. */
	private String indicadorValidaDevolucion;
	
	/** The indicador pedido chequeado. */
	private String indicadorPedidoChequeado;
	
	/** The codigo resultado chequeo. */
	private String codigoResultadoChequeo;
	
	/* INI JR PER-SiCC-2012-0304 */
	/** The indicador cdr rechazo. */
	private boolean indicadorCDRRechazo;
	
	/** The observacion cdr. */
	private String observacionCDR;
	
	/** The muestra indicador. */
	private String muestraIndicador;
	
	/** The direccion domicilio. */
	private String direccionDomicilio;
	
	/** The ubicacion geografica. */
	private String ubicacionGeografica;
	
	/* INI PER-SiCC-2012-0558 */
	/** The campana. */
	private String campana;
	
	/** The fecha pedido. */
	private String fechaPedido;
	
	/** The lista ident solic pos. */
	private String[] listaIdentSolicPos; 
	
	/** The periodo cdr. */
	private String periodoCDR;
	
	/** The indicador val cdr linea. */
	private String indicadorValCDRLinea ;
	
	/** The excede dias. */
	private String excedeDias;
	
	/** The codigo operacion correcto. */
	private String codigoOperacionCorrecto;
	/* FIN PER-SiCC-2012-0558 */
	
	// INI PER-SiCC-2012-0642 
	/** The codigo motivo rechazo def. */
	private String codigoMotivoRechazoDef;
	// FIN PER-SiCC-2012-0642 
	
	/** The str combo operacion deuda. */
	private String strComboOperacionDeuda;
	
	/** The str combo motivo deuda. */
	private String strComboMotivoDeuda;
	
	/** The lista ident fila. */
	private String[] listaIdentFila;
	
	/** The lista ident fila padre. */
	private String[] listaIdentFilaPadre;
		
	/** The indicador devolucion oferta. */
	private String indicadorDevolucionOferta;
	
	/** The indicador linea18. */
	private String indicadorLinea18;
	
	/** The valor desviacion precio unitario trueque. */
	private String valorDesviacionPrecioUnitarioTrueque;
	
	/** The indicador producto cambia igual desea. */
	private String indicadorProductoCambiaIgualDesea;
	
	/** The codigo cliente documento referencia. */
	private String codigoClienteDocumentoReferencia;
	
	/** The indicador desviacion precio unitario trueque valor absoluto. */
	private String indicadorDesviacionPrecioUnitarioTruequeValorAbsoluto;
	
	/** The saldo unico. */
	private String saldoUnico;
	
	/**
	 * @return the listaIdentFilaPadre
	 */
	public String[] getListaIdentFilaPadre() {
		return listaIdentFilaPadre;
	}


	/**
	 * @param listaIdentFilaPadre the listaIdentFilaPadre to set
	 */
	public void setListaIdentFilaPadre(String[] listaIdentFilaPadre) {
		this.listaIdentFilaPadre = listaIdentFilaPadre;
	}


	/**
	 * @return the listaIdentFila
	 */
	public String[] getListaIdentFila() {
		return listaIdentFila;
	}


	/**
	 * @param listaIdentFila the listaIdentFila to set
	 */
	public void setListaIdentFila(String[] listaIdentFila) {
		this.listaIdentFila = listaIdentFila;
	}


	/**
	 * @return the strComboOperacionDeuda
	 */
	public String getStrComboOperacionDeuda() {
		return strComboOperacionDeuda;
	}


	/**
	 * @param strComboOperacionDeuda the strComboOperacionDeuda to set
	 */
	public void setStrComboOperacionDeuda(String strComboOperacionDeuda) {
		this.strComboOperacionDeuda = strComboOperacionDeuda;
	}


	/**
	 * @return the strComboMotivoDeuda
	 */
	public String getStrComboMotivoDeuda() {
		return strComboMotivoDeuda;
	}


	/**
	 * @param strComboMotivoDeuda the strComboMotivoDeuda to set
	 */
	public void setStrComboMotivoDeuda(String strComboMotivoDeuda) {
		this.strComboMotivoDeuda = strComboMotivoDeuda;
	}
	
	/** The usuario. */
	private String usuario;
	
	
	
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}


	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	/**
	 * @return the codigoMotivoRechazoDef
	 */
	public String getCodigoMotivoRechazoDef() {
		return codigoMotivoRechazoDef;
	}


	/**
	 * @param codigoMotivoRechazoDef the codigoMotivoRechazoDef to set
	 */
	public void setCodigoMotivoRechazoDef(String codigoMotivoRechazoDef) {
		this.codigoMotivoRechazoDef = codigoMotivoRechazoDef;
	}


	/**
	 * @return the muestraIndicador
	 */
	public String getMuestraIndicador() {
		return muestraIndicador;
	}


	/**
	 * @return the excedeDias
	 */
	public String getExcedeDias() {
		return excedeDias;
	}

	/**
	 * @param excedeDias the excedeDias to set
	 */
	public void setExcedeDias(String excedeDias) {
		this.excedeDias = excedeDias;
	}

	/**
	 * @return the codigoOperacionCorrecto
	 */
	public String getCodigoOperacionCorrecto() {
		return codigoOperacionCorrecto;
	}

	/**
	 * @param codigoOperacionCorrecto the codigoOperacionCorrecto to set
	 */
	public void setCodigoOperacionCorrecto(String codigoOperacionCorrecto) {
		this.codigoOperacionCorrecto = codigoOperacionCorrecto;
	}


	/**
	 * @return the indicadorValCDRLinea
	 */
	public String getIndicadorValCDRLinea() {
		return indicadorValCDRLinea;
	}

	/**
	 * @param indicadorValCDRLinea the indicadorValCDRLinea to set
	 */
	public void setIndicadorValCDRLinea(String indicadorValCDRLinea) {
		this.indicadorValCDRLinea = indicadorValCDRLinea;
	}

	/**
	 * @return the periodoCDR
	 */
	public String getPeriodoCDR() {
		return periodoCDR;
	}

	/**
	 * @param periodoCDR the periodoCDR to set
	 */
	public void setPeriodoCDR(String periodoCDR) {
		this.periodoCDR = periodoCDR;
	}

	/**
	 * @return the campana
	 */
	public String getCampana() {
		return campana;
	}

	/**
	 * @param campana the campana to set
	 */
	public void setCampana(String campana) {
		this.campana = campana;
	}

	/**
	 * @return the fechaPedido
	 */
	public String getFechaPedido() {
		return fechaPedido;
	}

	/**
	 * @param fechaPedido the fechaPedido to set
	 */
	public void setFechaPedido(String fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	/**
	 * @return the listaIdentSolicPos
	 */
	public String[] getListaIdentSolicPos() {
		return listaIdentSolicPos;
	}

	/**
	 * @param listaIdentSolicPos the listaIdentSolicPos to set
	 */
	public void setListaIdentSolicPos(String[] listaIdentSolicPos) {
		this.listaIdentSolicPos = listaIdentSolicPos;
	}

	/**
	 * @param muestraIndicador the muestraIndicador to set
	 */
	public void setMuestraIndicador(String muestraIndicador) {
		this.muestraIndicador = muestraIndicador;
	}	

	/**
	 * @return the observacionCDR
	 */
	public String getObservacionCDR() {
		return observacionCDR;
	}

	/**
	 * @param observacionCDR the observacionCDR to set
	 */
	public void setObservacionCDR(String observacionCDR) {
		this.observacionCDR = observacionCDR;
	}
	/* FIN JR PER-SiCC-2012-0304 */

	/**
	 * @return the indicadorOnline
	 */
	public String getIndicadorOnline() {
		return indicadorOnline;
	}

	/**
	 * @param indicadorOnline the indicadorOnline to set
	 */
	public void setIndicadorOnline(String indicadorOnline) {
		this.indicadorOnline = indicadorOnline;
	}

	/**
	 * @return the periodoActivo
	 */
	public String getPeriodoActivo() {
		return periodoActivo;
	}

	/**
	 * @param periodoActivo the periodoActivo to set
	 */
	public void setPeriodoActivo(String periodoActivo) {
		this.periodoActivo = periodoActivo;
	}

	/**
	 * @return the strComboOperacion
	 */
	public String getStrComboOperacion() {
		return strComboOperacion;
	}

	/**
	 * @param strComboOperacion the strComboOperacion to set
	 */
	public void setStrComboOperacion(String strComboOperacion) {
		this.strComboOperacion = strComboOperacion;
	}

	/**
	 * @return the listaDescripcionCambia
	 */
	public String[] getListaDescripcionCambia() {
		return listaDescripcionCambia;
	}

	/**
	 * @param listaDescripcionCambia the listaDescripcionCambia to set
	 */
	public void setListaDescripcionCambia(String[] listaDescripcionCambia) {
		this.listaDescripcionCambia = listaDescripcionCambia;
	}

	/**
	 * @return the listaPrecioCambia
	 */
	public String[] getListaPrecioCambia() {
		return listaPrecioCambia;
	}

	/**
	 * @param listaPrecioCambia the listaPrecioCambia to set
	 */
	public void setListaPrecioCambia(String[] listaPrecioCambia) {
		this.listaPrecioCambia = listaPrecioCambia;
	}

	/**
	 * @return the strComboMotivo
	 */
	public String getStrComboMotivo() {
		return strComboMotivo;
	}

	/**
	 * @param strComboMotivo the strComboMotivo to set
	 */
	public void setStrComboMotivo(String strComboMotivo) {
		this.strComboMotivo = strComboMotivo;
	}
	
	/** The str combo motivo. */
	private String strComboMotivo;
	
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type="required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the numeroCDR
	 */
	public String getNumeroCDR() {
		return numeroCDR;
	}
	/**
	 * @param numeroCDR the numeroCDR to set
	 * @struts.validator type="required"
	 */
	public void setNumeroCDR(String numeroCDR) {
		this.numeroCDR = numeroCDR;
	}
	/**
	 * @return the numeroBoletaDespacho
	 */
	public String getNumeroBoletaDespacho() {
		return numeroBoletaDespacho;
	}
	/**
	 * @param numeroBoletaDespacho the numeroBoletaDespacho to set
	 * @struts.validator type="required"
	 */
	public void setNumeroBoletaDespacho(String numeroBoletaDespacho) {
		this.numeroBoletaDespacho = numeroBoletaDespacho;
	}
	/**
	 * @return the codigoConsejera
	 */
	public String getCodigoConsejera() {
		return codigoConsejera;
	}
	/**
	 * @param codigoConsejera the codigoConsejera to set
	 * @struts.validator type="required"
	 */
	public void setCodigoConsejera(String codigoConsejera) {
		this.codigoConsejera = codigoConsejera;
	}
	/**
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}
	/**
	 * @param periodo the periodo to set
	 * @struts.validator type="required"
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	/**
	 * @return the listaOperacion
	 */
	public String[] getListaOperacion() {
		return listaOperacion;
	}
	/**
	 * @param listaOperacion the listaOperacion to set
	 */
	public void setListaOperacion(String[] listaOperacion) {
		this.listaOperacion = listaOperacion;
	}
	/**
	 * @return the listaCUVCambia
	 */
	public String[] getListaCUVCambia() {
		return listaCUVCambia;
	}
	/**
	 * @param listaCUVCambia the listaCUVCambia to set
	 */
	public void setListaCUVCambia(String[] listaCUVCambia) {
		this.listaCUVCambia = listaCUVCambia;
	}
	/**
	 * @return the listaCantidadCambia
	 */
	public String[] getListaCantidadCambia() {
		return listaCantidadCambia;
	}
	/**
	 * @param listaCantidadCambia the listaCantidadCambia to set
	 */
	public void setListaCantidadCambia(String[] listaCantidadCambia) {
		this.listaCantidadCambia = listaCantidadCambia;
	}
	/**
	 * @return the listaMotivo
	 */
	public String[] getListaMotivo() {
		return listaMotivo;
	}
	/**
	 * @param listaMotivo the listaMotivo to set
	 */
	public void setListaMotivo(String[] listaMotivo) {
		this.listaMotivo = listaMotivo;
	}
	/**
	 * @return the listaIgualEnvio
	 */
	public String[] getListaIgualEnvio() {
		return listaIgualEnvio;
	}
	/**
	 * @param listaIgualEnvio the listaIgualEnvio to set
	 */
	public void setListaIgualEnvio(String[] listaIgualEnvio) {
		this.listaIgualEnvio = listaIgualEnvio;
	}
	/**
	 * @return the listaCUVDesea
	 */
	public String[] getListaCUVDesea() {
		return listaCUVDesea;
	}
	/**
	 * @param listaCUVDesea the listaCUVDesea to set
	 */
	public void setListaCUVDesea(String[] listaCUVDesea) {
		this.listaCUVDesea = listaCUVDesea;
	}
	/**
	 * @return the listaCantidadDesea
	 */
	public String[] getListaCantidadDesea() {
		return listaCantidadDesea;
	}
	/**
	 * @param listaCantidadDesea the listaCantidadDesea to set
	 */
	public void setListaCantidadDesea(String[] listaCantidadDesea) {
		this.listaCantidadDesea = listaCantidadDesea;
	}
	/**
	 * @return the nombreConsejera
	 */
	public String getNombreConsejera() {
		return nombreConsejera;
	}

	/**
	 * @param nombreConsejera the nombreConsejera to set
	 */
	public void setNombreConsejera(String nombreConsejera) {
		this.nombreConsejera = nombreConsejera;
	}

	/**
	 * @return the selectedItemsDelete
	 */
	public String[] getSelectedItemsDelete() {
		return selectedItemsDelete;
	}

	/**
	 * @param selectedItemsDelete the selectedItemsDelete to set
	 */
	public void setSelectedItemsDelete(String[] selectedItemsDelete) {
		this.selectedItemsDelete = selectedItemsDelete;
	}

	/**
	 * @return the indicadorModifica
	 */
	public String getIndicadorModifica() {
		return indicadorModifica;
	}

	/**
	 * @param indicadorModifica the indicadorModifica to set
	 */
	public void setIndicadorModifica(String indicadorModifica) {
		this.indicadorModifica = indicadorModifica;
	}

	/**
	 * @return the indicadorHayRegistros
	 */
	public String getIndicadorHayRegistros() {
		return indicadorHayRegistros;
	}

	/**
	 * @param indicadorHayRegistros the indicadorHayRegistros to set
	 */
	public void setIndicadorHayRegistros(String indicadorHayRegistros) {
		this.indicadorHayRegistros = indicadorHayRegistros;
	}	

	/**
	 * @return the indicadorExpressHidden
	 */
	public String getIndicadorExpressHidden() {
		return indicadorExpressHidden;
	}

	/**
	 * @param indicadorExpressHidden the indicadorExpressHidden to set
	 */
	public void setIndicadorExpressHidden(String indicadorExpressHidden) {
		this.indicadorExpressHidden = indicadorExpressHidden;
	}

	/**
	 * @return the zona
	 */
	public String getZona() {
		return zona;
	}

	/**
	 * @param zona the zona to set
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}

	/**
	 * @return the indicadorValidaDevolucion
	 */
	public String getIndicadorValidaDevolucion() {
		return indicadorValidaDevolucion;
	}

	/**
	 * @param indicadorValidaDevolucion the indicadorValidaDevolucion to set
	 */
	public void setIndicadorValidaDevolucion(String indicadorValidaDevolucion) {
		this.indicadorValidaDevolucion = indicadorValidaDevolucion;
	}

	/**
	 * @return the indicadorPedidoChequeado
	 */
	public String getIndicadorPedidoChequeado() {
		return indicadorPedidoChequeado;
	}

	/**
	 * @param indicadorPedidoChequeado the indicadorPedidoChequeado to set
	 */
	public void setIndicadorPedidoChequeado(String indicadorPedidoChequeado) {
		this.indicadorPedidoChequeado = indicadorPedidoChequeado;
	}

	/**
	 * @return the codigoResultadoChequeo
	 */
	public String getCodigoResultadoChequeo() {
		return codigoResultadoChequeo;
	}

	/**
	 * @param codigoResultadoChequeo the codigoResultadoChequeo to set
	 */
	public void setCodigoResultadoChequeo(String codigoResultadoChequeo) {
		this.codigoResultadoChequeo = codigoResultadoChequeo;
	}

	/**
	 * @return the direccionDomicilio
	 */
	public String getDireccionDomicilio() {
		return direccionDomicilio;
	}

	/**
	 * @param direccionDomicilio the direccionDomicilio to set
	 */
	public void setDireccionDomicilio(String direccionDomicilio) {
		this.direccionDomicilio = direccionDomicilio;
	}

	/**
	 * @return the ubicacionGeografica
	 */
	public String getUbicacionGeografica() {
		return ubicacionGeografica;
	}

	/**
	 * @param ubicacionGeografica the ubicacionGeografica to set
	 */
	public void setUbicacionGeografica(String ubicacionGeografica) {
		this.ubicacionGeografica = ubicacionGeografica;
	}


	/**
	 * @return the indicadorDevolucionOferta
	 */
	public String getIndicadorDevolucionOferta() {
		return indicadorDevolucionOferta;
	}


	/**
	 * @param indicadorDevolucionOferta the indicadorDevolucionOferta to set
	 */
	public void setIndicadorDevolucionOferta(String indicadorDevolucionOferta) {
		this.indicadorDevolucionOferta = indicadorDevolucionOferta;
	}


	/**
	 * @return the indicadorLinea18
	 */
	public String getIndicadorLinea18() {
		return indicadorLinea18;
	}


	/**
	 * @param indicadorLinea18 the indicadorLinea18 to set
	 */
	public void setIndicadorLinea18(String indicadorLinea18) {
		this.indicadorLinea18 = indicadorLinea18;
	}


	/**
	 * @return the valorDesviacionPrecioUnitarioTrueque
	 */
	public String getValorDesviacionPrecioUnitarioTrueque() {
		return valorDesviacionPrecioUnitarioTrueque;
	}


	/**
	 * @param valorDesviacionPrecioUnitarioTrueque the valorDesviacionPrecioUnitarioTrueque to set
	 */
	public void setValorDesviacionPrecioUnitarioTrueque(
			String valorDesviacionPrecioUnitarioTrueque) {
		this.valorDesviacionPrecioUnitarioTrueque = valorDesviacionPrecioUnitarioTrueque;
	}


	/**
	 * @return the indicadorProductoCambiaIgualDesea
	 */
	public String getIndicadorProductoCambiaIgualDesea() {
		return indicadorProductoCambiaIgualDesea;
	}


	/**
	 * @param indicadorProductoCambiaIgualDesea the indicadorProductoCambiaIgualDesea to set
	 */
	public void setIndicadorProductoCambiaIgualDesea(
			String indicadorProductoCambiaIgualDesea) {
		this.indicadorProductoCambiaIgualDesea = indicadorProductoCambiaIgualDesea;
	}


	/**
	 * @return the codigoClienteDocumentoReferencia
	 */
	public String getCodigoClienteDocumentoReferencia() {
		return codigoClienteDocumentoReferencia;
	}


	/**
	 * @param codigoClienteDocumentoReferencia the codigoClienteDocumentoReferencia to set
	 */
	public void setCodigoClienteDocumentoReferencia(
			String codigoClienteDocumentoReferencia) {
		this.codigoClienteDocumentoReferencia = codigoClienteDocumentoReferencia;
	}


	/**
	 * @return the indicadorDesviacionPrecioUnitarioTruequeValorAbsoluto
	 */
	public String getIndicadorDesviacionPrecioUnitarioTruequeValorAbsoluto() {
		return indicadorDesviacionPrecioUnitarioTruequeValorAbsoluto;
	}


	/**
	 * @param indicadorDesviacionPrecioUnitarioTruequeValorAbsoluto the indicadorDesviacionPrecioUnitarioTruequeValorAbsoluto to set
	 */
	public void setIndicadorDesviacionPrecioUnitarioTruequeValorAbsoluto(
			String indicadorDesviacionPrecioUnitarioTruequeValorAbsoluto) {
		this.indicadorDesviacionPrecioUnitarioTruequeValorAbsoluto = indicadorDesviacionPrecioUnitarioTruequeValorAbsoluto;
	}


	public String getSaldoUnico() {
		return saldoUnico;
	}


	public void setSaldoUnico(String saldoUnico) {
		this.saldoUnico = saldoUnico;
	}


	public boolean isIndicadorExpress() {
		return indicadorExpress;
	}


	public void setIndicadorExpress(boolean indicadorExpress) {
		this.indicadorExpress = indicadorExpress;
	}


	public boolean isIndicadorCDRRechazo() {
		return indicadorCDRRechazo;
	}


	public void setIndicadorCDRRechazo(boolean indicadorCDRRechazo) {
		this.indicadorCDRRechazo = indicadorCDRRechazo;
	}
}
