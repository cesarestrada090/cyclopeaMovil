/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ENTIDADES;

/**
 *
 * @author pc
 */
public class callcenter {
    private int Id;
    private String fecha;
    private String Observacion;
    private String Idusu;
    private int idVehiculo;
    private String comentario;
    private String citv;

    public callcenter() {
    }

    public callcenter(int Id, String fecha, String Observacion, String Idusu, int idVehiculo, String comentario, String citv) {
        this.Id = Id;
        this.fecha = fecha;
        this.Observacion = Observacion;
        this.Idusu = Idusu;
        this.idVehiculo = idVehiculo;
        this.comentario = comentario;
        this.citv = citv;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getIdusu() {
        return Idusu;
    }

    public void setIdusu(String Idusu) {
        this.Idusu = Idusu;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getCitv() {
        return citv;
    }

    public void setCitv(String citv) {
        this.citv = citv;
    }
}
