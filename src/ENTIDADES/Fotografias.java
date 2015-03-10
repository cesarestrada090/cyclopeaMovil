/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ENTIDADES;

/**
 *
 * @author KACESE
 */
import java.io.FileInputStream;

/**
 *
 * @author Darwin Durand
 */
public class Fotografias {


    private int tipoDocEval;
    private int numDocEval;    
    private FileInputStream foto1;
    private FileInputStream foto2;
    private FileInputStream foto3;

    public int getTipoDocEval() {
        return tipoDocEval;
    }

    public void setTipoDocEval(int tipoDocEval) {
        this.tipoDocEval = tipoDocEval;
    }

    public int getNumDocEval() {
        return numDocEval;
    }

    public void setNumDocEval(int numDocEval) {
        this.numDocEval = numDocEval;
    }

    public FileInputStream getFoto1() {
        return foto1;
    }

    public void setFoto1(FileInputStream foto1) {
        this.foto1 = foto1;
    }

    public FileInputStream getFoto2() {
        return foto2;
    }

    public void setFoto2(FileInputStream foto2) {
        this.foto2 = foto2;
    }

    public FileInputStream getFoto3() {
        return foto3;
    }

    public void setFoto3(FileInputStream foto3) {
        this.foto3 = foto3;
    }

    
   
}

