/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ENTIDADES;

/**
 *
 * @author pc
 */
public class Revision {
    private int idRevision;
    private String fecha;
    private int idVehiculo;

    public Revision() {
    }

    public Revision(int idRevision, String fecha, int idVehiculo) {
        this.idRevision = idRevision;
        this.fecha = fecha;
        this.idVehiculo = idVehiculo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdRevision() {
        return idRevision;
    }

    public void setIdRevision(int idRevision) {
        this.idRevision = idRevision;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }
    
}
