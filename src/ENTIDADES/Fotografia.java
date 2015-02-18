package clases;

import java.util.Date;

public class Fotografia {

	private String idExpediente;
	private String placa;
	private int id_inspector;
	private boolean rev_exped;
	private Date fechaRevExp;
	private int id_tarjeta1;
	private int id_tarjeta2;
	private String obsvInterna;
	private String idCategoria;
	private int estado;
	private String id_user;
	private Date fecha;
	private int idCodCliente;
	private boolean leasing;
	
	
	
	public String getIdExpediente() {
		return idExpediente;
	}
	public void setIdExpediente(String idExpediente) {
		this.idExpediente = idExpediente;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public int getId_inspector() {
		return id_inspector;
	}
	public void setId_inspector(int id_inspector) {
		this.id_inspector = id_inspector;
	}
	public boolean isRev_exped() {
		return rev_exped;
	}
	public void setRev_exped(boolean rev_exped) {
		this.rev_exped = rev_exped;
	}
	public Date getFechaRevExp() {
		return fechaRevExp;
	}
	public void setFechaRevExp(Date fechaRevExp) {
		this.fechaRevExp = fechaRevExp;
	}
	public int getId_tarjeta1() {
		return id_tarjeta1;
	}
	public void setId_tarjeta1(int id_tarjeta1) {
		this.id_tarjeta1 = id_tarjeta1;
	}
	public int getId_tarjeta2() {
		return id_tarjeta2;
	}
	public void setId_tarjeta2(int id_tarjeta2) {
		this.id_tarjeta2 = id_tarjeta2;
	}
	public String getObsvInterna() {
		return obsvInterna;
	}
	public void setObsvInterna(String obsvInterna) {
		this.obsvInterna = obsvInterna;
	}
	public String getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getId_user() {
		return id_user;
	}
	public void setId_user(String id_user) {
		this.id_user = id_user;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getIdCodCliente() {
		return idCodCliente;
	}
	public void setIdCodCliente(int idCodCliente) {
		this.idCodCliente = idCodCliente;
	}
	public boolean isLeasing() {
		return leasing;
	}
	public void setLeasing(boolean leasing) {
		this.leasing = leasing;
	}
	
	
}
