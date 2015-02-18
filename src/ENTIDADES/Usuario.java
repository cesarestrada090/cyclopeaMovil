/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ENTIDADES;

/**
 *
 * @author PC
 */

public class Usuario {
    private String usuario;
    private String contrasenia;
    private int tipo;

    public Usuario(String usuario, String contrasenia, int tipoUsuario) {
        this.usuario=usuario;
        this.contrasenia=contrasenia;
        this.tipo=tipoUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

}
