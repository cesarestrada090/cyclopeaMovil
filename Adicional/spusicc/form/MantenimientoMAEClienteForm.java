package biz.belcorp.ssicc.web.spusicc.form;

import java.util.Date;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * The Class MantenimientoPERNumeracionComprobantesSunatSearchForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 14/01/2015
 */
public class MantenimientoMAEClienteForm extends BaseSearchForm {

private static final long serialVersionUID = 1L;
	
	private String oidPais;
	private String codigoPais;
	private String codigoMarca;
	private String codigoCanal;
	
	private String oidPeriodo;
	private String fechaIngreso;
	private Date fechaIngresoD;
	
	
	private String subTipoCliente;
	
	private String tipoDocumentoIdentidad;
	private String numeroDocumentoIdentidad;
	private String codigoCliente;
	private String digitoControl;
	
	private boolean mostrarUnidadAdministrativa;
	private String codigoZona;
	private String codigoTerritorio;
	private String oidTerritorioAdministrativo;
	private String oidTerritorio;
	
	private boolean mostrarConsultoraRecomendante;
	private String oidConsultoraRecomendante;
	private String codigoConsultoraRecomendante;
	private String nombreConsultoraRecomendante;
	
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String sexo;
	private String nombre1;
	private String nombre2;
	private String tratamiento;
	private String apellidoCasada;
	private String edad;
	private String codigoEmpleado;
	private String fechaNacimiento;
	private Date fechaNacimientoD;
	private String estadoCivil;
	private String gradoInstruccion;
	private String nacionalidad;

	private boolean mostrarNumeroIdentidad;
	
	//Datos de SECCION DIRECCION DOMICILIO
	
	private boolean mostrarDireccion=false;;
	private String tipoVia;
	private String numeroPrincipal;
	private String nombreVia;
	private String correVia;//Correlativo Via
	private String observacionDireccion;
	private String barrio;
	private String nivel1;
	private String nivel2;
	private String nivel3;
	private String nivel4;
	private String nivel5;
	private String nivel6;
	private String telefonoCasa;
	private String telefonoCelular;
	private String mail;
	
	private String codNivel1;
	private String codNivel2;
	private String codNivel3;
	private String codNivel4;
	private String codNivel5;
	private String codNivel6;

	private String codNivel2CT;
	private String codNivel3CT;
	private String codNivel4CT;
	private String codNivel5CT;
	private String codNivel6CT;
	private boolean indicadorDocFiscal;
	/* INI SA PER-SiCC-2012-0459 */
	private String codigoCiudad;
	private String villaPoblacion;
	
	private boolean mostrarCiudad;
	private boolean mostrarVillaPoblacion;
	/* FIN SA PER-SiCC-2012-0459 */
	
	//Datos de SECCION DIRECCION CENTRO DE TRABAJO
	private String tipoViaCT;
	private String numeroPrincipalCT;
	private String nombreViaCT;
	private String correViaCT;
	private String observacionDireccionCT;
	private String barrioCT;
	private String nivel1CT;
	private String nivel2CT;
	private String nivel3CT;
	private String nivel4CT;
	private String nivel5CT;
	private String nivel6CT;
	private String empresa;
	private String cargo;
	private String telefono;
	private String sueldoMensual;
	private String tipoDireccion;
	
	private String telefonoCasaDireccionEntrega;
	private String telefonoCelularDireccionEntrega;
	
	private String descripcionNivel1;
	private String descripcionNivel2;
	private String descripcionNivel3;
	private String descripcionNivel4;
	private String descripcionNivel5;
	private String descripcionNivel6;
	private String totalNiveles;

	/* INI SA PER-SiCC-2012-0459 */
	private String codigoCiudadCT;
	private String villaPoblacionCT;
	/* FIN SA PER-SiCC-2012-0459 */

	/* INI SA PER-SiCC-2012-0365 */
	//Datos de SECCION DIRECCION VACACIONES
	private String codigoPeriodoInicio;
	private String codigoPeriodoFin;
	private String tipoViaVacaciones;
	private String numeroPrincipalVacaciones;
	private String nombreViaVacaciones;
	private String correViaVacaciones;
	private String observacionDireccionVacaciones;
	private String barrioVacaciones;
	
	private String nivel1Vacaciones;
	private String nivel2Vacaciones;
	private String nivel3Vacaciones;
	private String nivel4Vacaciones;
	private String nivel5Vacaciones;
	private String nivel6Vacaciones;
	private String codNivel2Vacaciones;
	private String codNivel3Vacaciones;
	private String codNivel4Vacaciones;
	private String codNivel5Vacaciones;
	private String codNivel6Vacaciones;
	
	private String codigoCiudadVacaciones;
	private String villaPoblacionVacaciones;

	private String telefonoCasaDireccionVacaciones;
	private String telefonoCelularDireccionVacaciones;
	
	private boolean mostrarDireccionVacaciones;
	private String indicadorDireccionVacaciones;
	private boolean primeraVezDespliegueDireccionVacaciones;
	private String codigoPeriodo;
	private String codigoPeriodoInicioVacaciones;
	private String codigoPeriodoFinVacaciones;
	private boolean actualizaUbigeoDirecciones;
	/* FIN SA PER-SiCC-2012-0365 */
	
	//Datos de VINCULO
	private boolean mostrarConsultoraVinculo;
	private String oidConsultoraVinculo;
	private String codigoConsultoraVinculo;
	private String nombreConsultoraVinculo;
	private String fechaDesde;
	private String fechaHasta;
	
	//Datos de OBSERVACIONES
	private String observaciones1;
	private String observaciones2;
	private String observaciones3;

	//Datos de CLASIFICACIONES
	private boolean mostrarClasificaciones;
	private String tipoClasificacion;
	private String Clasificacion;
	
	//Para verificar si es automatico la generacion del codigo de cliente para el pais
	private boolean esCodigoClienteAutomatico; 
	//longitu de codigo de cliente para el pais
	private String longitudCodigoCliente;
	//si el tipo de cliente y subtipo de cliente seleccionado es HijaDupla (es true), en otro caso (false)
	private boolean esDuplaCyzone;
	
	private String edadMinima;
	private String edadMaxima;
	private String fechaActual;
	
	//indica si grabo correctamente el cliente
	private boolean graboOK;
	
	//oid Nuevo Cliente
	private String oidNuevoCliente;
	
	//control que va a tener el foco cuando se pinte la pantalla
	private String controlFoco;
	
	//si se muestra el control de texto para el ingreso del codigo de empleado
	private boolean mostrarCodigoEmpleado;

	//si se agrega una clasificacion default relacionado al subtipo cliente ingresado
	private boolean agregarClasificacionDefault;

	//si se ha confirmado [Aceptar] cuando el territorio es diferente al ubigeo del distrito
	private String confirmacionTerritorio;

	//En casos que se necesita mostrar mensajes de confirmacion para poder proceder a grabar los datos del cliente
	private String mensajeConfirmacion;
	
	//Para obligar a ingresar nacionalidad
	private boolean indicadoObligatorioNacionalidad;	

	//Para obligar a ingresar grado de instruccion
	private boolean indicadoObligatorioGradoInstruccion;	

	//Para obligar a ingresar tratamiento
	private boolean indicadoObligatorioTratamiento;	
	
	private String indicadorClasificaciones;
	private String indicadorDireccionTrabajo;
	private String indicadorObservaciones;
	
	private String mensajeGrabarAlert;
	
	//Almacena la longitud del tipo de documento de Identidad
	private String longitudTipoDocumento; 
	
	private String oidPeriodoConcurso;

	private boolean primeraVezDespliegueDireccionEntrega;
	
	private String longitudCodigoZona;
	private boolean mostrarTipoVia;
	private boolean mostrarNumeroPrincipal;
	private boolean permitirModificarUbigeo;
	private boolean mostrarUbigeoEntrega;
	
	private boolean mostrarPantallaPremios;	
	//Referencia Familiar
	private String apellido1RefFamiliar;
	private String apellido2RefFamiliar;
	private String nombre1RefFamiliar;
	private String nombre2RefFamiliar;
	private String direccionRefFamiliar;
	private String barrioRefFamiliar;
	private String telefonoCasaRefFamiliar;
	private String telefonoCelRefFamiliar;
	private String codigoTipoVinculoRefFamiliar;
	//Referencia No Familiar
	private String apellido1RefNoFamiliar;
	private String apellido2RefNoFamiliar;
	private String nombre1RefNoFamiliar;
	private String nombre2RefNoFamiliar;
	private String direccionRefNoFamiliar;
	private String barrioRefNoFamiliar;
	private String telefonoCasaRefNoFamiliar;
	private String telefonoCelRefNoFamiliar;
	private String codigoTipoVinculoRefNoFamiliar;	
	//Referencia A Aval
	private String apellido1Aval;
	private String apellido2Aval;
	private String nombre1Aval;
	private String nombre2Aval;
	private String estado;
	private String municipio;
	private String parroquia;
	private String direccionAval;
	private String barrioAval;
	private String telefonoCasaAval;
	private String telefonoCelAval;
	private String codigoTipoVinculoAval;	
	private String codigoDepartamentoAval;
	private String codigoProvinciaAval;
	private String codigoDistritoAval;
	private String oidTipoDocumentoAval;
	private String numeroDocumentoAval;
	private String longitudTipoDocumentoAval;
	private boolean primeraVezDespliegueReferencias;
	
	private String chkReferencias;
	private boolean aprobarAvaladas;
	
	//Indica si se completa con Ceros el numero de Documento de Identidad
	private boolean permitirCompletarCerosIdentidad;
	
	//Indica si se valida si se deja grabar la consultora con '0' al inicio del numero de Documento de Identidad
	private boolean permitirComenzarCerosIdentidad;
	
	//Indica se se muestra segundo tipo de documento de identidad
	private boolean mostrarSegundoDocumento;
	
	private String tipoDocumentoIdentidad2;
	private String numeroDocumentoIdentidad2;
	private String longitudTipoDocumento2;
	
	private String validarCaracteres1;
	private String validarCaracteres2;
	private String validarCaracteres3;
	private String validarCaracteresIdentidad;
	
	private String cadenaCaracteresV1;
	private String cadenaCaracteresNV1;
	private String cadenaCaracteresV2;
	private String cadenaCaracteresNV2;
	private String cadenaCaracteresV3;
	private String cadenaCaracteresNV3;
	private String cadenaCaracteresVIdentidad;
	private String cadenaCaracteresNVIdentidad;
	
	private String telefonoReferencia;
	private String codigoAnterior;
	
	private boolean mostrarBarrio;
	
	public String tipoCutis;
	
	public String otrasMarcas;

	/* INI SA PER-SiCC-2012-0367 */
	private boolean validarEstatusComercial;
	private String mensajeConsultoraRoja;
	/* FIN SA PER-SiCC-2012-0367 */
	
	/* INI JJ PER-SiCC-2012-0329 */
	private boolean mostrarCodigoCUB;
	private String codigoCUB;
	
	private String numeroPremio;
	
	/* INI JV CHI-SiCC-2012-0003 */
	private boolean indicadorSeccionOtros;
	private String indicadorCompromiso;
	private String valorIndicadorCompromiso;
	private String motivo;
	/* FIN JV CHI-SiCC-2012-0003 */
	
	/* INI SA COS-SiCC-2013-0031 */
	private String indicadorImpresionPaqDoc;
	private boolean mostrarIndicadorImpresionPaqDoc;
	/* FIN SA COS-SiCC-2013-0031 */
	
	/* INI JP PER-SiCC-2013-0480 */
	private boolean indicadorDocumentosLegales;
	/* FIN JP PER-SiCC-2013-0480 */
	
	/*
	 * indica si se mostrara lupita de buscar direccion
	 */
	private boolean mostrarBuscarDireccion;
	
	/* INI PER-SiCC-2014-0162 */
	private boolean indicadorFactElect;
	private String valorIndicadorFactElect;
	private String booleanValorIndicadorFactElect;
	/* FIN PER-SiCC-2014-0162 */
	
	
	
	private String codigoPostal;
	private String codigoPostalCT;
	
	private String telefonoAdicional;
	
	//si el tipo de cliente y subtipo de cliente seleccionado es Consultora, sea Negocio u Oficina
	private boolean esTipoConsultora;
	

	private boolean mostrarConsultoraLiderRecomendante;
	private String oidConsultoraLiderRecomendante;
	private String codigoConsultoraLiderRecomendante;
	private String nombreConsultoraLiderRecomendante;
	
	/**
	 * @return the numeroPremio
	 */
	public String getNumeroPremio() {
		return numeroPremio;
	}

	public boolean isIndicadorFactElect() {
		return indicadorFactElect;
	}



	public void setIndicadorFactElect(boolean indicadorFactElect) {
		this.indicadorFactElect = indicadorFactElect;
	}



	public String getValorIndicadorFactElect() {
		return valorIndicadorFactElect;
	}

	public void setValorIndicadorFactElect(String valorIndicadorFactElect) {
		this.valorIndicadorFactElect = valorIndicadorFactElect;
	}

	
	public String getBooleanValorIndicadorFactElect() {
		return booleanValorIndicadorFactElect;
	}

