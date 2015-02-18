/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ENTIDADES;

/**
 *
 * @author PC
 */
public class exportarexcel {
    private int id;
    private String codigo;
    private String nombres;

    public exportarexcel(int id, String codigo, String nombres) {
        this.id = id;
        this.codigo = codigo;
        this.nombres = nombres;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNombres(){
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
}
