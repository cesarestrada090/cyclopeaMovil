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
public class Luxometro {
    private int TipoLuz;
    private double medidaDerLuz;
    private double medidaIzqLuz;
    private String alineamientoLuz;
    private String resultadoLuz;

    public int getTipoLuz() {
        return TipoLuz;
    }

    public void setTipoLuz(int TipoLuz) {
        this.TipoLuz = TipoLuz;
    }

    public double getMedidaDerLuz() {
        return medidaDerLuz;
    }

    public void setMedidaDerLuz(double medidaDerLuz) {
        this.medidaDerLuz = medidaDerLuz;
    }

    public double getMedidaIzqLuz() {
        return medidaIzqLuz;
    }

    public void setMedidaIzqLuz(double medidaIzqLuz) {
        this.medidaIzqLuz = medidaIzqLuz;
    }

    public String getAlineamientoLuz() {
        return alineamientoLuz;
    }

    public void setAlineamientoLuz(String alineamientoLuz) {
        this.alineamientoLuz = alineamientoLuz;
    }

    public String getResultadoLuz() {
        return resultadoLuz;
    }

    public void setResultadoLuz(String resultadoLuz) {
        this.resultadoLuz = resultadoLuz;
    }
    
}
