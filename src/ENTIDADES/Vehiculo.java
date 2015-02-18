/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ENTIDADES;

/**
 *
 * @author pc
 */

public class Vehiculo {
    private int idVehiculo;
    private String placa;
    private String anioFabricacion;
    private String Marca;
    private String Modelo;
    private String tiposervicio;
    private int IdPersona;
    private int estado;

    public Vehiculo() {
    }

    public Vehiculo(int idVehiculo, String placa, String anioFabricacion, String Marca, String Modelo, String tiposervicio, int IdPersona, int estado) {
        this.idVehiculo = idVehiculo;
        this.placa = placa;
        this.anioFabricacion = anioFabricacion;
        this.Marca = Marca;
        this.Modelo = Modelo;
        this.tiposervicio = tiposervicio;
        this.IdPersona = IdPersona;
        this.estado = estado;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getIdPersona() {
        return IdPersona;
    }

    public void setIdPersona(int IdPersona) {
        this.IdPersona = IdPersona;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    public String getAnioFabricacion() {
        return anioFabricacion;
    }

    public void setAnioFabricacion(String anioFabricacion) {
        this.anioFabricacion = anioFabricacion;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getTiposervicio() {
        return tiposervicio;
    }

    public void setTiposervicio(String tiposervicio) {
        this.tiposervicio = tiposervicio;
    } 

}
