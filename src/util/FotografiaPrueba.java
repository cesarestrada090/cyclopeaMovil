/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import DATOS.ObservacionDAL;
import ENTIDADES.Observacion;

/**
 *
 * @author rchuc
 */
public class FotografiaPrueba {

    public static void main(String[] args) {
        Observacion obs = new Observacion();
  
        ObservacionDAL data= new ObservacionDAL();
        System.out.print(data.registrarDatosObservacion(obs)) ;
        
     
        
        
        
    }
}
