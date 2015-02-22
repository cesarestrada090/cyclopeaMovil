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
public class Sonometro {
    private int idCertificado;
    private double sonometroValor;
    private String resultado;

    public int getIdCertificado() {
        return idCertificado;
    }

    public void setIdCertificado(int idCertificado) {
        this.idCertificado = idCertificado;
    }

    public double getSonometroValor() {
        return sonometroValor;
    }

    public void setSonometroValor(double sonometroValor) {
        this.sonometroValor = sonometroValor;
    }

   

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
   
}
