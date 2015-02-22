/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import DATOS.GasometroDAL;
import DATOS.ObservacionDAL;
import ENTIDADES.Gasometro;
import ENTIDADES.Observacion;

/**
 *
 * @author rchuc
 */
public class GasometroPruebas {

    public static void main(String[] args) {
        Gasometro c = new Gasometro();
        c.setCoAcel(32);
        c.setCoCo2Acel(322);
        c.setCoRalent(45);
        c.setCoco2Ralenti(54);
        c.setIdCertificado(442);
        c.setHcAcel(23);
        c.setHcRalentippm(3455);
        c.setOpacidad(3);
        c.setResultado("Aprobado");
        c.setRpm(56);
        c.settAceite(33.2);
        
        GasometroDAL data= new GasometroDAL();
        System.out.print(data.registrarDatosGasometro(c)) ;
    }
}
