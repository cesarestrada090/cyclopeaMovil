package biz.belcorp.ssicc.web.spusicc.zon.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoZONParametrosRutasForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:kgomez@sigcomt.com">Karina Gomez</a>
 */

public class MantenimientoZONParametrosRutasForm extends BaseEditForm{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1374620937061961410L;
	private String codigoPais;
	private String codigoNovedad;
	private String descripcionNovedad;
	private String emailAprobador;
	private String emailDestinatarios;
	private String asunto;
	private String mensaje;
	private String emailOrigen;
	private String indicadorDesactivarEnvio;
	private String indicadorEdit;
	private String indicadorDesactivarEnvioHidden;
	
	private String[] selectedItems = {};
	
	/**
	 * @return the selectedItems
	 */
	public String[] getSelectedItems() {
		return selectedItems;
	}
	/**
	 * @param selectedItems the selectedItems to set
	 */
	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}
	/**
	 * @return the indicadorDesactivarEnvioHidden
	 */
	public String getIndicadorDesactivarEnvioHidden() {
		return indicadorDesactivarEnvioHidden;
	}
	/**
	 * @param indicadorDesactivarEnvioHidden the indicadorDesactivarEnvioHidden to set
	 */
	public void setIndicadorDesactivarEnvioHidden(
			String indicadorDesactivarEnvioHidden) {
		this.indicadorDesactivarEnvioHidden = indicadorDesactivarEnvioHidden;
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
	 * @return the codigoNovedad
	 */
	public String getCodigoNovedad() {
		return codigoNovedad;
	}
	/**
	 * @param codigoNovedad the codigoNovedad to set
	 */
	public void setCodigoNovedad(String codigoNovedad) {
		this.codigoNovedad = codigoNovedad;
	}
	/**
	 * @return the descripcionNovedad
	 */
	public String getDescripcionNovedad() {
		return descripcionNovedad;
	}
	/**
	 * @param descripcionNovedad the descripcionNovedad to set
	 */
	public void setDescripcionNovedad(String descripcionNovedad) {
		this.descripcionNovedad = descripcionNovedad;
	}
	/**
	 * @return the emailAprobador
	 */
	public String getEmailAprobador() {
		return emailAprobador;
	}
	/**
	 * @param emailAprobador the emailAprobador to set
	 */
	public void setEmailAprobador(String emailAprobador) {
		this.emailAprobador = emailAprobador;
	}
	/**
	 * @return the emailDestinatarios
	 */
	public String getEmailDestinatarios() {
		return emailDestinatarios;
	}
	/**
	 * @param emailDestinatarios the emailDestinatarios to set
	 */
	public void setEmailDestinatarios(String emailDestinatarios) {
		this.emailDestinatarios = emailDestinatarios;
	}
	/**
	 * @return the asunto
	 */
	public String getAsunto() {
		return asunto;
	}
	/**
	 * @param asunto the asunto to set
	 */
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}
	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	/**
	 * @return the emailOrigen
	 */
	public String getEmailOrigen() {
		return emailOrigen;
	}
	/**
	 * @param emailOrigen the emailOrigen to set
	 */
	public void setEmailOrigen(String emailOrigen) {
		this.emailOrigen = emailOrigen;
	}
	/**
	 * @return the indicadorDesactivarEnvio
	 */
	public String getIndicadorDesactivarEnvio() {
		return indicadorDesactivarEnvio;
	}
	/**
	 * @param indicadorDesactivarEnvio the indicadorDesactivarEnvio to set
	 */
	public void setIndicadorDesactivarEnvio(String indicadorDesactivarEnvio) {
		this.indicadorDesactivarEnvio = indicadorDesactivarEnvio;
	}
	/**
	 * @return the indicadorEdit
	 */
	public String getIndicadorEdit() {
		return indicadorEdit;
	}
	/**
	 * @param indicadorEdit the indicadorEdit to set
	 */
	public void setIndicadorEdit(String indicadorEdit) {
		this.indicadorEdit = indicadorEdit;
	}
	
	
}
