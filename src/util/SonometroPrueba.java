/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import DATOS.ObservacionDAL;
import DATOS.SonometroDAL;
import ENTIDADES.Observacion;
import ENTIDADES.Sonometro;

/**
 *
 * @author rchuc
 */
public class SonometroPrueba {

    public static void main(String[] args) {
        Sonometro obs = new Sonometro();
        obs.setIdCertificado(2);
        obs.setResultado("Aprobado");
        obs.setSonometroValor(22.3);
        
        SonometroDAL data= new SonometroDAL();
        System.out.print(data.registrarDatosObservacion(obs)) ;
    }
}
