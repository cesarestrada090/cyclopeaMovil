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
public class EquipoCertificado {

    private int idCertificado;
    private int NumFrenometro;
    private int NumAlineador;
    private int NumAnalizador;
    private int NumRegloscopio;
    private int NumBancoSuspension;

    public int getIdCertificado() {
        return idCertificado;
    }

    public void setIdCertificado(int idCertificado) {
        this.idCertificado = idCertificado+1;
    }

    
    
    public int getNumFrenometro() {
        return NumFrenometro;
    }

    public void setNumFrenometro(int NumFrenometro) {
        this.NumFrenometro = NumFrenometro+1;
    }

    public int getNumAlineador() {
        return NumAlineador;
    }

    public void setNumAlineador(int NumAlineador) {
        this.NumAlineador = NumAlineador+1;
    }

    public int getNumAnalizador() {
        return NumAnalizador;
    }

    public void setNumAnalizador(int NumAnalizador) {
        this.NumAnalizador = NumAnalizador+1;
    }

    public int getNumRegloscopio() {
        return NumRegloscopio;
    }

    public void setNumRegloscopio(int NumRegloscopio) {
        this.NumRegloscopio = NumRegloscopio+1;
    }

    public int getNumBancoSuspension() {
        return NumBancoSuspension;
    }

    public void setNumBancoSuspension(int NumBancoSuspension) {
        this.NumBancoSuspension = NumBancoSuspension+1;
    }

   
    
}
