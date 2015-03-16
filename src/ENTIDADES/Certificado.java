package ENTIDADES;

import java.util.Date;

public class Certificado {

    private int idCertificado;
    private int idInforme;
    private int idExpediente;
    
    private int idTarjeta;
    private String tipoDocTransp;
    private String numDocTransp;
    private String razonTransp;
    private String tipoDocEvaluar;
    private String numDocEvaluar;
    private String claseAutorizacion;
    private int resultado;
    private String vigencia;
    private Object fecInspeccion;
    private Object fecVencimiento;
    private String cIdentidadCert;
    private String codLocal;
    private String ubigeo;
    private Date fecInspeccion1;
    private Date fecVencimiento1;    
    private String texto;
    private String titulo;
    private String claseAut;
    
    private String numCertificado;
    private String numInforme;
    private String numExpediente;

    public int getIdInforme() {
        return idInforme;
    }

    public void setIdInforme(int idInforme) {
        this.idInforme = idInforme;
    }

    public int getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(int idExpediente) {
        this.idExpediente = idExpediente;
    }    
    
    public String getNumCertificado() {
        return numCertificado;
    }

    public void setNumCertificado(String numCertificado) {
        this.numCertificado = numCertificado;
    }

    public String getNumInforme() {
        return numInforme;
    }

    public void setNumInforme(String numInforme) {
        this.numInforme = numInforme;
    }

    public String getNumExpediente() {
        return numExpediente;
    }

    public void setNumExpediente(String numExpediente) {
        this.numExpediente = numExpediente;
    }   
    
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getClaseAut() {
        return claseAut;
    }

    public void setClaseAut(String claseAut) {
        this.claseAut = claseAut;
    }

    public Date getFecInspeccion1() {
        return fecInspeccion1;
    }

    public void setFecInspeccion1(Date fecInspeccion1) {
        this.fecInspeccion1 = fecInspeccion1;
    }

    public Date getFecVencimiento1() {
        return fecVencimiento1;
    }

    public void setFecVencimiento1(Date fecVencimiento1) {
        this.fecVencimiento1 = fecVencimiento1;
    }
    
    public int getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    
    public int getIdCertificado() {
        return idCertificado;
    }

    public void setIdCertificado(int idCertificado) {
        this.idCertificado = idCertificado;
    }

    
    
    public String getTipoDocTransp() {
        return tipoDocTransp;
    }

    public void setTipoDocTransp(String tipoDocTransp) {
        this.tipoDocTransp = tipoDocTransp;
    }

    public String getNumDocTransp() {
        return numDocTransp;
    }

    public void setNumDocTransp(String numDocTransp) {
        this.numDocTransp = numDocTransp;
    }

    public String getRazonTransp() {
        return razonTransp;
    }

    public void setRazonTransp(String razonTransp) {
        this.razonTransp = razonTransp;
    }

    public String getTipoDocEvaluar() {
        return tipoDocEvaluar;
    }

    public void setTipoDocEvaluar(String tipoDocEvaluar) {
        this.tipoDocEvaluar = tipoDocEvaluar;
    }

    public String getNumDocEvaluar() {
        return numDocEvaluar;
    }

    public void setNumDocEvaluar(String numDocEvaluar) {
        this.numDocEvaluar = numDocEvaluar;
    }

    public String getClaseAutorizacion() {
        return claseAutorizacion;
    }

    public void setClaseAutorizacion(String claseAutorizacion) {
        this.claseAutorizacion = claseAutorizacion;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public Object getFecInspeccion() {
        return fecInspeccion;
    }

    public void setFecInspeccion(Object fecInspeccion) {
        this.fecInspeccion = fecInspeccion;
    }

    public Object getFecVencimiento() {
        return fecVencimiento;
    }

    public void setFecVencimiento(Object fecVencimiento) {
        this.fecVencimiento = fecVencimiento;
    }

  

    public void setFecVencimiento(Date fecVencimiento) {
        this.fecVencimiento = fecVencimiento;
    }

    public String getcIdentidadCert() {
        return cIdentidadCert;
    }

    public void setcIdentidadCert(String cIdentidadCert) {
        this.cIdentidadCert = cIdentidadCert;
    }

    public String getCodLocal() {
        return codLocal;
    }

    public void setCodLocal(String codLocal) {
        this.codLocal = codLocal;
    }

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }
}
