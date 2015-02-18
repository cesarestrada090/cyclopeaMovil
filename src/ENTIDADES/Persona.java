/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ENTIDADES;

/**
 *
 * @author pc
 */
public class Persona {
    private int id;
    private String DNI;
    private String RUC;
    private String Razon;
    private String Direccion;
    private String Telefono;
    private String Celular;
    private String Correo;

    public Persona() {
    }

    public Persona(int id, String DNI, String RUC, String Razon, String Direccion, String Telefono, String Celular, String Correo) {
        this.id = id;
        this.DNI = DNI;
        this.RUC = RUC;
        this.Razon = Razon;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Celular = Celular;
        this.Correo = Correo;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String Celular) {
        this.Celular = Celular;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public String getRazon() {
        return Razon;
    }

    public void setRazon(String Razon) {
        this.Razon = Razon;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