	public void setBooleanValorIndicadorFactElect(
			String booleanValorIndicadorFactElect) {
		this.booleanValorIndicadorFactElect = booleanValorIndicadorFactElect;
	}

	/**
	 * @param numeroPremio the numeroPremio to set
	 */
	public void setNumeroPremio(String numeroPremio) {
		this.numeroPremio = numeroPremio;
	}

	/**
	 * @return
	 */
	public boolean isMostrarCodigoCUB() {
		return mostrarCodigoCUB;
	}

	/**
	 * @param mostrarCodigoCUB
	 */
	public void setMostrarCodigoCUB(boolean mostrarCodigoCUB) {
		this.mostrarCodigoCUB = mostrarCodigoCUB;
	}

	
	
	
	public Date getFechaNacimientoD() {
		return fechaNacimientoD;
	}

	public void setFechaNacimientoD(Date fechaNacimientoD) {
		this.fechaNacimientoD = fechaNacimientoD;
	}

	public Date getFechaIngresoD() {
		return fechaIngresoD;
	}

	public void setFechaIngresoD(Date fechaIngresoD) {
		this.fechaIngresoD = fechaIngresoD;
	}

	/**
	 * @return
	 */
	public String getCodigoCUB() {
		return codigoCUB;
	}
	
	/**
	 * @param codigoCUB
	 */
	public void setCodigoCUB(String codigoCUB) {
		this.codigoCUB = codigoCUB;
	}
	/* FIN JJ PER-SiCC-2012-0329 */
	
