package ENTIDADES;

public class Frenometro {

    private int idFrenometro;
    private int idEsqueda;
    private int nEjes;

    private double pesoServicio;
    private double fuerzaServicioDer;
    private double fuerzaServicioIzq;
    private double fuerzaEstDer;
    private double fuerzaEstIzq;
    private double fuerzaEmerDer;
    private double fuerzaEmerIzq;
    private double eficienciaServicio;
    private double eficienciaEst;
    private double eficienciaEmergencia;

    private String resultadoEjeServicio;
    private String resultadoEjeEstacionamiento;
    private String resultadoEjeEmergencia;

    private String resultadoGlobalServicio;
    private String resultadoGlobalEstacionamiento;
    private String resultadoGlobalEmergencia;
    
    private double desequilibrioServicio;
    private double desequilibrioEstacionamiento;
    private double desequilibrioEmergencia;

    public double getPesoServicio() {
        return pesoServicio;
    }

    public void setPesoServicio(double pesoServicio) {
        this.pesoServicio = pesoServicio;
    }

    public double getFuerzaServicioDer() {
        return fuerzaServicioDer;
    }

    public void setFuerzaServicioDer(double fuerzaServicioDer) {
        this.fuerzaServicioDer = fuerzaServicioDer;
    }

    public double getFuerzaServicioIzq() {
        return fuerzaServicioIzq;
    }

    public void setFuerzaServicioIzq(double fuerzaServicioIzq) {
        this.fuerzaServicioIzq = fuerzaServicioIzq;
    }

    public double getFuerzaEstDer() {
        return fuerzaEstDer;
    }

    public void setFuerzaEstDer(double fuerzaEstDer) {
        this.fuerzaEstDer = fuerzaEstDer;
    }

    public double getFuerzaEstIzq() {
        return fuerzaEstIzq;
    }

    public void setFuerzaEstIzq(double fuerzaEstIzq) {
        this.fuerzaEstIzq = fuerzaEstIzq;
    }

    public double getFuerzaEmerDer() {
        return fuerzaEmerDer;
    }

    public void setFuerzaEmerDer(double fuerzaEmerDer) {
        this.fuerzaEmerDer = fuerzaEmerDer;
    }

    public double getFuerzaEmerIzq() {
        return fuerzaEmerIzq;
    }

    public void setFuerzaEmerIzq(double fuerzaEmerIzq) {
        this.fuerzaEmerIzq = fuerzaEmerIzq;
    }

    public double getEficienciaServicio() {
        return eficienciaServicio;
    }

    public void setEficienciaServicio(double eficienciaServicio) {
        this.eficienciaServicio = eficienciaServicio;
    }

    public double getEficienciaEst() {
        return eficienciaEst;
    }

    public void setEficienciaEst(double eficienciaEst) {
        this.eficienciaEst = eficienciaEst;
    }

    public double getEficienciaEmergencia() {
        return eficienciaEmergencia;
    }

    public void setEficienciaEmergencia(double eficienciaEmergencia) {
        this.eficienciaEmergencia = eficienciaEmergencia;
    }

    public double getDesequilibrioServicio() {
        return desequilibrioServicio;
    }

    public void setDesequilibrioServicio(double desequilibrioServicio) {
        this.desequilibrioServicio = desequilibrioServicio;
    }

    public double getDesequilibrioEstacionamiento() {
        return desequilibrioEstacionamiento;
    }

    public void setDesequilibrioEstacionamiento(double desequilibrioEstacionamiento) {
        this.desequilibrioEstacionamiento = desequilibrioEstacionamiento;
    }

    public double getDesequilibrioEmergencia() {
        return desequilibrioEmergencia;
    }

    public void setDesequilibrioEmergencia(double desequilibrioEmergencia) {
        this.desequilibrioEmergencia = desequilibrioEmergencia;
    }

    public String getResultadoEjeServicio() {
        return resultadoEjeServicio;
    }

    public void setResultadoEjeServicio(String resultadoEjeServicio) {
        this.resultadoEjeServicio = resultadoEjeServicio;
    }

    public String getResultadoEjeEstacionamiento() {
        return resultadoEjeEstacionamiento;
    }

    public void setResultadoEjeEstacionamiento(String resultadoEjeEstacionamiento) {
        this.resultadoEjeEstacionamiento = resultadoEjeEstacionamiento;
    }

    public String getResultadoEjeEmergencia() {
        return resultadoEjeEmergencia;
    }

    public void setResultadoEjeEmergencia(String resultadoEjeEmergencia) {
        this.resultadoEjeEmergencia = resultadoEjeEmergencia;
    }

    public String getResultadoGlobalServicio() {
        return resultadoGlobalServicio;
    }

    public void setResultadoGlobalServicio(String resultadoGlobalServicio) {
        this.resultadoGlobalServicio = resultadoGlobalServicio;
    }

    public String getResultadoGlobalEstacionamiento() {
        return resultadoGlobalEstacionamiento;
    }

    public void setResultadoGlobalEstacionamiento(String resultadoGlobalEstacionamiento) {
        this.resultadoGlobalEstacionamiento = resultadoGlobalEstacionamiento;
    }

    public String getResultadoGlobalEmergencia() {
        return resultadoGlobalEmergencia;
    }

    public void setResultadoGlobalEmergencia(String resultadoGlobalEmergencia) {
        this.resultadoGlobalEmergencia = resultadoGlobalEmergencia;
    }

    public int getIdFrenometro() {
        return idFrenometro;
    }

    public void setIdFrenometro(int idFrenometro) {
        this.idFrenometro = idFrenometro;
    }

    public int getIdEsqueda() {
        return idEsqueda;
    }

    public void setIdEsqueda(int idEsqueda) {
        this.idEsqueda = idEsqueda;
    }

    public int getnEjes() {
        return nEjes;
    }

    public void setnEjes(int nEjes) {
        this.nEjes = nEjes;
    }

}
