package biz.belcorp.ssicc.web.spusicc.form;

import java.io.Serializable;
import java.sql.Timestamp;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoGEOZonaTerritorioClienteForm extends BaseEditForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long oidTipoCliente;

	private String descripcionTipoCliente;

	private Long oidSubtipoCliente;

	private String descripcionSubtipoCliente;

	private String codigo;

	private String numeroDocumento;

	private String apellidoPaterno;

	private String apellidoMaterno;

	private String nombre;

	private Timestamp fechaIngreso;

	private String orden1;

	private String orden2;

	private String orden3;

	private String orden4;

	private String descripcionOrden1;

	private String descripcionOrden2;

	private String descripcionOrden3;

	private String descripcionOrden4;

	private String codigoTipoDireccion;

	private String descripcionTipoDireccion;

	private String codigoTipoVia;

	private String descripcionTipoVia;

	private String nombreVia;

	private String numeroPrincipal;

	private String observaciones;

	private String codigoZona;

	private String codigoTerritorio;

	private String estadoValidacion;

	private String estadoError1;

	private String estadoError2;

	private String estadoError3;

	public void inicializar() {
		this.oidTipoCliente = new Long(0);
		this.descripcionTipoCliente = "";
		this.oidSubtipoCliente = new Long(0);
		this.descripcionSubtipoCliente = "";
		this.codigo = "";
		this.numeroDocumento = "";
		this.apellidoPaterno = "";
		this.apellidoMaterno = "";
		this.nombre = "";
		// fechaIngreso="";
		this.orden1 = "";
		this.orden2 = "";
		this.orden3 = "";
		this.orden4 = "";
		this.descripcionOrden1 = "";
		this.descripcionOrden2 = "";
		this.descripcionOrden3 = "";
		this.descripcionOrden4 = "";
		this.codigoTipoDireccion = "";
		this.descripcionTipoDireccion = "";
		this.codigoTipoVia = "";
		this.descripcionTipoVia = "";
		this.nombreVia = "";
		this.numeroPrincipal = "";
		this.observaciones = "";
		this.codigoZona = "";
		this.codigoTerritorio = "";

		this.estadoValidacion = "";
		this.estadoError1 = "";
		this.estadoError2 = "";
		this.estadoError3 = "";

	}

	/**
	 * @return Returns the estadoError1.
	 */
	public String getEstadoError1() {
		return estadoError1;
	}

	/**
	 * @param estadoError1
	 *            The estadoError1 to set.
	 */
	public void setEstadoError1(String estadoError1) {
		this.estadoError1 = estadoError1;
	}

	/**
	 * @return Returns the estadoError2.
	 */
	public String getEstadoError2() {
		return estadoError2;
	}

	/**
	 * @param estadoError2
	 *            The estadoError2 to set.
	 */
	public void setEstadoError2(String estadoError2) {
		this.estadoError2 = estadoError2;
	}

	/**
	 * @return Returns the estadoError3.
	 */
	public String getEstadoError3() {
		return estadoError3;
	}

	/**
	 * @param estadoError3
	 *            The estadoError3 to set.
	 */
	public void setEstadoError3(String estadoError3) {
		this.estadoError3 = estadoError3;
	}

	/**
	 * @return Returns the estadoValidacion.
	 */
	public String getEstadoValidacion() {
		return estadoValidacion;
	}

	/**
	 * @param estadoValidacion
	 *            The estadoValidacion to set.
	 */
	public void setEstadoValidacion(String estadoValidacion) {
		this.estadoValidacion = estadoValidacion;
	}

	/**
	 * @return Returns the apellidoMaterno.
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	/**
	 * @param apellidoMaterno
	 *            The apellidoMaterno to set.
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
	 * @param apellidoPaterno
	 *            The apellidoPaterno to set.
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	/**
	 * @return Returns the codigo.
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            The codigo to set.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return Returns the codigoTerritorio.
	 */
	public String getCodigoTerritorio() {
		return codigoTerritorio;
	}

	/**
	 * @param codigoTerritorio
	 *            The codigoTerritorio to set.
	 * 
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
	 * @param codigoZona
	 *            The codigoZona to set.
	 * 
	 * @struts.validator type="required"
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return Returns the descripcionOrden1.
	 */
	public String getDescripcionOrden1() {
		return descripcionOrden1;
	}

	/**
	 * @param descripcionOrden1
	 *            The descripcionOrden1 to set.
	 */
	public void setDescripcionOrden1(String descripcionOrden1) {
		this.descripcionOrden1 = descripcionOrden1;
	}

	/**
	 * @return Returns the descripcionOrden2.
	 */
	public String getDescripcionOrden2() {
		return descripcionOrden2;
	}

	/**
	 * @param descripcionOrden2
	 *            The descripcionOrden2 to set.
	 */
	public void setDescripcionOrden2(String descripcionOrden2) {
		this.descripcionOrden2 = descripcionOrden2;
	}

	/**
	 * @return Returns the descripcionOrden3.
	 */
	public String getDescripcionOrden3() {
		return descripcionOrden3;
	}

	/**
	 * @param descripcionOrden3
	 *            The descripcionOrden3 to set.
	 */
	public void setDescripcionOrden3(String descripcionOrden3) {
		this.descripcionOrden3 = descripcionOrden3;
	}

	/**
	 * @return Returns the descripcionOrden4.
	 */
	public String getDescripcionOrden4() {
		return descripcionOrden4;
	}

	/**
	 * @param descripcionOrden4
	 *            The descripcionOrden4 to set.
	 */
	public void setDescripcionOrden4(String descripcionOrden4) {
		this.descripcionOrden4 = descripcionOrden4;
	}

	/**
	 * @return Returns the descripcionSubtipoCliente.
	 */
	public String getDescripcionSubtipoCliente() {
		return descripcionSubtipoCliente;
	}

	/**
	 * @param descripcionSubtipoCliente
	 *            The descripcionSubtipoCliente to set.
	 */
	public void setDescripcionSubtipoCliente(String descripcionSubtipoCliente) {
		this.descripcionSubtipoCliente = descripcionSubtipoCliente;
	}

	/**
	 * @return Returns the descripcionTipoCliente.
	 */
	public String getDescripcionTipoCliente() {
		return descripcionTipoCliente;
	}

	/**
	 * @param descripcionTipoCliente
	 *            The descripcionTipoCliente to set.
	 */
	public void setDescripcionTipoCliente(String descripcionTipoCliente) {
		this.descripcionTipoCliente = descripcionTipoCliente;
	}

	/**
	 * @return Returns the descripcionTipoDireccion.
	 */
	public String getDescripcionTipoDireccion() {
		return descripcionTipoDireccion;
	}

	/**
	 * @param descripcionTipoDireccion
	 *            The descripcionTipoDireccion to set.
	 */
	public void setDescripcionTipoDireccion(String descripcionTipoDireccion) {
		this.descripcionTipoDireccion = descripcionTipoDireccion;
	}

	/**
	 * @return Returns the descripcionTipoVia.
	 */
	public String getDescripcionTipoVia() {
		return descripcionTipoVia;
	}

	/**
	 * @param descripcionTipoVia
	 *            The descripcionTipoVia to set.
	 */
	public void setDescripcionTipoVia(String descripcionTipoVia) {
		this.descripcionTipoVia = descripcionTipoVia;
	}

	/**
	 * @return Returns the fechaIngreso.
	 */
	public Timestamp getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * @param fechaIngreso
	 *            The fechaIngreso to set.
	 */
	public void setFechaIngreso(Timestamp fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	/**
	 * @return Returns the nombre.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            The nombre to set.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return Returns the nombreVia.
	 */
	public String getNombreVia() {
		return nombreVia;
	}

	/**
	 * @param nombreVia
	 *            The nombreVia to set.
	 */
	public void setNombreVia(String nombreVia) {
		this.nombreVia = nombreVia;
	}

	/**
	 * @return Returns the numeroDocumento.
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento
	 *            The numeroDocumento to set.
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * @return Returns the numeroPrincipal.
	 */
	public String getNumeroPrincipal() {
		return numeroPrincipal;
	}

	/**
	 * @param numeroPrincipal
	 *            The numeroPrincipal to set.
	 */
	public void setNumeroPrincipal(String numeroPrincipal) {
		this.numeroPrincipal = numeroPrincipal;
	}

	/**
	 * @return Returns the observaciones.
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones
	 *            The observaciones to set.
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return Returns the oidSubtipoCliente.
	 */
	public Long getOidSubtipoCliente() {
		return oidSubtipoCliente;
	}

	/**
	 * @param oidSubtipoCliente
	 *            The oidSubtipoCliente to set.
	 */
	public void setOidSubtipoCliente(Long oidSubtipoCliente) {
		this.oidSubtipoCliente = oidSubtipoCliente;
	}

	/**
	 * @return Returns the oidTipoCliente.
	 */
	public Long getOidTipoCliente() {
		return oidTipoCliente;
	}

	/**
	 * @param oidTipoCliente
	 *            The oidTipoCliente to set.
	 */
	public void setOidTipoCliente(Long oidTipoCliente) {
		this.oidTipoCliente = oidTipoCliente;
	}

	/**
	 * @return Returns the codigoTipoDireccion.
	 */
	public String getcodigoTipoDireccion() {
		return codigoTipoDireccion;
	}

	/**
	 * @param codigoTipoDireccion
	 *            The codigoTipoDireccion to set.
	 */
	public void setcodigoTipoDireccion(String codigoTipoDireccion) {
		this.codigoTipoDireccion = codigoTipoDireccion;
	}

	/**
	 * @return Returns the codigoTipoVia.
	 */
	public String getcodigoTipoVia() {
		return codigoTipoVia;
	}

	/**
	 * @param codigoTipoVia
	 *            The codigoTipoVia to set.
	 */
	public void setcodigoTipoVia(String codigoTipoVia) {
		this.codigoTipoVia = codigoTipoVia;
	}

	/**
	 * @return Returns the orden1.
	 */
	public String getOrden1() {
		return orden1;
	}

	/**
	 * @param orden1
	 *            The orden1 to set.
	 */
	public void setOrden1(String orden1) {
		this.orden1 = orden1;
	}

	/**
	 * @return Returns the orden2.
	 */
	public String getOrden2() {
		return orden2;
	}

	/**
	 * @param orden2
	 *            The orden2 to set.
	 */
	public void setOrden2(String orden2) {
		this.orden2 = orden2;
	}

	/**
	 * @return Returns the orden3.
	 */
	public String getOrden3() {
		return orden3;
	}

	/**
	 * @param orden3
	 *            The orden3 to set.
	 */
	public void setOrden3(String orden3) {
		this.orden3 = orden3;
	}

	/**
	 * @return Returns the orden4.
	 */
	public String getOrden4() {
		return orden4;
	}

	/**
	 * @param orden4
	 *            The orden4 to set.
	 */
	public void setOrden4(String orden4) {
		this.orden4 = orden4;
	}

	public String getCodigoTipoDireccion() {
		return codigoTipoDireccion;
	}

	public void setCodigoTipoDireccion(String codigoTipoDireccion) {
		this.codigoTipoDireccion = codigoTipoDireccion;
	}

	public String getCodigoTipoVia() {
		return codigoTipoVia;
	}

	public void setCodigoTipoVia(String codigoTipoVia) {
		this.codigoTipoVia = codigoTipoVia;
	}
	
	

}
