package ENTIDADES;

import java.util.Date;

public class Certificado {

    private int idCertificado;
    private int idTarjeta;
    private String tipoDocTransp;
    private String numDocTransp;
    private String razonTransp;
    private String tipoDocEvaluar;
    private String numDocEvaluar;
    private String claseAutorizacion;
    private int resultado;
    private String vigencia;
    private Date fecInspeccion;
    private Date fecVencimiento;
    private String cIdentidadCert;
    private String codLocal;
    private String ubigeo;

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

    public Date getFecInspeccion() {
        return fecInspeccion;
    }

    public void setFecInspeccion(Date fecInspeccion) {
        this.fecInspeccion = fecInspeccion;
    }

    public Date getFecVencimiento() {
        return fecVencimiento;
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