	/**
	 * @return the chkReferencias
	 */
	public String getChkReferencias() {
		return chkReferencias;
	}
	/**
	 * @param chkReferencias the chkReferencias to set
	 */
	public void setChkReferencias(String chkReferencias) {
		this.chkReferencias = chkReferencias;
	}
	/**
	 * @return the oidTipoDocumentoAval
	 */
	public String getOidTipoDocumentoAval() {
		return oidTipoDocumentoAval;
	}
	/**
	 * @param oidTipoDocumentoAval the oidTipoDocumentoAval to set
	 */
	public void setOidTipoDocumentoAval(String oidTipoDocumentoAval) {
		this.oidTipoDocumentoAval = oidTipoDocumentoAval;
	}
	/**
	 * @return the numeroDocumentoAval
	 */
	public String getNumeroDocumentoAval() {
		return numeroDocumentoAval;
	}
	/**
	 * @param numeroDocumentoAval the numeroDocumentoAval to set
	 */
	public void setNumeroDocumentoAval(String numeroDocumentoAval) {
		this.numeroDocumentoAval = numeroDocumentoAval;
	}
	/**
	 * @return the codigoDepartamentoAval
	 */
	public String getCodigoDepartamentoAval() {
		return codigoDepartamentoAval;
	}
	/**
	 * @param codigoDepartamentoAval the codigoDepartamentoAval to set
	 */
	public void setCodigoDepartamentoAval(String codigoDepartamentoAval) {
		this.codigoDepartamentoAval = codigoDepartamentoAval;
	}
	/**
	 * @return the codigoProvinciaAval
	 */
	public String getCodigoProvinciaAval() {
		return codigoProvinciaAval;
	}
	/**
	 * @param codigoProvinciaAval the codigoProvinciaAval to set
	 */
	public void setCodigoProvinciaAval(String codigoProvinciaAval) {
		this.codigoProvinciaAval = codigoProvinciaAval;
	}
	/**
	 * @return the codigoDistritoAval
	 */
	public String getCodigoDistritoAval() {
		return codigoDistritoAval;
	}
	/**
	 * @param codigoDistritoAval the codigoDistritoAval to set
	 */
	public void setCodigoDistritoAval(String codigoDistritoAval) {
		this.codigoDistritoAval = codigoDistritoAval;
	}
	/**
	 * @return the apellido1RefFamiliar
	 */
	public String getApellido1RefFamiliar() {
		return apellido1RefFamiliar;
	}
	/**
	 * @param apellido1RefFamiliar the apellido1RefFamiliar to set
	 */
	public void setApellido1RefFamiliar(String apellido1RefFamiliar) {
		this.apellido1RefFamiliar = apellido1RefFamiliar;
	}
	/**
	 * @return the apellido2RefFamiliar
	 */
	public String getApellido2RefFamiliar() {
		return apellido2RefFamiliar;
	}
	/**
	 * @param apellido2RefFamiliar the apellido2RefFamiliar to set
	 */
	public void setApellido2RefFamiliar(String apellido2RefFamiliar) {
		this.apellido2RefFamiliar = apellido2RefFamiliar;
	}
	/**
	 * @return the nombre1RefFamiliar
	 */
	public String getNombre1RefFamiliar() {
		return nombre1RefFamiliar;
	}
	/**
	 * @param nombre1RefFamiliar the nombre1RefFamiliar to set
	 */
	public void setNombre1RefFamiliar(String nombre1RefFamiliar) {
		this.nombre1RefFamiliar = nombre1RefFamiliar;
	}
	/**
	 * @return the nombre2RefFamiliar
	 */
	public String getNombre2RefFamiliar() {
		return nombre2RefFamiliar;
	}
	/**
	 * @param nombre2RefFamiliar the nombre2RefFamiliar to set
	 */
	public void setNombre2RefFamiliar(String nombre2RefFamiliar) {
		this.nombre2RefFamiliar = nombre2RefFamiliar;
	}
	/**
	 * @return the direccionRefFamiliar
	 */
	public String getDireccionRefFamiliar() {
		return direccionRefFamiliar;
	}
	/**
	 * @param direccionRefFamiliar the direccionRefFamiliar to set
	 */
	public void setDireccionRefFamiliar(String direccionRefFamiliar) {
		this.direccionRefFamiliar = direccionRefFamiliar;
	}
	/**
	 * @return the telefonoCasaRefFamiliar
	 */
	public String getTelefonoCasaRefFamiliar() {
		return telefonoCasaRefFamiliar;
	}
	/**
	 * @param telefonoCasaRefFamiliar the telefonoCasaRefFamiliar to set
	 */
	public void setTelefonoCasaRefFamiliar(String telefonoCasaRefFamiliar) {
		this.telefonoCasaRefFamiliar = telefonoCasaRefFamiliar;
	}
	/**
	 * @return the telefonoCelRefFamiliar
	 */
	public String getTelefonoCelRefFamiliar() {
		return telefonoCelRefFamiliar;
	}
	/**
	 * @param telefonoCelRefFamiliar the telefonoCelRefFamiliar to set
	 */
	public void setTelefonoCelRefFamiliar(String telefonoCelRefFamiliar) {
		this.telefonoCelRefFamiliar = telefonoCelRefFamiliar;
	}
	/**
	 * @return the codigoTipoVinculoRefFamiliar
	 */
	public String getCodigoTipoVinculoRefFamiliar() {
		return codigoTipoVinculoRefFamiliar;
	}
	/**
	 * @param codigoTipoVinculoRefFamiliar the codigoTipoVinculoRefFamiliar to set
	 */
	public void setCodigoTipoVinculoRefFamiliar(String codigoTipoVinculoRefFamiliar) {
		this.codigoTipoVinculoRefFamiliar = codigoTipoVinculoRefFamiliar;
	}
	/**
	 * @return the apellido1RefNoFamiliar
	 */
	public String getApellido1RefNoFamiliar() {
		return apellido1RefNoFamiliar;
	}
	/**
	 * @param apellido1RefNoFamiliar the apellido1RefNoFamiliar to set
	 */
	public void setApellido1RefNoFamiliar(String apellido1RefNoFamiliar) {
		this.apellido1RefNoFamiliar = apellido1RefNoFamiliar;
	}
	/**
	 * @return the apellido2RefNoFamiliar
	 */
	public String getApellido2RefNoFamiliar() {
		return apellido2RefNoFamiliar;
	}
	/**
	 * @param apellido2RefNoFamiliar the apellido2RefNoFamiliar to set
	 */
	public void setApellido2RefNoFamiliar(String apellido2RefNoFamiliar) {
		this.apellido2RefNoFamiliar = apellido2RefNoFamiliar;
	}
	/**
	 * @return the nombre1RefNoFamiliar
	 */
	public String getNombre1RefNoFamiliar() {
		return nombre1RefNoFamiliar;
	}
	/**
	 * @param nombre1RefNoFamiliar the nombre1RefNoFamiliar to set
	 */
	public void setNombre1RefNoFamiliar(String nombre1RefNoFamiliar) {
		this.nombre1RefNoFamiliar = nombre1RefNoFamiliar;
	}
	/**
	 * @return the nombre2RefNoFamiliar
	 */
	public String getNombre2RefNoFamiliar() {
		return nombre2RefNoFamiliar;
	}
	/**
	 * @param nombre2RefNoFamiliar the nombre2RefNoFamiliar to set
	 */
	public void setNombre2RefNoFamiliar(String nombre2RefNoFamiliar) {
		this.nombre2RefNoFamiliar = nombre2RefNoFamiliar;
	}
	/**
	 * @return the direccionRefNoFamiliar
	 */
	public String getDireccionRefNoFamiliar() {
		return direccionRefNoFamiliar;
	}
	/**
	 * @param direccionRefNoFamiliar the direccionRefNoFamiliar to set
	 */
	public void setDireccionRefNoFamiliar(String direccionRefNoFamiliar) {
		this.direccionRefNoFamiliar = direccionRefNoFamiliar;
	}
	/**
	 * @return the telefonoCasaRefNoFamiliar
	 */
	public String getTelefonoCasaRefNoFamiliar() {
		return telefonoCasaRefNoFamiliar;
	}
	/**
	 * @param telefonoCasaRefNoFamiliar the telefonoCasaRefNoFamiliar to set
	 */
	public void setTelefonoCasaRefNoFamiliar(String telefonoCasaRefNoFamiliar) {
		this.telefonoCasaRefNoFamiliar = telefonoCasaRefNoFamiliar;
	}
	/**
	 * @return the telefonoCelRefNoFamiliar
	 */
	public String getTelefonoCelRefNoFamiliar() {
		return telefonoCelRefNoFamiliar;
	}
	/**
	 * @param telefonoCelRefNoFamiliar the telefonoCelRefNoFamiliar to set
	 */
	public void setTelefonoCelRefNoFamiliar(String telefonoCelRefNoFamiliar) {
		this.telefonoCelRefNoFamiliar = telefonoCelRefNoFamiliar;
	}
	/**
	 * @return the codigoTipoVinculoRefNoFamiliar
	 */
	public String getCodigoTipoVinculoRefNoFamiliar() {
		return codigoTipoVinculoRefNoFamiliar;
	}
	/**
	 * @param codigoTipoVinculoRefNoFamiliar the codigoTipoVinculoRefNoFamiliar to set
	 */
	public void setCodigoTipoVinculoRefNoFamiliar(
			String codigoTipoVinculoRefNoFamiliar) {
		this.codigoTipoVinculoRefNoFamiliar = codigoTipoVinculoRefNoFamiliar;
	}
	/**
	 * @return the apellido1Aval
	 */
	public String getApellido1Aval() {
		return apellido1Aval;
	}
	/**
	 * @param apellido1Aval the apellido1Aval to set
	 */
	public void setApellido1Aval(String apellido1Aval) {
		this.apellido1Aval = apellido1Aval;
	}
	/**
	 * @return the apellido2Aval
	 */
	public String getApellido2Aval() {
		return apellido2Aval;
	}
	/**
	 * @param apellido2Aval the apellido2Aval to set
	 */
	public void setApellido2Aval(String apellido2Aval) {
		this.apellido2Aval = apellido2Aval;
	}
	/**
	 * @return the nombre1Aval
	 */
	public String getNombre1Aval() {
		return nombre1Aval;
	}
	/**
	 * @param nombre1Aval the nombre1Aval to set
	 */
	public void setNombre1Aval(String nombre1Aval) {
		this.nombre1Aval = nombre1Aval;
	}
	/**
	 * @return the nombre2Aval
	 */
	public String getNombre2Aval() {
		return nombre2Aval;
	}
	/**
	 * @param nombre2Aval the nombre2Aval to set
	 */
	public void setNombre2Aval(String nombre2Aval) {
		this.nombre2Aval = nombre2Aval;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return the municipio
	 */
	public String getMunicipio() {
		return municipio;
	}
	/**
	 * @param municipio the municipio to set
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	/**
	 * @return the parroquia
	 */
	public String getParroquia() {
		return parroquia;
	}
	/**
	 * @param parroquia the parroquia to set
	 */
	public void setParroquia(String parroquia) {
		this.parroquia = parroquia;
	}
	/**
	 * @return the direccionAval
	 */
	public String getDireccionAval() {
		return direccionAval;
	}
	/**
	 * @param direccionAval the direccionAval to set
	 */
	public void setDireccionAval(String direccionAval) {
		this.direccionAval = direccionAval;
	}
	/**
	 * @return the telefonoCasaAval
	 */
	public String getTelefonoCasaAval() {
		return telefonoCasaAval;
	}
	/**
	 * @param telefonoCasaAval the telefonoCasaAval to set
	 */
	public void setTelefonoCasaAval(String telefonoCasaAval) {
		this.telefonoCasaAval = telefonoCasaAval;
	}
	/**
	 * @return the telefonoCelAval
	 */
	public String getTelefonoCelAval() {
		return telefonoCelAval;
	}
	/**
	 * @param telefonoCelAval the telefonoCelAval to set
	 */
	public void setTelefonoCelAval(String telefonoCelAval) {
		this.telefonoCelAval = telefonoCelAval;
	}
	/**
	 * @return the codigoTipoVinculoAval
	 */
	public String getCodigoTipoVinculoAval() {
		return codigoTipoVinculoAval;
	}
	/**
	 * @param codigoTipoVinculoAval the codigoTipoVinculoAval to set
	 */
	public void setCodigoTipoVinculoAval(String codigoTipoVinculoAval) {
		this.codigoTipoVinculoAval = codigoTipoVinculoAval;
	}	
	/**
	 * @return Returns the apellidoCasada.
	 */
	public String getApellidoCasada() {
		return apellidoCasada;
	}
	/**
	 * @param apellidoCasada The apellidoCasada to set.
	 */
	public void setApellidoCasada(String apellidoCasada) {
		this.apellidoCasada = apellidoCasada;
	}
	/**
	 * @return Returns the apellidoMaterno.
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	/**
	 * @param apellidoMaterno The apellidoMaterno to set.
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	/**
	 * @return Returns the apellidoPaterno.
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	/**
	 * @param apellidoPaterno The apellidoPaterno to set.
	 * @struts.validator type="required" 
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	/**
	 * @return Returns the codigoCanal.
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}
	/**
	 * @param codigoCanal The codigoCanal to set.
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}
	/**
	 * @return Returns the codigoCliente.
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}
	/**
	 * @param codigoCliente The codigoCliente to set.
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	/**
	 * @return Returns the codigoEmpleado.
	 */
	public String getCodigoEmpleado() {
		return codigoEmpleado;
	}
	/**
	 * @param codigoEmpleado The codigoEmpleado to set.
	 */
	public void setCodigoEmpleado(String codigoEmpleado) {
		this.codigoEmpleado = codigoEmpleado;
	}
	/**
	 * @return Returns the codigoMarca.
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}
	/**
	 * @param codigoMarca The codigoMarca to set.
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return Returns the codigoTerritorio.
	 */
	public String getCodigoTerritorio() {
		return codigoTerritorio;
	}
	/**
	 * @param codigoTerritorio The codigoTerritorio to set.
	 * @struts.validator type="required"
	 */
	public void setCodigoTerritorio(String codigoTerritorio) {
		this.codigoTerritorio = codigoTerritorio;
	}
	/**
	 * @return Returns the codigoZona.
	 */
	public String getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @param codigoZona The codigoZona to set.
	 * @struts.validator type="required"
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	/**
	 * @return Returns the edad.
	 */
	public String getEdad() {
		return edad;
	}
	/**
	 * @param edad The edad to set.
	 */
	public void setEdad(String edad) {
		this.edad = edad;
	}
	/**
	 * @return Returns the estadoCivil.
	 */
	public String getEstadoCivil() {
		return estadoCivil;
	}
	/**
	 * @param estadoCivil The estadoCivil to set.
	 * @struts.validator type="required" 
	 */
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	/**
	 * @return Returns the fechaIngreso.
	 */
	public String getFechaIngreso() {
		return fechaIngreso;
	}
	/**
	 * @param fechaIngreso The fechaIngreso to set.
	 * @struts.validator type="required"
 	 * @struts.validator type="date"
	 * @struts.validator-var name="datePatternStrict"
	 *                       value="${defaultDatePattern}"
	 */
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	/**
	 * @return Returns the fechaNacimiento.
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**
	 * @param fechaNacimiento The fechaNacimiento to set.
	 * @struts.validator type="required"
	 * @struts.validator type="date"
	 * @struts.validator-var name="datePatternStrict"
	 *                       value="${defaultDatePattern}"
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	/**
	 * @return Returns the nivel1.
	 */
	public String getNivel1() {
		return nivel1;
	}
	/**
	 * @param nivel1 The nivel1 to set.
	 * @struts.validator type="required" 
	 */
	public void setNivel1(String nivel1) {
		this.nivel1 = nivel1;
	}
	/**
	 * @return Returns the nivel2.
	 */
	public String getNivel2() {
		return nivel2;
	}
	/**
	 * @param nivel2 The nivel2 to set.
	 * @struts.validator type="required"
	 */
	public void setNivel2(String nivel2) {
		this.nivel2 = nivel2;
	}
	/**
	 * @return Returns the nivel3.
	 */
	public String getNivel3() {
		return nivel3;
	}
	/**
	 * @param nivel3 The nivel3 to set.
	 * @struts.validator type="required"
	 */
	public void setNivel3(String nivel3) {
		this.nivel3 = nivel3;
	}
	/**
	 * @return Returns the nivel4.
	 */
	public String getNivel4() {
		return nivel4;
	}
	/**
	 * @param nivel4 The nivel4 to set.
	 */
	public void setNivel4(String nivel4) {
		this.nivel4 = nivel4;
	}
	/**
	 * @return Returns the nivel5.
	 */
	public String getNivel5() {
		return nivel5;
	}
	/**
	 * @param nivel5 The nivel5 to set.
	 */
	public void setNivel5(String nivel5) {
		this.nivel5 = nivel5;
	}
	/**
	 * @return Returns the nivel6.
	 */
	public String getNivel6() {
		return nivel6;
	}
	/**
	 * @param nivel6 The nivel6 to set.
	 */
	public void setNivel6(String nivel6) {
		this.nivel6 = nivel6;
	}
	/**
	 * @return Returns the nombre1.
	 */
	public String getNombre1() {
		return nombre1;
	}
	/**
	 * @param nombre1 The nombre1 to set.
	 * @struts.validator type="required" 
	 */
	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}
	/**
	 * @return Returns the nombre2.
	 */
	public String getNombre2() {
		return nombre2;
	}
	/**
	 * @param nombre2 The nombre2 to set.
	 */
	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}
	/**
	 * @return Returns the nombreVia.
	 */
	public String getNombreVia() {
		return nombreVia;
	}
	/**
	 * @param nombreVia The nombreVia to set.
	 */
	public void setNombreVia(String nombreVia) {
		this.nombreVia = nombreVia;
	}
	
	/**
	 * @return the correVia
	 */
	public String getCorreVia() {
		return correVia;
	}

	/**
	 * @param correVia the correVia to set
	 */
	public void setCorreVia(String correVia) {
		this.correVia = correVia;
	}

	/**
	 * @return Returns the numeroDocumentoIdentidad.
	 */
	public String getNumeroDocumentoIdentidad() {
		return numeroDocumentoIdentidad;
	}
	/**
	 * @param numeroDocumentoIdentidad The numeroDocumentoIdentidad to set.
	 * @struts.validator type="required"
	 */
	public void setNumeroDocumentoIdentidad(String numeroDocumentoIdentidad) {
		this.numeroDocumentoIdentidad = numeroDocumentoIdentidad;
	}
	/**
	 * @return Returns the numeroPrincipal.
	 */
	public String getNumeroPrincipal() {
		return numeroPrincipal;
	}
	/**
	 * @param numeroPrincipal The numeroPrincipal to set.
	 */
	public void setNumeroPrincipal(String numeroPrincipal) {
		this.numeroPrincipal = numeroPrincipal;
	}
	/**
	 * @return Returns the observacionDireccion.
	 */
	public String getObservacionDireccion() {
		return observacionDireccion;
	}
	/**
	 * @param observacionDireccion The observacionDireccion to set.
	 */
	public void setObservacionDireccion(String observacionDireccion) {
		this.observacionDireccion = observacionDireccion;
	}
	/**
	 * @return Returns the oidPais.
	 */
	public String getOidPais() {
		return oidPais;
	}
	/**
	 * @param oidPais The oidPais to set.
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}
	/**
	 * @return Returns the oidPeriodo.
	 */
	public String getOidPeriodo() {
		return oidPeriodo;
	}
	/**
	 * @param oidPeriodo The oidPeriodo to set.
	 * @struts.validator type="required"
	 */
	public void setOidPeriodo(String oidPeriodo) {
		this.oidPeriodo = oidPeriodo;
	}
	/**
	 * @return Returns the sexo.
	 */
	public String getSexo() {
		return sexo;
	}
	/**
	 * @param sexo The sexo to set.
	 * @struts.validator type="required" 
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	/**
	 * @return Returns the subTipoCliente.
	 */
	public String getSubTipoCliente() {
		return subTipoCliente;
	}
	/**
	 * @param subTipoCliente The subTipoCliente to set.
	 */
	public void setSubTipoCliente(String subTipoCliente) {
		this.subTipoCliente = subTipoCliente;
	}
	/**
	 * @return Returns the tipoDocumentoIdentidad.
	 */
	public String getTipoDocumentoIdentidad() {
		return tipoDocumentoIdentidad;
	}
	/**
	 * @param tipoDocumentoIdentidad The tipoDocumentoIdentidad to set.
	 * @struts.validator type="required"
	 */
	public void setTipoDocumentoIdentidad(String tipoDocumentoIdentidad) {
		this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
	}
	/**
	 * @return Returns the tipoVia.
	 */
	public String getTipoVia() {
		return tipoVia;
	}
	/**
	 * @param tipoVia The tipoVia to set.
	 * @struts.validator type="required" 
	 */
	public void setTipoVia(String tipoVia) {
		this.tipoVia = tipoVia;
	}
	/**
	 * @return Returns the tratamiento.
	 */
	public String getTratamiento() {
		return tratamiento;
	}
	/**
	 * @param tratamiento The tratamiento to set.
	 * @struts.validator type="required"  
	 */
	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}
	/**
	 * @return Returns the descripcionNivel1.
	 */
	public String getDescripcionNivel1() {
		return descripcionNivel1;
	}
	/**
	 * @param descripcionNivel1 The descripcionNivel1 to set.
	 */
	public void setDescripcionNivel1(String descripcionNivel1) {
		this.descripcionNivel1 = descripcionNivel1;
	}
	/**
	 * @return Returns the descripcionNivel2.
	 */
	public String getDescripcionNivel2() {
		return descripcionNivel2;
	}
	/**
	 * @param descripcionNivel2 The descripcionNivel2 to set.
	 */
	public void setDescripcionNivel2(String descripcionNivel2) {
		this.descripcionNivel2 = descripcionNivel2;
	}
	/**
	 * @return Returns the descripcionNivel3.
	 */
	public String getDescripcionNivel3() {
		return descripcionNivel3;
	}
	/**
	 * @param descripcionNivel3 The descripcionNivel3 to set.
	 */
	public void setDescripcionNivel3(String descripcionNivel3) {
		this.descripcionNivel3 = descripcionNivel3;
	}
	/**
	 * @return Returns the descripcionNivel4.
	 */
	public String getDescripcionNivel4() {
		return descripcionNivel4;
	}
	/**
	 * @param descripcionNivel4 The descripcionNivel4 to set.
	 */
	public void setDescripcionNivel4(String descripcionNivel4) {
		this.descripcionNivel4 = descripcionNivel4;
	}
	/**
	 * @return Returns the descripcionNivel5.
	 */
	public String getDescripcionNivel5() {
		return descripcionNivel5;
	}
	/**
	 * @param descripcionNivel5 The descripcionNivel5 to set.
	 */
	public void setDescripcionNivel5(String descripcionNivel5) {
		this.descripcionNivel5 = descripcionNivel5;
	}
	/**
	 * @return Returns the descripcionNivel6.
	 */
	public String getDescripcionNivel6() {
		return descripcionNivel6;
	}
	/**
	 * @param descripcionNivel6 The descripcionNivel6 to set.
	 */
	public void setDescripcionNivel6(String descripcionNivel6) {
		this.descripcionNivel6 = descripcionNivel6;
	}
	/**
	 * @return Returns the totalNiveles.
	 */
	public String getTotalNiveles() {
		return totalNiveles;
	}
	/**
	 * @param totalNiveles The totalNiveles to set.
	 */
	public void setTotalNiveles(String totalNiveles) {
		this.totalNiveles = totalNiveles;
	}
	/**
	 * @return Returns the mostrarDireccion.
	 */
	public boolean isMostrarDireccion() {
		return mostrarDireccion;
	}
	/**
	 * @param mostrarDireccion The mostrarDireccion to set.
	 */
	public void setMostrarDireccion(boolean mostrarDireccion) {
		this.mostrarDireccion = mostrarDireccion;
	}
	/**
	 * @return Returns the mostrarUnidadAdministrativa.
	 */
	public boolean isMostrarUnidadAdministrativa() {
		return mostrarUnidadAdministrativa;
	}
	/**
	 * @param mostrarUnidadAdministrativa The mostrarUnidadAdministrativa to set.
	 */
	public void setMostrarUnidadAdministrativa(boolean mostrarUnidadAdministrativa) {
		this.mostrarUnidadAdministrativa = mostrarUnidadAdministrativa;
	}
	/**
	 * @return Returns the esCodigoClienteAutomatico.
	 */
	public boolean isEsCodigoClienteAutomatico() {
		return esCodigoClienteAutomatico;
	}
	/**
	 * @param esCodigoClienteAutomatico The esCodigoClienteAutomatico to set.
	 */
	public void setEsCodigoClienteAutomatico(boolean esCodigoClienteAutomatico) {
		this.esCodigoClienteAutomatico = esCodigoClienteAutomatico;
	}
	/**
	 * @return Returns the longitudCodigoCliente.
	 */
	public String getLongitudCodigoCliente() {
		return longitudCodigoCliente;
	}
	/**
	 * @param longitudCodigoCliente The longitudCodigoCliente to set.
	 */
	public void setLongitudCodigoCliente(String longitudCodigoCliente) {
		this.longitudCodigoCliente = longitudCodigoCliente;
	}
	/**
	 * @return Returns the codigoConsultoraRecomendante.
	 */
	public String getCodigoConsultoraRecomendante() {
		return codigoConsultoraRecomendante;
	}
	/**
	 * @param codigoConsultoraRecomendante The codigoConsultoraRecomendante to set.
	 */
	public void setCodigoConsultoraRecomendante(String codigoConsultoraRecomendante) {
		this.codigoConsultoraRecomendante = codigoConsultoraRecomendante;
	}
	/**
	 * @return Returns the gradoInstruccion.
	 */
	public String getGradoInstruccion() {
		return gradoInstruccion;
	}
	/**
	 * @param gradoInstruccion The gradoInstruccion to set.
	 */
	public void setGradoInstruccion(String gradoInstruccion) {
		this.gradoInstruccion = gradoInstruccion;
	}
	/**
	 * @return Returns the mail.
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail The mail to set.
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * @return Returns the nombreConsultoraRecomendante.
	 */
	public String getNombreConsultoraRecomendante() {
		return nombreConsultoraRecomendante;
	}
	/**
	 * @param nombreConsultoraRecomendante The nombreConsultoraRecomendante to set.
	 */
	public void setNombreConsultoraRecomendante(String nombreConsultoraRecomendante) {
		this.nombreConsultoraRecomendante = nombreConsultoraRecomendante;
	}
	/**
	 * @return Returns the oidConsultoraRecomendante.
	 */
	public String getOidConsultoraRecomendante() {
		return oidConsultoraRecomendante;
	}
	/**
	 * @param oidConsultoraRecomendante The oidConsultoraRecomendante to set.
	 */
	public void setOidConsultoraRecomendante(String oidConsultoraRecomendante) {
		this.oidConsultoraRecomendante = oidConsultoraRecomendante;
	}
	/**
	 * @return Returns the telefonoCasa.
	 */
	public String getTelefonoCasa() {
		return telefonoCasa;
	}
	/**
	 * @param telefonoCasa The telefonoCasa to set.
	 */
	public void setTelefonoCasa(String telefonoCasa) {
		this.telefonoCasa = telefonoCasa;
	}
	/**
	 * @return Returns the telefonoCelular.
	 */
	public String getTelefonoCelular() {
		return telefonoCelular;
	}
	/**
	 * @param telefonoCelular The telefonoCelular to set.
	 */
	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}
	/**
	 * @return Returns the cargo.
	 */
	public String getCargo() {
		return cargo;
	}
	/**
	 * @param cargo The cargo to set.
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	/**
	 * @return Returns the empresa.
	 */
	public String getEmpresa() {
		return empresa;
	}
	/**
	 * @param empresa The empresa to set.
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	/**
	 * @return Returns the nivel1CT.
	 */
	public String getNivel1CT() {
		return nivel1CT;
	}
	/**
	 * @param nivel1CT The nivel1CT to set.
	 */
	public void setNivel1CT(String nivel1CT) {
		this.nivel1CT = nivel1CT;
	}
	/**
	 * @return Returns the nivel2CT.
	 */
	public String getNivel2CT() {
		return nivel2CT;
	}
	/**
	 * @param nivel2CT The nivel2CT to set.
	 */
	public void setNivel2CT(String nivel2CT) {
		this.nivel2CT = nivel2CT;
	}
	/**
	 * @return Returns the nivel3CT.
	 */
	public String getNivel3CT() {
		return nivel3CT;
	}
	/**
	 * @param nivel3CT The nivel3CT to set.
	 */
	public void setNivel3CT(String nivel3CT) {
		this.nivel3CT = nivel3CT;
	}
	/**
	 * @return Returns the nivel4CT.
	 */
	public String getNivel4CT() {
		return nivel4CT;
	}
	/**
	 * @param nivel4CT The nivel4CT to set.
	 */
	public void setNivel4CT(String nivel4CT) {
		this.nivel4CT = nivel4CT;
	}
	/**
	 * @return Returns the nivel5CT.
	 */
	public String getNivel5CT() {
		return nivel5CT;
	}
	/**
	 * @param nivel5CT The nivel5CT to set.
	 */
	public void setNivel5CT(String nivel5CT) {
		this.nivel5CT = nivel5CT;
	}
	/**
	 * @return Returns the nivel6CT.
	 */
	public String getNivel6CT() {
		return nivel6CT;
	}
	/**
	 * @param nivel6CT The nivel6CT to set.
	 */
	public void setNivel6CT(String nivel6CT) {
		this.nivel6CT = nivel6CT;
	}
	/**
	 * @return Returns the nombreViaCT.
	 */
	public String getNombreViaCT() {
		return nombreViaCT;
	}
	/**
	 * @param nombreViaCT The nombreViaCT to set.
	 */
	public void setNombreViaCT(String nombreViaCT) {
		this.nombreViaCT = nombreViaCT;
	}
	/**
	 * @return Returns the numeroPrincipalCT.
	 */
	public String getNumeroPrincipalCT() {
		return numeroPrincipalCT;
	}
	/**
	 * @param numeroPrincipalCT The numeroPrincipalCT to set.
	 */
	public void setNumeroPrincipalCT(String numeroPrincipalCT) {
		this.numeroPrincipalCT = numeroPrincipalCT;
	}
	/**
	 * @return Returns the observacionDireccionCT.
	 */
	public String getObservacionDireccionCT() {
		return observacionDireccionCT;
	}
	/**
	 * @param observacionDireccionCT The observacionDireccionCT to set.
	 */
	public void setObservacionDireccionCT(String observacionDireccionCT) {
		this.observacionDireccionCT = observacionDireccionCT;
	}
	/**
	 * @return Returns the sueldoMensual.
	 */
	public String getSueldoMensual() {
		return sueldoMensual;
	}
	/**
	 * @param sueldoMensual The sueldoMensual to set.
	 */
	public void setSueldoMensual(String sueldoMensual) {
		this.sueldoMensual = sueldoMensual;
	}
	/**
	 * @return Returns the telefono.
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * @param telefono The telefono to set.
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * @return Returns the tipoViaCT.
	 */
	public String getTipoViaCT() {
		return tipoViaCT;
	}
	/**
	 * @param tipoViaCT The tipoViaCT to set.
	 */
	public void setTipoViaCT(String tipoViaCT) {
		this.tipoViaCT = tipoViaCT;
	}
	/**
	 * @return Returns the mostrarNumeroIdentidad.
	 */
	public boolean isMostrarNumeroIdentidad() {
		return mostrarNumeroIdentidad;
	}
	/**
	 * @param mostrarNumeroIdentidad The mostrarNumeroIdentidad to set.
	 */
	public void setMostrarNumeroIdentidad(boolean mostrarNumeroIdentidad) {
		this.mostrarNumeroIdentidad = mostrarNumeroIdentidad;
	}
	/**
	 * @return Returns the mostrarConsultoraRecomendante.
	 */
	public boolean isMostrarConsultoraRecomendante() {
		return mostrarConsultoraRecomendante;
	}
	/**
	 * @param mostrarConsultoraRecomendante The mostrarConsultoraRecomendante to set.
	 */
	public void setMostrarConsultoraRecomendante(
			boolean mostrarConsultoraRecomendante) {
		this.mostrarConsultoraRecomendante = mostrarConsultoraRecomendante;
	}
	/**
	 * @return Returns the clasificacion.
	 */
	public String getClasificacion() {
		return Clasificacion;
	}
	/**
	 * @param clasificacion The clasificacion to set.
	 */
	public void setClasificacion(String clasificacion) {
		Clasificacion = clasificacion;
	}
	/**
	 * @return Returns the mostrarClasificaciones.
	 */
	public boolean isMostrarClasificaciones() {
		return mostrarClasificaciones;
	}
	/**
	 * @param mostrarClasificaciones The mostrarClasificaciones to set.
	 */
	public void setMostrarClasificaciones(boolean mostrarClasificaciones) {
		this.mostrarClasificaciones = mostrarClasificaciones;
	}
	/**
	 * @return Returns the tipoClasificacion.
	 */
	public String getTipoClasificacion() {
		return tipoClasificacion;
	}
	/**
	 * @param tipoClasificacion The tipoClasificacion to set.
	 */
	public void setTipoClasificacion(String tipoClasificacion) {
		this.tipoClasificacion = tipoClasificacion;
	}
	/**
	 * @return Returns the oidTerritorioAdministrativo.
	 */
	public String getOidTerritorioAdministrativo() {
		return oidTerritorioAdministrativo;
	}
	/**
	 * @param oidTerritorioAdministrativo The oidTerritorioAdministrativo to set.
	 */
	public void setOidTerritorioAdministrativo(String oidTerritorioAdministrativo) {
		this.oidTerritorioAdministrativo = oidTerritorioAdministrativo;
	}
	/**
	 * @return Returns the codNivel2.
	 */
	public String getCodNivel2() {
		return codNivel2;
	}
	/**
	 * @param codNivel2 The codNivel2 to set.
	 */
	public void setCodNivel2(String codNivel2) {
		this.codNivel2 = codNivel2;
	}
	/**
	 * @return Returns the codNivel3.
	 */
	public String getCodNivel3() {
		return codNivel3;
	}
	/**
	 * @param codNivel3 The codNivel3 to set.
	 */
	public void setCodNivel3(String codNivel3) {
		this.codNivel3 = codNivel3;
	}
	/**
	 * @return Returns the codNivel4.
	 */
	public String getCodNivel4() {
		return codNivel4;
	}
	/**
	 * @param codNivel4 The codNivel4 to set.
	 */
	public void setCodNivel4(String codNivel4) {
		this.codNivel4 = codNivel4;
	}
	/**
	 * @return Returns the codNivel5.
	 */
	public String getCodNivel5() {
		return codNivel5;
	}
	/**
	 * @param codNivel5 The codNivel5 to set.
	 */
	public void setCodNivel5(String codNivel5) {
		this.codNivel5 = codNivel5;
	}
	/**
	 * @return Returns the codNivel6.
	 */
	public String getCodNivel6() {
		return codNivel6;
	}
	/**
	 * @param codNivel6 The codNivel6 to set.
	 */
	public void setCodNivel6(String codNivel6) {
		this.codNivel6 = codNivel6;
	}
	/**
	 * @return Returns the esDuplaCyzone.
	 */
	public boolean isEsDuplaCyzone() {
		return esDuplaCyzone;
	}
	/**
	 * @param esDuplaCyzone The esDuplaCyzone to set.
	 */
	public void setEsDuplaCyzone(boolean esDuplaCyzone) {
		this.esDuplaCyzone = esDuplaCyzone;
	}
	/**
	 * @return Returns the codigoConsultoraVinculo.
	 */
	public String getCodigoConsultoraVinculo() {
		return codigoConsultoraVinculo;
	}
	/**
	 * @param codigoConsultoraVinculo The codigoConsultoraVinculo to set.
	 */
	public void setCodigoConsultoraVinculo(String codigoConsultoraVinculo) {
		this.codigoConsultoraVinculo = codigoConsultoraVinculo;
	}
	/**
	 * @return Returns the fechaDesde.
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}
	/**
	 * @param fechaDesde The fechaDesde to set.
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	/**
	 * @return Returns the fechaHasta.
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}
	/**
	 * @param fechaHasta The fechaHasta to set.
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	/**
	 * @return Returns the mostrarConsultoraVinculo.
	 */
	public boolean isMostrarConsultoraVinculo() {
		return mostrarConsultoraVinculo;
	}
	/**
	 * @param mostrarConsultoraVinculo The mostrarConsultoraVinculo to set.
	 */
	public void setMostrarConsultoraVinculo(boolean mostrarConsultoraVinculo) {
		this.mostrarConsultoraVinculo = mostrarConsultoraVinculo;
	}
	/**
	 * @return Returns the nombreConsultoraVinculo.
	 */
	public String getNombreConsultoraVinculo() {
		return nombreConsultoraVinculo;
	}
	/**
	 * @param nombreConsultoraVinculo The nombreConsultoraVinculo to set.
	 */
	public void setNombreConsultoraVinculo(String nombreConsultoraVinculo) {
		this.nombreConsultoraVinculo = nombreConsultoraVinculo;
	}
	/**
	 * @return Returns the oidConsultoraVinculo.
	 */
	public String getOidConsultoraVinculo() {
		return oidConsultoraVinculo;
	}
	/**
	 * @param oidConsultoraVinculo The oidConsultoraVinculo to set.
	 */
	public void setOidConsultoraVinculo(String oidConsultoraVinculo) {
		this.oidConsultoraVinculo = oidConsultoraVinculo;
	}
	
	/**
	 * @return Returns the edadMinima
	 */
	public String getEdadMinima() {
		return edadMinima;
	}
	/**
	 * @param edadMinima The edadMinima to set
	 */
	public void setEdadMinima(String edadMinima) {
		this.edadMinima = edadMinima;
	}
	/**
	 * @return Returns the edadMaxima
	 */
	public String getEdadMaxima() {
		return edadMaxima;
	}
	/**
	 * @param edadMaxima The edadMaxima to set
	 */
	public void setEdadMaxima(String edadMaxima) {
		this.edadMaxima = edadMaxima;
	}
	/**
	 * @return Returns the fechaActual.
	 */
	public String getFechaActual() {
		return fechaActual;
	}
	/**
	 * @param fechaActual The fechaActual to set.
	 */
	public void setFechaActual(String fechaActual) {
		this.fechaActual = fechaActual;
	}
	/**
	 * @return Returns the graboOK.
	 */
	public boolean isGraboOK() {
		return graboOK;
	}
	/**
	 * @param graboOK The graboOK to set.
	 */
	public void setGraboOK(boolean graboOK) {
		this.graboOK = graboOK;
	}
	/**
	 * @return Returns the oidNuevoCliente.
	 */
	public String getOidNuevoCliente() {
		return oidNuevoCliente;
	}
	/**
	 * @param oidNuevoCliente The oidNuevoCliente to set.
	 */
	public void setOidNuevoCliente(String oidNuevoCliente) {
		this.oidNuevoCliente = oidNuevoCliente;
	}
	/**
	 * @return Returns the codNivel2CT.
	 */
	public String getCodNivel2CT() {
		return codNivel2CT;
	}
	/**
	 * @param codNivel2CT The codNivel2CT to set.
	 */
	public void setCodNivel2CT(String codNivel2CT) {
		this.codNivel2CT = codNivel2CT;
	}
	/**
	 * @return Returns the codNivel3CT.
	 */
	public String getCodNivel3CT() {
		return codNivel3CT;
	}
	/**
	 * @param codNivel3CT The codNivel3CT to set.
	 */
	public void setCodNivel3CT(String codNivel3CT) {
		this.codNivel3CT = codNivel3CT;
	}
	/**
	 * @return Returns the codNivel4CT.
	 */
	public String getCodNivel4CT() {
		return codNivel4CT;
	}
	/**
	 * @param codNivel4CT The codNivel4CT to set.
	 */
	public void setCodNivel4CT(String codNivel4CT) {
		this.codNivel4CT = codNivel4CT;
	}
	/**
	 * @return Returns the codNivel5CT.
	 */
	public String getCodNivel5CT() {
		return codNivel5CT;
	}
	/**
	 * @param codNivel5CT The codNivel5CT to set.
	 */
	public void setCodNivel5CT(String codNivel5CT) {
		this.codNivel5CT = codNivel5CT;
	}
	/**
	 * @return Returns the codNivel6CT.
	 */
	public String getCodNivel6CT() {
		return codNivel6CT;
	}
	/**
	 * @param codNivel6CT The codNivel6CT to set.
	 */
	public void setCodNivel6CT(String codNivel6CT) {
		this.codNivel6CT = codNivel6CT;
	}
	/**
	 * @return Returns the controlFoco.
	 */
	public String getControlFoco() {
		return controlFoco;
	}
	/**
	 * @param controlFoco The controlFoco to set.
	 */
	public void setControlFoco(String controlFoco) {
		this.controlFoco = controlFoco;
	}
	/**
	 * @return Returns the mostrarCodigoEmpleado.
	 */
	public boolean isMostrarCodigoEmpleado() {
		return mostrarCodigoEmpleado;
	}
	/**
	 * @param mostrarCodigoEmpleado The mostrarCodigoEmpleado to set.
	 */
	public void setMostrarCodigoEmpleado(boolean mostrarCodigoEmpleado) {
		this.mostrarCodigoEmpleado = mostrarCodigoEmpleado;
	}
	/**
	 * @return Returns the agregarClasificacionDefault.
	 */
	public boolean isAgregarClasificacionDefault() {
		return agregarClasificacionDefault;
	}
	/**
	 * @param agregarClasificacionDefault The agregarClasificacionDefault to set.
	 */
	public void setAgregarClasificacionDefault(boolean agregarClasificacionDefault) {
		this.agregarClasificacionDefault = agregarClasificacionDefault;
	}
	/**
	 * @return Returns the confirmacionTerritorio.
	 */
	public String getConfirmacionTerritorio() {
		return confirmacionTerritorio;
	}
	/**
	 * @param confirmacionTerritorio The confirmacionTerritorio to set.
	 */
	public void setConfirmacionTerritorio(String confirmacionTerritorio) {
		this.confirmacionTerritorio = confirmacionTerritorio;
	}
	/**
	 * @return Returns the mensajeConfirmacion.
	 */
	public String getMensajeConfirmacion() {
		return mensajeConfirmacion;
	}
	/**
	 * @param mensajeConfirmacion The mensajeConfirmacion to set.
	 */
	public void setMensajeConfirmacion(String mensajeConfirmacion) {
		this.mensajeConfirmacion = mensajeConfirmacion;
	}
	/**
	 * @return Returns the oidTerritorio.
	 */
	public String getOidTerritorio() {
		return oidTerritorio;
	}
	/**
	 * @param oidTerritorio The oidTerritorio to set.
	 */
	public void setOidTerritorio(String oidTerritorio) {
		this.oidTerritorio = oidTerritorio;
	}
	/**
	 * @return Returns the nacionalidad.
	 */
	public String getNacionalidad() {
		return nacionalidad;
	}
	/**
	 * @param nacionalidad The nacionalidad to set.
	 */
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	/**
	 * @return Returns the indicadoObligatorioNacionalidad.
	 */
	public boolean isIndicadoObligatorioNacionalidad() {
		return indicadoObligatorioNacionalidad;
	}
	/**
	 * @param indicadoObligatorioNacionalidad The indicadoObligatorioNacionalidad to set.
	 */
	public void setIndicadoObligatorioNacionalidad(
			boolean indicadoObligatorioNacionalidad) {
		this.indicadoObligatorioNacionalidad = indicadoObligatorioNacionalidad;
	}
	/**
	 * @return Returns the observaciones1.
	 */
	public String getObservaciones1() {
		return observaciones1;
	}
	/**
	 * @param observaciones1 The observaciones1 to set.
	 */
	public void setObservaciones1(String observaciones1) {
		this.observaciones1 = observaciones1;
	}
	/**
	 * @return Returns the observaciones2.
	 */
	public String getObservaciones2() {
		return observaciones2;
	}
	/**
	 * @param observaciones2 The observaciones2 to set.
	 */
	public void setObservaciones2(String observaciones2) {
		this.observaciones2 = observaciones2;
	}
	/**
	 * @return Returns the observaciones3.
	 */
	public String getObservaciones3() {
		return observaciones3;
	}
	/**
	 * @param observaciones3 The observaciones3 to set.
	 */
	public void setObservaciones3(String observaciones3) {
		this.observaciones3 = observaciones3;
	}
	/**
	 * @return Returns the indicadoObligatorioGradoInstruccion.
	 */
	public boolean isIndicadoObligatorioGradoInstruccion() {
		return indicadoObligatorioGradoInstruccion;
	}
	/**
	 * @param indicadoObligatorioGradoInstruccion The indicadoObligatorioGradoInstruccion to set.
	 */
	public void setIndicadoObligatorioGradoInstruccion(
			boolean indicadoObligatorioGradoInstruccion) {
		this.indicadoObligatorioGradoInstruccion = indicadoObligatorioGradoInstruccion;
	}
	/**
	 * @return Returns the digitoControl.
	 */
	public String getDigitoControl() {
		return digitoControl;
	}
	/**
	 * @param digitoControl The digitoControl to set.
	 */
	public void setDigitoControl(String digitoControl) {
		this.digitoControl = digitoControl;
	}
	/**
	 * @return Returns the indicadorDireccionTrabajo.
	 */
	public String getIndicadorDireccionTrabajo() {
		return indicadorDireccionTrabajo;
	}
	/**
	 * @param indicadorDireccionTrabajo The indicadorDireccionTrabajo to set.
	 */
	public void setIndicadorDireccionTrabajo(String indicadorDireccionTrabajo) {
		this.indicadorDireccionTrabajo = indicadorDireccionTrabajo;
	}
	/**
	 * @return Returns the indicadoObligatorioTratamiento.
	 */
	public boolean isIndicadoObligatorioTratamiento() {
		return indicadoObligatorioTratamiento;
	}
	/**
	 * @param indicadoObligatorioTratamiento The indicadoObligatorioTratamiento to set.
	 */
	public void setIndicadoObligatorioTratamiento(
			boolean indicadoObligatorioTratamiento) {
		this.indicadoObligatorioTratamiento = indicadoObligatorioTratamiento;
	}
	/**
	 * @return Returns the indicadorObservaciones.
	 */
	public String getIndicadorObservaciones() {
		return indicadorObservaciones;
	}
	/**
	 * @param indicadorObservaciones The indicadorObservaciones to set.
	 */
	public void setIndicadorObservaciones(String indicadorObservaciones) {
		this.indicadorObservaciones = indicadorObservaciones;
	}
	/**
	 * @return Returns the mensajeGrabarAlert.
	 */
	public String getMensajeGrabarAlert() {
		return mensajeGrabarAlert;
	}
	/**
	 * @param mensajeGrabarAlert The mensajeGrabarAlert to set.
	 */
	public void setMensajeGrabarAlert(String mensajeGrabarAlert) {
		this.mensajeGrabarAlert = mensajeGrabarAlert;
	}
	/**
	 * @return Returns the longitudTipoDocumento.
	 */
	public String getLongitudTipoDocumento() {
		return longitudTipoDocumento;
	}
	/**
	 * @param longitudTipoDocumento The longitudTipoDocumento to set.
	 */
	public void setLongitudTipoDocumento(String longitudTipoDocumento) {
		this.longitudTipoDocumento = longitudTipoDocumento;
	}
	/**
	 * @return Returns the tipoDireccion.
	 */
	public String getTipoDireccion() {
		return tipoDireccion;
	}
	/**
	 * @param tipoDireccion The tipoDireccion to set.
	 */
	public void setTipoDireccion(String tipoDireccion) {
		this.tipoDireccion = tipoDireccion;
	}
	/**
	 * @return the oidPeriodoConcurso
	 */
	public String getOidPeriodoConcurso() {
		return oidPeriodoConcurso;
	}
	/**
	 * @param oidPeriodoConcurso the oidPeriodoConcurso to set
	 */
	public void setOidPeriodoConcurso(String oidPeriodoConcurso) {
		this.oidPeriodoConcurso = oidPeriodoConcurso;
	}
	/**
	 * @return the primeraVezDespliegueDireccionEntrega
	 */
	public boolean isPrimeraVezDespliegueDireccionEntrega() {
		return primeraVezDespliegueDireccionEntrega;
	}
	/**
	 * @param primeraVezDespliegueDireccionEntrega the primeraVezDespliegueDireccionEntrega to set
	 */
	public void setPrimeraVezDespliegueDireccionEntrega(
			boolean primeraVezDespliegueDireccionEntrega) {
		this.primeraVezDespliegueDireccionEntrega = primeraVezDespliegueDireccionEntrega;
	}
	/**
	 * @return the mostrarPantallaPremios
	 */
	public boolean isMostrarPantallaPremios() {
		return mostrarPantallaPremios;
	}
	/**
	 * @param mostrarPantallaPremios the mostrarPantallaPremios to set
	 */
	public void setMostrarPantallaPremios(boolean mostrarPantallaPremios) {
		this.mostrarPantallaPremios = mostrarPantallaPremios;
	}
	/**
	 * @return the telefonoCasaDireccionEntrega
	 */
	public String getTelefonoCasaDireccionEntrega() {
		return telefonoCasaDireccionEntrega;
	}
	/**
	 * @param telefonoCasaDireccionEntrega the telefonoCasaDireccionEntrega to set
	 */
	public void setTelefonoCasaDireccionEntrega(String telefonoCasaDireccionEntrega) {
		this.telefonoCasaDireccionEntrega = telefonoCasaDireccionEntrega;
	}
	/**
	 * @return the telefonoCelularDireccionEntrega
	 */
	public String getTelefonoCelularDireccionEntrega() {
		return telefonoCelularDireccionEntrega;
	}
	/**
	 * @param telefonoCelularDireccionEntrega the telefonoCelularDireccionEntrega to set
	 */
	public void setTelefonoCelularDireccionEntrega(
			String telefonoCelularDireccionEntrega) {
		this.telefonoCelularDireccionEntrega = telefonoCelularDireccionEntrega;
	}	
    public void reset() {
    	//Referencia Familiar
    	this.apellido1RefFamiliar="";
    	this.apellido2RefFamiliar="";
    	this.nombre1RefFamiliar="";
    	this.nombre2RefFamiliar="";
    	this.direccionRefFamiliar="";
    	this.telefonoCasaRefFamiliar="";
    	this.telefonoCelRefFamiliar="";
    	this.codigoTipoVinculoRefFamiliar="";
    	//Referencia No Familiar
    	this.apellido1RefNoFamiliar="";
    	this.apellido2RefNoFamiliar="";
    	this.nombre1RefNoFamiliar="";
    	this.nombre2RefNoFamiliar="";
    	this.direccionRefNoFamiliar="";
    	this.telefonoCasaRefNoFamiliar="";
    	this.telefonoCelRefNoFamiliar="";
    	this.codigoTipoVinculoRefNoFamiliar="";	
    	//Referencia A Aval
    	this.apellido1Aval="";
    	this.apellido2Aval="";
    	this.nombre1Aval="";
    	this.nombre2Aval="";
    	this.estado="";
    	this.municipio="";
    	this.parroquia="";
    	this.direccionAval="";
    	this.telefonoCasaAval="";
    	this.telefonoCelAval="";
    	this.codigoTipoVinculoAval="";	    	
    	this.codigoDepartamentoAval="";
    	this.codigoProvinciaAval="";
    	this.codigoDistritoAval="";   
    	this.oidTipoDocumentoAval="";
    	this.numeroDocumentoAval="";	    	
    	this.chkReferencias = Constants.NUMERO_CERO;
    	this.indicadorCompromiso = Constants.NUMERO_CERO;
    	
    	/* INI SA COS-SiCC-2013-0031 */
    	this.indicadorImpresionPaqDoc = Constants.NO;
    	/* FIN SA COS-SiCC-2013-0031 */
    	
    	this.valorIndicadorFactElect = Constants.UNO;
    }
	/**
	 * @return the longitudCodigoZona
	 */
	public String getLongitudCodigoZona() {
		return longitudCodigoZona;
	}
	/**
	 * @param longitudCodigoZona the longitudCodigoZona to set
	 */
	public void setLongitudCodigoZona(String longitudCodigoZona) {
		this.longitudCodigoZona = longitudCodigoZona;
	}
	/**
	 * @return the mostrarTipoVia
	 */
	public boolean isMostrarTipoVia() {
		return mostrarTipoVia;
	}
	/**
	 * @param mostrarTipoVia the mostrarTipoVia to set
	 */
	public void setMostrarTipoVia(boolean mostrarTipoVia) {
		this.mostrarTipoVia = mostrarTipoVia;
	}
	/**
	 * @return the mostrarNumeroPrincipal
	 */
	public boolean isMostrarNumeroPrincipal() {
		return mostrarNumeroPrincipal;
	}
	/**
	 * @param mostrarNumeroPrincipal the mostrarNumeroPrincipal to set
	 */
	public void setMostrarNumeroPrincipal(boolean mostrarNumeroPrincipal) {
		this.mostrarNumeroPrincipal = mostrarNumeroPrincipal;
	}
	/**
	 * @return the permitirModificarUbigeo
	 */
	public boolean isPermitirModificarUbigeo() {
		return permitirModificarUbigeo;
	}
	/**
	 * @param permitirModificarUbigeo the permitirModificarUbigeo to set
	 */
	public void setPermitirModificarUbigeo(boolean permitirModificarUbigeo) {
		this.permitirModificarUbigeo = permitirModificarUbigeo;
	}
	/**
	 * @return the mostrarUbigeoEntrega
	 */
	public boolean isMostrarUbigeoEntrega() {
		return mostrarUbigeoEntrega;
	}
	/**
	 * @param mostrarUbigeoEntrega the mostrarUbigeoEntrega to set
	 */
	public void setMostrarUbigeoEntrega(boolean mostrarUbigeoEntrega) {
		this.mostrarUbigeoEntrega = mostrarUbigeoEntrega;
	}
	/**
	 * @return the codNivel1
	 */
	public String getCodNivel1() {
		return codNivel1;
	}
	/**
	 * @param codNivel1 the codNivel1 to set
	 */
	public void setCodNivel1(String codNivel1) {
		this.codNivel1 = codNivel1;
	}
	/**
	 * @return the indicadorClasificaciones
	 */
	public String getIndicadorClasificaciones() {
		return indicadorClasificaciones;
	}
	/**
	 * @param indicadorClasificaciones the indicadorClasificaciones to set
	 */
	public void setIndicadorClasificaciones(String indicadorClasificaciones) {
		this.indicadorClasificaciones = indicadorClasificaciones;
	}
	/**
	 * @return the longitudTipoDocumentoAval
	 */
	public String getLongitudTipoDocumentoAval() {
		return longitudTipoDocumentoAval;
	}
	/**
	 * @param longitudTipoDocumentoAval the longitudTipoDocumentoAval to set
	 */
	public void setLongitudTipoDocumentoAval(String longitudTipoDocumentoAval) {
		this.longitudTipoDocumentoAval = longitudTipoDocumentoAval;
	}
	/**
	 * @return the primeraVezDespliegueReferencias
	 */
	public boolean isPrimeraVezDespliegueReferencias() {
		return primeraVezDespliegueReferencias;
	}
	/**
	 * @param primeraVezDespliegueReferencias the primeraVezDespliegueReferencias to set
	 */
	public void setPrimeraVezDespliegueReferencias(
			boolean primeraVezDespliegueReferencias) {
		this.primeraVezDespliegueReferencias = primeraVezDespliegueReferencias;
	}
	/**
	 * @return the aprobarAvaladas
	 */
	public boolean isAprobarAvaladas() {
		return aprobarAvaladas;
	}
	/**
	 * @param aprobarAvaladas the aprobarAvaladas to set
	 */
	public void setAprobarAvaladas(boolean aprobarAvaladas) {
		this.aprobarAvaladas = aprobarAvaladas;
	}
	/**
	 * @return the permitirCompletarCerosIdentidad
	 */
	public boolean isPermitirCompletarCerosIdentidad() {
		return permitirCompletarCerosIdentidad;
	}
	/**
	 * @param permitirCompletarCerosIdentidad the permitirCompletarCerosIdentidad to set
	 */
	public void setPermitirCompletarCerosIdentidad(
			boolean permitirCompletarCerosIdentidad) {
		this.permitirCompletarCerosIdentidad = permitirCompletarCerosIdentidad;
	}
	/**
	 * @return the tipoDocumentoIdentidad2
	 */
	public String getTipoDocumentoIdentidad2() {
		return tipoDocumentoIdentidad2;
	}
	/**
	 * @param tipoDocumentoIdentidad2 the tipoDocumentoIdentidad2 to set
	 */
	public void setTipoDocumentoIdentidad2(String tipoDocumentoIdentidad2) {
		this.tipoDocumentoIdentidad2 = tipoDocumentoIdentidad2;
	}
	/**
	 * @return the numeroDocumentoIdentidad2
	 */
	public String getNumeroDocumentoIdentidad2() {
		return numeroDocumentoIdentidad2;
	}
	/**
	 * @param numeroDocumentoIdentidad2 the numeroDocumentoIdentidad2 to set
	 */
	public void setNumeroDocumentoIdentidad2(String numeroDocumentoIdentidad2) {
		this.numeroDocumentoIdentidad2 = numeroDocumentoIdentidad2;
	}
	/**
	 * @return the mostrarSegundoDocumento
	 */
	public boolean isMostrarSegundoDocumento() {
		return mostrarSegundoDocumento;
	}
	/**
	 * @param mostrarSegundoDocumento the mostrarSegundoDocumento to set
	 */
	public void setMostrarSegundoDocumento(boolean mostrarSegundoDocumento) {
		this.mostrarSegundoDocumento = mostrarSegundoDocumento;
	}
	/**
	 * @return the longitudTipoDocumento2
	 */
	public String getLongitudTipoDocumento2() {
		return longitudTipoDocumento2;
	}
	/**
	 * @param longitudTipoDocumento2 the longitudTipoDocumento2 to set
	 */
	public void setLongitudTipoDocumento2(String longitudTipoDocumento2) {
		this.longitudTipoDocumento2 = longitudTipoDocumento2;
	}
	/**
	 * @return the cadenaCaracteresV1
	 */
	public String getCadenaCaracteresV1() {
		return cadenaCaracteresV1;
	}
	/**
	 * @param cadenaCaracteresV1 the cadenaCaracteresV1 to set
	 */
	public void setCadenaCaracteresV1(String cadenaCaracteresV1) {
		this.cadenaCaracteresV1 = cadenaCaracteresV1;
	}
	/**
	 * @return the cadenaCaracteresV2
	 */
	public String getCadenaCaracteresV2() {
		return cadenaCaracteresV2;
	}
	/**
	 * @param cadenaCaracteresV2 the cadenaCaracteresV2 to set
	 */
	public void setCadenaCaracteresV2(String cadenaCaracteresV2) {
		this.cadenaCaracteresV2 = cadenaCaracteresV2;
	}
	/**
	 * @return the cadenaCaracteresV3
	 */
	public String getCadenaCaracteresV3() {
		return cadenaCaracteresV3;
	}
	/**
	 * @param cadenaCaracteresV3 the cadenaCaracteresV3 to set
	 */
	public void setCadenaCaracteresV3(String cadenaCaracteresV3) {
		this.cadenaCaracteresV3 = cadenaCaracteresV3;
	}
	/**
	 * @return the cadenaCaracteresNV1
	 */
	public String getCadenaCaracteresNV1() {
		return cadenaCaracteresNV1;
	}
	/**
	 * @param cadenaCaracteresNV1 the cadenaCaracteresNV1 to set
	 */
	public void setCadenaCaracteresNV1(String cadenaCaracteresNV1) {
		this.cadenaCaracteresNV1 = cadenaCaracteresNV1;
	}
	/**
	 * @return the cadenaCaracteresNV2
	 */
	public String getCadenaCaracteresNV2() {
		return cadenaCaracteresNV2;
	}
	/**
	 * @param cadenaCaracteresNV2 the cadenaCaracteresNV2 to set
	 */
	public void setCadenaCaracteresNV2(String cadenaCaracteresNV2) {
		this.cadenaCaracteresNV2 = cadenaCaracteresNV2;
	}
	/**
	 * @return the cadenaCaracteresNV3
	 */
	public String getCadenaCaracteresNV3() {
		return cadenaCaracteresNV3;
	}
	/**
	 * @param cadenaCaracteresNV3 the cadenaCaracteresNV3 to set
	 */
	public void setCadenaCaracteresNV3(String cadenaCaracteresNV3) {
		this.cadenaCaracteresNV3 = cadenaCaracteresNV3;
	}
	/**
	 * @return the telefonoReferencia
	 */
	public String getTelefonoReferencia() {
		return telefonoReferencia;
	}
	/**
	 * @param telefonoReferencia the telefonoReferencia to set
	 */
	public void setTelefonoReferencia(String telefonoReferencia) {
		this.telefonoReferencia = telefonoReferencia;
	}
	/**
	 * @return the codigoAnterior
	 */
	public String getCodigoAnterior() {
		return codigoAnterior;
	}
	/**
	 * @param codigoAnterior the codigoAnterior to set
	 */
	public void setCodigoAnterior(String codigoAnterior) {
		this.codigoAnterior = codigoAnterior;
	}
	/**
	 * @return the mostrarBarrio
	 */
	public boolean isMostrarBarrio() {
		return mostrarBarrio;
	}
	/**
	 * @param mostrarBarrio the mostrarBarrio to set
	 */
	public void setMostrarBarrio(boolean mostrarBarrio) {
		this.mostrarBarrio = mostrarBarrio;
	}
	/**
	 * @return the barrio
	 */
	public String getBarrio() {
		return barrio;
	}
	/**
	 * @param barrio the barrio to set
	 */
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	/**
	 * @return the barrioCT
	 */
	public String getBarrioCT() {
		return barrioCT;
	}
	/**
	 * @param barrioCT the barrioCT to set
	 */
	public void setBarrioCT(String barrioCT) {
		this.barrioCT = barrioCT;
	}
	/**
	 * @return the barrioRefFamiliar
	 */
	public String getBarrioRefFamiliar() {
		return barrioRefFamiliar;
	}
	/**
	 * @param barrioRefFamiliar the barrioRefFamiliar to set
	 */
	public void setBarrioRefFamiliar(String barrioRefFamiliar) {
		this.barrioRefFamiliar = barrioRefFamiliar;
	}
	/**
	 * @return the barrioRefNoFamiliar
	 */
	public String getBarrioRefNoFamiliar() {
		return barrioRefNoFamiliar;
	}
	/**
	 * @param barrioRefNoFamiliar the barrioRefNoFamiliar to set
	 */
	public void setBarrioRefNoFamiliar(String barrioRefNoFamiliar) {
		this.barrioRefNoFamiliar = barrioRefNoFamiliar;
	}
	/**
	 * @return the barrioAval
	 */
	public String getBarrioAval() {
		return barrioAval;
	}
	/**
	 * @param barrioAval the barrioAval to set
	 */
	public void setBarrioAval(String barrioAval) {
		this.barrioAval = barrioAval;
	}
	/**
	 * @return the tipoCutis
	 */
	public String getTipoCutis() {
		return tipoCutis;
	}
	/**
	 * @param tipoCutis the tipoCutis to set
	 */
	public void setTipoCutis(String tipoCutis) {
		this.tipoCutis = tipoCutis;
	}
	/**
	 * @return the validarCaracteres1
	 */
	public String getValidarCaracteres1() {
		return validarCaracteres1;
	}
	/**
	 * @param validarCaracteres1 the validarCaracteres1 to set
	 */
	public void setValidarCaracteres1(String validarCaracteres1) {
		this.validarCaracteres1 = validarCaracteres1;
	}
	/**
	 * @return the validarCaracteres2
	 */
	public String getValidarCaracteres2() {
		return validarCaracteres2;
	}
	/**
	 * @param validarCaracteres2 the validarCaracteres2 to set
	 */
	public void setValidarCaracteres2(String validarCaracteres2) {
		this.validarCaracteres2 = validarCaracteres2;
	}
	/**
	 * @return the validarCaracteres3
	 */
	public String getValidarCaracteres3() {
		return validarCaracteres3;
	}
	/**
	 * @param validarCaracteres3 the validarCaracteres3 to set
	 */
	public void setValidarCaracteres3(String validarCaracteres3) {
		this.validarCaracteres3 = validarCaracteres3;
	}
	/**
	 * @return the validarCaracteresIdentidad
	 */
	public String getValidarCaracteresIdentidad() {
		return validarCaracteresIdentidad;
	}
	/**
	 * @param validarCaracteresIdentidad the validarCaracteresIdentidad to set
	 */
	public void setValidarCaracteresIdentidad(String validarCaracteresIdentidad) {
		this.validarCaracteresIdentidad = validarCaracteresIdentidad;
	}
	/**
	 * @return the cadenaCaracteresVIdentidad
	 */
	public String getCadenaCaracteresVIdentidad() {
		return cadenaCaracteresVIdentidad;
	}
	/**
	 * @param cadenaCaracteresVIdentidad the cadenaCaracteresVIdentidad to set
	 */
	public void setCadenaCaracteresVIdentidad(String cadenaCaracteresVIdentidad) {
		this.cadenaCaracteresVIdentidad = cadenaCaracteresVIdentidad;
	}
	/**
	 * @return the cadenaCaracteresNVIdentidad
	 */
	public String getCadenaCaracteresNVIdentidad() {
		return cadenaCaracteresNVIdentidad;
	}
	/**
	 * @param cadenaCaracteresNVIdentidad the cadenaCaracteresNVIdentidad to set
	 */
	public void setCadenaCaracteresNVIdentidad(String cadenaCaracteresNVIdentidad) {
		this.cadenaCaracteresNVIdentidad = cadenaCaracteresNVIdentidad;
	}
	/**
	 * @return the permitirComenzarCerosIdentidad
	 */
	public boolean isPermitirComenzarCerosIdentidad() {
		return permitirComenzarCerosIdentidad;
	}
	/**
	 * @param permitirComenzarCerosIdentidad the permitirComenzarCerosIdentidad to set
	 */
	public void setPermitirComenzarCerosIdentidad(
			boolean permitirComenzarCerosIdentidad) {
		this.permitirComenzarCerosIdentidad = permitirComenzarCerosIdentidad;
	}
	/**
	 * @return the otrasMarcas
	 */
	public String getOtrasMarcas() {
		return otrasMarcas;
	}
	/**
	 * @param otrasMarcas the otrasMarcas to set
	 */
	public void setOtrasMarcas(String otrasMarcas) {
		this.otrasMarcas = otrasMarcas;
	}
	/**
	 * @return the codigoCiudad
	 */
	public String getCodigoCiudad() {
		return codigoCiudad;
	}
	/**
	 * @param codigoCiudad the codigoCiudad to set
	 */
	public void setCodigoCiudad(String codigoCiudad) {
		this.codigoCiudad = codigoCiudad;
	}
	/**
	 * @return the villaPoblacion
	 */
	public String getVillaPoblacion() {
		return villaPoblacion;
	}
	/**
	 * @param villaPoblacion the villaPoblacion to set
	 */
	public void setVillaPoblacion(String villaPoblacion) {
		this.villaPoblacion = villaPoblacion;
	}
	/**
	 * @return the codigoCiudadCT
	 */
	public String getCodigoCiudadCT() {
		return codigoCiudadCT;
	}
	/**
	 * @param codigoCiudadCT the codigoCiudadCT to set
	 */
	public void setCodigoCiudadCT(String codigoCiudadCT) {
		this.codigoCiudadCT = codigoCiudadCT;
	}
	/**
	 * @return the villaPoblacionCT
	 */
	public String getVillaPoblacionCT() {
		return villaPoblacionCT;
	}
	/**
	 * @param villaPoblacionCT the villaPoblacionCT to set
	 */
	public void setVillaPoblacionCT(String villaPoblacionCT) {
		this.villaPoblacionCT = villaPoblacionCT;
	}

	/**
	 * @return the mostrarCiudad
	 */
	public boolean isMostrarCiudad() {
		return mostrarCiudad;
	}
	/**
	 * @param mostrarCiudad the mostrarCiudad to set
	 */
	public void setMostrarCiudad(boolean mostrarCiudad) {
		this.mostrarCiudad = mostrarCiudad;
	}
	/**
	 * @return the mostrarVillaPoblacion
	 */
	public boolean isMostrarVillaPoblacion() {
		return mostrarVillaPoblacion;
	}
	/**
	 * @param mostrarVillaPoblacion the mostrarVillaPoblacion to set
	 */
	public void setMostrarVillaPoblacion(boolean mostrarVillaPoblacion) {
		this.mostrarVillaPoblacion = mostrarVillaPoblacion;
	}

	/**
	 * @return the tipoViaVacaciones
	 */
	public String getTipoViaVacaciones() {
		return tipoViaVacaciones;
	}

	/**
	 * @param tipoViaVacaciones the tipoViaVacaciones to set
	 */
	public void setTipoViaVacaciones(String tipoViaVacaciones) {
		this.tipoViaVacaciones = tipoViaVacaciones;
	}

	/**
	 * @return the numeroPrincipalVacaciones
	 */
	public String getNumeroPrincipalVacaciones() {
		return numeroPrincipalVacaciones;
	}

	/**
	 * @param numeroPrincipalVacaciones the numeroPrincipalVacaciones to set
	 */
	public void setNumeroPrincipalVacaciones(String numeroPrincipalVacaciones) {
		this.numeroPrincipalVacaciones = numeroPrincipalVacaciones;
	}

	/**
	 * @return the nombreViaVacaciones
	 */
	public String getNombreViaVacaciones() {
		return nombreViaVacaciones;
	}

	/**
	 * @param nombreViaVacaciones the nombreViaVacaciones to set
	 */
	public void setNombreViaVacaciones(String nombreViaVacaciones) {
		this.nombreViaVacaciones = nombreViaVacaciones;
	}

	/**
	 * @return the observacionDireccionVacaciones
	 */
	public String getObservacionDireccionVacaciones() {
		return observacionDireccionVacaciones;
	}

	/**
	 * @param observacionDireccionVacaciones the observacionDireccionVacaciones to set
	 */
	public void setObservacionDireccionVacaciones(
			String observacionDireccionVacaciones) {
		this.observacionDireccionVacaciones = observacionDireccionVacaciones;
	}

	/**
	 * @return the barrioVacaciones
	 */
	public String getBarrioVacaciones() {
		return barrioVacaciones;
	}

	/**
	 * @param barrioVacaciones the barrioVacaciones to set
	 */
	public void setBarrioVacaciones(String barrioVacaciones) {
		this.barrioVacaciones = barrioVacaciones;
	}

	/**
	 * @return the nivel1Vacaciones
	 */
	public String getNivel1Vacaciones() {
		return nivel1Vacaciones;
	}

	/**
	 * @param nivel1Vacaciones the nivel1Vacaciones to set
	 */
	public void setNivel1Vacaciones(String nivel1Vacaciones) {
		this.nivel1Vacaciones = nivel1Vacaciones;
	}

	/**
	 * @return the nivel2Vacaciones
	 */
	public String getNivel2Vacaciones() {
		return nivel2Vacaciones;
	}

	/**
	 * @param nivel2Vacaciones the nivel2Vacaciones to set
	 */
	public void setNivel2Vacaciones(String nivel2Vacaciones) {
		this.nivel2Vacaciones = nivel2Vacaciones;
	}

	/**
	 * @return the nivel3Vacaciones
	 */
	public String getNivel3Vacaciones() {
		return nivel3Vacaciones;
	}

	/**
	 * @param nivel3Vacaciones the nivel3Vacaciones to set
	 */
	public void setNivel3Vacaciones(String nivel3Vacaciones) {
		this.nivel3Vacaciones = nivel3Vacaciones;
	}

	/**
	 * @return the nivel4Vacaciones
	 */
	public String getNivel4Vacaciones() {
		return nivel4Vacaciones;
	}

	/**
	 * @param nivel4Vacaciones the nivel4Vacaciones to set
	 */
	public void setNivel4Vacaciones(String nivel4Vacaciones) {
		this.nivel4Vacaciones = nivel4Vacaciones;
	}

	/**
	 * @return the nivel5Vacaciones
	 */
	public String getNivel5Vacaciones() {
		return nivel5Vacaciones;
	}

	/**
	 * @param nivel5Vacaciones the nivel5Vacaciones to set
	 */
	public void setNivel5Vacaciones(String nivel5Vacaciones) {
		this.nivel5Vacaciones = nivel5Vacaciones;
	}

	/**
	 * @return the nivel6Vacaciones
	 */
	public String getNivel6Vacaciones() {
		return nivel6Vacaciones;
	}

	/**
	 * @param nivel6Vacaciones the nivel6Vacaciones to set
	 */
	public void setNivel6Vacaciones(String nivel6Vacaciones) {
		this.nivel6Vacaciones = nivel6Vacaciones;
	}

	/**
	 * @return the codNivel2Vacaciones
	 */
	public String getCodNivel2Vacaciones() {
		return codNivel2Vacaciones;
	}

	/**
	 * @param codNivel2Vacaciones the codNivel2Vacaciones to set
	 */
	public void setCodNivel2Vacaciones(String codNivel2Vacaciones) {
		this.codNivel2Vacaciones = codNivel2Vacaciones;
	}

	/**
	 * @return the codNivel3Vacaciones
	 */
	public String getCodNivel3Vacaciones() {
		return codNivel3Vacaciones;
	}

	/**
	 * @param codNivel3Vacaciones the codNivel3Vacaciones to set
	 */
	public void setCodNivel3Vacaciones(String codNivel3Vacaciones) {
		this.codNivel3Vacaciones = codNivel3Vacaciones;
	}

	/**
	 * @return the codNivel4Vacaciones
	 */
	public String getCodNivel4Vacaciones() {
		return codNivel4Vacaciones;
	}

	/**
	 * @param codNivel4Vacaciones the codNivel4Vacaciones to set
	 */
	public void setCodNivel4Vacaciones(String codNivel4Vacaciones) {
		this.codNivel4Vacaciones = codNivel4Vacaciones;
	}

	/**
	 * @return the codNivel5Vacaciones
	 */
	public String getCodNivel5Vacaciones() {
		return codNivel5Vacaciones;
	}

	/**
	 * @param codNivel5Vacaciones the codNivel5Vacaciones to set
	 */
	public void setCodNivel5Vacaciones(String codNivel5Vacaciones) {
		this.codNivel5Vacaciones = codNivel5Vacaciones;
	}

	/**
	 * @return the codNivel6Vacaciones
	 */
	public String getCodNivel6Vacaciones() {
		return codNivel6Vacaciones;
	}

	/**
	 * @param codNivel6Vacaciones the codNivel6Vacaciones to set
	 */
	public void setCodNivel6Vacaciones(String codNivel6Vacaciones) {
		this.codNivel6Vacaciones = codNivel6Vacaciones;
	}

	/**
	 * @return the codigoCiudadVacaciones
	 */
	public String getCodigoCiudadVacaciones() {
		return codigoCiudadVacaciones;
	}

	/**
	 * @param codigoCiudadVacaciones the codigoCiudadVacaciones to set
	 */
	public void setCodigoCiudadVacaciones(String codigoCiudadVacaciones) {
		this.codigoCiudadVacaciones = codigoCiudadVacaciones;
	}

	/**
	 * @return the villaPoblacionVacaciones
	 */
	public String getVillaPoblacionVacaciones() {
		return villaPoblacionVacaciones;
	}

	/**
	 * @param villaPoblacionVacaciones the villaPoblacionVacaciones to set
	 */
	public void setVillaPoblacionVacaciones(String villaPoblacionVacaciones) {
		this.villaPoblacionVacaciones = villaPoblacionVacaciones;
	}

	/**
	 * @return the telefonoCasaDireccionVacaciones
	 */
	public String getTelefonoCasaDireccionVacaciones() {
		return telefonoCasaDireccionVacaciones;
	}

	/**
	 * @param telefonoCasaDireccionVacaciones the telefonoCasaDireccionVacaciones to set
	 */
	public void setTelefonoCasaDireccionVacaciones(
			String telefonoCasaDireccionVacaciones) {
		this.telefonoCasaDireccionVacaciones = telefonoCasaDireccionVacaciones;
	}

	/**
	 * @return the telefonoCelularDireccionVacaciones
	 */
	public String getTelefonoCelularDireccionVacaciones() {
		return telefonoCelularDireccionVacaciones;
	}

	/**
	 * @param telefonoCelularDireccionVacaciones the telefonoCelularDireccionVacaciones to set
	 */
	public void setTelefonoCelularDireccionVacaciones(
			String telefonoCelularDireccionVacaciones) {
		this.telefonoCelularDireccionVacaciones = telefonoCelularDireccionVacaciones;
	}

	/**
	 * @return the mostrarDireccionVacaciones
	 */
	public boolean isMostrarDireccionVacaciones() {
		return mostrarDireccionVacaciones;
	}

	/**
	 * @param mostrarDireccionVacaciones the mostrarDireccionVacaciones to set
	 */
	public void setMostrarDireccionVacaciones(boolean mostrarDireccionVacaciones) {
		this.mostrarDireccionVacaciones = mostrarDireccionVacaciones;
	}

	/**
	 * @return the codigoPeriodoInicio
	 */
	public String getCodigoPeriodoInicio() {
		return codigoPeriodoInicio;
	}

	/**
	 * @param codigoPeriodoInicio the codigoPeriodoInicio to set
	 */
	public void setCodigoPeriodoInicio(String codigoPeriodoInicio) {
		this.codigoPeriodoInicio = codigoPeriodoInicio;
	}

	/**
	 * @return the codigoPeriodoFin
	 */
	public String getCodigoPeriodoFin() {
		return codigoPeriodoFin;
	}

	/**
	 * @param codigoPeriodoFin the codigoPeriodoFin to set
	 */
	public void setCodigoPeriodoFin(String codigoPeriodoFin) {
		this.codigoPeriodoFin = codigoPeriodoFin;
	}

	/**
	 * @return the indicadorDireccionVacaciones
	 */
	public String getIndicadorDireccionVacaciones() {
		return indicadorDireccionVacaciones;
	}

	/**
	 * @param indicadorDireccionVacaciones the indicadorDireccionVacaciones to set
	 */
	public void setIndicadorDireccionVacaciones(String indicadorDireccionVacaciones) {
		this.indicadorDireccionVacaciones = indicadorDireccionVacaciones;
	}

	/**
	 * @return the primeraVezDespliegueDireccionVacaciones
	 */
	public boolean isPrimeraVezDespliegueDireccionVacaciones() {
		return primeraVezDespliegueDireccionVacaciones;
	}

	/**
	 * @param primeraVezDespliegueDireccionVacaciones the primeraVezDespliegueDireccionVacaciones to set
	 */
	public void setPrimeraVezDespliegueDireccionVacaciones(
			boolean primeraVezDespliegueDireccionVacaciones) {
		this.primeraVezDespliegueDireccionVacaciones = primeraVezDespliegueDireccionVacaciones;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the codigoPeriodoInicioVacaciones
	 */
	public String getCodigoPeriodoInicioVacaciones() {
		return codigoPeriodoInicioVacaciones;
	}

	/**
	 * @param codigoPeriodoInicioVacaciones the codigoPeriodoInicioVacaciones to set
	 */
	public void setCodigoPeriodoInicioVacaciones(
			String codigoPeriodoInicioVacaciones) {
		this.codigoPeriodoInicioVacaciones = codigoPeriodoInicioVacaciones;
	}

	/**
	 * @return the codigoPeriodoFinVacaciones
	 */
	public String getCodigoPeriodoFinVacaciones() {
		return codigoPeriodoFinVacaciones;
	}

	/**
	 * @param codigoPeriodoFinVacaciones the codigoPeriodoFinVacaciones to set
	 */
	public void setCodigoPeriodoFinVacaciones(String codigoPeriodoFinVacaciones) {
		this.codigoPeriodoFinVacaciones = codigoPeriodoFinVacaciones;
	}

	/**
	 * @return the actualizaUbigeoDirecciones
	 */
	public boolean isActualizaUbigeoDirecciones() {
		return actualizaUbigeoDirecciones;
	}

	/**
	 * @param actualizaUbigeoDirecciones the actualizaUbigeoDirecciones to set
	 */
	public void setActualizaUbigeoDirecciones(boolean actualizaUbigeoDirecciones) {
		this.actualizaUbigeoDirecciones = actualizaUbigeoDirecciones;
	}

	/**
	 * @return the validarEstatusComercial
	 */
	public boolean isValidarEstatusComercial() {
		return validarEstatusComercial;
	}

	/**
	 * @param validarEstatusComercial the validarEstatusComercial to set
	 */
	public void setValidarEstatusComercial(boolean validarEstatusComercial) {
		this.validarEstatusComercial = validarEstatusComercial;
	}

	/**
	 * @return the mensajeConsultoraRoja
	 */
	public String getMensajeConsultoraRoja() {
		return mensajeConsultoraRoja;
	}

	/**
	 * @param mensajeConsultoraRoja the mensajeConsultoraRoja to set
	 */
	public void setMensajeConsultoraRoja(String mensajeConsultoraRoja) {
		this.mensajeConsultoraRoja = mensajeConsultoraRoja;
	}
	/* INI JV CHI-SiCC-2012-0003 */
	/**
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}

	/**
	 * @return the indicadorCompromiso
	 */
	public String getIndicadorCompromiso() {
		return indicadorCompromiso;
	}

	/**
	 * @param indicadorCompromiso the indicadorCompromiso to set
	 */
	public void setIndicadorCompromiso(String indicadorCompromiso) {
		this.indicadorCompromiso = indicadorCompromiso;
	}

	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	/**
	 * @return the indicadorSeccionOtros
	 */
	public boolean isIndicadorSeccionOtros() {
		return indicadorSeccionOtros;
	}

	/**
	 * @param indicadorSeccionOtros the indicadorSeccionOtros to set
	 */
	public void setIndicadorSeccionOtros(boolean indicadorSeccionOtros) {
		this.indicadorSeccionOtros = indicadorSeccionOtros;
	}

	/**
	 * @return the valorIndicadorCompromiso
	 */
	public String getValorIndicadorCompromiso() {
		return valorIndicadorCompromiso;
	}

	/**
	 * @param valorIndicadorCompromiso the valorIndicadorCompromiso to set
	 */
	public void setValorIndicadorCompromiso(String valorIndicadorCompromiso) {
		this.valorIndicadorCompromiso = valorIndicadorCompromiso;
	}
	/* FIN JV CHI-SiCC-2012-0003 */

	/**
	 * @return the indicadorImpresionPaqDoc
	 */
	public String getIndicadorImpresionPaqDoc() {
		return indicadorImpresionPaqDoc;
	}

	/**
	 * @param indicadorImpresionPaqDoc the indicadorImpresionPaqDoc to set
	 */
	public void setIndicadorImpresionPaqDoc(String indicadorImpresionPaqDoc) {
		this.indicadorImpresionPaqDoc = indicadorImpresionPaqDoc;
	}

	/**
	 * @return the mostrarIndicadorImpresionPaqDoc
	 */
	public boolean isMostrarIndicadorImpresionPaqDoc() {
		return mostrarIndicadorImpresionPaqDoc;
	}

	/**
	 * @param mostrarIndicadorImpresionPaqDoc the mostrarIndicadorImpresionPaqDoc to set
	 */
	public void setMostrarIndicadorImpresionPaqDoc(
			boolean mostrarIndicadorImpresionPaqDoc) {
		this.mostrarIndicadorImpresionPaqDoc = mostrarIndicadorImpresionPaqDoc;
	}

	/**
	 * @return the indicadorDocumentosLegales
	 */
	public boolean isIndicadorDocumentosLegales() {
		return indicadorDocumentosLegales;
	}

	/**
	 * @param indicadorDocumentosLegales the indicadorDocumentosLegales to set
	 */
	public void setIndicadorDocumentosLegales(boolean indicadorDocumentosLegales) {
		this.indicadorDocumentosLegales = indicadorDocumentosLegales;
	}

	/**
	 * @return isMostrarBuscarDireccion
	 */
	public boolean isMostrarBuscarDireccion() {
		return mostrarBuscarDireccion;
	}
	
	/**
	 * @param mostrarBuscarDireccion
	 */
	public void setMostrarBuscarDireccion(boolean mostrarBuscarDireccion) {
		this.mostrarBuscarDireccion = mostrarBuscarDireccion;
	}

	/**
	 * @return the correViaCT
	 */
	public String getCorreViaCT() {
		return correViaCT;
	}

	/**
	 * @param correViaCT the correViaCT to set
	 */
	public void setCorreViaCT(String correViaCT) {
		this.correViaCT = correViaCT;
	}

	/**
	 * @return the correViaVacaciones
	 */
	public String getCorreViaVacaciones() {
		return correViaVacaciones;
	}

	/**
	 * @param correViaVacaciones the correViaVacaciones to set
	 */
	public void setCorreViaVacaciones(String correViaVacaciones) {
		this.correViaVacaciones = correViaVacaciones;
	}

	/**
	 * @return the codigoPostal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * @param codigoPostal the codigoPostal to set
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/**
	 * @return the codigoPostalCT
	 */
	public String getCodigoPostalCT() {
		return codigoPostalCT;
	}

	/**
	 * @param codigoPostalCT the codigoPostalCT to set
	 */
	public void setCodigoPostalCT(String codigoPostalCT) {
		this.codigoPostalCT = codigoPostalCT;
	}

	/**
	 * @return the telefonoAdicional
	 */
	public String getTelefonoAdicional() {
		return telefonoAdicional;
	}

	/**
	 * @param telefonoAdicional the telefonoAdicional to set
	 */
	public void setTelefonoAdicional(String telefonoAdicional) {
		this.telefonoAdicional = telefonoAdicional;
	}
 
	public boolean isEsTipoConsultora() {
		return esTipoConsultora;
	}

	public void setEsTipoConsultora(boolean esTipoConsultora) {
		this.esTipoConsultora = esTipoConsultora;
	}

	/**
	 * @return the mostrarConsultoraLiderRecomendante
	 */
	public boolean isMostrarConsultoraLiderRecomendante() {
		return mostrarConsultoraLiderRecomendante;
	}

	/**
	 * @param mostrarConsultoraLiderRecomendante the mostrarConsultoraLiderRecomendante to set
	 */
	public void setMostrarConsultoraLiderRecomendante(
			boolean mostrarConsultoraLiderRecomendante) {
		this.mostrarConsultoraLiderRecomendante = mostrarConsultoraLiderRecomendante;
	}

	/**
	 * @return the oidConsultoraLiderRecomendante
	 */
	public String getOidConsultoraLiderRecomendante() {
		return oidConsultoraLiderRecomendante;
	}

	/**
	 * @param oidConsultoraLiderRecomendante the oidConsultoraLiderRecomendante to set
	 */
	public void setOidConsultoraLiderRecomendante(
			String oidConsultoraLiderRecomendante) {
		this.oidConsultoraLiderRecomendante = oidConsultoraLiderRecomendante;
	}

	/**
	 * @return the codigoConsultoraLiderRecomendante
	 */
	public String getCodigoConsultoraLiderRecomendante() {
		return codigoConsultoraLiderRecomendante;
	}

	/**
	 * @param codigoConsultoraLiderRecomendante the codigoConsultoraLiderRecomendante to set
	 */
	public void setCodigoConsultoraLiderRecomendante(
			String codigoConsultoraLiderRecomendante) {
		this.codigoConsultoraLiderRecomendante = codigoConsultoraLiderRecomendante;
	}

	/**
	 * @return the nombreConsultoraLiderRecomendante
	 */
	public String getNombreConsultoraLiderRecomendante() {
		return nombreConsultoraLiderRecomendante;
	}

	/**
	 * @param nombreConsultoraLiderRecomendante the nombreConsultoraLiderRecomendante to set
	 */
	public void setNombreConsultoraLiderRecomendante(
			String nombreConsultoraLiderRecomendante) {
		this.nombreConsultoraLiderRecomendante = nombreConsultoraLiderRecomendante;
	}

	public boolean isIndicadorDocFiscal() {
		return indicadorDocFiscal;
	}

	public void setIndicadorDocFiscal(boolean indicadorDocFiscal) {
		this.indicadorDocFiscal = indicadorDocFiscal;
	}

	

	
	

}
