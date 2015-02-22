package ENTIDADES;

public class Observacion {

    private int idCertificado;
    private String codigoObservacion;
    private String interpretacion;
    private String calificacion;

    public int getIdCertificado() {
        return idCertificado;
    }

    public void setIdCertificado(int idCertificado) {
        this.idCertificado = idCertificado;
    }

    public String getCodigoObservacion() {
        return codigoObservacion;
    }

    public void setCodigoObservacion(String codigoObservacion) {
        this.codigoObservacion = codigoObservacion;
    }

    public String getInterpretacion() {
        return interpretacion;
    }

    public void setInterpretacion(String interpretacion) {
        this.interpretacion = interpretacion;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }
    
}
