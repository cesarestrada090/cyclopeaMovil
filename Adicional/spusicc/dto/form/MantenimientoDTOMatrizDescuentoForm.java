package biz.belcorp.ssicc.web.spusicc.dto.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoDTOMatrizDescuentoForm extends BaseEditForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5055529429073157398L;

	private String codigoPais;
	
	private String codigoGrupo;
	private String codigoCategoria;
	private String subCategoria1;
	private String subCategoria2;
	private String estadoRegistro;
	
	private String oidTipoCliente;
	private String oidSubTipoCliente;
	private String oidTipoOferta;
	private String oidNegocio;
	private String oidUnidadNegocio;
	
	/* INI SA PER-SiCC-2013-0268 */	
	private String codigoProducto;
	private String descripcionProducto;
	/* FIN SA PER-SiCC-2013-0268 */
	private String muestra;
	private boolean visible;
	
	
	
	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}
	/**
	 * @param visible the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	/**
	 * @return the muestra
	 */
	public String getMuestra() {
		return muestra;
	}
	/**
	 * @param muestra the muestra to set
	 */
	public void setMuestra(String muestra) {
		this.muestra = muestra;
	}
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the codigoGrupo
	 */
	public String getCodigoGrupo() {
		return codigoGrupo;
	}
	/**
	 * @param codigoGrupo the codigoGrupo to set
	 */
	public void setCodigoGrupo(String codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}
	/**
	 * @return the codigoCategoria
	 */
	public String getCodigoCategoria() {
		return codigoCategoria;
	}
	/**
	 * @param codigoCategoria the codigoCategoria to set
	 */
	public void setCodigoCategoria(String codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}
	/**
	 * @return the subCategoria1
	 */
	public String getSubCategoria1() {
		return subCategoria1;
	}
	/**
	 * @param subCategoria1 the subCategoria1 to set
	 */
	public void setSubCategoria1(String subCategoria1) {
		this.subCategoria1 = subCategoria1;
	}
	/**
	 * @return the subCategoria2
	 */
	public String getSubCategoria2() {
		return subCategoria2;
	}
	/**
	 * @param subCategoria2 the subCategoria2 to set
	 */
	public void setSubCategoria2(String subCategoria2) {
		this.subCategoria2 = subCategoria2;
	}
	/**
	 * @return the estadoRegistro
	 */
	public String getEstadoRegistro() {
		return estadoRegistro;
	}
	/**
	 * @param estadoRegistro the estadoRegistro to set
	 */
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
	/**
	 * @return the oidTipoCliente
	 */
	public String getOidTipoCliente() {
		return oidTipoCliente;
	}
	/**
	 * @param oidTipoCliente the oidTipoCliente to set
	 */
	public void setOidTipoCliente(String oidTipoCliente) {
		this.oidTipoCliente = oidTipoCliente;
	}
	/**
	 * @return the oidSubTipoCliente
	 */
	public String getOidSubTipoCliente() {
		return oidSubTipoCliente;
	}
	/**
	 * @param oidSubTipoCliente the oidSubTipoCliente to set
	 */
	public void setOidSubTipoCliente(String oidSubTipoCliente) {
		this.oidSubTipoCliente = oidSubTipoCliente;
	}
	/**
	 * @return the oidTipoOferta
	 */
	public String getOidTipoOferta() {
		return oidTipoOferta;
	}
	/**
	 * @param oidTipoOferta the oidTipoOferta to set
	 */
	public void setOidTipoOferta(String oidTipoOferta) {
		this.oidTipoOferta = oidTipoOferta;
	}
	/**
	 * @return the oidNegocio
	 */
	public String getOidNegocio() {
		return oidNegocio;
	}
	/**
	 * @param oidNegocio the oidNegocio to set
	 */
	public void setOidNegocio(String oidNegocio) {
		this.oidNegocio = oidNegocio;
	}
	/**
	 * @return the oidUnidadNegocio
	 */
	public String getOidUnidadNegocio() {
		return oidUnidadNegocio;
	}
	/**
	 * @param oidUnidadNegocio the oidUnidadNegocio to set
	 */
	public void setOidUnidadNegocio(String oidUnidadNegocio) {
		this.oidUnidadNegocio = oidUnidadNegocio;
	}
	/**
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}
	/**
	 * @param codigoProducto the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	/**
	 * @return the descripcionProducto
	 */
	public String getDescripcionProducto() {
		return descripcionProducto;
	}
	/**
	 * @param descripcionProducto the descripcionProducto to set
	 */
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}
	
	
}
